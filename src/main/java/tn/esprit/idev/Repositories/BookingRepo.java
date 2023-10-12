package tn.esprit.idev.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.idev.Entities.Booking;
import tn.esprit.idev.Entities.Room;
import tn.esprit.idev.Entities.User;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Long>
{
    @Query(value = "SELECT booking FROM Booking booking"
            + " WHERE booking.room.idRoom = (select trr.idRoom from Room trr where trr.number = ?1)")
    List<Booking> findByName(String name);


    @Query("select b from Booking b where b.dateEnd >=:date order by b.createdAt desc")
    LinkedList<Booking> findAllByDate(Date date);

    @Query("select count(b) from Booking b where b.room.bloc.idBloc=:id ")
    public int countReservationByBloc(@Param("id") Long id);
    @Query(" select b from Booking b" +
            " where b.room.number=:number")
    List<Booking> findByRoomNumber(String number);


    @Query("select b from Booking b where b.user.email = ?1")
    List<Booking> findBookingByUserEmail(String email);

    List<Booking> findBookingByUser(User user);


    Booking findBookingByBookingId(Long idBooking);

    List<Booking> findBookingByRoom(Room room);
}
