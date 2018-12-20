package(default_visibility = ["//visibility:public"])

java_binary(
    name = "ClusterMain",
    srcs = ["ClusterMain.java"],
    main_class = "com.yaoshengzhe.paxos.ClusterMain",
    deps = ["//third_party/flogger",
            ":paxos_module",
            "//third_party/guice",
            ":node",
            ":paxos_group"],
)

java_library(
    name = "annotations",
    srcs = ["Annotations.java"],
    deps = ["//third_party/guice"],
)

java_library(
    name = "acceptor",
    srcs = ["Acceptor.java"],
    deps = ["//java/com/yaoshengzhe/paxos/log:persistent_log",
            "//third_party/guice",
            ":annotations"],
)

java_library(
    name = "node",
    srcs = ["Node.java"],
    deps = [":acceptor",
            ":proposer",
            "//third_party/guice",],
)

java_library(
    name = "paxos_module",
    srcs = ["PaxosModule.java"],
    deps = ["//java/com/yaoshengzhe/paxos/log:persistent_log",
            "//java/com/yaoshengzhe/paxos/log:in_memory_log",
            "//third_party/guava",
            "//third_party/guice",
            ":annotations",
            ":node",
            ":paxos_group"],
)

java_library(
    name = "paxos_group",
    srcs = ["PaxosGroup.java"],
    deps = ["//third_party/guava",
            ":node"],
)

java_library(
    name = "proposer",
    srcs = ["Proposer.java"],
    deps = ["//java/com/yaoshengzhe/paxos/log:persistent_log",
               "//third_party/guice",
               ":annotations"],
)