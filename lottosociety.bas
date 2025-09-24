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
	
	Dim su As StringUtils
	
	Dim lottogetter As String = "lottogetter"
	Dim isCall As Boolean
End Sub

Sub Globals
	Dim lottieview As AXrLottieImageView
	
	Dim datelb As Label
	Dim numlb As Label
	Dim scv As ScrollView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.Color=mycode.bgColor
	Dim appbar As Panel = mycode.appbar("ထုတ်လွှင့်မှုမှတ်စဥ် ၉",False)
	Activity.AddView(appbar,0,mycode.appbartop,100%x,mycode.appbarheight)
	Dim historybtn As Button
	historybtn.Initialize("historybtn")
	historybtn.Background=mycode.btnbg(False)
	historybtn.Text = buttoncsb(False,Chr(0xF1EC),True)
	appbar.AddView(historybtn,100%x-(mycode.appbarheight),0,mycode.appbarheight,mycode.appbarheight)
	mycode.SETnavigationcolor
	
	scv.Initialize(1000dip)
	Activity.AddView(scv,0,mycode.appbarheight+5dip,100%x,mycode.ActivityHeight-mycode.appbarheight+5dip)
	
	Dim axrlottie As AXrLottie
	axrlottie.Initialize
	lottieview.Initialize("")
	Dim left As Int = (100%x-150dip)/2
	Sleep(0)
	scv.Panel.AddView(lottieview,left,(mycode.ActivityHeight-150dip)/2,150dip,150dip)
	Dim Drawable As AXrLottieDrawableBuilder
	Drawable.InitializeFromFile(File.DirAssets,"peace.json") _
				.SetAutoRepeat(Drawable.AUTO_REPEAT_INFINITE) _
				.SetAutoStart(True) _
				.SetCacheEnabled(False)
	lottieview.SetLottieDrawable(Drawable.Build)

	
	ApiCall.getlottodata
'	Dim p As Panel
'	p.Initialize("")
'	scv.Panel.AddView(p,(100%x-300dip)/2,Ccard.Height+Ccard.Top+10dip,300dip,250dip)
'	
'	Dim pp As Panel
'	pp.Initialize("")
'	scv.Panel.AddView(pp,10dip,p.Height+p.Top+10dip,100%x-20dip,(mycode.ActivityHeight-(p.Height+p.Top+10dip)))
'	pp.Color=Colors.Gray
'	
'	scv.Panel.Height = pp.Height+pp.Top+10dip
'	
'	p.Color=Colors.Gray
'	If Main.adErrorCode = 0 Then
'		ToastMessageShow("failed to receive ad with this network",True)
'	End If
End Sub

Sub Activity_Resume
isCall =True
End Sub

Sub Activity_Pause (UserClosed As Boolean)
isCall =False
End Sub

Sub backbtn_click
	Activity.Finish
End Sub

Sub historybtn_click
	StartActivity(LottoHistory)
End Sub

Sub buttoncsb(home As Boolean,text As String,iconic As Boolean) As CSBuilder
	Dim csb As CSBuilder
	csb.Initialize
	If home = True Then

		csb.Color(Colors.Green).Typeface(Typeface.DEFAULT_BOLD).Size(20).Append(text).PopAll
	Else
		If iconic = True Then
			If text  = Chr(0xF06B) Then
				csb.Color(0xFFDBDBDB).Typeface(Typeface.FONTAWESOME).Size(35).Append(text).PopAll
			Else
				csb.Color(0xFFDBDBDB).Typeface(Typeface.FONTAWESOME).Size(20).Append(text).PopAll
			End If
		Else
			
		
			csb.Color(0xFFDBDBDB).Typeface(Typeface.DEFAULT_BOLD).Size(20).Append(text).PopAll
					
		End If
			
	End If
	Return csb
End Sub

