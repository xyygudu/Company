# Company程序简介 #
## 运行结果 ##
![](https://i.imgur.com/jXMim3p.png)
![](https://i.imgur.com/vwGA9NZ.png)

----------
## role包 ##
> 在com.zsk.role包中开辟了三个类——员工类（Employee）、经理类（Maneger）和股东类(Shareholder)，Employee类包含属性有员工姓名、工资、和出生月份，出生月份主要用于给员工或者经理发放生日礼物用，在Maneger类中，属性有，姓名、工资、出生月份和年终奖金，Shareholder类有属性：姓名和股份。以股东类为例代码如下（其他类与之相似）：
> 

	
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

对于这三个类方法都非常相似，都是获取或者设置属性值
## 连接数据库 ##
> 本程序使用的是SQL server数据库，在该类中，选择连接的数据库名为Company,数据库已经提前创建好，并且创建了员工、经理和股东三个表，表中的数据来源于程序运行时输入的值，该类封装好了三个函数，executeQuery（用来执行select和where等语句）、executeUpdate（进行insert等操作）和close（释放连接），主要代码如下所示：
	
			连接数据库
			public static Connection getConnection() {
						try {
				            //加载sqlserver的驱动类
				            Class.forName(driver);
				            //获取数据库连接
				            return DriverManager.getConnection(url,"sa", "456759");
				        } catch (Exception e) {
				            e.printStackTrace();
				            System.out.println("链接数据库失败");
				            return null;
				        }
			}
		
			/**
			 * 执行查询，返回结果集 ，使用next方法，迭代结果集，使用getXXX方法，获得每条记录的字段值 
			 * @param sql 要执行的sql语句
			 * @return
			 */
			public ResultSet executeQuery(String sql)throws Exception {
				try { //链接数据库然后执行sql语句
					conn = getConnection(); 
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					rs = stmt.executeQuery(sql);//执行sql语句
				} catch (SQLException ex) {
					System.err.println(ex.getMessage()); 
				}
				return rs; 
				
			}
			
			/**
			 * 更新操作
			 * @param sql
			 * @return 更新条数
			 * @throws Exception
			 */
			public int executeUpdate(String sql) throws Exception {
				int result = 0; //
				try { 
					conn = getConnection(); // 先进行链接
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					result = stmt.executeUpdate(sql); 
				} catch (SQLException ex) {
					result = 0; 
				}
				return result; 
			}
		
			//关闭链接
			public void close() {
				try {
					if (rs != null) { 
						rs.close(); 
					}
					if (stmt != null) { 
						stmt.close(); 
					}
					if (conn != null) {
						conn.close();
					}
				}
				catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
## 主控制类MainCtrl ##
> 在该类中定义了几个初始化函数，initE、initM、initS，分别用来在主函数启动时向数据库中插入员工、经理、股东信息，如下展示了如何初始化员工的代码，其他初始化类似
	
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
>然后再主函数中用while循环读取数据库中存入的信息，如下以读取员工信息为例

			while(resultSet.next()) {
				System.out.println("员工"+resultSet.getString("name")+"的工资为："+resultSet.getInt("salary"));
				ledger-=resultSet.getInt("salary");
				System.out.println("员工生日月份为："+resultSet.getInt("birthday"));
				if(month==resultSet.getInt("birthday")) {
					System.out.println("生日礼物为"+birthdaygift);
					ledger-=birthdaygift;
				}	
			}
>ledger是公司总收入，设定为一百万，没给员工发工资就要从总收入中减掉，将最后剩下的总资产的10%用来分红，最终股东分红的代码如下


		int totalfenhong=(int) (ledger*0.1);
			resultSet=connDB.executeQuery(sql);
			while(resultSet.next()) {
				System.out.println("股东"+resultSet.getString("name")+"的股份为："+
						resultSet.getFloat("share")+"分红为："+totalfenhong*resultSet.getFloat("share"));
		}
