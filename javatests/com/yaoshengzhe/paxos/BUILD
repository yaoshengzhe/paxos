java_test(
    name = "AcceptorTest",
    srcs = ["AcceptorTest.java"],
    deps = ["@org_junit_junit_4_12//jar"],
)

java_test(
    name = "NodeTest",
    srcs = ["NodeTest.java"],
    deps = ["@org_junit_junit_4_12//jar"],
)

java_test(
    name = "PaxosGroupTest",
    srcs = ["PaxosGroupTest.java"],
    deps = ["@org_junit_jupiter_5_3_2//jar",
            "//java/com/yaoshengzhe/paxos:paxos_group",
            "@com_google_inject_guice_4_0//jar",
            "//java/com/yaoshengzhe/paxos:paxos_module",
            "//java/com/yaoshengzhe/paxos:node",
            "@com_google_truth_0_42//jar",
            "@org_opentest4j_1_1_1//jar",
            "@org_apiguardian_1_0_0//jar"],
)

java_test(
    name = "ProposerTest",
    srcs = ["ProposerTest.java"],
    deps = ["@org_junit_junit_4_12//jar"],
)