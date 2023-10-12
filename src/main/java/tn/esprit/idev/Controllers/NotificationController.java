package tn.esprit.idev.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.idev.Entities.Notification;
import tn.esprit.idev.Services.Interfaces.INotificationService;

import java.util.List;
@RestController
@RequestMapping("notification")
@AllArgsConstructor
public class NotificationController {

	INotificationService notificationService;
	
	@GetMapping("/retrieve-all")
	public List<Notification> getAll() {
		List<Notification> listNotification = notificationService.ShowNotification();
		return listNotification;
	}
	@PostMapping("/add-notification")
	public Notification addNotification(@RequestBody Notification p) {
		return notificationService.AjouterNotification(p);
	}
	@PutMapping("/modify-notification")
	public Notification modifyNotification(@RequestBody Notification notification) {
		return notificationService.updateNotification(notification);
	}
	@DeleteMapping("/remove-notification/{id}")
	public void DeleteNotification(@PathVariable("id") Long notifid) {
		notificationService.DeleteNotification(notifid);
	}


	@PostMapping("/send-notification/{idUser}")
	public Notification sendNotification(@RequestBody Notification p,@PathVariable("idUser") Long iduser) {
		return notificationService.sendNotif(p,iduser);
	}
	
	@GetMapping("/retrieve-user-notification/{idUser}")
	public List<Notification> getUserNotification(@PathVariable("idUser") Long iduser) {
		List<Notification> listNotification = notificationService.userNotification(iduser);
		return listNotification;
	}
	
	
	
	
	
	
}
