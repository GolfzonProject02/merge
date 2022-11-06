package worktalk.com.host.repository;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import worktalk.com.host.domain.Room;

@Repository
public class RoomFAOimpl implements RoomFAO {

	private static final Logger logger = LoggerFactory.getLogger(RoomFAOimpl.class);

	@Autowired
	private ServletContext context;

	@Override
	public Room getVO(Room room) {
		logger.info("getVO....");
		logger.info("{}", room);

		// vo에 맵핑을 위한 빈등록-root-context.xml << multipartResolver
		logger.info("{} byte", room.getMultipartFile().getSize());

		if (room.getMultipartFile().getSize() > 0) { 
			String originFilename = room.getMultipartFile().getOriginalFilename();
			logger.info("{}", originFilename);
			room.setRoom_img(originFilename);

			String dir_path = context.getRealPath("resources/upload/room");
			logger.info(dir_path);

			File saveFile = new File(dir_path, room.getRoom_img());

			try {
				room.getMultipartFile().transferTo(saveFile);// 원본이미지 저장

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			if (room.getRoom_img() == null)
				room.setRoom_img("room.jpg");
		}
		return room;
	}

}
