package com.yaoshengzhe.paxos.core;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import java.util.List;

public class PaxosGroup {
    private final ImmutableList<Node> nodes;
    private boolean started;

    public PaxosGroup(List<Node> nodes) {
        this.nodes = ImmutableList.copyOf(nodes);
    }

    public void start() {
        reconfiguration();
        started = true;
    }

    public void consensus(int id, long proposalNum, byte[] value) {
        Preconditions.checkArgument(id >= 0 && id < nodes.size());
        Preconditions.checkState(started, "PaxosGroup has NOT been started.");

        nodes.get(id).propose(proposalNum, value);

    }

    public ImmutableList<Node> getNodes() {
        return nodes;
    }

    private void reconfiguration() {
        nodes.forEach(node -> node.reconfiguration(nodes));
    }

    @Override
    public String toString() {
        return Joiner.on('\n').join(nodes);
    }
}
