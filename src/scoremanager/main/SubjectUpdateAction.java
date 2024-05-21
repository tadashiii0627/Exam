package scoremanager.main;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		//DBから科目の詳細を取得
				HttpSession session = request.getSession();
				Teacher teacher = (Teacher)session.getAttribute("user");

				SubjectDao subjectdao = new SubjectDao();// 科目Daoを初期化

				String cd = request.getParameter("no");
				//String schoolCd = teacher.getSchool().getCd();

				Subject subject = subjectdao.get(cd, teacher.getSchool());
				request.setAttribute("code",cd );



		//JSPへフォワード
		request.getRequestDispatcher("subject_update.jsp").forward(request,response);
	}
}