package com.JVComponents.DB;

import java.sql.*;
//import java.util.*;

/**
 * @author root 鏁版嵁闆嗗璞�
 */
public class DBDataSet {

	private ResultSet resultSet;

	public Statement getStatement() {
		Statement result = null;
		if (resultSet != null) {
			try {
				result = resultSet.getStatement();
			} catch (SQLException e) {
				// 蹇界暐閿欒
			}
		}
		return result;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	/**
	 * 鏁版嵁闆嗗搴旂殑鏁版嵁搴撹繛鎺ュ璞�
	 */
	private DBConnection dbconn;

	public DBConnection getDbconn() {
		return dbconn;
	}

	public void setDbconn(DBConnection dbconn) {
		// 鐩稿悓鍒欓��鍑�?
		if (this.dbconn == dbconn)
			return;

		// 鍏抽棴褰撳墠鏁版嵁闆�?
		close();

		// 鍘熻繛鎺ヤ笂娉ㄩ�?
		this.dbconn.UnRegistDBDataSet(this);

		// 璁剧疆鏂拌繛鎺ュ苟娉ㄥ唽
		this.dbconn = dbconn;
		this.dbconn.RegistDBDataSet(this);
	}

	/**
	 * 鏁版嵁闆嗗搴旂殑SQL,鍙互鏄痵elect 锛屼篃鍙互鏄痠nsert绛夎鍙�?
	 */
	private String sql;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		// 鐩稿悓鍒欓��鍑�?
		if (this.sql.equals(sql))
			return;

		// 鍏抽棴褰撳墠鏁版嵁闆�?
		close();

		// 閲嶆柊璁剧疆
		this.sql = sql;
	}

	/**
	 * 鎸夌収sql灞炴�э紝寮�鍚竴涓暟鎹泦骞惰繑鍥炴暟鎹�?
	 * 
	 * @return 鏄惁鎴愬姛
	 */
	public Boolean OpenDataSet() {
		Boolean result = false;
		// 鏃犳暟鎹簱杩炴帴鎯呭喌
		if (dbconn == null)
			return result;
		// 閫氳繃鏁版嵁搴撹繛鎺ュ畬鎴愬紑鍚繃绋�
		result = dbconn.OpenDataSet(this);

		return result;
	}
	
	/**
	 * 鏈�鍚庝竴鏉QL鎵ц鍚庡奖鍝嶇殑璁板綍鏁�?
	 */
	private int RowEffected = 0;

	public int getRowEffected() {
		return RowEffected;
	}

	/**
	 * 鎸夌収sql灞炴�э紝鎵ц鍛戒护锛屼笉杩斿洖鏁版嵁
	 * 
	 * @return
	 */
	public Boolean ExecuteUpdate() {
		Boolean result = false;
		RowEffected = 0;
		// 鏃犳暟鎹簱杩炴帴鎯呭喌
		if (dbconn == null) {
			return result;
		} else {
			// 閫氳繃鏁版嵁搴撹繛鎺ュ畬鎴愬紑鍚繃绋�
			result = dbconn.ExecuteUpdate(this);
			//SQL鎵ц鍚庡奖鍝嶇殑璁板綍鏁�?
			if(result) {
				RowEffected = dbconn.getRowEffected();
			}
		}
		return result;
	}

	/**
	 * 鍏抽棴鏁版嵁闆�
	 * 
	 * @return 鏄惁鎴愬姛
	 */
	public Boolean close() {
		Boolean result = true;
		if (dbconn == null)
			return result;
		// 璋冪�? 鏁版嵁搴撹繛鎺ュ璞�? 杩涜鍏抽棴
		result = dbconn.CloseDataSet(this);
		// 娓呯┖鏁版嵁闆嗗璞�?
		resultSet = null;
		return result;
	}
	
	public Boolean next() {
		Boolean result = false;
		if(resultSet != null) {
			try {
				result = resultSet.next();
			}catch(SQLException e) {
				// 蹇界暐閿欒
			}
		}
		return result;
	}
	
	public Boolean previous() {
		Boolean result = false;
		if(resultSet != null) {
			try {
				result = resultSet.previous();
			}catch(SQLException e) {
				// 蹇界暐閿欒
			}
		}
		return result;
	}
	
	public Boolean first() {
		Boolean result = false;
		if(resultSet != null) {
			try {
				result = resultSet.first();
			}catch(SQLException e) {
				// 蹇界暐閿欒
			}
		}
		return result;
	}

	public Boolean last() {
		Boolean result = false;
		if(resultSet != null) {
			try {
				result = resultSet.last();
			}catch(SQLException e) {
				// 蹇界暐閿欒
			}
		}
		return result;
	}
	
	public Boolean gotoByIndex(int index) {
		Boolean result = false;
		if(resultSet != null) {
			try {
				resultSet.moveToCurrentRow();
			}catch(SQLException e) {
				// 蹇界暐閿欒
			}
		}
		return result;
	}
}
