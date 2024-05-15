package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		String num = req.getParameter("num");
		String name = req.getParameter("name");

					// beanであるstudentにstudent_update.jspからの取得情報(4つ)を格納
					// 取得されないがDBにあるIS_ATTEND,SCHOOL_CDは
					Student student = new Student();
					student.setNo(num);
					student.setSchool(teacher.getSchool());
					student.setName(name);

					StudentDao stDao= new StudentDao();
					stDao.save(student);
					// 登録完了！！

					// JSPへフォワード 7
					req.getRequestDispatcher("student_update_done.jsp").forward(req, res);

}
}