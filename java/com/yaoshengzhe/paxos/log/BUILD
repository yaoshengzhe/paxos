package(default_visibility = ["//java/com/yaoshengzhe/paxos:__subpackages__"])

java_library(
    name = "in_memory_log",
    srcs = ["InMemoryLog.java"],
    deps = [":persistent_log",
            "//third_party/guava"],
)

java_library(
    name = "persistent_log",
    srcs = ["PersistentLog.java"],
    deps = ["//third_party/guava"],
)
