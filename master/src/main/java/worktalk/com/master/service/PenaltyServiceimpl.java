package worktalk.com.master.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import worktalk.com.master.domain.Master;
import worktalk.com.master.domain.Penalty;
import worktalk.com.master.repository.MasterDAO;
import worktalk.com.master.repository.PenaltyDAO;

@Service
public class PenaltyServiceimpl implements PenaltyService {
	
	private static final Logger logger = LoggerFactory.getLogger(PenaltyServiceimpl.class);
	
	@Autowired
	PenaltyDAO p_dao;
	@Autowired
	MasterDAO m_dao;

	public PenaltyServiceimpl() {
		logger.info("PenaltyServiceimpl()....");
	}

	@Override
	public int insert(Penalty penalty) {
		logger.info("insert()....");
		logger.info("{}", penalty);
		
		int flag = p_dao.insert(penalty);
		
		if (flag == 1) {
			Master master = new Master();
			master.setName(penalty.getP_name());
			master.setPenalty(1);
			m_dao.update_penalty(master);
		}
		
		return flag;
	}

	@Override
	public int delete(Penalty penalty) {
		logger.info("delete()....");
		logger.info("{}", penalty);
		
		int flag = p_dao.delete(penalty);
		
		if (flag == 1) {
			Master master = new Master();
			master.setName(penalty.getP_name());
			master.setPenalty(0);
			m_dao.update_penalty(master);
		}
		
		return flag;
	}

	@Override
	public List<Master> findAll() {
		logger.info("findAll()....");
		
		return m_dao.findAll();
	}

}
