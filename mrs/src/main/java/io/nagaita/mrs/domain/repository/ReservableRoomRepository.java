package io.nagaita.mrs.domain.repository;

import io.nagaita.mrs.domain.model.ReservableRoom;
import io.nagaita.mrs.domain.model.ReservableRoomId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservableRoomRepository extends JpaRepository<ReservableRoom, ReservableRoomId> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<ReservableRoom> findOneForUpdateByReservableRoomId(ReservableRoomId reservableRoomId);

	@Query("SELECT DISTINCT x FROM ReservableRoom x LEFT JOIN FETCH x.meetingRoom WHERE x.reservableRoomId.reservedDate = :date ORDER BY x.reservableRoomId.roomId ASC")
	List<ReservableRoom> findByReservableRoomId_reservedDateOrderByReservableRoomId_roomIdAsc(@Param("date") LocalDate reservedDate);

}
