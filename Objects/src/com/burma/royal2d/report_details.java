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

public class report_details extends Activity implements B4AActivity{
	public static report_details mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "com.burma.royal2d", "com.burma.royal2d.report_details");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (report_details).");
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
		activityBA = new BA(this, layout, processBA, "com.burma.royal2d", "com.burma.royal2d.report_details");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.burma.royal2d.report_details", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (report_details) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (report_details) Resume **");
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
		return report_details.class;
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
            BA.LogInfo("** Activity (report_details) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (report_details) Pause event (activity is not paused). **");
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
            report_details mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (report_details) Resume **");
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
public static anywheresoftware.b4a.objects.collections.Map _mdata = null;
public static int _messageheight = 0;
public static anywheresoftware.b4a.objects.StringUtils _su = null;
public static int _reportpnheight = 0;
public static String _reportjob = "";
public com.burma.royal2d.main _main = null;
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

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _smspn = null;
 //BA.debugLineNum = 18;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 19;BA.debugLine="Activity.Color=mycode.bgColor";
mostCurrent._activity.setColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 20;BA.debugLine="mycode.SETnavigationcolor";
mostCurrent._mycode._setnavigationcolor /*String*/ (mostCurrent.activityBA);
 //BA.debugLineNum = 21;BA.debugLine="Activity.AddView(mycode.appbar(\"Report\",False),0,0";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._mycode._appbar /*anywheresoftware.b4a.objects.PanelWrapper*/ (mostCurrent.activityBA,"Report",anywheresoftware.b4a.keywords.Common.False).getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),mostCurrent._mycode._appbarheight /*int*/ );
 //BA.debugLineNum = 22;BA.debugLine="Dim smspn As Panel = OtherMessage(Mdata.Get(\"profi";
_smspn = new anywheresoftware.b4a.objects.PanelWrapper();
_smspn = _othermessage(BA.ObjectToString(_mdata.Get((Object)("profile_pic"))),BA.ObjectToString(_mdata.Get((Object)("message"))),BA.ObjectToString(_mdata.Get((Object)("name"))),BA.ObjectToString(_mdata.Get((Object)("id"))),(int) (0));
 //BA.debugLineNum = 23;BA.debugLine="Activity.AddView(smspn,0,mycode.appbarheight+ 30di";
mostCurrent._activity.AddView((android.view.View)(_smspn.getObject()),(int) (0),(int) (mostCurrent._mycode._appbarheight /*int*/ +anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30))),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),_messageheight);
 //BA.debugLineNum = 24;BA.debugLine="Activity.AddView(reportItem,0,smspn.Height+smspn.T";
