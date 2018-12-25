package(default_visibility = ["//visibility:public"])

java_binary(
    name = "ClusterMain",
    srcs = ["ClusterMain.java"],
    main_class = "com.yaoshengzhe.paxos.ClusterMain",
    deps = [
        ":paxos_module",
        "//java/com/yaoshengzhe/paxos/core:node",
        "//java/com/yaoshengzhe/paxos/core:paxos_group",
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
    name = "paxos_module",
    srcs = ["PaxosModule.java"],
    deps = [
        ":annotations",
        "//java/com/yaoshengzhe/paxos/core:in_memory_node",
        "//java/com/yaoshengzhe/paxos/core:node",
        "//java/com/yaoshengzhe/paxos/core:paxos_group",
        "//java/com/yaoshengzhe/paxos/log:in_memory_log",
        "//java/com/yaoshengzhe/paxos/log:persistent_log",
        "//java/com/yaoshengzhe/paxos/transport:in_process_transport_client",
        "//java/com/yaoshengzhe/paxos/transport:transport_client",
        "//third_party/guava",
        "//third_party/guice",
    ],
)
