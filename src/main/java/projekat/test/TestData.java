package projekat.test;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import projekat.test.model.Banka;
import projekat.test.model.Racun;
import projekat.test.model.TipRacuna;
import projekat.test.service.BankaService;
import projekat.test.service.RacunService;
import projekat.test.service.TipRacunaService;

@Component
public class TestData {
	@Autowired
	RacunService racunService;
	@Autowired
	TipRacunaService tipRacunaService;
	@Autowired
	BankaService bankaService;
	
	@PostConstruct
	public void init() {
		
		Banka banka = new Banka();
		banka.setNaziv("Erste");
		banka.setSredstvaBanke(446846.00);
		Banka banka1 = new Banka();
		banka1.setNaziv("AIK");
		banka1.setSredstvaBanke(818561.00);
		
		bankaService.save(banka);
		bankaService.save(banka1);
		
		TipRacuna tipRacuna=new TipRacuna();
		tipRacuna.setNaziv("Premium");
		tipRacuna.setProcenat(0.5);

		TipRacuna tipRacuna1=new TipRacuna();
		tipRacuna1.setNaziv("Standard");
		tipRacuna1.setProcenat(1.5);
		
		TipRacuna tipRacuna2=new TipRacuna();
		tipRacuna2.setNaziv("Gold");
		tipRacuna2.setProcenat(0.7);

		TipRacuna tipRacuna3=new TipRacuna();
		tipRacuna3.setNaziv("Silver");
		tipRacuna3.setProcenat(1.4);
		tipRacunaService.save(tipRacuna);
		tipRacunaService.save(tipRacuna1);
		tipRacunaService.save(tipRacuna2);
		tipRacunaService.save(tipRacuna3);
		
		Racun racun= new Racun();
		racun.setBrojRacuna("1");
		racun.setImePrezime("Petar Petrovic");
		racun.setJmbg("1708995456788");
		racun.setStanjeRacuna(2545.00);
		Racun racun1= new Racun();
		racun1.setBrojRacuna("2");
		racun1.setImePrezime("Marko Markovic");
		racun1.setJmbg("2609995800006");
		racun1.setStanjeRacuna(12614.00);
		
		racunService.save(racun);
		racunService.save(racun1);
		
		racun.setBanka(banka);
		racun.setTipRacuna(tipRacuna);
		racun1.setBanka(banka1);
		racun1.setTipRacuna(tipRacuna1);
		
		banka.addRacun(racun);
		
		
		banka1.addRacun(racun1);
		
		banka.addTipRacuna(tipRacuna);
		banka.addTipRacuna(tipRacuna1);
		banka1.addTipRacuna(tipRacuna2);
		banka1.addTipRacuna(tipRacuna3);

		bankaService.save(banka);
		bankaService.save(banka1);
		
		tipRacunaService.save(tipRacuna);
		tipRacunaService.save(tipRacuna1);
		tipRacunaService.save(tipRacuna2);
		tipRacunaService.save(tipRacuna3);
		
		racunService.save(racun);
		racunService.save(racun1);
	}

}
