package com.yaoshengzhe.paxos;

import java.util.List;
import java.util.OptionalLong;

public interface Node {
    void propose(long proposalNum, OptionalLong value, List<Node> nodes);

    void accept(long proposalNum, OptionalLong value);

    void down();
}
