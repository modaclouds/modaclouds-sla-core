package eu.atos.sla.enforcement.schedule;

import java.util.Date;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IEnforcementJob;
import eu.atos.sla.enforcement.IAgreementEnforcement;

public class EnforcementTask extends TimerTask implements Runnable {
	private static Logger logger = Logger
			.getLogger(ScheduledEnforcementWorker.class);

	private IAgreementEnforcement agreementEnforcement;
	private IEnforcementJob job;

	public EnforcementTask(IEnforcementJob job, IAgreementEnforcement agreementEnforcement) {

		this.job = job;
		this.agreementEnforcement = agreementEnforcement;
	}

	@Override
	public void run() {
		logger.debug(String.format("Scheduled task running on %1$tFT%1$tT", new Date()));

		if (job == null) {
			throw new NullPointerException("Job has not been set");
		}
		/*
		 * Get contract
		 */
		IAgreement contract = job.getAgreement();

		if (contract == null) {
			return;
		}
		
		agreementEnforcement.enforce(contract, job.getLastExecuted());
	}
	

}
