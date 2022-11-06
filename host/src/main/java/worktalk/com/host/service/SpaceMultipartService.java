package worktalk.com.host.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worktalk.com.host.domain.Space;
import worktalk.com.host.repository.SpaceFAO;

@Service
public class SpaceMultipartService {
	private static final Logger logger = LoggerFactory.getLogger(SpaceMultipartService.class);
	
	@Autowired
	SpaceFAO fao;
	
	public SpaceMultipartService() {
		logger.info("SpaceMultipartService()...");
	}

	public Space getVO(Space space) {
		logger.info("getVO()...");
		return fao.getVO(space);//return 1을 받고 controller에 1보내줌
	}
	
}
