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

public class lottohistory extends Activity implements B4AActivity{
	public static lottohistory mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "com.burma.royal2d", "com.burma.royal2d.lottohistory");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (lottohistory).");
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
		activityBA = new BA(this, layout, processBA, "com.burma.royal2d", "com.burma.royal2d.lottohistory");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.burma.royal2d.lottohistory", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (lottohistory) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (lottohistory) Resume **");
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
		return lottohistory.class;
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
            BA.LogInfo("** Activity (lottohistory) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (lottohistory) Pause event (activity is not paused). **");
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
            lottohistory mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (lottohistory) Resume **");
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
public static anywheresoftware.b4a.objects.Timer _progresstimer = null;
public static boolean _iscall = false;
public anywheresoftware.b4a.objects.PanelWrapper _progresspn = null;
public anywheresoftware.b4a.objects.ScrollViewWrapper _scv = null;
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
 //BA.debugLineNum = 16;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 17;BA.debugLine="Dim appbar As Panel = mycode.appbar(\"ထုတ်လွှင့်မှ";
_appbar = new anywheresoftware.b4a.objects.PanelWrapper();
_appbar = mostCurrent._mycode._appbar /*anywheresoftware.b4a.objects.PanelWrapper*/ (mostCurrent.activityBA,"ထုတ်လွှင့်မှုမှတ်တမ်း",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 18;BA.debugLine="Activity.AddView(appbar,0,mycode.appbartop,100%x,";
mostCurrent._activity.AddView((android.view.View)(_appbar.getObject()),(int) (0),mostCurrent._mycode._appbartop /*int*/ ,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),mostCurrent._mycode._appbarheight /*int*/ );
 //BA.debugLineNum = 19;BA.debugLine="mycode.SETnavigationcolor";
mostCurrent._mycode._setnavigationcolor /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 20;BA.debugLine="Activity.Color=mycode.bgColor";
mostCurrent._activity.setColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 21;BA.debugLine="scv.Initialize(1000dip)";
mostCurrent._scv.Initialize(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1000)));
 //BA.debugLineNum = 22;BA.debugLine="Activity.AddView(scv,0,mycode.appbarheight,100%x,";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._scv.getObject()),(int) (0),mostCurrent._mycode._appbarheight /*int*/ ,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),(int) (mostCurrent._mycode._activityheight /*int*/ -mostCurrent._mycode._appbarheight /*int*/ ));
 //BA.debugLineNum = 26;BA.debugLine="progresspn.Initialize(\"\")";
mostCurrent._progresspn.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 27;BA.debugLine="Activity.AddView(progresspn,0,mycode.appbarheight";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._progresspn.getObject()),(int) (0),mostCurrent._mycode._appbarheight /*int*/ ,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (2)));
 //BA.debugLineNum = 28;BA.debugLine="progresspn.Color=Colors.Yellow";
mostCurrent._progresspn.setColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 29;BA.debugLine="progresstimer.Initialize(\"ptimer\",50)";
_progresstimer.Initialize(processBA,"ptimer",(long) (50));
 //BA.debugLineNum = 30;BA.debugLine="progresstimer.Enabled=True";
_progresstimer.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 31;BA.debugLine="ApiCall.getlottohistory";
mostCurrent._apicall._getlottohistory /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 32;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 38;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 39;BA.debugLine="isCall=False";
_iscall = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 40;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 34;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 35;BA.debugLine="isCall=True";
_iscall = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 36;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
 //BA.debugLineNum = 41;BA.debugLine="Sub backbtn_click";
 //BA.debugLineNum = 42;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 43;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 12;BA.debugLine="Dim progresspn As Panel";
mostCurrent._progresspn = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 13;BA.debugLine="Dim scv As ScrollView";
mostCurrent._scv = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 14;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.PanelWrapper  _item(String _num,String _num1,String _date) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
anywheresoftware.b4a.objects.LabelWrapper _datelb = null;
anywheresoftware.b4a.objects.LabelWrapper _numlb = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
 //BA.debugLineNum = 54;BA.debugLine="Sub item(num As String,num1 As String ,date As Str";
 //BA.debugLineNum = 55;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 56;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 57;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 58;BA.debugLine="cd.Initialize(mycode.naviColor,10dip)";
