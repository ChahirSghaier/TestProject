package tn.esprit.idev.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "t_bloc")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bloc implements Serializable {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
       Long idBloc;

       String name ;

       String address ;

       Boolean isFull;
       @OneToMany(mappedBy = "bloc",cascade = CascadeType.ALL,fetch = FetchType.EAGER)

       List<Room> rooms ;


}
