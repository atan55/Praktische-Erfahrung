import java.time.LocalDateTime;
import java.util.Objects;

public class TrainStop {
	public String trainNumber;
	public LocalDateTime arrival;
	public LocalDateTime departure;

	public TrainStop(String trainNumber, LocalDateTime arrival, LocalDateTime departure) {
		this.trainNumber = trainNumber;
		this.arrival = arrival;
		this.departure = departure;
	}

	public String getTrainNumber() {
		
		return this.trainNumber;
	}
	
	@Override
	public String toString() {
		return trainNumber + " " + arrival + " - " + departure;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TrainStop trainStop = (TrainStop) o;
		return Objects.equals(trainNumber, trainStop.trainNumber) &&
				Objects.equals(arrival, trainStop.arrival) &&
				Objects.equals(departure, trainStop.departure);
	}

	@Override
	public int hashCode() {
		return Objects.hash(trainNumber, arrival, departure);
	}
}
