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

public class history extends androidx.fragment.app.FragmentActivity implements B4AActivity{
	public static history mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "com.burma.royal2d", "com.burma.royal2d.history");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (history).");
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
		activityBA = new BA(this, layout, processBA, "com.burma.royal2d", "com.burma.royal2d.history");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.burma.royal2d.history", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (history) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (history) Resume **");
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
		return history.class;
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
            BA.LogInfo("** Activity (history) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (history) Pause event (activity is not paused). **");
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
            history mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (history) Resume **");
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
public static String _historyloader = "";
public static boolean _hiscallable = false;
public static int _panelheight = 0;
public static int _mpanelheight = 0;
public static anywheresoftware.b4a.objects.Timer _progresstimer = null;
public static int _miheight = 0;
public anywheresoftware.b4a.objects.PanelWrapper _progressbg = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scv = null;
public anywheresoftware.b4a.objects.LabelWrapper _mitimelb = null;
public anywheresoftware.b4a.objects.LabelWrapper _modernlb = null;
public anywheresoftware.b4a.objects.LabelWrapper _internetlb = null;
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
com.burma.royal2d.httpjob _job = null;
anywheresoftware.b4a.objects.PanelWrapper _appbar = null;
anywheresoftware.b4a.objects.ButtonWrapper _searchbtn = null;
 //BA.debugLineNum = 26;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 28;BA.debugLine="mycode.SETnavigationcolor";
mostCurrent._mycode._setnavigationcolor /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 29;BA.debugLine="Activity.color=mycode.bgcolor";
mostCurrent._activity.setColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 31;BA.debugLine="Dim job As HttpJob";
_job = new com.burma.royal2d.httpjob();
 //BA.debugLineNum = 32;BA.debugLine="job.Initialize(historyloader,Starter)";
_job._initialize /*String*/ (processBA,_historyloader,(Object)(mostCurrent._starter.getObject()));
 //BA.debugLineNum = 33;BA.debugLine="job.Download(Main.site&\"?q=SELECT * FROM `dailyre";
_job._download /*String*/ (mostCurrent._main._site /*String*/ +"?q=SELECT * FROM `dailyresults` ORDER BY date DESC;");
 //BA.debugLineNum = 34;BA.debugLine="Dim appbar As Panel = mycode.appbar(\"2D History\",";
_appbar = new anywheresoftware.b4a.objects.PanelWrapper();
_appbar = mostCurrent._mycode._appbar /*anywheresoftware.b4a.objects.PanelWrapper*/ (mostCurrent.activityBA,"2D History",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 35;BA.debugLine="Dim searchbtn As Button";
_searchbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 36;BA.debugLine="searchbtn.Initialize(\"searchbtn\")";
_searchbtn.Initialize(mostCurrent.activityBA,"searchbtn");
 //BA.debugLineNum = 37;BA.debugLine="searchbtn.Typeface = Typeface.FONTAWESOME";
_searchbtn.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME());
 //BA.debugLineNum = 38;BA.debugLine="searchbtn.TextColor=Colors.White";
_searchbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 39;BA.debugLine="searchbtn.TextSize = mycode.textsize(7)";
_searchbtn.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (7)));
 //BA.debugLineNum = 40;BA.debugLine="searchbtn.Background = mycode.btnbgdynamic(Colors";
_searchbtn.setBackground((android.graphics.drawable.Drawable)(mostCurrent._mycode._btnbgdynamic /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.Colors.Transparent,mostCurrent._mycode._bgcolor /*int*/ ,(int) (180)).getObject()));
 //BA.debugLineNum = 41;BA.debugLine="searchbtn.Text=Chr(0xF073)";
_searchbtn.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf073))));
 //BA.debugLineNum = 43;BA.debugLine="Activity.AddView(appbar,0,mycode.appbartop,100%x,";
mostCurrent._activity.AddView((android.view.View)(_appbar.getObject()),(int) (0),mostCurrent._mycode._appbartop /*int*/ ,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),mostCurrent._mycode._appbarheight /*int*/ );
 //BA.debugLineNum = 45;BA.debugLine="scv.Initialize(1000dip)";
mostCurrent._scv.Initialize(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1000)));
 //BA.debugLineNum = 46;BA.debugLine="Activity.AddView(scv,0,mycode.appbarheight+2dip,1";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._scv.getObject()),(int) (0),(int) (mostCurrent._mycode._appbarheight /*int*/ +anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),(int) (mostCurrent._mycode._activityheight /*int*/ -mostCurrent._mycode._appbarheight /*int*/ ));
 //BA.debugLineNum = 48;BA.debugLine="progressShow";
_progressshow();
 //BA.debugLineNum = 49;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 55;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 56;BA.debugLine="hiscallable=False";
