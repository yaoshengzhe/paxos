package com.yaoshengzhe.paxos;

import com.yaoshengzhe.paxos.log.PersistentLog;

import java.util.List;
import java.util.OptionalLong;

public interface Node {
    void propose(long proposalNum, OptionalLong value, List<Node> nodes);

    void accept(long proposalNum, OptionalLong value);

    void down();

    PersistentLog<Long> getLog();
}
