package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public LogoutServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//MyAccountのログアウトボタンを押した時
		// リクエストパラメータの取得
        request.setCharacterEncoding("UTF-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/logoutCheck.jsp");
        dispatcher.forward(request, response);	
	}

//ログアウト確認画面の分岐	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String logout= request.getParameter("logout");
	       String top= request.getParameter("top");
	       //ログアウトボタンを押した時
	       if("ログアウト".equals(logout)) {
	    	   // セッションを取得
	           HttpSession session = request.getSession(false); // false: セッションがなければ null を返す
	         
	           if (session != null) {
	               // セッションを無効化
	               session.invalidate();
	               //ログアウト完了画面へ
	               RequestDispatcher dispatcher1 = request.getRequestDispatcher("/WEB-INF/jsp/user/logoutResult.jsp");
	       		   dispatcher1.forward(request, response);
	             
	           }
	       }
	       
	       //完了画面のトップページへボタンを押した時
	       if("トップページへ".equals(top)) {
	           // topページへリダイレクト
	           response.sendRedirect("index.jsp");
	           
	           }
	      }
}