_hiscallable = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 57;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 51;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 52;BA.debugLine="hiscallable=True";
_hiscallable = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return "";
}
public static void  _addview11(String _data) throws Exception{
ResumableSub_addview11 rsub = new ResumableSub_addview11(null,_data);
rsub.resume(processBA, null);
}
public static class ResumableSub_addview11 extends BA.ResumableSub {
public ResumableSub_addview11(com.burma.royal2d.history parent,String _data) {
this.parent = parent;
this._data = _data;
}
com.burma.royal2d.history parent;
String _data;
anywheresoftware.b4a.objects.collections.JSONParser _json = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
int _topp = 0;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _m = null;
String _ms = "";
String _mv = "";
String _mr = "";
String _es = "";
String _ev = "";
String _er = "";
String _date = "";
String _nm = "";
String _tm = "";
String _ni = "";
String _ti = "";
int step11;
int limit11;

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
 //BA.debugLineNum = 245;BA.debugLine="Log(data)";
anywheresoftware.b4a.keywords.Common.LogImpl("231916033",_data,0);
 //BA.debugLineNum = 246;BA.debugLine="Try";
if (true) break;

case 1:
//try
this.state = 14;
this.catchState = 13;
this.state = 3;
if (true) break;

case 3:
//C
this.state = 4;
this.catchState = 13;
 //BA.debugLineNum = 247;BA.debugLine="progressbg.Visible=False";
parent.mostCurrent._progressbg.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 248;BA.debugLine="progresstimer.Enabled=False";
parent._progresstimer.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 249;BA.debugLine="Dim json As JSONParser";
_json = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 250;BA.debugLine="json.Initialize(data)";
_json.Initialize(_data);
 //BA.debugLineNum = 251;BA.debugLine="Dim ls As List = json.NextArray";
_ls = new anywheresoftware.b4a.objects.collections.List();
_ls = _json.NextArray();
 //BA.debugLineNum = 252;BA.debugLine="Dim topp As Int = 10dip";
_topp = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10));
 //BA.debugLineNum = 253;BA.debugLine="scv.Panel.RemoveAllViews";
parent.mostCurrent._scv.getPanel().RemoveAllViews();
 //BA.debugLineNum = 254;BA.debugLine="If ls.Size>0 Then";
if (true) break;

case 4:
//if
this.state = 11;
if (_ls.getSize()>0) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 255;BA.debugLine="For i = 0 To ls.Size-1";
if (true) break;

case 7:
//for
this.state = 10;
step11 = 1;
limit11 = (int) (_ls.getSize()-1);
_i = (int) (0) ;
this.state = 15;
if (true) break;

case 15:
//C
this.state = 10;
if ((step11 > 0 && _i <= limit11) || (step11 < 0 && _i >= limit11)) this.state = 9;
if (true) break;

case 16:
//C
this.state = 15;
_i = ((int)(0 + _i + step11)) ;
if (true) break;

case 9:
//C
this.state = 16;
 //BA.debugLineNum = 256;BA.debugLine="Dim m As Map = ls.Get(i)";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_ls.Get(_i)));
 //BA.debugLineNum = 257;BA.debugLine="Dim ms ,mv,mr,es,ev,er,date,nm,tm,ni,ti As Str";
_ms = "";
_mv = "";
_mr = "";
_es = "";
_ev = "";
_er = "";
_date = "";
_nm = "";
_tm = "";
_ni = "";
_ti = "";
 //BA.debugLineNum = 258;BA.debugLine="ms = m.Get(\"1200set\")";
_ms = BA.ObjectToString(_m.Get((Object)("1200set")));
 //BA.debugLineNum = 259;BA.debugLine="mv=m.Get(\"1200value\")";
_mv = BA.ObjectToString(_m.Get((Object)("1200value")));
 //BA.debugLineNum = 260;BA.debugLine="mr=m.get(\"1200\")";
_mr = BA.ObjectToString(_m.Get((Object)("1200")));
 //BA.debugLineNum = 261;BA.debugLine="es=m.get(\"430set\")";
_es = BA.ObjectToString(_m.Get((Object)("430set")));
 //BA.debugLineNum = 262;BA.debugLine="ev=m.get(\"430value\")";
_ev = BA.ObjectToString(_m.Get((Object)("430value")));
 //BA.debugLineNum = 263;BA.debugLine="er=m.Get(\"430\")";
_er = BA.ObjectToString(_m.Get((Object)("430")));
 //BA.debugLineNum = 264;BA.debugLine="date= m.Get(\"date\")";
_date = BA.ObjectToString(_m.Get((Object)("date")));
 //BA.debugLineNum = 265;BA.debugLine="Log(ms)";
anywheresoftware.b4a.keywords.Common.LogImpl("231916053",_ms,0);
 //BA.debugLineNum = 266;BA.debugLine="Log(mv)";
anywheresoftware.b4a.keywords.Common.LogImpl("231916054",_mv,0);
 //BA.debugLineNum = 267;BA.debugLine="Log(mr)";
anywheresoftware.b4a.keywords.Common.LogImpl("231916055",_mr,0);
 //BA.debugLineNum = 268;BA.debugLine="Log(m.Get(\"date\"))";
anywheresoftware.b4a.keywords.Common.LogImpl("231916056",BA.ObjectToString(_m.Get((Object)("date"))),0);
 //BA.debugLineNum = 269;BA.debugLine="nm=m.get(\"930modern\")";
