package worktalk.com.user.service;

import worktalk.com.user.domain.User;

/**
 * 
 * @author Juhee Fred Lee (이주희)
 * Service interface for sending valdation email
 * 
 */

public interface MailSenderService {
	
	public int creatingRandomCode();
	public int checkDuplicatedEmail(User user);
	public String joinMail(String email);
	public void sendMail(String setFrom, String toMail, String title, String content);

}
