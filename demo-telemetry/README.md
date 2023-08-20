# 验证OpenTelemetory 工程示例

```shell
 java -javaagent:./lib/opentelemetry-javaagent.jar \
	-Dotel.exporter.otlp.endpoint=http://localhost:4316 \
	-Dotel.traces.exporter=otlp \
	-Dotel.metrics.exporter=otlp \
	-Dotel.logs.exporter=otlp \
	-jar target/demo-telemetry-0.0.1-SNAPSHOT.jar
```

## 参考资料

https://opentelemetry.io/docs/specs/otel/protocol/exporter/
