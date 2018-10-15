package com.zsk.role;

//员工
public class Employee {
	private String name;
	private int salary;
	private int birthday;

	public Employee(String name,int salary, int birthday) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.salary=salary;
		this.birthday=birthday;
	}


	public void setName(String name) {
		this.name = name;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public void setBirthday(int birthday) {
		this.birthday = birthday;
	}
	public int getBirthday() {
		return birthday;
	}
	public String getName() {
		return name;
	}
	public int getSalary() {
		return salary;
	}

}
