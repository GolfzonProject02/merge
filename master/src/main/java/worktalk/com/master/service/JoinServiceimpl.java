package worktalk.com.master.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worktalk.com.master.domain.Master;
import worktalk.com.master.repository.MasterDAO;

@Service
public class JoinServiceimpl implements JoinService{
	
	private static final Logger logger = LoggerFactory.getLogger(JoinServiceimpl.class);
	
	@Autowired
	MasterDAO dao;

	public JoinServiceimpl() {
		logger.info("JoinServiceimpl()....");
	}

	@Override
	public int join(Master master) {
		logger.info("join()....");
		logger.info("{}", master);
		
		return dao.join(master);
	}

	@Override
	public int checkDuplicatedName(Master master) {
		logger.info("join()....");
		logger.info("{}", master);
		
		Master result = dao.findByName(master);
		
		if (result == null) {
			return 0;
		} else {
			return 1;
		} 
	}

}
