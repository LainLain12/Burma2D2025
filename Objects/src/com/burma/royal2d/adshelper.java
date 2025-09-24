package com.burma.royal2d;

import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.appopen.*;
import com.google.android.gms.ads.appopen.AppOpenAd.*;
import com.google.android.gms.ads.*;
import com.google.android.gms.ads.rewardedinterstitial.*;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import androidx.annotation.NonNull;
import android.app.Activity;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class adshelper extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "com.burma.royal2d.adshelper");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", com.burma.royal2d.adshelper.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4j.object.JavaObject _appopenadcallback = null;
public anywheresoftware.b4j.object.JavaObject _appopenadfullscreencallback = null;
public anywheresoftware.b4j.object.JavaObject _appinterstitialradcallback = null;
public anywheresoftware.b4j.object.JavaObject _appinterstitialradfullscreencallback = null;
public anywheresoftware.b4j.object.JavaObject _appvideoradcallback = null;
public anywheresoftware.b4j.object.JavaObject _appvideoradfullscreencallback = null;
public anywheresoftware.b4j.object.JavaObject _ctxt = null;
public long _lastbackgroundtime = 0L;
public anywheresoftware.b4j.object.JavaObject _consentinformation = null;
public anywheresoftware.b4j.object.JavaObject _consentdebugsettings = null;
public anywheresoftware.b4j.object.JavaObject _nativeme = null;
public Object _callback_module_rewardedvideo = null;
public Object _callback_module_rewardedinterstitial = null;
public Object _callback_module_appopenad = null;
public String _event_rewardedvideo = "";
public String _event_rewardedinterstitial = "";
public String _event_appopenad = "";
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
public String  _background() throws Exception{
 //BA.debugLineNum = 55;BA.debugLine="Public Sub Background";
 //BA.debugLineNum = 56;BA.debugLine="LastBackgroundTime = DateTime.Now";
_lastbackgroundtime = __c.DateTime.getNow();
 //BA.debugLineNum = 57;BA.debugLine="End Sub";
return "";
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 8;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Private AppOpenAdCallback, AppOpenAdFullScreenCal";
_appopenadcallback = new anywheresoftware.b4j.object.JavaObject();
_appopenadfullscreencallback = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 10;BA.debugLine="Private AppInterstitialRAdCallback, AppInterstiti";
_appinterstitialradcallback = new anywheresoftware.b4j.object.JavaObject();
_appinterstitialradfullscreencallback = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 11;BA.debugLine="Private AppVideoRAdCallback, AppVideoRAdFullScree";
_appvideoradcallback = new anywheresoftware.b4j.object.JavaObject();
_appvideoradfullscreencallback = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 13;BA.debugLine="Private ctxt As JavaObject";
_ctxt = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 14;BA.debugLine="Private LastBackgroundTime As Long";
_lastbackgroundtime = 0L;
 //BA.debugLineNum = 15;BA.debugLine="Private ConsentInformation As JavaObject";
_consentinformation = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 16;BA.debugLine="Private ConsentDebugSettings As JavaObject";
_consentdebugsettings = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 18;BA.debugLine="Private nativeMe As JavaObject";
_nativeme = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 19;BA.debugLine="Private Callback_Module_RewardedVideo, Callback_M";
_callback_module_rewardedvideo = new Object();
_callback_module_rewardedinterstitial = new Object();
_callback_module_appopenad = new Object();
 //BA.debugLineNum = 20;BA.debugLine="Private Event_RewardedVideo, Event_RewardedInters";
_event_rewardedvideo = "";
_event_rewardedinterstitial = "";
_event_appopenad = "";
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
public String  _event_dismissed_rewarded(boolean _rewarded,Object _item,String _adtype) throws Exception{
 //BA.debugLineNum = 333;BA.debugLine="Sub Event_Dismissed_Rewarded(Rewarded As Boolean,";
 //BA.debugLineNum = 334;BA.debugLine="LogColor($\"Event_Dismissed(Rewarded=${Rewarded},";
__c.LogImpl("234406401",("Event_Dismissed(Rewarded="+__c.SmartStringFormatter("",(Object)(_rewarded))+", AdType="+__c.SmartStringFormatter("",(Object)(_adtype))+")"),__c.Colors.Green);
 //BA.debugLineNum = 335;BA.debugLine="If Rewarded Then";
if (_rewarded) { 
 //BA.debugLineNum = 336;BA.debugLine="Event_Rewarded(Item, AdType)";
_event_rewarded(_item,_adtype);
 };
 //BA.debugLineNum = 338;BA.debugLine="End Sub";
return "";
}
public String  _event_failedtoreceivead(String _errorcode,String _adtype) throws Exception{
 //BA.debugLineNum = 296;BA.debugLine="Sub Event_FailedToReceiveAd (ErrorCode As String,";
 //BA.debugLineNum = 298;BA.debugLine="Select AdType";
switch (BA.switchObjectToInt(_adtype,"AppOpenAd","RewardedVideo","RewardedInterstitial")) {
case 0: {
 //BA.debugLineNum = 300;BA.debugLine="CallSubDelayed2(Callback_Module_AppOpenAd, Even";
__c.CallSubDelayed2(ba,_callback_module_appopenad,_event_appopenad+"_FailedToReceiveAd",(Object)(_errorcode));
 break; }
case 1: {
 //BA.debugLineNum = 302;BA.debugLine="CallSubDelayed2(Callback_Module_RewardedVideo,";
__c.CallSubDelayed2(ba,_callback_module_rewardedvideo,_event_rewardedvideo+"_FailedToReceiveAd",(Object)(_errorcode));
 break; }
case 2: {
 //BA.debugLineNum = 304;BA.debugLine="CallSubDelayed2(Callback_Module_RewardedInterst";
__c.CallSubDelayed2(ba,_callback_module_rewardedinterstitial,_event_rewardedinterstitial+"_FailedToReceiveAd",(Object)(_errorcode));
 break; }
}
;
 //BA.debugLineNum = 306;BA.debugLine="End Sub";
return "";
}
public String  _event_receivead(String _adtype) throws Exception{
 //BA.debugLineNum = 309;BA.debugLine="Sub Event_ReceiveAd(AdType As String)";
 //BA.debugLineNum = 311;BA.debugLine="Select AdType";
switch (BA.switchObjectToInt(_adtype,"AppOpenAd","RewardedVideo","RewardedInterstitial")) {
case 0: {
 //BA.debugLineNum = 313;BA.debugLine="CallSubDelayed(Callback_Module_AppOpenAd, Event";
__c.CallSubDelayed(ba,_callback_module_appopenad,_event_appopenad+"_ReceiveAd");
 break; }
case 1: {
 //BA.debugLineNum = 315;BA.debugLine="CallSubDelayed(Callback_Module_RewardedVideo, E";
__c.CallSubDelayed(ba,_callback_module_rewardedvideo,_event_rewardedvideo+"_ReceiveAd");
 break; }
case 2: {
 //BA.debugLineNum = 317;BA.debugLine="CallSubDelayed(Callback_Module_RewardedIntersti";
__c.CallSubDelayed(ba,_callback_module_rewardedinterstitial,_event_rewardedinterstitial+"_ReceiveAd");
 break; }
}
;
 //BA.debugLineNum = 319;BA.debugLine="End Sub";
return "";
}
public String  _event_rewarded(Object _item,String _adtype) throws Exception{
 //BA.debugLineNum = 322;BA.debugLine="Sub Event_Rewarded(Item As Object, AdType As Strin";
 //BA.debugLineNum = 324;BA.debugLine="Select AdType";
switch (BA.switchObjectToInt(_adtype,"RewardedVideo","RewardedInterstitial")) {
case 0: {
 //BA.debugLineNum = 326;BA.debugLine="CallSubDelayed2(Callback_Module_RewardedVideo,";
__c.CallSubDelayed2(ba,_callback_module_rewardedvideo,_event_rewardedvideo+"_Rewarded",_item);
 break; }
case 1: {
 //BA.debugLineNum = 328;BA.debugLine="CallSubDelayed2(Callback_Module_RewardedInterst";
__c.CallSubDelayed2(ba,_callback_module_rewardedinterstitial,_event_rewardedinterstitial+"_Rewarded",_item);
 break; }
}
;
 //BA.debugLineNum = 330;BA.debugLine="End Sub";
return "";
}
public void  _fetchopenad(String _appopenadunit) throws Exception{
ResumableSub_FetchOpenAd rsub = new ResumableSub_FetchOpenAd(this,_appopenadunit);
rsub.resume(ba, null);
}
public static class ResumableSub_FetchOpenAd extends BA.ResumableSub {
public ResumableSub_FetchOpenAd(com.burma.royal2d.adshelper parent,String _appopenadunit) {
this.parent = parent;
this._appopenadunit = _appopenadunit;
}
com.burma.royal2d.adshelper parent;
String _appopenadunit;
anywheresoftware.b4j.object.JavaObject _appopenad = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 153;BA.debugLine="Dim AppOpenAd As JavaObject";
_appopenad = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 154;BA.debugLine="AppOpenAd.InitializeStatic(\"com.google.android.gm";
_appopenad.InitializeStatic("com.google.android.gms.ads.appopen.AppOpenAd");
 //BA.debugLineNum = 155;BA.debugLine="AppOpenAdCallback.InitializeNewInstance(Applicati";
parent._appopenadcallback.InitializeNewInstance(parent.__c.Application.getPackageName()+".adshelper$MyAddOpenAdCallback",(Object[])(parent.__c.Null));
 //BA.debugLineNum = 156;BA.debugLine="AppOpenAdFullScreenCallback.InitializeNewInstance";
parent._appopenadfullscreencallback.InitializeNewInstance(parent.__c.Application.getPackageName()+".adshelper$MyFullScreenContentCallback",(Object[])(parent.__c.Null));
 //BA.debugLineNum = 157;BA.debugLine="Do While True";
if (true) break;

case 1:
//do while
this.state = 8;
while (parent.__c.True) {
this.state = 3;
if (true) break;
}
if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 158;BA.debugLine="If AppOpenAdCallback.GetField(\"ad\") = Null Then";
if (true) break;

case 4:
//if
this.state = 7;
if (parent._appopenadcallback.GetField("ad")== null) { 
this.state = 6;
}if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 161;BA.debugLine="AppOpenAd.RunMethod(\"load\", Array(ctxt, AppOpen";
_appopenad.RunMethod("load",new Object[]{(Object)(parent._ctxt.getObject()),(Object)(_appopenadunit),parent._getadrequest(),(Object)(parent._appopenadcallback.getObject())});
 if (true) break;

case 7:
//C
this.state = 1;
;
 //BA.debugLineNum = 163;BA.debugLine="Sleep(60000)";
parent.__c.Sleep(ba,this,(int) (60000));
this.state = 9;
return;
case 9:
//C
this.state = 1;
;
 if (true) break;

case 8:
//C
this.state = -1;
;
 //BA.debugLineNum = 165;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public String  _fetchrewardedinterstitialad(String _rewardedinterstitialadadunit,Object _callbackmodule,String _event) throws Exception{
anywheresoftware.b4j.object.JavaObject _rewardedinterstitialad = null;
 //BA.debugLineNum = 185;BA.debugLine="Public Sub FetchRewardedInterstitialAd (RewardedIn";
 //BA.debugLineNum = 186;BA.debugLine="SetRewardedInterstitialCallbackModule(CallbackMod";
_setrewardedinterstitialcallbackmodule(_callbackmodule,_event);
 //BA.debugLineNum = 187;BA.debugLine="Dim RewardedInterstitialAd As JavaObject";
_rewardedinterstitialad = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 188;BA.debugLine="RewardedInterstitialAd.InitializeStatic(\"com.goog";
_rewardedinterstitialad.InitializeStatic("com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd");
 //BA.debugLineNum = 189;BA.debugLine="AppInterstitialRAdCallback.InitializeNewInstance(";
_appinterstitialradcallback.InitializeNewInstance(__c.Application.getPackageName()+".adshelper$MyRewardedInterstitialAdCallback",(Object[])(__c.Null));
 //BA.debugLineNum = 190;BA.debugLine="AppInterstitialRAdFullScreenCallback.InitializeNe";
_appinterstitialradfullscreencallback.InitializeNewInstance(__c.Application.getPackageName()+".adshelper$MyFullScreenContentCallback",(Object[])(__c.Null));
 //BA.debugLineNum = 192;BA.debugLine="If AppInterstitialRAdCallback.GetField(\"ad\") = Nu";
if (_appinterstitialradcallback.GetField("ad")== null) { 
 //BA.debugLineNum = 193;BA.debugLine="RewardedInterstitialAd.RunMethod(\"load\", Array(c";
_rewardedinterstitialad.RunMethod("load",new Object[]{(Object)(_ctxt.getObject()),(Object)(_rewardedinterstitialadadunit),_getadrequest(),(Object)(_appinterstitialradcallback.getObject())});
 };
 //BA.debugLineNum = 195;BA.debugLine="End Sub";
return "";
}
public String  _fetchrewardedvideoad(String _rewardedadadunit,Object _callbackmodule,String _event) throws Exception{
anywheresoftware.b4j.object.JavaObject _rewardedad = null;
 //BA.debugLineNum = 238;BA.debugLine="Public Sub FetchRewardedVideoAd (RewardedAdAdUnit";
 //BA.debugLineNum = 239;BA.debugLine="SetRewardedVideoCallbackModule(CallbackModule, Ev";
_setrewardedvideocallbackmodule(_callbackmodule,_event);
 //BA.debugLineNum = 240;BA.debugLine="Dim RewardedAd As JavaObject";
_rewardedad = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 241;BA.debugLine="RewardedAd.InitializeStatic(\"com.google.android.g";
_rewardedad.InitializeStatic("com.google.android.gms.ads.rewarded.RewardedAd");
 //BA.debugLineNum = 242;BA.debugLine="AppVideoRAdCallback.InitializeNewInstance(Applica";
_appvideoradcallback.InitializeNewInstance(__c.Application.getPackageName()+".adshelper$MyRewardedAdCallback",(Object[])(__c.Null));
 //BA.debugLineNum = 243;BA.debugLine="AppVideoRAdFullScreenCallback.InitializeNewInstan";
_appvideoradfullscreencallback.InitializeNewInstance(__c.Application.getPackageName()+".adshelper$MyFullScreenContentCallback",(Object[])(__c.Null));
 //BA.debugLineNum = 245;BA.debugLine="If AppVideoRAdCallback.GetField(\"ad\") = Null Then";
if (_appvideoradcallback.GetField("ad")== null) { 
 //BA.debugLineNum = 246;BA.debugLine="RewardedAd.RunMethod(\"load\", Array(ctxt, Rewarde";
_rewardedad.RunMethod("load",new Object[]{(Object)(_ctxt.getObject()),(Object)(_rewardedadadunit),_getadrequest(),(Object)(_appvideoradcallback.getObject())});
 };
 //BA.debugLineNum = 248;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.collections.Map  _getadaptiveadsize() throws Exception{
anywheresoftware.b4j.object.JavaObject _adsize = null;
int _width = 0;
anywheresoftware.b4j.object.JavaObject _native = null;
 //BA.debugLineNum = 31;BA.debugLine="Public Sub GetAdaptiveAdSize As Map";
 //BA.debugLineNum = 32;BA.debugLine="Dim ctxt As JavaObject";
_ctxt = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 33;BA.debugLine="ctxt.InitializeContext";
_ctxt.InitializeContext(ba);
 //BA.debugLineNum = 34;BA.debugLine="Dim AdSize As JavaObject";
_adsize = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 35;BA.debugLine="Dim width As Int = 100%x / GetDeviceLayoutValues.";
_width = (int) (__c.PerXToCurrent((float) (100),getActivityBA())/(double)__c.GetDeviceLayoutValues(getActivityBA()).Scale);
 //BA.debugLineNum = 36;BA.debugLine="Dim Native As JavaObject = AdSize.InitializeStati";
_native = new anywheresoftware.b4j.object.JavaObject();
_native = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_adsize.InitializeStatic("com.google.android.gms.ads.AdSize").RunMethod("getCurrentOrientationAnchoredAdaptiveBannerAdSize",new Object[]{(Object)(_ctxt.getObject()),(Object)(_width)})));
 //BA.debugLineNum = 37;BA.debugLine="Return CreateMap(\"native\": Native, \"width\": Nativ";
