package eu.modaclouds.sla.service.rest;

import it.polimi.modaclouds.monitoring.metrics_observer.model.Sparql_json_results;
import it.polimi.modaclouds.monitoring.metrics_observer.model.Variable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import eu.atos.sla.datamodel.IAgreement;
import eu.atos.sla.datamodel.IGuaranteeTerm;
import eu.atos.sla.evaluation.constraint.IConstraintEvaluator;
import eu.atos.sla.monitoring.IMetricTranslator;
import eu.atos.sla.monitoring.IMonitoringMetric;

/**
 * Translates a serialized json of metrics (as sent by MC'Monitoring Platform) into 
 * a set of metrics suitable for sla enforcement.
 * 
 * This version is coupled to modaclouds-metrics-observer v1.0.1
 * 
 * @author rsosa
 */
public class ModacloudsTranslator implements IMetricTranslator<String>{
	private static final Logger logger = Logger.getLogger(ModacloudsTranslator.class);
	
	/**
	 * Needed to get the guarantee term that evaluates a particular metric key.
	 */
	private final IConstraintEvaluator constraintEvaluator;
	
	public ModacloudsTranslator(IConstraintEvaluator constraintEvaluator) {
		this.constraintEvaluator = constraintEvaluator;
	}

	@Override
	public Map<IGuaranteeTerm, List<IMonitoringMetric>> translate(IAgreement agreement, String data) {
		MultivaluedMapWrapper<IGuaranteeTerm, IMonitoringMetric> resultWrapper = 
				new MultivaluedMapWrapper<IGuaranteeTerm, IMonitoringMetric>();
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			final Sparql_json_results json = 
					mapper.readValue(data, Sparql_json_results.class);
			
			final MultivaluedMapWrapper<String, IGuaranteeTerm> metric2terms = initTermsMap(agreement);
			
			for (Map<String, Variable> item : json.getResults().getBindings()) {
				BindingHelper var = new BindingHelper(item);
				List<IGuaranteeTerm> terms = metric2terms.get(var.key);

				if (terms == null) {
					logger.warn("List of terms handling " + var.key + " is empty");
				}
				
				resultWrapper.addToKeys(terms, newMetric(var));
			}
			return resultWrapper.getMap();
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private IMonitoringMetric newMetric(final BindingHelper binding) {
		return new IMonitoringMetric() {
			
			@Override
			public String getMetricValue() {
				return binding.value;
			}
			
			@Override
			public String getMetricKey() {
				return binding.key;
			}
			
			@Override
			public Date getDate() {
				return binding.date;
			}
			
			@Override
			public String toString() {
				return String.format("%s[key=%s, value=%s, date=%s]",
						this.getClass().getName(),
						this.getMetricKey(),
						this.getMetricValue(),
						this.getDate().toString());
			}
		};
	}
	
	/**
	 * Inits a MultivaluedMap with metrickey as key and the terms that evaluate the metrickey as values.
	 * @param agreement
	 * @return
	 */
	private MultivaluedMapWrapper<String, IGuaranteeTerm> initTermsMap(IAgreement agreement) {
		MultivaluedMapWrapper<String, IGuaranteeTerm> result = new MultivaluedMapWrapper<String, IGuaranteeTerm>();
		
		for (IGuaranteeTerm term : agreement.getGuaranteeTerms()) {
			String variable = constraintEvaluator.getConstraintVariable(term.getServiceLevel());
			result.add(variable, term);
		}
		return result;
	}	
	
	/**
	 * Wrapper over a Map that implements a MultivaluedMap, i.e, a single key may have more than one value.
	 * 
	 * Internally, it is a Map<K, List<V>>. This wrapper simply facilitates the adding operations.
	 * 
	 * After adding all values, you can work with the result using {@link #getMap()}. Remember that 
	 * any key that has no value may return <code>null</code> instead of an empty list.
	 *  
	 * @param <K> type of key
	 * @param <V> type value
	 * 
	 * @author rsosa
	 *
	 */
	public static class MultivaluedMapWrapper<K, V> {

		private final Map<K, List<V>> map;

		public MultivaluedMapWrapper() {
			this.map = new HashMap<K, List<V>>();
		}
		
		public MultivaluedMapWrapper(Map<K, List<V>> map) {
			if (map == null) {
				throw new IllegalArgumentException("map cannot be null");
			}
			this.map = map;
		}
		
		/**
		 * Return the list of values associated to a key, or an empty list. 
		 */
		public List<V> get(K key) {
			List<V> list = map.get(key);
			
			return list == null? Collections.<V>emptyList() : list;
		}

		/**
		 * Add a value to a key
		 */
		public void add(K key, V value) {
		
			List<V> list;
			if (map.containsKey(key)) {
				list = map.get(key);
			}
			else {
				list = new ArrayList<V>();
				map.put(key, list);
			}
			list.add(value);
		}
	
		/**
		 * Add a value to the given keys
		 */
		public void addToKeys(List<K> keys, V value) {
			
			for (K key : keys) {
				this.add(key, value);
			}
		}
		
		/**
		 * Returns an unmodifiable Map over the internal map.
		 */
		public Map<K, List<V>> getMap() {
			return Collections.unmodifiableMap(map);
		}
	}
	
	private static class BindingHelper {
		private final String key;
		private final String value;
		private final Date date;
		
		public BindingHelper(Map<String, Variable> binding) {
			key = binding.get("metric").getValue();
			value = binding.get("value").getValue();
			date = new Date(Long.parseLong(binding.get("timestamp").getValue()));
		}
	}
//	public static class ModaCloudsMonitoringMetric {
//		private ModaCloudsMonitoringMetric.Head head;
//		private ModaCloudsMonitoringMetric.Result results;
//		
//		public ModaCloudsMonitoringMetric.Head getHead() {
//			return head;
//		}
//		public void setHead(ModaCloudsMonitoringMetric.Head head) {
//			this.head = head;
//		}
//		public ModaCloudsMonitoringMetric.Result getResults() {
//			return results;
//		}
//		public void setResults(ModaCloudsMonitoringMetric.Result results) {
//			this.results = results;
//		}
//
//		public static class Head {
//			
//			private ArrayList<String> vars;
//
//			public ArrayList<String> getVars() {
//				return vars;
//			}
//
//			public void setVars(ArrayList<String> vars) {
//				this.vars = vars;
//			}
//		}
//		
//		public static class Result {
//			
//			private ArrayList<Map<String, ModaCloudsMonitoringMetric.Variable>> bindings;
//
//			public ArrayList<Map<String, ModaCloudsMonitoringMetric.Variable>> getBindings() {
//				return bindings;
//			}
//
//			public void setBindings(ArrayList<Map<String, ModaCloudsMonitoringMetric.Variable>> bindings) {
//				this.bindings = bindings;
//			}
//			
//		}
//		
//		public static class Variable {
//			
//			private String datatype;
//			private String type;
//			private String value;
//			
//			public String getDatatype() {
//				return datatype;
//			}
//			public void setDatatype(String datatype) {
//				this.datatype = datatype;
//			}
//			public String getType() {
//				return type;
//			}
//			public void setType(String type) {
//				this.type = type;
//			}
//			public String getValue() {
//				return value;
//			}
//			public void setValue(String value) {
//				this.value = value;
//			}
//		}
//	}
	
}