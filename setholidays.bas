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
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.Color=mycode.bgColor
	mycode.SETnavigationcolor
	Dim appbar As Panel = mycode.appbar("Set Holidays",False)
	Activity.AddView(appbar,0,0,100%x,mycode.appbarheight)
	
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
