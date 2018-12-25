package com.yaoshengzhe.paxos.log;

import java.util.Map;

public interface PersistentLog {
    void persist(long seqNum, byte[] value);

    Map<Long, byte[]> asMap();
}
