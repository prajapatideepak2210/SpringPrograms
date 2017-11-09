package controller;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import model.StudentBean;

@SuppressWarnings("deprecation")
public class MainClass {
	public static void main(String[] args) {  

		Resource resource=new ClassPathResource("Bean.xml");  
		BeanFactory factory=new XmlBeanFactory(resource);  

		StudentBean student=(StudentBean)factory.getBean("studentBean"); 
		student.display();

	}  
}
