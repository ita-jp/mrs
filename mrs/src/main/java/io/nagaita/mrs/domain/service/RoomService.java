package io.nagaita.mrs.domain.service;

import io.nagaita.mrs.domain.model.MeetingRoom;
import io.nagaita.mrs.domain.model.ReservableRoom;
import io.nagaita.mrs.domain.repository.MeetingRoomRepository;
import io.nagaita.mrs.domain.repository.ReservableRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoomService {

	@Autowired
	private ReservableRoomRepository reservableRoomRepository;
	@Autowired
	private MeetingRoomRepository meetingRoomRepository;

	public List<ReservableRoom> findReservableRooms(LocalDate date) {
		return reservableRoomRepository.findByReservableRoomId_reservedDateOrderByReservableRoomId_roomIdAsc(date);
	}

	public Optional<MeetingRoom> findMeetingRoom(Integer roomId) {
		return meetingRoomRepository.findById(roomId);
	}

}
