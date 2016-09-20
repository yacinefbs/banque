package org.gestion.bq.dao;

import java.util.List;

import org.gestion.bq.entities.Client;
import org.gestion.bq.entities.Compte;
import org.gestion.bq.entities.Employe;
import org.gestion.bq.entities.Groupe;
import org.gestion.bq.entities.Operation;

public interface IBanqueDao {
	public Client addClient(Client c);
	public Employe addEmploye(Employe e, Long codeSup);
	public Groupe addGroupe(Groupe g);
	public void addEmployeToGroupe(Long codeEmp, Long codeGr);
	public Compte addCompte(Compte cp, Long codeCli, Long codeEmp);
	public Operation addOperation(Operation op, String codeCpte, Long codeEmp);
//	public void versement(String codeCpte, double mt, Long codeEmp);
//	public void retrait(String codeCpte, double mt, Long codeEmp);
//	public void virement(String codeCpte1, String codeCpte2, double mt, Long codeEmp);
	
	public Compte consulterCompte(String codeCpte);
	public List<Operation> consulterOperations(String codeCpte, int position, int nbOperation);
	public Client consulterClient(Long codeCli);
	public List<Client> consulterClients(String mc);
	public List<Compte> getComptesByClient(Long codeCli);
	public List<Compte> getComptesByEmploye(Long codeEmp);
	public List<Employe> getEmploye();
	public List<Groupe> getGroupes();
	public List<Employe> getEmployesByGroupe(Long codeGr);
	public long getNombreOperation(String numCpte);
}
