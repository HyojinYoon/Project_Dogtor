package dogtor;

import java.sql.Timestamp;
import java.util.Date;

public class ValueCheck 
{
	
	public boolean checkNull(String str)
	{	return (str != null && str != "");	}
	
	
	public boolean charBlank(String str)
	{	
		for(int i=0; i < str.length();i++)
		{
			char c = str.charAt(i);
			if(c == 32)
			{ return false; }
		}
		return true;
	}
	
	public boolean charLength(String str, int startLength, int endLength)	
	{
		int size = str.length();
		return (size >= startLength && size <= endLength);
	}
	
	
	public boolean charCheck(String str, int index)				
	{
		char c = str.charAt(index);
		return (c >= 65 && c <= 90) || (c >= 97 && c <= 122);
	}
	
	
	public boolean charBig(String str)						
	{	
		boolean result = false;
		for(int i=0; i < str.length(); i++)
		{
			char c = str.charAt(i);
			if(c >= 65 && c <= 90)
			{	result = true;	}
		}
		return result;
	}
	
	
	public boolean charSmall(String str)						
	{	
		boolean result = false;
		for(int i=0; i < str.length(); i++)
		{
			char c = str.charAt(i);
			if(c >= 97 && c <= 122)
			{	result = true;	}
		}
		return result;
	}
	

	public boolean charNumber(String str)						
	{	
		boolean result = false;
		for(int i=0; i < str.length(); i++)
		{
			char c = str.charAt(i);
			if(c >= 48 && c <= 57)
			{	result = true;	}
		}
		return result;
	}
	

		public boolean charSpecial(String str, int charNumber)
		{
			boolean result = false;
			for(int i=0; i < str.length(); i++)
			{
				char c = str.charAt(i);
				if(c == charNumber)
				{	result = true;	}
			}
			return result;
		}

		
	public boolean charSpecial(String str, int charNumber1, int charNumber2, int charNumber3)
	{
		boolean result = false;
		for(int i=0; i < str.length(); i++)
		{
			char c = str.charAt(i);
			if(c == charNumber1 || c == charNumber2 || c == charNumber3)
			{	result = true;	}
		}
		return result;
	}
	
	
	public boolean pwCheck(String str, String strCheck)
	{	return str.equals(strCheck);	}
	
	
	public boolean birthdayLength(String str)
	{
		if(str.length() != 8)
		{	return false;	}
		return true;
	}
	
	
	public boolean numberCheck(String str, int numberlength)
	{
		boolean result = false;
		int size = str.length();
		if(size == numberlength)
		{
			for(int i=0; i < size ; i++)
			{
				char c = str.charAt(i);
				if(c>=48 && c<=57)
				{	result = true;	}
			}
		}
		return result;
	}
	public String timeToStr(Timestamp t)
	
	{	
		if(t.getMonth()+1 <= 9 && t.getDate() <= 9)
		{	 return ((t.getYear()+1900)+"-0"+(t.getMonth()+1)+"-0"+(t.getDate()));	}
		else if(t.getMonth()+1 <= 9 && t.getDate() > 9)
		{	return ((t.getYear()+1900)+"-0"+(t.getMonth()+1)+"-"+(t.getDate()));	}
		else if(t.getMonth()+1 > 9 && t.getDate() <= 9)
		{	return ((t.getYear()+1900)+"-"+(t.getMonth()+1)+"-0"+(t.getDate()));	}
		else
		{ 	return ((t.getYear()+1900)+"-"+(t.getMonth()+1)+"-"+(t.getDate()));		}
	}
	

	public int[] strArrToIntArr(String[] strArr)
	{
		int[] intArr = new int[strArr.length];
		for(int i=0; i<strArr.length; i++)
		{	intArr[i] = Integer.parseInt(strArr[i]);	}
		return intArr;
	}
}
