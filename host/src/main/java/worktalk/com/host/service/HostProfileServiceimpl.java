package worktalk.com.host.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worktalk.com.host.domain.Host;
import worktalk.com.host.repository.HostDAO;

@Service
public class HostProfileServiceimpl implements HostProfileService {
	
	private static final Logger logger = LoggerFactory.getLogger(HostProfileServiceimpl.class);
	
	@Autowired
	HostDAO dao;
	
	public HostProfileServiceimpl() {
		logger.info("ProfileServiceimpl()....");
	}

	@Override
	public int update(Host host) {
		logger.info("update()....");
		
		return dao.update(host);
	}

	@Override
	public int leave(Host host) {
		logger.info("leave()....");
		logger.info("user: {}",host);

		return dao.leave(host);
	}
	
	/*
	 * Select certain singular row that matches number
	 * returns dao.selectOne
	 */
	@Override
	public Host findByName(Host host) {
		logger.info("findByName()....");
		logger.info("{}", host);
		
		return dao.findByName(host);
	}

}
