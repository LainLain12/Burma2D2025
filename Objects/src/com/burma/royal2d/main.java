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

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "com.burma.royal2d", "com.burma.royal2d.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
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
		activityBA = new BA(this, layout, processBA, "com.burma.royal2d", "com.burma.royal2d.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.burma.royal2d.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
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
		return main.class;
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
            BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (main) Pause event (activity is not paused). **");
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
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
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
public static String _fututerpaper = "";
public static String _aderrorcode = "";
public static String _bannerunit = "";
public static String _interunit = "";
public static String _rwinterunit = "";
public static String _smallbannerunit = "";
public static com.burma.royal2d.sseconnector _sse = null;
public static String _newsite = "";
public static int _keypresscount = 0;
public static com.aghajari.axrlottie.AXrLottieDrawableBuilder _dwe = null;
public static int _mipnheingt = 0;
public static int _bannerheight = 0;
public static int _bannertop = 0;
public static String _lottoscoiety = "";
public static String _histroyact = "";
public static String _threedact = "";
public static String _publicchat = "";
public static anywheresoftware.b4a.objects.Timer _changetimer = null;
public static String _site = "";
public static String _inforeader = "";
public static String _livereader = "";
public static boolean _changelean = false;
public static boolean _isadreceive = false;
public static String _nextactivity = "";
public static boolean _isclosed = false;
public static int _navpanelheight = 0;
public static anywheresoftware.b4a.objects.FirebaseAnalyticsWrapper _fbi = null;
public static boolean _iscall = false;
public static boolean _showcustommsg = false;
public static anywheresoftware.b4a.phone.Phone _phone = null;
public anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public anywheresoftware.b4a.objects.ButtonWrapper _settingbtn = null;
public com.aghajari.axrlottie.AXrLottie _axrlottie = null;
public com.aghajari.axrlottie.AXrLottieImageView _lottieview = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _logo = null;
public com.burma.royal2d.adshelper _ads = null;
public anywheresoftware.b4a.objects.PanelWrapper _pn = null;
public anywheresoftware.b4a.admobwrapper.AdViewWrapper _banner = null;
public anywheresoftware.b4a.objects.LabelWrapper _versionlb = null;
public anywheresoftware.b4a.objects.LabelWrapper _morningresultlb = null;
public anywheresoftware.b4a.objects.LabelWrapper _eveningresultlb = null;
public anywheresoftware.b4a.objects.PanelWrapper _barpn = null;
public anywheresoftware.b4a.objects.PanelWrapper _livepn = null;
public anywheresoftware.b4a.objects.LabelWrapper _livelb = null;
public anywheresoftware.b4a.objects.LabelWrapper _timelb = null;
public anywheresoftware.b4a.objects.PanelWrapper _morningresultpn = null;
public anywheresoftware.b4a.objects.PanelWrapper _eveningresultpn = null;
public anywheresoftware.b4a.objects.PanelWrapper _mdevider = null;
public anywheresoftware.b4a.objects.PanelWrapper _edevider = null;
public anywheresoftware.b4a.objects.LabelWrapper _mtimelb = null;
public anywheresoftware.b4a.objects.LabelWrapper _etimelb = null;
public anywheresoftware.b4a.objects.PanelWrapper _splashscreen = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _mainscv = null;
public anywheresoftware.b4a.admobwrapper.AdViewWrapper.InterstitialAdWrapper _inter = null;
public anywheresoftware.b4a.objects.ButtonWrapper _chatbtn = null;
public com.aghajari.axrlottie.AXrLottieImageView _ltw = null;
public anywheresoftware.b4a.objects.ButtonWrapper _lottobtn = null;
public anywheresoftware.b4a.objects.LabelWrapper _nmodernlb = null;
public anywheresoftware.b4a.objects.LabelWrapper _ninternetlb = null;
public anywheresoftware.b4a.objects.LabelWrapper _tmodernlb = null;
public anywheresoftware.b4a.objects.LabelWrapper _tinternetlb = null;
public com.burma.royal2d.report_details _report_details = null;
public com.burma.royal2d.mycode _mycode = null;
public com.burma.royal2d.public_chat _public_chat = null;
public com.burma.royal2d.gift_imageview _gift_imageview = null;
public com.burma.royal2d.settings _settings = null;
public com.burma.royal2d.profile_activity _profile_activity = null;
public com.burma.royal2d.guideline _guideline = null;
public com.burma.royal2d.login _login = null;
public com.burma.royal2d.future_tips _future_tips = null;
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

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (report_details.mostCurrent != null);
vis = vis | (public_chat.mostCurrent != null);
vis = vis | (gift_imageview.mostCurrent != null);
vis = vis | (settings.mostCurrent != null);
vis = vis | (profile_activity.mostCurrent != null);
vis = vis | (guideline.mostCurrent != null);
vis = vis | (login.mostCurrent != null);
vis = vis | (future_tips.mostCurrent != null);
vis = vis | (history.mostCurrent != null);
vis = vis | (lottohistory.mostCurrent != null);
vis = vis | (lottosociety.mostCurrent != null);
vis = vis | (privacy_policy.mostCurrent != null);
vis = vis | (setholidays.mostCurrent != null);
vis = vis | (threed.mostCurrent != null);
vis = vis | (gift_activity.mostCurrent != null);
return vis;}
public static void  _activity_create(boolean _firsttime) throws Exception{
ResumableSub_Activity_Create rsub = new ResumableSub_Activity_Create(null,_firsttime);
rsub.resume(processBA, null);
}
public static class ResumableSub_Activity_Create extends BA.ResumableSub {
public ResumableSub_Activity_Create(com.burma.royal2d.main parent,boolean _firsttime) {
this.parent = parent;
this._firsttime = _firsttime;
}
com.burma.royal2d.main parent;
boolean _firsttime;
boolean _haspermission = false;
anywheresoftware.b4a.objects.PanelWrapper _appbar = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
int _lwh = 0;
int[] _c = null;
anywheresoftware.b4a.objects.drawable.GradientDrawable _gd = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
int _w = 0;
int _l = 0;
anywheresoftware.b4a.objects.PanelWrapper _mnpn = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 88;BA.debugLine="mycode.checkActivityHeihgt(100%y)";
parent.mostCurrent._mycode._checkactivityheihgt /*String*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA));
 //BA.debugLineNum = 98;BA.debugLine="If FirstTime = True Then";
if (true) break;

case 1:
//if
this.state = 4;
if (_firsttime==anywheresoftware.b4a.keywords.Common.True) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 99;BA.debugLine="mycode.CreateNotificationChannel";
parent.mostCurrent._mycode._createnotificationchannel /*String*/ (mostCurrent.activityBA);
 if (true) break;

case 4:
//C
this.state = 5;
;
 //BA.debugLineNum = 101;BA.debugLine="DateTime.SetTimeZone(6.5)";
anywheresoftware.b4a.keywords.Common.DateTime.SetTimeZone(6.5);
 //BA.debugLineNum = 102;BA.debugLine="Wait For (CheckAndRequestNotificationPermission)";
anywheresoftware.b4a.keywords.Common.WaitFor("complete", processBA, this, _checkandrequestnotificationpermission());
this.state = 39;
return;
case 39:
//C
this.state = 5;
_haspermission = (Boolean) result[0];
;
 //BA.debugLineNum = 103;BA.debugLine="If HasPermission = False Then";
if (true) break;

case 5:
//if
this.state = 8;
if (_haspermission==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 7;
}if (true) break;

case 7:
//C
this.state = 8;
 //BA.debugLineNum = 104;BA.debugLine="Log(\"no permission\")";
anywheresoftware.b4a.keywords.Common.LogImpl("2131089","no permission",0);
 //BA.debugLineNum = 105;BA.debugLine="ToastMessageShow(\"no permission\", True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("no permission"),anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 8:
//C
this.state = 9;
;
 //BA.debugLineNum = 107;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (0));
this.state = 40;
return;
case 40:
//C
this.state = 9;
;
 //BA.debugLineNum = 109;BA.debugLine="checkinfo";
_checkinfo();
 //BA.debugLineNum = 112;BA.debugLine="fbi.Initialize";
parent._fbi.Initialize();
 //BA.debugLineNum = 113;BA.debugLine="fbi.SendEvent(\"login\", CreateMap (\"additional par";
parent._fbi.SendEvent("login",anywheresoftware.b4a.keywords.Common.createMap(new Object[] {(Object)("additional parameter"),(Object)(100)}));
 //BA.debugLineNum = 114;BA.debugLine="CallSubDelayed2(FirebaseMessaging, \"SubscribeToTo";
anywheresoftware.b4a.keywords.Common.CallSubDelayed2(processBA,(Object)(parent.mostCurrent._firebasemessaging.getObject()),"SubscribeToTopics",(Object)(new Object[]{(Object)("general")}));
 //BA.debugLineNum = 116;BA.debugLine="mycode.SETnavigationcolor";
parent.mostCurrent._mycode._setnavigationcolor /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 118;BA.debugLine="Activity.Color=mycode.bgColor";
parent.mostCurrent._activity.setColor(parent.mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 120;BA.debugLine="Dim appbar As Panel = mycode.appbar(\"BURMA 2D\",Tr";
_appbar = new anywheresoftware.b4a.objects.PanelWrapper();
_appbar = parent.mostCurrent._mycode._appbar /*anywheresoftware.b4a.objects.PanelWrapper*/ (mostCurrent.activityBA,"BURMA 2D",anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 121;BA.debugLine="Activity.AddView(appbar,0,mycode.appbartop,100%x,";
parent.mostCurrent._activity.AddView((android.view.View)(_appbar.getObject()),(int) (0),parent.mostCurrent._mycode._appbartop /*int*/ ,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),parent.mostCurrent._mycode._appbarheight /*int*/ );
 //BA.debugLineNum = 122;BA.debugLine="chatbtn.Initialize(\"chatbtn\")";
parent.mostCurrent._chatbtn.Initialize(mostCurrent.activityBA,"chatbtn");
 //BA.debugLineNum = 123;BA.debugLine="chatbtn.Text= buttoncsb(False,Chr(0xF1D7),True)";
parent.mostCurrent._chatbtn.setText(BA.ObjectToCharSequence(_buttoncsb(anywheresoftware.b4a.keywords.Common.False,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf1d7))),anywheresoftware.b4a.keywords.Common.True).getObject()));
 //BA.debugLineNum = 124;BA.debugLine="chatbtn.Background=mycode.btnbg(False)";
parent.mostCurrent._chatbtn.setBackground((android.graphics.drawable.Drawable)(parent.mostCurrent._mycode._btnbg /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False).getObject()));
 //BA.debugLineNum = 125;BA.debugLine="appbar.AddView(chatbtn,(100%x-(mycode.appbarheigh";
_appbar.AddView((android.view.View)(parent.mostCurrent._chatbtn.getObject()),(int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-(parent.mostCurrent._mycode._appbarheight /*int*/ +anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))))),(int) (0),parent.mostCurrent._mycode._appbarheight /*int*/ ,parent.mostCurrent._mycode._appbarheight /*int*/ );
 //BA.debugLineNum = 127;BA.debugLine="lottobtn.Initialize(\"lottobtn\")";
parent.mostCurrent._lottobtn.Initialize(mostCurrent.activityBA,"lottobtn");
 //BA.debugLineNum = 128;BA.debugLine="lottobtn.Text= buttoncsb(False,Chr(0xF080),True)";
parent.mostCurrent._lottobtn.setText(BA.ObjectToCharSequence(_buttoncsb(anywheresoftware.b4a.keywords.Common.False,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf080))),anywheresoftware.b4a.keywords.Common.True).getObject()));
 //BA.debugLineNum = 129;BA.debugLine="lottobtn.Background=mycode.btnbg(False)";
parent.mostCurrent._lottobtn.setBackground((android.graphics.drawable.Drawable)(parent.mostCurrent._mycode._btnbg /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False).getObject()));
 //BA.debugLineNum = 130;BA.debugLine="appbar.AddView(lottobtn,chatbtn.Left-(mycode.appb";
_appbar.AddView((android.view.View)(parent.mostCurrent._lottobtn.getObject()),(int) (parent.mostCurrent._chatbtn.getLeft()-(parent.mostCurrent._mycode._appbarheight /*int*/ -anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)))),(int) (0),parent.mostCurrent._mycode._appbarheight /*int*/ ,parent.mostCurrent._mycode._appbarheight /*int*/ );
 //BA.debugLineNum = 132;BA.debugLine="settingbtn.Initialize(\"settingbtn\")";
parent.mostCurrent._settingbtn.Initialize(mostCurrent.activityBA,"settingbtn");
 //BA.debugLineNum = 133;BA.debugLine="settingbtn.Text= buttoncsb(False,Chr(0xF013),True";
parent.mostCurrent._settingbtn.setText(BA.ObjectToCharSequence(_buttoncsb(anywheresoftware.b4a.keywords.Common.False,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf013))),anywheresoftware.b4a.keywords.Common.True).getObject()));
 //BA.debugLineNum = 134;BA.debugLine="settingbtn.Background=mycode.btnbg(False)";
parent.mostCurrent._settingbtn.setBackground((android.graphics.drawable.Drawable)(parent.mostCurrent._mycode._btnbg /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False).getObject()));
 //BA.debugLineNum = 135;BA.debugLine="appbar.AddView(settingbtn,lottobtn.Left-(mycode.a";
_appbar.AddView((android.view.View)(parent.mostCurrent._settingbtn.getObject()),(int) (parent.mostCurrent._lottobtn.getLeft()-(parent.mostCurrent._mycode._appbarheight /*int*/ -anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)))),(int) (0),parent.mostCurrent._mycode._appbarheight /*int*/ ,parent.mostCurrent._mycode._appbarheight /*int*/ );
 //BA.debugLineNum = 138;BA.debugLine="mainscv.Initialize(1000dip)";
parent.mostCurrent._mainscv.Initialize(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1000)));
 //BA.debugLineNum = 139;BA.debugLine="Activity.AddView(mainscv,0,mycode.appbarheight,10";
parent.mostCurrent._activity.AddView((android.view.View)(parent.mostCurrent._mainscv.getObject()),(int) (0),parent.mostCurrent._mycode._appbarheight /*int*/ ,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),(int) (parent.mostCurrent._mycode._activityheight /*int*/ -(parent._navpanelheight+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))+parent.mostCurrent._mycode._appbarheight /*int*/ )));
 //BA.debugLineNum = 140;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (0));
this.state = 41;
return;
case 41:
//C
this.state = 9;
;
 //BA.debugLineNum = 141;BA.debugLine="barpn.Initialize(\"\")";
parent.mostCurrent._barpn.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 142;BA.debugLine="barpn.SetBackgroundImage(LoadBitmap(File.DirAsset";
parent.mostCurrent._barpn.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"profile_bg.png").getObject()));
 //BA.debugLineNum = 143;BA.debugLine="mainscv.Panel.AddView(barpn,0,0,100%x,40%x)";
parent.mostCurrent._mainscv.getPanel().AddView((android.view.View)(parent.mostCurrent._barpn.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (40),mostCurrent.activityBA));
 //BA.debugLineNum = 146;BA.debugLine="livepn.Initialize(\"\")";
parent.mostCurrent._livepn.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 147;BA.debugLine="livepn.Elevation=10dip";
parent.mostCurrent._livepn.setElevation((float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 148;BA.debugLine="mainscv.Panel.AddView(livepn,0,10dip,100dip,100di";
parent.mostCurrent._mainscv.getPanel().AddView((android.view.View)(parent.mostCurrent._livepn.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)));
 //BA.debugLineNum = 149;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (0));
this.state = 42;
return;
case 42:
//C
this.state = 9;
;
 //BA.debugLineNum = 151;BA.debugLine="Dim AXrLottie As AXrLottie";
parent.mostCurrent._axrlottie = new com.aghajari.axrlottie.AXrLottie();
 //BA.debugLineNum = 152;BA.debugLine="AXrLottie.Initialize";
parent.mostCurrent._axrlottie.Initialize();
 //BA.debugLineNum = 153;BA.debugLine="ltw.Initialize(\"\")";
parent.mostCurrent._ltw.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 154;BA.debugLine="dwe.InitializeFromFile(File.DirAssets,\"livee.json";
parent._dwe.InitializeFromFile(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"livee.json").SetAutoRepeat(parent._dwe.AUTO_REPEAT_INFINITE).SetAutoStart(anywheresoftware.b4a.keywords.Common.True).SetCacheEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 158;BA.debugLine="ltw.SetLottieDrawable(dwe.Build)";
parent.mostCurrent._ltw.SetLottieDrawable((com.aghajari.rlottie.AXrLottieDrawable)(parent._dwe.Build().getObject()));
 //BA.debugLineNum = 161;BA.debugLine="livelb.Initialize(\"\")";
parent.mostCurrent._livelb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 162;BA.debugLine="livelb.TextColor=Colors.White";
parent.mostCurrent._livelb.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 163;BA.debugLine="livelb.TextSize=mycode.textsize1(20)";
parent.mostCurrent._livelb.setTextSize(parent.mostCurrent._mycode._textsize1 /*float*/ (mostCurrent.activityBA,(int) (20)));
 //BA.debugLineNum = 164;BA.debugLine="livelb.Gravity=Gravity.CENTER";
parent.mostCurrent._livelb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 165;BA.debugLine="livelb.Text=\"--\"";
parent.mostCurrent._livelb.setText(BA.ObjectToCharSequence("--"));
 //BA.debugLineNum = 166;BA.debugLine="livelb.Typeface=mycode.livebold";
parent.mostCurrent._livelb.setTypeface((android.graphics.Typeface)(parent.mostCurrent._mycode._livebold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 167;BA.debugLine="livepn.AddView(livelb,0,0,livepn.Width,livepn.Hei";
parent.mostCurrent._livepn.AddView((android.view.View)(parent.mostCurrent._livelb.getObject()),(int) (0),(int) (0),parent.mostCurrent._livepn.getWidth(),parent.mostCurrent._livepn.getHeight());
 //BA.debugLineNum = 168;BA.debugLine="livelb.BringToFront";
parent.mostCurrent._livelb.BringToFront();
 //BA.debugLineNum = 169;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 170;BA.debugLine="livelb.Height=su.MeasureMultilineTextHeight(livel";
parent.mostCurrent._livelb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(parent.mostCurrent._livelb.getObject()),BA.ObjectToCharSequence(parent.mostCurrent._livelb.getText())));
 //BA.debugLineNum = 172;BA.debugLine="livelb.Width=mycode.TextWidth(livelb,livelb.Text)";
parent.mostCurrent._livelb.setWidth(parent.mostCurrent._mycode._textwidth /*int*/ (mostCurrent.activityBA,parent.mostCurrent._livelb,parent.mostCurrent._livelb.getText()));
 //BA.debugLineNum = 174;BA.debugLine="Dim lwh As Int";
_lwh = 0;
 //BA.debugLineNum = 175;BA.debugLine="If livelb.Height > livelb.Width Then";
if (true) break;

case 9:
//if
this.state = 14;
if (parent.mostCurrent._livelb.getHeight()>parent.mostCurrent._livelb.getWidth()) { 
this.state = 11;
}else {
this.state = 13;
}if (true) break;

case 11:
//C
this.state = 14;
 //BA.debugLineNum = 176;BA.debugLine="lwh  = livelb.Height+10dip";
_lwh = (int) (parent.mostCurrent._livelb.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 if (true) break;

case 13:
//C
this.state = 14;
 //BA.debugLineNum = 178;BA.debugLine="lwh = livelb.Width+10dip";
_lwh = (int) (parent.mostCurrent._livelb.getWidth()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 if (true) break;

case 14:
//C
this.state = 15;
;
 //BA.debugLineNum = 180;BA.debugLine="livelb.Width = lwh";
parent.mostCurrent._livelb.setWidth(_lwh);
 //BA.debugLineNum = 181;BA.debugLine="livelb.Height = lwh";
parent.mostCurrent._livelb.setHeight(_lwh);
 //BA.debugLineNum = 183;BA.debugLine="livepn.Height=lwh+40dip";
parent.mostCurrent._livepn.setHeight((int) (_lwh+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))));
 //BA.debugLineNum = 184;BA.debugLine="livepn.Width=lwh+40dip";
parent.mostCurrent._livepn.setWidth((int) (_lwh+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))));
 //BA.debugLineNum = 185;BA.debugLine="livelb.Left = (livepn.Width-livelb.Width)/2";
