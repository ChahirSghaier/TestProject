package tn.esprit.idev.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.idev.DTO.Subscription.BookingBySubscription;
import tn.esprit.idev.Entities.Booking;
import tn.esprit.idev.Entities.Room;
import tn.esprit.idev.Entities.Subscription;
import tn.esprit.idev.Entities.User;
import tn.esprit.idev.Repositories.BookingRepo;
import tn.esprit.idev.Repositories.RoomRepo;
import tn.esprit.idev.Repositories.UserRepo;
import tn.esprit.idev.Services.Interfaces.IBookingService;
import tn.esprit.idev.exception.RessourceNotFoundException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
@Slf4j

public class BookingService implements IBookingService {

   private BookingRepo bookingRepo;
   private RoomRepo roomRepo;
   private UserRepo userRepo;

    public long calculateDaysBetweenDates(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    /**
     * @apiNote this is a method to calculate the total amount for reserving room
     *
     * @param roomName
     * @param startTime
     * @param endTime
     * @return amount
     */
    public double calculateBookingAmountPerDays(String roomName, Date startTime, Date endTime) {
        // Get the room rate from the database
        Room room = roomRepo.findRoomByNumber(roomName);
        double rate = room.getPricePerNight();
        // Calculate the number of days between the start and end time
        long diff = calculateDaysBetweenDates(startTime,endTime);
        // Calculate the booking amount based on the room rate and the number of hours
        double amount;
        amount = rate * diff;
        return amount;
    }
    public double calculateBookingAmountForSubscription(String roomName, Subscription subscription) {
        // Get the room rate from the database
        Room room = roomRepo.findRoomByNumber(roomName);
        double rate = 0.0d;
        // Calculate the booking amount based on the room rate, the number of hours, and the subscription type
        switch(subscription) {
            case monthly :
                 rate = room.getPricePerMonth();
                break;
            case semestre :
                rate=room.getPricePerSemestre();
                break;
            case annual :
                rate = room.getAnnualPrice();
                break;
            default:
                throw new IllegalArgumentException("Invalid subscription type: " + subscription);
        }

        return rate;
    }



    @Override
    public List<Booking> getReservations() {
        return bookingRepo.findAll();
    }


    @Override
    public List<Booking> getFutureBookings() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(new Date());
        format = String.format("%s ", format.split(" ")[0]);
        LinkedList<Booking> allByDate = null;
        try {
            allByDate = bookingRepo.findAllByDate(dateFormat.parse(format));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return allByDate;
    }



    public boolean isRoomAvailable(Long idRoom, Date checkInDate, Date checkOutDate) {
        List<Booking>  reservations = bookingRepo.findAll();
        Room room = roomRepo.findRoomByIdRoom(idRoom);
        // Check if the room is available for the specified check-in and check-out dates
        for (Booking reservation : reservations) {
            if (reservation.getRoom().equals(room) && reservation.getDateBegin().before(checkOutDate)
                    && reservation.getDateEnd().after(checkInDate)) {
                return false;
            }
        }
        return true;
    }
    public boolean isDateRangeValid(Date checkInDate, Date checkOutDate) {
        // Check if the specified check-in and check-out dates are valid
        return checkInDate.before(checkOutDate) && checkInDate.after(Date.from(Instant.from(LocalDate.now()))) && checkOutDate.after(Date.from(Instant.from(LocalDate.now())));
    }

@Override
    public BookingBySubscription makeSubscription(BookingBySubscription booking, Long idRoom, Long idUser) throws Exception
    {
        Room room = roomRepo.findRoomByIdRoom(idRoom);
        User user = userRepo.findUserByIdUser(idUser);

        booking.setRoom(room);
        booking.setUser(user);
        double amount = calculateBookingAmountForSubscription(room.getNumber(),booking.getSubscription());
        booking.setTotalAmount(amount);
        room.setIsAvailable(false);
        roomRepo.save(room);
        return booking  ;
    }
    @Override
    public Booking makeReservation(Booking booking, Long idRoom, Long idUser) throws Exception
    {
        Room room = roomRepo.findById(idRoom).orElseThrow(() -> new RessourceNotFoundException("Room not found"));

        User user = userRepo.findUserByIdUser(idUser);
        if (user == null) {
            throw new RessourceNotFoundException("User not found");
        }
        // Check if the specified room is available for the specified check-in and check-out dates
        if (!isRoomAvailable(idRoom, booking.getDateBegin(), booking.getDateEnd())) {
            throw new Exception("The room is not available for the specified date range.");
        }

        // Check if the specified check-in and check-out dates are valid
        if (!isDateRangeValid(booking.getDateBegin(), booking.getDateEnd())) {
            throw new Exception("The specified check-in and check-out dates are invalid.");
        }

        booking.setRoom(room);
        booking.setUser(user);

        room.setIsAvailable(false);
        roomRepo.save(room);

        // Create a new reservation object and add it to the list of reservations

        return bookingRepo.save(booking);
    }

    public Boolean validateData(Booking reservation)
    {
        if     (reservation.getRoom() == null || reservation.getUser() == null ||
                reservation.getDateBegin() == null || reservation.getDateEnd() == null)
             return true ;
        else
            return false;
    }



@Override
    public List<Booking> getReservationsByRoom(Long roomId) {
        Room room = roomRepo.findById(roomId).orElseThrow(() -> new RessourceNotFoundException("Room not found"));
        return bookingRepo.findBookingByRoom(room);
    }
@Override
    public List<Booking> getReservationsByUser(Long userId) {
        User user = userRepo.findUserByIdUser(userId);
        if (user == null) {
            throw new RessourceNotFoundException("Student not found");
        }
        return bookingRepo.findBookingByUser(user);
    }

    @Override
    public void cancelReservation(Long reservationId, Long userId) {
        Booking booking = bookingRepo.findById(reservationId)
                .orElseThrow(() -> new RessourceNotFoundException("Reservation not found"));
        if (!booking.getUser().getIdUser().equals(userId)) {
            throw new RessourceNotFoundException("You are not authorized to cancel this reservation");
        }
        Room room = booking.getRoom();
        room.setIsAvailable(true);
        roomRepo.save(room);
        bookingRepo.delete(booking);
    }

    @Override
    public String validateAndUpdateBooking(Long bookingId, String signature) {
       String errorMsg = null;

        try{
          Booking booking = bookingRepo.findBookingByBookingId(Long.valueOf(bookingId)) ;
          String secret =booking.getSignature();
          if(secret.equals(signature)){
              booking.setBookingValidated(true);
              booking.setSignature(signature);
          } else {
              errorMsg = "Payment validation failed: Signature doesn't match";
          }
        }catch (Exception e){
            log.error("Payment validation failed", e);
            errorMsg = e.getMessage();

        }
  return  errorMsg;

    }


}
