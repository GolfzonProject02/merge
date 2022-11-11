package worktalk.com.user.repository;

import java.util.List;

import worktalk.com.user.domain.Reservation;

/**
 * 
 * @author Juhee Fred Lee (이주희)
 * Repository interface for reservation data crud
 * 
 */

public interface ReservationDAO {
	
	public Reservation reserve(Reservation reservation);
	public int cancel(Reservation reservation);
	public int delete(Reservation reservation);
	public List<Reservation> isBooked(Reservation reservation);
	public Reservation findReservationByNum(Reservation reservation);
	public long findSpaceNum(Reservation reservation);
	public List<Reservation> findReservationByName(Reservation reservation);
	public List<Reservation> findReservationByStatus(Reservation reservation);
	
}
