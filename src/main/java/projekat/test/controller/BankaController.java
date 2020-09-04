package projekat.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import projekat.test.dto.BankaDTO;
import projekat.test.dto.TipRacunaDTO;
import projekat.test.model.Banka;
import projekat.test.model.TipRacuna;
import projekat.test.service.BankaService;
import projekat.test.service.TipRacunaService;
import projekat.test.support.BankaToBankaDTO;
import projekat.test.support.TipRacunToTipRacunaDTO;

@Controller
@RequestMapping("/api/banke")
public class BankaController {
	
	@Autowired
	BankaService bankaService;
	@Autowired
	BankaToBankaDTO toDto;
	@Autowired
	TipRacunaService tipRacunaService;
	@Autowired
	TipRacunToTipRacunaDTO toDtoTipRacuna;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<BankaDTO>> findAll(){
		List<Banka> banke= bankaService.findAll();
		if(banke==null || banke.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<List<BankaDTO>>(toDto.convert(banke), HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/tipovi-racuna")
	public ResponseEntity<List<TipRacunaDTO>> findByBanka(@PathVariable("id") Long id){
		System.out.println("Doslo je do ovoga");
		List<TipRacuna> tipovi= tipRacunaService.findByBanka(id);
		if(tipovi==null || tipovi.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		System.out.println("asfasfas");
		System.out.println(tipovi.get(1).getNaziv());
		
		return new ResponseEntity<List<TipRacunaDTO>>(toDtoTipRacuna.convert(tipovi), HttpStatus.OK);
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/prenos")
	public ResponseEntity<BankaDTO> prenos(@RequestParam String uplatilac, @RequestParam String primalac, 
			@RequestParam Double iznos){
		System.out.println("Do ovde je doslo");
		Banka banka = bankaService.prenos(uplatilac, primalac, iznos);
		if(banka==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<BankaDTO>(toDto.convert(banka), HttpStatus.OK);
		
	}
}
