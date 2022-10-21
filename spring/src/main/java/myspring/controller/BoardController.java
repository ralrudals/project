package myspring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import myspring.model.Board;
import myspring.service.BoardService;

@Controller
public class BoardController 
{
	@Autowired
	private BoardService bs;
	
	// 글작성 폼
	@RequestMapping("boardform.do")
	public String boardform()
	{
		return "board/boardform";
	}
	
	@RequestMapping("boardwrite.do")
	public String boardwrite(Board board,Model model)
	{
		int result = bs.insert(board);
		if(result == 1) System.out.println("글 작성 성공");
		
		model.addAttribute("result", result);
		return "board/insertresult";
	}
	
	@RequestMapping("boardlist.do")
	public String boardlist(HttpServletRequest request, Model model)
	{
		int page = 1;	// 현재 페이지
		int limit = 10; // 한 화면에 출력할 페이지 개수
		int listcount = bs.getCount(); // 총 데이터 개수
		
		if(request.getParameter("page") != null)
		{
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int startRow = (page - 1) * limit + 1;
		int endRow = page * limit;
		
		List<Board> boardlist = bs.getBoardList(page);	
		
		// 총페이지
		int pageCount = listcount / limit + ((listcount%limit == 0) ? 0 : 1);
		
		int startPage = ((page - 1) / 10) * limit + 1;
		int endPage = startPage + 10 - 1;
		
		if(endPage > pageCount) endPage = pageCount;
		
		model.addAttribute("page", page);
		model.addAttribute("listcount", listcount);
		model.addAttribute("boardlist", boardlist);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "board/boardlist";
	}
	
	@RequestMapping("boardcontent.do")
	public String boardcontent(int no, int page, Model model)
	{
		bs.updatecount(no);
		Board board = bs.getBoard(no);
		String content = board.getContent().replace("\n", "<br>");
		
		model.addAttribute("board", board);
		model.addAttribute("content", content);
		model.addAttribute("page", page);
		
		return "board/boardcontent";
	}
	
	@RequestMapping("boardupdateform.do")
	public String boardupdateform(int no, int page, Model model)
	{
		Board board = bs.getBoard(no);
		
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		
		return "board/boardupdateform";
	}
	
	@RequestMapping("boardupdate.do")
	public String boardupdate(Board board, int page, Model model)
	{
		int result = 0;
		Board old = bs.getBoard(board.getNo());
		
		if(old.getPasswd().equals(board.getPasswd()))
		{
			System.out.println("글 수정 성공");
			
			result = bs.update(board);
		}
		else
		{
			System.out.println("글 수정 실패");		
			
			result = -1;
		}
		model.addAttribute("result", result);
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		
		return "board/boardupdateresult";
	}
	
	@RequestMapping("boarddeleteform.do")
	public String boarddeleteform()
	{
		return "board/boarddeleteformresult";
	}
	
	@RequestMapping("boarddelete.do")
	
	public String boarddelete(Board board, int page, Model model)
	{
		int result = 0;
		Board old = bs.getBoard(board.getNo());
		
		if(old.getPasswd().equals(board.getPasswd()))
		{
			System.out.println("글 삭제 성공");
			
			result = bs.delete(board.getNo());
		}
		else
		{
			System.out.println("글 삭제 실패");		
			
			result = -1;
		}
		model.addAttribute("result", result);
		model.addAttribute("page", page);
		
		return "board/boarddeleteresult";
	}
}
