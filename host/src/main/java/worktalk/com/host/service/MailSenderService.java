package worktalk.com.host.service;

import worktalk.com.host.domain.Host;

/**
 * 
 * @author Juhee Fred Lee (이주희)
 * Service interface for sending valdation email
 * 
 */

public interface MailSenderService {
	
	public int creatingRandomCode();
	public int checkDuplicatedEmail(Host host);
	public String joinMail(String email);
	public void sendMail(String setFrom, String toMail, String title, String content);

}
