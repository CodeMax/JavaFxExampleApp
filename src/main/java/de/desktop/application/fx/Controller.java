package de.desktop.application.fx;

import de.desktop.application.fx.model.ModelObject;
import de.desktop.application.fx.model.mapper.ModelMapper;
import de.desktop.application.service.DataService;
import de.desktop.application.service.DataServiceImpl;
import de.desktop.application.to.ServiceData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * 
 * @author Max.Auch
 *
 */
public class Controller {

	private ObservableList<ModelObject> projektauftragsdaten;
	
	private DataService dataService = new DataServiceImpl();
	
	@FXML
	private TableView<ModelObject> padTable;

	@FXML
	private TableColumn<ModelObject, String> padTableDatenfelder, padTableWerte;

	@FXML
	public void initialize() {
		padTableDatenfelder.setCellValueFactory(cellData -> cellData.getValue().getProperty());
		padTableWerte.setCellValueFactory(cellData -> cellData.getValue().getWert());
		padTableWerte.setCellFactory(TextFieldTableCell.forTableColumn());

		projektauftragsdaten = FXCollections.observableArrayList();
		ServiceData to = dataService.getProjektauftragsdaten();
		projektauftragsdaten.addAll(new ModelMapper<ModelObject>(ModelObject.class).toToModel(to));
		padTable.setItems(projektauftragsdaten);
		padTable.setEditable(true);
	}

	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
	}
}
