package com.alinesno.infra.ops.telemetry.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnComment;
import com.gitee.sunchenbin.mybatis.actable.annotation.ColumnType;
import lombok.Data;
import com.alinesno.infra.common.facade.mapper.entity.InfraBaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

/**
 * 数据点实体类。
 *
 * @author luoxiaodong
 * @version 1.0.0
 */
@TableName("metrics_data_point")
@Data
public class MetricsDataPoint extends InfraBaseEntity {

    @TableField("sum_id")
	@ColumnType(length=10)
	@ColumnComment("sumId")
    private String sumId ;

    @TableField("gauge_id")
	@ColumnType(length=255)
	@ColumnComment("测量Id")
    private String gaugeId ;

    @TableField("histogram_id")
	@ColumnType(length=10)
	@ColumnComment("直方图Id")
    private String histogramId ;

    @TableField("resource_id")
	@ColumnType(length=50)
	@ColumnComment("资源编号")
    private String resourceId; // 资源 ID

    @TableField("scope_id")
	@ColumnType(length=50)
	@ColumnComment("scopeId")
    private String scopeId ; // 资源 ID

    /**
     * 开始时间戳（纳秒）。
     */
    @TableField("start_time_unix_nano")
	@ColumnType(length=19)
	@ColumnComment("开始时间戳（纳秒）。")
    private long startTimeUnixNano;

    /**
     * 时间戳（纳秒）。
     */
    @TableField("time_unix_nano")
	@ColumnType(length=255)
	@ColumnComment("时间戳（纳秒）。")
    private long timeUnixNano;

    /**
     * 计数。
     */
    @TableField("count")
	@ColumnType(length=10)
	@ColumnComment("计数。")
    private int count;

    /**
     * 总和。
     */
    @TableField("sum")
	@ColumnType(length=10)
	@ColumnComment("总和。")
    private int sum;

    /**
     * 桶计数列表。
     */
    @TableField("bucket_counts")
	@ColumnType(length=10)
	@ColumnComment("桶计数列表。")
    private List<Integer> bucketCounts;

    /**
     * 显式边界列表。
     */
    @TableField("explicit_bounds")
	@ColumnType(length=10)
	@ColumnComment("显式边界列表。")
    private List<Integer> explicitBounds;

    /**
     * 最小值。
     */
    @TableField("min")
	@ColumnType(length=4)
	@ColumnComment("最小值。")
    private int min;

    /**
     * 最大值。
     */
    @TableField("max")
	@ColumnType(length=255)
	@ColumnComment("最大值。")
    private int max;

    /**
     * 属性列表。
     */
    @TableField("attributes")
	@ColumnType(length=255)
	@ColumnComment("属性列表。")
    private String attributes;
}
