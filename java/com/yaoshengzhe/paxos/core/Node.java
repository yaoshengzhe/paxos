package com.yaoshengzhe.paxos.core;

import com.yaoshengzhe.paxos.log.PersistentLog;

import java.util.List;
import java.util.OptionalLong;

public interface Node {
    void propose(long proposalNum, OptionalLong value, List<Node> nodes);

    void onReceiveProposal(long proposalNum, OptionalLong value);

    void down();

    String getAddress();

    PersistentLog<Long> getLog();
}
