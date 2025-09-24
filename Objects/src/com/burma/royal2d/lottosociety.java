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

public class lottosociety extends Activity implements B4AActivity{
	public static lottosociety mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "com.burma.royal2d", "com.burma.royal2d.lottosociety");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (lottosociety).");
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
		activityBA = new BA(this, layout, processBA, "com.burma.royal2d", "com.burma.royal2d.lottosociety");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.burma.royal2d.lottosociety", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (lottosociety) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (lottosociety) Resume **");
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
		return lottosociety.class;
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
            BA.LogInfo("** Activity (lottosociety) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (lottosociety) Pause event (activity is not paused). **");
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
            lottosociety mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (lottosociety) Resume **");
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
public static anywheresoftware.b4a.objects.StringUtils _su = null;
public static String _lottogetter = "";
public static boolean _iscall = false;
public com.aghajari.axrlottie.AXrLottieImageView _lottieview = null;
public anywheresoftware.b4a.objects.LabelWrapper _datelb = null;
public anywheresoftware.b4a.objects.LabelWrapper _numlb = null;
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
public com.burma.royal2d.lottohistory _lottohistory = null;
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
public ResumableSub_Activity_Create(com.burma.royal2d.lottosociety parent,boolean _firsttime) {
this.parent = parent;
this._firsttime = _firsttime;
}
com.burma.royal2d.lottosociety parent;
boolean _firsttime;
anywheresoftware.b4a.objects.PanelWrapper _appbar = null;
anywheresoftware.b4a.objects.ButtonWrapper _historybtn = null;
com.aghajari.axrlottie.AXrLottie _axrlottie = null;
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
 //BA.debugLineNum = 24;BA.debugLine="Activity.Color=mycode.bgColor";
parent.mostCurrent._activity.setColor(parent.mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 25;BA.debugLine="Dim appbar As Panel = mycode.appbar(\"ထုတ်လွှင့်မှ";
_appbar = new anywheresoftware.b4a.objects.PanelWrapper();
_appbar = parent.mostCurrent._mycode._appbar /*anywheresoftware.b4a.objects.PanelWrapper*/ (mostCurrent.activityBA,"ထုတ်လွှင့်မှုမှတ်စဥ် ၉",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 26;BA.debugLine="Activity.AddView(appbar,0,mycode.appbartop,100%x,";
parent.mostCurrent._activity.AddView((android.view.View)(_appbar.getObject()),(int) (0),parent.mostCurrent._mycode._appbartop /*int*/ ,anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),parent.mostCurrent._mycode._appbarheight /*int*/ );
 //BA.debugLineNum = 27;BA.debugLine="Dim historybtn As Button";
_historybtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 28;BA.debugLine="historybtn.Initialize(\"historybtn\")";
_historybtn.Initialize(mostCurrent.activityBA,"historybtn");
 //BA.debugLineNum = 29;BA.debugLine="historybtn.Background=mycode.btnbg(False)";
_historybtn.setBackground((android.graphics.drawable.Drawable)(parent.mostCurrent._mycode._btnbg /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.False).getObject()));
 //BA.debugLineNum = 30;BA.debugLine="historybtn.Text = buttoncsb(False,Chr(0xF1EC),Tru";
_historybtn.setText(BA.ObjectToCharSequence(_buttoncsb(anywheresoftware.b4a.keywords.Common.False,BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf1ec))),anywheresoftware.b4a.keywords.Common.True).getObject()));
 //BA.debugLineNum = 31;BA.debugLine="appbar.AddView(historybtn,100%x-(mycode.appbarhei";
_appbar.AddView((android.view.View)(_historybtn.getObject()),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-(parent.mostCurrent._mycode._appbarheight /*int*/ )),(int) (0),parent.mostCurrent._mycode._appbarheight /*int*/ ,parent.mostCurrent._mycode._appbarheight /*int*/ );
 //BA.debugLineNum = 32;BA.debugLine="mycode.SETnavigationcolor";
parent.mostCurrent._mycode._setnavigationcolor /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 34;BA.debugLine="scv.Initialize(1000dip)";
parent.mostCurrent._scv.Initialize(mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1000)));
 //BA.debugLineNum = 35;BA.debugLine="Activity.AddView(scv,0,mycode.appbarheight+5dip,1";
