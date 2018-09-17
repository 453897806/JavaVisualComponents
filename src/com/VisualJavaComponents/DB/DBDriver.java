package com.VisualJavaComponents.DB;

import java.sql.*;

/**
 * @author root 数据库驱动类
 */
public class DBDriver {

	/**
	 * 数据库驱动类型
	 */
	private TDBDrivers dbdriver;

	public TDBDrivers getDbdriver() {
		return dbdriver;
	}

	/**
	 * 错误信息
	 */
	private String errormsg;

	/**
	 * 执行ExecuteUpdate后影响的记录条数
	 */
	private int rowEffected;

	public DBDriver(TDBDrivers dbdriver) {
		super();
		this.dbdriver = dbdriver;
	}

	/**
	 * 获取数据库名称
	 * 
	 * @return 数据库名称，如oracle
	 */
	public String getName() {
		return dbdriver.getName();
	}

	/**
	 * 获取数据库驱动名称
	 * 
	 * @return 数据库驱动名称，如oracle.jdbc.driver.OracleDriver
	 */
	public String getDriverName() {
		return dbdriver.getDriverName();
	}

	/**
	 * 获取数据库驱动连接URL
	 * 
	 * @return 数据库驱动连接URL
	 */
	public String getUrl() {
		return dbdriver.getUrl();
	}

	/**
	 * 获取错误信息
	 * 
	 * @return 错误信息
	 */
	public String getErrorMessage() {
		return this.errormsg;
	}

	/**
	 * 获取影响的记录条数
	 * 
	 * @return 执行ExecuteUpdate后影响的记录条数
	 */
	public int getRowEffected() {
		return this.rowEffected;
	}

	/**
	 * 开启数据库连接
	 * 
	 * @param ip       服务器ip地址
	 * @param port     服务器端口号
	 * @param db       数据库名称
	 * @param user     用户名
	 * @param password 用户登录口令
	 * @return 数据库连接，如果null表示失败
	 */
	public Connection getConnection(String ip, String port, String db, String user, String password) {
		Connection result = null;

		String connurl = String.format(getUrl(), ip, port, db);
		try {
			// 1.注册驱动
			Class.forName(getDriverName());
			// 2.创建Connection(数据库连接对象)
			result = DriverManager.getConnection(connurl, user, password);
		} catch (ClassNotFoundException e) {
			errormsg = e.getMessage();
		} catch (SQLException e) {
			errormsg = e.getMessage();
		}
		return result;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param conn 需要关闭的数据库连接
	 * @return 是否成功
	 */
	public Boolean CloseConnection(Connection conn) {
		Boolean result = false;
		if (conn != null) {
			try {
				// 关闭连接
				conn.close();
				result = conn.isClosed();
			} catch (SQLException e) {
				errormsg = e.getMessage();
			}
		} else {
			errormsg = "数据库连接对象(conn)为空（null）!";
		}
		return result;
	}

	/**
	 * 启动数据库事务
	 * 
	 * @param conn 需要启动事务的数据库连接
	 * @return 是否成功
	 */
	public Boolean StartTransaction(Connection conn) {
		Boolean result = false;
		if (conn != null) {
			try {
				// 设置自动事务为false，让事务手动处理
				if (conn.getAutoCommit()) {
					conn.setAutoCommit(false);
				}
				result = true;
			} catch (SQLException e) {
				errormsg = e.getMessage();
			}
		}
		return result;
	}

	/**
	 * 回滚数据库事务
	 * 
	 * @param 需要回滚事务的数据库连接
	 * @return 是否成功
	 */
	public Boolean Rollback(Connection conn) {
		Boolean result = false;
		if (conn != null) {
			try {
				// 手动事务时回滚
				if (!conn.getAutoCommit()) {
					conn.rollback();
				}
				result = true;
			} catch (SQLException e) {
				errormsg = e.getMessage();
			}
		} else {
			errormsg = "数据库连接对象(conn)为空（null）!";
		}
		return result;
	}

	/**
	 * 提交数据库事务
	 * 
	 * @param 需要提交事务的数据库连接
	 * @return 是否成功
	 */
	public Boolean Commit(Connection conn) {
		Boolean result = false;
		if (conn != null) {
			try {
				// 手动事务时提交
				if (!conn.getAutoCommit()) {
					conn.commit();
				}
				result = true;
			} catch (SQLException e) {
				errormsg = e.getMessage();
			}
		} else {
			errormsg = "数据库连接对象(conn)为空（null）!";
		}
		return result;
	}

	/**
	 * 执行SQL语句
	 * 
	 * @param conn 需要执行sql的数据库连接
	 * @param sql  需要执行的sql
	 * @return 是否成功
	 */
	public Boolean ExecuteUpdate(Connection conn, String sql) {
		Boolean result = false;
		Statement st;
		if (conn != null) {
			try {
				// 创建Statement
				st = conn.createStatement();
				try {
					rowEffected = st.executeUpdate(sql);
					result = true;
				} finally {
					st.close();
				}
			} catch (SQLException e) {
				errormsg = e.getMessage();
			}
		} else {
			errormsg = "数据库连接对象(conn)为空（null）!";
		}
		return result;
	}

	/**
	 * 执行sql语句并返回一个数据集
	 * 
	 * @param conn 需要执行sql的数据库连接
	 * @param sql  需要执行的sql
	 * @return 结果数据集，如果为null表示失败
	 */
	public ResultSet OpenQuery(Connection conn, String sql) {
		ResultSet result = null;
		Statement st = null;
		if (conn != null) {
			try {
				// 创建Statement
				st = conn.createStatement();
				// 执行
				result = st.executeQuery(sql);
			} catch (SQLException e) {
				errormsg = e.getMessage();
			}
		} else {
			errormsg = "数据库连接对象(conn)为空（null）!";
		}

		// 检查结果以决定是否关闭Statement
		if ((result == null) && (st != null)) {
			try {
				st.close();
			} catch (SQLException e) {
				// 忽略错误信息
			}
		}

		return result;
	}

	/**
	 * 关闭数据集
	 * 
	 * @param rs 数据集
	 * @return 是否成功
	 */
	public Boolean CloseQuery(ResultSet rs) {
		Boolean result = true;
		if (rs != null) {
			try {
				//关闭数据集
				if (!rs.isClosed()) {
					rs.close();
				}
				//关闭对应的Statement
				Statement st = rs.getStatement();
				if((st != null) && (!st.isClosed())){
					st.close();
				}
			} catch (SQLException e) {
				// 忽略错误
				result = false;
			}
		}

		return result;
	}
}
