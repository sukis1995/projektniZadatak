package projekat.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projekat.test.model.Banka;
import projekat.test.model.Racun;
import projekat.test.repository.BankaRepository;
import projekat.test.service.BankaService;
import projekat.test.service.RacunService;

@Service
public class BankaJpaService implements BankaService {
	@Autowired
	BankaRepository bankaRepository;
	@Autowired
	RacunService racunService;

	@Override
	public List<Banka> findAll() {
		// TODO Auto-generated method stub
		return bankaRepository.findAll();
	}

	@Override
	public Banka findOne(Long id) {
		// TODO Auto-generated method stub
		return bankaRepository.findOne(id);
	}

	@Override
	public Banka save(Banka banka) {
		// TODO Auto-generated method stub
		return bankaRepository.save(banka);
	}

	@Override
	public Banka prenos(String uplatilac, String primalac, Double iznos) {
		Racun racunUplatioca= racunService.findByBroj(uplatilac);
		Racun racunPrimaoca= racunService.findByBroj(primalac);
		if(racunUplatioca!=null && racunPrimaoca!=null) {
			
		Banka banka= racunUplatioca.getBanka();
		double procenat= racunUplatioca.getTipRacuna().getProcenat();
		double provizija= iznos*procenat;
		if(provizija>1000) {
			banka.setSredstvaBanke(banka.getSredstvaBanke()+ 1000);
		}else {
			banka.setSredstvaBanke(banka.getSredstvaBanke()+ iznos*provizija);
		}
	
		
		racunUplatioca.setStanjeRacuna(racunUplatioca.getStanjeRacuna()-iznos-provizija);
		racunPrimaoca.setStanjeRacuna(racunPrimaoca.getStanjeRacuna()+iznos);
		
		racunService.save(racunPrimaoca);
		racunService.save(racunUplatioca);
		bankaRepository.save(banka);
		System.out.println("Uspeo prenos");
		return banka;
		}else {
			throw new IllegalStateException();
		}
	}
	
	

}
