package tn.esprit.idev.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "t_user")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long idUser;
    String firstName;
    String lastName;
      String email;
     String secret;


    @OneToMany(mappedBy = "user",orphanRemoval = true)
    private List<Booking> booking;

    @OneToMany (mappedBy ="user",orphanRemoval = true)
    List<Notification> notifications;
    @OneToMany (mappedBy ="user")
    List<Rating> ratings;
    @OneToOne(mappedBy = "user")
    Room room ;


}
