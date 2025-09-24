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

public class profile_activity extends Activity implements B4AActivity{
	public static profile_activity mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "com.burma.royal2d", "com.burma.royal2d.profile_activity");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (profile_activity).");
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
		activityBA = new BA(this, layout, processBA, "com.burma.royal2d", "com.burma.royal2d.profile_activity");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.burma.royal2d.profile_activity", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (profile_activity) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (profile_activity) Resume **");
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
		return profile_activity.class;
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
            BA.LogInfo("** Activity (profile_activity) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (profile_activity) Pause event (activity is not paused). **");
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
            profile_activity mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (profile_activity) Resume **");
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
public anywheresoftware.b4a.objects.PanelWrapper _pf = null;
public anywheresoftware.b4a.objects.PanelWrapper _blistpn = null;
public anywheresoftware.b4a.objects.PanelWrapper _pbitem = null;
public com.burma.royal2d.main _main = null;
public com.burma.royal2d.report_details _report_details = null;
public com.burma.royal2d.mycode _mycode = null;
public com.burma.royal2d.public_chat _public_chat = null;
public com.burma.royal2d.gift_imageview _gift_imageview = null;
public com.burma.royal2d.settings _settings = null;
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
anywheresoftware.b4a.objects.ButtonWrapper _logoutbtn = null;
 //BA.debugLineNum = 15;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 16;BA.debugLine="mycode.SETnavigationcolor";
mostCurrent._mycode._setnavigationcolor /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 17;BA.debugLine="Dim appbar As Panel = mycode.appbar(\"Profile Deta";
_appbar = new anywheresoftware.b4a.objects.PanelWrapper();
_appbar = mostCurrent._mycode._appbar /*anywheresoftware.b4a.objects.PanelWrapper*/ (mostCurrent.activityBA,"Profile Details",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 18;BA.debugLine="Activity.AddView(appbar,0,mycode.appbartop,100%x,";
mostCurrent._activity.AddView((android.view.View)(_appbar.getObject()),(int) (0),mostCurrent._mycode._appbartop /*int*/ ,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),mostCurrent._mycode._appbarheight /*int*/ );
 //BA.debugLineNum = 20;BA.debugLine="Dim logoutbtn As Button";
_logoutbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 21;BA.debugLine="logoutbtn.Initialize(\"logoutbtn\")";
_logoutbtn.Initialize(mostCurrent.activityBA,"logoutbtn");
 //BA.debugLineNum = 22;BA.debugLine="logoutbtn.Background=mycode.btnbg(False)";
_logoutbtn.setBackground((android.graphics.drawable.Drawable)(mostCurrent._mycode._btnbg /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False).getObject()));
 //BA.debugLineNum = 23;BA.debugLine="logoutbtn.Text=mycode.buttoncsb(False,Chr(0xF08B)";
_logoutbtn.setText(BA.ObjectToCharSequence(mostCurrent._mycode._buttoncsb /*anywheresoftware.b4a.objects.CSBuilder*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf08b))),anywheresoftware.b4a.keywords.Common.True).getObject()));
 //BA.debugLineNum = 24;BA.debugLine="appbar.AddView(logoutbtn,appbar.Width-mycode.appb";
_appbar.AddView((android.view.View)(_logoutbtn.getObject()),(int) (_appbar.getWidth()-mostCurrent._mycode._appbarheight /*int*/ ),(int) (0),mostCurrent._mycode._appbarheight /*int*/ ,mostCurrent._mycode._appbarheight /*int*/ );
 //BA.debugLineNum = 25;BA.debugLine="Activity.Color=mycode.bgColor";
mostCurrent._activity.setColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 26;BA.debugLine="pf = profile";
mostCurrent._pf = _profile();
 //BA.debugLineNum = 27;BA.debugLine="Activity.AddView(pf,0,mycode.appbarheight,100%x,p";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._pf.getObject()),(int) (0),mostCurrent._mycode._appbarheight /*int*/ ,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),mostCurrent._pf.getHeight());
 //BA.debugLineNum = 28;BA.debugLine="addblockpn";
