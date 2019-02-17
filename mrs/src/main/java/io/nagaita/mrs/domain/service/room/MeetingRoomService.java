package io.nagaita.mrs.domain.service.room;

import io.nagaita.mrs.domain.model.MeetingRoom;
import io.nagaita.mrs.domain.repository.MeetingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeetingRoomService {

    @Autowired
    private MeetingRoomRepository meetingRoomRepository;

    public MeetingRoom create(MeetingRoom room) {
        return meetingRoomRepository.save(room);
    }

    public List<MeetingRoom> findAll() {
        return meetingRoomRepository.findAll();
    }

    public Optional<MeetingRoom> findOne(Integer id) {
        return meetingRoomRepository.findById(id);
    }

    public MeetingRoom update(MeetingRoom room) {
        return meetingRoomRepository.save(room);
    }
}
