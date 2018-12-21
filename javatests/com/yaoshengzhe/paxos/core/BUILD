java_test(
    name = "AcceptorTest",
    srcs = ["AcceptorTest.java"],
    deps = ["//third_party/junit"],
)

java_test(
    name = "InMemoryNodeTest",
    srcs = ["InMemoryNodeTest.java"],
    deps = ["//third_party/junit"],
)

java_test(
    name = "PaxosGroupTest",
    srcs = ["PaxosGroupTest.java"],
    deps = [
        "//java/com/yaoshengzhe/paxos:paxos_module",
        "//java/com/yaoshengzhe/paxos/core:node",
        "//java/com/yaoshengzhe/paxos/core:in_memory_node",
        "//java/com/yaoshengzhe/paxos/core:paxos_group",
        "//third_party/guice",
        "//third_party/junit",
        "//third_party/truth",
    ],
)

java_test(
    name = "ProposerTest",
    srcs = ["ProposerTest.java"],
    deps = ["//third_party/junit"],
)
