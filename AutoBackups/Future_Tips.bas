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
	Private NumColumns As Int = 3
	Private ItemSize As Int = 100dip
	Dim Gap As Int = 2dip
	Dim NumColumns As Int = 3
	Dim papergetters As String="papergetter"
	Dim totalGapWidth As Int = Gap * (NumColumns + 1) ' e.g. 5 * 4 = 20dip
	Dim itemWidth As Int
	Dim progresstimer As Timer
	Dim isCall As Boolean
	Dim imgViewing As Boolean
	Dim showsavemsg As Boolean
	Dim xui As XUI
	Dim adreceived As Boolean
End Sub

Sub Globals
	Dim ASViewPager1 As ASViewPager
	Dim lbldaily,lblweekly,lblcalendar As Label
	Dim menubtn As Button
	Dim inter As InterstitialAd

	Dim glide As Amir_Glide
	Dim progresspn As Panel
	Private sv As ScrollView
	Dim banner As AdView
	Private ASViewPager2 As ASViewPager
End Sub

Sub Activity_Create(FirstTime As Boolean)
	inter.Initialize("inter",Main.interUnit)
	inter.LoadAd
	Activity.Color=mycode.bgColor
	mycode.SETnavigationcolor
	Dim appbar As Panel = mycode.appbar("ထိုင်းကဒ် / အတိတ်စာရွက်",False)
	Activity.AddView(appbar,0,mycode.appbartop,100%x,mycode.appbarheight)
	
	sv.Initialize(mycode.ActivityHeight)
	Activity.AddView(sv, 0, mycode.appbarheight, 100%x, mycode.ActivityHeight-mycode.appbarheight)
	itemWidth=   (100%x - totalGapWidth) / NumColumns
	progresspn.Initialize("")
	Activity.AddView(progresspn,0,mycode.appbarheight,100%x,2dip)
	progresspn.Color=Colors.Yellow
	progresstimer.Initialize("ptimer",50)
	progresstimer.Enabled=True
	papergetter
End Sub


Sub Activity_Resume
	If inter.Ready = False Then
		Log("load ad")
		inter.LoadAd
	Else
		Log("receive ad")
	End If
	
	If banner.IsInitialized Then
		If adreceived = True Then
			Else
				banner.LoadAd
		End If
		Else
		banner.Initialize("banner",Main.smallbannerunit)
		banner.LoadAd
	End If
	If Main.adErrorCode <>0 Then
		banner.RemoveView
		Activity.AddView(banner,0,100%y-50dip,100%x,50dip)
	End If
	
	isCall =True
	itemWidth=   (100%x - totalGapWidth) / NumColumns

End Sub

Sub Activity_Pause (UserClosed As Boolean)
isCall =False
End Sub

Sub backbtn_click
	Activity.Finish
End Sub

Sub banner_ReceiveAd
	adreceived =True
End Sub

Sub inter_ReceiveAd
	Log("inter receive")
End Sub

Sub inter_AdClosed
	imgViewing=True
End Sub

Sub inter_AdOpened
	Dim m As Map = store.tempmap
	imageView(m.Get("item"),m.Get("index"))
End Sub

Sub ptimer_tick
	If progresspn.Width = 100%x Then
		progresspn.Width = 0
	Else
		progresspn.Width =progresspn.Width+10%x
	End If
End Sub

Sub progressHide
	progresspn.Visible=False
	progresstimer.Enabled=False
End Sub


