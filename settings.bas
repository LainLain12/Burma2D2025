B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=13.35
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	 #IncludeTitle: false
#End Region

Sub Process_Globals
	
End Sub

Sub Globals
	Dim top As Int
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.Color=mycode.bgColor
	mycode.SETnavigationcolor
	Dim appbar As Panel  = mycode.appbar("Settings",False)
	Activity.AddView(appbar,0,mycode.appbartop,100%x,mycode.appbarheight)
	top = mycode.appbarheight+10dip
	
	If File.Exists(File.DirInternal,"user") Then
		profile
		top = top +10dip
		Else
			addAccount
	End If
	Addsettings
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub consentbtn_click
	ADConsent
End Sub

Sub addHolidays
	Dim p As Panel
	p.Initialize("")
	Dim cd As ColorDrawable
	cd.Initialize(mycode.naviColor,10dip)
	p.Background=cd
	Activity.AddView(p,10dip,top,100%x-20dip,50dip)
	Dim titlelb As Label
	titlelb.Initialize("")
	titlelb.Typeface=mycode.defaultfont
	titlelb.TextSize = mycode.textsize(8)
	titlelb.Text= "Set Holidays"
	titlelb.TextColor=Colors.White
	p.AddView(titlelb,10dip,10dip,p.Width-20dip,30dip)
	Dim su As StringUtils
	titlelb.Height = su.MeasureMultilineTextHeight(titlelb,titlelb.Text)
	Dim pacc As Panel = pitem(Chr(0xF2D3),"Set Holidays","pholiday")
	p.AddView(pacc,10dip,titlelb.Height+titlelb.Top+10dip,p.Width-20dip,50dip)
	p.Height = pacc.Height+pacc.Top+10dip
	top = top+p.Height+10dip
End Sub

Sub ADConsent
	Dim ads As AdsHelper
	ads.Initialize
	If ads.GetConsentStatus = "UNKNOWN" Or ads.GetConsentStatus = "REQUIRED" Then
		Wait For (ads.RequestConsentInformation(False)) Complete (Success As Boolean)
	End If
	If ads.GetConsentStatus = "REQUIRED" And ads.GetConsentFormAvailable Then
		Wait For (ads.ShowConsentForm) Complete (Success As Boolean)
	End If
End Sub

Sub backbtn_click
	Activity.Finish
End Sub

Sub pitem (icon As String,text As String,eventname As String) As Panel
	Dim p As Panel
	p.Initialize("")
	Dim cs As CSBuilder
	cs.Initialize
	cs.Color(Colors.White).Typeface(mycode.semibold).Size(mycode.textsize(7)).Append(text).PopAll
	Dim consentbtn As Label
	consentbtn.Initialize("")
	consentbtn.Text = cs
	consentbtn.Gravity=Gravity.CENTER_VERTICAL
	Dim iconlb As Label
	iconlb.Initialize("")
	Dim cs2 As CSBuilder
	cs2.Initialize.Color(Colors.Yellow).Typeface(Typeface.FONTAWESOME).Size(mycode.textsize(10)).Append(icon).PopAll
	iconlb.Text = cs2
	iconlb.Gravity=Gravity.CENTER
	p.AddView(iconlb,0,0,50dip,50dip)
	p.AddView(consentbtn,50dip,0,100%x-140dip,50dip)
	Dim lb As Label
	lb.Initialize("")
	Dim cs1 As CSBuilder
	cs1.Initialize.Color(Colors.White).Typeface(mycode.defaultfont).Size(mycode.textsize(11)).Append(">").PopAll
	lb.Text=cs1
	lb.Gravity=Gravity.CENTER_VERTICAL
	p.AddView(lb,consentbtn.Width+consentbtn.Left,0,50dip,50dip)
	Dim b As Button
	b.Initialize(eventname)
	b.Background= mycode.btnbgdynamic(Colors.Transparent,0x9A466584,10dip)
	p.AddView(b,0,0,100%x-40dip,50dip)
	Dim devider As Panel
	devider.Initialize("")
	devider.Color=0xB1FFFFFF
	'p.AddView(devider,((100%x-40dip)-(100%x-200dip))/2,49dip,100%x-200dip,1dip)
	p.Height = 50dip
	Return p 
End Sub

Sub privacybtn_click
	StartActivity(Privacy_Policy)
End Sub

