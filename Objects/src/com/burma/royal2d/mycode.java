package com.burma.royal2d;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class mycode {
private static mycode mostCurrent = new mycode();
public static Object getObject() {
    throw new RuntimeException("Code module does not support this method.");
}
 public anywheresoftware.b4a.keywords.Common __c = null;
public static int _checkentertime = 0;
public static anywheresoftware.b4a.objects.collections.List _futureimglist = null;
public static anywheresoftware.b4a.objects.collections.List _weeklyimglist = null;
public static anywheresoftware.b4a.objects.collections.List _calendarimglist = null;
public static int _activityheight = 0;
public static int _appbartop = 0;
public static int _appbarheight = 0;
public static int _navicolor = 0;
public static int _bgcolor = 0;
public static anywheresoftware.b4a.keywords.constants.TypefaceWrapper _mmfont = null;
public static anywheresoftware.b4a.keywords.constants.TypefaceWrapper _defaultfont = null;
public static anywheresoftware.b4a.keywords.constants.TypefaceWrapper _semibold = null;
public static anywheresoftware.b4a.keywords.constants.TypefaceWrapper _livebold = null;
public static anywheresoftware.b4a.keywords.constants.TypefaceWrapper _lightfont = null;
public static String _id = "";
public static String _profile_pic = "";
public static String _name = "";
public static String _email = "";
public static anywheresoftware.b4a.objects.collections.List _chatlist = null;
public static String _lastlive = "";
public static String _paperdata = "";
public com.burma.royal2d.main _main = null;
public com.burma.royal2d.report_details _report_details = null;
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
public static String  _addbitmaptogallery(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _in,String _targetname,String _mimetype) throws Exception{
anywheresoftware.b4a.phone.Phone _p = null;
anywheresoftware.b4j.object.JavaObject _ctxt = null;
anywheresoftware.b4a.objects.ContentResolverWrapper _cr = null;
anywheresoftware.b4a.objects.ContentResolverWrapper.ContentValuesWrapper _values = null;
anywheresoftware.b4j.object.JavaObject _mediastoreimagesmedia = null;
anywheresoftware.b4a.objects.ContentResolverWrapper.UriWrapper _external_content_uri = null;
anywheresoftware.b4j.object.JavaObject _imageuri = null;
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _out = null;
 //BA.debugLineNum = 240;BA.debugLine="Sub AddBitmapToGallery (In As InputStream, TargetN";
 //BA.debugLineNum = 241;BA.debugLine="Dim p As Phone";
_p = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 242;BA.debugLine="Dim ctxt As JavaObject";
_ctxt = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 243;BA.debugLine="ctxt.InitializeContext";
_ctxt.InitializeContext((_ba.processBA == null ? _ba : _ba.processBA));
 //BA.debugLineNum = 244;BA.debugLine="If p.SdkVersion >= 29 Then";
if (_p.getSdkVersion()>=29) { 
 //BA.debugLineNum = 245;BA.debugLine="Dim cr As ContentResolver";
_cr = new anywheresoftware.b4a.objects.ContentResolverWrapper();
 //BA.debugLineNum = 246;BA.debugLine="cr.Initialize(\"cr\")";
_cr.Initialize("cr");
 //BA.debugLineNum = 247;BA.debugLine="Dim values As ContentValues";
_values = new anywheresoftware.b4a.objects.ContentResolverWrapper.ContentValuesWrapper();
 //BA.debugLineNum = 248;BA.debugLine="values.Initialize";
_values.Initialize();
 //BA.debugLineNum = 249;BA.debugLine="values.PutString(\"_display_name\", TargetName)";
_values.PutString("_display_name",_targetname);
 //BA.debugLineNum = 250;BA.debugLine="values.PutString(\"mime_type\", MimeType)";
_values.PutString("mime_type",_mimetype);
 //BA.debugLineNum = 251;BA.debugLine="values.PutString(\"relative_path\", \"Pictures/Burm";
_values.PutString("relative_path","Pictures/Burma2D/");
 //BA.debugLineNum = 252;BA.debugLine="Dim MediaStoreImagesMedia As JavaObject";
_mediastoreimagesmedia = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 253;BA.debugLine="MediaStoreImagesMedia.InitializeStatic(\"android.";
_mediastoreimagesmedia.InitializeStatic("android.provider.MediaStore.Images$Media");
 //BA.debugLineNum = 254;BA.debugLine="Dim EXTERNAL_CONTENT_URI As Uri = MediaStoreImag";
_external_content_uri = new anywheresoftware.b4a.objects.ContentResolverWrapper.UriWrapper();
_external_content_uri = (anywheresoftware.b4a.objects.ContentResolverWrapper.UriWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ContentResolverWrapper.UriWrapper(), (android.net.Uri)(_mediastoreimagesmedia.GetField("EXTERNAL_CONTENT_URI")));
 //BA.debugLineNum = 255;BA.debugLine="cr.Delete(EXTERNAL_CONTENT_URI, \"_display_name =";
_cr.Delete((android.net.Uri)(_external_content_uri.getObject()),"_display_name = ?",new String[]{_targetname});
 //BA.debugLineNum = 256;BA.debugLine="Dim imageuri As JavaObject = cr.Insert(EXTERNAL_";
_imageuri = new anywheresoftware.b4j.object.JavaObject();
_imageuri = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_cr.Insert(_external_content_uri,(android.content.ContentValues)(_values.getObject())).getObject()));
 //BA.debugLineNum = 257;BA.debugLine="Dim out As OutputStream = ctxt.RunMethodJO(\"getC";
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
_out = (anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper(), (java.io.OutputStream)(_ctxt.RunMethodJO("getContentResolver",(Object[])(anywheresoftware.b4a.keywords.Common.Null)).RunMethod("openOutputStream",new Object[]{(Object)(_imageuri.getObject())})));
 //BA.debugLineNum = 258;BA.debugLine="File.Copy2(In, out)";
anywheresoftware.b4a.keywords.Common.File.Copy2((java.io.InputStream)(_in.getObject()),(java.io.OutputStream)(_out.getObject()));
 //BA.debugLineNum = 259;BA.debugLine="out.Close";
_out.Close();
 //BA.debugLineNum = 260;BA.debugLine="ToastMessageShow(\"save image success\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("save image success"),anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 262;BA.debugLine="ToastMessageShow(\"You Can't Save Take ScreenShot";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("You Can't Save Take ScreenShot"),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 264;BA.debugLine="End Sub";
return "";
}
public static String  _addbitmaptogallery1(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.B4XViewWrapper.B4XBitmapWrapper _in,String _targetname,String _mimetype) throws Exception{
anywheresoftware.b4a.phone.Phone _p = null;
anywheresoftware.b4j.object.JavaObject _ctxt = null;
anywheresoftware.b4a.objects.ContentResolverWrapper _cr = null;
anywheresoftware.b4a.objects.ContentResolverWrapper.ContentValuesWrapper _values = null;
anywheresoftware.b4j.object.JavaObject _mediastoreimagesmedia = null;
anywheresoftware.b4a.objects.ContentResolverWrapper.UriWrapper _external_content_uri = null;
anywheresoftware.b4j.object.JavaObject _imageuri = null;
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _out = null;
 //BA.debugLineNum = 266;BA.debugLine="Sub AddBitmapToGallery1(In As B4XBitmap, TargetNam";
 //BA.debugLineNum = 267;BA.debugLine="Dim p As Phone";
_p = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 268;BA.debugLine="Dim ctxt As JavaObject";
_ctxt = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 269;BA.debugLine="ctxt.InitializeContext";
_ctxt.InitializeContext((_ba.processBA == null ? _ba : _ba.processBA));
 //BA.debugLineNum = 270;BA.debugLine="If p.SdkVersion >= 29 Then";
if (_p.getSdkVersion()>=29) { 
 //BA.debugLineNum = 271;BA.debugLine="Dim cr As ContentResolver";
_cr = new anywheresoftware.b4a.objects.ContentResolverWrapper();
 //BA.debugLineNum = 272;BA.debugLine="cr.Initialize(\"cr\")";
_cr.Initialize("cr");
 //BA.debugLineNum = 273;BA.debugLine="Dim values As ContentValues";
_values = new anywheresoftware.b4a.objects.ContentResolverWrapper.ContentValuesWrapper();
 //BA.debugLineNum = 274;BA.debugLine="values.Initialize";
_values.Initialize();
 //BA.debugLineNum = 275;BA.debugLine="values.PutString(\"_display_name\", TargetName)";
_values.PutString("_display_name",_targetname);
 //BA.debugLineNum = 276;BA.debugLine="values.PutString(\"mime_type\", MimeType)";
_values.PutString("mime_type",_mimetype);
 //BA.debugLineNum = 277;BA.debugLine="values.PutString(\"relative_path\", \"Pictures/Burm";
_values.PutString("relative_path","Pictures/Burma2D/");
 //BA.debugLineNum = 278;BA.debugLine="Dim MediaStoreImagesMedia As JavaObject";
_mediastoreimagesmedia = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 279;BA.debugLine="MediaStoreImagesMedia.InitializeStatic(\"android.";
_mediastoreimagesmedia.InitializeStatic("android.provider.MediaStore.Images$Media");
 //BA.debugLineNum = 280;BA.debugLine="Dim EXTERNAL_CONTENT_URI As Uri = MediaStoreImag";
_external_content_uri = new anywheresoftware.b4a.objects.ContentResolverWrapper.UriWrapper();
_external_content_uri = (anywheresoftware.b4a.objects.ContentResolverWrapper.UriWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ContentResolverWrapper.UriWrapper(), (android.net.Uri)(_mediastoreimagesmedia.GetField("EXTERNAL_CONTENT_URI")));
 //BA.debugLineNum = 281;BA.debugLine="cr.Delete(EXTERNAL_CONTENT_URI, \"_display_name =";
_cr.Delete((android.net.Uri)(_external_content_uri.getObject()),"_display_name = ?",new String[]{_targetname});
 //BA.debugLineNum = 282;BA.debugLine="Dim imageuri As JavaObject = cr.Insert(EXTERNAL_";
_imageuri = new anywheresoftware.b4j.object.JavaObject();
_imageuri = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_cr.Insert(_external_content_uri,(android.content.ContentValues)(_values.getObject())).getObject()));
 //BA.debugLineNum = 283;BA.debugLine="Dim out As OutputStream = ctxt.RunMethodJO(\"getC";
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
_out = (anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper(), (java.io.OutputStream)(_ctxt.RunMethodJO("getContentResolver",(Object[])(anywheresoftware.b4a.keywords.Common.Null)).RunMethod("openOutputStream",new Object[]{(Object)(_imageuri.getObject())})));
 //BA.debugLineNum = 284;BA.debugLine="In.WriteToStream(out,100,\"PNG\")";
