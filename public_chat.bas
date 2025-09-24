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
Dim livesse As sseconnector
Dim timer As Timer
Dim su As StringUtils
Dim messageHeight As Int
Dim isCall As Boolean
	Dim livetimer As Timer
Dim numbarheight As Int
Dim typbarheight As Int
Dim sendSMS As String = "sendSMS"
Dim loadsmscount As Int =0
Dim chatsse As chatsseconnector
	Dim top As Int = 10dip
End Sub

Sub Globals
	Dim setlb,valuelb,twodlb,livelb As Label
	Dim mainscv As ScrollView
	Dim edt As EditText
	Dim ime As IME
	Dim typingpn As Panel
	Dim numpanel As Panel
	Dim ppopup As Panel
	'progress
	Dim AXrLottie As AXrLottie
	Dim LottieView As AXrLottieImageView
	Dim pimg As ImageView
End Sub

Sub Activity_Create(FirstTime As Boolean)

	timer.Initialize("tm",1000)
	timer.Enabled=True

	
	ime.Initialize("ime")
	ime.AddHeightChangedEvent
	Activity.Color=mycode.bgColor
	mycode.SETnavigationcolor
	
	
	Dim appbar As Panel = mycode.appbar("Public Chat",False)
	
	pimg.Initialize("profilebtn")
	appbar.AddView(pimg,100%x-mycode.appbarheight,5dip,mycode.appbarheight-10dip,mycode.appbarheight-10dip)
	pimg.Visible=False
	pimg.Enabled=False
	Dim glide As Amir_Glide
	glide.Initializer.Default
	glide.Load(mycode.getUserId(mycode.profile_pic)).Apply(glide.RO.CircleCrop).Into(pimg)
	
	Activity.AddView(appbar,0,mycode.appbartop,100%x,mycode.appbarheight)
	

	
	mainscv.Initialize(1000dip)

	numpanel = numbar
	Activity.AddView(numpanel,0,mycode.appbarheight,100%x,numbarheight)

	typingpn = typingBar
	Activity.AddView(typingpn,0,mycode.ActivityHeight-typbarheight,100%x,typbarheight)
	Activity.AddView(mainscv,0,numpanel.Height+numpanel.Top,100%x,typingpn.Top-(numpanel.Height+numpanel.Top))
	
	livetimer.Initialize("livetimer",1500)
	Change
End Sub

Sub Activity_Resume
	isCall =True
	liveLoader
	
	mycode.checkentertime = 0
	If File.Exists(File.DirInternal,"user") Then
		pimg.Visible=True 
		pimg.Enabled=True
		Else
			pimg.Visible=False
			pimg.Enabled=False
			pimg.RemoveView
	End If
	progressShow
	loadsmscount = 0 
	LoadMessage
End Sub

Sub Activity_Pause (UserClosed As Boolean)
	mycode.checkentertime =0
	isCall=False
	FinishStream
End Sub

Sub FinishStream
	If livesse.IsInitialized Then
		livesse.Finish
		Else
			livesse.Initialize(Me,"live","live",Application.PackageName)
			livesse.Finish
	End If
	If chatsse.IsInitialized Then
		chatsse.Finish
		Else
			chatsse.Initialize(Me,"chat","chat",Application.PackageName)
	End If
End Sub

Sub profilebtn_click
	StartActivity(Profile_Activity)
End Sub

Sub liveLoader
	If livesse.IsInitialized Then
		livesse.Finish
		livesse.Connect(Main.newsite&"live")
	Else
		livesse.Initialize(Me,"live","live",Application.PackageName)
		livesse.Connect(Main.newsite&"live")
	End If
End Sub


Sub LoadMessage
			If chatsse.IsInitialized Then
		chatsse.Finish
		chatsse.Connect(Main.newsite&"chat/sse")
				Else
		chatsse.Initialize(Me,"chat","chat",Application.PackageName)
		chatsse.Finish
		chatsse.Connect(Main.newsite&"chat/sse")
			End If
End Sub

Sub ime_HeightChanged (NewHeight As Int, OldHeight As Int)
	If NewHeight > 0 Then
		' Keyboard is shown
		Sleep(0)
		typingpn.Top = NewHeight  - 60dip
		mainscv.Height = typingpn.Top  - (numpanel.Top+numpanel.Height)
		Sleep(0)
		mainscv.FullScroll(True)
	
	Else
		Sleep(0)
		' Keyboard is hidden
		typingpn.Top = Activity.Height  - 60dip
		mainscv.Height = typingpn.Top - (numpanel.Top+numpanel.Height)
		Sleep(0)
		mainscv.FullScroll(True)
	End If