_addblockpn();
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 35;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 37;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 31;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 33;BA.debugLine="End Sub";
return "";
}
public static String  _addblockpn() throws Exception{
 //BA.debugLineNum = 78;BA.debugLine="Sub addblockpn";
 //BA.debugLineNum = 79;BA.debugLine="If blistpn.IsInitialized Then";
if (mostCurrent._blistpn.IsInitialized()) { 
 //BA.debugLineNum = 80;BA.debugLine="Activity.RemoveViewAt(2)";
mostCurrent._activity.RemoveViewAt((int) (2));
 };
 //BA.debugLineNum = 82;BA.debugLine="If blocklist.Size > 0 Then";
if (_blocklist().getSize()>0) { 
 //BA.debugLineNum = 83;BA.debugLine="blistpn = blockListpn";
mostCurrent._blistpn = _blocklistpn();
 //BA.debugLineNum = 84;BA.debugLine="Activity.AddView(blistpn, 0, pf.Top + pf.Height";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._blistpn.getObject()),(int) (0),(int) (mostCurrent._pf.getTop()+mostCurrent._pf.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),mostCurrent._blistpn.getHeight());
 };
 //BA.debugLineNum = 87;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
 //BA.debugLineNum = 208;BA.debugLine="Sub backbtn_click";
 //BA.debugLineNum = 209;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 210;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.PanelWrapper  _blockitem(String _name,String _profile_pic,int _i) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.LabelWrapper _namelb = null;
anywheresoftware.b4a.objects.ImageViewWrapper _profileimg = null;
anywheresoftware.b4a.objects.ButtonWrapper _unblockbtn = null;
ir.aghajari.retrofitglide.Amir_Glide _glide = null;
int _pw = 0;
int _l = 0;
int _r = 0;
int _w = 0;
 //BA.debugLineNum = 141;BA.debugLine="Sub blockitem(name as String,profile_pic As String";
 //BA.debugLineNum = 142;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 143;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 144;BA.debugLine="Dim namelb As Label";
_namelb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 145;BA.debugLine="Dim profileimg As ImageView";
_profileimg = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 146;BA.debugLine="Dim unblockbtn As Button";
_unblockbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 147;BA.debugLine="namelb.Initialize(\"\")";
_namelb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 148;BA.debugLine="profileimg.Initialize(\"\")";
_profileimg.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 149;BA.debugLine="unblockbtn.Initialize(\"unblockbtn\")";
_unblockbtn.Initialize(mostCurrent.activityBA,"unblockbtn");
 //BA.debugLineNum = 150;BA.debugLine="namelb.Typeface = mycode.defaultfont";
_namelb.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._defaultfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 151;BA.debugLine="namelb.TextSize = mycode.textsize(8)";
_namelb.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (8)));
 //BA.debugLineNum = 152;BA.debugLine="namelb.SingleLine=True";
_namelb.setSingleLine(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 153;BA.debugLine="namelb.Text= name";
_namelb.setText(BA.ObjectToCharSequence(_name));
 //BA.debugLineNum = 154;BA.debugLine="namelb.Gravity=Gravity.LEFT+Gravity.CENTER_VERTIC";
_namelb.setGravity((int) (anywheresoftware.b4a.keywords.Common.Gravity.LEFT+anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL));
 //BA.debugLineNum = 155;BA.debugLine="namelb.TextColor=Colors.White";
_namelb.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 156;BA.debugLine="Dim glide As Amir_Glide";
_glide = new ir.aghajari.retrofitglide.Amir_Glide();
 //BA.debugLineNum = 157;BA.debugLine="glide.Initializer.Default";
_glide.Initializer(processBA).Default();
 //BA.debugLineNum = 158;BA.debugLine="glide.Load(profile_pic).Apply(glide.RO.CircleCrop";
_glide.Load((Object)(_profile_pic)).Apply(_glide.getRO().CircleCrop()).Into((android.widget.ImageView)(_profileimg.getObject()));
 //BA.debugLineNum = 159;BA.debugLine="unblockbtn.Text= \"UNBLOCK\"";
_unblockbtn.setText(BA.ObjectToCharSequence("UNBLOCK"));
 //BA.debugLineNum = 160;BA.debugLine="unblockbtn.Tag= i";
_unblockbtn.setTag((Object)(_i));
 //BA.debugLineNum = 161;BA.debugLine="unblockbtn.Typeface=mycode.defaultfont";
_unblockbtn.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._defaultfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 162;BA.debugLine="unblockbtn.Background = mycode.btnbgdynamic(mycod";
_unblockbtn.setBackground((android.graphics.drawable.Drawable)(mostCurrent._mycode._btnbgdynamic /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,mostCurrent._mycode._bgcolor /*int*/ ,anywheresoftware.b4a.keywords.Common.Colors.DarkGray,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))).getObject()));
 //BA.debugLineNum = 163;BA.debugLine="unblockbtn.TextColor=Colors.White";
_unblockbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 164;BA.debugLine="p.AddView(profileimg,0,0,40dip,40dip)";
_p.AddView((android.view.View)(_profileimg.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 165;BA.debugLine="Dim pw As Int = (100%x-40dip)";
_pw = (int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))));
 //BA.debugLineNum = 166;BA.debugLine="Dim l As Int =(profileimg.Left+profileimg.Width+1";
_l = (int) ((_profileimg.getLeft()+_profileimg.getWidth()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 167;BA.debugLine="Dim r As Int = 110dip";
_r = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (110));
 //BA.debugLineNum = 168;BA.debugLine="Dim w As Int = pw -(l+r)";
_w = (int) (_pw-(_l+_r));
 //BA.debugLineNum = 169;BA.debugLine="p.AddView(namelb,profileimg.Left+profileimg.Heigh";
_p.AddView((android.view.View)(_namelb.getObject()),(int) (_profileimg.getLeft()+_profileimg.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),(int) (0),_w,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 170;BA.debugLine="p.AddView(unblockbtn,namelb.Left+namelb.Width+10d";
_p.AddView((android.view.View)(_unblockbtn.getObject()),(int) (_namelb.getLeft()+_namelb.getWidth()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 171;BA.debugLine="p.Height = 40dip";
_p.setHeight(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 172;BA.debugLine="Return p";
if (true) return _p;
 //BA.debugLineNum = 173;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.collections.List  _blocklist() throws Exception{
anywheresoftware.b4a.objects.collections.List _ls = null;
anywheresoftware.b4a.objects.collections.JSONParser _json = null;
 //BA.debugLineNum = 175;BA.debugLine="Sub blocklist As List";
 //BA.debugLineNum = 176;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 177;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 178;BA.debugLine="Try";
try { //BA.debugLineNum = 179;BA.debugLine="If File.Exists(File.DirInternal,\"block\") Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"block")) { 
 //BA.debugLineNum = 180;BA.debugLine="Dim json As JSONParser";
_json = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 181;BA.debugLine="json.Initialize(File.ReadString(File.DirInterna";
_json.Initialize(anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"block"));
 //BA.debugLineNum = 182;BA.debugLine="ls = json.NextArray";
_ls = _json.NextArray();
 };
 } 
       catch (Exception e10) {
			processBA.setLastException(e10); //BA.debugLineNum = 185;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("27012362",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 //BA.debugLineNum = 187;BA.debugLine="Return ls";
if (true) return _ls;
 //BA.debugLineNum = 188;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.PanelWrapper  _blocklistpn() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.PanelWrapper _cv = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
anywheresoftware.b4a.objects.LabelWrapper _titlelb = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
anywheresoftware.b4a.objects.LabelWrapper _subtitle = null;
int _top = 0;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4a.objects.PanelWrapper _item = null;
 //BA.debugLineNum = 88;BA.debugLine="Sub blockListpn As Panel";
 //BA.debugLineNum = 89;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 90;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 91;BA.debugLine="p.RemoveAllViews";
_p.RemoveAllViews();
 //BA.debugLineNum = 92;BA.debugLine="Dim cv As Panel";
_cv = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 93;BA.debugLine="cv.Initialize(\"\")";
_cv.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 95;BA.debugLine="pbitem.Initialize(\"\")";
mostCurrent._pbitem.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 96;BA.debugLine="p.AddView(cv,10dip,10dip,100%x-20dip,50dip)";
_p.AddView((android.view.View)(_cv.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 97;BA.debugLine="cv.Elevation = 5dip";
_cv.setElevation((float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 //BA.debugLineNum = 98;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 99;BA.debugLine="cd.Initialize(mycode.naviColor,10dip)";
_cd.Initialize(mostCurrent._mycode._navicolor /*int*/ ,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 100;BA.debugLine="cv.Background=cd";
_cv.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 101;BA.debugLine="cv.AddView(pbitem,0,0,cv.Width,cv.Height)";
_cv.AddView((android.view.View)(mostCurrent._pbitem.getObject()),(int) (0),(int) (0),_cv.getWidth(),_cv.getHeight());
 //BA.debugLineNum = 102;BA.debugLine="Dim titlelb As Label";
_titlelb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 103;BA.debugLine="titlelb.Initialize(\"\")";
_titlelb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 104;BA.debugLine="titlelb.Typeface = mycode.defaultfont";
_titlelb.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._defaultfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 105;BA.debugLine="titlelb.TextSize =  mycode.textsize(10)";
_titlelb.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (10)));
 //BA.debugLineNum = 106;BA.debugLine="titlelb.Gravity=Gravity.CENTER";
_titlelb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 107;BA.debugLine="titlelb.TextColor=Colors.White";
_titlelb.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 108;BA.debugLine="titlelb.Text= \"Block Users\"";
_titlelb.setText(BA.ObjectToCharSequence("Block Users"));
 //BA.debugLineNum = 109;BA.debugLine="pbitem.AddView(titlelb,0,10dip,pbitem.Width-20dip";
mostCurrent._pbitem.AddView((android.view.View)(_titlelb.getObject()),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (mostCurrent._pbitem.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 110;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 111;BA.debugLine="titlelb.Height = su.MeasureMultilineTextHeight(ti";
_titlelb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_titlelb.getObject()),BA.ObjectToCharSequence(_titlelb.getText())));
 //BA.debugLineNum = 113;BA.debugLine="Dim subtitle As Label";
_subtitle = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 114;BA.debugLine="subtitle.Initialize(\"\")";
_subtitle.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 115;BA.debugLine="subtitle.Typeface = mycode.defaultfont";
_subtitle.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._defaultfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 116;BA.debugLine="subtitle.TextSize =  mycode.textsize(7)";
_subtitle.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (7)));
 //BA.debugLineNum = 117;BA.debugLine="subtitle.Gravity=Gravity.CENTER";
_subtitle.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 118;BA.debugLine="subtitle.TextColor=Colors.White";
_subtitle.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 119;BA.debugLine="subtitle.Text= \"Will Not See Message From This Us";
_subtitle.setText(BA.ObjectToCharSequence("Will Not See Message From This Users"));
 //BA.debugLineNum = 120;BA.debugLine="pbitem.AddView(subtitle,0,titlelb.Height+titlelb.";
mostCurrent._pbitem.AddView((android.view.View)(_subtitle.getObject()),(int) (0),(int) (_titlelb.getHeight()+_titlelb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),(int) (mostCurrent._pbitem.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 121;BA.debugLine="subtitle.Height = su.MeasureMultilineTextHeight(s";
_subtitle.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_subtitle.getObject()),BA.ObjectToCharSequence(_subtitle.getText())));
 //BA.debugLineNum = 123;BA.debugLine="Dim top As Int = subtitle.Height+subtitle.Top+10d";
_top = (int) (_subtitle.getHeight()+_subtitle.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 125;BA.debugLine="If blocklist.Size > 0 Then";
if (_blocklist().getSize()>0) { 
 //BA.debugLineNum = 126;BA.debugLine="For i = 0 To blocklist.Size -1";
{
final int step34 = 1;
final int limit34 = (int) (_blocklist().getSize()-1);
_i = (int) (0) ;
for (;_i <= limit34 ;_i = _i + step34 ) {
 //BA.debugLineNum = 127;BA.debugLine="Dim m As Map = blocklist.Get(i)";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_blocklist().Get(_i)));
 //BA.debugLineNum = 128;BA.debugLine="Dim item As Panel = blockitem(m.Get(\"name\"),m.G";
_item = new anywheresoftware.b4a.objects.PanelWrapper();
_item = _blockitem(BA.ObjectToString(_m.Get((Object)("name"))),BA.ObjectToString(_m.Get((Object)("profile_pic"))),_i);
 //BA.debugLineNum = 129;BA.debugLine="pbitem.AddView(item,10dip,top,pbitem.Width-20di";
mostCurrent._pbitem.AddView((android.view.View)(_item.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_top,(int) (mostCurrent._pbitem.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_item.getHeight());
 //BA.debugLineNum = 130;BA.debugLine="top = top +item.Height+10dip";
_top = (int) (_top+_item.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 }
};
 };
 //BA.debugLineNum = 133;BA.debugLine="pbitem.Height = top";
mostCurrent._pbitem.setHeight(_top);
 //BA.debugLineNum = 134;BA.debugLine="cv.Height =top";
_cv.setHeight(_top);
 //BA.debugLineNum = 135;BA.debugLine="p.Height = cv.Height+20dip";
_p.setHeight((int) (_cv.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))));
 //BA.debugLineNum = 138;BA.debugLine="Return p";
if (true) return _p;
 //BA.debugLineNum = 139;BA.debugLine="End Sub";
return null;
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 9;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 10;BA.debugLine="Dim pf As Panel";
mostCurrent._pf = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 11;BA.debugLine="Dim blistpn As Panel";
mostCurrent._blistpn = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 12;BA.debugLine="Dim pbitem As Panel";
mostCurrent._pbitem = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
public static String  _logoutbtn_click() throws Exception{
anywheresoftware.b4a.objects.FirebaseAuthWrapper _auth = null;
 //BA.debugLineNum = 212;BA.debugLine="Sub logoutbtn_click";
 //BA.debugLineNum = 214;BA.debugLine="Dim auth As FirebaseAuth";
_auth = new anywheresoftware.b4a.objects.FirebaseAuthWrapper();
 //BA.debugLineNum = 215;BA.debugLine="auth.Initialize(\"\")";
_auth.Initialize(processBA,"");
 //BA.debugLineNum = 216;BA.debugLine="If File.Exists(File.DirInternal,\"user\") Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"user")) { 
 //BA.debugLineNum = 217;BA.debugLine="File.Delete(File.DirInternal,\"user\")";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"user");
 //BA.debugLineNum = 218;BA.debugLine="If File.Exists(File.DirInternal,\"block\") Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"block")) { 
 //BA.debugLineNum = 219;BA.debugLine="File.Delete(File.DirInternal,\"block\")";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"block");
 };
 //BA.debugLineNum = 221;BA.debugLine="auth.SignOutFromGoogle";
_auth.SignOutFromGoogle();
 //BA.debugLineNum = 222;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 224;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.PanelWrapper  _profile() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.PanelWrapper _pwall = null;
anywheresoftware.b4a.objects.ImageViewWrapper _img = null;
ir.aghajari.retrofitglide.Amir_Glide _glide = null;
int _l = 0;
anywheresoftware.b4a.objects.LabelWrapper _lblname = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
anywheresoftware.b4a.objects.LabelWrapper _lblemail = null;
 //BA.debugLineNum = 39;BA.debugLine="Sub profile As Panel";
 //BA.debugLineNum = 40;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 41;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 42;BA.debugLine="Dim pwall As Panel";
_pwall = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 43;BA.debugLine="pwall.Initialize(\"\")";
_pwall.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 44;BA.debugLine="p.AddView(pwall,0,0,100%x,40%x)";
_p.AddView((android.view.View)(_pwall.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (40),mostCurrent.activityBA));
 //BA.debugLineNum = 45;BA.debugLine="pwall.SetBackgroundImage(LoadBitmap(File.DirAsset";
_pwall.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"profile_bg.webp").getObject()));
 //BA.debugLineNum = 46;BA.debugLine="Dim img As ImageView";
_img = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 47;BA.debugLine="img.Initialize(\"\")";
_img.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 48;BA.debugLine="Dim glide As Amir_Glide";
_glide = new ir.aghajari.retrofitglide.Amir_Glide();
 //BA.debugLineNum = 49;BA.debugLine="glide.Initializer.Default";
_glide.Initializer(processBA).Default();
 //BA.debugLineNum = 50;BA.debugLine="glide.Load(mycode.getUserId(mycode.profile_pic)).";
_glide.Load((Object)(mostCurrent._mycode._getuserid /*String*/ (mostCurrent.activityBA,mostCurrent._mycode._profile_pic /*String*/ ))).Apply(_glide.getRO().CircleCrop()).Into((android.widget.ImageView)(_img.getObject()));
 //BA.debugLineNum = 51;BA.debugLine="Dim l As Int = (100%x-70dip)/2";
_l = (int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)))/(double)2);
 //BA.debugLineNum = 52;BA.debugLine="p.AddView(img,l,pwall.Height - 35dip,70dip,70dip)";
