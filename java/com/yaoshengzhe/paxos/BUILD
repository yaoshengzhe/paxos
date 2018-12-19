java_binary(
    name = "ClusterMain",
    srcs = ["ClusterMain.java"],
    main_class = "com.yaoshengzhe.paxos.ClusterMain",
    deps = [":paxos_module",
            "@com_google_inject_guice_4_0//jar",
            ":node"],
)

java_library(
    name = "annotations",
    srcs = ["Annotations.java"],
    deps = ["@com_google_inject_guice_4_0//jar"],
)

java_library(
    name = "acceptor",
    srcs = ["Acceptor.java"],
    deps = ["//java/com/yaoshengzhe/paxos/log:persistent_log",
            "@com_google_inject_guice_4_0//jar",
            ":annotations"],
)

java_library(
    name = "node",
    srcs = ["Node.java"],
    deps = [":acceptor",
            ":proposer",
            "@javax_inject_1//jar",
            "@com_google_inject_guice_4_0//jar",],
)

java_library(
    name = "paxos_module",
    srcs = ["PaxosModule.java"],
    deps = ["@com_google_inject_guice_4_0//jar",
            "//java/com/yaoshengzhe/paxos/log:persistent_log",
            "//java/com/yaoshengzhe/paxos/log:in_memory_log",
            "@javax_inject_1//jar",
            "@aopalliance_1_0//jar",
            ":annotations"],
)

java_library(
    name = "proposer",
    srcs = ["Proposer.java"],
    deps = ["//java/com/yaoshengzhe/paxos/log:persistent_log",
               "@com_google_inject_guice_4_0//jar",
               ":annotations"],
)