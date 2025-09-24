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
	Dim gl As String
End Sub

Sub Globals
Dim scv As ScrollView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.Color=mycode.bgColor
	mycode.SETnavigationcolor
	Dim appbar As Panel = mycode.appbar("လမ်းညွှန်ချက်",False)
	Activity.AddView(appbar,0,mycode.appbartop,100%x,mycode.appbarheight)
	scv.Initialize(1000dip)
	Activity.AddView(scv,0,mycode.appbarheight,100%x,mycode.ActivityHeight-mycode.appbarheight)
	Dim lb As Label 
	lb.Initialize("")
	scv.Panel.AddView(lb,0,10dip,100%x,20dip)
	If gl <> "" Or gl = "null" Then
		lb.Text = "လမ်းညွှန်ချက် မရှိသေးပါ"
		Else
		lb.Text=gl
	End If
	lb.Typeface = mycode.mmfont
	lb.TextSize = mycode.textsize(10)
	lb.TextColor=Colors.White
	lb.Gravity=Gravity.CENTER
	Dim su As StringUtils
	lb.Height = su.MeasureMultilineTextHeight(lb,lb.text)
	scv.Panel.Height = lb.Height+lb.Top+10dip
End Sub

Sub Activity_Resume
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
Sub backbtn_click
	Activity.Finish
End Sub
