package projekat.test.service;

import java.util.List;

import projekat.test.model.TipRacuna;

public interface TipRacunaService {
	
	public List<TipRacuna> findAll();
	public TipRacuna findOne(Long id);
	public TipRacuna save(TipRacuna tipRacuna);
	public List<TipRacuna> findByBanka(Long idBanka);

}
