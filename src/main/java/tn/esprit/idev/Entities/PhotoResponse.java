package tn.esprit.idev.Entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoResponse {

    private String id;

    private String type;

    private String name;
    private  Room room;


    public PhotoResponse(Long id, String type, String name, Room room) {
    }
}
