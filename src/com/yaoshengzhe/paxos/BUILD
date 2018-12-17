java_binary(
    name = "ClusterMain",
    srcs = ["ClusterMain.java"],
    main_class = "com.yaoshengzhe.paxos.ClusterMain",
)

java_library(
    name = "acceptor",
    srcs = ["Acceptor.java"],
    deps = ["//src/com/yaoshengzhe/paxos/log:persistent_log"],
)

java_library(
    name = "Node",
    srcs = ["Node.java"]
)