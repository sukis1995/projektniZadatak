package projekat.test.service;

import java.util.List;

import org.springframework.data.domain.Page;

import projekat.test.model.Racun;

public interface RacunService {
	
	public Page<Racun> findAll(int pageNum);
	public Racun findOne(Long id);
	public Racun save(Racun racun);
	public Racun delete(Long id);
	public Page<Racun> pretraga(String jmbg, Long idBanka, int pageNum);
	public Racun findByBroj(String broj);

}
