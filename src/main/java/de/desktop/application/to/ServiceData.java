package de.desktop.application.to;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * 
 * @author Max.Auch
 *
 */
public class ServiceData implements Serializable {

	
	/**
	 * Data.
	 */
	public String example;
	public String property;

	
	/**
	 * Mapping-Konstruktor.
	 * 
	 * @param mappingDaten
	 */
	public ServiceData(LinkedHashMap<String, Object> mappingDaten) {
		this.example = (String) mappingDaten.get("example");
		this.property = (String) mappingDaten.get("property");
	}

	/**
	 * Copy-Konstruktor.
	 * 
	 * @param serviceData
	 */
	public ServiceData(ServiceData to) {
		this.example = to.getExample();
		this.property = to.getProperty();
	}

	/**
	 * Default-Konstruktor.
	 */
	public ServiceData() {
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	@Override
	public String toString() {
		return "ProjektauftragTo [example=" + example + ", property=" + property + "]";
	}

}
