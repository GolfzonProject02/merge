package worktalk.com.master.repository;

import worktalk.com.master.domain.Penalty;

public interface PenaltyDAO {
	
	public int insert(Penalty penalty);
	public int delete(Penalty penalty);

}
