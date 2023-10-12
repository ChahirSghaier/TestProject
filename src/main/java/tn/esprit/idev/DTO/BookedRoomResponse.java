package tn.esprit.idev.DTO;


import lombok.Value;

@Value
public class BookedRoomResponse {
   private boolean success;
   private String qrCodeImage;


}
