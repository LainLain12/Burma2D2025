package com.burma.royal2d;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class asviewpager extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "com.burma.royal2d.asviewpager");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", com.burma.royal2d.asviewpager.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public String _meventname = "";
public Object _mcallback = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _mbase = null;
public anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public b4a.example3.customlistview _xclv_main = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _xpnl_loading = null;
public int _m_currentindex = 0;
public float _g_x = 0f;
public float _g_y = 0f;
public float _g_dv = 0f;
public float _g_ad = 0f;
public long _g_ms = 0L;
public boolean _isfirstmove = false;
public boolean _m_ignore = false;
public boolean _g_ignorepagechangedevent = false;
public boolean _g_ignorepagechangeevent = false;
public String _m_orientation = "";
public boolean _m_carousel = false;
public boolean _m_lazyloading = false;
public int _m_lazyloadingextrasize = 0;
public int _m_loadingpanelcolor = 0;
public boolean _m_backgesturegap = false;
public boolean _m_allownext = false;
public boolean _m_allowback = false;
public int _m_maxoffset = 0;
public boolean _ismoving = false;
public boolean _g_isscrollenabled = false;
public boolean _m_ignorelazyloading = false;
public Object _tag = null;
public boolean _islazyloadingready = false;
public boolean _ishidding = false;
public int _oldindex = 0;
public int _m_loadingpanelhideduration = 0;
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
 //BA.debugLineNum = 458;BA.debugLine="Public Sub AddPage(LayoutPanel As B4XView,Value As";
 //BA.debugLineNum = 463;BA.debugLine="xclv_main.Add(LayoutPanel,Value)";
_xclv_main._add(_layoutpanel,_value);
 //BA.debugLineNum = 465;BA.debugLine="End Sub";