Sub BuildGrid1(items As List, ParentPanel As Panel)
	Dim totalItems As Int = items.Size
	Dim totalGapWidth As Int = Gap * (NumColumns + 1)
	Dim itemWidth As Int = (100%x - totalGapWidth) / NumColumns
	Dim ItemSize As Int = itemWidth * 1.5

	Dim numRows As Int = Ceil(totalItems / NumColumns)
	Dim panelHeight As Int = numRows * (ItemSize + Gap) + Gap

	Dim pnl As Panel
	pnl.Initialize("")
	ParentPanel.AddView(pnl, 0, 0, 100%x, panelHeight)

	Dim bd As BitmapDrawable
	bd.Initialize(LoadBitmap(File.DirAssets,"pholder.webp"))

	Dim glide As Amir_Glide
	glide.Initializer.Default
	glide.RequestManager.ApplyDefaultRequestOptions(glide.RO.ErrorDrawable(bd).Placeholder(bd).CenterCrop)

	For i = 0 To items.Size - 1
		Dim row As Int = Floor(i / NumColumns)
		Dim col As Int = i Mod NumColumns
		Dim left As Int = Gap + col * (itemWidth + Gap)
		Dim top As Int = Gap + row * (ItemSize + Gap)

		Dim itemPanel As ImageView
		itemPanel.Initialize("item")
		pnl.AddView(itemPanel, left, top, itemWidth, ItemSize)

		glide.Load(items.Get(i)).Apply(glide.RO.CenterCrop).Into(itemPanel)

		Dim m As Map
		m.Initialize
		m.Put("item", items.Get(i))
		m.Put("index", i)
		itemPanel.Tag = m
	Next
	ParentPanel.Height = panelHeight
End Sub
Sub LoadGridPages(dailyItems As List, weeklyItems As List, calendarItems As List)

	
	Dim pp As Panel
	pp.Initialize("")
	lbldaily.Initialize("lbldaily")
	lblweekly.Initialize("lblweekly")
	lblweekly.Text = "တစ်ပတ်စာ"
	lblweekly.Typeface=mycode.mmfont
	lblweekly.TextColor=Colors.White
	lblweekly.Color=mycode.bgColor
	lbldaily.Text = "တစ်ရက်စာ"
	lbldaily.Typeface=mycode.mmfont
	lbldaily.TextColor=Colors.White
	lbldaily.Color=mycode.naviColor
	
	lblcalendar.Initialize("lblcalendar")
	lblcalendar.Text="ကလင်ဒါ"
	lblcalendar.Typeface=mycode.mmfont
	lblcalendar.TextColor=Colors.White
	lblcalendar.Color=mycode.bgColor
	
	Log("here >>>>>>>>>")
	lblcalendar.Gravity=Gravity.CENTER
	lbldaily.Gravity=Gravity.CENTER
	lblweekly.Gravity=Gravity.CENTER
	Dim su As StringUtils
	Dim w As Int = (100%x)/3
	Activity.AddView(lbldaily,0,mycode.appbarheight+1dip,w,20dip)
	Activity.AddView(lblweekly,lbldaily.Width+lbldaily.Left,mycode.appbarheight+1dip,w,20dip)
	Activity.AddView(lblcalendar,lblweekly.Width+lblweekly.Left,mycode.appbarheight+1dip,w,20dip)
	
	lbldaily.Height = su.MeasureMultilineTextHeight(lbldaily,lbldaily.Text)
	lblweekly.Height = su.MeasureMultilineTextHeight(lblweekly,lblweekly.Text)
	lblcalendar.Height = su.MeasureMultilineTextHeight(lblcalendar,lblcalendar.Text)
	
	If Main.adErrorCode  = 0 Then
		Log("here")
		Dim vpHeight As Int = 100%y - (lbldaily.Height+lbldaily.Top+1dip)
		Else
		Dim vpHeight As Int = 100%y - (lbldaily.Height+lbldaily.Top+1dip+55dip)
	End If
	Activity.AddView(pp, 0, lbldaily.Height+lbldaily.Top+1dip, 100%x, vpHeight)
	pp.LoadLayout("gridpages")
	
	
	ASViewPager2.Clear
	ASViewPager2.LazyLoading = False
	Dim xui As XUI

	' === Daily Page ===
	Dim dailyPanel As B4XView = xui.CreatePanel("")
	dailyPanel.SetLayoutAnimated(0, 0, 0, 100%x, vpHeight)
	dailyPanel.Color=mycode.bgColor

	Dim svDaily As ScrollView
	svDaily.Initialize(vpHeight)
	dailyPanel.AddView(svDaily, 0, 0, 100%x, vpHeight)

	BuildGrid1(dailyItems, svDaily.Panel)
	ASViewPager2.AddPage(dailyPanel, "daily")

	' === Weekly Page ===
	Dim weeklyPanel As B4XView = xui.CreatePanel("")
	weeklyPanel.SetLayoutAnimated(0, 0, 0, 100%x, vpHeight)
	weeklyPanel.Color=mycode.bgColor

	Dim svWeekly As ScrollView
	svWeekly.Initialize(vpHeight)
	weeklyPanel.AddView(svWeekly, 0, 0, 100%x, vpHeight)

	BuildGrid1(weeklyItems, svWeekly.Panel)
	ASViewPager2.AddPage(weeklyPanel, "weekly")
	
	' === Calendar Page ===
	Dim calendarPanel As B4XView = xui.CreatePanel("")
	calendarPanel.SetLayoutAnimated(0, 0, 0, 100%x, vpHeight)
	calendarPanel.Color=mycode.bgColor

	Dim svCalendar As ScrollView
	svCalendar.Initialize(vpHeight)
	calendarPanel.AddView(svCalendar, 0, 0, 100%x, vpHeight)
	
	BuildGrid1(calendarItems, svCalendar.Panel)
	ASViewPager2.AddPage(calendarPanel, "calendar")

	ASViewPager2.Commit
