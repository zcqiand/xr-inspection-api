package com.nanrong.inspection.domain.biz;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.Accessors;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import com.nanrong.inspection.domain.biz.Report;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * 样品组实体
 */
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
@DynamicUpdate
@Accessors(chain = true)
@Entity
@Table(name = "biz_sample_group")
@Comment("样品组表")
public class SampleGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String barcode; // 样品唯一条码
    
    @Column(nullable = false)
    private OffsetDateTime receivedTime; // 接收时间
    
    private BigDecimal storageTemperature; // 存储温度要求(℃)
    
    private BigDecimal currentTemperature; // 当前实际温度
    
    private OffsetDateTime lastTemperatureCheck; // 上次温度检查时间
    
    private OffsetDateTime firstExceededTime; // 首次温度超标时间
    
    @Enumerated(EnumType.STRING)
    private SampleStatus status = SampleStatus.RECEIVED; // 样品状态
    
    // 关联检测报告
    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;
    
    public enum SampleStatus {
        RECEIVED, // 已接收
        IN_TESTING, // 检测中
        ARCHIVED, // 已归档
        DISCARDED // 已处置
    }
    
    // 检查温度是否超标
    public boolean isTemperatureExceeded() {
        if (storageTemperature == null || currentTemperature == null) {
            return false;
        }
        // 假设存储温度要求是最高温度
        return currentTemperature.compareTo(storageTemperature) > 0;
    }
}