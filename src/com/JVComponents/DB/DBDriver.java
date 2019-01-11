package com.JVComponents.DB;

import java.sql.*;

/**
 * @author root 
 * 
 */
public class DBDriver {

	/**
	 * 鏁版嵁搴撻┍鍔ㄧ被鍨�?
	 */
	private TDBDrivers dbdriver;

	public TDBDrivers getDbdriver() {
		return dbdriver;
	}

	/**
	 * 閿欒淇℃伅
	 */
	private String errormsg;

	/**
	 * 鎵цExecuteUpdate鍚庡奖鍝嶇殑璁板綍鏉℃暟
	 */
	private int rowEffected;

	public DBDriver(TDBDrivers dbdriver) {
		super();
		this.dbdriver = dbdriver;
	}

	/**
	 * 鑾峰彇鏁版嵁搴撳悕绉�?
	 * 
	 * @return 鏁版嵁搴撳悕绉帮紝濡俹racle
	 */
	public String getName() {
		return dbdriver.getName();
	}

	/**
	 * 鑾峰彇鏁版嵁搴撻┍鍔ㄥ悕绉�
	 * 
	 * @return 鏁版嵁搴撻┍鍔ㄥ悕绉帮紝濡俹racle.jdbc.driver.OracleDriver
	 */
	public String getDriverName() {
		return dbdriver.getDriverName();
	}

	/**
	 * 鑾峰彇鏁版嵁搴撻┍鍔ㄨ繛鎺RL
	 * 
	 * @return 鏁版嵁搴撻┍鍔ㄨ繛鎺RL
	 */
	public String getUrl() {
		return dbdriver.getUrl();
	}

	/**
	 * 鑾峰彇閿欒淇℃�?
	 * 
	 * @return 閿欒淇℃伅
	 */
	public String getErrorMessage() {
		return this.errormsg;
	}

	/**
	 * 鑾峰彇褰卞搷鐨勮褰曟潯鏁�
	 * 
	 * @return 鎵цExecuteUpdate鍚庡奖鍝嶇殑璁板綍鏉℃暟
	 */
	public int getRowEffected() {
		return this.rowEffected;
	}

	/**
	 * 寮�鍚暟鎹簱杩炴�?
	 * 
	 * @param ip       鏈嶅姟鍣╥p鍦板�?
	 * @param port     鏈嶅姟鍣ㄧ鍙ｅ�?
	 * @param db       鏁版嵁搴撳悕绉�
	 * @param user     鐢ㄦ埛鍚�?
	 * @param password 鐢ㄦ埛鐧诲綍鍙ｄ�?
	 * @return 鏁版嵁搴撹繛鎺ワ紝濡傛灉null琛ㄧず澶辫触
	 */
	public Connection getConnection(String ip, String port, String db, String user, String password) {
		Connection result = null;

		String connurl = String.format(getUrl(), ip, port, db);
		try {
			// 1.娉ㄥ唽椹卞姩
			Class.forName(getDriverName());
			// 2.鍒涘缓Connection(鏁版嵁搴撹繛鎺ュ璞�?)
			result = DriverManager.getConnection(connurl, user, password);
		} catch (ClassNotFoundException e) {
			errormsg = e.getMessage();
		} catch (SQLException e) {
			errormsg = e.getMessage();
		}
		return result;
	}

	/**
	 * 鍏抽棴鏁版嵁搴撹繛鎺�?
	 * 
	 * @param conn 闇�瑕佸叧闂殑鏁版嵁搴撹繛鎺�
	 * @return 鏄惁鎴愬姛
	 */
	public Boolean CloseConnection(Connection conn) {
		Boolean result = false;
		if (conn != null) {
			try {
				// 鍏抽棴杩炴帴
				conn.close();
				result = conn.isClosed();
			} catch (SQLException e) {
				errormsg = e.getMessage();
			}
		} else {
			errormsg = "鏁版嵁搴撹繛鎺ュ璞�?(conn)涓虹┖锛坣ull锛�!";
		}
		return result;
	}