_in.WriteToStream((java.io.OutputStream)(_out.getObject()),(int) (100),BA.getEnumFromString(android.graphics.Bitmap.CompressFormat.class,"PNG"));
 //BA.debugLineNum = 285;BA.debugLine="out.Close";
_out.Close();
 //BA.debugLineNum = 286;BA.debugLine="ToastMessageShow(\"save image success\",False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("save image success"),anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 288;BA.debugLine="ToastMessageShow(\"You Can't Save Take ScreenShot";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("You Can't Save Take ScreenShot"),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 290;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.PanelWrapper  _appbar(anywheresoftware.b4a.BA _ba,String _title,boolean _ismain) throws Exception{
anywheresoftware.b4a.objects.PanelWrapper _p = null;
anywheresoftware.b4a.objects.ButtonWrapper _backbtn = null;
anywheresoftware.b4a.objects.LabelWrapper _tlb = null;
anywheresoftware.b4a.objects.CSBuilder _cs = null;
anywheresoftware.b4a.objects.StringUtils _su = null;
 //BA.debugLineNum = 171;BA.debugLine="Sub appbar(title As String,isMain As Boolean) As P";
 //BA.debugLineNum = 172;BA.debugLine="Dim p As Panel";
_p = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 173;BA.debugLine="p.Initialize(\"\")";
_p.Initialize(_ba,"");
 //BA.debugLineNum = 174;BA.debugLine="p.Color= naviColor";
_p.setColor(_navicolor);
 //BA.debugLineNum = 175;BA.debugLine="Dim backbtn As Button";
_backbtn = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 176;BA.debugLine="backbtn.Initialize(\"backbtn\")";
_backbtn.Initialize(_ba,"backbtn");
 //BA.debugLineNum = 178;BA.debugLine="backbtn.Background = btnbg(False)";
_backbtn.setBackground((android.graphics.drawable.Drawable)(_btnbg(_ba,anywheresoftware.b4a.keywords.Common.False).getObject()));
 //BA.debugLineNum = 179;BA.debugLine="backbtn.TextColor=Colors.White";
_backbtn.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 180;BA.debugLine="backbtn.Gravity=Gravity.CENTER";
_backbtn.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 181;BA.debugLine="backbtn.Typeface= Typeface.FONTAWESOME";
_backbtn.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME());
 //BA.debugLineNum = 182;BA.debugLine="backbtn.Text=Chr(0xF060)";
_backbtn.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf060))));
 //BA.debugLineNum = 183;BA.debugLine="Dim tlb As Label";
_tlb = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 184;BA.debugLine="tlb.Initialize(\"\")";
_tlb.Initialize(_ba,"");
 //BA.debugLineNum = 185;BA.debugLine="Dim cs As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 186;BA.debugLine="cs.Initialize.Color(Colors.White).Size(textsize(8";