if (true) return __c.createMap(new Object[] {(Object)("native"),(Object)(_native.getObject()),(Object)("width"),_native.RunMethod("getWidthInPixels",new Object[]{(Object)(_ctxt.getObject())}),(Object)("height"),_native.RunMethod("getHeightInPixels",new Object[]{(Object)(_ctxt.getObject())})});
 //BA.debugLineNum = 39;BA.debugLine="End Sub";
return null;
}
public Object  _getadrequest() throws Exception{
anywheresoftware.b4a.admobwrapper.AdViewWrapper.AdRequestBuilderWrapper _builder = null;
anywheresoftware.b4j.object.JavaObject _jo = null;
 //BA.debugLineNum = 41;BA.debugLine="Private Sub GetAdRequest As Object";
 //BA.debugLineNum = 42;BA.debugLine="Dim builder As AdRequestBuilder";
_builder = new anywheresoftware.b4a.admobwrapper.AdViewWrapper.AdRequestBuilderWrapper();
 //BA.debugLineNum = 43;BA.debugLine="builder.Initialize";
_builder.Initialize();
 //BA.debugLineNum = 44;BA.debugLine="Dim jo As JavaObject = builder";
_jo = new anywheresoftware.b4j.object.JavaObject();
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_builder.getObject()));
 //BA.debugLineNum = 45;BA.debugLine="Return jo.RunMethod(\"build\", Null)";
