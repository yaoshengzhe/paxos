package com.yaoshengzhe.paxos;

import com.google.inject.Inject;

public class Node {
    private final Proposer proposer;
    private final Acceptor acceptor;
    private int id;

    @Inject
    Node(Proposer proposer, Acceptor acceptor) {
        this.proposer = proposer;
        this.acceptor = acceptor;
    }

    public Node setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return String.format("Node: {Id: %d, Proposer: %s, Acceptor: %s}", id, proposer, acceptor);
    }
}
