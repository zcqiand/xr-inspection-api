package com.nanrong.inspection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nanrong.inspection.model.TestReportNameSetting;

@Repository
public interface TestReportNameSettingRepository
                extends JpaRepository<TestReportNameSetting, Long> {

}
