package net.pleso.framework.client.util;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

/**
 * Formats and parses dates and times using framework patterns.
 * Using {@link DateTimeFormat}.
 * 
 * @author Scater
 *
 */
public class DateUtil {

	private static class FrameworkDateTimeFormat extends DateTimeFormat {

		public FrameworkDateTimeFormat(String pattern) {
			super(pattern);
		}
	}

	/**
	 * Output date format. All controls in system will show date in this format.
	 */
	public final static String outputDatePattern = "dd-MM-yyyy HH:mm";

	/**
	 * Input date format. All this formats will be valid.
	 */
	public final static String[] inputDatePatterns = new String[] {
			"dd-MM-yyyy HH:mm", "dd-MM-yyyy" };

	/**
	 * Output time format. All controls in system will show time in this format.
	 */
	public final static String timePattern = "HH:mm";

	private final static FrameworkDateTimeFormat outputDateFormat = new FrameworkDateTimeFormat(
			outputDatePattern);

	private final static FrameworkDateTimeFormat simpleTimeFormat = new FrameworkDateTimeFormat(
			timePattern);

	/**
	 * Format a date object by output date format
	 * @param date the date object being formatted
	 * @return formatted date representation
	 */
	public static String formatDate(Date date) {
		return outputDateFormat.format(date);
	}

	/**
	 * Validate text by input date formats
	 * @param date the string being validated
	 * @return is date valid
	 */
	public static boolean isValidDate(String date) {
		FrameworkDateTimeFormat inputDateFormat = null;
		for (int i = 0; i < inputDatePatterns.length; i++) {
			inputDateFormat = new FrameworkDateTimeFormat(inputDatePatterns[i]);
			try {
				inputDateFormat.parse(date);
				// if date was parsed even if one time - than it is valid
				return true;
			} catch (IllegalArgumentException ex) {
				// "eat" parsing exception, because function should try another formats from inputDateFormats
			}
		}
		// date is not valid by any format
		return false;
	}

	/**
	 * Parses text to produce a {@link Date} value by input date formats.
	 * @param date the string being parsed
	 * @return  a parsed date/time value
	 */
	public static Date parseDate(String date) {
		FrameworkDateTimeFormat inputDateFormat = null;
		Date res = null;
		for (int i = 0; i < inputDatePatterns.length; i++) {
			inputDateFormat = new FrameworkDateTimeFormat(inputDatePatterns[i]);
			try {
				res = inputDateFormat.parse(date);
				// if date was parsed even if one time - than it is valid
				return res;
			} catch (IllegalArgumentException ex) {
				// "eat" parsing exception, because function should try another formats from inputDateFormats
			}
		}
		// date is not valid by any format
		throw new IllegalArgumentException("Date is not valid");
	}

	/**
	 * Format a time object by output time format
	 * @param time the date with time object being formatted
	 * @return formatted time representation
	 */
	public static String formatTime(Date time) {
		return simpleTimeFormat.format(time);
	}

	/**
	 * Validate text by input time format
	 * @param time the string being validated
	 * @return is time valid
	 */
	public static boolean isValidTime(String time) {
		try {
			simpleTimeFormat.parse(time);
		} catch (IllegalArgumentException ex) {
			return false;
		}
		return true;
	}

	/**
	 * Parses text to produce a {@link Date} value by input time formats.
	 * @param time the string being parsed
	 * @return  a parsed date/time value
	 */
	public static Date parseTime(String time) {
		return simpleTimeFormat.parse(time);
	}

}