mostCurrent._activity.AddView((android.view.View)(_reportitem().getObject()),(int) (0),(int) (_smspn.getHeight()+_smspn.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),_reportpnheight);
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 31;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 33;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 27;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public static String  _backbtn_click() throws Exception{
 //BA.debugLineNum = 149;BA.debugLine="Sub backbtn_click";
 //BA.debugLineNum = 150;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 151;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.PanelWrapper  _othermessage(String _profile,String _sms,String _name,String _id,int _top) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
ir.aghajari.retrofitglide.Amir_Glide _glide = null;
anywheresoftware.b4a.objects.ImageViewWrapper _img = null;
anywheresoftware.b4a.objects.LabelWrapper _lb = null;
anywheresoftware.b4a.objects.ButtonWrapper _smsitem = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 37;BA.debugLine="Sub OtherMessage(profile As String,sms As String,n";
 //BA.debugLineNum = 38;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 39;BA.debugLine="p.Initialize(\"p\")";
_p.Initialize(mostCurrent.activityBA,"p");
 //BA.debugLineNum = 40;BA.debugLine="Dim glide As Amir_Glide";
_glide = new ir.aghajari.retrofitglide.Amir_Glide();
 //BA.debugLineNum = 41;BA.debugLine="Dim img As ImageView";
_img = new anywheresoftware.b4a.objects.ImageViewWrapper();
 //BA.debugLineNum = 42;BA.debugLine="glide.Initializer";
_glide.Initializer(processBA);
 //BA.debugLineNum = 43;BA.debugLine="img.Initialize(\"\")";
_img.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 44;BA.debugLine="Dim lb As Label";
_lb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 45;BA.debugLine="lb.Initialize(\"\")";
_lb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 46;BA.debugLine="glide.Load(profile).Apply(glide.RO.CircleCrop).In";
_glide.Load((Object)(_profile)).Apply(_glide.getRO().CircleCrop()).Into((android.widget.ImageView)(_img.getObject()));
 //BA.debugLineNum = 47;BA.debugLine="lb.Text = OtherMessageCs(name,sms)";
_lb.setText(BA.ObjectToCharSequence(_othermessagecs(_name,_sms).getObject()));
 //BA.debugLineNum = 48;BA.debugLine="p.AddView(img,5dip,0,40dip,40dip)";
_p.AddView((android.view.View)(_img.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 49;BA.debugLine="p.AddView(lb,img.Width+img.Left+5dip,0,100%x-(lb.";
_p.AddView((android.view.View)(_lb.getObject()),(int) (_img.getWidth()+_img.getLeft()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))),(int) (0),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-(_lb.getLeft()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5)))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 50;BA.debugLine="lb.Height = su.MeasureMultilineTextHeight(lb,Othe";
_lb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_lb.getObject()),BA.ObjectToCharSequence(_othermessagecs(_name,_sms).getObject())));
 //BA.debugLineNum = 51;BA.debugLine="If lb.Height>img.Height Then";
if (_lb.getHeight()>_img.getHeight()) { 
 //BA.debugLineNum = 52;BA.debugLine="messageHeight = lb.Height";
_messageheight = _lb.getHeight();
 }else {
 //BA.debugLineNum = 54;BA.debugLine="messageHeight = img.Height";
_messageheight = _img.getHeight();
 };
 //BA.debugLineNum = 56;BA.debugLine="Dim smsitem As Button";
_smsitem = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 57;BA.debugLine="smsitem.Initialize(\"smsitem\")";
_smsitem.Initialize(mostCurrent.activityBA,"smsitem");
 //BA.debugLineNum = 58;BA.debugLine="smsitem.Color=Colors.Transparent";
_smsitem.setColor(anywheresoftware.b4a.keywords.Common.Colors.Transparent);
 //BA.debugLineNum = 59;BA.debugLine="p.AddView(smsitem,0,0,100%x,messageHeight)";
_p.AddView((android.view.View)(_smsitem.getObject()),(int) (0),(int) (0),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA),_messageheight);
 //BA.debugLineNum = 60;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 61;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 62;BA.debugLine="m.Put(\"id\",id)";
_m.Put((Object)("id"),(Object)(_id));
 //BA.debugLineNum = 63;BA.debugLine="m.Put(\"top\",top)";
_m.Put((Object)("top"),(Object)(_top));
 //BA.debugLineNum = 64;BA.debugLine="m.Put(\"message\",sms)";
_m.Put((Object)("message"),(Object)(_sms));
 //BA.debugLineNum = 65;BA.debugLine="m.Put(\"name\",name)";
_m.Put((Object)("name"),(Object)(_name));
 //BA.debugLineNum = 66;BA.debugLine="m.Put(\"profile_pic\",profile)";
_m.Put((Object)("profile_pic"),(Object)(_profile));
 //BA.debugLineNum = 67;BA.debugLine="smsitem.Tag  = m";
_smsitem.setTag((Object)(_m.getObject()));
 //BA.debugLineNum = 68;BA.debugLine="Return p";
if (true) return _p;
 //BA.debugLineNum = 69;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.CSBuilder  _othermessagecs(String _name,String _sms) throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 72;BA.debugLine="Sub OtherMessageCs(name as String,sms As String) A";
 //BA.debugLineNum = 73;BA.debugLine="Dim cs As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 74;BA.debugLine="cs.Initialize.Color(Colors.Yellow).Typeface(mycod";
