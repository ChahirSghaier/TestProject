package tn.esprit.idev.Services.Interfaces;

import tn.esprit.idev.Entities.Notification;

import java.util.List;

public interface INotificationService {
    Notification AjouterNotification(Notification notification);
    Notification updateNotification (Notification notification);
    void DeleteNotification(Long idNotif);
    List<Notification> ShowNotification();
    Notification sendNotif(Notification notification,Long idUser);
    List<Notification> userNotification(Long idUser);


}
