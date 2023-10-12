package tn.esprit.idev.Controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.idev.DTO.ReservationDate;
import tn.esprit.idev.Entities.Rating;
import tn.esprit.idev.Entities.Room;
import tn.esprit.idev.Services.Interfaces.IPhotoService;
import tn.esprit.idev.Services.Interfaces.IRoomService;

import java.io.IOException;
import java.util.List;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/room")
public class RoomController {
private IRoomService roomService;
private IPhotoService photoService;

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
    public List<Room> getAllRooms() {
        List<Room> rooms = roomService.findAllRooms();
        return rooms;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Room> getRoomById(@PathVariable("id") Long id) {
        log.info("Fetching room with id " + id);
        Room room = roomService.findRoomById(id);
        if (room == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @PostMapping("/add")

    public Room addRoom(@RequestBody Room room) throws IOException {
        System.out.println(" Adding new room : " + room.getNumber());
        return roomService.createRoom(room);
    }
    @PostMapping("/{roomId}/photos")
    public ResponseEntity<String> uploadPhoto(@PathVariable Long roomId, @RequestParam("file") MultipartFile file) {
        try {
            roomService.uploadPhotoToRoom(roomId, file);
            return ResponseEntity.ok().body("Photo uploaded successfully");
        } catch (IOException ex) {
            // Handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload photo");
        }
    }


    @DeleteMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") Long id) {
        Room room = roomService.findRoomById(id);
        if (room == null) {
            return new ResponseEntity<>("No such room", HttpStatus.NOT_FOUND);
        }
        roomService.deleteRoomById(id);
        return new ResponseEntity<>("Room deleted", HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/update", headers = "Accept=application/json")
    public ResponseEntity<Room> updateRoom(@RequestBody Room room, @RequestParam("photo") MultipartFile multipartFile) throws IOException {
        Room newRoom = roomService.findRoomById(room.getIdRoom());
        if (newRoom == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Room r1 = roomService.update(room, room.getIdRoom());
        return new ResponseEntity<>(r1, HttpStatus.OK);
    }

    @RequestMapping(value = "/date", method = RequestMethod.POST, produces = "application/json")
    public List<Room> getAllReservationsByDate(@RequestBody ReservationDate dates) {
        System.out.println("Dates to enquire from: " + dates.getDf() + " to: " + dates.getDt());
        List<Room> rooms = roomService.findRoomAvailable(dates.getDf(), dates.getDt());
        return rooms;
    }

    @PostMapping("/addrating/{note}/{id_room}/{id_user}")
    public void Rating(@PathVariable("note") Long note,@PathVariable("id_room") Long idRoom,@PathVariable("id_user") Long idUser) {
        roomService.addRating(note, idRoom, idUser);
    }
    @DeleteMapping("/deleterating")
    public void removeRating(@RequestBody Rating rating) {
        roomService.removeRating(rating);
    }

    @GetMapping("/getImage/{idRoom}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long idRoom)
    {
        byte[] image = roomService.getImageToRoom(idRoom);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }

}
