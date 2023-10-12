package tn.esprit.idev.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    private String cardholderName;

    @NotBlank
    @CreditCardNumber
    private String cardNumber;

    @NotBlank
    @Length(min = 2, max = 2)
    private String expiryMonth;

    @NotBlank
    @Length(min = 2, max = 4)
    private String expiryYear;

    @NotBlank
    @Length(min = 3, max = 3)
    private String cvv;

    @ManyToOne
    Booking booking;
}





