package com.yaoshengzhe.paxos;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.yaoshengzhe.paxos.log.InMemoryLog;
import com.yaoshengzhe.paxos.Annotations.PersistentLogInstance;
import com.yaoshengzhe.paxos.log.PersistentLog;

public class PaxosModule extends AbstractModule {
    @Override
    protected void configure() {
    }

    @Provides
    @PersistentLogInstance
    PersistentLog<Long> providesPersistentLog() {
        return new InMemoryLog<>();
    }
}
