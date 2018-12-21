package com.yaoshengzhe.paxos.transport;

import com.yaoshengzhe.paxos.core.Node;

import java.util.List;
import java.util.OptionalLong;

public interface TransportClient {
    void sendProposal(long proposalNum, OptionalLong value, List<Node> nodes);
}
