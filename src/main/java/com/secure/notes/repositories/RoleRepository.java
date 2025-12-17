package com.secure.notes.repositories;

import com.secure.notes.models.AppRole;
import com.secure.notes.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    //롤 테이블에서 특정 권한을 찾기
    Optional<Role> findByRoleName(AppRole appRole);
}
