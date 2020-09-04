package projekat.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import projekat.test.dto.TipRacunaDTO;
import projekat.test.model.TipRacuna;

@Component
public class TipRacunToTipRacunaDTO implements Converter<TipRacuna, TipRacunaDTO>{
	
	

	@Override
	public TipRacunaDTO convert(TipRacuna tipRacuna) {
		TipRacunaDTO dto= new TipRacunaDTO();
		dto.setId(tipRacuna.getId());
		dto.setIdBanka(tipRacuna.getBanka().getId());
		dto.setNaziv(tipRacuna.getNaziv());
		dto.setNazivBanka(tipRacuna.getBanka().getNaziv());
		dto.setProcenat(tipRacuna.getProcenat());
		
		
		return dto;
	}
	
	public List<TipRacunaDTO> convert(List<TipRacuna> tipoviRacuna){
		List<TipRacunaDTO> dtos= new ArrayList<TipRacunaDTO>();
		for (TipRacuna tipRacuna : tipoviRacuna) {
			TipRacunaDTO dto= convert(tipRacuna);
			dtos.add(dto);
		}
		
		return dtos;
	}

}