Sub channelcard(pw As Int,date As String,fnum As String, snum As String) As Panel
	
	Dim p As Panel
	p.Initialize("")
	p.Elevation =5dip
	Dim cd As ColorDrawable
	cd.Initialize(mycode.naviColor,10dip)
	p.Background=cd
	Dim apptitlelb As Label
	
	apptitlelb.Initialize("")
	apptitlelb.Text= "Burma 2D 2025"
	apptitlelb.Typeface=mycode.semibold
	apptitlelb.TextColor=0xFFF4F4F4
	apptitlelb.Gravity=Gravity.CENTER
	apptitlelb.TextSize = mycode.textsize(11)
	
	Dim img As ImageView
	img.Initialize("")
	img.SetBackgroundImage(LoadBitmap(File.DirAssets,"logor.webp"))
	img.Gravity=Gravity.FILL
	Dim img1 As ImageView
	img1.Initialize("")
	img1.SetBackgroundImage(LoadBitmap(File.DirAssets,"logor.webp"))
	img1.Gravity=Gravity.FILL
	

	datelb.Initialize("")
	datelb.Text=date
	datelb.SingleLine=True
	datelb.TextSize = mycode.textsize(9)
	datelb.Typeface=mycode.defaultfont
	datelb.TextColor=Colors.White
	datelb.Gravity=Gravity.CENTER
	
	Dim namelb As Label
	namelb.Initialize("")
	namelb.SingleLine=True
	namelb.Text="Thai Stock Analysis"
	namelb.TextSize = mycode.textsize(9)
	namelb.Typeface=mycode.semibold
	namelb.TextColor=Colors.Yellow
	namelb.Gravity=Gravity.CENTER
	
	
	numlb.Initialize("")
	numlb.TextSize =mycode.textsize(13)
	numlb.TextColor=Colors.White
	numlb.Text = fnum&Chr(9)&Chr(9)&snum
	numlb.Gravity=Gravity.CENTER
	numlb.Typeface=mycode.semibold
	
	Dim w As Int = 40dip
	p.AddView(img,10dip,10dip,w,w)
	p.AddView(apptitlelb,img.Width+img.Left,10dip,pw-(120dip),w)
	p.AddView(img1,(pw)-(w+10dip),10dip,w,w)
	p.AddView(datelb,0,img.Height+apptitlelb.Top+15dip,pw,w)
	datelb.Height=su.MeasureMultilineTextHeight(datelb,datelb.Text)
	p.AddView(namelb,0,datelb.Height+datelb.Top+18dip,pw,w)
	namelb.Height=su.MeasureMultilineTextHeight(namelb,namelb.Text)
	p.AddView(numlb,0,namelb.Height+namelb.Top+13dip,pw,w)
	
	p.Height =numlb.Height + numlb.Top+10dip
	
	Return p
End Sub



Sub getlottosuccess
	If ApiCall.lottodata <> "" Then
		lottieview.Visible=False
		lottieview.RemoveView
		Dim json As JSONParser
		json.Initialize(ApiCall.lottodata)
		
			Dim  m As Map = json.NextObject
			Dim p As Panel
			p.Initialize("")
			
			Dim Ccard As Panel = channelcard(100%x-20dip,m.Get("thaidate"),m.Get("fnum"),m.Get("snum"))
			Ccard.SetLayoutAnimated(1000,10dip,10dip,scv.Width-20dip,Ccard.Height)
			p.AddView(Ccard,10dip,10dip,100%x-20dip,Ccard.Height)
			p.Height=Ccard.Height+Ccard.Top+10dip
			Dim rw As Panel = review(Ccard)
			rw.SetLayoutAnimated(1000,10dip,10dip,scv.Width-20dip,rw.Height)
			p.AddView(rw,10dip,Ccard.Height+Ccard.Top+10dip,100%x-20dip,rw.Height)
			p.Height = rw.Height+rw.Top+10dip
			If m.Get("text")<>"" Then
			Dim mnumber As Panel = marketnumber (m.Get("text"))
			mnumber.SetLayoutAnimated(1500,10dip,10dip,scv.Width-20dip,mnumber.Height)
			p.AddView(mnumber,10dip,rw.Height+rw.Top+10dip,100%x-20dip,mnumber.Height)
			p.Height  =mnumber.Height+mnumber.Top+10dip
			End If

		scv.Panel.AddView(p,0,(scv.Height-p.Height)/2,100%x,p.Height)
			scv.Panel.Height = p.Height+p.Height+10dip
		
		Else
		ApiCall.getlottodata
	End If
