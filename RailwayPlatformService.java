import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.Duration;
//import java.io.File;
import java.io.Reader;
//import java.nio.file.FileSystems;
import java.nio.file.Files;
//import java.nio.file.Path;
import java.nio.file.Paths;
import com.google.gson.Gson;

public class RailwayPlatformService {
	// Set this to true to skip the slow tests for faster test runs. To finish the challenge you must set this to false.
	public static boolean skipSlowTests = false;
	
	public List<TrainStop> convertBoardsToTrainStops(List<Board> arrivalList, List<Board> departureList) {
	
		try {
	        // create Gson instance
	        Gson gson = new Gson();

	        // create a reader
	        Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\atan_\\Desktop\\Praktikum\\Deutsche Bahn\\ulm_arrivals_Test.json"));
	        Reader reader2 = Files.newBufferedReader(Paths.get("C:\\Users\\atan_\\Desktop\\Praktikum\\Deutsche Bahn\\ulm_departure_Test.json"));
	        // convert JSON array to list of books
	        List<Board> arrivalListHelp = Arrays.asList(gson.fromJson(reader, Board[].class));
	        
	        for(int i = 0; i < arrivalListHelp.size(); i++) {
	        	
	        	arrivalList.add(arrivalListHelp.get(i));
	        }
	        
	        List<Board> departureListHelp = Arrays.asList(gson.fromJson(reader2, Board[].class));
	        
	        for(int i = 0; i < departureListHelp.size(); i++) {
	        	
	        	departureList.add(departureListHelp.get(i));
	        }

	        // print books
	        //arrivals.forEach(System.out::println);
	        //departures.forEach(System.out::println);

	        // close reader
	        reader.close();
	        reader2.close();

	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
		
		LinkedList<TrainStop> listTStops = new LinkedList<TrainStop>();
		String arrivalBoardName = "";
		String departureBoardName = "";
		LocalDateTime arrivalTime = null;
		LocalDateTime departureTime = null;
		Duration diff = null;
		Board arrivalBoard = null;
		Board departureBoard = null;
		boolean arrivalHasAnDeparture = false;
		//LinkedList<Integer> listOfDeparturesWithArrivals = new LinkedList<Integer>();
		boolean arrivalBevoreDeparture = false;
		boolean arrivalAndDepartureNotEqual = false;
		boolean diffMax = false;
		//boolean hinzufuegen = false;
		
		for(int i = 0; i < arrivalList.size(); i++){
			
			arrivalBoard = arrivalList.get(i);
			arrivalBoardName = arrivalBoard.name;
			arrivalTime = LocalDateTime.parse(arrivalBoard.dateTime);
			
			int lHelp = 0;
			for(int l = 0; l < departureList.size(); l++){

				//System.out.println("Zweite For-Schleife");
				
				//try {
					//Thread.sleep(1000);
				//} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				//}
				
				lHelp = l;
				departureBoard = departureList.get(l);
				departureBoardName = departureBoard.name;
				departureTime = LocalDateTime.parse(departureBoard.dateTime);
				diff = Duration.between(arrivalTime, departureTime);
				
				if(arrivalBoardName.equals(departureBoardName)) {
					
					arrivalBevoreDeparture = arrivalTime.isBefore(departureTime);
					arrivalAndDepartureNotEqual = !arrivalTime.isEqual(departureTime);
					if(arrivalBevoreDeparture) {
						
						if(arrivalAndDepartureNotEqual) {
							
							//Es müssen auch die Tage und die Stunden verglichen werden
							if(diff.toMinutes() <= 60 && diff.toDays() < 1 && diff.toMinutes() > 0 && diff.toHours() < 1){
								//System.out.println("diff minutes: " + diff.toMinutes() + "diff days: " + diff.toDays());
								diffMax = true;
							}
							
							if(diffMax) {
								
								//hinzufuegen = true;
								arrivalHasAnDeparture = true;
								//System.out.println("Test" + l);
								//System.out.println("Hinzugefügt wird: " + arrivalBoardName + " " + arrivalTime + " " + departureTime);
								listTStops.add(new TrainStop(arrivalBoardName, arrivalTime, departureTime));
								//listOfDeparturesWithArrivals.add(l);
								System.out.println("Test: " + l);
								departureList.remove(l);
								lHelp = 0;
								diffMax = false;
								arrivalAndDepartureNotEqual = false;
								arrivalBevoreDeparture = false;
								//hinzufuegen = false;
							}
						}
					}
				}
				//if(hinzufuegen) {
					
					
				//}
				else{
					
					arrivalBevoreDeparture = false;
					arrivalAndDepartureNotEqual = false;
					diffMax = false;
				
				}
				
			}

			if(lHelp == (departureList.size()-1) && !arrivalHasAnDeparture) {
				
				departureTime = arrivalTime.plusMinutes(20);
				TrainStop newTrainStop = new TrainStop(arrivalBoardName, arrivalTime, departureTime);
				listTStops.add(newTrainStop);
				//System.out.println("Hinzugefügt wird: " + arrivalBoardName + " " + arrivalTime + " " + departureTime);
				arrivalHasAnDeparture = false;
				diffMax = false;
				arrivalAndDepartureNotEqual = false;
				arrivalBevoreDeparture = false;
			}
			//else {
				//arrivalHasAnDeparture = false;
			//}
		}
		
		for(int g = 0; g < departureList.size(); g++){
			
			//System.out.println("For-Schleife für departure");
			
			//try {
				//Thread.sleep(1000);
			//} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			//}
			
			//if(!listOfDeparturesWithArrivals.contains(g)) {
				
				departureBoard = departureList.get(g);
				departureBoardName = departureBoard.name;
				departureTime = LocalDateTime.parse(departureBoard.dateTime);
				
				arrivalTime = departureTime.minusMinutes(20);
				TrainStop newTrainStop = new TrainStop(departureBoardName, arrivalTime, departureTime);
				listTStops.add(newTrainStop);
				//System.out.println("Hinzugefügt wird: " + departureBoardName + " " + arrivalTime + " " + departureTime);
			//}
		}
		System.out.println("Number of Trainstops: " + listTStops.size());
		return listTStops;
	}

	public int calculateMinimumNumberOfPlatforms(List<TrainStop> trainStops) {
		
		TrainStop ts = null;
		TrainStop ts2 = null;
		int numberOfPlatforms = 0;
		int help = 1;
		int l = 0;
		boolean canContinueLoopOneTime = false;
		boolean canContinueLoop = true;
		int i = 0;
		
		while(trainStops.size() != 0){
			
			System.out.println("Erste Schleife: " + i);
			ts = trainStops.remove(0);
			//System.out.println("Number of TrainStops: " + trainStops.size());
			//System.out.println("Erste For-Schleife: " + i);
			l = 0;
			if(trainStops.size() != 0) {
				canContinueLoop = true;
			}
			else {
				canContinueLoop = false;
			}
			
			while(canContinueLoop) {
				
				/*try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			
				System.out.println("Zweite For-Schleife: " + l + " Elemente: " + trainStops.size());
				ts2 = trainStops.get(l);
				
				if((ts.arrival.isBefore(ts2.arrival) || ts.arrival.isEqual(ts2.arrival)) && (ts.departure.isAfter(ts2.departure) 
						|| ts.departure.isEqual(ts2.departure))) {
				
				//if((ts.arrival.isBefore(ts2.arrival)) && ts.departure.isAfter(ts2.departure)) {
						
					System.out.println("Die verglichene Fahrt kann rausgenommen werden.");
					help++;
					System.out.println("help: " + help);
					trainStops.remove(l);
					l--;
				}
				else if(ts.arrival.isBefore(ts2.departure) && ts.arrival.isAfter(ts2.arrival)){
					
					System.out.println("Wird von links geschnitten: " + l);
					help++;
				}
				else if(ts.departure.isAfter(ts2.arrival) && ts.arrival.isBefore(ts2.arrival)){
					System.out.println("Wird von rechts geschnitten: " + l);			
					help++;
				}
				else if(ts.arrival.isEqual(ts2.arrival) || ts.arrival.isEqual(ts2.departure) || ts.departure.isEqual(ts2.arrival)
						|| ts.departure.isEqual(ts2.departure)) {
					
					System.out.println("Es gibt zwei Züge mit einer identischen Ankunft oder Abfahrt." + l);
					help++;
				}
				if(canContinueLoopOneTime) {
					canContinueLoop = false;
				}
				if(trainStops.size() == 1) {
					canContinueLoopOneTime = true;
				}
				if(trainStops.size() == 0 || l == trainStops.size()-1) {
					canContinueLoop = false;
				}
				l++;
			}
			
			System.out.println("help: " + help);
			System.out.println("numberOfPlatforms: " + numberOfPlatforms);
			if(help > numberOfPlatforms) {
				
				numberOfPlatforms = help;
				help = 1;
			}
			
			help = 1;
			i++;
		}
		return numberOfPlatforms;
	}
}


