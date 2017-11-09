package controller;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import model.StudentBean;

@SuppressWarnings("deprecation")
public class MainClass {
	public static void main(String[] args) {  

		Resource r=new ClassPathResource("Bean.xml");  
		BeanFactory factory=new XmlBeanFactory(r);  

		StudentBean studentBean=(StudentBean)factory.getBean("student");  
		studentBean.show(); 

	}  
}
