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

/**
 * Servlet implementation class ChangeProfileServlet
 */
@WebServlet("/ChangeProfile")
public class ChangeProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegistLogic registLogic = new RegistLogic();

    public ChangeProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//myAccount.jspで変更ボタンを押した時の処理
			// リクエストパラメータの取得
			request.setCharacterEncoding("UTF-8");
			// セッション取得
			HttpSession session = request.getSession(false); // falseにしておくと新規作成されない

			if (session != null) {
				// セッションスコープから会員情報を取得
				User user = (User) session.getAttribute("user");
				if (user != null) {
					request.setAttribute("tentative", user);//仮セッションスコープとしてユーザー情報を保管
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/changeProfile.jsp");
					dispatcher.forward(request, response);
					return;
				}
			}
			
		}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		         request.setCharacterEncoding("UTF-8");
		 // セッション取得
				HttpSession session = request.getSession();
				String action= request.getParameter("action");
				String step = request.getParameter("step");
	    //入力画面のボタンの分岐
				//戻るボタンを押した時
				if ("戻る".equals(action)) {
					request.getRequestDispatcher("/WEB-INF/jsp/user/myAccount.jsp").forward(request, response);
					return;
				}
		// ① フォーム送信時
		if ("確認".equals(action)) {
			System.out.println(12);
			//入力した名前、メールアドレス、パスワードを取得
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String pass = request.getParameter("pass");
			String confirmPass = request.getParameter("confirmPass");
			User user = (User) session.getAttribute("user");
				// 1. 入力チェック
				//名前かemailが未入力の場合
				if (name == null || name.isEmpty() ||
						email == null || email.isEmpty() ) {
					
					request.setAttribute("error", "未入力項目があります。");
					User tentative = new User();
				    tentative.setId(user.getId());
				    tentative.setName(name);
				    tentative.setEmail(email);
				    tentative.setPass(pass);
				    tentative.setConfirmPass(confirmPass);
				    session.setAttribute("tentative", tentative);
					request.getRequestDispatcher("/WEB-INF/jsp/user/changeProfile.jsp").forward(request, response);
					return;
				}
				
				//パスワードが入力されて、かつ4文字未満の場合
				if (pass !=null && pass.length() < 4) {
					System.out.println(1234);
					request.setAttribute("error", "パスワードは4文字以上で設定してください。");
					 User tentative = new User(user.getId(),name, email, pass);  // ID=0で仮登録
					    session.setAttribute("tentative", tentative);
					request.getRequestDispatcher("/WEB-INF/jsp/user/changeProfile.jsp").forward(request, response);
					return;
				}
				//パスワードと確認用のパスワードの不一致
				if (pass !=null && !pass.equals(confirmPass)) {
					System.out.println(12345);
					request.setAttribute("error", "パスワードが一致しません。");
					 User tentative = new User(user.getId(),name, email, pass);  // ID=0, isAdmin=0 で仮登録
					    session.setAttribute("tentative", tentative);
					request.getRequestDispatcher("/WEB-INF/jsp/user/changeProfile.jsp").forward(request, response);
					return;
				}
				
				
				
				//登録するユーザー情報を設定
				User tentative = new User();
				System.out.println("tentative name: " + tentative.getName());
				System.out.println("tentative email: " + tentative.getEmail());
				System.out.println("tentative pass: " + tentative.getPass());
				tentative.setId(user.getId());
				tentative.setName(name);
				tentative.setEmail(email);
				tentative.setPass(pass);
				tentative.setConfirmPass(confirmPass);
				//セッションスコープに登録ユーザーを保存
				session.setAttribute("tentative", tentative);				

				//確認画面へフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/changeProfileCheck.jsp");
				dispatcher.forward(request, response);
				return;
			}
		
		
          
				User user = (User) session.getAttribute("user");
				if (user == null) {
					System.out.println(1212);
					response.sendRedirect(request.getContextPath() + "/index.jsp");
					return;
				}
							
			
			//確認画面のボタンの分岐	
				// ② 「戻る」ボタン押下
				if ("戻る".equals(step)) {
					User tentative = (User) session.getAttribute("tentative");
					request.setAttribute("tentative", tentative);
					//フォワード
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/changeProfile.jsp");
					dispatcher.forward(request, response);
					return;
				}
				
				// ③ 「変更する」ボタン押下
				if ("変更".equals(step)) {
					try {
						  // ✅ ここでtentativeを使うのが正解！
				        User tentative = (User) session.getAttribute("tentative");

				        // ✅ DAO呼び出しにもtentativeを使う
				        boolean success = registLogic.updateUser(tentative);

				        if (success) {
				            // ✅ セッション上のuserを上書き
				            session.setAttribute("user", tentative);

				            // ✅ tentativeは不要なので削除
				            session.removeAttribute("tentative");
							//フォワード
							RequestDispatcher dispatcher = request
									.getRequestDispatcher("/WEB-INF/jsp/user/changeProfileResult.jsp");
							dispatcher.forward(request, response);
						}
					} catch (SQLException e) {
						throw new ServletException("登録中にエラーが発生しました", e);
					}
				}	
				
	}

}
