package com.burma.royal2d;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class future_tips extends Activity implements B4AActivity{
	public static future_tips mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "com.burma.royal2d", "com.burma.royal2d.future_tips");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (future_tips).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "com.burma.royal2d", "com.burma.royal2d.future_tips");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.burma.royal2d.future_tips", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (future_tips) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (future_tips) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return future_tips.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        if (!dontPause)
            BA.LogInfo("** Activity (future_tips) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (future_tips) Pause event (activity is not paused). **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        if (!dontPause) {
            processBA.setActivityPaused(true);
            mostCurrent = null;
        }

        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            future_tips mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (future_tips) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static int _numcolumns = 0;
public static int _itemsize = 0;
public static int _gap = 0;
public static String _papergetters = "";
public static int _totalgapwidth = 0;
public static int _itemwidth = 0;
public static anywheresoftware.b4a.objects.Timer _progresstimer = null;
public static boolean _iscall = false;
public static boolean _imgviewing = false;
public static boolean _showsavemsg = false;
public static anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public static boolean _adreceived = false;
public anywheresoftware.b4a.objects.ButtonWrapper _swithbtn = null;
public com.burma.royal2d.asviewpager _asviewpager1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbldaily = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblweekly = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblcalendar = null;
public anywheresoftware.b4a.objects.ButtonWrapper _menubtn = null;
public ir.aghajari.retrofitglide.Amir_Glide _glide = null;
public anywheresoftware.b4a.objects.PanelWrapper _progresspn = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _sv = null;
public anywheresoftware.b4a.admobwrapper.AdViewWrapper _banner = null;
public com.burma.royal2d.asviewpager _asviewpager2 = null;
public static String _offstring = "";
public static String _onstring = "";
public static boolean _hqb = false;
public com.burma.royal2d.main _main = null;
public com.burma.royal2d.report_details _report_details = null;
public com.burma.royal2d.mycode _mycode = null;
public com.burma.royal2d.public_chat _public_chat = null;
public com.burma.royal2d.gift_imageview _gift_imageview = null;
public com.burma.royal2d.settings _settings = null;
public com.burma.royal2d.profile_activity _profile_activity = null;
public com.burma.royal2d.guideline _guideline = null;
public com.burma.royal2d.login _login = null;
public com.burma.royal2d.history _history = null;
public com.burma.royal2d.apicall _apicall = null;
public com.burma.royal2d.firebasemessaging _firebasemessaging = null;
public com.burma.royal2d.liveutils _liveutils = null;
public com.burma.royal2d.lottohistory _lottohistory = null;
public com.burma.royal2d.lottosociety _lottosociety = null;
public com.burma.royal2d.privacy_policy _privacy_policy = null;
public com.burma.royal2d.setholidays _setholidays = null;
public com.burma.royal2d.starter _starter = null;
public com.burma.royal2d.store _store = null;
public com.burma.royal2d.threed _threed = null;
public com.burma.royal2d.gift_activity _gift_activity = null;
public com.burma.royal2d.b4xpages _b4xpages = null;
public com.burma.royal2d.b4xcollections _b4xcollections = null;
public com.burma.royal2d.httputils2service _httputils2service = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _appbar = null;
 //BA.debugLineNum = 41;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 42;BA.debugLine="hqb = quality";
_hqb = _quality();
 //BA.debugLineNum = 43;BA.debugLine="Activity.Color=mycode.bgColor";
mostCurrent._activity.setColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 44;BA.debugLine="mycode.SETnavigationcolor";
mostCurrent._mycode._setnavigationcolor /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 45;BA.debugLine="Dim appbar As Panel = mycode.appbar(\"ထိုင်းကဒ် /";
_appbar = new anywheresoftware.b4a.objects.PanelWrapper();
_appbar = mostCurrent._mycode._appbar /*anywheresoftware.b4a.objects.PanelWrapper*/ (mostCurrent.activityBA,"ထိုင်းကဒ် / အတိတ်စာရွက်",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 46;BA.debugLine="Activity.AddView(appbar,0,mycode.appbartop,100%x,";
mostCurrent._activity.AddView((android.view.View)(_appbar.getObject()),(int) (0),mostCurrent._mycode._appbartop /*int*/ ,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),mostCurrent._mycode._appbarheight /*int*/ );
 //BA.debugLineNum = 48;BA.debugLine="swithbtn.Initialize(\"switchbtn\")";
mostCurrent._swithbtn.Initialize(mostCurrent.activityBA,"switchbtn");
 //BA.debugLineNum = 49;BA.debugLine="appbar.AddView(swithbtn,appbar.Width-appbar.Heigh";
_appbar.AddView((android.view.View)(mostCurrent._swithbtn.getObject()),(int) (_appbar.getWidth()-_appbar.getHeight()),(int) (0),_appbar.getHeight(),_appbar.getHeight());
 //BA.debugLineNum = 50;BA.debugLine="swithbtn.Background =mycode.btnbg(False)";
mostCurrent._swithbtn.setBackground((android.graphics.drawable.Drawable)(mostCurrent._mycode._btnbg /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False).getObject()));
 //BA.debugLineNum = 52;BA.debugLine="swithbtn.Typeface = Typeface.FONTAWESOME";
mostCurrent._swithbtn.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME());
 //BA.debugLineNum = 53;BA.debugLine="If hqb = True Then";
if (_hqb==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 54;BA.debugLine="swithbtn.Text = onstring";
mostCurrent._swithbtn.setText(BA.ObjectToCharSequence(mostCurrent._onstring));
 //BA.debugLineNum = 55;BA.debugLine="papergetter";
_papergetter();
 }else {
 //BA.debugLineNum = 58;BA.debugLine="swithbtn.Text = offstring";
mostCurrent._swithbtn.setText(BA.ObjectToCharSequence(mostCurrent._offstring));
 //BA.debugLineNum = 59;BA.debugLine="papergetterlow";
_papergetterlow();
 };
 //BA.debugLineNum = 61;BA.debugLine="swithbtn.TextSize = mycode.textsize (8)";
mostCurrent._swithbtn.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (8)));
 //BA.debugLineNum = 64;BA.debugLine="swithbtn.TextColor=Colors.White";
mostCurrent._swithbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 67;BA.debugLine="sv.Initialize(mycode.ActivityHeight)";
mostCurrent._sv.Initialize(mostCurrent.activityBA,mostCurrent._mycode._activityheight /*int*/ );
 //BA.debugLineNum = 68;BA.debugLine="Activity.AddView(sv, 0, mycode.appbarheight, 100%";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._sv.getObject()),(int) (0),mostCurrent._mycode._appbarheight /*int*/ ,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),(int) (mostCurrent._mycode._activityheight /*int*/ -mostCurrent._mycode._appbarheight /*int*/ ));
 //BA.debugLineNum = 69;BA.debugLine="itemWidth=   (100%x - totalGapWidth) / NumColumns";
_itemwidth = (int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-_totalgapwidth)/(double)_numcolumns);
 //BA.debugLineNum = 70;BA.debugLine="progressShow";
_progressshow();
 //BA.debugLineNum = 71;BA.debugLine="End Sub";
