package com.nanrong.inspection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nanrong.inspection.model.TestDetectionData;

/**
 * 检测数据存储库
 */
@Repository
public interface TestDetectionDataRepository extends JpaRepository<TestDetectionData, Long> {
}
