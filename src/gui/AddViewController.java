package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.imp.EmployeeDaoJDBC;
import model.entities.Employee;

public class AddViewController {
	@FXML
	private TextField tfName;
	@FXML
	private TextField tfEmail;
	@FXML
	private TextField tfPhone;
	@FXML
	private TextField tfSalary;
	@FXML
	private Button btAdd;
	@FXML
	private Button btCancel;
	
	@FXML
	public void initialize() {
		gui.util.Constraints.setTextFieldDouble(tfSalary);
	}
	
	@SuppressWarnings("exports")
	public void onActionBtAdd(ActionEvent event) {
		EmployeeDaoJDBC dao = new EmployeeDaoJDBC("employees");
		Employee insertion = new Employee(null ,tfName.getText(), tfEmail.getText(),
						tfPhone.getText(), Double.parseDouble(tfSalary.getText()));
		dao.insert(insertion);
		((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
	}
	
	@SuppressWarnings("exports")
	public void onActionBtCancel(ActionEvent event) {
		((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
	}
}
