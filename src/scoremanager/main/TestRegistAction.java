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
import dao.TestListStudentDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestRegistAction extends Action {

	@Override
	public  void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
		//セッションからユーザデータの値を取得
			HttpSession session = req.getSession();
			Teacher teacher = (Teacher)session.getAttribute("user");
			ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化

			//DBからデータの学校コードをもとにクラス番号の一覧を取得
			List<String> list = cNumDao.filter(teacher.getSchool());

			setTestListSubject(req,res);
	}
	private void setTestListSubject(HttpServletRequest req, HttpServletResponse res)throws Exception{

		//セッションからユーザデータの値を取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		List<TestListSubject> testlistsubjects= null;
		TestListStudentDao testliststudentdao = new TestListStudentDao();
		TestListSubjectDao testlistsubjectdao = new TestListSubjectDao();
		LocalDate todaysDate = LocalDate.now();// LocalDateインスタンスを取得
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		SubjectDao sDao = new SubjectDao();//科目Dao
		int year = todaysDate.getYear();// 現在の年を取得
		int entYear = 0;// 入学年度
		Subject subject = new Subject();
		String subject_cd = null;// bean.subjectからCdを取得
		String classNum = null;//bean.subjectからnameを取得
		String entYearStr = null;//bean.subjectからschoolを取得

		//リクエストパラメーターの取得
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		subject_cd = req.getParameter("f3");
		subject.setCd(subject_cd);

		//DBからデータの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());

		// 科目情報を取得
		List<Subject> list1 = sDao.filter(teacher.getSchool());

		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();

		// 10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 1; i++ ) {
			entYearSet.add(i);
		}

		// リストを初期化
		List<Integer> numList = new ArrayList<>();
		numList.add(1);
		numList.add(2);


		if (entYearStr != null) {
			//数値に変換
			entYear = Integer.parseInt(entYearStr);
		}
		testlistsubjects = testlistsubjectdao.filter(entYear, classNum, subject, teacher.getSchool());

		// リクエストにデータをセット
		req.setAttribute("class_subjectcd_set",list1);
		req.setAttribute("class_num_set", list);
		req.setAttribute("ent_year_set", entYearSet);
		req.setAttribute("num", numList);

		//JSPへフォワード
		req.getRequestDispatcher("test_regist.jsp").forward(req,res);
	}

	private void setTestListStudent(HttpServletRequest req, HttpServletResponse res)throws Exception{
		//セッションからユーザデータの値を取得
				HttpSession session = req.getSession();
				Teacher teacher = (Teacher)session.getAttribute("user");

				String entYearStr=null;// 入力された入学年度
				String classNum =null;// 入力されたクラス番号
				String subject_cd = null;//入力された科目コード
				int entYear = 0;// 入学年度
				String numStr= null;
				int num = 0;
				List<TestListSubject> testlistsubjects= null;
				TestListSubjectDao testlistsubjectdao = new TestListSubjectDao();
				TestListStudentDao testliststudentdao = new TestListStudentDao();
				ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
				Map<String, String>errors = new HashMap<>();// エラーメッセージ
				Subject subject = new Subject();

				//リクエストパラメーターの取得
				entYearStr = req.getParameter("f1");
				classNum = req.getParameter("f2");
				subject_cd = req.getParameter("f3");
				numStr = req.getParameter("f4");
				subject.setCd(subject_cd);

				if(entYearStr==null || classNum==null || subject_cd==null || numStr==null){
					errors.put("f1", "入学年度とクラスと科目と回数を選択してください。");
					req.setAttribute("errors", errors);
				}else{
					if (entYearStr != null) {
						//数値に変換
						entYear = Integer.parseInt(entYearStr);
						num = Integer.parseInt(numStr);
					}testlistsubjects = testlistsubjectdao.filter(entYear, classNum, subject,teacher.getSchool());
				}

				//DBからデータの学校コードをもとにクラス番号の一覧を取得
				List<String> list = cNumDao.filter(teacher.getSchool());

				// リクエストにデータをセット
				req.setAttribute("ent_year_set", entYear);
				req.setAttribute("class_num_set", classNum);
				req.setAttribute("class_subjectcd_set", subject);
				req.setAttribute("num", num);

				//JSPへフォワード
				req.getRequestDispatcher("test_regist.jsp").forward(req,res);
			}
}