if (true) return _jo.RunMethod("build",(Object[])(__c.Null));
 //BA.debugLineNum = 46;BA.debugLine="End Sub";
return null;
}
public boolean  _getconsentformavailable() throws Exception{
 //BA.debugLineNum = 78;BA.debugLine="Public Sub GetConsentFormAvailable As Boolean";
 //BA.debugLineNum = 79;BA.debugLine="Return ConsentInformation.RunMethod(\"isConsentFor";
if (true) return BA.ObjectToBoolean(_consentinformation.RunMethod("isConsentFormAvailable",(Object[])(__c.Null)));
 //BA.debugLineNum = 80;BA.debugLine="End Sub";
return false;
}
public String  _getconsentstatus() throws Exception{
anywheresoftware.b4a.objects.collections.Map _statuses = null;
int _status = 0;
 //BA.debugLineNum = 61;BA.debugLine="Public Sub GetConsentStatus As String";
 //BA.debugLineNum = 62;BA.debugLine="Log(\"GetConsentStatus\")";
__c.LogImpl("233030145","GetConsentStatus",0);
 //BA.debugLineNum = 63;BA.debugLine="Dim statuses As Map = CreateMap(1: \"NOT_REQUIRED\"";
_statuses = new anywheresoftware.b4a.objects.collections.Map();
_statuses = __c.createMap(new Object[] {(Object)(1),(Object)("NOT_REQUIRED"),(Object)(2),(Object)("REQUIRED"),(Object)(3),(Object)("OBTAINED"),(Object)(0),(Object)("UNKNOWN")});
 //BA.debugLineNum = 64;BA.debugLine="Dim status As Int = ConsentInformation.RunMethod(";
_status = (int)(BA.ObjectToNumber(_consentinformation.RunMethod("getConsentStatus",(Object[])(__c.Null))));
 //BA.debugLineNum = 65;BA.debugLine="Log(\"status: \" & status)";
__c.LogImpl("233030148","status: "+BA.NumberToString(_status),0);
 //BA.debugLineNum = 66;BA.debugLine="Return statuses.GetDefault(status, \"UNKNOWN\")";
if (true) return BA.ObjectToString(_statuses.GetDefault((Object)(_status),(Object)("UNKNOWN")));
 //BA.debugLineNum = 67;BA.debugLine="End Sub";
return "";
}
public String  _getconsenttype() throws Exception{
anywheresoftware.b4a.objects.collections.Map _statuses = null;
int _status = 0;
 //BA.debugLineNum = 70;BA.debugLine="Public Sub GetConsentType As String";
 //BA.debugLineNum = 71;BA.debugLine="Log(\"GetConsentType\")";
__c.LogImpl("233095681","GetConsentType",0);
 //BA.debugLineNum = 72;BA.debugLine="Dim statuses As Map = CreateMap(1: \"NON_PERSONALI";
_statuses = new anywheresoftware.b4a.objects.collections.Map();
_statuses = __c.createMap(new Object[] {(Object)(1),(Object)("NON_PERSONALIZED"),(Object)(2),(Object)("PERSONALIZED"),(Object)(0),(Object)("UNKNOWN")});
 //BA.debugLineNum = 73;BA.debugLine="Dim status As Int = ConsentInformation.RunMethod(";
_status = (int)(BA.ObjectToNumber(_consentinformation.RunMethod("getConsentType",(Object[])(__c.Null))));
 //BA.debugLineNum = 74;BA.debugLine="LogColor(\"status: \" & status, Colors.Cyan)";
__c.LogImpl("233095684","status: "+BA.NumberToString(_status),__c.Colors.Cyan);
 //BA.debugLineNum = 75;BA.debugLine="Return statuses.GetDefault(status, \"UNKNOWN\")";
if (true) return BA.ObjectToString(_statuses.GetDefault((Object)(_status),(Object)("UNKNOWN")));
 //BA.debugLineNum = 76;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba) throws Exception{
innerInitialize(_ba);
anywheresoftware.b4j.object.JavaObject _usermessagingplatform = null;
 //BA.debugLineNum = 23;BA.debugLine="Public Sub Initialize";
 //BA.debugLineNum = 24;BA.debugLine="ctxt.InitializeContext";
_ctxt.InitializeContext(ba);
 //BA.debugLineNum = 25;BA.debugLine="Dim UserMessagingPlatform As JavaObject";
_usermessagingplatform = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 26;BA.debugLine="UserMessagingPlatform.InitializeStatic(\"com.googl";
_usermessagingplatform.InitializeStatic("com.google.android.ump.UserMessagingPlatform");
 //BA.debugLineNum = 27;BA.debugLine="ConsentInformation = UserMessagingPlatform.RunMet";
_consentinformation = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_usermessagingplatform.RunMethod("getConsentInformation",new Object[]{(Object)(_ctxt.getObject())})));
 //BA.debugLineNum = 28;BA.debugLine="End Sub";
