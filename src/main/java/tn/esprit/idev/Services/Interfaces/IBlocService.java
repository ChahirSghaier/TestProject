package tn.esprit.idev.Services.Interfaces;

import tn.esprit.idev.Entities.Bloc;

public interface IBlocService {
    public Bloc addBloc(Bloc b);

    public  Bloc updateBloc(Bloc b);
    public void deleteBloc(Long id);

}
