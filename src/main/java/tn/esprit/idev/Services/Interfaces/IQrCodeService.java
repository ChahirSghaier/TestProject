package tn.esprit.idev.Services.Interfaces;


import dev.samstevens.totp.exceptions.QrGenerationException;
import tn.esprit.idev.Entities.Booking;

public interface IQrCodeService {

    String genereteQrCode(Booking booking) throws QrGenerationException;
}

