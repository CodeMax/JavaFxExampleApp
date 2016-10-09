package de.desktop.application.service;

import de.desktop.application.commons.JsonDeserializor;
import de.desktop.application.to.ServiceData;

/**
 * 
 * @author Max.Auch
 *
 */
public class DataServiceImpl implements DataService {

	/**
	 * Liste aller Projektaufträge.
	 */
	public ServiceData getProjektauftragsdaten() {
		return new JsonDeserializor<ServiceData>(ServiceData.class)
				.deserializeJsonToPojo("datensaetze/data.json");
	}
}
