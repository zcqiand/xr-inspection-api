package com.nanrong.inspection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nanrong.inspection.model.TestSampleGroup;

@Repository
public interface TestSampleGroupRepository extends JpaRepository<TestSampleGroup, Long> {

}
