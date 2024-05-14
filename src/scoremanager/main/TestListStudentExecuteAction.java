package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//セッションからユーザデータの値を取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		String student_no=(req.getParameter("no"));
		String student_name=(req.getParameter("name"));
		int student_entYear=Integer.parseInt(req.getParameter("ent_year"));
		String student_classNum=(req.getParameter("classNum"));

		School school=teacher.getSchool();

		Student student = new Student();
		student.setNo(student_no);
		student.setName(student_name);
		student.setEntYear(student_entYear);
		student.setClassNum(student_classNum);
		student.setSchool(school);

		StudentDao sDao= new StudentDao();
	}
}
