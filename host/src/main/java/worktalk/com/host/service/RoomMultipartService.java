package worktalk.com.host.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worktalk.com.host.domain.Room;
import worktalk.com.host.repository.RoomFAO;

@Service
public class RoomMultipartService {
	private static final Logger logger = LoggerFactory.getLogger(RoomMultipartService.class);
	
	@Autowired
	RoomFAO fao;
	
	public RoomMultipartService() {
		logger.info("RoomMultipartService()...");
	}

	public Room getVO(Room room) {
		logger.info("getVO()...");
		return fao.getVO(room);//return 1을 받고 controller에 1보내줌
	}
	
//	public Room getVO(Map<String, Room> map) {
//		logger.info("getVO()...");
//		return fao.getVO(map);//return 1을 받고 controller에 1보내줌
//	}
	
}
