package Programs;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Dao.EmployeeDao;

/**
 * @author DEEPAK PRAJAPATI
 * 
 * @DESCRIPTION THIS CLASS IS USED TO DELETE THE EMPLOYEE FROM DATBASE.
 *
 */
public class DeleteEmployee {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
		EmployeeDao employeeDao=(EmployeeDao) applicationContext.getBean("employeedao");
		boolean check=false;
		try {
			System.out.println("Enter employee id to delete : ");
			int id=scanner.nextInt();
			check=employeeDao.deleteEmployee(id);
		} catch (Exception e) {
			System.out.println("ID is wrong, Data is not Deleted.");
		}
		if(check)
		{
			System.out.println("Data deleted.");
		}
	}
}
