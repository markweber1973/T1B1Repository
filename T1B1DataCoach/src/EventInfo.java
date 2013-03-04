
public class EventInfo {
	private String name;
	private String date;
	private String place;
	private String location;
	private boolean international;
	
	public EventInfo(String name, String date, String place, String location, boolean international)
	{
		this.name = name;
		this.date = date;
		this.place = place;
		this.location = location;
		this.international = international;
	}
	
	public String getName()
	{
		return name;
	}

	public String getDate()
	{
		return date;
	}
	
	public String getPlace()
	{
		return place;
	}
	
	public String getLocation()
	{
		return location;
	}	
	
	public boolean getInternational()
	{
		return international;
	}	
	
	public void log()
	{
		System.out.println("name: " + name);
		System.out.println("date: " + date);
		System.out.println("place: " + place);
		System.out.println("location: " + location);	
		System.out.println("international: " + international);
	}
}