return "";
}
public String  _addpageat(int _index,anywheresoftware.b4a.objects.B4XViewWrapper _layoutpanel,Object _value) throws Exception{
 //BA.debugLineNum = 469;BA.debugLine="Public Sub AddPageAt(index As Int,LayoutPanel As B";
 //BA.debugLineNum = 471;BA.debugLine="If index <= m_CurrentIndex Then";
if (_index<=_m_currentindex) { 
 //BA.debugLineNum = 472;BA.debugLine="m_CurrentIndex = m_CurrentIndex +1";
_m_currentindex = (int) (_m_currentindex+1);
 };
 //BA.debugLineNum = 478;BA.debugLine="xclv_main.InsertAt(index,LayoutPanel,Value)";
_xclv_main._insertat(_index,_layoutpanel,_value);
 //BA.debugLineNum = 488;BA.debugLine="xclv_main.JumpToItem(m_CurrentIndex)";
_xclv_main._jumptoitem(_m_currentindex);
 //BA.debugLineNum = 493;BA.debugLine="End Sub";
return "";
}
public String  _allowback(boolean _allow) throws Exception{
 //BA.debugLineNum = 591;BA.debugLine="Public Sub AllowBack(Allow As Boolean)";
 //BA.debugLineNum = 592;BA.debugLine="m_AllowBack = Allow";
_m_allowback = _allow;
 //BA.debugLineNum = 598;BA.debugLine="End Sub";
return "";
}
public String  _allownext(boolean _allow) throws Exception{
 //BA.debugLineNum = 579;BA.debugLine="Public Sub AllowNext(Allow As Boolean)";
 //BA.debugLineNum = 580;BA.debugLine="m_AllowNext = Allow";
_m_allownext = _allow;
 //BA.debugLineNum = 586;BA.debugLine="End Sub";
return "";
}
public void  _base_resize(double _width,double _height) throws Exception{
ResumableSub_Base_Resize rsub = new ResumableSub_Base_Resize(this,_width,_height);
rsub.resume(ba, null);
}
public static class ResumableSub_Base_Resize extends BA.ResumableSub {
public ResumableSub_Base_Resize(com.burma.royal2d.asviewpager parent,double _width,double _height) {
this.parent = parent;
this._width = _width;
this._height = _height;
}
com.burma.royal2d.asviewpager parent;
double _width;
double _height;
int _i = 0;
int step9;
int limit9;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 395;BA.debugLine="xpnl_Loading.BringToFront";
parent._xpnl_loading.BringToFront();
 //BA.debugLineNum = 396;BA.debugLine="xpnl_Loading.SetLayoutAnimated(0,0,0,Width + 1000";
parent._xpnl_loading.SetLayoutAnimated((int) (0),(int) (0),(int) (0),(int) (_width+parent.__c.DipToCurrent((int) (1000))),(int) (_height+parent.__c.DipToCurrent((int) (1000))));
 //BA.debugLineNum = 397;BA.debugLine="xpnl_Loading.SetVisibleAnimated(0,True)";
parent._xpnl_loading.SetVisibleAnimated((int) (0),parent.__c.True);
 //BA.debugLineNum = 399;BA.debugLine="mBase.Width = Width";
parent._mbase.setWidth((int) (_width));
 //BA.debugLineNum = 400;BA.debugLine="mBase.Height = Height";
parent._mbase.setHeight((int) (_height));
 //BA.debugLineNum = 401;BA.debugLine="Sleep(0)";
parent.__c.Sleep(ba,this,(int) (0));
this.state = 16;
return;
case 16:
//C
this.state = 1;
;
 //BA.debugLineNum = 402;BA.debugLine="xclv_main.AsView.SetLayoutAnimated(0,xclv_main.As";
parent._xclv_main._asview().SetLayoutAnimated((int) (0),parent._xclv_main._asview().getLeft(),parent._xclv_main._asview().getTop(),(int) (_width),(int) (_height));
 //BA.debugLineNum = 403;BA.debugLine="xclv_main.Base_Resize(Width,Height)";
parent._xclv_main._base_resize(_width,_height);
 //BA.debugLineNum = 406;BA.debugLine="For i = 0 To xclv_main.Size -1";
if (true) break;

case 1:
//for
this.state = 10;
step9 = 1;
limit9 = (int) (parent._xclv_main._getsize()-1);
_i = (int) (0) ;
this.state = 17;
if (true) break;

case 17:
//C
this.state = 10;
if ((step9 > 0 && _i <= limit9) || (step9 < 0 && _i >= limit9)) this.state = 3;
if (true) break;

case 18:
//C
this.state = 17;
_i = ((int)(0 + _i + step9)) ;
if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 407;BA.debugLine="If m_Orientation = \"Horizontal\" Then";
if (true) break;

case 4:
//if
this.state = 9;
if ((parent._m_orientation).equals("Horizontal")) { 
this.state = 6;
}else {
this.state = 8;
}if (true) break;

case 6:
//C
this.state = 9;
 //BA.debugLineNum = 408;BA.debugLine="xclv_main.ResizeItem(i,mBase.Width)";
parent._xclv_main._resizeitem(_i,parent._mbase.getWidth());
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 410;BA.debugLine="xclv_main.ResizeItem(i,mBase.Height)";
parent._xclv_main._resizeitem(_i,parent._mbase.getHeight());
 if (true) break;

case 9:
//C
this.state = 18;
;
 if (true) break;
if (true) break;
;
 //BA.debugLineNum = 416;BA.debugLine="If xclv_main.Size > 0 Then xclv_main.JumpToItem(m";

case 10:
//if
this.state = 15;
if (parent._xclv_main._getsize()>0) { 
this.state = 12;
;}if (true) break;

case 12:
//C
this.state = 15;
parent._xclv_main._jumptoitem(parent._m_currentindex);
if (true) break;

case 15:
//C
this.state = -1;
;
 //BA.debugLineNum = 417;BA.debugLine="Sleep(250)";
parent.__c.Sleep(ba,this,(int) (250));
this.state = 19;
return;
case 19:
//C
this.state = -1;
;
 //BA.debugLineNum = 418;BA.debugLine="LoadingPanelHandling";
parent._loadingpanelhandling();
 //BA.debugLineNum = 424;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public void  _checkcarousel() throws Exception{
ResumableSub_CheckCarousel rsub = new ResumableSub_CheckCarousel(this);
rsub.resume(ba, null);
}
public static class ResumableSub_CheckCarousel extends BA.ResumableSub {
public ResumableSub_CheckCarousel(com.burma.royal2d.asviewpager parent) {
this.parent = parent;
}
com.burma.royal2d.asviewpager parent;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 898;BA.debugLine="If m_Carousel = True Then";
if (true) break;

case 1:
//if
this.state = 10;
if (parent._m_carousel==parent.__c.True) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 899;BA.debugLine="Sleep(0)";
parent.__c.Sleep(ba,this,(int) (0));
this.state = 11;
return;
case 11:
//C
this.state = 4;
;
 //BA.debugLineNum = 900;BA.debugLine="If m_CurrentIndex = xclv_main.Size -1 Then";
if (true) break;

case 4:
//if
this.state = 9;
if (parent._m_currentindex==parent._xclv_main._getsize()-1) { 
this.state = 6;
}else if(parent._m_currentindex==0) { 
this.state = 8;
}if (true) break;

case 6:
//C
this.state = 9;
 //BA.debugLineNum = 901;BA.debugLine="setCurrentIndex2(0)";
parent._setcurrentindex2((int) (0));
 if (true) break;

case 8:
//C
this.state = 9;
 //BA.debugLineNum = 903;BA.debugLine="setCurrentIndex2(xclv_main.Size -1)";
parent._setcurrentindex2((int) (parent._xclv_main._getsize()-1));
 if (true) break;

case 9:
//C
this.state = 10;
;
 if (true) break;

case 10:
//C
this.state = -1;
;
 //BA.debugLineNum = 906;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public void  _checkloadingpanelvisiblity() throws Exception{
ResumableSub_CheckLoadingPanelVisiblity rsub = new ResumableSub_CheckLoadingPanelVisiblity(this);
rsub.resume(ba, null);
}
public static class ResumableSub_CheckLoadingPanelVisiblity extends BA.ResumableSub {
public ResumableSub_CheckLoadingPanelVisiblity(com.burma.royal2d.asviewpager parent) {
this.parent = parent;
}
com.burma.royal2d.asviewpager parent;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 440;BA.debugLine="Sleep(250)";
parent.__c.Sleep(ba,this,(int) (250));
this.state = 5;
return;
case 5:
//C
this.state = 1;
;
 //BA.debugLineNum = 441;BA.debugLine="If xpnl_Loading.Visible = True And isHidding = Fa";
if (true) break;

case 1:
//if
this.state = 4;
if (parent._xpnl_loading.getVisible()==parent.__c.True && parent._ishidding==parent.__c.False) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 442;BA.debugLine="isHidding = True";
parent._ishidding = parent.__c.True;
 //BA.debugLineNum = 443;BA.debugLine="xpnl_Loading.SetVisibleAnimated(m_LoadingPanelHi";
parent._xpnl_loading.SetVisibleAnimated(parent._m_loadingpanelhideduration,parent.__c.False);
 //BA.debugLineNum = 444;BA.debugLine="Sleep(m_LoadingPanelHideDuration + 50)";
parent.__c.Sleep(ba,this,(int) (parent._m_loadingpanelhideduration+50));
this.state = 6;
return;
case 6:
//C
this.state = 4;
;
 //BA.debugLineNum = 445;BA.debugLine="isHidding = False";
parent._ishidding = parent.__c.False;
 if (true) break;

case 4:
//C
this.state = -1;
;
 //BA.debugLineNum = 448;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 176;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 177;BA.debugLine="Private mEventName As String 'ignore";
_meventname = "";
 //BA.debugLineNum = 178;BA.debugLine="Private mCallBack As Object 'ignore";
_mcallback = new Object();
 //BA.debugLineNum = 179;BA.debugLine="Private mBase As B4XView 'ignore";
_mbase = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 180;BA.debugLine="Private xui As XUI 'ignore";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 186;BA.debugLine="Private xclv_main As CustomListView";
_xclv_main = new b4a.example3.customlistview();
 //BA.debugLineNum = 190;BA.debugLine="Private xpnl_Loading As B4XView";
_xpnl_loading = new anywheresoftware.b4a.objects.B4XViewWrapper();
 //BA.debugLineNum = 193;BA.debugLine="Private m_CurrentIndex As Int = 0'ignore";
_m_currentindex = (int) (0);
 //BA.debugLineNum = 196;BA.debugLine="Private g_x,g_y,g_dv,g_ad As Float";
_g_x = 0f;
_g_y = 0f;
_g_dv = 0f;
_g_ad = 0f;
 //BA.debugLineNum = 197;BA.debugLine="Private g_ms As Long";
_g_ms = 0L;
 //BA.debugLineNum = 198;BA.debugLine="Private  isfirstmove As Boolean = False";
_isfirstmove = __c.False;
 //BA.debugLineNum = 206;BA.debugLine="Private m_Ignore As Boolean = False'Ignore";
_m_ignore = __c.False;
 //BA.debugLineNum = 207;BA.debugLine="Private g_ignorepagechangedevent As Boolean = Fal";
_g_ignorepagechangedevent = __c.False;
 //BA.debugLineNum = 208;BA.debugLine="Private g_ignorepagechangeevent As Boolean = Fals";
_g_ignorepagechangeevent = __c.False;
 //BA.debugLineNum = 209;BA.debugLine="Private m_Orientation As String";
_m_orientation = "";
 //BA.debugLineNum = 210;BA.debugLine="Private m_Carousel As Boolean";
_m_carousel = false;
 //BA.debugLineNum = 211;BA.debugLine="Private m_LazyLoading As Boolean";
_m_lazyloading = false;
 //BA.debugLineNum = 212;BA.debugLine="Private m_LazyLoadingExtraSize As Int";
_m_lazyloadingextrasize = 0;
 //BA.debugLineNum = 213;BA.debugLine="Private m_LoadingPanelColor As Int";
_m_loadingpanelcolor = 0;
 //BA.debugLineNum = 214;BA.debugLine="Private m_BackGestureGap As Boolean 'Ignore";
_m_backgesturegap = false;
 //BA.debugLineNum = 215;BA.debugLine="Private m_AllowNext As Boolean = True";
_m_allownext = __c.True;
 //BA.debugLineNum = 216;BA.debugLine="Private m_AllowBack As Boolean = True";
_m_allowback = __c.True;
 //BA.debugLineNum = 217;BA.debugLine="Private m_MaxOffset As Int = 0'Ignore";
_m_maxoffset = (int) (0);
 //BA.debugLineNum = 219;BA.debugLine="Private ismoving As Boolean = False'Ignore";
_ismoving = __c.False;
 //BA.debugLineNum = 220;BA.debugLine="Private g_isScrollEnabled As Boolean = True";
_g_isscrollenabled = __c.True;
 //BA.debugLineNum = 221;BA.debugLine="Private m_IgnoreLazyLoading As Boolean = False'Ig";
_m_ignorelazyloading = __c.False;
 //BA.debugLineNum = 223;BA.debugLine="Public Tag As Object";
_tag = new Object();
 //BA.debugLineNum = 224;BA.debugLine="Private isLazyLoadingReady As Boolean = False";
_islazyloadingready = __c.False;
 //BA.debugLineNum = 225;BA.debugLine="Private isHidding As Boolean = False";
_ishidding = __c.False;
 //BA.debugLineNum = 227;BA.debugLine="Private OldIndex As Int = 0";
_oldindex = (int) (0);
 //BA.debugLineNum = 228;BA.debugLine="Private m_LoadingPanelHideDuration As Int = 500";
_m_loadingpanelhideduration = (int) (500);
 //BA.debugLineNum = 229;BA.debugLine="End Sub";
return "";
}
public String  _clear() throws Exception{
 //BA.debugLineNum = 755;BA.debugLine="Public Sub Clear";
 //BA.debugLineNum = 757;BA.debugLine="xclv_main.Clear";
_xclv_main._clear();
 //BA.debugLineNum = 758;BA.debugLine="m_CurrentIndex = 0";
_m_currentindex = (int) (0);
 //BA.debugLineNum = 762;BA.debugLine="End Sub";
return "";
}
public String  _commit() throws Exception{
 //BA.debugLineNum = 566;BA.debugLine="Public Sub Commit";
 //BA.debugLineNum = 567;BA.debugLine="isLazyLoadingReady = True";
_islazyloadingready = __c.True;
 //BA.debugLineNum = 571;BA.debugLine="xclv_main.Refresh";
_xclv_main._refresh();
 //BA.debugLineNum = 574;BA.debugLine="End Sub";
return "";
}
public String  _designercreateview(Object _base,anywheresoftware.b4a.objects.LabelWrapper _lbl,anywheresoftware.b4a.objects.collections.Map _props) throws Exception{
 //BA.debugLineNum = 237;BA.debugLine="Public Sub DesignerCreateView (Base As Object, Lbl";
 //BA.debugLineNum = 238;BA.debugLine="mBase = Base";
_mbase = (anywheresoftware.b4a.objects.B4XViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.B4XViewWrapper(), (java.lang.Object)(_base));
 //BA.debugLineNum = 239;BA.debugLine="Tag = mBase.Tag";
_tag = _mbase.getTag();
 //BA.debugLineNum = 240;BA.debugLine="mBase.Tag = Me";
_mbase.setTag(this);
 //BA.debugLineNum = 242;BA.debugLine="xpnl_Loading = xui.CreatePanel(\"\")";
_xpnl_loading = _xui.CreatePanel(ba,"");
 //BA.debugLineNum = 243;BA.debugLine="mBase.AddView(xpnl_Loading,0,0,mBase.Width,mBase.";
_mbase.AddView((android.view.View)(_xpnl_loading.getObject()),(int) (0),(int) (0),_mbase.getWidth(),_mbase.getHeight());
 //BA.debugLineNum = 244;BA.debugLine="xpnl_Loading.Color = xui.Color_Black";
_xpnl_loading.setColor(_xui.Color_Black);
 //BA.debugLineNum = 245;BA.debugLine="xpnl_Loading.BringToFront";
_xpnl_loading.BringToFront();
 //BA.debugLineNum = 247;BA.debugLine="m_Orientation = Props.GetDefault(\"Orientation\",\"H";
_m_orientation = BA.ObjectToString(_props.GetDefault((Object)("Orientation"),(Object)("Horizontal")));
 //BA.debugLineNum = 248;BA.debugLine="m_Carousel = Props.GetDefault(\"Carousel\",False)";
_m_carousel = BA.ObjectToBoolean(_props.GetDefault((Object)("Carousel"),(Object)(__c.False)));
 //BA.debugLineNum = 249;BA.debugLine="m_LazyLoading = Props.GetDefault(\"LazyLoading\",Fa";
_m_lazyloading = BA.ObjectToBoolean(_props.GetDefault((Object)("LazyLoading"),(Object)(__c.False)));
 //BA.debugLineNum = 250;BA.debugLine="m_LazyLoadingExtraSize = Props.GetDefault(\"LazyLo";
_m_lazyloadingextrasize = (int)(BA.ObjectToNumber(_props.GetDefault((Object)("LazyLoadingExtraSize"),(Object)(5))));
 //BA.debugLineNum = 251;BA.debugLine="m_LoadingPanelColor = xui.PaintOrColorToColor(Pro";
_m_loadingpanelcolor = _xui.PaintOrColorToColor(_props.GetDefault((Object)("LoadingPanelColor"),(Object)(_xui.Color_Black)));
 //BA.debugLineNum = 252;BA.debugLine="xpnl_Loading.Color = m_LoadingPanelColor";
_xpnl_loading.setColor(_m_loadingpanelcolor);
 //BA.debugLineNum = 253;BA.debugLine="m_BackGestureGap = Props.GetDefault(\"BackGestureG";
_m_backgesturegap = BA.ObjectToBoolean(_props.GetDefault((Object)("BackGestureGap"),(Object)(__c.False)));
 //BA.debugLineNum = 256;BA.debugLine="If xclv_main.IsInitialized = False Then";
if (_xclv_main.IsInitialized()==__c.False) { 
 //BA.debugLineNum = 257;BA.debugLine="ini_xclv";
_ini_xclv();
 };
 //BA.debugLineNum = 266;BA.debugLine="Base_Resize(mBase.Width,mBase.Height)";
_base_resize(_mbase.getWidth(),_mbase.getHeight());
 //BA.debugLineNum = 284;BA.debugLine="End Sub";
return "";
}
public String  _ff(float _x,float _y) throws Exception{
 //BA.debugLineNum = 796;BA.debugLine="Private Sub ff(x As Float,y As Float)";
 //BA.debugLineNum = 797;BA.debugLine="isfirstmove = True";
_isfirstmove = __c.True;
 //BA.debugLineNum = 798;BA.debugLine="g_ms=DateTime.Now";
_g_ms = __c.DateTime.getNow();
 //BA.debugLineNum = 799;BA.debugLine="g_x=X";
_g_x = _x;
 //BA.debugLineNum = 800;BA.debugLine="g_y=Y";
_g_y = _y;
 //BA.debugLineNum = 801;BA.debugLine="If m_Orientation = \"Horizontal\" Then";
if ((_m_orientation).equals("Horizontal")) { 
 //BA.debugLineNum = 802;BA.debugLine="g_dv = (mBase.Width)/2";
_g_dv = (float) ((_mbase.getWidth())/(double)2);
 }else {
 //BA.debugLineNum = 804;BA.debugLine="g_dv = (mBase.Height)/2";
_g_dv = (float) ((_mbase.getHeight())/(double)2);
 };
 //BA.debugLineNum = 806;BA.debugLine="g_ad = 0";
_g_ad = (float) (0);
 //BA.debugLineNum = 807;BA.debugLine="End Sub";
return "";
}
public anywheresoftware.b4a.objects.B4XViewWrapper  _getbase() throws Exception{
 //BA.debugLineNum = 454;BA.debugLine="Public Sub getBase As B4XView";
 //BA.debugLineNum = 455;BA.debugLine="Return mBase";
if (true) return _mbase;
 //BA.debugLineNum = 456;BA.debugLine="End Sub";
return null;
}
public int  _getcurrentindex() throws Exception{
 //BA.debugLineNum = 689;BA.debugLine="Public Sub getCurrentIndex As Int";
 //BA.debugLineNum = 693;BA.debugLine="Return m_CurrentIndex";
if (true) return _m_currentindex;
 //BA.debugLineNum = 695;BA.debugLine="End Sub";
return 0;
}
public b4a.example3.customlistview  _getcustomlistview() throws Exception{
 //BA.debugLineNum = 730;BA.debugLine="Public Sub getCustomListView As CustomListView";
 //BA.debugLineNum = 731;BA.debugLine="Return xclv_main";
if (true) return _xclv_main;
 //BA.debugLineNum = 732;BA.debugLine="End Sub";
return null;
}
public boolean  _getisscrollenabled() throws Exception{
 //BA.debugLineNum = 642;BA.debugLine="Public Sub getisScrollEnabled As Boolean";
 //BA.debugLineNum = 643;BA.debugLine="Return g_isScrollEnabled";
if (true) return _g_isscrollenabled;
 //BA.debugLineNum = 644;BA.debugLine="End Sub";
return false;
}
public boolean  _getlazyloading() throws Exception{
 //BA.debugLineNum = 618;BA.debugLine="Public Sub getLazyLoading As Boolean";
 //BA.debugLineNum = 619;BA.debugLine="Return m_LazyLoading";
if (true) return _m_lazyloading;
 //BA.debugLineNum = 620;BA.debugLine="End Sub";
return false;
}
public int  _getlazyloadingextrasize() throws Exception{
 //BA.debugLineNum = 629;BA.debugLine="Public Sub getLazyLoadingExtraSize As Int";
 //BA.debugLineNum = 630;BA.debugLine="Return m_LazyLoadingExtraSize";
if (true) return _m_lazyloadingextrasize;
 //BA.debugLineNum = 631;BA.debugLine="End Sub";
return 0;
}
public int  _getloadingpanelcolor() throws Exception{
 //BA.debugLineNum = 607;BA.debugLine="Public Sub getLoadingPanelColor As Int";
 //BA.debugLineNum = 608;BA.debugLine="Return m_LoadingPanelColor";
if (true) return _m_loadingpanelcolor;
 //BA.debugLineNum = 609;BA.debugLine="End Sub";
return 0;
}
public anywheresoftware.b4a.objects.B4XViewWrapper  _getpanel(int _index) throws Exception{
 //BA.debugLineNum = 747;BA.debugLine="Public Sub GetPanel(Index As Int) As B4XView";
 //BA.debugLineNum = 751;BA.debugLine="Return xclv_main.GetPanel(Index)";
if (true) return _xclv_main._getpanel(_index);
 //BA.debugLineNum = 753;BA.debugLine="End Sub";
return null;
}
public int  _getsize() throws Exception{
 //BA.debugLineNum = 721;BA.debugLine="Public Sub getSize As Int";
 //BA.debugLineNum = 725;BA.debugLine="Return xclv_main.Size";
if (true) return _xclv_main._getsize();
 //BA.debugLineNum = 727;BA.debugLine="End Sub";
return 0;
}
public Object  _getvalue(int _index) throws Exception{
 //BA.debugLineNum = 739;BA.debugLine="Public Sub GetValue(Index As Int) As Object";
 //BA.debugLineNum = 743;BA.debugLine="Return xclv_main.GetValue(Index)";
if (true) return _xclv_main._getvalue(_index);
 //BA.debugLineNum = 745;BA.debugLine="End Sub";
return null;
}
public boolean  _handletouch(int _action,float _x,float _y) throws Exception{
int _i = 0;
 //BA.debugLineNum = 809;BA.debugLine="Private Sub HandleTouch(Action As Int,x As Float,y";
 //BA.debugLineNum = 815;BA.debugLine="Select Action";
switch (_action) {
case 2: {
 //BA.debugLineNum = 817;BA.debugLine="TouchBeginEvent";
_touchbeginevent();
 //BA.debugLineNum = 818;BA.debugLine="ismoving = True";
_ismoving = __c.True;
 //BA.debugLineNum = 819;BA.debugLine="If isfirstmove = False Then";
if (_isfirstmove==__c.False) { 
 //BA.debugLineNum = 821;BA.debugLine="ff(X,y)";
_ff(_x,_y);
 }else {
 };
 break; }
case 1: {
 //BA.debugLineNum = 828;BA.debugLine="TouchEndEvent";
_touchendevent();
 //BA.debugLineNum = 829;BA.debugLine="ismoving = False";
_ismoving = __c.False;
 //BA.debugLineNum = 830;BA.debugLine="isfirstmove = False";
_isfirstmove = __c.False;
 //BA.debugLineNum = 832;BA.debugLine="If m_Orientation = \"Horizontal\" Then";
if ((_m_orientation).equals("Horizontal")) { 
 //BA.debugLineNum = 833;BA.debugLine="If ((DateTime.Now-g_ms)<300) And (Abs(X - g_x)";
if (((__c.DateTime.getNow()-_g_ms)<300) && (__c.Abs(_x-_g_x)>(__c.DipToCurrent((int) (1))))) { 
 //BA.debugLineNum = 835;BA.debugLine="g_dv =1dip";
_g_dv = (float) (__c.DipToCurrent((int) (1)));
 //BA.debugLineNum = 837;BA.debugLine="If X < g_x Then g_ad = 1";
if (_x<_g_x) { 
_g_ad = (float) (1);};
 };
 }else {
 //BA.debugLineNum = 841;BA.debugLine="If ((DateTime.Now-g_ms)<300)  And (Abs(y - g_y";
if (((__c.DateTime.getNow()-_g_ms)<300) && (__c.Abs(_y-_g_y)>(__c.DipToCurrent((int) (1))))) { 
 //BA.debugLineNum = 842;BA.debugLine="g_dv =1dip";
_g_dv = (float) (__c.DipToCurrent((int) (1)));
 //BA.debugLineNum = 844;BA.debugLine="If y < g_y Then g_ad = 1";
if (_y<_g_y) { 
_g_ad = (float) (1);};
 };
 };
 //BA.debugLineNum = 849;BA.debugLine="For i = 0 To xclv_main.Size -1";
{
final int step24 = 1;
final int limit24 = (int) (_xclv_main._getsize()-1);
_i = (int) (0) ;
for (;_i <= limit24 ;_i = _i + step24 ) {
 //BA.debugLineNum = 850;BA.debugLine="If m_Orientation = \"Horizontal\" Then";
if ((_m_orientation).equals("Horizontal")) { 
 //BA.debugLineNum = 851;BA.debugLine="If (((i+1)*mBase.Width) - xclv_main.sv.Scroll";
if ((((_i+1)*_mbase.getWidth())-_xclv_main._sv.getScrollViewOffsetX())>(_g_dv)) { 
 //BA.debugLineNum = 853;BA.debugLine="If (i+g_ad) > (xclv_main.Size -1) Or xclv_ma";
if ((_i+_g_ad)>(_xclv_main._getsize()-1) || _xclv_main._sv.getScrollViewOffsetX()<=0) { 
 //BA.debugLineNum = 854;BA.debugLine="CheckCarousel";
_checkcarousel();
 //BA.debugLineNum = 855;BA.debugLine="Return False";
if (true) return __c.False;
 };
 //BA.debugLineNum = 858;BA.debugLine="If isNumberOneCloserToZero(g_x - x,g_y - y)";
if (_isnumberoneclosertozero((int) (_g_x-_x),(int) (_g_y-_y))==__c.False) { 
 //BA.debugLineNum = 859;BA.debugLine="xclv_main.ScrollToItem(m_CurrentIndex)";
_xclv_main._scrolltoitem(_m_currentindex);
 //BA.debugLineNum = 860;BA.debugLine="Return True";
if (true) return __c.True;
 };
 //BA.debugLineNum = 862;BA.debugLine="xclv_main.ScrollToItem(i+g_ad)";
_xclv_main._scrolltoitem((int) (_i+_g_ad));
 //BA.debugLineNum = 863;BA.debugLine="If m_CurrentIndex <> (i+g_ad) Then PageChang";
if (_m_currentindex!=(_i+_g_ad)) { 
_pagechangedevent((int) (_i+_g_ad),__c.True);};
 //BA.debugLineNum = 867;BA.debugLine="Return True";
if (true) return __c.True;
 };
 }else {
 //BA.debugLineNum = 871;BA.debugLine="If (((i+1)*mBase.Height) - xclv_main.sv.Scrol";
if ((((_i+1)*_mbase.getHeight())-_xclv_main._sv.getScrollViewOffsetY())>(_g_dv)) { 
 //BA.debugLineNum = 873;BA.debugLine="If (i+g_ad) > xclv_main.Size-1 Or  xclv_main";
if ((_i+_g_ad)>_xclv_main._getsize()-1 || _xclv_main._sv.getScrollViewOffsetY()<=0) { 
 //BA.debugLineNum = 874;BA.debugLine="CheckCarousel";
_checkcarousel();
 //BA.debugLineNum = 875;BA.debugLine="Return False";
if (true) return __c.False;
 };
 //BA.debugLineNum = 878;BA.debugLine="If isNumberOneCloserToZero(g_y - y,g_x - x)";
if (_isnumberoneclosertozero((int) (_g_y-_y),(int) (_g_x-_x))==__c.False) { 
 //BA.debugLineNum = 879;BA.debugLine="xclv_main.ScrollToItem(m_CurrentIndex)";
_xclv_main._scrolltoitem(_m_currentindex);
 //BA.debugLineNum = 880;BA.debugLine="Return True";
if (true) return __c.True;
 };
 //BA.debugLineNum = 882;BA.debugLine="xclv_main.ScrollToItem(i+g_ad)";
_xclv_main._scrolltoitem((int) (_i+_g_ad));
 //BA.debugLineNum = 883;BA.debugLine="If m_CurrentIndex <> (i+g_ad) Then PageChang";
if (_m_currentindex!=(_i+_g_ad)) { 
_pagechangedevent((int) (_i+_g_ad),__c.True);};
 //BA.debugLineNum = 887;BA.debugLine="Return True";
if (true) return __c.True;
 };
 };
 }
};
 break; }
}
;
 //BA.debugLineNum = 892;BA.debugLine="Return False";
if (true) return __c.False;
 //BA.debugLineNum = 893;BA.debugLine="End Sub";
return false;
}
public String  _ini_xclv() throws Exception{
anywheresoftware.b4a.objects.LabelWrapper _tmplbl = null;
anywheresoftware.b4a.objects.collections.Map _tmpmap = null;
anywheresoftware.b4a.agraham.reflection.Reflection _objpages = null;
 //BA.debugLineNum = 287;BA.debugLine="Private Sub ini_xclv";
 //BA.debugLineNum = 288;BA.debugLine="Dim tmplbl As Label";
_tmplbl = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 289;BA.debugLine="tmplbl.Initialize(\"\")";
_tmplbl.Initialize(ba,"");
 //BA.debugLineNum = 291;BA.debugLine="Dim tmpmap As Map";
_tmpmap = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 292;BA.debugLine="tmpmap.Initialize";
_tmpmap.Initialize();
 //BA.debugLineNum = 293;BA.debugLine="tmpmap.Put(\"DividerColor\",0x00FFFFFF)";
_tmpmap.Put((Object)("DividerColor"),(Object)(((int)0x00ffffff)));
 //BA.debugLineNum = 294;BA.debugLine="tmpmap.Put(\"DividerHeight\",0)";
_tmpmap.Put((Object)("DividerHeight"),(Object)(0));
 //BA.debugLineNum = 295;BA.debugLine="tmpmap.Put(\"PressedColor\",0x00FFFFFF)";
_tmpmap.Put((Object)("PressedColor"),(Object)(((int)0x00ffffff)));
 //BA.debugLineNum = 296;BA.debugLine="tmpmap.Put(\"InsertAnimationDuration\",0)";
_tmpmap.Put((Object)("InsertAnimationDuration"),(Object)(0));
 //BA.debugLineNum = 297;BA.debugLine="tmpmap.Put(\"ListOrientation\",m_Orientation)";
_tmpmap.Put((Object)("ListOrientation"),(Object)(_m_orientation));
 //BA.debugLineNum = 298;BA.debugLine="tmpmap.Put(\"ShowScrollBar\",False)";
_tmpmap.Put((Object)("ShowScrollBar"),(Object)(__c.False));
 //BA.debugLineNum = 299;BA.debugLine="xclv_main.Initialize(Me,\"xclv_main\")";
_xclv_main._initialize(ba,this,"xclv_main");
 //BA.debugLineNum = 300;BA.debugLine="xclv_main.DesignerCreateView(mBase,tmplbl,tmpmap)";
_xclv_main._designercreateview((Object)(_mbase.getObject()),_tmplbl,_tmpmap);
 //BA.debugLineNum = 302;BA.debugLine="Private objPages As Reflector";
_objpages = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 303;BA.debugLine="objPages.Target = xclv_main.sv";
_objpages.Target = (Object)(_xclv_main._sv.getObject());
 //BA.debugLineNum = 304;BA.debugLine="objPages.SetOnTouchListener(\"xpnl_PageArea2_Touch";
_objpages.SetOnTouchListener(ba,"xpnl_PageArea2_Touch");
 //BA.debugLineNum = 305;BA.debugLine="xclv_main.AsView.SetLayoutAnimated(0,xclv_main.As";
_xclv_main._asview().SetLayoutAnimated((int) (0),_xclv_main._asview().getLeft(),_xclv_main._asview().getTop(),_mbase.getWidth(),_mbase.getHeight());
 //BA.debugLineNum = 306;BA.debugLine="xclv_main.Base_Resize(mBase.Width,mBase.Height)";
_xclv_main._base_resize(_mbase.getWidth(),_mbase.getHeight());
 //BA.debugLineNum = 330;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba,Object _callback,String _eventname) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 231;BA.debugLine="Public Sub Initialize (Callback As Object, EventNa";
 //BA.debugLineNum = 232;BA.debugLine="mEventName = EventName";
_meventname = _eventname;
 //BA.debugLineNum = 233;BA.debugLine="mCallBack = Callback";
_mcallback = _callback;
 //BA.debugLineNum = 234;BA.debugLine="End Sub";
return "";
}
public boolean  _isnumberoneclosertozero(int _numberone,int _numbertwo) throws Exception{
 //BA.debugLineNum = 1214;BA.debugLine="Private Sub isNumberOneCloserToZero (numberOne As";
 //BA.debugLineNum = 1215;BA.debugLine="Return (Abs(numberOne) >= Abs(numberTwo))";
if (true) return (__c.Abs(_numberone)>=__c.Abs(_numbertwo));
 //BA.debugLineNum = 1216;BA.debugLine="End Sub";
return false;
}
public String  _lazyloadingaddcontent(anywheresoftware.b4a.objects.B4XViewWrapper _parent,Object _value) throws Exception{
 //BA.debugLineNum = 1158;BA.debugLine="Private Sub LazyLoadingAddContent(Parent As B4XVie";
 //BA.debugLineNum = 1159;BA.debugLine="If isLazyLoadingReady = True And xui.SubExists(mC";
if (_islazyloadingready==__c.True && _xui.SubExists(ba,_mcallback,_meventname+"_LazyLoadingAddContent",(int) (2))) { 
 //BA.debugLineNum = 1160;BA.debugLine="CallSub3(mCallBack, mEventName & \"_LazyLoadingAd";
__c.CallSubNew3(ba,_mcallback,_meventname+"_LazyLoadingAddContent",(Object)(_parent),_value);
 };
 //BA.debugLineNum = 1162;BA.debugLine="End Sub";
return "";
}
public void  _loadingpanelhandling() throws Exception{
ResumableSub_LoadingPanelHandling rsub = new ResumableSub_LoadingPanelHandling(this);
rsub.resume(ba, null);
}
public static class ResumableSub_LoadingPanelHandling extends BA.ResumableSub {
public ResumableSub_LoadingPanelHandling(com.burma.royal2d.asviewpager parent) {
this.parent = parent;
}
com.burma.royal2d.asviewpager parent;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 427;BA.debugLine="If isHidding = False Then";
if (true) break;

case 1:
//if
this.state = 4;
if (parent._ishidding==parent.__c.False) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 428;BA.debugLine="isHidding = True";
parent._ishidding = parent.__c.True;
 //BA.debugLineNum = 430;BA.debugLine="xpnl_Loading.SetVisibleAnimated(m_LoadingPanelHi";
parent._xpnl_loading.SetVisibleAnimated(parent._m_loadingpanelhideduration,parent.__c.False);
 //BA.debugLineNum = 431;BA.debugLine="Sleep(m_LoadingPanelHideDuration + 50)";
parent.__c.Sleep(ba,this,(int) (parent._m_loadingpanelhideduration+50));
this.state = 5;
return;
case 5:
//C
this.state = 4;
;
 //BA.debugLineNum = 432;BA.debugLine="isHidding = False";
parent._ishidding = parent.__c.False;
 if (true) break;

case 4:
//C
this.state = -1;
;
 //BA.debugLineNum = 434;BA.debugLine="Sleep(0)";
parent.__c.Sleep(ba,this,(int) (0));
this.state = 6;
return;
case 6:
//C
this.state = -1;
;
 //BA.debugLineNum = 435;BA.debugLine="CheckLoadingPanelVisiblity";
parent._checkloadingpanelvisiblity();
 //BA.debugLineNum = 436;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public String  _nextpage() throws Exception{
 //BA.debugLineNum = 537;BA.debugLine="Public Sub NextPage";
 //BA.debugLineNum = 538;BA.debugLine="If m_AllowNext = False Then Return";
if (_m_allownext==__c.False) { 
if (true) return "";};
 //BA.debugLineNum = 542;BA.debugLine="If (m_CurrentIndex +1) < xclv_main.Size Then";
if ((_m_currentindex+1)<_xclv_main._getsize()) { 
 //BA.debugLineNum = 543;BA.debugLine="xclv_main.ScrollToItem(m_CurrentIndex +1)";
_xclv_main._scrolltoitem((int) (_m_currentindex+1));
 //BA.debugLineNum = 544;BA.debugLine="PageChangedEvent(m_CurrentIndex +1,True)";
_pagechangedevent((int) (_m_currentindex+1),__c.True);
 }else if(_m_carousel==__c.True) { 
 //BA.debugLineNum = 546;BA.debugLine="CheckCarousel";
_checkcarousel();
 };
 //BA.debugLineNum = 549;BA.debugLine="End Sub";
return "";
}
public String  _nextpage2() throws Exception{
 //BA.debugLineNum = 551;BA.debugLine="Public Sub NextPage2";
 //BA.debugLineNum = 552;BA.debugLine="If m_AllowNext = False Then Return";
if (_m_allownext==__c.False) { 
if (true) return "";};
 //BA.debugLineNum = 556;BA.debugLine="If (m_CurrentIndex +1) < xclv_main.Size Then";
if ((_m_currentindex+1)<_xclv_main._getsize()) { 
 //BA.debugLineNum = 558;BA.debugLine="xclv_main.JumpToItem(m_CurrentIndex +1)";
_xclv_main._jumptoitem((int) (_m_currentindex+1));
 //BA.debugLineNum = 559;BA.debugLine="PageChangedEvent(m_CurrentIndex +1,False)";
_pagechangedevent((int) (_m_currentindex+1),__c.False);
 }else if(_m_carousel==__c.True) { 
 //BA.debugLineNum = 561;BA.debugLine="CheckCarousel";
_checkcarousel();
 };
 //BA.debugLineNum = 564;BA.debugLine="End Sub";
return "";
}
public void  _pagechangedevent(int _index,boolean _delayed) throws Exception{
ResumableSub_PageChangedEvent rsub = new ResumableSub_PageChangedEvent(this,_index,_delayed);
rsub.resume(ba, null);
}
public static class ResumableSub_PageChangedEvent extends BA.ResumableSub {
public ResumableSub_PageChangedEvent(com.burma.royal2d.asviewpager parent,int _index,boolean _delayed) {
this.parent = parent;
this._index = _index;
this._delayed = _delayed;
}
com.burma.royal2d.asviewpager parent;
int _index;
boolean _delayed;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 1165;BA.debugLine="If m_CurrentIndex = index Then Return";
if (true) break;

case 1:
//if
this.state = 6;
if (parent._m_currentindex==_index) { 
this.state = 3;
;}if (true) break;

case 3:
//C
this.state = 6;
if (true) return ;
if (true) break;

case 6:
//C
this.state = 7;
;
 //BA.debugLineNum = 1166;BA.debugLine="m_CurrentIndex = index";
parent._m_currentindex = _index;
 //BA.debugLineNum = 1167;BA.debugLine="If g_ignorepagechangeevent = False And xui.SubExi";
if (true) break;

case 7:
//if
this.state = 10;
if (parent._g_ignorepagechangeevent==parent.__c.False && parent._xui.SubExists(ba,parent._mcallback,parent._meventname+"_PageChange",(int) (1))) { 
this.state = 9;
}if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 1168;BA.debugLine="CallSub2(mCallBack, mEventName & \"_PageChange\",i";
parent.__c.CallSubNew2(ba,parent._mcallback,parent._meventname+"_PageChange",(Object)(_index));
 if (true) break;

case 10:
//C
this.state = 11;
;
 //BA.debugLineNum = 1171;BA.debugLine="m_MaxOffset = xclv_main.GetRawListItem(index).Off";
parent._m_maxoffset = parent._xclv_main._getrawlistitem(_index).Offset;
 //BA.debugLineNum = 1173;BA.debugLine="If delayed = True Then Sleep(300)";
if (true) break;

case 11:
//if
this.state = 16;
if (_delayed==parent.__c.True) { 
this.state = 13;
;}if (true) break;

case 13:
//C
this.state = 16;
parent.__c.Sleep(ba,this,(int) (300));
this.state = 24;
return;
case 24:
//C
this.state = 16;
;
if (true) break;

case 16:
//C
this.state = 17;
;
 //BA.debugLineNum = 1175;BA.debugLine="m_MaxOffset = xclv_main.GetRawListItem(index).Off";
parent._m_maxoffset = parent._xclv_main._getrawlistitem(_index).Offset;
 //BA.debugLineNum = 1177;BA.debugLine="If g_ignorepagechangedevent = False And xui.SubEx";
if (true) break;

case 17:
//if
this.state = 20;
if (parent._g_ignorepagechangedevent==parent.__c.False && parent._xui.SubExists(ba,parent._mcallback,parent._meventname+"_PageChanged",(int) (1))) { 
this.state = 19;
}if (true) break;

case 19:
//C
this.state = 20;
 //BA.debugLineNum = 1178;BA.debugLine="CallSub2(mCallBack, mEventName & \"_PageChanged\",";
parent.__c.CallSubNew2(ba,parent._mcallback,parent._meventname+"_PageChanged",(Object)(_index));
 if (true) break;
;
 //BA.debugLineNum = 1180;BA.debugLine="If g_ignorepagechangedevent = False And xui.SubEx";

case 20:
//if
this.state = 23;
if (parent._g_ignorepagechangedevent==parent.__c.False && parent._xui.SubExists(ba,parent._mcallback,parent._meventname+"_PageChanged2",(int) (2))) { 
this.state = 22;
}if (true) break;

case 22:
//C
this.state = 23;
 //BA.debugLineNum = 1181;BA.debugLine="CallSub3(mCallBack, mEventName & \"_PageChanged2\"";
parent.__c.CallSubNew3(ba,parent._mcallback,parent._meventname+"_PageChanged2",(Object)(_index),(Object)(parent._oldindex));
 if (true) break;

case 23:
//C
this.state = -1;
;
 //BA.debugLineNum = 1183;BA.debugLine="OldIndex = index";
parent._oldindex = _index;
 //BA.debugLineNum = 1184;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public String  _previouspage() throws Exception{
 //BA.debugLineNum = 508;BA.debugLine="Public Sub PreviousPage";
 //BA.debugLineNum = 509;BA.debugLine="If m_AllowBack = False Then Return";
if (_m_allowback==__c.False) { 
if (true) return "";};
 //BA.debugLineNum = 513;BA.debugLine="If (m_CurrentIndex -1) > -1 Then";
if ((_m_currentindex-1)>-1) { 
 //BA.debugLineNum = 514;BA.debugLine="xclv_main.ScrollToItem(m_CurrentIndex -1)";
_xclv_main._scrolltoitem((int) (_m_currentindex-1));
 //BA.debugLineNum = 516;BA.debugLine="PageChangedEvent(m_CurrentIndex -1,True)";
_pagechangedevent((int) (_m_currentindex-1),__c.True);
 }else if(_m_carousel==__c.True) { 
 //BA.debugLineNum = 518;BA.debugLine="CheckCarousel";
_checkcarousel();
 };
 //BA.debugLineNum = 521;BA.debugLine="End Sub";
return "";
}
public String  _previouspage2() throws Exception{
 //BA.debugLineNum = 523;BA.debugLine="Public Sub PreviousPage2";
 //BA.debugLineNum = 524;BA.debugLine="If m_AllowBack = False Then Return";
if (_m_allowback==__c.False) { 
if (true) return "";};
 //BA.debugLineNum = 528;BA.debugLine="If (m_CurrentIndex -1) > -1 Then";
if ((_m_currentindex-1)>-1) { 
 //BA.debugLineNum = 529;BA.debugLine="xclv_main.JumpToItem(m_CurrentIndex -1)";
_xclv_main._jumptoitem((int) (_m_currentindex-1));
 //BA.debugLineNum = 530;BA.debugLine="PageChangedEvent(m_CurrentIndex -1,False)";
_pagechangedevent((int) (_m_currentindex-1),__c.False);
 }else if(_m_carousel==__c.True) { 
 //BA.debugLineNum = 532;BA.debugLine="CheckCarousel";
_checkcarousel();
 };
 //BA.debugLineNum = 535;BA.debugLine="End Sub";
return "";
}
public String  _removepage(int _index) throws Exception{
 //BA.debugLineNum = 495;BA.debugLine="Public Sub RemovePage(index As Int)";
 //BA.debugLineNum = 497;BA.debugLine="xclv_main.RemoveAt(index)";
_xclv_main._removeat(_index);
 //BA.debugLineNum = 498;BA.debugLine="If m_Orientation = \"Horizontal\" Then";
if ((_m_orientation).equals("Horizontal")) { 
 //BA.debugLineNum = 499;BA.debugLine="m_CurrentIndex = xclv_main.FindIndexFromOffset(x";
_m_currentindex = _xclv_main._findindexfromoffset(_xclv_main._sv.getScrollViewOffsetX());
 }else {
 //BA.debugLineNum = 501;BA.debugLine="m_CurrentIndex = xclv_main.FindIndexFromOffset(x";
_m_currentindex = _xclv_main._findindexfromoffset(_xclv_main._sv.getScrollViewOffsetY());
 };
 //BA.debugLineNum = 506;BA.debugLine="End Sub";
return "";
}
public String  _resetlazyloadingindex() throws Exception{
 //BA.debugLineNum = 1093;BA.debugLine="Public Sub ResetLazyloadingIndex";
 //BA.debugLineNum = 1095;BA.debugLine="End Sub";
return "";
}
public String  _resetlazyloadingpanels() throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.B4XViewWrapper _p = null;
 //BA.debugLineNum = 770;BA.debugLine="Public Sub ResetLazyLoadingPanels";
 //BA.debugLineNum = 780;BA.debugLine="For i = 0 To xclv_main.Size - 1";
{
final int step1 = 1;
final int limit1 = (int) (_xclv_main._getsize()-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 781;BA.debugLine="Dim p As B4XView = xclv_main.GetPanel(i)";
_p = new anywheresoftware.b4a.objects.B4XViewWrapper();
_p = _xclv_main._getpanel(_i);
 //BA.debugLineNum = 783;BA.debugLine="If p.NumberOfViews > 0 Then";
if (_p.getNumberOfViews()>0) { 
 //BA.debugLineNum = 784;BA.debugLine="p.RemoveAllViews '<--- remove the layout";
_p.RemoveAllViews();
 };
 }
};
 //BA.debugLineNum = 788;BA.debugLine="End Sub";
