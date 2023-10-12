package tn.esprit.idev.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import tn.esprit.idev.Entities.Bloc;
import tn.esprit.idev.Entities.Room;
import tn.esprit.idev.Entities.RoomType;
import tn.esprit.idev.Entities.User;
import tn.esprit.idev.Repositories.BlocRepo;
import tn.esprit.idev.Repositories.RoomRepo;
import tn.esprit.idev.Repositories.UserRepo;

import javax.transaction.Transactional;

@Component
public class SetupDataLoader  implements ApplicationListener<ContextRefreshedEvent> {

private Boolean alreadySetup = false ;
@Autowired
 private UserRepo userRepo ;
@Autowired
 private RoomRepo roomRepo ;
@Autowired
 private BlocRepo blocRepo ;


    @Override
    @Transactional
    public void onApplicationEvent( ContextRefreshedEvent event )
 {
     if(alreadySetup){
         return ;
     }
     User user = createUserIfNotExisted("chahir.sghaier@esprit.tn");
     Room room = createRoomIfNotExisted("A01",false,500D,1400D,2700D,RoomType.single);
     Room room1 = createRoomIfNotExisted("A02",false,500D,1400D,2700D,RoomType.single);
     Room room2 = createRoomIfNotExisted("A03",false,500D,1400D,2700D,RoomType.single);
     Room room11 = createRoomIfNotExisted("A11",false,700D,2000D,3800D,RoomType.twin);
     Room room12 = createRoomIfNotExisted("A12",false,700D,2000D,3800D,RoomType.twin);
     Room room13 = createRoomIfNotExisted("A13",false,700D,2000D,3800D,RoomType.twin);
     Room room21 = createRoomIfNotExisted("A21",false,900D,2600D,5000D,RoomType.triple);
     Room room22 = createRoomIfNotExisted("A22",false,900D,2600D,5000D,RoomType.triple);
     Room room23 = createRoomIfNotExisted("A23",false,900D,2600D,5000D,RoomType.triple);

     alreadySetup = true;

 }


 @Transactional
 User createUserIfNotExisted(String email){
    User user = userRepo.findUserByEmail(email);
    if(user == null)
    {
         user =new User();
        user.setEmail(email);
        user.setFirstName("sghaier");
        user.setLastName("chahir");
    }
 return userRepo.save(user);
 }
@Transactional
Bloc createBlocIfNotExisted(Long id){
        Bloc bloc = blocRepo.findBlocByIdBloc(id);
        if(bloc == null ){
            bloc = new Bloc();
            bloc.setName("Residence A");
            bloc.setAddress("Zone Etudiant A ");
            bloc.setIsFull(false);
        }
        return blocRepo.save(bloc);
}

@Transactional

  Room createRoomIfNotExisted(final String number, boolean isAvailable,Double pricePerMonth, Double pricePerSemester, Double annualPrice, RoomType roomType ){
        Room room =roomRepo.findRoomByNumber(number);
        if(room == null){
            room= new Room();
            room.setIsAvailable(isAvailable);
            room.setNumber(number);
            room.setPricePerMonth(pricePerMonth);
            room.setPricePerSemestre(pricePerSemester);
            room.setAnnualPrice(annualPrice);
            room.setRoomType(roomType);
        }


    return roomRepo.save(room);

 }



}
