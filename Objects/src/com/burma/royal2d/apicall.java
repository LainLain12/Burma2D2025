package com.burma.royal2d;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class apicall {
private static apicall mostCurrent = new apicall();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static String _lottodata = "";
public static String _lottohis = "";
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
public static String  _getlottodata(anywheresoftware.b4a.BA _ba) throws Exception{
com.burma.royal2d.httpjob _j = null;
 //BA.debugLineNum = 10;BA.debugLine="Sub getlottodata";
 //BA.debugLineNum = 11;BA.debugLine="Dim j As HttpJob";
_j = new com.burma.royal2d.httpjob();
 //BA.debugLineNum = 12;BA.debugLine="j.Initialize(lottosociety.lottogetter,Starter)";
_j._initialize /*String*/ ((_ba.processBA == null ? _ba : _ba.processBA),mostCurrent._lottosociety._lottogetter /*String*/ ,(Object)(mostCurrent._starter.getObject()));
 //BA.debugLineNum = 13;BA.debugLine="j.Download(Main.newsite&\"lottosociety/getlotto?la";
_j._download /*String*/ (mostCurrent._main._newsite /*String*/ +"lottosociety/getlotto?last=true");
 //BA.debugLineNum = 14;BA.debugLine="End Sub";
return "";
}
public static String  _getlottohistory(anywheresoftware.b4a.BA _ba) throws Exception{
com.burma.royal2d.httpjob _j = null;
 //BA.debugLineNum = 16;BA.debugLine="Sub getlottohistory";
 //BA.debugLineNum = 17;BA.debugLine="Dim j As HttpJob";
_j = new com.burma.royal2d.httpjob();
 //BA.debugLineNum = 18;BA.debugLine="j.Initialize(\"lottohis\",Starter)";
_j._initialize /*String*/ ((_ba.processBA == null ? _ba : _ba.processBA),"lottohis",(Object)(mostCurrent._starter.getObject()));
 //BA.debugLineNum = 19;BA.debugLine="j.Download(Main.newsite&\"lottosociety/getlotto\")";
_j._download /*String*/ (mostCurrent._main._newsite /*String*/ +"lottosociety/getlotto");
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Dim lottodata As String";
_lottodata = "";
 //BA.debugLineNum = 7;BA.debugLine="Dim lottohis As String";
_lottohis = "";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
}