return "";
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 486;BA.debugLine="Sub activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 488;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 489;BA.debugLine="If imgViewing = True Then";
if (_imgviewing==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 490;BA.debugLine="mycode.SETnavigationcolor";
mostCurrent._mycode._setnavigationcolor /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 491;BA.debugLine="imgViewing =False";
_imgviewing = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 492;BA.debugLine="Activity.RemoveViewAt(Activity.NumberOfViews-1)";
mostCurrent._activity.RemoveViewAt((int) (mostCurrent._activity.getNumberOfViews()-1));
 }else {
 //BA.debugLineNum = 494;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 };
 //BA.debugLineNum = 499;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 500;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 125;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 126;BA.debugLine="isCall =False";
_iscall = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 127;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 98;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 101;BA.debugLine="If banner.IsInitialized Then";
if (mostCurrent._banner.IsInitialized()) { 
 //BA.debugLineNum = 102;BA.debugLine="If adreceived = True Then";
if (_adreceived==anywheresoftware.b4a.keywords.Common.True) { 
 }else {
 //BA.debugLineNum = 104;BA.debugLine="If Main.adErrorCode <>  0 Then";
if ((mostCurrent._main._aderrorcode /*String*/ ).equals(BA.NumberToString(0)) == false) { 
 //BA.debugLineNum = 105;BA.debugLine="banner.LoadAd";
mostCurrent._banner.LoadAd();
 };
 };
 }else {
 //BA.debugLineNum = 109;BA.debugLine="banner.Initialize(\"banner\",Main.smallbannerunit";
mostCurrent._banner.Initialize(mostCurrent.activityBA,"banner",mostCurrent._main._smallbannerunit /*String*/ );
 //BA.debugLineNum = 110;BA.debugLine="If Main.adErrorCode <> 0 Then";
if ((mostCurrent._main._aderrorcode /*String*/ ).equals(BA.NumberToString(0)) == false) { 
 //BA.debugLineNum = 111;BA.debugLine="banner.LoadAd";
mostCurrent._banner.LoadAd();
 };
 };
 //BA.debugLineNum = 115;BA.debugLine="If Main.adErrorCode <>0 Then";
if ((mostCurrent._main._aderrorcode /*String*/ ).equals(BA.NumberToString(0)) == false) { 
 //BA.debugLineNum = 116;BA.debugLine="banner.RemoveView";
mostCurrent._banner.RemoveView();
 //BA.debugLineNum = 117;BA.debugLine="Activity.AddView(banner,0,100%y-50dip,100%x,50di";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._banner.getObject()),(int) (0),(int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50))),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 };
 //BA.debugLineNum = 120;BA.debugLine="isCall =True";
_iscall = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 121;BA.debugLine="itemWidth=   (100%x - totalGapWidth) / NumColumns";
_itemwidth = (int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-_totalgapwidth)/(double)_numcolumns);
 //BA.debugLineNum = 123;BA.debugLine="End Sub";
return "";
}
public static String  _asviewpager1_lazyloadingaddcontent(anywheresoftware.b4a.objects.B4XViewWrapper _parent,Object _value) throws Exception{
anywheresoftware.b4a.objects.ImageViewWrapper _img = null;
 //BA.debugLineNum = 450;BA.debugLine="Sub ASViewPager1_LazyLoadingAddContent(Parent As B";
 //BA.debugLineNum = 451;BA.debugLine="Try";
try { //BA.debugLineNum = 453;BA.debugLine="Dim img As ImageView";
_img = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 454;BA.debugLine="img.Initialize(\"imgv\")";
_img.Initialize(mostCurrent.activityBA,"imgv");
 //BA.debugLineNum = 455;BA.debugLine="img.Gravity = Gravity.FILL";
_img.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 457;BA.debugLine="Parent.AddView(img, 0, 0, Parent.Width, Parent.H";
_parent.AddView((android.view.View)(_img.getObject()),(int) (0),(int) (0),_parent.getWidth(),_parent.getHeight());
 //BA.debugLineNum = 458;BA.debugLine="Dim glide As Amir_Glide";
mostCurrent._glide = new ir.aghajari.retrofitglide.Amir_Glide();
 //BA.debugLineNum = 459;BA.debugLine="glide.Initializer.Default";
mostCurrent._glide.Initializer(processBA).Default();
 //BA.debugLineNum = 460;BA.debugLine="glide.Load(Value).Apply(glide.RO.CenterInside).I";
mostCurrent._glide.Load(_value).Apply(mostCurrent._glide.getRO().CenterInside()).Into((android.widget.ImageView)(_img.getObject()));
 //BA.debugLineNum = 462;BA.debugLine="img.Tag = Value";
_img.setTag(_value);
 } 
       catch (Exception e11) {
			processBA.setLastException(e11); //BA.debugLineNum = 464;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("230343182",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 466;BA.debugLine="End Sub";
return "";
}
public static String  _asviewpager1_pagechange(int _index) throws Exception{
 //BA.debugLineNum = 442;BA.debugLine="Sub ASViewPager1_PageChange(Index As Int)";
 //BA.debugLineNum = 444;BA.debugLine="End Sub";
return "";
}
public static String  _asviewpager1_pageclick(int _index,Object _value) throws Exception{
 //BA.debugLineNum = 446;BA.debugLine="Sub ASViewPager1_PageClick (Index As Int, Value As";
 //BA.debugLineNum = 448;BA.debugLine="End Sub";
return "";
}
public static String  _asviewpager2_pagechange(int _index) throws Exception{
 //BA.debugLineNum = 575;BA.debugLine="Private Sub ASViewPager2_PageChange(Index As Int)";
 //BA.debugLineNum = 576;BA.debugLine="Select Index";
switch (_index) {
case 0: {
 //BA.debugLineNum = 578;BA.debugLine="lbldaily.Color=mycode.naviColor";
mostCurrent._lbldaily.setColor(mostCurrent._mycode._navicolor /*int*/ );
 //BA.debugLineNum = 579;BA.debugLine="lblweekly.Color=mycode.bgColor";
mostCurrent._lblweekly.setColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 580;BA.debugLine="lblweekly.TextColor=Colors.Gray";
mostCurrent._lblweekly.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 //BA.debugLineNum = 581;BA.debugLine="lbldaily.TextColor=Colors.White";
mostCurrent._lbldaily.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 582;BA.debugLine="lblcalendar.Color=mycode.bgColor";
mostCurrent._lblcalendar.setColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 583;BA.debugLine="lblcalendar.TextColor=Colors.Gray";
mostCurrent._lblcalendar.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 break; }
case 1: {
 //BA.debugLineNum = 585;BA.debugLine="lblweekly.Color=mycode.naviColor";
mostCurrent._lblweekly.setColor(mostCurrent._mycode._navicolor /*int*/ );
 //BA.debugLineNum = 586;BA.debugLine="lbldaily.Color=mycode.bgColor";
mostCurrent._lbldaily.setColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 587;BA.debugLine="lblcalendar.Color=mycode.bgColor";
mostCurrent._lblcalendar.setColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 588;BA.debugLine="lblcalendar.TextColor=Colors.Gray";
mostCurrent._lblcalendar.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 //BA.debugLineNum = 589;BA.debugLine="lbldaily.TextColor=Colors.Gray";
mostCurrent._lbldaily.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 //BA.debugLineNum = 590;BA.debugLine="lblweekly.TextColor=Colors.White";
mostCurrent._lblweekly.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 break; }
case 2: {
 //BA.debugLineNum = 592;BA.debugLine="lblweekly.Color=mycode.bgColor";
mostCurrent._lblweekly.setColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 593;BA.debugLine="lbldaily.Color=mycode.bgColor";
mostCurrent._lbldaily.setColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 594;BA.debugLine="lblcalendar.Color=mycode.naviColor";
mostCurrent._lblcalendar.setColor(mostCurrent._mycode._navicolor /*int*/ );
 //BA.debugLineNum = 595;BA.debugLine="lblcalendar.TextColor=Colors.White";
mostCurrent._lblcalendar.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 596;BA.debugLine="lbldaily.TextColor=Colors.Gray";
mostCurrent._lbldaily.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 //BA.debugLineNum = 597;BA.debugLine="lblweekly.TextColor=Colors.Gray";
mostCurrent._lblweekly.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 break; }
}
;
 //BA.debugLineNum = 599;BA.debugLine="End Sub";
return "";
}
public static String  _asviewpager2_pageclick(int _index,Object _value) throws Exception{
 //BA.debugLineNum = 571;BA.debugLine="Private Sub ASViewPager2_PageClick (Index As Int,";
 //BA.debugLineNum = 573;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
 //BA.debugLineNum = 150;BA.debugLine="Sub backbtn_click";
 //BA.debugLineNum = 151;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 152;BA.debugLine="End Sub";
return "";
}
public static String  _banner_receivead() throws Exception{
 //BA.debugLineNum = 154;BA.debugLine="Sub banner_ReceiveAd";
 //BA.debugLineNum = 155;BA.debugLine="adreceived =True";
_adreceived = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 156;BA.debugLine="End Sub";
return "";
}
public static String  _buildgrid1(anywheresoftware.b4a.objects.collections.List _items,anywheresoftware.b4a.objects.PanelWrapper _parentpanel) throws Exception{
int _totalitems = 0;
int _numrows = 0;
int _panelheight = 0;
anywheresoftware.b4a.objects.PanelWrapper _pnl = null;
anywheresoftware.b4a.objects.drawable.BitmapDrawable _bd = null;
int _i = 0;
int _row = 0;
int _col = 0;
int _left = 0;
int _top = 0;
anywheresoftware.b4a.objects.ImageViewWrapper _itempanel = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 185;BA.debugLine="Sub BuildGrid1(items As List, ParentPanel As Panel";
 //BA.debugLineNum = 186;BA.debugLine="Dim totalItems As Int = items.Size";
_totalitems = _items.getSize();
 //BA.debugLineNum = 187;BA.debugLine="Dim totalGapWidth As Int = Gap * (NumColumns + 1)";
_totalgapwidth = (int) (_gap*(_numcolumns+1));
 //BA.debugLineNum = 188;BA.debugLine="Dim itemWidth As Int = (100%x - totalGapWidth) /";
_itemwidth = (int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-_totalgapwidth)/(double)_numcolumns);
 //BA.debugLineNum = 189;BA.debugLine="Dim ItemSize As Int = itemWidth * 1.5";
_itemsize = (int) (_itemwidth*1.5);
 //BA.debugLineNum = 191;BA.debugLine="Dim numRows As Int = Ceil(totalItems / NumColumns";
_numrows = (int) (anywheresoftware.b4a.keywords.Common.Ceil(_totalitems/(double)_numcolumns));
 //BA.debugLineNum = 192;BA.debugLine="Dim panelHeight As Int = numRows * (ItemSize + Ga";
_panelheight = (int) (_numrows*(_itemsize+_gap)+_gap);
 //BA.debugLineNum = 194;BA.debugLine="Dim pnl As Panel";
_pnl = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 195;BA.debugLine="pnl.Initialize(\"\")";
_pnl.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 196;BA.debugLine="ParentPanel.AddView(pnl, 0, 0, 100%x, panelHeight";
_parentpanel.AddView((android.view.View)(_pnl.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),_panelheight);
 //BA.debugLineNum = 198;BA.debugLine="Dim bd As BitmapDrawable";
_bd = new anywheresoftware.b4a.objects.drawable.BitmapDrawable();
 //BA.debugLineNum = 199;BA.debugLine="bd.Initialize(LoadBitmap(File.DirAssets,\"pholder.";
_bd.Initialize((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"pholder.webp").getObject()));
 //BA.debugLineNum = 201;BA.debugLine="Dim glide As Amir_Glide";
