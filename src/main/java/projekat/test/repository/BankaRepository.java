package projekat.test.repository;



import projekat.test.model.Banka;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankaRepository extends JpaRepository<Banka, Long> {

}
