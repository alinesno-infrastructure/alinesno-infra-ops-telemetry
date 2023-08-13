# 验证OpenTelemetory 工程示例

```java
 java -javaagent:./lib/opentelemetry-javaagent-1.28.0.jar -Dotel.exporter.otlp.endpoint=http://localhost:4316 -jar target/demo-telemetry-0.0.1-SNAPSHOT.jar
```

## 参考资料

https://opentelemetry.io/docs/specs/otel/protocol/exporter/
