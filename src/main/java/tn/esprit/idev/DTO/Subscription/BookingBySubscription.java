package tn.esprit.idev.DTO.Subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.idev.Entities.Room;
import tn.esprit.idev.Entities.Subscription;
import tn.esprit.idev.Entities.User;

import javax.persistence.*;
import java.util.Date;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class BookingBySubscription {

    Long bookingId;
    @ManyToOne
    Room room;
    @Transient
    String name;
    @ManyToOne
    User user;

    @Temporal(TemporalType.DATE)
    Date dateBegin;
    @Enumerated(EnumType.STRING)
    Subscription subscription;

    Long daysOfBooking;
    Double totalAmount;


}