parent.mostCurrent._livelb.setLeft((int) ((parent.mostCurrent._livepn.getWidth()-parent.mostCurrent._livelb.getWidth())/(double)2));
 //BA.debugLineNum = 186;BA.debugLine="livelb.Top=(livepn.Height-livelb.Height)/2";
parent.mostCurrent._livelb.setTop((int) ((parent.mostCurrent._livepn.getHeight()-parent.mostCurrent._livelb.getHeight())/(double)2));
 //BA.debugLineNum = 187;BA.debugLine="livepn.Left=(100%x-livepn.Width)/2";
parent.mostCurrent._livepn.setLeft((int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-parent.mostCurrent._livepn.getWidth())/(double)2));
 //BA.debugLineNum = 188;BA.debugLine="livepn.AddView(ltw,0,0,livepn.Width,livepn.Height";
parent.mostCurrent._livepn.AddView((android.view.View)(parent.mostCurrent._ltw.getObject()),(int) (0),(int) (0),parent.mostCurrent._livepn.getWidth(),parent.mostCurrent._livepn.getHeight());
 //BA.debugLineNum = 189;BA.debugLine="livelb.BringToFront";
parent.mostCurrent._livelb.BringToFront();
 //BA.debugLineNum = 191;BA.debugLine="timelb.Initialize(\"\")";
parent.mostCurrent._timelb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 193;BA.debugLine="Dim c(2) As Int";
_c = new int[(int) (2)];
;
 //BA.debugLineNum = 194;BA.debugLine="c(0) = 0xC4140C1F";
_c[(int) (0)] = ((int)0xc4140c1f);
 //BA.debugLineNum = 195;BA.debugLine="c(1)=mycode.bgColor";
_c[(int) (1)] = parent.mostCurrent._mycode._bgcolor /*int*/ ;
 //BA.debugLineNum = 196;BA.debugLine="Dim gd As GradientDrawable";
_gd = new anywheresoftware.b4a.objects.drawable.GradientDrawable();
 //BA.debugLineNum = 197;BA.debugLine="gd.Initialize(\"TOP_BOTTOM\",c)";
_gd.Initialize(BA.getEnumFromString(android.graphics.drawable.GradientDrawable.Orientation.class,"TOP_BOTTOM"),_c);
 //BA.debugLineNum = 199;BA.debugLine="timelb.Gravity=Gravity.CENTER";
parent.mostCurrent._timelb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 200;BA.debugLine="timelb.TextSize = 13";
parent.mostCurrent._timelb.setTextSize((float) (13));
 //BA.debugLineNum = 202;BA.debugLine="mainscv.Panel.AddView(timelb,0,livepn.Height+live";
parent.mostCurrent._mainscv.getPanel().AddView((android.view.View)(parent.mostCurrent._timelb.getObject()),(int) (0),(int) (parent.mostCurrent._livepn.getHeight()+parent.mostCurrent._livepn.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 203;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 204;BA.debugLine="timelb.Height=su.MeasureMultilineTextHeight(timel";
parent.mostCurrent._timelb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(parent.mostCurrent._timelb.getObject()),BA.ObjectToCharSequence(_timecsbuilder(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf1da))),"-----------").getObject())));
 //BA.debugLineNum = 205;BA.debugLine="If timelb. Height +timelb.Top < barpn.Height Then";
if (true) break;

case 15:
//if
this.state = 20;
if (parent.mostCurrent._timelb.getHeight()+parent.mostCurrent._timelb.getTop()<parent.mostCurrent._barpn.getHeight()) { 
this.state = 17;
}else {
this.state = 19;
}if (true) break;

case 17:
//C
this.state = 20;
 //BA.debugLineNum = 206;BA.debugLine="timelb.Height = barpn.Height - timelb.Top";
parent.mostCurrent._timelb.setHeight((int) (parent.mostCurrent._barpn.getHeight()-parent.mostCurrent._timelb.getTop()));
 if (true) break;

case 19:
//C
this.state = 20;
 //BA.debugLineNum = 208;BA.debugLine="timelb.Height =timelb.Height+10dip";
parent.mostCurrent._timelb.setHeight((int) (parent.mostCurrent._timelb.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 if (true) break;

case 20:
//C
this.state = 21;
;
 //BA.debugLineNum = 211;BA.debugLine="morningresultpn.Initialize(\"\")";
parent.mostCurrent._morningresultpn.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 213;BA.debugLine="morningresultpn.Elevation = 10dip";
parent.mostCurrent._morningresultpn.setElevation((float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 214;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 215;BA.debugLine="cd.Initialize(mycode.naviColor,14dip)";
_cd.Initialize(parent.mostCurrent._mycode._navicolor /*int*/ ,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (14)));
 //BA.debugLineNum = 216;BA.debugLine="morningresultpn.Background=cd";
parent.mostCurrent._morningresultpn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 217;BA.debugLine="Dim w As Int = (100%x-30dip)/2";
_w = (int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)))/(double)2);
 //BA.debugLineNum = 218;BA.debugLine="mainscv.Panel.AddView(morningresultpn,10dip,timel";
parent.mostCurrent._mainscv.getPanel().AddView((android.view.View)(parent.mostCurrent._morningresultpn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (parent.mostCurrent._timelb.getHeight()+parent.mostCurrent._timelb.getTop()),_w,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 220;BA.debugLine="mtimelb.Initialize(\"\")";
parent.mostCurrent._mtimelb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 221;BA.debugLine="mdevider.Initialize(\"\")";
parent.mostCurrent._mdevider.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 222;BA.debugLine="morningresultpn.AddView(mtimelb,0,5dip,morningres";
parent.mostCurrent._morningresultpn.AddView((android.view.View)(parent.mostCurrent._mtimelb.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),parent.mostCurrent._morningresultpn.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 223;BA.debugLine="mtimelb.Gravity=Gravity.CENTER";
parent.mostCurrent._mtimelb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 224;BA.debugLine="mtimelb.Text=timecsb(\"11:00 / 12:01PM\")";
parent.mostCurrent._mtimelb.setText(BA.ObjectToCharSequence(_timecsb("11:00 / 12:01PM").getObject()));
 //BA.debugLineNum = 225;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 226;BA.debugLine="mtimelb.Height=su.MeasureMultilineTextHeight(mtim";
parent.mostCurrent._mtimelb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(parent.mostCurrent._mtimelb.getObject()),BA.ObjectToCharSequence(_timecsb("12:01 PM").getObject())));
 //BA.debugLineNum = 227;BA.debugLine="morningresultpn.AddView(mdevider,10dip,mtimelb.He";
parent.mostCurrent._morningresultpn.AddView((android.view.View)(parent.mostCurrent._mdevider.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (parent.mostCurrent._mtimelb.getHeight()+parent.mostCurrent._mtimelb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))),(int) (parent.mostCurrent._morningresultpn.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)));
 //BA.debugLineNum = 228;BA.debugLine="mdevider.Color=Colors.Gray";
parent.mostCurrent._mdevider.setColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 //BA.debugLineNum = 230;BA.debugLine="morningresultlb.Initialize(\"\")";
parent.mostCurrent._morningresultlb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 231;BA.debugLine="morningresultpn.AddView(morningresultlb,10dip,mde";
parent.mostCurrent._morningresultpn.AddView((android.view.View)(parent.mostCurrent._morningresultlb.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (parent.mostCurrent._mdevider.getHeight()+parent.mostCurrent._mdevider.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))),(int) (parent.mostCurrent._morningresultpn.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 232;BA.debugLine="morningresultlb.Gravity=Gravity.CENTER";
parent.mostCurrent._morningresultlb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 233;BA.debugLine="morningresultlb.Text=resultscs(\"----.--\",\"----.--";
parent.mostCurrent._morningresultlb.setText(BA.ObjectToCharSequence(_resultscs("----.--","----.--","--").getObject()));
 //BA.debugLineNum = 234;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 235;BA.debugLine="Log(\"here\")";
anywheresoftware.b4a.keywords.Common.LogImpl("2131220","here",0);
 //BA.debugLineNum = 237;BA.debugLine="morningresultlb.Height=su.MeasureMultilineTextHei";
parent.mostCurrent._morningresultlb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(parent.mostCurrent._morningresultlb.getObject()),BA.ObjectToCharSequence(_resultscs("1245.12","31465.88","25").getObject())));
 //BA.debugLineNum = 238;BA.debugLine="morningresultpn.Height=morningresultlb.Height+mor";
parent.mostCurrent._morningresultpn.setHeight((int) (parent.mostCurrent._morningresultlb.getHeight()+parent.mostCurrent._morningresultlb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 //BA.debugLineNum = 241;BA.debugLine="eveningresultpn.Initialize(\"\")";
parent.mostCurrent._eveningresultpn.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 242;BA.debugLine="eveningresultpn.Elevation =10dip";
parent.mostCurrent._eveningresultpn.setElevation((float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 243;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 244;BA.debugLine="cd.Initialize(mycode.naviColor,14dip)";
_cd.Initialize(parent.mostCurrent._mycode._navicolor /*int*/ ,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (14)));
 //BA.debugLineNum = 245;BA.debugLine="eveningresultpn.Background=cd";
parent.mostCurrent._eveningresultpn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 246;BA.debugLine="Dim w As Int = (100%x-30dip)/2";
_w = (int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)))/(double)2);
 //BA.debugLineNum = 247;BA.debugLine="mainscv.Panel.AddView(eveningresultpn,morningresu";
parent.mostCurrent._mainscv.getPanel().AddView((android.view.View)(parent.mostCurrent._eveningresultpn.getObject()),(int) (parent.mostCurrent._morningresultpn.getWidth()+parent.mostCurrent._morningresultpn.getLeft()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),parent.mostCurrent._morningresultpn.getTop(),_w,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 249;BA.debugLine="etimelb.Initialize(\"\")";
parent.mostCurrent._etimelb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 250;BA.debugLine="edevider.Initialize(\"\")";
parent.mostCurrent._edevider.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 251;BA.debugLine="eveningresultpn.AddView(etimelb,0,5dip,eveningres";
parent.mostCurrent._eveningresultpn.AddView((android.view.View)(parent.mostCurrent._etimelb.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),parent.mostCurrent._eveningresultpn.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 252;BA.debugLine="etimelb.Gravity=Gravity.CENTER";
parent.mostCurrent._etimelb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 253;BA.debugLine="etimelb.Text=timecsb(\"4:30PM\")";
parent.mostCurrent._etimelb.setText(BA.ObjectToCharSequence(_timecsb("4:30PM").getObject()));
 //BA.debugLineNum = 254;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 255;BA.debugLine="etimelb.Height=su.MeasureMultilineTextHeight(etim";
parent.mostCurrent._etimelb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(parent.mostCurrent._etimelb.getObject()),BA.ObjectToCharSequence(_timecsb("12:01 PM").getObject())));
 //BA.debugLineNum = 256;BA.debugLine="eveningresultpn.AddView(edevider,10dip,etimelb.He";
parent.mostCurrent._eveningresultpn.AddView((android.view.View)(parent.mostCurrent._edevider.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (parent.mostCurrent._etimelb.getHeight()+parent.mostCurrent._etimelb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))),(int) (parent.mostCurrent._eveningresultpn.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)));
 //BA.debugLineNum = 257;BA.debugLine="edevider.Color=Colors.Gray";
parent.mostCurrent._edevider.setColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 //BA.debugLineNum = 258;BA.debugLine="Log(\"here\")";
anywheresoftware.b4a.keywords.Common.LogImpl("2131243","here",0);
 //BA.debugLineNum = 259;BA.debugLine="eveningresultlb.Initialize(\"\")";
parent.mostCurrent._eveningresultlb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 260;BA.debugLine="eveningresultpn.AddView(eveningresultlb,10dip,ede";
parent.mostCurrent._eveningresultpn.AddView((android.view.View)(parent.mostCurrent._eveningresultlb.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (parent.mostCurrent._edevider.getHeight()+parent.mostCurrent._edevider.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))),(int) (parent.mostCurrent._eveningresultpn.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 261;BA.debugLine="eveningresultlb.Gravity=Gravity.CENTER";
parent.mostCurrent._eveningresultlb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 262;BA.debugLine="eveningresultlb.Text=resultscs(\"----.--\",\"----.--";
parent.mostCurrent._eveningresultlb.setText(BA.ObjectToCharSequence(_resultscs("----.--","----.--","--").getObject()));
 //BA.debugLineNum = 263;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 264;BA.debugLine="eveningresultlb.Height=su.MeasureMultilineTextHei";
parent.mostCurrent._eveningresultlb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(parent.mostCurrent._eveningresultlb.getObject()),BA.ObjectToCharSequence(_resultscs("1245.12","31465.88","25").getObject())));
 //BA.debugLineNum = 265;BA.debugLine="eveningresultpn.Height=eveningresultlb.Height+eve";
parent.mostCurrent._eveningresultpn.setHeight((int) (parent.mostCurrent._eveningresultlb.getHeight()+parent.mostCurrent._eveningresultlb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 //BA.debugLineNum = 266;BA.debugLine="bannertop = eveningresultpn.Height+eveningresultp";
parent._bannertop = (int) (parent.mostCurrent._eveningresultpn.getHeight()+parent.mostCurrent._eveningresultpn.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 268;BA.debugLine="If banner.IsInitialized Then";
if (true) break;

case 21:
//if
this.state = 34;
if (parent.mostCurrent._banner.IsInitialized()) { 
this.state = 23;
}else {
this.state = 33;
}if (true) break;

case 23:
//C
this.state = 24;
 //BA.debugLineNum = 269;BA.debugLine="If isAdReceive =True Then";
if (true) break;

case 24:
//if
this.state = 31;
if (parent._isadreceive==anywheresoftware.b4a.keywords.Common.True) { 
this.state = 26;
}if (true) break;

case 26:
//C
this.state = 27;
 //BA.debugLineNum = 270;BA.debugLine="Dim l As Int = (100%x-300dip)/2";
_l = (int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)))/(double)2);
 //BA.debugLineNum = 271;BA.debugLine="If showCustomMsg = False Then";
if (true) break;

case 27:
//if
this.state = 30;
if (parent._showcustommsg==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 29;
}if (true) break;

case 29:
//C
this.state = 30;
 //BA.debugLineNum = 272;BA.debugLine="mainscv.Panel.AddView(banner,l,bannertop,300di";
parent.mostCurrent._mainscv.getPanel().AddView((android.view.View)(parent.mostCurrent._banner.getObject()),_l,parent._bannertop,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250)));
 if (true) break;

case 30:
//C
this.state = 31;
;
 //BA.debugLineNum = 274;BA.debugLine="bannerheight=250dip";
parent._bannerheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250));
 if (true) break;

case 31:
//C
this.state = 34;
;
 if (true) break;

case 33:
//C
this.state = 34;
 //BA.debugLineNum = 277;BA.debugLine="banner.Initialize2(\"banner\",bannerUnit,banner.SI";
parent.mostCurrent._banner.Initialize2(mostCurrent.activityBA,"banner",parent._bannerunit,parent.mostCurrent._banner.SIZE_IAB_MRECT);
 //BA.debugLineNum = 278;BA.debugLine="banner.LoadAd";
parent.mostCurrent._banner.LoadAd();
 if (true) break;

case 34:
//C
this.state = 35;
;
 //BA.debugLineNum = 282;BA.debugLine="Dim mnpn As Panel =MornetPn";
_mnpn = new anywheresoftware.b4a.objects.PanelWrapper();
_mnpn = _mornetpn();
 //BA.debugLineNum = 283;BA.debugLine="If showCustomMsg=False Then";
if (true) break;

case 35:
//if
this.state = 38;
if (parent._showcustommsg==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 37;
}if (true) break;

case 37:
//C
this.state = 38;
 //BA.debugLineNum = 284;BA.debugLine="mainscv.Panel.AddView(mnpn,10dip,bannertop+banne";
parent.mostCurrent._mainscv.getPanel().AddView((android.view.View)(_mnpn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (parent._bannertop+parent._bannerheight+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_mnpn.getHeight());
 if (true) break;

case 38:
//C
this.state = -1;
;
 //BA.debugLineNum = 288;BA.debugLine="mainscv.Panel.Height=mnpn.Height+mnpn.Top+10dip";
parent.mostCurrent._mainscv.getPanel().setHeight((int) (_mnpn.getHeight()+_mnpn.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 290;BA.debugLine="Activity.AddView(navigationbar,0,(mycode.Activity";
parent.mostCurrent._activity.AddView((android.view.View)(_navigationbar().getObject()),(int) (0),(int) ((parent.mostCurrent._mycode._activityheight /*int*/ -parent._navpanelheight)),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),(int) (parent._navpanelheight+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))));
 //BA.debugLineNum = 293;BA.debugLine="progressshow";
_progressshow();
 //BA.debugLineNum = 295;BA.debugLine="change";
_change();
 //BA.debugLineNum = 296;BA.debugLine="Log(\"here = ==============\")";
anywheresoftware.b4a.keywords.Common.LogImpl("2131281","here = ==============",0);
 //BA.debugLineNum = 297;BA.debugLine="changetimer.Initialize(\"changetimer\",1000)";
parent._changetimer.Initialize(processBA,"changetimer",(long) (1000));
 //BA.debugLineNum = 298;BA.debugLine="changetimer.Enabled=True";
parent._changetimer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 300;BA.debugLine="ADConsent";
_adconsent();
 //BA.debugLineNum = 301;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _complete(boolean _haspermission) throws Exception{
}
public static boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 418;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
 //BA.debugLineNum = 419;BA.debugLine="If KeyCode  = KeyCodes.KEYCODE_BACK Then";
