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

public class gift_activity extends Activity implements B4AActivity{
	public static gift_activity mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "com.burma.royal2d", "com.burma.royal2d.gift_activity");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (gift_activity).");
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
		activityBA = new BA(this, layout, processBA, "com.burma.royal2d", "com.burma.royal2d.gift_activity");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.burma.royal2d.gift_activity", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (gift_activity) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (gift_activity) Resume **");
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
		return gift_activity.class;
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
            BA.LogInfo("** Activity (gift_activity) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (gift_activity) Pause event (activity is not paused). **");
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
            gift_activity mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (gift_activity) Resume **");
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
public static String _giftdataloader = "";
public static boolean _iscall = false;
public static anywheresoftware.b4a.objects.Timer _progresstimer = null;
public static anywheresoftware.b4a.objects.Timer _timer = null;
public anywheresoftware.b4a.admobwrapper.AdViewWrapper.InterstitialAdWrapper _inter = null;
public com.burma.royal2d.as_cardslider _as_cardslider1 = null;
public anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scv = null;
public anywheresoftware.b4a.objects.PanelWrapper _progresspn = null;
public com.burma.royal2d.main _main = null;
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
anywheresoftware.b4a.objects.PanelWrapper _pn = null;
int _i = 0;
anywheresoftware.b4a.objects.B4XViewWrapper _xpnl = null;
anywheresoftware.b4a.objects.ImageViewWrapper _img = null;
 //BA.debugLineNum = 24;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 26;BA.debugLine="Activity.color=mycode.bgcolor";
mostCurrent._activity.setColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 27;BA.debugLine="mycode.SETnavigationcolor";
mostCurrent._mycode._setnavigationcolor /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 28;BA.debugLine="Activity.AddView(mycode.appbar(\"လက်ဆောင်ဂဏန်းများ";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._mycode._appbar /*anywheresoftware.b4a.objects.PanelWrapper*/ (mostCurrent.activityBA,"လက်ဆောင်ဂဏန်းများ",anywheresoftware.b4a.keywords.Common.False).getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),mostCurrent._mycode._appbarheight /*int*/ );
 //BA.debugLineNum = 29;BA.debugLine="scv.Initialize(1000dip)";
mostCurrent._scv.Initialize(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1000)));
 //BA.debugLineNum = 30;BA.debugLine="Activity.AddView(scv,0,mycode.appbarheight+10dip,";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._scv.getObject()),(int) (0),(int) (mostCurrent._mycode._appbarheight /*int*/ +anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),(int) (mostCurrent._mycode._activityheight /*int*/ -(mostCurrent._mycode._appbarheight /*int*/ +anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)))));
 //BA.debugLineNum = 32;BA.debugLine="Dim pn As Panel";
_pn = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 33;BA.debugLine="pn.Initialize(\"\")";
_pn.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 34;BA.debugLine="scv.Panel.AddView(pn,0,0,100%x,100%x/2)";
mostCurrent._scv.getPanel().AddView((android.view.View)(_pn.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)/(double)2));
 //BA.debugLineNum = 35;BA.debugLine="pn.LoadLayout(\"frm_main\")";
_pn.LoadLayout("frm_main",mostCurrent.activityBA);
 //BA.debugLineNum = 36;BA.debugLine="AS_CardSlider1.ItemWidth = 100%x-10dip";
