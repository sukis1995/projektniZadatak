package projekat.test.service;

import projekat.test.model.Banka;

import java.util.List;

public interface BankaService {
	
	public List<Banka> findAll();
	public Banka findOne(Long id);
	public Banka save(Banka banka);
	public Banka prenos(String uplatilac, String primalac, Double iznos);
	

}
