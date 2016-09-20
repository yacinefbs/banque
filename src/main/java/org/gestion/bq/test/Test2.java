package org.gestion.bq.test;

import java.util.List;

import org.gestion.bq.entities.Compte;
import org.gestion.bq.entities.Operation;
import org.gestion.bq.metier.IBanqueMetier;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		IBanqueMetier metier = (IBanqueMetier) context.getBean("metier");
		Compte cp = metier.consulterCompte("CC1");
		
		System.out.println("Solde : " + cp.getSolde());
		System.out.println("Date : " + cp.getDateCreation());
		System.out.println("Client : " + cp.getClient().getNomClient());
		System.out.println("Employé : " + cp.getEmploye().getNomEmploye());
		
		
		List<Operation> ops = metier.consulterOperations("CC1", 0, 5);
		
		for(Operation op:ops){
			System.out.println("*****************");
			System.out.println(op.getNumeroOperation());
			System.out.println(op.getDateOperation());
			System.out.println(op.getMontant());
			System.out.println(op.getClass().getSimpleName());
		}
		
	}

}