parent.mostCurrent._activity.AddView((android.view.View)(parent.mostCurrent._scv.getObject()),(int) (0),(int) (parent.mostCurrent._mycode._appbarheight /*int*/ +anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),(int) (parent.mostCurrent._mycode._activityheight /*int*/ -parent.mostCurrent._mycode._appbarheight /*int*/ +anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 //BA.debugLineNum = 37;BA.debugLine="Dim axrlottie As AXrLottie";
_axrlottie = new com.aghajari.axrlottie.AXrLottie();
 //BA.debugLineNum = 38;BA.debugLine="axrlottie.Initialize";
_axrlottie.Initialize();
 //BA.debugLineNum = 39;BA.debugLine="lottieview.Initialize(\"\")";
parent.mostCurrent._lottieview.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 40;BA.debugLine="Dim left As Int = (100%x-150dip)/2";
_left = (int) ((anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)))/(double)2);
 //BA.debugLineNum = 41;BA.debugLine="Sleep(0)";
anywheresoftware.b4a.keywords.Common.Sleep(processBA,this,(int) (0));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 //BA.debugLineNum = 42;BA.debugLine="scv.Panel.AddView(lottieview,left,(mycode.Activit";
parent.mostCurrent._scv.getPanel().AddView((android.view.View)(parent.mostCurrent._lottieview.getObject()),_left,(int) ((parent.mostCurrent._mycode._activityheight /*int*/ -anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)))/(double)2),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)));
 //BA.debugLineNum = 43;BA.debugLine="Dim Drawable As AXrLottieDrawableBuilder";
_drawable = new com.aghajari.axrlottie.AXrLottieDrawableBuilder();
 //BA.debugLineNum = 44;BA.debugLine="Drawable.InitializeFromFile(File.DirAssets,\"peace";
_drawable.InitializeFromFile(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"peace.json").SetAutoRepeat(_drawable.AUTO_REPEAT_INFINITE).SetAutoStart(anywheresoftware.b4a.keywords.Common.True).SetCacheEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 48;BA.debugLine="lottieview.SetLottieDrawable(Drawable.Build)";
parent.mostCurrent._lottieview.SetLottieDrawable((com.aghajari.rlottie.AXrLottieDrawable)(_drawable.Build().getObject()));
 //BA.debugLineNum = 51;BA.debugLine="ApiCall.getlottodata";
parent.mostCurrent._apicall._getlottodata /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 67;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 73;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 74;BA.debugLine="isCall =False";
_iscall = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 75;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 69;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 70;BA.debugLine="isCall =True";
_iscall = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 71;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
 //BA.debugLineNum = 77;BA.debugLine="Sub backbtn_click";
 //BA.debugLineNum = 78;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 79;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.CSBuilder  _buttoncsb(boolean _home,String _text,boolean _iconic) throws Exception{
anywheresoftware.b4a.objects.CSBuilder _csb = null;
 //BA.debugLineNum = 85;BA.debugLine="Sub buttoncsb(home As Boolean,text As String,iconi";
 //BA.debugLineNum = 86;BA.debugLine="Dim csb As CSBuilder";
_csb = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 87;BA.debugLine="csb.Initialize";
_csb.Initialize();
 //BA.debugLineNum = 88;BA.debugLine="If home = True Then";
if (_home==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 90;BA.debugLine="csb.Color(Colors.Green).Typeface(Typeface.DEFAUL";
_csb.Color(anywheresoftware.b4a.keywords.Common.Colors.Green).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD).Size((int) (20)).Append(BA.ObjectToCharSequence(_text)).PopAll();
 }else {
 //BA.debugLineNum = 92;BA.debugLine="If iconic = True Then";
if (_iconic==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 93;BA.debugLine="If text  = Chr(0xF06B) Then";
if ((_text).equals(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf06b))))) { 
 //BA.debugLineNum = 94;BA.debugLine="csb.Color(0xFFDBDBDB).Typeface(Typeface.FONTAW";
_csb.Color(((int)0xffdbdbdb)).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME()).Size((int) (35)).Append(BA.ObjectToCharSequence(_text)).PopAll();
 }else {
 //BA.debugLineNum = 96;BA.debugLine="csb.Color(0xFFDBDBDB).Typeface(Typeface.FONTAW";
_csb.Color(((int)0xffdbdbdb)).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME()).Size((int) (20)).Append(BA.ObjectToCharSequence(_text)).PopAll();
 };
 }else {
 //BA.debugLineNum = 101;BA.debugLine="csb.Color(0xFFDBDBDB).Typeface(Typeface.DEFAULT";
_csb.Color(((int)0xffdbdbdb)).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD).Size((int) (20)).Append(BA.ObjectToCharSequence(_text)).PopAll();
 };
 };
 //BA.debugLineNum = 106;BA.debugLine="Return csb";
