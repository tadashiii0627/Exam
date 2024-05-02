package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bean.Student;
import bean.TestListStudent;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {
	private String baseSql = "select * from test where =?";

	// ＜postFilterメソッド＞
	private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {
		// リストを初期化
		List<TestListStudent> list = new ArrayList<>();
		try {
			// リザルトセットを完全走査
			while (rSet.next()){
				// 学生インスタンスを初期化

				TestListSubject testlistsubject = new TestListSubject ();
				// 学生インスタンスに検索結果をセット
				testlistsubject.setEntYear(rSet.getInt("ent_year"));
				testlistsubject.setStudentNo(rSet.getString("student_no"));
				testlistsubject.setStudentName(rSet.getString("student_name"));
				testlistsubject.setClassNum(rSet.getString("class_num"));

				// 書き方分からない！！！！！！
				testlistsubject.setPoints(Map<Integer,Integer>(rSet.getString("points"));
				testlistsubject.putPoint(rSet.getInt("key"));
				// リストに追加
				list.add(testlistsubject);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;

	}







}
