package com.JVComponents.DB;

import java.sql.*;
import java.util.*;

import com.JVComponents.core.JVContainer;
import com.JVComponents.core.JVException;
import com.JVComponents.core.JVirtualComponent;

/**
 * 数据库连接组件
 * 
 * @author 
 */
public class DBConnection extends JVirtualComponent{

	/**
	 * 驱动服务对象
	 */
	private DBDriver dbDriver;

	public DBDriver getDbDriver() {
		return dbDriver;
	}

	/**
	 * 
	 * 设置驱动
	 * 
	 * @param dbd
	 */
	public void setDBDriver(TDBDrivers dbd) {
		// 
		if (dbd != dbDriver.getDbdriver()) {
			// 
			CloseConnection();
			// 
			dbDriver = new DBDriver(dbd);
		}
	}

	// 閿欒淇℃伅
	private String errormsg;
	// 鏈嶅姟鍣╥p
	private String serverIp;
	// 鏈嶅姟鍣ㄧ鍙�
	private String serverPort;
	// 鏁版嵁搴撳悕
	private String dbName;
	// 鐢ㄦ埛鍚�?
	private String userName;
	// 鐢ㄦ埛鍙ｄ护
	private String password;

	public String getErrormsg() {
		return errormsg;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		// 鐩稿悓鍒欓��鍑�?
		if (this.serverIp.equalsIgnoreCase(serverIp))
			return;

		// 鍏抽棴杩炴帴鍚庨噸鏂拌缃�
		CloseConnection();
		this.serverIp = serverIp;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		// 鐩稿悓鍒欓��鍑�?
		if (this.serverPort.equalsIgnoreCase(serverPort))
			return;

		// 鍏抽棴杩炴帴鍚庨噸鏂拌缃�
		CloseConnection();
		this.serverPort = serverPort;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		// 鐩稿悓鍒欓��鍑�?
		if (this.dbName.equalsIgnoreCase(dbName))
			return;

		// 鍏抽棴杩炴帴鍚庨噸鏂拌缃�
		CloseConnection();
		this.dbName = dbName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		// 鐩稿悓鍒欓��鍑�?
		if (this.userName.equalsIgnoreCase(userName))
			return;

		// 鍏抽棴杩炴帴鍚庨噸鏂拌缃�
		CloseConnection();
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		// 鐩稿悓鍒欓��鍑�?
		if (this.password.equals(password))
			return;

		// 鍏抽棴杩炴帴鍚庨噸鏂拌缃�
		CloseConnection();
		this.password = password;
	}

	/**
	 * 鏁版嵁搴撹繛鎺�
	 */
	private Connection conn;

	public Boolean getConnected() {
		Boolean result = false;
		if (conn != null) {
			try {
				result = conn.isClosed();
			} catch (SQLException e) {
				// 蹇界暐閿欒
			}
		}
		return result;
	}

	public void setConnected(Boolean connected) {
		if (connected) {
			OpenConnection();
		} else {
			CloseConnection();
		}
	}

	public Boolean OpenConnection() {
		Boolean result = false;
		// 濡傛灉宸茬粡寮�鍚垯杩斿洖
		if (this.getConnected())
			return true;
		// 璋冪敤椹卞姩寮�鍚繛鎺�?
		if (dbDriver != null) {
			conn = dbDriver.getConnection(serverIp, serverPort, dbName, userName, password);
			if (conn != null) {
				result = true;
			} else {
				errormsg = dbDriver.getErrorMessage();
			}
		} else {
			errormsg = "鏁版嵁搴撻┍鍔ㄥ璞′笉瀛樺湪锛�?";
		}

		return result;
	}

