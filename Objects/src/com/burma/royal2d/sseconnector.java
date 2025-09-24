package com.burma.royal2d;

import java.io.*;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class sseconnector extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "com.burma.royal2d.sseconnector");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", com.burma.royal2d.sseconnector.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public Object _mcallback = null;
public String _meventname = "";
public anywheresoftware.b4h.okhttp.OkHttpClientWrapper _hc = null;
public com.burma.royal2d.b4xbytesbuilder _bbuffer = null;
public anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _gout = null;
public String _filename = "";
public String _packagename = "";
public anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpResponse _gresponse = null;
public long _lastreceivetime = 0L;
public anywheresoftware.b4a.keywords.StringBuilderWrapper _fullbuffer = null;
public String _urls = "";
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
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 2;BA.debugLine="Private mCallback As Object";
_mcallback = new Object();
 //BA.debugLineNum = 3;BA.debugLine="Private mEventName As String";
_meventname = "";
 //BA.debugLineNum = 4;BA.debugLine="Private hc As OkHttpClient";
_hc = new anywheresoftware.b4h.okhttp.OkHttpClientWrapper();
 //BA.debugLineNum = 5;BA.debugLine="Private bbuffer As B4XBytesBuilder";
_bbuffer = new com.burma.royal2d.b4xbytesbuilder();
 //BA.debugLineNum = 6;BA.debugLine="Public gout As OutputStream";
_gout = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
 //BA.debugLineNum = 7;BA.debugLine="Public filename As String";
_filename = "";
 //BA.debugLineNum = 8;BA.debugLine="Dim packageName As String";
_packagename = "";
 //BA.debugLineNum = 9;BA.debugLine="Dim gResponse As OkHttpResponse";
_gresponse = new anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpResponse();
 //BA.debugLineNum = 10;BA.debugLine="Dim lastReceiveTime As Long";
_lastreceivetime = 0L;
 //BA.debugLineNum = 11;BA.debugLine="Dim fullbuffer As StringBuilder";
