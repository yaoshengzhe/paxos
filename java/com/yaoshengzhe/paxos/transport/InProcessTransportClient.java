package com.yaoshengzhe.paxos.transport;

import com.yaoshengzhe.paxos.core.Node;

import java.util.List;
import java.util.OptionalLong;

public class InProcessTransportClient implements TransportClient {
    @Override
    public void sendProposal(long proposalNum, OptionalLong value, List<Node> nodes) {
        nodes.forEach(node -> node.onReceiveProposal(proposalNum, value));
    }
}
