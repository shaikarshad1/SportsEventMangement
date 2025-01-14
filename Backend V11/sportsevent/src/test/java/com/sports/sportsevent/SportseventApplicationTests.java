package com.sports.sportsevent;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sports.sportsevent.Controller.homeController;
import com.sports.sportsevent.Models.Event;
import com.sports.sportsevent.Models.JwtResponse;
import com.sports.sportsevent.Models.Sports;
import com.sports.sportsevent.Repository.eventSportRepo;
import com.sports.sportsevent.Repository.sportsEveRepo;
@SpringBootTest
public class SportseventApplicationTests {
	@Autowired
	private  homeController hmc;

	@MockBean
	private  eventSportRepo esp;
	
	@MockBean
	private sportsEveRepo sep;	
	
		
	
	@Test
	public void testGetAllEvents() throws Exception {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		Date date = simpleDateFormat.parse("2018-09-09");
		List<Event> pll=new ArrayList<>();
		pll.add(new Event(1,date,"saas",1001,"chess"));
		pll.add(new Event(2,date,"saas",1001,"chess"));
		
		Mockito.when(esp.findAll()).thenReturn(pll);
		List<Event> pl = esp.findAll();
		assertEquals(pll, pl);
	}
	//test1
		@Test
		public void testGetAllSports() throws Exception {
			List<Sports> aspo=new ArrayList<>();
			aspo.add(new Sports(1001,2,"chess","indoor"));
			aspo.add(new Sports(1002,2,"carrom","indoor"));
			aspo.add(new Sports(1003,22,"cricket","outdoor"));
			aspo.add(new Sports(1004,22,"hockey","outdoor"));
			Mockito.when(sep.findAll()).thenReturn(aspo);
			List<Sports> pl = sep.findAll();
			assertEquals(aspo, pl);
		}
		@Test
		public void testSportbyName() throws Exception {
			List<Sports> aspo=new ArrayList<>();
			aspo.add(new Sports(1001,2,"chess","indoor"));
			aspo.add(new Sports(1002,2,"carrom","indoor"));
			Mockito.when(sep.findBySportsName("chess")).thenReturn(aspo);
			List<Sports> pl=sep.findBySportsName("chess");
			assertEquals(aspo,pl);
			
			
		}
		
		@Test
		public void testEventByName() throws Exception {
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

			Date date = simpleDateFormat.parse("2018-09-09");
			List<Event> pll=new ArrayList<>();
			pll.add(new Event(1,date,"saas",1001,"chess"));
			pll.add(new Event(2,date,"maas",1003,"cricket"));
			
			Mockito.when(esp.findByEventName("saas")).thenReturn(pll);
			List<Event> pl = esp.findByEventName("saas");
			assertEquals(pll, pl);
		}
		
		@Test
		public void main() {
		      SportseventApplication.main(new String[] {});
		   }
		@Test
		public void test() {
			Sports s=new Sports(1003,22,"cricket","indoor");
			s.setSportsType("Outdoor");
			assertEquals("Outdoor",s.getSportsType());
		}
		
		@Test
		public void test1() {
			Sports s=new Sports(1007,22,"cricket","indoor");
			s.setSportsName("Hockey");
			assertEquals("Hockey",s.getSportsName());
		}
		@Test
		public void test2() {
			Sports s=new Sports(1003,12,"cricket","indoor");
			s.setNoOfPlayers(22);
			assertEquals(22,s.getNoOfPlayers());
		}
		
		@Test
		public void test3() {
			Sports s=new Sports();
			s.setSportsId(1005);
			assertEquals(1005,s.getSportsId());
		}
		
		@Test
		public void test4() {
			Event e=new Event();
			e.setEventId(1);
			assertEquals(1,e.getEventId());
		}
		
		@Test
		public void test5() {
			Event e=new Event();
			e.setEventName("Mass");
			assertEquals("Mass",e.getEventName());
		}
		
		@Test
		public void test6() {
			Event e=new Event();
			e.setNoOfSlots(2);
			assertEquals(2,e.getNoOfSlots());
		}
		@Test
		public void test7() {
			Event e=new Event();
			e.setSportsName("Hockey");
			assertEquals("Hockey",e.getSportsName());
		}
		@Test
		public void test8() throws ParseException {
			Event e=new Event();
			String pattern = "yyyy-MM-dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

			Date date = simpleDateFormat.parse("2018-09-09");
			e.setEventDate(date);
			assertEquals(date,e.getEventDate());
		}
		
		@Test
		public void test9() {
			JwtResponse j=new JwtResponse("root","root",true);
			
			j.setUserid("admin");
			assertEquals("admin",j.getUserid());
		}
		
		@Test
		public void test10() {
			JwtResponse j=new JwtResponse("root","root",true);
			
			j.setUsername("admin");
			assertEquals("admin",j.getUsername());
		}
		@Test
		public void test11() {
			JwtResponse j=new JwtResponse();
			
			j.setValid(false);
			assertEquals(false,j.isValid());
		}
		
		
		
}