return "";
}
public boolean  _isavailablerewardedinterstitialad() throws Exception{
boolean _retval = false;
anywheresoftware.b4j.object.JavaObject _ad = null;
 //BA.debugLineNum = 210;BA.debugLine="Public Sub isAvailableRewardedInterstitialAd As Bo";
 //BA.debugLineNum = 211;BA.debugLine="Dim Retval As Boolean";
_retval = false;
 //BA.debugLineNum = 212;BA.debugLine="Try";
try { //BA.debugLineNum = 213;BA.debugLine="Dim ad As JavaObject = AppInterstitialRAdCallbac";
_ad = new anywheresoftware.b4j.object.JavaObject();
_ad = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_appinterstitialradcallback.GetField("ad")));
 //BA.debugLineNum = 214;BA.debugLine="Retval=ad.IsInitialized";
_retval = _ad.IsInitialized();
 } 
       catch (Exception e6) {
			ba.setLastException(e6); //BA.debugLineNum = 216;BA.debugLine="Retval=False";
_retval = __c.False;
 };
 //BA.debugLineNum = 218;BA.debugLine="Return Retval";
if (true) return _retval;
 //BA.debugLineNum = 219;BA.debugLine="End Sub";
return false;
}
public boolean  _isavailablerewardedvideoad() throws Exception{
boolean _retval = false;
anywheresoftware.b4j.object.JavaObject _ad = null;
 //BA.debugLineNum = 271;BA.debugLine="Public Sub isAvailableRewardedVideoAd As Boolean";
 //BA.debugLineNum = 272;BA.debugLine="Dim Retval As Boolean";
_retval = false;
 //BA.debugLineNum = 273;BA.debugLine="Try";
try { //BA.debugLineNum = 274;BA.debugLine="Dim ad As JavaObject = AppVideoRAdCallback.GetFi";
_ad = new anywheresoftware.b4j.object.JavaObject();
_ad = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_appvideoradcallback.GetField("ad")));
 //BA.debugLineNum = 275;BA.debugLine="Retval=ad.IsInitialized";
_retval = _ad.IsInitialized();
 } 
       catch (Exception e6) {
			ba.setLastException(e6); //BA.debugLineNum = 277;BA.debugLine="Retval=False";
_retval = __c.False;
 };
 //BA.debugLineNum = 279;BA.debugLine="Return Retval";
if (true) return _retval;
 //BA.debugLineNum = 280;BA.debugLine="End Sub";
