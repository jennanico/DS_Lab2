import java.util.GregorianCalendar;

import calendar.Meeting;
import calendar.MeetingCalendar;


public class WeeklyEvent extends CalendarEvent
{

	private GregorianCalendar repeatUntil;
	
	public WeeklyEvent(String desc, String loc, GregorianCalendar start, GregorianCalendar end, GregorianCalendar repeat)
	{
		super(desc, loc, start, end);
		this.repeatUntil = repeat;
	}

	@Override
	public void scheduleEvent(MeetingCalendar cal)
	{
		GregorianCalendar myStart = (GregorianCalendar) this.getStartTime().clone();
		GregorianCalendar myEnd = (GregorianCalendar) this.getEndTime().clone();

		while (myStart.before(this.getRepeatUntil())) 
		{
			Meeting m = new Meeting(this.getDescription(), this.getLocation(), myStart, myEnd);
			cal.addMeeting(m);
			
			myStart.add(myStart.WEEK_OF_MONTH, 1);
			myEnd.add(myEnd.WEEK_OF_MONTH, 1);
		}
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
