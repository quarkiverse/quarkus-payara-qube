# Quarkus Payara Qube Extension

[![Version](https://img.shields.io/maven-central/v/io.quarkiverse.payara-qube/quarkus-payara-qube?logo=apache-maven&style=flat-square)](https://central.sonatype.com/artifact/io.quarkiverse.payara-qube/quarkus-payara-qube-parent)

## Deploy Quarkus Applications to Payara Qube

Payara Qube is a managed and hosted solution for managing the lifecycle of Java applications in Kubernetes clusters.

This extension sets up few aspects of the application that are required for it to nicely integrate into Qube's runtime.

The extension affects following things:

* Log output will be set to JSON in the format that Qube expects
* A Prometheus metric endpoint will be exposed for collecting memory usage
* The resulting application will be packaged as a Zip file
* Additional configuration sources will be added to apply user-specified properties from the UI

