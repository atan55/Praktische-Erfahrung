public class Board {
	public String name;
	public String type;
	public String boardId;
	public String stopId;
	public String dateTime;
	public String origin;
	public String track;
	public String detailsId;

	public Board(String name, String type, String boardId, String stopId, String dateTime, String origin,
	             String track, String detailsId) {
		this.name = name;
		this.type = type;
		this.boardId = boardId;
		this.stopId = stopId;
		this.dateTime = dateTime;
		this.origin = origin;
		this.track = track;
		this.detailsId = detailsId;
	}
	
	//public Board(String name, String dateTime) {
		
		//this.name = name;
		//this.dateTime = dateTime;
	//}

	@Override
	public String toString() {
		return name + " " + dateTime;
	}
}

