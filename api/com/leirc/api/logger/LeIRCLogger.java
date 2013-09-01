package com.leirc.api.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class LeIRCLogger extends SimpleFormatter {

	@Override
	public String format(LogRecord record) {
		StringBuilder string = new StringBuilder();
		string.append(new SimpleDateFormat("dd/MM/YYYY HH:mm").format(new Date()));
		string.append(" " + record);
		return string.toString();
	}

}
