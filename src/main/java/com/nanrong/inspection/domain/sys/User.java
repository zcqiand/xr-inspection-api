package com.nanrong.inspection.domain.sys;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nanrong.inspection.domain.biz.UserQualification;

/*
 * 系统用户实体
 */
@Entity
@Table(name = "sys_user")
@DynamicUpdate
@Accessors(chain = true)
@FieldNameConstants
@NoArgsConstructor
@Getter
@Setter
@Comment("系统用户表")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("用户ID")
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    @Comment("用户名")
    private String username;

    @Column(nullable = false, length = 100)
    @Comment("密码")
    private String password;

    @Comment("是否启用")
    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Comment("用户角色列表")
    private List<Role> roles = new ArrayList<>();
    

    @OneToMany(mappedBy = "user")
    @Comment("用户资质列表")
    private List<UserQualification> qualifications = new ArrayList<>();
    
    @Column(precision = 10, scale = 2)
    @Nullable
    @Comment("薪资")
    private BigDecimal salary;

    @Nullable
    @Comment("创建时间")
    private OffsetDateTime createdAt;

    @Nullable
    @Comment("更新时间")
    private OffsetDateTime updatedAt;

    @Column(length = 100)
    @Nullable
    @Comment("部门")
    private String department;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(permission -> new SimpleGrantedAuthority(permission.getCode()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}