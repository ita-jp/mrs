package io.nagaita.mrs.domain.repository;

import io.nagaita.mrs.domain.model.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Integer> {
}
