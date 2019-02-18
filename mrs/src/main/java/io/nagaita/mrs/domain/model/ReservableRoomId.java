package io.nagaita.mrs.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservableRoomId implements Serializable {

	private Integer roomId;
	@Column(columnDefinition = "DATE")
	private LocalDate reservedDate;

}