mostCurrent._glide = new ir.aghajari.retrofitglide.Amir_Glide();
 //BA.debugLineNum = 202;BA.debugLine="glide.Initializer.Default";
mostCurrent._glide.Initializer(processBA).Default();
 //BA.debugLineNum = 203;BA.debugLine="glide.RequestManager.ApplyDefaultRequestOptions(g";
mostCurrent._glide.RequestManager().ApplyDefaultRequestOptions(mostCurrent._glide.getRO().ErrorDrawable((android.graphics.drawable.Drawable)(_bd.getObject())).Placeholder((android.graphics.drawable.Drawable)(_bd.getObject())).CenterCrop());
 //BA.debugLineNum = 205;BA.debugLine="For i = 0 To items.Size - 1";
{
final int step15 = 1;
final int limit15 = (int) (_items.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit15 ;_i = _i + step15 ) {
 //BA.debugLineNum = 206;BA.debugLine="Dim row As Int = Floor(i / NumColumns)";
_row = (int) (anywheresoftware.b4a.keywords.Common.Floor(_i/(double)_numcolumns));
 //BA.debugLineNum = 207;BA.debugLine="Dim col As Int = i Mod NumColumns";
_col = (int) (_i%_numcolumns);
 //BA.debugLineNum = 208;BA.debugLine="Dim left As Int = Gap + col * (itemWidth + Gap)";
_left = (int) (_gap+_col*(_itemwidth+_gap));
 //BA.debugLineNum = 209;BA.debugLine="Dim top As Int = Gap + row * (ItemSize + Gap)";
_top = (int) (_gap+_row*(_itemsize+_gap));
 //BA.debugLineNum = 211;BA.debugLine="Dim itemPanel As ImageView";
_itempanel = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 212;BA.debugLine="itemPanel.Initialize(\"item\")";
_itempanel.Initialize(mostCurrent.activityBA,"item");
 //BA.debugLineNum = 213;BA.debugLine="pnl.AddView(itemPanel, left, top, itemWidth, Ite";
_pnl.AddView((android.view.View)(_itempanel.getObject()),_left,_top,_itemwidth,_itemsize);
 //BA.debugLineNum = 215;BA.debugLine="glide.Load(items.Get(i)).Apply(glide.RO.CenterCr";
mostCurrent._glide.Load(_items.Get(_i)).Apply(mostCurrent._glide.getRO().CenterCrop()).Into((android.widget.ImageView)(_itempanel.getObject()));
 //BA.debugLineNum = 217;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 218;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 219;BA.debugLine="m.Put(\"item\", items.Get(i))";
_m.Put((Object)("item"),_items.Get(_i));
 //BA.debugLineNum = 220;BA.debugLine="m.Put(\"index\", i)";
_m.Put((Object)("index"),(Object)(_i));
 //BA.debugLineNum = 221;BA.debugLine="itemPanel.Tag = m";
_itempanel.setTag((Object)(_m.getObject()));
 }
};
 //BA.debugLineNum = 223;BA.debugLine="ParentPanel.Height = panelHeight";
_parentpanel.setHeight(_panelheight);
 //BA.debugLineNum = 224;BA.debugLine="End Sub";
return "";
}
public static String  _changequality(boolean _b) throws Exception{
 //BA.debugLineNum = 135;BA.debugLine="Sub changequality(b As Boolean)";
 //BA.debugLineNum = 136;BA.debugLine="Select b";
switch (BA.switchObjectToInt(_b,anywheresoftware.b4a.keywords.Common.True,anywheresoftware.b4a.keywords.Common.False)) {
case 0: {
 //BA.debugLineNum = 138;BA.debugLine="swithbtn.Text = offstring";
mostCurrent._swithbtn.setText(BA.ObjectToCharSequence(mostCurrent._offstring));
 //BA.debugLineNum = 139;BA.debugLine="File.WriteString(File.DirInternal,\"qcheck\",\"fal";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"qcheck","false");
 //BA.debugLineNum = 140;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 141;BA.debugLine="StartActivity(Me)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,future_tips.getObject());
 break; }
case 1: {
 //BA.debugLineNum = 143;BA.debugLine="swithbtn.Text = onstring";
mostCurrent._swithbtn.setText(BA.ObjectToCharSequence(mostCurrent._onstring));
 //BA.debugLineNum = 144;BA.debugLine="File.WriteString(File.DirInternal,\"qcheck\",\"tr";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"qcheck","true");
 //BA.debugLineNum = 145;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 146;BA.debugLine="StartActivity(Me)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,future_tips.getObject());
 break; }
}
;
 //BA.debugLineNum = 148;BA.debugLine="End Sub";
return "";
}
public static String  _close_click() throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
 //BA.debugLineNum = 473;BA.debugLine="Sub close_click";
 //BA.debugLineNum = 474;BA.debugLine="mycode.SETnavigationcolor";
mostCurrent._mycode._setnavigationcolor /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 475;BA.debugLine="Dim b As Button = Sender";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 476;BA.debugLine="Dim p As Panel = b.Tag";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_b.getTag()));
 //BA.debugLineNum = 477;BA.debugLine="p.Visible=False";
_p.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 478;BA.debugLine="p.RemoveView";
_p.RemoveView();
 //BA.debugLineNum = 479;BA.debugLine="If showsavemsg = True Then";
if (_showsavemsg==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 480;BA.debugLine="showsavemsg=False";
_showsavemsg = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 481;BA.debugLine="Activity.RemoveViewAt(Activity.NumberOfViews-1)";
mostCurrent._activity.RemoveViewAt((int) (mostCurrent._activity.getNumberOfViews()-1));
 };
 //BA.debugLineNum = 483;BA.debugLine="imgViewing=False";
_imgviewing = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 484;BA.debugLine="End Sub";
return "";
}
public static String  _getpapersuccess() throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _json = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
anywheresoftware.b4a.objects.collections.List _ls1 = null;
anywheresoftware.b4a.objects.collections.List _ls2 = null;
 //BA.debugLineNum = 337;BA.debugLine="Sub getpapersuccess";
 //BA.debugLineNum = 338;BA.debugLine="If mycode.paperdata <> \"\" Then";
if ((mostCurrent._mycode._paperdata /*String*/ ).equals("") == false) { 
 //BA.debugLineNum = 339;BA.debugLine="progressHide";
_progresshide();
 //BA.debugLineNum = 340;BA.debugLine="Dim json As JSONParser";
_json = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 341;BA.debugLine="json.Initialize(mycode.paperdata)";
_json.Initialize(mostCurrent._mycode._paperdata /*String*/ );
 //BA.debugLineNum = 342;BA.debugLine="Dim m As Map = json.NextObject";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = _json.NextObject();
 //BA.debugLineNum = 343;BA.debugLine="Dim ls As List = m.Get(\"daily\")";
_ls = new anywheresoftware.b4a.objects.collections.List();
_ls = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_m.Get((Object)("daily"))));
 //BA.debugLineNum = 344;BA.debugLine="Dim ls1 As List = m.Get(\"weekly\")";
