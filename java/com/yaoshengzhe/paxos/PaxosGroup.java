package com.yaoshengzhe.paxos;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;

public class PaxosGroup {
    private List<Node> nodes;
    private boolean started;

    PaxosGroup() {
        nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        if (started) {
            throw new IllegalStateException("PaxosGroup has been started," +
                    "reconfiguration after start has not supported.");
        }
        nodes.add(node);
    }

    public void start() {
        started = true;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void propose(int id, long proposalNum, OptionalLong value) {
        Preconditions.checkArgument(id >= 0 && id < nodes.size());
        nodes.get(id).propose(proposalNum, value, nodes);
    }

    @Override
    public String toString() {
        return Joiner.on('\n').join(nodes);
    }
}
