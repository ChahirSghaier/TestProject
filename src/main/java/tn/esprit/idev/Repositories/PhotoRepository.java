package tn.esprit.idev.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.idev.Entities.Photo;

import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Long> {
    Optional<Photo> findPhotoByName(String fileName);


}
