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

public class public_chat extends Activity implements B4AActivity{
	public static public_chat mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "com.burma.royal2d", "com.burma.royal2d.public_chat");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (public_chat).");
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
		activityBA = new BA(this, layout, processBA, "com.burma.royal2d", "com.burma.royal2d.public_chat");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.burma.royal2d.public_chat", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (public_chat) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (public_chat) Resume **");
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
		return public_chat.class;
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
            BA.LogInfo("** Activity (public_chat) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (public_chat) Pause event (activity is not paused). **");
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
            public_chat mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (public_chat) Resume **");
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
public static com.burma.royal2d.sseconnector _livesse = null;
public static anywheresoftware.b4a.objects.Timer _timer = null;
public static anywheresoftware.b4a.objects.StringUtils _su = null;
public static int _messageheight = 0;
public static boolean _iscall = false;
public static anywheresoftware.b4a.objects.Timer _livetimer = null;
public static int _numbarheight = 0;
public static int _typbarheight = 0;
public static String _sendsms = "";
public static int _loadsmscount = 0;
public static com.burma.royal2d.chatsseconnector _chatsse = null;
public static int _top = 0;
public anywheresoftware.b4a.objects.LabelWrapper _setlb = null;
public anywheresoftware.b4a.objects.LabelWrapper _valuelb = null;
public anywheresoftware.b4a.objects.LabelWrapper _twodlb = null;
public anywheresoftware.b4a.objects.LabelWrapper _livelb = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _mainscv = null;
public anywheresoftware.b4a.objects.EditTextWrapper _edt = null;
public anywheresoftware.b4a.objects.IME _ime = null;
public anywheresoftware.b4a.objects.PanelWrapper _typingpn = null;
public anywheresoftware.b4a.objects.PanelWrapper _numpanel = null;
public anywheresoftware.b4a.objects.PanelWrapper _ppopup = null;
public com.aghajari.axrlottie.AXrLottie _axrlottie = null;
public com.aghajari.axrlottie.AXrLottieImageView _lottieview = null;
public anywheresoftware.b4a.objects.ImageViewWrapper _pimg = null;
public com.burma.royal2d.main _main = null;
public com.burma.royal2d.report_details _report_details = null;
public com.burma.royal2d.mycode _mycode = null;
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

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _appbar = null;
ir.aghajari.retrofitglide.Amir_Glide _glide = null;
 //BA.debugLineNum = 35;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 37;BA.debugLine="timer.Initialize(\"tm\",1000)";
_timer.Initialize(processBA,"tm",(long) (1000));
 //BA.debugLineNum = 38;BA.debugLine="timer.Enabled=True";
_timer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 41;BA.debugLine="ime.Initialize(\"ime\")";
mostCurrent._ime.Initialize("ime");
 //BA.debugLineNum = 42;BA.debugLine="ime.AddHeightChangedEvent";
mostCurrent._ime.AddHeightChangedEvent(mostCurrent.activityBA);
 //BA.debugLineNum = 43;BA.debugLine="Activity.Color=mycode.bgColor";
mostCurrent._activity.setColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 44;BA.debugLine="mycode.SETnavigationcolor";
mostCurrent._mycode._setnavigationcolor /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 47;BA.debugLine="Dim appbar As Panel = mycode.appbar(\"Public Chat\"";
_appbar = new anywheresoftware.b4a.objects.PanelWrapper();
_appbar = mostCurrent._mycode._appbar /*anywheresoftware.b4a.objects.PanelWrapper*/ (mostCurrent.activityBA,"Public Chat",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 49;BA.debugLine="pimg.Initialize(\"profilebtn\")";
mostCurrent._pimg.Initialize(mostCurrent.activityBA,"profilebtn");
 //BA.debugLineNum = 50;BA.debugLine="appbar.AddView(pimg,100%x-mycode.appbarheight,5di";
_appbar.AddView((android.view.View)(mostCurrent._pimg.getObject()),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-mostCurrent._mycode._appbarheight /*int*/ ),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),(int) (mostCurrent._mycode._appbarheight /*int*/ -anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),(int) (mostCurrent._mycode._appbarheight /*int*/ -anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 51;BA.debugLine="pimg.Visible=False";
mostCurrent._pimg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 52;BA.debugLine="pimg.Enabled=False";
mostCurrent._pimg.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 53;BA.debugLine="Dim glide As Amir_Glide";
_glide = new ir.aghajari.retrofitglide.Amir_Glide();
 //BA.debugLineNum = 54;BA.debugLine="glide.Initializer.Default";
_glide.Initializer(processBA).Default();
 //BA.debugLineNum = 55;BA.debugLine="glide.Load(mycode.getUserId(mycode.profile_pic)).";
_glide.Load((Object)(mostCurrent._mycode._getuserid /*String*/ (mostCurrent.activityBA,mostCurrent._mycode._profile_pic /*String*/ ))).Apply(_glide.getRO().CircleCrop()).Into((android.widget.ImageView)(mostCurrent._pimg.getObject()));
 //BA.debugLineNum = 57;BA.debugLine="Activity.AddView(appbar,0,mycode.appbartop,100%x,";
mostCurrent._activity.AddView((android.view.View)(_appbar.getObject()),(int) (0),mostCurrent._mycode._appbartop /*int*/ ,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),mostCurrent._mycode._appbarheight /*int*/ );
 //BA.debugLineNum = 61;BA.debugLine="mainscv.Initialize(1000dip)";
mostCurrent._mainscv.Initialize(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1000)));
 //BA.debugLineNum = 63;BA.debugLine="numpanel = numbar";
mostCurrent._numpanel = _numbar();
 //BA.debugLineNum = 64;BA.debugLine="Activity.AddView(numpanel,0,mycode.appbarheight,1";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._numpanel.getObject()),(int) (0),mostCurrent._mycode._appbarheight /*int*/ ,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),_numbarheight);
 //BA.debugLineNum = 66;BA.debugLine="typingpn = typingBar";
mostCurrent._typingpn = _typingbar();
 //BA.debugLineNum = 67;BA.debugLine="Activity.AddView(typingpn,0,mycode.ActivityHeight";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._typingpn.getObject()),(int) (0),(int) (mostCurrent._mycode._activityheight /*int*/ -_typbarheight),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),_typbarheight);
 //BA.debugLineNum = 68;BA.debugLine="Activity.AddView(mainscv,0,numpanel.Height+numpan";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._mainscv.getObject()),(int) (0),(int) (mostCurrent._numpanel.getHeight()+mostCurrent._numpanel.getTop()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),(int) (mostCurrent._typingpn.getTop()-(mostCurrent._numpanel.getHeight()+mostCurrent._numpanel.getTop())));
 //BA.debugLineNum = 70;BA.debugLine="livetimer.Initialize(\"livetimer\",1500)";
_livetimer.Initialize(processBA,"livetimer",(long) (1500));
 //BA.debugLineNum = 71;BA.debugLine="Change";
_change();
 //BA.debugLineNum = 72;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 92;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 93;BA.debugLine="mycode.checkentertime =0";
mostCurrent._mycode._checkentertime /*int*/  = (int) (0);
 //BA.debugLineNum = 94;BA.debugLine="isCall=False";
_iscall = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 95;BA.debugLine="FinishStream";
_finishstream();
 //BA.debugLineNum = 96;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 74;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 75;BA.debugLine="isCall =True";
_iscall = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 76;BA.debugLine="liveLoader";
_liveloader();
 //BA.debugLineNum = 78;BA.debugLine="mycode.checkentertime = 0";
mostCurrent._mycode._checkentertime /*int*/  = (int) (0);
 //BA.debugLineNum = 79;BA.debugLine="If File.Exists(File.DirInternal,\"user\") Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"user")) { 
 //BA.debugLineNum = 80;BA.debugLine="pimg.Visible=True";
mostCurrent._pimg.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 81;BA.debugLine="pimg.Enabled=True";
mostCurrent._pimg.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 83;BA.debugLine="pimg.Visible=False";
mostCurrent._pimg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 84;BA.debugLine="pimg.Enabled=False";
mostCurrent._pimg.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 85;BA.debugLine="pimg.RemoveView";
mostCurrent._pimg.RemoveView();
 };
 //BA.debugLineNum = 87;BA.debugLine="progressShow";
_progressshow();
 //BA.debugLineNum = 88;BA.debugLine="loadsmscount = 0";
_loadsmscount = (int) (0);
 //BA.debugLineNum = 89;BA.debugLine="LoadMessage";
_loadmessage();
 //BA.debugLineNum = 90;BA.debugLine="End Sub";
return "";
}
public static String  _addblock(anywheresoftware.b4a.objects.collections.Map _m) throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _js = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
boolean _shouldadd = false;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _mm = null;
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _jg = null;
anywheresoftware.b4a.objects.collections.List _lnew = null;
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _json = null;
 //BA.debugLineNum = 600;BA.debugLine="Sub AddBlock(m As Map)";
 //BA.debugLineNum = 601;BA.debugLine="If File.Exists(File.DirInternal,\"block\") Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"block")) { 
 //BA.debugLineNum = 602;BA.debugLine="Dim js As JSONParser";
_js = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 603;BA.debugLine="js.Initialize(File.ReadString(File.DirInternal,\"";
_js.Initialize(anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"block"));
 //BA.debugLineNum = 604;BA.debugLine="Dim ls As List = js.NextArray";
_ls = new anywheresoftware.b4a.objects.collections.List();
_ls = _js.NextArray();
 //BA.debugLineNum = 605;BA.debugLine="Dim shouldAdd As Boolean";
_shouldadd = false;
 //BA.debugLineNum = 606;BA.debugLine="If ls.Size > 0 Then";
