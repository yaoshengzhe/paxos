package(default_visibility = [
    "//java/com/yaoshengzhe/paxos:__subpackages__",
    "//javatests/com/yaoshengzhe/paxos:__subpackages__",
])

java_library(
    name = "acceptor",
    srcs = ["Acceptor.java"],
    deps = [
        "//java/com/yaoshengzhe/paxos:annotations",
        "//java/com/yaoshengzhe/paxos/log:persistent_log",
        "//java/com/yaoshengzhe/paxos/transport:transport_client",
        "//third_party/flogger",
        "//third_party/guice",
    ],
)

java_library(
    name = "node",
    srcs = ["Node.java"],
    deps = [
        "//java/com/yaoshengzhe/paxos/log:persistent_log",
    ],
)

java_library(
    name = "in_memory_node",
    srcs = ["InMemoryNode.java"],
    deps = [
        ":acceptor",
        ":node",
        ":proposer",
        "//java/com/yaoshengzhe/paxos/log:persistent_log",
        "//third_party/flogger",
        "//third_party/guava",
        "//third_party/guice",
    ],
)

java_library(
    name = "paxos_group",
    srcs = ["PaxosGroup.java"],
    deps = [
        ":node",
        "//third_party/guava",
    ],
)

java_library(
    name = "proposer",
    srcs = ["Proposer.java"],
    deps = [
        ":node",
        "//java/com/yaoshengzhe/paxos:annotations",
        "//java/com/yaoshengzhe/paxos/log:persistent_log",
        "//java/com/yaoshengzhe/paxos/transport:transport_client",
        "//third_party/autovalue",
        "//third_party/flogger",
        "//third_party/guice",
    ],
)
