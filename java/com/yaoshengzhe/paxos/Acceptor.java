package com.yaoshengzhe.paxos;

import com.yaoshengzhe.paxos.log.PersistentLog;

public class Acceptor<T> {
    private final PersistentLog<T> persistentLog;
    private long highestProposalNumReceived = Long.MIN_VALUE;

    Acceptor(PersistentLog<T> persistentLog) {
        this.persistentLog = persistentLog;
    }

    void accept(long proposalNum) {
        highestProposalNumReceived = Math.max(highestProposalNumReceived, proposalNum);
    }

    void accept(long proposalNum, T value) {
        if (highestProposalNumReceived > proposalNum) {
            return;
        }

        if (highestProposalNumReceived == proposalNum) {
            persistentLog.persist(proposalNum, value);
        }

        if (highestProposalNumReceived < proposalNum) {
            highestProposalNumReceived = proposalNum;
            return;
        }
    }
}