_cs.Initialize().Color(anywheresoftware.b4a.keywords.Common.Colors.White).Size((int) (_textsize(_ba,(int) (8)))).Typeface((android.graphics.Typeface)(_semibold.getObject())).Append(BA.ObjectToCharSequence(_title)).PopAll();
 //BA.debugLineNum = 187;BA.debugLine="tlb.Text=cs";
_tlb.setText(BA.ObjectToCharSequence(_cs.getObject()));
 //BA.debugLineNum = 188;BA.debugLine="tlb.SingleLine=True";
_tlb.setSingleLine(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 189;BA.debugLine="tlb.Gravity=Gravity.CENTER_VERTICAL";
_tlb.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER_VERTICAL);
 //BA.debugLineNum = 190;BA.debugLine="p.AddView(backbtn,10dip,0,50dip,50dip)";
_p.AddView((android.view.View)(_backbtn.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 //BA.debugLineNum = 191;BA.debugLine="Dim su As StringUtils";
_su = new anywheresoftware.b4a.objects.StringUtils();
 //BA.debugLineNum = 193;BA.debugLine="If isMain =True Then";
if (_ismain==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 194;BA.debugLine="backbtn.Visible=False";
_backbtn.setVisible(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 195;BA.debugLine="p.AddView(tlb,10dip,0,100%x-(backbtn.Width+backb";
_p.AddView((android.view.View)(_tlb.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),(int) (0),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),_ba)-(_backbtn.getWidth()+_backbtn.getLeft())),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 }else {
 //BA.debugLineNum = 198;BA.debugLine="p.AddView(tlb,backbtn.Width+backbtn.Left,0,100%x";
_p.AddView((android.view.View)(_tlb.getObject()),(int) (_backbtn.getWidth()+_backbtn.getLeft()),(int) (0),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),_ba)-(_backbtn.getWidth()+_backbtn.getLeft())),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50)));
 };
 //BA.debugLineNum = 202;BA.debugLine="If su.MeasureMultilineTextHeight(tlb,cs)> 50dip T";
if (_su.MeasureMultilineTextHeight((android.widget.TextView)(_tlb.getObject()),BA.ObjectToCharSequence(_cs.getObject()))>anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50))) { 
 //BA.debugLineNum = 203;BA.debugLine="appbarheight=tlb.Height";
_appbarheight = _tlb.getHeight();
 //BA.debugLineNum = 204;BA.debugLine="backbtn.Width=tlb.Height";
_backbtn.setWidth(_tlb.getHeight());
 //BA.debugLineNum = 205;BA.debugLine="backbtn.Height=tlb.Height";
_backbtn.setHeight(_tlb.getHeight());
 }else {
 //BA.debugLineNum = 207;BA.debugLine="appbarheight=50dip";
_appbarheight = anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (50));
 };
 //BA.debugLineNum = 210;BA.debugLine="Return p";
if (true) return _p;
 //BA.debugLineNum = 211;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.StateListDrawable  _btnbg(anywheresoftware.b4a.BA _ba,boolean _isgift) throws Exception{
anywheresoftware.b4a.objects.drawable.StateListDrawable _std = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _defaultdb = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _pressdb = null;
 //BA.debugLineNum = 115;BA.debugLine="Sub btnbg(isGift As Boolean) As StateListDrawable";
 //BA.debugLineNum = 116;BA.debugLine="Dim std As StateListDrawable";
_std = new anywheresoftware.b4a.objects.drawable.StateListDrawable();
 //BA.debugLineNum = 117;BA.debugLine="std.Initialize";
_std.Initialize();
 //BA.debugLineNum = 118;BA.debugLine="Dim defaultdb As ColorDrawable";
_defaultdb = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 119;BA.debugLine="Dim pressdb As ColorDrawable";
_pressdb = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 121;BA.debugLine="If isGift = True Then";
if (_isgift==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 122;BA.debugLine="defaultdb.Initialize(0xE1241A32,180)";
_defaultdb.Initialize(((int)0xe1241a32),(int) (180));
 }else {
 //BA.debugLineNum = 124;BA.debugLine="defaultdb.Initialize(Colors.Transparent,0)";
_defaultdb.Initialize(anywheresoftware.b4a.keywords.Common.Colors.Transparent,(int) (0));
 };
 //BA.debugLineNum = 126;BA.debugLine="pressdb.Initialize(0x9A466584,180)";
_pressdb.Initialize(((int)0x9a466584),(int) (180));
 //BA.debugLineNum = 127;BA.debugLine="std.AddState(std.State_Pressed,pressdb)";
_std.AddState(_std.State_Pressed,(android.graphics.drawable.Drawable)(_pressdb.getObject()));
 //BA.debugLineNum = 128;BA.debugLine="std.AddCatchAllState(defaultdb)";
_std.AddCatchAllState((android.graphics.drawable.Drawable)(_defaultdb.getObject()));
 //BA.debugLineNum = 129;BA.debugLine="Return std";
if (true) return _std;
 //BA.debugLineNum = 130;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.StateListDrawable  _btnbg2(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.drawable.StateListDrawable _std = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _defaultdb = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _pressdb = null;
 //BA.debugLineNum = 226;BA.debugLine="Sub btnbg2 As StateListDrawable";
 //BA.debugLineNum = 227;BA.debugLine="Dim std As StateListDrawable";
_std = new anywheresoftware.b4a.objects.drawable.StateListDrawable();
 //BA.debugLineNum = 228;BA.debugLine="std.Initialize";
_std.Initialize();
 //BA.debugLineNum = 229;BA.debugLine="Dim defaultdb As ColorDrawable";
_defaultdb = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 230;BA.debugLine="Dim pressdb As ColorDrawable";
_pressdb = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 231;BA.debugLine="defaultdb.Initialize(naviColor,10dip)";
_defaultdb.Initialize(_navicolor,anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 232;BA.debugLine="pressdb.Initialize(0x9A466584,10dip)";
_pressdb.Initialize(((int)0x9a466584),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)));
 //BA.debugLineNum = 233;BA.debugLine="std.AddState(std.State_Pressed,pressdb)";
_std.AddState(_std.State_Pressed,(android.graphics.drawable.Drawable)(_pressdb.getObject()));
 //BA.debugLineNum = 234;BA.debugLine="std.AddCatchAllState(defaultdb)";
_std.AddCatchAllState((android.graphics.drawable.Drawable)(_defaultdb.getObject()));
 //BA.debugLineNum = 235;BA.debugLine="Return std";
if (true) return _std;
 //BA.debugLineNum = 236;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.drawable.StateListDrawable  _btnbgdynamic(anywheresoftware.b4a.BA _ba,int _dc,int _pc,int _rd) throws Exception{
