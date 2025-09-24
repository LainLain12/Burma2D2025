package com.burma.royal2d;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class starter extends android.app.Service{
	public static class starter_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
            BA.LogInfo("** Receiver (starter) OnReceive **");
			android.content.Intent in = new android.content.Intent(context, starter.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
            ServiceHelper.StarterHelper.startServiceFromReceiver (context, in, true, BA.class);
		}

	}
    static starter mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return starter.class;
	}
	@Override
	public void onCreate() {
        super.onCreate();
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "com.burma.royal2d", "com.burma.royal2d.starter");
            if (BA.isShellModeRuntimeCheck(processBA)) {
                processBA.raiseEvent2(null, true, "SHELL", false);
		    }
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
            ServiceHelper.init();
        }
        _service = new ServiceHelper(this);
        processBA.service = this;
        
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "com.burma.royal2d.starter", processBA, _service, anywheresoftware.b4a.keywords.Common.Density);
		}
        if (!true && ServiceHelper.StarterHelper.startFromServiceCreate(processBA, false) == false) {
				
		}
		else {
            processBA.setActivityPaused(false);
            BA.LogInfo("*** Service (starter) Create ***");
            processBA.raiseEvent(null, "service_create");
        }
        processBA.runHook("oncreate", this, null);
        if (true) {
			if (ServiceHelper.StarterHelper.runWaitForLayouts() == false) {
                BA.LogInfo("stopping spontaneous created service");
                stopSelf();
            }
		}
    }
		@Override
	public void onStart(android.content.Intent intent, int startId) {
		onStartCommand(intent, 0, 0);
    }
    @Override
    public int onStartCommand(final android.content.Intent intent, int flags, int startId) {
    	if (ServiceHelper.StarterHelper.onStartCommand(processBA, new Runnable() {
            public void run() {
                handleStart(intent);
            }}))
			;
		else {
			ServiceHelper.StarterHelper.addWaitForLayout (new Runnable() {
				public void run() {
                    processBA.setActivityPaused(false);
                    BA.LogInfo("** Service (starter) Create **");
                    processBA.raiseEvent(null, "service_create");
					handleStart(intent);
                    ServiceHelper.StarterHelper.removeWaitForLayout();
				}
			});
		}
        processBA.runHook("onstartcommand", this, new Object[] {intent, flags, startId});
		return android.app.Service.START_NOT_STICKY;
    }
    public void onTaskRemoved(android.content.Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if (true)
            processBA.raiseEvent(null, "service_taskremoved");
            
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (starter) Start **");
    	java.lang.reflect.Method startEvent = processBA.htSubs.get("service_start");
    	if (startEvent != null) {
    		if (startEvent.getParameterTypes().length > 0) {
    			anywheresoftware.b4a.objects.IntentWrapper iw = ServiceHelper.StarterHelper.handleStartIntent(intent, _service, processBA);
    			processBA.raiseEvent(null, "service_start", iw);
    		}
    		else {
    			processBA.raiseEvent(null, "service_start");
    		}
    	}
    }

	public void onTimeout(int startId) {
        BA.LogInfo("** Service (starter) Timeout **");
        anywheresoftware.b4a.objects.collections.Map params = new anywheresoftware.b4a.objects.collections.Map();
        params.Initialize();
        params.Put("StartId", startId);
        processBA.raiseEvent(null, "service_timeout", params);
            
    }
	@Override
	public void onDestroy() {
        super.onDestroy();
        if (true) {
            BA.LogInfo("** Service (starter) Destroy (ignored)**");
        }
        else {
            BA.LogInfo("** Service (starter) Destroy **");
		    processBA.raiseEvent(null, "service_destroy");
            processBA.service = null;
		    mostCurrent = null;
		    processBA.setActivityPaused(true);
            processBA.runHook("ondestroy", this, null);
        }
	}

@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.FirebaseAnalyticsWrapper _fbi = null;
public static com.burma.royal2d.httpjob _downloader = null;
public static String _infodata = "";
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
public com.burma.royal2d.store _store = null;
public com.burma.royal2d.threed _threed = null;
public com.burma.royal2d.gift_activity _gift_activity = null;
public com.burma.royal2d.b4xpages _b4xpages = null;
public com.burma.royal2d.b4xcollections _b4xcollections = null;
public com.burma.royal2d.httputils2service _httputils2service = null;
public static boolean  _application_error(anywheresoftware.b4a.objects.B4AException _error,String _stacktrace) throws Exception{
 //BA.debugLineNum = 33;BA.debugLine="Sub Application_Error (Error As Exception, StackTr";
 //BA.debugLineNum = 34;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 35;BA.debugLine="End Sub";
return false;
}
public static String  _jobdone(com.burma.royal2d.httpjob _job) throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _out = null;
anywheresoftware.b4a.objects.collections.JSONParser _json = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 41;BA.debugLine="Sub JobDone (job As HttpJob)";
 //BA.debugLineNum = 43;BA.debugLine="Try";
