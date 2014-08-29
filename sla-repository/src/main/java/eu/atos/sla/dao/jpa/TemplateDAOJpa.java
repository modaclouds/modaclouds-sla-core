package eu.atos.sla.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import eu.atos.sla.dao.ITemplateDAO;
import eu.atos.sla.datamodel.ITemplate;
import eu.atos.sla.datamodel.bean.Template;

@Repository("TemplateRepository")
public class TemplateDAOJpa implements ITemplateDAO {
	private static Logger logger = Logger.getLogger(TemplateDAOJpa.class);
	private EntityManager entityManager;

	@PersistenceContext(unitName = "slarepositoryDB")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Template getById(Long id) {
		return entityManager.find(Template.class, id);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Template getByUuid(String uuid) {
		try {
			Query query = entityManager
					.createNamedQuery(Template.QUERY_FIND_BY_UUID);
			query.setParameter("uuid", uuid);
			Template template = null;
			template = (Template) query.getSingleResult();
			return template;
		} catch (NoResultException e) {
			logger.debug("No Result found: " + e);
			return null;
		}
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<ITemplate> getByProvider(String providerUuid) {
		try {
			TypedQuery<ITemplate> query = entityManager.createNamedQuery(
					Template.QUERY_FIND_BY_PROVIDER, ITemplate.class);
			query.setParameter("providerUuid", providerUuid);
			List<ITemplate> templates = new ArrayList<ITemplate>();
			templates = (List<ITemplate>) query.getResultList();
			return templates;
		} catch (NoResultException e) {
			logger.debug("No Result found: " + e);
			return null;
		}

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<ITemplate> getByServiceId(String serviceId) {

		TypedQuery<ITemplate> query = entityManager.createNamedQuery(
				Template.QUERY_FIND_BY_SERVICEID, ITemplate.class);
		query.setParameter("serviceId", serviceId);
		List<ITemplate> templates = new ArrayList<ITemplate>();
		templates = (List<ITemplate>) query.getResultList();

		if (templates != null) {
			logger.debug("Number of templates:" + templates.size());
		} else {
			logger.debug("No Result found.");
		}

		return templates;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<ITemplate> getByProviderAndServiceId(String providerUuid, String serviceId) {

		TypedQuery<ITemplate> query = entityManager.createNamedQuery(
				Template.QUERY_FIND_BY_PROVIDER_AND_SERVICEID, ITemplate.class);
		query.setParameter("providerUuid", providerUuid);
		query.setParameter("serviceId", serviceId);
		List<ITemplate> templates = new ArrayList<ITemplate>();
		templates = (List<ITemplate>) query.getResultList();

		if (templates != null) {
			logger.debug("Number of templates:" + templates.size());
		} else {
			logger.debug("No Result found.");
		}

		return templates;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<ITemplate> getByAgreement(String agreement) {

		TypedQuery<ITemplate> query = entityManager.createNamedQuery(
				Template.QUERY_FIND_BY_AGREEMENT, ITemplate.class);
		query.setParameter("agreement", agreement);
		List<ITemplate> templates = new ArrayList<ITemplate>();
		templates = (List<ITemplate>) query.getResultList();

		if (templates != null) {
			logger.debug("Number of templates:" + templates.size());
		} else {
			logger.debug("No Result found.");
		}

		return templates;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<ITemplate> getAll() {

		TypedQuery<ITemplate> query = entityManager.createNamedQuery(
				Template.QUERY_FIND_ALL, ITemplate.class);
		List<ITemplate> templates = new ArrayList<ITemplate>();
		templates = (List<ITemplate>) query.getResultList();
		if (templates != null) {
			logger.debug("Number of templates:" + templates.size());
		} else {
			logger.debug("No Result found.");
		}

		return templates;

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ITemplate save(ITemplate template) {
		logger.info("template.getUuid() "+template.getUuid());
		entityManager.persist(template);
		entityManager.flush();
		return template;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean update(String uuid, ITemplate template) {
		Template templateDB = null;
		try {
			Query query = entityManager.createNamedQuery(Template.QUERY_FIND_BY_UUID);
			query.setParameter("uuid", uuid);
			templateDB = (Template)query.getSingleResult();
		} catch (NoResultException e) {
			logger.debug("No Result found: " + e);
		}
		
		if (templateDB!=null){
			template.setId(templateDB.getId());
			logger.info("template to update with id"+template.getId());		
			entityManager.merge(template);
			entityManager.flush();
		}else
			return false;
		return true;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean delete(ITemplate template) {
		try {
			Template templateDeleted = entityManager.getReference(
					Template.class, template.getId());
			entityManager.remove(templateDeleted);
			entityManager.flush();
			return true;
		} catch (EntityNotFoundException e) {
			logger.debug(e);
			return false;
		}
	}

}
