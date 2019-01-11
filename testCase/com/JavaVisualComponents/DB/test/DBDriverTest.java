package com.JavaVisualComponents.DB.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.JVComponents.DB.DBDriver;
import com.JVComponents.DB.TDBDrivers;

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
			fail("杩炴帴鏁版嵁搴撳け璐ワ紒");
		} else {
			System.out.println("杩炴帴鏁版嵁搴撴垚鍔燂紒");
			dbdriver.CloseConnection(conn);
		}
	}

	@Test
	void test2ExecuteUpdate() {
		String sql = "INSERT INTO T_CLIC_NAME(CLIC_NAME_ID,CLIC_NAME)VALUES(1,'娴嬭瘯鏂囧瓧')";
		Connection conn = dbdriver.getConnection(ip, port, db, user, pwd);
		if (conn == null) {
			System.out.println(dbdriver.getErrorMessage());
			fail("杩炴帴鏁版嵁搴撳け璐ワ紒");
		} else {
			System.out.println("杩炴帴鏁版嵁搴撴垚鍔燂紒");

			try {
				if (!dbdriver.StartTransaction(conn)) {
					System.out.println(dbdriver.getErrorMessage());
					fail("浜嬪姟寮�鍚け璐ワ�?");
				} else {
					System.out.println("浜嬪姟寮�鍚垚鍔燂�?");
				}

				if (!dbdriver.ExecuteUpdate(conn, sql)) {
					System.out.println(dbdriver.getErrorMessage());
					fail("鏇存柊鎵ц澶辫触锛�");
				} else {
					System.out.println("SQL鎵ц鎴愬姛锛�?");
				}

				if (!dbdriver.Commit(conn)) {
					System.out.println(dbdriver.getErrorMessage());
					fail("浜嬪姟鎻愪氦澶辫触锛�?");
				} else {
					System.out.println("浜嬪姟鎻愪氦鎴愬姛锛�?");
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
			fail("杩炴帴鏁版嵁搴撳け璐ワ紒");
		} else {
			System.out.println("杩炴帴鏁版嵁搴撴垚鍔燂紒");

			try {
				ResultSet st = dbdriver.OpenQuery(conn, sql);
				if (st == null) {
					System.out.println(dbdriver.getErrorMessage());
					fail("鏁版嵁闆嗗紑鍚け璐ワ紒");
				} else {
					// 璇诲彇鏁版嵁
					try {
						while (st.next()) {
							System.out.println(String.format("id=%s name=%s", st.getString("CLIC_NAME_ID"),
									st.getString("CLIC_NAME")));
						}
					} catch (SQLException e) {
						System.out.println(e.getMessage());
						fail("鏁版嵁闆嗛亶鍘嗗け璐ワ紒");
					} finally {
						// 鍏抽棴鏁版嵁闆�
						dbdriver.CloseQuery(st);
					}
				}
			} finally {
				dbdriver.CloseConnection(conn);
			}
		}
	}

}
