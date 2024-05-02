package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		// TODO 自動生成されたメソッド・スタブ

					// beanであるstudentにstudent_create.jspからの取得情報(4つ)を格納
					// 取得されないがDBにあるIS_ATTEND,SCHOOL_CDは
					Subject subject = new Subject();
					subject.getCd();
					subject.getSchool();
					subject.getName();

					SubjectDao sDao= new SubjectDao();
					sDao.save(subject);
					// 登録完了！！

					// JSPへフォワード 7
					req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);

}
}