package com.demo;


import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import com.demo.controller.MasterController;
import com.demo.model.Master;
import com.demo.repository.MasterRepo;


@SpringBootTest
class UnifiedSecurityApplicationTest{

private MasterController masterController;
@Autowired
MasterRepo repo;


  @Test 
  void getAll() {
  ResponseEntity<List<Master>>list=masterController.fetchData();
  Assertions.assertNull(list); 
  }
 
	@Test
	 void testRead () {
	Master master =repo.findBySymbol("20MICRONS").get();
	assertEquals("INE144J01027", master.getIsinNumber());
 }
	private void assertEquals(String string, Object ISIN) {
	}
	@Test
	void testCreate() {
    Master s=new Master();
	s.setSymbol("LTIM");
	s.setDescription("LTIMINDTREE LTD");
	s.setSector("Commercial Services");
    s.setIndustry("Miscellaneous Commercial Services");
	s.setExchange("NSE");
	s.setSeries("EQ");
	s.setIsinNumber("INE214T01019");
    s.setCurrency("INR");
    s.setCountry("INDIA");
	repo.save(s);
    assertNotNull(repo.findBySymbol("LTIM").get());;
	}
	private void assertNotNull(Master master) {
	}
	@Test
	void testData() {
		List list=repo.findAll();
		assertThat(list).size();
	}
	private List<Master> assertThat(List list) {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	}
