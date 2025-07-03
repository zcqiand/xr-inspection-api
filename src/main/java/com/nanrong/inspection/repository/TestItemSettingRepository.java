package com.nanrong.inspection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nanrong.inspection.model.TestItemSetting;

@Repository
public interface TestItemSettingRepository extends JpaRepository<TestItemSetting, Long> {

  boolean existsByTestItemName(String testItemName);
}
