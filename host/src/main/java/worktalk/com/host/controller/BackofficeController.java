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
import worktalk.com.host.domain.QnaComment;
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
 * Handles requests for the application home page.
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

	// 공간이미지를 받아오는 service
	@Autowired
	SpaceMultipartService service_file;

	@Autowired
	RoomService r_service;

	@Autowired
	RoomMultipartService r_service_file;
	
	@Autowired
	QnaDAO q_dao;

	@Autowired
	QnaCommentDAO qc_dao;
	
	// 공간전체목록페이지
	@RequestMapping(value = "/main.do", method = RequestMethod.GET)
	public String selectAll(Model model) {
		logger.info("Welcome selectAll!");

		List<Space> space_list = service.selectAll();
		logger.info("space_list.size() : {}", space_list.size());
		logger.info("space_list : {}", space_list);

		model.addAttribute("space_list", space_list);

		return "main";
//		return "backoffice/space/space_selectAll";
	}

	// 공간상세페이지
	@RequestMapping(value = "/backoffice/space_selectOne.do", method = RequestMethod.GET)
	public String selectOne(long space_num, Model model) {
		logger.info("Welcome selectOne!");

		Space space2 = service.selectOne(space_num);
		logger.info("{}", space2);

//		List<Room> room_list = r_service.selectAll(space_num);
//		logger.info("room_list.size() : {}", room_list.size());
		
		
		List<Room> room_list = dao.findRoom(space_num);
		logger.info("room_list.size() : {}", room_list.size());
		logger.info("room_list : {}", room_list);
		
		List<Qna> qna_list = q_dao.findQnaBySpaceNum(space_num);
		logger.info("qna_list.size() : {}", qna_list.size());
		logger.info("qna_list : {}", qna_list);
				
//		List<Review> review_list = dao.findReview(space_num);
//		logger.info("review_list.size() : {}", review_list.size());
//		logger.info("review_list : {}", review_list);
		
		model.addAttribute("space2", space2);
		model.addAttribute("room_list", room_list);
		model.addAttribute("qna_list", qna_list);
//		model.addAttribute("review_list", review_list);

		return "spacepage/spacedetail";
//		return "backoffice/space/space_selectOne";
	}

	// 공간등록 페이지로 이동
	@RequestMapping(value = "/backoffice/space_insert.do", method = RequestMethod.GET)
	public String insert() {
		logger.info("Welcome insert!");

		return "spacepage/spacePage";
//		return "backoffice/space/space_insert";
	}

	// 공간등록 요청페이지
	@RequestMapping(value = "/backoffice/space_insertOK.do", method = RequestMethod.POST)
	public String insertOK(Space space) {
		logger.info("Welcome insertOK...");
		logger.info("{}", space);
		// Host닉네임 가져오기
//		space.setHost((String)session.getAttribute("user_name"));
//		logger.info("host: {}", space.getHost());
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

	// 공간상세 페이지로 이동
	@RequestMapping(value = "/backoffice/space_update.do", method = RequestMethod.GET)
	public String update() {
		logger.info("Welcome update!");

		return "backoffice/space/space_selectOne";
	}

	// 공간수정 요청페이지
	@RequestMapping(value = "/backoffice/space_updateOK.do", method = RequestMethod.POST)
	@ResponseBody
	public String updateOK(Space space) {
		logger.info("Welcome updateOK...");
		logger.info("{}", space);
		// 공간이미지 가져오기
//		if (space.getMultipartFile() != null) {
//			space = service_file.getVO(space);
//			logger.info("{}", space);
//		}
		// 이미지 외 정보들 입력
		int result = service.update(space);
		logger.info("result : {}", result);

		return String.valueOf(result);
	}
	
	// 공간수정 요청페이지
		@RequestMapping(value = "/backoffice/space_deleteOK.do", method = RequestMethod.GET)
		public String deleteOK(Space space) {
			logger.info("Welcome deleteOK...");
			logger.info("{}", space);

			int result = service.delete(space);
			logger.info("result : {}", result);

			return "redirect:main.do";
		}
	
	//사무공간 검색페이지
	@RequestMapping(value = "/backoffice/space_searchList.do", method = RequestMethod.GET)
	public String searchList(Model model, String searchKey, String searchWord) {
		logger.info("Welcome searchList!");
		List<Space> space_list = service.searchList(searchWord);
		logger.info("result : {}.", space_list.size());
		
		model.addAttribute("space_list",space_list);
		
		return "main";
	}
	

//	// 공간수정 요청페이지
//		@RequestMapping(value = "/backoffice/space_updateOK.do", method = RequestMethod.POST)
//		public String updateOK(Space space) {
//			logger.info("Welcome updateOK...");
//			logger.info("{}", space);
//			// 공간이미지 가져오기
//			space = service_file.getVO(space);
//			// 이미지 외 정보들 입력
//			int result = service.update(space);
//			logger.info("result : {}", result);
//			if (result == 1) {
//				return "redirect:space_selectOne.do";
//			} else {
//				return "redirect:space_selectOne.do";
//			}
//		}

	// 상세공간등록 페이지로 이동
	@RequestMapping(value = "/backoffice/room_insert.do", method = RequestMethod.GET)
	public String r_insert() {
		logger.info("Welcome insert!");

		return "spacepage/roomPage";
	}

	// 상세공간등록 요청페이지
	@RequestMapping(value = "/backoffice/room_insertOK.do", method = RequestMethod.POST)
	@ResponseBody
	public int r_insertOK(String multiroom) {
		logger.info("Welcome r_insertOK!");
		logger.info("multiroom:{}",multiroom);
		multiroom = "[{\"space_num\":\"4\",\"room_name\":\"sss\",\"room_type\":\"회의실4인\",\"room_price\":\"20000\",\"room_detail\":\"ss\"}]";
//		Gson gson = new GsonBuilder().create();
//		Room[] vo_gsons = new GsonBuilder().create().fromJson(multiroom, Room[].class);
		
		List<Room> vos = Arrays.asList(new GsonBuilder().create().fromJson(multiroom, Room[].class));
		for (Room vo : vos) {
			logger.info(vo.toString());
			int result = r_service.insert(vo);
		}
		
		
		
		
		
		
		
		
//		Map<String, Object> map2 = new HashMap<String, Object>();
		
//		// 공간이미지 가져오기
//		multiroom = r_service_file.getVO(room);
//		// 이미지 외 정보들 입력
//		int flag = r_service.insert(multiroom);

		
//		try {
//			JSONParser jsonParse = new JSONParser();
//			JSONArray jarr = new JSONArray();
//			JSONArray itemArray = (JSONArray)jsonParse.parse(multiroom);
//			
//			for (int i = 0; i < itemArray.size(); i++) {
//				JSONObject itemObject = (JSONObject) itemArray.get(i);
//				long space_num = Long.parseLong(String.valueOf(itemObject.get("space_num")));				
//				String room_name = String.valueOf(itemObject.get("room_name"));
//				String room_type = String.valueOf(itemObject.get("room_type"));
//				int room_price = Integer.parseInt(String.valueOf(itemObject.get("room_price")));
//				String room_detail = String.valueOf(itemObject.get("room_detail"));
//				String work_start = String.valueOf(itemObject.get("work_start"));
//				String work_end = String.valueOf(itemObject.get("work_end"));
//				String room_img = String.valueOf(itemObject.get("room_img"));
//				
//				itemArray.add(space_num);
//				itemArray.add(room_name);
//				itemArray.add(room_type);
//				itemArray.add(room_price);
//				itemArray.add(room_detail);
//				itemArray.add(work_start);
//				itemArray.add(work_end);
//				itemArray.add(room_img);
////				int result = r_service.insert(map);
////				logger.info("result:{}",result);
//				logger.info("itemArray:{}",itemArray);
//				
//			}
//			
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		

		return 1;
	}

	// 공간상세 페이지로 이동
	@RequestMapping(value = "/backoffice/room_update.do", method = RequestMethod.GET)
	public String r_update() {
		logger.info("Welcome update!");

		return "backoffice/space/space_selectOne";
	}

	// 공간수정 요청페이지
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
