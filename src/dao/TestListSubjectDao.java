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
		String order = " order by cd asc";
		// リストを初期化
		List<TestListStudent> list = new ArrayList<>();


		try {
			// プリペアードステートメントのSQL文をセット
			statement = connection.prepareStatement("select * from subject where school_cd=?"+ order);
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			// プリペアードステートメントを実行
			rSet = statement.executeQuery();

						// リザルトセットを完全走査
						while (rSet.next()){
							// 学生インスタンスを初期化
							TestListSubject testlistsubject = new TestListSubject();
							// 学生インスタンスに検索結果をセット
							testlistsubject.setCd(rSet.getString("school_cd"));

							// リストに追加
							list.add(subject);
						}











	}

}
