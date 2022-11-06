package worktalk.com.host.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import worktalk.com.host.domain.Qna;
import worktalk.com.host.domain.QnaComment;
import worktalk.com.host.domain.Room;
import worktalk.com.host.domain.Space;
import worktalk.com.host.repository.QnaCommentDAO;
import worktalk.com.host.repository.QnaDAO;
import worktalk.com.host.service.QnaCommentService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class QnaController {

	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);

	@Autowired
	HttpSession session;
	
	@Autowired
	QnaDAO q_dao;

	@Autowired
	QnaCommentDAO qc_dao;
	
	@Autowired
	QnaCommentService qc_service;
	
	// 공간전체목록페이지
	@RequestMapping(value = "/backoffice/qna_insert.do", method = RequestMethod.POST)
	public String insertOK(Model model, QnaComment qnacomment, long space_num) {
		logger.info("Welcome insertOK...");
		logger.info("{}", qnacomment);

		int result = qc_service.insert(qnacomment);
		logger.info("result : {}", result);
		
			return "redirect:space_selectOne.do?space_num="+space_num;
	
	}

	
	@RequestMapping(value = "/backoffice/qna_update.do", method = RequestMethod.POST)
	public String updateOK(QnaComment qnacomment,long space_num) {
		logger.info("Welcome updateOK...");
		logger.info("{}", qnacomment);
 
		int result = qc_service.update(qnacomment);
		logger.info("result : {}", result);

		return "redirect:space_selectOne.do?space_num="+space_num;
	}
	
	// 공간수정 요청페이지
		@RequestMapping(value = "/backoffice/qna_delete.do", method = RequestMethod.GET)
		public String deleteOK(QnaComment qnacomment, long space_num) {
			logger.info("Welcome deleteOK...");
			logger.info("{}", qnacomment);

			int result = qc_service.delete(qnacomment);
			logger.info("result : {}", result);

			return "redirect:space_selectOne.do?space_num="+space_num;
		}

	
}
