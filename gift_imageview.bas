B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=13.1
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: false
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim data As Map
	Dim progresstimer As Timer
	Dim imageloader As String  = "imageloader"
	Dim isCall As Boolean
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim progresspn As Panel
	Dim img As ImageView
	Dim scv As ScrollView
End Sub

Sub Activity_Create(FirstTime As Boolean)

	mycode.SETnavigationcolor
	Activity.color=mycode.bgcolor
	'data.Initialize
	Dim appbar As Panel =mycode.appbar(data.Get("title_mm"),False)
	Activity.AddView(appbar,0,mycode.appbartop,100%x,mycode.appbarheight)
	
	Dim guidelinebtn As Button
	guidelinebtn.Initialize("guideline")
	guidelinebtn.Background=mycode.btnbg(False)
	guidelinebtn.Text=mycode.buttoncsb(False,Chr(0xF059),True)
	If data.Get("category") = "weekly" Then
		appbar.AddView(guidelinebtn,100%x-mycode.appbarheight,0,mycode.appbarheight,mycode.appbarheight)
	End If
	progresspn.Initialize("")
	Activity.AddView(progresspn,0,mycode.appbarheight,100%x,2dip)
	progresspn.Color=Colors.Yellow
	progresstimer.Initialize("ptimer",50)
	progresstimer.Enabled=True
	img.Initialize("")
	scv.Initialize(1000dip)
	Activity.AddView(scv,0,mycode.appbarheight,100%x,mycode.ActivityHeight-mycode.appbarheight)
	scv.Panel.AddView(img,0,0,scv.Width,scv.Height)
	
	scv.Panel.Height=img.Height
End Sub

Sub Activity_Resume
	isCall= True
	Dim ls As List 
	Dim fileex As Boolean =False
	ls = File.ListFiles(File.DirInternal)
	For i = 0 To ls.Size -1
			If ls.Get(i) = data.Get("img_id")&".png" Then
				fileex = True
			End If
	Next
	
	If fileex =True Then
	
		
		progresshide
		Else
	
		Dim job As HttpJob
		job.Initialize(imageloader,Starter)
		job.Download(data.Get("img"))
	End If

End Sub

Sub Activity_Pause (UserClosed As Boolean)
isCall = False
End Sub
Sub backbtn_click
	Activity.Finish
End Sub

Sub guideline_click
	GuideLine.gl = data.Get("guideline")
	StartActivity(GuideLine)
End Sub

Sub ptimer_tick
	If progresspn.Width = 100%x Then
		progresspn.Width = 0
		Else
			progresspn.Width =progresspn.Width+10%x
	End If
End Sub

Sub progresshide
	progresspn.Visible=False
	progresstimer.Enabled=False
	Dim bmp As Bitmap = LoadBitmap(File.DirInternal, data.Get("img_id")&".png")
	' မင်း ImageView1 ရဲ့ အချင်းချင်းယူ
	Dim w As Int = img.Width
	Dim h As Int = img.Height

	' Resize: TRUE ဆိုတာ aspect ratio မပျက်
	Dim resized As Bitmap = bmp.Resize(w, h, True)

	' ပုံ ထည့်
	img.Bitmap = resized
	img.Gravity=Gravity.TOP
	scv.Panel.Height=img.Height+img.Top
End Sub