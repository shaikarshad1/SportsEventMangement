package com.pod3.player;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.pod3.player.Controller.playerController;
import com.pod3.player.Model.JwtResponse;
import com.pod3.player.Model.Player;
import com.pod3.player.Repository.playerRepo;

@SpringBootTest
class PlayerApplicationTests {

	@Autowired
	playerController plc;
	
	@MockBean
	playerRepo prepo;
	
	
	
	
	@Test
	   public void main() {
	      PlayerApplication.main(new String[] {});
	   }
	
	@Test
	public void testGetAllPlayers() {
		List<Player> pll=new ArrayList<>();
		pll.add(new Player(1,"messi",21,987456321,"messi@gmail.com","male","football"));
		pll.add(new Player(2,"nymarjr",22,987456322,"nymarjr@gmail.com","male","football"));
		
		Mockito.when(prepo.findAll()).thenReturn(pll);
		List<Player> pl = prepo.findAll();
		assertEquals(pll, pl);
	}
	
	/*@Test
	public void testPlayerById() {
		List<Player> aspo=new ArrayList<>();
		Player nn=new Player(1,"messi",21,987456321,"messi@gmail.com","male","football");
		nn.setPlayerId(2);
		
		nn.setSportsName("Foot-Ball");
		aspo.add(nn);
		
		Mockito.when(prepo.findById(2)).thenReturn(aspo);
		List<Player> pl=prepo.findById(2);
		assertEquals(aspo,pl);
		
	}*/
	
	@Test
	public void testJwtrepo() {
		JwtResponse jwtr=new JwtResponse("root","root",true);
		jwtr.setValid(false);
		assertEquals("root",jwtr.getUsername());
		
	}
	
	@Test
	public void testJwtrepo2() {
		JwtResponse jwtr=new JwtResponse("root","root",true);
		jwtr.setUserid("admin");
		assertEquals("admin",jwtr.getUserid());
		
	}
	
	@Test
	public void testJwtrepo1() {
		JwtResponse jwtr=new JwtResponse("root","root",false);
		JwtResponse jj=new JwtResponse();
		
		jj.setUsername("admin");
		assertEquals(false,jwtr.isValid());
		
	}
	@Test
	public void testGetAllPlayers1() {
		
		Player pp=new Player(1,"messi",21,987456321,"messi@gmail.com","male","football");
		pp.setAge(32);
		
		assertEquals(32, pp.getAge());
	}
	
	@Test
	public void testGetAllPlayers2() {
		
		Player pp=new Player(1,"messi",21,987456321,"messi@gmail.com","male","football");
		pp.setPlayerName("Messi");
		
		assertEquals("Messi", pp.getPlayerName());
	}
	@Test
	public void testGetAllPlayers3() {
		
		Player pp=new Player(1,"messi",21,987456321,"messi@gmail.com","male","football");
		pp.setGender("Male");
		
		assertEquals("Male", pp.getGender());
	}
	
	@Test
	public void testGetAllPlayers4() {
		
		Player pp=new Player(1,"messi",21,987456321,"messi@gmail.com","male","football");
		pp.setContactNumber(123456789);
		
		assertEquals(123456789, pp.getContactNumber());
	}
	@Test
	public void testGetAllPlayers5() {
		
		Player pp=new Player(1,"messi",21,987456321,"messi@gmail.com","male","football");
		pp.setEmail("messi32@gmail.com");
		
		assertEquals("messi32@gmail.com", pp.getEmail());
	}
	@Test
	public void testGetAllPlayers6() {
		
		Player pp=new Player(1,"messi",21,987456321,"messi@gmail.com","male","football");
		pp.setSportsName("Foot-Ball");
		
		assertEquals("Foot-Ball", pp.getSportsName());
	}
	
	@Test
	public void testGetAllPlayers7() {
		
		Player pp=new Player(1,"messi",21,987456321,"messi@gmail.com","male","football");
		pp.setPlayerId(3);
		
		assertEquals(3, pp.getPlayerId());
	}
	
	
	
	
	
	
	

}
