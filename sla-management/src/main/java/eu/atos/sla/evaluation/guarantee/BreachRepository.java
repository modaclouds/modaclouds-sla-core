package eu.atos.sla.evaluation.guarantee;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import eu.atos.sla.dao.IBreachDAO;
import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IBreach;

/**
 * Uses a BreachDao to implement the IBreachRepository functionality. 
 * @author rsosa
 *
 */
public class BreachRepository implements IBreachRepository {

	@Autowired
	IBreachDAO breachDao;
	
	public BreachRepository() {
	}

	@Override
	public List<IBreach> getByTimeRange(IAgreement agreement, String kpiName, Date begin, Date end) {

		List<IBreach> result = breachDao.getByTimeRange(agreement, kpiName, begin, end);
		return result;
	}

	@Override
	public void saveBreaches(List<IBreach> breaches) {

		for (IBreach breach : breaches) {
		
			breachDao.save(breach);
		}
	}

}
