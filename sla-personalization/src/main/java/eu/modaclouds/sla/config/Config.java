package eu.modaclouds.sla.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Config implements InitializingBean {
	private static Logger logger = LoggerFactory.getLogger(Config.class);
	
	@Value("${MODACLOUDS_SLACORE_URL}")
	private String MODACLOUDS_SLACORE_URL;

	@Value("${MODACLOUDS_MONITORING_MANAGER_URL}")
	private String MODACLOUDS_MONITORING_MANAGER_URL;

	@Value("${MODACLOUDS_ACCOUNTING_URL}")
	private String MODACLOUDS_ACCOUNTING_URL;
	
	@Value("${MODACLOUDS_MYSQL_URL}")
	private String MODACLOUDS_MYSQL_URL;
	
	@Value("${MODACLOUDS_MYSQL_USERNAME}")
	private String MODACLOUDS_MYSQL_USERNAME;

	@Value("${MODACLOUDS_MAIL_USER}")
	private String MODACLOUDS_MAIL_USER;
	
	@Value("${MODACLOUDS_MAIL_PWD}")
	private String MODACLOUDS_MAIL_PWD;
	
	@Value("${MODACLOUDS_MAIL_FROM}")
	private String MODACLOUDS_MAIL_FROM;
	
	@Value("${MODACLOUDS_MAIL_TO}")
	private String MODACLOUDS_MAIL_TO;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		logger.info("\n  {}='{}'\n  {}='{}'\n  {}='{}'\n  {}='{}'" + 
					"\n  {}='{}'\n  {}='{}'\n  {}='{}'\n  {}='{}'" + 
					"\n  {}='{}'",
					"MODACLOUDS_SLACORE_URL", MODACLOUDS_SLACORE_URL,
					"MODACLOUDS_MONITORING_MANAGER_URL", MODACLOUDS_MONITORING_MANAGER_URL,
					"MODACLOUDS_ACCOUNTING_URL", MODACLOUDS_ACCOUNTING_URL,
					"MODACLOUDS_MYSQL_URL", MODACLOUDS_MYSQL_URL,
					"MODACLOUDS_MYSQL_USERNAME", MODACLOUDS_MYSQL_USERNAME,
					"MODACLOUDS_MAIL_USER", MODACLOUDS_MAIL_USER,
					"MODACLOUDS_MAIL_PWD", MODACLOUDS_MAIL_PWD,
					"MODACLOUDS_MAIL_FROM", MODACLOUDS_MAIL_FROM,
					"MODACLOUDS_MAIL_TO", MODACLOUDS_MAIL_TO);
	}

}
