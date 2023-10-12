package tn.esprit.idev.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_notification")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends AuditListening
{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long notifId;
    String type;
    boolean status;
    Timestamp notifDate;
    String content;
    @JsonIgnore
    @ManyToOne
    User user;
}
