package io.github.sspanak.tt9.preferences.settings;

import android.content.Context;
import android.os.Build;

import io.github.sspanak.tt9.hacks.DeviceInfo;
import io.github.sspanak.tt9.preferences.screens.debug.ItemInputHandlingMode;
import io.github.sspanak.tt9.util.Logger;

class SettingsHacks extends BaseSettings {
	SettingsHacks(Context context) { super(context); }

	/************* debugging settings *************/

	public int getLogLevel() {
		return getStringifiedInt("pref_log_level", Logger.LEVEL);
	}

	public boolean getEnableSystemLogs() {
		return prefs.getBoolean("pref_enable_system_logs", false);
	}

	public int getInputHandlingMode() {
		return getStringifiedInt("pref_input_handling_mode", ItemInputHandlingMode.NORMAL);
	}


	/************* hack settings *************/

	public int getSuggestionScrollingDelay() {
		boolean defaultOn = DeviceInfo.noTouchScreen(context) && Build.VERSION.SDK_INT < Build.VERSION_CODES.Q;
		return prefs.getBoolean("pref_alternative_suggestion_scrolling", defaultOn) ? 200 : 0;
	}

	public boolean clearInsets() {
		return prefs.getBoolean("pref_clear_insets", DeviceInfo.isSonimGen2(context));
	}

	public boolean getFbMessengerHack() {
		return prefs.getBoolean("pref_hack_fb_messenger", false);
	}

	public boolean getGoogleChatHack() {
		return prefs.getBoolean("pref_hack_google_chat", false);
	}

	/**
	 * Protection against faulty devices, that sometimes send two (or more) click events
	 * per a single key press, which absolutely undesirable side effects.
	 * There were reports about this on <a href="https://github.com/sspanak/tt9/issues/117">Kyocera KYF31</a>
	 * and on <a href="https://github.com/sspanak/tt9/issues/399">CAT S22</a>.
	 */

	public int getKeyPadDebounceTime() {
		return getStringifiedInt("pref_key_pad_debounce_time", 0);
	}
}
