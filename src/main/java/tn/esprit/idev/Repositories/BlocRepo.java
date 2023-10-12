package tn.esprit.idev.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.idev.Entities.Bloc;

@Repository
public interface BlocRepo extends JpaRepository<Bloc,Long> {

    Bloc findBlocByIdBloc(long idBloc);


}
