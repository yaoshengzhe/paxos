package com.yaoshengzhe.paxos;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;

public class ClusterMain {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new PaxosModule());
        Acceptor<Long> acceptor = injector.getInstance(Key.get(new TypeLiteral<Acceptor<Long>>(){}));
        System.out.println("Acceptor: " + acceptor);
    }
}