End Sub

Sub lblcalendar_click
	ASViewPager2.CurrentIndex =2
End Sub
Sub lbldaily_click
	ASViewPager2.CurrentIndex= 0
End Sub

Sub lblweekly_click
	ASViewPager2.CurrentIndex = 1
End Sub

Sub papergetter
	Dim j As HttpJob
	j.Initialize(papergetters,Starter)
	j.Download(Main.newsite&"/futurepaper/getallpaper")
End Sub

Sub getpapersuccess
	If mycode.paperdata <> "" Then
		progressHide
		Dim json As JSONParser
		json.Initialize(mycode.paperdata)
		Dim m As Map = json.NextObject
		Dim ls As List = m.Get("daily")
		Dim ls1 As List = m.Get("weekly")
		Dim ls2 As List = m.Get("calendar")
		mycode.futureimglist = ls
		mycode.weeklyimglist = ls1
		mycode.calendarimglist = ls2
	
		LoadGridPages(ls,ls1,ls2)
		Else
			papergetter
	End If
End Sub

Sub item_click
	Dim p As ImageView =Sender
	Dim m As Map = p.Tag
	If inter.Ready Then
		inter.Show
			store.tempmap = m
		Else
		
			imageView(m.Get("item"),m.Get("index"))
	End If
End Sub