mostCurrent._as_cardslider1._setitemwidth /*float*/ ((float) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 37;BA.debugLine="For i = 0 To 5 -1";
{
final int step11 = 1;
final int limit11 = (int) (5-1);
_i = (int) (0) ;
for (;_i <= limit11 ;_i = _i + step11 ) {
 //BA.debugLineNum = 39;BA.debugLine="Dim xpnl As B4XView = xui.CreatePanel(\"\")";
_xpnl = new anywheresoftware.b4a.objects.B4XViewWrapper();
_xpnl = mostCurrent._xui.CreatePanel(processBA,"");
 //BA.debugLineNum = 40;BA.debugLine="xpnl.SetLayoutAnimated(0,0,0,AS_CardSlider1.Item";
_xpnl.SetLayoutAnimated((int) (0),(int) (0),(int) (0),(int) (mostCurrent._as_cardslider1._getitemwidth /*float*/ ()),mostCurrent._as_cardslider1._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .getHeight());
 //BA.debugLineNum = 41;BA.debugLine="xpnl.LoadLayout(\"frm_Item1\")";
_xpnl.LoadLayout("frm_Item1",mostCurrent.activityBA);
 //BA.debugLineNum = 42;BA.debugLine="Dim img As ImageView = xpnl.GetView(0)";
_img = new anywheresoftware.b4a.objects.ImageViewWrapper();
_img = (anywheresoftware.b4a.objects.ImageViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ImageViewWrapper(), (android.widget.ImageView)(_xpnl.GetView((int) (0)).getObject()));
 //BA.debugLineNum = 43;BA.debugLine="img.SetBackgroundImage(LoadBitmap(File.DirAssets";
_img.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"wb.png").getObject()));
 //BA.debugLineNum = 44;BA.debugLine="img.Gravity=Gravity.FILL";
_img.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 45;BA.debugLine="AS_CardSlider1.AddPage(xpnl,i)";
mostCurrent._as_cardslider1._addpage /*String*/ (_xpnl,(Object)(_i));
 }
};
 //BA.debugLineNum = 47;BA.debugLine="pn.Height=170dip";
_pn.setHeight(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (170)));
 //BA.debugLineNum = 50;BA.debugLine="progresspn.Initialize(\"\")";
mostCurrent._progresspn.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 51;BA.debugLine="Activity.AddView(progresspn,0,mycode.appbarheight";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._progresspn.getObject()),(int) (0),mostCurrent._mycode._appbarheight /*int*/ ,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)));
 //BA.debugLineNum = 52;BA.debugLine="progresstimer.Initialize(\"ptimer\",50)";
_progresstimer.Initialize(processBA,"ptimer",(long) (50));
 //BA.debugLineNum = 53;BA.debugLine="progresstimer.Enabled=True";
_progresstimer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 55;BA.debugLine="timer.Initialize(\"timer\",3000)";
_timer.Initialize(processBA,"timer",(long) (3000));
 //BA.debugLineNum = 56;BA.debugLine="timer.Enabled=True";
_timer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 57;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 85;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 86;BA.debugLine="isCall = False";
_iscall = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 87;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
com.burma.royal2d.httpjob _job = null;
 //BA.debugLineNum = 67;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 69;BA.debugLine="If inter.IsInitialized Then";
if (mostCurrent._inter.IsInitialized()) { 
 //BA.debugLineNum = 70;BA.debugLine="If  inter.Ready = False Then";
if (mostCurrent._inter.getReady()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 71;BA.debugLine="inter.LoadAd";
mostCurrent._inter.LoadAd(mostCurrent.activityBA);
 };
 }else {
 //BA.debugLineNum = 74;BA.debugLine="inter.Initialize(\"inter\",Main.interUnit)";
mostCurrent._inter.Initialize(mostCurrent.activityBA,"inter",mostCurrent._main._interunit /*String*/ );
 //BA.debugLineNum = 75;BA.debugLine="inter.LoadAd";
mostCurrent._inter.LoadAd(mostCurrent.activityBA);
 };
 //BA.debugLineNum = 79;BA.debugLine="isCall = True";
_iscall = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 80;BA.debugLine="Dim job As HttpJob";
_job = new com.burma.royal2d.httpjob();
 //BA.debugLineNum = 81;BA.debugLine="job.Initialize(giftdataloader,Starter)";
_job._initialize /*String*/ (processBA,_giftdataloader,(Object)(mostCurrent._starter.getObject()));
 //BA.debugLineNum = 82;BA.debugLine="job.Download(Main.site&\"?q=SELECT * FROM `gift` W";
_job._download /*String*/ (mostCurrent._main._site /*String*/ +"?q=SELECT * FROM `gift` WHERE 1;");
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
 //BA.debugLineNum = 187;BA.debugLine="Sub backbtn_click";
 //BA.debugLineNum = 188;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 189;BA.debugLine="End Sub";
