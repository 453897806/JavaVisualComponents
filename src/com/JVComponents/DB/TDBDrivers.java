package com.JVComponents.DB;

/**
 * 
 * @author root
 *
 */
public enum TDBDrivers {
	
	/*
	 * url鐨勫弬鏁伴�?�搴忎竴瀹氳鏄細鏈嶅姟鍣╥p锛屾湇鍔″櫒port锛屾暟鎹簱鍚嶇О
	 * 
	 */
	dbdORACLE("Oracle", "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@%s:%s:%s"),
	dbdMYSQL("Mysql", "com.mysql.jdbc.Driver", "jdbc:mysql://%s:%s/%s"),
	//mysql8鐗堟湰鐨勮繛鎺ユ柟寮忕暐鏈変笉鍚�?
	dbdMYSQL8("Mysql8", "com.mysql.cj.jdbc.Driver", "jdbc:mysql://%s:%s/%s?characterEncoding=utf8&useSSL=false");
	
		// 鎴愬憳鍙�?�?
	private String name;
	private String driverName;
	private String url;

	// 鏋勯�犳柟娉�?
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
