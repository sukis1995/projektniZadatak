package projekat.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import projekat.test.dto.BankaDTO;
import projekat.test.model.Banka;

@Component
public class BankaToBankaDTO implements Converter<Banka, BankaDTO> {

	@Override
	public BankaDTO convert(Banka banka) {
		
		if(banka==null) {
			return null;
		}
		BankaDTO dto= new BankaDTO();
		dto.setId(banka.getId());
		dto.setNaziv(banka.getNaziv());
		dto.setSredstvaBanke(banka.getSredstvaBanke());
		
		return dto;
	}
	
	public List<BankaDTO> convert(List<Banka> banke){
		List<BankaDTO> dtos= new ArrayList<BankaDTO>();
		for (Banka banka : banke) {
			BankaDTO dto= convert(banka);
			dtos.add(dto);
			
		}
		
		return dtos;
	}

}
