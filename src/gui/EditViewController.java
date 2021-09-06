package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dao.imp.EmployeeDaoJDBC;
import model.entities.Employee;

public class EditViewController {

	private Employee emp;

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
		emp = Mediator.getEmployeeBeingEdited();
		gui.util.Constraints.setTextFieldDouble(tfSalary);
		tfName.setText(emp.getName());
		tfEmail.setText(emp.getEmail());
		tfPhone.setText(emp.getPhone());
		tfSalary.setText(emp.getSalary().toString());
	}

	@SuppressWarnings("exports")
	public void onActionBtEdit(ActionEvent event) {
		EmployeeDaoJDBC dao = new EmployeeDaoJDBC("employees");
		dao.update(new Employee(emp.getId(), tfName.getText(), tfEmail.getText(), tfPhone.getText(),
				Double.parseDouble(tfSalary.getText())));
		Mediator.setEmployeeBeingEdited(null);
		((Stage)((Node)event.getSource()).getScene().getWindow()).close();
	}

	@SuppressWarnings("exports")
	public void onActionBtCancel(ActionEvent event) {
		Mediator.setEmployeeBeingEdited(null);
		((Stage)((Node)event.getSource()).getScene().getWindow()).close();
	}
}
