package com.burma.royal2d;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class b4xpagesdelegator extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "com.burma.royal2d.b4xpagesdelegator");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", com.burma.royal2d.b4xpagesdelegator.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
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
public com.burma.royal2d.gift_activity _gift_activity = null;
public com.burma.royal2d.b4xpages _b4xpages = null;
public com.burma.royal2d.b4xcollections _b4xcollections = null;
public com.burma.royal2d.httputils2service _httputils2service = null;
public String  _activity_actionbarhomeclick() throws Exception{
 //BA.debugLineNum = 35;BA.debugLine="Public Sub Activity_ActionBarHomeClick";
 //BA.debugLineNum = 36;BA.debugLine="B4XPages.GetManager.Activity_ActionBarHomeClick";
_b4xpages._getmanager /*com.burma.royal2d.b4xpagesmanager*/ (getActivityBA())._activity_actionbarhomeclick /*String*/ ();
 //BA.debugLineNum = 37;BA.debugLine="End Sub";
return "";
}
public boolean  _activity_keypress(int _keycode) throws Exception{
 //BA.debugLineNum = 27;BA.debugLine="Public Sub Activity_KeyPress (KeyCode As Int) As B";
 //BA.debugLineNum = 28;BA.debugLine="Return B4XPages.GetManager.Activity_KeyPress (Key";
if (true) return _b4xpages._getmanager /*com.burma.royal2d.b4xpagesmanager*/ (getActivityBA())._activity_keypress /*boolean*/ (_keycode);
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return false;
}
public String  _activity_pause() throws Exception{
 //BA.debugLineNum = 52;BA.debugLine="Public Sub Activity_Pause";
 //BA.debugLineNum = 53;BA.debugLine="B4XPages.GetManager.Activity_Pause";
_b4xpages._getmanager /*com.burma.royal2d.b4xpagesmanager*/ (getActivityBA())._activity_pause /*String*/ ();
 //BA.debugLineNum = 54;BA.debugLine="End Sub";
return "";
}
public String  _activity_permissionresult(String _permission,boolean _result) throws Exception{
 //BA.debugLineNum = 31;BA.debugLine="Sub Activity_PermissionResult (Permission As Strin";
 //BA.debugLineNum = 32;BA.debugLine="B4XPages.GetManager.RaiseEvent(B4XPages.GetManage";
_b4xpages._getmanager /*com.burma.royal2d.b4xpagesmanager*/ (getActivityBA())._raiseevent /*String*/ (_b4xpages._getmanager /*com.burma.royal2d.b4xpagesmanager*/ (getActivityBA())._gettoppage /*com.burma.royal2d.b4xpagesmanager._b4xpageinfo*/ (),"B4XPage_PermissionResult",new Object[]{(Object)(_permission),(Object)(_result)});
 //BA.debugLineNum = 33;BA.debugLine="End Sub";
return "";
}
public String  _activity_resume() throws Exception{
 //BA.debugLineNum = 48;BA.debugLine="Public Sub Activity_Resume";
 //BA.debugLineNum = 49;BA.debugLine="B4XPages.GetManager.Activity_Resume";
_b4xpages._getmanager /*com.burma.royal2d.b4xpagesmanager*/ (getActivityBA())._activity_resume /*String*/ ();
 //BA.debugLineNum = 50;BA.debugLine="End Sub";
return "";
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 2;BA.debugLine="End Sub";
return "";
}
public String  _create_menu(Object _menu) throws Exception{
 //BA.debugLineNum = 39;BA.debugLine="Public Sub Create_Menu (Menu As Object)";
 //BA.debugLineNum = 40;BA.debugLine="B4XPages.GetManager.CreateMenu(Menu)";
_b4xpages._getmanager /*com.burma.royal2d.b4xpagesmanager*/ (getActivityBA())._createmenu /*String*/ (_menu);
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 4;BA.debugLine="Public Sub Initialize";
 //BA.debugLineNum = 6;BA.debugLine="End Sub";
return "";
}
public String  _mainform_resize(double _width,double _height) throws Exception{
 //BA.debugLineNum = 44;BA.debugLine="Public Sub MainForm_Resize(Width As Double, Height";
 //BA.debugLineNum = 45;BA.debugLine="B4XPages.GetManager.MainForm_Resize(Width, Height";
_b4xpages._getmanager /*com.burma.royal2d.b4xpagesmanager*/ (getActivityBA())._mainform_resize /*String*/ (_width,_height);
 //BA.debugLineNum = 46;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
