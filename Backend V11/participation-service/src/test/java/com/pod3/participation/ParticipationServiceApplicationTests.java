package com.pod3.participation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.pod3.participation.model.Participation;
import com.pod3.participation.repository.ParticipationRepository;

@SpringBootTest
class ParticipationServiceApplicationTests {

	@MockBean
	private ParticipationRepository partrepo;
	
	/*private int participation_id;
	private int player_id;
	private String player_name;
	private int event_id;
	private String event_name;
	private String sports_id;
	private String sports_name;
	private String status;*/
	
	@Test
	public void testGetAllParticipations() {
		List<Participation> lpar=new ArrayList<>();
		lpar.add(new Participation(10,100,"messi",1,"mass","1004","football","approved"));
		lpar.add(new Participation(11,101,"neymarJr",1,"mass","1004","football","approved"));
		Mockito.when(partrepo.findAll()).thenReturn(lpar);
		List<Participation> lppp= (List<Participation>) partrepo.findAll();
		assertEquals(lpar,lppp);
		}
	
//	@Test
//	public void testParticipationById() {
//		List<Participation> lpar=new ArrayList<>();
//		lpar.add(new Participation(10,100,"messi",1,"mass","1004","football","approved"));
//		lpar.add(new Participation(11,101,"neymarJr",1,"mass","1004","football","approved"));
//		Mockito.when(partrepo.findById(10)).thenReturn(lpar);
//		List<Participation> lpp= (List<Participation>) partrepo.findById(10);
//		assertEquals(lpar,lpp);
		
		
		
//	}
	
	@Test
	public void testParticipationStatus() {
		List<Participation> lpar=new ArrayList<>();
		lpar.add(new Participation(10,100,"messi",1,"mass","1004","football","approved"));
		
		lpar.add(new Participation(11,101,"neymarJr",1,"mass","1004","football","approved"));
		
		Mockito.when(partrepo.findByStatus("approved")).thenReturn(lpar);
		List<Participation> lpp= (List<Participation>) partrepo.findByStatus("approved");
		assertEquals(lpar,lpp);
		
		
		
	}

}
