package com.secure.note.repositories;

import com.secure.note.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //유저를 찾을 때 유저네임으로 찾음
    Optional<User> findByUserName(String username);
    //유저네임으로 유저가 있는지 확인
    Boolean existsByUserName(String username);
}
