package controler;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.cglib.proxy.Factory;
import org.springframework.core.io.ClassPathResource;

import model.StudentBean;

@SuppressWarnings({ "deprecation", "unused" })
public class MainClass {
	public static void main(String[] args) {
		XmlBeanFactory factory = new XmlBeanFactory (new ClassPathResource("Bean.xml"));
		StudentBean studentBean=(StudentBean) factory.getBean("student");
		int id=studentBean.getId();
		int age=studentBean.getAge();
		String name=studentBean.getName();
		System.out.println("Student Id : "+id+", name : "+name+", age : "+age+".");
	}
}