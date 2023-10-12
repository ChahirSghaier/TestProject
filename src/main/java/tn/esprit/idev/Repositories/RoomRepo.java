package tn.esprit.idev.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.idev.Entities.Room;

import java.util.Date;

@Repository
public interface RoomRepo extends JpaRepository<Room,Long> {

    Room findRoomByIdRoom(long idSalle);


    Room findRoomByNumber(String nom);

   /* @Query(
            value =
                    "select * from (select * from t_room as ro where ro.id_room not in "
            + "(select re.room_id from t_booking as re where (re. >= ?1 and re.date_begin < ?2) "
            + "or (re.date_end >= ?1 and re.date_end < ?2))) as roo where roo.id_room = ?3",
            nativeQuery = true)
    Room checkAvailability(Date dateBegin, Date dateEnd, Long roomId);*/

    @Query(
                value =   " select room from Room as room "
                    + " where room.idRoom not in "
                    + " ( "
                    + "select booking.room.idRoom "
                    + "from Booking as booking "
                    + "where (booking.dateBegin >= ?1 and booking.dateBegin < ?2) "
                    + "or (booking.dateEnd >= ?1 and booking.dateEnd < ?2) "
                    + ")" )
    Room findRoomAvailable(Date db, Date de);

}
