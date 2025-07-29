package com.nanrong.inspection.repository.sys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.nanrong.inspection.domain.sys.Role;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

/**
 * 角色数据访问接口
 */
@Transactional(readOnly = true)
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * 根据角色名称查询角色
     *
     * @param name 角色名称
     * @return 角色实体Optional包装
     */
    Optional<Role> findByName(String name);

    /**
     * 检查角色名称是否存在
     *
     * @param name 角色名称
     * @return 是否存在
     */
    boolean existsByName(String name);

    /**
     * 分页查询所有角色
     *
     * @param pageable 分页参数
     * @return 角色分页列表
     */
    Page<Role> findAll(Pageable pageable);
}