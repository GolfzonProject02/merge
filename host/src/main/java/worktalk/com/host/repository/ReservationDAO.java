package worktalk.com.host.repository;

import java.util.List;

import worktalk.com.host.domain.Reservation;

public interface ReservationDAO {
	
	public int updateStatus(Reservation reservation);
	public int cancel(Reservation reservation);
	public Reservation findReservationByNum(Reservation reservation);
	public List<Reservation> findReservationByName(Reservation reservation);
	public List<Reservation> findReservationByKeywords(Reservation reservation);
	
}
