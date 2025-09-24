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

public class login extends Activity implements B4AActivity{
	public static login mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "com.burma.royal2d", "com.burma.royal2d.login");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (login).");
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
		activityBA = new BA(this, layout, processBA, "com.burma.royal2d", "com.burma.royal2d.login");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.burma.royal2d.login", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (login) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (login) Resume **");
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
		return login.class;
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
            BA.LogInfo("** Activity (login) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (login) Pause event (activity is not paused). **");
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
            login mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (login) Resume **");
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
public static anywheresoftware.b4a.objects.FirebaseAuthWrapper _auth = null;
public com.aghajari.axrlottie.AXrLottie _axrlottie = null;
public com.aghajari.axrlottie.AXrLottieImageView _lottieview = null;
public com.burma.royal2d.main _main = null;
public com.burma.royal2d.report_details _report_details = null;
public com.burma.royal2d.mycode _mycode = null;
public com.burma.royal2d.public_chat _public_chat = null;
public com.burma.royal2d.gift_imageview _gift_imageview = null;
public com.burma.royal2d.settings _settings = null;
public com.burma.royal2d.profile_activity _profile_activity = null;
public com.burma.royal2d.guideline _guideline = null;
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
public static void  _activity_create(boolean _firsttime) throws Exception{
ResumableSub_Activity_Create rsub = new ResumableSub_Activity_Create(null,_firsttime);
rsub.resume(processBA, null);
}
public static class ResumableSub_Activity_Create extends BA.ResumableSub {
public ResumableSub_Activity_Create(com.burma.royal2d.login parent,boolean _firsttime) {
this.parent = parent;
this._firsttime = _firsttime;
}
com.burma.royal2d.login parent;
boolean _firsttime;
anywheresoftware.b4a.objects.PanelWrapper _appbar = null;
com.aghajari.axrlottie.AXrLottieDrawableBuilder _drawable = null;
anywheresoftware.b4a.objects.PanelWrapper _base = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 21;BA.debugLine="Activity.Color=mycode.bgColor";
parent.mostCurrent._activity.setColor(parent.mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 22;BA.debugLine="mycode.SETnavigationcolor";
parent.mostCurrent._mycode._setnavigationcolor /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 23;BA.debugLine="Dim appbar As Panel = mycode.appbar(\"Login\",False";
_appbar = new anywheresoftware.b4a.objects.PanelWrapper();
_appbar = parent.mostCurrent._mycode._appbar /*anywheresoftware.b4a.objects.PanelWrapper*/ (mostCurrent.activityBA,"Login",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 24;BA.debugLine="Activity.AddView(appbar,0,mycode.appbartop,100%x,";
parent.mostCurrent._activity.AddView((android.view.View)(_appbar.getObject()),(int) (0),parent.mostCurrent._mycode._appbartop /*int*/ ,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),parent.mostCurrent._mycode._appbarheight /*int*/ );
 //BA.debugLineNum = 27;BA.debugLine="AXrLottie.Initialize";
parent.mostCurrent._axrlottie.Initialize();
 //BA.debugLineNum = 28;BA.debugLine="LottieView.Initialize(\"\")";
parent.mostCurrent._lottieview.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 30;BA.debugLine="Sleep(100)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (100));
this.state = 5;
return;
case 5:
//C
this.state = 1;
;
 //BA.debugLineNum = 31;BA.debugLine="Dim Drawable As AXrLottieDrawableBuilder";
_drawable = new com.aghajari.axrlottie.AXrLottieDrawableBuilder();
 //BA.debugLineNum = 32;BA.debugLine="Drawable.InitializeFromFile(File.DirAssets,\"login";
_drawable.InitializeFromFile(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"login.json").SetAutoRepeat(_drawable.AUTO_REPEAT_INFINITE).SetAutoStart(anywheresoftware.b4a.keywords.Common.True).SetCacheEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 36;BA.debugLine="LottieView.SetLottieDrawable(Drawable.Build)";
parent.mostCurrent._lottieview.SetLottieDrawable((com.aghajari.rlottie.AXrLottieDrawable)(_drawable.Build().getObject()));
 //BA.debugLineNum = 38;BA.debugLine="Dim base As Panel = pbase";
_base = new anywheresoftware.b4a.objects.PanelWrapper();
_base = _pbase();
 //BA.debugLineNum = 39;BA.debugLine="Activity.AddView(base,0,((mycode.ActivityHeight-m";
parent.mostCurrent._activity.AddView((android.view.View)(_base.getObject()),(int) (0),(int) (((parent.mostCurrent._mycode._activityheight /*int*/ -parent.mostCurrent._mycode._appbarheight /*int*/ )-_base.getHeight())/(double)2),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),_base.getHeight());
 //BA.debugLineNum = 40;BA.debugLine="If FirstTime Then";
if (true) break;

case 1:
//if
this.state = 4;
if (_firsttime) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 41;BA.debugLine="auth.Initialize(\"auth\")";
parent._auth.Initialize(processBA,"auth");
 if (true) break;

case 4:
//C
this.state = -1;
;
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 50;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 52;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 46;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 48;BA.debugLine="End Sub";
return "";
}
public static String  _auth_signedin(anywheresoftware.b4a.objects.FirebaseAuthWrapper.FirebaseUserWrapper _user) throws Exception{
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _json = null;
 //BA.debugLineNum = 79;BA.debugLine="Sub auth_SignedIn (User As FirebaseUser)";
 //BA.debugLineNum = 80;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 81;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 82;BA.debugLine="m.Put(\"id\",User.Uid)";
_m.Put((Object)("id"),(Object)(_user.getUid()));
 //BA.debugLineNum = 83;BA.debugLine="m.Put(\"name\",User.DisplayName)";
_m.Put((Object)("name"),(Object)(_user.getDisplayName()));
 //BA.debugLineNum = 84;BA.debugLine="m.Put(\"email\",User.Email)";
_m.Put((Object)("email"),(Object)(_user.getEmail()));
 //BA.debugLineNum = 85;BA.debugLine="m.Put(\"profile_pic\",User.PhotoUrl)";
_m.Put((Object)("profile_pic"),(Object)(_user.getPhotoUrl()));
 //BA.debugLineNum = 86;BA.debugLine="Dim json As JSONGenerator";
_json = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
 //BA.debugLineNum = 87;BA.debugLine="json.Initialize(m)";
_json.Initialize(_m);
 //BA.debugLineNum = 88;BA.debugLine="File.WriteString(File.DirInternal,\"user\",json.ToS";
anywheresoftware.b4a.keywords.Common.File.WriteString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"user",_json.ToString());
 //BA.debugLineNum = 89;BA.debugLine="If File.Exists(File.DirInternal,\"user\") Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"user")) { 
 //BA.debugLineNum = 90;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 };
 //BA.debugLineNum = 93;BA.debugLine="End Sub";
