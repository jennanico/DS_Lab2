import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import calendar.Meeting;
import calendar.MeetingCalendar;

class CalendarEventTest
{

	MeetingCalendar calA;
	Meeting A;
	Meeting B;
	Meeting C;
	Meeting D;
	Meeting E;
	Meeting F;
	
	GregorianCalendar startA;
	GregorianCalendar endA;
	GregorianCalendar startB;
	GregorianCalendar startC;
	GregorianCalendar startD;
	GregorianCalendar startE;
	GregorianCalendar startF;
	int[] daysA;
	int[] daysB;
	
	OneTimeEvent oneEventA;
	OneTimeEvent oneEventA2;
	
	PriorityEvent priorEventA;
	PriorityEvent priorEventA2;
	
	WeeklyEvent weekEventA;
	WeeklyEvent weekEventA2;
	
	MultiDayPerWeekEvent multiEventA;
	MultiDayPerWeekEvent multiEventA2;
	MultiDayPerWeekEvent multiEventB;
	
	@BeforeEach
	void setUp() throws Exception
	{
		calA = new MeetingCalendar();
		
		startA = new GregorianCalendar(2024,9,14,8,30);
		endA = new GregorianCalendar(2024,9,14,9,30);
		startB = new GregorianCalendar(2024,9,15,8,30);
		startC = new GregorianCalendar(2024,9,21,8,30);
		startD = new GregorianCalendar(2024,9,28,8,30);
		startE = new GregorianCalendar(2024,9,29,8,30);
		startF = new GregorianCalendar(2024,9,30,8,30);
		int[] daysA = {Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY};
		int[] daysB = {Calendar.SATURDAY, Calendar.SUNDAY};
		
		oneEventA = new OneTimeEvent("Meeting", "Olin", startA, endA);
		oneEventA2 = new OneTimeEvent("Lunch", "Olin", startA, endA);
		
		priorEventA = new PriorityEvent("Lunch", "Young", startA, endA);
		priorEventA2 = new PriorityEvent("Meeting", "Young", startA, endA);

		weekEventA = new WeeklyEvent("Dinner", "Cowan", startA, endA, startD);
		weekEventA2 = new WeeklyEvent("Lunch", "Cowan", startA, endA, startD);
		
		multiEventA = new MultiDayPerWeekEvent("Coffee", "Bingham", startA, endA, startD, daysA);
		multiEventA2 = new MultiDayPerWeekEvent("Biscuits", "Bingham", startA, endA, startD, daysA);
		multiEventB = new MultiDayPerWeekEvent("Tea", "JVAC", startA, endA, startD, daysB);
		
	}

	@Test
	void testGettingAttributes()
	{
		// One Time Event
		assertEquals("Meeting", oneEventA.getDescription());
		assertEquals("Olin", oneEventA.getLocation());
		assertEquals(startA, oneEventA.getStartTime());
		assertEquals(endA, oneEventA.getEndTime());

		// Priority Event
		assertEquals("Lunch", priorEventA.getDescription());
		assertEquals("Young", priorEventA.getLocation());
		assertEquals(startA, priorEventA.getStartTime());
		assertEquals(endA, priorEventA.getEndTime());
		
		// Weekly Event
		assertEquals("Dinner", weekEventA.getDescription());
		assertEquals("Cowan", weekEventA.getLocation());
		assertEquals(startA, weekEventA.getStartTime());
		assertEquals(endA, weekEventA.getEndTime());
		assertEquals(startD, weekEventA.getRepeatUntil());
		
		// Multi Event
		assertEquals("Coffee", multiEventA.getDescription());
		assertEquals("Bingham", multiEventA.getLocation());
		assertEquals(startA, multiEventA.getStartTime());
		assertEquals(endA, multiEventA.getEndTime());
		assertEquals(startD, multiEventA.getRepeatUntil());
		
	}
	
	@Test
	void testOneTimeEventEqualsCalendarMeeting()
	{
		// One Time Event
		oneEventA.scheduleEvent(calA);
		A = calA.findMeeting(startA);
		assertEquals(A.getDescription(), oneEventA.getDescription());
		assertEquals(A.getLocation(), oneEventA.getLocation());
		assertEquals(A.getStartTime(), oneEventA.getStartTime());
		assertEquals(A.getEndTime(), oneEventA.getEndTime());
		
	}
	
	@Test
	void testOneTimeEventIsOneTime()
	{
		// One Time Event
		oneEventA.scheduleEvent(calA);
		
		B = calA.findMeeting(startB);
		C = calA.findMeeting(startC);
		assertEquals(null, B);
		assertEquals(null, C);
		
	}
	
	@Test
	void testOneTimeEventDoesNotDisplace()
	{
		// One Time Event
		oneEventA.scheduleEvent(calA);
		oneEventA2.scheduleEvent(calA);

		A = calA.findMeeting(startA);
		assertEquals(oneEventA.getDescription(), A.getDescription());
		
	}
	
