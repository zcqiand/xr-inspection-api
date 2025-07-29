package com.nanrong.inspection.repository.biz;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nanrong.inspection.domain.biz.BTemplate;

public interface BTemplateRepository extends JpaRepository<BTemplate, Long> {
}