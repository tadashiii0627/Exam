package scoremanager.main;

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

		//リクエストパラメーターの取得
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		subject_cd = req.getParameter("f3");
		subject.setCd(subject_cd);

		if(entYearStr==null || classNum==null || subject==null){
			errors.put("f1", "入学年度とクラスと科目を選択してください。");
			req.setAttribute("errors", errors);
		}else{
			if (entYearStr != null) {
				//数値に変換
				entYear = Integer.parseInt(entYearStr);
			}
			testlistsubjects = testlistsubjectdao.filter(entYear, classNum, subject, teacher.getSchool());
		}
		// リクエストに学生リストをセット
				req.setAttribute("testlistsubjects", testlistsubjects);
				// リクエストにデータをセット
				req.setAttribute("class_num_set", list);
				//req.setAttribute("ent_year_set", entYearSet);

				// JSPへフォワード 7
				req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);

	}
}