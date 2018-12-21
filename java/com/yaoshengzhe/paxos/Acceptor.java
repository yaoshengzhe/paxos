package com.yaoshengzhe.paxos;

import com.google.common.flogger.FluentLogger;
import com.google.inject.Inject;
import com.yaoshengzhe.paxos.Annotations.PersistentLogInstance;
import com.yaoshengzhe.paxos.log.PersistentLog;

import java.util.OptionalLong;

public class Acceptor {
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();
    private final PersistentLog<Long> persistentLog;
    private long highestProposalNumReceived = Long.MIN_VALUE;

    @Inject
    Acceptor(@PersistentLogInstance PersistentLog<Long> persistentLog) {
        this.persistentLog = persistentLog;
    }

    void accept(long proposalNum, OptionalLong value) {
        if (!value.isPresent()) {
            highestProposalNumReceived = Math.max(highestProposalNumReceived, proposalNum);
            return;
        }

        if (highestProposalNumReceived > proposalNum) {
            return;
        }

        if (highestProposalNumReceived == proposalNum) {
            persistentLog.persist(proposalNum, value.getAsLong());
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