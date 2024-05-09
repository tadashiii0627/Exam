package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//セッションからユーザデータの値を取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute("user");

		// TODO 自動生成されたメソッド・スタブ
					String subject_cd=(req.getParameter("cd"));
					String subject_name=req.getParameter("name");
					School school=teacher.getSchool();

					// beanであるstudentにstudent_create.jspからの取得情報(4つ)を格納
					// 取得されないがDBにあるIS_ATTEND,SCHOOL_CDは
					Subject subject = new Subject();
					subject.setCd(subject_cd);
					subject.setSchool(school);
					subject.setName(subject_name);

					SubjectDao sDao= new SubjectDao();
	}
}