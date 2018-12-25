package com.yaoshengzhe.paxos.transport;

import com.google.common.collect.ImmutableList;
import com.yaoshengzhe.paxos.core.Node;

import java.util.List;
import java.util.Optional;

public interface TransportClient {

    void setNodes(List<Node> nodes);

    ImmutableList<Node> getNodes();

    void sendProposal(long proposalNum);

    void responseProposal(long proposalNum, int acceptorId, long highestAcceptedProposalNum,
                          Optional<byte[]> valueAccepted,
                          boolean accepted);

    void sendAccept(long proposalNum, byte[] value);

    void responseAccept(long proposalNum, int acceptorId, long highestAcceptedProposalNum,
                        Optional<byte[]> valueAccepted,
                        boolean accepted);
}
