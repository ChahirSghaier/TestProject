package tn.esprit.idev.Services;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.idev.Entities.Notification;
import tn.esprit.idev.Entities.User;
import tn.esprit.idev.Repositories.NotificationRepo;
import tn.esprit.idev.Repositories.UserRepo;
import tn.esprit.idev.Services.Interfaces.INotificationService;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class NotificationService implements INotificationService {
    NotificationRepo notificationRepo;
    @Override
    public Notification AjouterNotification(Notification notification) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        notification.setNotifDate(timestamp);
        return notificationRepo.save(notification);
    }

    @Override
    public Notification updateNotification(Notification notification) {
        return notificationRepo.save(notification);
    }

    @Override
    public void DeleteNotification(Long idNotif) {
notificationRepo.deleteById(idNotif);
    }

    @Override
    public List<Notification> ShowNotification() {
        List<Notification> notification = null;
        try {

            notification = (List<Notification>)notificationRepo.findAll();
            for (Notification notif : notification) {
                log.debug(" User : " + notif.toString());
            }
        }
        catch (Exception e) {log.error("Error in retrieve notifications : " + e);}


        return notification;
    }


    UserRepo userRepo;
    @Override
    public Notification sendNotif(Notification notification, Long idUser) {
        User user=userRepo.findById(idUser).get();
        notification.setUser(user);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        notification.setNotifDate(timestamp);

        return notificationRepo.save(notification);    }

    @Override
    public List<Notification> userNotification(Long idUser) {
        List<Notification> notifs=notificationRepo.showUserNotification(idUser);
        return notifs;
    }
}