	/**
	 * 鍏抽棴鎵�鏈夋暟鎹簱杩炴帴鍙婃暟鎹�?�?
	 * 
	 * @return 鏄惁鎴愬姛
	 */
	public Boolean CloseConnection() {
		Boolean result = true;
		// 鍏抽棴鎵�鏈夋暟鎹泦锛岄噴�?捐祫婧�
		for (DBDataSet dbs : dbDataSets) {
			dbs.close();
		}

		// 鍏抽棴杩炴帴
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// 蹇界暐閿欒
			}
			// 璁剧疆杩炴帴涓虹┖锛岄噴�?捐祫婧�
			conn = null;
		}
		return result;
	}

	/**
	 * 鍏抽棴涓�涓暟鎹�?
	 * 
	 * @param ds 闇�瑕佸叧闂殑鏁版嵁闆�?
	 * @return 鏄惁鎴愬姛
	 */
	public Boolean CloseDataSet(DBDataSet ds) {
		Boolean result = true;
		// 纭繚鏄綋鍓嶈繛鎺�?
		if ((ds != null) && (ds.getDbconn() == this)) {
			result = dbDriver.CloseQuery(ds.getResultSet());
		}
		return result;
	}

	/**
	 * 寮�鍚竴涓暟鎹�?
	 * 
	 * @param ds 闇�瑕佸紑鍚殑鏁版嵁闆�?
	 * @return 鏄惁鎴愬姛
	 */
	public Boolean OpenDataSet(DBDataSet ds) {
		Boolean result = false;
		// 纭繚鏄綋鍓嶈繛鎺�?
		if ((ds != null) && (ds.getDbconn() == this)) {
			// 寮�鍚暟鎹�?�?
			ResultSet st = dbDriver.OpenQuery(conn, ds.getSql());
			if (st != null) {
				// 灏嗘暟鎹�?泦缁撴灉淇濆瓨鍦ㄦ暟鎹泦�?�硅薄涓�?
				ds.setResultSet(st);
				result = true;
			}
		}
		return result;
	}

	/**
	 * 鎵ц鏁版嵁闆嗗搴旂殑SQL
	 * 
	 * @param ds 鏁版嵁闆�?
	 * @return 鏄惁鎴愬姛
	 */
	public Boolean ExecuteUpdate(DBDataSet ds) {
		Boolean result = false;
		// 纭繚鏄綋鍓嶈繛鎺�?
		if ((ds != null) && (ds.getDbconn() == this)) {
			// 鎵ц鏁版嵁闆哠QL
			result = dbDriver.ExecuteUpdate(conn, ds.getSql());
		}
		return result;
	}

	/**
	 * 鑾峰彇褰卞搷鐨勮褰曟暟
	 * 
	 * @return 鏈�鍚庝竴鏉QL鎵ц鍚庡奖鍝嶇殑璁板綍鏁�?
	 */
	public int getRowEffected() {
		int result = 0;
		if (dbDriver != null) {
			result = dbDriver.getRowEffected();
		}
		return result;
	}

	/**
	 * 涓庢杩炴帴鐩稿叧鐨勬暟鎹泦锛屽綋璁剧疆鏁版嵁闆嗙殑Connection灞炴�ф椂杩涜娉ㄥ唽
	 */
	private HashSet<DBDataSet> dbDataSets;

	public DBConnection(JVContainer container) throws JVException {
		super(container);
		
		//连接的数据集�?
		dbDataSets = new HashSet<DBDataSet>();
	}

	/**
	 * 注册数据�?
	 * 
	 * @param dbset
	 */
	public void RegistDBDataSet(DBDataSet dbset) {
		if (dbset == null)
			return;

		if (!dbDataSets.contains(dbset)) {
			dbDataSets.add(dbset);
		}
	}

	/**
	 * 注销数据�?
	 * 
	 * @param dbset
	 */
	public void UnRegistDBDataSet(DBDataSet dbset) {
		if (dbset == null)
			return;

		if (dbDataSets.contains(dbset)) {
			// 关闭数据�?
			dbset.close();
			// 注销
			dbDataSets.remove(dbset);
		}
	}

}
