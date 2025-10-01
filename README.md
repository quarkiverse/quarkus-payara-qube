# Quarkus Payara Qube Extension

[![Version](https://img.shields.io/maven-central/v/io.quarkiverse.payara-qube/quarkus-payara-qube?logo=apache-maven&style=flat-square)](https://central.sonatype.com/artifact/io.quarkiverse.payara-qube/quarkus-payara-qube-parent)

## What is Payara Qube?

Payara Qube is a Java platform for deploying and running Quarkus, Spring Boot, and Jakarta EE applications in production. 
It handles the operational complexity of running Java applications at scale — handling monitoring, logging, scaling, and
configuration management — so you can focus on building features.

Available as a fully managed cloud service or self-hosted on your infrastructure, Payara Qube provides:

- Integrated Monitoring: Built-in dashboards for application metrics, memory usage, and performance tracking
- Centralized Logging: Structured log aggregation with search and filtering capabilities
- Dynamic Configuration: Modify application properties through a web console without rebuilding or redeploying
- Automatic Scaling: Scale applications based on demand with zero downtime deployments

## What This Extension Does

This extension prepares your Quarkus application for the Payara Qube runtime. Add it to your project and it automatically configures:

- JSON-formatted logging in the structure Payara Qube expects for log aggregation and search
- Prometheus metrics endpoint exposing memory, performance, and custom application metrics to the monitoring dashboard
- ZIP packaging containing your application in the deployment format Payara Qube requires
- Runtime configuration sources that allow updating application properties through the Payara Qube console

### Installation

Add the extension to your pom.xml:

```xml
  <dependency>
      <groupId>io.quarkiverse.payara-qube</groupId>
      <artifactId>quarkus-payara-qube</artifactId>
      <version>${quarkus-payara-qube.version}</version>
  </dependency>
```

Build your application as normal. The extension produces a ZIP file in your build output directory ready for deployment.

### Deployment

Upload the ZIP to Payara Qube using:

- Web Console: Drag and drop through the application management interface
- CLI: Deploy via command-line tools for CI/CD integration

Your application runs immediately with full access to monitoring, logging, and configuration management features.

Documentation

Complete documentation: https://docs.payara.fish/cloud/


