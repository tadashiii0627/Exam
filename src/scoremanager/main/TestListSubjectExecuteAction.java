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
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//セッションからユーザデータの値を取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		String entYearStr=null;// 入力された入学年度
		String classNum =null;// 入力されたクラス番号
		String subject_cd = null;//入力された科目コード
		int entYear = 0;// 入学年度
		List<TestListSubject> testlistsubjects= null;
		TestListSubjectDao testlistsubjectdao = new TestListSubjectDao();
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		Map<String, String>errors = new HashMap<>();// エラーメッセージ
		//DBからデータの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());
		Subject subject = new Subject();
		SubjectDao sDao = new SubjectDao();//科目Dao
		LocalDate todaysDate = LocalDate.now();// LocalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得

		//リクエストパラメーターの取得
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		subject_cd = req.getParameter("f3");
		subject.setCd(subject_cd);

		if(entYearStr.equals("0") || classNum.equals("0") || subject.equals("0")){
			errors.put("f1", "入学年度とクラスと科目を選択してください。");
			req.setAttribute("errors", errors);
		}else{
			if (entYearStr != null) {
				//数値に変換
				entYear = Integer.parseInt(entYearStr);
			}
			testlistsubjects = testlistsubjectdao.filter(entYear, classNum, subject, teacher.getSchool());
		}

		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();

		// 10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 1; i++ ) {
			entYearSet.add(i);
		}
		// 科目情報を取得
				List<Subject> list1 = sDao.filter(teacher.getSchool());
		// リクエストに学生リストをセット
				req.setAttribute("testlistsubjects", testlistsubjects);
				// リクエストにデータをセット
				req.setAttribute("class_num_set", list);
				req.setAttribute("ent_year_set", entYearSet);
				req.setAttribute("class_subjectcd_set",list1);
				req.setAttribute("f1", entYear);
				req.setAttribute("f2", classNum);
				req.setAttribute("f3", subject_cd);
				//一覧で表示
				req.setAttribute("test.student.entYear", year);
				req.setAttribute("students", classNum);
				//JSPへフォワード
				req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);

	}
}