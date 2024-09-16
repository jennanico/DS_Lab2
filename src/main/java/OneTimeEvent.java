import java.util.GregorianCalendar;
import calendar.MeetingCalendar;

//stub
public class OneTimeEvent extends CalendarEvent
{

	public OneTimeEvent(String desc, String loc, GregorianCalendar start, GregorianCalendar end)
	{
		super(desc, loc, start, end);
	}
	
	@Override
	public void scheduleEvent(MeetingCalendar cal)
	{
	}
	

}