if (_keycode==anywheresoftware.b4a.keywords.Common.KeyCodes.KEYCODE_BACK) { 
 //BA.debugLineNum = 420;BA.debugLine="If showCustomMsg = True Then";
if (_showcustommsg==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 421;BA.debugLine="showCustomMsg=False";
_showcustommsg = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 422;BA.debugLine="Activity.RemoveViewAt(Activity.NumberOfViews-1)";
mostCurrent._activity.RemoveViewAt((int) (mostCurrent._activity.getNumberOfViews()-1));
 }else {
 //BA.debugLineNum = 424;BA.debugLine="keypresscount = keypresscount +1";
_keypresscount = (int) (_keypresscount+1);
 //BA.debugLineNum = 425;BA.debugLine="If keypresscount >1 Then";
if (_keypresscount>1) { 
 //BA.debugLineNum = 426;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 }else {
 //BA.debugLineNum = 428;BA.debugLine="ToastMessageShow(\"Press Again To Closed\",False";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Press Again To Closed"),anywheresoftware.b4a.keywords.Common.False);
 };
 };
 };
 //BA.debugLineNum = 432;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 433;BA.debugLine="End Sub";
return false;
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 496;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 497;BA.debugLine="sse.Finish";
_sse._finish /*String*/ ();
 //BA.debugLineNum = 498;BA.debugLine="If sse.IsInitialized Then";
if (_sse.IsInitialized /*boolean*/ ()) { 
 //BA.debugLineNum = 499;BA.debugLine="sse.Finish";
_sse._finish /*String*/ ();
 }else {
 //BA.debugLineNum = 501;BA.debugLine="sse.Initialize(Me,\"live\",\"live\",Application.Pac";
_sse._initialize /*String*/ (processBA,main.getObject(),"live","live",anywheresoftware.b4a.keywords.Common.Application.getPackageName());
 //BA.debugLineNum = 502;BA.debugLine="sse.Finish";
_sse._finish /*String*/ ();
 };
 //BA.debugLineNum = 504;BA.debugLine="If changetimer.IsInitialized Then";
if (_changetimer.IsInitialized()) { 
 //BA.debugLineNum = 505;BA.debugLine="changetimer.Enabled=False";
_changetimer.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 507;BA.debugLine="isCall=False";
_iscall = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 508;BA.debugLine="If UserClosed =False Then";
if (_userclosed==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 509;BA.debugLine="isCLosed = False";
_isclosed = anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 511;BA.debugLine="isCLosed=True";
_isclosed = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 513;BA.debugLine="banner.Pause";
mostCurrent._banner.Pause();
 //BA.debugLineNum = 515;BA.debugLine="End Sub";
return "";
}
public static String  _activity_permissionresult(String _permission,boolean _result) throws Exception{
 //BA.debugLineNum = 517;BA.debugLine="Sub Activity_PermissionResult (Permission As Strin";
 //BA.debugLineNum = 519;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 446;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 447;BA.debugLine="isCall=True";
_iscall = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 449;BA.debugLine="keypresscount =0";
_keypresscount = (int) (0);
 //BA.debugLineNum = 450;BA.debugLine="If changetimer.IsInitialized Then";
if (_changetimer.IsInitialized()) { 
 //BA.debugLineNum = 451;BA.debugLine="changetimer.Enabled=True";
_changetimer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 453;BA.debugLine="Connect";
_connect();
 //BA.debugLineNum = 454;BA.debugLine="change";
_change();
 //BA.debugLineNum = 456;BA.debugLine="If inter.IsInitialized Then";
if (mostCurrent._inter.IsInitialized()) { 
 //BA.debugLineNum = 457;BA.debugLine="If  inter.Ready = False Then";
if (mostCurrent._inter.getReady()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 458;BA.debugLine="inter.LoadAd";
mostCurrent._inter.LoadAd(mostCurrent.activityBA);
 };
 }else {
 //BA.debugLineNum = 462;BA.debugLine="inter.Initialize(\"inter\",interUnit)";
mostCurrent._inter.Initialize(mostCurrent.activityBA,"inter",_interunit);
 //BA.debugLineNum = 463;BA.debugLine="inter.LoadAd";
mostCurrent._inter.LoadAd(mostCurrent.activityBA);
 };
 //BA.debugLineNum = 468;BA.debugLine="If banner.IsInitialized Then";
if (mostCurrent._banner.IsInitialized()) { 
 //BA.debugLineNum = 469;BA.debugLine="banner.Resume";
mostCurrent._banner.Resume();
 //BA.debugLineNum = 470;BA.debugLine="If isAdReceive =True Then";
if (_isadreceive==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 471;BA.debugLine="bannerheight =250dip";
_bannerheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250));
 }else {
 //BA.debugLineNum = 473;BA.debugLine="bannerheight = 0";
_bannerheight = (int) (0);
 //BA.debugLineNum = 474;BA.debugLine="banner.LoadAd";
mostCurrent._banner.LoadAd();
 };
 }else {
 //BA.debugLineNum = 477;BA.debugLine="banner.Initialize2(\"banner\",bannerUnit,banner.SI";
mostCurrent._banner.Initialize2(mostCurrent.activityBA,"banner",_bannerunit,mostCurrent._banner.SIZE_IAB_MRECT);
 //BA.debugLineNum = 478;BA.debugLine="banner.LoadAd";
mostCurrent._banner.LoadAd();
 };
 //BA.debugLineNum = 481;BA.debugLine="If isCLosed = False Then";
if (_isclosed==anywheresoftware.b4a.keywords.Common.False) { 
 }else {
 //BA.debugLineNum = 483;BA.debugLine="If pn.IsInitialized Then";
if (mostCurrent._pn.IsInitialized()) { 
 //BA.debugLineNum = 485;BA.debugLine="pn.RemoveView";
mostCurrent._pn.RemoveView();
 //BA.debugLineNum = 486;BA.debugLine="If showCustomMsg =False Then";
if (_showcustommsg==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 487;BA.debugLine="mainscv.Panel.AddView(pn,10dip,bannerheight+ba";
mostCurrent._mainscv.getPanel().AddView((android.view.View)(mostCurrent._pn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_bannerheight+_bannertop+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_mipnheingt);
 //BA.debugLineNum = 488;BA.debugLine="mainscv.Panel.Height=mipnheingt+250dip+bannert";
mostCurrent._mainscv.getPanel().setHeight((int) (_mipnheingt+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250))+_bannertop+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 };
 };
 };
 //BA.debugLineNum = 494;BA.debugLine="End Sub";
return "";
}
public static void  _adconsent() throws Exception{
ResumableSub_ADConsent rsub = new ResumableSub_ADConsent(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_ADConsent extends BA.ResumableSub {
public ResumableSub_ADConsent(com.burma.royal2d.main parent) {
this.parent = parent;
}
com.burma.royal2d.main parent;
boolean _success = false;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 1082;BA.debugLine="Dim ads As AdsHelper";
parent.mostCurrent._ads = new com.burma.royal2d.adshelper();
 //BA.debugLineNum = 1083;BA.debugLine="ads.Initialize";
parent.mostCurrent._ads._initialize /*String*/ (processBA);
 //BA.debugLineNum = 1084;BA.debugLine="If ads.GetConsentStatus = \"UNKNOWN\" Or ads.GetCon";
if (true) break;

case 1:
//if
this.state = 4;
if ((parent.mostCurrent._ads._getconsentstatus /*String*/ ()).equals("UNKNOWN") || (parent.mostCurrent._ads._getconsentstatus /*String*/ ()).equals("REQUIRED")) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 1085;BA.debugLine="Wait For (ads.RequestConsentInformation(False))";
anywheresoftware.b4a.keywords.Common.WaitFor("complete", processBA, this, parent.mostCurrent._ads._requestconsentinformation /*anywheresoftware.b4a.keywords.Common.ResumableSubWrapper*/ (anywheresoftware.b4a.keywords.Common.False));
this.state = 8;
return;
case 8:
//C
this.state = 4;
_success = (Boolean) result[0];
;
 if (true) break;
;
 //BA.debugLineNum = 1087;BA.debugLine="If ads.GetConsentStatus = \"REQUIRED\" And ads.GetC";

case 4:
//if
this.state = 7;
if ((parent.mostCurrent._ads._getconsentstatus /*String*/ ()).equals("REQUIRED") && parent.mostCurrent._ads._getconsentformavailable /*boolean*/ ()) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 1088;BA.debugLine="Wait For (ads.ShowConsentForm) Complete (Success";
anywheresoftware.b4a.keywords.Common.WaitFor("complete", processBA, this, parent.mostCurrent._ads._showconsentform /*anywheresoftware.b4a.keywords.Common.ResumableSubWrapper*/ ());
this.state = 9;
return;
case 9:
//C
this.state = 7;
_success = (Boolean) result[0];
;
 if (true) break;

case 7:
//C
this.state = -1;
;
 //BA.debugLineNum = 1090;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _banner_failedtoreceivead(String _errorcode) throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _json = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 407;BA.debugLine="Sub banner_FailedToReceiveAd (ErrorCode As String)";
 //BA.debugLineNum = 408;BA.debugLine="isAdReceive=False";
_isadreceive = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 409;BA.debugLine="Dim json As JSONParser";
_json = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 410;BA.debugLine="json.Initialize(ErrorCode)";
_json.Initialize(_errorcode);
 //BA.debugLineNum = 411;BA.debugLine="Dim m As Map  = json.NextObject";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = _json.NextObject();
 //BA.debugLineNum = 413;BA.debugLine="adErrorCode =m.Get(\"Code\")";
_aderrorcode = BA.ObjectToString(_m.Get((Object)("Code")));
 //BA.debugLineNum = 414;BA.debugLine="Log(\"Failed: \" & ErrorCode)";
anywheresoftware.b4a.keywords.Common.LogImpl("224838151","Failed: "+_errorcode,0);
 //BA.debugLineNum = 416;BA.debugLine="End Sub";
return "";
}
public static String  _banner_receivead() throws Exception{
int _scvheiht = 0;
 //BA.debugLineNum = 390;BA.debugLine="Sub banner_ReceiveAd";
 //BA.debugLineNum = 391;BA.debugLine="adErrorCode = \"got\"";
_aderrorcode = "got";
 //BA.debugLineNum = 392;BA.debugLine="If showCustomMsg =False Then";
if (_showcustommsg==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 393;BA.debugLine="Log(\"ad recived\")";
anywheresoftware.b4a.keywords.Common.LogImpl("224772611","ad recived",0);
 //BA.debugLineNum = 394;BA.debugLine="banner.RemoveView";
mostCurrent._banner.RemoveView();
 //BA.debugLineNum = 395;BA.debugLine="bannerheight = 250dip";
_bannerheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250));
 //BA.debugLineNum = 396;BA.debugLine="pn.RemoveView";
mostCurrent._pn.RemoveView();
 //BA.debugLineNum = 397;BA.debugLine="mainscv.Panel.AddView(banner,(100%x-300dip)/2,ba";
mostCurrent._mainscv.getPanel().AddView((android.view.View)(mostCurrent._banner.getObject()),(int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)))/(double)2),_bannertop,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)),_bannerheight);
 //BA.debugLineNum = 399;BA.debugLine="mainscv.Panel.AddView(pn,10dip,bannerheight+bann";
mostCurrent._mainscv.getPanel().AddView((android.view.View)(mostCurrent._pn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_bannerheight+_bannertop+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_mipnheingt);
 //BA.debugLineNum = 400;BA.debugLine="Dim scvheiht As Int";
_scvheiht = 0;
 //BA.debugLineNum = 401;BA.debugLine="scvheiht  = mipnheingt+250dip+bannertop";
_scvheiht = (int) (_mipnheingt+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250))+_bannertop);
 //BA.debugLineNum = 402;BA.debugLine="mainscv.Panel.Height=scvheiht+100dip";
mostCurrent._mainscv.getPanel().setHeight((int) (_scvheiht+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100))));
 };
 //BA.debugLineNum = 404;BA.debugLine="isAdReceive=True";
_isadreceive = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 405;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.CSBuilder  _buttoncsb(boolean _home,String _text,boolean _iconic) throws Exception{
anywheresoftware.b4a.objects.CSBuilder _csb = null;
 //BA.debugLineNum = 980;BA.debugLine="Sub buttoncsb(home As Boolean,text As String,iconi";
 //BA.debugLineNum = 981;BA.debugLine="Dim csb As CSBuilder";
_csb = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 982;BA.debugLine="csb.Initialize";
_csb.Initialize();
 //BA.debugLineNum = 983;BA.debugLine="If home = True Then";
if (_home==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 985;BA.debugLine="csb.Color(Colors.Green).Typeface(Typeface.DEFAUL";
_csb.Color(anywheresoftware.b4a.keywords.Common.Colors.Green).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD).Size((int) (20)).Append(BA.ObjectToCharSequence(_text)).PopAll();
 }else {
 //BA.debugLineNum = 987;BA.debugLine="If iconic = True Then";
if (_iconic==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 988;BA.debugLine="If text  = Chr(0xF06B) Then";
if ((_text).equals(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf06b))))) { 
 //BA.debugLineNum = 989;BA.debugLine="csb.Color(0xFFDBDBDB).Typeface(Typeface.FONTAW";
_csb.Color(((int)0xffdbdbdb)).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME()).Size((int) (35)).Append(BA.ObjectToCharSequence(_text)).PopAll();
 }else {
 //BA.debugLineNum = 991;BA.debugLine="csb.Color(0xFFDBDBDB).Typeface(Typeface.FONTAW";
_csb.Color(((int)0xffdbdbdb)).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME()).Size((int) (20)).Append(BA.ObjectToCharSequence(_text)).PopAll();
 };
 }else {
 //BA.debugLineNum = 996;BA.debugLine="csb.Color(0xFFDBDBDB).Typeface(Typeface.DEFAULT";
_csb.Color(((int)0xffdbdbdb)).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD).Size((int) (20)).Append(BA.ObjectToCharSequence(_text)).PopAll();
 };
 };
 //BA.debugLineNum = 1001;BA.debugLine="Return csb";
if (true) return _csb;
 //BA.debugLineNum = 1002;BA.debugLine="End Sub";
return null;
}
public static void  _change() throws Exception{
ResumableSub_change rsub = new ResumableSub_change(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_change extends BA.ResumableSub {
public ResumableSub_change(com.burma.royal2d.main parent) {
this.parent = parent;
}
com.burma.royal2d.main parent;
anywheresoftware.b4a.objects.collections.JSONParser _json = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _m = null;
int step7;
int limit7;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
try {

        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 886;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 31;
this.catchState = 30;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 30;
 //BA.debugLineNum = 887;BA.debugLine="If File.Exists(File.DirInternal,\"live\") Then";
if (true) break;

case 4:
//if
this.state = 28;
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"live")) { 
this.state = 6;
}else {
this.state = 27;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 888;BA.debugLine="Dim json As JSONParser";
_json = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 889;BA.debugLine="json.Initialize(File.ReadString(File.DirInterna";
_json.Initialize(anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"live"));
 //BA.debugLineNum = 890;BA.debugLine="Dim ls As List = json.NextArray";
_ls = new anywheresoftware.b4a.objects.collections.List();
_ls = _json.NextArray();
 //BA.debugLineNum = 892;BA.debugLine="If ls.Size>0 Then";
if (true) break;

case 7:
//if
this.state = 22;
if (_ls.getSize()>0) { 
this.state = 9;
}else {
this.state = 21;
}if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 893;BA.debugLine="For i = 0 To ls.Size-1";
if (true) break;

case 10:
//for
this.state = 19;
step7 = 1;
limit7 = (int) (_ls.getSize()-1);
_i = (int) (0) ;
this.state = 32;
if (true) break;

case 32:
//C
this.state = 19;
if ((step7 > 0 && _i <= limit7) || (step7 < 0 && _i >= limit7)) this.state = 12;
if (true) break;

case 33:
//C
this.state = 32;
_i = ((int)(0 + _i + step7)) ;
if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 894;BA.debugLine="Dim m As Map = ls.Get(i)";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_ls.Get(_i)));
 //BA.debugLineNum = 895;BA.debugLine="If m.Get(\"status\") = \"On\" Then";
if (true) break;

case 13:
//if
this.state = 18;
if ((_m.Get((Object)("status"))).equals((Object)("On"))) { 
this.state = 15;
}else if((_m.Get((Object)("status"))).equals((Object)("OFF"))) { 
this.state = 17;
}if (true) break;

case 15:
//C
this.state = 18;
 //BA.debugLineNum = 896;BA.debugLine="StrokeCSBuilder(timelb,timecsbuilder(Chr(0xF";
_strokecsbuilder(parent.mostCurrent._timelb,_timecsbuilder(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf1da))),BA.ObjectToString(_m.Get((Object)("updatetime")))),anywheresoftware.b4a.keywords.Common.Colors.Black,anywheresoftware.b4a.keywords.Common.Colors.White,(float) (5));
 //BA.debugLineNum = 898;BA.debugLine="changelean = True";
parent._changelean = anywheresoftware.b4a.keywords.Common.True;
 if (true) break;

case 17:
//C
this.state = 18;
 //BA.debugLineNum = 900;BA.debugLine="StrokeCSBuilder(timelb,timecsbuilder(Chr(0xF";
_strokecsbuilder(parent.mostCurrent._timelb,_timecsbuilder(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf00c))),BA.ObjectToString(_m.Get((Object)("updatetime")))),anywheresoftware.b4a.keywords.Common.Colors.Black,anywheresoftware.b4a.keywords.Common.Colors.White,(float) (5));
 //BA.debugLineNum = 903;BA.debugLine="changelean=False";
parent._changelean = anywheresoftware.b4a.keywords.Common.False;
 if (true) break;

case 18:
//C
this.state = 33;
;
 //BA.debugLineNum = 908;BA.debugLine="livelb.Text= m.Get(\"live\")";
parent.mostCurrent._livelb.setText(BA.ObjectToCharSequence(_m.Get((Object)("live"))));
 //BA.debugLineNum = 910;BA.debugLine="morningresultlb.Text = resultscs(m.Get(\"mset\"";
parent.mostCurrent._morningresultlb.setText(BA.ObjectToCharSequence(_resultscs(BA.ObjectToString(_m.Get((Object)("mset"))),BA.ObjectToString(_m.Get((Object)("mvalue"))),BA.ObjectToString(_m.Get((Object)("mresult")))).getObject()));
 //BA.debugLineNum = 912;BA.debugLine="eveningresultlb.Text = resultscs(m.Get(LiveUt";
