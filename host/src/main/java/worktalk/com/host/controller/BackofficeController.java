package worktalk.com.host.controller;

import java.util.Arrays;
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

import com.google.gson.GsonBuilder;

import worktalk.com.host.domain.Qna;
import worktalk.com.host.domain.Review;
import worktalk.com.host.domain.Room;
import worktalk.com.host.domain.Space;
import worktalk.com.host.repository.QnaCommentDAO;
import worktalk.com.host.repository.QnaDAO;
import worktalk.com.host.repository.SpaceDAO;
import worktalk.com.host.service.RoomMultipartService;
import worktalk.com.host.service.RoomService;
import worktalk.com.host.service.SpaceMultipartService;
import worktalk.com.host.service.SpaceService;

/**
 * 작성자 : 최수연 
 * 백오피스 기능 구현 (사무공간 CRUD 및 검색, 사무 상세공간 insert)
 */
@Controller
public class BackofficeController {

	private static final Logger logger = LoggerFactory.getLogger(BackofficeController.class);

	@Autowired
	HttpSession session;

	@Autowired
	SpaceService service;

	@Autowired
	SpaceDAO dao;

	// 사무공간이미지를 받아오는 service
	@Autowired
	SpaceMultipartService service_file;

	@Autowired
	RoomService r_service;

	// 상세사무공간이미지를 받아오는 service
	@Autowired
	RoomMultipartService r_service_file;

	@Autowired
	QnaDAO q_dao;

	@Autowired
	QnaCommentDAO qc_dao;

	// 공간전체목록페이지
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String selectAll(String host, Model model) {
		logger.info("Welcome selectAll!");

		host = (String) session.getAttribute("host_name"); // 접속자(호스트)의 사무공간만 출력하기위해 접속자 받아오기

		if (host == null) {
			return "login/login"; // 비로그인시 로그인창으로 이동
		} else {
			logger.info("host : {}", host);
			List<Space> space_list = service.selectAll(host);
			logger.info("space_list.size() : {}", space_list.size());
			logger.info("space_list : {}", space_list);

			model.addAttribute("space_list", space_list);

			return "main";
		}
	}

	// 사무공간상세페이지
	@RequestMapping(value = "/backoffice/space_selectOne.do", method = RequestMethod.GET)
	public String selectOne(long space_num, Model model) {
		logger.info("Welcome selectOne!");

		// 사무공간 상세정보 출력
		Space space2 = service.selectOne(space_num);
		logger.info("{}", space2);

		// 해당 사무공간에 속한 세부공간 정보 출력
		List<Room> room_list = dao.findRoom(space_num);
		logger.info("room_list.size() : {}", room_list.size());
		logger.info("room_list : {}", room_list);

		// 해당 사무공간의 Q&A 리스트 출력
		List<Qna> qna_list = q_dao.findQnaBySpaceNum(space_num);
		logger.info("qna_list.size() : {}", qna_list.size());
		logger.info("qna_list : {}", qna_list);

		// 해당 사무공간의 후기 출력
//		List<Review> review_list = dao.findReview(space_num);
//		logger.info("review_list.size() : {}", review_list.size());
//		logger.info("review_list : {}", review_list);

		model.addAttribute("space2", space2);
		model.addAttribute("room_list", room_list);
		model.addAttribute("qna_list", qna_list);
//		model.addAttribute("review_list", review_list);

		return "spacepage/spacedetail";
	}

	// 사무공간등록 페이지로 이동
	@RequestMapping(value = "/backoffice/space_insert.do", method = RequestMethod.GET)
	public String insert() {
		logger.info("Welcome insert!");

		return "spacepage/spacePage";
	}

	// 공간등록 요청페이지
	@RequestMapping(value = "/backoffice/space_insertOK.do", method = RequestMethod.POST)
	public String insertOK(String host, Space space) {
		logger.info("Welcome insertOK...");
		logger.info("{}", space);
		host = (String) session.getAttribute("host_name"); // 접속자(호스트) 받아오기

		if (host == null) {
			return "login/login"; // 비로그인시 로그인창으로 이동
		} else {
			// 공간이미지 가져오기
			space = service_file.getVO(space);
			// 이미지 외 정보들 입력
			int result = service.insert(space);
			logger.info("result : {}", result);
			if (result == 1) {
				return "redirect:/main.do";
			} else {
				return "redirect:space_insert.do";
			}
		}
	}

	// 사무공간 정보 업데이트
	@RequestMapping(value = "/backoffice/space_update.do", method = RequestMethod.GET)
	public String update() {
		logger.info("Welcome update!");

		return "backoffice/space/space_selectOne";
	}

	// 사무공간수정 요청페이지
	@RequestMapping(value = "/backoffice/space_updateOK.do", method = RequestMethod.POST)
	@ResponseBody
	public String updateOK(Space space) {
		logger.info("Welcome updateOK...");
		logger.info("{}", space);
		// 사무공간이미지 가져오기
		if (space.getMultipartFile() != null) {
			space = service_file.getVO(space);
			logger.info("{}", space);
		}
		// 이미지 외 정보들 입력
		int result = service.update(space);
		logger.info("result : {}", result);

		return String.valueOf(result);
	}

	// 공간삭제 요청페이지
	@RequestMapping(value = "/backoffice/space_deleteOK.do", method = RequestMethod.GET)
	public String deleteOK(Space space) {
		logger.info("Welcome deleteOK...");
		logger.info("{}", space);

		int result = service.delete(space);
		logger.info("result : {}", result);

		return "redirect:main.do";
	}

	// 사무공간 검색페이지
	@RequestMapping(value = "/backoffice/space_searchList.do", method = RequestMethod.GET)
	public String searchList(Model model, String searchKey, String searchWord) {
		logger.info("Welcome searchList!");
		List<Space> space_list = service.searchList(searchWord);
		logger.info("result : {}.", space_list.size());

		model.addAttribute("space_list", space_list);

		return "main";
	}


	// 상세공간등록 페이지로 이동
	@RequestMapping(value = "/backoffice/room_insert.do", method = RequestMethod.GET)
	public String r_insert() {
		logger.info("Welcome insert!");

		return "spacepage/roomPage";
	}

	// 상세공간등록 요청페이지 : 개발 중입니다..
	@RequestMapping(value = "/backoffice/room_insertOK.do", method = RequestMethod.POST)
	@ResponseBody
	public String r_insertOK(String multiroom) {
		logger.info("Welcome r_insertOK!");
		logger.info("multiroom:{}", multiroom);

		List<Room> vos = Arrays.asList(new GsonBuilder().create().fromJson(multiroom, Room[].class));
		for (Room vo : vos) {
			logger.info(vo.toString());
			int result = r_service.insert(vo);
		}

		return "redirect:/space_selectOne.do";
	}

	// 세부공간수정 페이지
	@RequestMapping(value = "/backoffice/room_update.do", method = RequestMethod.GET)
	public String r_update() {
		logger.info("Welcome update!");

		return "backoffice/space/space_selectOne";
	}

	// 세부공간수정 요청페이지
	@RequestMapping(value = "/backoffice/room_updateOK.do", method = RequestMethod.POST)
	public String r_updateOK(Space space) {
		logger.info("Welcome updateOK...");
		logger.info("{}", space);
		// 공간이미지 가져오기
		space = service_file.getVO(space);
		// 이미지 외 정보들 입력
		int result = service.update(space);
		logger.info("result : {}", result);
		if (result == 1) {
			return "redirect:space_selectOne.do";
		} else {
			return "redirect:space_selectOne.do";
		}
	}
}
