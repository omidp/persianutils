package com.omidbiz.persianutils;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * <code>JalaliCalendar</code> is a concrete subclass of
 * {@link java.util.Calendar}
 * and provides the standard jalalicalendar used in Iran.
 * <p/>
 * <p/>
 * The Jalali jalalicalendar has 2 eras, BH (Before Hegira) and AH (After Hegira).
 * <p/>
 * <p/>
 * <code>JalaliCalendar</code> implements <em>proleptic</em> Jalali
 * jalalicalendar. That is, dates are computed by extrapolating the current
 * rules indefinitely far backward and forward in time. As a result,
 * <code>JalaliCalendar</code> may be used for all years to generate
 * meaningful and consistent results.
 * <p/>
 * <p>Values calculated for the <code>WEEK_OF_YEAR</code> field range from 1 to
 * 53.  Week 1 for a year is the earliest seven day period starting on
 * <code>getFirstDayOfWeek()</code> that contains at least
 * <code>getMinimalDaysInFirstWeek()</code> days from that year.  It thus
 * depends on the values of <code>getMinimalDaysInFirstWeek()</code>,
 * <code>getFirstDayOfWeek()</code>, and the day of the week of Farvardin 1.
 * Weeks between week 1 of one year and week 1 of the following year are
 * numbered sequentially from 2 to 52 or 53 (as needed).
 * <p/>
 * <p>For example, Farvardin 1, 1380 was a Wednesday.  If
 * <code>getFirstDayOfWeek()</code> is <code>SATURDAY</code> and
 * <code>getMinimalDaysInFirstWeek()</code> is 4, then week 1 of 1380 starts
 * on Farvardin 4, 1380, and ends on Farvardin 10, 1380; the first three days
 * of 1380 then are part of week 53 of 1379.
 * If, however, <code>getFirstDayOfWeek()</code> is <code>SUNDAY</code>, then
 * week 1 of 1380 starts on Esfand 28, 1379 (year 1379 is Leap), and ends on
 * Farvardin 4, 1380.
 * <p/>
 * <p>Values calculated for the <code>WEEK_OF_MONTH</code> field range from 0 or
 * 1 to 4 or 5.  Week 1 of a month (the days with <code>WEEK_OF_MONTH =
 * 1</code>) is the earliest set of at least
 * <code>getMinimalDaysInFirstWeek()</code> contiguous days in that month,
 * ending on the day before <code>getFirstDayOfWeek()</code>.  Unlike
 * week 1 of a year, week 1 of a month may be shorter than 7 days, need
 * not start on <code>getFirstDayOfWeek()</code>, and will not include days of
 * the previous month.  Days of a month before week 1 have a
 * <code>WEEK_OF_MONTH</code> of 0.
 * <p/>
 * <p>For example, if <code>getFirstDayOfWeek()</code> is <code>SATURDAY</code>
 * and <code>getMinimalDaysInFirstWeek()</code> is 4, then the first week of
 * Farvardin 1380 is Saturday, Farvardin 3 through Friday, Farvardin 9.  These days
 * have a <code>WEEK_OF_MONTH</code> of 1.  Wednesday, Farvardin 1 through
 * Friday, Farvardin 3 have a <code>WEEK_OF_MONTH</code> of 0.  If
 * <code>getMinimalDaysInFirstWeek()</code> is changed to 3, then Farvardin 1
 * through Farvardin 3 have a <code>WEEK_OF_MONTH</code> of 1.
 * <p/>
 * <p/>
 * <strong>Example:</strong>
 * <blockquote>
 * <pre>
 * // get the supported ids for GMT+03:30 (Tehran Standard Time)
 * String[] ids = TimeZone.getAvailableIDs((3 * 60 + 30) * 60 * 1000);
 * // if no ids were returned, something is wrong. get out.
 * if (ids.length == 0)
 *     System.exit(0);
 * <p/>
 *  // begin output
 * System.out.println("Current Time");
 * <p/>
 * // create a Tehran Standard Time time zone
 * SimpleTimeZone pdt = new SimpleTimeZone((3 * 60 + 30) * 60 * 1000, ids[0]);
 * <p/>
 * // set up rules for daylight savings time
 * pdt.setStartRule(JalaliCalendar.FARVARDIN, 2, 0);
 * pdt.setEndRule(JalaliCalendar.SHAHRIVAR, 31, 0);
 * <p/>
 * // create a JalaliCalendar with the Tehran Daylight time zone
 * // and the current date and time
 * Calendar jalalicalendar = new JalaliCalendar(pdt);
 * Date trialTime = new Date();
 * jalalicalendar.setTime(trialTime);
 * <p/>
 * // print out a bunch of interesting things
 * System.out.println("ERA: " + jalalicalendar.get(Calendar.ERA));
 * System.out.println("YEAR: " + jalalicalendar.get(Calendar.YEAR));
 * System.out.println("MONTH: " + jalalicalendar.get(Calendar.MONTH));
 * System.out.println("WEEK_OF_YEAR: " + jalalicalendar.get(Calendar.WEEK_OF_YEAR));
 * System.out.println("WEEK_OF_MONTH: " + jalalicalendar.get(Calendar.WEEK_OF_MONTH));
 * System.out.println("DATE: " + jalalicalendar.get(Calendar.DATE));
 * System.out.println("DAY_OF_MONTH: " + jalalicalendar.get(Calendar.DAY_OF_MONTH));
 * System.out.println("DAY_OF_YEAR: " + jalalicalendar.get(Calendar.DAY_OF_YEAR));
 * System.out.println("DAY_OF_WEEK: " + jalalicalendar.get(Calendar.DAY_OF_WEEK));
 * System.out.println("DAY_OF_WEEK_IN_MONTH: "
 *                    + jalalicalendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
 * System.out.println("AM_PM: " + jalalicalendar.get(Calendar.AM_PM));
 * System.out.println("HOUR: " + jalalicalendar.get(Calendar.HOUR));
 * System.out.println("HOUR_OF_DAY: " + jalalicalendar.get(Calendar.HOUR_OF_DAY));
 * System.out.println("MINUTE: " + jalalicalendar.get(Calendar.MINUTE));
 * System.out.println("SECOND: " + jalalicalendar.get(Calendar.SECOND));
 * System.out.println("MILLISECOND: " + jalalicalendar.get(Calendar.MILLISECOND));
 * System.out.println("ZONE_OFFSET: "
 *                    + (jalalicalendar.get(Calendar.ZONE_OFFSET)/(60*60*1000)));
 * System.out.println("DST_OFFSET: "
 *                    + (jalalicalendar.get(Calendar.DST_OFFSET)/(60*60*1000)));
 * <p/>
 * System.out.println("Current Time, with hour reset to 3");
 * jalalicalendar.clear(Calendar.HOUR_OF_DAY); // so doesn't override
 * jalalicalendar.set(Calendar.HOUR, 3);
 * System.out.println("ERA: " + jalalicalendar.get(Calendar.ERA));
 * System.out.println("YEAR: " + jalalicalendar.get(Calendar.YEAR));
 * System.out.println("MONTH: " + jalalicalendar.get(Calendar.MONTH));
 * System.out.println("WEEK_OF_YEAR: " + jalalicalendar.get(Calendar.WEEK_OF_YEAR));
 * System.out.println("WEEK_OF_MONTH: " + jalalicalendar.get(Calendar.WEEK_OF_MONTH));
 * System.out.println("DATE: " + jalalicalendar.get(Calendar.DATE));
 * System.out.println("DAY_OF_MONTH: " + jalalicalendar.get(Calendar.DAY_OF_MONTH));
 * System.out.println("DAY_OF_YEAR: " + jalalicalendar.get(Calendar.DAY_OF_YEAR));
 * System.out.println("DAY_OF_WEEK: " + jalalicalendar.get(Calendar.DAY_OF_WEEK));
 * System.out.println("DAY_OF_WEEK_IN_MONTH: "
 *                    + jalalicalendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
 * System.out.println("AM_PM: " + jalalicalendar.get(Calendar.AM_PM));
 * System.out.println("HOUR: " + jalalicalendar.get(Calendar.HOUR));
 * System.out.println("HOUR_OF_DAY: " + jalalicalendar.get(Calendar.HOUR_OF_DAY));
 * System.out.println("MINUTE: " + jalalicalendar.get(Calendar.MINUTE));
 * System.out.println("SECOND: " + jalalicalendar.get(Calendar.SECOND));
 * System.out.println("MILLISECOND: " + jalalicalendar.get(Calendar.MILLISECOND));
 * System.out.println("ZONE_OFFSET: "
 *        + (jalalicalendar.get(Calendar.ZONE_OFFSET)/(60*60*1000))); // in hours
 * System.out.println("DST_OFFSET: "
 *        + (jalalicalendar.get(Calendar.DST_OFFSET)/(60*60*1000))); // in hours
 * </pre>
 * </blockquote>
 *
 * @author David Goldsmith, Mark Davis, Chen-Lieh Huang, Alan Liu, Behdad Esfahbod
 * @version 1.00
 * @see java.util.TimeZone
 * @since JDK1.1
 */

public final class JalaliCalendar extends Calendar {

    // Special values of stamp[]
    /**
     * The corresponding fields[] has no value.
     */
    private static final int UNSET = 0;

    /**
     * The value of the corresponding fields[] has been calculated internally.
     */
    private static final int COMPUTED = 1;

    /**
     * The value of the corresponding fields[] has been set externally. Stamp
     * values which are greater than 1 represents the (pseudo) time when the
     * corresponding fields[] value was set.
     */
    private static final int MINIMUM_USER_STAMP = 2;

    /*
    * Implementation Notes
    *
    * The Jalali day number, as used here, is a modified number which has its
    * onset at midnight, rather than noon.
    *
    * The epoch is the number of days or milliseconds from some defined
    * starting point. The epoch for java.util.Date is used here; that is,
    * milliseconds from January 1, 1970 (Standard) midnight UTC.
    * Other epoch which is used is Farvardin 1, 1376 (Jalali).
    *
    * With the Jalali jalalicalendar, the leap years have a period of 33 years,
    * that is the years that their remainder plus 11, modula 33 is a multiple
    * of 4 and is not 32, it means, there are eight leap years with four
    * years between each one and the next, and five years between the last
    * one and the first one of the next period, and we now that 1370, 1375
    * and 1379 are leap years (1375 - 1370 = 5, 1379 - 1375 = 4).
    *
    * Given that milliseconds are a long, day numbers such as Jalali day
    * numbers, Jalali jalalicalendar days, or epoch days, are also longs. Years
    * can fit into an int.
    */

//////////////////
// Class Variables
//////////////////
    // --BE

    /**
     * Value of the <code>ERA</code> field indicating
     * the period before the common era (Before Hagira).
     * The sequence of years at the transition from <code>BH</code> to <code>AH</code> is
     * ..., 2 BH, 1 BH, 1 AH, 2 AH,...
     *
     * @see java.util.Calendar#ERA
     */
    public static final int BH = 0;

    /**
     * Value of the <code>ERA</code> field indicating
     * the common era (After Hagira).
     * The sequence of years at the transition from <code>BH</code> to <code>AH</code> is
     * ..., 2 BH, 1 BH, 1 AH, 2 AH,...
     *
     * @see java.util.Calendar#ERA
     */
    public static final int AH = 1;

    /**
     * Value of the <code>MONTH</code> field indicating the
     * first month of the year.
     */
    public final static int FARVARDIN = JANUARY;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * second month of the year.
     */
    public final static int ORDIBEHESHT = FEBRUARY;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * third month of the year.
     */
    public final static int KHORDAD = MARCH;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * fourth month of the year.
     */
    public final static int TIR = APRIL;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * fifth month of the year.
     */
    public final static int MORDAD = MAY;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * sixth month of the year.
     */
    public final static int SHAHRIVAR = JUNE;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * seventh month of the year.
     */
    public final static int MEHR = JULY;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * eighth month of the year.
     */
    public final static int ABAN = AUGUST;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * ninth month of the year.
     */
    public final static int AZAR = SEPTEMBER;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * tenth month of the year.
     */
    public final static int DEY = OCTOBER;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * eleventh month of the year.
     */
    public final static int BAHMAN = NOVEMBER;
    /**
     * Value of the <code>MONTH</code> field indicating the
     * twelfth month of the year.
     */
    public final static int ESFAND = DECEMBER;

    private static final int FAR_1_1376_JALALI_DAY = 2450529; // Farvardin 1, 1376 (Jalali)
    private static final int EPOCH_JALALI_DAY = 2440588; // January 1, 1970 (Standard)
    private static final int BASE_YEAR = 1375; // EPOCH_YEAR - 1, should be start of a
    // 33 year cycle

    private static final int NUM_DAYS[]
            = {0, 31, 62, 93, 124, 155, 186, 216, 246, 276, 306, 336}; // 0-based, for day-in-year
    private static final int MONTH_LENGTH[]
            = {31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29}; // 0-based
    private static final int LEAP_MONTH_LENGTH[]
            = {31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 30}; // 0-based

    // Useful millisecond constants.  Although ONE_DAY and ONE_WEEK can fit
    // into ints, they must be longs in order to prevent arithmetic overflow
    // when performing.
    private static final int ONE_SECOND = 1000;
    private static final int ONE_MINUTE = 60 * ONE_SECOND;
    private static final int ONE_HOUR = 60 * ONE_MINUTE;
    private static final long ONE_DAY = 24 * ONE_HOUR;
    private static final long ONE_WEEK = 7 * ONE_DAY;

    /*
     * <pre>
     *                            Greatest       Least
     * Field name        Minimum   Minimum     Maximum     Maximum
     * ----------        -------   -------     -------     -------
     * ERA                     0         0           1           1
     * YEAR                    1         1   292269054   292278994
     * MONTH                   0         0          11          11
     * WEEK_OF_YEAR            1         1          52          53
     * WEEK_OF_MONTH           0         0           5           6
     * DAY_OF_MONTH            1         1          29          31
     * DAY_OF_YEAR             1         1         365         366
     * DAY_OF_WEEK             1         1           7           7
     * DAY_OF_WEEK_IN_MONTH   -1        -1           4           6
     * AM_PM                   0         0           1           1
     * HOUR                    0         0          11          11
     * HOUR_OF_DAY             0         0          23          23
     * MINUTE                  0         0          59          59
     * SECOND                  0         0          59          59
     * MILLISECOND             0         0         999         999
     * ZONE_OFFSET           -12*      -12*         12*         12*
     * DST_OFFSET              0         0           1*          1*
     * </pre>
     * (*) In units of one-hour
     */
    private static final int MIN_VALUES[] = {
            0, 1, 0, 1, 0, 1, 1, 1, -1, 0, 0, 0, 0, 0, 0, -12 * ONE_HOUR, 0
    };
    private static final int LEAST_MAX_VALUES[] = {
            1, 292269054, 11, 52, 5, 29, 365, 7, 4, 1, 11, 23, 59, 59, 999, 12 * ONE_HOUR, ONE_HOUR
    };
    private static final int MAX_VALUES[] = {
            1, 292278994, 11, 53, 6, 31, 366, 7, 6, 1, 11, 23, 59, 59, 999, 12 * ONE_HOUR, ONE_HOUR
    };

/////////////////////
// Instance Variables
/////////////////////

    // Proclaim serialization compatiblity with JDK 1.1
    static final long serialVersionUID = -5468927952461089424L;

///////////////
// Constructors
///////////////

    /**
     * Constructs a default JalaliCalendar using the current time
     * in the default time zone with the default locale.
     */
    public JalaliCalendar() {
        this(TimeZone.getDefault(), Locale.getDefault());
    }

    /**
     * Constructs a JalaliCalendar based on the current time
     * in the given time zone with the default locale.
     *
     * @param zone the given time zone.
     */
    public JalaliCalendar(TimeZone zone) {
        this(zone, Locale.getDefault());
    }

    /**
     * Constructs a JalaliCalendar based on the current time
     * in the default time zone with the given locale.
     *
     * @param aLocale the given locale.
     */
    public JalaliCalendar(Locale aLocale) {
        this(TimeZone.getDefault(), aLocale);
    }

    /**
     * Constructs a JalaliCalendar based on the current time
     * in the given time zone with the given locale.
     *
     * @param zone    the given time zone.
     * @param aLocale the given locale.
     */
    public JalaliCalendar(TimeZone zone, Locale aLocale) {
        super(zone, aLocale);
        setTimeInMillis(System.currentTimeMillis());
    }

    /**
     * Constructs a JalaliCalendar with the given date set
     * in the default time zone with the default locale.
     *
     * @param year  the value used to set the YEAR time field in the jalalicalendar.
     * @param month the value used to set the MONTH time field in the jalalicalendar.
     *              Month value is 0-based. e.g., 0 for Farvardin.
     * @param date  the value used to set the DATE time field in the jalalicalendar.
     */
    public JalaliCalendar(int year, int month, int date) {
        super(TimeZone.getDefault(), Locale.getDefault());
        this.set(ERA, AH);
        this.set(YEAR, year);
        this.set(MONTH, month);
        this.set(DATE, date);
    }

    /**
     * Constructs a JalaliCalendar with the given date
     * and time set for the default time zone with the default locale.
     *
     * @param year   the value used to set the YEAR time field in the jalalicalendar.
     * @param month  the value used to set the MONTH time field in the jalalicalendar.
     *               Month value is 0-based. e.g., 0 for Farvardin.
     * @param date   the value used to set the DATE time field in the jalalicalendar.
     * @param hour   the value used to set the HOUR_OF_DAY time field
     *               in the jalalicalendar.
     * @param minute the value used to set the MINUTE time field
     *               in the jalalicalendar.
     */
    public JalaliCalendar(int year, int month, int date, int hour,
                          int minute) {
        super(TimeZone.getDefault(), Locale.getDefault());
        this.set(ERA, AH);
        this.set(YEAR, year);
        this.set(MONTH, month);
        this.set(DATE, date);
        this.set(HOUR_OF_DAY, hour);
        this.set(MINUTE, minute);
    }

    /**
     * Constructs a JalaliCalendar with the given date
     * and time set for the default time zone with the default locale.
     *
     * @param year   the value used to set the YEAR time field in the jalalicalendar.
     * @param month  the value used to set the MONTH time field in the jalalicalendar.
     *               Month value is 0-based. e.g., 0 for Farvardin.
     * @param date   the value used to set the DATE time field in the jalalicalendar.
     * @param hour   the value used to set the HOUR_OF_DAY time field
     *               in the jalalicalendar.
     * @param minute the value used to set the MINUTE time field
     *               in the jalalicalendar.
     * @param second the value used to set the SECOND time field
     *               in the jalalicalendar.
     */
    public JalaliCalendar(int year, int month, int date, int hour,
                          int minute, int second) {
        super(TimeZone.getDefault(), Locale.getDefault());
        this.set(ERA, AH);
        this.set(YEAR, year);
        this.set(MONTH, month);
        this.set(DATE, date);
        this.set(HOUR_OF_DAY, hour);
        this.set(MINUTE, minute);
        this.set(SECOND, second);
    }

/////////////////
// Public methods
/////////////////

    /**
     * Determines if the given year is a leap year. Returns true if the
     * given year is a leap year.
     *
     * @param year the given year.
     * @return true if the given year is a leap year; false otherwise.
     */
    public static boolean isLeapYear(int year) {
        int mod = (year + 11) % 33;
        return mod % 4 == 0 && mod != 32;
    }

    /**
     * Compares this JalaliCalendar to an object reference.
     *
     * @param obj the object reference with which to compare
     * @return true if this object is equal to <code>obj</code>; false otherwise
     */
    public boolean equals(Object obj) {
        return obj instanceof JalaliCalendar && super.equals(obj);
    }

    /**
     * Overrides Calendar
     * Date Arithmetic function.
     * Adds the specified (signed) amount of time to the given time field,
     * based on the jalalicalendar's rules.
     *
     * @param field  the time field.
     * @param amount the amount of date or time to be added to the field.
     * @throws IllegalArgumentException if an unknown field is given.
     */
    public void add(int field, int amount) {
        if (amount == 0) return;   // Do nothing!
        complete();

        if (field == YEAR) {
            int year = this.internalGet(YEAR);
            if (this.internalGetEra() == AH) {
                year += amount;
                if (year > 0)
                    this.set(YEAR, year);
                else { // year <= 0
                    this.set(YEAR, 1 - year);
                    // if year == 0, you get 1 BH
                    this.set(ERA, BH);
                }
            } else { // era == BH
                year -= amount;
                if (year > 0)
                    this.set(YEAR, year);
                else { // year <= 0
                    this.set(YEAR, 1 - year);
                    // if year == 0, you get 1 AH
                    this.set(ERA, AH);
                }
            }
            pinDayOfMonth();
        } else if (field == MONTH) {
            int month = this.internalGet(MONTH) + amount;
            if (month >= 0) {
                set(YEAR, internalGet(YEAR) + (month / 12));
                set(MONTH, (month % 12));
            } else { // month < 0

                set(YEAR, internalGet(YEAR) + ((month + 1) / 12) - 1);
                month %= 12;
                if (month < 0) month += 12;
                //noinspection PointlessArithmeticExpression
                set(MONTH, FARVARDIN + month);
            }
            pinDayOfMonth();
        } else if (field == ERA) {
            int era = internalGet(ERA) + amount;
            if (era < 0) era = 0;
            if (era > 1) era = 1;
            set(ERA, era);
        } else {
            // We handle most fields here.  The algorithm is to add a computed amount
            // of millis to the current millis.  The only wrinkle is with DST -- if
            // the result of the add operation is to move from DST to Standard, or vice
            // versa, we need to adjust by an hour forward or back, respectively.
            // Otherwise you get weird effects in which the hour seems to shift when
            // you add to the DAY_OF_MONTH field, for instance.

            // We only adjust the DST for fields larger than an hour.  For fields
            // smaller than an hour, we cannot adjust for DST without causing problems.
            // for instance, if you add one hour to April 5, 1380, 1:00 AM, in PST,
            // the time becomes "2:00 AM PDT" (an illegal value), but then the adjustment
            // sees the change and compensates by subtracting an hour.  As a result the
            // time doesn't advance at all.

            long delta = amount;
            boolean adjustDST = true;

            switch (field) {
                case WEEK_OF_YEAR:
                case WEEK_OF_MONTH:
                case DAY_OF_WEEK_IN_MONTH:
                    delta *= 7 * 24 * 60 * 60 * 1000; // 7 days
                    break;

                case AM_PM:
                    delta *= 12 * 60 * 60 * 1000; // 12 hrs
                    break;

                case DATE: // synonym of DAY_OF_MONTH
                case DAY_OF_YEAR:
                case DAY_OF_WEEK:
                    delta *= 24 * 60 * 60 * 1000; // 1 day
                    break;

                case HOUR_OF_DAY:
                case HOUR:
                    delta *= 60 * 60 * 1000; // 1 hour
                    adjustDST = false;
                    break;

                case MINUTE:
                    delta *= 60 * 1000; // 1 minute
                    adjustDST = false;
                    break;

                case SECOND:
                    delta *= 1000; // 1 second
                    adjustDST = false;
                    break;

                case MILLISECOND:
                    adjustDST = false;
                    break;

                case ZONE_OFFSET:
                case DST_OFFSET:
                default:
                    throw new IllegalArgumentException();
            }

            // Save the current DST state.
            long dst = 0;
            if (adjustDST) dst = internalGet(DST_OFFSET);

            setTimeInMillis(time + delta); // Automatically computes fields if necessary

            if (adjustDST) {
                // Now do the DST adjustment alluded to above.
                // Only call setTimeInMillis if necessary, because it's an expensive call.
                dst -= internalGet(DST_OFFSET);
                if (delta != 0) setTimeInMillis(time + dst);
            }
        }
    }


    /**
     * Overrides Calendar
     * Time Field Rolling function.
     * Rolls (up/down) a single unit of time on the given time field.
     *
     * @param field the time field.
     * @param up    Indicates if rolling up or rolling down the field value.
     * @throws IllegalArgumentException if an unknown field value is given.
     */
    public void roll(int field, boolean up) {
        roll(field, up ? +1 : -1);
    }

    /**
     * Roll a field by a signed amount.
     *
     * @since 1.2
     */
    public void roll(int field, int amount) {
        if (amount == 0) return; // Nothing to do

        int min = 0, max = 0, gap;
        if (field >= 0 && field < FIELD_COUNT) {
            complete();
            min = getMinimum(field);
            max = getMaximum(field);
        }

        switch (field) {
            case ERA:
            case YEAR:
            case AM_PM:
            case MINUTE:
            case SECOND:
            case MILLISECOND:
                // These fields are handled simply, since they have fixed minima
                // and maxima.  The field DAY_OF_MONTH is almost as simple.  Other
                // fields are complicated, since the range within they must roll
                // varies depending on the date.
                break;

            case HOUR:
            case HOUR_OF_DAY:
                // Rolling the hour is difficult on the ONSET and CEASE days of
                // daylight savings.  For example, if the change occurs at
                // 2 AM, we have the following progression:
                // ONSET: 12 Std -> 1 Std -> 3 Dst -> 4 Dst
                // CEASE: 12 Dst -> 1 Dst -> 1 Std -> 2 Std
                // To get around this problem we don't use fields; we manipulate
                // the time in millis directly.
            {
                // Assume min == 0 in calculations below
                Date start = getTime();
                int oldHour = internalGet(field);
                int newHour = (oldHour + amount) % (max + 1);
                if (newHour < 0) {
                    newHour += max + 1;
                }
                setTime(new Date(start.getTime() + ONE_HOUR * (newHour - oldHour)));
                return;
            }
            case MONTH:
                // Rolling the month involves both pinning the final value to [0, 11]
                // and adjusting the DAY_OF_MONTH if necessary.  We only adjust the
                // DAY_OF_MONTH if, after updating the MONTH field, it is illegal.
                // E.g., <bahm31>.roll(MONTH, 1) -> <esf29> or <esf30>.
            {
                int mon = (internalGet(MONTH) + amount) % 12;
                if (mon < 0) mon += 12;
                set(MONTH, mon);

                // Keep the day of month in range.  We don't want to spill over
                // into the next month; e.g., we don't want farv31 + 1 mo -> ordi31 ->
                // khor3.
                // NOTE: We could optimize this later by checking for dom <= 29
                // first.  Do this if there appears to be a need. [LIU]
                int monthLen = monthLength(mon);
                int dom = internalGet(DAY_OF_MONTH);
                if (dom > monthLen) set(DAY_OF_MONTH, monthLen);
                return;
            }

            case WEEK_OF_YEAR: {
                // Unlike WEEK_OF_MONTH, WEEK_OF_YEAR never shifts the day of the
                // week.  Also, rolling the week of the year can have seemingly
                // strange effects simply because the year of the week of year
                // may be different from the jalalicalendar year.  For example, the
                // date Esf 28, 1379 is the first day of week 1 of 1380 (if
                // weeks start on Saturday and the minimal days in first week is
                // <= 3).
                int woy = internalGet(WEEK_OF_YEAR);
                // Get the ISO year, which matches the week of year.  This
                // may be one year before or after the jalalicalendar year.
                int isoYear = internalGet(YEAR);
                int isoDoy = internalGet(DAY_OF_YEAR);
                if (internalGet(MONTH) == FARVARDIN) {
                    if (woy >= 52) {
                        --isoYear;
                        isoDoy += yearLength(isoYear);
                    }
                } else {
                    if (woy == 1) {
                        isoDoy -= yearLength(isoYear);
                        ++isoYear;
                    }
                }
                woy += amount;
                // Do fast checks to avoid unnecessary computation:
                if (woy < 1 || woy > 52) {
                    // Determine the last week of the ISO year.
                    // We do this using the standard formula we use
                    // everywhere in this file.  If we can see that the
                    // days at the end of the year are going to fall into
                    // week 1 of the next year, we drop the last week by
                    // subtracting 7 from the last day of the year.
                    int lastDoy = yearLength(isoYear);
                    int lastRelDow = (lastDoy - isoDoy + internalGet(DAY_OF_WEEK) -
                            getFirstDayOfWeek()) % 7;
                    if (lastRelDow < 0) lastRelDow += 7;
                    if ((6 - lastRelDow) >= getMinimalDaysInFirstWeek()) lastDoy -= 7;
                    int lastWoy = weekNumber(lastDoy, lastRelDow + 1);
                    woy = ((woy + lastWoy - 1) % lastWoy) + 1;
                }
                set(WEEK_OF_YEAR, woy);
                set(YEAR, isoYear);
                return;
            }
            case WEEK_OF_MONTH: {
                // This is tricky, because during the roll we may have to shift
                // to a different day of the week.  For example:

                //    s s  m  t  w  r  f
                //            1  2  3  4
                //    5 6  7  8  9 10 11

                // When rolling from the 5th or 6th back one week, we go to the
                // 1st (assuming that the first partial week counts).  The same
                // thing happens at the end of the month.

                // The other tricky thing is that we have to figure out whether
                // the first partial week actually counts or not, based on the
                // minimal first days in the week.  And we have to use the
                // correct first day of the week to delineate the week
                // boundaries.

                // Here's our algorithm.  First, we find the real boundaries of
                // the month.  Then we discard the first partial week if it
                // doesn't count in this locale.  Then we fill in the ends with
                // phantom days, so that the first partial week and the last
                // partial week are full weeks.  We then have a nice square
                // block of weeks.  We do the usual rolling within this block,
                // as is done elsewhere in this method.  If we wind up on one of
                // the phantom days that we added, we recognize this and pin to
                // the first or the last day of the month.  Easy, eh?

                // Normalize the DAY_OF_WEEK so that 0 is the first day of the week
                // in this locale.  We have dow in 0..6.
                int dow = internalGet(DAY_OF_WEEK) - getFirstDayOfWeek();
                if (dow < 0) dow += 7;

                // Find the day of the week (normalized for locale) for the first
                // of the month.
                int fdm = (dow - internalGet(DAY_OF_MONTH) + 1) % 7;
                if (fdm < 0) fdm += 7;

                // Get the first day of the first full week of the month,
                // including phantom days, if any.  Figure out if the first week
                // counts or not; if it counts, then fill in phantom days.  If
                // not, advance to the first real full week (skip the partial week).
                int start;
                if ((7 - fdm) < getMinimalDaysInFirstWeek())
                    start = 8 - fdm; // Skip the first partial week
                else
                    start = 1 - fdm; // This may be zero or negative

                // Get the day of the week (normalized for locale) for the last
                // day of the month.
                int monthLen = monthLength(internalGet(MONTH));
                int ldm = (monthLen - internalGet(DAY_OF_MONTH) + dow) % 7;
                // We know monthLen >= DAY_OF_MONTH so we skip the += 7 step here.

                // Get the limit day for the blocked-off rectangular month; that
                // is, the day which is one past the last day of the month,
                // after the month has already been filled in with phantom days
                // to fill out the last week.  This day has a normalized DOW of 0.
                int limit = monthLen + 7 - ldm;

                // Now roll between start and (limit - 1).
                gap = limit - start;
                int day_of_month = (internalGet(DAY_OF_MONTH) + amount * 7 -
                        start) % gap;
                if (day_of_month < 0) day_of_month += gap;
                day_of_month += start;

                // Finally, pin to the real start and end of the month.
                if (day_of_month < 1) day_of_month = 1;
                if (day_of_month > monthLen) day_of_month = monthLen;

                // Set the DAY_OF_MONTH.  We rely on the fact that this field
                // takes precedence over everything else (since all other fields
                // are also set at this point).  If this fact changes (if the
                // disambiguation algorithm changes) then we will have to unset
                // the appropriate fields here so that DAY_OF_MONTH is attended
                // to.
                set(DAY_OF_MONTH, day_of_month);
                return;
            }
            case DAY_OF_MONTH:
                max = monthLength(internalGet(MONTH));
                break;
            case DAY_OF_YEAR: {
                // Roll the day of year using millis.  Compute the millis for
                // the start of the year, and get the length of the year.
                long delta = amount * ONE_DAY; // Scale up from days to millis
                long min2 = time - (internalGet(DAY_OF_YEAR) - 1) * ONE_DAY;
                int yearLength = yearLength();
                time = (time + delta - min2) % (yearLength * ONE_DAY);
                if (time < 0) time += yearLength * ONE_DAY;
                setTimeInMillis(time + min2);
                return;
            }
            case DAY_OF_WEEK: {
                // Roll the day of week using millis.  Compute the millis for
                // the start of the week, using the first day of week setting.
                // Restrict the millis to [start, start+7days).
                long delta = amount * ONE_DAY; // Scale up from days to millis
                // Compute the number of days before the current day in this
                // week.  This will be a value 0..6.
                int leadDays = internalGet(DAY_OF_WEEK) - getFirstDayOfWeek();
                if (leadDays < 0) leadDays += 7;
                long min2 = time - leadDays * ONE_DAY;
                time = (time + delta - min2) % ONE_WEEK;
                if (time < 0) time += ONE_WEEK;
                setTimeInMillis(time + min2);
                return;
            }
            case DAY_OF_WEEK_IN_MONTH: {
                // Roll the day of week in the month using millis.  Determine
                // the first day of the week in the month, and then the last,
                // and then roll within that range.
                long delta = amount * ONE_WEEK; // Scale up from weeks to millis
                // Find the number of same days of the week before this one
                // in this month.
                int preWeeks = (internalGet(DAY_OF_MONTH) - 1) / 7;
                // Find the number of same days of the week after this one
                // in this month.
                int postWeeks = (monthLength(internalGet(MONTH)) -
                        internalGet(DAY_OF_MONTH)) / 7;
                // From these compute the min and gap millis for rolling.
                long min2 = time - preWeeks * ONE_WEEK;
                long gap2 = ONE_WEEK * (preWeeks + postWeeks + 1); // Must add 1!
                // Roll within this range
                time = (time + delta - min2) % gap2;
                if (time < 0) time += gap2;
                setTimeInMillis(time + min2);
                return;
            }
            case ZONE_OFFSET:
            case DST_OFFSET:
            default:
                // These fields cannot be rolled
                throw new IllegalArgumentException();
        }

        // These are the standard roll instructions.  These work for all
        // simple cases, that is, cases in which the limits are fixed, such
        // as the hour, the month, and the era.
        gap = max - min + 1;
        int value = internalGet(field) + amount;
        value = (value - min) % gap;
        if (value < 0) value += gap;
        value += min;

        set(field, value);
    }

    /**
     * Returns minimum value for the given field.
     * e.g. for Jalali DAY_OF_MONTH, 1
     * Please see Calendar.getMinimum for descriptions on parameters and
     * the return value.
     */
    public int getMinimum(int field) {
        return MIN_VALUES[field];
    }

    /**
     * Returns maximum value for the given field.
     * e.g. for Jalali DAY_OF_MONTH, 31
     * Please see Calendar.getMaximum for descriptions on parameters and
     * the return value.
     */
    public int getMaximum(int field) {
        return MAX_VALUES[field];
    }

    /**
     * Returns highest minimum value for the given field if varies.
     * Otherwise same as getMinimum(). For Jalali, no difference.
     * Please see Calendar.getGreatestMinimum for descriptions on parameters
     * and the return value.
     */
    public int getGreatestMinimum(int field) {
        return MIN_VALUES[field];
    }

    /**
     * Returns lowest maximum value for the given field if varies.
     * Otherwise same as getMaximum(). For Jalali DAY_OF_MONTH, 29
     * Please see Calendar.getLeastMaximum for descriptions on parameters and
     * the return value.
     */
    public int getLeastMaximum(int field) {
        return LEAST_MAX_VALUES[field];
    }

    /**
     * Return the minimum value that this field could have, given the current date.
     * For the Jalali jalalicalendar, this is the same as getMinimum() and getGreatestMinimum().
     *
     * @since 1.2
     */
    public int getActualMinimum(int field) {
        return getMinimum(field);
    }

    /**
     * Return the maximum value that this field could have, given the current date.
     * For example, with the date "Esf 3, 1379" and the DAY_OF_MONTH field, the actual
     * maximum would be 30; for "Esf 3, 1380" it s 29.  Similarly for a Hebrew jalalicalendar,
     * for some years the actual maximum for MONTH is 12, and for others 13.
     *
     * @since 1.2
     */
    public int getActualMaximum(int field) {
        switch (field) {
            // we have functions that enable us to fast-path number of days in month
            // of year
            case DAY_OF_MONTH:
                return monthLength(get(MONTH));

            case DAY_OF_YEAR:
                return yearLength();

            // for week of year, week of month, or day of week in month, we
            // just fall back on the default implementation in Calendar (I'm not sure
            // we could do better by having special calculations here)
            case WEEK_OF_YEAR:
            case WEEK_OF_MONTH:
            case DAY_OF_WEEK_IN_MONTH:
                return super.getActualMaximum(field);

            case YEAR:
                /* The year computation is no different, in principle, from the
                 * others, however, the range of possible maxima is large.  In
                 * addition, the way we know we've exceeded the range is different.
                 * For these reasons, we use the special case code below to handle
                 * this field.
                 *
                 * The actual maxima for YEAR is like this:
                 *
                 *     Jalali    = 292275056 BH - 292278994 AH
                 *
                 * We know we've exceeded the maximum when either the month, date,
                 * time, or era changes in response to setting the year.  We don't
                 * check for month, date, and time here because the year and era are
                 * sufficient to detect an invalid year setting.  NOTE: If code is
                 * added to check the month and date in the future for some reason,
                 * Esf 30 must be allowed to shift to Farv 1 when setting the year.
                 */
            {
                Calendar cal = (Calendar) this.clone();
                cal.setLenient(true);

                int era = cal.get(ERA);
                Date d = cal.getTime();

                /* Perform a binary search, with the invariant that lowGood is a
                * valid year, and highBad is an out of range year.
                */
                int lowGood = LEAST_MAX_VALUES[YEAR];
                int highBad = MAX_VALUES[YEAR] + 1;
                while ((lowGood + 1) < highBad) {
                    int y = (lowGood + highBad) / 2;
                    cal.set(YEAR, y);
                    if (cal.get(YEAR) == y && cal.get(ERA) == era) {
                        lowGood = y;
                    } else {
                        highBad = y;
                        cal.setTime(d); // Restore original fields
                    }
                }

                return lowGood;
            }

            // and we know none of the other fields have variable maxima in
            // JalaliCalendar, so we can just return the fixed maximum
            default:
                return getMaximum(field);
        }
    }

//////////////////////
// Proposed public API
//////////////////////

    /**
     * Return true if the current time for this Calendar is in Daylignt
     * Savings Time.
     * <p/>
     * Note -- MAKE THIS PUBLIC AT THE NEXT API CHANGE.  POSSIBLY DEPRECATE
     * AND REMOVE TimeZone.inDaylightTime().
     */
    boolean inDaylightTime() {
        if (!getTimeZone().useDaylightTime()) return false;
//        return true;
        complete(); // Force update of DST_OFFSET field
        return internalGet(DST_OFFSET) != 0;
    }

    /**
     * Return the year that corresponds to the <code>WEEK_OF_YEAR</code> field.
     * This may be one year before or after the jalalicalendar year stored
     * in the <code>YEAR</code> field.  For example, Farvardin 1, 1381 is considered
     * Thursday of week 53 of 1380 (if minimal days in first week is
     * 3 or more, and the first day of the week is Saturday).  Given
     * these same settings, the ISO year of Farvardin 1, 1381 is
     * 1380.
     * <p/>
     * Warning: This method will complete all fields.
     *
     * @return the year corresponding to the <code>WEEK_OF_YEAR</code> field, which
     *         may be one year before or after the <code>YEAR</code> field.
     * @see #WEEK_OF_YEAR
     */
    int getISOYear() {
        complete();
        int woy = internalGet(WEEK_OF_YEAR);
        // Get the ISO year, which matches the week of year.  This
        // may be one year before or after the jalalicalendar year.
        int isoYear = internalGet(YEAR);
        if (internalGet(MONTH) == FARVARDIN) {
            if (woy >= 52) {
                --isoYear;
            }
        } else {
            if (internalGet(MONTH) == ESFAND) {
                if (woy == 1) {
                    ++isoYear;
                }
            }
        }
        return isoYear;
    }

/////////////////////////////
// Time => Fields computation
/////////////////////////////

    /**
     * Overrides Calendar
     * Converts UTC as milliseconds to time field values.
     * The time is <em>not</em>
     * recomputed first; to recompute the time, then the fields, call the
     * <code>complete</code> method.
     *
     * @see java.util.Calendar#complete
     */
    protected void computeFields() {
        int rawOffset = getTimeZone().getRawOffset();
        long localMillis = time + rawOffset;

        /* Check for very extreme values -- millis near Long.MIN_VALUE or
         * Long.MAX_VALUE.  For these values, adding the zone offset can push
         * the millis past MAX_VALUE to MIN_VALUE, or vice versa.  This produces
         * the undesirable effect that the time can wrap around at the ends,
         * yielding, for example, a Date(Long.MAX_VALUE) with a big BH year
         * (should be AH).  Handle this by pinning such values to Long.MIN_VALUE
         * or Long.MAX_VALUE. */
        if (time > 0 && localMillis < 0 && rawOffset > 0) {
            localMillis = Long.MAX_VALUE;
        } else if (time < 0 && localMillis > 0 && rawOffset < 0) {
            localMillis = Long.MIN_VALUE;
        }

        // Time to fields takes the wall millis (Standard or DST).
        timeToFields(localMillis, false);

        // commented by Amir Pashazadeh
//        int era = internalGetEra();
//        int year = internalGet(YEAR);
//        int month = internalGet(MONTH);
//        int date = internalGet(DATE);
//        int dayOfWeek = internalGet(DAY_OF_WEEK);

        long days = (localMillis / ONE_DAY);
        int millisInDay = (int) (localMillis - (days * ONE_DAY));
        if (millisInDay < 0) millisInDay += ONE_DAY;

        // Call getOffset() to get the TimeZone offset.  The millisInDay value must
        // be standard local millis.
        // TBD: getOffset assumes a GregorianCalendar
        int dstOffset = 0/*getTimeZone().getOffset(era,year,month,date,dayOfWeek,millisInDay)
                        - rawOffset*/;

        // Adjust our millisInDay for DST, if necessary.
        millisInDay += dstOffset;

        // If DST has pushed us into the next day, we must call timeToFields() again.
        // This happens in DST between 12:00 am and 1:00 am every day.  The call to
        // timeToFields() will give the wrong day, since the Standard time is in the
        // previous day.
        if (millisInDay >= ONE_DAY) {
            long dstMillis = localMillis + dstOffset;
            millisInDay -= ONE_DAY;
            // As above, check for and pin extreme values
            if (localMillis > 0 && dstMillis < 0 && dstOffset > 0) {
                dstMillis = Long.MAX_VALUE;
            } else if (localMillis < 0 && dstMillis > 0 && dstOffset < 0) {
                dstMillis = Long.MIN_VALUE;
            }
            timeToFields(dstMillis, false);
        }

        // Fill in all time-related fields based on millisInDay.  Call internalSet()
        // so as not to perturb flags.
        internalSet(MILLISECOND, millisInDay % 1000);
        millisInDay /= 1000;
        internalSet(SECOND, millisInDay % 60);
        millisInDay /= 60;
        internalSet(MINUTE, millisInDay % 60);
        millisInDay /= 60;
        internalSet(HOUR_OF_DAY, millisInDay);
        internalSet(AM_PM, millisInDay / 12); // Assume AM == 0
        internalSet(HOUR, millisInDay % 12);

        internalSet(ZONE_OFFSET, rawOffset);
        internalSet(DST_OFFSET, dstOffset);

        // Careful here: We are manually setting the time stamps[] flags to
        // INTERNALLY_SET, so we must be sure that the above code actually does
        // set all these fields.
        for (int i = 0; i < FIELD_COUNT; ++i) {
            setStamp(i, COMPUTED);
            isSet[i] = true; // Remove later
        }
    }

    // --BE

    /**
     * Convert the time as milliseconds to the date fields.  Millis must be
     * given as local wall millis to get the correct local day.  For example,
     * if it is 11:30 pm Standard, and DST is in effect, the correct DST millis
     * must be passed in to get the right date.
     * <p/>
     * Fields that are completed by this method: ERA, YEAR, MONTH, DATE,
     * DAY_OF_WEEK, DAY_OF_YEAR, WEEK_OF_YEAR, WEEK_OF_MONTH,
     * DAY_OF_WEEK_IN_MONTH.
     *
     * @param theTime the time in wall millis (either Standard or DST),
     *                whichever is in effect
     * @param quick   if true, only compute the ERA, YEAR, MONTH, DATE,
     *                DAY_OF_WEEK, and DAY_OF_YEAR.
     */
    private void timeToFields(long theTime, boolean quick) {
        int rawYear, year, month, date, dayOfWeek, dayOfYear, /*weekCount, */era;
//        boolean isLeap;

        // Compute the year, month, and day of month from the given millis.
        // The Jalali internal day we use is zero for Friday Farvardin 1, 1376.
        long jalaliEpochDay = millisToJalaliDay(theTime) - FAR_1_1376_JALALI_DAY;
        // Here we convert from the day number to the multiple radix
        // representation.  We use 33-year and 4-year cycles.
        // For example, the 4-year cycle has 4 years + 1 leap day; giving
        // 1461 == 365*4 + 1 days, and the 33-year cycle has 33 years + 8
        // leap day; giving 12053 == 365*33 + 8 days.
        int[] rem = new int[1];
        int n33 = floorDivide(jalaliEpochDay, 12053, rem); // 33-year cycle length
        int n4 = floorDivide(rem[0], 1461, rem); // 4-year cycle length
        int n1 = floorDivide(rem[0], 365, rem);
        rawYear = BASE_YEAR + 33 * n33 + 4 * n4 + n1;
        dayOfYear = rem[0]; // zero-based day of year
        if (n4 != 7 && n1 == 4) {
            dayOfYear = 365; // Esf 30 at end of 4-year cycle
        } else {
            ++rawYear;
            if (n4 == 8) {
                dayOfYear++; // last year of last 4-year cycle is not Leap
                // add the extra day to next year
            } else if (n4 == 7 && n1 == 4) {
                dayOfYear = 0;  // 1 Farv of the last year of 33-year cycle
            }
        }

//        isLeap = floorDivide((rawYear + 11), 33, rem) != 8;
//        isLeap = isLeap && rem[0] % 4 == 0;

        // Jalali day zero is a Friday
        dayOfWeek = (int) ((jalaliEpochDay + FRIDAY - SUNDAY) % 7);

        if (dayOfYear < NUM_DAYS[6])
            month = dayOfYear / 31; // zero-based month
        else
            month = (dayOfYear - 6) / 30; // zero-based month
        date = dayOfYear - NUM_DAYS[month] + 1; // one-based DOM

        // Normalize day of week
        dayOfWeek += (dayOfWeek < 0) ? (SUNDAY + 7) : SUNDAY;

        era = AH;
        year = rawYear;
        if (year < 1) {
            era = BH;
            year = 1 - year;
        }

        internalSet(ERA, era);
        internalSet(YEAR, year);
        //noinspection PointlessArithmeticExpression
        internalSet(MONTH, month + FARVARDIN); // 0-based
        internalSet(DATE, date);
        internalSet(DAY_OF_WEEK, dayOfWeek);
        internalSet(DAY_OF_YEAR, ++dayOfYear); // Convert from 0-based to 1-based
        if (quick) {
            return;
        }

        // WEEK_OF_YEAR start
        // Compute the week of the year.  Valid week numbers run from 1 to 52
        // or 53, depending on the year, the first day of the week, and the
        // minimal days in the first week.  Days at the start of the year may
        // fall into the last week of the previous year; days at the end of
        // the year may fall into the first week of the next year.
        int relDow = (dayOfWeek + 7 - getFirstDayOfWeek()) % 7; // 0..6
        int relDowFar1 = (dayOfWeek - dayOfYear + 701 - getFirstDayOfWeek()) % 7; // 0..6
        int woy = (dayOfYear - 1 + relDowFar1) / 7; // 0..53
        if ((7 - relDowFar1) >= getMinimalDaysInFirstWeek()) {
            ++woy;
        }

        if (dayOfYear > 359) { // Fast check which eliminates most cases
            // Check to see if we are in the last week; if so, we need
            // to handle the case in which we are the first week of the
            // next year.
            int lastDoy = yearLength();
            int lastRelDow = (relDow + lastDoy - dayOfYear) % 7;
            if (lastRelDow < 0) {
                lastRelDow += 7;
            }
            if (((6 - lastRelDow) >= getMinimalDaysInFirstWeek()) &&
                    ((dayOfYear + 7 - relDow) > lastDoy)) {
                woy = 1;
            }
        } else if (woy == 0) {
// We are the last week of the previous year.
            int prevDoy = dayOfYear + yearLength(rawYear - 1);
            woy = weekNumber(prevDoy, dayOfWeek);
        }
        internalSet(WEEK_OF_YEAR, woy);
        // WEEK_OF_YEAR end

        internalSet(WEEK_OF_MONTH, weekNumber(date, dayOfWeek));
        internalSet(DAY_OF_WEEK_IN_MONTH, (date - 1) / 7 + 1);
    }

/////////////////////////////
// Fields => Time computation

    /////////////////////////////
    // --BE

    /**
     * Overrides Calendar
     * Converts time field values to UTC as milliseconds.
     *
     * @throws IllegalArgumentException if any fields are invalid.
     */
    protected void computeTime() {
        if (!isLenient() && !validateFields())
            throw new IllegalArgumentException();

        // This function takes advantage of the fact that unset fields in
        // the time field list have a value of zero.

        // The year defaults to the epoch start.
        int year = (getStamp(YEAR) != UNSET) ? internalGet(YEAR) : BASE_YEAR;

        int era;
        if (getStamp(ERA) != UNSET) {
            era = internalGet(ERA);
            if (era == BH)
                year = 1 - year;
                // Even in lenient mode we disallow ERA values other than AH & BH
            else if (era != AH)
                throw new IllegalArgumentException("Invalid era");
        }

        long jalaliDay = computeJalaliDay(year);
        long millis = jalaliDayToMillis(jalaliDay);

        // Do the time portion of the conversion.

        int millisInDay = 0;

        // Find the best set of fields specifying the time of day.  There
        // are only two possibilities here; the HOUR_OF_DAY or the
        // AM_PM and the HOUR.
        int hourOfDayStamp = getStamp(HOUR_OF_DAY);
        int hourStamp = getStamp(HOUR);
        int bestStamp = (hourStamp > hourOfDayStamp) ? hourStamp : hourOfDayStamp;

        // Hours
        if (bestStamp != UNSET) {
            if (bestStamp == hourOfDayStamp)
                // Don't normalize here; let overflow bump into the next period.
                // This is consistent with how we handle other fields.
                millisInDay += internalGet(HOUR_OF_DAY);

            else {
                // Don't normalize here; let overflow bump into the next period.
                // This is consistent with how we handle other fields.
                millisInDay += internalGet(HOUR);

                millisInDay += 12 * internalGet(AM_PM); // Default works for unset AM_PM
            }
        }

        // We use the fact that unset == 0; we start with millisInDay
        // == HOUR_OF_DAY.
        millisInDay *= 60;
        millisInDay += internalGet(MINUTE); // now have minutes
        millisInDay *= 60;
        millisInDay += internalGet(SECOND); // now have seconds
        millisInDay *= 1000;
        millisInDay += internalGet(MILLISECOND); // now have millis

        // Compute the time zone offset and DST offset.  There are two potential
        // ambiguities here.  We'll assume a 2:00 am (wall time) switchover time
        // for discussion purposes here.
        // 1. The transition into DST.  Here, a designated time of 2:00 am - 2:59 am
        //    can be in standard or in DST depending.  However, 2:00 am is an invalid
        //    representation (the representation jumps from 1:59:59 am Std to 3:00:00 am DST).
        //    We assume standard time.
        // 2. The transition out of DST.  Here, a designated time of 1:00 am - 1:59 am
        //    can be in standard or DST.  Both are valid representations (the rep
        //    jumps from 1:59:59 DST to 1:00:00 Std).
        //    Again, we assume standard time.
        // We use the TimeZone object, unless the user has explicitly set the ZONE_OFFSET
        // or DST_OFFSET fields; then we use those fields.
        TimeZone zone = getTimeZone();
        int zoneOffset = (getStamp(ZONE_OFFSET) >= MINIMUM_USER_STAMP)
                /*isSet(ZONE_OFFSET) && userSetZoneOffset*/ ?
                internalGet(ZONE_OFFSET) : zone.getRawOffset();

        // Now add date and millisInDay together, to make millis contain local wall
        // millis, with no zone or DST adjustments
        millis += millisInDay;

        int dstOffset;
        if (getStamp(ZONE_OFFSET) >= MINIMUM_USER_STAMP
            /*isSet(DST_OFFSET) && userSetDSTOffset*/)
            dstOffset = internalGet(DST_OFFSET);
        else {
            /* Normalize the millisInDay to 0..ONE_DAY-1.  If the millis is out
             * of range, then we must call timeToFields() to recompute our
             * fields. */
            int[] normalizedMillisInDay = new int[1];
            floorDivide(millis, (int) ONE_DAY, normalizedMillisInDay);

            // We need to have the month, the day, and the day of the week.
            // Calling timeToFields will compute the MONTH and DATE fields.
            // If we're lenient then we need to call timeToFields() to
            // normalize the year, month, and date numbers.

            // commented by Amir Pashazadeh
//            int dow;

            if (isLenient() || getStamp(MONTH) == UNSET || getStamp(DATE) == UNSET
                    || millisInDay != normalizedMillisInDay[0]) {
                timeToFields(millis, true); // Use wall time; true == do quick computation

                // commented by Amir Pashazadeh
//                dow = internalGet(DAY_OF_WEEK); // DOW is computed by timeToFields
            } else {
                // It's tempting to try to use DAY_OF_WEEK here, if it
                // is set, but we CAN'T.  Even if it's set, it might have
                // been set wrong by the user.  We should rely only on
                // the Jalali day number, which has been computed correctly
                // using the disambiguation algorithm above. [LIU]

                // commented by Amir Pashazadeh
//                dow = jalaliDayToDayOfWeek(jalaliDay);
            }

            // It's tempting to try to use DAY_OF_WEEK here, if it
            // is set, but we CAN'T.  Even if it's set, it might have
            // been set wrong by the user.  We should rely only on
            // the Jalali day number, which has been computed correctly
            // using the disambiguation algorithm above. [LIU]
            // TBD: getOffset assumes a GregorianCalendar
            dstOffset = 0/*zone.getOffset(era,
                                       internalGet(YEAR),
                                       internalGet(MONTH),
                                       internalGet(DATE),
                                       dow,
                                       normalizedMillisInDay[0]) -
                zoneOffset*/;
            // Note: Because we pass in wall millisInDay, rather than
            // standard millisInDay, we interpret "1:00 am" on the day
            // of cessation of DST as "1:00 am Std" (assuming the time
            // of cessation is 2:00 am).
        }

        // Store our final computed GMT time, with timezone adjustments.
        time = millis - zoneOffset - dstOffset;
    }

    // --BE

    /**
     * Compute the Jalali day number, using the given year and the
     * remaining fields.
     *
     * @param year the adjusted year number, with 0 indicating the
     *             year 1 BH, -1 indicating 2 BH, etc.
     * @return the Jalali day number
     */
    private long computeJalaliDay(int year) {
        int month = 0, date, y;

        // commented by Amir Pashazadeh
//        long millis = 0;

        // Find the most recent group of fields specifying the day within
        // the year.  These may be any of the following combinations:
        //   MONTH + DAY_OF_MONTH
        //   MONTH + WEEK_OF_MONTH + DAY_OF_WEEK
        //   MONTH + DAY_OF_WEEK_IN_MONTH + DAY_OF_WEEK
        //   DAY_OF_YEAR
        //   WEEK_OF_YEAR + DAY_OF_WEEK
        // We look for the most recent of the fields in each group to determine
        // the age of the group.  For groups involving a week-related field such
        // as WEEK_OF_MONTH, DAY_OF_WEEK_IN_MONTH, or WEEK_OF_YEAR, both the
        // week-related field and the DAY_OF_WEEK must be set for the group as a
        // whole to be considered.
        int dowStamp = getStamp(DAY_OF_WEEK);
        int monthStamp = getStamp(MONTH);
        int domStamp = getStamp(DAY_OF_MONTH);
        int womStamp = aggregateStamp(getStamp(WEEK_OF_MONTH), dowStamp);
        int dowimStamp = aggregateStamp(getStamp(DAY_OF_WEEK_IN_MONTH), dowStamp);
        int doyStamp = getStamp(DAY_OF_YEAR);
        int woyStamp = aggregateStamp(getStamp(WEEK_OF_YEAR), dowStamp);

        int bestStamp = domStamp;
        if (womStamp > bestStamp) bestStamp = womStamp;
        if (dowimStamp > bestStamp) bestStamp = dowimStamp;
        if (doyStamp > bestStamp) bestStamp = doyStamp;
        if (woyStamp > bestStamp) bestStamp = woyStamp;

        /* No complete combination exists.  Look for WEEK_OF_MONTH,
         * DAY_OF_WEEK_IN_MONTH, or WEEK_OF_YEAR alone.  Treat DAY_OF_WEEK alone
         * as DAY_OF_WEEK_IN_MONTH.
         */
        if (bestStamp == UNSET) {
            womStamp = getStamp(WEEK_OF_MONTH);
            dowimStamp = Math.max(getStamp(DAY_OF_WEEK_IN_MONTH), dowStamp);
            woyStamp = getStamp(WEEK_OF_YEAR);
            bestStamp = Math.max(Math.max(womStamp, dowimStamp), woyStamp);

            /* Treat MONTH alone or no fields at all as DAY_OF_MONTH.  This may
             * result in bestStamp = domStamp = UNSET if no fields are set,
             * which indicates DAY_OF_MONTH.
             */
            if (bestStamp == UNSET) {
                bestStamp = domStamp = monthStamp;
            }
        }

        boolean useMonth = false;

        if (bestStamp == domStamp ||
                bestStamp == womStamp ||
                bestStamp == dowimStamp) {
            useMonth = true;

            // We have the month specified. Make it 0-based for the algorithm.
            //noinspection PointlessArithmeticExpression
            month = (monthStamp != UNSET) ? internalGet(MONTH) - FARVARDIN : 0;

            // If the month is out of range, adjust it into range
            if (month < 0 || month > 11) {
                int[] rem = new int[1];
                year += floorDivide(month, 12, rem);
                month = rem[0];
            }
        }

        y = year - BASE_YEAR - 1;
        int[] rem = new int[1];
        long jalaliDay = FAR_1_1376_JALALI_DAY + 365L * y - 1 /* Constant */;
        jalaliDay += floorDivide(y, 33, rem) * 8;
        jalaliDay += floorDivide(rem[0], 4);
        jalaliDay -= floorDivide(rem[0], 32);
        // At this point jalaliDay is the 0-based day BEFORE the first day of
        // Farvardin 1, 1376.

        if (useMonth) {

            jalaliDay += NUM_DAYS[month];

            if (bestStamp == domStamp) {

                date = (getStamp(DAY_OF_MONTH) != UNSET) ? internalGet(DAY_OF_MONTH) : 1;
            } else { // assert(bestStamp == womStamp || bestStamp == dowimStamp)
                // Compute from day of week plus week number or from the day of
                // week plus the day of week in month.  The computations are
                // almost identical.

                // Find the day of the week for the first of this month.  This
                // is zero-based, with 0 being the locale-specific first day of
                // the week.  Add 1 to get the 1st day of month.  Subtract
                // getFirstDayOfWeek() to make 0-based.
                int fdm = jalaliDayToDayOfWeek(jalaliDay + 1) - getFirstDayOfWeek();
                if (fdm < 0) fdm += 7;

                // Find the start of the first week.  This will be a date from
                // 1..-6.  It represents the locale-specific first day of the
                // week of the first day of the month, ignoring minimal days in
                // first week.
                date = 1 - fdm + (dowStamp != UNSET ?
                        (internalGet(DAY_OF_WEEK) - getFirstDayOfWeek()) : 0);

                if (bestStamp == womStamp) {
                    // Adjust for minimal days in first week.
                    if ((7 - fdm) < getMinimalDaysInFirstWeek()) date += 7;

                    // Now adjust for the week number.
                    date += 7 * (internalGet(WEEK_OF_MONTH) - 1);
                } else { // assert(bestStamp == dowimStamp)
                    // Adjust into the month, if needed.
                    if (date < 1) date += 7;

                    // We are basing this on the day-of-week-in-month.  The only
                    // trickiness occurs if the day-of-week-in-month is
                    // negative.
                    int dim = getStamp(DAY_OF_WEEK_IN_MONTH) != UNSET ?
                            internalGet(DAY_OF_WEEK_IN_MONTH) : 1;
                    if (dim >= 0)
                        date += 7 * (dim - 1);
                    else {
                        // Move date to the last of this day-of-week in this
                        // month, then back up as needed.  If dim==-1, we don't
                        // back up at all.  If dim==-2, we back up once, etc.
                        // Don't back up past the first of the given day-of-week
                        // in this month.  Note that we handle -2, -3,
                        // etc. correctly, even though values < -1 are
                        // technically disallowed.
                        date += ((monthLength(internalGet(MONTH), year) - date) / 7 + dim + 1) * 7;
                    }
                }
            }

            jalaliDay += date;
        } else {
            // assert(bestStamp == doyStamp || bestStamp == woyStamp ||
            // bestStamp == UNSET).  In the last case we should use Farvardin 1.

            // No month, start with Farvardin 0 (day before Farv 1), then adjust.

            if (bestStamp == doyStamp) {
                jalaliDay += internalGet(DAY_OF_YEAR);
            } else { // assert(bestStamp == woyStamp)
                // Compute from day of week plus week of year

                // Find the day of the week for the first of this year.  This
                // is zero-based, with 0 being the locale-specific first day of
                // the week.  Add 1 to get the 1st day of month.  Subtract
                // getFirstDayOfWeek() to make 0-based.
                int fdy = jalaliDayToDayOfWeek(jalaliDay + 1) - getFirstDayOfWeek();
                if (fdy < 0) fdy += 7;

                // Find the start of the first week.  This may be a valid date
                // from 1..7, or a date before the first, from 0..-6.  It
                // represents the locale-specific first day of the week
                // of the first day of the year.

                // First ignore the minimal days in first week.
                date = 1 - fdy + (dowStamp != UNSET ?
                        (internalGet(DAY_OF_WEEK) - getFirstDayOfWeek()) : 0);

                // Adjust for minimal days in first week.
                if ((7 - fdy) < getMinimalDaysInFirstWeek()) date += 7;

                // Now adjust for the week number.
                date += 7 * (internalGet(WEEK_OF_YEAR) - 1);

                jalaliDay += date;
            }
        }

        return jalaliDay;
    }

/////////////////
// Implementation
/////////////////

    /**
     * Converts time as milliseconds to Jalali day.
     *
     * @param millis the given milliseconds.
     * @return the Jalali day number.
     */
    private static long millisToJalaliDay(long millis) {
        return EPOCH_JALALI_DAY + floorDivide(millis, ONE_DAY);
    }

    /**
     * Converts Jalali day to time as milliseconds.
     *
     * @param jalali the given Jalali day number.
     * @return time as milliseconds.
     */
    private static long jalaliDayToMillis(long jalali) {
        return (jalali - EPOCH_JALALI_DAY) * ONE_DAY;
    }

    private static int jalaliDayToDayOfWeek(long jalali) {
        // If jalali is negative, then jalali%7 will be negative, so we adjust
        // accordingly.  We add 5 because Jalali day 0 is Friday.
        int dayOfWeek = (int) ((jalali + FRIDAY - SUNDAY) % 7);
        return dayOfWeek + ((dayOfWeek < 0) ? (7 + SUNDAY) : SUNDAY);
    }

    /**
     * Divide two long integers, returning the floor of the quotient.
     * <p/>
     * Unlike the built-in division, this is mathematically well-behaved.
     * E.g., <code>-1/4</code> => 0
     * but <code>floorDivide(-1,4)</code> => -1.
     *
     * @param numerator   the numerator
     * @param denominator a divisor which must be > 0
     * @return the floor of the quotient.
     */
    private static long floorDivide(long numerator, long denominator) {
        // We do this computation in order to handle
        // a numerator of Long.MIN_VALUE correctly
        return (numerator >= 0) ?
                numerator / denominator :
                ((numerator + 1) / denominator) - 1;
    }

    /**
     * Divide two integers, returning the floor of the quotient.
     * <p/>
     * Unlike the built-in division, this is mathematically well-behaved.
     * E.g., <code>-1/4</code> => 0
     * but <code>floorDivide(-1,4)</code> => -1.
     *
     * @param numerator   the numerator
     * @param denominator a divisor which must be > 0
     * @return the floor of the quotient.
     */
    private static int floorDivide(int numerator, int denominator) {
        // We do this computation in order to handle
        // a numerator of Integer.MIN_VALUE correctly
        return (numerator >= 0) ?
                numerator / denominator :
                ((numerator + 1) / denominator) - 1;
    }

    /**
     * Divide two integers, returning the floor of the quotient, and
     * the modulus remainder.
     * <p/>
     * Unlike the built-in division, this is mathematically well-behaved.
     * E.g., <code>-1/4</code> => 0 and <code>-1%4</code> => -1,
     * but <code>floorDivide(-1,4)</code> => -1 with <code>remainder[0]</code> => 3.
     *
     * @param numerator   the numerator
     * @param denominator a divisor which must be > 0
     * @param remainder   an array of at least one element in which the value
     *                    <code>numerator mod denominator</code> is returned. Unlike <code>numerator
     *                    % denominator</code>, this will always be non-negative.
     * @return the floor of the quotient.
     */
    private static int floorDivide(int numerator, int denominator, int[] remainder) {
        if (numerator >= 0) {
            remainder[0] = numerator % denominator;
            return numerator / denominator;
        }
        int quotient = ((numerator + 1) / denominator) - 1;
        remainder[0] = numerator - (quotient * denominator);
        return quotient;
    }

    /**
     * Divide two integers, returning the floor of the quotient, and
     * the modulus remainder.
     * <p/>
     * Unlike the built-in division, this is mathematically well-behaved.
     * E.g., <code>-1/4</code> => 0 and <code>-1%4</code> => -1,
     * but <code>floorDivide(-1,4)</code> => -1 with <code>remainder[0]</code> => 3.
     *
     * @param numerator   the numerator
     * @param denominator a divisor which must be > 0
     * @param remainder   an array of at least one element in which the value
     *                    <code>numerator mod denominator</code> is returned. Unlike <code>numerator
     *                    % denominator</code>, this will always be non-negative.
     * @return the floor of the quotient.
     */
    private static int floorDivide(long numerator, int denominator, int[] remainder) {
        if (numerator >= 0) {
            remainder[0] = (int) (numerator % denominator);
            return (int) (numerator / denominator);
        }
        int quotient = (int) (((numerator + 1) / denominator) - 1);
        remainder[0] = (int) (numerator - (quotient * denominator));
        return quotient;
    }

    /**
     * Return the pseudo-time-stamp for two fields, given their
     * individual pseudo-time-stamps.  If either of the fields
     * is unset, then the aggregate is unset.  Otherwise, the
     * aggregate is the later of the two stamps.
     */
    private static int aggregateStamp(int stamp_a, int stamp_b) {
        return (stamp_a != UNSET && stamp_b != UNSET) ?
                Math.max(stamp_a, stamp_b) : UNSET;
    }

    /**
     * Return the week number of a day, within a period. This may be the week number in
     * a year, or the week number in a month. Usually this will be a value >= 1, but if
     * some initial days of the period are excluded from week 1, because
     * minimalDaysInFirstWeek is > 1, then the week number will be zero for those
     * initial days. Requires the day of week for the given date in order to determine
     * the day of week of the first day of the period.
     *
     * @param dayOfPeriod Day-of-year or day-of-month. Should be 1 for first day of period.
     * @param dayOfWeek   Day-of-week for given dayOfPeriod. 1-based with 1=Sunday.
     * @return Week number, one-based, or zero if the day falls in part of the
     *         month before the first week, when there are days before the first
     *         week because the minimum days in the first week is more than one.
     */
    private int weekNumber(int dayOfPeriod, int dayOfWeek) {
        // Determine the day of the week of the first day of the period
        // in question (either a year or a month).  Zero represents the
        // first day of the week on this jalalicalendar.
        int periodStartDayOfWeek = (dayOfWeek - getFirstDayOfWeek() - dayOfPeriod + 1) % 7;
        if (periodStartDayOfWeek < 0) periodStartDayOfWeek += 7;

        // Compute the week number.  Initially, ignore the first week, which
        // may be fractional (or may not be).  We add periodStartDayOfWeek in
        // order to fill out the first week, if it is fractional.
        int weekNo = (dayOfPeriod + periodStartDayOfWeek - 1) / 7;

        // If the first week is long enough, then count it.  If
        // the minimal days in the first week is one, or if the period start
        // is zero, we always increment weekNo.
        if ((7 - periodStartDayOfWeek) >= getMinimalDaysInFirstWeek()) ++weekNo;

        return weekNo;
    }

    private int monthLength(int month, int year) {
        return isLeapYear(year) ? LEAP_MONTH_LENGTH[month] : MONTH_LENGTH[month];
    }

    private int monthLength(int month) {
        int year = internalGet(YEAR);
        if (internalGetEra() == BH) {
            year = 1 - year;
        }
        return monthLength(month, year);
    }

    /*
     * Returns the length of the previous month.  For Farvardin, returns the
     * arbitrary value 29, which will not be used:  This value is passed to
     * SimpleTimeZone.getOffset(), and if the month is -1 (the month before
     * Farvardin), the day value will be ignored.
     */
    // commented by Amir Pashazadeh
//    private final int prevMonthLength(int month) {
//        return (month > 1) ? monthLength(month - 1) : 29;
//    }

    private int yearLength(int year) {
        return isLeapYear(year) ? 366 : 365;
    }

    private int yearLength() {
        return isLeapYear(internalGet(YEAR)) ? 366 : 365;
    }

    /**
     * After adjustments such as add(MONTH), add(YEAR), we don't want the
     * month to jump around.  E.g., we don't want Jan 31 + 1 month to go to Mar
     * 3, we want it to go to Feb 28.  Adjustments which might run into this
     * problem call this method to retain the proper month.
     */
    private void pinDayOfMonth() {
        int monthLen = monthLength(internalGet(MONTH));
        int dom = internalGet(DAY_OF_MONTH);
        if (dom > monthLen) set(DAY_OF_MONTH, monthLen);
    }

    /**
     * Validates the values of the set time fields.
     */
    private boolean validateFields() {
        for (int field = 0; field < FIELD_COUNT; field++) {
            // Ignore DATE and DAY_OF_YEAR which are handled below
            if (field != DATE &&
                    field != DAY_OF_YEAR &&
                    isSet(field) &&
                    !boundsCheck(internalGet(field), field))

                return false;
        }

        // Values differ in Least-Maximum and Maximum should be handled
        // specially.
        if (isSet(DATE)) {
            int date = internalGet(DATE);
            if (date < getMinimum(DATE) ||
                    date > monthLength(internalGet(MONTH))) {
                return false;
            }
        }

        if (isSet(DAY_OF_YEAR)) {
            int days = internalGet(DAY_OF_YEAR);
            if (days < 1 || days > yearLength()) return false;
        }

        // Handle DAY_OF_WEEK_IN_MONTH, which must not have the value zero.
        // We've checked against minimum and maximum above already.
        //noinspection RedundantIfStatement
        if (isSet(DAY_OF_WEEK_IN_MONTH) &&
                0 == internalGet(DAY_OF_WEEK_IN_MONTH))
            return false;

        return true;
    }

    /**
     * Validates the value of the given time field.
     */
    private boolean boundsCheck(int value, int field) {
        return value >= getMinimum(field) && value <= getMaximum(field);
    }

    /**
     * Return the ERA.  We need a special method for this because the
     * default ERA is AH, but a zero (unset) ERA is BH.
     */
    private int internalGetEra() {
        return isSet(ERA) ? internalGet(ERA) : AH;
    }

    private int getStamp(int index) {
        return getStamps()[index];
    }

    private void setStamp(int index, int value) {
        getStamps()[index] = value;
    }

    private int[] getStamps() {
        try {
            Field field = Calendar.class.getDeclaredField("stamp");
            field.setAccessible(true);
            return (int[]) field.get(this);
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            } else {
                throw new RuntimeException(e);
            }
        }
    }

    private void internalSet(int field, int value) {
        fields[field] = value;
    }



}