parent.mostCurrent._eveningresultlb.setText(BA.ObjectToCharSequence(_resultscs(BA.ObjectToString(_m.Get((Object)(parent.mostCurrent._liveutils._change /*String*/ (mostCurrent.activityBA,"430set")))),BA.ObjectToString(_m.Get((Object)(parent.mostCurrent._liveutils._change /*String*/ (mostCurrent.activityBA,"430value")))),BA.ObjectToString(_m.Get((Object)(parent.mostCurrent._liveutils._change /*String*/ (mostCurrent.activityBA,"430"))))).getObject()));
 //BA.debugLineNum = 913;BA.debugLine="nmodernlb.Text = mycode.moderninernetcs1(m.Ge";
parent.mostCurrent._nmodernlb.setText(BA.ObjectToCharSequence(parent.mostCurrent._mycode._moderninernetcs1 /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,BA.ObjectToString(_m.Get((Object)(parent.mostCurrent._liveutils._change /*String*/ (mostCurrent.activityBA,"930modern"))))).getObject()));
 //BA.debugLineNum = 914;BA.debugLine="ninternetlb.Text = mycode.moderninernetcs1(m.";
parent.mostCurrent._ninternetlb.setText(BA.ObjectToCharSequence(parent.mostCurrent._mycode._moderninernetcs1 /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,BA.ObjectToString(_m.Get((Object)(parent.mostCurrent._liveutils._change /*String*/ (mostCurrent.activityBA,"930internet"))))).getObject()));
 //BA.debugLineNum = 915;BA.debugLine="tmodernlb.Text = mycode.moderninernetcs1(m.Ge";
parent.mostCurrent._tmodernlb.setText(BA.ObjectToCharSequence(parent.mostCurrent._mycode._moderninernetcs1 /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,BA.ObjectToString(_m.Get((Object)(parent.mostCurrent._liveutils._change /*String*/ (mostCurrent.activityBA,"200modern"))))).getObject()));
 //BA.debugLineNum = 916;BA.debugLine="tinternetlb.Text = mycode.moderninernetcs1(m.";
parent.mostCurrent._tinternetlb.setText(BA.ObjectToCharSequence(parent.mostCurrent._mycode._moderninernetcs1 /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,BA.ObjectToString(_m.Get((Object)(parent.mostCurrent._liveutils._change /*String*/ (mostCurrent.activityBA,"200internet"))))).getObject()));
 if (true) break;
if (true) break;

case 19:
//C
this.state = 22;
;
 if (true) break;

case 21:
//C
this.state = 22;
 //BA.debugLineNum = 921;BA.debugLine="Sleep(1000)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (1000));
this.state = 34;
return;
case 34:
//C
this.state = 22;
;
 //BA.debugLineNum = 923;BA.debugLine="livelb.Text= \"--\"";
parent.mostCurrent._livelb.setText(BA.ObjectToCharSequence("--"));
 //BA.debugLineNum = 924;BA.debugLine="morningresultlb.Text = resultscs(\"----.--\",\"--";
parent.mostCurrent._morningresultlb.setText(BA.ObjectToCharSequence(_resultscs("----.--","----.--","--").getObject()));
 //BA.debugLineNum = 925;BA.debugLine="eveningresultlb.Text = resultscs(\"----.--\",\"--";
parent.mostCurrent._eveningresultlb.setText(BA.ObjectToCharSequence(_resultscs("----.--","----.--","--").getObject()));
 //BA.debugLineNum = 926;BA.debugLine="nmodernlb.Text = mycode.moderninernetcs1(\"---\"";
parent.mostCurrent._nmodernlb.setText(BA.ObjectToCharSequence(parent.mostCurrent._mycode._moderninernetcs1 /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"---").getObject()));
 //BA.debugLineNum = 927;BA.debugLine="ninternetlb.Text = mycode.moderninernetcs1(\"--";
parent.mostCurrent._ninternetlb.setText(BA.ObjectToCharSequence(parent.mostCurrent._mycode._moderninernetcs1 /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"---").getObject()));
 //BA.debugLineNum = 928;BA.debugLine="tmodernlb.Text = mycode.moderninernetcs1(\"---\"";
parent.mostCurrent._tmodernlb.setText(BA.ObjectToCharSequence(parent.mostCurrent._mycode._moderninernetcs1 /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"---").getObject()));
 //BA.debugLineNum = 929;BA.debugLine="tinternetlb.Text = mycode.moderninernetcs1(\"--";
parent.mostCurrent._tinternetlb.setText(BA.ObjectToCharSequence(parent.mostCurrent._mycode._moderninernetcs1 /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"---").getObject()));
 if (true) break;
;
 //BA.debugLineNum = 932;BA.debugLine="If changetimer.IsInitialized Then";

case 22:
//if
this.state = 25;
if (parent._changetimer.IsInitialized()) { 
this.state = 24;
}if (true) break;

case 24:
//C
this.state = 25;
 //BA.debugLineNum = 933;BA.debugLine="changetimer.Enabled=True";
parent._changetimer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 25:
//C
this.state = 28;
;
 if (true) break;

case 27:
//C
this.state = 28;
 if (true) break;

case 28:
//C
this.state = 31;
;
 if (true) break;

case 30:
//C
this.state = 31;
this.catchState = 0;
 //BA.debugLineNum = 939;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("226083383",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 31:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 941;BA.debugLine="End Sub";
if (true) break;
}} 
       catch (Exception e0) {
			
if (catchState == 0)
    throw e0;
else {
    state = catchState;
processBA.setLastException(e0);}
            }
        }
    }
}
public static void  _changetimer_tick() throws Exception{
ResumableSub_changetimer_tick rsub = new ResumableSub_changetimer_tick(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_changetimer_tick extends BA.ResumableSub {
public ResumableSub_changetimer_tick(com.burma.royal2d.main parent) {
this.parent = parent;
}
com.burma.royal2d.main parent;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 597;BA.debugLine="DateTime.DateFormat=\"yyyy/MM/dd\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy/MM/dd");
 //BA.debugLineNum = 598;BA.debugLine="If livelb.IsInitialized Then";
if (true) break;

case 1:
//if
this.state = 24;
if (parent.mostCurrent._livelb.IsInitialized()) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 599;BA.debugLine="If changelean =True Then";
if (true) break;

case 4:
//if
this.state = 23;
if (parent._changelean==anywheresoftware.b4a.keywords.Common.True) { 
this.state = 6;
}else {
this.state = 22;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 600;BA.debugLine="If DateTime.TimeParse(\"09:20:00\") <(DateTime.No";
if (true) break;

case 7:
//if
this.state = 20;
if (anywheresoftware.b4a.keywords.Common.DateTime.TimeParse("09:20:00")<(anywheresoftware.b4a.keywords.Common.DateTime.getNow()) && anywheresoftware.b4a.keywords.Common.DateTime.TimeParse("12:01:00")>(anywheresoftware.b4a.keywords.Common.DateTime.getNow())) { 
this.state = 9;
}else if(anywheresoftware.b4a.keywords.Common.DateTime.TimeParse("13:50:00")<(anywheresoftware.b4a.keywords.Common.DateTime.getNow()) && anywheresoftware.b4a.keywords.Common.DateTime.TimeParse("16:30:00")>(anywheresoftware.b4a.keywords.Common.DateTime.getNow())) { 
this.state = 15;
}if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 601;BA.debugLine="If DateTime.Now  - sse.lastime >30000 Then";
if (true) break;

case 10:
//if
this.state = 13;
if (anywheresoftware.b4a.keywords.Common.DateTime.getNow()-parent._sse._getlastime /*long*/ ()>30000) { 
this.state = 12;
}if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 602;BA.debugLine="Connect";
_connect();
 if (true) break;

case 13:
//C
this.state = 20;
;
 //BA.debugLineNum = 604;BA.debugLine="ltw.PlayAnimation";
parent.mostCurrent._ltw.PlayAnimation();
 //BA.debugLineNum = 605;BA.debugLine="livelb.Text=\"  \"";
parent.mostCurrent._livelb.setText(BA.ObjectToCharSequence("  "));
 //BA.debugLineNum = 606;BA.debugLine="morningresultlb.Text = resultscs(\"     \",\"";
parent.mostCurrent._morningresultlb.setText(BA.ObjectToCharSequence(_resultscs("     ","     ","--").getObject()));
 //BA.debugLineNum = 607;BA.debugLine="Sleep(500)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (500));
this.state = 25;
return;
case 25:
//C
this.state = 20;
;
 if (true) break;

case 15:
//C
this.state = 16;
 //BA.debugLineNum = 609;BA.debugLine="If DateTime.Now  - sse.lastime >30000 Then";
if (true) break;

case 16:
//if
this.state = 19;
if (anywheresoftware.b4a.keywords.Common.DateTime.getNow()-parent._sse._getlastime /*long*/ ()>30000) { 
this.state = 18;
}if (true) break;

case 18:
//C
this.state = 19;
 //BA.debugLineNum = 610;BA.debugLine="Connect";
_connect();
 if (true) break;

case 19:
//C
this.state = 20;
;
 //BA.debugLineNum = 612;BA.debugLine="ltw.PlayAnimation";
parent.mostCurrent._ltw.PlayAnimation();
 //BA.debugLineNum = 613;BA.debugLine="livelb.Text=\"  \"";
parent.mostCurrent._livelb.setText(BA.ObjectToCharSequence("  "));
 //BA.debugLineNum = 614;BA.debugLine="eveningresultlb.Text = resultscs(\"     \",\"";
parent.mostCurrent._eveningresultlb.setText(BA.ObjectToCharSequence(_resultscs("     ","     ","--").getObject()));
 //BA.debugLineNum = 615;BA.debugLine="Sleep(500)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (500));
this.state = 26;
return;
case 26:
//C
this.state = 20;
;
 if (true) break;

case 20:
//C
this.state = 23;
;
 if (true) break;

case 22:
//C
this.state = 23;
 //BA.debugLineNum = 619;BA.debugLine="ltw.StopAnimation";
parent.mostCurrent._ltw.StopAnimation();
 if (true) break;

case 23:
//C
this.state = 24;
;
 if (true) break;

case 24:
//C
this.state = -1;
;
 //BA.debugLineNum = 624;BA.debugLine="changetimer.Enabled=False";
parent._changetimer.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 625;BA.debugLine="change";
_change();
 //BA.debugLineNum = 626;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _chatbtn_click() throws Exception{
 //BA.debugLineNum = 1014;BA.debugLine="Sub chatbtn_click";
 //BA.debugLineNum = 1015;BA.debugLine="If inter.Ready Then";
if (mostCurrent._inter.getReady()) { 
 //BA.debugLineNum = 1016;BA.debugLine="inter.Show";
mostCurrent._inter.Show(mostCurrent.activityBA);
 //BA.debugLineNum = 1017;BA.debugLine="nextactivity = publicChat";
_nextactivity = _publicchat;
 }else {
 //BA.debugLineNum = 1019;BA.debugLine="StartActivity(public_chat)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._public_chat.getObject()));
 };
 //BA.debugLineNum = 1022;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.keywords.Common.ResumableSubWrapper  _checkandrequestnotificationpermission() throws Exception{
ResumableSub_CheckAndRequestNotificationPermission rsub = new ResumableSub_CheckAndRequestNotificationPermission(null);
rsub.resume(processBA, null);
return (anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.Common.ResumableSubWrapper(), rsub);
}
public static class ResumableSub_CheckAndRequestNotificationPermission extends BA.ResumableSub {
public ResumableSub_CheckAndRequestNotificationPermission(com.burma.royal2d.main parent) {
this.parent = parent;
}
com.burma.royal2d.main parent;
anywheresoftware.b4a.phone.Phone _p = null;
anywheresoftware.b4j.object.JavaObject _ctxt = null;
int _targetsdkversion = 0;
anywheresoftware.b4j.object.JavaObject _notificationsmanager = null;
boolean _notificationsenabled = false;
anywheresoftware.b4a.objects.RuntimePermissions _rp = null;
String _permission = "";
boolean _result = false;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
{
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,null);return;}
case 0:
//C
this.state = 1;
 //BA.debugLineNum = 1094;BA.debugLine="Dim p As Phone";
_p = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 1095;BA.debugLine="If p.SdkVersion < 33 Then Return True";
if (true) break;

case 1:
//if
this.state = 6;
if (_p.getSdkVersion()<33) { 
this.state = 3;
;}if (true) break;

case 3:
//C
this.state = 6;
if (true) {
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,(Object)(anywheresoftware.b4a.keywords.Common.True));return;};
if (true) break;

case 6:
//C
this.state = 7;
;
 //BA.debugLineNum = 1096;BA.debugLine="Dim ctxt As JavaObject";
_ctxt = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 1097;BA.debugLine="ctxt.InitializeContext";
_ctxt.InitializeContext(processBA);
 //BA.debugLineNum = 1098;BA.debugLine="Dim targetSdkVersion As Int = ctxt.RunMethodJO(\"g";
_targetsdkversion = (int)(BA.ObjectToNumber(_ctxt.RunMethodJO("getApplicationInfo",(Object[])(anywheresoftware.b4a.keywords.Common.Null)).GetField("targetSdkVersion")));
 //BA.debugLineNum = 1099;BA.debugLine="If targetSdkVersion < 33 Then Return True";
if (true) break;

case 7:
//if
this.state = 12;
if (_targetsdkversion<33) { 
this.state = 9;
;}if (true) break;

case 9:
//C
this.state = 12;
if (true) {
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,(Object)(anywheresoftware.b4a.keywords.Common.True));return;};
if (true) break;

case 12:
//C
this.state = 13;
;
 //BA.debugLineNum = 1100;BA.debugLine="Dim NotificationsManager As JavaObject = ctxt.Run";
_notificationsmanager = new anywheresoftware.b4j.object.JavaObject();
_notificationsmanager = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_ctxt.RunMethod("getSystemService",new Object[]{(Object)("notification")})));
 //BA.debugLineNum = 1101;BA.debugLine="Dim NotificationsEnabled As Boolean = Notificatio";
_notificationsenabled = BA.ObjectToBoolean(_notificationsmanager.RunMethod("areNotificationsEnabled",(Object[])(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 1102;BA.debugLine="If NotificationsEnabled Then Return True";
if (true) break;

case 13:
//if
this.state = 18;
if (_notificationsenabled) { 
this.state = 15;
;}if (true) break;

case 15:
//C
this.state = 18;
if (true) {
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,(Object)(anywheresoftware.b4a.keywords.Common.True));return;};
if (true) break;

case 18:
//C
this.state = -1;
;
 //BA.debugLineNum = 1103;BA.debugLine="Dim rp As RuntimePermissions";
_rp = new anywheresoftware.b4a.objects.RuntimePermissions();
 //BA.debugLineNum = 1104;BA.debugLine="rp.CheckAndRequest(rp.PERMISSION_POST_NOTIFICATIO";
_rp.CheckAndRequest(processBA,_rp.PERMISSION_POST_NOTIFICATIONS);
 //BA.debugLineNum = 1105;BA.debugLine="Wait For Activity_PermissionResult (Permission As";
anywheresoftware.b4a.keywords.Common.WaitFor("activity_permissionresult", processBA, this, null);
this.state = 19;
return;
case 19:
//C
this.state = -1;
_permission = (String) result[0];
_result = (Boolean) result[1];
;
 //BA.debugLineNum = 1106;BA.debugLine="Log(Permission & \": \" & Result)";
anywheresoftware.b4a.keywords.Common.LogImpl("226935309",_permission+": "+BA.ObjectToString(_result),0);
 //BA.debugLineNum = 1107;BA.debugLine="Return Result";
if (true) {
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,(Object)(_result));return;};
 //BA.debugLineNum = 1108;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _checkinfo() throws Exception{
com.burma.royal2d.httpjob _job = null;
 //BA.debugLineNum = 1155;BA.debugLine="Sub checkinfo";
 //BA.debugLineNum = 1156;BA.debugLine="Dim Job As HttpJob";
_job = new com.burma.royal2d.httpjob();
 //BA.debugLineNum = 1157;BA.debugLine="Job.Initialize(inforeader,Me)";
_job._initialize /*String*/ (processBA,_inforeader,main.getObject());
 //BA.debugLineNum = 1158;BA.debugLine="Job.Download(site&\"read.php?name=info.txt\")";
_job._download /*String*/ (_site+"read.php?name=info.txt");
 //BA.debugLineNum = 1160;BA.debugLine="End Sub";
return "";
}
public static String  _connect() throws Exception{
 //BA.debugLineNum = 435;BA.debugLine="Sub Connect";
 //BA.debugLineNum = 436;BA.debugLine="If sse.IsInitialized Then";
if (_sse.IsInitialized /*boolean*/ ()) { 
 //BA.debugLineNum = 437;BA.debugLine="sse.Finish";
_sse._finish /*String*/ ();
 //BA.debugLineNum = 438;BA.debugLine="sse.Connect(newsite&\"live\")";
_sse._connect /*String*/ (_newsite+"live");
 }else {
 //BA.debugLineNum = 440;BA.debugLine="sse.Initialize(Me,\"live\",\"live\",Application.Pack";
_sse._initialize /*String*/ (processBA,main.getObject(),"live","live",anywheresoftware.b4a.keywords.Common.Application.getPackageName());
 //BA.debugLineNum = 441;BA.debugLine="sse.Connect(newsite&\"live\")";
_sse._connect /*String*/ (_newsite+"live");
 };
 //BA.debugLineNum = 443;BA.debugLine="End Sub";
return "";
}
public static String  _create_menu(Object _menu) throws Exception{
 //BA.debugLineNum = 521;BA.debugLine="Sub Create_Menu (Menu As Object)";
 //BA.debugLineNum = 523;BA.debugLine="End Sub";
return "";
}
public static String  _custommsg(String _t,String _b,String _link) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
anywheresoftware.b4a.objects.PanelWrapper _base = null;
anywheresoftware.b4a.objects.LabelWrapper _titlelb = null;
anywheresoftware.b4a.objects.LabelWrapper _bodylb = null;
anywheresoftware.b4a.objects.ButtonWrapper _ybtn = null;
anywheresoftware.b4a.objects.ButtonWrapper _nbtn = null;
int _top = 0;
int _w = 0;
 //BA.debugLineNum = 670;BA.debugLine="Sub customMsg(t As String,b As String,link As Stri";
 //BA.debugLineNum = 671;BA.debugLine="showCustomMsg =True";
