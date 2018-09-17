package com.VisualJavaComponents.DB;

import java.sql.*;
import java.util.*;

/**
 * @author root 数据库连接对象
 */
public class DBConnection {

	/**
	 * 数据库驱动对象
	 */
	private DBDriver dbDriver;

	public DBDriver getDbDriver() {
		return dbDriver;
	}

	/**
	 * 设置数据库驱动
	 * 
	 * @param dbd
	 */
	public void setDBDriver(TDBDrivers dbd) {
		// 如果驱动类型不同则释放后重新创建
		if (dbd != dbDriver.getDbdriver()) {
			// 关闭所有连接和数据集
			CloseConnection();
			// 改变原对象的引用，自动释放
			dbDriver = new DBDriver(dbd);
		}
	}

	// 错误信息
	private String errormsg;
	// 服务器ip
	private String serverIp;
	// 服务器端口
	private String serverPort;
	// 数据库名
	private String dbName;
	// 用户名
	private String userName;
	// 用户口令
	private String password;

	public String getErrormsg() {
		return errormsg;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		// 相同则退出
		if (this.serverIp.equalsIgnoreCase(serverIp))
			return;

		// 关闭连接后重新设置
		CloseConnection();
		this.serverIp = serverIp;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		// 相同则退出
		if (this.serverPort.equalsIgnoreCase(serverPort))
			return;

		// 关闭连接后重新设置
		CloseConnection();
		this.serverPort = serverPort;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		// 相同则退出
		if (this.dbName.equalsIgnoreCase(dbName))
			return;

		// 关闭连接后重新设置
		CloseConnection();
		this.dbName = dbName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		// 相同则退出
		if (this.userName.equalsIgnoreCase(userName))
			return;

		// 关闭连接后重新设置
		CloseConnection();
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		// 相同则退出
		if (this.password.equals(password))
			return;

		// 关闭连接后重新设置
		CloseConnection();
		this.password = password;
	}

	/**
	 * 数据库连接
	 */
	private Connection conn;

	public Boolean getConnected() {
		Boolean result = false;
		if (conn != null) {
			try {
				result = conn.isClosed();
			} catch (SQLException e) {
				// 忽略错误
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
		// 如果已经开启则返回
		if (this.getConnected())
			return true;
		// 调用驱动开启连接
		if (dbDriver != null) {
			conn = dbDriver.getConnection(serverIp, serverPort, dbName, userName, password);
			if (conn != null) {
				result = true;
			} else {
				errormsg = dbDriver.getErrorMessage();
			}
		} else {
			errormsg = "数据库驱动对象不存在！";
		}

		return result;
	}

	/**
	 * 关闭所有数据库连接及数据集
	 * 
	 * @return 是否成功
	 */
	public Boolean CloseConnection() {
		Boolean result = true;
		// 关闭所有数据集，释放资源
		for (DBDataSet dbs : dbDataSets) {
			dbs.close();
		}

		// 关闭连接
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// 忽略错误
			}
			// 设置连接为空，释放资源
			conn = null;
		}
		return result;
	}

	/**
	 * 关闭一个数据集
	 * 
	 * @param ds 需要关闭的数据集
	 * @return 是否成功
	 */
	public Boolean CloseDataSet(DBDataSet ds) {
		Boolean result = true;
		// 确保是当前连接
		if ((ds != null) && (ds.getDbconn() == this)) {
			result = dbDriver.CloseQuery(ds.getResultSet());
		}
		return result;
	}

	/**
	 * 开启一个数据集
	 * 
	 * @param ds 需要开启的数据集
	 * @return 是否成功
	 */
	public Boolean OpenDataSet(DBDataSet ds) {
		Boolean result = false;
		// 确保是当前连接
		if ((ds != null) && (ds.getDbconn() == this)) {
			// 开启数据集
			ResultSet st = dbDriver.OpenQuery(conn, ds.getSql());
			if (st != null) {
				// 将数据集结果保存在数据集对象中
				ds.setResultSet(st);
				result = true;
			}
		}
		return result;
	}

	/**
	 * 执行数据集对应的SQL
	 * 
	 * @param ds 数据集
	 * @return 是否成功
	 */
	public Boolean ExecuteUpdate(DBDataSet ds) {
		Boolean result = false;
		// 确保是当前连接
		if ((ds != null) && (ds.getDbconn() == this)) {
			// 执行数据集SQL
			result = dbDriver.ExecuteUpdate(conn, ds.getSql());
		}
		return result;
	}

	/**
	 * 获取影响的记录数
	 * 
	 * @return 最后一条SQL执行后影响的记录数
	 */
	public int getRowEffected() {
		int result = 0;
		if (dbDriver != null) {
			result = dbDriver.getRowEffected();
		}
		return result;
	}

	/**
	 * 与此连接相关的数据集，当设置数据集的Connection属性时进行注册
	 */
	private HashSet<DBDataSet> dbDataSets;

	public DBConnection() {
		super();
		dbDataSets = new HashSet<DBDataSet>();
	}

	/**
	 * 注册一个数据集
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
	 * 注销一个数据集
	 * 
	 * @param dbset
	 */
	public void UnRegistDBDataSet(DBDataSet dbset) {
		if (dbset == null)
			return;

		if (dbDataSets.contains(dbset)) {
			// 先关闭数据集
			dbset.close();
			// 注销
			dbDataSets.remove(dbset);
		}
	}

}
