package model;

public class StudentBean {
	private int id;
	private String name;
	private String address;
	  
	 
	public void setId(int id) {  
	    this.id = id;  
	}  
	 
	public void setName(String name) {  
	    this.name = name;  
	}  
	    
	public void setAddress(String address) {  
	    this.address = address;  
	}
	
	public void display(){  
	    System.out.println("Student Id : "+id+", Name : "+name+", Addres : "+address);  
	}  
}
