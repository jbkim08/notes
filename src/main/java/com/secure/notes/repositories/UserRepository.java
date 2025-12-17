package com.secure.notes.repositories;

import com.secure.notes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /* 유저네임으로 찾는 메소드 */
    Optional<User> findByUserName(String username);
    /* 유저네임으로 유저가 있는지 확인 */
    Boolean existsByUsername(String username);

}
