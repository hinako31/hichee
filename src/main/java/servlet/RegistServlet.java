package servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.User;
import service.RegistLogic;


@WebServlet("/Regist")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegistLogic registLogic = new RegistLogic();
	
    public RegistServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String check = request.getParameter("check");
		String back = request.getParameter("back");
		HttpSession session = request.getSession();
		
		//戻るボタンを押したらトップに戻る
		if("戻る".equals(back)) {
			  response.sendRedirect(request.getContextPath() + "/index.jsp");
			    return;
		}
		//確認ボタンを押した時
		else if("確認".equals(check)) {
			//入力した名前、メールアドレス、パスワードを取得
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String pass = request.getParameter("pass");
			String confirmPass = request.getParameter("confirmPass");

			// 1. 入力チェック
        		//未入力の場合
			if (name == null || name.isEmpty() ||
					email == null || email.isEmpty() ||
					pass == null || pass.isEmpty() ||
					confirmPass == null || confirmPass.isEmpty()) {
					request.setAttribute("error", "未入力項目があります。");
					request.setAttribute("name", name);
					request.setAttribute("email", email);
					request.setAttribute("pass", pass);
					request.setAttribute("confirmPass","");
					request.getRequestDispatcher("/WEB-INF/jsp/user/regist.jsp").forward(request, response);
					return;
				}
			//パスワードの文字が８文字未満の場合
			if (pass.length() < 4) {
					request.setAttribute("error", "パスワードは4文字以上で設定してください。");
					request.setAttribute("name", name);
				    request.setAttribute("email", email);
					request.getRequestDispatcher("/WEB-INF/jsp/user/regist.jsp").forward(request, response);
					return;
				}
				//パスワードと確認用のパスワードの不一致
			 if (!pass.equals(confirmPass)) {
					request.setAttribute("error", "パスワードが一致しません。");
					request.setAttribute("name", name);
					request.setAttribute("email", email);
					request.setAttribute("pass", pass);
					request.setAttribute("confirmPass",""); // ← これを追加！
					request.getRequestDispatcher("/WEB-INF/jsp/user/regist.jsp").forward(request, response);
					return;
				}//登録するユーザー情報を設定
				User user = new User(name, email, pass);

				//セッションスコープに登録ユーザーを保存
				session.setAttribute("tentative", user);
				//フォワード

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/registCheck.jsp");
				dispatcher.forward(request, response);
			}
		
		//登録確認画面からのPost
		User user = (User) session.getAttribute("tentative");
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
			return;
		}
		
		// --------------------------------
				// ② 「戻る」ボタン押下
				// --------------------------------
				String back2=request.getParameter("back2");
				String go=request.getParameter("go");
				if ("戻る".equals(back2)) {
					request.setAttribute("name", user.getName());
					request.setAttribute("email", user.getEmail());
					request.setAttribute("pass", "");//入力値は保持しない
					request.setAttribute("confirmPass", "");
					//フォワード
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/regist.jsp");
					dispatcher.forward(request, response);
				}
				// --------------------------------
				// ③ 「登録する」ボタン押下
				// --------------------------------
				if ("会員登録".equals(go)) {
					try {
						boolean success = registLogic.register(user);
						if (success) {//会員登録情報をDBに無事追加できたら
							// 登録成功 → セッションにログインユーザー保存
							session.setAttribute("user", user);
							session.removeAttribute("tentative");//仮データは削除
							//フォワード
							RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/registResult.jsp");
							dispatcher.forward(request, response);
						} else {
							// メールアドレス重複など
							request.setAttribute("error", "このメールアドレスは既に登録されています。");
							request.setAttribute("name", user.getName());
							request.setAttribute("email", user.getEmail());
							//フォワード
							RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/regist.jsp");
							dispatcher.forward(request, response);
						}
					} catch (SQLException e) {
						throw new ServletException("登録中にエラーが発生しました", e);
					}
				}
			}
}
