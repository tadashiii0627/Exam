package dao;

//SQLと連携コネクション
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {
	//＜＜get メソッド＞＞
	public Subject get(String cd, School school) throws Exception {
		// 科目インスタンスを初期化
		Subject subject = new Subject();
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
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
				// 学校のフィールドには学校コードで検索した学校インスタンスをセット
				subject.setSchool(schoolDao.get(rSet.getString("school")));
			} else {
				//リザルトセットが存在しない場合
				// 学生インスタンスにnullをセット
				subject = null;
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
		return subject;
	}

	// ＜＜filterメソッド＞＞
    // ＜＜Subjectはbeanが完成しだいエラー消える＞＞
	public List<Subject> filter(School school) throws Exception {

		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;

		// SQL文のソート
		String order = " order by cd asc";

		// リストを初期化
		List<Subject> list = new ArrayList<>();

		try {
			// プリペアードステートメントのSQL文をセット
			statement = connection.prepareStatement("select * from subject where school_cd=?"+ order);
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			// プリペアードステートメントを実行
			rSet = statement.executeQuery();
			// リストへの格納処理を実行 <いるのか?>

						// リザルトセットを完全走査
						while (rSet.next()){
							// 学生インスタンスを初期化
							Subject subject = new Subject();
							// 学生インスタンスに検索結果をセット
							subject.setCd(rSet.getString("school_cd"));

							// リストに追加
							list.add(subject);
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

	// ＜＜saveメソッド＞＞
	public boolean save(Subject subject) throws Exception {
	// コネクションを確立
	Connection connection = getConnection();
	// プリペアードステートメント
	PreparedStatement statement = null;
	// 実行件数
	int count = 0;


	try {
		// データベースから学生を取得
		// getメソッドの引き数(2つ)指定
		Subject old = get(subject.getCd(), subject.getSchool());
		if (old == null) {
			// 学生が存在しなかった場合
			// プリペアードステートメントにINSERT文をセット
			statement = connection.prepareStatement(
					"insert into subject(school_cd, cd, name) values(?, ?, ?)");
			// プリペアードステートメントに値をバインド
			statement.setString(1, subject.getSchool().getCd());
			statement.setString(2, subject.getCd());
			statement.setString(3, subject.getName());

			// プリペアードステートメントを実行!!!
			count = statement.executeUpdate();

		} else {
			// 学生が存在した場合
			// プリペアードステートメントにUPDATE文をセット
			statement = connection
					.prepareStatement("update subject set school_cd=?, cd=?, name=?");
			//プリペアードステートメントに値をバインド
			statement.setString(1, subject.getSchool().getCd());
			statement.setString(2, subject.getCd());
			statement.setString(3, subject.getName());

			// プリペアードステートメントを実行!!!
			count = statement.executeUpdate();

		}
		}catch (Exception e) {
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


	//＜＜deleteメソッド＞＞
	public boolean delete(Subject subject) throws Exception {
	// コネクションを確立
	Connection connection = getConnection();
	// プリペアードステートメント
	PreparedStatement statement = null;
	// 実行件数
	int count = 0;


	try {
		// データベースから学生を取得
		// getメソッドの引き数(2つ)指定
		Subject old = get(subject.getCd(), subject.getSchool());
		if (old == null) {
			// 学生が存在しなかった場合
			// プリペアードステートメントにDELETE文をセット
			statement = connection.prepareStatement(
					"delete from subject where school_cd=?, cd=?");
			// プリペアードステートメントに値をバインド
			statement.setString(1, subject.getSchool().getCd());
			statement.setString(2, subject.getCd());


			// プリペアードステートメントを実行!!!
			count = statement.executeUpdate();

		} else {
			// 科目が存在した場合
			// プリペアードステートメントにDELETE文をセット
			statement = connection
					.prepareStatement("update subject set school_cd=?, cd=?");
			//プリペアードステートメントに値をバインド
			statement.setString(1, subject.getSchool().getCd());
			statement.setString(2, subject.getCd());

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






