package(default_visibility = ["//visibility:public"])

licenses(["notice"])  # Apache 2.0

java_plugin(
    name = "autovalue_plugin",
    processor_class = "com.google.auto.value.processor.AutoValueProcessor",
    deps = ["@com_google_auto_value_1_6_3//jar"],
)

java_library(
    name = "autovalue",
    exported_plugins = [":autovalue_plugin"],
    exports = [
        "@com_google_auto_value_1_6_3//jar",
        "@com_google_auto_value_annotations_1_6_3//jar",
    ],
)
