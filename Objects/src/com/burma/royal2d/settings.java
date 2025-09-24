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

public class settings extends Activity implements B4AActivity{
	public static settings mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "com.burma.royal2d", "com.burma.royal2d.settings");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (settings).");
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
		activityBA = new BA(this, layout, processBA, "com.burma.royal2d", "com.burma.royal2d.settings");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.burma.royal2d.settings", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (settings) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (settings) Resume **");
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
		return settings.class;
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
            BA.LogInfo("** Activity (settings) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (settings) Pause event (activity is not paused). **");
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
            settings mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (settings) Resume **");
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
public static int _top = 0;
public com.burma.royal2d.main _main = null;
public com.burma.royal2d.report_details _report_details = null;
public com.burma.royal2d.mycode _mycode = null;
public com.burma.royal2d.public_chat _public_chat = null;
public com.burma.royal2d.gift_imageview _gift_imageview = null;
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
 //BA.debugLineNum = 14;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 15;BA.debugLine="Activity.Color=mycode.bgColor";
mostCurrent._activity.setColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 16;BA.debugLine="mycode.SETnavigationcolor";
mostCurrent._mycode._setnavigationcolor /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 17;BA.debugLine="Dim appbar As Panel  = mycode.appbar(\"Settings\",F";
_appbar = new anywheresoftware.b4a.objects.PanelWrapper();
_appbar = mostCurrent._mycode._appbar /*anywheresoftware.b4a.objects.PanelWrapper*/ (mostCurrent.activityBA,"Settings",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 18;BA.debugLine="Activity.AddView(appbar,0,mycode.appbartop,100%x,";
mostCurrent._activity.AddView((android.view.View)(_appbar.getObject()),(int) (0),mostCurrent._mycode._appbartop /*int*/ ,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),mostCurrent._mycode._appbarheight /*int*/ );
 //BA.debugLineNum = 19;BA.debugLine="top = mycode.appbarheight+10dip";
