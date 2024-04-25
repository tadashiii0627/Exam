package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
		//セッションからユーザデータの値を取得

		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");



		LocalDate todaysDate = LocalDate.now();// LocalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得
		SubjectDao sDao = new SubjectDao();//学生Dao
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		Map<String, String>errors = new HashMap<>();// エラーメッセージ

		//DBからデータの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());

			errors.put("f1", "クラスを指定する場合は入学年度も指定ください。");
			req.setAttribute("errors", errors);
			// 全学生情報を取得
			Subject subject = sDao.filter(teacher.getSchool());



		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 1; i++ ) {
			entYearSet.add(i);
		}


		//レスポンス値をセット 6
		// リクエストに学生リストをセット
		req.setAttribute("subject", subject);
		// リクエストにデータをセット
		req.setAttribute("class_num_set", list);
		req.setAttribute("ent_year_set", entYearSet);

		// JSPへフォワード 7
		req.getRequestDispatcher("subject_list.jsp").forward(req, res);
	}
	}