return "";
}
public static String  _auth_signerror(anywheresoftware.b4a.objects.B4AException _error) throws Exception{
 //BA.debugLineNum = 75;BA.debugLine="Sub auth_SignError (Error As Exception)";
 //BA.debugLineNum = 76;BA.debugLine="ToastMessageShow(\"error\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("error"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 77;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
 //BA.debugLineNum = 95;BA.debugLine="Sub backbtn_click";
 //BA.debugLineNum = 96;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 97;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 15;BA.debugLine="Dim AXrLottie As AXrLottie";
mostCurrent._axrlottie = new com.aghajari.axrlottie.AXrLottie();
 //BA.debugLineNum = 16;BA.debugLine="Dim LottieView As AXrLottieImageView";
mostCurrent._lottieview = new com.aghajari.axrlottie.AXrLottieImageView();
 //BA.debugLineNum = 17;BA.debugLine="End Sub";
return "";
}
public static String  _loginbtn_click() throws Exception{
 //BA.debugLineNum = 54;BA.debugLine="Sub loginbtn_click";
 //BA.debugLineNum = 55;BA.debugLine="auth.SignInWithGoogle";
_auth.SignInWithGoogle(processBA);
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.PanelWrapper  _pbase() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
int _left = 0;
int _width = 0;
int _height = 0;
anywheresoftware.b4a.objects.ButtonWrapper _loginbtn = null;
 //BA.debugLineNum = 58;BA.debugLine="Sub pbase As Panel";
 //BA.debugLineNum = 59;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 60;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 61;BA.debugLine="Dim bmp As Bitmap";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 62;BA.debugLine="bmp.Initialize(File.DirAssets, \"lg.webp\")";
_bmp.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lg.webp");
 //BA.debugLineNum = 63;BA.debugLine="Dim left As Int = (100%x-(300dip))/2";
_left = (int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300))))/(double)2);
 //BA.debugLineNum = 64;BA.debugLine="p.AddView(LottieView,left,0,(300dip),(300dip)/1.5";
_p.AddView((android.view.View)(mostCurrent._lottieview.getObject()),_left,(int) (0),(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300))),(int) ((anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (300)))/(double)1.5));
 //BA.debugLineNum = 65;BA.debugLine="Dim width As Int = bmp.Width/1.5";
_width = (int) (_bmp.getWidth()/(double)1.5);
 //BA.debugLineNum = 66;BA.debugLine="Dim height As Int = bmp.Height/1.5";
_height = (int) (_bmp.getHeight()/(double)1.5);
 //BA.debugLineNum = 67;BA.debugLine="Dim loginbtn As Button";
_loginbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 68;BA.debugLine="loginbtn.Initialize(\"loginbtn\")";
_loginbtn.Initialize(mostCurrent.activityBA,"loginbtn");
 //BA.debugLineNum = 69;BA.debugLine="loginbtn.SetBackgroundImage(LoadBitmap(File.DirAs";
_loginbtn.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"lg.webp").getObject()));
 //BA.debugLineNum = 70;BA.debugLine="p.AddView(loginbtn,(100%x-width)/2,LottieView.Hei";
_p.AddView((android.view.View)(_loginbtn.getObject()),(int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-_width)/(double)2),(int) (mostCurrent._lottieview.getHeight()+mostCurrent._lottieview.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_width,_height);
 //BA.debugLineNum = 71;BA.debugLine="p.Height = loginbtn.Height+loginbtn.Top";
_p.setHeight((int) (_loginbtn.getHeight()+_loginbtn.getTop()));
 //BA.debugLineNum = 72;BA.debugLine="Return p";
if (true) return _p;
 //BA.debugLineNum = 73;BA.debugLine="End Sub";
return null;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim auth As FirebaseAuth";
_auth = new anywheresoftware.b4a.objects.FirebaseAuthWrapper();
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
}