End Sub

Sub OtherMessage(profile As String,sms As String,name As String,id As String,tp As Int) As Panel
	Dim p As Panel
	p.Initialize("p")
	Dim glide As Amir_Glide
	Dim img As ImageView
	glide.Initializer
	img.Initialize("")
	Dim lb As Label
	lb.Initialize("")
	glide.Load(profile).Apply(glide.RO.CircleCrop).Into(img)
	lb.Text = OtherMessageCs(name,sms)
	p.AddView(img,5dip,0,40dip,40dip)
	p.AddView(lb,img.Width+img.Left+5dip,0,100%x-(lb.Left+5dip),40dip)
	lb.Height = su.MeasureMultilineTextHeight(lb,OtherMessageCs(name,sms))
	If lb.Height>img.Height Then
		messageHeight = lb.Height
		Else
		messageHeight = img.Height
	End If
	Dim smsitem As Button
	smsitem.Initialize("smsitem")
	smsitem.Color=Colors.Transparent
	p.AddView(smsitem,0,0,100%x,messageHeight)
	Dim m As Map 
	m.Initialize
	m.Put("id",id)
	m.Put("top",tp)
	m.Put("message",sms)
	m.Put("name",name)
	m.Put("profile_pic",profile)
	smsitem.Tag  = m
	Return p
End Sub

Sub smsitem_click
	If File.Exists(File.DirInternal,"user") Then
		Dim b As Button = Sender
		Dim m As Map =b.Tag
		Dim pp As Panel  = popuppn(m.Get("top"),m)
		pp.BringToFront
		If mainscv.Panel.Height < mainscv.Height Then
			Log("here")
			Activity.AddView(pp,mainscv.Left,mainscv.Top,mainscv.Panel.Width,mainscv.Height)
		
		Else
			mainscv.Panel.AddView(pp,0,0,mainscv.Panel.Width,mainscv.Panel.Height)
		End If
	Else
End If
	
End Sub

Sub popup_click
	Dim p As Panel =Sender
	p.RemoveView
	p.Visible=False
End Sub

Sub popuppn(tp As Int,mp As Map) As Panel
	
	ppopup.Initialize("popup")
	Dim pbase As Panel
	pbase.Initialize("")
	Dim cd As ColorDrawable
	cd.Initialize(mycode.naviColor,10dip)
	pbase.Background = cd
	Dim ls As List 
	ls.Initialize
	ls.AddAll(Array As String("Report","Block"))
	ppopup.AddView(pbase,50dip,tp,100dip,80dip)
	Dim tp As Int = 40dip
	For i  = 0 To ls.Size -1
		pbase.AddView(popupitem(ls.Get(i),mp),0,tp*i,100dip,40dip)
	Next
	
	Return ppopup
End Sub

Sub popupitem(title As String,tag  As Map) As Button
	Dim b As Button
	b.Initialize("popupitem")
	b.Color=Colors.Transparent
	b.Text = title
	b.Tag  = tag
	b.TextColor=Colors.White
	b.Background=mycode.btnbgdynamic(Colors.Transparent,Colors.DarkGray,0)
	Return b
End Sub




Sub popupitem_click

	Dim b As Button = Sender
	Dim m As Map = b.Tag
	Select b.Text
		Case "Report"
				ppopup.Visible=False
				report_details.Mdata = m
				StartActivity(report_details)
			Case "Block"
				ppopup.Visible=False
				AddBlock(m)
				
	End Select
End Sub

Sub MyMessage(sms As String) As Panel
	Log(sms)
	Dim p As Panel
	p.Initialize("")
	Dim lb As Label
	lb.Initialize("")
	lb.Width = 100%x-10dip
	lb.TextSize=mycode.textsize(8)
	lb.Typeface = mycode.mmfont
	lb.TextColor=Colors.White
	lb.Text  = sms
	lb.Gravity=Gravity.RIGHT
	p.AddView(lb,0,0,100%x-10dip,10dip)
	lb.Height  = su.MeasureMultilineTextHeight(lb,sms)
	
	messageHeight = lb.Height
	Return p
End Sub

Sub OtherMessageCs(name As String,sms As String) As CSBuilder
	Dim cs As CSBuilder
	cs.Initialize.Color(Colors.Yellow).Typeface(mycode.mmfont).Size(mycode.textsize(7)).Append(name&CRLF).Pop
	cs.Color(Colors.White).Typeface(mycode.mmfont).Size(mycode.textsize(7)).Append(sms).PopAll
	Return cs
End Sub