_fullbuffer = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 12;BA.debugLine="Dim urls As String";
_urls = "";
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
public String  _connect(String _url) throws Exception{
anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest _req = null;
 //BA.debugLineNum = 25;BA.debugLine="Public Sub Connect(url As String)";
 //BA.debugLineNum = 26;BA.debugLine="Log(Main.isCall)";
__c.LogImpl("232243713",BA.ObjectToString(_main._iscall /*boolean*/ ),0);
 //BA.debugLineNum = 27;BA.debugLine="Log(public_chat.isCall)";
__c.LogImpl("232243714",BA.ObjectToString(_public_chat._iscall /*boolean*/ ),0);
 //BA.debugLineNum = 28;BA.debugLine="If public_chat.isCall =True Or Main.isCall = True";
if (_public_chat._iscall /*boolean*/ ==__c.True || _main._iscall /*boolean*/ ==__c.True) { 
 //BA.debugLineNum = 29;BA.debugLine="Log(Main.isCall)";
__c.LogImpl("232243716",BA.ObjectToString(_main._iscall /*boolean*/ ),0);
 //BA.debugLineNum = 30;BA.debugLine="Log(public_chat.isCall)";
__c.LogImpl("232243717",BA.ObjectToString(_public_chat._iscall /*boolean*/ ),0);
 //BA.debugLineNum = 31;BA.debugLine="urls = url";
_urls = _url;
 //BA.debugLineNum = 32;BA.debugLine="Dim req As OkHttpRequest";
_req = new anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest();
 //BA.debugLineNum = 33;BA.debugLine="req.InitializeGet(url)";
_req.InitializeGet(_url);
 //BA.debugLineNum = 34;BA.debugLine="hc.Execute(req, 0)";
_hc.Execute(ba,_req,(int) (0));
 };
 //BA.debugLineNum = 36;BA.debugLine="End Sub";
return "";
}
public String  _data_available(byte[] _buffer) throws Exception{
String _data = "";
anywheresoftware.b4a.objects.collections.JSONParser _json = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 64;BA.debugLine="Private Sub Data_Available (Buffer() As Byte)";
 //BA.debugLineNum = 65;BA.debugLine="lastReceiveTime = DateTime.Now";
_lastreceivetime = __c.DateTime.getNow();
 //BA.debugLineNum = 66;BA.debugLine="Dim data As String =BytesToString(Buffer,0,Buffer.";
_data = __c.BytesToString(_buffer,(int) (0),_buffer.length,"UTF-8");
 //BA.debugLineNum = 68;BA.debugLine="Try";
try { //BA.debugLineNum = 70;BA.debugLine="If data.Contains(\"data:\") Then";
if (_data.contains("data:")) { 
 //BA.debugLineNum = 71;BA.debugLine="data = data.Replace(\"data: \",\"\")";
_data = _data.replace("data: ","");
 //BA.debugLineNum = 72;BA.debugLine="File.WriteString(File.DirInternal,filename,data";
__c.File.WriteString(__c.File.getDirInternal(),_filename,_data);
 //BA.debugLineNum = 73;BA.debugLine="Dim json As JSONParser";
_json = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 74;BA.debugLine="json.Initialize(data)";
_json.Initialize(_data);
 //BA.debugLineNum = 75;BA.debugLine="Dim ls As List = json.NextArray";
_ls = new anywheresoftware.b4a.objects.collections.List();
_ls = _json.NextArray();
 //BA.debugLineNum = 76;BA.debugLine="If ls.Size>0 Then";
if (_ls.getSize()>0) { 
 //BA.debugLineNum = 77;BA.debugLine="For i = 0 To ls.Size -1";
{
final int step11 = 1;
final int limit11 = (int) (_ls.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit11 ;_i = _i + step11 ) {
 //BA.debugLineNum = 78;BA.debugLine="Dim m As Map = ls.Get(i)";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = (anywheresoftware.b4a.objects.collections.Map) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.collections.Map(), (java.util.Map)(_ls.Get(_i)));
 //BA.debugLineNum = 80;BA.debugLine="If mycode.LastLive  <> m.Get(\"live\") Then";
if ((_mycode._lastlive /*String*/ ).equals(BA.ObjectToString(_m.Get((Object)("live")))) == false) { 
 //BA.debugLineNum = 81;BA.debugLine="If Main.isCall = True Then";
if (_main._iscall /*boolean*/ ==__c.True) { 
 //BA.debugLineNum = 82;BA.debugLine="Log(\"live\")";
__c.LogImpl("232440338","live",0);
 //BA.debugLineNum = 83;BA.debugLine="CallSubDelayed(Main,\"change\")";
__c.CallSubDelayed(ba,(Object)(_main.getObject()),"change");
 }else if(_public_chat._iscall /*boolean*/ ==__c.True) { 
 //BA.debugLineNum = 85;BA.debugLine="CallSubDelayed(public_chat,\"Change\")";
__c.CallSubDelayed(ba,(Object)(_public_chat.getObject()),"Change");
 };
 };
 //BA.debugLineNum = 88;BA.debugLine="mycode.LastLive = m.Get(\"live\")";
_mycode._lastlive /*String*/  = BA.ObjectToString(_m.Get((Object)("live")));
 }
};
 };
 };
 } 
       catch (Exception e26) {
			ba.setLastException(e26); //BA.debugLineNum = 95;BA.debugLine="Log(\"Data_Available error: \" & LastException)";
__c.LogImpl("232440351","Data_Available error: "+BA.ObjectToString(__c.LastException(getActivityBA())),0);
 };
 //BA.debugLineNum = 97;BA.debugLine="End Sub";
return "";
}
public String  _finish() throws Exception{
 //BA.debugLineNum = 101;BA.debugLine="Public Sub Finish";
 //BA.debugLineNum = 102;BA.debugLine="If gout.IsInitialized Then";
if (_gout.IsInitialized()) { 
 //BA.debugLineNum = 103;BA.debugLine="Try";
try { //BA.debugLineNum = 104;BA.debugLine="gResponse.Release";
_gresponse.Release();
 //BA.debugLineNum = 105;BA.debugLine="gout.Close";
_gout.Close();
 } 
       catch (Exception e6) {
			ba.setLastException(e6); //BA.debugLineNum = 107;BA.debugLine="Log(LastException)";
__c.LogImpl("232505862",BA.ObjectToString(__c.LastException(getActivityBA())),0);
 };
 };
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
return "";
}
public long  _getlastime() throws Exception{
 //BA.debugLineNum = 112;BA.debugLine="Public Sub getlastime As Long";
 //BA.debugLineNum = 113;BA.debugLine="Return	lastReceiveTime";
if (true) return _lastreceivetime;
 //BA.debugLineNum = 114;BA.debugLine="End Sub";
return 0L;
}
public String  _hc_responseerror(anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpResponse _response,String _reason,int _statuscode,int _taskid) throws Exception{
 //BA.debugLineNum = 38;BA.debugLine="Private Sub hc_ResponseError (Response As OkHttpRe";
 //BA.debugLineNum = 39;BA.debugLine="Log(\"Live SSE Error: \" & StatusCode)";
__c.LogImpl("232309249","Live SSE Error: "+BA.NumberToString(_statuscode),0);
 //BA.debugLineNum = 40;BA.debugLine="If Main.isCall=True Then";
if (_main._iscall /*boolean*/ ==__c.True) { 
 //BA.debugLineNum = 41;BA.debugLine="CallSubDelayed(Main,\"Connect\")";
__c.CallSubDelayed(ba,(Object)(_main.getObject()),"Connect");
 };
 //BA.debugLineNum = 43;BA.debugLine="If public_chat.isCall = True Then";
if (_public_chat._iscall /*boolean*/ ==__c.True) { 
 //BA.debugLineNum = 44;BA.debugLine="CallSubDelayed(public_chat,\"liveLoader\")";
__c.CallSubDelayed(ba,(Object)(_public_chat.getObject()),"liveLoader");
 };
 //BA.debugLineNum = 46;BA.debugLine="Response.Release";
_response.Release();
 //BA.debugLineNum = 47;BA.debugLine="End Sub";
return "";
}
public void  _hc_responsesuccess(anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpResponse _response,int _taskid) throws Exception{
ResumableSub_hc_ResponseSuccess rsub = new ResumableSub_hc_ResponseSuccess(this,_response,_taskid);
rsub.resume(ba, null);
}
public static class ResumableSub_hc_ResponseSuccess extends BA.ResumableSub {
public ResumableSub_hc_ResponseSuccess(com.burma.royal2d.sseconnector parent,anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpResponse _response,int _taskid) {
this.parent = parent;
this._response = _response;
this._taskid = _taskid;
}
com.burma.royal2d.sseconnector parent;
anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpResponse _response;
int _taskid;
anywheresoftware.b4j.object.JavaObject _out = null;
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
 //BA.debugLineNum = 50;BA.debugLine="Dim out As JavaObject";
_out = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 51;BA.debugLine="out.InitializeNewInstance(packageName&\".sseconnec";
_out.InitializeNewInstance(parent._packagename+".sseconnector$MyOutputStream",new Object[]{parent});
 //BA.debugLineNum = 52;BA.debugLine="gout = out";
parent._gout = (anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper(), (java.io.OutputStream)(_out.getObject()));
 //BA.debugLineNum = 53;BA.debugLine="gResponse=Response";
parent._gresponse = _response;
 //BA.debugLineNum = 54;BA.debugLine="bbuffer.Clear";
parent._bbuffer._clear /*String*/ ();
 //BA.debugLineNum = 55;BA.debugLine="Response.GetAsynchronously(\"req\", out, False, 0)";
_response.GetAsynchronously(ba,"req",(java.io.OutputStream)(_out.getObject()),parent.__c.False,(int) (0));
 //BA.debugLineNum = 56;BA.debugLine="Wait For req_StreamFinish (Success As Boolean, Ta";
parent.__c.WaitFor("req_streamfinish", ba, this, null);
this.state = 5;
return;
case 5:
//C
this.state = 1;
_success = (Boolean) result[0];
_taskid = (Integer) result[1];
;
 //BA.debugLineNum = 57;BA.debugLine="If Main.isCall =True Or public_chat.isCall = True";
if (true) break;

case 1:
//if
this.state = 4;
if (parent._main._iscall /*boolean*/ ==parent.__c.True || parent._public_chat._iscall /*boolean*/ ==parent.__c.True) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 58;BA.debugLine="CallSubDelayed(Me,Finish)";
parent.__c.CallSubDelayed(ba,parent,parent._finish());
 //BA.debugLineNum = 59;BA.debugLine="CallSubDelayed2(Me,\"Connect\",urls)";
parent.__c.CallSubDelayed2(ba,parent,"Connect",(Object)(parent._urls));
 if (true) break;

case 4:
//C
this.state = -1;
;
 //BA.debugLineNum = 61;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public void  _req_streamfinish(boolean _success,int _taskid) throws Exception{
}
public String  _initialize(anywheresoftware.b4a.BA _ba,Object _callback,String _eventname,String _fname,String _pkg) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 15;BA.debugLine="Public Sub Initialize (Callback As Object, EventNa";
 //BA.debugLineNum = 16;BA.debugLine="fullbuffer.Initialize";
