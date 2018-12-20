package com.yaoshengzhe.paxos;

import com.google.common.flogger.FluentLogger;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class ClusterMain {
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new PaxosModule(5));
        PaxosGroup paxosGroup = injector.getInstance(PaxosGroup.class);

        logger.atInfo().log("PaxosGroup: " + paxosGroup);
    }
}
