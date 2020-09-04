package projekat.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import projekat.test.model.TipRacuna;

@Repository
public interface TipRacunaRepository extends JpaRepository<TipRacuna, Long> {
	
	@Query("Select t from TipRacuna t where t.banka.id=:banka")
	public List<TipRacuna> findByBanka(@Param("banka") Long idBanka);

}