if (_ls.getSize()>0) { 
 //BA.debugLineNum = 607;BA.debugLine="For i = 0 To ls.Size -1";
{
final int step7 = 1;
final int limit7 = (int) (_ls.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
 //BA.debugLineNum = 608;BA.debugLine="Dim mm As Map = ls.Get(i)";
_mm = new anywheresoftware.b4a.objects.collections.Map();
_mm = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_ls.Get(_i)));
 //BA.debugLineNum = 609;BA.debugLine="If mm.Get(mycode.id) = m.Get(mycode.id) Then";
if ((_mm.Get((Object)(mostCurrent._mycode._id /*String*/ ))).equals(_m.Get((Object)(mostCurrent._mycode._id /*String*/ )))) { 
 //BA.debugLineNum = 610;BA.debugLine="shouldAdd = False";
_shouldadd = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 611;BA.debugLine="Exit";
if (true) break;
 }else {
 //BA.debugLineNum = 613;BA.debugLine="shouldAdd =True";
_shouldadd = anywheresoftware.b4a.keywords.Common.True;
 };
 }
};
 //BA.debugLineNum = 616;BA.debugLine="If shouldAdd =True Then";
if (_shouldadd==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 617;BA.debugLine="ls.Add(m)";
_ls.Add((Object)(_m.getObject()));
 };
 }else {
 //BA.debugLineNum = 621;BA.debugLine="ls.Add(m)";
_ls.Add((Object)(_m.getObject()));
 };
 //BA.debugLineNum = 623;BA.debugLine="Dim jg As JSONGenerator";
_jg = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
 //BA.debugLineNum = 624;BA.debugLine="jg.Initialize2(ls)";
_jg.Initialize2(_ls);
 //BA.debugLineNum = 625;BA.debugLine="File.WriteString(File.DirInternal,\"block\",jg.ToS";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"block",_jg.ToString());
 //BA.debugLineNum = 626;BA.debugLine="Log(\"OLD BLOCK\")";
anywheresoftware.b4a.keywords.Common.LogImpl("24587546","OLD BLOCK",0);
 }else {
 //BA.debugLineNum = 628;BA.debugLine="Log(\"NEW BLOCK\")";
anywheresoftware.b4a.keywords.Common.LogImpl("24587548","NEW BLOCK",0);
 //BA.debugLineNum = 629;BA.debugLine="Dim lnew As List";
_lnew = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 630;BA.debugLine="lnew.Initialize";
_lnew.Initialize();
 //BA.debugLineNum = 631;BA.debugLine="lnew.Add(m)";
_lnew.Add((Object)(_m.getObject()));
 //BA.debugLineNum = 632;BA.debugLine="Dim json As JSONGenerator";
_json = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
 //BA.debugLineNum = 633;BA.debugLine="json.Initialize2(lnew)";
_json.Initialize2(_lnew);
 //BA.debugLineNum = 634;BA.debugLine="File.WriteString(File.DirInternal,\"block\",json.T";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"block",_json.ToString());
 };
 //BA.debugLineNum = 636;BA.debugLine="CallSubDelayed2(Me,\"loadMessageSuccess\",mycode.ch";
anywheresoftware.b4a.keywords.Common.CallSubDelayed2(processBA,public_chat.getObject(),"loadMessageSuccess",(Object)(mostCurrent._mycode._chatjson /*String*/ (mostCurrent.activityBA)));
 //BA.debugLineNum = 637;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
 //BA.debugLineNum = 596;BA.debugLine="Sub backbtn_click";
 //BA.debugLineNum = 597;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 598;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.collections.List  _blocklist() throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _json = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
 //BA.debugLineNum = 639;BA.debugLine="Sub blockList As List";
 //BA.debugLineNum = 640;BA.debugLine="Dim json As JSONParser";
_json = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 641;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 642;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 643;BA.debugLine="If File.Exists(File.DirInternal,\"block\") Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"block")) { 
 //BA.debugLineNum = 644;BA.debugLine="json.Initialize(File.ReadString(File.DirInternal,";
_json.Initialize(anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"block"));
 //BA.debugLineNum = 645;BA.debugLine="ls = json.NextArray";
_ls = _json.NextArray();
 };
 //BA.debugLineNum = 647;BA.debugLine="Return ls";
if (true) return _ls;
 //BA.debugLineNum = 648;BA.debugLine="End Sub";
return null;
}
public static String  _change() throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _json = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 354;BA.debugLine="Sub Change";
 //BA.debugLineNum = 355;BA.debugLine="Try";
try { //BA.debugLineNum = 356;BA.debugLine="checktime";
_checktime();
 //BA.debugLineNum = 357;BA.debugLine="Dim json As JSONParser";
_json = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 358;BA.debugLine="json.Initialize(File.ReadString(File.DirInternal";
_json.Initialize(anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"live"));
 //BA.debugLineNum = 359;BA.debugLine="Dim ls As List = json.NextArray";
_ls = new anywheresoftware.b4a.objects.collections.List();
_ls = _json.NextArray();
 //BA.debugLineNum = 360;BA.debugLine="If ls.Size >0 Then";
