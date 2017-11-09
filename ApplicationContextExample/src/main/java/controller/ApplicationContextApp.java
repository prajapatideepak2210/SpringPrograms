package controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.EmployeeBean;

public class ApplicationContextApp {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Bean.xml");
		EmployeeBean employeeBean=(EmployeeBean) context.getBean("employeebean");
		int id=employeeBean.getId();
		String name=employeeBean.getName();
		String position=employeeBean.getPosition();
		System.out.println("Employee Id : "+id+", name : "+name+", position : "+position+".");
	}
	
}
