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
	Dim timer As Timer

End Sub

Sub Globals
	Dim inter As InterstitialAd
	Private AS_CardSlider1 As AS_CardSlider
	Dim xui As XUI
	Dim scv As ScrollView
	Dim progresspn As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)

	Activity.color=mycode.bgcolor
	mycode.SETnavigationcolor
	Activity.AddView(mycode.appbar("လက်ဆောင်ဂဏန်းများ",False),0,0,100%x,mycode.appbarheight)
	scv.Initialize(1000dip)
	Activity.AddView(scv,0,mycode.appbarheight+10dip,100%x,mycode.ActivityHeight-(mycode.appbarheight+10dip))
	
	Dim pn As Panel
	pn.Initialize("")
	scv.Panel.AddView(pn,0,0,100%x,100%x/2)
	pn.LoadLayout("frm_main")
	AS_CardSlider1.ItemWidth = 100%x-10dip
	For i = 0 To 5 -1
	
		Dim xpnl As B4XView = xui.CreatePanel("")
		xpnl.SetLayoutAnimated(0,0,0,AS_CardSlider1.ItemWidth,AS_CardSlider1.mBase.Height)
		xpnl.LoadLayout("frm_Item1")
		Dim img As ImageView = xpnl.GetView(0)
		img.SetBackgroundImage(LoadBitmap(File.DirAssets,"wb.png"))
		img.Gravity=Gravity.FILL
		AS_CardSlider1.AddPage(xpnl,i)
	Next
	pn.Height=170dip
	
	
	progresspn.Initialize("")
	Activity.AddView(progresspn,0,mycode.appbarheight,100%x,2dip)
	progresstimer.Initialize("ptimer",50)
	progresstimer.Enabled=True
	
	timer.Initialize("timer",3000)
	timer.Enabled=True
End Sub

Sub ptimer_tick
	If progresspn.Width =100%x Then
		progresspn.Width =0
		Else
			progresspn.Width =progresspn.Width+10%x
	End If
End Sub

Sub Activity_Resume
	
	If inter.IsInitialized Then
		If  inter.Ready = False Then
			inter.LoadAd
		End If
	Else
		inter.Initialize("inter",Main.interUnit)
		inter.LoadAd
	
	End If
	
	isCall = True
	Dim job As HttpJob
	job.Initialize(giftdataloader,Starter)
	job.Download(Main.site&"?q=SELECT * FROM `gift` WHERE 1;")
End Sub

Sub Activity_Pause (UserClosed As Boolean)
isCall = False
End Sub
Sub timer_tick
	If AS_CardSlider1.Index = AS_CardSlider1.Size -1 Then
		AS_CardSlider1.Index= 0
	Else
		AS_CardSlider1.NextPage
	End If
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
		
		Dim dailypn As Panel
		dailypn.Initialize("")
		scv.Panel.AddView(dailypn,0,180dip,100%x,50dip)
		Dim lb1 As Label
		lb1.Initialize("")
		lb1.Text = "တစ်ရက်စာလက်ဆောင်"
		lb1.Typeface =mycode.mmfont
		lb1.TextColor=Colors.White
		lb1.TextSize =mycode.textsize(9)
		dailypn.AddView(lb1,10dip,10dip,100%x-20dip,10dip)
		Dim su As StringUtils
		lb1.Height= su.MeasureMultilineTextHeight(lb1,lb1.Text)
		Dim top1 As Int = lb1.Height+lb1.Top +10dip
		If ldaily.Size >  0 Then
			For a = 0 To ldaily.Size -1
				Dim buton As Button
			
				buton.Initialize("giftclick")
				buton.Background=mycode.btnbg2
				dailypn.AddView(buton,10dip,top1,100%x-20dip,50dip)
				Dim m As Map = ldaily.Get(a)
				buton.Text = m.Get("title_mm")
				buton.Typeface=mycode.mmfont
				buton.TextSize = mycode.textsize(7)
				buton.Tag=m
				buton.TextColor=Colors.White
				top1 = top1 +buton.Height+10dip
			Next
			dailypn.Height= top1
		
		End If
		
		
		Dim weeklypn As Panel
		weeklypn.Initialize("")
		scv.Panel.AddView(weeklypn,0,dailypn.Height+dailypn.Top+10dip,100%x,50dip)
		Dim lb As Label
		lb.Initialize("")
		lb.TextColor=Colors.White
		lb.Text= "တစ်ပတ်စာလက်ဆောင်"
		lb.Typeface=mycode.mmfont
		lb.TextSize =mycode.textsize(9)
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
				buton.Typeface=mycode.mmfont
				buton.TextSize = mycode.textsize(7)
			buton.Tag=m
			buton.TextColor=Colors.White
			top = top +buton.Height+10dip
		Next
		weeklypn.Height= top 
		End If
		
		
		scv.Panel.Height=weeklypn.Height+weeklypn.Top+10dip
	End If

End Sub

Sub backbtn_click
	Activity.Finish
End Sub


Sub giftclick_click
	Dim b As Button =Sender
	gift_imageview.data= b.Tag
	If inter.Ready Then
		inter.Show
'		If DateTime.Now - showtime > interval Then
'			inter.Show
'			showtime = DateTime.Now
'			Else
'				StartActivity(gift_imageview)
'		End If
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
