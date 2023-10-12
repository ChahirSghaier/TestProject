package tn.esprit.idev.DTO;

import lombok.Value;

@Value
public class QrCodeResponse {
   private boolean payment;
   private String qrCodeImage;


}
