package com.yaoshengzhe.paxos.core;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.yaoshengzhe.paxos.PaxosModule;
import org.junit.Test;

import java.util.List;
import java.util.OptionalLong;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaxosGroupTest {

    @Test
    public void shouldFailToAddNodeAfterStart() {
        Injector injector = Guice.createInjector(PaxosModule.newBuilder().setGroupSize(5).build());
        PaxosGroup paxosGroup = injector.getInstance(PaxosGroup.class);
        paxosGroup.start();

        Node node = injector.getInstance(InMemoryNode.class);

        assertThrows(IllegalStateException.class, () -> paxosGroup.addNode(node));
    }

    @Test
    public void shouldStart() {
        Injector injector = Guice.createInjector(PaxosModule.newBuilder().setGroupSize(5).build());
        PaxosGroup paxosGroup = injector.getInstance(PaxosGroup.class);
        paxosGroup.start();

        List<Node> nodes = paxosGroup.getNodes();

        nodes.get(0).down();
        paxosGroup.propose(1, 0, OptionalLong.of(1));

        System.out.println(paxosGroup);
    }
}