package com.burma.royal2d;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class as_cardslider extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "com.burma.royal2d.as_cardslider");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", com.burma.royal2d.as_cardslider.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public String _meventname = "";
public Object _mcallback = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _mbase = null;
public anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public Object _tag = null;
public b4a.example3.customlistview _xclv_main = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _xpnl_loading = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _xpnl_clv_background = null;
public boolean _m_lazyloading = false;
public int _m_lazyloadingextrasize = 0;
public int _m_loadingpanelcolor = 0;
public float _g_cornerradius = 0f;
public int _m_index = 0;
public float _m_itemwidth = 0f;
public float _m_sidegap = 0f;
public float _g_x = 0f;
public boolean _isfirstmove = false;
public int _m_lastindex = 0;
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
public String  _addpage(anywheresoftware.b4a.objects.B4XViewWrapper _layoutpanel,Object _value) throws Exception{
 //BA.debugLineNum = 179;BA.debugLine="Public Sub AddPage(LayoutPanel As B4XView,Value As";
 //BA.debugLineNum = 185;BA.debugLine="xclv_Main.InsertAt(xclv_Main.Size -1,LayoutPanel,";
_xclv_main._insertat((int) (_xclv_main._getsize()-1),_layoutpanel,_value);
 //BA.debugLineNum = 187;BA.debugLine="End Sub";
return "";
}
public String  _addplaceholder() throws Exception{
anywheresoftware.b4a.objects.B4XViewWrapper _xpnl_bottom = null;
 //BA.debugLineNum = 228;BA.debugLine="Private Sub AddPlaceholder";
 //BA.debugLineNum = 229;BA.debugLine="Dim xpnl_bottom As B4XView = xui.CreatePanel(\"\")";
_xpnl_bottom = new anywheresoftware.b4a.objects.B4XViewWrapper();
_xpnl_bottom = _xui.CreatePanel(ba,"");
 //BA.debugLineNum = 230;BA.debugLine="xpnl_bottom.SetLayoutAnimated(0,0,0,m_SideGap - 0";
_xpnl_bottom.SetLayoutAnimated((int) (0),(int) (0),(int) (0),(int) (_m_sidegap-__c.DipToCurrent((int) (0))),_mbase.getHeight());
 //BA.debugLineNum = 231;BA.debugLine="xpnl_bottom.Color = mBase.Color";
_xpnl_bottom.setColor(_mbase.getColor());
 //BA.debugLineNum = 233;BA.debugLine="xclv_Main.Add(xpnl_bottom,\"Placeholder\")";
_xclv_main._add(_xpnl_bottom,(Object)("Placeholder"));
 //BA.debugLineNum = 237;BA.debugLine="End Sub";
return "";
}
public void  _base_resize(double _width,double _height) throws Exception{
ResumableSub_Base_Resize rsub = new ResumableSub_Base_Resize(this,_width,_height);
rsub.resume(ba, null);
}
public static class ResumableSub_Base_Resize extends BA.ResumableSub {
public ResumableSub_Base_Resize(com.burma.royal2d.as_cardslider parent,double _width,double _height) {
this.parent = parent;
this._width = _width;
this._height = _height;
}
com.burma.royal2d.as_cardslider parent;
double _width;
double _height;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
 //BA.debugLineNum = 114;BA.debugLine="mBase.SetLayoutAnimated(0,mBase.Left,mBase.Top,Wi";
parent._mbase.SetLayoutAnimated((int) (0),parent._mbase.getLeft(),parent._mbase.getTop(),(int) (_width),(int) (_height));
 //BA.debugLineNum = 115;BA.debugLine="mBase.SetColorAndBorder(mBase.Color,0,xui.Color_T";
parent._mbase.SetColorAndBorder(parent._mbase.getColor(),(int) (0),parent._xui.Color_Transparent,(int) (parent._g_cornerradius));
 //BA.debugLineNum = 116;BA.debugLine="SetCircleClip(mBase,g_CornerRadius)";
parent._setcircleclip(parent._mbase,(int) (parent._g_cornerradius));
 //BA.debugLineNum = 127;BA.debugLine="xpnl_clv_background.SetLayoutAnimated(0,0,0,Width";
parent._xpnl_clv_background.SetLayoutAnimated((int) (0),(int) (0),(int) (0),(int) (_width),(int) (_height));
 //BA.debugLineNum = 135;BA.debugLine="Sleep(250)";
parent.__c.Sleep(ba,this,(int) (250));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 //BA.debugLineNum = 136;BA.debugLine="xpnl_Loading.SetVisibleAnimated(500,False)";
parent._xpnl_loading.SetVisibleAnimated((int) (500),parent.__c.False);
 //BA.debugLineNum = 138;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 20;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 22;BA.debugLine="Private mEventName As String 'ignore";
_meventname = "";
 //BA.debugLineNum = 23;BA.debugLine="Private mCallBack As Object 'ignore";
_mcallback = new Object();
 //BA.debugLineNum = 24;BA.debugLine="Public mBase As B4XView";
_mbase = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private xui As XUI 'ignore";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 26;BA.debugLine="Public Tag As Object";
_tag = new Object();
 //BA.debugLineNum = 31;BA.debugLine="Private xclv_Main As CustomListView";
_xclv_main = new b4a.example3.customlistview();
 //BA.debugLineNum = 33;BA.debugLine="Private xpnl_Loading As B4XView";
_xpnl_loading = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private xpnl_clv_background As B4XView";
_xpnl_clv_background = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private m_LazyLoading As Boolean";
_m_lazyloading = false;
 //BA.debugLineNum = 47;BA.debugLine="Private m_LazyLoadingExtraSize As Int";
_m_lazyloadingextrasize = 0;
 //BA.debugLineNum = 48;BA.debugLine="Private m_LoadingPanelColor As Int";
_m_loadingpanelcolor = 0;
 //BA.debugLineNum = 50;BA.debugLine="Private g_CornerRadius As Float";
_g_cornerradius = 0f;
 //BA.debugLineNum = 52;BA.debugLine="Private m_Index As Int = 1";
_m_index = (int) (1);
 //BA.debugLineNum = 54;BA.debugLine="Private m_ItemWidth As Float";
_m_itemwidth = 0f;
 //BA.debugLineNum = 55;BA.debugLine="Private m_SideGap As Float";
_m_sidegap = 0f;
 //BA.debugLineNum = 56;BA.debugLine="Private g_x As Float 'Ignore";
_g_x = 0f;
 //BA.debugLineNum = 57;BA.debugLine="Private isfirstmove As Boolean 'Ignore";
_isfirstmove = false;
 //BA.debugLineNum = 58;BA.debugLine="Private m_LastIndex As Int";
_m_lastindex = 0;
 //BA.debugLineNum = 59;BA.debugLine="End Sub";
return "";
}
public String  _clear() throws Exception{
 //BA.debugLineNum = 216;BA.debugLine="Public Sub Clear";
 //BA.debugLineNum = 218;BA.debugLine="setItemWidth(m_ItemWidth)";
_setitemwidth(_m_itemwidth);
 //BA.debugLineNum = 219;BA.debugLine="m_Index = 1";
_m_index = (int) (1);
 //BA.debugLineNum = 220;BA.debugLine="m_LastIndex = 1";
_m_lastindex = (int) (1);
 //BA.debugLineNum = 226;BA.debugLine="End Sub";
return "";
}
public String  _designercreateview(Object _base,anywheresoftware.b4a.objects.LabelWrapper _lbl,anywheresoftware.b4a.objects.collections.Map _props) throws Exception{
 //BA.debugLineNum = 67;BA.debugLine="Public Sub DesignerCreateView (Base As Object, Lbl";
 //BA.debugLineNum = 68;BA.debugLine="mBase = Base";
_mbase = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_base));
 //BA.debugLineNum = 69;BA.debugLine="Tag = mBase.Tag";
