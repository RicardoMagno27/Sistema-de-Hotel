package System.Hotel.Hotel.System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import System.Hotel.Hotel.System.model.Users;

import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("SELECT e FROM Users e WHERE e.email = email ")
    public Users findByEmail(String email);

    @Query("SELECT u FROM Users u where u.username = :username and u.password= :password ")
    public Users buscarlogin(String username, String password);

}