Sub addAccount
	Dim p As Panel
	p.Initialize("")
	Dim cd As ColorDrawable
	cd.Initialize(mycode.naviColor,10dip)
	p.Background=cd
	Dim titlelb As Label
	titlelb.Initialize("")
	p.AddView(titlelb,10dip,10dip,100%x-20dip,30dip)
	titlelb.Typeface=mycode.semibold
	titlelb.TextColor=Colors.White
	titlelb.TextSize = mycode.textsize(8)
	titlelb.Gravity=Gravity.CENTER_VERTICAL
	titlelb.Text= "User Account"
	Dim su As StringUtils
	titlelb.Height = su.MeasureMultilineTextHeight(titlelb,titlelb.Text)
	Dim pacc As Panel = pitem(Chr(0xF2BD),"Log In To Burma 2D","pacc")
	Activity.AddView(p,10dip,top,100%x-20dip,pacc.Height)
	p.AddView(pacc,10dip,titlelb.Height+titlelb.Top+10dip,p.Width-20dip,50dip)
	p.Height = pacc.Height+pacc.Top+10dip
	top = p.Height+p.Top+10dip
End Sub

Sub Addsettings
	
	Dim base As Panel
	Dim pprivacy As Panel = pitem(Chr(0xF265),"  Privacy Policy","privacybtn")
	
	base.Initialize("")
	Dim cd As ColorDrawable
	cd.Initialize(mycode.naviColor,10dip)
	base.Background= cd
	Activity.AddView(base,10dip,top,100%x-20dip,50dip)
	Dim lbltitle As Label
	lbltitle.Initialize("")
	lbltitle.TextSize =mycode.textsize(8)
	lbltitle.Text="Settings"
	lbltitle.Typeface= mycode.semibold
	lbltitle.TextColor=Colors.White
	base.AddView(lbltitle,10dip,10dip,base.Width-20dip,30dip)
	Dim su As StringUtils
	lbltitle.Height = su.MeasureMultilineTextHeight(lbltitle,lbltitle.Text)
	base.AddView(pprivacy,10dip,lbltitle.Height+lbltitle.Top+10dip,base.Width-20dip,50dip)
	base.Height = pprivacy.Height+pprivacy.Top+10dip
	Dim tp As Int = pprivacy.Height +pprivacy.Top+10dip
	Dim p As Panel = pitem(Chr(0xF15C),"  Change Consent Type","consentbtn")
	
	base.AddView(p,10dip,tp,base.Width-20dip,50dip)
	base.Height = p.Height+p.Top+10dip
	top=base.Height+base.Top+10dip
End Sub



Sub profile 
	Dim p As Panel
	p.Initialize("")
	Dim img As ImageView
	img.Initialize("")
	Dim glide As Amir_Glide
	glide.Initializer.Default
	glide.Load(mycode.getUserId(mycode.profile_pic)).Apply(glide.RO.CircleCrop).Into(img)
	Dim l As Int = (100%x-70dip)/2
	p.AddView(img,l,10dip,70dip,70dip)
	Dim lblname As Label
	lblname.Initialize("")
	lblname.text = mycode.getUserId(mycode.name)
	lblname.Typeface=mycode.defaultfont
	lblname.Gravity=Gravity.CENTER
	lblname.TextSize = mycode.textsize (9)
	lblname.TextColor=Colors.White
	p.AddView(lblname,0,img.Height+img.Top+10dip,100%x,10dip)
	Dim su As StringUtils
	lblname.Height = su.MeasureMultilineTextHeight(lblname,lblname.Text)
	Dim lblemail As Label
	lblemail.Initialize("")
	lblemail.text = mycode.getUserId(mycode.email)
	lblemail.Typeface=mycode.defaultfont
	lblemail.Gravity=Gravity.CENTER
	lblemail.TextSize = mycode.textsize (8)
	lblemail.TextColor=Colors.White
	p.AddView(lblemail,0,lblname.Height+lblname.Top+10dip,100%x,10dip)
	Dim su As StringUtils
	lblemail.Height = su.MeasureMultilineTextHeight(lblemail,lblemail.Text)
	p.Height = lblemail.Height+lblemail.Top
	
	Activity.AddView(p,0,top,100%x,p.Height)
	top = p.Height+p.Top+10dip
End Sub

Sub pacc_click
	StartActivity(Login)
End Sub