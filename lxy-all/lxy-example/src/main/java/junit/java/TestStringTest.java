package junit.java;

import javaclass.string.StringTest;

import org.junit.Test;

public class TestStringTest {

	@Test
	public void testStringReplace()
	{
		StringTest st = new StringTest();
		String str = "Mr\\John\\Smith";
		String strAfter = st.replaceSpace(str, 13);
		System.out.println(strAfter);
	}
	
	
	@Test
	public void testStringContain()
	{
		StringTest st = new StringTest();
		String str = "Mr\\John\\Smith";
		Boolean isContain = st.containsSub(str, "ohan");
		System.out.println(isContain);
	}
	
	
	@Test
	public void testStringEqualsCharArray()
	{
		StringTest st = new StringTest();
		Boolean flag = st.stringEqualsCharArray();
		System.out.println(flag);
	}
	
	@Test
	public void splitStringByBytesNum()
	{
		String a = "aaAÄãËûBCdº¹DEFgÎÒÄãÄãÄã";
		int bytes = 6;
		StringTest st = new StringTest();
		st.splitStringByBytesNum(a, bytes);
		
	}
	
	@Test
	public void testCharPlusAndTimes()
	{
		StringTest st = new StringTest();
		st.charPlusAndTimes();
	}

}



















