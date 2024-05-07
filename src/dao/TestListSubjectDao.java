package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListStudent;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {
	private String baseSql = "select * from test where =?";

	// ＜postFilterメソッド＞
	private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
		// リストを初期化
		List<TestListSubject> list = new ArrayList<>();
		try {
			// リザルトセットを完全走査
			while (rSet.next()){
				// testlistsubjectインスタンスを初期化

				TestListSubject testlistsubject = new TestListSubject ();
				// 学生インスタンスに検索結果をセット
				testlistsubject.setEntYear(rSet.getInt("ent_year"));
				testlistsubject.setStudentNo(rSet.getString("student_no"));
				testlistsubject.setStudentName(rSet.getString("student_name"));
				testlistsubject.setClassNum(rSet.getString("class_num"));
				//numで回数,pointsで点数を受け取る
				testlistsubject.putPoint(rSet.getInt("num"),rSet.getInt("points"));

				// リストに追加
				list.add(testlistsubject);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;

	}


	// ＜filterメソッド＞
	public List<TestListStudent> filter(int entYear, String classNum, Subject subject, School school) throws Exception {

		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;

		// ＊＊＊＊cd部分にはないが入るのか分からない＊＊＊＊//
		// SQL文のソート
		String order = " order by no asc";
		// リストを初期化
		List<TestListStudent> list = new ArrayList<>();


		try {
			// プリペアードステートメントのSQL文をセット
			statement = connection.prepareStatement("select ent_year, student.class_num=?, no=?, name=?, point=?
from student left join test on student.no = test.student_no"+ order);
			// プリペアードステートメントに受け取った値をバインド

			statement.setInt(1, entYear.getEntYear());
			statement.setString(2, classNum.getClassNum());
			statement.setString(3, no.getClassNum());
			statement.setString(4, classNum.getClassNum());
			statement.setString(4, classNum.getClassNum());
			// プリペアードステートメントを実行
			rSet = statement.executeQuery();

						// リザルトセットを完全走査
						while (rSet.next()){
							// 学生インスタンスを初期化
							TestListSubject testlistsubject = new TestListSubject();
							// 学生インスタンスに検索結果をセット
							testlistsubject.setCd(rSet.getString("school_cd"));

							// リストに追加
							list.add(testlistsubject);
						}
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