if (_ls.getSize()>0) { 
 //BA.debugLineNum = 361;BA.debugLine="For i  = 0 To ls.Size -1";
{
final int step7 = 1;
final int limit7 = (int) (_ls.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit7 ;_i = _i + step7 ) {
 //BA.debugLineNum = 362;BA.debugLine="Dim m As Map  =ls.Get(i)";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_ls.Get(_i)));
 }
};
 //BA.debugLineNum = 364;BA.debugLine="If DateTime.GetDayOfWeek(DateTime.Now) <>0 Or D";
if (anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfWeek(anywheresoftware.b4a.keywords.Common.DateTime.getNow())!=0 || anywheresoftware.b4a.keywords.Common.DateTime.GetDayOfWeek(anywheresoftware.b4a.keywords.Common.DateTime.getNow())!=7) { 
 //BA.debugLineNum = 365;BA.debugLine="If DateTime.Now>DateTime.TimeParse(\"9:30:00\")";
if (anywheresoftware.b4a.keywords.Common.DateTime.getNow()>anywheresoftware.b4a.keywords.Common.DateTime.TimeParse("9:30:00") && anywheresoftware.b4a.keywords.Common.DateTime.getNow()<anywheresoftware.b4a.keywords.Common.DateTime.TimeParse("13:50:00")) { 
 //BA.debugLineNum = 366;BA.debugLine="setlb.Text = numcs(\"SET\",m.Get(LiveUtils.chan";
mostCurrent._setlb.setText(BA.ObjectToCharSequence(_numcs("SET",BA.ObjectToString(_m.Get((Object)(mostCurrent._liveutils._change /*String*/ (mostCurrent.activityBA,"1200set")))),anywheresoftware.b4a.keywords.Common.Colors.White).getObject()));
 //BA.debugLineNum = 367;BA.debugLine="valuelb.Text = numcs(\"VALUE\",m.Get(LiveUtils.";
mostCurrent._valuelb.setText(BA.ObjectToCharSequence(_numcs("VALUE",BA.ObjectToString(_m.Get((Object)(mostCurrent._liveutils._change /*String*/ (mostCurrent.activityBA,"1200value")))),anywheresoftware.b4a.keywords.Common.Colors.White).getObject()));
 //BA.debugLineNum = 368;BA.debugLine="twodlb.Text = numcs(\"2D\",m.get(LiveUtils.chan";
mostCurrent._twodlb.setText(BA.ObjectToCharSequence(_numcs("2D",BA.ObjectToString(_m.Get((Object)(mostCurrent._liveutils._change /*String*/ (mostCurrent.activityBA,"1200")))),anywheresoftware.b4a.keywords.Common.Colors.Yellow).getObject()));
 }else {
 //BA.debugLineNum = 370;BA.debugLine="setlb.Text = numcs(\"SET\",m.get(LiveUtils.chan";
mostCurrent._setlb.setText(BA.ObjectToCharSequence(_numcs("SET",BA.ObjectToString(_m.Get((Object)(mostCurrent._liveutils._change /*String*/ (mostCurrent.activityBA,"430set")))),anywheresoftware.b4a.keywords.Common.Colors.White).getObject()));
 //BA.debugLineNum = 371;BA.debugLine="valuelb.Text = numcs(\"VALUE\",m.get(LiveUtils.";
mostCurrent._valuelb.setText(BA.ObjectToCharSequence(_numcs("VALUE",BA.ObjectToString(_m.Get((Object)(mostCurrent._liveutils._change /*String*/ (mostCurrent.activityBA,"430value")))),anywheresoftware.b4a.keywords.Common.Colors.White).getObject()));
 //BA.debugLineNum = 372;BA.debugLine="twodlb.Text = numcs(\"2D\",m.Get(LiveUtils.chan";
mostCurrent._twodlb.setText(BA.ObjectToCharSequence(_numcs("2D",BA.ObjectToString(_m.Get((Object)(mostCurrent._liveutils._change /*String*/ (mostCurrent.activityBA,"430")))),anywheresoftware.b4a.keywords.Common.Colors.Yellow).getObject()));
 };
 };
 //BA.debugLineNum = 375;BA.debugLine="livelb.Text = numcs(\"LIVE\",m.Get(\"live\"),Colors";
mostCurrent._livelb.setText(BA.ObjectToCharSequence(_numcs("LIVE",BA.ObjectToString(_m.Get((Object)("live"))),anywheresoftware.b4a.keywords.Common.Colors.White).getObject()));
 //BA.debugLineNum = 376;BA.debugLine="If m.Get(\"status\")=\"On\" Then";
if ((_m.Get((Object)("status"))).equals((Object)("On"))) { 
 //BA.debugLineNum = 377;BA.debugLine="livetimer.Enabled=True";
_livetimer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 379;BA.debugLine="livetimer.Enabled=False";
_livetimer.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 };
 } 
       catch (Exception e29) {
			processBA.setLastException(e29); //BA.debugLineNum = 383;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("23997725",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 385;BA.debugLine="End Sub";
return "";
}
public static String  _checktime() throws Exception{
String _tm = "";
 //BA.debugLineNum = 387;BA.debugLine="Sub checktime As String";
 //BA.debugLineNum = 388;BA.debugLine="Dim tm As String";
_tm = "";
 //BA.debugLineNum = 389;BA.debugLine="If DateTime.Now > DateTime.TimeParse(\"09:30:00\")";
if (anywheresoftware.b4a.keywords.Common.DateTime.getNow()>anywheresoftware.b4a.keywords.Common.DateTime.TimeParse("09:30:00") && anywheresoftware.b4a.keywords.Common.DateTime.getNow()<anywheresoftware.b4a.keywords.Common.DateTime.TimeParse("13:50:00")) { 
 //BA.debugLineNum = 390;BA.debugLine="tm = \"morning\"";
_tm = "morning";
 }else {
 //BA.debugLineNum = 392;BA.debugLine="tm = \"evening\"";
_tm = "evening";
 };
 //BA.debugLineNum = 394;BA.debugLine="Return tm";
if (true) return _tm;
 //BA.debugLineNum = 395;BA.debugLine="End Sub";
return "";
}
public static String  _finishstream() throws Exception{
 //BA.debugLineNum = 98;BA.debugLine="Sub FinishStream";
 //BA.debugLineNum = 99;BA.debugLine="If livesse.IsInitialized Then";
if (_livesse.IsInitialized /*boolean*/ ()) { 
 //BA.debugLineNum = 100;BA.debugLine="livesse.Finish";
_livesse._finish /*String*/ ();
 }else {
 //BA.debugLineNum = 102;BA.debugLine="livesse.Initialize(Me,\"live\",\"live\",Application";
_livesse._initialize /*String*/ (processBA,public_chat.getObject(),"live","live",anywheresoftware.b4a.keywords.Common.Application.getPackageName());
 //BA.debugLineNum = 103;BA.debugLine="livesse.Finish";
_livesse._finish /*String*/ ();
 };
 //BA.debugLineNum = 105;BA.debugLine="If chatsse.IsInitialized Then";
if (_chatsse.IsInitialized /*boolean*/ ()) { 
 //BA.debugLineNum = 106;BA.debugLine="chatsse.Finish";
_chatsse._finish /*String*/ ();
 }else {
 //BA.debugLineNum = 108;BA.debugLine="chatsse.Initialize(Me,\"chat\",\"chat\",Application";
_chatsse._initialize /*String*/ (processBA,public_chat.getObject(),"chat","chat",anywheresoftware.b4a.keywords.Common.Application.getPackageName());
 };
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 22;BA.debugLine="Dim setlb,valuelb,twodlb,livelb As Label";
mostCurrent._setlb = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._valuelb = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._twodlb = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._livelb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Dim mainscv As ScrollView";
mostCurrent._mainscv = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Dim edt As EditText";
mostCurrent._edt = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Dim ime As IME";
mostCurrent._ime = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 26;BA.debugLine="Dim typingpn As Panel";
mostCurrent._typingpn = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Dim numpanel As Panel";
mostCurrent._numpanel = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Dim ppopup As Panel";
mostCurrent._ppopup = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Dim AXrLottie As AXrLottie";
mostCurrent._axrlottie = new com.aghajari.axrlottie.AXrLottie();
 //BA.debugLineNum = 31;BA.debugLine="Dim LottieView As AXrLottieImageView";
mostCurrent._lottieview = new com.aghajari.axrlottie.AXrLottieImageView();
 //BA.debugLineNum = 32;BA.debugLine="Dim pimg As ImageView";
mostCurrent._pimg = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 33;BA.debugLine="End Sub";
return "";
}
public static void  _ime_heightchanged(int _newheight,int _oldheight) throws Exception{
ResumableSub_ime_HeightChanged rsub = new ResumableSub_ime_HeightChanged(null,_newheight,_oldheight);
rsub.resume(processBA, null);
}
public static class ResumableSub_ime_HeightChanged extends BA.ResumableSub {
public ResumableSub_ime_HeightChanged(com.burma.royal2d.public_chat parent,int _newheight,int _oldheight) {
this.parent = parent;
this._newheight = _newheight;
this._oldheight = _oldheight;
}
com.burma.royal2d.public_chat parent;
int _newheight;
int _oldheight;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 139;BA.debugLine="If NewHeight > 0 Then";
if (true) break;

case 1:
//if
this.state = 6;
if (_newheight>0) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 //BA.debugLineNum = 141;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (0));
this.state = 7;
return;
case 7:
//C
this.state = 6;
;
 //BA.debugLineNum = 142;BA.debugLine="typingpn.Top = NewHeight  - 60dip";
parent.mostCurrent._typingpn.setTop((int) (_newheight-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60))));
 //BA.debugLineNum = 143;BA.debugLine="mainscv.Height = typingpn.Top  - (numpanel.Top+n";
parent.mostCurrent._mainscv.setHeight((int) (parent.mostCurrent._typingpn.getTop()-(parent.mostCurrent._numpanel.getTop()+parent.mostCurrent._numpanel.getHeight())));
 //BA.debugLineNum = 144;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (0));
this.state = 8;
return;
case 8:
//C
this.state = 6;
;
 //BA.debugLineNum = 145;BA.debugLine="mainscv.FullScroll(True)";
parent.mostCurrent._mainscv.FullScroll(anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 148;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (0));
this.state = 9;
return;
case 9:
//C
this.state = 6;
;
 //BA.debugLineNum = 150;BA.debugLine="typingpn.Top = Activity.Height  - 60dip";
parent.mostCurrent._typingpn.setTop((int) (parent.mostCurrent._activity.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (60))));
 //BA.debugLineNum = 151;BA.debugLine="mainscv.Height = typingpn.Top - (numpanel.Top+nu";
parent.mostCurrent._mainscv.setHeight((int) (parent.mostCurrent._typingpn.getTop()-(parent.mostCurrent._numpanel.getTop()+parent.mostCurrent._numpanel.getHeight())));
 //BA.debugLineNum = 152;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (0));
this.state = 10;
return;
case 10:
//C
this.state = 6;
;
 //BA.debugLineNum = 153;BA.debugLine="mainscv.FullScroll(True)";
