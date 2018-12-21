package(default_visibility = ["//java/com/yaoshengzhe/paxos:__subpackages__"])

java_library(
    name = "transport_client",
    srcs = ["TransportClient.java"],
    deps = [
        "//java/com/yaoshengzhe/paxos:node",
    ],
)

java_library(
    name = "in_process_transport_client",
    srcs = ["InProcessTransportClient.java"],
    deps = [
        ":transport_client",
        "//java/com/yaoshengzhe/paxos:node",
    ],
)