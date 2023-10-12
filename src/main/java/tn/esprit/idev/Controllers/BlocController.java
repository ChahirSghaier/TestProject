package tn.esprit.idev.Controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tn.esprit.idev.Entities.Bloc;
import tn.esprit.idev.Services.Interfaces.IBlocService;
@Slf4j

@RestController
@RequestMapping("/bloc")
@AllArgsConstructor
public class BlocController {
   private IBlocService blocService;
    @PostMapping(value = "/add")
    public Bloc ajouterBloc(@RequestBody Bloc b){
        return  blocService.addBloc(b);
    }
    @PutMapping("/update")
    public  Bloc modifyBloc(@RequestBody Bloc b){

        return blocService.updateBloc(b);
    }



 /*  @GetMapping("/liste")
    public List<Room>    listOfRoomsAvailable(){
      return  blocService.();
    }*/
}
