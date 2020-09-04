package projekat.test.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Banka {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, unique = true)
	private String naziv;
	@Column
	private Double sredstvaBanke;
	@OneToMany(mappedBy = "banka", fetch = FetchType.LAZY)
	private List<TipRacuna> tipoviRacuna= new ArrayList<TipRacuna>();
	@OneToMany(mappedBy ="banka",fetch = FetchType.LAZY )
	private List<Racun> racuni= new ArrayList<Racun>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public Double getSredstvaBanke() {
		return sredstvaBanke;
	}
	public void setSredstvaBanke(Double sredstvaBanke) {
		this.sredstvaBanke = sredstvaBanke;
	}
	public List<TipRacuna> getTipoviRacuna() {
		return tipoviRacuna;
	}
	public void setTipoviRacuna(List<TipRacuna> tipoviRacuna) {
		this.tipoviRacuna = tipoviRacuna;
	}
	public List<Racun> getRacuni() {
		return racuni;
	}
	public void setRacuni(List<Racun> racuni) {
		this.racuni = racuni;
	}
	
	
	public void addTipRacuna(TipRacuna tipRacuna) {
		this.tipoviRacuna.add(tipRacuna);
		if(tipRacuna.getBanka()!=this) {
			tipRacuna.setBanka(this);
		}
	}
	
	public void addRacun(Racun racun) {
		this.racuni.add(racun);
		if(racun.getBanka()!=this) {
			racun.setBanka(this);
		}
	}

}