return false;
}
public anywheresoftware.b4a.keywords.Common.ResumableSubWrapper  _requestconsentinformation(boolean _tagforunderageofconsent) throws Exception{
ResumableSub_RequestConsentInformation rsub = new ResumableSub_RequestConsentInformation(this,_tagforunderageofconsent);
rsub.resume(ba, null);
return (anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.Common.ResumableSubWrapper(), rsub);
}
public static class ResumableSub_RequestConsentInformation extends BA.ResumableSub {
public ResumableSub_RequestConsentInformation(com.burma.royal2d.adshelper parent,boolean _tagforunderageofconsent) {
this.parent = parent;
this._tagforunderageofconsent = _tagforunderageofconsent;
}
com.burma.royal2d.adshelper parent;
boolean _tagforunderageofconsent;
anywheresoftware.b4j.object.JavaObject _consentrequestparameters = null;
Object _callback1 = null;
Object _callback2 = null;
String _methodname = "";
Object[] _args = null;
anywheresoftware.b4j.object.JavaObject _formerror = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
{
parent.__c.ReturnFromResumableSub(this,null);return;}
case 0:
//C
this.state = 1;
 //BA.debugLineNum = 84;BA.debugLine="Dim ConsentRequestParameters As JavaObject";
_consentrequestparameters = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 85;BA.debugLine="ConsentRequestParameters.InitializeNewInstance(\"c";
_consentrequestparameters.InitializeNewInstance("com.google.android.ump.ConsentRequestParameters$Builder",(Object[])(parent.__c.Null));
 //BA.debugLineNum = 86;BA.debugLine="ConsentRequestParameters.RunMethod(\"setTagForUnde";
_consentrequestparameters.RunMethod("setTagForUnderAgeOfConsent",new Object[]{(Object)(_tagforunderageofconsent)});
 //BA.debugLineNum = 88;BA.debugLine="If ConsentDebugSettings.IsInitialized Then";
if (true) break;

case 1:
//if
this.state = 4;
if (parent._consentdebugsettings.IsInitialized()) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 89;BA.debugLine="ConsentRequestParameters.RunMethod(\"setConsentDe";
_consentrequestparameters.RunMethod("setConsentDebugSettings",new Object[]{(Object)(parent._consentdebugsettings.getObject())});
 if (true) break;

case 4:
//C
this.state = 5;
;
 //BA.debugLineNum = 92;BA.debugLine="Dim callback1 As Object = ConsentInformation.Crea";
_callback1 = parent._consentinformation.CreateEventFromUI(ba,"com.google.android.ump.ConsentInformation$OnConsentInfoUpdateSuccessListener","callback",parent.__c.Null);
 //BA.debugLineNum = 95;BA.debugLine="Dim callback2 As Object = ConsentInformation.Crea";
_callback2 = parent._consentinformation.CreateEventFromUI(ba,"com.google.android.ump.ConsentInformation$OnConsentInfoUpdateFailureListener","callback",parent.__c.Null);
 //BA.debugLineNum = 98;BA.debugLine="ConsentInformation.RunMethod(\"requestConsentInfoU";
parent._consentinformation.RunMethod("requestConsentInfoUpdate",new Object[]{(Object)(parent._ctxt.getObject()),_consentrequestparameters.RunMethod("build",(Object[])(parent.__c.Null)),_callback1,_callback2});
 //BA.debugLineNum = 99;BA.debugLine="Wait For Callback_Event (MethodName As String, Ar";
parent.__c.WaitFor("callback_event", ba, this, null);
this.state = 11;
return;
case 11:
//C
this.state = 5;
_methodname = (String) result[0];
_args = (Object[]) result[1];
;
 //BA.debugLineNum = 100;BA.debugLine="Log(MethodName)";
parent.__c.LogImpl("233226769",_methodname,0);
 //BA.debugLineNum = 101;BA.debugLine="LogColor(Args, Colors.Cyan)";
parent.__c.LogImpl("233226770",BA.ObjectToString(_args),parent.__c.Colors.Cyan);
 //BA.debugLineNum = 102;BA.debugLine="If MethodName = \"onConsentInfoUpdateFailure\" Then";
if (true) break;

case 5:
//if
this.state = 10;
if ((_methodname).equals("onConsentInfoUpdateFailure")) { 
this.state = 7;
}else if((_methodname).equals("onConsentInfoUpdateSuccess")) { 
this.state = 9;
}if (true) break;

case 7:
//C
this.state = 10;
 //BA.debugLineNum = 103;BA.debugLine="Dim FormError As JavaObject = Args(0)";
_formerror = new anywheresoftware.b4j.object.JavaObject();
_formerror = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_args[(int) (0)]));
 //BA.debugLineNum = 104;BA.debugLine="Log(\"onConsentInfoUpdateFailure: \" & FormError.R";
parent.__c.LogImpl("233226773","onConsentInfoUpdateFailure: "+BA.ObjectToString(_formerror.RunMethod("getMessage",(Object[])(parent.__c.Null)))+", code: "+BA.ObjectToString(_formerror.RunMethod("getErrorCode",(Object[])(parent.__c.Null))),0);
 //BA.debugLineNum = 105;BA.debugLine="Return False";
if (true) {
parent.__c.ReturnFromResumableSub(this,(Object)(parent.__c.False));return;};
 if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 107;BA.debugLine="Log(\"onConsentInfoUpdateSuccess\")";
parent.__c.LogImpl("233226776","onConsentInfoUpdateSuccess",0);
 //BA.debugLineNum = 108;BA.debugLine="Return True";
if (true) {
parent.__c.ReturnFromResumableSub(this,(Object)(parent.__c.True));return;};
 if (true) break;

case 10:
//C
this.state = -1;
;
 //BA.debugLineNum = 110;BA.debugLine="Return False";
if (true) {
parent.__c.ReturnFromResumableSub(this,(Object)(parent.__c.False));return;};
 //BA.debugLineNum = 111;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public void  _callback_event(String _methodname,Object[] _args) throws Exception{
}
public String  _resetconsentstatus() throws Exception{
 //BA.debugLineNum = 49;BA.debugLine="Public Sub ResetConsentStatus";
 //BA.debugLineNum = 50;BA.debugLine="Log(\"Consent information is being reset!\")";
__c.LogImpl("232899073","Consent information is being reset!",0);
 //BA.debugLineNum = 51;BA.debugLine="ConsentInformation.RunMethod(\"reset\", Null)";
_consentinformation.RunMethod("reset",(Object[])(__c.Null));
 //BA.debugLineNum = 52;BA.debugLine="End Sub";
return "";
}
public Object  _rewardedinterstitialad_event(String _methodname,Object[] _args) throws Exception{
 //BA.debugLineNum = 228;BA.debugLine="Sub RewardedInterstitialAd_Event (MethodName As St";
 //BA.debugLineNum = 229;BA.debugLine="Log(\"RewardedInterstitialAd_Event \"&MethodName)";
__c.LogImpl("233816577","RewardedInterstitialAd_Event "+_methodname,0);
 //BA.debugLineNum = 230;BA.debugLine="Event_Rewarded(Args(0), \"RewardedInterstitial\")";
_event_rewarded(_args[(int) (0)],"RewardedInterstitial");
 //BA.debugLineNum = 231;BA.debugLine="Return True";
if (true) return (Object)(__c.True);
 //BA.debugLineNum = 232;BA.debugLine="End Sub";
return null;
}
public Object  _rewardedvideoad_event(Object[] _args) throws Exception{
 //BA.debugLineNum = 289;BA.debugLine="Sub RewardedVideoAd_Event (Args() As Object) As Ob";
 //BA.debugLineNum = 291;BA.debugLine="Event_Rewarded(Args(0), \"RewardedVideo\")";
_event_rewarded(_args[(int) (0)],"RewardedVideo");
 //BA.debugLineNum = 292;BA.debugLine="Return True";
if (true) return (Object)(__c.True);
 //BA.debugLineNum = 293;BA.debugLine="End Sub";
return null;
}
public String  _setconsentdebugparameters(String _testid,boolean _ineea) throws Exception{
int _geo = 0;
 //BA.debugLineNum = 113;BA.debugLine="Public Sub SetConsentDebugParameters (TestId As St";
 //BA.debugLineNum = 114;BA.debugLine="Log(\"Consent debug parameters are being set. Don'";
__c.LogImpl("233292289","Consent debug parameters are being set. Don't forget to remove before production.",0);
 //BA.debugLineNum = 115;BA.debugLine="Log(\"SetConsentDebugParameters: \" & TestId & \" -";
__c.LogImpl("233292290","SetConsentDebugParameters: "+_testid+" - "+BA.ObjectToString(_ineea),0);
 //BA.debugLineNum = 116;BA.debugLine="ConsentDebugSettings.InitializeNewInstance(\"com.g";
_consentdebugsettings.InitializeNewInstance("com.google.android.ump.ConsentDebugSettings$Builder",new Object[]{(Object)(_ctxt.getObject())});
 //BA.debugLineNum = 117;BA.debugLine="Dim geo As Int";
_geo = 0;
 //BA.debugLineNum = 118;BA.debugLine="If InEEA Then geo = 1 Else geo = 2";
if (_ineea) { 
_geo = (int) (1);}
else {
_geo = (int) (2);};
 //BA.debugLineNum = 119;BA.debugLine="ConsentDebugSettings.RunMethod(\"setDebugGeography";
_consentdebugsettings.RunMethod("setDebugGeography",new Object[]{(Object)(_geo)});
 //BA.debugLineNum = 120;BA.debugLine="ConsentDebugSettings.RunMethod(\"addTestDeviceHash";
_consentdebugsettings.RunMethod("addTestDeviceHashedId",new Object[]{(Object)(_testid)});
 //BA.debugLineNum = 121;BA.debugLine="ConsentDebugSettings = ConsentDebugSettings.RunMe";
_consentdebugsettings = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_consentdebugsettings.RunMethod("build",(Object[])(__c.Null))));
 //BA.debugLineNum = 122;BA.debugLine="End Sub";