if (true) return _csb;
 //BA.debugLineNum = 107;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.PanelWrapper  _channelcard(int _pw,String _date,String _fnum,String _snum) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
anywheresoftware.b4a.objects.LabelWrapper _apptitlelb = null;
anywheresoftware.b4a.objects.ImageViewWrapper _img = null;
anywheresoftware.b4a.objects.ImageViewWrapper _img1 = null;
anywheresoftware.b4a.objects.LabelWrapper _namelb = null;
int _w = 0;
 //BA.debugLineNum = 109;BA.debugLine="Sub channelcard(pw As Int,date As String,fnum As S";
 //BA.debugLineNum = 111;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 112;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 113;BA.debugLine="p.Elevation =5dip";
_p.setElevation((float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 //BA.debugLineNum = 114;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 115;BA.debugLine="cd.Initialize(mycode.naviColor,10dip)";
_cd.Initialize(mostCurrent._mycode._navicolor /*int*/ ,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 116;BA.debugLine="p.Background=cd";
_p.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 117;BA.debugLine="Dim apptitlelb As Label";
_apptitlelb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 119;BA.debugLine="apptitlelb.Initialize(\"\")";
_apptitlelb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 120;BA.debugLine="apptitlelb.Text= \"Burma 2D 2025\"";
_apptitlelb.setText(BA.ObjectToCharSequence("Burma 2D 2025"));
 //BA.debugLineNum = 121;BA.debugLine="apptitlelb.Typeface=mycode.semibold";
_apptitlelb.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._semibold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 122;BA.debugLine="apptitlelb.TextColor=0xFFF4F4F4";
_apptitlelb.setTextColor(((int)0xfff4f4f4));
 //BA.debugLineNum = 123;BA.debugLine="apptitlelb.Gravity=Gravity.CENTER";
_apptitlelb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 124;BA.debugLine="apptitlelb.TextSize = mycode.textsize(11)";
_apptitlelb.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (11)));
 //BA.debugLineNum = 126;BA.debugLine="Dim img As ImageView";
_img = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 127;BA.debugLine="img.Initialize(\"\")";
_img.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 128;BA.debugLine="img.SetBackgroundImage(LoadBitmap(File.DirAssets,";
_img.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"logor.webp").getObject()));
 //BA.debugLineNum = 129;BA.debugLine="img.Gravity=Gravity.FILL";
_img.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 130;BA.debugLine="Dim img1 As ImageView";
_img1 = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 131;BA.debugLine="img1.Initialize(\"\")";
_img1.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 132;BA.debugLine="img1.SetBackgroundImage(LoadBitmap(File.DirAssets";
_img1.SetBackgroundImageNew((android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.LoadBitmap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"logor.webp").getObject()));
 //BA.debugLineNum = 133;BA.debugLine="img1.Gravity=Gravity.FILL";
_img1.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.FILL);
 //BA.debugLineNum = 136;BA.debugLine="datelb.Initialize(\"\")";
mostCurrent._datelb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 137;BA.debugLine="datelb.Text=date";
mostCurrent._datelb.setText(BA.ObjectToCharSequence(_date));
 //BA.debugLineNum = 138;BA.debugLine="datelb.SingleLine=True";
mostCurrent._datelb.setSingleLine(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 139;BA.debugLine="datelb.TextSize = mycode.textsize(9)";
mostCurrent._datelb.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (9)));
 //BA.debugLineNum = 140;BA.debugLine="datelb.Typeface=mycode.defaultfont";
mostCurrent._datelb.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._defaultfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 141;BA.debugLine="datelb.TextColor=Colors.White";
mostCurrent._datelb.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 142;BA.debugLine="datelb.Gravity=Gravity.CENTER";
mostCurrent._datelb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 144;BA.debugLine="Dim namelb As Label";
_namelb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 145;BA.debugLine="namelb.Initialize(\"\")";
_namelb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 146;BA.debugLine="namelb.SingleLine=True";
_namelb.setSingleLine(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 147;BA.debugLine="namelb.Text=\"Thai Stock Analysis\"";
_namelb.setText(BA.ObjectToCharSequence("Thai Stock Analysis"));
 //BA.debugLineNum = 148;BA.debugLine="namelb.TextSize = mycode.textsize(9)";
_namelb.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (9)));
 //BA.debugLineNum = 149;BA.debugLine="namelb.Typeface=mycode.semibold";
_namelb.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._semibold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 150;BA.debugLine="namelb.TextColor=Colors.Yellow";
_namelb.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.Yellow);
 //BA.debugLineNum = 151;BA.debugLine="namelb.Gravity=Gravity.CENTER";
