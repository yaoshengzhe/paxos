package com.yaoshengzhe.paxos.log;

import java.util.HashMap;
import java.util.Map;

public class InMemoryLog implements PersistentLog {
    Map<Long, byte[]> data;

    public InMemoryLog() {
        data = new HashMap<>();
    }

    @Override
    public void persist(long seqNum, byte[] value) {
        data.putIfAbsent(seqNum, value);
    }

    @Override
    public Map<Long, byte[]> asMap() {
        return data;
    }
}
