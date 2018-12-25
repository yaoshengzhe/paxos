package com.yaoshengzhe.paxos.transport;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.yaoshengzhe.paxos.core.Node;

import java.util.List;
import java.util.Optional;

public class InProcessTransportClient implements TransportClient {

    private ImmutableList<Node> nodes;

    @Inject
    public InProcessTransportClient() {
    }


    @Override
    public void setNodes(List<Node> nodes) {
        this.nodes = ImmutableList.copyOf(nodes);
    }

    @Override
    public ImmutableList<Node> getNodes() {
        return nodes;
    }

    @Override
    public void sendProposal(long proposalNum) {
        nodes.forEach(node -> node.onReceiveProposal(proposalNum));
    }

    @Override
    public void responseProposal(long proposalNum, int acceptorId, long highestAcceptedProposalNum,
                                 Optional<byte[]> valueAccepted,
                                 boolean accepted) {
        nodes.forEach(node -> node.onReceiveProposalResponse(proposalNum, acceptorId,
                highestAcceptedProposalNum, valueAccepted,
                accepted));
    }

    @Override
    public void sendAccept(long proposalNum, byte[] value) {
        nodes.forEach(node -> node.onReceiveAccept(proposalNum, value));
    }

    @Override
    public void responseAccept(long proposalNum, int acceptorId, long highestAcceptedProposalNum, Optional<byte[]> valueAccepted, boolean accepted) {
    }
}
