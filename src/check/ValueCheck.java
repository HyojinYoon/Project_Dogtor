package check;

import java.sql.Timestamp;
import java.util.Date;

public class ValueCheck 
{
	// null값 확인 메서드
	public boolean checkNull(String str)
	{	return (str != null && str != "");	}
	
	// 공백 확인 메서드
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
	// 글자수확인 메서드 (starLength 이상 endLength 이하)
	public boolean charLength(String str, int startLength, int endLength)	
	{
		int size = str.length();
		return (size >= startLength && size <= endLength);
	}
	
	// n번째 글자가 알파벳조건
	public boolean charCheck(String str, int index)				
	{
		char c = str.charAt(index);
		return (c >= 65 && c <= 90) || (c >= 97 && c <= 122);
	}
	
	// 대문자 조건
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
	
	// 소문자 조건
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
	
	// 숫자 조건
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
	
	// 특수문자(1개)
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
	
	// 특수문자(3개 중 한개 포함)
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
	
	// 비밀번호 확인
	public boolean pwCheck(String str, String strCheck)
	{	return str.equals(strCheck);	}
	
	// 생년월일 확인
	public boolean birthdayLength(String str)
	{
		if(str.length() != 8)
		{	return false;	}
		return true;
	}
	
	// 나이 계산
	public int ageCal(String str)
	{
		Date date = new Date();
		String year = str.substring(0, 4);
		Integer i = new Integer(year);
		int age =(date.getYear()+1900)-i.parseInt(year);
		return age;
	}
	
	// 숫자 체크
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
	
	// Timstamp 변환(Timstamp -> String)
	public String timeToStr(Timestamp t)
	{	
		if(t != null)
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
		else
		{ 	return("");	}
	}
	
	// Int배열 변환(String[] -> int[])
	public int[] strArrToIntArr(String[] strArr)
	{
		int[] intArr = new int[strArr.length];
		for(int i=0; i<strArr.length; i++)
		{	intArr[i] = Integer.parseInt(strArr[i]);	}
		return intArr;
	}
}