_nm = BA.ObjectToString(_m.Get((Object)("930modern")));
 //BA.debugLineNum = 270;BA.debugLine="tm=m.Get(\"200modern\")";
_tm = BA.ObjectToString(_m.Get((Object)("200modern")));
 //BA.debugLineNum = 271;BA.debugLine="ni =m.Get(\"930internet\")";
_ni = BA.ObjectToString(_m.Get((Object)("930internet")));
 //BA.debugLineNum = 272;BA.debugLine="ti =m.Get(\"200internet\")";
_ti = BA.ObjectToString(_m.Get((Object)("200internet")));
 //BA.debugLineNum = 273;BA.debugLine="Log(\"here>\")";
anywheresoftware.b4a.keywords.Common.LogImpl("231916061","here>",0);
 //BA.debugLineNum = 274;BA.debugLine="scv.Panel.AddView(mresultspn( _ 		ms,mv,mr,es,";
parent.mostCurrent._scv.getPanel().AddView((android.view.View)(_mresultspn(_ms,_mv,_mr,_es,_ev,_er,_date,_nm,_ni,_tm,_ti).getObject()),(int) (0),_topp,parent.mostCurrent._scv.getWidth(),parent._mpanelheight);
 //BA.debugLineNum = 277;BA.debugLine="Log(\"here<<\")";
anywheresoftware.b4a.keywords.Common.LogImpl("231916065","here<<",0);
 //BA.debugLineNum = 278;BA.debugLine="topp=topp+mpanelheight";
_topp = (int) (_topp+parent._mpanelheight);
 //BA.debugLineNum = 279;BA.debugLine="scv.Panel.Height=topp+10dip";
parent.mostCurrent._scv.getPanel().setHeight((int) (_topp+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 280;BA.debugLine="Sleep(100)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (100));
this.state = 17;
return;
case 17:
//C
this.state = 16;
;
 if (true) break;
if (true) break;

case 10:
//C
this.state = 11;
;
 if (true) break;

case 11:
//C
this.state = 14;
;
 if (true) break;

case 13:
//C
this.state = 14;
this.catchState = 0;
 //BA.debugLineNum = 284;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("231916072",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 if (true) break;
if (true) break;

case 14:
//C
this.state = -1;
this.catchState = 0;
;
 //BA.debugLineNum = 286;BA.debugLine="End Sub";
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
public static String  _backbtn_click() throws Exception{
 //BA.debugLineNum = 289;BA.debugLine="Sub backbtn_click";
 //BA.debugLineNum = 290;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 291;BA.debugLine="End Sub";
return "";
}
public static String  _can_oncanceldate() throws Exception{
 //BA.debugLineNum = 105;BA.debugLine="Sub can_OnCancelDate ()";
 //BA.debugLineNum = 107;BA.debugLine="End Sub";
return "";
}
public static String  _date_ondateset(int _year,int _monthofyear,int _dayofmonth) throws Exception{
String _date = "";
com.burma.royal2d.httpjob _job = null;
 //BA.debugLineNum = 97;BA.debugLine="Sub Date_OnDateSet (year As Int ,monthOfYear As In";
 //BA.debugLineNum = 98;BA.debugLine="Dim date As String = year&\"/\"&(NumberFormat(month";
_date = BA.NumberToString(_year)+"/"+(anywheresoftware.b4a.keywords.Common.NumberFormat(_monthofyear,(int) (2),(int) (0)))+"/"+anywheresoftware.b4a.keywords.Common.NumberFormat(_dayofmonth,(int) (2),(int) (0));
 //BA.debugLineNum = 99;BA.debugLine="progressShow";
_progressshow();
 //BA.debugLineNum = 100;BA.debugLine="Dim job As HttpJob";
_job = new com.burma.royal2d.httpjob();
 //BA.debugLineNum = 101;BA.debugLine="job.Initialize(historyloader,Starter)";
_job._initialize /*String*/ (processBA,_historyloader,(Object)(mostCurrent._starter.getObject()));
 //BA.debugLineNum = 102;BA.debugLine="job.Download(Main.site&\"?q=SELECT * FROM `dailyre";
_job._download /*String*/ (mostCurrent._main._site /*String*/ +"?q=SELECT * FROM `dailyresults` WHERE date='"+_date+"'");
 //BA.debugLineNum = 103;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 18;BA.debugLine="Dim progressbg As Panel";
mostCurrent._progressbg = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Dim scv As ScrollView";
mostCurrent._scv = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 20;BA.debugLine="Dim mitimelb As Label";
mostCurrent._mitimelb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 21;BA.debugLine="Dim modernlb,internetlb As Label";
mostCurrent._modernlb = new anywheresoftware.b4a.objects.LabelWrapper();
mostCurrent._internetlb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 24;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.PanelWrapper  _mornetpanel(String _nm,String _tm,String _ni,String _ti) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _pn = null;
int _w = 0;
anywheresoftware.b4a.objects.StringUtils _su = null;
 //BA.debugLineNum = 293;BA.debugLine="Sub mornetpanel(nm As String,tm As String,ni As St";
 //BA.debugLineNum = 294;BA.debugLine="Dim pn As Panel";
_pn = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 295;BA.debugLine="pn.Initialize(\"\")";
_pn.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 296;BA.debugLine="pn.Elevation=10dip";
_pn.setElevation((float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 297;BA.debugLine="pn.Background=mycode.btnbg2";
_pn.setBackground((android.graphics.drawable.Drawable)(mostCurrent._mycode._btnbg2 /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 298;BA.debugLine="modernlb.Initialize(\"\")";
mostCurrent._modernlb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 299;BA.debugLine="internetlb.Initialize(\"\")";
mostCurrent._internetlb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 300;BA.debugLine="Dim w As Int = (100%x-40dip)/2";
_w = (int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)))/(double)2);
 //BA.debugLineNum = 301;BA.debugLine="mitimelb.Initialize(\"\")";
mostCurrent._mitimelb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 302;BA.debugLine="pn.AddView(mitimelb,10dip,10dip,w,20dip)";
_pn.AddView((android.view.View)(mostCurrent._mitimelb.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_w,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 303;BA.debugLine="mitimelb.Text=mycode.mitimecs";
mostCurrent._mitimelb.setText(BA.ObjectToCharSequence(mostCurrent._mycode._mitimecs /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA).getObject()));
 //BA.debugLineNum = 304;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 305;BA.debugLine="mitimelb.Height=su.MeasureMultilineTextHeight(mit";
mostCurrent._mitimelb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(mostCurrent._mitimelb.getObject()),BA.ObjectToCharSequence(mostCurrent._mycode._mitimecs /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA).getObject())));
 //BA.debugLineNum = 307;BA.debugLine="pn.AddView(modernlb,w,10dip,w/2,20dip)";
_pn.AddView((android.view.View)(mostCurrent._modernlb.getObject()),_w,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_w/(double)2),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 308;BA.debugLine="modernlb.Text=mycode.moderninernetcs(nm,tm)";
mostCurrent._modernlb.setText(BA.ObjectToCharSequence(mostCurrent._mycode._moderninernetcs /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,_nm,_tm).getObject()));
 //BA.debugLineNum = 309;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 310;BA.debugLine="modernlb.Height=su.MeasureMultilineTextHeight(mod";
mostCurrent._modernlb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(mostCurrent._modernlb.getObject()),BA.ObjectToCharSequence(mostCurrent._mycode._moderninernetcs /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,"---","---").getObject())));
 //BA.debugLineNum = 312;BA.debugLine="pn.AddView(internetlb,modernlb.Left+modernlb.Widt";
