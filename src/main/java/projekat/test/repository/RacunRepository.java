package projekat.test.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import projekat.test.model.Racun;
import org.springframework.stereotype.Repository;

@Repository
public interface RacunRepository extends JpaRepository<Racun, Long> {
	
	@Query("Select r from Racun r where (:jmbg is null or r.jmbg like :jmbg) AND (:idBanka is null or r.banka.id=:idBanka)")
	public Page<Racun> pretraga(@Param("jmbg") String jmbg, @Param("idBanka") Long idBanka, Pageable pageable);
	@Query("Select r from Racun r where r.brojRacuna=:brojRacuna")
	public Racun pronadjiPoBrojuRacuna(@Param("brojRacuna") String brojRacuna);

}
