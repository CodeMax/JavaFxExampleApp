package de.desktop.application.fx.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author Max.Auch
 *
 */
public class ModelObject {

	private StringProperty property;
	private StringProperty wert;
	
	
	public ModelObject(String property, String wert) {
		this.property = new SimpleStringProperty(property);
		this.wert = new SimpleStringProperty(wert);
	}
	
	
	public StringProperty getProperty() {
		return property;
	}
	public void setProperty(StringProperty property) {
		this.property = property;
	}
	public StringProperty getWert() {
		return wert;
	}
	public void setWert(StringProperty wert) {
		this.wert = wert;
	}


	@Override
	public String toString() {
		return "ModelObject [property=" + property + ", wert=" + wert + "]";
	}
	
}
