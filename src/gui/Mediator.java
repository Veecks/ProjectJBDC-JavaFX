package gui;

import model.entities.Employee;

public class Mediator {
	static Employee beingEdited;
	
	static public Employee getEmployeeBeingEdited() {
		return beingEdited;
	}

	static public void setEmployeeBeingEdited(Employee emp) {
		beingEdited = emp;
	}
}