return "";
}
public String  _scroll2value(String _value) throws Exception{
int _i = 0;
 //BA.debugLineNum = 653;BA.debugLine="Public Sub Scroll2Value(Value As String)";
 //BA.debugLineNum = 662;BA.debugLine="For i = 0 To xclv_main.Size -1";
{
final int step1 = 1;
final int limit1 = (int) (_xclv_main._getsize()-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 663;BA.debugLine="If xclv_main.GetValue(i) Is String And Value = x";
if (_xclv_main._getvalue(_i) instanceof String && (_value).equals(BA.ObjectToString(_xclv_main._getvalue(_i)))) { 
 //BA.debugLineNum = 664;BA.debugLine="setCurrentIndex(i)";
_setcurrentindex(_i);
 //BA.debugLineNum = 665;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 //BA.debugLineNum = 669;BA.debugLine="End Sub";
return "";
}
public String  _scroll2value2(String _value) throws Exception{
int _i = 0;
 //BA.debugLineNum = 671;BA.debugLine="Public Sub Scroll2Value2(Value As String)";
 //BA.debugLineNum = 680;BA.debugLine="For i = 0 To xclv_main.Size -1";
{
final int step1 = 1;
final int limit1 = (int) (_xclv_main._getsize()-1);
_i = (int) (0) ;
for (;_i <= limit1 ;_i = _i + step1 ) {
 //BA.debugLineNum = 681;BA.debugLine="If xclv_main.GetValue(i) Is String And Value = x";
if (_xclv_main._getvalue(_i) instanceof String && (_value).equals(BA.ObjectToString(_xclv_main._getvalue(_i)))) { 
 //BA.debugLineNum = 682;BA.debugLine="setCurrentIndex2(i)";
_setcurrentindex2(_i);
 //BA.debugLineNum = 683;BA.debugLine="Exit";
if (true) break;
 };
 }
};
 //BA.debugLineNum = 687;BA.debugLine="End Sub";
return "";
}
public void  _setcurrentindex(int _index) throws Exception{
ResumableSub_setCurrentIndex rsub = new ResumableSub_setCurrentIndex(this,_index);
rsub.resume(ba, null);
}
public static class ResumableSub_setCurrentIndex extends BA.ResumableSub {
public ResumableSub_setCurrentIndex(com.burma.royal2d.asviewpager parent,int _index) {
this.parent = parent;
this._index = _index;
}
com.burma.royal2d.asviewpager parent;
int _index;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
 //BA.debugLineNum = 701;BA.debugLine="PageChangedEvent(index,True)";
parent._pagechangedevent(_index,parent.__c.True);
 //BA.debugLineNum = 702;BA.debugLine="m_Ignore = True";
parent._m_ignore = parent.__c.True;
 //BA.debugLineNum = 703;BA.debugLine="xclv_main.ScrollToItem(index)";
parent._xclv_main._scrolltoitem(_index);
 //BA.debugLineNum = 704;BA.debugLine="Sleep(250)";
parent.__c.Sleep(ba,this,(int) (250));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 //BA.debugLineNum = 705;BA.debugLine="m_Ignore = False";
parent._m_ignore = parent.__c.False;
 //BA.debugLineNum = 707;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public void  _setcurrentindex2(int _index) throws Exception{
ResumableSub_setCurrentIndex2 rsub = new ResumableSub_setCurrentIndex2(this,_index);
rsub.resume(ba, null);
}
public static class ResumableSub_setCurrentIndex2 extends BA.ResumableSub {
public ResumableSub_setCurrentIndex2(com.burma.royal2d.asviewpager parent,int _index) {
this.parent = parent;
this._index = _index;
}
com.burma.royal2d.asviewpager parent;
int _index;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = -1;
 //BA.debugLineNum = 713;BA.debugLine="m_Ignore = True";
parent._m_ignore = parent.__c.True;
 //BA.debugLineNum = 714;BA.debugLine="PageChangedEvent(index,False)";
parent._pagechangedevent(_index,parent.__c.False);
 //BA.debugLineNum = 715;BA.debugLine="xclv_main.JumpToItem(index)";
parent._xclv_main._jumptoitem(_index);
 //BA.debugLineNum = 716;BA.debugLine="Sleep(250)";
parent.__c.Sleep(ba,this,(int) (250));
this.state = 1;
return;
case 1:
//C
this.state = -1;
;
 //BA.debugLineNum = 717;BA.debugLine="m_Ignore = False";
parent._m_ignore = parent.__c.False;
 //BA.debugLineNum = 719;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public String  _setignorelazyloading(boolean _ignore) throws Exception{
 //BA.debugLineNum = 1097;BA.debugLine="Public Sub setIgnoreLazyLoading(Ignore As Boolean)";
 //BA.debugLineNum = 1098;BA.debugLine="m_IgnoreLazyLoading = Ignore";
_m_ignorelazyloading = _ignore;
 //BA.debugLineNum = 1099;BA.debugLine="End Sub";
return "";
}
public String  _setignorepagechangedevent(boolean _ignore) throws Exception{
 //BA.debugLineNum = 646;BA.debugLine="Public Sub setIgnorePageChangedEvent(ignore As Boo";
 //BA.debugLineNum = 647;BA.debugLine="g_ignorepagechangedevent = ignore";
_g_ignorepagechangedevent = _ignore;
 //BA.debugLineNum = 648;BA.debugLine="End Sub";
return "";
}
public String  _setignorepagechangeevent(boolean _ignore) throws Exception{
 //BA.debugLineNum = 649;BA.debugLine="Public Sub setIgnorePageChangeEvent(ignore As Bool";
 //BA.debugLineNum = 650;BA.debugLine="g_ignorepagechangeevent = ignore";
_g_ignorepagechangeevent = _ignore;
 //BA.debugLineNum = 651;BA.debugLine="End Sub";
return "";
}
public String  _setlazyloading(boolean _enabled) throws Exception{
 //BA.debugLineNum = 611;BA.debugLine="Public Sub setLazyLoading(Enabled As Boolean)";
 //BA.debugLineNum = 612;BA.debugLine="m_LazyLoading = Enabled";
_m_lazyloading = _enabled;
 //BA.debugLineNum = 616;BA.debugLine="End Sub";
return "";
}
public String  _setlazyloadingextrasize(int _extrasize) throws Exception{
 //BA.debugLineNum = 622;BA.debugLine="Public Sub setLazyLoadingExtraSize(ExtraSize As In";
 //BA.debugLineNum = 623;BA.debugLine="m_LazyLoadingExtraSize = ExtraSize";
_m_lazyloadingextrasize = _extrasize;
 //BA.debugLineNum = 627;BA.debugLine="End Sub";
return "";
}
public String  _setloadingpanelcolor(int _color) throws Exception{
 //BA.debugLineNum = 602;BA.debugLine="Public Sub setLoadingPanelColor(Color As Int)";
 //BA.debugLineNum = 603;BA.debugLine="m_LoadingPanelColor = Color";
_m_loadingpanelcolor = _color;
 //BA.debugLineNum = 604;BA.debugLine="xpnl_Loading.Color = Color";
_xpnl_loading.setColor(_color);
 //BA.debugLineNum = 605;BA.debugLine="End Sub";
return "";
}
public String  _setloadingpanelhideduration(int _duration) throws Exception{
 //BA.debugLineNum = 450;BA.debugLine="Public Sub setLoadingPanelHideDuration(Duration As";
 //BA.debugLineNum = 451;BA.debugLine="m_LoadingPanelHideDuration = Duration";
_m_loadingpanelhideduration = _duration;
 //BA.debugLineNum = 452;BA.debugLine="End Sub";
return "";
}
public String  _setscroll(boolean _enabled) throws Exception{
 //BA.debugLineNum = 634;BA.debugLine="Public Sub setScroll(enabled As Boolean)";
 //BA.debugLineNum = 635;BA.debugLine="g_isScrollEnabled = enabled";
_g_isscrollenabled = _enabled;
 //BA.debugLineNum = 640;BA.debugLine="End Sub";
return "";
}
public String  _touchbeginevent() throws Exception{
 //BA.debugLineNum = 1186;BA.debugLine="Private Sub TouchBeginEvent";
 //BA.debugLineNum = 1187;BA.debugLine="If ismoving = False Then";
if (_ismoving==__c.False) { 
 //BA.debugLineNum = 1188;BA.debugLine="If xui.SubExists(mCallBack, mEventName & \"_TouchB";
if (_xui.SubExists(ba,_mcallback,_meventname+"_TouchBegin",(int) (0))) { 
 //BA.debugLineNum = 1189;BA.debugLine="CallSub(mCallBack, mEventName & \"_TouchBegin\")";
__c.CallSubNew(ba,_mcallback,_meventname+"_TouchBegin");
 };
 };
 //BA.debugLineNum = 1192;BA.debugLine="End Sub";
return "";
}
public String  _touchendevent() throws Exception{
 //BA.debugLineNum = 1194;BA.debugLine="Private Sub TouchEndEvent";
 //BA.debugLineNum = 1195;BA.debugLine="If xui.SubExists(mCallBack, mEventName & \"_TouchE";
if (_xui.SubExists(ba,_mcallback,_meventname+"_TouchEnd",(int) (0))) { 
 //BA.debugLineNum = 1196;BA.debugLine="CallSub(mCallBack, mEventName & \"_TouchEnd\")";
__c.CallSubNew(ba,_mcallback,_meventname+"_TouchEnd");
 };
 //BA.debugLineNum = 1198;BA.debugLine="End Sub";
return "";
}
public String  _xclv_main_itemclick(int _index,Object _value) throws Exception{
 //BA.debugLineNum = 1201;BA.debugLine="Private Sub xclv_main_ItemClick (Index As Int, Val";
 //BA.debugLineNum = 1202;BA.debugLine="If xui.SubExists(mCallBack, mEventName & \"_PageCl";
if (_xui.SubExists(ba,_mcallback,_meventname+"_PageClick",(int) (2))) { 
 //BA.debugLineNum = 1203;BA.debugLine="CallSub3(mCallBack, mEventName & \"_PageClick\",In";
__c.CallSubNew3(ba,_mcallback,_meventname+"_PageClick",(Object)(_index),_value);
 };
 //BA.debugLineNum = 1205;BA.debugLine="End Sub";
return "";
}
public String  _xclv_main_reachend() throws Exception{
 //BA.debugLineNum = 1144;BA.debugLine="Private Sub xclv_main_ReachEnd";
 //BA.debugLineNum = 1145;BA.debugLine="If xui.SubExists(mCallBack, mEventName & \"_ReachE";
if (_xui.SubExists(ba,_mcallback,_meventname+"_ReachEnd",(int) (0))) { 
 //BA.debugLineNum = 1146;BA.debugLine="CallSub(mCallBack, mEventName & \"_ReachEnd\")";
__c.CallSubNew(ba,_mcallback,_meventname+"_ReachEnd");
 };
 //BA.debugLineNum = 1148;BA.debugLine="End Sub";
return "";
}
public String  _xclv_main_scrollchanged(int _offset) throws Exception{
 //BA.debugLineNum = 1103;BA.debugLine="Private Sub xclv_main_ScrollChanged (Offset As Int";
 //BA.debugLineNum = 1123;BA.debugLine="If xclv_main.FindIndexFromOffset(Offset) <> m_Cur";
if (_xclv_main._findindexfromoffset(_offset)!=_m_currentindex && _offset==(double)(BA.ObjectToNumber((((_m_orientation).equals("Vertical")) ? ((Object)(_mbase.getHeight())) : ((Object)(_mbase.getWidth())))))*_xclv_main._findindexfromoffset(_offset) && _m_ignore==__c.False) { 
 //BA.debugLineNum = 1124;BA.debugLine="PageChangedEvent(xclv_main.FindIndexFromOffset(O";
_pagechangedevent(_xclv_main._findindexfromoffset(_offset),__c.False);
 };
 //BA.debugLineNum = 1127;BA.debugLine="If xui.SubExists(mCallBack, mEventName & \"_Scroll";
if (_xui.SubExists(ba,_mcallback,_meventname+"_ScrollChanged",(int) (1))) { 
 //BA.debugLineNum = 1128;BA.debugLine="CallSub2(mCallBack, mEventName & \"_ScrollChanged";
__c.CallSubNew2(ba,_mcallback,_meventname+"_ScrollChanged",(Object)(_offset));
 };
 //BA.debugLineNum = 1130;BA.debugLine="End Sub";
return "";
}
public String  _xclv_main_visiblerangechanged(int _firstindex,int _lastindex) throws Exception{
int _i = 0;
anywheresoftware.b4a.objects.B4XViewWrapper _p = null;
 //BA.debugLineNum = 1043;BA.debugLine="Private Sub xclv_main_VisibleRangeChanged (FirstIn";
 //BA.debugLineNum = 1054;BA.debugLine="If m_LazyLoading = False Or m_IgnoreLazyLoading =";
if (_m_lazyloading==__c.False || _m_ignorelazyloading==__c.True) { 
if (true) return "";};
 //BA.debugLineNum = 1068;BA.debugLine="For i = 0 To xclv_main.Size - 1";
{
final int step2 = 1;
final int limit2 = (int) (_xclv_main._getsize()-1);
_i = (int) (0) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 1069;BA.debugLine="Dim p As B4XView = xclv_main.GetPanel(i)";
_p = new anywheresoftware.b4a.objects.B4XViewWrapper();
_p = _xclv_main._getpanel(_i);
 //BA.debugLineNum = 1070;BA.debugLine="If i > FirstIndex - m_LazyLoadingExtraSize And i";
if (_i>_firstindex-_m_lazyloadingextrasize && _i<_lastindex+_m_lazyloadingextrasize) { 
 //BA.debugLineNum = 1072;BA.debugLine="If p.NumberOfViews = 0 Then";
if (_p.getNumberOfViews()==0) { 
 //BA.debugLineNum = 1073;BA.debugLine="LazyLoadingAddContent(p,xclv_main.GetValue(i))";
_lazyloadingaddcontent(_p,_xclv_main._getvalue(_i));
 };
 }else {
 //BA.debugLineNum = 1077;BA.debugLine="If i > m_CurrentIndex - m_LazyLoadingExtraSize";
if (_i>_m_currentindex-_m_lazyloadingextrasize && _i<_m_currentindex+_m_lazyloadingextrasize) { 
 }else {
 //BA.debugLineNum = 1081;BA.debugLine="If p.NumberOfViews > 0 Then";
if (_p.getNumberOfViews()>0) { 
 //BA.debugLineNum = 1082;BA.debugLine="p.RemoveAllViews '<--- remove the layout";
_p.RemoveAllViews();
 };
 };
 };
 }
};
 //BA.debugLineNum = 1089;BA.debugLine="End Sub";
return "";
}
public boolean  _xpnl_pagearea2_touch(Object _viewtag,int _action,float _x,float _y,Object _motionevent) throws Exception{
boolean _skipcheck = false;
 //BA.debugLineNum = 910;BA.debugLine="Private Sub xpnl_PageArea2_Touch(ViewTag As Object";
 //BA.debugLineNum = 911;BA.debugLine="If g_isScrollEnabled = False Or (m_AllowBack = Fa";
if (_g_isscrollenabled==__c.False || (_m_allowback==__c.False && _m_allownext==__c.False)) { 
if (true) return __c.True;};
 //BA.debugLineNum = 914;BA.debugLine="If m_AllowNext = False Or m_AllowBack = False The";
if (_m_allownext==__c.False || _m_allowback==__c.False) { 
 //BA.debugLineNum = 916;BA.debugLine="Dim SkipCheck As Boolean = False";
_skipcheck = __c.False;
 //BA.debugLineNum = 917;BA.debugLine="If Action = 1 Then";
if (_action==1) { 
 //BA.debugLineNum = 918;BA.debugLine="isfirstmove = False";
_isfirstmove = __c.False;
 //BA.debugLineNum = 919;BA.debugLine="SkipCheck = True";
_skipcheck = __c.True;
 };
 //BA.debugLineNum = 922;BA.debugLine="If isfirstmove = False And SkipCheck = False The";
if (_isfirstmove==__c.False && _skipcheck==__c.False) { 
 //BA.debugLineNum = 924;BA.debugLine="ff(X,y)";
_ff(_x,_y);
 //BA.debugLineNum = 926;BA.debugLine="Return True";
if (true) return __c.True;
 };
 //BA.debugLineNum = 929;BA.debugLine="If m_AllowNext = False And x < g_x Then";
if (_m_allownext==__c.False && _x<_g_x) { 
 //BA.debugLineNum = 930;BA.debugLine="xclv_main.sv.ScrollViewOffsetX = m_MaxOffset";
_xclv_main._sv.setScrollViewOffsetX(_m_maxoffset);
 //BA.debugLineNum = 932;BA.debugLine="Return True";
if (true) return __c.True;
 }else if(_m_allowback==__c.False && _x>_g_x) { 
 //BA.debugLineNum = 934;BA.debugLine="xclv_main.sv.ScrollViewOffsetX = m_MaxOffset";
_xclv_main._sv.setScrollViewOffsetX(_m_maxoffset);
 //BA.debugLineNum = 936;BA.debugLine="Return True";
if (true) return __c.True;
 };
 };
 //BA.debugLineNum = 944;BA.debugLine="Return HandleTouch(Action,x,y)";
if (true) return _handletouch(_action,_x,_y);
 //BA.debugLineNum = 945;BA.debugLine="End Sub";
return false;
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
