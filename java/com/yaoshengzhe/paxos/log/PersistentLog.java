package com.yaoshengzhe.paxos.log;

import java.util.Map;

public interface PersistentLog<T> {
    void persist(long seqNum, T value);

    Map<Long, T> asMap();
}
