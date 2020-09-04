package projekat.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import projekat.test.model.Racun;
import projekat.test.repository.RacunRepository;
import projekat.test.service.RacunService;

@Service
public class RacunJpaService implements RacunService {
	@Autowired
	RacunRepository racunRepository;

	@Override
	public Page<Racun> findAll(int pageNum) {
		// TODO Auto-generated method stub
		return racunRepository.findAll(new PageRequest(pageNum, 2));
	}

	@Override
	public Racun findOne(Long id) {
		// TODO Auto-generated method stub
		return racunRepository.findOne(id);
	}

	@Override
	public Racun save(Racun racun) {
		// TODO Auto-generated method stub
		return racunRepository.save(racun);
	}

	@Override
	public Racun delete(Long id) {
		Racun racun= findOne(id);
		if(racun!=null) {
			racunRepository.delete(racun);
		}
		return racun;
	}

	@Override
	public Page<Racun> pretraga(String jmbg, Long idBanka, int pageNum) {
		// TODO Auto-generated method stub
		if(jmbg!=null) {
			jmbg = "%"+ jmbg + "%";
		}
		return racunRepository.pretraga(jmbg, idBanka, new PageRequest(pageNum, 2));
	}

	@Override
	public Racun findByBroj(String broj) {
		// TODO Auto-generated method stub
		return racunRepository.pronadjiPoBrojuRacuna(broj);
	}

}