_cs.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.Yellow).Typeface((android.graphics.Typeface)(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (7)))).Append(BA.ObjectToCharSequence(_name+anywheresoftware.b4a.keywords.Common.CRLF)).Pop();
 //BA.debugLineNum = 75;BA.debugLine="cs.Color(Colors.White).Typeface(mycode.mmfont).Si";
_cs.Color(anywheresoftware.b4a.keywords.Common.Colors.White).Typeface((android.graphics.Typeface)(mostCurrent._mycode._mmfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject())).Size((int) (mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (7)))).Append(BA.ObjectToCharSequence(_sms)).PopAll();
 //BA.debugLineNum = 76;BA.debugLine="Return cs";
if (true) return _cs;
 //BA.debugLineNum = 77;BA.debugLine="End Sub";
return null;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Dim Mdata As Map";
_mdata = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 8;BA.debugLine="Dim messageHeight As Int";
_messageheight = 0;
 //BA.debugLineNum = 9;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 10;BA.debugLine="Dim reportpnHeight As Int";
_reportpnheight = 0;
 //BA.debugLineNum = 11;BA.debugLine="Dim reportJob As String = \"reportJob\"";
_reportjob = "reportJob";
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper  _radiotype(String _txt,int _i) throws Exception{
anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper _rd = null;
 //BA.debugLineNum = 136;BA.debugLine="Sub Radiotype(txt As String,i As Int) As RadioButt";
 //BA.debugLineNum = 137;BA.debugLine="Dim rd As RadioButton";
_rd = new anywheresoftware.b4a.objects.CompoundButtonWrapper.RadioButtonWrapper();
 //BA.debugLineNum = 138;BA.debugLine="rd.Initialize(\"\")";
_rd.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 139;BA.debugLine="rd.Text  = txt";
_rd.setText(BA.ObjectToCharSequence(_txt));
 //BA.debugLineNum = 140;BA.debugLine="rd.TextColor= Colors.White";
_rd.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 141;BA.debugLine="rd.TextSize = mycode.textsize(7)";
_rd.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (7)));
 //BA.debugLineNum = 142;BA.debugLine="rd.Typeface =mycode.defaultfont";
_rd.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._defaultfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 143;BA.debugLine="If i = 0 Then";
if (_i==0) { 
 //BA.debugLineNum = 144;BA.debugLine="rd.Checked=True";
_rd.setChecked(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 146;BA.debugLine="Return rd";
if (true) return _rd;
 //BA.debugLineNum = 147;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.PanelWrapper  _reportitem() throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.PanelWrapper _cv = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _cd = null;
anywheresoftware.b4a.objects.PanelWrapper _pbase = null;
anywheresoftware.b4a.objects.LabelWrapper _lb = null;
anywheresoftware.b4a.objects.LabelWrapper _lbl2 = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
int _top = 0;
int _i = 0;
anywheresoftware.b4a.objects.ButtonWrapper _btnreport = null;
int _l = 0;
 //BA.debugLineNum = 80;BA.debugLine="Sub reportItem As Panel";
 //BA.debugLineNum = 81;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 82;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 83;BA.debugLine="Dim cv As Panel";
_cv = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 84;BA.debugLine="cv.Initialize(\"\")";
_cv.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 85;BA.debugLine="cv.Color=mycode.bgColor";
_cv.setColor(mostCurrent._mycode._bgcolor /*int*/ );
 //BA.debugLineNum = 86;BA.debugLine="cv.Elevation= 5dip";
_cv.setElevation((float) (anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (5))));
 //BA.debugLineNum = 87;BA.debugLine="Dim cd As ColorDrawable";
_cd = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 88;BA.debugLine="cd.Initialize(0xFF492D6F,10dip)";
_cd.Initialize(((int)0xff492d6f),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 89;BA.debugLine="cv.Background=cd";
_cv.setBackground((android.graphics.drawable.Drawable)(_cd.getObject()));
 //BA.debugLineNum = 90;BA.debugLine="Dim pbase As Panel";
_pbase = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 91;BA.debugLine="pbase.Initialize(\"\")";
_pbase.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 92;BA.debugLine="p.AddView(cv,10dip,10dip,100%x-20dip,100dip)";
_p.AddView((android.view.View)(_cv.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (100)));
 //BA.debugLineNum = 93;BA.debugLine="cv.AddView(pbase,0,0,cv.Width,cv.Height)";
_cv.AddView((android.view.View)(_pbase.getObject()),(int) (0),(int) (0),_cv.getWidth(),_cv.getHeight());
 //BA.debugLineNum = 95;BA.debugLine="Dim lb As Label";
_lb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 96;BA.debugLine="lb.Initialize(\"\")";
_lb.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 97;BA.debugLine="lb.Text = \"Reporting\"";
_lb.setText(BA.ObjectToCharSequence("Reporting"));
 //BA.debugLineNum = 98;BA.debugLine="lb.TextSize = mycode.textsize(8)";
_lb.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (8)));
 //BA.debugLineNum = 99;BA.debugLine="lb.TextColor=Colors.White";