parent.mostCurrent._mainscv.FullScroll(anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 6:
//C
this.state = -1;
;
 //BA.debugLineNum = 155;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _liveloader() throws Exception{
 //BA.debugLineNum = 116;BA.debugLine="Sub liveLoader";
 //BA.debugLineNum = 117;BA.debugLine="If livesse.IsInitialized Then";
if (_livesse.IsInitialized /*boolean*/ ()) { 
 //BA.debugLineNum = 118;BA.debugLine="livesse.Finish";
_livesse._finish /*String*/ ();
 //BA.debugLineNum = 119;BA.debugLine="livesse.Connect(Main.newsite&\"live\")";
_livesse._connect /*String*/ (mostCurrent._main._newsite /*String*/ +"live");
 }else {
 //BA.debugLineNum = 121;BA.debugLine="livesse.Initialize(Me,\"live\",\"live\",Application.";
_livesse._initialize /*String*/ (processBA,public_chat.getObject(),"live","live",anywheresoftware.b4a.keywords.Common.Application.getPackageName());
 //BA.debugLineNum = 122;BA.debugLine="livesse.Connect(Main.newsite&\"live\")";
_livesse._connect /*String*/ (mostCurrent._main._newsite /*String*/ +"live");
 };
 //BA.debugLineNum = 124;BA.debugLine="End Sub";
return "";
}
public static void  _livetimer_tick() throws Exception{
ResumableSub_livetimer_tick rsub = new ResumableSub_livetimer_tick(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_livetimer_tick extends BA.ResumableSub {
public ResumableSub_livetimer_tick(com.burma.royal2d.public_chat parent) {
this.parent = parent;
}
com.burma.royal2d.public_chat parent;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
 //BA.debugLineNum = 398;BA.debugLine="setlb.Text = numcs(\"SET\",\"     \",Colors.White)";
parent.mostCurrent._setlb.setText(BA.ObjectToCharSequence(_numcs("SET","     ",anywheresoftware.b4a.keywords.Common.Colors.White).getObject()));
 //BA.debugLineNum = 399;BA.debugLine="valuelb.Text = numcs(\"VALUE\",\"    \",Colors.White)";
parent.mostCurrent._valuelb.setText(BA.ObjectToCharSequence(_numcs("VALUE","    ",anywheresoftware.b4a.keywords.Common.Colors.White).getObject()));
 //BA.debugLineNum = 400;BA.debugLine="livelb.Text = numcs(\"LIVE\",\"  \",Colors.White)";
parent.mostCurrent._livelb.setText(BA.ObjectToCharSequence(_numcs("LIVE","  ",anywheresoftware.b4a.keywords.Common.Colors.White).getObject()));
 //BA.debugLineNum = 401;BA.debugLine="Sleep(300)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (300));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 //BA.debugLineNum = 402;BA.debugLine="Change";
_change();
 //BA.debugLineNum = 403;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _loadmessage() throws Exception{
 //BA.debugLineNum = 127;BA.debugLine="Sub LoadMessage";
 //BA.debugLineNum = 128;BA.debugLine="If chatsse.IsInitialized Then";
if (_chatsse.IsInitialized /*boolean*/ ()) { 
 //BA.debugLineNum = 129;BA.debugLine="chatsse.Finish";
_chatsse._finish /*String*/ ();
 //BA.debugLineNum = 130;BA.debugLine="chatsse.Connect(Main.newsite&\"chat/sse\")";
_chatsse._connect /*String*/ (mostCurrent._main._newsite /*String*/ +"chat/sse");
 }else {
 //BA.debugLineNum = 132;BA.debugLine="chatsse.Initialize(Me,\"chat\",\"chat\",Application.";
_chatsse._initialize /*String*/ (processBA,public_chat.getObject(),"chat","chat",anywheresoftware.b4a.keywords.Common.Application.getPackageName());
 //BA.debugLineNum = 133;BA.debugLine="chatsse.Finish";
_chatsse._finish /*String*/ ();
 //BA.debugLineNum = 134;BA.debugLine="chatsse.Connect(Main.newsite&\"chat/sse\")";
_chatsse._connect /*String*/ (mostCurrent._main._newsite /*String*/ +"chat/sse");
 };
 //BA.debugLineNum = 136;BA.debugLine="End Sub";
return "";
}
public static void  _loadmessagesuccess(String _data) throws Exception{
ResumableSub_loadMessageSuccess rsub = new ResumableSub_loadMessageSuccess(null,_data);
rsub.resume(processBA, null);
}
public static class ResumableSub_loadMessageSuccess extends BA.ResumableSub {
public ResumableSub_loadMessageSuccess(com.burma.royal2d.public_chat parent,String _data) {
this.parent = parent;
this._data = _data;
}
com.burma.royal2d.public_chat parent;
String _data;
anywheresoftware.b4a.objects.collections.JSONParser _json = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4a.objects.PanelWrapper _othsmspn = null;
int step9;
int limit9;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 464;BA.debugLine="progressHide";
_progresshide();
 //BA.debugLineNum = 467;BA.debugLine="If data.StartsWith(\"[\") Then";
if (true) break;

case 1:
//if
this.state = 50;
if (_data.startsWith("[")) { 
this.state = 3;
}else if(_data.startsWith("{")) { 
this.state = 35;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 468;BA.debugLine="Dim json As JSONParser";
_json = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 469;BA.debugLine="json.Initialize(data)";
_json.Initialize(_data);
 //BA.debugLineNum = 470;BA.debugLine="Dim ls As List = json.NextArray";
_ls = new anywheresoftware.b4a.objects.collections.List();
_ls = _json.NextArray();
 //BA.debugLineNum = 471;BA.debugLine="If ls.Size > 0 Then";
if (true) break;

case 4:
//if
this.state = 33;
if (_ls.getSize()>0) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 473;BA.debugLine="loadsmscount = loadsmscount + 1";
parent._loadsmscount = (int) (parent._loadsmscount+1);
 //BA.debugLineNum = 474;BA.debugLine="top = 10dip";
parent._top = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10));
 //BA.debugLineNum = 476;BA.debugLine="For i = 0 To ls.Size - 1";
if (true) break;

case 7:
//for
this.state = 28;
step9 = 1;
limit9 = (int) (_ls.getSize()-1);
_i = (int) (0) ;
this.state = 51;
if (true) break;

case 51:
//C
this.state = 28;
if ((step9 > 0 && _i <= limit9) || (step9 < 0 && _i >= limit9)) this.state = 9;
if (true) break;

case 52:
//C
this.state = 51;
_i = ((int)(0 + _i + step9)) ;
if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 477;BA.debugLine="Dim m As Map = ls.Get(i)";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_ls.Get(_i)));
 //BA.debugLineNum = 478;BA.debugLine="If ShouldDisplayMessage(m) Then";
if (true) break;

case 10:
//if
this.state = 27;
if (_shoulddisplaymessage(_m)) { 
this.state = 12;
}if (true) break;

case 12:
//C
this.state = 13;
 //BA.debugLineNum = 480;BA.debugLine="If i= ls.Size-1 And mainscv.Panel.Height<main";
if (true) break;

case 13:
//if
this.state = 20;
if (_i==_ls.getSize()-1 && parent.mostCurrent._mainscv.getPanel().getHeight()<parent.mostCurrent._mainscv.getHeight()) { 
this.state = 15;
}else if(_i==0) { 
this.state = 17;
}else {
this.state = 19;
}if (true) break;

case 15:
//C
this.state = 20;
 if (true) break;

case 17:
//C
this.state = 20;
 if (true) break;

case 19:
//C
this.state = 20;
 if (true) break;

case 20:
//C
this.state = 21;
;
 //BA.debugLineNum = 485;BA.debugLine="Dim othsmspn As Panel";
_othsmspn = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 486;BA.debugLine="If m.Get(\"id\")= mycode.getUserId(mycode.id) T";
if (true) break;

case 21:
//if
this.state = 26;
if ((_m.Get((Object)("id"))).equals((Object)(parent.mostCurrent._mycode._getuserid /*String*/ (mostCurrent.activityBA,parent.mostCurrent._mycode._id /*String*/ )))) { 
this.state = 23;
}else {
this.state = 25;
}if (true) break;

case 23:
//C
this.state = 26;
 //BA.debugLineNum = 487;BA.debugLine="Log(m)";
anywheresoftware.b4a.keywords.Common.LogImpl("24390937",BA.ObjectToString(_m),0);
 //BA.debugLineNum = 488;BA.debugLine="othsmspn=MyMessage(m.Get(\"message\"))";
_othsmspn = _mymessage(BA.ObjectToString(_m.Get((Object)("message"))));
 if (true) break;

case 25:
//C
this.state = 26;
 //BA.debugLineNum = 490;BA.debugLine="othsmspn = OtherMessage(m.Get(\"profile_pic\")";
_othsmspn = _othermessage(BA.ObjectToString(_m.Get((Object)("profile_pic"))),BA.ObjectToString(_m.Get((Object)("message"))),BA.ObjectToString(_m.Get((Object)("name"))),BA.ObjectToString(_m.Get((Object)("id"))),parent._top);
 if (true) break;

case 26:
//C
this.state = 27;
;
 //BA.debugLineNum = 493;BA.debugLine="mainscv.Panel.AddView(othsmspn, 0, top, 100%x";
parent.mostCurrent._mainscv.getPanel().AddView((android.view.View)(_othsmspn.getObject()),(int) (0),parent._top,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),parent._messageheight);
 //BA.debugLineNum = 494;BA.debugLine="top=top + messageHeight + 5dip";
parent._top = (int) (parent._top+parent._messageheight+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)));
 if (true) break;

case 27:
//C
this.state = 52;
;
 if (true) break;
if (true) break;

case 28:
//C
this.state = 29;
;
 //BA.debugLineNum = 498;BA.debugLine="mainscv.Panel.Height = top + 5dip";
parent.mostCurrent._mainscv.getPanel().setHeight((int) (parent._top+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 //BA.debugLineNum = 499;BA.debugLine="If loadsmscount = 1 Then";
if (true) break;

case 29:
//if
this.state = 32;
if (parent._loadsmscount==1) { 
this.state = 31;
}if (true) break;

case 31:
//C
this.state = 32;
 //BA.debugLineNum = 500;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (0));
this.state = 53;
return;
case 53:
//C
this.state = 32;
;
 //BA.debugLineNum = 501;BA.debugLine="mainscv.FullScroll(True)";
parent.mostCurrent._mainscv.FullScroll(anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 32:
//C
this.state = 33;
;
 if (true) break;

case 33:
//C
this.state = 50;
;
 if (true) break;

case 35:
//C
this.state = 36;
 //BA.debugLineNum = 506;BA.debugLine="If loadsmscount = 0 Then";
if (true) break;

case 36:
//if
this.state = 39;
if (parent._loadsmscount==0) { 
this.state = 38;
}if (true) break;

case 38:
//C
this.state = 39;
 //BA.debugLineNum = 507;BA.debugLine="mainscv.Panel.RemoveAllViews";
parent.mostCurrent._mainscv.getPanel().RemoveAllViews();
 if (true) break;

case 39:
//C
this.state = 40;
;
 //BA.debugLineNum = 509;BA.debugLine="loadsmscount = loadsmscount +1";
parent._loadsmscount = (int) (parent._loadsmscount+1);
 //BA.debugLineNum = 510;BA.debugLine="Dim json As JSONParser";
_json = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 511;BA.debugLine="json.Initialize(data)";
_json.Initialize(_data);
 //BA.debugLineNum = 512;BA.debugLine="Dim m As Map = json.NextObject";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = _json.NextObject();
 //BA.debugLineNum = 513;BA.debugLine="If ShouldDisplayMessage(m) Then";
if (true) break;

case 40:
//if
this.state = 49;
if (_shoulddisplaymessage(_m)) { 
this.state = 42;
}if (true) break;

case 42:
//C
this.state = 43;
 //BA.debugLineNum = 515;BA.debugLine="Dim othsmspn As Panel";
_othsmspn = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 517;BA.debugLine="If m.Get(\"id\")= mycode.getUserId(mycode.id) The";
if (true) break;

case 43:
//if
this.state = 48;
if ((_m.Get((Object)("id"))).equals((Object)(parent.mostCurrent._mycode._getuserid /*String*/ (mostCurrent.activityBA,parent.mostCurrent._mycode._id /*String*/ )))) { 
this.state = 45;
}else {
this.state = 47;
}if (true) break;

case 45:
//C
this.state = 48;
 //BA.debugLineNum = 518;BA.debugLine="Log(m.Get(\"message\"))";
anywheresoftware.b4a.keywords.Common.LogImpl("24390968",BA.ObjectToString(_m.Get((Object)("message"))),0);
 //BA.debugLineNum = 519;BA.debugLine="othsmspn=MyMessage(m.Get(\"message\"))";
_othsmspn = _mymessage(BA.ObjectToString(_m.Get((Object)("message"))));
 if (true) break;

case 47:
//C
this.state = 48;
 //BA.debugLineNum = 521;BA.debugLine="othsmspn = OtherMessage(m.Get(\"profile_pic\"),";
_othsmspn = _othermessage(BA.ObjectToString(_m.Get((Object)("profile_pic"))),BA.ObjectToString(_m.Get((Object)("message"))),BA.ObjectToString(_m.Get((Object)("name"))),BA.ObjectToString(_m.Get((Object)("id"))),parent._top);
 if (true) break;

case 48:
//C
this.state = 49;
;
 //BA.debugLineNum = 526;BA.debugLine="mainscv.Panel.AddView(othsmspn, 0, top, 100%x,";
parent.mostCurrent._mainscv.getPanel().AddView((android.view.View)(_othsmspn.getObject()),(int) (0),parent._top,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),parent._messageheight);
 //BA.debugLineNum = 527;BA.debugLine="top = top + messageHeight +5dip";
parent._top = (int) (parent._top+parent._messageheight+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)));
 //BA.debugLineNum = 528;BA.debugLine="mainscv.Panel.Height = top +  5dip";