_tag = _mbase.getTag();
 //BA.debugLineNum = 70;BA.debugLine="mBase.Tag = Me";
_mbase.setTag(this);
 //BA.debugLineNum = 72;BA.debugLine="ini_props(Props)";
_ini_props(_props);
 //BA.debugLineNum = 82;BA.debugLine="xpnl_clv_background = xui.CreatePanel(\"\")";
_xpnl_clv_background = _xui.CreatePanel(ba,"");
 //BA.debugLineNum = 83;BA.debugLine="mBase.AddView(xpnl_clv_background,0,0,0,0)";
_mbase.AddView((android.view.View)(_xpnl_clv_background.getObject()),(int) (0),(int) (0),(int) (0),(int) (0));
 //BA.debugLineNum = 86;BA.debugLine="xpnl_Loading = xui.CreatePanel(\"\")";
_xpnl_loading = _xui.CreatePanel(ba,"");
 //BA.debugLineNum = 87;BA.debugLine="mBase.AddView(xpnl_Loading,0,0,mBase.Width,mBase.";
_mbase.AddView((android.view.View)(_xpnl_loading.getObject()),(int) (0),(int) (0),_mbase.getWidth(),_mbase.getHeight());
 //BA.debugLineNum = 88;BA.debugLine="xpnl_Loading.Color = m_LoadingPanelColor";