_namelb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 154;BA.debugLine="numlb.Initialize(\"\")";
mostCurrent._numlb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 155;BA.debugLine="numlb.TextSize =mycode.textsize(13)";
mostCurrent._numlb.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (13)));
 //BA.debugLineNum = 156;BA.debugLine="numlb.TextColor=Colors.White";
mostCurrent._numlb.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 157;BA.debugLine="numlb.Text = fnum&Chr(9)&Chr(9)&snum";
mostCurrent._numlb.setText(BA.ObjectToCharSequence(_fnum+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (9)))+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (9)))+_snum));
 //BA.debugLineNum = 158;BA.debugLine="numlb.Gravity=Gravity.CENTER";
mostCurrent._numlb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 159;BA.debugLine="numlb.Typeface=mycode.semibold";
mostCurrent._numlb.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._semibold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 161;BA.debugLine="Dim w As Int = 40dip";
_w = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40));
 //BA.debugLineNum = 162;BA.debugLine="p.AddView(img,10dip,10dip,w,w)";
_p.AddView((android.view.View)(_img.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_w,_w);
 //BA.debugLineNum = 163;BA.debugLine="p.AddView(apptitlelb,img.Width+img.Left,10dip,pw-";
_p.AddView((android.view.View)(_apptitlelb.getObject()),(int) (_img.getWidth()+_img.getLeft()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_pw-(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (120)))),_w);
 //BA.debugLineNum = 164;BA.debugLine="p.AddView(img1,(pw)-(w+10dip),10dip,w,w)";
_p.AddView((android.view.View)(_img1.getObject()),(int) ((_pw)-(_w+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_w,_w);
 //BA.debugLineNum = 165;BA.debugLine="p.AddView(datelb,0,img.Height+apptitlelb.Top+15di";
_p.AddView((android.view.View)(mostCurrent._datelb.getObject()),(int) (0),(int) (_img.getHeight()+_apptitlelb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (15))),_pw,_w);
 //BA.debugLineNum = 166;BA.debugLine="datelb.Height=su.MeasureMultilineTextHeight(datel";
mostCurrent._datelb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(mostCurrent._datelb.getObject()),BA.ObjectToCharSequence(mostCurrent._datelb.getText())));
 //BA.debugLineNum = 167;BA.debugLine="p.AddView(namelb,0,datelb.Height+datelb.Top+18dip";
_p.AddView((android.view.View)(_namelb.getObject()),(int) (0),(int) (mostCurrent._datelb.getHeight()+mostCurrent._datelb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (18))),_pw,_w);
 //BA.debugLineNum = 168;BA.debugLine="namelb.Height=su.MeasureMultilineTextHeight(namel";
_namelb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_namelb.getObject()),BA.ObjectToCharSequence(_namelb.getText())));
 //BA.debugLineNum = 169;BA.debugLine="p.AddView(numlb,0,namelb.Height+namelb.Top+13dip,";
_p.AddView((android.view.View)(mostCurrent._numlb.getObject()),(int) (0),(int) (_namelb.getHeight()+_namelb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (13))),_pw,_w);
 //BA.debugLineNum = 171;BA.debugLine="p.Height =numlb.Height + numlb.Top+10dip";
