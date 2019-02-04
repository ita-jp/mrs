package io.nagaita.mrs.domain.service;

import io.nagaita.mrs.domain.model.ReservableRoom;
import io.nagaita.mrs.domain.repository.ReservableRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService {

	@Autowired
	private ReservableRoomRepository reservableRoomRepository;

	public List<ReservableRoom> findReservableRooms(LocalDate date) {
		return reservableRoomRepository.findByReservableRoomId_reservedDateOrderByReservableRoomId_roomIdAsc(date);
	}

}
