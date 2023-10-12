package tn.esprit.idev.Entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_room")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Room extends AuditListening {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long idRoom;

     @Column(name="name")
     String number;

    @Column(name="location")
     String location;

    @Column(name = "price_per_night" )
    Double pricePerNight ;
    Double pricePerMonth ;

    Double pricePerWeek ;

    Double pricePerSemestre;

    Double annualPrice ;
    @Enumerated(value =EnumType.STRING)
    RoomType roomType;

    @Column(name="available")
    Boolean isAvailable;

    @ManyToOne
    private Bloc bloc;

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    private List<Booking> bookingListe ;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "photo_id")
    private List<Photo> photos = new ArrayList<>();
   @OneToOne
    private User user ;

}
