package com.yaoshengzhe.paxos.core;

import com.yaoshengzhe.paxos.log.PersistentLog;

import java.util.List;
import java.util.Optional;

public interface Node {
    // Start a proposal.
    void propose(long proposalNum, byte[] initialValue);

    // Upon receiving an proposal request.
    void onReceiveProposal(long proposalNum);

    // Upon receiving an response for a particular proposal
    void onReceiveProposalResponse(long proposalNum, int acceptorId, long highestProposalNum,
                                   Optional<byte[]> valueAccepted,
                                   boolean accepted);

    // Upon receiving an accept request.
    void onReceiveAccept(long proposalNum, byte[] value);

    void down();

    void restart();

    void reconfiguration(List<Node> nodes);

    String getAddress();

    Node setId(int id);

    PersistentLog getLog();
}
