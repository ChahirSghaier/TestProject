package tn.esprit.idev.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.idev.Entities.Bloc;
import tn.esprit.idev.Entities.Room;
import tn.esprit.idev.Repositories.BlocRepo;
import tn.esprit.idev.Repositories.BookingRepo;
import tn.esprit.idev.Repositories.RoomRepo;
import tn.esprit.idev.Services.Interfaces.IBlocService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class BlocService implements IBlocService {
    private BlocRepo blocRepo;
    private RoomRepo roomRepo;
    private BookingRepo bookingRepo;

    public Bloc addBloc(Bloc b) {
        return blocRepo.save(b);
    }

    public Bloc updateBloc(Bloc b) {
        return blocRepo.save(b);
    }

    public void deleteBloc(Long id) {
        blocRepo.deleteById(id);
    }


    public List<Room> listOfRoomsAvailable(Long idBloc) {
        Bloc bloc = blocRepo.findBlocByIdBloc(idBloc);
        List<Room> listRooms = bloc.getRooms();
        List<Room> availableRooms = new ArrayList<>();
        return availableRooms;
    }


}