return "";
}
public String  _setrewardedinterstitialcallbackmodule(Object _module,String _event) throws Exception{
 //BA.debugLineNum = 221;BA.debugLine="Private Sub SetRewardedInterstitialCallbackModule(";
 //BA.debugLineNum = 222;BA.debugLine="nativeMe = Me";
_nativeme = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(this));
 //BA.debugLineNum = 223;BA.debugLine="nativeMe.RunMethod(\"setEventCallback\", Array(Me))";
_nativeme.RunMethod("setEventCallback",new Object[]{this});
 //BA.debugLineNum = 224;BA.debugLine="Callback_Module_RewardedInterstitial=Module";
_callback_module_rewardedinterstitial = _module;
 //BA.debugLineNum = 225;BA.debugLine="Event_RewardedInterstitial=Event";
_event_rewardedinterstitial = _event;
 //BA.debugLineNum = 226;BA.debugLine="End Sub";
return "";
}
public String  _setrewardedvideocallbackmodule(Object _module,String _event) throws Exception{
 //BA.debugLineNum = 282;BA.debugLine="Private Sub SetRewardedVideoCallbackModule(Module";
 //BA.debugLineNum = 283;BA.debugLine="nativeMe = Me";
_nativeme = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(this));
 //BA.debugLineNum = 284;BA.debugLine="nativeMe.RunMethod(\"setEventCallback\", Array(Me))";
_nativeme.RunMethod("setEventCallback",new Object[]{this});
 //BA.debugLineNum = 285;BA.debugLine="Callback_Module_RewardedVideo=Module";
_callback_module_rewardedvideo = _module;
 //BA.debugLineNum = 286;BA.debugLine="Event_RewardedVideo=Event";
_event_rewardedvideo = _event;
 //BA.debugLineNum = 287;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.keywords.Common.ResumableSubWrapper  _showconsentform() throws Exception{
ResumableSub_ShowConsentForm rsub = new ResumableSub_ShowConsentForm(this);
rsub.resume(ba, null);
return (anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.Common.ResumableSubWrapper(), rsub);
}
public static class ResumableSub_ShowConsentForm extends BA.ResumableSub {
public ResumableSub_ShowConsentForm(com.burma.royal2d.adshelper parent) {
this.parent = parent;
}
com.burma.royal2d.adshelper parent;
anywheresoftware.b4j.object.JavaObject _usermessagingplatform = null;
Object _callback1 = null;
Object _callback2 = null;
String _methodname = "";
Object[] _args = null;
anywheresoftware.b4j.object.JavaObject _formerror = null;
anywheresoftware.b4j.object.JavaObject _form = null;
Object _listener = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
{
parent.__c.ReturnFromResumableSub(this,null);return;}
case 0:
//C
this.state = 1;
 //BA.debugLineNum = 125;BA.debugLine="Dim UserMessagingPlatform As JavaObject";
_usermessagingplatform = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 126;BA.debugLine="UserMessagingPlatform.InitializeStatic(\"com.googl";
_usermessagingplatform.InitializeStatic("com.google.android.ump.UserMessagingPlatform");
 //BA.debugLineNum = 127;BA.debugLine="Dim callback1 As Object = UserMessagingPlatform.C";
_callback1 = _usermessagingplatform.CreateEventFromUI(ba,"com.google.android.ump.UserMessagingPlatform$OnConsentFormLoadSuccessListener","callback",parent.__c.Null);
 //BA.debugLineNum = 129;BA.debugLine="Dim callback2 As Object = UserMessagingPlatform.C";
_callback2 = _usermessagingplatform.CreateEventFromUI(ba,"com.google.android.ump.UserMessagingPlatform$OnConsentFormLoadFailureListener","callback",parent.__c.Null);
 //BA.debugLineNum = 132;BA.debugLine="UserMessagingPlatform.RunMethod(\"loadConsentForm\"";
_usermessagingplatform.RunMethod("loadConsentForm",new Object[]{(Object)(parent._ctxt.getObject()),_callback1,_callback2});
 //BA.debugLineNum = 133;BA.debugLine="Wait For Callback_Event (MethodName As String, Ar";
parent.__c.WaitFor("callback_event", ba, this, null);
this.state = 7;
return;
case 7:
//C
this.state = 1;
_methodname = (String) result[0];
_args = (Object[]) result[1];
;
 //BA.debugLineNum = 134;BA.debugLine="Log(MethodName)";
parent.__c.LogImpl("233357834",_methodname,0);
 //BA.debugLineNum = 135;BA.debugLine="LogColor(Args, Colors.Magenta)";
parent.__c.LogImpl("233357835",BA.ObjectToString(_args),parent.__c.Colors.Magenta);
 //BA.debugLineNum = 136;BA.debugLine="If MethodName = \"onConsentFormLoadFailure\" Then";
if (true) break;

case 1:
//if
this.state = 6;
if ((_methodname).equals("onConsentFormLoadFailure")) { 
this.state = 3;
}else if((_methodname).equals("onConsentFormLoadSuccess")) { 
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 //BA.debugLineNum = 137;BA.debugLine="Dim FormError As JavaObject = Args(0)";
_formerror = new anywheresoftware.b4j.object.JavaObject();
_formerror = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_args[(int) (0)]));
 //BA.debugLineNum = 138;BA.debugLine="Log(\"onConsentFormLoadFailure: \" & FormError.Run";
