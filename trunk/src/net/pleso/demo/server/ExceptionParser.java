package net.pleso.demo.server;

/*
 * Этот клас нужен для обработки сообщений ошибок сервера
 * Переделывают сообщения iBatis и SQL сервера в удобный для пользователя вид
 */
public class ExceptionParser {
	
	private final static String plSQLExceptionText = "org.postgresql.util.PSQLException: ERROR:";
	
	public static String parseServerException(String errorText){
		int excIndex = errorText.indexOf(plSQLExceptionText);
		if (excIndex > -1)
			return errorText.substring(excIndex + plSQLExceptionText.length());
		return errorText;
	}

}
