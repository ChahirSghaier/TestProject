package tn.esprit.idev.DTO.Subscription;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PaymentResponse {
    private Long bookingId;
    private Boolean payment;
    private String signature;
}
