package com.nanrong.inspection.repository.biz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nanrong.inspection.domain.biz.SampleGroup;

@Repository
public interface SampleGroupRepository extends JpaRepository<SampleGroup, Long> {
    // 根据条码查询样品
    SampleGroup findByBarcode(String barcode);
}