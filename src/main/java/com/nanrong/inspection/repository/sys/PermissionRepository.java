package com.nanrong.inspection.repository.sys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.nanrong.inspection.domain.sys.Permission;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 权限数据访问接口
 */
@Transactional(readOnly = true)
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    /**
     * 根据权限编码查询权限
     *
     * @param code 权限编码
     * @return 包含权限的Optional对象
     */
    Optional<Permission> findByCode(String code);

    /**
     * 检查权限编码是否存在
     *
     * @param code 权限编码
     * @return 存在返回true，否则false
     */
    boolean existsByCode(String code);

    /**
     * 分页查询所有权限
     * 
     * @param pageable 分页参数
     * @return 权限分页列表
     */
    Page<Permission> findAll(Pageable pageable);
}