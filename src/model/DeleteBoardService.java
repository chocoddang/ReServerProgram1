package model;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ModelAndView;
import dao.BoardDAO;
import dto.ReplyDTO;

public class DeleteBoardService implements MemberService {

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Long no = Long.parseLong(request.getParameter("no"));	
		
		List<ReplyDTO> replyList = BoardDAO.getInstance().selectReplyList(no);

		PrintWriter out = response.getWriter();
		// 댓글이 없다 삭제 가능!!!
		if(replyList.size() == 0) {	
			int result = BoardDAO.getInstance().deleteBoard(no);	
			// 댓글이 있다  삭제 못함!!
			if (result > 0) {
				out.println("<script>");
				out.println("alert('게시글이 삭제되었습니다.')");
				out.println("location.href='selectBoardList.do'");
				out.println("</script>");
				out.close();
			} else {
				out.println("<script>");
				out.println("alert('게시글 삭제 실패')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
			}
		} else {	
			out.println("<script>");
			out.println("alert('댓글이 달린 게시글은 삭제할 수 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
		return null;
	}
}
