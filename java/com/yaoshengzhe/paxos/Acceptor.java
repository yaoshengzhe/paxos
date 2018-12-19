package com.yaoshengzhe.paxos;

import com.google.inject.Inject;
import com.yaoshengzhe.paxos.Annotations.PersistentLogInstance;
import com.yaoshengzhe.paxos.log.PersistentLog;

public class Acceptor {
    private final PersistentLog<Long> persistentLog;
    private long highestProposalNumReceived = Long.MIN_VALUE;

    @Inject
    Acceptor(@PersistentLogInstance PersistentLog<Long> persistentLog) {
        this.persistentLog = persistentLog;
    }

    void accept(long proposalNum) {
        highestProposalNumReceived = Math.max(highestProposalNumReceived, proposalNum);
    }

    void accept(long proposalNum, long value) {
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

    @Override
    public String toString() {
        return String.format("{highestProposalNumReceived: %d}", highestProposalNumReceived);
    }
}