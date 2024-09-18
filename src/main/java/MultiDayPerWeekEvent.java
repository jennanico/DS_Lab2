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
	

		while (myStart.before(this.getRepeatUntil())) 
		{
			for (int i = 0; i < 7 && myStart.before(this.getRepeatUntil()); i++)
			{
				for (int j = 0; j < this.getDays().length && myStart.before(this.getRepeatUntil()); j++)
				{
					if (myStart.get(myStart.DAY_OF_WEEK) == this.getDays()[j])
					{
						Meeting m = new Meeting(this.getDescription(), this.getLocation(), myStart, myEnd);
						cal.addMeeting(m);
					}
				}	
				
				myStart.add(myStart.DAY_OF_MONTH, 1);
				myEnd.add(myEnd.DAY_OF_MONTH, 1);
			}
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