anywheresoftware.b4a.objects.drawable.StateListDrawable _std = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _defaultdb = null;
anywheresoftware.b4a.objects.drawable.ColorDrawable _pressdb = null;
 //BA.debugLineNum = 213;BA.debugLine="Sub btnbgdynamic(dc As Int ,pc As Int,rd As Int) A";
 //BA.debugLineNum = 214;BA.debugLine="Dim std As StateListDrawable";
_std = new anywheresoftware.b4a.objects.drawable.StateListDrawable();
 //BA.debugLineNum = 215;BA.debugLine="std.Initialize";
_std.Initialize();
 //BA.debugLineNum = 216;BA.debugLine="Dim defaultdb As ColorDrawable";
_defaultdb = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 217;BA.debugLine="Dim pressdb As ColorDrawable";
_pressdb = new anywheresoftware.b4a.objects.drawable.ColorDrawable();
 //BA.debugLineNum = 218;BA.debugLine="defaultdb.Initialize(dc,rd)";
_defaultdb.Initialize(_dc,_rd);
 //BA.debugLineNum = 219;BA.debugLine="pressdb.Initialize(pc,rd)";
_pressdb.Initialize(_pc,_rd);
 //BA.debugLineNum = 220;BA.debugLine="std.AddState(std.State_Pressed,pressdb)";
_std.AddState(_std.State_Pressed,(android.graphics.drawable.Drawable)(_pressdb.getObject()));
 //BA.debugLineNum = 221;BA.debugLine="std.AddCatchAllState(defaultdb)";
_std.AddCatchAllState((android.graphics.drawable.Drawable)(_defaultdb.getObject()));
 //BA.debugLineNum = 222;BA.debugLine="Return std";
if (true) return _std;
 //BA.debugLineNum = 223;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.CSBuilder  _buttoncsb(anywheresoftware.b4a.BA _ba,boolean _home,String _text,boolean _iconic) throws Exception{
anywheresoftware.b4a.objects.CSBuilder _csb = null;
 //BA.debugLineNum = 50;BA.debugLine="Sub buttoncsb(home As Boolean,text As String,iconi";
 //BA.debugLineNum = 51;BA.debugLine="Dim csb As CSBuilder";
_csb = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 52;BA.debugLine="csb.Initialize";
_csb.Initialize();
 //BA.debugLineNum = 53;BA.debugLine="If home = True Then";
if (_home==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 55;BA.debugLine="csb.Color(Colors.Green).Typeface(Typeface.DEFAUL";
_csb.Color(anywheresoftware.b4a.keywords.Common.Colors.Green).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD).Size((int) (20)).Append(BA.ObjectToCharSequence(_text)).PopAll();
 }else {
 //BA.debugLineNum = 57;BA.debugLine="If iconic = True Then";
if (_iconic==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 58;BA.debugLine="If text  = Chr(0xF06B) Then";
if ((_text).equals(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf06b))))) { 
 //BA.debugLineNum = 59;BA.debugLine="csb.Color(0xFFDBDBDB).Typeface(Typeface.FONTAW";
_csb.Color(((int)0xffdbdbdb)).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME()).Size((int) (35)).Append(BA.ObjectToCharSequence(_text)).PopAll();
 }else {
 //BA.debugLineNum = 61;BA.debugLine="csb.Color(0xFFDBDBDB).Typeface(Typeface.FONTAW";
_csb.Color(((int)0xffdbdbdb)).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME()).Size((int) (20)).Append(BA.ObjectToCharSequence(_text)).PopAll();
 };
 }else {
 //BA.debugLineNum = 66;BA.debugLine="csb.Color(0xFFDBDBDB).Typeface(Typeface.DEFAULT";
_csb.Color(((int)0xffdbdbdb)).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD).Size((int) (20)).Append(BA.ObjectToCharSequence(_text)).PopAll();
 };
 };
 //BA.debugLineNum = 71;BA.debugLine="Return csb";
if (true) return _csb;
 //BA.debugLineNum = 72;BA.debugLine="End Sub";
return null;
}
public static String  _chatjson(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _json = null;
anywheresoftware.b4a.objects.collections.List _ls = null;
 //BA.debugLineNum = 401;BA.debugLine="Sub chatjson As String";
 //BA.debugLineNum = 402;BA.debugLine="Dim json As JSONGenerator";
_json = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
 //BA.debugLineNum = 403;BA.debugLine="If chatlist.IsInitialized And chatlist.Size >0 Th";
if (_chatlist.IsInitialized() && _chatlist.getSize()>0) { 
 //BA.debugLineNum = 404;BA.debugLine="json.Initialize2(chatlist)";
_json.Initialize2(_chatlist);
 }else {
 //BA.debugLineNum = 406;BA.debugLine="Dim ls As List";
_ls = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 407;BA.debugLine="ls.Initialize";
_ls.Initialize();
 //BA.debugLineNum = 408;BA.debugLine="json.Initialize2(ls)";
_json.Initialize2(_ls);
 };
 //BA.debugLineNum = 411;BA.debugLine="Return json.ToString";
if (true) return _json.ToString();
 //BA.debugLineNum = 412;BA.debugLine="End Sub";
return "";
}
public static String  _checkactivityheihgt(anywheresoftware.b4a.BA _ba,int _h) throws Exception{
anywheresoftware.b4a.phone.Phone _phone = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 31;BA.debugLine="Sub checkActivityHeihgt(h As Int)";
 //BA.debugLineNum = 32;BA.debugLine="Dim phone As Phone";
_phone = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 33;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 34;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 41;BA.debugLine="m.Put(\"activityheight\",h)";
_m.Put((Object)("activityheight"),(Object)(_h));
 //BA.debugLineNum = 42;BA.debugLine="m.Put(\"appbartop\",0)";
_m.Put((Object)("appbartop"),(Object)(0));
 //BA.debugLineNum = 43;BA.debugLine="ActivityHeight = h";
_activityheight = _h;
 //BA.debugLineNum = 44;BA.debugLine="appbartop=0";
_appbartop = (int) (0);
 //BA.debugLineNum = 46;BA.debugLine="File.WriteMap(File.DirInternal,\"heighttop\",m)";
anywheresoftware.b4a.keywords.Common.File.WriteMap(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"heighttop",_m);
 //BA.debugLineNum = 47;BA.debugLine="End Sub";
return "";
}
public static String  _createnotificationchannel(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4j.object.JavaObject _ctxt = null;
anywheresoftware.b4j.object.JavaObject _channel = null;
anywheresoftware.b4j.object.JavaObject _sounduri = null;
anywheresoftware.b4j.object.JavaObject _attrbuilder = null;
anywheresoftware.b4j.object.JavaObject _audioattr = null;
anywheresoftware.b4j.object.JavaObject _manager = null;
 //BA.debugLineNum = 88;BA.debugLine="Sub CreateNotificationChannel";
 //BA.debugLineNum = 89;BA.debugLine="Dim ctxt As JavaObject";
_ctxt = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 90;BA.debugLine="ctxt.InitializeContext";
_ctxt.InitializeContext((_ba.processBA == null ? _ba : _ba.processBA));
 //BA.debugLineNum = 91;BA.debugLine="Dim channel As JavaObject";
_channel = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 92;BA.debugLine="channel.InitializeNewInstance(\"android.app.Notifi";
_channel.InitializeNewInstance("android.app.NotificationChannel",new Object[]{(Object)("burma"),(Object)("My Channel"),(Object)(4)});
 //BA.debugLineNum = 96;BA.debugLine="Dim soundUri As JavaObject";
_sounduri = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 97;BA.debugLine="soundUri = soundUri.InitializeStatic(\"android.net";
_sounduri = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_sounduri.InitializeStatic("android.net.Uri").RunMethod("parse",new Object[]{(Object)("android.resource://"+BA.ObjectToString(_ctxt.RunMethod("getPackageName",(Object[])(anywheresoftware.b4a.keywords.Common.Null)))+"/raw/bell")})));
 //BA.debugLineNum = 101;BA.debugLine="Dim attrBuilder As JavaObject";
