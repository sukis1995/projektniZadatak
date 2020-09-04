package projekat.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import projekat.test.dto.RacunDTO;
import projekat.test.model.Racun;

@Component
public class RacunToRacunDTO implements Converter<Racun, RacunDTO>{

	@Override
	public RacunDTO convert(Racun racun) {
		if(racun==null) {
			return null;
		}
		RacunDTO dto= new RacunDTO();
		dto.setBrojRacuna(racun.getBrojRacuna());
		dto.setId(racun.getId());
		dto.setIdBanka(racun.getBanka().getId());
		dto.setIdTipRacuna(racun.getTipRacuna().getId());
		dto.setImePrezime(racun.getImePrezime());
		dto.setJmbg(racun.getJmbg());
		dto.setNazivBanka(racun.getBanka().getNaziv());
		dto.setStanjeRacuna(racun.getStanjeRacuna());
		dto.setNazivTipRacuna(racun.getTipRacuna().getNaziv());
		return dto;
	}
	
	public List<RacunDTO> convert(List<Racun> racuni){
		List<RacunDTO> dtos= new ArrayList<RacunDTO>();
		for (Racun racun : racuni) {
			RacunDTO dto= convert(racun);
			dtos.add(dto);
		}
		
		return dtos;
		
	}

}
