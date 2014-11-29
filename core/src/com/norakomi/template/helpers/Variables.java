package com.norakomi.template.helpers;

public class Variables {
	
	
	// V_Width/V_HEIGHT sets how big our game window on desktop will be, but on Android device
	// v_width/v_height need to be set to the specific devices dimensions
	public static int V_WIDTH = 800;
	public static int V_HEIGHT = 480;
	
	// This sets viewport variables for our camera and these dimensions will be 
	// stretched to the device's width/height (or V_WIDTH/V_HEIGHT that we set for desktop)
	public static int VIEWPORT_WIDTH = 312;//312 std, 256 voor lvl 3
	public static int VIEWPORT_HEIGHT = 192;

	public static float V_MAPWIDTH;
	
	public static int LEVEL = 1;

	public static float PLAYER_MAXX = 0.8f * VIEWPORT_WIDTH;
	public static float PLAYER_MINX = 0.2f * VIEWPORT_WIDTH;
	public static float PLAYER_MAXY = 0.8f * VIEWPORT_HEIGHT;
	public static float PLAYER_MINY = 0.2f * VIEWPORT_HEIGHT;
	public static float V_PLAYERWIDTH;
	public static float V_PLAYERHEIGHT;
	
	public static int DIRECTION;

	public static boolean DEBUG = true;
	public static boolean PLAYERDEAD;
	public static boolean TOUCHABLE;
	public static boolean PAUSE;
	
	public static boolean KILLABLE;
	public static boolean MOVINGUP, MOVINGDOWN, MOVINGLEFT, MOVINGRIGHT;
	public static boolean BUTTON_A_PRESSED, BUTTON_B_PRESSED, PAUSEBUTTON_PRESSED;
	
	public static int COLLECTEDREDPOWERUPS;

	public static float SCROLLTRACKER_X, SCROLLTRACKER_Y;
	public static float SCROLLSPEED_X, SCROLLSPEED_Y;
	public static float OLDSCROLLSPEED_X, OLDSCROLLSPEED_Y;

}
