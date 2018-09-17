package com.VisualJavaComponents.DB;

/**
 * @author root
 *
 */
public enum TDBDrivers {
	
	/*
	 * url的参数顺序一定要是：服务器ip，服务器port，数据库名称
	 * 
	 */
	dbdORACLE("Oracle", "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@%s:%s:%s"),
	dbdMYSQL("Mysql", "com.mysql.jdbc.Driver", "jdbc:mysql://%s:%s/%s"),
	//mysql8版本的连接方式略有不同
	dbdMYSQL8("Mysql8", "com.mysql.cj.jdbc.Driver", "jdbc:mysql://%s:%s/%s?characterEncoding=utf8&useSSL=false");
	
		// 成员变量
	private String name;
	private String driverName;
	private String url;

	// 构造方法
	private TDBDrivers(String name, String driverName, String url) {
		this.name = name;
		this.driverName = driverName;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public String getDriverName() {
		return driverName;
	}

	public String getUrl() {
		return url;
	}
}
