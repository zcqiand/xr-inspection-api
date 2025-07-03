package com.nanrong.inspection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nanrong.inspection.model.TestResult;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {
}
