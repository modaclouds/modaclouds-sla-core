package eu.atos.sla.service.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.atos.sla.dao.IProviderDAO;
import eu.atos.sla.dao.ITemplateDAO;
import eu.atos.sla.datamodel.ITemplate;
import eu.atos.sla.datamodel.bean.Template;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/sla-repository-db-JPA-test-context.xml")
public class TemplateDAOJpaTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	ITemplateDAO templateDAO;

	@Autowired
	IProviderDAO providerDAO;

	@Test
	public void notNull() {
		if (templateDAO == null)
			fail();
	}

	@Test
	public void save() {

		String templateUuid = UUID.randomUUID().toString();

		ITemplate template = new Template();
		template.setText("Template name 1");
		template.setUuid(templateUuid);
		template.setServiceId("serviceTest");

		@SuppressWarnings("unused")
		ITemplate saved = new Template();
		try {
			saved = templateDAO.save(template);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

	}

	@Test
	public void getById() {
		@SuppressWarnings("unused")
		int size = templateDAO.getAll().size();

		String templateUuid = UUID.randomUUID().toString();

		Template template = new Template();
		template.setText("Template name 1");
		template.setUuid(templateUuid);
		template.setServiceId("serviceTest");

		ITemplate saved = new Template();
		try {
			saved = templateDAO.save(template);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}

		assertEquals(templateUuid, saved.getUuid());
		assertEquals("Template name 1", saved.getText());
		assertEquals(templateUuid, saved.getUuid());

		ITemplate nullTemplate = templateDAO.getById(new Long(30000));
		assertEquals(null, nullTemplate);

	}

	@Test
	public void detete() {

		int size = templateDAO.getAll().size();
		String uuid = UUID.randomUUID().toString();
		ITemplate template = new Template();
		template.setUuid(uuid);
		template.setText("template text");
		template.setServiceId("serviceTest");

		@SuppressWarnings("unused")
		ITemplate saved = new Template();
		try {
			saved = templateDAO.save(template);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		ITemplate templateFromDatabase = templateDAO.getAll().get(size);
		Long id = templateFromDatabase.getId();

		templateFromDatabase = templateDAO.getById(id);
		boolean deleted = templateDAO.delete(templateFromDatabase);
		assertTrue(deleted);

		deleted = templateDAO.delete(templateFromDatabase);
		assertTrue(!deleted);

		templateFromDatabase = templateDAO.getById(new Long(223232));
		assertEquals(null, templateFromDatabase);
	}

	@Test
	public void update() {

		int size = templateDAO.getAll().size();
		String uuid = UUID.randomUUID().toString();

		Template template = new Template();
		template.setText("template text");
		template.setServiceId("serviceTest");
		template.setUuid(uuid);

		@SuppressWarnings("unused")
		ITemplate saved = new Template();
		try {
			saved = templateDAO.save(template);
		} catch (Exception e) {
			fail();
		}

		ITemplate templateFromDatabase = templateDAO.getAll().get(size);
		Long id = templateFromDatabase.getId();
		assertEquals("template text", templateFromDatabase.getText());
		assertEquals(uuid, templateFromDatabase.getUuid());

		templateFromDatabase.setText("text updated");
		boolean updated = templateDAO.update(uuid, templateFromDatabase);
		assertTrue(updated);

		templateFromDatabase = templateDAO.getById(id);
		assertEquals("text updated", templateFromDatabase.getText());

	}

}
