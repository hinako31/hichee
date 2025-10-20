package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Diary;
import model.LoginUser;
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
            // ログイン処理    
        	// 空欄の場合	
        	if (email == null || email.isEmpty() || pass == null || pass.isEmpty()) {
                request.setAttribute("error", "メールアドレスまたはパスワードが誤っています。");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
                return;
        		}
        	
        	//空欄ではない場合
        	LoginUser loginUser = new LoginUser(email,pass);//入力した値を代入
    		LoginLogic bo = new LoginLogic();
    		User user = bo.execute(loginUser);
    		//      入力したemail、passが存在してない→user=null
            //      存在する→user=（DBに保存されている）id,name,email,passが代入 
    		
    		//一致した場合
    		if(user != null) {
   			HttpSession session = request.getSession();
    			session.setAttribute("user", user);
    			//userセッションスコープにログインユーザー情報を保存する
    			List<Diary> diaryList = bo.getDiaryList(user.getId());
                session.setAttribute("diaryList", diaryList);
                
                //ユーザー情報とdiary情報を取得してセッションスコープに保存後
                //マイページに遷移
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/mypage.jsp");
                dispatcher.forward(request, response);
         
	          }
    		
           }
      //もし会員登録ボタンを押していたら会員登録ページに遷移
        else if ("regist".equals(action)) {
			 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/regist.jsp");
             dispatcher.forward(request, response);
             System.out.println(1);
             return; 
      
          }
        //検索画面、新規日記登録完了画面からマイページに遷移する
        if ("My Pageへ".equals(mypage)) {
        	  RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/mypage.jsp");
              dispatcher.forward(request, response);
	    }
   }
}
