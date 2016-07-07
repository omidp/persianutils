package com.omidbiz.persianutils.test;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.omidbiz.persianutils.JalaliCalendar;
import com.omidbiz.persianutils.PersianCharacterUnifier;
import com.omidbiz.persianutils.PersianDateConverter;

/**
 * @author omidp
 *
 */
public class PersianUtilsTest {

	@Test
	public void testDateConverter() {
		String str = "1394/05/01";
		String strtime = "1394/05/01 15:14";
		PersianDateConverter pc = PersianDateConverter.getInstance();
		Assert.assertEquals("2015/07/23", pc.SolarToGregorian(str));
		Assert.assertEquals("2015/07/23 15:14", pc.SolarToGregorian(strtime));
		System.out.println(pc.SolarToGregorianAsDate(strtime));
		System.out.println(pc.SolarToGregorianAsDate(str));
		//
		String gdate = "2015/07/23";
		String gdateTime = "2015/07/23 14:13";
		Assert.assertEquals("1394/05/01", pc.GregorianToSolar(gdate));
		Assert.assertEquals("1394/05/01 14:13", pc.GregorianToSolar(gdateTime));
		System.out.println(pc.GregorianToSolar(new Date(), true));
	}

	@Test
	public void testUnifier() {
		String str = "ك ك ي ي ي ي";
		PersianCharacterUnifier pc = PersianCharacterUnifier.getInstance();
		System.out.println(pc.unify(str));
		System.out.println(Integer.toHexString('‏'));
		System.out.println(Integer.toHexString('‫'));

	}

	@Test
	public void testJalaliCalendar() {
		Date d = new Date();
		JalaliCalendar jc = new JalaliCalendar();
		jc.setTime(d);
		Assert.assertEquals(d, jc.getTime());
		jc.set(JalaliCalendar.YEAR, 1393);
		jc.set(Calendar.MONTH, 7);
		jc.set(Calendar.DATE, 1);
		Assert.assertEquals(1393, jc.get(JalaliCalendar.YEAR));
		Assert.assertFalse(d == jc.getTime());
	}

	@Test
	public void testTimeSec() {
		String str = "2013/01/02 00:00:00";
		PersianDateConverter pc = PersianDateConverter.getInstance();
		System.out.println(pc.GregorianToSolar(str));
		//
		str = "2013/01/02 00:00:00.0";
		System.out.println(pc.GregorianToSolar(str));
		
	}

}
