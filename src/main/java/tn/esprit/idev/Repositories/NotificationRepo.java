package tn.esprit.idev.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.idev.Entities.Notification;

import java.util.List;

public interface NotificationRepo extends JpaRepository<Notification,Long> {

    @Query(value ="select n from Notification n where n.user.idUser = :idUser")
    List<Notification> showUserNotification(Long idUser);




}
