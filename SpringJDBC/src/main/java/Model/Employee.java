package Model;

/**
 * @author Deepak Prajapati
 * 
 * @Description This is a model class in which you will found some properties of employee.
 *
 */
public class Employee {
	private int id, age;
	private String name, position;
	
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", age=" + age + ", name=" + name + ", position=" + position + "]";
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
}
