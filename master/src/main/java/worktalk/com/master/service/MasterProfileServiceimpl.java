package worktalk.com.master.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worktalk.com.master.domain.Master;
import worktalk.com.master.repository.MasterDAO;

@Service
public class MasterProfileServiceimpl implements MasterProfileService {
	
	private static final Logger logger = LoggerFactory.getLogger(MasterProfileServiceimpl.class);
	
	@Autowired
	MasterDAO dao;
	
	public MasterProfileServiceimpl() {
		logger.info("ProfileServiceimpl()....");
	}

	@Override
	public int update(Master master) {
		logger.info("update()....");
		
		return dao.update(master);
	}

	@Override
	public int leave(Master master) {
		logger.info("leave()....");
		logger.info("master: {}",master);

		return dao.leave(master);
	}
	
	/*
	 * Select certain singular row that matches number
	 * returns dao.selectOne
	 */
	@Override
	public Master findByName(Master master) {
		logger.info("findByName()....");
		logger.info("{}", master);
		
		return dao.findByName(master);
	}

}
