package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import model.EmployeeBean;

/**
 * @author Deepak Prajapati
 * 
 * @Description This class is for crud operation of database.
 *
 */
public class EmployeeDao {
	JdbcTemplate jdbcTemplate;
	  
	public void setTemplate(JdbcTemplate template) {  
	    this.jdbcTemplate = template;  
	}
	/**
	 * @param employeeBean
	 * @return 
	 */
	public int save(EmployeeBean employeeBean){
		
	    String sql="INSERT INTO empMVC(id,name,designation) values('"+employeeBean.getId()
	    +"', '"+employeeBean.getName()+"', '"+employeeBean.getDesignation()+"')";
	    return jdbcTemplate.update(sql);
	}  
	
	public int update(EmployeeBean employeeBean){
	    String sql=" UPDATE empMVC SET name='"+employeeBean.getName()
	    +"', designation='"+employeeBean.getDesignation()+"' where id="+employeeBean.getId()+"";
	    
	    return jdbcTemplate.update(sql);  
	}  
	
	public int delete(int id){
	    String sql="delete from empMVC where id="+id+"";  
	    return jdbcTemplate.update(sql);  
	}
	
	public EmployeeBean getEmpById(int id){
	    String sql="select * from empMVC where id=?";
	    return jdbcTemplate.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<EmployeeBean>(EmployeeBean.class));  
	}
	
	public List<EmployeeBean> getAllEmployees(){
		JdbcTemplate template=jdbcTemplate;
		return template.query("select * from empMVC",new ResultSetExtractor<List<EmployeeBean>>(){  
			
			public List<EmployeeBean> extractData(ResultSet resultSet) throws SQLException,  
			DataAccessException {  

				List<EmployeeBean> list=new ArrayList<EmployeeBean>();
				while(resultSet.next()){  
					EmployeeBean employee=new EmployeeBean();  
				String id = resultSet.getString(1);
					employee.setId(id);  
					employee.setName(resultSet.getString(2));  
					employee.setDesignation(resultSet.getString(3));
					list.add(employee);  
				}  
				return list;  
			}  
		});
	}  
}
