B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=13.35
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals
	
End Sub

Sub Globals
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.Color=mycode.bgColor
	mycode.SETnavigationcolor
	Dim appbar As Panel = mycode.appbar("Future Paper",False)
	Activity.AddView(appbar,0,0,100%x,mycode.appbarheight)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
