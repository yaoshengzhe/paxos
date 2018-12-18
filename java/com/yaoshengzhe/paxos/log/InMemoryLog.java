package com.yaoshengzhe.paxos.log;

import java.util.HashMap;
import java.util.Map;

public class InMemoryLog<T> implements PersistentLog<T> {
    Map<Long, T> data;

    InMemoryLog() {
        data = new HashMap<>();
    }

    @Override
    public void persist(long seqNum, T value) {
        data.putIfAbsent(seqNum, value);
    }
}
