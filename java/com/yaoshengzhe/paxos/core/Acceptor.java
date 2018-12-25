package com.yaoshengzhe.paxos.core;

import com.google.common.flogger.FluentLogger;
import com.google.inject.Inject;
import com.yaoshengzhe.paxos.Annotations.PersistentLogInstance;
import com.yaoshengzhe.paxos.Annotations.TransportClientInstance;
import com.yaoshengzhe.paxos.log.PersistentLog;
import com.yaoshengzhe.paxos.transport.TransportClient;

import java.util.Optional;

public class Acceptor {
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();
    private final PersistentLog persistentLog;
    private final TransportClient transportClient;
    private int id;
    private long highestProposalNumReceived = Long.MIN_VALUE;
    private long highestProposalNumAccepted = Long.MIN_VALUE;
    private Optional<byte[]> valueAccepted;

    @Inject
    Acceptor(@PersistentLogInstance PersistentLog persistentLog,
             @TransportClientInstance TransportClient transportClient) {
        this.persistentLog = persistentLog;
        this.transportClient = transportClient;
        this.valueAccepted = Optional.empty();
    }

    TransportClient getTransportClient() {
        return transportClient;
    }

    // Update the highestProposalNumReceived iff the received proposalNum is larger.
    void onReceiveProposal(long proposalNum) {
        if (highestProposalNumReceived > proposalNum) {
            transportClient.responseProposal(proposalNum, id, highestProposalNumAccepted,
                    valueAccepted, false);
            return;
        }

        highestProposalNumReceived = proposalNum;
        transportClient.responseProposal(proposalNum, id, highestProposalNumAccepted,
                valueAccepted, true);
    }

    void onReceiveAccept(long proposalNum, byte[] value) {
        logger.atFine().log("Acceptor[%d] received an accept request: {proposalNum: %s, value:" +
                        " %s}."
                , id, proposalNum, value);
        if (highestProposalNumReceived < proposalNum) {
            logger.atInfo().log("Acceptor[%d] reject an accept request: {proposalNum: %s, value:" +
                            " %s}, highestProposalNumReceived: %d"
                    , id, proposalNum, value, highestProposalNumReceived);

            transportClient.responseAccept(proposalNum, id,
                    highestProposalNumAccepted,
                    valueAccepted,
                    false);
            return;
        }

        logger.atInfo().log("Acceptor[%d] accepts the accept request: {proposalNum: %s, value:" +
                        " %s}, old value: %s"
                , id, proposalNum, value, valueAccepted);

        valueAccepted = Optional.of(value);
        persistentLog.persist(0, value);
        highestProposalNumAccepted = Math.max(highestProposalNumAccepted, proposalNum);

        transportClient.responseAccept(proposalNum, id,
                highestProposalNumAccepted,
                valueAccepted,
                true);
    }

    PersistentLog getLog() {
        return persistentLog;
    }

    void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("{highestProposalNumReceived: %d}", highestProposalNumReceived);
    }
}