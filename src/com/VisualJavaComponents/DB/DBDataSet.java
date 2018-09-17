package com.VisualJavaComponents.DB;

import java.sql.*;
import java.util.*;

/**
 * @author root 数据集对象
 */
public class DBDataSet {

	private ResultSet resultSet;

	public Statement getStatement() {
		Statement result = null;
		if (resultSet != null) {
			try {
				result = resultSet.getStatement();
			} catch (SQLException e) {
				// 忽略错误
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
	 * 数据集对应的数据库连接对象
	 */
	private DBConnection dbconn;

	public DBConnection getDbconn() {
		return dbconn;
	}

	public void setDbconn(DBConnection dbconn) {
		// 相同则退出
		if (this.dbconn == dbconn)
			return;

		// 关闭当前数据集
		close();

		// 原连接上注销
		this.dbconn.UnRegistDBDataSet(this);

		// 设置新连接并注册
		this.dbconn = dbconn;
		this.dbconn.RegistDBDataSet(this);
	}

	/**
	 * 数据集对应的SQL,可以是select ，也可以是insert等语句
	 */
	private String sql;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		// 相同则退出
		if (this.sql.equals(sql))
			return;

		// 关闭当前数据集
		close();

		// 重新设置
		this.sql = sql;
	}

	/**
	 * 按照sql属性，开启一个数据集并返回数据
	 * 
	 * @return 是否成功
	 */
	public Boolean OpenDataSet() {
		Boolean result = false;
		// 无数据库连接情况
		if (dbconn == null)
			return result;
		// 通过数据库连接完成开启过程
		result = dbconn.OpenDataSet(this);

		return result;
	}
	
	/**
	 * 最后一条SQL执行后影响的记录数
	 */
	private int RowEffected = 0;

	public int getRowEffected() {
		return RowEffected;
	}

	/**
	 * 按照sql属性，执行命令，不返回数据
	 * 
	 * @return
	 */
	public Boolean ExecuteUpdate() {
		Boolean result = false;
		RowEffected = 0;
		// 无数据库连接情况
		if (dbconn == null) {
			return result;
		} else {
			// 通过数据库连接完成开启过程
			result = dbconn.ExecuteUpdate(this);
			//SQL执行后影响的记录数
			if(result) {
				RowEffected = dbconn.getRowEffected();
			}
		}
		return result;
	}

	/**
	 * 关闭数据集
	 * 
	 * @return 是否成功
	 */
	public Boolean close() {
		Boolean result = true;
		if (dbconn == null)
			return result;
		// 调用 数据库连接对象 进行关闭
		result = dbconn.CloseDataSet(this);
		// 清空数据集对象
		resultSet = null;
		return result;
	}
	
	public Boolean next() {
		Boolean result = false;
		if(resultSet != null) {
			try {
				result = resultSet.next();
			}catch(SQLException e) {
				// 忽略错误
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
				// 忽略错误
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
				// 忽略错误
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
				// 忽略错误
			}
		}
		return result;
	}
	
	public Boolean gotoByIndex(int index) {
		Boolean result = false;
		if(resultSet != null) {
			try {
				resultSet.mov;
			}catch(SQLException e) {
				// 忽略错误
			}
		}
		return result;
	}
}
