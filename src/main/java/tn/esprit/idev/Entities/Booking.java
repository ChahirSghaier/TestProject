package tn.esprit.idev.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_booking")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking extends AuditListening{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "booking_id")
         Long bookingId;
        @ManyToOne(cascade = CascadeType.ALL)
         Room room;
        @Transient
        String name;

        @Column(nullable = true , name = "number_of_nights")
        int numberOfNights;

        @ManyToOne(cascade = CascadeType.ALL)
         User user;
       @Column(name = "description")
       String description;

       @Column(name = "date_begin")
       @Temporal(TemporalType.DATE)
       Date dateBegin;

       @Column(name = "date_end")
       @Temporal(TemporalType.DATE)
       Date dateEnd;


       @Column(name="secret")
       String signature;


       Boolean bookingValidated;

       Long daysOfBooking;
       Double totalAmount;
       @Enumerated(EnumType.STRING)
       Subscription subscibe;
    
       @OneToMany(mappedBy = "booking",cascade = CascadeType.ALL)
       List<PaymentDetails> paymentDetailsList;
}
