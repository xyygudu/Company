package com.zsk.role;

//股东
public class ShareHolder {
	private String name;
	private int salary;
	private float share;//股份
	public ShareHolder(String name, float share) {
		// TODO Auto-generated constructor stub
		this.salary=salary;
		this.name=name;
		this.share=share;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getShare() {
		return share;
	}
}
