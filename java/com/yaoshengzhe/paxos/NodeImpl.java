package com.yaoshengzhe.paxos;

import com.google.common.flogger.FluentLogger;
import com.google.inject.Inject;

import java.util.List;
import java.util.OptionalLong;

public class NodeImpl implements Node {
    enum State {
        HEALTHY,
        PARTITIONED,
        DOWN
    }

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();
    private final Proposer proposer;
    private final Acceptor acceptor;
    private State state;
    private int id;

    @Inject
    NodeImpl(Proposer proposer, Acceptor acceptor) {
        this.proposer = proposer;
        this.acceptor = acceptor;
        this.state = State.HEALTHY;
    }

    public NodeImpl setId(int id) {
        this.id = id;
        return this;
    }

    public NodeImpl setState(State state) {
        logger.atInfo().log("Node[%d] state change, old value: %s, new value: %s.", id,
                this.state, state);
        this.state = state;
        return this;
    }

    public void propose(long proposalNum, OptionalLong value, List<Node> nodes) {
        switch (state) {
            case HEALTHY:
            case PARTITIONED:
                logger.atInfo().log("Node[%d] made a proposal: {proposalNum: %s, value: " +
                        "%s}.", id, proposalNum, value);
                proposer.propose(proposalNum, value, nodes);
                break;
            case DOWN:
                break;
        }
    }

    public void accept(long proposalNum, OptionalLong value) {
        switch (state) {
            case HEALTHY:
            case PARTITIONED:
                logger.atInfo().log("Node[%d] received a proposal: {proposalNum: %s, value: " +
                                "%s}."
                        , id, proposalNum, value);
                acceptor.accept(proposalNum, value);
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
    public String toString() {
        return String.format("Node: {\n\tId: %d,\n\tProposer: %s,\n\tAcceptor: %s,\n\tState: " +
                "%s\n}", id, proposer, acceptor, state);
    }
}
