# 验证OpenTelemetory 工程示例

```shell
 java -javaagent:./lib/opentelemetry-javaagent-1.28.0.jar \
        -Dotel.resource.attributes=service.name=demo-telemetry \
        -Dotel.exporter.otlp.headers=Authentication=aip@local \
        -Dotel.exporter.otlp.endpoint=http://localhost:4316 \
        -jar target/demo-telemetry-0.0.1-SNAPSHOT.jar
```

## 参考资料

https://opentelemetry.io/docs/specs/otel/protocol/exporter/
