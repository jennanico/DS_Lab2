import java.util.GregorianCalendar;

import calendar.MeetingCalendar;


//stub
public class MultiDayPerWeekEvent extends CalendarEvent
{
	
	private GregorianCalendar repeatUntil;
	private int[] Days;
	
	public MultiDayPerWeekEvent(String desc, String loc, GregorianCalendar start, GregorianCalendar end, GregorianCalendar repeat, int days[])
	{
		super(desc, loc, start, end);
		this.repeatUntil = repeat;
		this.Days = days;
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

	public int[] getDays()
	{
		return Days;
	}

	public void setDays(int[] days)
	{
		this.Days = days;
	}
	
	

}
