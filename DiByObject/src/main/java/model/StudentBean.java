package model;

public class StudentBean {
	private int id;  
	private String name;  
	private Address address;//Aggregation  
	  
	public StudentBean() 
	{
		System.out.println("nothing is here.");
	}  
	  
	public StudentBean(int id, String name, Address address) {  
	    this.id = id;  
	    this.name = name;  
	    this.address = address;  
	}  
	  
	public void show(){  
	    System.out.println("Student Id : "+id+", Name : "+name+", Address : "+address.toString());  
	}  
}