Sub numbar As Panel
	Dim pbase As Panel
	pbase.Initialize("")
	Dim cv As Panel
	cv.Initialize("")
	pbase.AddView(cv,5dip,5dip,100%x-10dip,40dip)
	Dim cd As ColorDrawable
	cd.Initialize(mycode.naviColor,10dip)
	cv.Background=cd
	cv.Elevation = 3dip
	cv.Color=mycode.naviColor
	Dim p As Panel
	p.Initialize("pp")
	cv.AddView(p,0,0,cv.Width,cv.Height)
	setlb.Initialize("")
	valuelb.Initialize("")
	twodlb.Initialize("")
	livelb.Initialize("")
	Dim w As Int  = (100%x-120dip)/2
	p.AddView(livelb,0,0,50dip,cv.Height)
	p.AddView(setlb,livelb.Width,0,w,cv.Height)
	p.AddView(valuelb,setlb.Width+setlb.Left,0,w,cv.Height)
	p.AddView(twodlb,valuelb.Width+valuelb.Left,0,50dip,cv.Height)
	livelb.Text = numcs("LIVE","--",Colors.White)
	setlb.Text = numcs("SET","----.--",Colors.White)
	valuelb.Text = numcs("VALUE","-----.--",Colors.White)
	twodlb.Text = numcs("2D","--",Colors.Yellow)
	livelb.Height = su.MeasureMultilineTextHeight(livelb,numcs("SET","----.--",Colors.White))
	setlb.Height = livelb.Height
	valuelb.Height = livelb.Height
	twodlb.Height = livelb.Height
	livelb.Gravity=Gravity.CENTER
	setlb.Gravity=Gravity.CENTER
	valuelb.Gravity=Gravity.CENTER
	twodlb.Gravity=Gravity.CENTER
	p.Height = livelb.Height
	cv.Height = livelb.Height
	numbarheight  = cv.Height+10dip
	Return pbase
End Sub

Sub numcs(typ As String,num As String,c As Int) As CSBuilder
	Dim cs As CSBuilder
	cs.Initialize.Color(Colors.Gray).Size(mycode.textsize(5)).Typeface(mycode.defaultfont).Append(typ&CRLF).Pop
	cs.Color(c).Size(mycode.textsize(7)).Typeface(mycode.semibold).Append(num).PopAll
	Return cs
End Sub




Sub tm_tick
	If DateTime.Now - livesse.lastime > 6000*3  Then
		If livesse.IsInitialized Then
			livesse.Finish
			livesse.Connect(Main.newsite)
		Else
			liveLoader
		End If
	End If
End Sub

Sub Change
Try
		checktime
		Dim json As JSONParser
		json.Initialize(File.ReadString(File.DirInternal,"live"))
		Dim ls As List = json.NextArray
		If ls.Size >0 Then
			For i  = 0 To ls.Size -1
				Dim m As Map  =ls.Get(i)
			Next
			If DateTime.GetDayOfWeek(DateTime.Now) <>0 Or DateTime.GetDayOfWeek(DateTime.Now)  <> 7 Then
				If DateTime.Now>DateTime.TimeParse("9:30:00") And DateTime.Now < DateTime.TimeParse("13:50:00") Then
					setlb.Text = numcs("SET",m.Get(LiveUtils.change("1200set")),Colors.White)
					valuelb.Text = numcs("VALUE",m.Get(LiveUtils.change("1200value")),Colors.White)
					twodlb.Text = numcs("2D",m.get(LiveUtils.change("1200")),Colors.Yellow)
				Else
					setlb.Text = numcs("SET",m.get(LiveUtils.change("430set")),Colors.White)
					valuelb.Text = numcs("VALUE",m.get(LiveUtils.change("430value")),Colors.White)
					twodlb.Text = numcs("2D",m.Get(LiveUtils.change("430")),Colors.Yellow)
				End If
			End If
			livelb.Text = numcs("LIVE",m.Get("live"),Colors.White)
			If m.Get("status")="On" Then
				livetimer.Enabled=True
				Else
					livetimer.Enabled=False
			End If
		End If
Catch
	Log(LastException)
End Try
End Sub

Sub checktime As String
	Dim tm As String 
	If DateTime.Now > DateTime.TimeParse("09:30:00") And DateTime.Now < DateTime.TimeParse("13:50:00") Then
		tm = "morning"
		Else
			tm = "evening"
	End If
	Return tm
End Sub

Sub livetimer_tick
	setlb.Text = numcs("SET","     ",Colors.White)
	valuelb.Text = numcs("VALUE","    ",Colors.White)
	livelb.Text = numcs("LIVE","  ",Colors.White)
	Sleep(300)
	Change
End Sub