parent.__c.LogImpl("233357838","onConsentFormLoadFailure: "+BA.ObjectToString(_formerror.RunMethod("getMessage",(Object[])(parent.__c.Null)))+", code: "+BA.ObjectToString(_formerror.RunMethod("getErrorCode",(Object[])(parent.__c.Null))),0);
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 140;BA.debugLine="Dim form As JavaObject = Args(0)";
_form = new anywheresoftware.b4j.object.JavaObject();
_form = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_args[(int) (0)]));
 //BA.debugLineNum = 141;BA.debugLine="Dim listener As Object = form.CreateEventFromUI(";
_listener = _form.CreateEventFromUI(ba,"com.google.android.ump.ConsentForm$OnConsentFormDismissedListener","callback",parent.__c.Null);
 //BA.debugLineNum = 142;BA.debugLine="form.RunMethod(\"show\", Array(ctxt, listener))";
_form.RunMethod("show",new Object[]{(Object)(parent._ctxt.getObject()),_listener});
 //BA.debugLineNum = 143;BA.debugLine="Wait For Callback_Event (MethodName As String, A";
parent.__c.WaitFor("callback_event", ba, this, null);
this.state = 8;
return;
case 8:
//C
this.state = 6;
_methodname = (String) result[0];
_args = (Object[]) result[1];
;
 //BA.debugLineNum = 144;BA.debugLine="Log(\"consent form dismissed\")";
parent.__c.LogImpl("233357844","consent form dismissed",0);
 if (true) break;

case 6:
//C
this.state = -1;
;
 //BA.debugLineNum = 146;BA.debugLine="Return True";
if (true) {
parent.__c.ReturnFromResumableSub(this,(Object)(parent.__c.True));return;};
 //BA.debugLineNum = 147;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public String  _showopenadifavailable() throws Exception{
anywheresoftware.b4j.object.JavaObject _ad = null;
 //BA.debugLineNum = 168;BA.debugLine="Public Sub ShowOpenAdIfAvailable";
 //BA.debugLineNum = 169;BA.debugLine="LogColor(\"ShowOpenAdIfAvailable: \" & AppOpenAdCal";
__c.LogImpl("233488897","ShowOpenAdIfAvailable: "+BA.ObjectToString(_appopenadcallback.IsInitialized()),__c.Colors.Green);
 //BA.debugLineNum = 170;BA.debugLine="If AppOpenAdCallback.IsInitialized = False Then R";
if (_appopenadcallback.IsInitialized()==__c.False) { 
if (true) return "";};
 //BA.debugLineNum = 171;BA.debugLine="If AppOpenAdFullScreenCallback.GetField(\"isShowin";
if ((_appopenadfullscreencallback.GetField("isShowingAd")).equals((Object)(__c.True))) { 
if (true) return "";};
 //BA.debugLineNum = 173;BA.debugLine="If LastBackgroundTime + 2 * DateTime.TicksPerMinu";
if (_lastbackgroundtime+2*__c.DateTime.TicksPerMinute>__c.DateTime.getNow()) { 
if (true) return "";};
 //BA.debugLineNum = 174;BA.debugLine="Dim ad As JavaObject = AppOpenAdCallback.GetField";
_ad = new anywheresoftware.b4j.object.JavaObject();
_ad = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_appopenadcallback.GetField("ad")));
 //BA.debugLineNum = 175;BA.debugLine="If ad.IsInitialized Then";
if (_ad.IsInitialized()) { 
 //BA.debugLineNum = 176;BA.debugLine="ad.RunMethod(\"setFullScreenContentCallback\", Arr";
_ad.RunMethod("setFullScreenContentCallback",new Object[]{(Object)(_appopenadfullscreencallback.getObject())});
 //BA.debugLineNum = 177;BA.debugLine="ad.RunMethod(\"show\", Array(ctxt))";
_ad.RunMethod("show",new Object[]{(Object)(_ctxt.getObject())});
 //BA.debugLineNum = 178;BA.debugLine="AppOpenAdCallback.SetField(\"ad\", Null)";
_appopenadcallback.SetField("ad",__c.Null);
 };
 //BA.debugLineNum = 180;BA.debugLine="End Sub";
return "";
}
public String  _showrewardedinterstitialad() throws Exception{
anywheresoftware.b4j.object.JavaObject _ad = null;
 //BA.debugLineNum = 198;BA.debugLine="Public Sub ShowRewardedInterstitialAd";
 //BA.debugLineNum = 199;BA.debugLine="If AppInterstitialRAdCallback.IsInitialized And N";
if (_appinterstitialradcallback.IsInitialized() && __c.Not(BA.ObjectToBoolean(_appinterstitialradfullscreencallback.GetField("isShowingAd")))) { 
 //BA.debugLineNum = 200;BA.debugLine="Dim ad As JavaObject = AppInterstitialRAdCallbac";
_ad = new anywheresoftware.b4j.object.JavaObject();
_ad = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_appinterstitialradcallback.GetField("ad")));
 //BA.debugLineNum = 201;BA.debugLine="If ad.IsInitialized Then";
if (_ad.IsInitialized()) { 
 //BA.debugLineNum = 202;BA.debugLine="ad.RunMethod(\"setFullScreenContentCallback\", Ar";
_ad.RunMethod("setFullScreenContentCallback",new Object[]{(Object)(_appinterstitialradfullscreencallback.getObject())});
 //BA.debugLineNum = 203;BA.debugLine="nativeMe = Me";
_nativeme = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(this));
 //BA.debugLineNum = 204;BA.debugLine="nativeMe.RunMethod(\"showRewardedInterstitialAd\"";
_nativeme.RunMethod("showRewardedInterstitialAd",new Object[]{(Object)(_ad.getObject()),(Object)(_ctxt.getObject()),(Object)("RewardedInterstitialAd_Event")});
 //BA.debugLineNum = 205;BA.debugLine="AppInterstitialRAdCallback.SetField(\"ad\", Null)";
