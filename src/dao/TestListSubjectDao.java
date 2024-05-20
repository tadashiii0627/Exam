package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
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
	public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {

		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;

		// ＊＊＊＊cd部分にはないが入るのか分からない＊＊＊＊//
		// SQL文のソート
		String order = " order by student.no asc";
		// リストを初期化
		List<TestListSubject> list = new ArrayList<>();


		try {
			// プリペアードステートメントのSQL文をセット (5)
			//
			statement = connection.prepareStatement("select ent_year, student.class_num, subject_cd, test.school_cd, point, student.no,student.name "
			+"from student left join test on student.no = test.student_no where ent_year = ?"
			+"and  student.class_num = ? and subject_cd = ? and test.school_cd = ?");

			// プリペアードステートメントに受け取った値をバインド
			// 画面設計書(科目別成績一覧)に反映
			// 入学年度, クラス番号, 科目
			statement.setInt(1, entYear);
			statement.setString(2, classNum);
			statement.setString(3, subject.getCd());
			// 学生番号
			statement.setString(4, school.getCd());


			// プリペアードステートメントを実行
			rSet = statement.executeQuery();

						// リザルトセットを完全走査
						while (rSet.next()){
							// 成績一覧のインスタンスを初期化
							TestListSubject testlistsubject = new TestListSubject();
							// 成績一覧インスタンスに検索結果をセット(入学年度, クラス番号, 学生番号, 名前, 得点)
							testlistsubject.setEntYear(rSet.getInt("ent_year"));
							//???????????
							testlistsubject.setClassNum(rSet.getString("class_num"));
							testlistsubject.setStudentNo(rSet.getString("no"));
							testlistsubject.setStudentName(rSet.getString("name"));
							// 回数と得点をセット
							testlistsubject.putPoint(rSet.getInt("no"),rSet.getInt("point"));

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
