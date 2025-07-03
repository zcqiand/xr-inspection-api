package com.nanrong.inspection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nanrong.inspection.model.TestReport;

@Repository
public interface TestReportRepository extends JpaRepository<TestReport, Long> {
}
