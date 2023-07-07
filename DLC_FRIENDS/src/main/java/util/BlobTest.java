package util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BlobTest {

	public static void main(String[] args) {
		Connection conn = DBManager.getConnection();
		PreparedStatement pstmt = null;
		if(conn != null) {
			String sql = "insert into tbl_test (ID, FILENAME, FILE) VALUES (?, ?, ?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, 7);
				pstmt.setString(2, "namu");
				File file = new File("C:\\Users\\regul\\git\\DLC_FRIENDS\\DLC_FRIENDS\\src\\main\\webapp\\resources\\images\\namu.jpg");
				FileInputStream fin = new FileInputStream(file);
				pstmt.setBinaryStream(3, fin,(int)file.length());
				pstmt.execute();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBManager.close(conn, pstmt);
			}
		}
	}

}
