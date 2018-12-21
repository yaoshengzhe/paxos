package com.yaoshengzhe.paxos.core;

import com.google.common.base.Joiner;
import com.google.common.flogger.FluentLogger;
import com.google.inject.Inject;
import com.yaoshengzhe.paxos.log.PersistentLog;

import java.util.List;
import java.util.OptionalLong;
import java.util.TreeMap;

public class InMemoryNode implements Node {
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
    InMemoryNode(Proposer proposer, Acceptor acceptor) {
        this.proposer = proposer;
        this.acceptor = acceptor;
        this.state = State.HEALTHY;
    }

    public InMemoryNode setId(int id) {
        this.id = id;
        return this;
    }

    public InMemoryNode setState(State state) {
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

    public void onReceiveProposal(long proposalNum, OptionalLong value) {
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
    public String getAddress() {
        return null;
    }

    @Override
    public PersistentLog<Long> getLog() {
        return acceptor.getLog();
    }

    @Override
    public String toString() {
        return String.format("Node: {\n\tId: %d,\n\tProposer: %s,\n\tAcceptor: %s,\n\tState: " +
                        "%s\n\tCommit Log: %s\n}", id, proposer, acceptor, state,
                Joiner.on(',').join(new TreeMap<>(getLog().asMap()).entrySet()));
    }
}
