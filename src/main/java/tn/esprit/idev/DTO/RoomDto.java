package tn.esprit.idev.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import tn.esprit.idev.Entities.RoomType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDto {

private  String nameRoom;
private Boolean isAvailable ;
private RoomType roomType ;



}
