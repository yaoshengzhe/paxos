package com.yaoshengzhe.paxos.log;

public interface PersistentLog<T> {
    void persist(long seqNum, T value);
}