parent.mostCurrent._mainscv.getPanel().setHeight((int) (parent._top+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 //BA.debugLineNum = 529;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (0));
this.state = 54;
return;
case 54:
//C
this.state = 49;
;
 //BA.debugLineNum = 530;BA.debugLine="mainscv.FullScroll(True)";
parent.mostCurrent._mainscv.FullScroll(anywheresoftware.b4a.keywords.Common.True);
 if (true) break;

case 49:
//C
this.state = 50;
;
 if (true) break;

case 50:
//C
this.state = -1;
;
 //BA.debugLineNum = 533;BA.debugLine="mycode.checkentertime = mycode.checkentertime +1";
parent.mostCurrent._mycode._checkentertime /*int*/  = (int) (parent.mostCurrent._mycode._checkentertime /*int*/ +1);
 //BA.debugLineNum = 534;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static anywheresoftware.b4a.objects.PanelWrapper  _mymessage(String _sms) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.LabelWrapper _lb = null;
 //BA.debugLineNum = 265;BA.debugLine="Sub MyMessage(sms As String) As Panel";
 //BA.debugLineNum = 266;BA.debugLine="Log(sms)";
anywheresoftware.b4a.keywords.Common.LogImpl("23670017",_sms,0);
 //BA.debugLineNum = 267;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 268;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 269;BA.debugLine="Dim lb As Label";
_lb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 270;BA.debugLine="lb.Initialize(\"\")";
_lb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 271;BA.debugLine="lb.Width = 100%x-10dip";
_lb.setWidth((int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 272;BA.debugLine="lb.TextSize=mycode.textsize(8)";
_lb.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (8)));
 //BA.debugLineNum = 273;BA.debugLine="lb.Typeface = mycode.mmfont";
_lb.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 274;BA.debugLine="lb.TextColor=Colors.White";
_lb.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 275;BA.debugLine="lb.Text  = sms";
_lb.setText(BA.ObjectToCharSequence(_sms));
 //BA.debugLineNum = 276;BA.debugLine="lb.Gravity=Gravity.RIGHT";
_lb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.RIGHT);
 //BA.debugLineNum = 277;BA.debugLine="p.AddView(lb,0,0,100%x-10dip,10dip)";
_p.AddView((android.view.View)(_lb.getObject()),(int) (0),(int) (0),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 278;BA.debugLine="lb.Height  = su.MeasureMultilineTextHeight(lb,sms";
_lb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_lb.getObject()),BA.ObjectToCharSequence(_sms)));
 //BA.debugLineNum = 280;BA.debugLine="messageHeight = lb.Height";
_messageheight = _lb.getHeight();
 //BA.debugLineNum = 281;BA.debugLine="Return p";
if (true) return _p;
 //BA.debugLineNum = 282;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.PanelWrapper  _numbar() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _pbase = null;
anywheresoftware.b4a.objects.PanelWrapper _cv = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
int _w = 0;
 //BA.debugLineNum = 292;BA.debugLine="Sub numbar As Panel";
 //BA.debugLineNum = 293;BA.debugLine="Dim pbase As Panel";
_pbase = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 294;BA.debugLine="pbase.Initialize(\"\")";
_pbase.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 295;BA.debugLine="Dim cv As Panel";
_cv = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 296;BA.debugLine="cv.Initialize(\"\")";
_cv.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 297;BA.debugLine="pbase.AddView(cv,5dip,5dip,100%x-10dip,40dip)";
_pbase.AddView((android.view.View)(_cv.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 298;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 299;BA.debugLine="cd.Initialize(mycode.naviColor,10dip)";
_cd.Initialize(mostCurrent._mycode._navicolor /*int*/ ,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 300;BA.debugLine="cv.Background=cd";
_cv.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 301;BA.debugLine="cv.Elevation = 3dip";
_cv.setElevation((float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (3))));
 //BA.debugLineNum = 302;BA.debugLine="cv.Color=mycode.naviColor";
_cv.setColor(mostCurrent._mycode._navicolor /*int*/ );
 //BA.debugLineNum = 303;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 304;BA.debugLine="p.Initialize(\"pp\")";
_p.Initialize(mostCurrent.activityBA,"pp");
 //BA.debugLineNum = 305;BA.debugLine="cv.AddView(p,0,0,cv.Width,cv.Height)";
_cv.AddView((android.view.View)(_p.getObject()),(int) (0),(int) (0),_cv.getWidth(),_cv.getHeight());
 //BA.debugLineNum = 306;BA.debugLine="setlb.Initialize(\"\")";
mostCurrent._setlb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 307;BA.debugLine="valuelb.Initialize(\"\")";
mostCurrent._valuelb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 308;BA.debugLine="twodlb.Initialize(\"\")";
mostCurrent._twodlb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 309;BA.debugLine="livelb.Initialize(\"\")";
mostCurrent._livelb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 310;BA.debugLine="Dim w As Int  = (100%x-120dip)/2";
_w = (int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)))/(double)2);
 //BA.debugLineNum = 311;BA.debugLine="p.AddView(livelb,0,0,50dip,cv.Height)";
_p.AddView((android.view.View)(mostCurrent._livelb.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),_cv.getHeight());
 //BA.debugLineNum = 312;BA.debugLine="p.AddView(setlb,livelb.Width,0,w,cv.Height)";
_p.AddView((android.view.View)(mostCurrent._setlb.getObject()),mostCurrent._livelb.getWidth(),(int) (0),_w,_cv.getHeight());
 //BA.debugLineNum = 313;BA.debugLine="p.AddView(valuelb,setlb.Width+setlb.Left,0,w,cv.H";
_p.AddView((android.view.View)(mostCurrent._valuelb.getObject()),(int) (mostCurrent._setlb.getWidth()+mostCurrent._setlb.getLeft()),(int) (0),_w,_cv.getHeight());
 //BA.debugLineNum = 314;BA.debugLine="p.AddView(twodlb,valuelb.Width+valuelb.Left,0,50d";
_p.AddView((android.view.View)(mostCurrent._twodlb.getObject()),(int) (mostCurrent._valuelb.getWidth()+mostCurrent._valuelb.getLeft()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),_cv.getHeight());
 //BA.debugLineNum = 315;BA.debugLine="livelb.Text = numcs(\"LIVE\",\"--\",Colors.White)";
mostCurrent._livelb.setText(BA.ObjectToCharSequence(_numcs("LIVE","--",anywheresoftware.b4a.keywords.Common.Colors.White).getObject()));
 //BA.debugLineNum = 316;BA.debugLine="setlb.Text = numcs(\"SET\",\"----.--\",Colors.White)";
mostCurrent._setlb.setText(BA.ObjectToCharSequence(_numcs("SET","----.--",anywheresoftware.b4a.keywords.Common.Colors.White).getObject()));
 //BA.debugLineNum = 317;BA.debugLine="valuelb.Text = numcs(\"VALUE\",\"-----.--\",Colors.Wh";
mostCurrent._valuelb.setText(BA.ObjectToCharSequence(_numcs("VALUE","-----.--",anywheresoftware.b4a.keywords.Common.Colors.White).getObject()));
 //BA.debugLineNum = 318;BA.debugLine="twodlb.Text = numcs(\"2D\",\"--\",Colors.Yellow)";
mostCurrent._twodlb.setText(BA.ObjectToCharSequence(_numcs("2D","--",anywheresoftware.b4a.keywords.Common.Colors.Yellow).getObject()));
 //BA.debugLineNum = 319;BA.debugLine="livelb.Height = su.MeasureMultilineTextHeight(liv";
mostCurrent._livelb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(mostCurrent._livelb.getObject()),BA.ObjectToCharSequence(_numcs("SET","----.--",anywheresoftware.b4a.keywords.Common.Colors.White).getObject())));
 //BA.debugLineNum = 320;BA.debugLine="setlb.Height = livelb.Height";
mostCurrent._setlb.setHeight(mostCurrent._livelb.getHeight());
 //BA.debugLineNum = 321;BA.debugLine="valuelb.Height = livelb.Height";
mostCurrent._valuelb.setHeight(mostCurrent._livelb.getHeight());
 //BA.debugLineNum = 322;BA.debugLine="twodlb.Height = livelb.Height";
mostCurrent._twodlb.setHeight(mostCurrent._livelb.getHeight());
 //BA.debugLineNum = 323;BA.debugLine="livelb.Gravity=Gravity.CENTER";
mostCurrent._livelb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 324;BA.debugLine="setlb.Gravity=Gravity.CENTER";
mostCurrent._setlb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 325;BA.debugLine="valuelb.Gravity=Gravity.CENTER";
mostCurrent._valuelb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 326;BA.debugLine="twodlb.Gravity=Gravity.CENTER";
mostCurrent._twodlb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 327;BA.debugLine="p.Height = livelb.Height";
_p.setHeight(mostCurrent._livelb.getHeight());
 //BA.debugLineNum = 328;BA.debugLine="cv.Height = livelb.Height";
