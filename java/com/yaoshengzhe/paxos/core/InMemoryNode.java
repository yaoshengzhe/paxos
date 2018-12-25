package com.yaoshengzhe.paxos.core;

import com.google.common.base.Joiner;
import com.google.common.flogger.FluentLogger;
import com.google.inject.Inject;
import com.yaoshengzhe.paxos.log.PersistentLog;

import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class InMemoryNode implements Node {
    enum State {
        HEALTHY,
        DOWN
    }

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();
    private final Proposer proposer;
    private final Acceptor acceptor;
    private State state;
    private int id;

    @Inject
    InMemoryNode(Proposer proposer, Acceptor acceptor) {
        this.proposer = proposer;
        this.acceptor = acceptor;
        this.state = State.HEALTHY;
    }

    public Node setId(int id) {
        this.id = id;
        proposer.setId(id);
        acceptor.setId(id);
        return this;
    }

    public InMemoryNode setState(State state) {
        logger.atInfo().log("Node[%d] state change, old value: %s, new value: %s.", id,
                this.state, state);
        this.state = state;
        return this;
    }

    @Override
    public void propose(long proposalNum, byte[] initialValue) {
        switch (state) {
            case HEALTHY:
                logger.atInfo().log("Node[%d] made a proposal: {proposalNum: %s, initialValue: " +
                                "%s}.", id,
                        proposalNum, initialValue);
                proposer.propose(proposalNum, initialValue);
                break;
            case DOWN:
                break;
        }
    }

    @Override
    public void onReceiveProposal(long proposalNum) {
        switch (state) {
            case HEALTHY:
                logger.atInfo().log("Node[%d] received a proposal: {proposalNum: %s}."
                        , id, proposalNum);
                acceptor.onReceiveProposal(proposalNum);
                break;
            case DOWN:
                break;
        }
    }

    @Override
    public void onReceiveProposalResponse(long proposalNum, int acceptorId, long highestProposalNum,
                                          Optional<byte[]> valueAccepted,
                                          boolean accepted) {
        switch (state) {
            case HEALTHY:
                logger.atFine().log("Node[%d] received a proposal response: {proposalNum: %s}."
                        , id, proposalNum);
                proposer.onReceiveProposeResponse(proposalNum, acceptorId, highestProposalNum,
                        valueAccepted, accepted);
                break;
            case DOWN:
                break;
        }
    }

    @Override
    public void onReceiveAccept(long proposalNum, byte[] value) {
        switch (state) {
            case HEALTHY:
                logger.atFine().log("Node[%d] received an accept request: {proposalNum: %s, " +
                                "value:" +
                                " %s}."
                        , id, proposalNum, value);
                acceptor.onReceiveAccept(proposalNum, value);
                break;
            case DOWN:
                break;
        }
    }

    @Override
    public void down() {
        setState(State.DOWN);
    }

    @Override
    public void restart() {
        setState(State.HEALTHY);
    }

    @Override
    public void reconfiguration(List<Node> nodes) {
        this.proposer.getTransportClient().setNodes(nodes);
        this.acceptor.getTransportClient().setNodes(nodes);
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public PersistentLog getLog() {
        return acceptor.getLog();
    }

    @Override
    public String toString() {
        return String.format("Node: {\n\tId: %d,\n\tProposer: %s,\n\tAcceptor: %s,\n\tState: " +
                        "%s\n\tCommit Log: %s\n}", id, proposer, acceptor, state,
                Joiner.on(',').join(
                        new TreeMap<>(getLog().asMap()).values().stream()
                                .map(v -> new String(v)).collect(Collectors.toList())));
    }
}
