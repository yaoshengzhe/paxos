package com.yaoshengzhe.paxos;

import com.google.inject.Inject;

public class Node {
    enum State {
        HEALTHY,
        PARTITIONED,
        DOWN
    }

    private final Proposer proposer;
    private final Acceptor acceptor;
    private State state;
    private int id;

    @Inject
    Node(Proposer proposer, Acceptor acceptor) {
        this.proposer = proposer;
        this.acceptor = acceptor;
        this.state = State.HEALTHY;
    }

    public Node setId(int id) {
        this.id = id;
        return this;
    }

    public Node setState(State state) {
        this.state = state;
        return this;
    }

    @Override
    public String toString() {
        return String.format("Node: {Id: %d, Proposer: %s, Acceptor: %s}", id, proposer, acceptor);
    }
}
