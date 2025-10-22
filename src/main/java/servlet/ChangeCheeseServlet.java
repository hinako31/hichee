package servlet;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

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


@WebServlet("/ChangeCheese")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // 5MBまで
public class ChangeCheeseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ChangeCheeseServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   // リクエストパラメータの取得
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
		// セッション取得
		HttpSession session = request.getSession(false); // falseにしておくと新規作成されない
		User user = (User) session.getAttribute("user");

		if (user == null) {
		     // ログインしていない、またはセッション切れの場合の処理
		     response.sendRedirect("login.jsp");  // ログインページなどに戻す
		     return;
		 }
		 HttpSession session2 = request.getSession();
		//確認画面からの分岐
		 String step = request.getParameter("step");
		  if ("戻る".equals(step)) {
		        Diary diary = (Diary) session2.getAttribute("tentative");
		        request.setAttribute("tentative", diary);
		        AreaLogic areaLogic = new AreaLogic();
				List<Area> areaList = areaLogic.getAllAreas();
				request.setAttribute("areaList", areaList);
		        
		        request.getRequestDispatcher("/WEB-INF/jsp/user/changeCheese.jsp").forward(request, response);
		        return;
		    }
		    if ("登録".equals(step)) {
		        Diary diary = (Diary) session.getAttribute("tentative");
		        if (diary == null) {
		            // セッション切れなどの処理
		            response.sendRedirect("/WEB-INF/jsp/user/changeCheese.jsp");
		            return;
		        }
		        // DiaryLogicに登録処理依頼
		        DiaryLogic diaryLogic = new DiaryLogic();
		      
		        boolean success = diaryLogic.registerDiary(diary, user.getId());

		        if (success) {
		            session.removeAttribute("tentative");
		            response.sendRedirect("/WEB-INF/jsp/user/newCheeseResult.jsp");
		        } else {
		            // 登録失敗時の処理
		            request.setAttribute("errorMessage", "登録に失敗しました。再度お試しください。");
		            request.getRequestDispatcher("/WEB-INF/jsp/user/newCheeseCheck.jsp").forward(request, response);
		        }
		        return;
		    }

		
        //マイチーズの詳細のChangeCheeseボタンをおしたとき
		    if ("editForm".equals(action)) {
        	// 詳細表示
            String idStr = request.getParameter("id");
            
            System.out.println("取得したid: " + idStr);
            
            if (idStr != null && !idStr.isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
        	DiaryLogic diaryLogic = new DiaryLogic();
        	
        	int userId = user.getId();

        	Diary diary = diaryLogic.getDiaryById(id, userId);

            
            System.out.println("取得したDiary: " + diary); 
            if (diary == null) {
                System.out.println("Diaryが見つかりませんでした。id=" + id);
                response.sendRedirect("MyCheese");  // or エラーページに
                return;
            }

            HttpSession session3 = request.getSession();
            session3.setAttribute("tentative", diary);  // ✅ 必須

            // 必要ならエリアリストもセット
            AreaLogic areaLogic = new AreaLogic();
            List<Area> areaList = areaLogic.getAllAreas();
            session3.setAttribute("areaList", areaList);

            request.getRequestDispatcher("/WEB-INF/jsp/user/changeCheese.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect("MyCheese");
        }
    } else {
        response.sendRedirect("MyCheese");
   
                return;
            }
        } 
        
        //入力後確認画面へ遷移する際のチェック
        if("確認".equals(action)) {
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
                session.setAttribute("tentative", diary);

                request.getRequestDispatcher("/changeCheese.jsp").forward(request, response);
                return;
            }

            // エラーなし → 入力値をセッションに保存して確認画面へリダイレクト
            session.setAttribute("diary", diary); // セッションに保存
            request.getRequestDispatcher("/changeCheeseCheck.jsp").forward(request, response); 
            // 確認画面へフォワード
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



}