_cd.Initialize(mostCurrent._mycode._navicolor /*int*/ ,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 59;BA.debugLine="p.Background=cd";
_p.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 60;BA.debugLine="p.Elevation= 5dip";
_p.setElevation((float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 //BA.debugLineNum = 61;BA.debugLine="Dim datelb As Label";
_datelb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 62;BA.debugLine="Dim numlb As Label";
_numlb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 63;BA.debugLine="datelb.Initialize(\"\")";
_datelb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 64;BA.debugLine="datelb.Gravity=Gravity.CENTER";
_datelb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 65;BA.debugLine="datelb.TextColor= Colors.Yellow'0xFFDDDDDD";
_datelb.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 66;BA.debugLine="datelb.Typeface=mycode.defaultfont";
_datelb.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._defaultfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 67;BA.debugLine="datelb.TextSize = mycode.textsize(10)";
_datelb.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (10)));
 //BA.debugLineNum = 68;BA.debugLine="datelb.Text= date";
_datelb.setText(BA.ObjectToCharSequence(_date));
 //BA.debugLineNum = 70;BA.debugLine="numlb.Initialize(\"\")";
_numlb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 71;BA.debugLine="numlb.Gravity=Gravity.CENTER";
_numlb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 72;BA.debugLine="numlb.TextColor=0xFFFFFFFF";
_numlb.setTextColor(((int)0xffffffff));
 //BA.debugLineNum = 73;BA.debugLine="numlb.Typeface=mycode.semibold";
_numlb.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._semibold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 74;BA.debugLine="numlb.TextSize= mycode.textsize (12)";
_numlb.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (12)));
 //BA.debugLineNum = 75;BA.debugLine="numlb.Text= num&Chr(9)&Chr(9)&num1";
_numlb.setText(BA.ObjectToCharSequence(_num+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (9)))+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (9)))+_num1));
 //BA.debugLineNum = 77;BA.debugLine="p.AddView(datelb,0,5dip,100%x-20dip,50dip)";
_p.AddView((android.view.View)(_datelb.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 78;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 79;BA.debugLine="datelb.Height =su.MeasureMultilineTextHeight(date";
_datelb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_datelb.getObject()),BA.ObjectToCharSequence(_datelb.getText())));
 //BA.debugLineNum = 80;BA.debugLine="p.AddView(numlb,0,datelb.Height+datelb.Top+10dip,";
_p.AddView((android.view.View)(_numlb.getObject()),(int) (0),(int) (_datelb.getHeight()+_datelb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 81;BA.debugLine="numlb.Height= su.MeasureMultilineTextHeight(numlb";
_numlb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_numlb.getObject()),BA.ObjectToCharSequence(_numlb.getText())));
 //BA.debugLineNum = 83;BA.debugLine="p.Height = numlb.Height+numlb.Top+5dip";
