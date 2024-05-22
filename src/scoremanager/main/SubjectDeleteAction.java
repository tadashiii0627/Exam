package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//DBから科目の詳細を取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		SubjectDao subjectdao = new SubjectDao();// 科目Daoを初期化

		String cd = req.getParameter("no");
		//String schoolCd = teacher.getSchool().getCd();

		Subject subject = subjectdao.get(cd, teacher.getSchool());
			//JSPへフォワード
			req.getRequestDispatcher("subject_delete.jsp").forward(req,res);
	}
}