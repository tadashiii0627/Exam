package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao{
	private String baseSql = "select * from student where school_cd=?";

	private List<TestListStudent> postFilter(ResultSet rSet) throws Exception{
		List<TestListStudent> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try{
			while (rSet.next()){
				TestListStudent testliststudent = new TestListStudent();
				testliststudent.setSubjectName(rSet.getString("subjectName"));
				testliststudent.setSubjectCd(rSet.getString("subjectCd"));
				testliststudent.setNum(rSet.getInt("num"));
				testliststudent.setPoint(rSet.getInt("point"));

				list.add(testliststudent);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}
	private List<TestListStudent> filter(Student student) throws Exception{
			// コネクションを確立
			Connection connection = getConnection();
			// プリペアードステートメント
			PreparedStatement statement = null;
			// リザルトセット
			ResultSet rSet = null;
			// SQL文のソート
			String order = " order by no asc";
			//リストを初期化
			List<TestListStudent> list = new ArrayList<>();

			try {
				// プリペアードステートメントのSQL文をセット
				statement = connection.prepareStatement("select * from student where school_cd=?"+ order);
				// プリペアードステートメントに学生番号をバインド
				statement.setString(1, student.getNo());
				// プリペアードステートメントを実行
				rSet = statement.executeQuery();

			} catch (Exception e) {
				throw e;
			} finally {
				// プリペアードステートメントを閉じる
				if (statement != null) {
					try {
						statement.close();
					} catch (SQLException sqle) {
						throw sqle;
					}
				}
				// コネクションを閉じる
				if (connection != null) {
					try {
						connection .close();
					} catch (SQLException sqle) {
						throw sqle;
					}
				}
			}

			return list;
	}
}