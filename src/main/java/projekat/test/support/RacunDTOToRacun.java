package projekat.test.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import projekat.test.dto.RacunDTO;
import projekat.test.model.Banka;
import projekat.test.model.Racun;
import projekat.test.model.TipRacuna;
import projekat.test.service.BankaService;
import projekat.test.service.RacunService;
import projekat.test.service.TipRacunaService;

@Component
public class RacunDTOToRacun implements Converter<RacunDTO, Racun> {
	@Autowired
	TipRacunaService tipRacunaService;
	@Autowired
	BankaService bankaService;
	@Autowired
	RacunService racunService;

	@Override
	public Racun convert(RacunDTO dto) {
		
		TipRacuna tipRacuna= tipRacunaService.findOne(dto.getIdTipRacuna());
		Banka banka= bankaService.findOne(dto.getIdBanka());
		
		if(banka==null || tipRacuna==null) {
			throw new IllegalStateException();
		}
		
		Racun racun= null;
		if(dto.getId()!=null) {
			racun = racunService.findOne(dto.getId());
		}else {
			racun= new Racun();
		}
		
		racun.setBanka(banka);
		racun.setBrojRacuna(dto.getBrojRacuna());
		racun.setImePrezime(dto.getImePrezime());
		racun.setJmbg(dto.getJmbg());
		if(dto.getStanjeRacuna()==null) {
			racun.setStanjeRacuna(0.0);
		}else {
			racun.setStanjeRacuna(dto.getStanjeRacuna());
		}
			
	
		racun.setTipRacuna(tipRacuna);
		
		
		
		return racun;
	}

}