_pn.AddView((android.view.View)(mostCurrent._internetlb.getObject()),(int) (mostCurrent._modernlb.getLeft()+mostCurrent._modernlb.getWidth()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_w/(double)2),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 313;BA.debugLine="internetlb.Text=mycode.moderninernetcs(ni,ti)";
mostCurrent._internetlb.setText(BA.ObjectToCharSequence(mostCurrent._mycode._moderninernetcs /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,_ni,_ti).getObject()));
 //BA.debugLineNum = 314;BA.debugLine="internetlb.Height = modernlb.Height";
mostCurrent._internetlb.setHeight(mostCurrent._modernlb.getHeight());
 //BA.debugLineNum = 315;BA.debugLine="modernlb.Gravity=Gravity.CENTER";
mostCurrent._modernlb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 316;BA.debugLine="internetlb.Gravity=Gravity.CENTER";
mostCurrent._internetlb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 317;BA.debugLine="miheight=internetlb.Height+20dip";
_miheight = (int) (mostCurrent._internetlb.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 319;BA.debugLine="Return pn";
if (true) return _pn;
 //BA.debugLineNum = 320;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.PanelWrapper  _mresultspn(String _ms,String _mv,String _mr,String _es,String _ev,String _er,String _date,String _nm,String _tm,String _ni,String _ti) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _pn = null;
anywheresoftware.b4a.objects.LabelWrapper _datelb = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
 //BA.debugLineNum = 152;BA.debugLine="Sub mresultspn(ms As String,mv As String,mr As Str";
 //BA.debugLineNum = 153;BA.debugLine="Dim pn As Panel";
_pn = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 154;BA.debugLine="pn.Initialize(\"\")";
_pn.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 155;BA.debugLine="Dim datelb As Label";
_datelb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 156;BA.debugLine="datelb.Initialize(\"\")";
_datelb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 157;BA.debugLine="datelb.Text= date";
_datelb.setText(BA.ObjectToCharSequence(_date));
 //BA.debugLineNum = 158;BA.debugLine="datelb.Gravity=Gravity.CENTER";
_datelb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 159;BA.debugLine="pn.AddView(datelb,0,0,100%x,40dip)";
_pn.AddView((android.view.View)(_datelb.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 160;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 161;BA.debugLine="datelb.TextSize=16";
_datelb.setTextSize((float) (16));
 //BA.debugLineNum = 162;BA.debugLine="datelb.Height=su.MeasureMultilineTextHeight(datel";
_datelb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_datelb.getObject()),BA.ObjectToCharSequence(_datelb.getText())));
 //BA.debugLineNum = 163;BA.debugLine="datelb.TextColor=Colors.White";