_xpnl_loading.setColor(_m_loadingpanelcolor);
 //BA.debugLineNum = 89;BA.debugLine="xpnl_Loading.BringToFront";
_xpnl_loading.BringToFront();
 //BA.debugLineNum = 91;BA.debugLine="m_ItemWidth = mBase.Width - (m_SideGap*2)";
_m_itemwidth = (float) (_mbase.getWidth()-(_m_sidegap*2));
 //BA.debugLineNum = 94;BA.debugLine="ini_xclv";
_ini_xclv();
 //BA.debugLineNum = 97;BA.debugLine="AddPlaceholder";
_addplaceholder();
 //BA.debugLineNum = 98;BA.debugLine="AddPlaceholder";
_addplaceholder();
 //BA.debugLineNum = 101;BA.debugLine="Base_Resize(mBase.Width,mBase.Height)";
_base_resize(_mbase.getWidth(),_mbase.getHeight());
 //BA.debugLineNum = 104;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.B4XViewWrapper  _getbase() throws Exception{
 //BA.debugLineNum = 375;BA.debugLine="Public Sub getBase As B4XView";
 //BA.debugLineNum = 376;BA.debugLine="Return mBase";
if (true) return _mbase;
 //BA.debugLineNum = 377;BA.debugLine="End Sub";
