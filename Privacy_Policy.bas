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
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Globals
	Dim wb As WebView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.Color=mycode.bgColor
	mycode.SETnavigationcolor
	Dim appbar As Panel =  mycode.appbar("Privacy Policy",False)
	Activity.AddView(appbar,0,mycode.appbartop,100%x,mycode.appbarheight)
	
	wb.Initialize("wb")
	wb.Color=mycode.bgColor
	wb.Enabled=False
	wb.ZoomEnabled=False
	Sleep(100)
	wb.LoadHtml(File.ReadString(File.DirAssets,"privacy_policy.html"))
	Activity.AddView(wb,0,mycode.appbarheight,100%x,mycode.ActivityHeight-mycode.appbarheight)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub backbtn_click
	Activity.Finish
End Sub