package com.yaoshengzhe.paxos;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class ClusterMain {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new PaxosModule());
        Node node = injector.getInstance(Node.class);
        System.out.println("Node: " + node);
    }
}
