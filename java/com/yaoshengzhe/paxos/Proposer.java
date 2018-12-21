package com.yaoshengzhe.paxos;

import com.google.common.flogger.FluentLogger;
import com.google.inject.Inject;
import com.yaoshengzhe.paxos.log.PersistentLog;

import java.util.List;
import java.util.OptionalLong;

public class Proposer {
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();
    private final PersistentLog<Long> persistentLog;

    @Inject
    Proposer(@Annotations.PersistentLogInstance PersistentLog<Long> persistentLog) {
        this.persistentLog = persistentLog;
    }

    void propose(long proposalNum, OptionalLong value, List<Node> nodes) {
        nodes.forEach(node -> node.accept(proposalNum, value));
    }

    @Override
    public String toString() {
        return String.format("{Proposer}");
    }

}
