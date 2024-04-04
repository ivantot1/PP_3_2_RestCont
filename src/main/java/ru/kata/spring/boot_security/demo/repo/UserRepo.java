package ru.kata.spring.boot_security.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Query("Select u from User u join fetch u.roles where u.username=:username")
    User findByUsername(String username);


}
