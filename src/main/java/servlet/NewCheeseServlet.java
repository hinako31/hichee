package servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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
		 
		 System.out.println("diary=" + session.getAttribute("diary"));
		
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
		    if ("作成".equals(step)) {
		    	System.out.println("doPostが呼ばれました。step=" + step);
		    	Diary diary = (Diary) session.getAttribute("diary");
		    	System.out.println("diary from session: " + diary);
		    	
		        if (diary == null) {
		            // セッション切れなどの処理
		        	response.sendRedirect("NewCheese");
		            return;
		        }
		        // DiaryLogicに登録処理依頼
		        DiaryLogic diaryLogic = new DiaryLogic();
		      
		        boolean success = diaryLogic.registerDiary(diary, user.getId());
		        System.out.println("登録結果 success = " + success);
		        
		        if (success) {
		            session.removeAttribute("diary");
		            response.sendRedirect("NewCheeseResult");  // 完了画面を表示するサーブレットかJSPのURLへリダイレクト
		            return;
		        }else {
		            // 登録失敗時の処理
		            request.setAttribute("errorMessage", "登録に失敗しました。再度お試しください。");
		            Integer memorialYear = diary.getPeriod_year();
		            Integer memorialMonth = diary.getPeriod_month();
		            Integer areaId = diary.getArea_id();

		            String memorialYearDisplay = (memorialYear != null) ? memorialYear.toString() : "不明";
		            String memorialMonthDisplay = (memorialMonth != null) ? memorialMonth.toString() : "不明";

		            AreaLogic areaLogic = new AreaLogic();
		            List<Area> areaList = areaLogic.getAllAreas();
		            String areaName = "不明";
		            if (areaId != null) {
		                for (Area area : areaList) {
		                    if (area.getId() == areaId) {
		                        areaName = area.getArea_name();
		                        break;
		                    }
		                }
		            }
		            request.setAttribute("memorialYearDisplay", memorialYearDisplay);
		            request.setAttribute("memorialMonthDisplay", memorialMonthDisplay);
		            request.setAttribute("areaName", areaName);

		            request.getRequestDispatcher("/WEB-INF/jsp/user/newCheeseCheck.jsp").forward(request, response);
		            return;
		        }
		      
		    }

		 
//NewCheese新規登録→確認ボタンを押した時
		    if ("確認".equals(step)) {
		        request.setCharacterEncoding("UTF-8");
		        String name = request.getParameter("name");
		        String memorialYearStr = request.getParameter("memorial_year");
		        String memorialMonthStr = request.getParameter("memorial_month");
		        String areaIdStr = request.getParameter("area_id");
		        String review = request.getParameter("review");
		        Part imgPart = request.getPart("file_name");

		        StringBuilder error = new StringBuilder();
		        Diary diary = new Diary();

		        diary.setName(name);
		        diary.setReview(review);

		        // 記念年月とエリアID
		        Integer memorialYear = parseIntOrNull(memorialYearStr, "記念年", error);
		        Integer memorialMonth = parseIntOrNull(memorialMonthStr, "記念月", error);
		        Integer areaId = parseIntOrNull(areaIdStr, "場所", error);

		        diary.setPeriod_year(memorialYear);
		        diary.setPeriod_month(memorialMonth);
		        diary.setArea_id(areaId);

		        // 店名必須チェック
		        if (name == null || name.trim().isEmpty()) {
		            error.append("店名を入力してください。<br>");
		        }

		        // 画像アップロード処理（ここが重要！）
		        if (imgPart != null && imgPart.getSize() > 0) {
		            String fileName = Paths.get(imgPart.getSubmittedFileName()).getFileName().toString();

		            if (!fileName.matches("(?i).*\\.(jpg|jpeg|png|gif)$")) {
		                error.append("画像ファイルは jpg, jpeg, png, gif のみ対応しています。<br>");
		            } else {
		                try {
		                    saveFile(imgPart, fileName, request); // ファイルをサーバーに保存
		                    diary.setFile_name(fileName);
		                    diary.setFile_path("upload/" + fileName);
		                    System.out.println("アップロード成功: " + diary.getFile_path());
		                } catch (IOException e) {
		                    e.printStackTrace();
		                    error.append("画像の保存に失敗しました。<br>");
		                }
		            }
		        } else {
		            // 画像が未選択でもOK（任意の場合）
		            diary.setFile_name(null);
		            diary.setFile_path(null);
		        }

		        // バリデーションエラーがあれば入力画面へ戻す
		        if (error.length() > 0) {
		            request.setAttribute("errorMessage", error.toString());
		            AreaLogic areaLogic = new AreaLogic();
		            request.setAttribute("areaList", areaLogic.getAllAreas());
		            request.setAttribute("diary", diary);
		            request.getRequestDispatcher("/WEB-INF/jsp/user/newCheese.jsp").forward(request, response);
		            return;
		        }

		        // 成功時：確認画面へ
		        HttpSession session1 = request.getSession();
		        session1.setAttribute("diary", diary);

		        AreaLogic areaLogic = new AreaLogic();
		        List<Area> areaList = areaLogic.getAllAreas();
		        String areaName = "不明";
		        if (areaId != null) {
		            for (Area area : areaList) {
		                if (area.getId() == areaId) {
		                    areaName = area.getArea_name();
		                    break;
		                }
		            }
		        }

		        request.setAttribute("areaName", areaName);
		        request.setAttribute("memorialYearDisplay", memorialYear != null ? memorialYear.toString() : "不明");
		        request.setAttribute("memorialMonthDisplay", memorialMonth != null ? memorialMonth.toString() : "不明");
		        request.getRequestDispatcher("/WEB-INF/jsp/user/newCheeseCheck.jsp").forward(request, response);
		    }

// 確認画面へフォワード
	}
	
	private Integer parseIntOrNull(String str, String label, StringBuilder error) {
	    if (str == null || str.isEmpty()) return null;
	    try {
	        return Integer.parseInt(str);
	    } catch (NumberFormatException e) {
	        error.append(label + "の指定が不正です。<br>");
	        return null;
	    }
	}
	   // =====================================================
    // ファイル保存処理（共通化）
    // =====================================================
    private void saveFile(Part filePart, String fileName, HttpServletRequest request) throws IOException {
        String uploadDir = request.getServletContext().getRealPath("/upload");
        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        File file = new File(uploadFolder, fileName);
        filePart.write(file.getAbsolutePath());
        System.out.println("ファイル保存完了: " + file.getAbsolutePath());
    }

	//postの外側
    // imgPartからファイル名を取り出す
	private String getFile_name(Part part) {
	    if (part == null) return null;
	    String contentDisp = part.getHeader("content-disposition");
	    if (contentDisp == null) return null;

	    for (String cd : contentDisp.split(";")) {
	        if (cd.trim().startsWith("filename")) {  // ここをfile_name→filenameに変更
	            String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return fileName.substring(fileName.lastIndexOf("\\") + 1);
	        }
	    }
	    return null;
	}


}