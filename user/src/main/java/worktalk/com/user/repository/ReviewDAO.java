package worktalk.com.user.repository;

import java.util.List;

import worktalk.com.user.domain.Review;

public interface ReviewDAO {

	public int insert(Review review);

	public int update(Review review);

	public int delete(Review review);

	public List<Review> findByName(String writer);
	
	public List<Review> findBySpaceNum(long space_num);
}
