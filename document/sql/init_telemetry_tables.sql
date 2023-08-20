-- 创建名为metrics_summary的表，用于存储指标摘要数据
-- 表包含以下列：
-- ResourceAttributes: 资源属性，使用低基数字符串映射和ZSTD(1)编码
-- ResourceSchemaUrl: 资源模式URL，使用ZSTD(1)编码
-- ScopeName: 作用域名称，使用ZSTD(1)编码
-- ScopeVersion: 作用域版本，使用ZSTD(1)编码
-- ScopeAttributes: 作用域属性，使用低基数字符串映射和ZSTD(1)编码
-- ScopeDroppedAttrCount: 作用域删除的属性计数，使用UInt32类型和ZSTD(1)编码
-- ScopeSchemaUrl: 作用域模式URL，使用ZSTD(1)编码
-- MetricName: 指标名称，使用ZSTD(1)编码
-- MetricDescription: 指标描述，使用ZSTD(1)编码
-- MetricUnit: 指标单位，使用ZSTD(1)编码
-- Attributes: 属性，使用低基数字符串映射和ZSTD(1)编码
-- StartTimeUnix: 开始时间戳，使用Delta和ZSTD(1)编码
-- TimeUnix: 时间戳，使用Delta和ZSTD(1)编码
-- Count: 计数，使用UInt64类型、Delta和ZSTD(1)编码
-- Sum: 总和，使用Float64类型和ZSTD(1)编码
-- ValueAtQuantiles: 分位数值，使用嵌套结构，包含Quantile和Value两列，使用ZSTD(1)编码
-- Flags: 标志，使用UInt32类型和ZSTD(1)编码
-- 索引idx_res_attr_key: 对ResourceAttributes列的键创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_res_attr_value: 对ResourceAttributes列的值创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_scope_attr_key: 对ScopeAttributes列的键创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_scope_attr_value: 对ScopeAttributes列的值创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_attr_key: 对Attributes列的键创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_attr_value: 对Attributes列的值创建布隆过滤器索引，误差率为0.01，粒度为1

