package projekat.test.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class RacunDTO {
	
	private Long id;
	private String imePrezime;
	@Length(min = 13, max = 13)
	private String jmbg;
	@NotBlank
	private String brojRacuna;
	private Double stanjeRacuna;
	private Long idTipRacuna;
	private String nazivTipRacuna;
	private Long idBanka;
	private String nazivBanka;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImePrezime() {
		return imePrezime;
	}
	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}
	public String getJmbg() {
		return jmbg;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	public String getBrojRacuna() {
		return brojRacuna;
	}
	public void setBrojRacuna(String brojRacuna) {
		this.brojRacuna = brojRacuna;
	}
	public Double getStanjeRacuna() {
		return stanjeRacuna;
	}
	public void setStanjeRacuna(Double stanjeRacuna) {
		this.stanjeRacuna = stanjeRacuna;
	}
	public Long getIdTipRacuna() {
		return idTipRacuna;
	}
	public void setIdTipRacuna(Long idTipRacuna) {
		this.idTipRacuna = idTipRacuna;
	}
	public String getNazivTipRacuna() {
		return nazivTipRacuna;
	}
	public void setNazivTipRacuna(String nazivTipRacuna) {
		this.nazivTipRacuna = nazivTipRacuna;
	}
	public Long getIdBanka() {
		return idBanka;
	}
	public void setIdBanka(Long idBanka) {
		this.idBanka = idBanka;
	}
	public String getNazivBanka() {
		return nazivBanka;
	}
	public void setNazivBanka(String nazivBanka) {
		this.nazivBanka = nazivBanka;
	}
	
	

}