	/**
	 * 鍚姩鏁版嵁搴撲簨鍔�?
	 * 
	 * @param conn 闇�瑕佸惎鍔ㄤ簨鍔＄殑鏁版嵁搴撹繛鎺�?
	 * @return 鏄惁鎴愬姛
	 */
	public Boolean StartTransaction(Connection conn) {
		Boolean result = false;
		if (conn != null) {
			try {
				// 璁剧疆鑷姩浜嬪姟涓篺alse锛岃�?浜嬪姟鎵嬪姩澶勭�?
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
	 * 鍥炴粴鏁版嵁搴撲簨鍔�?
	 * 
	 * @param 闇�瑕佸洖婊氫簨鍔＄殑鏁版嵁搴撹繛鎺�?
	 * @return 鏄惁鎴愬姛
	 */
	public Boolean Rollback(Connection conn) {
		Boolean result = false;
		if (conn != null) {
			try {
				// 鎵嬪姩浜嬪姟鏃跺洖婊�?
				if (!conn.getAutoCommit()) {
					conn.rollback();
				}
				result = true;
			} catch (SQLException e) {
				errormsg = e.getMessage();
			}
		} else {
			errormsg = "鏁版嵁搴撹繛鎺ュ璞�?(conn)涓虹┖锛坣ull锛�!";
		}
		return result;
	}

	/**
	 * 鎻愪氦鏁版嵁搴撲簨鍔�?
	 * 
	 * @param 闇�瑕佹彁浜や簨鍔＄殑鏁版嵁搴撹繛鎺�?
	 * @return 鏄惁鎴愬姛
	 */
	public Boolean Commit(Connection conn) {
		Boolean result = false;
		if (conn != null) {
			try {
				// 鎵嬪姩浜嬪姟鏃舵彁浜�?
				if (!conn.getAutoCommit()) {
					conn.commit();
				}
				result = true;
			} catch (SQLException e) {
				errormsg = e.getMessage();
			}
		} else {
			errormsg = "鏁版嵁搴撹繛鎺ュ璞�?(conn)涓虹┖锛坣ull锛�!";
		}
		return result;
	}

	/**
	 * 鎵цSQL璇�?
	 * 
	 * @param conn 闇�瑕佹墽琛宻ql鐨勬暟鎹簱杩炴�?
	 * @param sql  闇�瑕佹墽琛�?殑sql
	 * @return 鏄惁鎴愬姛
	 */
	public Boolean ExecuteUpdate(Connection conn, String sql) {
		Boolean result = false;
		Statement st;
		if (conn != null) {
			try {
				// 鍒涘缓Statement
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
			errormsg = "鏁版嵁搴撹繛鎺ュ璞�?(conn)涓虹┖锛坣ull锛�!";
		}
		return result;
	}

	/**
	 * 鎵цsql璇彞骞惰繑鍥炰竴涓暟鎹�?
	 * 
	 * @param conn 闇�瑕佹墽琛宻ql鐨勬暟鎹簱杩炴�?
	 * @param sql  闇�瑕佹墽琛�?殑sql
	 * @return 缁撴灉鏁版嵁闆嗭紝濡傛灉涓簄ull琛ㄧず澶辫触
	 */
	public ResultSet OpenQuery(Connection conn, String sql) {
		ResultSet result = null;
		Statement st = null;
		if (conn != null) {
			try {
				// 鍒涘缓Statement
				st = conn.createStatement();
				// 鎵ц
				result = st.executeQuery(sql);
			} catch (SQLException e) {
				errormsg = e.getMessage();
			}
		} else {
			errormsg = "鏁版嵁搴撹繛鎺ュ璞�?(conn)涓虹┖锛坣ull锛�!";
		}

		// 妫�鏌ョ粨鏋�?互鍐冲畾鏄惁鍏抽棴Statement
		if ((result == null) && (st != null)) {
			try {
				st.close();
			} catch (SQLException e) {
				// 蹇界暐閿欒淇℃�?
			}
		}

		return result;
	}

	/**
	 * 鍏抽棴鏁版嵁闆�
	 * 
	 * @param rs 鏁版嵁闆�?
	 * @return 鏄惁鎴愬姛
	 */
	public Boolean CloseQuery(ResultSet rs) {
		Boolean result = true;
		if (rs != null) {
			try {
				//鍏抽棴鏁版嵁闆�
				if (!rs.isClosed()) {
					rs.close();
				}
				//鍏抽棴�?�瑰簲鐨凷tatement
				Statement st = rs.getStatement();
				if((st != null) && (!st.isClosed())){
					st.close();
				}
			} catch (SQLException e) {
				// 蹇界暐閿欒
				result = false;
			}
		}

		return result;
	}
}