_datelb.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 164;BA.debugLine="pn.AddView(resultpn( _  	ms,mv,mr,es,ev,er,nm,ni,";
_pn.AddView((android.view.View)(_resultpn(_ms,_mv,_mr,_es,_ev,_er,_nm,_ni,_tm,_ti).getObject()),(int) (0),(int) (_datelb.getHeight()+_datelb.getTop()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),_panelheight);
 //BA.debugLineNum = 167;BA.debugLine="mpanelheight=panelheight+datelb.Height+datelb.Top";
_mpanelheight = (int) (_panelheight+_datelb.getHeight()+_datelb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 168;BA.debugLine="Return pn";
if (true) return _pn;
 //BA.debugLineNum = 169;BA.debugLine="End Sub";
return null;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Dim historyloader As String = \"historyloader\"";
_historyloader = "historyloader";
 //BA.debugLineNum = 9;BA.debugLine="Dim hiscallable As Boolean";
_hiscallable = false;
 //BA.debugLineNum = 10;BA.debugLine="Dim panelheight As Int";
_panelheight = 0;
 //BA.debugLineNum = 11;BA.debugLine="Dim mpanelheight As Int";
_mpanelheight = 0;
 //BA.debugLineNum = 12;BA.debugLine="Dim progresstimer As Timer";
_progresstimer = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 13;BA.debugLine="Dim miheight As Int";
_miheight = 0;
 //BA.debugLineNum = 14;BA.debugLine="End Sub";
return "";
}
public static String  _progressshow() throws Exception{
 //BA.debugLineNum = 67;BA.debugLine="Sub progressShow";
 //BA.debugLineNum = 68;BA.debugLine="progressbg.Initialize(\"\")";
mostCurrent._progressbg.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 69;BA.debugLine="Activity.AddView(progressbg,0,mycode.appbarHeight";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._progressbg.getObject()),(int) (0),mostCurrent._mycode._appbarheight /*int*/ ,(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)));
 //BA.debugLineNum = 70;BA.debugLine="progressbg.Color=Colors.Yellow";
mostCurrent._progressbg.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 72;BA.debugLine="progresstimer.Initialize(\"progresstimer\",50)";
_progresstimer.Initialize(processBA,"progresstimer",(long) (50));
 //BA.debugLineNum = 73;BA.debugLine="progresstimer.Enabled=True";
_progresstimer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 74;BA.debugLine="End Sub";
return "";
}
public static String  _progresstimer_tick() throws Exception{
 //BA.debugLineNum = 59;BA.debugLine="Sub progresstimer_tick";
 //BA.debugLineNum = 60;BA.debugLine="If progressbg.Width = 100%x Then";
if (mostCurrent._progressbg.getWidth()==anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)) { 
 //BA.debugLineNum = 61;BA.debugLine="progressbg.Width = 0";
mostCurrent._progressbg.setWidth((int) (0));
 }else {
 //BA.debugLineNum = 63;BA.debugLine="progressbg.Width = progressbg.Width+5%x";
mostCurrent._progressbg.setWidth((int) (mostCurrent._progressbg.getWidth()+anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (5),mostCurrent.activityBA)));
 };
 //BA.debugLineNum = 65;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.PanelWrapper  _resultpn(String _mset,String _mvalue,String _mresult,String _eset,String _evalue,String _eresult,String _nm,String _ni,String _tm,String _ti) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _mainpn = null;
anywheresoftware.b4a.objects.LabelWrapper _morningresultlb = null;
anywheresoftware.b4a.objects.LabelWrapper _eveningresultlb = null;
anywheresoftware.b4a.objects.PanelWrapper _morningresultpn = null;
anywheresoftware.b4a.objects.PanelWrapper _eveningresultpn = null;
anywheresoftware.b4a.objects.PanelWrapper _mdevider = null;
anywheresoftware.b4a.objects.PanelWrapper _edevider = null;
anywheresoftware.b4a.objects.LabelWrapper _mtimelb = null;
anywheresoftware.b4a.objects.LabelWrapper _etimelb = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
int _w = 0;
anywheresoftware.b4a.objects.StringUtils _su = null;
 //BA.debugLineNum = 171;BA.debugLine="Sub resultpn( mset As String,mvalue As String,mres";
 //BA.debugLineNum = 172;BA.debugLine="Dim mainpn As Panel";
