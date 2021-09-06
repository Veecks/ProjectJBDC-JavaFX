package model.entities;

public class Employee {
	private Integer id;
	private String name;
	private String email;
	private String phone;
	private String salary;
	
	public Employee(Integer id, String name, String email, String phone, Double salary) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.salary = String.format("%.2f", salary);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSalary() {
		return salary;
	}
	
	public Double getSalaryDouble() {
		return Double.parseDouble(salary);
	}


	public void setSalary(Double salary) {
		this.salary = String.format("%.2f", salary);
	}

	@Override
	public String toString() {
		return String.format("[%s, %s, %s, %s]", name, email, phone, salary);
	}
}