_p.setHeight((int) (_numlb.getHeight()+_numlb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 //BA.debugLineNum = 85;BA.debugLine="Return p";
if (true) return _p;
 //BA.debugLineNum = 86;BA.debugLine="End Sub";
return null;
}
public static void  _lottohisuccess() throws Exception{
ResumableSub_lottohisuccess rsub = new ResumableSub_lottohisuccess(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_lottohisuccess extends BA.ResumableSub {
public ResumableSub_lottohisuccess(com.burma.royal2d.lottohistory parent) {
this.parent = parent;
}
com.burma.royal2d.lottohistory parent;
anywheresoftware.b4a.objects.collections.JSONParser _json = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
int _tp = 0;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4a.objects.PanelWrapper _ip = null;
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
 //BA.debugLineNum = 90;BA.debugLine="If ApiCall.lottohis <>\"\" Then";
if (true) break;

case 1:
//if
this.state = 16;
if ((parent.mostCurrent._apicall._lottohis /*String*/ ).equals("") == false) { 
this.state = 3;
}else {
this.state = 15;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 91;BA.debugLine="Log(\"here >>>>>>>>>>>>\")";
anywheresoftware.b4a.keywords.Common.LogImpl("237289987","here >>>>>>>>>>>>",0);
 //BA.debugLineNum = 92;BA.debugLine="progresspn.Visible=False";
parent.mostCurrent._progresspn.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 93;BA.debugLine="progresspn.RemoveView";
parent.mostCurrent._progresspn.RemoveView();
 //BA.debugLineNum = 94;BA.debugLine="Dim json As JSONParser";
_json = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 95;BA.debugLine="json.Initialize(ApiCall.lottohis)";
_json.Initialize(parent.mostCurrent._apicall._lottohis /*String*/ );
 //BA.debugLineNum = 96;BA.debugLine="Dim ls As List = json.NextArray";
_ls = new anywheresoftware.b4a.objects.collections.List();
_ls = _json.NextArray();
 //BA.debugLineNum = 97;BA.debugLine="Dim tp As Int = 10dip";
_tp = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10));
 //BA.debugLineNum = 99;BA.debugLine="For i = 0 To ls.Size -1";
if (true) break;

case 4:
//for
this.state = 13;
step9 = 1;
limit9 = (int) (_ls.getSize()-1);
_i = (int) (0) ;
this.state = 17;
if (true) break;

case 17:
//C
this.state = 13;
if ((step9 > 0 && _i <= limit9) || (step9 < 0 && _i >= limit9)) this.state = 6;
if (true) break;

case 18:
//C
this.state = 17;
_i = ((int)(0 + _i + step9)) ;
if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 100;BA.debugLine="Dim m As Map = ls.Get(i)";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_ls.Get(_i)));
 //BA.debugLineNum = 101;BA.debugLine="Dim ip As Panel = item(m.Get(\"fnum\"),m.Get(\"snu";
_ip = new anywheresoftware.b4a.objects.PanelWrapper();
_ip = _item(BA.ObjectToString(_m.Get((Object)("fnum"))),BA.ObjectToString(_m.Get((Object)("snum"))),BA.ObjectToString(_m.Get((Object)("thaidate"))));
 //BA.debugLineNum = 102;BA.debugLine="If i Mod 2=0 Then";
if (true) break;

case 7:
//if
this.state = 12;
if (_i%2==0) { 
this.state = 9;
}else {
this.state = 11;
}if (true) break;

case 9:
//C
this.state = 12;
 //BA.debugLineNum = 103;BA.debugLine="ip.SetLayoutAnimated(500,10dip,10dip,scv.Width";
_ip.SetLayoutAnimated((int) (500),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (parent.mostCurrent._scv.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_ip.getHeight());
 if (true) break;

case 11:
//C
this.state = 12;
 //BA.debugLineNum = 105;BA.debugLine="ip.SetLayoutAnimated(500,10dip,10dip,20dip,ip.";
_ip.SetLayoutAnimated((int) (500),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)),_ip.getHeight());
 if (true) break;

case 12:
//C
this.state = 18;
;
 //BA.debugLineNum = 107;BA.debugLine="scv.Panel.AddView(ip,10dip,tp,scv.Width-20dip,i";
parent.mostCurrent._scv.getPanel().AddView((android.view.View)(_ip.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_tp,(int) (parent.mostCurrent._scv.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_ip.getHeight());
 //BA.debugLineNum = 108;BA.debugLine="tp= tp +ip.Height+10dip";
_tp = (int) (_tp+_ip.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 109;BA.debugLine="Sleep(200)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (200));
this.state = 19;
return;
case 19:
//C
this.state = 18;
;
 if (true) break;
if (true) break;

case 13:
//C
this.state = 16;
;
 //BA.debugLineNum = 111;BA.debugLine="scv.Panel.Height =tp+10dip";
parent.mostCurrent._scv.getPanel().setHeight((int) (_tp+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 if (true) break;

case 15:
//C
this.state = 16;
 //BA.debugLineNum = 114;BA.debugLine="ToastMessageShow(\"data not found\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("data not found"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 115;BA.debugLine="Activity.Finish";
parent.mostCurrent._activity.Finish();
 if (true) break;

case 16:
//C
this.state = -1;
;
 //BA.debugLineNum = 117;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Dim progresstimer As Timer";
_progresstimer = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 8;BA.debugLine="Dim isCall As Boolean";
_iscall = false;
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public static String  _ptimer_tick() throws Exception{
 //BA.debugLineNum = 45;BA.debugLine="Sub ptimer_tick";
 //BA.debugLineNum = 46;BA.debugLine="If progresspn.Width = 100%x Then";
if (mostCurrent._progresspn.getWidth()==anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)) { 
 //BA.debugLineNum = 47;BA.debugLine="progresspn.Width = 0";
mostCurrent._progresspn.setWidth((int) (0));
 }else {
 //BA.debugLineNum = 49;BA.debugLine="progresspn.Width =progresspn.Width+10%x";
mostCurrent._progresspn.setWidth((int) (mostCurrent._progresspn.getWidth()+anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (10),mostCurrent.activityBA)));
 };
 //BA.debugLineNum = 51;BA.debugLine="End Sub";
return "";
}
}
