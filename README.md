# Paxos

Java implementation for the basic Paxos algorithm described in the paper "Paxos Made Simple".

Properties

```
P1. An acceptor must accept the first proposal that it receives.
```

```
P1a . An acceptor can accept a proposal numbered n iff it has not responded to a prepare request having a number greater than n.
```

```
P2. If a proposal with value v is chosen, then every higher-numbered proposal that is chosen has value v.
```

```
P2a. If a proposal with value v is chosen, then every higher-numbered proposal accepted by any acceptor has value v.
```

```
P2b. If a proposal with value v is chosen, then every higher-numbered proposal issued by any proposer has value v.
```

Invariance to hold:

```
P2c. For any v and n, if a proposal with value v and number n is issued, then there is a set S consisting of a majority of acceptors such that either
  (a) no acceptor in S has accepted any proposal numbered less than n, or
  (b) v is the value of the highest-numbered proposal among all proposals numbered less than n accepted by the acceptors in S.
```

# How to Build

This project uses [Bazel](https://www.bazel.build) as the build engine.


Build:

``` bash
$ bazel build java/...
```

Test:

``` bash
$ bazel test javatests/...
```

Run:

``` bash
$ bazel run java/com/yaoshengzhe/paxos:ClusterMain
```
