package worktalk.com.user.controller.mypage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import worktalk.com.user.domain.Customer_center;
import worktalk.com.user.domain.Qna;
import worktalk.com.user.repository.QnaDAO;
import worktalk.com.user.service.QnaService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class QnaController {
	
	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	
	@Autowired
	QnaService service;
	
	@Autowired
	QnaDAO dao;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */

	//qna관리 페이지
		@RequestMapping(value = "/mypage/qna.do", method = RequestMethod.GET)
		public String selectAll(Model model, String writer) {
			logger.info("Welcome selectAll");
			
			List<Qna> qna_list = dao.findQnaByName(writer);
			logger.info("qna_list.size() : {}",qna_list.size());
			
			model.addAttribute("qna_list",qna_list);

			return "reviewqna/reviewqna";
		}
		
	//QNA 작성
	@RequestMapping(value = "/qna_insert.do", method = RequestMethod.POST)
	public String insert(Qna qna, long space_num) {
		logger.info("Welcome insertOK...");
		logger.info("{}", qna);
		
		int result = service.insert(qna);
		logger.info("result : {}", result);
		if (result == 1) {
			return "redirect:space_selectOne.do?space_num="+space_num;
		} else {
			return "redirect:qna_insert.do";
		}
	}
	
	//문의 수정
	@RequestMapping(value = "/mypage/qna_update.do", method = RequestMethod.POST)
	public String update(Qna qna) {
		logger.info("Welcome updateOK");
		
		
		int result = service.update(qna);
		logger.info("result : {}", result);
		if (result == 1) {
			return "redirect:space_selectOne.do";
		} else {
			return "redirect:qna_update.do";
		}
	}
	
	//문의 삭제
	@RequestMapping(value = "/mypage/qna_delete.do", method = RequestMethod.GET)
	public String delete(Qna qna) {
		logger.info("Welcome deleteOK");
		int result = service.delete(qna);
		logger.info("result : {}", result);

		return "redirect:space_selectOne.do";
	}
	
}