_ls1 = new anywheresoftware.b4a.objects.collections.List();
_ls1 = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_m.Get((Object)("weekly"))));
 //BA.debugLineNum = 345;BA.debugLine="Dim ls2 As List = m.Get(\"calendar\")";
_ls2 = new anywheresoftware.b4a.objects.collections.List();
_ls2 = (anywheresoftware.b4a.objects.collections.List) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.List(), (java.util.List)(_m.Get((Object)("calendar"))));
 //BA.debugLineNum = 346;BA.debugLine="mycode.futureimglist = ls";
mostCurrent._mycode._futureimglist /*anywheresoftware.b4a.objects.collections.List*/  = _ls;
 //BA.debugLineNum = 347;BA.debugLine="mycode.weeklyimglist = ls1";
mostCurrent._mycode._weeklyimglist /*anywheresoftware.b4a.objects.collections.List*/  = _ls1;
 //BA.debugLineNum = 348;BA.debugLine="mycode.calendarimglist = ls2";
mostCurrent._mycode._calendarimglist /*anywheresoftware.b4a.objects.collections.List*/  = _ls2;
 //BA.debugLineNum = 350;BA.debugLine="LoadGridPages(ls,ls1,ls2)";
_loadgridpages(_ls,_ls1,_ls2);
 }else {
 //BA.debugLineNum = 352;BA.debugLine="papergetter";
_papergetter();
 };
 //BA.debugLineNum = 354;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 24;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 25;BA.debugLine="Dim swithbtn As Button";
mostCurrent._swithbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Dim ASViewPager1 As ASViewPager";
mostCurrent._asviewpager1 = new com.burma.royal2d.asviewpager();
 //BA.debugLineNum = 28;BA.debugLine="Dim lbldaily,lblweekly,lblcalendar As Label";
mostCurrent._lbldaily = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblweekly = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._lblcalendar = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Dim menubtn As Button";
mostCurrent._menubtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Dim glide As Amir_Glide";
mostCurrent._glide = new ir.aghajari.retrofitglide.Amir_Glide();
 //BA.debugLineNum = 32;BA.debugLine="Dim progresspn As Panel";
mostCurrent._progresspn = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 33;BA.debugLine="Private sv As ScrollView";
mostCurrent._sv = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Dim banner As AdView";
mostCurrent._banner = new anywheresoftware.b4a.admobwrapper.AdViewWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private ASViewPager2 As ASViewPager";
mostCurrent._asviewpager2 = new com.burma.royal2d.asviewpager();
 //BA.debugLineNum = 36;BA.debugLine="Dim offstring As String = Chr(0xF204)";
mostCurrent._offstring = BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf204)));
 //BA.debugLineNum = 37;BA.debugLine="Dim onstring As String = Chr(0xF205)";
mostCurrent._onstring = BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf205)));
 //BA.debugLineNum = 38;BA.debugLine="Dim hqb As Boolean";
_hqb = false;
 //BA.debugLineNum = 39;BA.debugLine="End Sub";
return "";
}
public static void  _imageview(String _imgl,int _i) throws Exception{
ResumableSub_imageView rsub = new ResumableSub_imageView(null,_imgl,_i);
rsub.resume(processBA, null);
}
public static class ResumableSub_imageView extends BA.ResumableSub {
public ResumableSub_imageView(com.burma.royal2d.future_tips parent,String _imgl,int _i) {
this.parent = parent;
this._imgl = _imgl;
this._i = _i;
}
com.burma.royal2d.future_tips parent;
String _imgl;
int _i;
anywheresoftware.b4a.objects.ButtonWrapper _closebtn = null;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.PanelWrapper _panel1 = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
int _a = 0;
anywheresoftware.b4a.objects.B4XViewWrapper _pimg = null;
anywheresoftware.b4a.objects.ImageViewWrapper _img = null;
int step33;
int limit33;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 370;BA.debugLine="mycode.SETnavigationcolor1";
parent.mostCurrent._mycode._setnavigationcolor1 /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 371;BA.debugLine="imgViewing= True";
parent._imgviewing = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 372;BA.debugLine="Dim closebtn As Button";
_closebtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 373;BA.debugLine="closebtn.Initialize(\"close\")";
_closebtn.Initialize(mostCurrent.activityBA,"close");
 //BA.debugLineNum = 374;BA.debugLine="closebtn.Background= mycode.btnbg(False)";
_closebtn.setBackground((android.graphics.drawable.Drawable)(parent.mostCurrent._mycode._btnbg /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False).getObject()));
 //BA.debugLineNum = 375;BA.debugLine="closebtn. Text = mycode.buttoncsb(False,Chr(0xF00";
_closebtn.setText(BA.ObjectToCharSequence(parent.mostCurrent._mycode._buttoncsb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf00d))),anywheresoftware.b4a.keywords.Common.True).getObject()));
 //BA.debugLineNum = 376;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 377;BA.debugLine="p.Initialize(\"ppp\")";
_p.Initialize(mostCurrent.activityBA,"ppp");
 //BA.debugLineNum = 378;BA.debugLine="p.Color=mycode.bgColor";
_p.setColor(parent.mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 380;BA.debugLine="closebtn.Tag= p";
_closebtn.setTag((Object)(_p.getObject()));
 //BA.debugLineNum = 382;BA.debugLine="If Main.adErrorCode <> 0 Then";
if (true) break;

case 1:
//if
this.state = 6;
if ((parent.mostCurrent._main._aderrorcode /*String*/ ).equals(BA.NumberToString(0)) == false) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 //BA.debugLineNum = 383;BA.debugLine="Activity.AddView(p,0,0,100%x,mycode.ActivityHeig";
parent.mostCurrent._activity.AddView((android.view.View)(_p.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),(int) (parent.mostCurrent._mycode._activityheight /*int*/ -anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (55))));
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 385;BA.debugLine="Activity.AddView(p,0,0,100%x,mycode.ActivityHeig";
parent.mostCurrent._activity.AddView((android.view.View)(_p.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),parent.mostCurrent._mycode._activityheight /*int*/ );
 if (true) break;

case 6:
//C
this.state = 7;
;
 //BA.debugLineNum = 388;BA.debugLine="Dim xui As XUI";
parent._xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 389;BA.debugLine="Private Panel1 As Panel";
_panel1 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 390;BA.debugLine="Panel1.Initialize(\"\")";
_panel1.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 391;BA.debugLine="Panel1.Color=Colors.Blue";
_panel1.setColor(anywheresoftware.b4a.keywords.Common.Colors.Blue);
 //BA.debugLineNum = 392;BA.debugLine="p.AddView(Panel1,0,50dip,100%x,p.Height-50dip)";
_p.AddView((android.view.View)(_panel1.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),(int) (_p.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50))));
 //BA.debugLineNum = 393;BA.debugLine="Panel1.LoadLayout(\"viewpager\")";
_panel1.LoadLayout("viewpager",mostCurrent.activityBA);
 //BA.debugLineNum = 395;BA.debugLine="ASViewPager1.LazyLoading = True";
parent.mostCurrent._asviewpager1._setlazyloading /*boolean*/ (anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 396;BA.debugLine="ASViewPager1.IgnoreLazyLoading=False";
parent.mostCurrent._asviewpager1._setignorelazyloading(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 397;BA.debugLine="ASViewPager1.LazyLoadingExtraSize=4";
parent.mostCurrent._asviewpager1._setlazyloadingextrasize /*int*/ ((int) (4));
 //BA.debugLineNum = 398;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 399;BA.debugLine="ls = setList";
_ls = _setlist();
 //BA.debugLineNum = 400;BA.debugLine="p.AddView(closebtn,100%x-mycode.appbarheight,0,my";
_p.AddView((android.view.View)(_closebtn.getObject()),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-parent.mostCurrent._mycode._appbarheight /*int*/ ),(int) (0),parent.mostCurrent._mycode._appbarheight /*int*/ ,parent.mostCurrent._mycode._appbarheight /*int*/ );
 //BA.debugLineNum = 401;BA.debugLine="menubtn.Initialize(\"menu\")";
parent.mostCurrent._menubtn.Initialize(mostCurrent.activityBA,"menu");
 //BA.debugLineNum = 403;BA.debugLine="menubtn.Background = mycode.btnbg(False)";
parent.mostCurrent._menubtn.setBackground((android.graphics.drawable.Drawable)(parent.mostCurrent._mycode._btnbg /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False).getObject()));
 //BA.debugLineNum = 404;BA.debugLine="menubtn.Text=mycode.buttoncsb(False,Chr(0xF0C7),T";
