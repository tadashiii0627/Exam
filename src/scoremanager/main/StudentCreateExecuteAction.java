package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

		//入学年度,学生番号,氏名,クラスをstudent_create.jspから受け取る

		@Override
		public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
			//セッションからユーザデータの値を取得
			HttpSession session = req.getSession();
			Teacher teacher = (Teacher)session.getAttribute("user");

			// TODO 自動生成されたメソッド・スタブ
			int entyear=Integer.parseInt(req.getParameter("ent_year"));
			String no=req.getParameter("no");
			String name=req.getParameter("name");
			String class_num=req.getParameter("class_num");

			boolean isAttend=true;
			School school=teacher.getSchool();

			// beanであるstudentにstudent_create.jspからの取得情報(4つ)を格納
			// 取得されないがDBにあるIS_ATTEND,SCHOOL_CDは
			Student student = new Student();
			student.setEntYear(entyear);
			student.setNo(no);
			student.setName(name);
			student.setClassNum(class_num);

			student.setAttend(isAttend);
			student.setSchool(school);

			StudentDao sDao= new StudentDao();
			sDao.save(student);
			// 登録完了！！

			// JSPへフォワード 7
			req.getRequestDispatcher("student_create_done.jsp").forward(req, res);

		}
}

