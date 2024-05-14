package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import tool.Action;

public  class TestListAction extends Action{
	@Override
	public  void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
	//セッションからユーザデータの値を取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");
		String entYearStr="";// 入力された入学年度
		String classNum = "";// 入力されたクラス番号
		String isAttendStr = "";// 入力された在学フラグ
		int entYear = 0;// 入学年度
		boolean isAttend = false;// 在学フラグ
		List<Student> students = null;// 学生リスト
		LocalDate todaysDate = LocalDate.now();// LocalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得
		StudentDao sDao = new StudentDao();//学生Dao
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		Map<String, String>errors = new HashMap<>();// エラーメッセージ
	//DBからデータの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());

	//リクエストパラメーターの取得
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		isAttendStr = req.getParameter("f3");
	}
	private void setTestListSubject(HttpServletRequest req, HttpServletResponse res)throws Exception{
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		//セッションからユーザデータの値を取得
		String cd = null;// bean.subjectからCdを取得
		String name = null;//bean.subjectからnameを取得
		School school = null;//bean.subjectからschoolを取得

		//リクエストパラメーターの取得
		cd = req.getParameter("f1");
		name = req.getParameter("f2");
		school = req.getParameter("f3");


		//レスポンス値をセット 6
		// リクエストに学生リストをセット
		req.setAttribute("subject", list1);
		// リクエストにデータをセット
		req.setAttribute("class_num_set", list1);
		req.setAttribute("ent_year_set", entYearSet);

		//JSPへフォワード
		req.getRequestDispatcher("test_list.jsp").forward(req,res);
	}

	private void setTestListStudent(HttpServletRequest req, HttpServletResponse res)throws Exception{
		//セッションからユーザデータの値を取得
				HttpSession session = req.getSession();
				Teacher teacher = (Teacher)session.getAttribute("user");

				//セッションからユーザデータの値を取得
				String cd = null;// bean.subjectからCdを取得
				String name = null;//bean.subjectからnameを取得
				School school = null;//bean.subjectからschoolを取得

				//リクエストパラメーターの取得
				cd = req.getParameter("f1");
				name = req.getParameter("f2");
				school = req.getParameter("f3");



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
				req.getRequestDispatcher("test_list.jsp").forward(req, res);
			}
}