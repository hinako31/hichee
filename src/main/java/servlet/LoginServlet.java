package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Diary;
import model.User;
import service.LoginLogic;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public LoginServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		    dispatcher.forward(request, response);
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   // リクエストパラメータの取得
        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String action = request.getParameter("action");
        String mypage =request.getParameter("mypage");
     	
        if ("login".equals(action)) {
            // 未入力チェック
            if (email == null || email.isEmpty() || pass == null || pass.isEmpty()) {
                request.setAttribute("error", "メールアドレスまたはパスワードが未入力です。");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                return;
            }

            try {
                LoginLogic loginLogic = new LoginLogic();
                User user = loginLogic.login(email, pass);

                if (user == null) {
                    // ユーザーが存在しない
                    request.setAttribute("error", "このメールアドレスは会員登録されていません。会員登録してください。");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    return;
                }

                if ("INVALID".equals(user.getPass())) {
                    // パスワードが違う
                    request.setAttribute("error", "パスワードが違います。");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    return;
                }

                // ログイン成功
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                List<Diary> diaryList = loginLogic.getDiaryList(user.getId());
                session.setAttribute("diaryList", diaryList);

                request.getRequestDispatcher("/WEB-INF/jsp/user/mypage.jsp").forward(request, response);

            } catch (SQLException e) {
                throw new ServletException("ログイン処理中にエラーが発生しました", e);
            }
        }
    
      //もし会員登録ボタンを押していたら会員登録ページに遷移
         if ("regist".equals(action)) {
        	 System.out.println(4);
			 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/regist.jsp");
             dispatcher.forward(request, response);
            
             return; 
      
          }
        //検索画面、新規日記登録完了画面からマイページに遷移する
        if ("My Pageへ".equals(mypage)) {
        	 System.out.println(5);
        	  RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/mypage.jsp");
              dispatcher.forward(request, response);
	    }
   }
}
