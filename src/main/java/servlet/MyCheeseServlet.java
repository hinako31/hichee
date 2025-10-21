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

import model.Area;
import model.Diary;
import model.User;
import service.AreaLogic;
import service.DiaryLogic;



@WebServlet("/MyCheese")
public class MyCheeseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public MyCheeseServlet() {
        super();
        
    }

	
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	    HttpSession session = request.getSession();

    	    // セッションから検索条件を取得
    	    String name = (String) session.getAttribute("searchName");
    	    Integer periodYearStr = (Integer) session.getAttribute("searchPeriodYear");
    	    Integer periodMonthStr = (Integer) session.getAttribute("searchPeriodMonth");
    	    Integer areaIdStr= (Integer) session.getAttribute("searchAreaId");

    	    // ログインユーザーの取得
    	    User user = (User) session.getAttribute("user");
    	    if (user == null) {
    	        response.sendRedirect("index.jsp");
    	        return;
    	    }

    	    // 検索条件を使ってDiaryLogicで検索
    	    DiaryLogic diaryLogic = new DiaryLogic();
    	    List<Diary> diaryList = diaryLogic.searchDiaries(user.getId(), name, 
    	    		periodYearStr != null ? periodYearStr.toString() : null, 
    	    				periodMonthStr != null ? periodMonthStr.toString() : null, 
    	    						areaIdStr != null ? areaIdStr.toString() : null);

    	    request.setAttribute("diaryList", diaryList);

    	 AreaLogic areaLogic = new AreaLogic();
			List<Area> areaList = areaLogic.getAllAreas();
			request.setAttribute("areaList", areaList);

    	    // 検索条件もリクエストにセット（フォームに戻す用）
    	    request.setAttribute("searchName", name);
    	    request.setAttribute("searchPeriodYear", periodYearStr);
    	    request.setAttribute("searchPeriodMonth", periodMonthStr);
    	    request.setAttribute("searchAreaId", areaIdStr);

    	    // 一覧画面へフォワード
    	    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/myCheeseList.jsp");
    	    dispatcher.forward(request, response);
    	}



	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String action = request.getParameter("action");

        // ログインチェック
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        if ("My Cheese".equals(action)) {
// マイチーズ画面に遷移
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/myCheese.jsp");
            dispatcher.forward(request, response);
            return;
        } else if ("戻る".equals(action)) {
// 戻るボタン（検索画面に戻る）
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/myCheese.jsp");
            dispatcher.forward(request, response);
            return;
        } 
//検索画面の検索ボタンを押した時        
        else if ("検索".equals(action)) {
            String name = request.getParameter("name");
            String periodYearStr = request.getParameter("period_year");
            String periodMonthStr = request.getParameter("period_month");
            String areaIdStr = request.getParameter("area_id");

            Integer periodYear = null;
            Integer periodMonth = null;
            Integer areaId = null;

            if (periodYearStr != null && !periodYearStr.isEmpty() && !"unknown".equals(periodYearStr)) {
                try {
                    periodYear = Integer.valueOf(periodYearStr);
                } catch (NumberFormatException e) {
                    // エラーハンドリング
                }
            }

            if (periodMonthStr != null && !periodMonthStr.isEmpty() && !"unknown".equals(periodMonthStr)) {
                try {
                    periodMonth = Integer.valueOf(periodMonthStr);
                } catch (NumberFormatException e) {
                    // エラーハンドリング
                }
            }

            if (areaIdStr != null && !areaIdStr.isEmpty() && !"unknown".equals(areaIdStr)) {
                try {
                    areaId = Integer.valueOf(areaIdStr);
                } catch (NumberFormatException e) {
                    // エラーハンドリング
                }
            }

            // セッションに検索条件を保存
            session.setAttribute("searchName", name);
            session.setAttribute("searchPeriodYear", periodYearStr);
            session.setAttribute("searchPeriodMonth", periodMonthStr);
            session.setAttribute("searchAreaId", areaIdStr);

            DiaryLogic diaryLogic = new DiaryLogic();
            List<model.Diary> diaryList = diaryLogic.searchDiaries(user.getId(),name,periodYearStr, periodMonthStr,areaIdStr);

            request.setAttribute("diaryList", diaryList);

         AreaLogic areaLogic = new AreaLogic();
			List<Area> areaList = areaLogic.getAllAreas();
			request.setAttribute("areaList", areaList);

            // 検索条件もリクエストにセット（フォームの初期値に使用）
            request.setAttribute("searchName", name);
            request.setAttribute("searchPeriodYear", periodYearStr);
            request.setAttribute("searchPeriodMonth", periodMonthStr);
            request.setAttribute("searchAreaId", areaIdStr);

            request.getRequestDispatcher("/WEB-INF/jsp/user/myCheeseList.jsp").forward(request, response);
            return;
        }

//検索画面から詳細画面に遷移するときの指示        
        else if ("detail".equals(action)) {
            // 詳細表示
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    DiaryLogic diaryLogic = new DiaryLogic();
                    Diary diary = diaryLogic.getDiaryById(id);
                    request.setAttribute("diary", diary);
                    request.getRequestDispatcher("/WEB-INF/jsp/user/myCheeseDetail.jsp").forward(request, response);
                    return;
                } catch (NumberFormatException e) {
                    // 不正なIDの場合の処理
                    response.sendRedirect("MyCheese");
                    return;
                }
            } else {
                response.sendRedirect("MyCheese");
                return;
            }
        } else {
            // 未定義のactionは検索画面に戻すなど
            response.sendRedirect("MyCheese");
        }
    }
}
