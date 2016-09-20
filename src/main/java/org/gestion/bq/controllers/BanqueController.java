package org.gestion.bq.controllers;

import java.util.List;

import javax.validation.Valid;

import org.gestion.bq.entities.Compte;
import org.gestion.bq.entities.Operation;
import org.gestion.bq.metier.IBanqueMetier;
import org.gestion.bq.models.BanqueForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BanqueController {
	@Autowired
	private IBanqueMetier metier;
	
	@RequestMapping(value="/index")
	public String index(Model model){
		model.addAttribute("banqueForm", new BanqueForm());
		return "banque";
	}
	
	@RequestMapping(value="/chargerCompte")
	public String chargerCompte(@Valid BanqueForm bf, BindingResult bindingResult, Model model){
		
		if(bindingResult.hasErrors()){
			return "banque";
		}
		
		chargerCompte(bf);
		
		model.addAttribute("banqueForm", bf);
		return "banque";
	}
	
	@RequestMapping(value="/saveOperation")
	public String saveOp(@Valid BanqueForm bf, BindingResult BindingResult){
		
		//S'il ya des erreurs, on revient � la page "banque.jsp"
		if(BindingResult.hasErrors()){
			return "banque";
		}
		
		try {
			if(bf.getAction()!=null){
				if(bf.getTypeOperation().equals("VER")){
					metier.verser(bf.getMontant(), bf.getCode(), 1L);
				}
				else if(bf.getTypeOperation().equals("RET")){
					metier.retirer(bf.getMontant(), bf.getCode(), 1L);
				}
				else if(bf.getTypeOperation().equals("VIR")){
					metier.virement(bf.getMontant(), bf.getCode(), bf.getCode2(), 1L);
				}
			}
		} catch (Exception e) {
			bf.setException(e.getMessage());
		}
		
		chargerCompte(bf);
		
		
		return "banque";
	}
	
	
	public void chargerCompte(BanqueForm bf){
		//Charger le compte
		
		try {
			Compte cp = metier.consulterCompte(bf.getCode());
			bf.setTypeCompte(cp.getClass().getSimpleName());
			bf.setCompte(cp);
			int pos = bf.getNbLigne()*bf.getPage();
			List<Operation> ops = metier.consulterOperations(bf.getCode(), pos, bf.getNbLigne());
			bf.setOperations(ops);
			long nbOp = metier.getNombreOperation(bf.getCode());
			bf.setNombrePages((int)(nbOp / bf.getNbLigne()) + 1);
			
		} catch (Exception e) {
			bf.setException(e.getMessage());
		}
	}
}
