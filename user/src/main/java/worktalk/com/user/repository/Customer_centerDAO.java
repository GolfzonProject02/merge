package worktalk.com.user.repository;

import java.util.List;

import worktalk.com.user.domain.Customer_center;

public interface Customer_centerDAO {

	public int insert(Customer_center customer_center); //1대1문의 등록

	public int update(Customer_center customer_center); //1대1문의 수정

	public int delete(Customer_center customer_center); //1대1문의 삭제

	public List<Customer_center> findByWriter(String writer); //사용자가 작성한 1대1문의 출력

	public List<Customer_center> searchList(String searchKey, String searchWord); //1대1문의 검색
}
