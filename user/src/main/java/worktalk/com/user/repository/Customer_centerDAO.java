package worktalk.com.user.repository;

import java.util.List;

import worktalk.com.user.domain.Customer_center;

public interface Customer_centerDAO {

	public int insert(Customer_center customer_center);

	public int update(Customer_center customer_center);

	public int delete(Customer_center customer_center);

	public List<Customer_center> selectAll();

	public List<Customer_center> searchList(String searchKey, String searchWord);
}
