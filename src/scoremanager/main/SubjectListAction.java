package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class SubjectListAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
		//セッションからユーザデータの値を取得

		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");


		boolean isAttend = false;// 在学フラグ
		List<Student> students = null;// 学生リスト
		LocalDate todaysDate = LocalDate.now();// LocalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得
		SubjectDao sDao = new SubjectDao();//学生Dao
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		Map<String, String>errors = new HashMap<>();// エラーメッセージ

		//DBからデータの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());

		if {
			//指定なしの場合
			//全学生情報を取得
			subject = sDao.filter(teacher.getSchool(), isAttend );
		}else {
			errors.put("f1", "クラスを指定する場合は入学年度も指定ください。");
			req.setAttribute("errors", errors);
			// 全学生情報を取得
			students = sDao.filter(teacher.getSchool(), isAttend);
		}


		//ビジネスロック 4(絞り込みプルダウン用)
		if (entYearStr != null) {
			//数値に変換
			entYear = Integer.parseInt(entYearStr);
		}
		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 1; i++ ) {
			entYearSet.add(i);
		}


		//レスポンス値をセット 6
		//リクエストに入学年度をセット
		req.setAttribute("f1", entYear);
		//リクエストにクラス番号をセット
		req.setAttribute("f2", classNum);
		//在学フラグが送信されていた場合
		if (isAttendStr != null) {
			// 在学フラグを立てる
			isAttend = true;
			// リクエストに在学フラグをセット
			req.setAttribute("f2", isAttendStr);
		}
		// リクエストに学生リストをセット
		req.setAttribute("students", students);
		// リクエストにデータをセット
		req.setAttribute("class_num_set", list);
		req.setAttribute("ent_year_set", entYearSet);

		// JSPへフォワード 7
		req.getRequestDispatcher("student_list.jsp").forward(req, res);
	}
	}
