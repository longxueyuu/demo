package javaclass.string;

public class StringTest {

	public String replaceSpace(String iniString, int length) {
       return iniString.replaceAll("\\\\", "*");
    }
	
	public Boolean containsSub(String iniString, String subStr)
	{
		
		return iniString.contains(subStr.subSequence(0, subStr.length()));
	}
	
	public Boolean stringEqualsCharArray()
	{
		String str = "hello";
		char [] ch = {'h', 'e', 'l', 'l', 'o'};
		return str.equals(ch);
	}
	
	public void splitStringByBytesNum(String a, int bytes)
	{
		String temp;
		int count = 0;
		int i = 0;
		int prePos = i;
		for(i = 0; i < a.length(); i++)
		{
			int  chBytes = new String(a.substring(i, i + 1)).getBytes().length;
			count = count + chBytes;
			if(count > bytes)
			{
				
				temp = a.substring(prePos, i);
				
				//System.out.println(new String("บน").getBytes().length);
				System.out.println(temp);
				
				prePos = i;
				count = chBytes;
				if(i == a.length() -1)
				{
					i--;
				}
			}
			if(i == a.length() - 1)
			{
				temp = a.substring(prePos, a.length());
				System.out.println(temp);
			}
		}
	}
	
	public void charPlusAndTimes()
	{
		char ch = '*';
		
		System.out.println(ch + ch);
		
		System.out.println(2 * ch);
		
		System.out.println(2 + ch);
		
		System.out.println(ch + '0');
		
		System.out.println("---------------------");
		
		ch += ch;
		System.out.println(ch);
		ch += 10;
		System.out.println(ch);
		
		byte a1 = 1, b2 = 1;
		a1 += a1;
		System.out.println(a1);
		a1 += 1;
		System.out.println(a1);
	}
	
	
}






















