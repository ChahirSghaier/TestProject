package tn.esprit.idev.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.idev.Entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
     User findUserByIdUser(Long idUser);


      User  findUserByEmail(String email);
}
