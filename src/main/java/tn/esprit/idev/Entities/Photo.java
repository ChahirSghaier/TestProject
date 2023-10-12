package tn.esprit.idev.Entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "t_photo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Photo {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;

     String name;

     String type;

      @Lob
      @Column(name = "image")
      byte[] imageData;



}
