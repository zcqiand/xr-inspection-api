package com.nanrong.inspection.repository.biz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nanrong.inspection.domain.biz.BMethod;

@Repository
public interface BMethodRepository extends JpaRepository<BMethod, Long> {
    // 根据方法名称查询
    BMethod findByName(String name);
}