_showcustommsg = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 672;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 673;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 674;BA.debugLine="p.Elevation=5dip";
_p.setElevation((float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 //BA.debugLineNum = 675;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 676;BA.debugLine="cd.Initialize(Colors.White,10dip)";
_cd.Initialize(anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 677;BA.debugLine="p.Background=cd";
_p.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 678;BA.debugLine="Dim base As Panel";
_base = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 679;BA.debugLine="base.Initialize(\"\")";
_base.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 680;BA.debugLine="Dim titlelb As Label";
_titlelb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 681;BA.debugLine="Dim bodylb As Label";
_bodylb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 682;BA.debugLine="titlelb = lbbb(Typeface.CreateNew(mycode.mmfont,T";
_titlelb = _lbbb((anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.CreateNew((android.graphics.Typeface)(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()),anywheresoftware.b4a.keywords.Common.Typeface.STYLE_BOLD))),anywheresoftware.b4a.keywords.Common.Colors.Black,_t,(int) (10));
 //BA.debugLineNum = 683;BA.debugLine="Dim ybtn As Button";
_ybtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 684;BA.debugLine="Dim nbtn As Button";
_nbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 685;BA.debugLine="ybtn.Initialize(\"ybtn\")";
_ybtn.Initialize(mostCurrent.activityBA,"ybtn");
 //BA.debugLineNum = 686;BA.debugLine="ybtn.Background = mycode.btnbgdynamic(mycode.navi";
_ybtn.setBackground((android.graphics.drawable.Drawable)(mostCurrent._mycode._btnbgdynamic /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,mostCurrent._mycode._navicolor /*int*/ ,anywheresoftware.b4a.keywords.Common.Colors.Gray,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))).getObject()));
 //BA.debugLineNum = 687;BA.debugLine="ybtn.Text = \"Yes\"";
_ybtn.setText(BA.ObjectToCharSequence("Yes"));
 //BA.debugLineNum = 688;BA.debugLine="ybtn.Tag=link";
_ybtn.setTag((Object)(_link));
 //BA.debugLineNum = 689;BA.debugLine="nbtn.Initialize(\"nbtn\")";
_nbtn.Initialize(mostCurrent.activityBA,"nbtn");
 //BA.debugLineNum = 690;BA.debugLine="nbtn.Background=mycode.btnbgdynamic(mycode.naviCo";
_nbtn.setBackground((android.graphics.drawable.Drawable)(mostCurrent._mycode._btnbgdynamic /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,mostCurrent._mycode._navicolor /*int*/ ,anywheresoftware.b4a.keywords.Common.Colors.Gray,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))).getObject()));
 //BA.debugLineNum = 691;BA.debugLine="nbtn.Text = \"No\"";
_nbtn.setText(BA.ObjectToCharSequence("No"));
 //BA.debugLineNum = 692;BA.debugLine="ybtn.TextColor=Colors.White";
_ybtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 693;BA.debugLine="nbtn.TextColor=Colors.White";
_nbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 694;BA.debugLine="ybtn.TextSize = mycode.textsize(7)";
_ybtn.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (7)));
 //BA.debugLineNum = 695;BA.debugLine="nbtn.TextSize = mycode.textsize(7)";
_nbtn.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (7)));
 //BA.debugLineNum = 696;BA.debugLine="nbtn.Tag=base";
_nbtn.setTag((Object)(_base.getObject()));
 //BA.debugLineNum = 697;BA.debugLine="Activity.AddView(base,0,0,100%x,mycode.ActivityHe";
mostCurrent._activity.AddView((android.view.View)(_base.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),mostCurrent._mycode._activityheight /*int*/ );
 //BA.debugLineNum = 698;BA.debugLine="base. AddView(p,15dip,10dip,100%x-30dip,30dip)";
_base.AddView((android.view.View)(_p.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 699;BA.debugLine="p.AddView(titlelb,10dip,10dip,p.Width-20dip,title";
_p.AddView((android.view.View)(_titlelb.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_p.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_titlelb.getHeight());
 //BA.debugLineNum = 700;BA.debugLine="Dim top As Int = titlelb.Height +titlelb.Top+15di";
_top = (int) (_titlelb.getHeight()+_titlelb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15)));
 //BA.debugLineNum = 701;BA.debugLine="bodylb = lbbb(mycode.mmfont,Colors.Black,b,9)";
_bodylb = _lbbb(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ ,anywheresoftware.b4a.keywords.Common.Colors.Black,_b,(int) (9));
 //BA.debugLineNum = 702;BA.debugLine="p.AddView(bodylb,10dip,top,p.Width-20dip,bodylb.H";
_p.AddView((android.view.View)(_bodylb.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_top,(int) (_p.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_bodylb.getHeight());
 //BA.debugLineNum = 703;BA.debugLine="bodylb.Gravity=Gravity.CENTER";
_bodylb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 704;BA.debugLine="top = bodylb.Height +bodylb.Top+20dip";
_top = (int) (_bodylb.getHeight()+_bodylb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 705;BA.debugLine="Dim w As Int = (p.Width-30dip)/2";
_w = (int) ((_p.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)))/(double)2);
 //BA.debugLineNum = 706;BA.debugLine="p.AddView(nbtn,10dip,top,w,40dip)";
_p.AddView((android.view.View)(_nbtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_top,_w,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 707;BA.debugLine="p.AddView(ybtn,nbtn.Width+nbtn.Left+10dip,top,w,4";
_p.AddView((android.view.View)(_ybtn.getObject()),(int) (_nbtn.getWidth()+_nbtn.getLeft()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),_top,_w,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 708;BA.debugLine="p.Height =ybtn.Height+ybtn.Top+10dip";
_p.setHeight((int) (_ybtn.getHeight()+_ybtn.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 709;BA.debugLine="p.Top=(mycode.ActivityHeight-p.Height)/2";
_p.setTop((int) ((mostCurrent._mycode._activityheight /*int*/ -_p.getHeight())/(double)2));
 //BA.debugLineNum = 710;BA.debugLine="End Sub";
return "";
}
public static String  _customsgbase_click() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
 //BA.debugLineNum = 739;BA.debugLine="Sub customsgbase_click";
 //BA.debugLineNum = 740;BA.debugLine="showCustomMsg =False";
_showcustommsg = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 741;BA.debugLine="Dim p As Panel = Sender";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 742;BA.debugLine="p.Visible=False";
_p.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 743;BA.debugLine="p.RemoveView";
_p.RemoveView();
 //BA.debugLineNum = 744;BA.debugLine="End Sub";
return "";
}
public static String  _getinfo(String _info) throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _json = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _m = null;
int _a = 0;
anywheresoftware.b4a.objects.PanelWrapper _pp = null;
com.burma.royal2d.httpjob _job = null;
 //BA.debugLineNum = 637;BA.debugLine="Sub getinfo(info As String)";
 //BA.debugLineNum = 639;BA.debugLine="Try";
try { //BA.debugLineNum = 640;BA.debugLine="Dim json As JSONParser";
_json = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 641;BA.debugLine="json.Initialize(info)";
_json.Initialize(_info);
 //BA.debugLineNum = 642;BA.debugLine="Dim ls As List = json.NextArray";
_ls = new anywheresoftware.b4a.objects.collections.List();
_ls = _json.NextArray();
 //BA.debugLineNum = 643;BA.debugLine="If ls.Size >0 Then";
if (_ls.getSize()>0) { 
 //BA.debugLineNum = 644;BA.debugLine="For i = 0 To ls.Size -1";
{
final int step6 = 1;
final int limit6 = (int) (_ls.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit6 ;_i = _i + step6 ) {
 //BA.debugLineNum = 645;BA.debugLine="Dim m As Map = ls.Get(i)";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_ls.Get(_i)));
 //BA.debugLineNum = 646;BA.debugLine="Dim  a As Int = m.Get(\"version\")";
_a = (int)(BA.ObjectToNumber(_m.Get((Object)("version"))));
 //BA.debugLineNum = 647;BA.debugLine="If a > Application.VersionCode Then";
if (_a>anywheresoftware.b4a.keywords.Common.Application.getVersionCode()) { 
 //BA.debugLineNum = 648;BA.debugLine="updateMsg(m.Get(\"upfun\"))";
_updatemsg(BA.ObjectToString(_m.Get((Object)("upfun"))));
 }else {
 //BA.debugLineNum = 650;BA.debugLine="progressHide";
_progresshide();
 //BA.debugLineNum = 651;BA.debugLine="Dim pp As Panel = navigationbar";
_pp = new anywheresoftware.b4a.objects.PanelWrapper();
_pp = _navigationbar();
 //BA.debugLineNum = 652;BA.debugLine="pp.Elevation=10dip";
_pp.setElevation((float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 };
 //BA.debugLineNum = 654;BA.debugLine="If m.Get(\"inappMsg\") = \"true\" Then";
if ((_m.Get((Object)("inappMsg"))).equals((Object)("true"))) { 
 //BA.debugLineNum = 655;BA.debugLine="If splashscreen.Visible=False Then";
if (mostCurrent._splashscreen.getVisible()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 656;BA.debugLine="Log(\"show message box\")";
anywheresoftware.b4a.keywords.Common.LogImpl("225427987","show message box",0);
 //BA.debugLineNum = 657;BA.debugLine="customMsg(m.Get(\"title\"),m.Get(\"body\"),m.Get";
_custommsg(BA.ObjectToString(_m.Get((Object)("title"))),BA.ObjectToString(_m.Get((Object)("body"))),BA.ObjectToString(_m.Get((Object)("link"))));
 };
 };
 }
};
 };
 } 
       catch (Exception e25) {
			processBA.setLastException(e25); //BA.debugLineNum = 663;BA.debugLine="Dim job As HttpJob";
_job = new com.burma.royal2d.httpjob();
 //BA.debugLineNum = 664;BA.debugLine="job.Initialize(inforeader,Starter)";
_job._initialize /*String*/ (processBA,_inforeader,(Object)(mostCurrent._starter.getObject()));
 //BA.debugLineNum = 665;BA.debugLine="job.Download(site&\"read.php?name=info.txt\")";
_job._download /*String*/ (_site+"read.php?name=info.txt");
 //BA.debugLineNum = 666;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("225427997",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 668;BA.debugLine="End Sub";
return "";
}
public static String  _giftbtn_click() throws Exception{
 //BA.debugLineNum = 1047;BA.debugLine="Sub giftbtn_Click";
 //BA.debugLineNum = 1048;BA.debugLine="StartActivity(gift_activity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._gift_activity.getObject()));
 //BA.debugLineNum = 1049;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 59;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 61;BA.debugLine="Dim xui As XUI";
mostCurrent._xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 62;BA.debugLine="Dim settingbtn As Button";
mostCurrent._settingbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 63;BA.debugLine="Dim AXrLottie As AXrLottie";
mostCurrent._axrlottie = new com.aghajari.axrlottie.AXrLottie();
 //BA.debugLineNum = 64;BA.debugLine="Dim LottieView As AXrLottieImageView";
mostCurrent._lottieview = new com.aghajari.axrlottie.AXrLottieImageView();
 //BA.debugLineNum = 65;BA.debugLine="Dim logo As ImageView";
mostCurrent._logo = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 67;BA.debugLine="Dim ads As AdsHelper";
mostCurrent._ads = new com.burma.royal2d.adshelper();
 //BA.debugLineNum = 68;BA.debugLine="Dim pn As Panel";
mostCurrent._pn = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 69;BA.debugLine="Dim banner As AdView";
mostCurrent._banner = new anywheresoftware.b4a.admobwrapper.AdViewWrapper();
 //BA.debugLineNum = 70;BA.debugLine="Dim versionlb As Label";
mostCurrent._versionlb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 71;BA.debugLine="Dim morningresultlb,eveningresultlb As Label";
mostCurrent._morningresultlb = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._eveningresultlb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 72;BA.debugLine="Dim barpn,livepn As Panel";
mostCurrent._barpn = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._livepn = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 73;BA.debugLine="Dim livelb As Label";
mostCurrent._livelb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 74;BA.debugLine="Dim timelb As Label";
mostCurrent._timelb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 75;BA.debugLine="Dim morningresultpn,eveningresultpn As Panel";
mostCurrent._morningresultpn = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._eveningresultpn = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 76;BA.debugLine="Dim mdevider,edevider As Panel";
mostCurrent._mdevider = new anywheresoftware.b4a.objects.PanelWrapper();
mostCurrent._edevider = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 77;BA.debugLine="Dim mtimelb,etimelb As Label";
mostCurrent._mtimelb = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._etimelb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 78;BA.debugLine="Dim splashscreen As Panel";
mostCurrent._splashscreen = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 79;BA.debugLine="Dim mainscv As ScrollView";
mostCurrent._mainscv = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 80;BA.debugLine="Dim inter As InterstitialAd";
mostCurrent._inter = new anywheresoftware.b4a.admobwrapper.AdViewWrapper.InterstitialAdWrapper();
 //BA.debugLineNum = 81;BA.debugLine="Dim chatbtn As Button";
mostCurrent._chatbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 82;BA.debugLine="Dim ltw As AXrLottieImageView";
mostCurrent._ltw = new com.aghajari.axrlottie.AXrLottieImageView();
 //BA.debugLineNum = 83;BA.debugLine="Dim lottobtn As Button";
mostCurrent._lottobtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 84;BA.debugLine="Dim nmodernlb,ninternetlb,tmodernlb,tinternetlb A";
mostCurrent._nmodernlb = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._ninternetlb = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._tmodernlb = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._tinternetlb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 85;BA.debugLine="End Sub";
return "";
}
public static String  _histroybtn_click() throws Exception{
 //BA.debugLineNum = 1024;BA.debugLine="Sub histroybtn_click";
 //BA.debugLineNum = 1025;BA.debugLine="StartActivity(history)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._history.getObject()));
 //BA.debugLineNum = 1033;BA.debugLine="End Sub";
return "";
}
public static String  _inter_adclosed() throws Exception{
 //BA.debugLineNum = 1060;BA.debugLine="Sub inter_AdClosed";
 //BA.debugLineNum = 1062;BA.debugLine="End Sub";
return "";
}
public static String  _inter_adopened() throws Exception{
 //BA.debugLineNum = 1064;BA.debugLine="Sub inter_AdOpened";
 //BA.debugLineNum = 1065;BA.debugLine="Select nextactivity";
switch (BA.switchObjectToInt(_nextactivity,_histroyact,_threedact,_publicchat,_lottoscoiety,_fututerpaper)) {
case 0: {
 //BA.debugLineNum = 1067;BA.debugLine="StartActivity(history)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._history.getObject()));
 break; }
case 1: {
 //BA.debugLineNum = 1069;BA.debugLine="StartActivity(threedact)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_threedact));
 break; }
case 2: {
 //BA.debugLineNum = 1071;BA.debugLine="StartActivity(public_chat)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._public_chat.getObject()));
 break; }
case 3: {
 //BA.debugLineNum = 1073;BA.debugLine="StartActivity(lottosociety)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._lottosociety.getObject()));
 break; }
case 4: {
 //BA.debugLineNum = 1075;BA.debugLine="StartActivity(Future_Tips)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._future_tips.getObject()));
 break; }
}
;
 //BA.debugLineNum = 1078;BA.debugLine="End Sub";
return "";
}
public static String  _inter_failedtoreceivead(String _errorcode) throws Exception{
 //BA.debugLineNum = 1055;BA.debugLine="Sub inter_FailedToReceiveAd (ErrorCode As String)";
 //BA.debugLineNum = 1056;BA.debugLine="adErrorCode=ErrorCode";
_aderrorcode = _errorcode;
 //BA.debugLineNum = 1057;BA.debugLine="Log(\"Failed: \" & ErrorCode)";
anywheresoftware.b4a.keywords.Common.LogImpl("226673154","Failed: "+_errorcode,0);
 //BA.debugLineNum = 1058;BA.debugLine="End Sub";
return "";
}
public static String  _inter_receivead() throws Exception{
 //BA.debugLineNum = 1051;BA.debugLine="Sub inter_ReceiveAd";
 //BA.debugLineNum = 1052;BA.debugLine="adErrorCode = \"got\"";
_aderrorcode = "got";
 //BA.debugLineNum = 1053;BA.debugLine="End Sub";
return "";
}
public static String  _jobdone(com.burma.royal2d.httpjob _job) throws Exception{
 //BA.debugLineNum = 1162;BA.debugLine="Sub JobDone (job As HttpJob)";
 //BA.debugLineNum = 1163;BA.debugLine="Try";
try { //BA.debugLineNum = 1164;BA.debugLine="If job.Success Then";
if (_job._success /*boolean*/ ) { 
 //BA.debugLineNum = 1166;BA.debugLine="File.WriteString(File.DirInternal,\"info\",job.G";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"info",_job._getstring /*String*/ ());
 //BA.debugLineNum = 1167;BA.debugLine="CallSubDelayed2(Me,\"getinfo\",File.ReadString(F";
anywheresoftware.b4a.keywords.Common.CallSubDelayed2(processBA,main.getObject(),"getinfo",(Object)(anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"info")));
 //BA.debugLineNum = 1168;BA.debugLine="job.Release";
_job._release /*String*/ ();
 }else {
 //BA.debugLineNum = 1170;BA.debugLine="Log(\"fail retry\")";
anywheresoftware.b4a.keywords.Common.LogImpl("227262984","fail retry",0);
 //BA.debugLineNum = 1171;BA.debugLine="checkinfo";
_checkinfo();
 };
 } 
       catch (Exception e11) {
			processBA.setLastException(e11); //BA.debugLineNum = 1175;BA.debugLine="checkinfo";
_checkinfo();
 };
 //BA.debugLineNum = 1177;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.LabelWrapper  _lbbb(anywheresoftware.b4a.keywords.constants.TypefaceWrapper _tp,int _color,String _text,int _size) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lb = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
 //BA.debugLineNum = 746;BA.debugLine="Sub lbbb(tp As Typeface,color As Int,text As Strin";
 //BA.debugLineNum = 747;BA.debugLine="Dim lb As Label";
_lb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 748;BA.debugLine="lb.Initialize(\"\")";
_lb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 749;BA.debugLine="lb.Typeface = tp";
_lb.setTypeface((android.graphics.Typeface)(_tp.getObject()));
 //BA.debugLineNum = 750;BA.debugLine="lb.TextColor=color";
_lb.setTextColor(_color);
 //BA.debugLineNum = 751;BA.debugLine="lb.Text= text";
_lb.setText(BA.ObjectToCharSequence(_text));
 //BA.debugLineNum = 752;BA.debugLine="lb.Width = 100%x-40dip";
_lb.setWidth((int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))));
 //BA.debugLineNum = 753;BA.debugLine="lb.TextSize = mycode.textsize (size)";
_lb.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,_size));
 //BA.debugLineNum = 754;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 755;BA.debugLine="lb.Height = su.MeasureMultilineTextHeight(lb,text";
_lb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_lb.getObject()),BA.ObjectToCharSequence(_text)));
 //BA.debugLineNum = 756;BA.debugLine="Return lb";
if (true) return _lb;
 //BA.debugLineNum = 757;BA.debugLine="End Sub";
return null;
}
public static String  _lottobtn_click() throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _json = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 807;BA.debugLine="Sub lottobtn_click";
 //BA.debugLineNum = 808;BA.debugLine="Try";
try { //BA.debugLineNum = 809;BA.debugLine="Dim json As JSONParser";
_json = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 810;BA.debugLine="json.Initialize(File.ReadString(File.DirInternal";
_json.Initialize(anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"info"));
 //BA.debugLineNum = 811;BA.debugLine="Dim ls As List = json.NextArray";
