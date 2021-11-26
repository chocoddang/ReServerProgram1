package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dto.BoardDTO;
import dto.ReplyDTO;
import mybatis.config.DBService;

public class BoardDAO {

	private SqlSessionFactory factory;
	private static BoardDAO instance;
	private BoardDAO() {
		factory = DBService.getInstance().getFactory();
	}
	public static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	

	public List<BoardDTO> selectBoardList() {
		SqlSession ss = factory.openSession();
		List<BoardDTO> list = ss.selectList("dao.board.selectBoardList");
		ss.close();
		return list;
	}
	


	public int insertBoard(BoardDTO boardDTO) {
		SqlSession ss = factory.openSession(false); 
		int result = ss.insert("dao.board.insertBoard", boardDTO);
		if (result > 0) ss.commit();
		ss.close();
		return result;
	}
	
	public int getTotalCount() {
		SqlSession ss = factory.openSession();
		int totalCount = ss.selectOne("dao.board.getTotalCount");		
		ss.close();
		return totalCount;
	}
	
	
	public BoardDTO selectBoardByNo(Long no) {
		SqlSession ss = factory.openSession();
		BoardDTO boardDTO = ss.selectOne("dao.board.selectBoardByNo", no);
		ss.close();
		return boardDTO;
	}
	
	public int updateBoardHit (Long no) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update("dao.board.updateBoardHit", no);
		if(result > 0) ss.commit();
		ss.close();
		return result;
	}
	
	

	public int insertReply(ReplyDTO replyDTO) {	
		SqlSession ss = factory.openSession(false);
		int result = ss.insert("dao.board.insertReply", replyDTO);
		if (result > 0) ss.commit();
		ss.close();
		return result;
	}
	

	public List<ReplyDTO> selectReplyList(Long no) {	
		SqlSession ss = factory.openSession();
		List<ReplyDTO> list = ss.selectList("dao.board.selectReplyList", no);	
		ss.close();
		return list;
	}

	public List<BoardDTO> selectMaxHit() {
		SqlSession ss = factory.openSession();
		List<BoardDTO> maxHit = ss.selectList("dao.board.selectMaxHit");
		ss.close();
		return maxHit;
	}	

	public int deleteBoard(Long no) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete("dao.board.deleteBoard", no);
		if (result > 0) ss.commit();
		ss.close();
		return result;
	}

	
	
}
