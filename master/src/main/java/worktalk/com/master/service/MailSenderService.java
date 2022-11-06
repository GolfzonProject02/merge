package worktalk.com.master.service;

import worktalk.com.master.domain.Master;

public interface MailSenderService {
	
	public int creatingRandomCode();
	public int checkDuplicatedEmail(Master master);
	public String joinMail(String email);
	public void sendMail(String setFrom, String toMail, String title, String content);

}