_p.setHeight((int) (mostCurrent._numlb.getHeight()+mostCurrent._numlb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 173;BA.debugLine="Return p";
if (true) return _p;
 //BA.debugLineNum = 174;BA.debugLine="End Sub";
return null;
}
public static String  _getlottosuccess() throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _json = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.PanelWrapper _ccard = null;
anywheresoftware.b4a.objects.PanelWrapper _rw = null;
anywheresoftware.b4a.objects.PanelWrapper _mnumber = null;
 //BA.debugLineNum = 178;BA.debugLine="Sub getlottosuccess";
 //BA.debugLineNum = 179;BA.debugLine="If ApiCall.lottodata <> \"\" Then";
if ((mostCurrent._apicall._lottodata /*String*/ ).equals("") == false) { 
 //BA.debugLineNum = 180;BA.debugLine="lottieview.Visible=False";
mostCurrent._lottieview.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 181;BA.debugLine="lottieview.RemoveView";
mostCurrent._lottieview.RemoveView();
 //BA.debugLineNum = 182;BA.debugLine="Dim json As JSONParser";
_json = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 183;BA.debugLine="json.Initialize(ApiCall.lottodata)";
_json.Initialize(mostCurrent._apicall._lottodata /*String*/ );
 //BA.debugLineNum = 185;BA.debugLine="Dim  m As Map = json.NextObject";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = _json.NextObject();
 //BA.debugLineNum = 186;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 187;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 189;BA.debugLine="Dim Ccard As Panel = channelcard(100%x-20dip,m.";
_ccard = new anywheresoftware.b4a.objects.PanelWrapper();
_ccard = _channelcard((int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),BA.ObjectToString(_m.Get((Object)("thaidate"))),BA.ObjectToString(_m.Get((Object)("fnum"))),BA.ObjectToString(_m.Get((Object)("snum"))));
 //BA.debugLineNum = 190;BA.debugLine="Ccard.SetLayoutAnimated(1000,10dip,10dip,scv.Wi";
_ccard.SetLayoutAnimated((int) (1000),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (mostCurrent._scv.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_ccard.getHeight());
 //BA.debugLineNum = 191;BA.debugLine="p.AddView(Ccard,10dip,10dip,100%x-20dip,Ccard.H";
_p.AddView((android.view.View)(_ccard.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_ccard.getHeight());
 //BA.debugLineNum = 192;BA.debugLine="p.Height=Ccard.Height+Ccard.Top+10dip";
_p.setHeight((int) (_ccard.getHeight()+_ccard.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 193;BA.debugLine="Dim rw As Panel = review(Ccard)";
_rw = new anywheresoftware.b4a.objects.PanelWrapper();
_rw = _review(_ccard);
 //BA.debugLineNum = 194;BA.debugLine="rw.SetLayoutAnimated(1000,10dip,10dip,scv.Width";
_rw.SetLayoutAnimated((int) (1000),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (mostCurrent._scv.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_rw.getHeight());
 //BA.debugLineNum = 195;BA.debugLine="p.AddView(rw,10dip,Ccard.Height+Ccard.Top+10dip";
_p.AddView((android.view.View)(_rw.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_ccard.getHeight()+_ccard.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_rw.getHeight());
 //BA.debugLineNum = 196;BA.debugLine="p.Height = rw.Height+rw.Top+10dip";
_p.setHeight((int) (_rw.getHeight()+_rw.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 197;BA.debugLine="If m.Get(\"text\")<>\"\" Then";
if ((_m.Get((Object)("text"))).equals((Object)("")) == false) { 
 //BA.debugLineNum = 198;BA.debugLine="Dim mnumber As Panel = marketnumber (m.Get(\"tex";
_mnumber = new anywheresoftware.b4a.objects.PanelWrapper();
_mnumber = _marketnumber(BA.ObjectToString(_m.Get((Object)("text"))));
 //BA.debugLineNum = 199;BA.debugLine="mnumber.SetLayoutAnimated(1500,10dip,10dip,scv.";
_mnumber.SetLayoutAnimated((int) (1500),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (mostCurrent._scv.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_mnumber.getHeight());
 //BA.debugLineNum = 200;BA.debugLine="p.AddView(mnumber,10dip,rw.Height+rw.Top+10dip,";
_p.AddView((android.view.View)(_mnumber.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_rw.getHeight()+_rw.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_mnumber.getHeight());
 //BA.debugLineNum = 201;BA.debugLine="p.Height  =mnumber.Height+mnumber.Top+10dip";
_p.setHeight((int) (_mnumber.getHeight()+_mnumber.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 };
 //BA.debugLineNum = 204;BA.debugLine="scv.Panel.AddView(p,0,(scv.Height-p.Height)/2,10";
mostCurrent._scv.getPanel().AddView((android.view.View)(_p.getObject()),(int) (0),(int) ((mostCurrent._scv.getHeight()-_p.getHeight())/(double)2),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),_p.getHeight());
 //BA.debugLineNum = 205;BA.debugLine="scv.Panel.Height = p.Height+p.Height+10dip";
mostCurrent._scv.getPanel().setHeight((int) (_p.getHeight()+_p.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 }else {
 //BA.debugLineNum = 208;BA.debugLine="ApiCall.getlottodata";
mostCurrent._apicall._getlottodata /*String*/ (mostCurrent.activityBA);
 };
 //BA.debugLineNum = 210;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 15;BA.debugLine="Dim lottieview As AXrLottieImageView";
mostCurrent._lottieview = new com.aghajari.axrlottie.AXrLottieImageView();
 //BA.debugLineNum = 17;BA.debugLine="Dim datelb As Label";
mostCurrent._datelb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 18;BA.debugLine="Dim numlb As Label";
mostCurrent._numlb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 19;BA.debugLine="Dim scv As ScrollView";
mostCurrent._scv = new anywheresoftware.b4a.objects.ScrollViewWrapper();
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return "";
}
public static String  _historybtn_click() throws Exception{
 //BA.debugLineNum = 81;BA.debugLine="Sub historybtn_click";
 //BA.debugLineNum = 82;BA.debugLine="StartActivity(LottoHistory)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._lottohistory.getObject()));
 //BA.debugLineNum = 83;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.LabelWrapper  _lbbb(anywheresoftware.b4a.keywords.constants.TypefaceWrapper _tp,int _color,String _text,int _size,boolean _iscs) throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _lb = null;
 //BA.debugLineNum = 276;BA.debugLine="Sub lbbb(tp As Typeface,color As Int,text As Strin";
 //BA.debugLineNum = 277;BA.debugLine="Dim lb As Label";
_lb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 278;BA.debugLine="lb.Initialize(\"\")";
_lb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 279;BA.debugLine="lb.Text= text";
_lb.setText(BA.ObjectToCharSequence(_text));
 //BA.debugLineNum = 280;BA.debugLine="lb.Width = 100%x-40dip";
_lb.setWidth((int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40))));
 //BA.debugLineNum = 281;BA.debugLine="If iscs =True Then";
if (_iscs==anywheresoftware.b4a.keywords.Common.True) { 
 }else {
 //BA.debugLineNum = 283;BA.debugLine="lb.Typeface = tp";
_lb.setTypeface((android.graphics.Typeface)(_tp.getObject()));
 //BA.debugLineNum = 284;BA.debugLine="lb.TextColor=color";
_lb.setTextColor(_color);
 //BA.debugLineNum = 285;BA.debugLine="lb.TextSize = mycode.textsize (size)";
_lb.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,_size));
 };
 //BA.debugLineNum = 287;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 288;BA.debugLine="lb.Height = su.MeasureMultilineTextHeight(lb,text";
_lb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_lb.getObject()),BA.ObjectToCharSequence(_text)));
 //BA.debugLineNum = 289;BA.debugLine="Return lb";
if (true) return _lb;
 //BA.debugLineNum = 290;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.PanelWrapper  _marketnumber(String _txt) throws Exception{
anywheresoftware.b4a.objects.collections.List _ls = null;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.LabelWrapper _lb = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
 //BA.debugLineNum = 213;BA.debugLine="Sub marketnumber (txt As String) As Panel";
 //BA.debugLineNum = 214;BA.debugLine="Dim ls As List = Regex.Split(CRLF,txt)";
_ls = new anywheresoftware.b4a.objects.collections.List();
_ls = anywheresoftware.b4a.keywords.Common.ArrayToList(anywheresoftware.b4a.keywords.Common.Regex.Split(anywheresoftware.b4a.keywords.Common.CRLF,_txt));
 //BA.debugLineNum = 215;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 216;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 217;BA.debugLine="Dim lb As Label";
_lb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 218;BA.debugLine="lb.Initialize(\"\")";
_lb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 219;BA.debugLine="Dim cs As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 220;BA.debugLine="cs.Initialize.Color(0xFFEFEFEF).Typeface(mycode.d";
_cs.Initialize().Color(((int)0xffefefef)).Typeface((android.graphics.Typeface)(mostCurrent._mycode._defaultfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (10)))).Append(BA.ObjectToCharSequence(BA.ObjectToString(_ls.Get((int) (0)))+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF)).Pop();
 //BA.debugLineNum = 221;BA.debugLine="cs.Color(Colors.White).Typeface(mycode.semibold).";
_cs.Color(anywheresoftware.b4a.keywords.Common.Colors.White).Typeface((android.graphics.Typeface)(mostCurrent._mycode._semibold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (10)))).Append(BA.ObjectToCharSequence(BA.ObjectToString(_ls.Get((int) (1)))+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+BA.ObjectToString(_ls.Get((int) (2))))).PopAll();
 //BA.debugLineNum = 222;BA.debugLine="lb.Text= cs";
_lb.setText(BA.ObjectToCharSequence(_cs.getObject()));
 //BA.debugLineNum = 223;BA.debugLine="lb.Gravity=Gravity.CENTER";
_lb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 224;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 225;BA.debugLine="cd.Initialize(mycode.naviColor,10dip)";
_cd.Initialize(mostCurrent._mycode._navicolor /*int*/ ,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 226;BA.debugLine="p.Background=cd";
_p.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 227;BA.debugLine="p.Width = 100%x-20dip";
_p.setWidth((int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))));
 //BA.debugLineNum = 228;BA.debugLine="p.AddView(lb,10dip,10dip,p.Width-20dip,10dip)";
_p.AddView((android.view.View)(_lb.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_p.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 229;BA.debugLine="lb.Height = su.MeasureMultilineTextHeight(lb,cs)";
_lb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_lb.getObject()),BA.ObjectToCharSequence(_cs.getObject())));
 //BA.debugLineNum = 230;BA.debugLine="p.Height = lb.Height+lb.Top+10dip";
_p.setHeight((int) (_lb.getHeight()+_lb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 231;BA.debugLine="Return p";
if (true) return _p;
 //BA.debugLineNum = 232;BA.debugLine="End Sub";
return null;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 10;BA.debugLine="Dim lottogetter As String = \"lottogetter\"";
_lottogetter = "lottogetter";
 //BA.debugLineNum = 11;BA.debugLine="Dim isCall As Boolean";
_iscall = false;
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.PanelWrapper  _review(anywheresoftware.b4a.objects.PanelWrapper _tag) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
anywheresoftware.b4a.objects.ButtonWrapper _btn = null;
anywheresoftware.b4a.objects.LabelWrapper _tlb = null;
anywheresoftware.b4a.objects.LabelWrapper _blb = null;
int _top = 0;
int _w = 0;
anywheresoftware.b4a.objects.ButtonWrapper _btn1 = null;
 //BA.debugLineNum = 234;BA.debugLine="Sub review(tag As Panel) As Panel";
 //BA.debugLineNum = 235;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 236;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 237;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 238;BA.debugLine="cd.Initialize(mycode.naviColor,10dip)";
_cd.Initialize(mostCurrent._mycode._navicolor /*int*/ ,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 239;BA.debugLine="p.Background=cd";
_p.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 240;BA.debugLine="Dim btn As Button";
_btn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 241;BA.debugLine="Dim tlb As Label";
_tlb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 242;BA.debugLine="Dim blb As Label";
_blb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 243;BA.debugLine="btn.Initialize(\"review\")";
_btn.Initialize(mostCurrent.activityBA,"review");
 //BA.debugLineNum = 244;BA.debugLine="p.Width = 100%x-20dip";
_p.setWidth((int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))));
 //BA.debugLineNum = 245;BA.debugLine="tlb = lbbb(Typeface.CreateNew(mycode.mmfont,Typef";
_tlb = _lbbb((anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.CreateNew((android.graphics.Typeface)(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()),anywheresoftware.b4a.keywords.Common.Typeface.STYLE_BOLD))),anywheresoftware.b4a.keywords.Common.Colors.White,"လူကြီးမင်းခင်ဗျာ",(int) (10),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 246;BA.debugLine="p.AddView(tlb,10dip,10dip,p.Width-20dip,tlb.Heigh";
_p.AddView((android.view.View)(_tlb.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_p.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_tlb.getHeight());
 //BA.debugLineNum = 247;BA.debugLine="Dim top As Int =tlb.Height +tlb.Top+10dip";
_top = (int) (_tlb.getHeight()+_tlb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 248;BA.debugLine="blb = lbbb(mycode.mmfont,Colors.White,\"သုံးရတာကြိ";
_blb = _lbbb(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ ,anywheresoftware.b4a.keywords.Common.Colors.White,"သုံးရတာကြိုက်ပါက 5⭐ပေးနိုင်ပါသည်",(int) (9),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 249;BA.debugLine="blb.Gravity=Gravity.CENTER";
_blb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 250;BA.debugLine="p.AddView(blb,10dip,top,p.Width-20dip,blb.Height)";
_p.AddView((android.view.View)(_blb.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_top,(int) (_p.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),_blb.getHeight());
 //BA.debugLineNum = 251;BA.debugLine="top = blb.Height +blb.Top+10dip";
_top = (int) (_blb.getHeight()+_blb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 252;BA.debugLine="btn.Background=mycode.btnbgdynamic(Colors.White,C";
_btn.setBackground((android.graphics.drawable.Drawable)(mostCurrent._mycode._btnbgdynamic /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Gray,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))).getObject()));
 //BA.debugLineNum = 253;BA.debugLine="btn.Typeface=mycode.mmfont";
_btn.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 254;BA.debugLine="btn.Text=\"★ ပေးမယ်\"";
_btn.setText(BA.ObjectToCharSequence("★ ပေးမယ်"));
 //BA.debugLineNum = 255;BA.debugLine="btn.TextColor=mycode.bgColor";
_btn.setTextColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 256;BA.debugLine="btn.Textsize = mycode.textsize(8.5)";
_btn.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (8.5)));
 //BA.debugLineNum = 257;BA.debugLine="Dim w As Int = (p.Width-30dip)/2";
_w = (int) ((_p.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)))/(double)2);
 //BA.debugLineNum = 259;BA.debugLine="Dim btn1 As Button";
_btn1 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 260;BA.debugLine="btn1.Initialize(\"savebtn\")";
_btn1.Initialize(mostCurrent.activityBA,"savebtn");
 //BA.debugLineNum = 261;BA.debugLine="btn1.Background=mycode.btnbgdynamic(Colors.White,";
_btn1.setBackground((android.graphics.drawable.Drawable)(mostCurrent._mycode._btnbgdynamic /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,anywheresoftware.b4a.keywords.Common.Colors.White,anywheresoftware.b4a.keywords.Common.Colors.Gray,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))).getObject()));
 //BA.debugLineNum = 262;BA.debugLine="btn1.TextColor=mycode.bgColor";
