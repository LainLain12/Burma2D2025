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
	Dim lottodata As String
	Dim lottohis As String
End Sub

Sub getlottodata
	Dim j As HttpJob
	j.Initialize(lottosociety.lottogetter,Starter)
	j.Download(Main.newsite&"lottosociety/getlotto?last=true")
End Sub

Sub getlottohistory
	Dim j As HttpJob
	j.Initialize("lottohis",Starter)
	j.Download(Main.newsite&"lottosociety/getlotto")
End Sub