Sub imageView(imgl As String,i As Int)
	mycode.SETnavigationcolor1
	imgViewing= True
	Dim closebtn As Button
	closebtn.Initialize("close")
	closebtn.Background= mycode.btnbg(False)
	closebtn. Text = mycode.buttoncsb(False,Chr(0xF00D),True)
	Dim p As Panel
	p.Initialize("ppp")
	p.Color=mycode.bgColor
	
	closebtn.Tag= p
	
	If Main.adErrorCode <> 0 Then
		Activity.AddView(p,0,0,100%x,mycode.ActivityHeight-55dip)
		Else
		Activity.AddView(p,0,0,100%x,mycode.ActivityHeight)
	End If

	Dim xui As XUI
	Private Panel1 As Panel
	Panel1.Initialize("")
	Panel1.Color=Colors.Blue
	p.AddView(Panel1,0,50dip,100%x,p.Height-50dip)
	Panel1.LoadLayout("viewpager")

	ASViewPager1.LazyLoading = True
	ASViewPager1.IgnoreLazyLoading=False
	ASViewPager1.LazyLoadingExtraSize=4
	Dim ls As List
	ls = setList
	For a = 0 To ls.Size-1

		Dim pimg As B4XView = xui.CreatePanel("")
		pimg.SetLayoutAnimated(0,0,0,ASViewPager1.Base.Width,ASViewPager1.Base.Height)
		pimg.Color = mycode.bgColor
		Dim img As ImageView
		img.Initialize("imgv")

		pimg.AddView(img,0,0,pimg.Width,pimg.Height)
		Dim glide As Amir_Glide
		glide.Initializer.Default
		glide.Load(ls.Get(a)).Apply(glide.RO.CenterInside).Into(img)
		img.Tag = ls.Get(a)
		ASViewPager1.AddPage(pimg,ls.Get(a))
	Next
	Sleep(0)
	ASViewPager1.CurrentIndex=i
	ASViewPager1.Commit
	p.AddView(closebtn,100%x-mycode.appbarheight,0,mycode.appbarheight,mycode.appbarheight)
	menubtn.Initialize("menu")
	menubtn.Tag= ls.Get(ASViewPager1.CurrentIndex)
	menubtn.Background = mycode.btnbg(False)
	menubtn.Text=mycode.buttoncsb(False,Chr(0xF0C7),True)
	menubtn.TextColor=Colors.White
	p.AddView(menubtn,closebtn.Left-mycode.appbarheight,0,mycode.appbarheight,mycode.appbarheight)
End Sub

Sub setList  As List
	Select ASViewPager2.CurrentIndex
		Case 0 
			Return mycode.futureimglist
			Case 1
			Return mycode.weeklyimglist	
			Case 2
				Return mycode.calendarimglist
	End Select
End Sub

Sub imgv_LongClick
'	Dim i As ImageView = Sender
'	Savemsg(i.Tag)
End Sub
Sub ASViewPager1_PageChange(Index As Int)
	Dim ls As List = setList
	Select ASViewPager2.CurrentIndex
		Case 0
			If menubtn.IsInitialized Then
				menubtn.Tag = ls.Get(Index)
			End If
			Case 1
			If menubtn.IsInitialized Then
				menubtn.Tag = ls.Get(Index)
			End If
				Case 2
			If menubtn.IsInitialized Then
				menubtn.Tag = ls.Get(Index)
				
			End If
			
	End Select
End Sub

Sub ASViewPager1_PageClick (Index As Int, Value As Object)

End Sub

Sub ASViewPager1_LazyLoadingAddContent(Parent As B4XView, Value As Object)
	Try
		
		Dim img As ImageView
		img.Initialize("imgv")
		img.Gravity = Gravity.FILL
	
		Parent.AddView(img, 0, 0, Parent.Width, Parent.Height)
		Dim glide As Amir_Glide
		glide.Initializer.Default
		glide.Load(Value).Apply(glide.RO.CenterInside).Into(img)

		img.Tag = Value
	Catch
		Log(LastException)
	End Try
End Sub


Sub ppp_Click
	
End Sub

Sub close_click
	mycode.SETnavigationcolor
	Dim b As Button = Sender
	Dim p As Panel = b.Tag
	p.Visible=False
	p.RemoveView
	If showsavemsg = True Then
		showsavemsg=False
		Activity.RemoveViewAt(Activity.NumberOfViews-1)
	End If
	imgViewing=False
End Sub

Sub activity_KeyPress (KeyCode As Int) As Boolean 'Return True to consume the event
	
	If KeyCode = KeyCodes.KEYCODE_BACK Then
		If imgViewing = True Then
				mycode.SETnavigationcolor
				imgViewing =False
				Activity.RemoveViewAt(Activity.NumberOfViews-1)
		Else
				Activity.Finish
		End If
		
		
	End If
	Return True
End Sub

Sub menu_click
	Dim b As Button = Sender
	saveimage(b.Tag)
'	Dim b As Button = Sender
'	
'	Savemsg(b.Tag)
End Sub

