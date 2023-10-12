package tn.esprit.idev.Services.Interfaces;

import tn.esprit.idev.DTO.Subscription.BookingBySubscription;
import tn.esprit.idev.Entities.Booking;
import tn.esprit.idev.Entities.Subscription;

import java.util.Date;
import java.util.List;

public interface IBookingService {


    double calculateBookingAmountPerDays(String roomName, Date startTime, Date endTime);

    double calculateBookingAmountForSubscription(String roomName, Subscription subscription);

    List<Booking> getReservations();

    List<Booking> getFutureBookings();

    BookingBySubscription makeSubscription(BookingBySubscription booking, Long idRoom, Long idUser) throws Exception;

    Booking makeReservation(Booking booking, Long idRoom, Long idUser) throws Exception;

    List<Booking> getReservationsByRoom(Long roomId);

    List<Booking> getReservationsByUser(Long userId);

    void cancelReservation(Long reservationId, Long userId);

    String validateAndUpdateBooking(Long bookingId, String signature);
}
