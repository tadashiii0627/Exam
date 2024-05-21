package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
				test.setStudent(student);
				test.setSubject(subject);
				test.setSchool(school);
				test.setNo(no);
				test.setPoint(rSet.getInt("point"));
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
			}return test;
	}
	private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
		// リストを初期化
				List<Test> list = new ArrayList<>();
				try {
					// リザルトセットを完全走査
					while (rSet.next()){
						// 学生インスタンスを初期化
						Student student = new Student();
						// 学生インスタンスに検索結果をセット
						student.setNo(rSet.getString("no"));
						student.setName(rSet.getString("name"));
						student.setEntYear(rSet.getInt("ent_year"));
						student.setClassNum(rSet.getString("class_num"));
						student.setAttend(rSet.getBoolean("is_attend"));
						student.setSchool(school);


						//科目で同じことをする
						Subject subject = new Subject();
						subject.setCd(rSet.getString("cd"));
						subject.setName(rSet.getString("name"));

						//同様にTestで同じことをする。
						Test test = new Test();
						test.setStudent(student);
						test.setClassNum(rSet.getString("classNum"));
						test.setSubject(subject);
						test.setSchool(school);
						test.setNo(rSet.getInt("no"));
						test.setPoint(rSet.getInt("point"));

						//リストに追加
						list.add(test);

					}
				} catch (SQLException | NullPointerException e) {
					e.printStackTrace();
				}
				return list;
	}
	public List<Test> filter(int entYear ,String classNum , Subject subject , int num , School school) throws Exception {
		List<Test> list = new ArrayList<>();

		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rSet = null;

		//SQL文の条件
		String condition = "and Test.subject_cd=? and Test.no=?"
						+"where Student ent_Yesr=? and student class_Num=? and student.is_attend=true";
		//SQL文のソート
		String order = " order by student.no asc";

		try{
			statement = connection.prepareStatement(baseSql + condition + order);
			statement.setString(1,subject.getCd());
			statement.setInt(2,num);
			statement.setInt(3,entYear);
			statement.setString(4, classNum);
			statement.setString(5, school.getCd());
			rSet = statement.executeQuery();

			list = postFilter(rSet, school);
		} catch (Exception e){
			throw e;
		} finally {
			if (statement !=null){
				try{
					connection.close();
				} catch (SQLException sqle){
					throw sqle;
				}
			}
		} return list;
	}
	public boolean save(List<Test> list) throws Exception {
		Connection connection = getConnection();
		boolean canCommit = true;

		try{
			connection.setAutoCommit(false);
			for (Test test : list){
				canCommit = save(test, connection);
				if (!canCommit){
					break;
				}
			}

			if (canCommit) {
				connection.commit();

			}else {
				throw new Exception();
			}
		}catch (SQLException sqle){
			try{
				connection.rollback();
			}catch (SQLException e) {
				throw e;
			}
		} finally {
			if(connection != null){
				try{
					connection.setAutoCommit(true);
					connection.close();
				} catch (SQLException sqle){
					throw sqle;
				}
			}
		}return canCommit;
	}
	private boolean save(Test test , Connection connection)throws Exception {
		PreparedStatement statement = null;
		int count = 0;

		try{
			Test old = get(test.getStudent(), test.getSubject(), test.getSchool() , test.getNo());
			if (old == null){
				statement = connection.prepareStatement("insert into test(point, no, student_no , subject_cd , class.num) values(?,?,?,?,?,?)");
				statement.setInt(1,  test.getPoint());
				statement.setInt(2, test.getNo());
				statement.setString(3, test.getStudent().getNo());
				statement.setString(4, test.getSubject().getCd());
				statement.setString(5, test.getSchool().getCd());
				statement.setString(6, test.getStudent().getClassNum());
			}else {
				statement = connection.prepareStatement(
						"update test set point=? where no =? and student_no=? and subject_cd=? and school_cd=?");
				statement.setInt(1,  test.getPoint());
				statement.setInt(2, test.getNo());
				statement.setString(3, test.getStudent().getNo());
				statement.setString(4, test.getSubject().getCd());
				statement.setString(5, test.getSchool().getCd());
			}

			count = statement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			if (statement !=null){
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		if (count > 0){
			return true;
		}else {
			return false;
		}
	}
	public boolean delete(List<Test> list)throws Exception {
		return true;
	}
	private boolean delete  (Test test , Connection connection)throws Exception {
		return true;
	}
}
