package com.myorganisation.linkora.repository;

import com.myorganisation.linkora.entity.User;
import com.myorganisation.linkora.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom finder methods
    Optional<User> findByEmail(String email);
    List<User> findByFirstName(String firstName);
    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    List<User> findByGenderAndFirstNameContainingOrLastNameContaining(Gender gender, String firstName, String lastName);
}
