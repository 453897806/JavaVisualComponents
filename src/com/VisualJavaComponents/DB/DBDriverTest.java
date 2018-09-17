package com.VisualJavaComponents.DB;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
//import org.junit.runners.MethodSorters;

import java.sql.*;

public class DBDriverTest {

	private static DBDriver dbdriver;
	private static String ip = "127.0.0.1";
	private static String port = "3306";
	private static String db = "db1";
	private static String user = "root";
	private static String pwd = "Clb123!@#";

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		dbdriver = new DBDriver(TDBDrivers.dbdMYSQL8);
	}

	@Test
	void test1GetConnection() {
		Connection conn = dbdriver.getConnection(ip, port, db, user, pwd);
		if (conn == null) {
			System.out.println(dbdriver.getErrorMessage());
			fail("连接数据库失败！");
		} else {
			System.out.println("连接数据库成功！");
			dbdriver.CloseConnection(conn);
		}
	}

	@Test
	void test2ExecuteUpdate() {
		String sql = "INSERT INTO T_CLIC_NAME(CLIC_NAME_ID,CLIC_NAME)VALUES(1,'测试文字')";
		Connection conn = dbdriver.getConnection(ip, port, db, user, pwd);
		if (conn == null) {
			System.out.println(dbdriver.getErrorMessage());
			fail("连接数据库失败！");
		} else {
			System.out.println("连接数据库成功！");

			try {
				if (!dbdriver.StartTransaction(conn)) {
					System.out.println(dbdriver.getErrorMessage());
					fail("事务开启失败！");
				} else {
					System.out.println("事务开启成功！");
				}

				if (!dbdriver.ExecuteUpdate(conn, sql)) {
					System.out.println(dbdriver.getErrorMessage());
					fail("更新执行失败！");
				} else {
					System.out.println("SQL执行成功！");
				}

				if (!dbdriver.Commit(conn)) {
					System.out.println(dbdriver.getErrorMessage());
					fail("事务提交失败！");
				} else {
					System.out.println("事务提交成功！");
				}
			} finally {
				dbdriver.CloseConnection(conn);
			}
		}
	}

	@Test
	void test3OpenQuery() {
		String sql = "SELECT * FROM T_CLIC_NAME";
		Connection conn = dbdriver.getConnection(ip, port, db, user, pwd);
		if (conn == null) {
			System.out.println(dbdriver.getErrorMessage());
			fail("连接数据库失败！");
		} else {
			System.out.println("连接数据库成功！");

			try {
				ResultSet st = dbdriver.OpenQuery(conn, sql);
				if (st == null) {
					System.out.println(dbdriver.getErrorMessage());
					fail("数据集开启失败！");
				} else {
					// 读取数据
					try {
						while (st.next()) {
							System.out.println(String.format("id=%s name=%s", st.getString("CLIC_NAME_ID"),
									st.getString("CLIC_NAME")));
						}
					} catch (SQLException e) {
						System.out.println(e.getMessage());
						fail("数据集遍历失败！");
					} finally {
						// 关闭数据集
						dbdriver.CloseQuery(st);
					}
				}
			} finally {
				dbdriver.CloseConnection(conn);
			}
		}
	}

}
