package fx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.mybatis.domain.CreateTableInfo;
import org.mybatis.domain.DataTypeList;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class CreateController implements Initializable {

	@FXML private TableView<CreateTableInfo> tableView;

	@FXML private TableColumn<CreateTableInfo, String> columnName;
	@FXML private TableColumn<CreateTableInfo, DataTypeList> dataType;
	@FXML private TableColumn<CreateTableInfo, Boolean> primaryKey;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		tableView.setEditable(true);

		// ======================= columnName (Text Field) =======================
		columnName.setCellValueFactory(new PropertyValueFactory<>("columnName"));
		columnName.setCellFactory(TextFieldTableCell.<CreateTableInfo>forTableColumn());

		columnName.setOnEditCommit((CellEditEvent<CreateTableInfo, String> event) -> {

			TablePosition<CreateTableInfo, String> pos = event.getTablePosition();
			String newColumnName = event.getNewValue();

			int row = pos.getRow();
			CreateTableInfo data = event.getTableView().getItems().get(row);

			data.setColumnName(newColumnName);

		});

		// ======================= dataType(ComboBox) =======================

		ObservableList<DataTypeList> typeList = FXCollections.observableArrayList(DataTypeList.values());

		dataType.setCellValueFactory(
				new Callback<CellDataFeatures<CreateTableInfo, DataTypeList>, ObservableValue<DataTypeList>>() {

					@Override
					public ObservableValue<DataTypeList> call(CellDataFeatures<CreateTableInfo, DataTypeList> param) {
						CreateTableInfo type = param.getValue();
						// F,M
						String typeCode = type.getDataType();
						DataTypeList columnType = DataTypeList.getByCode(typeCode);
						return new SimpleObjectProperty<DataTypeList>(columnType);
					}
				});

		dataType.setCellFactory(ComboBoxTableCell.forTableColumn(typeList));

		dataType.setOnEditCommit((CellEditEvent<CreateTableInfo, DataTypeList> event) -> {

			TablePosition<CreateTableInfo, DataTypeList> pos = event.getTablePosition();

			DataTypeList newType = event.getNewValue();

			int row = pos.getRow();
			CreateTableInfo tableData = event.getTableView().getItems().get(row);

			// System.out.println(newType.getCode());

			tableData.setDataType(newType.getCode());

		});

		// ======================= primaryKey(ChackBox) =======================

		primaryKey.setCellValueFactory(
				new Callback<CellDataFeatures<CreateTableInfo, Boolean>, ObservableValue<Boolean>>() {

					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<CreateTableInfo, Boolean> param) {

						CreateTableInfo info = param.getValue();

						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(info.isPrimaryKey());

						booleanProp.addListener(new ChangeListener<Boolean>() {
							@Override
							public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
									Boolean newValue) {
								info.setPrimaryKey(newValue);
							}
						});
						return booleanProp;
					}
				});

		primaryKey.setCellFactory(
				new Callback<TableColumn<CreateTableInfo, Boolean>, TableCell<CreateTableInfo, Boolean>>() {

					@Override
					public TableCell<CreateTableInfo, Boolean> call(TableColumn<CreateTableInfo, Boolean> param) {

						CheckBoxTableCell<CreateTableInfo, Boolean> cell = new CheckBoxTableCell<CreateTableInfo, Boolean>();
						cell.setAlignment(Pos.CENTER);

						return cell;
					}

				});

		ObservableList<CreateTableInfo> list = getPersonList();
		tableView.setItems(list);

	}

	private ObservableList<CreateTableInfo> getPersonList() {

		CreateTableInfo person1 = new CreateTableInfo("", DataTypeList.CHAR.getCode(), false);
		CreateTableInfo person2 = new CreateTableInfo("", DataTypeList.CHAR.getCode(), false);
		CreateTableInfo person3 = new CreateTableInfo("", DataTypeList.CHAR.getCode(), false);

		ObservableList<CreateTableInfo> list = FXCollections.observableArrayList(person1, person2, person3);
		return list;
	}
}