return null;
}
public b4a.example3.customlistview  _getcustomlistview() throws Exception{
 //BA.debugLineNum = 369;BA.debugLine="Public Sub getCustomListView As CustomListView";
 //BA.debugLineNum = 370;BA.debugLine="Return xclv_Main";
if (true) return _xclv_main;
 //BA.debugLineNum = 371;BA.debugLine="End Sub";
return null;
}
public int  _getindex() throws Exception{
 //BA.debugLineNum = 380;BA.debugLine="Public Sub getIndex As Int";
 //BA.debugLineNum = 381;BA.debugLine="Return m_Index -1";
if (true) return (int) (_m_index-1);
 //BA.debugLineNum = 382;BA.debugLine="End Sub";
return 0;
}
public float  _getitemwidth() throws Exception{
 //BA.debugLineNum = 314;BA.debugLine="Public Sub getItemWidth As Float";
 //BA.debugLineNum = 315;BA.debugLine="Return m_ItemWidth";
if (true) return _m_itemwidth;
 //BA.debugLineNum = 316;BA.debugLine="End Sub";
return 0f;
}
public int  _getlazyloadingextrasize() throws Exception{
 //BA.debugLineNum = 421;BA.debugLine="Public Sub getLazyLoadingExtraSize As Int";
 //BA.debugLineNum = 422;BA.debugLine="Return m_LazyLoadingExtraSize";
if (true) return _m_lazyloadingextrasize;
 //BA.debugLineNum = 423;BA.debugLine="End Sub";
return 0;
}
public int  _getloadingpanelcolor() throws Exception{
 //BA.debugLineNum = 413;BA.debugLine="Public Sub getLoadingPanelColor As Int";
 //BA.debugLineNum = 414;BA.debugLine="Return m_LoadingPanelColor";
if (true) return _m_loadingpanelcolor;
 //BA.debugLineNum = 415;BA.debugLine="End Sub";
return 0;
}
public anywheresoftware.b4a.objects.B4XViewWrapper  _getpanel(int _index) throws Exception{
 //BA.debugLineNum = 429;BA.debugLine="Public Sub GetPanel(Index As Int) As B4XView";
 //BA.debugLineNum = 430;BA.debugLine="Index = Index +1";
_index = (int) (_index+1);
 //BA.debugLineNum = 434;BA.debugLine="Return xclv_Main.GetPanel(Index)";
if (true) return _xclv_main._getpanel(_index);
 //BA.debugLineNum = 436;BA.debugLine="End Sub";
return null;
}
public int  _getsize() throws Exception{
 //BA.debugLineNum = 399;BA.debugLine="Public Sub getSize As Int";
 //BA.debugLineNum = 401;BA.debugLine="Return xclv_Main.Size -2";
if (true) return (int) (_xclv_main._getsize()-2);
 //BA.debugLineNum = 405;BA.debugLine="End Sub";
return 0;
}
public Object  _getvalue(int _index) throws Exception{
 //BA.debugLineNum = 390;BA.debugLine="Public Sub GetValue(Index As Int) As Object";
 //BA.debugLineNum = 391;BA.debugLine="Index = Index +1";
_index = (int) (_index+1);
 //BA.debugLineNum = 395;BA.debugLine="Return xclv_Main.GetValue(Index)";
if (true) return _xclv_main._getvalue(_index);
 //BA.debugLineNum = 397;BA.debugLine="End Sub";
return null;
}
public boolean  _handletouch(int _action,float _x,float _y) throws Exception{
 //BA.debugLineNum = 247;BA.debugLine="Private Sub HandleTouch(Action As Int,x As Float,y";
 //BA.debugLineNum = 252;BA.debugLine="Select Action";
switch (_action) {
case 2: {
 //BA.debugLineNum = 255;BA.debugLine="If isfirstmove = False Then";
if (_isfirstmove==__c.False) { 
 //BA.debugLineNum = 256;BA.debugLine="isfirstmove = True";
_isfirstmove = __c.True;
 //BA.debugLineNum = 257;BA.debugLine="g_x=X";
_g_x = _x;
 };
 break; }
case 1: {
 //BA.debugLineNum = 262;BA.debugLine="isfirstmove = False";
_isfirstmove = __c.False;
 //BA.debugLineNum = 263;BA.debugLine="m_LastIndex = m_Index";
_m_lastindex = _m_index;
 //BA.debugLineNum = 264;BA.debugLine="If g_x < x Then";
if (_g_x<_x) { 
 //BA.debugLineNum = 265;BA.debugLine="m_Index = Max(1,m_Index -1)";
_m_index = (int) (__c.Max(1,_m_index-1));
 }else {
 //BA.debugLineNum = 267;BA.debugLine="m_Index = Min(xclv_Main.Size -2, m_Index +1)";
_m_index = (int) (__c.Min(_xclv_main._getsize()-2,_m_index+1));
 };
 //BA.debugLineNum = 269;BA.debugLine="SnapCLV";
_snapclv();
 //BA.debugLineNum = 270;BA.debugLine="Return True";
if (true) return __c.True;
 break; }
}
;
 //BA.debugLineNum = 273;BA.debugLine="Return False";
if (true) return __c.False;
 //BA.debugLineNum = 274;BA.debugLine="End Sub";
return false;
}
public String  _ini_props(anywheresoftware.b4a.objects.collections.Map _props) throws Exception{
 //BA.debugLineNum = 106;BA.debugLine="Private Sub ini_props(Props As Map)";
 //BA.debugLineNum = 107;BA.debugLine="m_SideGap = DipToCurrent(Props.GetDefault(\"SideGa";
_m_sidegap = (float) (__c.DipToCurrent((int)(BA.ObjectToNumber(_props.GetDefault((Object)("SideGap"),(Object)(__c.DipToCurrent((int) (80))))))));
 //BA.debugLineNum = 108;BA.debugLine="m_LazyLoading = Props.GetDefault(\"LazyLoading\",Fa";
_m_lazyloading = BA.ObjectToBoolean(_props.GetDefault((Object)("LazyLoading"),(Object)(__c.False)));
 //BA.debugLineNum = 109;BA.debugLine="m_LazyLoadingExtraSize = Props.GetDefault(\"LazyLo";
_m_lazyloadingextrasize = (int)(BA.ObjectToNumber(_props.GetDefault((Object)("LazyLoadingExtraSize"),(Object)(5))));
 //BA.debugLineNum = 110;BA.debugLine="m_LoadingPanelColor = xui.PaintOrColorToColor(Pro";
_m_loadingpanelcolor = _xui.PaintOrColorToColor(_props.GetDefault((Object)("LoadingPanelColor"),(Object)(_xui.Color_Black)));
 //BA.debugLineNum = 111;BA.debugLine="End Sub";
return "";
}
public String  _ini_xclv() throws Exception{
anywheresoftware.b4a.objects.B4XViewWrapper _xpnl_base = null;
anywheresoftware.b4a.objects.LabelWrapper _tmplbl = null;
anywheresoftware.b4a.objects.collections.Map _tmpmap = null;
anywheresoftware.b4a.agraham.reflection.Reflection _objpages = null;
 //BA.debugLineNum = 141;BA.debugLine="Private Sub ini_xclv";
 //BA.debugLineNum = 142;BA.debugLine="Dim xpnl_base As B4XView = xui.CreatePanel(\"\")";
_xpnl_base = new anywheresoftware.b4a.objects.B4XViewWrapper();
_xpnl_base = _xui.CreatePanel(ba,"");
 //BA.debugLineNum = 143;BA.debugLine="xpnl_base.Color = xui.Color_Transparent";
_xpnl_base.setColor(_xui.Color_Transparent);
 //BA.debugLineNum = 144;BA.debugLine="xpnl_clv_background.AddView(xpnl_base,0,0,mBase.W";
_xpnl_clv_background.AddView((android.view.View)(_xpnl_base.getObject()),(int) (0),(int) (0),_mbase.getWidth(),_mbase.getHeight());
 //BA.debugLineNum = 146;BA.debugLine="Dim tmplbl As Label";
_tmplbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 147;BA.debugLine="tmplbl.Initialize(\"\")";
_tmplbl.Initialize(ba,"");
 //BA.debugLineNum = 149;BA.debugLine="Dim tmpmap As Map";
_tmpmap = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 150;BA.debugLine="tmpmap.Initialize";
_tmpmap.Initialize();
 //BA.debugLineNum = 151;BA.debugLine="tmpmap.Put(\"DividerColor\",0x00FFFFFF)";
_tmpmap.Put((Object)("DividerColor"),(Object)(((int)0x00ffffff)));
 //BA.debugLineNum = 152;BA.debugLine="tmpmap.Put(\"DividerHeight\",0)";
_tmpmap.Put((Object)("DividerHeight"),(Object)(0));
 //BA.debugLineNum = 153;BA.debugLine="tmpmap.Put(\"PressedColor\",0x00FFFFFF)";
_tmpmap.Put((Object)("PressedColor"),(Object)(((int)0x00ffffff)));
 //BA.debugLineNum = 154;BA.debugLine="tmpmap.Put(\"InsertAnimationDuration\",0)";
_tmpmap.Put((Object)("InsertAnimationDuration"),(Object)(0));
 //BA.debugLineNum = 155;BA.debugLine="tmpmap.Put(\"ListOrientation\",\"Horizontal\")";
_tmpmap.Put((Object)("ListOrientation"),(Object)("Horizontal"));
 //BA.debugLineNum = 156;BA.debugLine="tmpmap.Put(\"ShowScrollBar\",False)";
_tmpmap.Put((Object)("ShowScrollBar"),(Object)(__c.False));
 //BA.debugLineNum = 157;BA.debugLine="xclv_Main.Initialize(Me,\"xclv\")";
_xclv_main._initialize(ba,this,"xclv");
 //BA.debugLineNum = 158;BA.debugLine="xclv_Main.DesignerCreateView(xpnl_base,tmplbl,tmp";
_xclv_main._designercreateview((Object)(_xpnl_base.getObject()),_tmplbl,_tmpmap);
 //BA.debugLineNum = 161;BA.debugLine="Private objPages As Reflector";
_objpages = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 162;BA.debugLine="objPages.Target = xclv_Main.sv";
_objpages.Target = (Object)(_xclv_main._sv.getObject());
 //BA.debugLineNum = 163;BA.debugLine="objPages.SetOnTouchListener(\"xpnl_PageArea2_Touch";
_objpages.SetOnTouchListener(ba,"xpnl_PageArea2_Touch");
 //BA.debugLineNum = 176;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba,Object _callback,String _eventname) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 61;BA.debugLine="Public Sub Initialize (Callback As Object, EventNa";
 //BA.debugLineNum = 62;BA.debugLine="mEventName = EventName";
_meventname = _eventname;
 //BA.debugLineNum = 63;BA.debugLine="mCallBack = Callback";
_mcallback = _callback;
 //BA.debugLineNum = 64;BA.debugLine="End Sub";
return "";
}
public String  _lazyloadingaddcontent(anywheresoftware.b4a.objects.B4XViewWrapper _parent,Object _value) throws Exception{
 //BA.debugLineNum = 442;BA.debugLine="Private Sub LazyLoadingAddContent(Parent As B4XVie";
 //BA.debugLineNum = 443;BA.debugLine="If xui.SubExists(mCallBack, mEventName & \"_LazyLo";
if (_xui.SubExists(ba,_mcallback,_meventname+"_LazyLoadingAddContent",(int) (2))) { 
 //BA.debugLineNum = 444;BA.debugLine="CallSub3(mCallBack, mEventName & \"_LazyLoadingAd";
__c.CallSubNew3(ba,_mcallback,_meventname+"_LazyLoadingAddContent",(Object)(_parent),_value);
 };
 //BA.debugLineNum = 446;BA.debugLine="End Sub";
return "";
}
public String  _nextpage() throws Exception{
 //BA.debugLineNum = 334;BA.debugLine="Public Sub NextPage";
 //BA.debugLineNum = 336;BA.debugLine="If m_Index < xclv_Main.Size -2 Then";
if (_m_index<_xclv_main._getsize()-2) { 
 //BA.debugLineNum = 340;BA.debugLine="m_LastIndex = m_Index";
_m_lastindex = _m_index;
 //BA.debugLineNum = 341;BA.debugLine="m_Index = m_Index +1";
_m_index = (int) (_m_index+1);
 //BA.debugLineNum = 342;BA.debugLine="SnapCLV";
_snapclv();
 };
 //BA.debugLineNum = 344;BA.debugLine="End Sub";
return "";
}
public String  _pagechanged() throws Exception{
 //BA.debugLineNum = 448;BA.debugLine="Private Sub PageChanged";
 //BA.debugLineNum = 449;BA.debugLine="If xui.SubExists(mCallBack, mEventName & \"_PageCh";
if (_xui.SubExists(ba,_mcallback,_meventname+"_PageChanged",(int) (2))) { 
 //BA.debugLineNum = 450;BA.debugLine="CallSub3(mCallBack, mEventName & \"_PageChanged\",";
__c.CallSubNew3(ba,_mcallback,_meventname+"_PageChanged",(Object)(((int) (__c.Max(0,_m_lastindex-1)))),(Object)(((int) ((_m_index-1)))));
 };
 //BA.debugLineNum = 452;BA.debugLine="End Sub";
return "";
}
public String  _previouspage() throws Exception{
 //BA.debugLineNum = 346;BA.debugLine="Public Sub PreviousPage";
 //BA.debugLineNum = 347;BA.debugLine="If m_Index > 1 Then";
if (_m_index>1) { 
 //BA.debugLineNum = 348;BA.debugLine="m_LastIndex = m_Index";
_m_lastindex = _m_index;
 //BA.debugLineNum = 349;BA.debugLine="m_Index = m_Index -1";
_m_index = (int) (_m_index-1);
 //BA.debugLineNum = 350;BA.debugLine="SnapCLV";
_snapclv();
 };
 //BA.debugLineNum = 352;BA.debugLine="End Sub";
return "";
}
public String  _refresh() throws Exception{
 //BA.debugLineNum = 354;BA.debugLine="Public Sub Refresh";
 //BA.debugLineNum = 355;BA.debugLine="Base_Resize(mBase.Width,mBase.Height)";
_base_resize(_mbase.getWidth(),_mbase.getHeight());
 //BA.debugLineNum = 356;BA.debugLine="End Sub";
return "";
}
public String  _refreshlite() throws Exception{
int _z = 0;
 //BA.debugLineNum = 360;BA.debugLine="Public Sub RefreshLite";
 //BA.debugLineNum = 361;BA.debugLine="For z = 1 To xclv_Main.Size -2";
{
final int step1 = 1;
final int limit1 = (int) (_xclv_main._getsize()-2);
_z = (int) (1) ;
for (;_z <= limit1 ;_z = _z + step1 ) {
 //BA.debugLineNum = 362;BA.debugLine="xclv_Main.GetPanel(z).RemoveAllViews";
_xclv_main._getpanel(_z).RemoveAllViews();
 }
};
 //BA.debugLineNum = 364;BA.debugLine="xclv_Main.Refresh";
_xclv_main._refresh();
 //BA.debugLineNum = 365;BA.debugLine="End Sub";
return "";
}
public String  _removepageat(int _index) throws Exception{
 //BA.debugLineNum = 189;BA.debugLine="Public Sub RemovePageAt(Index As Int)";
 //BA.debugLineNum = 211;BA.debugLine="xclv_Main.RemoveAt(Index +1)";
_xclv_main._removeat((int) (_index+1));
 //BA.debugLineNum = 214;BA.debugLine="End Sub";
return "";
}
public String  _setcircleclip(anywheresoftware.b4a.objects.B4XViewWrapper _pnl,int _radius) throws Exception{
anywheresoftware.b4j.object.JavaObject _jo = null;
 //BA.debugLineNum = 498;BA.debugLine="Private Sub SetCircleClip (pnl As B4XView,radius A";
 //BA.debugLineNum = 512;BA.debugLine="Dim jo As JavaObject = pnl";
_jo = new anywheresoftware.b4j.object.JavaObject();
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_pnl.getObject()));
 //BA.debugLineNum = 513;BA.debugLine="jo.RunMethod(\"setClipToOutline\", Array(True))";
