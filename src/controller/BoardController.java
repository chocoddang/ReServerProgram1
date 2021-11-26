package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import model.DeleteBoardService;
import model.BoardDetailService;
import model.InsertBoardService;
import model.BoardListService;
import model.MemberService;
import model.InsertReplyService;
import model.ReplyListService;

@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String requestURI = request.getRequestURI();																		 
		String contextPath = request.getContextPath();																	
		String command = requestURI.substring(contextPath.length() + 1);  					
		
		ModelAndView mav = null;
		MemberService service = null; 													
		switch(command) {
		
		case "selectBoardList.do" : 
			service = new BoardListService();
			break;
		
		case "selectBoardByNo.do" :
			service = new BoardDetailService();
			break;
		
		case "insertBoard.do" :
			service = new InsertBoardService();
			break;
			
		case "insertBoardForm.do" :
			mav = new ModelAndView("views/insertBoardForm.jsp", false);
			break;		

		case "insertReply.do" :
			service = new InsertReplyService();
			break;

		case "deleteBoard.do" :
			service = new DeleteBoardService();        
			break;			
		}
		
		if (service != null) {
			try {														
				mav = service.execute(request, response);		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	
		if (mav != null) {				
			if(mav.isRedirect()) {		
					response.sendRedirect(mav.getView());										
			} else {
					request.getRequestDispatcher(mav.getView()).forward(request, response);		
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