	@Test
	void testPriorityEventEqualsCalendarMeeting()
	{
		// Priority Event
		priorEventA.scheduleEvent(calA);
		A = calA.findMeeting(startA);
		assertEquals(A.getDescription(), priorEventA.getDescription());
		assertEquals(A.getLocation(), priorEventA.getLocation());
		assertEquals(A.getStartTime(), priorEventA.getStartTime());
		assertEquals(A.getEndTime(), priorEventA.getEndTime());
	}
	
	@Test
	void testPriorityEventIsOneTime()
	{
		// Priority Event
		priorEventA.scheduleEvent(calA);
		
		B = calA.findMeeting(startB);
		C = calA.findMeeting(startC);
		assertEquals(null, B);
		assertEquals(null, C);
	}
	
	@Test
	void testPriorityEventDisplaces()
	{
		// Priority Event
		priorEventA.scheduleEvent(calA);
		priorEventA2.scheduleEvent(calA);
		A = calA.findMeeting(startA);
		assertEquals(priorEventA2.getDescription(), A.getDescription());
	}
	
	@Test
	void testWeeklyEventEqualsCalendarMeeting()
	{
		// Weekly Event
		weekEventA.scheduleEvent(calA);
		A = calA.findMeeting(startA);
		assertEquals(A.getDescription(), weekEventA.getDescription());
		assertEquals(A.getLocation(), weekEventA.getLocation());
		assertEquals(A.getStartTime(), weekEventA.getStartTime());
		assertEquals(A.getEndTime(), weekEventA.getEndTime());
	}
	
	@Test
	void testWeeklyEventIsOneTime()
	{
		// Weekly Event
		priorEventA.scheduleEvent(calA);
				
		B = calA.findMeeting(startB);
		assertEquals(null, B);
		
	}
	
	@Test
	void testWeeklyEventIsWeekly()
	{
		// Weekly Event
		weekEventA.scheduleEvent(calA);
		
		C = calA.findMeeting(startC);
		assertEquals(C.getDescription(), weekEventA.getDescription());
		assertEquals(C.getLocation(), weekEventA.getLocation());
		assertEquals(C.getStartTime(), startC);
	}
	
	@Test
	void testWeeklyEventDoesNotDisplace()
	{
		// Weekly Event
		weekEventA.scheduleEvent(calA);
		weekEventA2.scheduleEvent(calA);

		A = calA.findMeeting(startA);
		assertEquals(weekEventA.getDescription(), A.getDescription());
		
	}
	
	@Test
	void testWeeklyEventStopsAtRepeat()
	{
		// Weekly Event
		weekEventA.scheduleEvent(calA);
		
		C = calA.findMeeting(startC);
		assertNotEquals(null, C);

		D = calA.findMeeting(startD);
		E = calA.findMeeting(startE);
		assertEquals(null, D);
		assertEquals(null, E);
		
	}

	@Test
	void testMultiEventEqualsCalendarMeeting()
	{
		// Multi Event
		multiEventA.scheduleEvent(calA);
		A = calA.findMeeting(startA);
		assertEquals(A.getDescription(), multiEventA.getDescription());
		assertEquals(A.getLocation(), multiEventA.getLocation());
		assertEquals(A.getStartTime(), multiEventA.getStartTime());
		assertEquals(A.getEndTime(), multiEventA.getEndTime());
	}

	@Test
	void testMultiEventIsMultiDayPerWeek()
	{
		// Multi Event
		multiEventA.scheduleEvent(calA);
		
		B = calA.findMeeting(startB);
		assertEquals(B.getDescription(), multiEventA.getDescription());
		assertEquals(B.getLocation(), multiEventA.getLocation());
		
		C = calA.findMeeting(startC);
		assertEquals(C.getDescription(), multiEventA.getDescription());
		assertEquals(C.getLocation(), multiEventA.getLocation());
	}
	
	@Test
	void testMultiEventNotEveryDay()
	{
		// Multi Event
		multiEventB.scheduleEvent(calA);
		
		A = calA.findMeeting(startA);
		B = calA.findMeeting(startB);
		assertEquals(null, A);
		assertEquals(null, B);
	}
	
	@Test
	void testMultiEventDoesNotDisplace()
	{
		// Multi Event
		multiEventA.scheduleEvent(calA);
		multiEventA2.scheduleEvent(calA);

		A = calA.findMeeting(startA);
		assertEquals(multiEventA.getDescription(), A.getDescription());
		
	}
	
	@Test
	void testMultiEventStopsAtRepeat()
	{
		// Multi Event
		multiEventA.scheduleEvent(calA);
		
		C = calA.findMeeting(startC);
		assertNotEquals(null, C);
		
		E = calA.findMeeting(startE);
		F = calA.findMeeting(startF);
		assertEquals(null, E);
		assertEquals(null, F);
		
	}
	
}