parent.mostCurrent._menubtn.setText(BA.ObjectToCharSequence(parent.mostCurrent._mycode._buttoncsb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf0c7))),anywheresoftware.b4a.keywords.Common.True).getObject()));
 //BA.debugLineNum = 405;BA.debugLine="menubtn.TextColor=Colors.White";
parent.mostCurrent._menubtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 406;BA.debugLine="p.AddView(menubtn,closebtn.Left-mycode.appbarheig";
_p.AddView((android.view.View)(parent.mostCurrent._menubtn.getObject()),(int) (_closebtn.getLeft()-parent.mostCurrent._mycode._appbarheight /*int*/ ),(int) (0),parent.mostCurrent._mycode._appbarheight /*int*/ ,parent.mostCurrent._mycode._appbarheight /*int*/ );
 //BA.debugLineNum = 407;BA.debugLine="For a = 0 To ls.Size-1";
if (true) break;

case 7:
//for
this.state = 10;
step33 = 1;
limit33 = (int) (_ls.getSize()-1);
_a = (int) (0) ;
this.state = 11;
if (true) break;

case 11:
//C
this.state = 10;
if ((step33 > 0 && _a <= limit33) || (step33 < 0 && _a >= limit33)) this.state = 9;
if (true) break;

case 12:
//C
this.state = 11;
_a = ((int)(0 + _a + step33)) ;
if (true) break;

case 9:
//C
this.state = 12;
 //BA.debugLineNum = 409;BA.debugLine="Dim pimg As B4XView = xui.CreatePanel(\"\")";
_pimg = new anywheresoftware.b4a.objects.B4XViewWrapper();
_pimg = parent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 410;BA.debugLine="pimg.SetLayoutAnimated(0,0,0,ASViewPager1.Base.W";
_pimg.SetLayoutAnimated((int) (0),(int) (0),(int) (0),parent.mostCurrent._asviewpager1._getbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ().getWidth(),parent.mostCurrent._asviewpager1._getbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ().getHeight());
 //BA.debugLineNum = 411;BA.debugLine="pimg.Color = mycode.bgColor";
_pimg.setColor(parent.mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 412;BA.debugLine="Dim img As ImageView";
_img = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 413;BA.debugLine="img.Initialize(\"imgv\")";
_img.Initialize(mostCurrent.activityBA,"imgv");
 //BA.debugLineNum = 414;BA.debugLine="pimg.AddView(img,0,0,pimg.Width,pimg.Height)";
_pimg.AddView((android.view.View)(_img.getObject()),(int) (0),(int) (0),_pimg.getWidth(),_pimg.getHeight());
 //BA.debugLineNum = 415;BA.debugLine="Dim glide As Amir_Glide";
parent.mostCurrent._glide = new ir.aghajari.retrofitglide.Amir_Glide();
 //BA.debugLineNum = 416;BA.debugLine="glide.Initializer.Default";
parent.mostCurrent._glide.Initializer(processBA).Default();
 //BA.debugLineNum = 417;BA.debugLine="glide.Load(ls.Get(a)).Apply(glide.RO.CenterInsid";
parent.mostCurrent._glide.Load(_ls.Get(_a)).Apply(parent.mostCurrent._glide.getRO().CenterInside()).Into((android.widget.ImageView)(_img.getObject()));
 //BA.debugLineNum = 418;BA.debugLine="img.Tag = ls.Get(a)";
_img.setTag(_ls.Get(_a));
 //BA.debugLineNum = 419;BA.debugLine="ASViewPager1.AddPage(pimg,ls.Get(a))";
parent.mostCurrent._asviewpager1._addpage /*String*/ (_pimg,_ls.Get(_a));
 if (true) break;
if (true) break;

case 10:
//C
this.state = -1;
;
 //BA.debugLineNum = 421;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (0));
this.state = 13;
return;
case 13:
//C
this.state = -1;
;
 //BA.debugLineNum = 422;BA.debugLine="ASViewPager1.CurrentIndex=i";
parent.mostCurrent._asviewpager1._setcurrentindex /*int*/ (_i);
 //BA.debugLineNum = 423;BA.debugLine="ASViewPager1.Commit";
parent.mostCurrent._asviewpager1._commit /*String*/ ();
 //BA.debugLineNum = 425;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _imgv_longclick() throws Exception{
 //BA.debugLineNum = 438;BA.debugLine="Sub imgv_LongClick";
 //BA.debugLineNum = 441;BA.debugLine="End Sub";
return "";
}
public static String  _inter_adclosed() throws Exception{
 //BA.debugLineNum = 162;BA.debugLine="Sub inter_AdClosed";
 //BA.debugLineNum = 163;BA.debugLine="imgViewing=True";
_imgviewing = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 164;BA.debugLine="End Sub";
return "";
}
public static String  _inter_adopened() throws Exception{
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 166;BA.debugLine="Sub inter_AdOpened";
 //BA.debugLineNum = 167;BA.debugLine="Dim m As Map = store.tempmap";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = mostCurrent._store._tempmap /*anywheresoftware.b4a.objects.collections.Map*/ ;
 //BA.debugLineNum = 168;BA.debugLine="imageView(m.Get(\"item\"),m.Get(\"index\"))";
_imageview(BA.ObjectToString(_m.Get((Object)("item"))),(int)(BA.ObjectToNumber(_m.Get((Object)("index")))));
 //BA.debugLineNum = 169;BA.debugLine="End Sub";
return "";
}
public static String  _inter_receivead() throws Exception{
 //BA.debugLineNum = 158;BA.debugLine="Sub inter_ReceiveAd";
 //BA.debugLineNum = 159;BA.debugLine="Log(\"inter receive\")";
anywheresoftware.b4a.keywords.Common.LogImpl("229097985","inter receive",0);
 //BA.debugLineNum = 160;BA.debugLine="End Sub";
return "";
}
public static String  _item_click() throws Exception{
anywheresoftware.b4a.objects.ImageViewWrapper _p = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 356;BA.debugLine="Sub item_click";
 //BA.debugLineNum = 357;BA.debugLine="Dim p As ImageView =Sender";
_p = new anywheresoftware.b4a.objects.ImageViewWrapper();
_p = (anywheresoftware.b4a.objects.ImageViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ImageViewWrapper(), (android.widget.ImageView)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 358;BA.debugLine="Dim m As Map = p.Tag";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_p.getTag()));
 //BA.debugLineNum = 363;BA.debugLine="store.tempmap = m";
mostCurrent._store._tempmap /*anywheresoftware.b4a.objects.collections.Map*/  = _m;
 //BA.debugLineNum = 365;BA.debugLine="imageView(m.Get(\"item\"),m.Get(\"index\"))";
_imageview(BA.ObjectToString(_m.Get((Object)("item"))),(int)(BA.ObjectToNumber(_m.Get((Object)("index")))));
 //BA.debugLineNum = 367;BA.debugLine="End Sub";
return "";
}
public static String  _lblcalendar_click() throws Exception{
 //BA.debugLineNum = 314;BA.debugLine="Sub lblcalendar_click";
 //BA.debugLineNum = 315;BA.debugLine="ASViewPager2.CurrentIndex =2";
mostCurrent._asviewpager2._setcurrentindex /*int*/ ((int) (2));
 //BA.debugLineNum = 316;BA.debugLine="End Sub";
return "";
}
public static String  _lbldaily_click() throws Exception{
 //BA.debugLineNum = 317;BA.debugLine="Sub lbldaily_click";
 //BA.debugLineNum = 318;BA.debugLine="ASViewPager2.CurrentIndex= 0";
mostCurrent._asviewpager2._setcurrentindex /*int*/ ((int) (0));
 //BA.debugLineNum = 319;BA.debugLine="End Sub";
return "";
}
public static String  _lblweekly_click() throws Exception{
 //BA.debugLineNum = 321;BA.debugLine="Sub lblweekly_click";
 //BA.debugLineNum = 322;BA.debugLine="ASViewPager2.CurrentIndex = 1";
mostCurrent._asviewpager2._setcurrentindex /*int*/ ((int) (1));
 //BA.debugLineNum = 323;BA.debugLine="End Sub";
return "";
}
public static String  _loadgridpages(anywheresoftware.b4a.objects.collections.List _dailyitems,anywheresoftware.b4a.objects.collections.List _weeklyitems,anywheresoftware.b4a.objects.collections.List _calendaritems) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _pp = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
int _w = 0;
int _vpheight = 0;
anywheresoftware.b4a.objects.B4XViewWrapper _dailypanel = null;
anywheresoftware.b4a.objects.ScrollViewWrapper _svdaily = null;
anywheresoftware.b4a.objects.B4XViewWrapper _weeklypanel = null;
anywheresoftware.b4a.objects.ScrollViewWrapper _svweekly = null;
anywheresoftware.b4a.objects.B4XViewWrapper _calendarpanel = null;
anywheresoftware.b4a.objects.ScrollViewWrapper _svcalendar = null;
 //BA.debugLineNum = 225;BA.debugLine="Sub LoadGridPages(dailyItems As List, weeklyItems";
 //BA.debugLineNum = 228;BA.debugLine="Dim pp As Panel";