_top = (int) (mostCurrent._mycode._appbarheight /*int*/ +anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 21;BA.debugLine="If File.Exists(File.DirInternal,\"user\") Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"user")) { 
 //BA.debugLineNum = 22;BA.debugLine="profile";
_profile();
 //BA.debugLineNum = 23;BA.debugLine="top = top +10dip";
_top = (int) (_top+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 }else {
 //BA.debugLineNum = 25;BA.debugLine="addAccount";
_addaccount();
 };
 //BA.debugLineNum = 27;BA.debugLine="Addsettings";
_addsettings();
 //BA.debugLineNum = 28;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 34;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 36;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 30;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 32;BA.debugLine="End Sub";
return "";
}
public static void  _adconsent() throws Exception{
ResumableSub_ADConsent rsub = new ResumableSub_ADConsent(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_ADConsent extends BA.ResumableSub {
public ResumableSub_ADConsent(com.burma.royal2d.settings parent) {
this.parent = parent;
}
com.burma.royal2d.settings parent;
com.burma.royal2d.adshelper _ads = null;
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
 //BA.debugLineNum = 65;BA.debugLine="Dim ads As AdsHelper";
_ads = new com.burma.royal2d.adshelper();
 //BA.debugLineNum = 66;BA.debugLine="ads.Initialize";
_ads._initialize /*String*/ (processBA);
 //BA.debugLineNum = 67;BA.debugLine="If ads.GetConsentStatus = \"UNKNOWN\" Or ads.GetCon";
if (true) break;

case 1:
//if
this.state = 4;
if ((_ads._getconsentstatus /*String*/ ()).equals("UNKNOWN") || (_ads._getconsentstatus /*String*/ ()).equals("REQUIRED")) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 68;BA.debugLine="Wait For (ads.RequestConsentInformation(False))";
anywheresoftware.b4a.keywords.Common.WaitFor("complete", processBA, this, _ads._requestconsentinformation /*anywheresoftware.b4a.keywords.Common.ResumableSubWrapper*/ (anywheresoftware.b4a.keywords.Common.False));
this.state = 8;
return;
case 8:
//C
this.state = 4;
_success = (Boolean) result[0];
;
 if (true) break;
;
 //BA.debugLineNum = 70;BA.debugLine="If ads.GetConsentStatus = \"REQUIRED\" And ads.GetC";

case 4:
//if
this.state = 7;
if ((_ads._getconsentstatus /*String*/ ()).equals("REQUIRED") && _ads._getconsentformavailable /*boolean*/ ()) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 71;BA.debugLine="Wait For (ads.ShowConsentForm) Complete (Success";
anywheresoftware.b4a.keywords.Common.WaitFor("complete", processBA, this, _ads._showconsentform /*anywheresoftware.b4a.keywords.Common.ResumableSubWrapper*/ ());
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
 //BA.debugLineNum = 73;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _complete(boolean _success) throws Exception{
}
public static String  _addaccount() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
anywheresoftware.b4a.objects.LabelWrapper _titlelb = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
anywheresoftware.b4a.objects.PanelWrapper _pacc = null;
 //BA.debugLineNum = 120;BA.debugLine="Sub addAccount";
 //BA.debugLineNum = 121;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 122;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 123;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 124;BA.debugLine="cd.Initialize(mycode.naviColor,10dip)";
_cd.Initialize(mostCurrent._mycode._navicolor /*int*/ ,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 125;BA.debugLine="p.Background=cd";
_p.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 126;BA.debugLine="Dim titlelb As Label";
_titlelb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 127;BA.debugLine="titlelb.Initialize(\"\")";
_titlelb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 128;BA.debugLine="p.AddView(titlelb,10dip,10dip,100%x-20dip,30dip)";
_p.AddView((android.view.View)(_titlelb.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 129;BA.debugLine="titlelb.Typeface=mycode.semibold";
_titlelb.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._semibold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 130;BA.debugLine="titlelb.TextColor=Colors.White";
_titlelb.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 131;BA.debugLine="titlelb.TextSize = mycode.textsize(8)";
_titlelb.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (8)));
 //BA.debugLineNum = 132;BA.debugLine="titlelb.Gravity=Gravity.CENTER_VERTICAL";
_titlelb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
 //BA.debugLineNum = 133;BA.debugLine="titlelb.Text= \"User Account\"";
_titlelb.setText(BA.ObjectToCharSequence("User Account"));
 //BA.debugLineNum = 134;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 135;BA.debugLine="titlelb.Height = su.MeasureMultilineTextHeight(ti";
_titlelb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_titlelb.getObject()),BA.ObjectToCharSequence(_titlelb.getText())));
 //BA.debugLineNum = 136;BA.debugLine="Dim pacc As Panel = pitem(Chr(0xF2BD),\"Log In To";
_pacc = new anywheresoftware.b4a.objects.PanelWrapper();
_pacc = _pitem(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf2bd))),"Log In To Burma 2D","pacc");
 //BA.debugLineNum = 137;BA.debugLine="Activity.AddView(p,10dip,top,100%x-20dip,pacc.Hei";