return "";
}
public static String  _giftclick_click() throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
 //BA.debugLineNum = 192;BA.debugLine="Sub giftclick_click";
 //BA.debugLineNum = 193;BA.debugLine="Dim b As Button =Sender";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 194;BA.debugLine="gift_imageview.data= b.Tag";
mostCurrent._gift_imageview._data /*anywheresoftware.b4a.objects.collections.Map*/  = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_b.getTag()));
 //BA.debugLineNum = 195;BA.debugLine="If inter.Ready Then";
if (mostCurrent._inter.getReady()) { 
 //BA.debugLineNum = 196;BA.debugLine="inter.Show";
mostCurrent._inter.Show(mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 204;BA.debugLine="StartActivity(gift_imageview)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._gift_imageview.getObject()));
 };
 //BA.debugLineNum = 206;BA.debugLine="End Sub";
return "";
}
public static String  _giftsuccess(String _data) throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _json = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
anywheresoftware.b4a.objects.collections.List _lweekly = null;
anywheresoftware.b4a.objects.collections.List _ldaily = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4a.objects.PanelWrapper _dailypn = null;
anywheresoftware.b4a.objects.LabelWrapper _lb1 = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
int _top1 = 0;
int _a = 0;
anywheresoftware.b4a.objects.ButtonWrapper _buton = null;
anywheresoftware.b4a.objects.PanelWrapper _weeklypn = null;
anywheresoftware.b4a.objects.LabelWrapper _lb = null;
int _top = 0;
 //BA.debugLineNum = 96;BA.debugLine="Sub giftsuccess(data As String)";
 //BA.debugLineNum = 97;BA.debugLine="progresspn.Visible=False";
mostCurrent._progresspn.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 98;BA.debugLine="progresstimer.Enabled=False";
_progresstimer.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 99;BA.debugLine="Dim json As JSONParser";
_json = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 100;BA.debugLine="json.Initialize(data)";
_json.Initialize(_data);
 //BA.debugLineNum = 101;BA.debugLine="Dim ls As List = json.NextArray";
_ls = new anywheresoftware.b4a.objects.collections.List();
_ls = _json.NextArray();
 //BA.debugLineNum = 102;BA.debugLine="Dim lweekly As List";
_lweekly = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 103;BA.debugLine="Dim ldaily As List";
_ldaily = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 104;BA.debugLine="lweekly.Initialize";
_lweekly.Initialize();
 //BA.debugLineNum = 105;BA.debugLine="ldaily.Initialize";
_ldaily.Initialize();
 //BA.debugLineNum = 106;BA.debugLine="If ls.Size > 0 Then";
if (_ls.getSize()>0) { 
 //BA.debugLineNum = 107;BA.debugLine="For i = 0 To ls.Size -1";
{
final int step11 = 1;
final int limit11 = (int) (_ls.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit11 ;_i = _i + step11 ) {
 //BA.debugLineNum = 108;BA.debugLine="Dim m As Map = ls.Get(i)";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_ls.Get(_i)));
 //BA.debugLineNum = 109;BA.debugLine="If m.Get(\"category\") = \"weekly\" Then";
if ((_m.Get((Object)("category"))).equals((Object)("weekly"))) { 
 //BA.debugLineNum = 110;BA.debugLine="lweekly.Add(m)";
_lweekly.Add((Object)(_m.getObject()));
 };
 //BA.debugLineNum = 112;BA.debugLine="If m.Get(\"category\") = \"daily\" Then";
if ((_m.Get((Object)("category"))).equals((Object)("daily"))) { 
 //BA.debugLineNum = 113;BA.debugLine="ldaily.Add(m)";
_ldaily.Add((Object)(_m.getObject()));
 };
 }
};
 //BA.debugLineNum = 117;BA.debugLine="Dim dailypn As Panel";
_dailypn = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 118;BA.debugLine="dailypn.Initialize(\"\")";
_dailypn.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 119;BA.debugLine="scv.Panel.AddView(dailypn,0,180dip,100%x,50dip)";
mostCurrent._scv.getPanel().AddView((android.view.View)(_dailypn.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (180)),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 120;BA.debugLine="Dim lb1 As Label";
_lb1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 121;BA.debugLine="lb1.Initialize(\"\")";
_lb1.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 122;BA.debugLine="lb1.Text = \"တစ်ရက်စာလက်ဆောင်\"";
_lb1.setText(BA.ObjectToCharSequence("တစ်ရက်စာလက်ဆောင်"));
 //BA.debugLineNum = 123;BA.debugLine="lb1.Typeface =mycode.mmfont";
_lb1.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 124;BA.debugLine="lb1.TextColor=Colors.White";
_lb1.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 125;BA.debugLine="lb1.TextSize =mycode.textsize(9)";
_lb1.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (9)));
 //BA.debugLineNum = 126;BA.debugLine="dailypn.AddView(lb1,10dip,10dip,100%x-20dip,10di";
