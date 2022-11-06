package worktalk.com.master.service;

import java.util.List;

import worktalk.com.master.domain.Master;
import worktalk.com.master.domain.Penalty;

public interface PenaltyService {
	
	public int insert(Penalty penalty);
	public int delete(Penalty penalty);
	public List<Master> findAll();
	
}
