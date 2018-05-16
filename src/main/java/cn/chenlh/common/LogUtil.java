package cn.chenlh.common;

public abstract class LogUtil {

	public static boolean isDebug = false;

	public static void i(Object... msgs) {
		if (!isDebug) {
			return;
		}

		for (Object msg : msgs) {
			System.out.print(msg + " ");
		}
		System.out.println();
	}

	public static void f(Object... msgs) {
		if (!isDebug) {
			return;
		}
		String msg = "";
		for (int i = 0; i < msgs.length; i++) {
			if (i == 0) {
				msg = (String) msgs[i];
				continue;
			}
			String m = msgs[i] == null ? "null" : msgs[i].toString();
			msg = msg.replaceFirst("\\?", m);
		}
		System.out.println(msg);
	}
	
}
