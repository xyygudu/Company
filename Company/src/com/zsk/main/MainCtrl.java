package com.zsk.main;

import java.sql.ResultSet;
import java.util.Random;
import java.util.Scanner;

import com.zsk.role.Employee;
import com.zsk.role.Maneger;
import com.zsk.role.ShareHolder;
import com.zsk.util.ConnDB;

public class MainCtrl {
	
	public static void initE() throws Exception {
		//插入员工数据
		Scanner in=new Scanner(System.in);
		System.out.println("输入公司的员工信息(姓名，工资，生日月份)：");
		Employee employee=new Employee(in.next(), in.nextInt(), in.nextInt());
		ConnDB connDB=new ConnDB();
		String sql = null;
		try {
			sql = "insert into employee values('"
					+employee.getName()+"',"+employee.getSalary()+","+employee.getBirthday()+")";
			System.out.println("sql语句为："+sql);
			connDB.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		connDB.close();
	}
	
	public static void initM() throws Exception {
		//经理员工数据
		Scanner in=new Scanner(System.in);
		System.out.println("输入公司的经理信息(姓名，工资，生日月份，奖金)：");
		Maneger maneger=new Maneger(in.next(), in.nextInt(), in.nextInt(),in.nextInt());
		ConnDB connDB=new ConnDB();
		String sql = null;
		try {
			sql = "insert into maneger values('"
					+maneger.getName()+"',"+maneger.getSalary()+","+maneger.getBirthday()+","+maneger.getAward()+")";
			System.out.println("sql语句为："+sql);
			connDB.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		connDB.close();
	}
	//插入股东信息
	public static void initS() throws Exception {
		
		Scanner in=new Scanner(System.in);
		System.out.println("输入公司的股东信息(姓名，股份)：");
		ShareHolder shareHolder=new ShareHolder(in.next(),in.nextFloat());
		ConnDB connDB=new ConnDB();
		String sql = null;
		try {
			sql = "insert into shareholder values('"
					+shareHolder.getName()+"',"+shareHolder.getShare()+")";
			System.out.println("sql语句为："+sql);
			connDB.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		connDB.close();
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		System.out.print("输入公司的员工数量：");
		int employNum= in.nextInt();
		for(int i=0;i<employNum;i++) {
			MainCtrl.initE();
		}
		System.out.print("输入经理数：");
		int manegerNum=in.nextInt();
		for(int i=0;i<manegerNum;i++) {
			MainCtrl.initM();
		}
		System.out.print("输入股东数：");
		int shareHolderNum=in.nextInt();
		for(int i=0;i<shareHolderNum;i++) {
			MainCtrl.initS();
		}
		
		//发工资
		int ledger=1000000;//用资产为一百万
		System.out.print("输入发放工资的月份：");
		int month=in.nextInt();
		ConnDB connDB=new ConnDB();
		ResultSet resultSet;
		int birthdaygift=200,award=1000;
		
		String sql="select * from employee";
		resultSet=connDB.executeQuery(sql);
		while(resultSet.next()) {
			System.out.println("员工"+resultSet.getString("name")+"的工资为："+resultSet.getInt("salary"));
			ledger-=resultSet.getInt("salary");
			System.out.println("员工生日月份为："+resultSet.getInt("birthday"));
			if(month==resultSet.getInt("birthday")) {
				System.out.println("生日礼物为"+birthdaygift);
				ledger-=birthdaygift;
			}	
		}
		sql="select * from maneger";
		resultSet=connDB.executeQuery(sql);
		while(resultSet.next()) {
			System.out.println("经理"+resultSet.getString("name")+"的工资为："+
					resultSet.getInt("salary")+"奖金为："+resultSet.getInt("award"));
			ledger-=resultSet.getInt("salary");
			ledger-=resultSet.getInt("award");
			if(month==resultSet.getInt("birthday")) {
				System.out.println("生日礼物为"+birthdaygift);
				ledger-=birthdaygift;
			}	
		}
		sql="select * from shareholder";
		resultSet=connDB.executeQuery(sql);
		System.out.println("公司剩下总财产"+ledger);
		int totalfenhong=(int) (ledger*0.1);
		resultSet=connDB.executeQuery(sql);
		while(resultSet.next()) {
			System.out.println("股东"+resultSet.getString("name")+"的股份为："+
					resultSet.getFloat("share")+"分红为："+totalfenhong*resultSet.getFloat("share"));
		}
	}

}























