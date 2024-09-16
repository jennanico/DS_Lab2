import java.util.GregorianCalendar;
import calendar.MeetingCalendar;

//stub
public class PriorityEvent extends CalendarEvent
{

	public PriorityEvent(String desc, String loc, GregorianCalendar start, GregorianCalendar end)
	{
		super(desc, loc, start, end);
	}

	@Override
	public void scheduleEvent(MeetingCalendar cal)
	{
	}

}
