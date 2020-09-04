package projekat.test.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class TipRacuna {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false, unique = true)
	private String naziv;
	@Column
	private Double procenat;
	@ManyToOne(fetch = FetchType.EAGER)
	private Banka banka;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipRacuna")
	private List<Racun>racuni= new ArrayList<Racun>();
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
	public Double getProcenat() {
		return procenat;
	}
	public void setProcenat(Double procenat) {
		this.procenat = procenat;
	}
	public Banka getBanka() {
		return banka;
	}
	public void setBanka(Banka banka) {
		this.banka = banka;
		if(!banka.getTipoviRacuna().contains(this)) {
			banka.getTipoviRacuna().add(this);
		}
	}
	public List<Racun> getRacuni() {
		return racuni;
	}
	public void setRacuni(List<Racun> racuni) {
		this.racuni = racuni;
	}
	
	public void addRacun(Racun racun) {
		this.racuni.add(racun);
		if(racun.getTipRacuna()!=this) {
			racun.setTipRacuna(this);
		}
	}
	
	
	
	

}
