package fx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.mybatis.domain.ColumnDataType;
import org.mybatis.domain.DefaultTableData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class CreateController implements Initializable {
	
	@FXML private TableView<DefaultTableData> tableView;
	
	@FXML private TableColumn<DefaultTableData, String> columnName;
	@FXML private TableColumn<DefaultTableData, String> dataType;
	@FXML private TableColumn<DefaultTableData, String> primaryKey;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tableView.setEditable(true);
		
		// ==== columnName (Text Field) ====
		columnName.setCellValueFactory(new PropertyValueFactory<>("columnName"));
		columnName.setCellFactory(TextFieldTableCell.<DefaultTableData>forTableColumn());
		
		columnName.setOnEditCommit((CellEditEvent<DefaultTableData, String> event) -> {
			
			TablePosition<DefaultTableData, String> pos = event.getTablePosition();
			String newColumnName = event.getNewValue();

			int row = pos.getRow();
			DefaultTableData data = event.getTableView().getItems().get(row);

			data.setColumnName(newColumnName);
			
		});
		
		ObservableList<ColumnDataType> typeList = FXCollections.observableArrayList(ColumnDataType.values());

		ObservableList<DefaultTableData> list = getPersonList();
		tableView.setItems(list);
		
		//tableView.getColumns().addAll(columnName, dataType, primaryKey);
		
		System.out.println("@@");
		
	}

		
	private ObservableList<DefaultTableData> getPersonList() {

		DefaultTableData person1 = new DefaultTableData("", ColumnDataType.CHAR, true);
		DefaultTableData person2 = new DefaultTableData("", ColumnDataType.DATE, true);
		DefaultTableData person3 = new DefaultTableData("", ColumnDataType.VARCHAR, false);
		
		ObservableList<DefaultTableData> list = FXCollections.observableArrayList(person1, person2, person3);
		return list;
	}
}
