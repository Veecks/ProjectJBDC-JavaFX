package model.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import model.dao.EmployeeDao;
import model.entities.Employee;

public class EmployeeDaoJDBC implements EmployeeDao {

	Connection conn;
	String tableName;

	public EmployeeDaoJDBC(String tableName) {
		conn = DB.getConnection();
		this.tableName = tableName;
	}

	@Override
	public void insert(Employee emp) {
		try {
			PreparedStatement st = conn.prepareStatement(
							"INSERT INTO employees "
							+ "(Name, Email, Phone, Salary) "
							+ "VALUES (?,?,?,?);");
			st.setString(1, emp.getName());
			st.setString(2, emp.getEmail());
			st.setString(3, emp.getPhone());
			st.setDouble(4, emp.getSalaryDouble());
			System.out.println(st.executeUpdate() + " row added.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Employee emp) {
		try {
			System.out.println("Id: " + emp.getId());
			PreparedStatement st = conn.prepareStatement(
							"UPDATE employees " +
							"SET Name = ?, " + 
							"Email = ?, " + 
							"Phone = ?, " + 
							"Salary =  ? " + 
							"WHERE Id = ?;");
			st.setString(1, emp.getName());
			st.setString(2, emp.getEmail());
			st.setString(3, emp.getPhone());
			st.setDouble(4, emp.getSalaryDouble());
			st.setInt(5, emp.getId());
			System.out.println(st.executeUpdate() + " row edited.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Employee emp) {
		try {
			System.out.println("Id: " + emp.getId());
			PreparedStatement st = conn.prepareStatement(
							"DELETE FROM employees "
							+ "WHERE Id=?;");
			st.setInt(1, emp.getId());
			System.out.println(st.executeUpdate() + " row deleted.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Employee findByid(int id) {
		return null;
	}

	@Override
	public List<Employee> findAll() {
		List<Employee> list = new ArrayList<>();
		PreparedStatement st;
		ResultSet rs;
		try {
			st = conn.prepareStatement("SELECT * FROM projeto_jdbc_javafx.employees;", Statement.RETURN_GENERATED_KEYS);
			rs = st.executeQuery();
			while (rs.next()) {
				list.add(new Employee(rs.getInt("id"), rs.getString("Name"), rs.getString("Email"),
						rs.getString("Phone"), rs.getDouble("Salary")));
			}
			list.forEach(System.out::println);
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return list;
	}

	@Override
	public List<String> getFields() {
		List<String> list = new ArrayList<>();
		PreparedStatement st;
		ResultSet rs;
		try {
			st = conn.prepareStatement("SELECT COLUMN_NAME " + "FROM INFORMATION_SCHEMA.COLUMNS "
					+ "WHERE TABLE_NAME = \'employees\' " + "ORDER BY ORDINAL_POSITION;");
			rs = st.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			list.forEach(System.out::println);
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return list;
	}
}