_mainpn = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 173;BA.debugLine="mainpn.Initialize(\"\")";
_mainpn.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 175;BA.debugLine="Dim morningresultlb,eveningresultlb As Label";
_morningresultlb = new anywheresoftware.b4a.objects.LabelWrapper();
_eveningresultlb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 176;BA.debugLine="Dim morningresultpn,eveningresultpn As Panel";
_morningresultpn = new anywheresoftware.b4a.objects.PanelWrapper();
_eveningresultpn = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 177;BA.debugLine="Dim mdevider,edevider As Panel";
_mdevider = new anywheresoftware.b4a.objects.PanelWrapper();
_edevider = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 178;BA.debugLine="Dim mtimelb,etimelb As Label";
_mtimelb = new anywheresoftware.b4a.objects.LabelWrapper();
_etimelb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 180;BA.debugLine="morningresultpn.Initialize(\"\")";
_morningresultpn.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 181;BA.debugLine="morningresultpn.Elevation=10dip";
_morningresultpn.setElevation((float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 182;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 183;BA.debugLine="cd.Initialize(mycode.naviColor,14dip)";
_cd.Initialize(mostCurrent._mycode._navicolor /*int*/ ,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (14)));
 //BA.debugLineNum = 184;BA.debugLine="morningresultpn.Background=cd";
_morningresultpn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 185;BA.debugLine="Dim w As Int = (100%x-30dip)/2";
_w = (int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)))/(double)2);
 //BA.debugLineNum = 186;BA.debugLine="mainpn.AddView(morningresultpn,10dip,10dip,w,50di";
_mainpn.AddView((android.view.View)(_morningresultpn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_w,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 187;BA.debugLine="mtimelb.Initialize(\"\")";
_mtimelb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 188;BA.debugLine="mdevider.Initialize(\"\")";
_mdevider.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 189;BA.debugLine="morningresultpn.AddView(mtimelb,0,5dip,morningres";
_morningresultpn.AddView((android.view.View)(_mtimelb.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),_morningresultpn.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 190;BA.debugLine="mtimelb.Gravity=Gravity.CENTER";
_mtimelb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 191;BA.debugLine="mtimelb.Text=timecsb(\"12:01\",Chr(0xF185),\"PM\")";
_mtimelb.setText(BA.ObjectToCharSequence(_timecsb("12:01",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf185))),"PM").getObject()));
 //BA.debugLineNum = 192;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 193;BA.debugLine="mtimelb.Height=su.MeasureMultilineTextHeight(mtim";
_mtimelb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_mtimelb.getObject()),BA.ObjectToCharSequence(_timecsb("12:01 PM",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf185))),"PM").getObject())));
 //BA.debugLineNum = 194;BA.debugLine="morningresultpn.AddView(mdevider,10dip,mtimelb.He";
_morningresultpn.AddView((android.view.View)(_mdevider.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_mtimelb.getHeight()+_mtimelb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))),(int) (_morningresultpn.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)));
 //BA.debugLineNum = 195;BA.debugLine="mdevider.Color=Colors.Gray";
_mdevider.setColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 //BA.debugLineNum = 197;BA.debugLine="morningresultlb.Initialize(\"\")";
_morningresultlb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 198;BA.debugLine="morningresultpn.AddView(morningresultlb,10dip,mde";
_morningresultpn.AddView((android.view.View)(_morningresultlb.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_mdevider.getHeight()+_mdevider.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))),(int) (_morningresultpn.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 199;BA.debugLine="morningresultlb.Gravity=Gravity.CENTER";
_morningresultlb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 200;BA.debugLine="Log(mset)";
anywheresoftware.b4a.keywords.Common.LogImpl("231850525",_mset,0);
 //BA.debugLineNum = 201;BA.debugLine="Log(mvalue)";
anywheresoftware.b4a.keywords.Common.LogImpl("231850526",_mvalue,0);
 //BA.debugLineNum = 202;BA.debugLine="Log(mresult)";
anywheresoftware.b4a.keywords.Common.LogImpl("231850527",_mresult,0);
 //BA.debugLineNum = 203;BA.debugLine="morningresultlb.Text=resultscs(mset,mvalue,mresul";
_morningresultlb.setText(BA.ObjectToCharSequence(_resultscs(_mset,_mvalue,_mresult).getObject()));
 //BA.debugLineNum = 204;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 205;BA.debugLine="morningresultlb.Height=su.MeasureMultilineTextHei";
_morningresultlb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_morningresultlb.getObject()),BA.ObjectToCharSequence(_resultscs("1245.12","31465.88","25").getObject())));
 //BA.debugLineNum = 206;BA.debugLine="morningresultpn.Height=morningresultlb.Height+mor";
_morningresultpn.setHeight((int) (_morningresultlb.getHeight()+_morningresultlb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 //BA.debugLineNum = 207;BA.debugLine="Log(\"error break point\")";
anywheresoftware.b4a.keywords.Common.LogImpl("231850532","error break point",0);
 //BA.debugLineNum = 209;BA.debugLine="eveningresultpn.Initialize(\"\")";
_eveningresultpn.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 210;BA.debugLine="eveningresultpn.Elevation=10dip";
_eveningresultpn.setElevation((float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 211;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 212;BA.debugLine="cd.Initialize(mycode.naviColor,14dip)";
_cd.Initialize(mostCurrent._mycode._navicolor /*int*/ ,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (14)));
 //BA.debugLineNum = 213;BA.debugLine="eveningresultpn.Background=cd";
_eveningresultpn.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 214;BA.debugLine="Dim w As Int = (100%x-30dip)/2";
_w = (int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)))/(double)2);
 //BA.debugLineNum = 215;BA.debugLine="mainpn.AddView(eveningresultpn,morningresultpn.Wi";
