package io.nagaita.mrs.domain.repository;

import io.nagaita.mrs.domain.model.ReservableRoom;
import io.nagaita.mrs.domain.model.ReservableRoomId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservableRoomRepository extends JpaRepository<ReservableRoom, ReservableRoomId> {

	List<ReservableRoom> findByReservableRoomId_reservedDateOrderByReservableRoomId_roomIdAsc(LocalDate reservedDate);

}
