package com.leirc.api.rsrc;

import com.leirc.api.LeIRCApi;
import com.leirc.api.data.LeVersion;
import com.leirc.api.os.OS;
import com.leirc.api.user.User;
import com.leirc.api.user.UserHelper;

public class SessionData {
	public static boolean DEBUG = false;
	public static boolean WIN_COMPAT = false;
	public static OS CURRENT_OS = LeIRCApi.currentOS;
	public static User CURRENT_USER = UserHelper.CURRENT;
	public static boolean FIRST_LAUNCH = false;
	public static LeVersion CURRENT_VERSION = null;
}