_cv.setHeight(mostCurrent._livelb.getHeight());
 //BA.debugLineNum = 329;BA.debugLine="numbarheight  = cv.Height+10dip";
_numbarheight = (int) (_cv.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 330;BA.debugLine="Return pbase";
if (true) return _pbase;
 //BA.debugLineNum = 331;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.CSBuilder  _numcs(String _typ,String _num,int _c) throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 333;BA.debugLine="Sub numcs(typ As String,num As String,c As Int) As";
 //BA.debugLineNum = 334;BA.debugLine="Dim cs As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 335;BA.debugLine="cs.Initialize.Color(Colors.Gray).Size(mycode.text";
_cs.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.Gray).Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (5)))).Typeface((android.graphics.Typeface)(mostCurrent._mycode._defaultfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Append(BA.ObjectToCharSequence(_typ+anywheresoftware.b4a.keywords.Common.CRLF)).Pop();
 //BA.debugLineNum = 336;BA.debugLine="cs.Color(c).Size(mycode.textsize(7)).Typeface(myc";
_cs.Color(_c).Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (7)))).Typeface((android.graphics.Typeface)(mostCurrent._mycode._semibold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Append(BA.ObjectToCharSequence(_num)).PopAll();
 //BA.debugLineNum = 337;BA.debugLine="Return cs";
if (true) return _cs;
 //BA.debugLineNum = 338;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.PanelWrapper  _othermessage(String _profile,String _sms,String _name,String _id,int _tp) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
ir.aghajari.retrofitglide.Amir_Glide _glide = null;
anywheresoftware.b4a.objects.ImageViewWrapper _img = null;
anywheresoftware.b4a.objects.LabelWrapper _lb = null;
anywheresoftware.b4a.objects.ButtonWrapper _smsitem = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 157;BA.debugLine="Sub OtherMessage(profile As String,sms As String,n";
 //BA.debugLineNum = 158;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 159;BA.debugLine="p.Initialize(\"p\")";
_p.Initialize(mostCurrent.activityBA,"p");
 //BA.debugLineNum = 160;BA.debugLine="Dim glide As Amir_Glide";
_glide = new ir.aghajari.retrofitglide.Amir_Glide();
 //BA.debugLineNum = 161;BA.debugLine="Dim img As ImageView";
_img = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 162;BA.debugLine="glide.Initializer";
_glide.Initializer(processBA);
 //BA.debugLineNum = 163;BA.debugLine="img.Initialize(\"\")";
_img.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 164;BA.debugLine="Dim lb As Label";
_lb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 165;BA.debugLine="lb.Initialize(\"\")";
_lb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 166;BA.debugLine="glide.Load(profile).Apply(glide.RO.CircleCrop).In";
_glide.Load((Object)(_profile)).Apply(_glide.getRO().CircleCrop()).Into((android.widget.ImageView)(_img.getObject()));
 //BA.debugLineNum = 167;BA.debugLine="lb.Text = OtherMessageCs(name,sms)";
_lb.setText(BA.ObjectToCharSequence(_othermessagecs(_name,_sms).getObject()));
 //BA.debugLineNum = 168;BA.debugLine="p.AddView(img,5dip,0,40dip,40dip)";
_p.AddView((android.view.View)(_img.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 169;BA.debugLine="p.AddView(lb,img.Width+img.Left+5dip,0,100%x-(lb.";
_p.AddView((android.view.View)(_lb.getObject()),(int) (_img.getWidth()+_img.getLeft()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))),(int) (0),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-(_lb.getLeft()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 170;BA.debugLine="lb.Height = su.MeasureMultilineTextHeight(lb,Othe";
_lb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_lb.getObject()),BA.ObjectToCharSequence(_othermessagecs(_name,_sms).getObject())));
 //BA.debugLineNum = 171;BA.debugLine="If lb.Height>img.Height Then";
if (_lb.getHeight()>_img.getHeight()) { 
 //BA.debugLineNum = 172;BA.debugLine="messageHeight = lb.Height";
_messageheight = _lb.getHeight();
 }else {
 //BA.debugLineNum = 174;BA.debugLine="messageHeight = img.Height";
_messageheight = _img.getHeight();
 };
 //BA.debugLineNum = 176;BA.debugLine="Dim smsitem As Button";
_smsitem = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 177;BA.debugLine="smsitem.Initialize(\"smsitem\")";
_smsitem.Initialize(mostCurrent.activityBA,"smsitem");
 //BA.debugLineNum = 178;BA.debugLine="smsitem.Color=Colors.Transparent";
_smsitem.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 179;BA.debugLine="p.AddView(smsitem,0,0,100%x,messageHeight)";
_p.AddView((android.view.View)(_smsitem.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),_messageheight);
 //BA.debugLineNum = 180;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 181;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 182;BA.debugLine="m.Put(\"id\",id)";
_m.Put((Object)("id"),(Object)(_id));
 //BA.debugLineNum = 183;BA.debugLine="m.Put(\"top\",tp)";
_m.Put((Object)("top"),(Object)(_tp));
 //BA.debugLineNum = 184;BA.debugLine="m.Put(\"message\",sms)";
_m.Put((Object)("message"),(Object)(_sms));
 //BA.debugLineNum = 185;BA.debugLine="m.Put(\"name\",name)";
_m.Put((Object)("name"),(Object)(_name));
 //BA.debugLineNum = 186;BA.debugLine="m.Put(\"profile_pic\",profile)";
_m.Put((Object)("profile_pic"),(Object)(_profile));
 //BA.debugLineNum = 187;BA.debugLine="smsitem.Tag  = m";
_smsitem.setTag((Object)(_m.getObject()));
 //BA.debugLineNum = 188;BA.debugLine="Return p";
if (true) return _p;
 //BA.debugLineNum = 189;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.CSBuilder  _othermessagecs(String _name,String _sms) throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 284;BA.debugLine="Sub OtherMessageCs(name As String,sms As String) A";
 //BA.debugLineNum = 285;BA.debugLine="Dim cs As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 286;BA.debugLine="cs.Initialize.Color(Colors.Yellow).Typeface(mycod";
_cs.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.Yellow).Typeface((android.graphics.Typeface)(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (7)))).Append(BA.ObjectToCharSequence(_name+anywheresoftware.b4a.keywords.Common.CRLF)).Pop();
 //BA.debugLineNum = 287;BA.debugLine="cs.Color(Colors.White).Typeface(mycode.mmfont).Si";
_cs.Color(anywheresoftware.b4a.keywords.Common.Colors.White).Typeface((android.graphics.Typeface)(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (7)))).Append(BA.ObjectToCharSequence(_sms)).PopAll();
 //BA.debugLineNum = 288;BA.debugLine="Return cs";
if (true) return _cs;
 //BA.debugLineNum = 289;BA.debugLine="End Sub";
return null;
}
public static String  _popup_click() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
 //BA.debugLineNum = 209;BA.debugLine="Sub popup_click";
 //BA.debugLineNum = 210;BA.debugLine="Dim p As Panel =Sender";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 211;BA.debugLine="p.RemoveView";
_p.RemoveView();
 //BA.debugLineNum = 212;BA.debugLine="p.Visible=False";
_p.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 213;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.ButtonWrapper  _popupitem(String _title,anywheresoftware.b4a.objects.collections.Map _tag) throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
 //BA.debugLineNum = 235;BA.debugLine="Sub popupitem(title As String,tag  As Map) As Butt";
 //BA.debugLineNum = 236;BA.debugLine="Dim b As Button";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 237;BA.debugLine="b.Initialize(\"popupitem\")";
_b.Initialize(mostCurrent.activityBA,"popupitem");
 //BA.debugLineNum = 238;BA.debugLine="b.Color=Colors.Transparent";
_b.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 239;BA.debugLine="b.Text = title";
_b.setText(BA.ObjectToCharSequence(_title));
 //BA.debugLineNum = 240;BA.debugLine="b.Tag  = tag";
_b.setTag((Object)(_tag.getObject()));
 //BA.debugLineNum = 241;BA.debugLine="b.TextColor=Colors.White";
_b.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 242;BA.debugLine="b.Background=mycode.btnbgdynamic(Colors.Transpare";
_b.setBackground((android.graphics.drawable.Drawable)(mostCurrent._mycode._btnbgdynamic /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.Colors.Transparent,anywheresoftware.b4a.keywords.Common.Colors.DarkGray,(int) (0)).getObject()));
 //BA.debugLineNum = 243;BA.debugLine="Return b";
if (true) return _b;
 //BA.debugLineNum = 244;BA.debugLine="End Sub";
return null;
}
public static String  _popupitem_click() throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 249;BA.debugLine="Sub popupitem_click";
 //BA.debugLineNum = 251;BA.debugLine="Dim b As Button = Sender";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 252;BA.debugLine="Dim m As Map = b.Tag";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_b.getTag()));
 //BA.debugLineNum = 253;BA.debugLine="Select b.Text";
switch (BA.switchObjectToInt(_b.getText(),"Report","Block")) {
case 0: {
 //BA.debugLineNum = 255;BA.debugLine="ppopup.Visible=False";
mostCurrent._ppopup.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 256;BA.debugLine="report_details.Mdata = m";
mostCurrent._report_details._mdata /*anywheresoftware.b4a.objects.collections.Map*/  = _m;
 //BA.debugLineNum = 257;BA.debugLine="StartActivity(report_details)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._report_details.getObject()));
 break; }
case 1: {
 //BA.debugLineNum = 259;BA.debugLine="ppopup.Visible=False";
mostCurrent._ppopup.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 260;BA.debugLine="AddBlock(m)";
_addblock(_m);
 break; }
}
;
 //BA.debugLineNum = 263;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.PanelWrapper  _popuppn(int _tp,anywheresoftware.b4a.objects.collections.Map _mp) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _pbase = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