_attrbuilder = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 102;BA.debugLine="attrBuilder.InitializeNewInstance(\"android.media.";
_attrbuilder.InitializeNewInstance("android.media.AudioAttributes$Builder",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 103;BA.debugLine="attrBuilder.RunMethod(\"setContentType\", Array(4))";
_attrbuilder.RunMethod("setContentType",new Object[]{(Object)(4)});
 //BA.debugLineNum = 104;BA.debugLine="attrBuilder.RunMethod(\"setUsage\", Array(5)) ' USA";
_attrbuilder.RunMethod("setUsage",new Object[]{(Object)(5)});
 //BA.debugLineNum = 105;BA.debugLine="Dim audioAttr As JavaObject = attrBuilder.RunMeth";
_audioattr = new anywheresoftware.b4j.object.JavaObject();
_audioattr = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_attrbuilder.RunMethod("build",(Object[])(anywheresoftware.b4a.keywords.Common.Null))));
 //BA.debugLineNum = 108;BA.debugLine="channel.RunMethod(\"setSound\", Array(soundUri, aud";
_channel.RunMethod("setSound",new Object[]{(Object)(_sounduri.getObject()),(Object)(_audioattr.getObject())});
 //BA.debugLineNum = 111;BA.debugLine="Dim manager As JavaObject = ctxt.RunMethod(\"getSy";
_manager = new anywheresoftware.b4j.object.JavaObject();
_manager = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_ctxt.RunMethod("getSystemService",new Object[]{(Object)("notification")})));
 //BA.debugLineNum = 112;BA.debugLine="manager.RunMethod(\"createNotificationChannel\", Ar";
_manager.RunMethod("createNotificationChannel",new Object[]{(Object)(_channel.getObject())});
 //BA.debugLineNum = 113;BA.debugLine="End Sub";
return "";
}
public static int  _getstatusbarheight(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4j.object.JavaObject _ctxt = null;
anywheresoftware.b4j.object.JavaObject _resources = null;
int _resourceid = 0;
 //BA.debugLineNum = 74;BA.debugLine="Sub GetStatusBarHeight As Int";
 //BA.debugLineNum = 75;BA.debugLine="Dim ctxt As JavaObject";
_ctxt = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 76;BA.debugLine="ctxt.InitializeContext";
_ctxt.InitializeContext((_ba.processBA == null ? _ba : _ba.processBA));
 //BA.debugLineNum = 77;BA.debugLine="Dim resources As JavaObject = ctxt.RunMethod(\"get";
_resources = new anywheresoftware.b4j.object.JavaObject();
_resources = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_ctxt.RunMethod("getResources",(Object[])(anywheresoftware.b4a.keywords.Common.Null))));
 //BA.debugLineNum = 78;BA.debugLine="Dim resourceId As Int = resources.RunMethod(\"getI";
_resourceid = (int)(BA.ObjectToNumber(_resources.RunMethod("getIdentifier",new Object[]{(Object)("status_bar_height"),(Object)("dimen"),(Object)("android")})));
 //BA.debugLineNum = 79;BA.debugLine="If resourceId > 0 Then";
if (_resourceid>0) { 
 //BA.debugLineNum = 80;BA.debugLine="Return resources.RunMethod(\"getDimensionPixelSiz";
if (true) return (int)(BA.ObjectToNumber(_resources.RunMethod("getDimensionPixelSize",new Object[]{(Object)(_resourceid)})));
 }else {
 //BA.debugLineNum = 82;BA.debugLine="Return 0";
if (true) return (int) (0);
 };
 //BA.debugLineNum = 84;BA.debugLine="End Sub";
return 0;
}
public static String  _getuserid(anywheresoftware.b4a.BA _ba,String _typ) throws Exception{
String _rt = "";
anywheresoftware.b4a.objects.collections.JSONParser _json = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
 //BA.debugLineNum = 377;BA.debugLine="Sub getUserId(typ As String) As String";
 //BA.debugLineNum = 378;BA.debugLine="Dim rt As String";
_rt = "";
 //BA.debugLineNum = 379;BA.debugLine="If File.Exists(File.DirInternal,\"user\") Then";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"user")) { 
 //BA.debugLineNum = 380;BA.debugLine="Dim json As JSONParser";
_json = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 381;BA.debugLine="json.Initialize(File.ReadString(File.DirInternal";
_json.Initialize(anywheresoftware.b4a.keywords.Common.File.ReadString(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"user"));
 //BA.debugLineNum = 382;BA.debugLine="Dim m As Map = json.NextObject";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = _json.NextObject();
 //BA.debugLineNum = 383;BA.debugLine="Log(m.Get(\"id\"))";
anywheresoftware.b4a.keywords.Common.LogImpl("22490374",BA.ObjectToString(_m.Get((Object)("id"))),0);
 //BA.debugLineNum = 384;BA.debugLine="Log(m.Get(\"name\"))";
anywheresoftware.b4a.keywords.Common.LogImpl("22490375",BA.ObjectToString(_m.Get((Object)("name"))),0);
 //BA.debugLineNum = 385;BA.debugLine="Log(m.Get(\"profile_pic\"))";
anywheresoftware.b4a.keywords.Common.LogImpl("22490376",BA.ObjectToString(_m.Get((Object)("profile_pic"))),0);
 //BA.debugLineNum = 386;BA.debugLine="Select typ";
switch (BA.switchObjectToInt(_typ,_id,_name,_profile_pic,_email)) {
case 0: {
 //BA.debugLineNum = 388;BA.debugLine="rt  = m.Get(id)";
_rt = BA.ObjectToString(_m.Get((Object)(_id)));
 break; }
case 1: {
 //BA.debugLineNum = 390;BA.debugLine="rt = m.Get(name)";
_rt = BA.ObjectToString(_m.Get((Object)(_name)));
 break; }
case 2: {
 //BA.debugLineNum = 392;BA.debugLine="rt= m.Get(profile_pic)";
_rt = BA.ObjectToString(_m.Get((Object)(_profile_pic)));
 break; }
case 3: {
 //BA.debugLineNum = 394;BA.debugLine="rt = m.Get(email)";
_rt = BA.ObjectToString(_m.Get((Object)(_email)));
 break; }
}
;
 };
 //BA.debugLineNum = 397;BA.debugLine="Return rt";
