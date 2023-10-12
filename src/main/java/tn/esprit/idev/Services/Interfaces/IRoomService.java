package tn.esprit.idev.Services.Interfaces;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.idev.Entities.Rating;
import tn.esprit.idev.Entities.Room;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public interface IRoomService {


    Room createRoom(Room room) throws IOException;
    public void uploadPhotoToRoom(Long roomId, MultipartFile file) throws IOException;
    public byte[] getImageToRoom(Long id);

    public Room findRoomById(Long id);
    public Room update(Room room, Long id);
    public Boolean deleteRoomById(Long id);
    public List<Room> findRoomAvailable(Date db, Date de);

  //  public List<Room> listOfRoomsAvailable();

    Room addAndAssignRoomToBloc(Room r, Long idBloc);

    List<Room> findAllRooms();
    public void addRating(Long note, Long idPost, Long idUser);
    public void DeleteRating(Long id);
    public void removeRating(Rating rating);

}
