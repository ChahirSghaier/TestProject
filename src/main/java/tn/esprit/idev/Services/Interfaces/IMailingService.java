package tn.esprit.idev.Services.Interfaces;

import org.springframework.scheduling.annotation.Async;
import tn.esprit.idev.Entities.User;

public interface IMailingService {

    @Async
    void sendConfirmationBooking(String QrCode, User user);
}
