package net.pleso.demo.client.localization;


import com.google.gwt.core.client.GWT;

public class Locale{

	private static Constants _const = null;

	public static Constants constants(){
		if (_const == null)
			_const = (Constants)GWT.create(Constants.class);
		return _const;
	}
	
	private static Messages _mess = null;

	public static Messages messages(){
		if (_mess == null)
			_mess = (Messages)GWT.create(Messages.class);
		return _mess;
	}
} 