package eu.atos.sla.datamodel.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import eu.atos.sla.datamodel.ITemplate;

@Entity
@Table(name = "template")
@NamedQueries({
		@NamedQuery(name = Template.QUERY_FIND_ALL, query = "SELECT p FROM Template p"),
		@NamedQuery(name = Template.QUERY_FIND_BY_UUID, query = "SELECT p FROM Template p WHERE p.uuid = :uuid"),
		@NamedQuery(name = Template.QUERY_SEARCH, query = "SELECT t FROM Provider p "
				+ "INNER JOIN p.templates t "
				+ "WHERE (:providerId is null or p.uuid = :providerId) "
				+ "AND (:flagServiceIds is null or t.serviceId in (:serviceIds))"),
		@NamedQuery(name = Template.QUERY_FIND_BY_AGREEMENT, query = "SELECT a.template FROM Agreement a "
				+ "WHERE a.agreementId = :agreementId"),
		})
public class Template implements ITemplate, Serializable {

	public final static String QUERY_FIND_ALL = "Template.findAll";
	public final static String QUERY_FIND_BY_UUID = "Template.getByUuid";
	public final static String QUERY_SEARCH = "Template.search";
	public final static String QUERY_FIND_BY_AGREEMENT = "Template.getByAgreement";
	

	private static final long serialVersionUID = -6390910175637896300L;
	private Long id;
	private String uuid;
	private String text;
	private String serviceId;

	public Template() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	 @Override
	 public int hashCode() {
		 
		 return uuid.hashCode();
	 }
	 
	 @Override
	 public boolean equals(Object obj) {
		 if (this == obj) {
			 return true;
		 }
	     if (!(obj instanceof Template)) {
	    	 return false;
	     }
	     Template that = (Template) obj;
	     return uuid.equals(that.getUuid());
	}	
	@Column(name = "uuid", unique = true, nullable = false)
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Column(name = "text", columnDefinition = "longtext", nullable = false)
	@Lob
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "service_id", nullable = true)
	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

}
