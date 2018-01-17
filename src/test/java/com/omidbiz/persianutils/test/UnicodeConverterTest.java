package com.omidbiz.persianutils.test;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.omidbiz.persianutils.JalaliCalendar;
import com.omidbiz.persianutils.PersianCharacterUnifier;
import com.omidbiz.persianutils.PersianDateConverter;
import com.omidbiz.persianutils.UnicodeConverter;

/**
 * @author omidp
 *
 */
public class UnicodeConverterTest
{

    @Test
    public void testUCConverter()
    {
     // "\u0627"
        String s = "971456-#U0647#U0645#U0631#U0627#U0647_#U0627#U0648#U0644-#U0645#U0648#U0633#U0633#U0647_#U0641#U0631#U0647#U06cc#U062e#U062a#U06af#U0627#U0646_#U0639#U0635#U0631_#U0647#U0646#U0631_-_#U0647#U0645#U0631#U0627#U0647_#U0627#U0648#U0644-290967659.pdf";
        String input = s.replaceAll("#U", "\\\\u");
        System.out.println(UnicodeConverter.getInstance().unicodeUnescape(input));
    }

}
