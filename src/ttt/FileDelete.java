package ttt;

import model.Action;

import model.ActionData;
import model.BoardDAO;
import model.BoardVO;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import model.Action;

import model.ActionData;

public class FileDelete implements Action {

	@Override

	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {

		
		
		
		// TODO Auto-generated method stub

		BoardVO vo = new BoardVO();

		vo.setId(Integer.parseInt(request.getParameter("id")));

		vo.setSeq(Integer.parseInt(request.getParameter("seq"))  );	
		
		vo.setPw(request.getParameter("pw"));

		vo.setPname(request.getParameter("pname"));

		vo.setTitle(request.getParameter("title"));

		vo.setContent(request.getParameter("content"));

		vo.setUpfile(request.getParameter("upfile"));//파일 삭제 누를 시 원래 것 가져옴... 응?

		String msg = "인증실패";
		/*String url = "ModifyForm?id=" + vo.getId();*/
		
		BoardDAO dao = new BoardDAO();
		
		if (dao.sch(vo) != null) {//파일을 삭제하러 가즈아
			//인증됐는지안됐는지 리턴값 0인지 확인 후 업파일만 디비를 턴다 . 파일삭제가 주 목적이기떄무에
			//그런다음에 뷰만 모디파이 폼으로 들어감 왜이렇게 하냐면디비의내용을 바꾸느게아니고 내가 기존에썻던내용을 그대로 물고들어오기때문에
			msg = "파일 삭제 성공";
			String path = request.getRealPath("up");

			path = "D:\\SEO\\mvcjsp\\WebContent\\up";

			new File(path + "\\" + request.getParameter("upfile")).delete();

			dao.fileDelete(vo.getId());
			vo.setUpfile("");
		}
		
		request.setAttribute("msg", msg);

		request.setAttribute("main", "modifyForm.jsp");

		request.setAttribute("data", vo);

		dao.close();

		return new ActionData();

	}

}

		

		/*request.setAttribute("main", "modifyForm.jsp");

		request.setAttribute("data", vo);

		dao.close();

		return new ActionData();

		
	}
	
	

}
*/
