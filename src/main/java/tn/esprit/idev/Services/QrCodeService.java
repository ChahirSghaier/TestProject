package tn.esprit.idev.Services;


import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrDataFactory;
import dev.samstevens.totp.qr.QrGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.idev.Entities.Booking;
import tn.esprit.idev.Services.Interfaces.IQrCodeService;

import static dev.samstevens.totp.util.Utils.getDataUriForImage;
@Service
@AllArgsConstructor
public class QrCodeService implements IQrCodeService {

    private QrDataFactory qrDataFactory;

    private QrGenerator qrGenerator;

    @Override
    public String genereteQrCode(Booking booking) throws QrGenerationException {

        QrData  data = qrDataFactory.newBuilder().label(booking.getSignature()).build();
        // Generate the QR code image data as a base64 string which can
        // be used in an <img> tag:
        String qrCodeImage = getDataUriForImage(qrGenerator.generate(data), qrGenerator.getImageMimeType());
      return qrCodeImage;
    }





}

