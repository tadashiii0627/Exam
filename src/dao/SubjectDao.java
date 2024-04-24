package dao;

//SQLと連携コネクション
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class SubjectDao extends Dao {
	//＜＜get メソッド＞＞
	public Student get(String cd, School school) throws Exception {
		// 学生インスタンスを初期化
		Student student = new Student();
		// データベースへのコネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from subject where school_cd = ? and cd = ?");
			// プリペアードステートメントに学生番号をバインド(2つ)
			statement.setString(1, school.getCd());
			statement.setString(2, cd);

			// プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			// 学校Daoを初期化
			SchoolDao schoolDao = new SchoolDao();

			if (rSet.next()) {
				// リザルトセットが存在する場合
				// 学生インスタンスに検索結果(Cd, Name, School)をセット
				student.setNo(rSet.getString("cd"));
				student.setName(rSet.getString("name"));
				// 学校のフィールドには学校コードで検索した学校インスタンスをセット
				student.setSchool(schoolDao.get(rSet.getString("school")));
			} else {
				//リザルトセットが存在しない場合
				// 学生インスタンスにnullをセット
				student = null;
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
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return student;

	// ＜＜filterメソッド＞＞
	// ＜＜Subjectはbeanが完成しだいエラー消える＞＞
	public List<Subject> filter(School school) throws Exception {
		// リストを初期化
		List<Student> list = new ArrayList<>();
		try {
			// リザルトセットを完全走査
			while (school.next()){
				// 学生インスタンスを初期化
				Student student = new Student();
				// 学生インスタンスに検索結果をセット
				student.setSchool(school);
				// リストに追加
				list.add(student);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;

    }




	// ＜＜saveメソッド＞＞
	public boolean save(Student student) throws Exception {
	// コネクションを確立
	Connection connection = getConnection();
	// プリペアードステートメント
	PreparedStatement statement = null;
	// 実行件数
	int count = 0;


	try {
		// データベースから学生を取得
		Student old = get(student.getNo());
		if (old == null) {
			// 学生が存在しなかった場合
			// プリペアードステートメントにINSERT文をセット
			statement = connection.prepareStatement(
					"insert into student(school_cd, cd, name) values(?, ?, ?)");
			// プリペアードステートメントに値をバインド
			statement.setInt(1, student.getSchool());
			statement.setString(2, student.getNo());
			statement.setString(3, student.getName());

			// プリペアードステートメントを実行!!!
			count = statement.executeUpdate();

		} else {
			// 学生が存在した場合
			// プリペアードステートメントにUPDATE文をセット
			statement = connection
					.prepareStatement("update student set name=?, ent_year=?, class_num=?, is_attend=? where no=?");
			//プリペアードステートメントに値をバインド
			statement.setString(1, student.getName());
			statement.setInt(2, student.getEntYear());
			statement.setString(3, student.getClassNum());

			// プリペアードステートメントを実行!!!
			count = statement.executeUpdate();

		} }catch (Exception e) {
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
		if (count > 0) {
			// 実行件数が１件以上ある場合
			return true;
		} else {
			// 実行件数が0件の場合
			return false;
		}

	}
}





