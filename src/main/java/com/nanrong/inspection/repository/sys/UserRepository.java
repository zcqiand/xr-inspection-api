package com.nanrong.inspection.repository.sys;

import com.nanrong.inspection.domain.biz.WorkloadAnalysisVO;
import com.nanrong.inspection.domain.sys.User;
import com.nanrong.inspection.dto.biz.QualificationWarningDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 用户数据访问接口
 */
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户名查询用户
     */
    Optional<User> findByUsername(String username);

    /**
     * 检查用户名是否存在
     */
    boolean existsByUsername(String username);
    
    /**
     * 查询即将到期的资质
     */
    @Query("SELECT u, q, uq FROM User u JOIN u.qualifications uq JOIN uq.qualification q " +
           "WHERE uq.expiryDate BETWEEN CURRENT_DATE AND :endDate")
    List<Object[]> findExpiringQualifications(@Param("endDate") java.time.LocalDate endDate);
    
    /**
     * 更新能力等级
     */
    @Modifying
    @Transactional
    @Query("UPDATE UserQualification uq SET uq.level = :newLevel " +
           "WHERE uq.user.id = :userId AND uq.qualification.id = :qualificationId")
    void updateCompetenceLevel(Long userId, Long qualificationId, String newLevel);
    
}