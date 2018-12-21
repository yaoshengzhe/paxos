# Paxos

Java implementation for the basic Paxos algorithm described in the paper
"Paxos Made Simple".

Properties

__P1__. An acceptor must accept the first proposal that it receives.

__P1a.__ An acceptor can accept a proposal numbered n iff it has not responded
to a prepare request having a number greater than n.

__P2.__ If a proposal with value v is chosen, then every higher-numbered
proposal that is chosen has value v.

__P2a.__ If a proposal with value v is chosen, then every higher-numbered
proposal accepted by any acceptor has value v.

__P2b.__ If a proposal with value v is chosen, then every higher-numbered
proposal issued by any proposer has value v.

## Invariance to hold:

__P2c.__ For any v and n, if a proposal with value v and number n is issued,
then there is a set S consisting of a majority of acceptors such that either
  * No acceptor in S has accepted any proposal numbered less than n, or
  * v is the value of the highest-numbered proposal among all proposals
  numbered less than n accepted by the acceptors in S.

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
