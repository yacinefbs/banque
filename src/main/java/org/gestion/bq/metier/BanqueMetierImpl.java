package org.gestion.bq.metier;

import java.util.Date;
import java.util.List;

import org.gestion.bq.dao.IBanqueDao;
import org.gestion.bq.entities.Client;
import org.gestion.bq.entities.Compte;
import org.gestion.bq.entities.Employe;
import org.gestion.bq.entities.Groupe;
import org.gestion.bq.entities.Operation;
import org.gestion.bq.entities.Retrait;
import org.gestion.bq.entities.Versement;
import org.springframework.transaction.annotation.Transactional;

@Transactional 
public class BanqueMetierImpl implements IBanqueMetier {

	private IBanqueDao dao;
	
	public void setDao(IBanqueDao dao) {
		this.dao = dao;
	}

	@Override
	public Client addClient(Client c) {
		// TODO Auto-generated method stub
		return dao.addClient(c);
	}

	@Override
	public Employe addEmploye(Employe e, Long codeSup) {
		// TODO Auto-generated method stub
		return dao.addEmploye(e, codeSup);
	}

	@Override
	public Groupe addGroupe(Groupe g) {
		// TODO Auto-generated method stub
		return dao.addGroupe(g);
	}

	@Override
	public void addEmployeToGroupe(Long codeEmp, Long codeGr) {
		// TODO Auto-generated method stub
		dao.addEmployeToGroupe(codeEmp, codeGr);
	}

	@Override
	public Compte addCompte(Compte cp, Long codeCli, Long codeEmp) {
		// TODO Auto-generated method stub
		return dao.addCompte(cp, codeCli, codeEmp);
	}

	@Override
	public void verser(double mt, String cpte, Long codeEmp) {
		// TODO Auto-generated method stub
		dao.addOperation(new Versement(new Date(), mt), cpte, codeEmp);
		Compte cp = dao.consulterCompte(cpte);
		cp.setSolde(cp.getSolde() + mt);
		
	}

	@Override
	public void retirer(double mt, String cpte, Long codeEmp) {
		// TODO Auto-generated method stub
		dao.addOperation(new Retrait(new Date(), mt), cpte, codeEmp);
		Compte cp = dao.consulterCompte(cpte);
		cp.setSolde(cp.getSolde() - mt);
	}

	@Override
	public void virement(double mt, String cpte1, String cpte2, Long codeEmp) {
		// TODO Auto-generated method stub
		retirer(mt, cpte1, codeEmp);
		verser(mt, cpte2, codeEmp);
	}

	@Override
	public Compte consulterCompte(String codeCpte) {
		// TODO Auto-generated method stub
		return dao.consulterCompte(codeCpte);
	}

	@Override
	public List<Operation> consulterOperation(String codeCpte) {
		// TODO Auto-generated method stub
		return dao.consulterOperation(codeCpte);
	}

	@Override
	public Client consulterClient(Long codeCli) {
		// TODO Auto-generated method stub
		return dao.consulterClient(codeCli);
	}

	@Override
	public List<Client> consulterClients(String mc) {
		// TODO Auto-generated method stub
		return dao.consulterClients(mc);
	}

	@Override
	public List<Compte> getComptesByClient(Long codeCli) {
		// TODO Auto-generated method stub
		return dao.getComptesByClient(codeCli);
	}

	@Override
	public List<Compte> getComptesByEmploye(Long codeEmp) {
		// TODO Auto-generated method stub
		return dao.getComptesByEmploye(codeEmp);
	}

	@Override
	public List<Employe> getEmploye() {
		// TODO Auto-generated method stub
		return dao.getEmploye();
	}

	@Override
	public List<Groupe> getGroupes() {
		// TODO Auto-generated method stub
		return dao.getGroupes();
	}

	@Override
	public List<Employe> getEmployesByGroupe(Long codeGr) {
		// TODO Auto-generated method stub
		return dao.getEmployesByGroupe(codeGr);
	}
}
