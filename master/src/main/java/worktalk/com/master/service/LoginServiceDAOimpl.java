package worktalk.com.master.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worktalk.com.master.domain.Master;
import worktalk.com.master.repository.MasterLoginDAO;

@Service
public class LoginServiceDAOimpl implements LoginService{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceDAOimpl.class);
	
	@Autowired
	MasterLoginDAO loginDao;
	
	public LoginServiceDAOimpl() {
		logger.info("UserLogInServiceDAOimpl()....");
	}

	@Override
	public Master login(Master master) {
		logger.info("UserLogInServiceDAOimpl()....");
		return loginDao.logIn(master);
	}

}
