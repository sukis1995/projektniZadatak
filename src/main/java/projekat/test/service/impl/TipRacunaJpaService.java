package projekat.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.test.model.TipRacuna;
import projekat.test.repository.TipRacunaRepository;
import projekat.test.service.TipRacunaService;

@Service
public class TipRacunaJpaService implements TipRacunaService {
	@Autowired
	TipRacunaRepository tipRacunaRepository;

	@Override
	public List<TipRacuna> findAll() {
		// TODO Auto-generated method stub
		return tipRacunaRepository.findAll();
	}

	@Override
	public TipRacuna findOne(Long id) {
		// TODO Auto-generated method stub
		return tipRacunaRepository.findOne(id);
	}

	@Override
	public TipRacuna save(TipRacuna tipRacuna) {
		// TODO Auto-generated method stub
		return tipRacunaRepository.save(tipRacuna);
	}

	@Override
	public List<TipRacuna> findByBanka(Long idBanka) {
		// TODO Auto-generated method stub
		return tipRacunaRepository.findByBanka(idBanka);
	}

}