_p.AddView((android.view.View)(_img.getObject()),_l,(int) (_pwall.getHeight()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (35))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (70)));
 //BA.debugLineNum = 53;BA.debugLine="Dim lblname As Label";
_lblname = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 54;BA.debugLine="lblname.Initialize(\"\")";
_lblname.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 55;BA.debugLine="lblname.text = mycode.getUserId(mycode.name)";
_lblname.setText(BA.ObjectToCharSequence(mostCurrent._mycode._getuserid /*String*/ (mostCurrent.activityBA,mostCurrent._mycode._name /*String*/ )));
 //BA.debugLineNum = 56;BA.debugLine="lblname.Typeface=mycode.defaultfont";
_lblname.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._defaultfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 57;BA.debugLine="lblname.Gravity=Gravity.CENTER";
_lblname.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 58;BA.debugLine="lblname.TextSize = mycode.textsize (9)";
_lblname.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (9)));
 //BA.debugLineNum = 59;BA.debugLine="lblname.TextColor=Colors.White";
_lblname.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 60;BA.debugLine="p.AddView(lblname,0,img.Height+img.Top+10dip,100%";
_p.AddView((android.view.View)(_lblname.getObject()),(int) (0),(int) (_img.getHeight()+_img.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 61;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 62;BA.debugLine="lblname.Height = su.MeasureMultilineTextHeight(lb";
_lblname.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_lblname.getObject()),BA.ObjectToCharSequence(_lblname.getText())));
 //BA.debugLineNum = 63;BA.debugLine="Dim lblemail As Label";