try { //BA.debugLineNum = 44;BA.debugLine="If job.Success Then";
if (_job._success /*boolean*/ ) { 
 //BA.debugLineNum = 45;BA.debugLine="Select job.JobName";
switch (BA.switchObjectToInt(_job._jobname /*String*/ ,"lottohis",mostCurrent._lottosociety._lottogetter /*String*/ ,"saveimage",mostCurrent._future_tips._papergetters /*String*/ ,mostCurrent._history._historyloader /*String*/ ,mostCurrent._threed._threedloader /*String*/ ,mostCurrent._gift_activity._giftdataloader /*String*/ ,mostCurrent._gift_imageview._imageloader /*String*/ ,mostCurrent._report_details._reportjob /*String*/ ,mostCurrent._public_chat._sendsms /*String*/ )) {
case 0: {
 //BA.debugLineNum = 47;BA.debugLine="ApiCall.lottohis = job.GetString";
mostCurrent._apicall._lottohis /*String*/  = _job._getstring /*String*/ ();
 //BA.debugLineNum = 48;BA.debugLine="If LottoHistory .isCall = True Then";
if (mostCurrent._lottohistory._iscall /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 49;BA.debugLine="CallSubDelayed(LottoHistory,\"lottohisuccess\")";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(mostCurrent._lottohistory.getObject()),"lottohisuccess");
 };
 break; }
case 1: {
 //BA.debugLineNum = 53;BA.debugLine="ApiCall.lottodata=job.GetString";
mostCurrent._apicall._lottodata /*String*/  = _job._getstring /*String*/ ();
 //BA.debugLineNum = 54;BA.debugLine="If lottosociety.isCall =True Then";
if (mostCurrent._lottosociety._iscall /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 55;BA.debugLine="CallSubDelayed(lottosociety,\"getlottosuccess";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(mostCurrent._lottosociety.getObject()),"getlottosuccess");
 };
 break; }
case 2: {
 //BA.debugLineNum = 58;BA.debugLine="mycode.AddBitmapToGallery(job.GetInputStream,";
mostCurrent._mycode._addbitmaptogallery /*String*/ (processBA,_job._getinputstream /*anywheresoftware.b4a.objects.streams.File.InputStreamWrapper*/ (),BA.NumberToString(anywheresoftware.b4a.keywords.Common.DateTime.getNow())+".png","image/png");
 break; }
case 3: {
 //BA.debugLineNum = 60;BA.debugLine="mycode.paperdata= job.GetString";
mostCurrent._mycode._paperdata /*String*/  = _job._getstring /*String*/ ();
 //BA.debugLineNum = 61;BA.debugLine="If Future_Tips.isCall = True Then";
if (mostCurrent._future_tips._iscall /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 62;BA.debugLine="CallSubDelayed(Future_Tips,\"getpapersuccess\")";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(mostCurrent._future_tips.getObject()),"getpapersuccess");
 };
 break; }
case 4: {
 //BA.debugLineNum = 66;BA.debugLine="If history.hiscallable=True Then";
if (mostCurrent._history._hiscallable /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 68;BA.debugLine="CallSubDelayed2(history,\"addview11\",job.GetSt";
anywheresoftware.b4a.keywords.Common.CallSubDelayed2(processBA,(Object)(mostCurrent._history.getObject()),"addview11",(Object)(_job._getstring /*String*/ ()));
 };
 break; }
case 5: {
 //BA.debugLineNum = 73;BA.debugLine="If threed.IsCall = True Then";
if (mostCurrent._threed._iscall /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 74;BA.debugLine="CallSubDelayed2(threed,\"addview\",job.GetStrin";
anywheresoftware.b4a.keywords.Common.CallSubDelayed2(processBA,(Object)(mostCurrent._threed.getObject()),"addview",(Object)(_job._getstring /*String*/ ()));
 };
 break; }