CREATE TABLE IF NOT EXISTS telemetry_metrics_summary (
    ResourceAttributes Map(LowCardinality(String), String) CODEC(ZSTD(1)),
    ResourceSchemaUrl String CODEC(ZSTD(1)),
    ScopeName String CODEC(ZSTD(1)),
    ScopeVersion String CODEC(ZSTD(1)),
    ScopeAttributes Map(LowCardinality(String), String) CODEC(ZSTD(1)),
    ScopeDroppedAttrCount UInt32 CODEC(ZSTD(1)),
    ScopeSchemaUrl String CODEC(ZSTD(1)),
    MetricName String CODEC(ZSTD(1)),
    MetricDescription String CODEC(ZSTD(1)),
    MetricUnit String CODEC(ZSTD(1)),
    Attributes Map(LowCardinality(String), String) CODEC(ZSTD(1)),
    StartTimeUnix DateTime64(9) CODEC(Delta, ZSTD(1)),
    TimeUnix DateTime64(9) CODEC(Delta, ZSTD(1)),
    Count UInt64 CODEC(Delta, ZSTD(1)),
    Sum Float64 CODEC(ZSTD(1)),
    ValueAtQuantiles Nested(
        Quantile Float64,
        Value Float64
    ) CODEC(ZSTD(1)),
    Flags UInt32  CODEC(ZSTD(1)),
    INDEX idx_res_attr_key mapKeys(ResourceAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_res_attr_value mapValues(ResourceAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_scope_attr_key mapKeys(ScopeAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_scope_attr_value mapValues(ScopeAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_attr_key mapKeys(Attributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_attr_value mapValues(Attributes) TYPE bloom_filter(0.01) GRANULARITY 1
) ENGINE MergeTree() PARTITION BY toDate(TimeUnix)
ORDER BY (MetricName, Attributes, toUnixTimestamp64Nano(TimeUnix))
SETTINGS index_granularity=8192, ttl_only_drop_parts = 1;




-- 创建名为telemetry_sum的表，用于存储遥测摘要数据
-- 表包含以下列：
-- ResourceAttributes: 资源属性，使用低基数字符串映射和ZSTD(1)编码
-- ResourceSchemaUrl: 资源模式URL，使用ZSTD(1)编码
-- ScopeName: 作用域名称，使用ZSTD(1)编码
-- ScopeVersion: 作用域版本，使用ZSTD(1)编码
-- ScopeAttributes: 作用域属性，使用低基数字符串映射和ZSTD(1)编码
-- ScopeDroppedAttrCount: 作用域删除的属性计数，使用UInt32类型和ZSTD(1)编码
-- ScopeSchemaUrl: 作用域模式URL，使用ZSTD(1)编码
-- MetricName: 指标名称，使用ZSTD(1)编码
-- MetricDescription: 指标描述，使用ZSTD(1)编码
-- MetricUnit: 指标单位，使用ZSTD(1)编码
-- Attributes: 属性，使用低基数字符串映射和ZSTD(1)编码
-- StartTimeUnix: 开始时间戳，使用Delta和ZSTD(1)编码
-- TimeUnix: 时间戳，使用Delta和ZSTD(1)编码
-- Value: 值，使用Float64类型和ZSTD(1)编码
-- Flags: 标志，使用UInt32类型和ZSTD(1)编码
-- Exemplars: 示例，使用嵌套结构，包含FilteredAttributes、TimeUnix、Value、SpanId和TraceId五列，使用ZSTD(1)编码
-- AggTemp: 聚合临时值，使用Int32类型和ZSTD(1)编码
-- IsMonotonic: 是否单调，使用Boolean类型、Delta和ZSTD(1)编码
-- 索引idx_res_attr_key: 对ResourceAttributes列的键创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_res_attr_value: 对ResourceAttributes列的值创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_scope_attr_key: 对ScopeAttributes列的键创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_scope_attr_value: 对ScopeAttributes列的值创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_attr_key: 对Attributes列的键创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_attr_value: 对Attributes列的值创建布隆过滤器索引，误差率为0.01，粒度为1

CREATE TABLE IF NOT EXISTS telemetry_metrics_sum (
    ResourceAttributes Map(LowCardinality(String), String) CODEC(ZSTD(1)),
    ResourceSchemaUrl String CODEC(ZSTD(1)),
    ScopeName String CODEC(ZSTD(1)),
    ScopeVersion String CODEC(ZSTD(1)),
    ScopeAttributes Map(LowCardinality(String), String) CODEC(ZSTD(1)),
    ScopeDroppedAttrCount UInt32 CODEC(ZSTD(1)),
    ScopeSchemaUrl String CODEC(ZSTD(1)),
    MetricName String CODEC(ZSTD(1)),
    MetricDescription String CODEC(ZSTD(1)),
    MetricUnit String CODEC(ZSTD(1)),
    Attributes Map(LowCardinality(String), String) CODEC(ZSTD(1)),
    StartTimeUnix DateTime64(9) CODEC(Delta, ZSTD(1)),
    TimeUnix DateTime64(9) CODEC(Delta, ZSTD(1)),
    Value Float64 CODEC(ZSTD(1)),
    Flags UInt32  CODEC(ZSTD(1)),
    Exemplars Nested (
        FilteredAttributes Map(LowCardinality(String), String),
        TimeUnix DateTime64(9),
        Value Float64,
        SpanId String,
        TraceId String
    ) CODEC(ZSTD(1)),
    AggTemp Int32 CODEC(ZSTD(1)),
    IsMonotonic Boolean CODEC(Delta, ZSTD(1)),
    INDEX idx_res_attr_key mapKeys(ResourceAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_res_attr_value mapValues(ResourceAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_scope_attr_key mapKeys(ScopeAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_scope_attr_value mapValues(ScopeAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_attr_key mapKeys(Attributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_attr_value mapValues(Attributes) TYPE bloom_filter(0.01) GRANULARITY 1
) ENGINE MergeTree() PARTITION BY toDate(TimeUnix)
ORDER BY (MetricName, Attributes, toUnixTimestamp64Nano(TimeUnix))
SETTINGS index_granularity=8192, ttl_only_drop_parts = 1;


-- 创建名为telemetry_metrics_histogram的表，用于存储遥测指标的直方图数据
-- 表包含以下列：
-- ResourceAttributes: 资源属性，使用低基数字符串映射和ZSTD(1)编码
-- ResourceSchemaUrl: 资源模式URL，使用ZSTD(1)编码
-- ScopeName: 作用域名称，使用ZSTD(1)编码
-- ScopeVersion: 作用域版本，使用ZSTD(1)编码
-- ScopeAttributes: 作用域属性，使用低基数字符串映射和ZSTD(1)编码
-- ScopeDroppedAttrCount: 作用域删除的属性计数，使用UInt32类型和ZSTD(1)编码
-- ScopeSchemaUrl: 作用域模式URL，使用ZSTD(1)编码
-- MetricName: 指标名称，使用ZSTD(1)编码
-- MetricDescription: 指标描述，使用ZSTD(1)编码
-- MetricUnit: 指标单位，使用ZSTD(1)编码
-- Attributes: 属性，使用低基数字符串映射和ZSTD(1)编码
-- StartTimeUnix: 开始时间戳，使用Delta和ZSTD(1)编码
-- TimeUnix: 时间戳，使用Delta和ZSTD(1)编码
-- Count: 计数，使用Int64类型、Delta和ZSTD(1)编码
-- Sum: 总和，使用Float64类型和ZSTD(1)编码
-- BucketCounts: 桶计数，使用UInt64数组和ZSTD(1)编码
-- ExplicitBounds: 显式边界，使用Float64数组和ZSTD(1)编码
-- Exemplars: 示例，使用嵌套结构，包含FilteredAttributes、TimeUnix、Value、SpanId和TraceId五列，使用ZSTD(1)编码
-- Flags: 标志，使用UInt32类型和ZSTD(1)编码
-- Min: 最小值，使用Float64类型和ZSTD(1)编码
-- Max: 最大值，使用Float64类型和ZSTD(1)编码
-- 索引idx_res_attr_key: 对ResourceAttributes列的键创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_res_attr_value: 对ResourceAttributes列的值创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_scope_attr_key: 对ScopeAttributes列的键创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_scope_attr_value: 对ScopeAttributes列的值创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_attr_key: 对Attributes列的键创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_attr_value: 对Attributes列的值创建布隆过滤器索引，误差率为0.01，粒度为1

CREATE TABLE IF NOT EXISTS telemetry_metrics_histogram (
    ResourceAttributes Map(LowCardinality(String), String) CODEC(ZSTD(1)),
    ResourceSchemaUrl String CODEC(ZSTD(1)),
    ScopeName String CODEC(ZSTD(1)),
    ScopeVersion String CODEC(ZSTD(1)),
    ScopeAttributes Map(LowCardinality(String), String) CODEC(ZSTD(1)),
    ScopeDroppedAttrCount UInt32 CODEC(ZSTD(1)),
    ScopeSchemaUrl String CODEC(ZSTD(1)),
    MetricName String CODEC(ZSTD(1)),
    MetricDescription String CODEC(ZSTD(1)),
    MetricUnit String CODEC(ZSTD(1)),
    Attributes Map(LowCardinality(String), String) CODEC(ZSTD(1)),
    StartTimeUnix DateTime64(9) CODEC(Delta, ZSTD(1)),
    TimeUnix DateTime64(9) CODEC(Delta, ZSTD(1)),
    Count Int64 CODEC(Delta, ZSTD(1)),
    Sum Float64 CODEC(ZSTD(1)),
    BucketCounts Array(UInt64) CODEC(ZSTD(1)),
    ExplicitBounds Array(Float64) CODEC(ZSTD(1)),
    Exemplars Nested (
        FilteredAttributes Map(LowCardinality(String), String),
        TimeUnix DateTime64(9),
        Value Float64,
        SpanId String,
        TraceId String
    ) CODEC(ZSTD(1)),
    Flags UInt32 CODEC(ZSTD(1)),
    Min Float64 CODEC(ZSTD(1)),
    Max Float64 CODEC(ZSTD(1)),
    INDEX idx_res_attr_key mapKeys(ResourceAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_res_attr_value mapValues(ResourceAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_scope_attr_key mapKeys(ScopeAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_scope_attr_value mapValues(ScopeAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_attr_key mapKeys(Attributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_attr_value mapValues(Attributes) TYPE bloom_filter(0.01) GRANULARITY 1
) ENGINE MergeTree() PARTITION BY toDate(TimeUnix)
ORDER BY (MetricName, Attributes, toUnixTimestamp64Nano(TimeUnix))
SETTINGS index_granularity=8192, ttl_only_drop_parts = 1;


-- 创建名为telemetry_metrics_gauge的表，用于存储遥测指标的仪表盘数据
-- 表包含以下列：
-- ResourceAttributes: 资源属性，使用低基数字符串映射和ZSTD(1)编码
-- ResourceSchemaUrl: 资源模式URL，使用ZSTD(1)编码
-- ScopeName: 作用域名称，使用ZSTD(1)编码
-- ScopeVersion: 作用域版本，使用ZSTD(1)编码
-- ScopeAttributes: 作用域属性，使用低基数字符串映射和ZSTD(1)编码
-- ScopeDroppedAttrCount: 作用域删除的属性计数，使用UInt32类型和ZSTD(1)编码
-- ScopeSchemaUrl: 作用域模式URL，使用ZSTD(1)编码
-- MetricName: 指标名称，使用ZSTD(1)编码
-- MetricDescription: 指标描述，使用ZSTD(1)编码
-- MetricUnit: 指标单位，使用ZSTD(1)编码
-- Attributes: 属性，使用低基数字符串映射和ZSTD(1)编码
-- StartTimeUnix: 开始时间戳，使用Delta和ZSTD(1)编码
-- TimeUnix: 时间戳，使用Delta和ZSTD(1)编码
-- Value: 值，使用Float64类型和ZSTD(1)编码
-- Exemplars: 示例，使用嵌套结构，包含FilteredAttributes、TimeUnix、Value、SpanId和TraceId五列，使用ZSTD(1)编码
-- 索引idx_res_attr_key: 对ResourceAttributes列的键创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_res_attr_value: 对ResourceAttributes列的值创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_scope_attr_key: 对ScopeAttributes列的键创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_scope_attr_value: 对ScopeAttributes列的值创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_attr_key: 对Attributes列的键创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_attr_value: 对Attributes列的值创建布隆过滤器索引，误差率为0.01，粒度为1

CREATE TABLE IF NOT EXISTS telemetry_metrics_gauge (
    ResourceAttributes Map(LowCardinality(String), String) CODEC(ZSTD(1)),
    ResourceSchemaUrl String CODEC(ZSTD(1)),
    ScopeName String CODEC(ZSTD(1)),
    ScopeVersion String CODEC(ZSTD(1)),
    ScopeAttributes Map(LowCardinality(String), String) CODEC(ZSTD(1)),
    ScopeDroppedAttrCount UInt32 CODEC(ZSTD(1)),
    ScopeSchemaUrl String CODEC(ZSTD(1)),
    MetricName String CODEC(ZSTD(1)),
    MetricDescription String CODEC(ZSTD(1)),
    MetricUnit String CODEC(ZSTD(1)),
    Attributes Map(LowCardinality(String), String) CODEC(ZSTD(1)),
    StartTimeUnix DateTime64(9) CODEC(Delta, ZSTD(1)),
    TimeUnix DateTime64(9) CODEC(Delta, ZSTD(1)),
    Value Float64 CODEC(ZSTD(1)),
    Flags UInt32 CODEC(ZSTD(1)),
    Exemplars Nested (
        FilteredAttributes Map(LowCardinality(String), String),
        TimeUnix DateTime64(9),
        Value Float64,
        SpanId String,
        TraceId String
    ) CODEC(ZSTD(1)),
    INDEX idx_res_attr_key mapKeys(ResourceAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_res_attr_value mapValues(ResourceAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_scope_attr_key mapKeys(ScopeAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_scope_attr_value mapValues(ScopeAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_attr_key mapKeys(Attributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_attr_value mapValues(Attributes) TYPE bloom_filter(0.01) GRANULARITY 1
) ENGINE MergeTree() PARTITION BY toDate(TimeUnix)
ORDER BY (MetricName, Attributes, toUnixTimestamp64Nano(TimeUnix))
SETTINGS index_granularity=8192, ttl_only_drop_parts = 1;


-- 创建名为telemetry_metrics_exponential_histogram的表，用于存储指数直方图的遥测指标数据
-- 表包含以下列：
-- ResourceAttributes: 资源属性，使用低基数字符串映射和ZSTD(1)编码
-- ResourceSchemaUrl: 资源模式URL，使用ZSTD(1)编码
-- ScopeName: 作用域名称，使用ZSTD(1)编码
-- ScopeVersion: 作用域版本，使用ZSTD(1)编码
-- ScopeAttributes: 作用域属性，使用低基数字符串映射和ZSTD(1)编码
-- ScopeDroppedAttrCount: 作用域删除的属性计数，使用UInt32类型和ZSTD(1)编码
-- ScopeSchemaUrl: 作用域模式URL，使用ZSTD(1)编码
-- MetricName: 指标名称，使用ZSTD(1)编码
-- MetricDescription: 指标描述，使用ZSTD(1)编码
-- MetricUnit: 指标单位，使用ZSTD(1)编码
-- Attributes: 属性，使用低基数字符串映射和ZSTD(1)编码
-- StartTimeUnix: 开始时间戳，使用Delta和ZSTD(1)编码
-- TimeUnix: 时间戳，使用Delta和ZSTD(1)编码
-- Count: 计数，使用Delta和ZSTD(1)编码
-- Sum: 总和，使用Float64类型和ZSTD(1)编码
-- Scale: 缩放值，使用Int32类型和ZSTD(1)编码
-- ZeroCount: 零计数，使用UInt64类型和ZSTD(1)编码
-- PositiveOffset: 正偏移量，使用Int32类型和ZSTD(1)编码
-- PositiveBucketCounts: 正桶计数，使用UInt64数组和ZSTD(1)编码
-- NegativeOffset: 负偏移量，使用Int32类型和ZSTD(1)编码
-- NegativeBucketCounts: 负桶计数，使用UInt64数组和ZSTD(1)编码
-- Exemplars: 示例，使用嵌套结构，包含FilteredAttributes、TimeUnix、Value、SpanId和TraceId五列，使用ZSTD(1)编码
-- Flags: 标志，使用UInt32类型和ZSTD(1)编码
-- Min: 最小值，使用Float64类型和ZSTD(1)编码
-- Max: 最大值，使用Float64类型和ZSTD(1)编码
-- 索引idx_res_attr_key: 对ResourceAttributes列的键创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_res_attr_value: 对ResourceAttributes列的值创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_scope_attr_key: 对ScopeAttributes列的键创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_scope_attr_value: 对ScopeAttributes列的值创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_attr_key: 对Attributes列的键创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_attr_value: 对Attributes列的值创建布隆过滤器索引，误差率为0.01，粒度为1

CREATE TABLE IF NOT EXISTS telemetry_metrics_exponential_histogram (
    ResourceAttributes Map(LowCardinality(String), String) CODEC(ZSTD(1)),
    ResourceSchemaUrl String CODEC(ZSTD(1)),
    ScopeName String CODEC(ZSTD(1)),
    ScopeVersion String CODEC(ZSTD(1)),
    ScopeAttributes Map(LowCardinality(String), String) CODEC(ZSTD(1)),
    ScopeDroppedAttrCount UInt32 CODEC(ZSTD(1)),
    ScopeSchemaUrl String CODEC(ZSTD(1)),
    MetricName String CODEC(ZSTD(1)),
    MetricDescription String CODEC(ZSTD(1)),
    MetricUnit String CODEC(ZSTD(1)),
    Attributes Map(LowCardinality(String), String) CODEC(ZSTD(1)),
    StartTimeUnix DateTime64(9) CODEC(Delta, ZSTD(1)),
    TimeUnix DateTime64(9) CODEC(Delta, ZSTD(1)),
    Count Int64 CODEC(Delta, ZSTD(1)),
    Sum Float64 CODEC(ZSTD(1)),
    Scale Int32 CODEC(ZSTD(1)),
    ZeroCount UInt64 CODEC(ZSTD(1)),
    PositiveOffset Int32 CODEC(ZSTD(1)),
    PositiveBucketCounts Array(UInt64) CODEC(ZSTD(1)),
    NegativeOffset Int32 CODEC(ZSTD(1)),
    NegativeBucketCounts Array(UInt64) CODEC(ZSTD(1)),
    Exemplars Nested (
        FilteredAttributes Map(LowCardinality(String), String),
        TimeUnix DateTime64(9),
        Value Float64,
        SpanId String,
        TraceId String
    ) CODEC(ZSTD(1)),
    Flags UInt32  CODEC(ZSTD(1)),
    Min Float64 CODEC(ZSTD(1)),
    Max Float64 CODEC(ZSTD(1)),
    INDEX idx_res_attr_key mapKeys(ResourceAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_res_attr_value mapValues(ResourceAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_scope_attr_key mapKeys(ScopeAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_scope_attr_value mapValues(ScopeAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_attr_key mapKeys(Attributes) TYPE bloom_filter(0.01) GRANULARITY 1,
    INDEX idx_attr_value mapValues(Attributes) TYPE bloom_filter(0.01) GRANULARITY 1
) ENGINE MergeTree() PARTITION BY toDate(TimeUnix)
ORDER BY (MetricName, Attributes, toUnixTimestamp64Nano(TimeUnix))
SETTINGS index_granularity=8192, ttl_only_drop_parts = 1;


-- 创建名为telemetry_trace的表，用于存储遥测跟踪数据
-- 表包含以下列：
-- Timestamp: 时间戳，使用Delta和ZSTD(1)编码
-- TraceId: 追踪ID，使用ZSTD(1)编码
-- SpanId: 跨度ID，使用ZSTD(1)编码
-- ParentSpanId: 父跨度ID，使用ZSTD(1)编码
-- TraceState: 追踪状态，使用ZSTD(1)编码
-- SpanName: 跨度名称，使用低基数字符串映射和ZSTD(1)编码
-- SpanKind: 跨度类型，使用低基数字符串映射和ZSTD(1)编码
-- ServiceName: 服务名称，使用低基数字符串映射和ZSTD(1)编码
-- ResourceAttributes: 资源属性，使用低基数字符串映射和ZSTD(1)编码
-- ScopeName: 作用域名称，使用ZSTD(1)编码
-- ScopeVersion: 作用域版本，使用ZSTD(1)编码
-- SpanAttributes: 跨度属性，使用低基数字符串映射和ZSTD(1)编码
-- Duration: 持续时间，使用Int64类型和ZSTD(1)编码
-- StatusCode: 状态码，使用低基数字符串映射和ZSTD(1)编码
-- StatusMessage: 状态信息，使用ZSTD(1)编码
-- Events: 事件，使用嵌套结构，包含Timestamp、Name和Attributes三列，使用ZSTD(1)编码
-- Links: 链接，使用嵌套结构，包含TraceId、SpanId、TraceState和Attributes四列，使用ZSTD(1)编码
-- 索引idx_trace_id: 对TraceId列创建布隆过滤器索引，误差率为0.001，粒度为1
-- 索引idx_res_attr_key: 对ResourceAttributes列的键创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_res_attr_value: 对ResourceAttributes列的值创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_span_attr_key: 对SpanAttributes列的键创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_span_attr_value: 对SpanAttributes列的值创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_duration: 对Duration列创建最小最大索引，粒度为1

CREATE TABLE IF NOT EXISTS telemetry_trace(
     Timestamp DateTime64(9) CODEC(Delta, ZSTD(1)),
     TraceId String CODEC(ZSTD(1)),
     SpanId String CODEC(ZSTD(1)),
     ParentSpanId String CODEC(ZSTD(1)),
     TraceState String CODEC(ZSTD(1)),
     SpanName LowCardinality(String) CODEC(ZSTD(1)),
     SpanKind LowCardinality(String) CODEC(ZSTD(1)),
     ServiceName LowCardinality(String) CODEC(ZSTD(1)),
     ResourceAttributes Map(LowCardinality(String), String) CODEC(ZSTD(1)),
     ScopeName String CODEC(ZSTD(1)),
     ScopeVersion String CODEC(ZSTD(1)),
     SpanAttributes Map(LowCardinality(String), String) CODEC(ZSTD(1)),
     Duration Int64 CODEC(ZSTD(1)),
     StatusCode LowCardinality(String) CODEC(ZSTD(1)),
     StatusMessage String CODEC(ZSTD(1)),
     Events Nested (
         Timestamp DateTime64(9),
         Name LowCardinality(String),
         Attributes Map(LowCardinality(String), String)
     ) CODEC(ZSTD(1)),
     Links Nested (
         TraceId String,
         SpanId String,
         TraceState String,
         Attributes Map(LowCardinality(String), String)
     ) CODEC(ZSTD(1)),
     INDEX idx_trace_id TraceId TYPE bloom_filter(0.001) GRANULARITY 1,
     INDEX idx_res_attr_key mapKeys(ResourceAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
     INDEX idx_res_attr_value mapValues(ResourceAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
     INDEX idx_span_attr_key mapKeys(SpanAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
     INDEX idx_span_attr_value mapValues(SpanAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
     INDEX idx_duration Duration TYPE minmax GRANULARITY 1
) ENGINE MergeTree()
PARTITION BY toDate(Timestamp)
ORDER BY (ServiceName, SpanName, toUnixTimestamp(Timestamp), TraceId)
SETTINGS index_granularity=8192, ttl_only_drop_parts = 1;


-- 创建名为telemetry_trace_id_ts的表，用于存储遥测跟踪数据
-- 表包含以下列：
-- TraceId: 跟踪ID，使用ZSTD(1)编码
-- Start: 跟踪开始时间，使用Delta和ZSTD(1)编码
-- End: 跟踪结束时间，使用Delta和ZSTD(1)编码
-- 索引idx_trace_id: 对TraceId列创建布隆过滤器索引，误差率为0.01，粒度为1

CREATE TABLE IF NOT EXISTS telemetry_trace_id_ts (
     TraceId String CODEC(ZSTD(1)),
     Start DateTime64(9) CODEC(Delta, ZSTD(1)),
     End DateTime64(9) CODEC(Delta, ZSTD(1)),
     INDEX idx_trace_id TraceId TYPE bloom_filter(0.01) GRANULARITY 1
) ENGINE MergeTree() ORDER BY (TraceId, toUnixTimestamp(Start))
SETTINGS index_granularity=8192;



-- 创建名为telemetry_logs的表，用于存储遥测日志数据
-- 表包含以下列：
-- Timestamp: 时间戳，使用Delta和ZSTD(1)编码
-- TraceId: 跟踪ID，使用ZSTD(1)编码
-- SpanId: 跨度ID，使用ZSTD(1)编码
-- TraceFlags: 跟踪标志，使用UInt32类型和ZSTD(1)编码
-- SeverityText: 严重性文本，使用低基数字符串和ZSTD(1)编码
-- SeverityNumber: 严重性数字，使用Int32类型和ZSTD(1)编码
-- ServiceName: 服务名称，使用低基数字符串和ZSTD(1)编码
-- Body: 日志内容，使用ZSTD(1)编码
-- ResourceSchemaUrl: 资源模式URL，使用ZSTD(1)编码
-- ResourceAttributes: 资源属性，使用低基数字符串映射和ZSTD(1)编码
-- ScopeSchemaUrl: 作用域模式URL，使用ZSTD(1)编码
-- ScopeName: 作用域名称，使用ZSTD(1)编码
-- ScopeVersion: 作用域版本，使用ZSTD(1)编码
-- ScopeAttributes: 作用域属性，使用低基数字符串映射和ZSTD(1)编码
-- LogAttributes: 日志属性，使用低基数字符串映射和ZSTD(1)编码
-- 索引idx_trace_id: 对TraceId列创建布隆过滤器索引，误差率为0.001，粒度为1
-- 索引idx_res_attr_key: 对ResourceAttributes列的键创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_res_attr_value: 对ResourceAttributes列的值创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_scope_attr_key: 对ScopeAttributes列的键创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_scope_attr_value: 对ScopeAttributes列的值创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_log_attr_key: 对LogAttributes列的键创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_log_attr_value: 对LogAttributes列的值创建布隆过滤器索引，误差率为0.01，粒度为1
-- 索引idx_body: 对Body列创建tokenbf_v1索引，参数为(32768, 3, 0)，粒度为1

CREATE TABLE IF NOT EXISTS telemetry_logs (
     Timestamp DateTime64(9) CODEC(Delta, ZSTD(1)),
     TraceId String CODEC(ZSTD(1)),
     SpanId String CODEC(ZSTD(1)),
     TraceFlags UInt32 CODEC(ZSTD(1)),
     SeverityText LowCardinality(String) CODEC(ZSTD(1)),
     SeverityNumber Int32 CODEC(ZSTD(1)),
     ServiceName LowCardinality(String) CODEC(ZSTD(1)),
     Body String CODEC(ZSTD(1)),
     ResourceSchemaUrl String CODEC(ZSTD(1)),
     ResourceAttributes Map(LowCardinality(String), String) CODEC(ZSTD(1)),
     ScopeSchemaUrl String CODEC(ZSTD(1)),
     ScopeName String CODEC(ZSTD(1)),
     ScopeVersion String CODEC(ZSTD(1)),
     ScopeAttributes Map(LowCardinality(String), String) CODEC(ZSTD(1)),
     LogAttributes Map(LowCardinality(String), String) CODEC(ZSTD(1)),
     INDEX idx_trace_id TraceId TYPE bloom_filter(0.001) GRANULARITY 1,
     INDEX idx_res_attr_key mapKeys(ResourceAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
     INDEX idx_res_attr_value mapValues(ResourceAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
     INDEX idx_scope_attr_key mapKeys(ScopeAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
     INDEX idx_scope_attr_value mapValues(ScopeAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
     INDEX idx_log_attr_key mapKeys(LogAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
     INDEX idx_log_attr_value mapValues(LogAttributes) TYPE bloom_filter(0.01) GRANULARITY 1,
     INDEX idx_body Body TYPE tokenbf_v1(32768, 3, 0) GRANULARITY 1
) ENGINE MergeTree() PARTITION BY toDate(Timestamp)
ORDER BY (ServiceName, SeverityText, toUnixTimestamp(Timestamp), TraceId)
SETTINGS index_granularity=8192, ttl_only_drop_parts = 1;



