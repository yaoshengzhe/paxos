package com.yaoshengzhe.paxos;

import com.google.inject.Inject;
import com.yaoshengzhe.paxos.log.PersistentLog;

public class Proposer {
    private final PersistentLog<Long> persistentLog;

    @Inject
    Proposer(@Annotations.PersistentLogInstance PersistentLog<Long> persistentLog) {
        this.persistentLog = persistentLog;
    }

    @Override
    public String toString() {
        return String.format("{Proposer}");
    }

}