Sub typingBar As Panel
	Dim p As Panel
	p.Initialize("")
	Dim devider As Panel
	devider.Initialize("")
	devider.Color=mycode.naviColor
'	p.AddView(devider,0,0,100%x,1dip)
	edt.Initialize("")
	edt.HintColor=Colors.Gray
	edt.TextColor=Colors.White
	edt.Hint="Type Something.."
	edt.InputType = edt.INPUT_TYPE_TEXT
	edt.Typeface=mycode.mmfont
	edt.TextSize=mycode.textsize(8)
	edt.Gravity=Gravity.LEFT+Gravity.TOP
	Dim cd As ColorDrawable
	cd.Initialize(mycode.naviColor,10dip)
	edt.Background=cd
	Dim sendbtn As Button
	sendbtn.Initialize("sendbtn")
	sendbtn.Background = mycode.btnbg(False)
	sendbtn.Typeface = Typeface.CreateNew(Typeface.MATERIALICONS,Typeface.STYLE_BOLD)
	sendbtn.Text =Chr(0xE163)
	sendbtn.TextColor=Colors.White
	sendbtn.TextSize = mycode.textsize(10)
	p.AddView(edt,5dip,5dip,100%x-55dip,45dip)
	p.AddView(sendbtn,100%x-45dip,5dip,40dip,40dip)
	sendbtn.Width = edt.Height
	sendbtn.Height = edt.Height
	sendbtn.Left = 100%x-(sendbtn.Width+5dip)
	
	typbarheight = edt.Height+10dip
	Return p
End Sub



Sub progressShow
	AXrLottie.Initialize
	LottieView.Initialize("")
	Dim left As Int = (100%x-100dip)/2
	Dim top As Int  = (mainscv.Height-100dip)/2
	mainscv.Panel.AddView(LottieView,left,top,100dip,100dip)
	Dim Drawable As AXrLottieDrawableBuilder
	Drawable.InitializeFromFile(File.DirAssets,"loading.json") _
				.SetAutoRepeat(Drawable.AUTO_REPEAT_INFINITE) _
				.SetAutoStart(True) _
				.SetCacheEnabled(False)
	LottieView.SetLottieDrawable(Drawable.Build)
	LottieView.BringToFront
	LottieView.Visible=True
End Sub

Sub progressHide
	LottieView.Visible=False
End Sub

Sub loadMessageSuccess(data As String)
	
	progressHide
	
	' Check if data starts with [ or {
	If data.StartsWith("[") Then
		Dim json As JSONParser
		json.Initialize(data)
		Dim ls As List = json.NextArray
		If ls.Size > 0 Then
		
			loadsmscount = loadsmscount + 1
			top = 10dip
			
			For i = 0 To ls.Size - 1
				Dim m As Map = ls.Get(i)
				If ShouldDisplayMessage(m) Then
					
					If i= ls.Size-1 And mainscv.Panel.Height<mainscv.Height  Then
						
						Else If i = 0 Then
						Else
					End If
					Dim othsmspn As Panel
					If m.Get("id")= mycode.getUserId(mycode.id) Then
						Log(m)
						othsmspn=MyMessage(m.Get("message"))
						Else
						othsmspn = OtherMessage(m.Get("profile_pic"), m.Get("message"), m.Get("name"), m.Get("id"), top)
					End If
					
					mainscv.Panel.AddView(othsmspn, 0, top, 100%x, messageHeight)
					top=top + messageHeight + 5dip
				End If
			Next
			
			mainscv.Panel.Height = top + 5dip
			If loadsmscount = 1 Then
				Sleep(0)
				mainscv.FullScroll(True)
			End If
		End If
		
	Else If data.StartsWith("{") Then
		If loadsmscount = 0 Then
		mainscv.Panel.RemoveAllViews
		End If
		loadsmscount = loadsmscount +1
		Dim json As JSONParser
		json.Initialize(data)
		Dim m As Map = json.NextObject
		If ShouldDisplayMessage(m) Then
		
			Dim othsmspn As Panel
					
			If m.Get("id")= mycode.getUserId(mycode.id) Then
				Log(m.Get("message"))
				othsmspn=MyMessage(m.Get("message"))
			Else
				othsmspn = OtherMessage(m.Get("profile_pic"), m.Get("message"), m.Get("name"), m.Get("id"), top)
					
						
			End If
			
			mainscv.Panel.AddView(othsmspn, 0, top, 100%x, messageHeight)
			top = top + messageHeight +5dip
			mainscv.Panel.Height = top +  5dip
			Sleep(0)
			mainscv.FullScroll(True)
		End If
	End If
	mycode.checkentertime = mycode.checkentertime +1
