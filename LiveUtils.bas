B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=StaticCode
Version=13.35
@EndOfDesignText@
'Code module
'Subs in this code module will be accessible from all modules.
Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Change(t As String) As String
	Dim rt As String = t
	Select t
		Case "1200set"
			rt = "mset"
			Case "1200value"
				rt = "mvalue"
				Case "1200"
					rt ="mresult"
					Case "430set"
						rt ="eset"
						Case "430value"
							rt = "evalue"
							Case "430"
								rt = "eresult"
								Case "930modern"
									rt ="nmodern"
									Case "930internet"
										rt = "ninternet"
										Case "200modern"
											rt ="tmodern"
											Case "200internet"
												rt = "tinternet"
											
	End Select
	Return rt
End Sub