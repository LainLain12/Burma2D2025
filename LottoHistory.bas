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
	Dim progresstimer As Timer
Dim isCall As Boolean
End Sub

Sub Globals
	Dim progresspn As Panel
	Dim scv As ScrollView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Dim appbar As Panel = mycode.appbar("ထုတ်လွှင့်မှုမှတ်တမ်း",False)
	Activity.AddView(appbar,0,mycode.appbartop,100%x,mycode.appbarheight)
	mycode.SETnavigationcolor
	Activity.Color=mycode.bgColor
	scv.Initialize(1000dip)
	Activity.AddView(scv,0,mycode.appbarheight,100%x,mycode.ActivityHeight-mycode.appbarheight)
	

	
	progresspn.Initialize("")
	Activity.AddView(progresspn,0,mycode.appbarheight,100%x,2dip)
	progresspn.Color=Colors.Yellow
	progresstimer.Initialize("ptimer",50)
	progresstimer.Enabled=True
	ApiCall.getlottohistory
End Sub

Sub Activity_Resume
	isCall=True
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	isCall=False
End Sub
Sub backbtn_click
	Activity.Finish
End Sub

Sub ptimer_tick
	If progresspn.Width = 100%x Then
		progresspn.Width = 0
	Else
		progresspn.Width =progresspn.Width+10%x
	End If
End Sub


Sub item(num As String,num1 As String ,date As String) As Panel
	Dim p As Panel
	p.Initialize("")
	Dim cd As ColorDrawable 
	cd.Initialize(mycode.naviColor,10dip)
	p.Background=cd
	p.Elevation= 5dip
	Dim datelb As Label
	Dim numlb As Label
	datelb.Initialize("")
	datelb.Gravity=Gravity.CENTER
	datelb.TextColor= Colors.Yellow'0xFFDDDDDD
	datelb.Typeface=mycode.defaultfont
	datelb.TextSize = mycode.textsize(10)
	datelb.Text= date
	
	numlb.Initialize("")
	numlb.Gravity=Gravity.CENTER
	numlb.TextColor=0xFFFFFFFF
	numlb.Typeface=mycode.semibold
	numlb.TextSize= mycode.textsize (12)
	numlb.Text= num&Chr(9)&Chr(9)&num1
	
	p.AddView(datelb,0,5dip,100%x-20dip,50dip)
	Dim su As StringUtils 
	datelb.Height =su.MeasureMultilineTextHeight(datelb,datelb.Text)
	p.AddView(numlb,0,datelb.Height+datelb.Top+10dip,100%x-20dip,50dip)
	numlb.Height= su.MeasureMultilineTextHeight(numlb,numlb.Text)
	
	p.Height = numlb.Height+numlb.Top+5dip
	
	Return p
End Sub

Sub lottohisuccess
	
	If ApiCall.lottohis <>"" Then
		Log("here >>>>>>>>>>>>")
		progresspn.Visible=False
		progresspn.RemoveView
		Dim json As JSONParser
		json.Initialize(ApiCall.lottohis)
		Dim ls As List = json.NextArray
		Dim tp As Int = 10dip
		
		For i = 0 To ls.Size -1
			Dim m As Map = ls.Get(i)
			Dim ip As Panel = item(m.Get("fnum"),m.Get("snum"),m.Get("thaidate"))
			If i Mod 2=0 Then
				ip.SetLayoutAnimated(500,10dip,10dip,scv.Width-20dip,ip.Height)
				Else
				ip.SetLayoutAnimated(500,10dip,10dip,20dip,ip.Height)
			End If
			scv.Panel.AddView(ip,10dip,tp,scv.Width-20dip,ip.Height)
			tp= tp +ip.Height+10dip
			Sleep(200)
		Next
		scv.Panel.Height =tp+10dip
		
	Else
		ToastMessageShow("data not found",False)
		Activity.Finish
	End If
End Sub