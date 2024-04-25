package scoremanager.main;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubjectCreateAction {
	public void execute(HttpServletRequest request, HttpServletResponse response)throws Exception{
		request.getRequestDispatcher("subject_create.jsp").forward(request,response);
	}
}
