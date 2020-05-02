package com.chex.place;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.endpoint.http.ActuatorMediaType;

import com.chex.utils.IdUtils;

class IdUtilsTest {

	@BeforeEach
	void setUp() throws Exception {
	}
	
	String idplace = "EU0PLDLSREG0001";

	@Test
	public void testGetContinentId() {
		String expect = "EU0000000000000";
		String actual = IdUtils.getContinentId(idplace);
		
		assertEquals(expect, actual);
		assertEquals(15, actual.length());
	}
	
	@Test
	public void testGetCountrytId() {
		String expect = "EU0PL0000000000";
		String actual = IdUtils.getCountryId(idplace);
		
		assertEquals(expect, actual);
		assertEquals(15, actual.length());
	}
	
	@Test
	public void testGetRegionId() {
		String expect = "EU0PLDLS0000000";
		String actual = IdUtils.getRegionId(idplace);
		
		assertEquals(expect, actual);
		assertEquals(15, actual.length());
	}
	
	@Test
	public void testGetSubregionId() {
		String expect = "EU0PLDLSREG0000";
		String actual = IdUtils.getSubregionId(idplace);
		
		assertEquals(expect, actual);
		assertEquals(15, actual.length());
	}
	
	@Test
	public void testSplitfunctionWithEmptyParameter() {
		String input = "";
		
		assertNull(IdUtils.splitToLongList(input));
	}
	
	@Test
	public void testSplitfunctionWithOneParameter() {
		String input = "123";
		Long expected = 123l;
		
		List<Long> actual = IdUtils.splitToLongList(input);
		
		assertNotNull(actual);
		assertEquals(expected, actual.get(0));
	}
	
	@Test
	public void splitfunctionWithThreeParam() {
		String input = "123:321:159";
		
		List<Long> actual = IdUtils.splitToLongList(input);
		
		assertNotNull(actual);
		assertEquals(123l, actual.get(0));
		assertEquals(321l, actual.get(1));
		assertEquals(159l, actual.get(2));
	}
	

}
