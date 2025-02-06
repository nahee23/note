package com.secure.note.repositories;

import com.secure.note.models.AppRole;
import com.secure.note.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    //유저권한을 role 테이블에서 찾음
    Optional<Role> findByRoleName(AppRole appRole);
}
