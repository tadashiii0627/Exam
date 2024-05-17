package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao{
	private String baseSql = "select * from test where =?";

	public Test get(Student student, Subject subject, School school, int no) throws Exception{
		//得点インスタンスを初期化
		Test test = new Test();
		//データベースのコネクションの確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		try {
			// プリペアードステートメントにSQL文をセット
			//getメソッドは1つのデータを取り出すものだから、SQLは1つだけ
			statement = connection.prepareStatement("select * from Test where no=?");
			// プリペアードステートメントに学生番号をバインド(1)
			statement.setString(1, student.getNo());

			// プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			if (rSet.next()) {
				//リザルトセットが存在する場合
				//学生インスタンスに検索結果をセット
				student.setNo(rSet.getString("no"));
				student.setName(rSet.getString("name"));
				student.setEntYear(rSet.getInt("ent_year"));
				student.setClassNum(rSet.getString("class_num"));
				student.setName(rSet.getString("name"));
			} else {
				//リザルトセットが存在する場合
				//学生インスタンスに検索結果をセット
				student = null;
				}
			} catch (Exception e){
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
						connection.close();
					} catch (SQLException sqle) {
						throw sqle;
					}
				}
			}
	}
	private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
		
	}
	public List<Test> filter(int entYear ,String classNum , Subject subject , int num , School school) throws Exception {}
	public boolean save(List<Test> list) throws Exception {}
	private boolean save(Test test , Connection connection)throws Exception {}
	public boolean delete(List<Test> list)throws Exception {}
	private boolean delete  (Test test , Connection connection)throws Exception {}
}
