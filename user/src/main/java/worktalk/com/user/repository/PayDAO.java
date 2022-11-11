package worktalk.com.user.repository;

import java.util.List;

import worktalk.com.user.domain.Pay;

/**
 * 
 * @author Juhee Fred Lee (이주희)
 * Repository interface for payment data crud
 * 
 */

public interface PayDAO {
	
	public int insert(Pay pay);
	public List<Pay> findByName(Pay pay);
	public List<Pay> findByNum(Pay pay);
	public List<Pay> findHistoryByName(Pay pay);
	public List<Pay> findDateUidByRnum(Pay pay);

}