_ls = new anywheresoftware.b4a.objects.collections.List();
_ls = _json.NextArray();
 //BA.debugLineNum = 813;BA.debugLine="Dim m As Map = ls.Get(0)";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_ls.Get((int) (0))));
 //BA.debugLineNum = 814;BA.debugLine="If adErrorCode = \"0\" And adErrorCode <> \"\" Then";
if ((_aderrorcode).equals("0") && (_aderrorcode).equals("") == false) { 
 //BA.debugLineNum = 815;BA.debugLine="Log(\"code : 0\")";
anywheresoftware.b4a.keywords.Common.LogImpl("225952264","code : 0",0);
 //BA.debugLineNum = 816;BA.debugLine="If m.Get(\"require\") = \"true\" Then";
if ((_m.Get((Object)("require"))).equals((Object)("true"))) { 
 //BA.debugLineNum = 817;BA.debugLine="Log(\"errorcode : 0\")";
anywheresoftware.b4a.keywords.Common.LogImpl("225952266","errorcode : 0",0);
 //BA.debugLineNum = 818;BA.debugLine="ToastMessageShow(\"⚠️ကွန်ရက်ချိတ်ဆက်မှု မအောင်မြ";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("⚠️ကွန်ရက်ချိတ်ဆက်မှု မအောင်မြင်ပါ"),anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 822;BA.debugLine="If inter.Ready Then";
if (mostCurrent._inter.getReady()) { 
 //BA.debugLineNum = 823;BA.debugLine="inter.Show";
mostCurrent._inter.Show(mostCurrent.activityBA);
 //BA.debugLineNum = 824;BA.debugLine="nextactivity = lottoscoiety";
_nextactivity = _lottoscoiety;
 }else {
 //BA.debugLineNum = 826;BA.debugLine="StartActivity(lottosociety)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._lottosociety.getObject()));
 };
 };
 }else {
 //BA.debugLineNum = 831;BA.debugLine="If inter.Ready Then";
if (mostCurrent._inter.getReady()) { 
 //BA.debugLineNum = 832;BA.debugLine="inter.Show";
mostCurrent._inter.Show(mostCurrent.activityBA);
 //BA.debugLineNum = 833;BA.debugLine="nextactivity = lottoscoiety";
_nextactivity = _lottoscoiety;
 }else {
 //BA.debugLineNum = 835;BA.debugLine="StartActivity(lottosociety)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._lottosociety.getObject()));
 };
 };
 } 
       catch (Exception e28) {
			processBA.setLastException(e28); //BA.debugLineNum = 840;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("225952289",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 842;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.PanelWrapper  _mornetnumpn(String _time) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.LabelWrapper _tmlb = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
int _tmw = 0;
 //BA.debugLineNum = 325;BA.debugLine="Sub MornetNumpn(time  As String) As Panel";
 //BA.debugLineNum = 326;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 327;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 328;BA.debugLine="p.Width = 100%x-40dip";
_p.setWidth((int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))));
 //BA.debugLineNum = 329;BA.debugLine="Dim tmlb As Label";
_tmlb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 330;BA.debugLine="tmlb.Initialize(\"\")";
_tmlb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 331;BA.debugLine="tmlb.Text=mycode.mitimecs1(time)";
_tmlb.setText(BA.ObjectToCharSequence(mostCurrent._mycode._mitimecs1 /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,_time).getObject()));
 //BA.debugLineNum = 332;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 333;BA.debugLine="Dim tmw As Int = p.Width /2";
_tmw = (int) (_p.getWidth()/(double)2);
 //BA.debugLineNum = 334;BA.debugLine="p.AddView(tmlb,0,0,tmw,20dip)";
_p.AddView((android.view.View)(_tmlb.getObject()),(int) (0),(int) (0),_tmw,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 335;BA.debugLine="tmlb.Gravity=Gravity.CENTER_VERTICAL";
_tmlb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
 //BA.debugLineNum = 336;BA.debugLine="If time.Contains(\"9\") Then";
if (_time.contains("9")) { 
 //BA.debugLineNum = 337;BA.debugLine="nmodernlb.Initialize(\"\")";
mostCurrent._nmodernlb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 338;BA.debugLine="ninternetlb.Initialize(\"\")";
mostCurrent._ninternetlb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 339;BA.debugLine="nmodernlb.Text= mycode.moderninernetcs1(\"***\")";
mostCurrent._nmodernlb.setText(BA.ObjectToCharSequence(mostCurrent._mycode._moderninernetcs1 /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"***").getObject()));
 //BA.debugLineNum = 340;BA.debugLine="nmodernlb.Gravity=Gravity.CENTER";
mostCurrent._nmodernlb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 341;BA.debugLine="ninternetlb.Text= mycode.moderninernetcs1(\"***\")";
mostCurrent._ninternetlb.setText(BA.ObjectToCharSequence(mostCurrent._mycode._moderninernetcs1 /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"***").getObject()));
 //BA.debugLineNum = 342;BA.debugLine="ninternetlb.Gravity=Gravity.CENTER";
mostCurrent._ninternetlb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 343;BA.debugLine="p.AddView(nmodernlb,tmw,0,tmw/2,20dip)";
_p.AddView((android.view.View)(mostCurrent._nmodernlb.getObject()),_tmw,(int) (0),(int) (_tmw/(double)2),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 344;BA.debugLine="p.AddView(ninternetlb,nmodernlb.Width+nmodernlb.";
_p.AddView((android.view.View)(mostCurrent._ninternetlb.getObject()),(int) (mostCurrent._nmodernlb.getWidth()+mostCurrent._nmodernlb.getLeft()),(int) (0),(int) (_tmw/(double)2),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 345;BA.debugLine="nmodernlb.Height = su.MeasureMultilineTextHeight";
mostCurrent._nmodernlb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(mostCurrent._nmodernlb.getObject()),BA.ObjectToCharSequence(mostCurrent._mycode._moderninernetcs1 /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"***").getObject())));
 //BA.debugLineNum = 346;BA.debugLine="ninternetlb.Height = nmodernlb.Height";
mostCurrent._ninternetlb.setHeight(mostCurrent._nmodernlb.getHeight());
 //BA.debugLineNum = 347;BA.debugLine="tmlb.Height = nmodernlb.Height";
_tmlb.setHeight(mostCurrent._nmodernlb.getHeight());
 //BA.debugLineNum = 348;BA.debugLine="p.Height =tmlb.Height";
_p.setHeight(_tmlb.getHeight());
 }else {
 //BA.debugLineNum = 350;BA.debugLine="tmodernlb.Initialize(\"\")";
mostCurrent._tmodernlb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 351;BA.debugLine="tinternetlb.Initialize(\"\")";
mostCurrent._tinternetlb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 352;BA.debugLine="tmodernlb.Text=mycode.moderninernetcs1(\"***\")";
mostCurrent._tmodernlb.setText(BA.ObjectToCharSequence(mostCurrent._mycode._moderninernetcs1 /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"***").getObject()));
 //BA.debugLineNum = 353;BA.debugLine="tmodernlb.Gravity=Gravity.CENTER";
mostCurrent._tmodernlb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 354;BA.debugLine="tinternetlb.Text=mycode.moderninernetcs1(\"***\")";
mostCurrent._tinternetlb.setText(BA.ObjectToCharSequence(mostCurrent._mycode._moderninernetcs1 /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"***").getObject()));
 //BA.debugLineNum = 355;BA.debugLine="tinternetlb.Gravity=Gravity.CENTER";
mostCurrent._tinternetlb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 356;BA.debugLine="p.AddView(tmodernlb,tmw,0,tmw/2,20dip)";
_p.AddView((android.view.View)(mostCurrent._tmodernlb.getObject()),_tmw,(int) (0),(int) (_tmw/(double)2),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 357;BA.debugLine="p.AddView(tinternetlb,tmodernlb.Width+tmodernlb.";
_p.AddView((android.view.View)(mostCurrent._tinternetlb.getObject()),(int) (mostCurrent._tmodernlb.getWidth()+mostCurrent._tmodernlb.getLeft()),(int) (0),(int) (_tmw/(double)2),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 358;BA.debugLine="tmodernlb.Height = su.MeasureMultilineTextHeight";
mostCurrent._tmodernlb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(mostCurrent._tmodernlb.getObject()),BA.ObjectToCharSequence(mostCurrent._mycode._moderninernetcs1 /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"***").getObject())));
 //BA.debugLineNum = 359;BA.debugLine="tinternetlb.Height = tmodernlb.Height";
mostCurrent._tinternetlb.setHeight(mostCurrent._tmodernlb.getHeight());
 //BA.debugLineNum = 360;BA.debugLine="tmlb.Height = tmodernlb.Height";
_tmlb.setHeight(mostCurrent._tmodernlb.getHeight());
 //BA.debugLineNum = 361;BA.debugLine="p.Height =tmlb.Height";
_p.setHeight(_tmlb.getHeight());
 };
 //BA.debugLineNum = 363;BA.debugLine="Return p";
if (true) return _p;
 //BA.debugLineNum = 364;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.PanelWrapper  _mornetpn() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _npn = null;
int _top = 0;
anywheresoftware.b4a.objects.PanelWrapper _tpn = null;
 //BA.debugLineNum = 306;BA.debugLine="Sub MornetPn As Panel";
 //BA.debugLineNum = 307;BA.debugLine="If pn.IsInitialized Then";
if (mostCurrent._pn.IsInitialized()) { 
 }else {
 //BA.debugLineNum = 309;BA.debugLine="pn.Initialize(\"\")";
mostCurrent._pn.Initialize(mostCurrent.activityBA,"");
 };
 //BA.debugLineNum = 311;BA.debugLine="pn.Background=mycode.btnbg2";
mostCurrent._pn.setBackground((android.graphics.drawable.Drawable)(mostCurrent._mycode._btnbg2 /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 312;BA.debugLine="Dim npn As Panel = MornetNumpn(\"9:30 AM\")";
_npn = new anywheresoftware.b4a.objects.PanelWrapper();
_npn = _mornetnumpn("9:30 AM");
 //BA.debugLineNum = 314;BA.debugLine="Dim top As Int = 10dip";
_top = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10));
 //BA.debugLineNum = 315;BA.debugLine="pn.AddView(npn,10dip,top,100%x-40dip,npn.Height)";
mostCurrent._pn.AddView((android.view.View)(_npn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_top,(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))),_npn.getHeight());
 //BA.debugLineNum = 316;BA.debugLine="top = top +npn.Height+7dip";
_top = (int) (_top+_npn.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (7)));
 //BA.debugLineNum = 317;BA.debugLine="Dim tpn As Panel  = MornetNumpn(\"2:00 PM\")";
_tpn = new anywheresoftware.b4a.objects.PanelWrapper();
_tpn = _mornetnumpn("2:00 PM");
 //BA.debugLineNum = 318;BA.debugLine="pn.AddView(tpn,10dip,top,100%x-40dip,tpn.Height)";
mostCurrent._pn.AddView((android.view.View)(_tpn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_top,(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))),_tpn.getHeight());
 //BA.debugLineNum = 319;BA.debugLine="top = top +tpn.Height";
_top = (int) (_top+_tpn.getHeight());
 //BA.debugLineNum = 320;BA.debugLine="pn.Height =top+10dip";
mostCurrent._pn.setHeight((int) (_top+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 321;BA.debugLine="mipnheingt = pn.Height";
_mipnheingt = mostCurrent._pn.getHeight();
 //BA.debugLineNum = 322;BA.debugLine="Return pn";
if (true) return mostCurrent._pn;
 //BA.debugLineNum = 323;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.PanelWrapper  _navigationbar() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _navigatepanel = null;
anywheresoftware.b4a.objects.ButtonWrapper _historybtn = null;
anywheresoftware.b4a.objects.ButtonWrapper _giftbtn = null;
anywheresoftware.b4a.objects.ButtonWrapper _threedbtn = null;
int _w = 0;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
 //BA.debugLineNum = 944;BA.debugLine="Sub navigationbar As Panel";
 //BA.debugLineNum = 945;BA.debugLine="Dim navigatepanel As Panel";
_navigatepanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 946;BA.debugLine="Dim historybtn As Button";
_historybtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 947;BA.debugLine="Dim giftbtn As Button";
_giftbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 948;BA.debugLine="Dim threedbtn As Button";
_threedbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 949;BA.debugLine="Dim chatbtn As Button";
mostCurrent._chatbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 950;BA.debugLine="navigatepanel.Initialize(\"\")";
_navigatepanel.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 952;BA.debugLine="historybtn.Initialize(\"histroybtn\")";
_historybtn.Initialize(mostCurrent.activityBA,"histroybtn");
 //BA.debugLineNum = 953;BA.debugLine="giftbtn.Initialize(\"giftbtn\")";
_giftbtn.Initialize(mostCurrent.activityBA,"giftbtn");
 //BA.debugLineNum = 954;BA.debugLine="threedbtn.Initialize(\"threedbtn\")";
_threedbtn.Initialize(mostCurrent.activityBA,"threedbtn");
 //BA.debugLineNum = 955;BA.debugLine="chatbtn.Initialize(\"paperbtn\")";
mostCurrent._chatbtn.Initialize(mostCurrent.activityBA,"paperbtn");
 //BA.debugLineNum = 956;BA.debugLine="chatbtn.Text= buttoncsb(False,Chr(0xF1EA),True)";
mostCurrent._chatbtn.setText(BA.ObjectToCharSequence(_buttoncsb(anywheresoftware.b4a.keywords.Common.False,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf1ea))),anywheresoftware.b4a.keywords.Common.True).getObject()));
 //BA.debugLineNum = 957;BA.debugLine="chatbtn.Background=mycode.btnbg(False)";
mostCurrent._chatbtn.setBackground((android.graphics.drawable.Drawable)(mostCurrent._mycode._btnbg /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False).getObject()));
 //BA.debugLineNum = 958;BA.debugLine="Dim w As Int";
_w = 0;
 //BA.debugLineNum = 959;BA.debugLine="w = (100%x-60dip)/5";
_w = (int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60)))/(double)5);
 //BA.debugLineNum = 960;BA.debugLine="navigatepanel.AddView(giftbtn,10dip,5dip,w*2,w)";
_navigatepanel.AddView((android.view.View)(_giftbtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),(int) (_w*2),_w);
 //BA.debugLineNum = 961;BA.debugLine="navigatepanel.AddView(historybtn,giftbtn.Width+gi";
_navigatepanel.AddView((android.view.View)(_historybtn.getObject()),(int) (_giftbtn.getWidth()+_giftbtn.getLeft()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),_w,_w);
 //BA.debugLineNum = 963;BA.debugLine="navigatepanel.AddView(threedbtn,historybtn.Width+";
_navigatepanel.AddView((android.view.View)(_threedbtn.getObject()),(int) (_historybtn.getWidth()+_historybtn.getLeft()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),_w,_w);
 //BA.debugLineNum = 964;BA.debugLine="navigatepanel.AddView(chatbtn,threedbtn.Width+thr";
_navigatepanel.AddView((android.view.View)(mostCurrent._chatbtn.getObject()),(int) (_threedbtn.getWidth()+_threedbtn.getLeft()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),_w,_w);
 //BA.debugLineNum = 965;BA.debugLine="historybtn.Text= buttoncsb(False,Chr(0xF073),True";
_historybtn.setText(BA.ObjectToCharSequence(_buttoncsb(anywheresoftware.b4a.keywords.Common.False,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf073))),anywheresoftware.b4a.keywords.Common.True).getObject()));
 //BA.debugLineNum = 966;BA.debugLine="historybtn.Background=mycode.btnbg(False)";
_historybtn.setBackground((android.graphics.drawable.Drawable)(mostCurrent._mycode._btnbg /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False).getObject()));
 //BA.debugLineNum = 968;BA.debugLine="giftbtn.Color=Colors.Transparent";
_giftbtn.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 969;BA.debugLine="giftbtn.SetBackgroundImage(LoadBitmap(File.DirAss";
_giftbtn.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"giftbtn.webp").getObject()));
 //BA.debugLineNum = 970;BA.debugLine="threedbtn.Text=buttoncsb(False,\"3D\",False)";
_threedbtn.setText(BA.ObjectToCharSequence(_buttoncsb(anywheresoftware.b4a.keywords.Common.False,"3D",anywheresoftware.b4a.keywords.Common.False).getObject()));
 //BA.debugLineNum = 971;BA.debugLine="threedbtn.Background=mycode.btnbg(False)";
_threedbtn.setBackground((android.graphics.drawable.Drawable)(mostCurrent._mycode._btnbg /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False).getObject()));
 //BA.debugLineNum = 973;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 974;BA.debugLine="cd.Initialize(mycode.naviColor,25dip)";
_cd.Initialize(mostCurrent._mycode._navicolor /*int*/ ,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (25)));
 //BA.debugLineNum = 975;BA.debugLine="navigatepanel.Background=cd";
_navigatepanel.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 976;BA.debugLine="navpanelheight=w +10dip";
_navpanelheight = (int) (_w+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 977;BA.debugLine="Return navigatepanel";
if (true) return _navigatepanel;
 //BA.debugLineNum = 978;BA.debugLine="End Sub";
return null;
}
public static String  _nbtn_click() throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
int _scvheiht = 0;
 //BA.debugLineNum = 718;BA.debugLine="Sub nbtn_click";
 //BA.debugLineNum = 719;BA.debugLine="showCustomMsg =False";
_showcustommsg = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 720;BA.debugLine="Dim b As Button = Sender";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 721;BA.debugLine="Dim p As Panel =b.Tag";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_b.getTag()));
 //BA.debugLineNum = 722;BA.debugLine="p.Visible=False";
_p.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 723;BA.debugLine="p.RemoveView";
_p.RemoveView();
 //BA.debugLineNum = 724;BA.debugLine="Log(\"ad recived\")";
anywheresoftware.b4a.keywords.Common.LogImpl("225624582","ad recived",0);
 //BA.debugLineNum = 725;BA.debugLine="If isAdReceive = True Then";
if (_isadreceive==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 726;BA.debugLine="banner.RemoveView";
mostCurrent._banner.RemoveView();
 //BA.debugLineNum = 727;BA.debugLine="bannerheight = 250dip";
_bannerheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250));
 //BA.debugLineNum = 728;BA.debugLine="pn.RemoveView";
mostCurrent._pn.RemoveView();
 //BA.debugLineNum = 729;BA.debugLine="mainscv.Panel.AddView(banner,(100%x-300dip)/2,ba";
mostCurrent._mainscv.getPanel().AddView((android.view.View)(mostCurrent._banner.getObject()),(int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)))/(double)2),_bannertop,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)),_bannerheight);
 //BA.debugLineNum = 731;BA.debugLine="mainscv.Panel.AddView(pn,10dip,bannerheight+bann";