_appinterstitialradcallback.SetField("ad",__c.Null);
 };
 };
 //BA.debugLineNum = 208;BA.debugLine="End Sub";
return "";
}
public String  _showrewardedvideoad() throws Exception{
anywheresoftware.b4j.object.JavaObject _ad = null;
 //BA.debugLineNum = 253;BA.debugLine="Public Sub ShowRewardedVideoAd";
 //BA.debugLineNum = 254;BA.debugLine="If AppVideoRAdCallback.IsInitialized Then";
if (_appvideoradcallback.IsInitialized()) { 
 //BA.debugLineNum = 255;BA.debugLine="If Not(AppVideoRAdFullScreenCallback.GetField(\"i";
if (__c.Not(BA.ObjectToBoolean(_appvideoradfullscreencallback.GetField("isShowingAd")))) { 
 //BA.debugLineNum = 257;BA.debugLine="Dim ad As JavaObject = AppVideoRAdCallback.GetF";
_ad = new anywheresoftware.b4j.object.JavaObject();
_ad = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_appvideoradcallback.GetField("ad")));
 //BA.debugLineNum = 258;BA.debugLine="If ad.IsInitialized Then";
if (_ad.IsInitialized()) { 
 //BA.debugLineNum = 260;BA.debugLine="ad.RunMethod(\"setFullScreenContentCallback\", A";
_ad.RunMethod("setFullScreenContentCallback",new Object[]{(Object)(_appvideoradfullscreencallback.getObject())});
 //BA.debugLineNum = 262;BA.debugLine="AppVideoRAdCallback.SetField(\"ad\", Null)";
_appvideoradcallback.SetField("ad",__c.Null);
 //BA.debugLineNum = 263;BA.debugLine="nativeMe = Me";
_nativeme = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(this));
 //BA.debugLineNum = 264;BA.debugLine="nativeMe.RunMethod(\"showRewardedVideoAd\", Arra";
_nativeme.RunMethod("showRewardedVideoAd",new Object[]{(Object)(_ad.getObject()),(Object)(_ctxt.getObject()),(Object)("RewardedVideoAd_Event")});
 };
 };
 };
 //BA.debugLineNum = 268;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
//added jc

static B4AClass target;
static Boolean rewarded;
static RewardItem lastRewardItem;
static String lastRewardShownType;

public static class MyAddOpenAdCallback extends AppOpenAdLoadCallback {
	public AppOpenAd ad;
	@Override
	public void onAdFailedToLoad(LoadAdError adError) {
		BA.Log("Failed to load OpenAd: " + adError);
	}
	 @Override
    public void onAdLoaded(AppOpenAd ad) {
		BA.Log("OpenAd received");
		this.ad = ad;
	}	
}

public static class MyFullScreenContentCallback extends FullScreenContentCallback {
	public boolean isShowingAd;	
	@Override
    public void onAdDismissedFullScreenContent() {
		BA.Log("full screen content dismissed");
      	isShowingAd = false;
	  	if (lastRewardItem!=null) {
	  		target.getBA().raiseEventFromDifferentThread(target, null, 0, "event_dismissed_rewarded", false, new Object[] {rewarded, lastRewardItem, lastRewardShownType});
		}
	  	lastRewardShownType="";
	  	rewarded=false;
    }

    @Override
    public void onAdFailedToShowFullScreenContent(AdError adError) {}

    @Override
    public void onAdShowedFullScreenContent() {
      isShowingAd = true;
    }
}

public static class MyRewardedInterstitialAdCallback extends RewardedInterstitialAdLoadCallback {
	public RewardedInterstitialAd ad;
	
	@Override
	public void onAdFailedToLoad(LoadAdError adError) {
		BA.Log("Failed to load RewardedInterstitialAd: " + adError);
		target.getBA().raiseEventFromDifferentThread(target, null, 0, "event_failedtoreceivead", false, new Object[] {adError.toString(), "RewardedInterstitial"});
	}
	
	@Override
    public void onAdLoaded(RewardedInterstitialAd ad) {
		BA.Log("RewardedInterstitialAd received");
		target.getBA().raiseEventFromDifferentThread(target, null, 0, "event_receivead", false, new Object[] {"RewardedInterstitial"});
		this.ad = ad;
	}	
}

public void setEventCallback(B4AClass target) {
	//BA.Log("setEventCallback");	   
    this.target = target;	   
}
      
public void showRewardedVideoAd(RewardedAd mRewardedAd, Activity context, String theEvent) {
	rewarded=false;
	lastRewardShownType="RewardedVideo";
	if (mRewardedAd != null) {
//	  isShowingAd=true;
	  mRewardedAd.show(context, new OnUserEarnedRewardListener() {
	    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
	      // Handle the reward.
	      BA.Log("The user earned the reward.");
	      int rewardAmount = rewardItem.getAmount();
	      String rewardType = rewardItem.getType();
		  rewarded=true;
		  lastRewardItem=rewardItem;
		  target.getBA().raiseEventFromDifferentThread(target, null, 0, "theEvent", false, new Object[] {rewardItem});
	    }
	  });
	} else {
	  BA.Log("The rewarded ad wasn't ready yet.");
	}
}
      
public void showRewardedInterstitialAd(RewardedInterstitialAd mRewardedAd, Activity context, String theEvent) {
	rewarded=false;
	lastRewardShownType="RewardedInterstitial";
	if (mRewardedAd != null) {
//	  isShowingAd=true;
	  mRewardedAd.show(context, new OnUserEarnedRewardListener() {
	    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
	      // Handle the reward.
	      BA.Log("The user earned the reward.");
	      int rewardAmount = rewardItem.getAmount();
	      String rewardType = rewardItem.getType();
		  rewarded=true;
		  lastRewardItem=rewardItem;
		  target.getBA().raiseEventFromDifferentThread(target, null, 0, "theEvent", false, new Object[] {rewardItem});
	    }
	  });
	} else {
	  BA.Log("The rewarded ad wasn't ready yet.");
	}
}

//rewarded video ad
public static class MyRewardedAdCallback extends RewardedAdLoadCallback {
	public RewardedAd ad;
	
	@Override
	public void onAdFailedToLoad(LoadAdError adError) {
		BA.Log("Failed to load RewardedVideoAd: " + adError);
		target.getBA().raiseEventFromDifferentThread(target, null, 0, "event_failedtoreceivead", false, new Object[] {adError.toString(), "RewardedVideo"});
	}
	
	@Override
    public void onAdLoaded(RewardedAd ad) {
		BA.Log("RewardedVideoAd received");
		target.getBA().raiseEventFromDifferentThread(target, null, 0, "event_receivead", false, new Object[] {"RewardedVideo"});
		this.ad = ad;
	}	
}
}
