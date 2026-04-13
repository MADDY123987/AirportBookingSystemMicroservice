package com.FlyNova.user_service.Repository;

import com.FlyNova.user_service.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
