package Programs;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Dao.EmployeeDao;

/**
 * @author DEEPAK PRAJAPATI
 * 
 * @DESCRIPTION THIS CLASS IS USED TO INSERT THE EMPLOYEE INTO DATBASE.
 *
 */
public class InsertEmployee {
	@SuppressWarnings("resource")
	public static void main(String[] args) {  
		Scanner scanner=new Scanner(System.in);
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");  
		EmployeeDao dao=(EmployeeDao)ctx.getBean("employeedao");
		boolean status=false;
		try{
			System.out.println("Enter employee id : ");
			int id=scanner.nextInt();
			System.out.println("Enter employee age : ");
			int age=scanner.nextInt();
			System.out.println("Enter employee name : ");
			scanner.nextLine();
			String name=scanner.nextLine();
			System.out.println("Enter employee position : ");
			String position=scanner.nextLine();
			status=dao.insertEmployee(id, name, age, position);
		}catch (Exception e) {
			System.out.println("Input is wrong, Data is not Inserted.");
		}
		if(status){
			System.out.println("Data Inserted.");
		}
	}  
}