case 6: {
 //BA.debugLineNum = 78;BA.debugLine="If gift_activity.isCall = True Then";
if (mostCurrent._gift_activity._iscall /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 79;BA.debugLine="CallSubDelayed2(gift_activity,\"giftsuccess\",j";
anywheresoftware.b4a.keywords.Common.CallSubDelayed2(processBA,(Object)(mostCurrent._gift_activity.getObject()),"giftsuccess",(Object)(_job._getstring /*String*/ ()));
 };
 break; }
case 7: {
 //BA.debugLineNum = 82;BA.debugLine="Dim bmp As Bitmap = job.GetBitmap";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
_bmp = _job._getbitmap /*anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper*/ ();
 //BA.debugLineNum = 83;BA.debugLine="Dim out As OutputStream";
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
 //BA.debugLineNum = 84;BA.debugLine="out = File.OpenOutput(File.DirInternal, gift_";
_out = anywheresoftware.b4a.keywords.Common.File.OpenOutput(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),BA.ObjectToString(mostCurrent._gift_imageview._data /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)("img_id")))+".png",anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 85;BA.debugLine="bmp.WriteToStream(out, 100, \"PNG\") ' 100 = qu";
_bmp.WriteToStream((java.io.OutputStream)(_out.getObject()),(int) (100),BA.getEnumFromString(android.graphics.Bitmap.CompressFormat.class,"PNG"));
 //BA.debugLineNum = 86;BA.debugLine="out.Close";
_out.Close();
 //BA.debugLineNum = 87;BA.debugLine="If gift_imageview.isCall = True Then";
if (mostCurrent._gift_imageview._iscall /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 88;BA.debugLine="CallSubDelayed(gift_imageview,\"progresshide\"";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(mostCurrent._gift_imageview.getObject()),"progresshide");
 };
 break; }
case 8: {
 //BA.debugLineNum = 91;BA.debugLine="Dim json As JSONParser";
_json = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 92;BA.debugLine="json.Initialize(job.GetString)";
_json.Initialize(_job._getstring /*String*/ ());
 //BA.debugLineNum = 93;BA.debugLine="Dim m As Map = json.NextObject";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = _json.NextObject();
 //BA.debugLineNum = 94;BA.debugLine="ToastMessageShow(m.Get(\"message\"),False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(_m.Get((Object)("message"))),anywheresoftware.b4a.keywords.Common.False);
 break; }
