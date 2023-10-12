package tn.esprit.idev.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.idev.Entities.*;
import tn.esprit.idev.Repositories.*;
import tn.esprit.idev.Services.Interfaces.IRoomService;
import tn.esprit.idev.exception.RessourceNotFoundException;
import tn.esprit.idev.util.FileUploader;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoomService implements IRoomService {
  private RoomRepo roomRepo;
  private BlocRepo blocRepo;
  private BookingRepo bookingRepo;


  /* public List<Room> listOfRoomsAvailable() {
    List<Booking> bookingList = bookingRepo.findAll();
    List<Room> roomList = new ArrayList<>();
    for(Booking b : bookingList){
      roomList= Collections.singletonList(roomRepo.findRoomAvailable(b.getDateBegin(), b.getDateEnd(), b.getRoom().getIdRoom()));
    }
    return roomList;
  }*/
    PhotoService photoService;
    @Override
    public Room createRoom(Room room) throws IOException
    {

       roomRepo.save(room);
       return room;

    }





  @Override
  public Room findRoomById(Long id)
    {
   return roomRepo.findRoomByIdRoom(id); // returns
    }

  @Override
  public Room update(Room newRoom, Long id) {
    Room oldRoom = roomRepo.findRoomByIdRoom(id); // returns java8 optional
    if (oldRoom != null) {
      newRoom.setNumber(oldRoom.getNumber());
      newRoom.setLocation(oldRoom.getLocation());
      newRoom.setIsAvailable(oldRoom.getIsAvailable());
      Room updatedroom =roomRepo.save(newRoom);
      return updatedroom;
    } else {
      throw new RessourceNotFoundException("Room not existed ");
    }

  }

  @Override
  public Boolean deleteRoomById(Long id) {

    Optional<Room> optRoom = roomRepo.findById(id); // returns java8 optional
    if (optRoom.isPresent()) {
      roomRepo.delete(optRoom.get());
      return true;
    } else {
      throw new RessourceNotFoundException("Room not found ");
    }

  }

  @Override
  public List<Room> findRoomAvailable(Date db, Date de) {

   return (List<Room>) roomRepo.findRoomAvailable(db, de);

  }

  @Override
  public Room addAndAssignRoomToBloc(Room r, Long idBloc) {
    Room room = roomRepo.save(r);
    Bloc bloc= blocRepo.findBlocByIdBloc(idBloc);
    bloc.getRooms().add(room);
    blocRepo.save(bloc);
    return room;
  }

  @Override
  public List<Room> findAllRooms() {
    return roomRepo.findAll();
  }

  /**
   *  Rating
   *
   */

  UserRepo userRepo;
  RatingRepository ratingRepository;
  @Override
  public void addRating(Long note, Long idPost, Long idUser)
  {
    User user = userRepo.findById(idUser).get();
    Room room = roomRepo.findById(idPost).get();
    Rating rating = new Rating();
    rating.setUser(user);
    rating.setRoom(room);
    rating.setValue(note);
    ratingRepository.save(rating);
  }
  @Override

  public void DeleteRating(Long id)
  {
    ratingRepository.deleteById(id);

  }
  @Override

  public void removeRating(Rating rating)
  {
    ratingRepository.delete(rating);
  }
                      // adding photos


  /************************************ file **********************************/

  PhotoRepository photoRepository;
  public void uploadPhotoToRoom(Long roomId, MultipartFile file) throws IOException
  {

    Room room = roomRepo.findRoomByIdRoom(roomId);
    // Create a new photo entity and add it to the room
    Photo photo = photoService.uploadImage(file);
    room.getPhotos().add(photo);
    // Save the room to update the photo list
    roomRepo.save(room);

  }
  public byte[] getImageToRoom(Long id)
  {
    Room room = roomRepo.findRoomByIdRoom(id);
    Optional<Photo> imageData =  room.getPhotos().stream().findFirst();
    return FileUploader.decompressImage(imageData.get().getImageData());

  }


/**
 * @apiNote
 *
 */
/*
@Transactional
public String validateAndUpdateOrder(final String razorpayOrderId, final String razorpayPaymentId, final String razorpaySignature, final String secret) {
  String errorMsg = null;
  try {
    Booking order = bookingRepo.findByRoomNumber(razorpayOrderId);
    // Verify if the razorpay signature matches the generated one to
    // confirm the authenticity of the details returned
    String generatedSignature = Signature.calculateRFC2104HMAC(order.getRazorpayOrderId() + "|" + razorpayPaymentId, secret);
    if (generatedSignature.equals(razorpaySignature)) {
      order.setRazorpayOrderId(razorpayOrderId);
      order.setRazorpayPaymentId(razorpayPaymentId);
      order.setRazorpaySignature(razorpaySignature);
      orderRepository.save(order);
    } else {
      errorMsg = "Payment validation failed: Signature doesn't match";
    }
  } catch (Exception e) {
    log.error("Payment validation failed", e);
    errorMsg = e.getMessage();
  }
  return errorMsg;
}
*/
}
