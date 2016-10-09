package de.desktop.application.service;

import de.desktop.application.to.ServiceData;

/**
 * 
 * @author Max.Auch
 *
 */
public interface DataService {

	/**
	 * Liefert alle hinterlegten {@link Projektauftragsdaten} zurück.
	 *
	 * @return alle {@link ServiceData}
	 */
	public ServiceData getProjektauftragsdaten();

}
