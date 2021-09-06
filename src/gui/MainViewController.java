package gui;

import java.util.List;

import gui.util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dao.imp.EmployeeDaoJDBC;
import model.entities.Employee;

public class MainViewController {

	Employee beingEdited;
	Employee beingRemoved;

	@FXML
	private Button btAdd;
	@FXML
	private Button btEdit;
	@FXML
	private Button btRemove;
	@FXML
	private Button btRefresh;
	@FXML
	private TableView<Employee> tvDataBase;

	@FXML
	public void initialize() {
		refreshTableView();
	}

	@SuppressWarnings("exports")
	public void OnActionBtAdd(ActionEvent event) {
		System.out.println("Adding...");
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/gui/AddView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.showAndWait();
			refreshTableView();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@SuppressWarnings("exports")
	public void OnActionBtEdit(ActionEvent event) {
		System.out.println("Editing...");
		Mediator.setEmployeeBeingEdited(tvDataBase.getSelectionModel().getSelectedItem());
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/gui/EditView.fxml"));
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.showAndWait();
			refreshTableView();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@SuppressWarnings("exports")
	public void OnActionBtRemove(ActionEvent event) {
		System.out.println("Removing...");
		if(!Alerts.showAlert("Warning", null, "Are you you want to delete this entity?\nThis action can\'t be undone!",
				Alert.AlertType.CONFIRMATION))
			return;
		
		EmployeeDaoJDBC dao = new EmployeeDaoJDBC("employees");
		dao.delete(tvDataBase.getSelectionModel().getSelectedItem());
		refreshTableView();
	}

	@SuppressWarnings("exports")
	public void OnActionBtRefresh(ActionEvent event) {
		refreshTableView();
	}

	public void refreshTableView() {
		System.out.println("Refreshing...");
		EmployeeDaoJDBC dao = new EmployeeDaoJDBC("employees");
		ObservableList<Employee> data = FXCollections.observableArrayList(dao.findAll());

		tvDataBase.getColumns().clear();
		List<String> fields = dao.getFields();
		for (String x : fields) {
			TableColumn<Employee, String> col = new TableColumn<Employee, String>(x);
			col.setMinWidth(x.toUpperCase().contains("ID") ? 40 : 150);
			col.setCellValueFactory(new PropertyValueFactory<>(x.toLowerCase()));
			tvDataBase.getColumns().add(col);
		}
		tvDataBase.setItems(data);
	}
}
