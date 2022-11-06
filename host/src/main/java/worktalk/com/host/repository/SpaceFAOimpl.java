package worktalk.com.host.repository;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import worktalk.com.host.domain.Space;

@Repository
public class SpaceFAOimpl implements SpaceFAO {

	private static final Logger logger = LoggerFactory.getLogger(SpaceFAOimpl.class);

	@Autowired
	private ServletContext context;

	@Override
	public Space getVO(Space space) {
		logger.info("getVO....");
		logger.info("{}", space);

		// vo에 맵핑을 위한 빈등록-root-context.xml << multipartResolver
		logger.info("{} byte", space.getMultipartFile().getSize());

		if (space.getMultipartFile().getSize() > 0) { 
			String originFilename = space.getMultipartFile().getOriginalFilename();
			logger.info("{}", originFilename);
			space.setSpace_img(originFilename);

			String dir_path = context.getRealPath("resources/upload/space");
			logger.info(dir_path);

			File saveFile = new File(dir_path, space.getSpace_img());

			try {
				space.getMultipartFile().transferTo(saveFile);// 원본이미지 저장

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			if (space.getSpace_img() == null)
				space.setSpace_img("space.jpg");
		}
		return space;
	}

}