_lblemail = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 64;BA.debugLine="lblemail.Initialize(\"\")";
_lblemail.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 65;BA.debugLine="lblemail.text = mycode.getUserId(mycode.email)";
_lblemail.setText(BA.ObjectToCharSequence(mostCurrent._mycode._getuserid /*String*/ (mostCurrent.activityBA,mostCurrent._mycode._email /*String*/ )));
 //BA.debugLineNum = 66;BA.debugLine="lblemail.Typeface=mycode.defaultfont";
_lblemail.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._defaultfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 67;BA.debugLine="lblemail.Gravity=Gravity.CENTER";
_lblemail.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 68;BA.debugLine="lblemail.TextSize = mycode.textsize (8)";
_lblemail.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (8)));
 //BA.debugLineNum = 69;BA.debugLine="lblemail.TextColor=Colors.White";
_lblemail.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 70;BA.debugLine="p.AddView(lblemail,0,lblname.Height+lblname.Top+1";
_p.AddView((android.view.View)(_lblemail.getObject()),(int) (0),(int) (_lblname.getHeight()+_lblname.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 71;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 72;BA.debugLine="lblemail.Height = su.MeasureMultilineTextHeight(l";
_lblemail.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_lblemail.getObject()),BA.ObjectToCharSequence(_lblemail.getText())));
 //BA.debugLineNum = 73;BA.debugLine="p.Height = lblemail.Height+lblemail.Top";
