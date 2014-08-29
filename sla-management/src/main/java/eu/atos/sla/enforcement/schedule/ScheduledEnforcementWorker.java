package eu.atos.sla.enforcement.schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import eu.atos.sla.dao.IEnforcementJobDAO;
import eu.atos.sla.datamodel.IEnforcementJob;
import eu.atos.sla.enforcement.IAgreementEnforcement;
import eu.atos.sla.enforcement.IEnforcementService;

/**
 * This class is the root of the execution of the enforcement tasks.
 * 
 * When in a spring context, is executed periodically according to the value of <code>spawnlookup.cron</code>.
 * 
 * Once running, it finds the enforcement jobs to run since some date (<code>poll.interval</code>
 * dependent) and schedules a task to check each agreement.
 * 
 * 
 * Following properties must have been set before running in production mode
 * 
 * eu.atos.sla.enforcement.spawnlookup.cron=*\/5 * * * * *
 * eu.atos.sla.enforcement.poll.interval.mseconds=30000
 */
@Component
@Transactional
public class ScheduledEnforcementWorker implements InitializingBean, IScheduledEnforcementWorker {
	private static final String POLL_INTERVAL = "eu.atos.sla.enforcement.poll.interval.mseconds";

	private static final String CRON = "eu.atos.sla.enforcement.spawnlookup.cron";

	private static Logger logger = Logger.getLogger(ScheduledEnforcementWorker.class);

	/**
	 * If true, enforcement tasks are run as new threads. Else, are run sequentially in this thread.
	 */
	private boolean spawn = false;
	
	private ThreadPoolTaskScheduler scheduler;

	@Value("ENF{" + CRON + "}")
	private String cron;
	
	@Value("ENF{" + POLL_INTERVAL + "}")
	private String pollIntervalString;
	private long pollInterval;
	
	@Autowired
	private IEnforcementService enforcementService;
	
	
	/*
	 * TODO rsosa: I prefer this field as non autowired, but I don't know being a scheduled instance. 
	 */
	@Autowired
	private IAgreementEnforcement agreementEnforcement;
	
	@Autowired
	private IEnforcementJobDAO enforcementJobDAO;

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	/* (non-Javadoc)
	 * @see eu.atos.sla.enforcement.IScheduledEnforcementWorker#spawnMonitors()
	 */
	@Override
	@Scheduled(cron = "ENF{" + CRON + "}")
	public void spawnMonitors() {
		Date since = computeOffset();
		
		List<IEnforcementJob> nonExecuted = enforcementJobDAO.getNotExecuted(since);

		logger.debug(String.format("spawning {%d} future tasks", nonExecuted.size()));

		for (IEnforcementJob job : nonExecuted) {
			try{
				EnforcementTask task = new EnforcementTask(job, agreementEnforcement);
				Date delay = computeDelay(job);
				
				/*
				 * TODO rsosa FIX: if spawned, the new thread does not have jpa session. 
				 */
				if (spawn) {
					logger.debug(String.format("job.lastExecuted=[%s]; scheduling task on %s", 
							iso8601(job.getLastExecuted()), iso8601(delay)));
					scheduler.schedule(task, delay);
				}
				else {
					task.run();
				}
			}catch(Throwable t){
				logger.fatal("Error while executing enforcement job",t);				
			}
		}
	}
	
	private Date computeDelay(IEnforcementJob enforcementJob){
		Calendar calendar = Calendar.getInstance();

		if (enforcementJob.getLastExecuted() != null) {
			calendar.setTime(enforcementJob.getLastExecuted());
			calendar.add(Calendar.SECOND, (int)pollInterval/1000);
		}
		
		return calendar.getTime();
	}
	
	private Date computeOffset(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MILLISECOND, -((int)(pollInterval)));
		return calendar.getTime();
	}

	@Override
	public void afterPropertiesSet() throws Exception {

		try {
			pollInterval = Long.parseLong(pollIntervalString);
		}catch(NumberFormatException npe){
			String str = String.format("Can not parse ENF{%s} value{%s}. Is it a number?", 
					POLL_INTERVAL, pollIntervalString); 
			throw new IllegalArgumentException(str);
		}
		
		scheduler = new ThreadPoolTaskScheduler();
		scheduler.initialize();
		
		logger.debug(String.format("EnforcementWorker registered, cron{%s}, interval{%s}", 
				cron, pollIntervalString));
	}
	
	private String iso8601(Date date) {
		return String.format("%tFT%<tTZ", date);
	}
}
