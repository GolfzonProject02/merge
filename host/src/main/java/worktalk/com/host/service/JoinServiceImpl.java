package worktalk.com.host.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worktalk.com.host.domain.Host;
import worktalk.com.host.repository.HostDAO;

/**
 * 
 * @author Juhee Fred Lee
 * Service class for host data CRUD
 *
 */
@Service
public class JoinServiceImpl implements JoinService {
	private static final Logger logger = LoggerFactory.getLogger(JoinServiceImpl.class);

	@Autowired
	HostDAO dao;
	
	public JoinServiceImpl() {
		logger.info("JoinServiceImpl()....");
	}
	
	/*
	 * Add Member Data to MEMBER Table
	 * return dao.join
	 */
	@Override
	public int join(Host host) {
		logger.info("join()....");
		logger.info("{}", host);
		return dao.join(host);
	}

	/*
	 * Checks whether Member Table has the same email address
	 * Returns dao.checkDuplicateName
	 */
	@Override
	public int checkDuplicatedName(Host host) {
		logger.info("checkDuplicatedName()....");
		logger.info("{}", host);
		return dao.checkDuplicatedName(host);
	}

}
