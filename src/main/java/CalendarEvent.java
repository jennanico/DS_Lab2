import java.util.GregorianCalendar;
import calendar.MeetingCalendar;

// stub
public abstract class CalendarEvent 
{
	private String Description;
	private String location;
	private GregorianCalendar startTime;
	private GregorianCalendar endtime;
	
	public CalendarEvent(String desc, String loc, GregorianCalendar start, GregorianCalendar end)
	{	
	}
	
	public abstract void scheduleEvent(MeetingCalendar cal);
	
	// getters and setters
	public String getDescription()
	{
		return Description;
	}

	public void setDescription(String description)
	{
		Description = description;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public GregorianCalendar getStartTime()
	{
		return startTime;
	}

	public void setStartTime(GregorianCalendar startTime)
	{
		this.startTime = startTime;
	}

	public GregorianCalendar getEndTime()
	{
		return endtime;
	}

	public void setEndtime(GregorianCalendar endtime)
	{
		this.endtime = endtime;
	}
	
}