int _i = 0;
 //BA.debugLineNum = 215;BA.debugLine="Sub popuppn(tp As Int,mp As Map) As Panel";
 //BA.debugLineNum = 217;BA.debugLine="ppopup.Initialize(\"popup\")";
mostCurrent._ppopup.Initialize(mostCurrent.activityBA,"popup");
 //BA.debugLineNum = 218;BA.debugLine="Dim pbase As Panel";
_pbase = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 219;BA.debugLine="pbase.Initialize(\"\")";
_pbase.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 220;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 221;BA.debugLine="cd.Initialize(mycode.naviColor,10dip)";
_cd.Initialize(mostCurrent._mycode._navicolor /*int*/ ,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 222;BA.debugLine="pbase.Background = cd";
_pbase.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 223;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 224;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 225;BA.debugLine="ls.AddAll(Array As String(\"Report\",\"Block\"))";
_ls.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"Report","Block"}));
 //BA.debugLineNum = 226;BA.debugLine="ppopup.AddView(pbase,50dip,tp,100dip,80dip)";
mostCurrent._ppopup.AddView((android.view.View)(_pbase.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),_tp,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (80)));
 //BA.debugLineNum = 227;BA.debugLine="Dim tp As Int = 40dip";
_tp = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40));
 //BA.debugLineNum = 228;BA.debugLine="For i  = 0 To ls.Size -1";
{
final int step12 = 1;
final int limit12 = (int) (_ls.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit12 ;_i = _i + step12 ) {
 //BA.debugLineNum = 229;BA.debugLine="pbase.AddView(popupitem(ls.Get(i),mp),0,tp*i,100";
_pbase.AddView((android.view.View)(_popupitem(BA.ObjectToString(_ls.Get(_i)),_mp).getObject()),(int) (0),(int) (_tp*_i),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 }
};
 //BA.debugLineNum = 232;BA.debugLine="Return ppopup";
if (true) return mostCurrent._ppopup;
 //BA.debugLineNum = 233;BA.debugLine="End Sub";
return null;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Dim livesse As sseconnector";
_livesse = new com.burma.royal2d.sseconnector();
 //BA.debugLineNum = 8;BA.debugLine="Dim timer As Timer";
_timer = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 9;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 10;BA.debugLine="Dim messageHeight As Int";
_messageheight = 0;
 //BA.debugLineNum = 11;BA.debugLine="Dim isCall As Boolean";
_iscall = false;
 //BA.debugLineNum = 12;BA.debugLine="Dim livetimer As Timer";
_livetimer = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 13;BA.debugLine="Dim numbarheight As Int";
_numbarheight = 0;
 //BA.debugLineNum = 14;BA.debugLine="Dim typbarheight As Int";
_typbarheight = 0;
 //BA.debugLineNum = 15;BA.debugLine="Dim sendSMS As String = \"sendSMS\"";
_sendsms = "sendSMS";
 //BA.debugLineNum = 16;BA.debugLine="Dim loadsmscount As Int =0";
_loadsmscount = (int) (0);
 //BA.debugLineNum = 17;BA.debugLine="Dim chatsse As chatsseconnector";
_chatsse = new com.burma.royal2d.chatsseconnector();
 //BA.debugLineNum = 18;BA.debugLine="Dim top As Int = 10dip";
_top = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10));
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public static String  _profilebtn_click() throws Exception{
 //BA.debugLineNum = 112;BA.debugLine="Sub profilebtn_click";
 //BA.debugLineNum = 113;BA.debugLine="StartActivity(Profile_Activity)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._profile_activity.getObject()));
 //BA.debugLineNum = 114;BA.debugLine="End Sub";
return "";
}
public static String  _progresshide() throws Exception{
 //BA.debugLineNum = 458;BA.debugLine="Sub progressHide";
 //BA.debugLineNum = 459;BA.debugLine="LottieView.Visible=False";
mostCurrent._lottieview.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 460;BA.debugLine="End Sub";
return "";
}
public static String  _progressshow() throws Exception{
int _left = 0;
com.aghajari.axrlottie.AXrLottieDrawableBuilder _drawable = null;
 //BA.debugLineNum = 442;BA.debugLine="Sub progressShow";
 //BA.debugLineNum = 443;BA.debugLine="AXrLottie.Initialize";
mostCurrent._axrlottie.Initialize();
 //BA.debugLineNum = 444;BA.debugLine="LottieView.Initialize(\"\")";
mostCurrent._lottieview.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 445;BA.debugLine="Dim left As Int = (100%x-100dip)/2";
_left = (int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)))/(double)2);
 //BA.debugLineNum = 446;BA.debugLine="Dim top As Int  = (mainscv.Height-100dip)/2";
_top = (int) ((mostCurrent._mainscv.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)))/(double)2);
 //BA.debugLineNum = 447;BA.debugLine="mainscv.Panel.AddView(LottieView,left,top,100dip,";
mostCurrent._mainscv.getPanel().AddView((android.view.View)(mostCurrent._lottieview.getObject()),_left,_top,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)));
 //BA.debugLineNum = 448;BA.debugLine="Dim Drawable As AXrLottieDrawableBuilder";
_drawable = new com.aghajari.axrlottie.AXrLottieDrawableBuilder();
 //BA.debugLineNum = 449;BA.debugLine="Drawable.InitializeFromFile(File.DirAssets,\"loadi";
_drawable.InitializeFromFile(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"loading.json").SetAutoRepeat(_drawable.AUTO_REPEAT_INFINITE).SetAutoStart(anywheresoftware.b4a.keywords.Common.True).SetCacheEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 453;BA.debugLine="LottieView.SetLottieDrawable(Drawable.Build)";
mostCurrent._lottieview.SetLottieDrawable((com.aghajari.rlottie.AXrLottieDrawable)(_drawable.Build().getObject()));
 //BA.debugLineNum = 454;BA.debugLine="LottieView.BringToFront";
mostCurrent._lottieview.BringToFront();
 //BA.debugLineNum = 455;BA.debugLine="LottieView.Visible=True";
mostCurrent._lottieview.setVisible(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 456;BA.debugLine="End Sub";
return "";
}
public static String  _sendbtn_click() throws Exception{
 //BA.debugLineNum = 676;BA.debugLine="Sub sendbtn_click";
 //BA.debugLineNum = 677;BA.debugLine="sendmessage";
_sendmessage();
 //BA.debugLineNum = 678;BA.debugLine="End Sub";
return "";
}
public static String  _sendmessage() throws Exception{
com.burma.royal2d.httpjob _j = null;
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _json = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 651;BA.debugLine="Sub sendmessage";
 //BA.debugLineNum = 652;BA.debugLine="If edt.Text <>\"\" Then";
if ((mostCurrent._edt.getText()).equals("") == false) { 
 //BA.debugLineNum = 653;BA.debugLine="If File.Exists(File.DirInternal,\"user\") Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"user")) { 
 //BA.debugLineNum = 654;BA.debugLine="Dim j As HttpJob";
_j = new com.burma.royal2d.httpjob();
 //BA.debugLineNum = 655;BA.debugLine="j.Initialize(sendSMS,Starter)";
_j._initialize /*String*/ (processBA,_sendsms,(Object)(mostCurrent._starter.getObject()));
 //BA.debugLineNum = 656;BA.debugLine="Dim json As JSONGenerator";
_json = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
 //BA.debugLineNum = 657;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 658;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 659;BA.debugLine="m.Put(\"id\",mycode.getUserId(mycode.id))";
_m.Put((Object)("id"),(Object)(mostCurrent._mycode._getuserid /*String*/ (mostCurrent.activityBA,mostCurrent._mycode._id /*String*/ )));
 //BA.debugLineNum = 660;BA.debugLine="m.Put(\"name\",mycode.getUserId(mycode.name))";
_m.Put((Object)("name"),(Object)(mostCurrent._mycode._getuserid /*String*/ (mostCurrent.activityBA,mostCurrent._mycode._name /*String*/ )));
 //BA.debugLineNum = 661;BA.debugLine="m.Put(\"profile_pic\",mycode.getUserId(mycode.pro";
_m.Put((Object)("profile_pic"),(Object)(mostCurrent._mycode._getuserid /*String*/ (mostCurrent.activityBA,mostCurrent._mycode._profile_pic /*String*/ )));
 //BA.debugLineNum = 662;BA.debugLine="m.Put(\"message\",edt.Text)";
_m.Put((Object)("message"),(Object)(mostCurrent._edt.getText()));
 //BA.debugLineNum = 663;BA.debugLine="m.Put(\"create_at\",DateTime.Now)";
_m.Put((Object)("create_at"),(Object)(anywheresoftware.b4a.keywords.Common.DateTime.getNow()));
 //BA.debugLineNum = 664;BA.debugLine="edt.Text=\"\"";
mostCurrent._edt.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 665;BA.debugLine="json.Initialize(m)";
_json.Initialize(_m);
 //BA.debugLineNum = 666;BA.debugLine="j.PostString(Main.newsite&\"chat/sendmessage\",js";
_j._poststring /*String*/ (mostCurrent._main._newsite /*String*/ +"chat/sendmessage",_json.ToString());
 }else {
 //BA.debugLineNum = 668;BA.debugLine="ToastMessageShow(\"You Need To Login\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("You Need To Login"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 669;BA.debugLine="StartActivity(Login)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._login.getObject()));
 };
 }else {
 //BA.debugLineNum = 672;BA.debugLine="ToastMessageShow(\"please text something..\",False";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("please text something.."),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 674;BA.debugLine="End Sub";
return "";
}
public static boolean  _shoulddisplaymessage(anywheresoftware.b4a.objects.collections.Map _m) throws Exception{
boolean _b = false;
int _a = 0;
anywheresoftware.b4a.objects.collections.Map _mblock = null;
 //BA.debugLineNum = 536;BA.debugLine="Sub ShouldDisplayMessage(m As Map) As Boolean";
 //BA.debugLineNum = 537;BA.debugLine="Dim b As Boolean = True";
_b = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 538;BA.debugLine="If blockList.Size > 0 Then";
if (_blocklist().getSize()>0) { 
 //BA.debugLineNum = 539;BA.debugLine="For a = 0 To blockList.Size - 1";
{
final int step3 = 1;
final int limit3 = (int) (_blocklist().getSize()-1);
_a = (int) (0) ;
for (;_a <= limit3 ;_a = _a + step3 ) {
 //BA.debugLineNum = 540;BA.debugLine="Dim mblock As Map = blockList.Get(a)";
_mblock = new anywheresoftware.b4a.objects.collections.Map();
_mblock = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_blocklist().Get(_a)));
 //BA.debugLineNum = 541;BA.debugLine="If mblock.Get(mycode.id) = m.Get(mycode.id) The";
if ((_mblock.Get((Object)(mostCurrent._mycode._id /*String*/ ))).equals(_m.Get((Object)(mostCurrent._mycode._id /*String*/ )))) { 
 //BA.debugLineNum = 542;BA.debugLine="b=False";
_b = anywheresoftware.b4a.keywords.Common.False;
 }else {
 //BA.debugLineNum = 544;BA.debugLine="b=True";
_b = anywheresoftware.b4a.keywords.Common.True;
 };
 }
};
 };
 //BA.debugLineNum = 548;BA.debugLine="Return b";
