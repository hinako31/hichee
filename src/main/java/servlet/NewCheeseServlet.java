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
import service.AreaLogic;


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
		}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		 HttpSession session = request.getSession();
		 
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

            // areaListの準備（DAOなどから）
            AreaLogic areaLogic = new AreaLogic();
            List<Area> areaList = areaLogic.getAllAreas();
            request.setAttribute("areaList", areaList);

            // セッションに入力値を保存（エラー時）
            session.setAttribute("diary", diary);

            request.getRequestDispatcher("/newCheese.jsp").forward(request, response);
            return;
        }

        // エラーなし → 入力値をセッションに保存して確認画面へリダイレクト
        session.setAttribute("diary", diary);

        // 確認画面にリダイレクト（仮）
        response.sendRedirect("newCheeseCheck.jsp");
    }
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