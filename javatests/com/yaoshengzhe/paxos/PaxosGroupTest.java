package com.yaoshengzhe.paxos;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaxosGroupTest {

    @Test
    public void shouldFailToAddNodeAfterStart() {
        Injector injector = Guice.createInjector(new PaxosModule(5));
        PaxosGroup paxosGroup = injector.getInstance(PaxosGroup.class);
        paxosGroup.start();

        Node node = injector.getInstance(Node.class);

        assertThrows(IllegalStateException.class, () -> paxosGroup.addNode(node));
    }

    @Test
    public void shouldStart() {
        Injector injector = Guice.createInjector(new PaxosModule(5));
        PaxosGroup paxosGroup = injector.getInstance(PaxosGroup.class);
        paxosGroup.start();

    }
}