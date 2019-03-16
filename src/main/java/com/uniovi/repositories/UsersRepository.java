package com.uniovi.repositories;

import com.uniovi.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    @Query("SELECT r FROM User r WHERE r.isDeleted = false")
    List<User> getUsersNotDeleted();
}