if (true) return _rt;
 //BA.debugLineNum = 398;BA.debugLine="End Sub";
return "";
}
public static String  _mimain(anywheresoftware.b4a.BA _ba,String _m) throws Exception{
 //BA.debugLineNum = 315;BA.debugLine="Sub mimain(m As String) As String";
 //BA.debugLineNum = 316;BA.debugLine="m = m.SubString(1)";
_m = _m.substring((int) (1));
 //BA.debugLineNum = 317;BA.debugLine="Return m";
if (true) return _m;
 //BA.debugLineNum = 318;BA.debugLine="End Sub";
return "";
}
public static String  _misub(anywheresoftware.b4a.BA _ba,String _m) throws Exception{
 //BA.debugLineNum = 319;BA.debugLine="Sub misub(m As String) As String";
 //BA.debugLineNum = 320;BA.debugLine="m = m.SubString2(0,1)";
_m = _m.substring((int) (0),(int) (1));
 //BA.debugLineNum = 321;BA.debugLine="Return m";
if (true) return _m;
 //BA.debugLineNum = 322;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.CSBuilder  _mitimecs(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 324;BA.debugLine="Sub mitimecs As CSBuilder";
 //BA.debugLineNum = 325;BA.debugLine="Dim cs As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 326;BA.debugLine="cs.Initialize";
_cs.Initialize();
 //BA.debugLineNum = 327;BA.debugLine="cs.Color(Colors.White).Size(textsize(8)).Typeface";
_cs.Color(anywheresoftware.b4a.keywords.Common.Colors.White).Size((int) (_textsize(_ba,(int) (8)))).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME()).Append(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf2d5)))).Pop();
 //BA.debugLineNum = 328;BA.debugLine="cs.Color(Colors.White).Typeface(defaultfont).Size";
_cs.Color(anywheresoftware.b4a.keywords.Common.Colors.White).Typeface((android.graphics.Typeface)(_defaultfont.getObject())).Size((int) (_textsize(_ba,(int) (8)))).Append(BA.ObjectToCharSequence(" 9:30 AM")).Pop();
 //BA.debugLineNum = 329;BA.debugLine="cs.Size(textsize(8)).Color(Colors.White).Typeface";
_cs.Size((int) (_textsize(_ba,(int) (8)))).Color(anywheresoftware.b4a.keywords.Common.Colors.White).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME()).Append(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.CRLF+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf2d5))))).Pop();
 //BA.debugLineNum = 330;BA.debugLine="cs.Size(textsize(8)).Typeface(defaultfont).Color(";
_cs.Size((int) (_textsize(_ba,(int) (8)))).Typeface((android.graphics.Typeface)(_defaultfont.getObject())).Color(anywheresoftware.b4a.keywords.Common.Colors.White).Append(BA.ObjectToCharSequence(" 2:00 PM")).PopAll();
 //BA.debugLineNum = 331;BA.debugLine="Return cs";
if (true) return _cs;
 //BA.debugLineNum = 332;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.CSBuilder  _mitimecs1(anywheresoftware.b4a.BA _ba,String _tm) throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 333;BA.debugLine="Sub mitimecs1(tm As String) As CSBuilder";
 //BA.debugLineNum = 334;BA.debugLine="Dim cs As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 335;BA.debugLine="cs.Initialize";
_cs.Initialize();
 //BA.debugLineNum = 336;BA.debugLine="cs.Color(Colors.White).Size(textsize(8)).Typeface";
_cs.Color(anywheresoftware.b4a.keywords.Common.Colors.White).Size((int) (_textsize(_ba,(int) (8)))).Typeface(anywheresoftware.b4a.keywords.Common.Typeface.getFONTAWESOME()).Append(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.Chr(((int)0xf2d5)))).Pop();
 //BA.debugLineNum = 337;BA.debugLine="cs.Color(Colors.White).Typeface(defaultfont).Size";
_cs.Color(anywheresoftware.b4a.keywords.Common.Colors.White).Typeface((android.graphics.Typeface)(_defaultfont.getObject())).Size((int) (_textsize(_ba,(int) (8)))).Append(BA.ObjectToCharSequence(" "+_tm)).PopAll();
 //BA.debugLineNum = 338;BA.debugLine="Return cs";
if (true) return _cs;
 //BA.debugLineNum = 339;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.CSBuilder  _moderninernetcs(anywheresoftware.b4a.BA _ba,String _n,String _t) throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 292;BA.debugLine="Sub moderninernetcs(n As String,t As String) As CS";
 //BA.debugLineNum = 293;BA.debugLine="Dim cs As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 294;BA.debugLine="cs.Initialize";
_cs.Initialize();
 //BA.debugLineNum = 295;BA.debugLine="cs.Color(Colors.Yellow).Typeface(semibold).Size(t";
_cs.Color(anywheresoftware.b4a.keywords.Common.Colors.Yellow).Typeface((android.graphics.Typeface)(_semibold.getObject())).Size((int) (_textsize(_ba,(int) (9)))).Append(BA.ObjectToCharSequence(_misub(_ba,_n))).Pop();
 //BA.debugLineNum = 296;BA.debugLine="cs.Color(Colors.White).Typeface(semibold).Size(te";
_cs.Color(anywheresoftware.b4a.keywords.Common.Colors.White).Typeface((android.graphics.Typeface)(_semibold.getObject())).Size((int) (_textsize(_ba,(int) (9)))).Append(BA.ObjectToCharSequence(_mimain(_ba,_n))).Pop();
 //BA.debugLineNum = 297;BA.debugLine="cs.Color(Colors.Yellow).Typeface(semibold).Size(t";
_cs.Color(anywheresoftware.b4a.keywords.Common.Colors.Yellow).Typeface((android.graphics.Typeface)(_semibold.getObject())).Size((int) (_textsize(_ba,(int) (9)))).Append(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.CRLF+_misub(_ba,_t))).Pop();
 //BA.debugLineNum = 298;BA.debugLine="cs.Color(Colors.White).Typeface(semibold).Size(te";
