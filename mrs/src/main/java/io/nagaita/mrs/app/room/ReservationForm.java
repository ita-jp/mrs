package io.nagaita.mrs.app.room;

import io.nagaita.mrs.app.EndTimeMustBeAfterStartTime;
import io.nagaita.mrs.app.ThirtyMinutesUnit;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@EndTimeMustBeAfterStartTime(message = "終了時刻は開始時刻よりあとにしてください")
public class ReservationForm implements Serializable {

	@NotNull(message = "必須です")
	@DateTimeFormat(pattern = "HH:mm")
	@ThirtyMinutesUnit(message = "30分単位で入力してください")
	private LocalTime startTime;

	@NotNull(message = "必須です")
	@DateTimeFormat(pattern = "HH:mm")
	@ThirtyMinutesUnit(message = "30分単位で入力してください")
	private LocalTime endTime;

}
