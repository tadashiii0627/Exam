package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

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
					Subject subject = new Subject();
					subject.getCd(cd);

					subject.setSchool(school);

					SubjectDao sDao= new SubjectDao();
					sDao.save(subject);
					// 登録完了！！

					// JSPへフォワード 7
					req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);

				}
		}
