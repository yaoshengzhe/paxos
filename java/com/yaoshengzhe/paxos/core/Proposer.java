package com.yaoshengzhe.paxos.core;

import com.google.common.flogger.FluentLogger;
import com.google.inject.Inject;
import com.yaoshengzhe.paxos.Annotations.PersistentLogInstance;
import com.yaoshengzhe.paxos.Annotations.TransportClientInstance;
import com.yaoshengzhe.paxos.log.PersistentLog;
import com.yaoshengzhe.paxos.transport.TransportClient;

import java.util.List;
import java.util.OptionalLong;

public class Proposer {
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();
    private final PersistentLog<Long> persistentLog;
    private final TransportClient transportClient;

    @Inject
    Proposer(@PersistentLogInstance PersistentLog<Long> persistentLog,
             @TransportClientInstance TransportClient transportClient) {
        this.persistentLog = persistentLog;
        this.transportClient = transportClient;
    }

    void propose(long proposalNum, OptionalLong value, List<Node> nodes) {
        transportClient.sendProposal(proposalNum, value, nodes);
    }

    @Override
    public String toString() {
        return String.format("{Proposer}");
    }

}
