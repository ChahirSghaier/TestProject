package tn.esprit.idev.Controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.idev.DTO.ApiResponse;
import tn.esprit.idev.DTO.Subscription.BookingBySubscription;
import tn.esprit.idev.DTO.Subscription.PaymentResponse;
import tn.esprit.idev.Entities.Booking;
import tn.esprit.idev.Entities.PaymentDetails;
import tn.esprit.idev.Repositories.BookingRepo;
import tn.esprit.idev.Repositories.UserRepo;
import tn.esprit.idev.Services.Interfaces.IBookingService;
import tn.esprit.idev.Services.Interfaces.IRoomService;
import tn.esprit.idev.util.ReservationCode;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/booking")
@Slf4j
public class BookingController {

    private IBookingService bookingService;

    private IRoomService roomService;

    private UserRepo userRepo ;

    private BookingRepo bookingRepo;

    private ReservationCode reservationCode;
     @GetMapping
    List<Booking> getReservations(){
     return bookingService.getReservations();
    }
@GetMapping("/future")
    List<Booking> getFutureBookings(){
         return bookingService.getFutureBookings();
    }

    @PostMapping("/{idRoom}/{idUser}")
    Booking makeReservation(Booking booking, Long idRoom, Long idUser) throws Exception{
         return bookingService.makeReservation(booking,idRoom,idUser);
    }

    @GetMapping("/room/{idRoom}")
    List<Booking> getReservationsByRoom(@PathVariable(name = "idRoom") Long roomId){
         return bookingService.getReservationsByRoom(roomId);
    }
    @GetMapping("/user/{idUser}")
    List<Booking> getReservationsByUser(@PathVariable(name = "idUser") Long userId){
         return bookingService.getReservationsByUser(userId);
    }
    @DeleteMapping("/cancel/{idReservation}/{userId}")
    void cancelReservation(@PathVariable(name = "idReservation") Long reservationId,@PathVariable(name = "userId") Long userId){
          bookingService.cancelReservation(reservationId,userId);
    }

    @PostMapping("/validate-payment")
    public ResponseEntity<String> validatePaymentDetails(@Valid @RequestBody PaymentDetails paymentDetails) {
        // Handle the payment details validation
        // ...
        return ResponseEntity.ok("Payment details validated successfully.");
    }

    @PostMapping("/subscribe/{idUser}/{idRoom}")
    public ResponseEntity<?> createOrder(@RequestBody BookingBySubscription bookingBySubscription,@PathVariable("idUser")  Long idUser,@PathVariable("idRoom") Long idRoom)
        {
            PaymentResponse paymentResponse =null;
            try{
                BookingBySubscription bs = new BookingBySubscription();
                 bs= bookingService.makeSubscription(bookingBySubscription,idRoom,idUser) ;
                // Save booking in the database
                Booking b = mapToBooking(bs);
                String secret = reservationCode.generateReservationCode(b.getBookingId());
                b.setSignature(secret);
                b.setBookingValidated(false);
                bookingRepo.save(b);
                paymentResponse.setBookingId(b.getBookingId());
                paymentResponse.setSignature(secret);
             //   paymentResponse.setPaymentId(b.getPaymentDetailsList().contains());

            }catch (Exception e){}
            return ResponseEntity.ok(paymentResponse);
        }

    /**
     *
     * @param paymentResponse
     * @return
     */

    @PutMapping("/subcribe/validate")
    public ResponseEntity<?> validateSubscription(@RequestBody PaymentResponse paymentResponse) {
        String errorMsg = bookingService.validateAndUpdateBooking
                (paymentResponse.getBookingId(), paymentResponse.getSignature());
        if (errorMsg != null) {
            return new ResponseEntity<>(new ApiResponse(errorMsg), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new ApiResponse(paymentResponse.getBookingId().toString()));
    }


    private Booking mapToBooking(BookingBySubscription bookingBySubscription)
       {
        Booking booking = new Booking();
        booking.setSubscibe(bookingBySubscription.getSubscription());
        booking.setUser(bookingBySubscription.getUser());
        booking.setRoom(bookingBySubscription.getRoom());
        booking.setName(bookingBySubscription.getName());
        booking.setDateBegin(bookingBySubscription.getDateBegin());
        booking.setTotalAmount(bookingBySubscription.getTotalAmount());
       return bookingRepo.save(booking);
       }







    }
