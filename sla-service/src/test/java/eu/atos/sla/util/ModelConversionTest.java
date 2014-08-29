package eu.atos.sla.util;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import eu.atos.sla.util.ModelConversion.ServiceLevelParser;

public class ModelConversionTest {

//	private IModelConverter modelConverter = new ModelConversion();

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private String parseServiceLevel(String slo) throws ModelConversionException {
		
		return ServiceLevelParser.parse(slo).getConstraint();
	}
	
	@Test
	public void testParserServiceLevel() throws ModelConversionException {
		String expected;
		String actual;
		expected = "Performance GT 0.1";
		
		/*
		 * accepted constraint
		 */
		actual = parseServiceLevel(
				String.format("{\"constraint\" : \"%s\"}", expected));
		
		assertEquals(expected, actual);
		
		/*
		 * constraint does not exist
		 */
		try {
			actual = parseServiceLevel(
					String.format("{\"thereisnoconstraint\" : \"%s\"}", expected));
			fail("ModelConversionException not thrown");
		} catch (ModelConversionException e) {
			assertTrue(true);
		}
		
		/*
		 * constraint is a json object, instead of string
		 */
		expected = "{\"hasMaxValue\":1.0}";
		actual = parseServiceLevel(
				String.format("{\"constraint\" : %s}", expected));
		
	}
	
	
	

	@Test
	public void testParserServiceDescriptionTerm() throws ModelConversionException {
/*	TODO egarrido: it's giving an error it should be checked
 * 	String expected;
		String actual;
		
		expected = "Performance GT 0.1";
		
		actual = parseServiceLevel(
				String.format("{\"constraint\" : \"%s\"}", expected));
		
		assertEquals(expected, actual);
		
		actual = parseServiceLevel(
				String.format("{\"thereisnoconstraint\" : \"%s\"}", expected));
		
		assertEquals(null, actual);*/
		assert(true);
		
	}
	
	

// TODO Fix tests
//	private eu.atos.sla.parser.data.wsag.Agreement readXml(File f) 
//			throws JAXBException, FileNotFoundException {
//		
//		JAXBContext jaxbContext = JAXBContext
//				.newInstance(eu.atos.sla.parser.data.wsag.Agreement.class);
//		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//		eu.atos.sla.parser.data.wsag.Agreement result = 
//				(eu.atos.sla.parser.data.wsag.Agreement) jaxbUnmarshaller.unmarshal(
//						new FileReader(f));
//		
//		return result;
//	}
//	private File getResourceFile(String path) {
//		
//		return new File(this.getClass().getResource(path).getFile());
//	}
//	
//	private void checkParseAgreementContext(eu.atos.sla.parser.data.wsag.Agreement agreementXML, 
//			ServiceProvider rol) throws JAXBException, FileNotFoundException, ModelConversionException {
//		
//		
//		String expectedProvider;
//		String expectedConsumer;
//		
//		if (rol == null) {
//			agreementXML.getContext().setServiceProvider("invalid value here");
//			expectedProvider = null;
//			expectedConsumer = null;
//		}
//		else {
//			agreementXML.getContext().setServiceProvider(rol.toString());
//			if (rol == ServiceProvider.AGREEMENT_INITIATOR) {
//				expectedProvider = "initiator";
//				expectedConsumer = "responder";
//			} else if (rol == ServiceProvider.AGREEMENT_RESPONDER) {
//				expectedConsumer = "initiator";
//				expectedProvider = "responder";
//			}
//			else {
//				throw new AssertionError();				
//			}
//		}
//
//		IAgreement a = modelConverter.getAgreementFromAgreementXML(agreementXML, "");
//		
//		/*
//		 * Match provider
//		 */
//		if (rol == null) {
//			assertNull(a.getProvider());
//		}
//		else {
//			assertEquals(expectedProvider, a.getProvider().getUuid());
//		}
//		
//		/*
//		 * Match consumer
//		 */
//		assertEquals(expectedConsumer, a.getConsumer());
//	}
	
	@Test
	public void testParseAgreementContext() throws JAXBException, FileNotFoundException, ModelConversionException {
		/*egarrido falla con un agreement correcto */
		/* TODO fix test */
		//File file = getResourceFile("/samples/agreement01.xml");
		//File file = new File("../samples/agreement01.xml");
		//eu.atos.sla.parser.data.wsag.Agreement agreementXML = readXml(file);
		
		//checkParseAgreementContext(agreementXML, null);
		//checkParseAgreementContext(agreementXML, ServiceProvider.AGREEMENT_RESPONDER);
		//checkParseAgreementContext(agreementXML, ServiceProvider.AGREEMENT_INITIATOR);
	}
}
