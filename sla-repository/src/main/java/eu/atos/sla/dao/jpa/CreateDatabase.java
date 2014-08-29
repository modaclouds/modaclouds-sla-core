package eu.atos.sla.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;


public class CreateDatabase {
	@PersistenceContext(unitName = "slarepositoryDB")
	private EntityManager manager;

	@Transactional(noRollbackFor = Exception.class)
	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("slarepositoryDB");
	}
}
