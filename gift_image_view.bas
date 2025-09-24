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
Dim m As Map
End Sub

Sub Globals
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
mycode.SETnavigationcolor
Dim appbar As Panel = mycode.appbar(m.Get("name"),False)


End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