if (true) return _b;
 //BA.debugLineNum = 549;BA.debugLine="End Sub";
return false;
}
public static String  _smsitem_click() throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4a.objects.PanelWrapper _pp = null;
 //BA.debugLineNum = 191;BA.debugLine="Sub smsitem_click";
 //BA.debugLineNum = 192;BA.debugLine="If File.Exists(File.DirInternal,\"user\") Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"user")) { 
 //BA.debugLineNum = 193;BA.debugLine="Dim b As Button = Sender";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 194;BA.debugLine="Dim m As Map =b.Tag";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_b.getTag()));
 //BA.debugLineNum = 195;BA.debugLine="Dim pp As Panel  = popuppn(m.Get(\"top\"),m)";
_pp = new anywheresoftware.b4a.objects.PanelWrapper();
_pp = _popuppn((int)(BA.ObjectToNumber(_m.Get((Object)("top")))),_m);
 //BA.debugLineNum = 196;BA.debugLine="pp.BringToFront";
_pp.BringToFront();
 //BA.debugLineNum = 197;BA.debugLine="If mainscv.Panel.Height < mainscv.Height Then";
if (mostCurrent._mainscv.getPanel().getHeight()<mostCurrent._mainscv.getHeight()) { 
 //BA.debugLineNum = 198;BA.debugLine="Log(\"here\")";
anywheresoftware.b4a.keywords.Common.LogImpl("23342343","here",0);
 //BA.debugLineNum = 199;BA.debugLine="Activity.AddView(pp,mainscv.Left,mainscv.Top,ma";
mostCurrent._activity.AddView((android.view.View)(_pp.getObject()),mostCurrent._mainscv.getLeft(),mostCurrent._mainscv.getTop(),mostCurrent._mainscv.getPanel().getWidth(),mostCurrent._mainscv.getHeight());
 }else {
 //BA.debugLineNum = 202;BA.debugLine="mainscv.Panel.AddView(pp,0,0,mainscv.Panel.Widt";
mostCurrent._mainscv.getPanel().AddView((android.view.View)(_pp.getObject()),(int) (0),(int) (0),mostCurrent._mainscv.getPanel().getWidth(),mostCurrent._mainscv.getPanel().getHeight());
 };
 }else {
 };
 //BA.debugLineNum = 207;BA.debugLine="End Sub";
return "";
}
public static String  _tm_tick() throws Exception{
 //BA.debugLineNum = 343;BA.debugLine="Sub tm_tick";
 //BA.debugLineNum = 344;BA.debugLine="If DateTime.Now - livesse.lastime > 6000*3  Then";
if (anywheresoftware.b4a.keywords.Common.DateTime.getNow()-_livesse._getlastime /*long*/ ()>6000*3) { 
 //BA.debugLineNum = 345;BA.debugLine="If livesse.IsInitialized Then";
if (_livesse.IsInitialized /*boolean*/ ()) { 
 //BA.debugLineNum = 346;BA.debugLine="livesse.Finish";
_livesse._finish /*String*/ ();
 //BA.debugLineNum = 347;BA.debugLine="livesse.Connect(Main.newsite)";
_livesse._connect /*String*/ (mostCurrent._main._newsite /*String*/ );
 }else {
 //BA.debugLineNum = 349;BA.debugLine="liveLoader";
_liveloader();
 };
 };
 //BA.debugLineNum = 352;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.PanelWrapper  _typingbar() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.PanelWrapper _devider = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
anywheresoftware.b4a.objects.ButtonWrapper _sendbtn = null;
 //BA.debugLineNum = 405;BA.debugLine="Sub typingBar As Panel";
 //BA.debugLineNum = 406;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 407;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 408;BA.debugLine="Dim devider As Panel";
_devider = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 409;BA.debugLine="devider.Initialize(\"\")";
_devider.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 410;BA.debugLine="devider.Color=mycode.naviColor";
_devider.setColor(mostCurrent._mycode._navicolor /*int*/ );
 //BA.debugLineNum = 412;BA.debugLine="edt.Initialize(\"\")";
mostCurrent._edt.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 413;BA.debugLine="edt.HintColor=Colors.Gray";
mostCurrent._edt.setHintColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 //BA.debugLineNum = 414;BA.debugLine="edt.TextColor=Colors.White";
mostCurrent._edt.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 415;BA.debugLine="edt.Hint=\"Type Something..\"";
mostCurrent._edt.setHint("Type Something..");
 //BA.debugLineNum = 416;BA.debugLine="edt.InputType = edt.INPUT_TYPE_TEXT";
mostCurrent._edt.setInputType(mostCurrent._edt.INPUT_TYPE_TEXT);
 //BA.debugLineNum = 417;BA.debugLine="edt.Typeface=mycode.mmfont";
mostCurrent._edt.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 418;BA.debugLine="edt.TextSize=mycode.textsize(8)";
mostCurrent._edt.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (8)));
 //BA.debugLineNum = 419;BA.debugLine="edt.Gravity=Gravity.LEFT+Gravity.TOP";
mostCurrent._edt.setGravity((int) (anywheresoftware.b4a.keywords.Common.Gravity.LEFT+anywheresoftware.b4a.keywords.Common.Gravity.TOP));
 //BA.debugLineNum = 420;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 421;BA.debugLine="cd.Initialize(mycode.naviColor,10dip)";
_cd.Initialize(mostCurrent._mycode._navicolor /*int*/ ,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 422;BA.debugLine="edt.Background=cd";
mostCurrent._edt.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 423;BA.debugLine="Dim sendbtn As Button";
_sendbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 424;BA.debugLine="sendbtn.Initialize(\"sendbtn\")";
_sendbtn.Initialize(mostCurrent.activityBA,"sendbtn");
 //BA.debugLineNum = 425;BA.debugLine="sendbtn.Background = mycode.btnbg(False)";
_sendbtn.setBackground((android.graphics.drawable.Drawable)(mostCurrent._mycode._btnbg /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False).getObject()));
 //BA.debugLineNum = 426;BA.debugLine="sendbtn.Typeface = Typeface.CreateNew(Typeface.MA";
_sendbtn.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.CreateNew(anywheresoftware.b4a.keywords.Common.Typeface.getMATERIALICONS(),anywheresoftware.b4a.keywords.Common.Typeface.STYLE_BOLD));
 //BA.debugLineNum = 427;BA.debugLine="sendbtn.Text =Chr(0xE163)";
_sendbtn.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Chr(((int)0xe163))));
 //BA.debugLineNum = 428;BA.debugLine="sendbtn.TextColor=Colors.White";
_sendbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 429;BA.debugLine="sendbtn.TextSize = mycode.textsize(10)";
_sendbtn.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (10)));
 //BA.debugLineNum = 430;BA.debugLine="p.AddView(edt,5dip,5dip,100%x-55dip,45dip)";
_p.AddView((android.view.View)(mostCurrent._edt.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (55))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (45)));
 //BA.debugLineNum = 431;BA.debugLine="p.AddView(sendbtn,100%x-45dip,5dip,40dip,40dip)";
_p.AddView((android.view.View)(_sendbtn.getObject()),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (45))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 432;BA.debugLine="sendbtn.Width = edt.Height";
_sendbtn.setWidth(mostCurrent._edt.getHeight());
 //BA.debugLineNum = 433;BA.debugLine="sendbtn.Height = edt.Height";
_sendbtn.setHeight(mostCurrent._edt.getHeight());
 //BA.debugLineNum = 434;BA.debugLine="sendbtn.Left = 100%x-(sendbtn.Width+5dip)";
_sendbtn.setLeft((int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-(_sendbtn.getWidth()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)))));
 //BA.debugLineNum = 436;BA.debugLine="typbarheight = edt.Height+10dip";
_typbarheight = (int) (mostCurrent._edt.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 437;BA.debugLine="Return p";
if (true) return _p;
 //BA.debugLineNum = 438;BA.debugLine="End Sub";
return null;
}
}
