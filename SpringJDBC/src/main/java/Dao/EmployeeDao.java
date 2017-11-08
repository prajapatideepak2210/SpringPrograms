package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import Model.Employee;

/**
 * @author DEEPAK PRAJAPATI
 * 
 * @DESCRIPTION IN THIS CLASS YOU WILL FOUND CRUD OPERATIONS OF SPRING JDBC.
 *
 */
public class EmployeeDao {
	private JdbcTemplate jdbcTemplate;  

	/**
	 * @param jdbcTemplate
	 * 
	 * @DESCRIPTION This method is for only initializing purpose to jdbcTemplate.
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
		this.jdbcTemplate = jdbcTemplate;  
	}  

	/**
	 * @param id
	 * @param name
	 * @param age
	 * @param position
	 * @return it returns true if employee is inserted otherwise returns false.
	 * 
	 * @Description This method is used to insert the new employee into the database. 
	 */
	public boolean insertEmployee(int id, String name, int age, String position){  
		String query="insert into SpringJDBC_Employee values('"+id+"','"+name
		+"','"+age+"','"+position+"')";
		int check=jdbcTemplate.update(query);
		if(check==1)
			return true;
		else
			return false; 
	}
	
	/**
	 * @param id
	 * @param name
	 * @param age
	 * @param position
	 * @return it returns true if employee's detail update otherwise returns false.
	 * 
	 * @Description This method is used to update the employee details.
	 */
	public boolean updateEmployee(int id, String name, int age, String position){  
		String query="UPDATE SpringJDBC_Employee SET name='"+name+"', age='"+age+"', position='"
				+position+"' where id='"+id+"'";
		int check=jdbcTemplate.update(query);
		if(check==1)
			return true;
		else
			return false;
	} 
	
	/**
	 * @param id
	 * @return it returns true if employee deleted, otherwise returns false.
	 * 
	 * @Description This method is used to delete the employee from the database.
	 */
	public boolean deleteEmployee(int id){  
		String query="delete from SpringJDBC_Employee where id='"+id+"' ";
		int check=jdbcTemplate.update(query);
		if(check==1)
			return true;
		else
			return false;  
	}
	
	
	/**
	 * @return it returns List of employee which available in database.
	 * 
	 * @Description Here you will get the List of Employee that are available in database.
	 */
	public List<Employee> getAllEmployees(){  
		JdbcTemplate template=jdbcTemplate;
		return template.query("select * from SpringJDBC_Employee",new ResultSetExtractor<List<Employee>>(){  
			@Override  
			public List<Employee> extractData(ResultSet resultSet) throws SQLException,  
			DataAccessException {  

				List<Employee> list=new ArrayList<Employee>();  
				while(resultSet.next()){  
					Employee employee=new Employee();  
					employee.setId(resultSet.getInt(1));  
					employee.setName(resultSet.getString(2));  
					employee.setAge(resultSet.getInt(3));
					employee.setPosition(resultSet.getString(4));
					list.add(employee);  
				}  
				return list;  
			}  
		});
	}  

}
