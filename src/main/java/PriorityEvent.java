import java.util.GregorianCalendar;

import calendar.Meeting;
import calendar.MeetingCalendar;

public class PriorityEvent extends CalendarEvent
{

	public PriorityEvent(String desc, String loc, GregorianCalendar start, GregorianCalendar end)
	{
		super(desc, loc, start, end);
	}

	@Override
	public void scheduleEvent(MeetingCalendar cal)
	{
		Meeting m = new Meeting(this.getDescription(), this.getLocation(), this.getStartTime(), this.getEndTime());
		cal.addMeeting(m, true);
	}

}
