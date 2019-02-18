package io.nagaita.mrs.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class ReservableRoom implements Serializable {

	@EmbeddedId
	private ReservableRoomId reservableRoomId;
	@ManyToOne
	@JoinColumn(name = "room_id", insertable = false, updatable = false)
	@MapsId("roomId")
	private MeetingRoom meetingRoom;

	public ReservableRoom(ReservableRoomId reservableRoomId) {
		this.reservableRoomId = reservableRoomId;
	}

}