_lb.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 100;BA.debugLine="lb.Typeface = mycode.semibold";
_lb.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._semibold /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 102;BA.debugLine="Dim lbl2 As Label";
_lbl2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 103;BA.debugLine="lbl2.Initialize(\"\")";
_lbl2.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 104;BA.debugLine="lbl2.Typeface = mycode.defaultfont";
_lbl2.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._defaultfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 105;BA.debugLine="lbl2.TextSize  = mycode.textsize(8)";
_lbl2.setTextSize(mostCurrent._mycode._textsize /*float*/ (mostCurrent.activityBA,(int) (8)));
 //BA.debugLineNum = 106;BA.debugLine="lbl2.TextColor=Colors.LightGray";
_lbl2.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.LightGray);
 //BA.debugLineNum = 107;BA.debugLine="lbl2.Text = \"Do you want to report \"&Mdata.Get(\"n";
_lbl2.setText(BA.ObjectToCharSequence("Do you want to report "+BA.ObjectToString(_mdata.Get((Object)("name")))));
 //BA.debugLineNum = 109;BA.debugLine="pbase.AddView(lb,10dip,10dip,pbase.Width-20dip,30";
_pbase.AddView((android.view.View)(_lb.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_pbase.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 110;BA.debugLine="lb.Height = su.MeasureMultilineTextHeight(lb,lb.T";
_lb.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_lb.getObject()),BA.ObjectToCharSequence(_lb.getText())));
 //BA.debugLineNum = 112;BA.debugLine="pbase.AddView(lbl2,10dip,lb.Height+lb.Top+10dip,p";
_pbase.AddView((android.view.View)(_lbl2.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (_lb.getHeight()+_lb.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))),(int) (_pbase.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 113;BA.debugLine="lbl2.Height = su.MeasureMultilineTextHeight(lbl2,";
_lbl2.setHeight(_su.MeasureMultilineTextHeight((android.widget.TextView)(_lbl2.getObject()),BA.ObjectToCharSequence(_lbl2.getText())));
 //BA.debugLineNum = 114;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 115;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 116;BA.debugLine="ls.AddAll(Array As String(\"Behavior for potential";
_ls.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"Behavior for potential violation","Content for potential violation"}));
 //BA.debugLineNum = 117;BA.debugLine="Dim top As Int = lbl2.Height +lbl2.Top+10dip";
