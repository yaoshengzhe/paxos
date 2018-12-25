package com.yaoshengzhe.paxos.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.yaoshengzhe.paxos.PaxosModule;
import org.junit.Test;

import java.util.List;

public class PaxosGroupTest {

    @Test
    public void shouldStart() {
        Injector injector = Guice.createInjector(PaxosModule.newBuilder().setGroupSize(5).build());
        PaxosGroup paxosGroup = injector.getInstance(PaxosGroup.class);
        paxosGroup.start();

        List<Node> nodes = paxosGroup.getNodes();

        nodes.get(0).down();
        paxosGroup.consensus(1, 123, "34897".getBytes());

        System.out.println(paxosGroup);

        nodes.get(0).restart();

        System.out.println("Node[0] restarted...");

        paxosGroup.consensus(3, 234, "9834".getBytes());

        System.out.println(paxosGroup);
    }
}