package io.nagaita.mrs.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Data
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;
    private LocalTime startTime;
    private LocalTime endTime;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "reserved_date"),
            @JoinColumn(name = "room_id")
    })
    private ReservableRoom reservableRoom;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public boolean isOverlap(Reservation other) {
        if (!Objects.equals(reservableRoom.getReservableRoomId(), other.getReservableRoom().getReservableRoomId())) {
            return false;
        }

        if (startTime.equals(other.startTime) && endTime.equals(other.endTime)) {
            return true;
        }

        return other.endTime.isAfter(startTime) && endTime.isAfter(other.startTime);
    }
}
