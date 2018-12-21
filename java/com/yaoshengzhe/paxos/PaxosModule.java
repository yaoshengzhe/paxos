package com.yaoshengzhe.paxos;

import com.google.common.base.Preconditions;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.yaoshengzhe.paxos.Annotations.PersistentLogInstance;
import com.yaoshengzhe.paxos.Annotations.TransportClientInstance;
import com.yaoshengzhe.paxos.core.InMemoryNode;
import com.yaoshengzhe.paxos.core.PaxosGroup;
import com.yaoshengzhe.paxos.log.InMemoryLog;
import com.yaoshengzhe.paxos.log.PersistentLog;
import com.yaoshengzhe.paxos.transport.InProcessTransportClient;
import com.yaoshengzhe.paxos.transport.TransportClient;

public class PaxosModule extends AbstractModule {
    private final int groupSize;

    PaxosModule(int groupSize) {
        this.groupSize = groupSize;
    }

    @Override
    protected void configure() {
    }

    @Provides
    @PersistentLogInstance
    PersistentLog<Long> providesPersistentLog() {
        return new InMemoryLog<>();
    }

    @Provides
    @TransportClientInstance
    TransportClient providesTransportClient() {
        return new InProcessTransportClient();
    }

    @Provides
    PaxosGroup providesPaxosGroup(Provider<InMemoryNode> nodeProvider) {
        PaxosGroup group = new PaxosGroup();
        for (int i = 0; i < groupSize; i++) {
            group.addNode(nodeProvider.get().setId(i));
        }
        return group;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private int groupSize;

        public Builder setGroupSize(int groupSize) {
            Preconditions.checkArgument(groupSize > 0);
            this.groupSize = groupSize;
            return this;
        }

        public PaxosModule build() {
            return new PaxosModule(groupSize);
        }
    }
}