_pp = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 229;BA.debugLine="pp.Initialize(\"\")";
_pp.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 230;BA.debugLine="lbldaily.Initialize(\"lbldaily\")";
mostCurrent._lbldaily.Initialize(mostCurrent.activityBA,"lbldaily");
 //BA.debugLineNum = 231;BA.debugLine="lblweekly.Initialize(\"lblweekly\")";
mostCurrent._lblweekly.Initialize(mostCurrent.activityBA,"lblweekly");
 //BA.debugLineNum = 232;BA.debugLine="lblweekly.Text = \"တစ်ပတ်စာ\"";
mostCurrent._lblweekly.setText(BA.ObjectToCharSequence("တစ်ပတ်စာ"));
 //BA.debugLineNum = 233;BA.debugLine="lblweekly.Typeface=mycode.mmfont";
mostCurrent._lblweekly.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 234;BA.debugLine="lblweekly.TextColor=Colors.White";
mostCurrent._lblweekly.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 235;BA.debugLine="lblweekly.Color=mycode.bgColor";
mostCurrent._lblweekly.setColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 236;BA.debugLine="lbldaily.Text = \"တစ်ရက်စာ\"";
mostCurrent._lbldaily.setText(BA.ObjectToCharSequence("တစ်ရက်စာ"));
 //BA.debugLineNum = 237;BA.debugLine="lbldaily.Typeface=mycode.mmfont";
mostCurrent._lbldaily.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 238;BA.debugLine="lbldaily.TextColor=Colors.White";
mostCurrent._lbldaily.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 239;BA.debugLine="lbldaily.Color=mycode.naviColor";
mostCurrent._lbldaily.setColor(mostCurrent._mycode._navicolor /*int*/ );
 //BA.debugLineNum = 241;BA.debugLine="lblcalendar.Initialize(\"lblcalendar\")";
mostCurrent._lblcalendar.Initialize(mostCurrent.activityBA,"lblcalendar");
 //BA.debugLineNum = 242;BA.debugLine="lblcalendar.Text=\"ထိုင်းကဒ်\"";
mostCurrent._lblcalendar.setText(BA.ObjectToCharSequence("ထိုင်းကဒ်"));
 //BA.debugLineNum = 243;BA.debugLine="lblcalendar.Typeface=mycode.mmfont";
mostCurrent._lblcalendar.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 244;BA.debugLine="lblcalendar.TextColor=Colors.White";
mostCurrent._lblcalendar.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 245;BA.debugLine="lblcalendar.Color=mycode.bgColor";
mostCurrent._lblcalendar.setColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 247;BA.debugLine="Log(\"here >>>>>>>>>\")";
anywheresoftware.b4a.keywords.Common.LogImpl("229491222","here >>>>>>>>>",0);
 //BA.debugLineNum = 248;BA.debugLine="lblcalendar.Gravity=Gravity.CENTER";
mostCurrent._lblcalendar.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 249;BA.debugLine="lbldaily.Gravity=Gravity.CENTER";
mostCurrent._lbldaily.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 250;BA.debugLine="lblweekly.Gravity=Gravity.CENTER";
mostCurrent._lblweekly.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 251;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 252;BA.debugLine="Dim w As Int = (100%x)/3";
_w = (int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA))/(double)3);
 //BA.debugLineNum = 253;BA.debugLine="Activity.AddView(lbldaily,0,mycode.appbarheight+1";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._lbldaily.getObject()),(int) (0),(int) (mostCurrent._mycode._appbarheight /*int*/ +anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))),_w,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 254;BA.debugLine="Activity.AddView(lblweekly,lbldaily.Width+lbldail";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._lblweekly.getObject()),(int) (mostCurrent._lbldaily.getWidth()+mostCurrent._lbldaily.getLeft()),(int) (mostCurrent._mycode._appbarheight /*int*/ +anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))),_w,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 255;BA.debugLine="Activity.AddView(lblcalendar,lblweekly.Width+lblw";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._lblcalendar.getObject()),(int) (mostCurrent._lblweekly.getWidth()+mostCurrent._lblweekly.getLeft()),(int) (mostCurrent._mycode._appbarheight /*int*/ +anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))),_w,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 257;BA.debugLine="lbldaily.Height = su.MeasureMultilineTextHeight(l";
mostCurrent._lbldaily.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(mostCurrent._lbldaily.getObject()),BA.ObjectToCharSequence(mostCurrent._lbldaily.getText())));
 //BA.debugLineNum = 258;BA.debugLine="lblweekly.Height = su.MeasureMultilineTextHeight(";
mostCurrent._lblweekly.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(mostCurrent._lblweekly.getObject()),BA.ObjectToCharSequence(mostCurrent._lblweekly.getText())));
 //BA.debugLineNum = 259;BA.debugLine="lblcalendar.Height = su.MeasureMultilineTextHeigh";
mostCurrent._lblcalendar.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(mostCurrent._lblcalendar.getObject()),BA.ObjectToCharSequence(mostCurrent._lblcalendar.getText())));
 //BA.debugLineNum = 261;BA.debugLine="If Main.adErrorCode  = 0 Then";
if ((mostCurrent._main._aderrorcode /*String*/ ).equals(BA.NumberToString(0))) { 
 //BA.debugLineNum = 262;BA.debugLine="Log(\"here\")";
anywheresoftware.b4a.keywords.Common.LogImpl("229491237","here",0);
 //BA.debugLineNum = 263;BA.debugLine="Dim vpHeight As Int = 100%y - (lbldaily.Height+l";
_vpheight = (int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)-(mostCurrent._lbldaily.getHeight()+mostCurrent._lbldaily.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))));
 }else {
 //BA.debugLineNum = 265;BA.debugLine="Dim vpHeight As Int = 100%y - (lbldaily.Height+l";
_vpheight = (int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)-(mostCurrent._lbldaily.getHeight()+mostCurrent._lbldaily.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (55))));
 };
 //BA.debugLineNum = 267;BA.debugLine="Activity.AddView(pp, 0, lbldaily.Height+lbldaily.";
mostCurrent._activity.AddView((android.view.View)(_pp.getObject()),(int) (0),(int) (mostCurrent._lbldaily.getHeight()+mostCurrent._lbldaily.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1))),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),_vpheight);
 //BA.debugLineNum = 268;BA.debugLine="pp.LoadLayout(\"gridpages\")";
_pp.LoadLayout("gridpages",mostCurrent.activityBA);
 //BA.debugLineNum = 271;BA.debugLine="ASViewPager2.Clear";
mostCurrent._asviewpager2._clear /*String*/ ();
 //BA.debugLineNum = 272;BA.debugLine="ASViewPager2.LazyLoading = False";
mostCurrent._asviewpager2._setlazyloading /*boolean*/ (anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 273;BA.debugLine="Dim xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 276;BA.debugLine="Dim dailyPanel As B4XView = xui.CreatePanel(\"\")";
_dailypanel = new anywheresoftware.b4a.objects.B4XViewWrapper();
_dailypanel = _xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 277;BA.debugLine="dailyPanel.SetLayoutAnimated(0, 0, 0, 100%x, vpHe";
_dailypanel.SetLayoutAnimated((int) (0),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),_vpheight);
 //BA.debugLineNum = 278;BA.debugLine="dailyPanel.Color=mycode.bgColor";
_dailypanel.setColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 280;BA.debugLine="Dim svDaily As ScrollView";
_svdaily = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 281;BA.debugLine="svDaily.Initialize(vpHeight)";
_svdaily.Initialize(mostCurrent.activityBA,_vpheight);
 //BA.debugLineNum = 282;BA.debugLine="dailyPanel.AddView(svDaily, 0, 0, 100%x, vpHeight";
_dailypanel.AddView((android.view.View)(_svdaily.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),_vpheight);
 //BA.debugLineNum = 284;BA.debugLine="BuildGrid1(dailyItems, svDaily.Panel)";
_buildgrid1(_dailyitems,_svdaily.getPanel());
 //BA.debugLineNum = 285;BA.debugLine="ASViewPager2.AddPage(dailyPanel, \"daily\")";
mostCurrent._asviewpager2._addpage /*String*/ (_dailypanel,(Object)("daily"));
 //BA.debugLineNum = 288;BA.debugLine="Dim weeklyPanel As B4XView = xui.CreatePanel(\"\")";
_weeklypanel = new anywheresoftware.b4a.objects.B4XViewWrapper();
_weeklypanel = _xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 289;BA.debugLine="weeklyPanel.SetLayoutAnimated(0, 0, 0, 100%x, vpH";
_weeklypanel.SetLayoutAnimated((int) (0),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),_vpheight);
 //BA.debugLineNum = 290;BA.debugLine="weeklyPanel.Color=mycode.bgColor";