_fullbuffer.Initialize();
 //BA.debugLineNum = 17;BA.debugLine="mCallback = Callback";
_mcallback = _callback;
 //BA.debugLineNum = 18;BA.debugLine="mEventName = EventName";
_meventname = _eventname;
 //BA.debugLineNum = 19;BA.debugLine="hc.Initialize(\"hc\")";
_hc.Initialize("hc");
 //BA.debugLineNum = 20;BA.debugLine="filename=fname";
_filename = _fname;
 //BA.debugLineNum = 21;BA.debugLine="packageName=pkg";
_packagename = _pkg;
 //BA.debugLineNum = 22;BA.debugLine="bbuffer.Initialize";
_bbuffer._initialize /*String*/ (ba);
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
if (BA.fastSubCompare(sub, "CONNECT"))
	return _connect((String) args[0]);
return BA.SubDelegator.SubNotFound;
}
public static class MyOutputStream extends OutputStream {
    B4AClass cls;
    private boolean closed;
    public MyOutputStream (B4AClass cls) {
        this.cls = cls;
    }
    
    public void write(int b) throws IOException {
        if (closed)
            throw new IOException("closed");
        cls.getBA().raiseEventFromDifferentThread (null, null, 0, "data_available", true, new Object[] {new byte[] {(byte)b}});
    }
    public void write(byte b[], int off, int len) throws IOException {
        if (closed)
            throw new IOException("closed");
        byte[] bb = new byte[len];
        System.arraycopy(b, off, bb, 0, len);
        cls.getBA().raiseEventFromDifferentThread (null, null, 0, "data_available", true, new Object[] {bb});
    }
    public void close() {
        closed = true;
    }
}
}
