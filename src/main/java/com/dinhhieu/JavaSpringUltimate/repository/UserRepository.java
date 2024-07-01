package com.dinhhieu.JavaSpringUltimate.repository;

import com.dinhhieu.JavaSpringUltimate.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User save(User eric);
    List<User> findOneByEmail(String email);

    boolean existsByEmail(String email);

    User findByEmail(String email);

}