_jo.RunMethod("setClipToOutline",new Object[]{(Object)(__c.True)});
 //BA.debugLineNum = 515;BA.debugLine="End Sub";
return "";
}
public String  _setcornerradius(int _radius) throws Exception{
 //BA.debugLineNum = 305;BA.debugLine="Public Sub setCornerRadius(Radius As Int)";
 //BA.debugLineNum = 306;BA.debugLine="mBase.SetColorAndBorder(mBase.Color,0,xui.Color_T";
_mbase.SetColorAndBorder(_mbase.getColor(),(int) (0),_xui.Color_Transparent,_radius);
 //BA.debugLineNum = 307;BA.debugLine="g_CornerRadius = Radius";
_g_cornerradius = (float) (_radius);
 //BA.debugLineNum = 308;BA.debugLine="SetCircleClip(mBase,g_CornerRadius)";
_setcircleclip(_mbase,(int) (_g_cornerradius));
 //BA.debugLineNum = 309;BA.debugLine="End Sub";
return "";
}
public String  _setindex(int _index) throws Exception{
 //BA.debugLineNum = 384;BA.debugLine="Public Sub setIndex(Index As Int)";
 //BA.debugLineNum = 385;BA.debugLine="m_LastIndex = m_Index";
_m_lastindex = _m_index;
 //BA.debugLineNum = 386;BA.debugLine="m_Index = Index +1";
_m_index = (int) (_index+1);
 //BA.debugLineNum = 387;BA.debugLine="SnapCLV";
_snapclv();
 //BA.debugLineNum = 388;BA.debugLine="End Sub";
return "";
}
public String  _setitemwidth(float _width) throws Exception{
 //BA.debugLineNum = 318;BA.debugLine="Public Sub setItemWidth(Width As Float)";
 //BA.debugLineNum = 319;BA.debugLine="m_ItemWidth = Width";
_m_itemwidth = _width;
 //BA.debugLineNum = 320;BA.debugLine="m_SideGap = (mBase.Width - m_ItemWidth)/2";
_m_sidegap = (float) ((_mbase.getWidth()-_m_itemwidth)/(double)2);
 //BA.debugLineNum = 327;BA.debugLine="xclv_Main.Clear";
_xclv_main._clear();
 //BA.debugLineNum = 330;BA.debugLine="AddPlaceholder";
_addplaceholder();
 //BA.debugLineNum = 331;BA.debugLine="AddPlaceholder";
_addplaceholder();
 //BA.debugLineNum = 332;BA.debugLine="End Sub";
return "";
}
public String  _setlazyloading(boolean _enabled) throws Exception{
 //BA.debugLineNum = 425;BA.debugLine="Public Sub setLazyLoading(Enabled As Boolean)";
 //BA.debugLineNum = 426;BA.debugLine="m_LazyLoading = Enabled";
_m_lazyloading = _enabled;
 //BA.debugLineNum = 427;BA.debugLine="End Sub";
return "";
}
public String  _setlazyloadingextrasize(int _extrasize) throws Exception{
 //BA.debugLineNum = 417;BA.debugLine="Public Sub setLazyLoadingExtraSize(ExtraSize As In";
 //BA.debugLineNum = 418;BA.debugLine="m_LazyLoadingExtraSize = ExtraSize";
_m_lazyloadingextrasize = _extrasize;
 //BA.debugLineNum = 419;BA.debugLine="End Sub";
return "";
}
public String  _setloadingpanelcolor(int _color) throws Exception{
 //BA.debugLineNum = 408;BA.debugLine="Public Sub setLoadingPanelColor(Color As Int)";
 //BA.debugLineNum = 409;BA.debugLine="m_LoadingPanelColor = Color";
_m_loadingpanelcolor = _color;
 //BA.debugLineNum = 410;BA.debugLine="xpnl_Loading.Color = Color";
_xpnl_loading.setColor(_color);
 //BA.debugLineNum = 411;BA.debugLine="End Sub";
return "";
}
public String  _snapclv() throws Exception{
b4a.example3.customlistview._clvitem _item = null;
 //BA.debugLineNum = 483;BA.debugLine="Private Sub SnapCLV";
 //BA.debugLineNum = 484;BA.debugLine="If m_LastIndex <> m_Index Then PageChanged";
if (_m_lastindex!=_m_index) { 
_pagechanged();};
 //BA.debugLineNum = 486;BA.debugLine="Dim item As CLVItem	 = xclv_Main.GetRawListItem(m";
_item = _xclv_main._getrawlistitem(_m_index);
 //BA.debugLineNum = 488;BA.debugLine="xclv_Main.sv.As(HorizontalScrollView).ScrollPosit";
((anywheresoftware.b4a.objects.HorizontalScrollViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.HorizontalScrollViewWrapper(), (android.widget.HorizontalScrollView)(_xclv_main._sv.getObject()))).setScrollPosition((int) (_item.Offset-(_m_sidegap-__c.DipToCurrent((int) (0)))));
 //BA.debugLineNum = 496;BA.debugLine="End Sub";
return "";
}
public String  _xclv_visiblerangechanged(int _firstindex,int _lastindex) throws Exception{
b4a.example3.customlistview _xclv = null;
int _i = 0;
anywheresoftware.b4a.objects.B4XViewWrapper _p = null;
 //BA.debugLineNum = 460;BA.debugLine="Private Sub xclv_VisibleRangeChanged (FirstIndex A";
 //BA.debugLineNum = 461;BA.debugLine="Dim xclv As CustomListView = Sender";
_xclv = (b4a.example3.customlistview)(__c.Sender(ba));
 //BA.debugLineNum = 462;BA.debugLine="If m_LazyLoading = False Then Return";
if (_m_lazyloading==__c.False) { 
if (true) return "";};
 //BA.debugLineNum = 463;BA.debugLine="For i = 0 To xclv.Size - 1";
{
final int step3 = 1;
final int limit3 = (int) (_xclv._getsize()-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 464;BA.debugLine="Dim p As B4XView = xclv.GetPanel(i)";
_p = new anywheresoftware.b4a.objects.B4XViewWrapper();
_p = _xclv._getpanel(_i);
 //BA.debugLineNum = 465;BA.debugLine="If i > FirstIndex - m_LazyLoadingExtraSize And i";
if (_i>_firstindex-_m_lazyloadingextrasize && _i<_lastindex+_m_lazyloadingextrasize) { 
 //BA.debugLineNum = 467;BA.debugLine="If p.NumberOfViews = 0 Then";
if (_p.getNumberOfViews()==0) { 
 //BA.debugLineNum = 468;BA.debugLine="If \"Placeholder\" <> xclv.GetValue(i) Then";
if (("Placeholder").equals(BA.ObjectToString(_xclv._getvalue(_i))) == false) { 
 //BA.debugLineNum = 469;BA.debugLine="LazyLoadingAddContent(p,xclv.GetValue(i))";
_lazyloadingaddcontent(_p,_xclv._getvalue(_i));
 };
 };
 }else {
 //BA.debugLineNum = 474;BA.debugLine="If p.NumberOfViews > 0 Then";
if (_p.getNumberOfViews()>0) { 
 //BA.debugLineNum = 475;BA.debugLine="p.RemoveAllViews '<--- remove the layout";
_p.RemoveAllViews();
 };
 };
 }
};
 //BA.debugLineNum = 479;BA.debugLine="End Sub";
return "";
}
public boolean  _xpnl_pagearea2_touch(Object _viewtag,int _action,float _x,float _y,Object _motionevent) throws Exception{
 //BA.debugLineNum = 240;BA.debugLine="Private Sub xpnl_PageArea2_Touch(ViewTag As Object";
 //BA.debugLineNum = 241;BA.debugLine="Return HandleTouch(Action,x,y)";
if (true) return _handletouch(_action,_x,_y);
 //BA.debugLineNum = 242;BA.debugLine="End Sub";
return false;
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