Sub Savemsg (link As String)
	showsavemsg=True
	Dim base As Panel
	base.Initialize("pbase")
	Dim p As Panel
	p.Initialize("")
	p.Color=mycode.naviColor
	Dim btn As Button
	btn.Initialize("save")
	btn.Background=mycode.btnbgdynamic(Colors.Transparent,0x9A466584,0)
	Dim cs As CSBuilder
	cs.Initialize.Color(Colors.Yellow).Size(mycode.textsize(10)).Typeface(Typeface.FONTAWESOME).Append(Chr(0xF0C7)).Pop
	cs.Color(Colors.White).Typeface(mycode.defaultfont).Size(mycode.textsize(8)).Append("  Save").PopAll
	btn.Text = cs
	p.AddView(btn,0,0,150dip,50dip)
	Dim m As Map
	m.Initialize
	m.Put("view",base)
	m.Put("link",link)
	btn.Tag=m
	Activity.AddView(base,0,0,100%x,mycode.ActivityHeight)
	base.AddView(p,100%x-160dip,mycode.ActivityHeight-50dip,150dip,50dip)
End Sub

Sub pbase_Click
	Dim p As Panel =Sender
	If showsavemsg =True Then
		showsavemsg=False
		p.Visible=False
		p.RemoveView
	End If
End Sub

Sub save_click
	Dim b As Button =Sender
	Dim m As Map = b.Tag
		showsavemsg =False
		Dim p As Panel = m.Get("view")
		p.Visible=False
		p.RemoveView
	saveimage(m.Get("link"))
End Sub

Sub saveimage (imglink As String)
	Dim j As HttpJob
	j.Initialize("saveimage",Starter)
	j.Download(imglink)
End Sub


Private Sub ASViewPager2_PageClick (Index As Int, Value As Object)
	
End Sub

Private Sub ASViewPager2_PageChange(Index As Int)
	Select Index
		Case 0 
			lbldaily.Color=mycode.naviColor
			lblweekly.Color=mycode.bgColor
			lblweekly.TextColor=Colors.Gray
			lbldaily.TextColor=Colors.White
			lblcalendar.Color=mycode.bgColor
			lblcalendar.TextColor=Colors.Gray
		Case 1
			lblweekly.Color=mycode.naviColor
			lbldaily.Color=mycode.bgColor
			lblcalendar.Color=mycode.bgColor
			lblcalendar.TextColor=Colors.Gray
			lbldaily.TextColor=Colors.Gray
			lblweekly.TextColor=Colors.White
		Case 2
			lblweekly.Color=mycode.bgColor
			lbldaily.Color=mycode.bgColor
			lblcalendar.Color=mycode.naviColor
			lblcalendar.TextColor=Colors.White
			lbldaily.TextColor=Colors.Gray
			lblweekly.TextColor=Colors.Gray
	End Select
End Sub
'
'Private Sub ASViewPager2_PageChanged(Index As Int)
'	Select Index
'		Case 0
'			lbldaily.Color=mycode.naviColor
'			lblweekly.Color=mycode.bgColor
'			lblweekly.TextColor=Colors.Gray
'			lbldaily.TextColor=Colors.White
'			lblcalendar.Color=mycode.bgColor
'			lblcalendar.TextColor=Colors.Gray
'		Case 1
'			lblweekly.Color=mycode.naviColor
'			lbldaily.Color=mycode.bgColor
'			lblcalendar.Color=mycode.bgColor
'			lblcalendar.TextColor=Colors.Gray
'			lbldaily.TextColor=Colors.Gray
'			lblweekly.TextColor=Colors.White
'		Case 2
'			lblweekly.Color=mycode.bgColor
'			lbldaily.Color=mycode.bgColor
'			lblcalendar.Color=mycode.naviColor
'			lblcalendar.TextColor=Colors.White
'			lbldaily.TextColor=Colors.Gray
'			lblweekly.TextColor=Colors.Gray
'	End Select
'End Sub