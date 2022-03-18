import java.util.LinkedList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		
		RailwayPlatformService rs = new RailwayPlatformService();
		
		//System.out.println("Test");
		
		List<Board> arrivalList = new LinkedList<Board>();
		
		List<Board> departureList = new LinkedList<Board>();

		List<TrainStop> lts = rs.convertBoardsToTrainStops(arrivalList, departureList);
		//2021-03-06T11:16
		
		/*LocalDateTime ar1 = LocalDateTime.of(2021, 3, 6, 11, 16, 4);
		LocalDateTime dp1 = LocalDateTime.of(2021, 3, 6, 11, 36, 4);
		
		LocalDateTime ar2 = LocalDateTime.of(2021, 3, 6, 11, 17, 4);
		LocalDateTime dp2 = LocalDateTime.of(2021, 3, 6, 11, 35, 4);
		
		LocalDateTime ar3 = LocalDateTime.of(2021, 3, 6, 11, 35, 4);
		LocalDateTime dp3 = LocalDateTime.of(2021, 3, 6, 12, 0, 0);
		
		TrainStop tr1 = new TrainStop("ICE 513", ar1, dp1);
		TrainStop tr2 = new TrainStop("ICE 513", ar2, dp2);
		TrainStop tr3 = new TrainStop("ICE 513", ar3, dp3);
		
		List<TrainStop> listeTest = new LinkedList<TrainStop>();
		listeTest.add(tr1);
		listeTest.add(tr2);
		listeTest.add(tr3);
		
		listeTest.remove(0);*/
		
		//System.out.println("Jahr: " + ar1.getYear() + " Monat: " + ar1.getMonth() + " Tag: " + ar1.getDayOfWeek() + " Stunde: " + ar1.getHour()
		//+ " Minute: " + ar1.getMinute() + " Sekunde: " + ar1.getSecond());
		
		int numberOfMinimalTrainStops = rs.calculateMinimumNumberOfPlatforms(lts);
		
		System.out.println(numberOfMinimalTrainStops);
	}
}
