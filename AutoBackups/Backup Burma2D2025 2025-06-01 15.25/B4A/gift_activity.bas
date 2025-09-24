B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=13.1
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: false
	#IncludeTitle: false
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim giftdataloader As String = "giftdataloader"
	Dim isCall  As Boolean
	Dim progresstimer As Timer
	Dim interval As Long =2*DateTime.TicksPerMinute
	Dim showtime As Long = 0
End Sub

Sub Globals
	Dim inter As InterstitialAd
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim scv As ScrollView
	Dim progresspn As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	inter.Initialize("inter","ca-app-pub-3940256099942544/1033173712")
	inter.LoadAd
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")
	Activity.Color=0xFF241A32
	
	mycode.SETnavigationcolor
	Activity.AddView(mycode.appbar("လက်ဆောင်ဂဏန်းများ"),0,0,100%x,mycode.appbarheight)
	scv.Initialize(1000dip)
	Activity.AddView(scv,0,mycode.appbarheight+10dip,100%x,100%y-(mycode.appbarheight+10dip))
	progresspn.Initialize("")
	Activity.AddView(progresspn,0,mycode.appbarheight,100%x,2dip)
	progresstimer.Initialize("ptimer",50)
	progresstimer.Enabled=True
End Sub

Sub ptimer_tick
	If progresspn.Width =100%x Then
		progresspn.Width =0
		Else
			progresspn.Width =progresspn.Width+10%x
	End If
End Sub

Sub Activity_Resume
	
	isCall = True
	Dim job As HttpJob
	job.Initialize(giftdataloader,Starter)
	job.Download(Main.site&"?q=SELECT * FROM `gift` WHERE 1;")
End Sub

Sub Activity_Pause (UserClosed As Boolean)
isCall = False
End Sub


Sub giftsuccess(data As String)
	progresspn.Visible=False
	progresstimer.Enabled=False
	Dim json As JSONParser
	json.Initialize(data)
	Dim ls As List = json.NextArray
	Dim lweekly As List
	Dim ldaily As List
	lweekly.Initialize
	ldaily.Initialize
	If ls.Size > 0 Then
		For i = 0 To ls.Size -1
			Dim m As Map = ls.Get(i)
			If m.Get("category") = "weekly" Then
				lweekly.Add(m)
			End If
			If m.Get("category") = "daily" Then
				ldaily.Add(m)
			End If
		Next
		Dim weeklypn As Panel
		weeklypn.Initialize("")
		scv.Panel.AddView(weeklypn,0,0,100%x,50dip)
		Dim lb As Label
		lb.Initialize("")
		lb.TextColor=Colors.White
		lb.Text= "တစ်ပတ်စာလက်ဆောင်"
		lb.TextSize =20
		weeklypn.AddView(lb,10dip,10dip,100%x-20dip,10dip)
		Dim su As StringUtils
		lb.Height= su.MeasureMultilineTextHeight(lb,"weeklytips")
		Dim top As Int = lb.Height+lb.Top+10dip
		If lweekly.Size>0 Then
		For i = 0 To lweekly.Size-1
				Dim buton As Button
			
			buton.Initialize("giftclick")
			buton.Background=mycode.btnbg2
			weeklypn.AddView(buton,10dip,top,weeklypn.Width-20dip,50dip)
			Dim m As Map = lweekly.Get(i)
			buton.Text = m.Get("title_mm")
			buton.Tag=m
			buton.TextColor=Colors.White
			top = top +buton.Height+10dip
		Next
		weeklypn.Height= top 
		End If
		
		Dim dailypn As Panel
		dailypn.Initialize("")
		scv.Panel.AddView(dailypn,0,weeklypn.Height+weeklypn.Top+10dip,100%x,50dip)
		Dim lb1 As Label
		lb1.Initialize("")
		lb1.Text = "တစ်ရက်စာလက်ဆောင်"
		lb1.TextColor=Colors.White
		lb1.TextSize =20
		dailypn.AddView(lb1,10dip,10dip,100%x-20dip,10dip)
		Dim su As StringUtils
		lb1.Height= su.MeasureMultilineTextHeight(lb1,lb.Text)
		Dim top1 As Int = lb1.Height+lb.Top +10dip
		If ldaily.Size >  0 Then
		For a = 0 To ldaily.Size -1
			Dim buton As Button
			
			buton.Initialize("giftclick")
			buton.Background=mycode.btnbg2
			dailypn.AddView(buton,10dip,top1,weeklypn.Width-20dip,50dip)
			Dim m As Map = ldaily.Get(a)
			buton.Text = m.Get("title_mm")
		
			buton.Tag=m
			buton.TextColor=Colors.White
			top1 = top1 +buton.Height+10dip
		Next
			dailypn.Height= top1
		
		End If
	End If

End Sub

Sub backbtn_click
	Activity.Finish
End Sub


Sub giftclick_click
	Dim b As Button =Sender
	gift_imageview.data= b.Tag
	If inter.Ready Then
		If DateTime.Now - showtime > interval Then
			inter.Show
			showtime = DateTime.Now
			Else
				StartActivity(gift_imageview)
		End If
		Else
			StartActivity(gift_imageview)
	End If
End Sub

Sub inter_ReceiveAd
	Log("IAd received. Now wait for the right moment to show the ad.")
	
End Sub

Sub inter_FailedToReceiveAd (ErrorCode As String)
	Log("Failed: " & ErrorCode)
End Sub

Sub inter_AdClosed
	Log("Closed")
StartActivity(gift_imageview)
End Sub

Sub inter_AdOpened

	Log("Opened")
End Sub