_weeklypanel.setColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 292;BA.debugLine="Dim svWeekly As ScrollView";
_svweekly = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 293;BA.debugLine="svWeekly.Initialize(vpHeight)";
_svweekly.Initialize(mostCurrent.activityBA,_vpheight);
 //BA.debugLineNum = 294;BA.debugLine="weeklyPanel.AddView(svWeekly, 0, 0, 100%x, vpHeig";
_weeklypanel.AddView((android.view.View)(_svweekly.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),_vpheight);
 //BA.debugLineNum = 296;BA.debugLine="BuildGrid1(weeklyItems, svWeekly.Panel)";
_buildgrid1(_weeklyitems,_svweekly.getPanel());
 //BA.debugLineNum = 297;BA.debugLine="ASViewPager2.AddPage(weeklyPanel, \"weekly\")";
mostCurrent._asviewpager2._addpage /*String*/ (_weeklypanel,(Object)("weekly"));
 //BA.debugLineNum = 300;BA.debugLine="Dim calendarPanel As B4XView = xui.CreatePanel(\"\"";
_calendarpanel = new anywheresoftware.b4a.objects.B4XViewWrapper();
_calendarpanel = _xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 301;BA.debugLine="calendarPanel.SetLayoutAnimated(0, 0, 0, 100%x, v";
_calendarpanel.SetLayoutAnimated((int) (0),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),_vpheight);
 //BA.debugLineNum = 302;BA.debugLine="calendarPanel.Color=mycode.bgColor";
_calendarpanel.setColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 304;BA.debugLine="Dim svCalendar As ScrollView";
_svcalendar = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 305;BA.debugLine="svCalendar.Initialize(vpHeight)";
_svcalendar.Initialize(mostCurrent.activityBA,_vpheight);
 //BA.debugLineNum = 306;BA.debugLine="calendarPanel.AddView(svCalendar, 0, 0, 100%x, vp";
_calendarpanel.AddView((android.view.View)(_svcalendar.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),_vpheight);
 //BA.debugLineNum = 308;BA.debugLine="BuildGrid1(calendarItems, svCalendar.Panel)";
_buildgrid1(_calendaritems,_svcalendar.getPanel());
 //BA.debugLineNum = 309;BA.debugLine="ASViewPager2.AddPage(calendarPanel, \"calendar\")";
mostCurrent._asviewpager2._addpage /*String*/ (_calendarpanel,(Object)("calendar"));
 //BA.debugLineNum = 311;BA.debugLine="ASViewPager2.Commit";
mostCurrent._asviewpager2._commit /*String*/ ();
 //BA.debugLineNum = 312;BA.debugLine="End Sub";
return "";
}
public static String  _menu_click() throws Exception{
 //BA.debugLineNum = 502;BA.debugLine="Sub menu_click";
 //BA.debugLineNum = 515;BA.debugLine="saveimage(setList.Get(ASViewPager1.CurrentIndex))";
_saveimage(BA.ObjectToString(_setlist().Get(mostCurrent._asviewpager1._getcurrentindex /*int*/ ())));
 //BA.debugLineNum = 519;BA.debugLine="End Sub";
return "";
}
public static String  _papergetter() throws Exception{
com.burma.royal2d.httpjob _j = null;
 //BA.debugLineNum = 325;BA.debugLine="Sub papergetter";
 //BA.debugLineNum = 326;BA.debugLine="Dim j As HttpJob";
_j = new com.burma.royal2d.httpjob();
 //BA.debugLineNum = 327;BA.debugLine="j.Initialize(papergetters,Starter)";
_j._initialize /*String*/ (processBA,_papergetters,(Object)(mostCurrent._starter.getObject()));
 //BA.debugLineNum = 328;BA.debugLine="j.Download(Main.newsite&\"/futurepaper/getallpaper";
_j._download /*String*/ (mostCurrent._main._newsite /*String*/ +"/futurepaper/getallpaper/high");
 //BA.debugLineNum = 329;BA.debugLine="End Sub";
return "";
}
public static String  _papergetterlow() throws Exception{
com.burma.royal2d.httpjob _j = null;
 //BA.debugLineNum = 331;BA.debugLine="Sub papergetterlow";
 //BA.debugLineNum = 332;BA.debugLine="Dim j As HttpJob";
_j = new com.burma.royal2d.httpjob();
 //BA.debugLineNum = 333;BA.debugLine="j.Initialize(papergetters,Starter)";
_j._initialize /*String*/ (processBA,_papergetters,(Object)(mostCurrent._starter.getObject()));
 //BA.debugLineNum = 334;BA.debugLine="j.Download(Main.newsite&\"/futurepaper/getallpaper";
_j._download /*String*/ (mostCurrent._main._newsite /*String*/ +"/futurepaper/getallpaper/low");
 //BA.debugLineNum = 335;BA.debugLine="End Sub";
return "";
}
public static String  _pbase_click() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
 //BA.debugLineNum = 545;BA.debugLine="Sub pbase_Click";
 //BA.debugLineNum = 546;BA.debugLine="Dim p As Panel =Sender";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 547;BA.debugLine="If showsavemsg =True Then";
if (_showsavemsg==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 548;BA.debugLine="showsavemsg=False";
_showsavemsg = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 549;BA.debugLine="p.Visible=False";
_p.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 550;BA.debugLine="p.RemoveView";
_p.RemoveView();
 };
 //BA.debugLineNum = 552;BA.debugLine="End Sub";
return "";
}
public static String  _ppp_click() throws Exception{
 //BA.debugLineNum = 469;BA.debugLine="Sub ppp_Click";
 //BA.debugLineNum = 471;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 8;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Private NumColumns As Int = 3";
_numcolumns = (int) (3);
 //BA.debugLineNum = 10;BA.debugLine="Private ItemSize As Int = 100dip";
_itemsize = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100));
 //BA.debugLineNum = 11;BA.debugLine="Dim Gap As Int = 2dip";
_gap = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2));
 //BA.debugLineNum = 12;BA.debugLine="Dim NumColumns As Int = 3";
_numcolumns = (int) (3);
 //BA.debugLineNum = 13;BA.debugLine="Dim papergetters As String=\"papergetter\"";
_papergetters = "papergetter";
 //BA.debugLineNum = 14;BA.debugLine="Dim totalGapWidth As Int = Gap * (NumColumns + 1)";
_totalgapwidth = (int) (_gap*(_numcolumns+1));
 //BA.debugLineNum = 15;BA.debugLine="Dim itemWidth As Int";
_itemwidth = 0;
 //BA.debugLineNum = 16;BA.debugLine="Dim progresstimer As Timer";
_progresstimer = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 17;BA.debugLine="Dim isCall As Boolean";
_iscall = false;
 //BA.debugLineNum = 18;BA.debugLine="Dim imgViewing As Boolean";
_imgviewing = false;
 //BA.debugLineNum = 19;BA.debugLine="Dim showsavemsg As Boolean";
_showsavemsg = false;
 //BA.debugLineNum = 20;BA.debugLine="Dim xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 21;BA.debugLine="Dim adreceived As Boolean";
_adreceived = false;
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return "";
}
public static String  _progresshide() throws Exception{
 //BA.debugLineNum = 179;BA.debugLine="Sub progressHide";
 //BA.debugLineNum = 180;BA.debugLine="progresspn.Visible=False";
mostCurrent._progresspn.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 181;BA.debugLine="progresstimer.Enabled=False";
_progresstimer.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 182;BA.debugLine="End Sub";
return "";
}
public static String  _progressshow() throws Exception{
 //BA.debugLineNum = 74;BA.debugLine="Sub progressShow";
 //BA.debugLineNum = 75;BA.debugLine="progresspn.Initialize(\"\")";
mostCurrent._progresspn.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 76;BA.debugLine="Activity.AddView(progresspn,0,mycode.appbarheight";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._progresspn.getObject()),(int) (0),mostCurrent._mycode._appbarheight /*int*/ ,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)));
 //BA.debugLineNum = 77;BA.debugLine="progresspn.Color=Colors.Yellow";
mostCurrent._progresspn.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 78;BA.debugLine="progresstimer.Initialize(\"ptimer\",50)";
_progresstimer.Initialize(processBA,"ptimer",(long) (50));
 //BA.debugLineNum = 79;BA.debugLine="progresstimer.Enabled=True";
