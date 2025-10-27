package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.User;
import service.DiaryLogic;
import service.UserLogic;


@WebServlet("/Resign")
public class ResignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ResignServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//マイページの退会ボタン 
				// セッション取得
				
				  HttpSession session = request.getSession(false); // falseにしておくと新規作成されない
		      if (session != null) {
		              
		          // セッションスコープから会員情報を取得
		          User user = (User) session.getAttribute("user");
		          if (user != null) {
		          request.setAttribute("user", user);
		          RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/resignCheck.jsp");
		          dispatcher.forward(request, response);
		          return;
		          }
		      }
	}	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        //本当に退会する場合
	 HttpSession session = request.getSession(false);

      if (session == null || session.getAttribute("user") == null) {
          response.sendRedirect("index.jsp");
          return;
      }
                
	            User user = (User) session.getAttribute("user");
	            int userId=user.getId();
	            
	            //diaryの削除の指示
		        DiaryLogic diarylogic=new DiaryLogic();
	        	boolean diaryDeleted = diarylogic.resigndeleteDiary(userId);

	        //User情報の削除の指示
          	UserLogic userlogic=new UserLogic();
          	boolean userDeleted=userlogic.withdrawUser(userId);
              
          
          	//どちらも削除成功したらセッションスコープの削除と完了画面に遷移
          	if(diaryDeleted || userDeleted ) {
          		session.invalidate();
          		 RequestDispatcher dispatcher  = request.getRequestDispatcher("/WEB-INF/jsp/user/resignResult.jsp");
                  dispatcher.forward(request, response);
          }
      }

}
