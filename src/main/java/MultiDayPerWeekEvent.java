import java.util.GregorianCalendar;

import calendar.Meeting;
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
		GregorianCalendar myStart = (GregorianCalendar) this.getStartTime().clone();
		GregorianCalendar myEnd = (GregorianCalendar) this.getEndTime().clone();
	
		myStart.clear(myStart.DAY_OF_MONTH);
		myEnd.clear(myEnd.DAY_OF_MONTH);
		
		while (myStart.before(this.getRepeatUntil())) 
		{
			for (int i = 0; i < this.getDays().length && myStart.before(this.getRepeatUntil()); i++)
			{
				myStart.set(myStart.DAY_OF_WEEK, this.getDays()[i]);
				myEnd.set(myEnd.DAY_OF_WEEK, this.getDays()[i]);
				myStart.getTime();
				myEnd.getTime();
				
				Meeting m = new Meeting(this.getDescription(), this.getLocation(), myStart, myEnd);
				cal.addMeeting(m);	
			}
			
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

	public int[] getDays()
	{
		return Days;
	}

	public void setDays(int[] days)
	{
		this.Days = days;
	}
	
	

}