End Sub

Sub ShouldDisplayMessage(m As Map) As Boolean
	Dim b As Boolean = True
	If blockList.Size > 0 Then
		For a = 0 To blockList.Size - 1
			Dim mblock As Map = blockList.Get(a)
			If mblock.Get(mycode.id) = m.Get(mycode.id) Then
				b=False
				Else
					b=True
			End If
		Next
	End If
	Return b
End Sub


'
'Sub loadMessageSuccess(data As String)
'	progressHide
'	Dim json As JSONParser
'	json.Initialize(data)
'	Dim ls As List = json.NextArray
'	If ls.Size > 0 Then
'	mainscv.Panel.RemoveAllViews
'		Dim shouldAdd As Boolean
'		loadsmscount=loadsmscount+1
'		Dim top As Int  =10dip
'		For i   = 0 To ls.Size -1
'			Dim m As Map = ls.Get(i)
'			
'			If blockList.Size>0 Then
'				For a = 0  To blockList.Size -1
'					Dim mblock As Map = blockList.Get(a)
'					If mblock.Get(mycode.id) = m.Get(mycode.id) Then
'						shouldAdd = False
'						Else
'							shouldAdd = True
'					End If
'				Next
'				If shouldAdd = False Then
'				Else
'					Dim othsmspn As Panel = OtherMessage(m.Get("profile_pic"),m.Get("message"),m.Get("name"),m.Get("id"),top)
'					mainscv.Panel.AddView(othsmspn,0,top,100%x,messageHeight)
'				End If
'				Else
'				Dim othsmspn As Panel = OtherMessage(m.Get("profile_pic"),m.Get("message"),m.Get("name"),m.Get("id"),top)
'				mainscv.Panel.AddView(othsmspn,0,top,100%x,messageHeight)
'			End If
'			
'			
'			top = top + messageHeight+5dip
'		Next
'		mainscv.Panel.Height = top+5dip
'		If loadsmscount = 1 Then
'			Sleep (0)
'			mainscv.FullScroll(True)
'		End If
'	End If
'End Sub

Sub backbtn_click
	Activity.Finish
End Sub

Sub AddBlock(m As Map)
	If File.Exists(File.DirInternal,"block") Then
		Dim js As JSONParser
		js.Initialize(File.ReadString(File.DirInternal,"block"))
		Dim ls As List = js.NextArray
		Dim shouldAdd As Boolean 
		If ls.Size > 0 Then
			For i = 0 To ls.Size -1
				Dim mm As Map = ls.Get(i)
				If mm.Get(mycode.id) = m.Get(mycode.id) Then
					shouldAdd = False
					Exit
				Else
					shouldAdd =True
				End If
			Next
			If shouldAdd =True Then
				ls.Add(m)
			End If
			
			Else
				ls.Add(m)
		End If
		Dim jg As JSONGenerator
		jg.Initialize2(ls)
		File.WriteString(File.DirInternal,"block",jg.ToString)
		Log("OLD BLOCK")
		Else
			Log("NEW BLOCK")
			Dim lnew As List
			lnew.Initialize
			lnew.Add(m)
		 Dim json As JSONGenerator
		 json.Initialize2(lnew)
		File.WriteString(File.DirInternal,"block",json.ToString)
	End If
	CallSubDelayed2(Me,"loadMessageSuccess",mycode.chatjson)
End Sub

Sub blockList As List
	Dim json As JSONParser
	Dim ls As List
	ls.Initialize
	If File.Exists(File.DirInternal,"block") Then
	json.Initialize(File.ReadString(File.DirInternal,"block"))
		ls = json.NextArray
	End If
	Return ls
End Sub


Sub sendmessage
	If edt.Text <>"" Then
		If File.Exists(File.DirInternal,"user") Then
			Dim j As HttpJob
			j.Initialize(sendSMS,Starter)
			Dim json As JSONGenerator
			Dim m As Map
			m.Initialize
			m.Put("id",mycode.getUserId(mycode.id))
			m.Put("name",mycode.getUserId(mycode.name))
			m.Put("profile_pic",mycode.getUserId(mycode.profile_pic))
			m.Put("message",edt.Text)
			m.Put("create_at",DateTime.Now)
			edt.Text=""
			json.Initialize(m)
			j.PostString(Main.newsite&"chat/sendmessage",json.ToString)
		Else
			ToastMessageShow("You Need To Login",False)
			StartActivity(Login)
		End If
	Else
		ToastMessageShow("please text something..",False)
End If
End Sub

Sub sendbtn_click
	sendmessage
End Sub