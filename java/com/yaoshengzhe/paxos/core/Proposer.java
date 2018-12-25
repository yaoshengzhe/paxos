package com.yaoshengzhe.paxos.core;

import com.google.auto.value.AutoValue;
import com.google.common.flogger.FluentLogger;
import com.google.inject.Inject;
import com.yaoshengzhe.paxos.Annotations.PersistentLogInstance;
import com.yaoshengzhe.paxos.Annotations.TransportClientInstance;
import com.yaoshengzhe.paxos.log.PersistentLog;
import com.yaoshengzhe.paxos.transport.TransportClient;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Proposer {
    @AutoValue
    abstract static class AcceptResponse {
        abstract long proposalNum();

        abstract Optional<byte[]> valueAccepted();

        static AcceptResponse create(long proposalNum, Optional<byte[]> valueAccepted) {
            return new AutoValue_Proposer_AcceptResponse(proposalNum, valueAccepted);
        }
    }

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();
    private final PersistentLog persistentLog;
    private final TransportClient transportClient;
    private Map<Integer, AcceptResponse> acceptsReceived;
    private long currentProposalNum;
    private int id;
    private Optional<byte[]> initialValue;
    private Optional<AcceptResponse> responseWithHighestProposalNum;

    @Inject
    Proposer(@PersistentLogInstance PersistentLog persistentLog,
             @TransportClientInstance TransportClient transportClient) {
        this.persistentLog = persistentLog;
        this.transportClient = transportClient;
        this.acceptsReceived = new HashMap<>();
        this.initialValue = Optional.empty();
        this.responseWithHighestProposalNum = Optional.empty();
    }

    TransportClient getTransportClient() {
        return transportClient;
    }

    void propose(long proposalNum, byte[] initialValue) {
        this.currentProposalNum = proposalNum;
        this.initialValue = Optional.ofNullable(initialValue);
        acceptsReceived.clear();
        transportClient.sendProposal(proposalNum);
    }

    void onReceiveProposeResponse(long proposalNum, int acceptorId, long highestProposalNum,
                                  Optional<byte[]> valueAccepted,
                                  boolean accepted) {
        if (currentProposalNum != proposalNum || !accepted) {
            return;
        }

        logger.atInfo().log("Proposer[%d] received an proposal response: " +
                        "{proposalNum: %d, highestProposalNum: %d, valueAccepted: %s}.",
                id, proposalNum,
                highestProposalNum, valueAccepted);

        acceptsReceived.put(acceptorId, AcceptResponse.create(highestProposalNum, valueAccepted));

        if (!responseWithHighestProposalNum.isPresent()) {
            responseWithHighestProposalNum = Optional.of(AcceptResponse.create(highestProposalNum,
                    valueAccepted));
        } else if (responseWithHighestProposalNum.get().proposalNum() < highestProposalNum && valueAccepted.isPresent()) {
            responseWithHighestProposalNum = Optional.of(AcceptResponse.create(highestProposalNum,
                    valueAccepted));
        }

        // Received accepted response from majority.
        if (acceptsReceived.size() >= getGroupSize() / 2 + 1) {
            if (responseWithHighestProposalNum.isPresent() && responseWithHighestProposalNum.get().valueAccepted().isPresent()) {
                logger.atInfo().log("Propose[%d] send an accept request with value from existing " +
                                "acceptors: {proposalNum: %s, value: %s}."
                        , id, proposalNum, responseWithHighestProposalNum.get().valueAccepted().get());

                transportClient.sendAccept(proposalNum,
                        responseWithHighestProposalNum.get().valueAccepted().get());
            } else {
                if (initialValue.isPresent()) {
                    logger.atInfo().log("Propose[%d] send an accept request with value from its " +
                                    "initial value: {proposalNum: %s, value: %s}."
                            , id, proposalNum, initialValue.get());
                    transportClient.sendAccept(proposalNum, initialValue.get());
                }
            }
            acceptsReceived.clear();
            responseWithHighestProposalNum = Optional.empty();
        }
    }

    int getGroupSize() {
        return transportClient.getNodes().size();
    }

    void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("{Proposer}");
    }

}
