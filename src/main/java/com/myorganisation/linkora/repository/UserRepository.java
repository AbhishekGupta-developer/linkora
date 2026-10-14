package com.myorganisation.linkora.repository;

import com.myorganisation.linkora.entity.User;
import com.myorganisation.linkora.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    // JPQL -> @Query Annotation
    @Query("SELECT u FROM User u WHERE u.email = :mail")
    Optional<User> searchByEmail(@Param("mail") String email);

    @Query(nativeQuery = false, value = "SELECT u FROM User u WHERE (u.firstName LIKE CONCAT('%', :fn, '%') OR u.lastName LIKE CONCAT('%', :ln, '%')) AND u.gender = :gender")
    List<User> searchByFirstNameAndLastNameWithGender(@Param("fn") String firstName, @Param("ln") String lastName, @Param("gender") Gender gender);

    // Native Query -> SQL (According to the exact Database)
    @Query(
            value =
                """
                SELECT *
                FROM users
                ORDER BY
                first_name ASC,
                last_name ASC
                """,
            nativeQuery = true)
    List<User> getAllUsers();

}
