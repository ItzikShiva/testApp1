package app.absproj.screenshot.api;

import com.google.gson.Gson;

import app.absproj.email.api.EmailService;
import app.absproj.screenshot.api.ScreenshotService;

public class ScreenshotUtils {
	static Gson gson = new Gson();
	static ScreenshotParams screenshotParams = new ScreenshotParams();
	static ScreenshotService screenshotService = new ScreenshotService();
}