mostCurrent._mainscv.getPanel().AddView((android.view.View)(mostCurrent._pn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_bannerheight+_bannertop+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_mipnheingt);
 //BA.debugLineNum = 732;BA.debugLine="Dim scvheiht As Int";
_scvheiht = 0;
 //BA.debugLineNum = 733;BA.debugLine="scvheiht  = mipnheingt+250dip+bannertop";
_scvheiht = (int) (_mipnheingt+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (250))+_bannertop);
 //BA.debugLineNum = 734;BA.debugLine="mainscv.Panel.Height=scvheiht+100dip";
mostCurrent._mainscv.getPanel().setHeight((int) (_scvheiht+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100))));
 };
 //BA.debugLineNum = 737;BA.debugLine="End Sub";
return "";
}
public static String  _notupdate_click(Object _tag) throws Exception{
 //BA.debugLineNum = 799;BA.debugLine="Sub notupdate_click(tag As Object)";
 //BA.debugLineNum = 800;BA.debugLine="splashscreen.RemoveView";
mostCurrent._splashscreen.RemoveView();
 //BA.debugLineNum = 801;BA.debugLine="splashscreen.Visible=False";
mostCurrent._splashscreen.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 802;BA.debugLine="End Sub";
return "";
}
public static String  _paperbtn_click() throws Exception{
 //BA.debugLineNum = 1004;BA.debugLine="Sub paperbtn_click";
 //BA.debugLineNum = 1005;BA.debugLine="If inter.Ready Then";
if (mostCurrent._inter.getReady()) { 
 //BA.debugLineNum = 1006;BA.debugLine="inter.Show";
mostCurrent._inter.Show(mostCurrent.activityBA);
 //BA.debugLineNum = 1007;BA.debugLine="nextactivity = fututerpaper";
_nextactivity = _fututerpaper;
 }else {
 //BA.debugLineNum = 1009;BA.debugLine="StartActivity(Future_Tips)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._future_tips.getObject()));
 };
 //BA.debugLineNum = 1012;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
report_details._process_globals();
mycode._process_globals();
public_chat._process_globals();
gift_imageview._process_globals();
settings._process_globals();
profile_activity._process_globals();
guideline._process_globals();
login._process_globals();
future_tips._process_globals();
history._process_globals();
apicall._process_globals();
firebasemessaging._process_globals();
liveutils._process_globals();
lottohistory._process_globals();
lottosociety._process_globals();
privacy_policy._process_globals();
setholidays._process_globals();
starter._process_globals();
store._process_globals();
threed._process_globals();
gift_activity._process_globals();
b4xpages._process_globals();
b4xcollections._process_globals();
httputils2service._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 20;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 21;BA.debugLine="Dim fututerpaper As String = \"futurepaper\"";
_fututerpaper = "futurepaper";
 //BA.debugLineNum = 22;BA.debugLine="Dim adErrorCode As String";
_aderrorcode = "";
 //BA.debugLineNum = 26;BA.debugLine="Dim bannerUnit As String=\"ca-app-pub-386784115735";
_bannerunit = "ca-app-pub-3867841157350558/8640750180";
 //BA.debugLineNum = 27;BA.debugLine="Dim interUnit As String = \"ca-app-pub-38678411573";
_interunit = "ca-app-pub-3867841157350558/8756385975";
 //BA.debugLineNum = 28;BA.debugLine="Dim rwinterUnit As String= \"ca-app-pub-3867841157";
_rwinterunit = "ca-app-pub-3867841157350558/8544392708";
 //BA.debugLineNum = 29;BA.debugLine="Dim smallbannerunit As String = \"ca-app-pub-38678";
_smallbannerunit = "ca-app-pub-3867841157350558/4004567803";
 //BA.debugLineNum = 30;BA.debugLine="Dim sse As sseconnector";
_sse = new com.burma.royal2d.sseconnector();
 //BA.debugLineNum = 31;BA.debugLine="Dim newsite As String = \"https://app.shwemyanmar2";
_newsite = "https://app.shwemyanmar2d.us/";
 //BA.debugLineNum = 32;BA.debugLine="Dim keypresscount As Int";
_keypresscount = 0;
 //BA.debugLineNum = 33;BA.debugLine="Dim dwe As AXrLottieDrawableBuilder";
_dwe = new com.aghajari.axrlottie.AXrLottieDrawableBuilder();
 //BA.debugLineNum = 34;BA.debugLine="Dim mipnheingt As Int";
_mipnheingt = 0;
 //BA.debugLineNum = 35;BA.debugLine="Dim bannerheight As Int";
_bannerheight = 0;
 //BA.debugLineNum = 36;BA.debugLine="Dim bannertop As Int";
_bannertop = 0;
 //BA.debugLineNum = 37;BA.debugLine="Dim lottoscoiety As String = \"lottosociety\"";
_lottoscoiety = "lottosociety";
 //BA.debugLineNum = 38;BA.debugLine="Dim histroyact As String =\"history\"";
_histroyact = "history";
 //BA.debugLineNum = 39;BA.debugLine="Dim threedact As String = \"threed\"";
_threedact = "threed";
 //BA.debugLineNum = 40;BA.debugLine="Dim publicChat As String = \"publichat\"";
_publicchat = "publichat";
 //BA.debugLineNum = 41;BA.debugLine="Dim changetimer As Timer";
_changetimer = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 42;BA.debugLine="Dim site As String = \"https://shwemyanmar2d.us/\"";
_site = "https://shwemyanmar2d.us/";
 //BA.debugLineNum = 43;BA.debugLine="Dim inforeader As String = \"inforeader\"";
_inforeader = "inforeader";
 //BA.debugLineNum = 44;BA.debugLine="Dim livereader As String = \"livereader\"";
_livereader = "livereader";
 //BA.debugLineNum = 45;BA.debugLine="Dim changelean As Boolean = False";
_changelean = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 46;BA.debugLine="Dim changelean As Boolean = False";
_changelean = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 47;BA.debugLine="Dim isAdReceive As Boolean = False";
_isadreceive = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 48;BA.debugLine="Dim nextactivity As String";
_nextactivity = "";
 //BA.debugLineNum = 49;BA.debugLine="Dim isCLosed As Boolean";
_isclosed = false;
 //BA.debugLineNum = 50;BA.debugLine="Dim navpanelheight As Int";
_navpanelheight = 0;
 //BA.debugLineNum = 51;BA.debugLine="Dim fbi As FirebaseAnalytics";
_fbi = new anywheresoftware.b4a.objects.FirebaseAnalyticsWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Dim isCall As Boolean";
_iscall = false;
 //BA.debugLineNum = 53;BA.debugLine="Dim showCustomMsg As Boolean =False";
_showcustommsg = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 55;BA.debugLine="Dim phone As Phone";
_phone = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 57;BA.debugLine="End Sub";
return "";
}
public static String  _progresshide() throws Exception{
 //BA.debugLineNum = 1149;BA.debugLine="Sub progressHide";
 //BA.debugLineNum = 1150;BA.debugLine="splashscreen.Visible=False";
mostCurrent._splashscreen.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1151;BA.debugLine="End Sub";
return "";
}
public static void  _progressshow() throws Exception{
ResumableSub_progressshow rsub = new ResumableSub_progressshow(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_progressshow extends BA.ResumableSub {
public ResumableSub_progressshow(com.burma.royal2d.main parent) {
this.parent = parent;
}
com.burma.royal2d.main parent;
int _tlogo = 0;
int _llogo = 0;
anywheresoftware.b4a.objects.StringUtils _su = null;
int _left = 0;
com.aghajari.axrlottie.AXrLottieDrawableBuilder _drawable = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
 //BA.debugLineNum = 1115;BA.debugLine="splashscreen.Initialize(\"splash\")";
parent.mostCurrent._splashscreen.Initialize(mostCurrent.activityBA,"splash");
 //BA.debugLineNum = 1116;BA.debugLine="splashscreen.Color=mycode.naviColor";
parent.mostCurrent._splashscreen.setColor(parent.mostCurrent._mycode._navicolor /*int*/ );
 //BA.debugLineNum = 1117;BA.debugLine="Activity.AddView(splashscreen,0,0,100%x,mycode.Ac";
parent.mostCurrent._activity.AddView((android.view.View)(parent.mostCurrent._splashscreen.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),parent.mostCurrent._mycode._activityheight /*int*/ );
 //BA.debugLineNum = 1118;BA.debugLine="versionlb.Initialize(\"\")";
parent.mostCurrent._versionlb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 1120;BA.debugLine="splashscreen.AddView(versionlb,0,mycode.ActivityH";
parent.mostCurrent._splashscreen.AddView((android.view.View)(parent.mostCurrent._versionlb.getObject()),(int) (0),(int) (parent.mostCurrent._mycode._activityheight /*int*/ -anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50))),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 1121;BA.debugLine="versionlb.Text=Application.VersionName";
parent.mostCurrent._versionlb.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Application.getVersionName()));
 //BA.debugLineNum = 1122;BA.debugLine="versionlb.TextColor=Colors.White";
parent.mostCurrent._versionlb.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 1123;BA.debugLine="versionlb.Gravity=Gravity.CENTER";
parent.mostCurrent._versionlb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 1124;BA.debugLine="Dim tlogo As Int = (mycode.ActivityHeight-50dip)/";
_tlogo = (int) ((parent.mostCurrent._mycode._activityheight /*int*/ -anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)))/(double)2);
 //BA.debugLineNum = 1125;BA.debugLine="Dim llogo As Int = (100%x-50dip)/2";
_llogo = (int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)))/(double)2);
 //BA.debugLineNum = 1126;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 1127;BA.debugLine="versionlb.Top=(mycode.ActivityHeight-su.MeasureMu";
parent.mostCurrent._versionlb.setTop((int) ((parent.mostCurrent._mycode._activityheight /*int*/ -_su.MeasureMultilineTextHeight((android.widget.TextView)(parent.mostCurrent._versionlb.getObject()),BA.ObjectToCharSequence(parent.mostCurrent._versionlb.getText())))));
 //BA.debugLineNum = 1128;BA.debugLine="versionlb.Top=versionlb.Top-10dip";
parent.mostCurrent._versionlb.setTop((int) (parent.mostCurrent._versionlb.getTop()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 1130;BA.debugLine="AXrLottie.Initialize";
parent.mostCurrent._axrlottie.Initialize();
 //BA.debugLineNum = 1131;BA.debugLine="LottieView.Initialize(\"\")";
parent.mostCurrent._lottieview.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 1132;BA.debugLine="Dim left As Int = (100%x-150dip)/2";
_left = (int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)))/(double)2);
 //BA.debugLineNum = 1133;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (0));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 //BA.debugLineNum = 1134;BA.debugLine="splashscreen.AddView(LottieView,left,mycode.Activ";
parent.mostCurrent._splashscreen.AddView((android.view.View)(parent.mostCurrent._lottieview.getObject()),_left,(int) (parent.mostCurrent._mycode._activityheight /*int*/ -anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (200))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)));
 //BA.debugLineNum = 1135;BA.debugLine="Dim Drawable As AXrLottieDrawableBuilder";
_drawable = new com.aghajari.axrlottie.AXrLottieDrawableBuilder();
 //BA.debugLineNum = 1136;BA.debugLine="Drawable.InitializeFromFile(File.DirAssets,\"peace";
_drawable.InitializeFromFile(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"peace.json").SetAutoRepeat(_drawable.AUTO_REPEAT_INFINITE).SetAutoStart(anywheresoftware.b4a.keywords.Common.True).SetCacheEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 1140;BA.debugLine="LottieView.SetLottieDrawable(Drawable.Build)";
parent.mostCurrent._lottieview.SetLottieDrawable((com.aghajari.rlottie.AXrLottieDrawable)(_drawable.Build().getObject()));
 //BA.debugLineNum = 1142;BA.debugLine="logo.Initialize(\"\")";
parent.mostCurrent._logo.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 1143;BA.debugLine="splashscreen.AddView(logo,llogo,tlogo,50dip,50dip";
parent.mostCurrent._splashscreen.AddView((android.view.View)(parent.mostCurrent._logo.getObject()),_llogo,_tlogo,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 1144;BA.debugLine="logo.SetBackgroundImage(LoadBitmap(File.DirAssets";
parent.mostCurrent._logo.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"burma2d_logo.webp").getObject()));
 //BA.debugLineNum = 1145;BA.debugLine="logo.Gravity=Gravity.FILL";
parent.mostCurrent._logo.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 1146;BA.debugLine="splashscreen.BringToFront";
parent.mostCurrent._splashscreen.BringToFront();
 //BA.debugLineNum = 1147;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static anywheresoftware.b4a.objects.CSBuilder  _resultscs(String _set,String _value,String _twod) throws Exception{
String _sett = "";
String _setresult = "";
String _valuel = "";
String _valuer = "";
String _valueresult = "";
anywheresoftware.b4a.objects.CSBuilder _cs = null;
anywheresoftware.b4a.objects.CSBuilder _cs1 = null;
anywheresoftware.b4a.objects.collections.List _twodl = null;
 //BA.debugLineNum = 536;BA.debugLine="Sub resultscs (set As String, value As String,twod";
 //BA.debugLineNum = 537;BA.debugLine="Dim sett As String";
_sett = "";
 //BA.debugLineNum = 538;BA.debugLine="Dim setresult As String";
_setresult = "";
 //BA.debugLineNum = 539;BA.debugLine="Dim valuel As String";
_valuel = "";
 //BA.debugLineNum = 540;BA.debugLine="Dim valuer As String";
_valuer = "";
 //BA.debugLineNum = 541;BA.debugLine="Dim valueresult As String";
_valueresult = "";
 //BA.debugLineNum = 543;BA.debugLine="If set.Length >0 And set.Contains(\".\") Then";
if (_set.length()>0 && _set.contains(".")) { 
 //BA.debugLineNum = 544;BA.debugLine="setresult = set.SubString(set.Length-1)";
_setresult = _set.substring((int) (_set.length()-1));
 //BA.debugLineNum = 545;BA.debugLine="sett  = set.SubString2(0,set.Length-1)";
_sett = _set.substring((int) (0),(int) (_set.length()-1));
 };
 //BA.debugLineNum = 547;BA.debugLine="If value.Length>0 And value.Contains(\".\") Then";
if (_value.length()>0 && _value.contains(".")) { 
 //BA.debugLineNum = 548;BA.debugLine="valuel = value.SubString(value.IndexOf(\".\"))";
_valuel = _value.substring(_value.indexOf("."));
 //BA.debugLineNum = 549;BA.debugLine="valuer = value.Replace(valuel,\"\")";
_valuer = _value.replace(_valuel,"");
 //BA.debugLineNum = 550;BA.debugLine="valueresult = valuer.SubString(valuer.Length-1)";
_valueresult = _valuer.substring((int) (_valuer.length()-1));
 //BA.debugLineNum = 551;BA.debugLine="valuer = valuer.SubString2(0,valuer.Length-1)";
_valuer = _valuer.substring((int) (0),(int) (_valuer.length()-1));
 };
 //BA.debugLineNum = 554;BA.debugLine="Dim  cs As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 555;BA.debugLine="cs.Initialize";
_cs.Initialize();
 //BA.debugLineNum = 556;BA.debugLine="Dim cs1 As CSBuilder";
_cs1 = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 557;BA.debugLine="cs1.Initialize";
_cs1.Initialize();
 //BA.debugLineNum = 558;BA.debugLine="If twod.Contains(\"=\") Then";
if (_twod.contains("=")) { 
 //BA.debugLineNum = 560;BA.debugLine="Dim twodl As List = Regex.Split(\"=\",twod)";
_twodl = new anywheresoftware.b4a.objects.collections.List();
_twodl = anywheresoftware.b4a.keywords.Common.ArrayToList(anywheresoftware.b4a.keywords.Common.Regex.Split("=",_twod));
 //BA.debugLineNum = 561;BA.debugLine="cs.Size(mycode.textsize(13)).Typeface(mycode.sem";
_cs.Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (13)))).Typeface((android.graphics.Typeface)(mostCurrent._mycode._semibold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Color(anywheresoftware.b4a.keywords.Common.Colors.LightGray).Append(BA.ObjectToCharSequence(_twodl.Get((int) (0)))).Pop();
 //BA.debugLineNum = 562;BA.debugLine="cs.Size(mycode.textsize(15)).Typeface(mycode.sem";
_cs.Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (15)))).Typeface((android.graphics.Typeface)(mostCurrent._mycode._semibold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Color(anywheresoftware.b4a.keywords.Common.Colors.DarkGray).Append(BA.ObjectToCharSequence(" / ")).Pop();
 //BA.debugLineNum = 564;BA.debugLine="cs.Size(mycode.textsize(15)).Typeface(mycode.sem";
_cs.Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (15)))).Typeface((android.graphics.Typeface)(mostCurrent._mycode._semibold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Color(anywheresoftware.b4a.keywords.Common.Colors.Yellow).Append(BA.ObjectToCharSequence(BA.ObjectToString(_twodl.Get((int) (1)))+anywheresoftware.b4a.keywords.Common.CRLF)).Pop();
 }else {
 //BA.debugLineNum = 566;BA.debugLine="cs.Size(mycode.textsize(15)).Typeface(mycode.sem";
_cs.Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (15)))).Typeface((android.graphics.Typeface)(mostCurrent._mycode._semibold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Color(anywheresoftware.b4a.keywords.Common.Colors.Yellow).Append(BA.ObjectToCharSequence(_twod+anywheresoftware.b4a.keywords.Common.CRLF)).Pop();
 };
 //BA.debugLineNum = 569;BA.debugLine="If set.Contains(\".\")  Then";
if (_set.contains(".")) { 
 //BA.debugLineNum = 570;BA.debugLine="cs.Size(mycode.textsize(9)).Typeface(mycode.ligh";
_cs.Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (9)))).Typeface((android.graphics.Typeface)(mostCurrent._mycode._lightfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(_sett)).Pop();
 //BA.debugLineNum = 571;BA.debugLine="cs.Size(mycode.textsize(9)).Typeface(mycode.ligh";
_cs.Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (9)))).Typeface((android.graphics.Typeface)(mostCurrent._mycode._lightfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Color(anywheresoftware.b4a.keywords.Common.Colors.Yellow).Append(BA.ObjectToCharSequence(_setresult+anywheresoftware.b4a.keywords.Common.CRLF)).Pop();
 //BA.debugLineNum = 573;BA.debugLine="cs.Size(mycode.textsize(9)).Typeface(mycode.ligh";
_cs.Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (9)))).Typeface((android.graphics.Typeface)(mostCurrent._mycode._lightfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(_valuer)).Pop();
 //BA.debugLineNum = 574;BA.debugLine="cs.Size(mycode.textsize(9)).Typeface(mycode.ligh";
_cs.Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (9)))).Typeface((android.graphics.Typeface)(mostCurrent._mycode._lightfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Color(anywheresoftware.b4a.keywords.Common.Colors.Yellow).Append(BA.ObjectToCharSequence(_valueresult)).Pop();
 //BA.debugLineNum = 575;BA.debugLine="cs.Size(mycode.textsize(9)).Typeface(mycode.ligh";
_cs.Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (9)))).Typeface((android.graphics.Typeface)(mostCurrent._mycode._lightfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(_valuel)).PopAll();
 }else {
 //BA.debugLineNum = 577;BA.debugLine="cs.Size(mycode.textsize(9)).Typeface(mycode.ligh";
_cs.Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (9)))).Typeface((android.graphics.Typeface)(mostCurrent._mycode._lightfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(_set)).Pop();
 //BA.debugLineNum = 578;BA.debugLine="cs.Size(mycode.textsize(9)).Typeface(mycode.ligh";
_cs.Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (9)))).Typeface((android.graphics.Typeface)(mostCurrent._mycode._lightfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(_value)).PopAll();
 };
 //BA.debugLineNum = 582;BA.debugLine="Return cs";
