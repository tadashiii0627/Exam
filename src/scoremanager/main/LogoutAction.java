package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		HttpSession session = req.getSession();
		//ローカル変数の宣言 1
		//なし
		//リクエストパラメータ―の取得 2-
		//なし
		//DBからデータ取得 3
		//なし
		//ビジネスロジック 4
		//なし
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		//なし
		//JSPへフォワード 7


		//P388
		if (session.getAttribute("user")!=null) {
			session.invalidate();

		}
		req.getRequestDispatcher("logout.jsp").forward(req, res);
	}





}