_progresstimer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 80;BA.debugLine="End Sub";
return "";
}
public static String  _ptimer_tick() throws Exception{
 //BA.debugLineNum = 171;BA.debugLine="Sub ptimer_tick";
 //BA.debugLineNum = 172;BA.debugLine="If progresspn.Width = 100%x Then";
if (mostCurrent._progresspn.getWidth()==anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)) { 
 //BA.debugLineNum = 173;BA.debugLine="progresspn.Width = 0";
mostCurrent._progresspn.setWidth((int) (0));
 }else {
 //BA.debugLineNum = 175;BA.debugLine="progresspn.Width =progresspn.Width+10%x";
mostCurrent._progresspn.setWidth((int) (mostCurrent._progresspn.getWidth()+anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (10),mostCurrent.activityBA)));
 };
 //BA.debugLineNum = 177;BA.debugLine="End Sub";
return "";
}
public static boolean  _quality() throws Exception{
boolean _r = false;
String _a = "";
 //BA.debugLineNum = 82;BA.debugLine="Sub quality As Boolean";
 //BA.debugLineNum = 83;BA.debugLine="Dim r As Boolean";
_r = false;
 //BA.debugLineNum = 84;BA.debugLine="If File.Exists(File.DirInternal,\"qcheck\") Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"qcheck")) { 
 //BA.debugLineNum = 85;BA.debugLine="Dim a As String =  File.ReadString(File.DirInter";
_a = anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"qcheck");
 //BA.debugLineNum = 86;BA.debugLine="If a = \"true\" Then";
if ((_a).equals("true")) { 
 //BA.debugLineNum = 87;BA.debugLine="r = True";
_r = anywheresoftware.b4a.keywords.Common.True;
 }else {
 //BA.debugLineNum = 89;BA.debugLine="r = False";
_r = anywheresoftware.b4a.keywords.Common.False;
 };
 }else {
 //BA.debugLineNum = 92;BA.debugLine="File.WriteString(File.DirInternal,\"qcheck\",\"true";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"qcheck","true");
 //BA.debugLineNum = 93;BA.debugLine="r = True";
_r = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 95;BA.debugLine="Return r";
if (true) return _r;
 //BA.debugLineNum = 96;BA.debugLine="End Sub";
return false;
}
public static String  _save_click() throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
 //BA.debugLineNum = 554;BA.debugLine="Sub save_click";
 //BA.debugLineNum = 555;BA.debugLine="Dim b As Button =Sender";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 556;BA.debugLine="Dim m As Map = b.Tag";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_b.getTag()));
 //BA.debugLineNum = 557;BA.debugLine="showsavemsg =False";
_showsavemsg = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 558;BA.debugLine="Dim p As Panel = m.Get(\"view\")";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_m.Get((Object)("view"))));
 //BA.debugLineNum = 559;BA.debugLine="p.Visible=False";
_p.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 560;BA.debugLine="p.RemoveView";
_p.RemoveView();
 //BA.debugLineNum = 561;BA.debugLine="saveimage(m.Get(\"link\"))";
_saveimage(BA.ObjectToString(_m.Get((Object)("link"))));
 //BA.debugLineNum = 562;BA.debugLine="End Sub";
return "";
}
public static String  _saveimage(String _imglink) throws Exception{
com.burma.royal2d.httpjob _j = null;
 //BA.debugLineNum = 564;BA.debugLine="Sub saveimage (imglink As String)";
 //BA.debugLineNum = 565;BA.debugLine="Dim j As HttpJob";
_j = new com.burma.royal2d.httpjob();
 //BA.debugLineNum = 566;BA.debugLine="j.Initialize(\"saveimage\",Starter)";
_j._initialize /*String*/ (processBA,"saveimage",(Object)(mostCurrent._starter.getObject()));
 //BA.debugLineNum = 567;BA.debugLine="j.Download(imglink)";
_j._download /*String*/ (_imglink);
 //BA.debugLineNum = 568;BA.debugLine="End Sub";
return "";
}
public static String  _savemsg(String _link) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _base = null;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.ButtonWrapper _btn = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 521;BA.debugLine="Sub Savemsg (link As String)";
 //BA.debugLineNum = 522;BA.debugLine="showsavemsg=True";
_showsavemsg = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 523;BA.debugLine="Dim base As Panel";
_base = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 524;BA.debugLine="base.Initialize(\"pbase\")";
_base.Initialize(mostCurrent.activityBA,"pbase");
 //BA.debugLineNum = 525;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 526;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 527;BA.debugLine="p.Color=mycode.naviColor";
_p.setColor(mostCurrent._mycode._navicolor /*int*/ );
 //BA.debugLineNum = 528;BA.debugLine="Dim btn As Button";
_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 529;BA.debugLine="btn.Initialize(\"save\")";
_btn.Initialize(mostCurrent.activityBA,"save");
 //BA.debugLineNum = 530;BA.debugLine="btn.Background=mycode.btnbgdynamic(Colors.Transpa";
_btn.setBackground((android.graphics.drawable.Drawable)(mostCurrent._mycode._btnbgdynamic /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.Colors.Transparent,((int)0x9a466584),(int) (0)).getObject()));
 //BA.debugLineNum = 531;BA.debugLine="Dim cs As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 532;BA.debugLine="cs.Initialize.Color(Colors.Yellow).Size(mycode.te";
_cs.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.Yellow).Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (10)))).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME()).Append(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf0c7)))).Pop();
 //BA.debugLineNum = 533;BA.debugLine="cs.Color(Colors.White).Typeface(mycode.defaultfon";
_cs.Color(anywheresoftware.b4a.keywords.Common.Colors.White).Typeface((android.graphics.Typeface)(mostCurrent._mycode._defaultfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (8)))).Append(BA.ObjectToCharSequence("  Save")).PopAll();
 //BA.debugLineNum = 534;BA.debugLine="btn.Text = cs";
_btn.setText(BA.ObjectToCharSequence(_cs.getObject()));
 //BA.debugLineNum = 535;BA.debugLine="p.AddView(btn,0,0,150dip,50dip)";
_p.AddView((android.view.View)(_btn.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 536;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 537;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 538;BA.debugLine="m.Put(\"view\",base)";
_m.Put((Object)("view"),(Object)(_base.getObject()));
 //BA.debugLineNum = 539;BA.debugLine="m.Put(\"link\",link)";
_m.Put((Object)("link"),(Object)(_link));
 //BA.debugLineNum = 540;BA.debugLine="btn.Tag=m";
_btn.setTag((Object)(_m.getObject()));
 //BA.debugLineNum = 541;BA.debugLine="Activity.AddView(base,0,0,100%x,mycode.ActivityHe";
mostCurrent._activity.AddView((android.view.View)(_base.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),mostCurrent._mycode._activityheight /*int*/ );
 //BA.debugLineNum = 542;BA.debugLine="base.AddView(p,100%x-160dip,mycode.ActivityHeight";
_base.AddView((android.view.View)(_p.getObject()),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (160))),(int) (mostCurrent._mycode._activityheight /*int*/ -anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 543;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.List  _setlist() throws Exception{
 //BA.debugLineNum = 427;BA.debugLine="Sub setList  As List";
 //BA.debugLineNum = 428;BA.debugLine="Select ASViewPager2.CurrentIndex";
switch (BA.switchObjectToInt(mostCurrent._asviewpager2._getcurrentindex /*int*/ (),(int) (0),(int) (1),(int) (2))) {
case 0: {
 //BA.debugLineNum = 430;BA.debugLine="Return mycode.futureimglist";
if (true) return mostCurrent._mycode._futureimglist /*anywheresoftware.b4a.objects.collections.List*/ ;
 break; }
case 1: {
 //BA.debugLineNum = 432;BA.debugLine="Return mycode.weeklyimglist";
if (true) return mostCurrent._mycode._weeklyimglist /*anywheresoftware.b4a.objects.collections.List*/ ;
 break; }
case 2: {
 //BA.debugLineNum = 434;BA.debugLine="Return mycode.calendarimglist";
if (true) return mostCurrent._mycode._calendarimglist /*anywheresoftware.b4a.objects.collections.List*/ ;
 break; }
}
;
 //BA.debugLineNum = 436;BA.debugLine="End Sub";
return null;
}
public static String  _switchbtn_click() throws Exception{
 //BA.debugLineNum = 129;BA.debugLine="Sub switchbtn_click";
 //BA.debugLineNum = 130;BA.debugLine="changequality(hqb)";
_changequality(_hqb);
 //BA.debugLineNum = 131;BA.debugLine="End Sub";
return "";
}
}
