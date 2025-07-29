package com.nanrong.inspection.domain.biz;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.Accessors;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.*;
import java.time.OffsetDateTime;

/**
 * 设备实体
 */
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
@DynamicUpdate
@Accessors(chain = true)
@Entity
@Table(name = "biz_device")
@Comment("设备表")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // 设备名称

    private String model; // 设备型号
    
    @Column(name = "serial_number")
    private String serialNumber; // 序列号
    
    @Column(name = "purchase_date")
    private OffsetDateTime purchaseDate; // 购置日期
    
    private String status; // 状态：NORMAL-正常, MAINTENANCE-维修中, SCRAPPED-报废
    
    @Column(name = "last_maintenance_date")
    private OffsetDateTime lastMaintenanceDate; // 上次维护日期
    
    private String location; // 存放位置
    
    @Column(name = "responsible_person")
    private String responsiblePerson; // 责任人
}