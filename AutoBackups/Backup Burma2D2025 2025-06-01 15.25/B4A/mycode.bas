B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=StaticCode
Version=13.1
@EndOfDesignText@
'Code module
'Subs in this code module will be accessible from all modules.
Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim appbarheight As Int
	Public naviColor As Int =0xFF842F46
End Sub


Sub btnbg(isGift As Boolean) As StateListDrawable
	Dim std As StateListDrawable
	std.Initialize
	Dim defaultdb As ColorDrawable
	Dim pressdb As ColorDrawable
	
	If isGift = True Then
		defaultdb.Initialize(0xE1241A32,180)
		Else
		defaultdb.Initialize(Colors.Transparent,0)
	End If
	pressdb.Initialize(0x9A466584,180)
	std.AddState(std.State_Pressed,pressdb)
	std.AddCatchAllState(defaultdb)
	Return std
End Sub

Sub SETnavigationcolor
	Dim phone As Phone
	Dim jo As JavaObject
	If phone.SdkVersion >= 21 Then
		jo.InitializeContext
		Dim window As JavaObject = jo.RunMethodJO("getWindow", Null)
		window.RunMethod("setStatusBarColor", Array As Object(0xFF842F46)) ' Replace with your color (ARGB)
	End If

	' Set light status bar icons (requires Android 6.0 / API 23)
	If phone.SdkVersion >= 23 Then
		Dim decorView As JavaObject = jo.RunMethodJO("getWindow", Null).RunMethodJO("getDecorView", Null)
		Dim flags As Int = decorView.RunMethod("getSystemUiVisibility", Null)
		flags = Bit.Or(flags, 0x00002000) ' SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
		decorView.RunMethod("setSystemUiVisibility", Array(flags))
	End If
End Sub

Sub appbar(title As String) As Panel
	Dim p As Panel
	p.Initialize("")
	p.Color=0xFF842F46
	Dim backbtn As Button
	backbtn.Initialize("backbtn")
	
	backbtn.Background = btnbg(False)
	backbtn.TextColor=Colors.White
	backbtn.Gravity=Gravity.CENTER
	backbtn.Typeface= Typeface.FONTAWESOME
	backbtn.Text=Chr(0xF060)
	Dim tlb As Label
	tlb.Initialize("")
	tlb.Text=title
	tlb.TextSize=20
	tlb.TextColor=Colors.White
	tlb.Gravity=Gravity.CENTER_VERTICAL
	p.AddView(backbtn,10dip,10dip,50dip,50dip)
	p.AddView(tlb,backbtn.Width+backbtn.Left+10dip,10dip,100%x-(backbtn.Width+backbtn.Left),50dip)
	Dim su As StringUtils
	
	If su.MeasureMultilineTextHeight(tlb,tlb.Text)> 50dip Then
		appbarheight=tlb.Height+20dip
		backbtn.Width=tlb.Height
		backbtn.Height=tlb.Height
	Else
		appbarheight=70dip
	End If
	Return p
End Sub




Sub btnbg2 As StateListDrawable
	Dim std As StateListDrawable
	std.Initialize
	Dim defaultdb As ColorDrawable
	Dim pressdb As ColorDrawable
	defaultdb.Initialize(0xFF842F46,10dip)
	pressdb.Initialize(0x9A466584,10dip)
	std.AddState(std.State_Pressed,pressdb)
	std.AddCatchAllState(defaultdb)
	Return std
End Sub

Sub btnbg3 As StateListDrawable
	Dim std As StateListDrawable
	std.Initialize
	Dim defaultdb As ColorDrawable
	Dim pressdb As ColorDrawable
	defaultdb.Initialize(0x9A466584,10dip)
	pressdb.Initialize(0xFF842F46,10dip)
	std.AddState(std.State_Pressed,pressdb)
	std.AddCatchAllState(defaultdb)
	Return std
End Sub

Sub updatemessage As Panel
	Dim pn As Panel
	pn.Initialize("")
	pn.Color=Colors.Transparent
	Dim uppn As Panel
	uppn.Initialize("")
	uppn.Background=btnbg2
	pn.AddView(uppn,(100%x-300dip)/2,(100%y-200dip)/2,300dip,200dip)
	Dim lb As Label
	lb.Initialize("")
	lb.Text = "ပိုမိုကောင်းမွန်မြန်ဆန်သော Update ကို Download လုပ်ပါ"
	Dim updatebtn As Button
	updatebtn.Initialize("updatebtn")
	uppn.AddView(lb,10dip,10dip,uppn.Width-20dip,30dip)
	Dim su As StringUtils
	lb.Text=su.MeasureMultilineTextHeight(lb,lb.Text)
	Return pn
End Sub


Sub moderninernetcs(n,t,ty As String) As CSBuilder
	Dim cs As CSBuilder
	cs.Initialize
	cs.Color(Colors.Gray).Size(15).Append(ty).Pop
	cs.Color(Colors.Yellow).Size(20).Append(CRLF&misub(n)).Pop
	cs.Color(Colors.White).Size(20).Append(mimain(n)).Pop
	cs.Color(Colors.Yellow).Size(20).Append(CRLF&misub(t)).Pop
	cs.Color(Colors.White).Size(20).Append(mimain(t)).PopAll
	Return cs
End Sub




Sub mimain(m As String) As String
	m = m.SubString(1)
	Return m
End Sub
Sub misub(m As String) As String
	m = m.SubString2(0,1)
	Return m
End Sub

Sub mitimecs As CSBuilder
	Dim cs As CSBuilder
	cs.Initialize
	cs.Size(20).Append(CRLF).Pop
	cs.Color(Colors.White).Size(17).Typeface(Typeface.FONTAWESOME).Append(Chr(0xF2D5)).Pop
	cs.Color(Colors.White).Size(17).Append(" 9:30 AM").Pop
	cs.Size(20).Append(CRLF).Pop
	cs.Size(17).Color(Colors.White).Typeface(Typeface.FONTAWESOME).Append(Chr(0xF2D5)).Pop
	cs.Size(17).Color(Colors.White).Append(" 2:00 PM").PopAll
	Return cs
End Sub