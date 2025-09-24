package com.burma.royal2d;

import java.io.*;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class chatsseconnector extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "com.burma.royal2d.chatsseconnector");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", com.burma.royal2d.chatsseconnector.class).invoke(this, new Object[] {null});
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
 //BA.debugLineNum = 12;BA.debugLine="Private fullbuffer As StringBuilder";
_fullbuffer = new anywheresoftware.b4a.keywords.StringBuilderWrapper();
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
public String  _connect(String _url) throws Exception{
anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest _req = null;
 //BA.debugLineNum = 25;BA.debugLine="Public Sub Connect(url As String)";
 //BA.debugLineNum = 26;BA.debugLine="If public_chat.isCall = True Then";
if (_public_chat._iscall /*boolean*/ ==__c.True) { 
 //BA.debugLineNum = 27;BA.debugLine="Dim req As OkHttpRequest";
_req = new anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpRequest();
 //BA.debugLineNum = 28;BA.debugLine="req.InitializeGet(url)";
_req.InitializeGet(_url);
 //BA.debugLineNum = 29;BA.debugLine="hc.Execute(req, 0)";
_hc.Execute(ba,_req,(int) (0));
 };
 //BA.debugLineNum = 31;BA.debugLine="End Sub";
return "";
}
public String  _data_available(byte[] _buffer) throws Exception{
String _chunk = "";
int _newlineindex = 0;
String _onemessage = "";
 //BA.debugLineNum = 48;BA.debugLine="Private Sub Data_Available (Buffer() As Byte)";
 //BA.debugLineNum = 49;BA.debugLine="lastReceiveTime = DateTime.Now";
_lastreceivetime = __c.DateTime.getNow();
 //BA.debugLineNum = 51;BA.debugLine="Try";
try { //BA.debugLineNum = 52;BA.debugLine="Dim chunk As String = BytesToString(Buffer, 0, B";
_chunk = __c.BytesToString(_buffer,(int) (0),_buffer.length,"UTF-8");
 //BA.debugLineNum = 53;BA.debugLine="If chunk.Contains(\"data: \") Then";
if (_chunk.contains("data: ")) { 
 //BA.debugLineNum = 54;BA.debugLine="chunk = chunk.Replace(\"data: \",\"\")";
_chunk = _chunk.replace("data: ","");
 };
 //BA.debugLineNum = 56;BA.debugLine="If(chunk.Contains(\"data: \") ) Then";
if ((_chunk.contains("data: "))) { 
 //BA.debugLineNum = 57;BA.debugLine="chunk =chunk.Replace(\"data: \",\"\")";
_chunk = _chunk.replace("data: ","");
 };
 //BA.debugLineNum = 59;BA.debugLine="fullbuffer.Append(chunk)";
_fullbuffer.Append(_chunk);
 //BA.debugLineNum = 62;BA.debugLine="Do While True";
while (__c.True) {
 //BA.debugLineNum = 63;BA.debugLine="Dim newlineIndex As Int = fullbuffer.ToString.I";
_newlineindex = _fullbuffer.ToString().indexOf(__c.CRLF);
 //BA.debugLineNum = 64;BA.debugLine="If newlineIndex = -1 Then Exit";
if (_newlineindex==-1) { 
if (true) break;};
 //BA.debugLineNum = 66;BA.debugLine="Dim oneMessage As String = fullbuffer.ToString.";
_onemessage = _fullbuffer.ToString().substring((int) (0),_newlineindex).trim();
 //BA.debugLineNum = 67;BA.debugLine="fullbuffer.Remove(0, newlineIndex + 2)";
_fullbuffer.Remove((int) (0),(int) (_newlineindex+2));
 //BA.debugLineNum = 69;BA.debugLine="If oneMessage.Length > 0 Then";
if (_onemessage.length()>0) { 
 //BA.debugLineNum = 70;BA.debugLine="CallSubDelayed2(Me, \"LiveChat\", oneMessage)";
__c.CallSubDelayed2(ba,this,"LiveChat",(Object)(_onemessage));
 };
 }
;
 } 
       catch (Exception e21) {
			ba.setLastException(e21); //BA.debugLineNum = 75;BA.debugLine="Log(\"Data_Available error: \" & LastException)";
__c.LogImpl("235192859","Data_Available error: "+BA.ObjectToString(__c.LastException(getActivityBA())),0);
 };
 //BA.debugLineNum = 77;BA.debugLine="End Sub";
return "";
}
public String  _finish() throws Exception{
 //BA.debugLineNum = 79;BA.debugLine="Public Sub Finish";
 //BA.debugLineNum = 80;BA.debugLine="If gout.IsInitialized Then";
if (_gout.IsInitialized()) { 
 //BA.debugLineNum = 81;BA.debugLine="Try";
try { //BA.debugLineNum = 82;BA.debugLine="gResponse.Release";
_gresponse.Release();
 //BA.debugLineNum = 83;BA.debugLine="gout.Close";
_gout.Close();
 } 
       catch (Exception e6) {
			ba.setLastException(e6); //BA.debugLineNum = 85;BA.debugLine="Log(LastException)";
__c.LogImpl("235258374",BA.ObjectToString(__c.LastException(getActivityBA())),0);
 };
 };
 //BA.debugLineNum = 88;BA.debugLine="End Sub";
return "";
}
public long  _getlastime() throws Exception{
 //BA.debugLineNum = 90;BA.debugLine="Public Sub getlastime As Long";
 //BA.debugLineNum = 91;BA.debugLine="Return	lastReceiveTime";
if (true) return _lastreceivetime;
 //BA.debugLineNum = 92;BA.debugLine="End Sub";
return 0L;
}
public String  _hchat_responseerror(anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpResponse _response,String _reason,int _statuscode,int _taskid) throws Exception{
 //BA.debugLineNum = 33;BA.debugLine="Private Sub hchat_ResponseError (Response As OkHtt";
 //BA.debugLineNum = 34;BA.debugLine="Log(\"Chat SSE Error: \" & StatusCode)";
__c.LogImpl("235061761","Chat SSE Error: "+BA.NumberToString(_statuscode),0);
 //BA.debugLineNum = 35;BA.debugLine="Response.Release";
_response.Release();
 //BA.debugLineNum = 36;BA.debugLine="End Sub";
return "";
}
public void  _hchat_responsesuccess(anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpResponse _response,int _taskid) throws Exception{
ResumableSub_hchat_ResponseSuccess rsub = new ResumableSub_hchat_ResponseSuccess(this,_response,_taskid);
rsub.resume(ba, null);
}
public static class ResumableSub_hchat_ResponseSuccess extends BA.ResumableSub {
public ResumableSub_hchat_ResponseSuccess(com.burma.royal2d.chatsseconnector parent,anywheresoftware.b4h.okhttp.OkHttpClientWrapper.OkHttpResponse _response,int _taskid) {
this.parent = parent;
this._response = _response;
this._taskid = _taskid;
}
com.burma.royal2d.chatsseconnector parent;
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
this.state = -1;
 //BA.debugLineNum = 39;BA.debugLine="Dim out As JavaObject";
_out = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 40;BA.debugLine="out.InitializeNewInstance(packageName&\".chatsseco";
_out.InitializeNewInstance(parent._packagename+".chatsseconnector$MyOutputStream",new Object[]{parent});
 //BA.debugLineNum = 41;BA.debugLine="gout = out";
parent._gout = (anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper(), (java.io.OutputStream)(_out.getObject()));
 //BA.debugLineNum = 42;BA.debugLine="gResponse=Response";
parent._gresponse = _response;
 //BA.debugLineNum = 43;BA.debugLine="bbuffer.Clear";
parent._bbuffer._clear /*String*/ ();
 //BA.debugLineNum = 44;BA.debugLine="Response.GetAsynchronously(\"chatreq\", out, False,";
_response.GetAsynchronously(ba,"chatreq",(java.io.OutputStream)(_out.getObject()),parent.__c.False,(int) (0));
 //BA.debugLineNum = 45;BA.debugLine="Wait For chatreq_StreamFinish (Success As Boolean";
parent.__c.WaitFor("chatreq_streamfinish", ba, this, null);
this.state = 1;
return;
case 1:
//C
this.state = -1;
_success = (Boolean) result[0];
_taskid = (Integer) result[1];
;
 //BA.debugLineNum = 46;BA.debugLine="Log(\"Stream finish\")";
parent.__c.LogImpl("235127304","Stream finish",0);
 //BA.debugLineNum = 47;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public void  _chatreq_streamfinish(boolean _success,int _taskid) throws Exception{
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
 //BA.debugLineNum = 19;BA.debugLine="hc.Initialize(\"hchat\")";
_hc.Initialize("hchat");
 //BA.debugLineNum = 20;BA.debugLine="filename=fname";
_filename = _fname;
 //BA.debugLineNum = 21;BA.debugLine="packageName=pkg";
_packagename = _pkg;
 //BA.debugLineNum = 22;BA.debugLine="bbuffer.Initialize";
_bbuffer._initialize /*String*/ (ba);
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public String  _livechat(String _data) throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _json = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 95;BA.debugLine="Private Sub LiveChat (data As String)";
 //BA.debugLineNum = 96;BA.debugLine="If public_chat.isCall = True Then";
if (_public_chat._iscall /*boolean*/ ==__c.True) { 
 //BA.debugLineNum = 97;BA.debugLine="If data.Contains(\": ping\") Then";
if (_data.contains(": ping")) { 
 }else {
 //BA.debugLineNum = 99;BA.debugLine="If data.StartsWith(\"[\") Then";
if (_data.startsWith("[")) { 
 //BA.debugLineNum = 101;BA.debugLine="Dim json As JSONParser";
_json = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 102;BA.debugLine="json.Initialize(data)";
_json.Initialize(_data);
 //BA.debugLineNum = 103;BA.debugLine="mycode.chatlist =json.NextArray";
_mycode._chatlist /*anywheresoftware.b4a.objects.collections.List*/  = _json.NextArray();
 }else if(_data.startsWith("{")) { 
 //BA.debugLineNum = 106;BA.debugLine="Dim json As JSONParser";
_json = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 107;BA.debugLine="json.Initialize(data)";
_json.Initialize(_data);
 //BA.debugLineNum = 108;BA.debugLine="Dim m As Map = json.NextObject";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = _json.NextObject();
 //BA.debugLineNum = 109;BA.debugLine="If mycode.chatlist.IsInitialized Then";
if (_mycode._chatlist /*anywheresoftware.b4a.objects.collections.List*/ .IsInitialized()) { 
 //BA.debugLineNum = 110;BA.debugLine="mycode.chatlist.Add(m)";
_mycode._chatlist /*anywheresoftware.b4a.objects.collections.List*/ .Add((Object)(_m.getObject()));
 //BA.debugLineNum = 111;BA.debugLine="If mycode.chatlist.Size >= 50 Then";
if (_mycode._chatlist /*anywheresoftware.b4a.objects.collections.List*/ .getSize()>=50) { 
 //BA.debugLineNum = 112;BA.debugLine="mycode.chatlist.RemoveAt(0) ' Remove first i";
_mycode._chatlist /*anywheresoftware.b4a.objects.collections.List*/ .RemoveAt((int) (0));
 };
 }else {
 };
 };
 //BA.debugLineNum = 117;BA.debugLine="CallSubDelayed2(public_chat,\"loadMessageSuccess";
__c.CallSubDelayed2(ba,(Object)(_public_chat.getObject()),"loadMessageSuccess",(Object)(_data));
 };
 };
 //BA.debugLineNum = 120;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
if (BA.fastSubCompare(sub, "CONNECT"))
	return _connect((String) args[0]);
if (BA.fastSubCompare(sub, "LIVECHAT"))
	return _livechat((String) args[0]);
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