_dailypn.AddView((android.view.View)(_lb1.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 127;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 128;BA.debugLine="lb1.Height= su.MeasureMultilineTextHeight(lb1,lb";
_lb1.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_lb1.getObject()),BA.ObjectToCharSequence(_lb1.getText())));
 //BA.debugLineNum = 129;BA.debugLine="Dim top1 As Int = lb1.Height+lb1.Top +10dip";
_top1 = (int) (_lb1.getHeight()+_lb1.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 130;BA.debugLine="If ldaily.Size >  0 Then";
if (_ldaily.getSize()>0) { 
 //BA.debugLineNum = 131;BA.debugLine="For a = 0 To ldaily.Size -1";
{
final int step34 = 1;
final int limit34 = (int) (_ldaily.getSize()-1);
_a = (int) (0) ;
for (;_a <= limit34 ;_a = _a + step34 ) {
 //BA.debugLineNum = 132;BA.debugLine="Dim buton As Button";
_buton = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 134;BA.debugLine="buton.Initialize(\"giftclick\")";
_buton.Initialize(mostCurrent.activityBA,"giftclick");
 //BA.debugLineNum = 135;BA.debugLine="buton.Background=mycode.btnbg2";
_buton.setBackground((android.graphics.drawable.Drawable)(mostCurrent._mycode._btnbg2 /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 136;BA.debugLine="dailypn.AddView(buton,10dip,top1,100%x-20dip,5";
_dailypn.AddView((android.view.View)(_buton.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_top1,(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 137;BA.debugLine="Dim m As Map = ldaily.Get(a)";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_ldaily.Get(_a)));
 //BA.debugLineNum = 138;BA.debugLine="buton.Text = m.Get(\"title_mm\")";
_buton.setText(BA.ObjectToCharSequence(_m.Get((Object)("title_mm"))));
 //BA.debugLineNum = 139;BA.debugLine="buton.Typeface=mycode.mmfont";
_buton.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 140;BA.debugLine="buton.TextSize = mycode.textsize(7)";
_buton.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (7)));
 //BA.debugLineNum = 141;BA.debugLine="buton.Tag=m";
_buton.setTag((Object)(_m.getObject()));
 //BA.debugLineNum = 142;BA.debugLine="buton.TextColor=Colors.White";
_buton.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 143;BA.debugLine="top1 = top1 +buton.Height+10dip";
_top1 = (int) (_top1+_buton.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 }
};
 //BA.debugLineNum = 145;BA.debugLine="dailypn.Height= top1";
_dailypn.setHeight(_top1);
 };
 //BA.debugLineNum = 150;BA.debugLine="Dim weeklypn As Panel";
_weeklypn = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 151;BA.debugLine="weeklypn.Initialize(\"\")";
_weeklypn.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 152;BA.debugLine="scv.Panel.AddView(weeklypn,0,dailypn.Height+dail";
mostCurrent._scv.getPanel().AddView((android.view.View)(_weeklypn.getObject()),(int) (0),(int) (_dailypn.getHeight()+_dailypn.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 153;BA.debugLine="Dim lb As Label";
_lb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 154;BA.debugLine="lb.Initialize(\"\")";
_lb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 155;BA.debugLine="lb.TextColor=Colors.White";
_lb.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 156;BA.debugLine="lb.Text= \"တစ်ပတ်စာလက်ဆောင်\"";
_lb.setText(BA.ObjectToCharSequence("တစ်ပတ်စာလက်ဆောင်"));
 //BA.debugLineNum = 157;BA.debugLine="lb.Typeface=mycode.mmfont";
_lb.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 158;BA.debugLine="lb.TextSize =mycode.textsize(9)";
_lb.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (9)));
 //BA.debugLineNum = 159;BA.debugLine="weeklypn.AddView(lb,10dip,10dip,100%x-20dip,10di";
_weeklypn.AddView((android.view.View)(_lb.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 160;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 161;BA.debugLine="lb.Height= su.MeasureMultilineTextHeight(lb,\"wee";
_lb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_lb.getObject()),BA.ObjectToCharSequence("weeklytips")));
 //BA.debugLineNum = 162;BA.debugLine="Dim top As Int = lb.Height+lb.Top+10dip";
_top = (int) (_lb.getHeight()+_lb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 163;BA.debugLine="If lweekly.Size>0 Then";
if (_lweekly.getSize()>0) { 
 //BA.debugLineNum = 164;BA.debugLine="For i = 0 To lweekly.Size-1";
{
final int step63 = 1;
final int limit63 = (int) (_lweekly.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit63 ;_i = _i + step63 ) {
 //BA.debugLineNum = 165;BA.debugLine="Dim buton As Button";
_buton = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 167;BA.debugLine="buton.Initialize(\"giftclick\")";
_buton.Initialize(mostCurrent.activityBA,"giftclick");
 //BA.debugLineNum = 168;BA.debugLine="buton.Background=mycode.btnbg2";
_buton.setBackground((android.graphics.drawable.Drawable)(mostCurrent._mycode._btnbg2 /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 169;BA.debugLine="weeklypn.AddView(buton,10dip,top,weeklypn.Width";
_weeklypn.AddView((android.view.View)(_buton.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_top,(int) (_weeklypn.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 170;BA.debugLine="Dim m As Map = lweekly.Get(i)";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_lweekly.Get(_i)));
 //BA.debugLineNum = 171;BA.debugLine="buton.Text = m.Get(\"title_mm\")";
_buton.setText(BA.ObjectToCharSequence(_m.Get((Object)("title_mm"))));
 //BA.debugLineNum = 172;BA.debugLine="buton.Typeface=mycode.mmfont";
_buton.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 173;BA.debugLine="buton.TextSize = mycode.textsize(7)";
_buton.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (7)));
 //BA.debugLineNum = 174;BA.debugLine="buton.Tag=m";
_buton.setTag((Object)(_m.getObject()));
 //BA.debugLineNum = 175;BA.debugLine="buton.TextColor=Colors.White";
_buton.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 176;BA.debugLine="top = top +buton.Height+10dip";
_top = (int) (_top+_buton.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 }
};
 //BA.debugLineNum = 178;BA.debugLine="weeklypn.Height= top";
_weeklypn.setHeight(_top);
 };
 //BA.debugLineNum = 182;BA.debugLine="scv.Panel.Height=weeklypn.Height+weeklypn.Top+10";
mostCurrent._scv.getPanel().setHeight((int) (_weeklypn.getHeight()+_weeklypn.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 };
 //BA.debugLineNum = 185;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 16;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 17;BA.debugLine="Dim inter As InterstitialAd";
mostCurrent._inter = new anywheresoftware.b4a.admobwrapper.AdViewWrapper.InterstitialAdWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Private AS_CardSlider1 As AS_CardSlider";
mostCurrent._as_cardslider1 = new com.burma.royal2d.as_cardslider();
 //BA.debugLineNum = 19;BA.debugLine="Dim xui As XUI";
mostCurrent._xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 20;BA.debugLine="Dim scv As ScrollView";
mostCurrent._scv = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Dim progresspn As Panel";
mostCurrent._progresspn = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return "";
}
public static String  _inter_adclosed() throws Exception{
 //BA.debugLineNum = 217;BA.debugLine="Sub inter_AdClosed";
 //BA.debugLineNum = 218;BA.debugLine="Log(\"Closed\")";
anywheresoftware.b4a.keywords.Common.LogImpl("236503553","Closed",0);
 //BA.debugLineNum = 219;BA.debugLine="StartActivity(gift_imageview)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._gift_imageview.getObject()));
 //BA.debugLineNum = 220;BA.debugLine="End Sub";
return "";
}
public static String  _inter_adopened() throws Exception{
 //BA.debugLineNum = 222;BA.debugLine="Sub inter_AdOpened";
 //BA.debugLineNum = 223;BA.debugLine="Log(\"Opened\")";
anywheresoftware.b4a.keywords.Common.LogImpl("236569089","Opened",0);
 //BA.debugLineNum = 224;BA.debugLine="End Sub";
return "";
}
public static String  _inter_failedtoreceivead(String _errorcode) throws Exception{
 //BA.debugLineNum = 213;BA.debugLine="Sub inter_FailedToReceiveAd (ErrorCode As String)";
 //BA.debugLineNum = 214;BA.debugLine="Log(\"Failed: \" & ErrorCode)";
anywheresoftware.b4a.keywords.Common.LogImpl("236438017","Failed: "+_errorcode,0);
 //BA.debugLineNum = 215;BA.debugLine="End Sub";
return "";
}
public static String  _inter_receivead() throws Exception{
 //BA.debugLineNum = 208;BA.debugLine="Sub inter_ReceiveAd";
 //BA.debugLineNum = 209;BA.debugLine="Log(\"IAd received. Now wait for the right moment";
anywheresoftware.b4a.keywords.Common.LogImpl("236372481","IAd received. Now wait for the right moment to show the ad.",0);
 //BA.debugLineNum = 211;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim giftdataloader As String = \"giftdataloader\"";
_giftdataloader = "giftdataloader";
 //BA.debugLineNum = 10;BA.debugLine="Dim isCall  As Boolean";
_iscall = false;
 //BA.debugLineNum = 11;BA.debugLine="Dim progresstimer As Timer";
_progresstimer = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 12;BA.debugLine="Dim timer As Timer";
_timer = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 14;BA.debugLine="End Sub";
return "";
}
public static String  _ptimer_tick() throws Exception{
 //BA.debugLineNum = 59;BA.debugLine="Sub ptimer_tick";
 //BA.debugLineNum = 60;BA.debugLine="If progresspn.Width =100%x Then";
if (mostCurrent._progresspn.getWidth()==anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)) { 
 //BA.debugLineNum = 61;BA.debugLine="progresspn.Width =0";
mostCurrent._progresspn.setWidth((int) (0));
 }else {
 //BA.debugLineNum = 63;BA.debugLine="progresspn.Width =progresspn.Width+10%x";
mostCurrent._progresspn.setWidth((int) (mostCurrent._progresspn.getWidth()+anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (10),mostCurrent.activityBA)));
 };
 //BA.debugLineNum = 65;BA.debugLine="End Sub";
return "";
}
public static String  _timer_tick() throws Exception{
 //BA.debugLineNum = 88;BA.debugLine="Sub timer_tick";
 //BA.debugLineNum = 89;BA.debugLine="If AS_CardSlider1.Index = AS_CardSlider1.Size -1";
if (mostCurrent._as_cardslider1._getindex /*int*/ ()==mostCurrent._as_cardslider1._getsize /*int*/ ()-1) { 
 //BA.debugLineNum = 90;BA.debugLine="AS_CardSlider1.Index= 0";
mostCurrent._as_cardslider1._setindex /*int*/ ((int) (0));
 }else {
 //BA.debugLineNum = 92;BA.debugLine="AS_CardSlider1.NextPage";
mostCurrent._as_cardslider1._nextpage /*String*/ ();
 };
 //BA.debugLineNum = 94;BA.debugLine="End Sub";
return "";
}
}
