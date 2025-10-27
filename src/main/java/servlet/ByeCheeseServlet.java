package servlet;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import model.Diary;
import model.User;
import service.DiaryLogic;

/**
 * Servlet implementation class ByeCheeseServlet
 */
@WebServlet("/ByeCheese")
public class ByeCheeseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ByeCheeseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  // リクエストパラメータの取得
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        
        //詳細画面のByeCheeseを押した時
        if ("deleteConfirm".equals(action)) {
            String idStr = request.getParameter("id");
            int id = Integer.parseInt(idStr);

            HttpSession session = request.getSession(false);
            User user = (User) session.getAttribute("user");
            int userId = user.getId();

            DiaryLogic logic = new DiaryLogic();
            Diary diary = logic.getDiaryById(id, userId);

            if (diary == null) {
                request.setAttribute("errorMsg", "データが見つかりません。");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
                dispatcher.forward(request, response);
                return;
            }

            session.setAttribute("diary", diary); // ✅ JSPでsessionScope参照するため
            // 場所名や年月表示用の属性
            request.setAttribute("memorialYearDisplay", diary.getPeriod_year());
            request.setAttribute("memorialMonthDisplay", diary.getPeriod_month());
            request.setAttribute("areaName", "例: 東京"); // 実際はLogicなどで取得

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/byeCheeseCheck.jsp");
            dispatcher.forward(request, response);
        }
        
        
        //削除確認画面からの遷移
        String steps = request.getParameter("steps");
        if ("削除".equals(steps)) {
            // ② 実際に削除処理を行う
        	 // 確認画面からの分岐   
        	System.out.println("削除ボタンを押した");
        	 String idStr = request.getParameter("id");
             int id = Integer.parseInt(idStr);
        	HttpSession session = request.getSession(false);
        	 if (session == null) {
        	        // セッション切れ時の処理
        	        request.setAttribute("msg", "セッションが切れています。もう一度ログインしてください。");
        	        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        	        dispatcher.forward(request, response);
        	        return;
        	    }
            User user = (User) session.getAttribute("user");
            if (user == null) {
                request.setAttribute("msg", "ログイン情報が見つかりません。");
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
                return;
            }
            int userId=user.getId();
            
            //diaryの削除の指示
	        DiaryLogic diarylogic=new DiaryLogic();
        	boolean diaryDeleted = diarylogic.deleteDiary(id,userId);

        	 if (diaryDeleted) {
        	        System.out.println("削除成功");
        	        request.setAttribute("msg", "日記を削除しました。");
        	        session.removeAttribute("diary"); // invalidateより安全
        	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/byeCheeseResult.jsp");
        	        dispatcher.forward(request, response);
        	    } else {
        	        request.setAttribute("msg", "削除に失敗しました。");
        	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/error.jsp");
        	        dispatcher.forward(request, response);
        	    }
        	
        }
    }

	//postの外側
    // imgPartからファイル名を取り出す
     private String getFileName(Part part) {
     if (part == null) return null;
     String contentDisp = part.getHeader("content-disposition");
     if (contentDisp == null) return null;

     for (String cd : contentDisp.split(";")) {
         if (cd.trim().startsWith("filename")) {
            String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            return fileName.substring(fileName.lastIndexOf("\\") + 1);
          }
        }
      return null;
     
     }    
     private String saveFile(Part filePart, String fileName, HttpServletRequest request) throws IOException {
    	    // 保存先ディレクトリ（Webアプリ配下の /upload）
    	    String uploadDir = request.getServletContext().getRealPath("/upload");

    	    File uploadFolder = new File(uploadDir);
    	    if (!uploadFolder.exists()) {
    	        uploadFolder.mkdirs(); // フォルダがなければ作成
    	    }

    	    // ファイル保存処理
    	    File file = new File(uploadFolder, fileName);
    	    filePart.write(file.getAbsolutePath());

    	    System.out.println("ファイル保存完了: " + file.getAbsolutePath());
    	    return fileName;
    	}


}