_top = (int) (_lbl2.getHeight()+_lbl2.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 118;BA.debugLine="For i = 0 To ls.Size -1";
{
final int step34 = 1;
final int limit34 = (int) (_ls.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit34 ;_i = _i + step34 ) {
 //BA.debugLineNum = 119;BA.debugLine="pbase.AddView(Radiotype(ls.Get(i),i),10dip,top,p";
_pbase.AddView((android.view.View)(_radiotype(BA.ObjectToString(_ls.Get(_i)),_i).getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),_top,(int) (_pbase.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (30)));
 //BA.debugLineNum = 120;BA.debugLine="top = top + 40dip";
_top = (int) (_top+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 }
};
 //BA.debugLineNum = 122;BA.debugLine="Dim btnreport As Button";
_btnreport = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 123;BA.debugLine="btnreport.Initialize(\"summitbtn\")";
_btnreport.Initialize(mostCurrent.activityBA,"summitbtn");
 //BA.debugLineNum = 124;BA.debugLine="btnreport.Text = \"Report\"";
_btnreport.setText(BA.ObjectToCharSequence("Report"));
 //BA.debugLineNum = 125;BA.debugLine="btnreport.Typeface = mycode.defaultfont";
_btnreport.setTypeface((android.graphics.Typeface)(mostCurrent._mycode._defaultfont /*anywheresoftware.b4a.keywords.constants.TypefaceWrapper*/ .getObject()));
 //BA.debugLineNum = 126;BA.debugLine="btnreport.TextColor=Colors.White";
_btnreport.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 127;BA.debugLine="btnreport.Background = mycode.btnbgdynamic(mycode";
_btnreport.setBackground((android.graphics.drawable.Drawable)(mostCurrent._mycode._btnbgdynamic /*anywheresoftware.b4a.objects.drawable.StateListDrawable*/ (mostCurrent.activityBA,mostCurrent._mycode._navicolor /*int*/ ,mostCurrent._mycode._bgcolor /*int*/ ,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))).getObject()));
 //BA.debugLineNum = 128;BA.debugLine="Dim l As Int = (pbase.Width-150dip)/2";
_l = (int) ((_pbase.getWidth()-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)))/(double)2);
 //BA.debugLineNum = 129;BA.debugLine="pbase.AddView(btnreport,l,top,150dip,40dip)";
_pbase.AddView((android.view.View)(_btnreport.getObject()),_l,_top,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (40)));
 //BA.debugLineNum = 130;BA.debugLine="pbase.Height = btnreport.Height+btnreport.Top+10d";
_pbase.setHeight((int) (_btnreport.getHeight()+_btnreport.getTop()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10))));
 //BA.debugLineNum = 131;BA.debugLine="cv.Height= pbase.Height";
_cv.setHeight(_pbase.getHeight());
 //BA.debugLineNum = 132;BA.debugLine="reportpnHeight = cv.Height+20dip";
_reportpnheight = (int) (_cv.getHeight()+anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20)));
 //BA.debugLineNum = 133;BA.debugLine="Return p";
if (true) return _p;
 //BA.debugLineNum = 134;BA.debugLine="End Sub";
return null;
}
public static String  _setreport() throws Exception{
com.burma.royal2d.httpjob _j = null;
 //BA.debugLineNum = 153;BA.debugLine="Sub setreport";
 //BA.debugLineNum = 154;BA.debugLine="Dim j As HttpJob";
_j = new com.burma.royal2d.httpjob();
 //BA.debugLineNum = 155;BA.debugLine="j.Initialize(reportJob,Starter)";
_j._initialize /*String*/ (processBA,_reportjob,(Object)(mostCurrent._starter.getObject()));
 //BA.debugLineNum = 156;BA.debugLine="j.Download(Main.site&\"public_chat/report.php?id=\"";
_j._download /*String*/ (mostCurrent._main._site /*String*/ +"public_chat/report.php?id="+BA.ObjectToString(_mdata.Get((Object)("id")))+"&report_id="+mostCurrent._mycode._getuserid /*String*/ (mostCurrent.activityBA,mostCurrent._mycode._id /*String*/ ));
 //BA.debugLineNum = 157;BA.debugLine="End Sub";
return "";
}
public static String  _summitbtn_click() throws Exception{
 //BA.debugLineNum = 159;BA.debugLine="Sub summitbtn_click";
 //BA.debugLineNum = 160;BA.debugLine="setreport";
_setreport();
 //BA.debugLineNum = 161;BA.debugLine="End Sub";
return "";
}
}