if (true) return _cs;
 //BA.debugLineNum = 583;BA.debugLine="End Sub";
return null;
}
public static String  _rewardedinterstitialad_failedtoreceivead(String _errorcode) throws Exception{
 //BA.debugLineNum = 373;BA.debugLine="Sub RewardedInterstitialAd_FailedToReceiveAd (Erro";
 //BA.debugLineNum = 374;BA.debugLine="LogColor($\"RewardedInterstitialAd_FailedToReceive";
anywheresoftware.b4a.keywords.Common.LogImpl("224576001",("RewardedInterstitialAd_FailedToReceiveAd ErrorCode="+anywheresoftware.b4a.keywords.Common.SmartStringFormatter("",(Object)(_errorcode))+""),anywheresoftware.b4a.keywords.Common.Colors.Green);
 //BA.debugLineNum = 375;BA.debugLine="End Sub";
return "";
}
public static String  _rewardedinterstitialad_receivead() throws Exception{
 //BA.debugLineNum = 368;BA.debugLine="Sub RewardedInterstitialAd_ReceiveAd";
 //BA.debugLineNum = 369;BA.debugLine="ToastMessageShow(\"RewardInterstitialAd_ReceiveAd\"";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("RewardInterstitialAd_ReceiveAd"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 370;BA.debugLine="LogColor($\"RewardInterstitialAd_ReceiveAd\"$, Colo";
anywheresoftware.b4a.keywords.Common.LogImpl("224510466",("RewardInterstitialAd_ReceiveAd"),anywheresoftware.b4a.keywords.Common.Colors.Green);
 //BA.debugLineNum = 371;BA.debugLine="End Sub";
return "";
}
public static String  _rewardedinterstitialad_rewarded(Object _item) throws Exception{
anywheresoftware.b4j.object.JavaObject _reward = null;
int _amount = 0;
String _rewardtype = "";
 //BA.debugLineNum = 377;BA.debugLine="Sub RewardedInterstitialAd_Rewarded (Item As Objec";
 //BA.debugLineNum = 378;BA.debugLine="LogColor(\"RewardedInterstitialAd_Rewarded\",Colors";
anywheresoftware.b4a.keywords.Common.LogImpl("224641537","RewardedInterstitialAd_Rewarded",anywheresoftware.b4a.keywords.Common.Colors.Green);
 //BA.debugLineNum = 379;BA.debugLine="Dim Reward As JavaObject = Item";
_reward = new anywheresoftware.b4j.object.JavaObject();
_reward = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_item));
 //BA.debugLineNum = 380;BA.debugLine="Dim Amount As Int = Reward.RunMethod(\"getAmount\",";
_amount = (int)(BA.ObjectToNumber(_reward.RunMethod("getAmount",(Object[])(anywheresoftware.b4a.keywords.Common.Null))));
 //BA.debugLineNum = 381;BA.debugLine="Dim RewardType As String = Reward.RunMethod(\"getT";
_rewardtype = BA.ObjectToString(_reward.RunMethod("getType",(Object[])(anywheresoftware.b4a.keywords.Common.Null)));
 //BA.debugLineNum = 382;BA.debugLine="Log(\"Rewarded: \" & Amount & \" -> \" & RewardType)";
anywheresoftware.b4a.keywords.Common.LogImpl("224641541","Rewarded: "+BA.NumberToString(_amount)+" -> "+_rewardtype,0);
 //BA.debugLineNum = 384;BA.debugLine="End Sub";
return "";
}
public static String  _settingbtn_click() throws Exception{
 //BA.debugLineNum = 386;BA.debugLine="Sub settingbtn_click";
 //BA.debugLineNum = 387;BA.debugLine="StartActivity(settings)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._settings.getObject()));
 //BA.debugLineNum = 388;BA.debugLine="End Sub";
return "";
}
public static String  _splash_click() throws Exception{
 //BA.debugLineNum = 1110;BA.debugLine="Sub splash_Click";
 //BA.debugLineNum = 1113;BA.debugLine="End Sub";
return "";
}
public static String  _strokecsbuilder(anywheresoftware.b4a.objects.LabelWrapper _lbl,anywheresoftware.b4a.objects.CSBuilder _cs,int _strokecolor,int _fillcolor,float _strokewidth) throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper _cvs = null;
int _angle = 0;
float _dx = 0f;
float _dy = 0f;
 //BA.debugLineNum = 849;BA.debugLine="Sub StrokeCSBuilder(lbl As Label, cs As CSBuilder,";
 //BA.debugLineNum = 850;BA.debugLine="Dim cvs As Canvas";
_cvs = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 851;BA.debugLine="cvs.Initialize(lbl)";
_cvs.Initialize((android.view.View)(_lbl.getObject()));
 //BA.debugLineNum = 854;BA.debugLine="For angle = 0 To 360 Step 15";
{
final int step3 = 15;
final int limit3 = (int) (360);
_angle = (int) (0) ;
for (;_angle <= limit3 ;_angle = _angle + step3 ) {
 //BA.debugLineNum = 855;BA.debugLine="Dim dx As Float = CosD(angle) * strokeWidth";
_dx = (float) (anywheresoftware.b4a.keywords.Common.CosD(_angle)*_strokewidth);
 //BA.debugLineNum = 856;BA.debugLine="Dim dy As Float = SinD(angle) * strokeWidth";
_dy = (float) (anywheresoftware.b4a.keywords.Common.SinD(_angle)*_strokewidth);
 //BA.debugLineNum = 857;BA.debugLine="cvs.DrawText(cs.ToString, lbl.Width/2 + dx, lbl.";
_cvs.DrawText(mostCurrent.activityBA,_cs.ToString(),(float) (_lbl.getWidth()/(double)2+_dx),(float) (_lbl.getHeight()/(double)2+_dy),anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME(),_lbl.getTextSize(),_strokecolor,BA.getEnumFromString(android.graphics.Paint.Align.class,"CENTER"));
 }
};
 //BA.debugLineNum = 861;BA.debugLine="cvs.DrawText(cs.ToString, lbl.Width/2, lbl.Height";
_cvs.DrawText(mostCurrent.activityBA,_cs.ToString(),(float) (_lbl.getWidth()/(double)2),(float) (_lbl.getHeight()/(double)2),anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME(),_lbl.getTextSize(),_fillcolor,BA.getEnumFromString(android.graphics.Paint.Align.class,"CENTER"));
 //BA.debugLineNum = 864;BA.debugLine="End Sub";
return "";
}
public static String  _strokecsbuilder1(anywheresoftware.b4a.objects.LabelWrapper _lbl,String _cs,int _strokecolor,int _fillcolor,float _strokewidth) throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper _cvs = null;
int _angle = 0;
float _dx = 0f;
float _dy = 0f;
 //BA.debugLineNum = 866;BA.debugLine="Sub StrokeCSBuilder1(lbl As Label, cs As String, s";
 //BA.debugLineNum = 867;BA.debugLine="Dim cvs As Canvas";
_cvs = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 868;BA.debugLine="cvs.Initialize(lbl)";
_cvs.Initialize((android.view.View)(_lbl.getObject()));
 //BA.debugLineNum = 871;BA.debugLine="For angle = 0 To 360 Step 15";
{
final int step3 = 15;
final int limit3 = (int) (360);
_angle = (int) (0) ;
for (;_angle <= limit3 ;_angle = _angle + step3 ) {
 //BA.debugLineNum = 872;BA.debugLine="Dim dx As Float = CosD(angle) * strokeWidth";
_dx = (float) (anywheresoftware.b4a.keywords.Common.CosD(_angle)*_strokewidth);
 //BA.debugLineNum = 873;BA.debugLine="Dim dy As Float = SinD(angle) * strokeWidth";
_dy = (float) (anywheresoftware.b4a.keywords.Common.SinD(_angle)*_strokewidth);
 //BA.debugLineNum = 874;BA.debugLine="cvs.DrawText(cs, lbl.Width/2 + dx, lbl.Height/2";
_cvs.DrawText(mostCurrent.activityBA,_cs,(float) (_lbl.getWidth()/(double)2+_dx),(float) (_lbl.getHeight()/(double)2+_dy),anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME(),_lbl.getTextSize(),_strokecolor,BA.getEnumFromString(android.graphics.Paint.Align.class,"CENTER"));
 }
};
 //BA.debugLineNum = 878;BA.debugLine="cvs.DrawText(cs, lbl.Width/2, lbl.Height/2, Typef";
_cvs.DrawText(mostCurrent.activityBA,_cs,(float) (_lbl.getWidth()/(double)2),(float) (_lbl.getHeight()/(double)2),anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME(),_lbl.getTextSize(),_fillcolor,BA.getEnumFromString(android.graphics.Paint.Align.class,"CENTER"));
 //BA.debugLineNum = 881;BA.debugLine="End Sub";
return "";
}
public static String  _threedbtn_click() throws Exception{
 //BA.debugLineNum = 1036;BA.debugLine="Sub threedbtn_click";
 //BA.debugLineNum = 1037;BA.debugLine="StartActivity(threed)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._threed.getObject()));
 //BA.debugLineNum = 1045;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.CSBuilder  _timecsb(String _time) throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 585;BA.debugLine="Sub timecsb (time As String) As CSBuilder";
 //BA.debugLineNum = 586;BA.debugLine="Dim  cs As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 587;BA.debugLine="cs.Initialize";
_cs.Initialize();
 //BA.debugLineNum = 591;BA.debugLine="cs.Typeface(mycode.defaultfont).Size(mycode.texts";
_cs.Typeface((android.graphics.Typeface)(mostCurrent._mycode._defaultfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (8)))).Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(_time+" ")).PopAll();
 //BA.debugLineNum = 593;BA.debugLine="Return cs";
if (true) return _cs;
 //BA.debugLineNum = 594;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.CSBuilder  _timecsbuilder(String _icon,String _time) throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 628;BA.debugLine="Sub timecsbuilder (icon As String,time As String)";
 //BA.debugLineNum = 629;BA.debugLine="Dim cs As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 630;BA.debugLine="cs.Initialize";
_cs.Initialize();
 //BA.debugLineNum = 631;BA.debugLine="cs.Color(Colors.White).Typeface(Typeface.FONTAWES";
_cs.Color(anywheresoftware.b4a.keywords.Common.Colors.White).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME()).Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (6)))).Append(BA.ObjectToCharSequence(_icon)).Pop();
 //BA.debugLineNum = 632;BA.debugLine="cs.Color(Colors.White).Typeface(mycode.defaultfon";
_cs.Color(anywheresoftware.b4a.keywords.Common.Colors.White).Typeface((android.graphics.Typeface)(mostCurrent._mycode._defaultfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (6)))).Append(BA.ObjectToCharSequence("Update Time : "+_time)).PopAll();
 //BA.debugLineNum = 634;BA.debugLine="Return cs";
if (true) return _cs;
 //BA.debugLineNum = 635;BA.debugLine="End Sub";
return null;
}
public static String  _updatebtn_click() throws Exception{
anywheresoftware.b4a.phone.Phone.PhoneIntents _pi = null;
 //BA.debugLineNum = 844;BA.debugLine="Sub updatebtn_click";
 //BA.debugLineNum = 845;BA.debugLine="Dim pi As PhoneIntents";
_pi = new anywheresoftware.b4a.phone.Phone.PhoneIntents();
 //BA.debugLineNum = 846;BA.debugLine="StartActivity(pi.OpenBrowser(\"https://play.google";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_pi.OpenBrowser("https://play.google.com/store/apps/details?id=com.burma.royal2d")));
 //BA.debugLineNum = 847;BA.debugLine="End Sub";
return "";
}
public static String  _updatemsg(String _upfun) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.LabelWrapper _lb = null;
anywheresoftware.b4a.objects.LabelWrapper _lb2 = null;
anywheresoftware.b4a.objects.ButtonWrapper _updatebtn = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 760;BA.debugLine="Sub updateMsg (upfun As String)";
 //BA.debugLineNum = 761;BA.debugLine="LottieView.Visible=False";
mostCurrent._lottieview.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 762;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 763;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 764;BA.debugLine="Dim lb As Label";
_lb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 765;BA.debugLine="Dim lb2 As Label";
_lb2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 766;BA.debugLine="Dim updatebtn As Button";
_updatebtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 767;BA.debugLine="lb.Initialize(\"\")";
_lb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 768;BA.debugLine="lb2.Initialize(\"\")";
_lb2.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 769;BA.debugLine="updatebtn.Initialize(\"updatebtn\")";
_updatebtn.Initialize(mostCurrent.activityBA,"updatebtn");
 //BA.debugLineNum = 770;BA.debugLine="updatebtn.Background=mycode.btnbgdynamic(mycode.b";
_updatebtn.setBackground((android.graphics.drawable.Drawable)(mostCurrent._mycode._btnbgdynamic /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,mostCurrent._mycode._bgcolor /*int*/ ,anywheresoftware.b4a.keywords.Common.Colors.DarkGray,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))).getObject()));
 //BA.debugLineNum = 771;BA.debugLine="updatebtn.Text = \"Update လုပ်မယ်\"";
_updatebtn.setText(BA.ObjectToCharSequence("Update လုပ်မယ်"));
 //BA.debugLineNum = 772;BA.debugLine="updatebtn.TextSize = mycode.textsize(9)";
_updatebtn.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (9)));
 //BA.debugLineNum = 773;BA.debugLine="updatebtn.Typeface = mycode.mmfont";
_updatebtn.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 774;BA.debugLine="updatebtn.TextColor=Colors.White";
_updatebtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 775;BA.debugLine="splashscreen.AddView(p,10dip,mycode.ActivityHeigh";
mostCurrent._splashscreen.AddView((android.view.View)(_p.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (mostCurrent._mycode._activityheight /*int*/ -anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (200))),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)));
 //BA.debugLineNum = 776;BA.debugLine="p.AddView(lb,10dip,10dip,p.Width-20dip,30dip)";
_p.AddView((android.view.View)(_lb.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_p.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 777;BA.debugLine="lb.Gravity=Gravity.CENTER";
_lb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 778;BA.debugLine="lb.Text=  upfun";
_lb.setText(BA.ObjectToCharSequence(_upfun));
 //BA.debugLineNum = 779;BA.debugLine="lb.Typeface=mycode.mmfont";
_lb.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 780;BA.debugLine="lb.TextSize = mycode.textsize(9)";
_lb.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (9)));
 //BA.debugLineNum = 781;BA.debugLine="lb.TextColor=Colors.White";
_lb.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 782;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 783;BA.debugLine="lb.Height = su.MeasureMultilineTextHeight(lb,lb.T";
_lb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_lb.getObject()),BA.ObjectToCharSequence(_lb.getText())));
 //BA.debugLineNum = 784;BA.debugLine="p.AddView(updatebtn,(p.Width-200dip)/2,lb.Height+";
_p.AddView((android.view.View)(_updatebtn.getObject()),(int) ((_p.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (200)))/(double)2),(int) (_lb.getHeight()+_lb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (200)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 785;BA.debugLine="p.Height = updatebtn.Height +updatebtn.Top+10dip";
_p.setHeight((int) (_updatebtn.getHeight()+_updatebtn.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 786;BA.debugLine="lb2.Gravity=Gravity.CENTER";
_lb2.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 787;BA.debugLine="Dim cs As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 788;BA.debugLine="cs.Initialize.Color(Colors.RGB(255,191,0)).Size(m";
_cs.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (255),(int) (191),(int) (0))).Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (9)))).Typeface((android.graphics.Typeface)(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Pop();
 //BA.debugLineNum = 789;BA.debugLine="cs.Clickable(\"notupdate\",1).Underline.Append(\"မလု";
_cs.Clickable(processBA,"notupdate",(Object)(1)).Underline().Append(BA.ObjectToCharSequence("မလုပ်သေးပါ")).Pop().Pop().PopAll();
 //BA.debugLineNum = 790;BA.debugLine="cs.EnableClickEvents(lb2)";
_cs.EnableClickEvents((android.widget.TextView)(_lb2.getObject()));
 //BA.debugLineNum = 791;BA.debugLine="lb2.Text= cs";
_lb2.setText(BA.ObjectToCharSequence(_cs.getObject()));
 //BA.debugLineNum = 792;BA.debugLine="p.AddView(lb2,10dip,updatebtn.Height+updatebtn.To";
_p.AddView((android.view.View)(_lb2.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_updatebtn.getHeight()+_updatebtn.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),(int) (_p.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 793;BA.debugLine="lb2.Height = su.MeasureMultilineTextHeight(lb2,cs";
_lb2.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_lb2.getObject()),BA.ObjectToCharSequence(_cs.getObject())));
 //BA.debugLineNum = 794;BA.debugLine="p.Height = lb2.Height+lb2.Top+10dip";
_p.setHeight((int) (_lb2.getHeight()+_lb2.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 796;BA.debugLine="p.Top = mycode.ActivityHeight - (p.Height+50dip)";
_p.setTop((int) (mostCurrent._mycode._activityheight /*int*/ -(_p.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)))));
 //BA.debugLineNum = 797;BA.debugLine="End Sub";
return "";
}
public static String  _ybtn_click() throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
anywheresoftware.b4a.phone.Phone.PhoneIntents _pi = null;
 //BA.debugLineNum = 712;BA.debugLine="Sub ybtn_click";
 //BA.debugLineNum = 713;BA.debugLine="Dim b As Button =Sender";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 714;BA.debugLine="Dim pi As PhoneIntents";
_pi = new anywheresoftware.b4a.phone.Phone.PhoneIntents();
 //BA.debugLineNum = 715;BA.debugLine="StartActivity(pi.OpenBrowser(b.Tag))";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_pi.OpenBrowser(BA.ObjectToString(_b.getTag()))));
 //BA.debugLineNum = 716;BA.debugLine="End Sub";
return "";
}
public boolean _onCreateOptionsMenu(android.view.Menu menu) {
	 processBA.raiseEvent(null, "create_menu", menu);
	 return true;
	
}
}