_cs.Color(anywheresoftware.b4a.keywords.Common.Colors.White).Typeface((android.graphics.Typeface)(_semibold.getObject())).Size((int) (_textsize(_ba,(int) (9)))).Append(BA.ObjectToCharSequence(_mimain(_ba,_t))).PopAll();
 //BA.debugLineNum = 299;BA.debugLine="Return cs";
if (true) return _cs;
 //BA.debugLineNum = 300;BA.debugLine="End Sub";
return null;
}
public static anywheresoftware.b4a.objects.CSBuilder  _moderninernetcs1(anywheresoftware.b4a.BA _ba,String _n) throws Exception{
anywheresoftware.b4a.objects.CSBuilder _cs = null;
 //BA.debugLineNum = 304;BA.debugLine="Sub moderninernetcs1(n As String) As CSBuilder";
 //BA.debugLineNum = 305;BA.debugLine="Dim cs As CSBuilder";
_cs = new anywheresoftware.b4a.objects.CSBuilder();
 //BA.debugLineNum = 306;BA.debugLine="cs.Initialize";
_cs.Initialize();
 //BA.debugLineNum = 307;BA.debugLine="cs.Color(Colors.Yellow).Typeface(semibold).Size(t";
_cs.Color(anywheresoftware.b4a.keywords.Common.Colors.Yellow).Typeface((android.graphics.Typeface)(_semibold.getObject())).Size((int) (_textsize(_ba,(int) (10)))).Append(BA.ObjectToCharSequence(_misub(_ba,_n))).Pop();
 //BA.debugLineNum = 308;BA.debugLine="cs.Color(Colors.White).Typeface(semibold).Size(te";
_cs.Color(anywheresoftware.b4a.keywords.Common.Colors.White).Typeface((android.graphics.Typeface)(_semibold.getObject())).Size((int) (_textsize(_ba,(int) (10)))).Append(BA.ObjectToCharSequence(_mimain(_ba,_n))).PopAll();
 //BA.debugLineNum = 309;BA.debugLine="Return cs";
if (true) return _cs;
 //BA.debugLineNum = 310;BA.debugLine="End Sub";
return null;
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 4;BA.debugLine="Dim checkentertime As Int";
_checkentertime = 0;
 //BA.debugLineNum = 8;BA.debugLine="Dim futureimglist As List";
_futureimglist = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 9;BA.debugLine="Dim weeklyimglist As List";
_weeklyimglist = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 10;BA.debugLine="Dim calendarimglist As List";
_calendarimglist = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 12;BA.debugLine="Dim ActivityHeight As Int";
_activityheight = 0;
 //BA.debugLineNum = 13;BA.debugLine="Dim appbartop As Int";
_appbartop = 0;
 //BA.debugLineNum = 14;BA.debugLine="Dim appbarheight As Int";
_appbarheight = 0;
 //BA.debugLineNum = 15;BA.debugLine="Public naviColor As Int =Colors.RGB(114,28,48) '0";
_navicolor = anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (114),(int) (28),(int) (48));
 //BA.debugLineNum = 16;BA.debugLine="Public bgColor As Int  = Colors.RGB(20,12,31)'0xF";
_bgcolor = anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (20),(int) (12),(int) (31));
 //BA.debugLineNum = 17;BA.debugLine="Dim mmfont As Typeface = Typeface.LoadFromAssets(";
_mmfont = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
_mmfont = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("PYIDAUNGSU-2.5.3_REGULAR.TTF")));
 //BA.debugLineNum = 18;BA.debugLine="Dim defaultfont As Typeface =Typeface.LoadFromAss";
_defaultfont = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
_defaultfont = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("DMSans-Regular.ttf")));
 //BA.debugLineNum = 19;BA.debugLine="Dim semibold As Typeface = Typeface.LoadFromAsset";
_semibold = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
_semibold = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("DMSans-SemiBold.ttf")));
 //BA.debugLineNum = 20;BA.debugLine="Dim livebold As Typeface = Typeface.LoadFromAsset";
_livebold = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
_livebold = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("DMSans-ExtraBold.ttf")));
 //BA.debugLineNum = 21;BA.debugLine="Dim lightfont As Typeface = Typeface.LoadFromAsse";
_lightfont = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
_lightfont = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("DMSans-Light.ttf")));
 //BA.debugLineNum = 22;BA.debugLine="Dim id As String  = \"id\"";
_id = "id";
 //BA.debugLineNum = 23;BA.debugLine="Dim profile_pic As String = \"profile_pic\"";
_profile_pic = "profile_pic";
 //BA.debugLineNum = 24;BA.debugLine="Dim name As String = \"name\"";
_name = "name";
 //BA.debugLineNum = 25;BA.debugLine="Dim email As String = \"email\"";
_email = "email";
 //BA.debugLineNum = 26;BA.debugLine="Dim chatlist As List";
_chatlist = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 27;BA.debugLine="Dim LastLive As String";
_lastlive = "";
 //BA.debugLineNum = 28;BA.debugLine="Dim paperdata As String";