_btn1.setTextColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 263;BA.debugLine="btn1.Typeface=mycode.mmfont";
_btn1.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 264;BA.debugLine="btn1.TextSize=mycode.textsize(8.5)";
_btn1.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (8.5)));
 //BA.debugLineNum = 265;BA.debugLine="btn1.Text=\"💾 ပုံသိမ်းမယ်\"";
_btn1.setText(BA.ObjectToCharSequence("💾 ပုံသိမ်းမယ်"));
 //BA.debugLineNum = 267;BA.debugLine="btn1.Tag=tag";
_btn1.setTag((Object)(_tag.getObject()));
 //BA.debugLineNum = 269;BA.debugLine="p.AddView(btn1,10dip,top,w,45dip)";
_p.AddView((android.view.View)(_btn1.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_top,_w,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (45)));
 //BA.debugLineNum = 270;BA.debugLine="p.AddView(btn,btn1.Width+btn1.Left+10dip,top,w,45";
_p.AddView((android.view.View)(_btn.getObject()),(int) (_btn1.getWidth()+_btn1.getLeft()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),_top,_w,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (45)));
 //BA.debugLineNum = 272;BA.debugLine="p.Height = btn.Height+btn.Top+10dip";
_p.setHeight((int) (_btn.getHeight()+_btn.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 273;BA.debugLine="Return p";
if (true) return _p;
 //BA.debugLineNum = 274;BA.debugLine="End Sub";
return null;
}
public static String  _review_click() throws Exception{
anywheresoftware.b4a.phone.Phone.PhoneIntents _pi = null;
 //BA.debugLineNum = 292;BA.debugLine="Sub review_click";
 //BA.debugLineNum = 293;BA.debugLine="Dim pi As PhoneIntents";
_pi = new anywheresoftware.b4a.phone.Phone.PhoneIntents();
 //BA.debugLineNum = 294;BA.debugLine="StartActivity(pi.OpenBrowser(\"https://play.google";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(_pi.OpenBrowser("https://play.google.com/store/apps/details?id=com.burma.royal2d")));
 //BA.debugLineNum = 295;BA.debugLine="End Sub";
return "";
}
public static String  _savebtn_click() throws Exception{
anywheresoftware.b4a.objects.ButtonWrapper _b = null;
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.B4XViewWrapper _x = null;
anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper _bb = null;
 //BA.debugLineNum = 297;BA.debugLine="Sub savebtn_click";
 //BA.debugLineNum = 298;BA.debugLine="Dim b As Button =Sender";
_b = new anywheresoftware.b4a.objects.ButtonWrapper();
_b = (anywheresoftware.b4a.objects.ButtonWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ButtonWrapper(), (android.widget.Button)(anywheresoftware.b4a.keywords.Common.Sender(mostCurrent.activityBA)));
 //BA.debugLineNum = 299;BA.debugLine="Dim p As Panel = b.Tag";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
_p = (anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(_b.getTag()));
 //BA.debugLineNum = 300;BA.debugLine="Dim x As B4XView = p";
_x = new anywheresoftware.b4a.objects.B4XViewWrapper();
_x = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_p.getObject()));
 //BA.debugLineNum = 301;BA.debugLine="Dim bb As B4XBitmap = x.Snapshot";
_bb = new anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper();
_bb = _x.Snapshot();
 //BA.debugLineNum = 302;BA.debugLine="mycode.AddBitmapToGallery1(bb,DateTime.Now&\".png\"";
mostCurrent._mycode._addbitmaptogallery1 /*String*/ (mostCurrent.activityBA,_bb,BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.getNow())+".png","image/png");
 //BA.debugLineNum = 303;BA.debugLine="End Sub";
return "";
}
}