_p.setHeight((int) (_lblemail.getHeight()+_lblemail.getTop()));
 //BA.debugLineNum = 75;BA.debugLine="Return p";
if (true) return _p;
 //BA.debugLineNum = 76;BA.debugLine="End Sub";
return null;
}
public static String  _unblockbtn_click() throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
int _i = 0;
 //BA.debugLineNum = 189;BA.debugLine="Sub unblockbtn_click";
 //BA.debugLineNum = 190;BA.debugLine="Dim b As Button = Sender";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 191;BA.debugLine="Dim i As Int = b.Tag";
_i = (int)(BA.ObjectToNumber(_b.getTag()));
 //BA.debugLineNum = 192;BA.debugLine="unlock(i)";
_unlock(_i);
 //BA.debugLineNum = 193;BA.debugLine="End Sub";
return "";
}
public static String  _unlock(int _i) throws Exception{
anywheresoftware.b4a.objects.collections.List _ls = null;
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _js = null;
 //BA.debugLineNum = 195;BA.debugLine="Sub unlock(i As Int)";
 //BA.debugLineNum = 196;BA.debugLine="If blocklist.Size>0 Then";
if (_blocklist().getSize()>0) { 
 //BA.debugLineNum = 197;BA.debugLine="Dim ls As List  = blocklist";
_ls = new anywheresoftware.b4a.objects.collections.List();
_ls = _blocklist();
 //BA.debugLineNum = 199;BA.debugLine="ls.RemoveAt(i)";
_ls.RemoveAt(_i);
 //BA.debugLineNum = 201;BA.debugLine="Dim js As JSONGenerator";
_js = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
 //BA.debugLineNum = 202;BA.debugLine="js.Initialize2(ls)";
_js.Initialize2(_ls);
 //BA.debugLineNum = 203;BA.debugLine="File.WriteString(File.DirInternal,\"block\",js.ToS";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"block",_js.ToString());
 //BA.debugLineNum = 204;BA.debugLine="addblockpn";
_addblockpn();
 };
 //BA.debugLineNum = 206;BA.debugLine="End Sub";
return "";
}
}
