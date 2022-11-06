package worktalk.com.host.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Roomtype {
	meetingroom_4("회의실(4인)"),
	meetingroom_6("회의실(6인)"),
	meetingroom_8("회의실(8인~10인)"),
	meetingroom_20("회의실(20인)"),
	desk("데스크"),
	office("오피스");
	
	@Getter
	private final String label;
}
