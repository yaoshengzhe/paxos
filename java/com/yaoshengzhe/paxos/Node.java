package com.yaoshengzhe.paxos;

import com.google.inject.Inject;

public class Node {
    private final Proposer proposer;
    private final Acceptor acceptor;

    @Inject
    Node(Proposer proposer, Acceptor acceptor) {
        this.proposer = proposer;
        this.acceptor = acceptor;
    }

    void start() {
    }

    @Override
    public String toString() {
        return String.format("Proposer: %s, Acceptor: %s", proposer, acceptor);
    }
}