mostCurrent._activity.AddView((android.view.View)(_p.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_top,(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_pacc.getHeight());
 //BA.debugLineNum = 138;BA.debugLine="p.AddView(pacc,10dip,titlelb.Height+titlelb.Top+1";
_p.AddView((android.view.View)(_pacc.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_titlelb.getHeight()+_titlelb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),(int) (_p.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 139;BA.debugLine="p.Height = pacc.Height+pacc.Top+10dip";
_p.setHeight((int) (_pacc.getHeight()+_pacc.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 140;BA.debugLine="top = p.Height+p.Top+10dip";
_top = (int) (_p.getHeight()+_p.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 141;BA.debugLine="End Sub";
return "";
}
public static String  _addholidays() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
anywheresoftware.b4a.objects.LabelWrapper _titlelb = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
anywheresoftware.b4a.objects.PanelWrapper _pacc = null;
 //BA.debugLineNum = 42;BA.debugLine="Sub addHolidays";
 //BA.debugLineNum = 43;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 44;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 45;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 46;BA.debugLine="cd.Initialize(mycode.naviColor,10dip)";
_cd.Initialize(mostCurrent._mycode._navicolor /*int*/ ,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 47;BA.debugLine="p.Background=cd";
_p.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 48;BA.debugLine="Activity.AddView(p,10dip,top,100%x-20dip,50dip)";
mostCurrent._activity.AddView((android.view.View)(_p.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_top,(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 49;BA.debugLine="Dim titlelb As Label";
_titlelb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 50;BA.debugLine="titlelb.Initialize(\"\")";
_titlelb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 51;BA.debugLine="titlelb.Typeface=mycode.defaultfont";
_titlelb.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._defaultfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 52;BA.debugLine="titlelb.TextSize = mycode.textsize(8)";
_titlelb.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (8)));
 //BA.debugLineNum = 53;BA.debugLine="titlelb.Text= \"Set Holidays\"";
_titlelb.setText(BA.ObjectToCharSequence("Set Holidays"));
 //BA.debugLineNum = 54;BA.debugLine="titlelb.TextColor=Colors.White";
_titlelb.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 55;BA.debugLine="p.AddView(titlelb,10dip,10dip,p.Width-20dip,30dip";
_p.AddView((android.view.View)(_titlelb.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_p.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 56;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 57;BA.debugLine="titlelb.Height = su.MeasureMultilineTextHeight(ti";
_titlelb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_titlelb.getObject()),BA.ObjectToCharSequence(_titlelb.getText())));
 //BA.debugLineNum = 58;BA.debugLine="Dim pacc As Panel = pitem(Chr(0xF2D3),\"Set Holida";
_pacc = new anywheresoftware.b4a.objects.PanelWrapper();
_pacc = _pitem(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf2d3))),"Set Holidays","pholiday");
 //BA.debugLineNum = 59;BA.debugLine="p.AddView(pacc,10dip,titlelb.Height+titlelb.Top+1";
_p.AddView((android.view.View)(_pacc.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_titlelb.getHeight()+_titlelb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),(int) (_p.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 60;BA.debugLine="p.Height = pacc.Height+pacc.Top+10dip";
_p.setHeight((int) (_pacc.getHeight()+_pacc.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 61;BA.debugLine="top = top+p.Height+10dip";
_top = (int) (_top+_p.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 62;BA.debugLine="End Sub";
return "";
}
public static String  _addsettings() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _base = null;
anywheresoftware.b4a.objects.PanelWrapper _pprivacy = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
anywheresoftware.b4a.objects.LabelWrapper _lbltitle = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
int _tp = 0;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
 //BA.debugLineNum = 143;BA.debugLine="Sub Addsettings";
 //BA.debugLineNum = 145;BA.debugLine="Dim base As Panel";
_base = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 146;BA.debugLine="Dim pprivacy As Panel = pitem(Chr(0xF265),\"  Priv";
_pprivacy = new anywheresoftware.b4a.objects.PanelWrapper();
_pprivacy = _pitem(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf265))),"  Privacy Policy","privacybtn");
 //BA.debugLineNum = 148;BA.debugLine="base.Initialize(\"\")";
_base.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 149;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 150;BA.debugLine="cd.Initialize(mycode.naviColor,10dip)";
_cd.Initialize(mostCurrent._mycode._navicolor /*int*/ ,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 151;BA.debugLine="base.Background= cd";
_base.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 152;BA.debugLine="Activity.AddView(base,10dip,top,100%x-20dip,50dip";
mostCurrent._activity.AddView((android.view.View)(_base.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_top,(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 153;BA.debugLine="Dim lbltitle As Label";
_lbltitle = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 154;BA.debugLine="lbltitle.Initialize(\"\")";
_lbltitle.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 155;BA.debugLine="lbltitle.TextSize =mycode.textsize(8)";
_lbltitle.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (8)));
 //BA.debugLineNum = 156;BA.debugLine="lbltitle.Text=\"Settings\"";
_lbltitle.setText(BA.ObjectToCharSequence("Settings"));
 //BA.debugLineNum = 157;BA.debugLine="lbltitle.Typeface= mycode.semibold";
_lbltitle.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._semibold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 158;BA.debugLine="lbltitle.TextColor=Colors.White";
_lbltitle.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 159;BA.debugLine="base.AddView(lbltitle,10dip,10dip,base.Width-20di";
_base.AddView((android.view.View)(_lbltitle.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_base.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 160;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 161;BA.debugLine="lbltitle.Height = su.MeasureMultilineTextHeight(l";
_lbltitle.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_lbltitle.getObject()),BA.ObjectToCharSequence(_lbltitle.getText())));
 //BA.debugLineNum = 162;BA.debugLine="base.AddView(pprivacy,10dip,lbltitle.Height+lblti";
_base.AddView((android.view.View)(_pprivacy.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_lbltitle.getHeight()+_lbltitle.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),(int) (_base.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 163;BA.debugLine="base.Height = pprivacy.Height+pprivacy.Top+10dip";
_base.setHeight((int) (_pprivacy.getHeight()+_pprivacy.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 164;BA.debugLine="Dim tp As Int = pprivacy.Height +pprivacy.Top+10d";
_tp = (int) (_pprivacy.getHeight()+_pprivacy.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 165;BA.debugLine="Dim p As Panel = pitem(Chr(0xF15C),\"  Change Cons";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = _pitem(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf15c))),"  Change Consent Type","consentbtn");
 //BA.debugLineNum = 167;BA.debugLine="base.AddView(p,10dip,tp,base.Width-20dip,50dip)";
_base.AddView((android.view.View)(_p.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_tp,(int) (_base.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 168;BA.debugLine="base.Height = p.Height+p.Top+10dip";
_base.setHeight((int) (_p.getHeight()+_p.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 169;BA.debugLine="top=base.Height+base.Top+10dip";
_top = (int) (_base.getHeight()+_base.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 170;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
 //BA.debugLineNum = 75;BA.debugLine="Sub backbtn_click";
 //BA.debugLineNum = 76;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 77;BA.debugLine="End Sub";
return "";
}
public static String  _consentbtn_click() throws Exception{
 //BA.debugLineNum = 38;BA.debugLine="Sub consentbtn_click";
 //BA.debugLineNum = 39;BA.debugLine="ADConsent";
_adconsent();
 //BA.debugLineNum = 40;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 11;BA.debugLine="Dim top As Int";
_top = 0;
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public static String  _pacc_click() throws Exception{
 //BA.debugLineNum = 210;BA.debugLine="Sub pacc_click";
 //BA.debugLineNum = 211;BA.debugLine="StartActivity(Login)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._login.getObject()));
 //BA.debugLineNum = 212;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.PanelWrapper  _pitem(String _icon,String _text,String _eventname) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
anywheresoftware.b4a.objects.LabelWrapper _consentbtn = null;
anywheresoftware.b4a.objects.LabelWrapper _iconlb = null;
anywheresoftware.b4a.objects.CSBuilder _cs2 = null;
anywheresoftware.b4a.objects.LabelWrapper _lb = null;
anywheresoftware.b4a.objects.CSBuilder _cs1 = null;
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
anywheresoftware.b4a.objects.PanelWrapper _devider = null;
 //BA.debugLineNum = 79;BA.debugLine="Sub pitem (icon As String,text As String,eventname";
 //BA.debugLineNum = 80;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 81;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 82;BA.debugLine="Dim cs As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 83;BA.debugLine="cs.Initialize";
_cs.Initialize();
 //BA.debugLineNum = 84;BA.debugLine="cs.Color(Colors.White).Typeface(mycode.semibold).";
_cs.Color(anywheresoftware.b4a.keywords.Common.Colors.White).Typeface((android.graphics.Typeface)(mostCurrent._mycode._semibold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (7)))).Append(BA.ObjectToCharSequence(_text)).PopAll();
 //BA.debugLineNum = 85;BA.debugLine="Dim consentbtn As Label";
_consentbtn = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 86;BA.debugLine="consentbtn.Initialize(\"\")";
_consentbtn.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 87;BA.debugLine="consentbtn.Text = cs";
_consentbtn.setText(BA.ObjectToCharSequence(_cs.getObject()));
 //BA.debugLineNum = 88;BA.debugLine="consentbtn.Gravity=Gravity.CENTER_VERTICAL";
_consentbtn.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
 //BA.debugLineNum = 89;BA.debugLine="Dim iconlb As Label";
_iconlb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 90;BA.debugLine="iconlb.Initialize(\"\")";
_iconlb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 91;BA.debugLine="Dim cs2 As CSBuilder";
_cs2 = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 92;BA.debugLine="cs2.Initialize.Color(Colors.Yellow).Typeface(Type";
_cs2.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.Yellow).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME()).Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (10)))).Append(BA.ObjectToCharSequence(_icon)).PopAll();
 //BA.debugLineNum = 93;BA.debugLine="iconlb.Text = cs2";
_iconlb.setText(BA.ObjectToCharSequence(_cs2.getObject()));
 //BA.debugLineNum = 94;BA.debugLine="iconlb.Gravity=Gravity.CENTER";
_iconlb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 95;BA.debugLine="p.AddView(iconlb,0,0,50dip,50dip)";
_p.AddView((android.view.View)(_iconlb.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 96;BA.debugLine="p.AddView(consentbtn,50dip,0,100%x-140dip,50dip)";
_p.AddView((android.view.View)(_consentbtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),(int) (0),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (140))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 97;BA.debugLine="Dim lb As Label";
_lb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 98;BA.debugLine="lb.Initialize(\"\")";
_lb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 99;BA.debugLine="Dim cs1 As CSBuilder";
_cs1 = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 100;BA.debugLine="cs1.Initialize.Color(Colors.White).Typeface(mycod";
_cs1.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.White).Typeface((android.graphics.Typeface)(mostCurrent._mycode._defaultfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (11)))).Append(BA.ObjectToCharSequence(">")).PopAll();
 //BA.debugLineNum = 101;BA.debugLine="lb.Text=cs1";
_lb.setText(BA.ObjectToCharSequence(_cs1.getObject()));
 //BA.debugLineNum = 102;BA.debugLine="lb.Gravity=Gravity.CENTER_VERTICAL";
_lb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
 //BA.debugLineNum = 103;BA.debugLine="p.AddView(lb,consentbtn.Width+consentbtn.Left,0,5";
_p.AddView((android.view.View)(_lb.getObject()),(int) (_consentbtn.getWidth()+_consentbtn.getLeft()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 104;BA.debugLine="Dim b As Button";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 105;BA.debugLine="b.Initialize(eventname)";
_b.Initialize(mostCurrent.activityBA,_eventname);
 //BA.debugLineNum = 106;BA.debugLine="b.Background= mycode.btnbgdynamic(Colors.Transpar";
_b.setBackground((android.graphics.drawable.Drawable)(mostCurrent._mycode._btnbgdynamic /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.Colors.Transparent,((int)0x9a466584),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))).getObject()));
 //BA.debugLineNum = 107;BA.debugLine="p.AddView(b,0,0,100%x-40dip,50dip)";
_p.AddView((android.view.View)(_b.getObject()),(int) (0),(int) (0),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 108;BA.debugLine="Dim devider As Panel";
_devider = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 109;BA.debugLine="devider.Initialize(\"\")";
_devider.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 110;BA.debugLine="devider.Color=0xB1FFFFFF";
_devider.setColor(((int)0xb1ffffff));
 //BA.debugLineNum = 112;BA.debugLine="p.Height = 50dip";
_p.setHeight(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 113;BA.debugLine="Return p";
if (true) return _p;
 //BA.debugLineNum = 114;BA.debugLine="End Sub";
return null;
}
public static String  _privacybtn_click() throws Exception{
 //BA.debugLineNum = 116;BA.debugLine="Sub privacybtn_click";
 //BA.debugLineNum = 117;BA.debugLine="StartActivity(Privacy_Policy)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._privacy_policy.getObject()));
 //BA.debugLineNum = 118;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public static String  _profile() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.ImageViewWrapper _img = null;
ir.aghajari.retrofitglide.Amir_Glide _glide = null;
int _l = 0;
anywheresoftware.b4a.objects.LabelWrapper _lblname = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
anywheresoftware.b4a.objects.LabelWrapper _lblemail = null;
 //BA.debugLineNum = 174;BA.debugLine="Sub profile";
 //BA.debugLineNum = 175;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 176;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 177;BA.debugLine="Dim img As ImageView";
_img = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 178;BA.debugLine="img.Initialize(\"\")";
_img.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 179;BA.debugLine="Dim glide As Amir_Glide";
_glide = new ir.aghajari.retrofitglide.Amir_Glide();
 //BA.debugLineNum = 180;BA.debugLine="glide.Initializer.Default";
_glide.Initializer(processBA).Default();
 //BA.debugLineNum = 181;BA.debugLine="glide.Load(mycode.getUserId(mycode.profile_pic)).";
_glide.Load((Object)(mostCurrent._mycode._getuserid /*String*/ (mostCurrent.activityBA,mostCurrent._mycode._profile_pic /*String*/ ))).Apply(_glide.getRO().CircleCrop()).Into((android.widget.ImageView)(_img.getObject()));
 //BA.debugLineNum = 182;BA.debugLine="Dim l As Int = (100%x-70dip)/2";
_l = (int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)))/(double)2);
 //BA.debugLineNum = 183;BA.debugLine="p.AddView(img,l,10dip,70dip,70dip)";
_p.AddView((android.view.View)(_img.getObject()),_l,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)));
 //BA.debugLineNum = 184;BA.debugLine="Dim lblname As Label";
_lblname = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 185;BA.debugLine="lblname.Initialize(\"\")";
_lblname.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 186;BA.debugLine="lblname.text = mycode.getUserId(mycode.name)";
_lblname.setText(BA.ObjectToCharSequence(mostCurrent._mycode._getuserid /*String*/ (mostCurrent.activityBA,mostCurrent._mycode._name /*String*/ )));
 //BA.debugLineNum = 187;BA.debugLine="lblname.Typeface=mycode.defaultfont";
_lblname.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._defaultfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 188;BA.debugLine="lblname.Gravity=Gravity.CENTER";
_lblname.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 189;BA.debugLine="lblname.TextSize = mycode.textsize (9)";
_lblname.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (9)));
 //BA.debugLineNum = 190;BA.debugLine="lblname.TextColor=Colors.White";
_lblname.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 191;BA.debugLine="p.AddView(lblname,0,img.Height+img.Top+10dip,100%";
_p.AddView((android.view.View)(_lblname.getObject()),(int) (0),(int) (_img.getHeight()+_img.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 192;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 193;BA.debugLine="lblname.Height = su.MeasureMultilineTextHeight(lb";
_lblname.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_lblname.getObject()),BA.ObjectToCharSequence(_lblname.getText())));
 //BA.debugLineNum = 194;BA.debugLine="Dim lblemail As Label";
_lblemail = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 195;BA.debugLine="lblemail.Initialize(\"\")";
_lblemail.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 196;BA.debugLine="lblemail.text = mycode.getUserId(mycode.email)";
_lblemail.setText(BA.ObjectToCharSequence(mostCurrent._mycode._getuserid /*String*/ (mostCurrent.activityBA,mostCurrent._mycode._email /*String*/ )));
 //BA.debugLineNum = 197;BA.debugLine="lblemail.Typeface=mycode.defaultfont";
_lblemail.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._defaultfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 198;BA.debugLine="lblemail.Gravity=Gravity.CENTER";
_lblemail.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 199;BA.debugLine="lblemail.TextSize = mycode.textsize (8)";
_lblemail.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (8)));
 //BA.debugLineNum = 200;BA.debugLine="lblemail.TextColor=Colors.White";
_lblemail.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 201;BA.debugLine="p.AddView(lblemail,0,lblname.Height+lblname.Top+1";
_p.AddView((android.view.View)(_lblemail.getObject()),(int) (0),(int) (_lblname.getHeight()+_lblname.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 202;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 203;BA.debugLine="lblemail.Height = su.MeasureMultilineTextHeight(l";
_lblemail.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_lblemail.getObject()),BA.ObjectToCharSequence(_lblemail.getText())));
 //BA.debugLineNum = 204;BA.debugLine="p.Height = lblemail.Height+lblemail.Top";
_p.setHeight((int) (_lblemail.getHeight()+_lblemail.getTop()));
 //BA.debugLineNum = 206;BA.debugLine="Activity.AddView(p,0,top,100%x,p.Height)";
mostCurrent._activity.AddView((android.view.View)(_p.getObject()),(int) (0),_top,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),_p.getHeight());
 //BA.debugLineNum = 207;BA.debugLine="top = p.Height+p.Top+10dip";
_top = (int) (_p.getHeight()+_p.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 208;BA.debugLine="End Sub";
return "";
}
}