End Sub


Sub marketnumber (txt As String) As Panel
	Dim ls As List = Regex.Split(CRLF,txt)
	Dim p As Panel
	p.Initialize("")
	Dim lb As Label
	lb.Initialize("")
	Dim cs As CSBuilder
	cs.Initialize.Color(0xFFEFEFEF).Typeface(mycode.defaultfont).Size(mycode.textsize(10)).Append(ls.Get(0)&CRLF&CRLF).Pop
	cs.Color(Colors.White).Typeface(mycode.semibold).Size(mycode.textsize(10)).Append(ls.Get(1)&CRLF&CRLF&ls.Get(2)).PopAll
	lb.Text= cs
	lb.Gravity=Gravity.CENTER
	Dim cd As ColorDrawable
	cd.Initialize(mycode.naviColor,10dip)
	p.Background=cd
	p.Width = 100%x-20dip
	p.AddView(lb,10dip,10dip,p.Width-20dip,10dip)
	lb.Height = su.MeasureMultilineTextHeight(lb,cs)
	p.Height = lb.Height+lb.Top+10dip
	Return p
End Sub

Sub review(tag As Panel) As Panel
	Dim p As Panel
	p.Initialize("")
	Dim cd As ColorDrawable
	cd.Initialize(mycode.naviColor,10dip)
	p.Background=cd
	Dim btn As Button
	Dim tlb As Label
	Dim blb As Label
	btn.Initialize("review")
	p.Width = 100%x-20dip
	tlb = lbbb(Typeface.CreateNew(mycode.mmfont,Typeface.STYLE_BOLD),Colors.White,"လူကြီးမင်းခင်ဗျာ",10,False)
	p.AddView(tlb,10dip,10dip,p.Width-20dip,tlb.Height)
	Dim top As Int =tlb.Height +tlb.Top+10dip
	blb = lbbb(mycode.mmfont,Colors.White,"သုံးရတာကြိုက်ပါက 5⭐ပေးနိုင်ပါသည်",9,False)
	blb.Gravity=Gravity.CENTER
	p.AddView(blb,10dip,top,p.Width-20dip,blb.Height)
	top = blb.Height +blb.Top+10dip
	btn.Background=mycode.btnbgdynamic(Colors.White,Colors.Gray,10dip)
	btn.Typeface=mycode.mmfont
	btn.Text="★ ပေးမယ်"
	btn.TextColor=mycode.bgColor
	btn.Textsize = mycode.textsize(8.5)
	Dim w As Int = (p.Width-30dip)/2

	Dim btn1 As Button
	btn1.Initialize("savebtn")
	btn1.Background=mycode.btnbgdynamic(Colors.White,Colors.Gray,10dip)
	btn1.TextColor=mycode.bgColor
	btn1.Typeface=mycode.mmfont
	btn1.TextSize=mycode.textsize(8.5)
	btn1.Text="💾 ပုံသိမ်းမယ်"

	btn1.Tag=tag
	
	p.AddView(btn1,10dip,top,w,45dip)
	p.AddView(btn,btn1.Width+btn1.Left+10dip,top,w,45dip)
	
	p.Height = btn.Height+btn.Top+10dip
	Return p
End Sub

Sub lbbb(tp As Typeface,color As Int,text As String,size As Int,iscs As Boolean) As Label
	Dim lb As Label
	lb.Initialize("")
	lb.Text= text
	lb.Width = 100%x-40dip
	If iscs =True Then
		Else
		lb.Typeface = tp
		lb.TextColor=color
		lb.TextSize = mycode.textsize (size)
	End If
	Dim su As StringUtils
	lb.Height = su.MeasureMultilineTextHeight(lb,text)
	Return lb
End Sub

Sub review_click
	Dim pi As PhoneIntents
	StartActivity(pi.OpenBrowser("https://play.google.com/store/apps/details?id=com.burma.royal2d"))
End Sub

Sub savebtn_click
	Dim b As Button =Sender
	Dim p As Panel = b.Tag
	Dim x As B4XView = p
	Dim bb As B4XBitmap = x.Snapshot
	mycode.AddBitmapToGallery1(bb,DateTime.Now&".png","image/png")
End Sub