_mainpn.AddView((android.view.View)(_eveningresultpn.getObject()),(int) (_morningresultpn.getWidth()+_morningresultpn.getLeft()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),_morningresultpn.getTop(),_w,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 217;BA.debugLine="etimelb.Initialize(\"\")";
_etimelb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 218;BA.debugLine="edevider.Initialize(\"\")";
_edevider.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 219;BA.debugLine="eveningresultpn.AddView(etimelb,0,5dip,eveningres";
_eveningresultpn.AddView((android.view.View)(_etimelb.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),_eveningresultpn.getWidth(),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 220;BA.debugLine="etimelb.Gravity=Gravity.CENTER";
_etimelb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 221;BA.debugLine="etimelb.Text=timecsb(\"4:30\",Chr(0xF186),\"PM\")";
_etimelb.setText(BA.ObjectToCharSequence(_timecsb("4:30",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf186))),"PM").getObject()));
 //BA.debugLineNum = 222;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 223;BA.debugLine="etimelb.Height=su.MeasureMultilineTextHeight(etim";
_etimelb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_etimelb.getObject()),BA.ObjectToCharSequence(_timecsb("12:01 PM",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf185))),"PM").getObject())));
 //BA.debugLineNum = 224;BA.debugLine="eveningresultpn.AddView(edevider,10dip,etimelb.He";
_eveningresultpn.AddView((android.view.View)(_edevider.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_etimelb.getHeight()+_etimelb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))),(int) (_eveningresultpn.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)));
 //BA.debugLineNum = 225;BA.debugLine="edevider.Color=Colors.Gray";
_edevider.setColor(anywheresoftware.b4a.keywords.Common.Colors.Gray);
 //BA.debugLineNum = 227;BA.debugLine="eveningresultlb.Initialize(\"\")";
_eveningresultlb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 228;BA.debugLine="eveningresultpn.AddView(eveningresultlb,10dip,ede";
_eveningresultpn.AddView((android.view.View)(_eveningresultlb.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_edevider.getHeight()+_edevider.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2))),(int) (_eveningresultpn.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 229;BA.debugLine="eveningresultlb.Gravity=Gravity.CENTER";
_eveningresultlb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 230;BA.debugLine="eveningresultlb.Text=resultscs(eset,evalue,eresul";
_eveningresultlb.setText(BA.ObjectToCharSequence(_resultscs(_eset,_evalue,_eresult).getObject()));
 //BA.debugLineNum = 231;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 232;BA.debugLine="eveningresultlb.Height=su.MeasureMultilineTextHei";
_eveningresultlb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_eveningresultlb.getObject()),BA.ObjectToCharSequence(_resultscs("1245.12","31465.88","25").getObject())));
 //BA.debugLineNum = 233;BA.debugLine="eveningresultpn.Height=eveningresultlb.Height+eve";
_eveningresultpn.setHeight((int) (_eveningresultlb.getHeight()+_eveningresultlb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 //BA.debugLineNum = 235;BA.debugLine="mainpn.AddView(mornetpanel(nm,ni,tm,ti),10dip,eve";
_mainpn.AddView((android.view.View)(_mornetpanel(_nm,_ni,_tm,_ti).getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_eveningresultpn.getHeight()+_eveningresultpn.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),(int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)))),_miheight);
 //BA.debugLineNum = 237;BA.debugLine="panelheight = miheight+eveningresultpn.Height+eve";