case 9: {
 break; }
}
;
 }else {
 //BA.debugLineNum = 101;BA.debugLine="Select job.JobName";
switch (BA.switchObjectToInt(_job._jobname /*String*/ ,mostCurrent._lottosociety._lottogetter /*String*/ ,mostCurrent._future_tips._papergetters /*String*/ ,mostCurrent._threed._threedloader /*String*/ ,mostCurrent._gift_activity._giftdataloader /*String*/ ,mostCurrent._main._inforeader /*String*/ ,mostCurrent._history._historyloader /*String*/ )) {
case 0: {
 //BA.debugLineNum = 103;BA.debugLine="ApiCall.getlottodata";
mostCurrent._apicall._getlottodata /*String*/ (processBA);
 break; }
case 1: {
 break; }
case 2: {
 //BA.debugLineNum = 109;BA.debugLine="If threed.IsCall = True Then";
if (mostCurrent._threed._iscall /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 110;BA.debugLine="downloader.Initialize(threed.threedloader,Me";
_downloader._initialize /*String*/ (processBA,mostCurrent._threed._threedloader /*String*/ ,starter.getObject());
 //BA.debugLineNum = 111;BA.debugLine="downloader.Download(Main.site&\"?q= SELECT *";
_downloader._download /*String*/ (mostCurrent._main._site /*String*/ +"?q= SELECT * FROM `threed` ORDER BY date DESC;");
 };
 break; }
case 3: {
 //BA.debugLineNum = 116;BA.debugLine="If gift_activity.isCall = True Then";
if (mostCurrent._gift_activity._iscall /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 117;BA.debugLine="downloader.Initialize(gift_activity.giftdata";
_downloader._initialize /*String*/ (processBA,mostCurrent._gift_activity._giftdataloader /*String*/ ,starter.getObject());
 //BA.debugLineNum = 118;BA.debugLine="downloader.Download(Main.site&\"?q=SELECT * F";
_downloader._download /*String*/ (mostCurrent._main._site /*String*/ +"?q=SELECT * FROM `gift` WHERE 1;");
 };
 break; }
case 4: {
 //BA.debugLineNum = 122;BA.debugLine="Log(\"Info Check Fail\")";
anywheresoftware.b4a.keywords.Common.LogImpl("239452753","Info Check Fail",0);
 //BA.debugLineNum = 123;BA.debugLine="If Main.isCall = True Then";
if (mostCurrent._main._iscall /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 124;BA.debugLine="downloader.Initialize(Main.inforeader,Me)";
_downloader._initialize /*String*/ (processBA,mostCurrent._main._inforeader /*String*/ ,starter.getObject());
 //BA.debugLineNum = 125;BA.debugLine="downloader.Download(Main.site&\"info.txt\")";
_downloader._download /*String*/ (mostCurrent._main._site /*String*/ +"info.txt");
 };
 break; }
case 5: {
 //BA.debugLineNum = 128;BA.debugLine="If history.hiscallable = True Then";
if (mostCurrent._history._hiscallable /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 129;BA.debugLine="downloader.Initialize(Main.livereader,Me)";
_downloader._initialize /*String*/ (processBA,mostCurrent._main._livereader /*String*/ ,starter.getObject());
 //BA.debugLineNum = 130;BA.debugLine="downloader.Download(Main.site&\"?q=SELECT * F";
_downloader._download /*String*/ (mostCurrent._main._site /*String*/ +"?q=SELECT * FROM `dailyresults` ORDER BY date DESC;");
 };
 break; }
}
;
 };
 } 
       catch (Exception e78) {
			processBA.setLastException(e78); //BA.debugLineNum = 136;BA.debugLine="Select job.JobName";
switch (BA.switchObjectToInt(_job._jobname /*String*/ ,mostCurrent._main._inforeader /*String*/ )) {
case 0: {
 //BA.debugLineNum = 138;BA.debugLine="Log(\"Info Check Fail\")";
anywheresoftware.b4a.keywords.Common.LogImpl("239452769","Info Check Fail",0);
 //BA.debugLineNum = 139;BA.debugLine="If Main.isCall = True Then";
if (mostCurrent._main._iscall /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 140;BA.debugLine="downloader.Initialize(Main.inforeader,Me)";
_downloader._initialize /*String*/ (processBA,mostCurrent._main._inforeader /*String*/ ,starter.getObject());
 //BA.debugLineNum = 141;BA.debugLine="downloader.Download(Main.site&\"info.txt\")";
_downloader._download /*String*/ (mostCurrent._main._site /*String*/ +"info.txt");
 };
 break; }
}
;
 //BA.debugLineNum = 145;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("239452776",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(processBA)),0);
 };
 //BA.debugLineNum = 147;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Dim fbi As FirebaseAnalytics";
_fbi = new anywheresoftware.b4a.objects.FirebaseAnalyticsWrapper();
 //BA.debugLineNum = 9;BA.debugLine="Dim downloader As HttpJob";
_downloader = new com.burma.royal2d.httpjob();
 //BA.debugLineNum = 10;BA.debugLine="Dim infodata As String";
_infodata = "";
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 13;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 14;BA.debugLine="fbi.Initialize";
_fbi.Initialize();
 //BA.debugLineNum = 15;BA.debugLine="fbi.SendEvent(\"login\", CreateMap (\"additional par";
_fbi.SendEvent("login",anywheresoftware.b4a.keywords.Common.createMap(new Object[] {(Object)("additional parameter"),(Object)(100)}));
 //BA.debugLineNum = 16;BA.debugLine="CallSubDelayed2(FirebaseMessaging, \"SubscribeToTo";
anywheresoftware.b4a.keywords.Common.CallSubDelayed2(processBA,(Object)(mostCurrent._firebasemessaging.getObject()),"SubscribeToTopics",(Object)(new Object[]{(Object)("general")}));
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 37;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 39;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
anywheresoftware.b4a.objects.FirebaseAuthWrapper _auth = null;
 //BA.debugLineNum = 20;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 21;BA.debugLine="If File.Exists(File.DirInternal,\"user\") =False Th";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"user")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 22;BA.debugLine="Dim auth As FirebaseAuth";
_auth = new anywheresoftware.b4a.objects.FirebaseAuthWrapper();
 //BA.debugLineNum = 23;BA.debugLine="auth.Initialize(\"auth\")";
_auth.Initialize(processBA,"auth");
 //BA.debugLineNum = 24;BA.debugLine="auth.SignOutFromGoogle";
_auth.SignOutFromGoogle();
 };
 //BA.debugLineNum = 26;BA.debugLine="End Sub";
return "";
}
public static String  _service_taskremoved() throws Exception{
 //BA.debugLineNum = 28;BA.debugLine="Sub Service_TaskRemoved";
 //BA.debugLineNum = 30;BA.debugLine="End Sub";
return "";
}
}
