package(default_visibility = ["//visibility:public"])

java_binary(
    name = "ClusterMain",
    srcs = ["ClusterMain.java"],
    main_class = "com.yaoshengzhe.paxos.ClusterMain",
    deps = [
        ":node",
        ":paxos_group",
        ":paxos_module",
        "//third_party/flogger",
        "//third_party/guice",
    ],
)

java_library(
    name = "annotations",
    srcs = ["Annotations.java"],
    deps = ["//third_party/guice"],
)

java_library(
    name = "acceptor",
    srcs = ["Acceptor.java"],
    deps = [
        ":annotations",
        "//java/com/yaoshengzhe/paxos/log:persistent_log",
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
    name = "node_impl",
    srcs = ["NodeImpl.java"],
    deps = [
        ":acceptor",
        ":proposer",
        ":node",
        "//java/com/yaoshengzhe/paxos/log:persistent_log",
        "//third_party/flogger",
        "//third_party/guava",
        "//third_party/guice",
    ],
)

java_library(
    name = "paxos_module",
    srcs = ["PaxosModule.java"],
    deps = [
        ":annotations",
        ":node_impl",
        ":paxos_group",
        "//java/com/yaoshengzhe/paxos/log:in_memory_log",
        "//java/com/yaoshengzhe/paxos/log:persistent_log",
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
        ":annotations",
        ":node",
        "//java/com/yaoshengzhe/paxos/log:persistent_log",
        "//third_party/flogger",
        "//third_party/guice",
    ],
)
