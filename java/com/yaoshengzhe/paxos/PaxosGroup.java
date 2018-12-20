package com.yaoshengzhe.paxos;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

public class PaxosGroup {
    private List<Node> nodes;
    private boolean started;

    PaxosGroup() {
        nodes = new ArrayList<>();
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void start() {
        started = true;
    }

    @Override
    public String toString() {
        return Joiner.on('\n').join(nodes);
    }
}
