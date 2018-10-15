package com.zsk.role;

//经理
public class Maneger {
	private String name;
	private int salary;
	private int birthday;
	private int award;//奖金
	
	public Maneger(String name, int salary, int birthday, int award) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.salary=salary;
		this.birthday=birthday;
		this.award=award;
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
	public int getAward() {
		return award;
	}

}
