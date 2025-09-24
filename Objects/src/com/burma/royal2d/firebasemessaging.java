package com.burma.royal2d;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ReceiverHelper;
import anywheresoftware.b4a.debug.*;

public class firebasemessaging extends android.content.BroadcastReceiver{
		
    static firebasemessaging mostCurrent;
	public static BA processBA;
    private ReceiverHelper _receiver;
    private static boolean firstTime = true;
    public static Class<?> getObject() {
		return firebasemessaging.class;
	}
	@Override
	public void onReceive(android.content.Context context, android.content.Intent intent) {
        mostCurrent = this;
       
        if (processBA == null) {
           
		    processBA = new BA(context, null, null, anywheresoftware.b4a.BA.SharedProcessBA.ModuleType.RECEIVER, "com.burma.royal2d.firebasemessaging");
            if (BA.isShellModeRuntimeCheck(processBA)) {
                processBA.raiseEvent2(null, true, "SHELL", false);
		    }
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
        }
         _receiver = new ReceiverHelper(this);
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "com.burma.royal2d.firebasemessaging", processBA, _receiver, anywheresoftware.b4a.keywords.Common.Density);
		}
        processBA.setActivityPaused(false);
        BA.LogInfo("*** Receiver (firebasemessaging) Receive " + (firstTime ? "(first time)" : "") + " ***");
        anywheresoftware.b4a.objects.IntentWrapper iw = new anywheresoftware.b4a.objects.IntentWrapper();
        iw.setObject(intent);
        processBA.raiseEvent(null, "receiver_receive", firstTime, iw);
        firstTime = false;
    }
	
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.FirebaseNotificationsService.FirebaseMessageWrapper _fm = null;
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
public static String  _fm_messagearrived(anywheresoftware.b4a.objects.FirebaseNotificationsService.RemoteMessageWrapper _message) throws Exception{
anywheresoftware.b4a.objects.NotificationWrapper _n2 = null;
 //BA.debugLineNum = 18;BA.debugLine="Sub fm_MessageArrived (Message As RemoteMessage)";
 //BA.debugLineNum = 20;BA.debugLine="If B4XPages.IsInitialized And B4XPages.GetManager";
if (mostCurrent._b4xpages._isinitialized /*boolean*/ (processBA) && mostCurrent._b4xpages._getmanager /*com.burma.royal2d.b4xpagesmanager*/ (processBA)._isforeground /*boolean*/ ) { 
 //BA.debugLineNum = 21;BA.debugLine="Log(\"App is in the foreground. In iOS a notifica";
anywheresoftware.b4a.keywords.Common.LogImpl("235651587","App is in the foreground. In iOS a notification will not appear while the app is in the foreground (unless UserNotificationCenter is used).",0);
 };
 //BA.debugLineNum = 23;BA.debugLine="Dim n2 As Notification";
_n2 = new anywheresoftware.b4a.objects.NotificationWrapper();
 //BA.debugLineNum = 24;BA.debugLine="n2.Initialize2(n2.IMPORTANCE_HIGH)";
_n2.Initialize2(_n2.IMPORTANCE_HIGH);
 //BA.debugLineNum = 25;BA.debugLine="n2.Icon = \"icon\"";
_n2.setIcon("icon");
 //BA.debugLineNum = 26;BA.debugLine="n2.SetInfo2(Message.GetData.Get(\"title\"), Message";
_n2.SetInfo2New(processBA,BA.ObjectToCharSequence(_message.GetData().Get((Object)("title"))),BA.ObjectToCharSequence(_message.GetData().Get((Object)("body"))),BA.ObjectToCharSequence("burma"),(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 27;BA.debugLine="n2.Notify(1)";
_n2.Notify((int) (1));
 //BA.debugLineNum = 28;BA.debugLine="End Sub";
return "";
}
public static String  _fm_tokenrefresh(String _token) throws Exception{
 //BA.debugLineNum = 30;BA.debugLine="Sub fm_TokenRefresh (Token As String)";
 //BA.debugLineNum = 31;BA.debugLine="Log(\"TokenRefresh: \" & Token)";
anywheresoftware.b4a.keywords.Common.LogImpl("235717121","TokenRefresh: "+_token,0);
 //BA.debugLineNum = 32;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 1;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 2;BA.debugLine="Private fm As FirebaseMessaging";
_fm = new anywheresoftware.b4a.objects.FirebaseNotificationsService.FirebaseMessageWrapper();
 //BA.debugLineNum = 3;BA.debugLine="End Sub";
return "";
}
public static String  _receiver_receive(boolean _firsttime,anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Private Sub Receiver_Receive (FirstTime As Boolean";
 //BA.debugLineNum = 6;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 7;BA.debugLine="fm.Initialize(\"fm\")";
_fm.Initialize(processBA,"fm");
 };
 //BA.debugLineNum = 9;BA.debugLine="fm.HandleIntent(StartingIntent)";
_fm.HandleIntent((android.content.Intent)(_startingintent.getObject()));
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _subscribetotopics(Object[] _topics) throws Exception{
String _topic = "";
 //BA.debugLineNum = 12;BA.debugLine="Public Sub SubscribeToTopics (Topics() As Object)";
 //BA.debugLineNum = 13;BA.debugLine="For Each topic As String In Topics";
{
final Object[] group1 = _topics;
final int groupLen1 = group1.length
;int index1 = 0;
;
for (; index1 < groupLen1;index1++){
_topic = BA.ObjectToString(group1[index1]);
 //BA.debugLineNum = 14;BA.debugLine="fm.SubscribeToTopic(topic)";
_fm.SubscribeToTopic(_topic);
 }
};
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return "";
}
}
