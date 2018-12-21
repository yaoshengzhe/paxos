java_test(
    name = "AcceptorTest",
    srcs = ["AcceptorTest.java"],
    deps = ["//third_party/junit"],
)

java_test(
    name = "NodeImplTest",
    srcs = ["NodeImplTest.java"],
    deps = ["//third_party/junit"],
)

java_test(
    name = "PaxosGroupTest",
    srcs = ["PaxosGroupTest.java"],
    deps = [
        "//java/com/yaoshengzhe/paxos:node",
        "//java/com/yaoshengzhe/paxos:node_impl",
        "//java/com/yaoshengzhe/paxos:paxos_group",
        "//java/com/yaoshengzhe/paxos:paxos_module",
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
