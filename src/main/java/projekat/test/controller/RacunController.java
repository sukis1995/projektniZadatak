package projekat.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import projekat.test.dto.RacunDTO;
import projekat.test.model.Racun;
import projekat.test.service.RacunService;
import projekat.test.support.RacunDTOToRacun;
import projekat.test.support.RacunToRacunDTO;

@Controller
@RequestMapping("/api/racuni")
public class RacunController {
	@Autowired
	RacunService racunService;
	@Autowired
	RacunToRacunDTO toDto;
	@Autowired
	RacunDTOToRacun toRacun;
	
	/*@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<RacunDTO>> findAll(){
		List<Racun> racuni= racunService.findAll();
		if(racuni==null || racuni.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(toDto.convert(racuni), HttpStatus.OK);
	}*/
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<RacunDTO> findOne(@PathVariable("id") Long id){
		Racun racun= racunService.findOne(id);
		if(racun==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(toDto.convert(racun), HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<RacunDTO> save (@Validated @RequestBody RacunDTO dto){
		
		Racun saved= racunService.save(toRacun.convert(dto));
		if(saved==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(toDto.convert(saved), HttpStatus.CREATED);
		
	}
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}" )
	public ResponseEntity<RacunDTO> update(@PathVariable("id") Long id, @Validated @RequestBody RacunDTO dto){
		System.out.println("Update ");
		Racun racun = toRacun.convert(dto);
		if(racun==null || !(racun.getId().equals(id))) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Racun update= racunService.save(racun);
		if(update==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(toDto.convert(update), HttpStatus.ACCEPTED);
		
	}
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<RacunDTO> delete(@PathVariable("id") Long id){
		Racun racun = racunService.findOne(id);
		if(racun==null || racun.getStanjeRacuna()==null || racun.getStanjeRacuna()>0) {
			return new ResponseEntity<RacunDTO>(HttpStatus.BAD_REQUEST);
		}
		Racun deleted= racunService.delete(id);
		if(deleted==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(toDto.convert(deleted), HttpStatus.NO_CONTENT);
		
	}
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handler(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<RacunDTO>> pretraga(@RequestParam(required = false ) String jmbg ,
			@RequestParam(required = false) Long idBanka, 
			@RequestParam(defaultValue = "0") int pageNum){
		Page<Racun> racuniStranica;
		if(jmbg!=null || idBanka!=null) {
			racuniStranica=racunService.pretraga(jmbg, idBanka,pageNum);
		}else {
			racuniStranica=racunService.findAll(pageNum);
		}
		List<Racun> racuni= racuniStranica.getContent();
		HttpHeaders headers= new HttpHeaders();
		headers.add("totalPages", Integer.toString(racuniStranica.getTotalPages()));
		if(racuni==null || racuni.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(toDto.convert(racuni),headers, HttpStatus.OK);
	}

}
