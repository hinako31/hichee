package servlet;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import model.Area;
import model.Diary;
import model.User;
import service.AreaLogic;
import service.DiaryLogic;


@WebServlet("/NewCheese")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // 5MBまで
public class NewCheeseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewCheeseServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
		        AreaLogic areaLogic = new AreaLogic();
		        List<Area> areaList = areaLogic.getAllAreas();
		     request.setAttribute("areaList", areaList);

//	        if (areaList == null) {
//	            throw new ServletException("エリアリストの取得に失敗しました（null）");
//	        }
		     System.out.println("areaList size: " + areaList.size());
		     for (Area area : areaList) {
		         System.out.println("area.id = " + area.getId() + ", name = " + area.getArea_name());
		     }

		     System.out.println("newCheese.jsp にフォワードします。areaList サイズ：" + areaList.size());

	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/newCheese.jsp");
	        dispatcher.forward(request, response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "エリア情報の取得に失敗しました");
	    }
		 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		 HttpSession session2 = request.getSession();
		 User user = (User) session2.getAttribute("user");

		 if (user == null) {
		     // ログインしていない、またはセッション切れの場合の処理
		     response.sendRedirect("/WEB-INF/jsp/user/login.jsp");  // ログインページなどに戻す
		     return;
		 }
		 HttpSession session = request.getSession();
		//確認画面からの分岐
		 String step = request.getParameter("step");
		  if ("戻る".equals(step)) {
		        Diary diary = (Diary) session.getAttribute("diary");
		        request.setAttribute("diary", diary);
		        // エリアリストも必要ならセットする
		        AreaLogic areaLogic = new AreaLogic();
		        List<Area> areaList = areaLogic.getAllAreas();
		        request.setAttribute("areaList", areaList);
		        
		        request.getRequestDispatcher("/WEB-INF/jsp/user/newCheese.jsp").forward(request, response);
		        return;
		    }
		    if ("登録".equals(step)) {
		        Diary diary = (Diary) session.getAttribute("diary");
		        if (diary == null) {
		            // セッション切れなどの処理
		            response.sendRedirect("/WEB-INF/jsp/user/newCheese.jsp");
		            return;
		        }
		        // DiaryLogicに登録処理依頼
		        DiaryLogic diaryLogic = new DiaryLogic();
		      
		        boolean success = diaryLogic.registerDiary(diary, user.getId());

		        if (success) {
		            session.removeAttribute("diary");
		            response.sendRedirect("/WEB-INF/jsp/user/newCheeseResult.jsp");
		        } else {
		            // 登録失敗時の処理
		            request.setAttribute("errorMessage", "登録に失敗しました。再度お試しください。");
		            request.getRequestDispatcher("/WEB-INF/jsp/user/newCheeseCheck.jsp").forward(request, response);
		        }
		        return;
		    }

		 
//NewCheese新規登録→確認ボタンを押した時
		// 入力値取得
        String name = request.getParameter("name");
        String memorialYearStr = request.getParameter("memorial_year");
        String memorialMonthStr = request.getParameter("memorial_month");
        String areaIdStr = request.getParameter("area_id");
        String review = request.getParameter("review");
        Part imgPart = request.getPart("img_name");

        StringBuilder error = new StringBuilder();

        // 入力値をJavaBeanに格納（Storeクラスは要用意）
        Diary diary = new Diary();

        diary.setName(name);
        diary.setReview(review);

        //エラーの☑
        // memorialYear
        Integer memorialYear = null;
        if (memorialYearStr != null && !memorialYearStr.isEmpty()) {
            try {
                memorialYear = Integer.valueOf(memorialYearStr);
                diary.setPeriod_year(memorialYear);
            } catch (NumberFormatException e) {
                error.append("記念年の指定が不正です。<br>");
            }
        } else {
            diary.setPeriod_year(null);  // 未選択ならnull
        }

        // memorialMonth
        Integer memorialMonth = null;
        if (memorialMonthStr != null && !memorialMonthStr.isEmpty()) {
            try {
                memorialMonth = Integer.valueOf(memorialMonthStr);
                diary.setPeriod_month(memorialMonth);
            } catch (NumberFormatException e) {
                error.append("記念月の指定が不正です。<br>");
            }
        } else {
            diary.setPeriod_month(null); // 未選択ならnull
        }
        // areaId
        Integer areaId = null;
        if (areaIdStr != null && !areaIdStr.isEmpty()) {
            try {
                areaId = Integer.parseInt(areaIdStr);
                diary.setArea_id(areaId);
            } catch (NumberFormatException e) {
                error.append("場所の指定が不正です。<br>");
            }
        } else {
            error.append("場所を選択してください。<br>");
        }

        // バリデーション

        // 店名必須
        if (name == null || name.trim().isEmpty()) {
            error.append("店名を入力してください。<br>");
        }

        // 記念年月の未来日チェック（年月両方ある時のみ）
        if (memorialYear != null && memorialMonth != null) {
            try {
                LocalDate now = LocalDate.now();
                LocalDate enteredDate = LocalDate.of(memorialYear, memorialMonth, 1);
                if (enteredDate.isAfter(now)) {
                    error.append("記念年月は未来に設定できません。<br>");
                }
            } catch (DateTimeException e) {
                error.append("記念年月の指定が不正です。<br>");
            }
        }

        // 画像チェック
        String fileName = getFileName(imgPart);
        if (fileName != null && !fileName.isEmpty()) {
            if (!fileName.matches("(?i).*\\.(jpg|jpeg|png|gif)$")) {
                error.append("画像ファイルは jpg, jpeg, png, gif のみ対応しています。<br>");
            }
            // 画像保存処理は別途対応
        }

     // エラーがある場合
        if (error.length() > 0) {
            request.setAttribute("errorMessage", error.toString());

            AreaLogic areaLogic = new AreaLogic();
			List<Area> areaList = areaLogic.getAllAreas();
			request.setAttribute("areaList", areaList);

            // セッションに入力値を保存（エラー時）
            session.setAttribute("diary", diary);

            request.getRequestDispatcher("/WEB-INF/jsp/user/newCheese.jsp").forward(request, response);
            return;
        }
        // エラーなし → 入力値をセッションに保存して確認画面へ
        session.setAttribute("diary", diary); // セッションに保存
     // areaId が null じゃないときだけ名前取得
        String areaName = null;
        if (areaId != null) {
            AreaLogic areaLogic = new AreaLogic();
            List<Area> areaList = areaLogic.getAllAreas();  // DBから再取得
            for (Area area : areaList) {
                if (area.getId() == areaId) {
                    areaName = area.getArea_name();
                    break;
                }
            }
        }
        request.setAttribute("areaName", areaName != null ? areaName : "不明");      
        request.getRequestDispatcher("/WEB-INF/jsp/user/newCheeseCheck.jsp").forward(request, response); 
        // 確認画面へフォワード
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

}