_paperdata = "";
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public static String  _setnavigationcolor(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.phone.Phone _phone = null;
anywheresoftware.b4j.object.JavaObject _jo = null;
anywheresoftware.b4j.object.JavaObject _window = null;
anywheresoftware.b4j.object.JavaObject _decorview = null;
int _flags = 0;
 //BA.debugLineNum = 133;BA.debugLine="Sub SETnavigationcolor";
 //BA.debugLineNum = 136;BA.debugLine="Dim phone As Phone";
_phone = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 137;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 138;BA.debugLine="If phone.SdkVersion >= 21 Then";
if (_phone.getSdkVersion()>=21) { 
 //BA.debugLineNum = 139;BA.debugLine="jo.InitializeContext";
_jo.InitializeContext((_ba.processBA == null ? _ba : _ba.processBA));
 //BA.debugLineNum = 140;BA.debugLine="Dim window As JavaObject = jo.RunMethodJO(\"getWi";
_window = new anywheresoftware.b4j.object.JavaObject();
_window = _jo.RunMethodJO("getWindow",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 141;BA.debugLine="window.RunMethod(\"setStatusBarColor\", Array As O";
_window.RunMethod("setStatusBarColor",new Object[]{(Object)(_navicolor)});
 };
 //BA.debugLineNum = 145;BA.debugLine="If phone.SdkVersion >= 23 Then";
if (_phone.getSdkVersion()>=23) { 
 //BA.debugLineNum = 146;BA.debugLine="Dim decorView As JavaObject = jo.RunMethodJO(\"ge";
_decorview = new anywheresoftware.b4j.object.JavaObject();
_decorview = _jo.RunMethodJO("getWindow",(Object[])(anywheresoftware.b4a.keywords.Common.Null)).RunMethodJO("getDecorView",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 147;BA.debugLine="Dim flags As Int = decorView.RunMethod(\"getSyste";
_flags = (int)(BA.ObjectToNumber(_decorview.RunMethod("getSystemUiVisibility",(Object[])(anywheresoftware.b4a.keywords.Common.Null))));
 //BA.debugLineNum = 148;BA.debugLine="flags = Bit.Or(flags, 0x00000000) ' SYSTEM_UI_FL";
_flags = anywheresoftware.b4a.keywords.Common.Bit.Or(_flags,((int)0x00000000));
 //BA.debugLineNum = 149;BA.debugLine="decorView.RunMethod(\"setSystemUiVisibility\", Arr";
_decorview.RunMethod("setSystemUiVisibility",new Object[]{(Object)(_flags)});
 };
 //BA.debugLineNum = 151;BA.debugLine="End Sub";
return "";
}
public static String  _setnavigationcolor1(anywheresoftware.b4a.BA _ba) throws Exception{
anywheresoftware.b4a.phone.Phone _phone = null;
anywheresoftware.b4j.object.JavaObject _jo = null;
anywheresoftware.b4j.object.JavaObject _window = null;
anywheresoftware.b4j.object.JavaObject _decorview = null;
int _flags = 0;
 //BA.debugLineNum = 153;BA.debugLine="Sub SETnavigationcolor1";
 //BA.debugLineNum = 154;BA.debugLine="Dim phone As Phone";
_phone = new anywheresoftware.b4a.phone.Phone();
 //BA.debugLineNum = 155;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 156;BA.debugLine="If phone.SdkVersion >= 21 Then";
if (_phone.getSdkVersion()>=21) { 
 //BA.debugLineNum = 157;BA.debugLine="jo.InitializeContext";
_jo.InitializeContext((_ba.processBA == null ? _ba : _ba.processBA));
 //BA.debugLineNum = 158;BA.debugLine="Dim window As JavaObject = jo.RunMethodJO(\"getWi";
_window = new anywheresoftware.b4j.object.JavaObject();
_window = _jo.RunMethodJO("getWindow",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 159;BA.debugLine="window.RunMethod(\"setStatusBarColor\", Array As O";
_window.RunMethod("setStatusBarColor",new Object[]{(Object)(_bgcolor)});
 };
 //BA.debugLineNum = 163;BA.debugLine="If phone.SdkVersion >= 23 Then";
if (_phone.getSdkVersion()>=23) { 
 //BA.debugLineNum = 164;BA.debugLine="Dim decorView As JavaObject = jo.RunMethodJO(\"ge";
_decorview = new anywheresoftware.b4j.object.JavaObject();
_decorview = _jo.RunMethodJO("getWindow",(Object[])(anywheresoftware.b4a.keywords.Common.Null)).RunMethodJO("getDecorView",(Object[])(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 165;BA.debugLine="Dim flags As Int = decorView.RunMethod(\"getSyste";
_flags = (int)(BA.ObjectToNumber(_decorview.RunMethod("getSystemUiVisibility",(Object[])(anywheresoftware.b4a.keywords.Common.Null))));
 //BA.debugLineNum = 166;BA.debugLine="flags = Bit.Or(flags, 0x00000000) ' SYSTEM_UI_FL";
_flags = anywheresoftware.b4a.keywords.Common.Bit.Or(_flags,((int)0x00000000));
 //BA.debugLineNum = 167;BA.debugLine="decorView.RunMethod(\"setSystemUiVisibility\", Arr";
_decorview.RunMethod("setSystemUiVisibility",new Object[]{(Object)(_flags)});
 };
 //BA.debugLineNum = 169;BA.debugLine="End Sub";
return "";
}
public static float  _textsize(anywheresoftware.b4a.BA _ba,int _size) throws Exception{
 //BA.debugLineNum = 341;BA.debugLine="Sub textsize(size As Int) As Float";
 //BA.debugLineNum = 343;BA.debugLine="If GetDeviceLayoutValues.Scale >2.5 Then";
if (anywheresoftware.b4a.keywords.Common.GetDeviceLayoutValues(_ba).Scale>2.5) { 
 //BA.debugLineNum = 344;BA.debugLine="Return size *2.5";
if (true) return (float) (_size*2.5);
 }else if(anywheresoftware.b4a.keywords.Common.GetDeviceLayoutValues(_ba).Scale<2) { 
 //BA.debugLineNum = 346;BA.debugLine="Return size *2.3";
if (true) return (float) (_size*2.3);
 }else {
 //BA.debugLineNum = 348;BA.debugLine="Return size * GetDeviceLayoutValues.Scale";
if (true) return (float) (_size*anywheresoftware.b4a.keywords.Common.GetDeviceLayoutValues(_ba).Scale);
 };
 //BA.debugLineNum = 351;BA.debugLine="End Sub";
return 0f;
}
public static float  _textsize1(anywheresoftware.b4a.BA _ba,int _size) throws Exception{
 //BA.debugLineNum = 354;BA.debugLine="Sub textsize1(size As Int) As Float";
 //BA.debugLineNum = 355;BA.debugLine="Return size * GetDeviceLayoutValues.Scale";
if (true) return (float) (_size*anywheresoftware.b4a.keywords.Common.GetDeviceLayoutValues(_ba).Scale);
 //BA.debugLineNum = 365;BA.debugLine="End Sub";
return 0f;
}
public static int  _textwidth(anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.LabelWrapper _lb,String _texto) throws Exception{
anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper _bmp = null;
anywheresoftware.b4a.objects.drawable.CanvasWrapper _cvs = null;
 //BA.debugLineNum = 368;BA.debugLine="Sub TextWidth(lb As Label, Texto As String) As Int";
 //BA.debugLineNum = 369;BA.debugLine="Private bmp As Bitmap";
_bmp = new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper();
 //BA.debugLineNum = 370;BA.debugLine="bmp.InitializeMutable(1dip, 1dip)";
_bmp.InitializeMutable(anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (1)));
 //BA.debugLineNum = 371;BA.debugLine="Private cvs As Canvas";
_cvs = new anywheresoftware.b4a.objects.drawable.CanvasWrapper();
 //BA.debugLineNum = 372;BA.debugLine="cvs.Initialize2(bmp)";
_cvs.Initialize2((android.graphics.Bitmap)(_bmp.getObject()));
 //BA.debugLineNum = 373;BA.debugLine="Return cvs.MeasureStringWidth(Texto, lb.Typeface";
if (true) return (int) (_cvs.MeasureStringWidth(_texto,_lb.getTypeface(),_lb.getTextSize()));
 //BA.debugLineNum = 374;BA.debugLine="End Sub";
return 0;
}
}
