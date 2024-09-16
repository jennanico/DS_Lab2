import java.util.GregorianCalendar;

import calendar.MeetingCalendar;


//stub
public class WeeklyEvent extends CalendarEvent
{

	private GregorianCalendar repeatUntil;
	
	public WeeklyEvent(String desc, String loc, GregorianCalendar start, GregorianCalendar end, GregorianCalendar repeat)
	{
		super(desc, loc, start, end);
	}

	@Override
	public void scheduleEvent(MeetingCalendar cal)
	{

	}

	public GregorianCalendar getRepeatUntil()
	{
		return repeatUntil;
	}

	public void setRepeatUntil(GregorianCalendar repeatUntil)
	{
		this.repeatUntil = repeatUntil;
	}
	
	
	

}