_panelheight = (int) (_miheight+_eveningresultpn.getHeight()+_eveningresultpn.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 238;BA.debugLine="mainpn.Height=panelheight";
_mainpn.setHeight(_panelheight);
 //BA.debugLineNum = 239;BA.debugLine="Return mainpn";
if (true) return _mainpn;
 //BA.debugLineNum = 240;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.CSBuilder  _resultscs(String _set,String _value,String _twod) throws Exception{
String _sett = "";
String _setresult = "";
String _valuel = "";
String _valuer = "";
String _valueresult = "";
anywheresoftware.b4a.objects.CSBuilder _cs = null;
anywheresoftware.b4a.objects.CSBuilder _cs1 = null;
anywheresoftware.b4a.keywords.constants.TypefaceWrapper _tfthin = null;
 //BA.debugLineNum = 110;BA.debugLine="Sub resultscs (set As String, value As String,twod";
 //BA.debugLineNum = 111;BA.debugLine="Dim sett As String";
_sett = "";
 //BA.debugLineNum = 112;BA.debugLine="Dim setresult As String";
_setresult = "";
 //BA.debugLineNum = 113;BA.debugLine="Dim valuel As String";
_valuel = "";
 //BA.debugLineNum = 114;BA.debugLine="Dim valuer As String";
_valuer = "";
 //BA.debugLineNum = 115;BA.debugLine="Dim valueresult As String";
_valueresult = "";
 //BA.debugLineNum = 117;BA.debugLine="If set.Length >0 Then";
if (_set.length()>0) { 
 //BA.debugLineNum = 118;BA.debugLine="setresult = set.SubString(set.Length-1)";
_setresult = _set.substring((int) (_set.length()-1));
 //BA.debugLineNum = 119;BA.debugLine="sett  = set.SubString2(0,set.Length-1)";
_sett = _set.substring((int) (0),(int) (_set.length()-1));
 };
 //BA.debugLineNum = 121;BA.debugLine="If value.Length>0 Then";
if (_value.length()>0) { 
 //BA.debugLineNum = 122;BA.debugLine="valuel = value.SubString(value.IndexOf(\".\"))";
_valuel = _value.substring(_value.indexOf("."));
 //BA.debugLineNum = 123;BA.debugLine="valuer = value.Replace(valuel,\"\")";
_valuer = _value.replace(_valuel,"");
 //BA.debugLineNum = 124;BA.debugLine="valueresult = valuer.SubString(valuer.Length-1)";
_valueresult = _valuer.substring((int) (_valuer.length()-1));
 //BA.debugLineNum = 125;BA.debugLine="valuer = valuer.SubString2(0,valuer.Length-1)";
_valuer = _valuer.substring((int) (0),(int) (_valuer.length()-1));
 };
 //BA.debugLineNum = 128;BA.debugLine="Dim  cs As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 129;BA.debugLine="cs.Initialize";
_cs.Initialize();
 //BA.debugLineNum = 130;BA.debugLine="Dim cs1 As CSBuilder";
_cs1 = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 131;BA.debugLine="cs1.Initialize";
_cs1.Initialize();
 //BA.debugLineNum = 132;BA.debugLine="Dim tfthin As Typeface = mycode.lightfont";
_tfthin = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
_tfthin = mostCurrent._mycode._lightfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ ;
 //BA.debugLineNum = 133;BA.debugLine="cs.Size(30).Bold.Color(Colors.White).Append(twod&";
_cs.Size((int) (30)).Bold().Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(_twod+anywheresoftware.b4a.keywords.Common.CRLF)).Pop();
 //BA.debugLineNum = 134;BA.debugLine="cs.Size(18).Typeface(tfthin).Color(Colors.Yellow)";
_cs.Size((int) (18)).Typeface((android.graphics.Typeface)(_tfthin.getObject())).Color(anywheresoftware.b4a.keywords.Common.Colors.Yellow).Append(BA.ObjectToCharSequence(_sett)).Pop();
 //BA.debugLineNum = 135;BA.debugLine="cs.Size(18).Typeface(tfthin).Color(Colors.White).";
_cs.Size((int) (18)).Typeface((android.graphics.Typeface)(_tfthin.getObject())).Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(_setresult+anywheresoftware.b4a.keywords.Common.CRLF)).Pop();
 //BA.debugLineNum = 136;BA.debugLine="cs.Size(18).Typeface(tfthin).Color(Colors.Yellow)";
_cs.Size((int) (18)).Typeface((android.graphics.Typeface)(_tfthin.getObject())).Color(anywheresoftware.b4a.keywords.Common.Colors.Yellow).Append(BA.ObjectToCharSequence(_valuer)).Pop();
 //BA.debugLineNum = 137;BA.debugLine="cs.Size(18).Typeface(tfthin).Color(Colors.White).";
_cs.Size((int) (18)).Typeface((android.graphics.Typeface)(_tfthin.getObject())).Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(_valueresult)).Pop();
 //BA.debugLineNum = 138;BA.debugLine="cs.Size(18).Typeface(tfthin).Color(Colors.Yellow)";
_cs.Size((int) (18)).Typeface((android.graphics.Typeface)(_tfthin.getObject())).Color(anywheresoftware.b4a.keywords.Common.Colors.Yellow).Append(BA.ObjectToCharSequence(_valuel)).PopAll();
 //BA.debugLineNum = 140;BA.debugLine="Return cs";
if (true) return _cs;
 //BA.debugLineNum = 141;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.CSBuilder  _timecsb(String _time,String _icon,String _ampm) throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 143;BA.debugLine="Sub timecsb (time As String,icon As String,ampm As";
 //BA.debugLineNum = 144;BA.debugLine="Dim  cs As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 145;BA.debugLine="cs.Initialize";
_cs.Initialize();
 //BA.debugLineNum = 146;BA.debugLine="cs.Typeface(Typeface.FONTAWESOME).Size(15).Color(";
_cs.Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME()).Size((int) (15)).Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(_icon+" ")).Pop();
 //BA.debugLineNum = 147;BA.debugLine="cs.Typeface(Typeface.DEFAULT_BOLD).Size(15).Color";
_cs.Typeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD).Size((int) (15)).Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(_time+" "));
 //BA.debugLineNum = 148;BA.debugLine="cs.Typeface(Typeface.DEFAULT_BOLD).Size(15).Color";
_cs.Typeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD).Size((int) (15)).Color(((int)0xffc5c5c5)).Append(BA.ObjectToCharSequence(_ampm)).PopAll();
 //BA.debugLineNum = 149;BA.debugLine="Return cs";
if (true) return _cs;
 //BA.debugLineNum = 150;BA.debugLine="End Sub";
return null;
}
}
