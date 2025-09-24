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
	Dim Mdata As Map
	Dim messageHeight As Int
	Dim su As StringUtils
	Dim reportpnHeight As Int
	Dim reportJob As String = "reportJob"
End Sub

Sub Globals
	
End Sub

Sub Activity_Create(FirstTime As Boolean)
Activity.Color=mycode.bgColor
mycode.SETnavigationcolor
Dim b As Button
Activity.AddView(mycode.appbar("Report",False),0,0,100%x,mycode.appbarheight)
Dim smspn As Panel = OtherMessage(Mdata.Get("profile_pic"),Mdata.Get("message"),Mdata.Get("name"),Mdata.Get("id"),0)
Activity.AddView(smspn,0,mycode.appbarheight+ 30dip,100%x,messageHeight)
Activity.AddView(reportItem,0,smspn.Height+smspn.Top+20dip,100%x,reportpnHeight)
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub



Sub OtherMessage(profile,sms,name,id As String,top As Int) As Panel
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
	m.Put("top",top)
	m.Put("message",sms)
	m.Put("name",name)
	m.Put("profile_pic",profile)
	smsitem.Tag  = m
	Return p
End Sub


Sub OtherMessageCs(name,sms As String) As CSBuilder
	Dim cs As CSBuilder
	cs.Initialize.Color(Colors.Yellow).Typeface(mycode.mmfont).Size(mycode.textsize(7)).Append(name&CRLF).Pop
	cs.Color(Colors.White).Typeface(mycode.mmfont).Size(mycode.textsize(7)).Append(sms).PopAll
	Return cs
End Sub


Sub reportItem As Panel
	Dim p As Panel
	p.Initialize("")
	Dim cv As CardView
	cv.Initialize("")
	cv.Color=mycode.naviColor
	cv.Elevation= 5dip
	cv.CornerRadius=10dip
	Dim pbase As Panel
	pbase.Initialize("")
	p.AddView(cv,10dip,10dip,100%x-20dip,100dip)
	cv.AddView(pbase,0,0,cv.Width,cv.Height)
	
	Dim lb As Label
	lb.Initialize("")
	lb.Text = "Reporting"
	lb.TextSize = mycode.textsize(9)
	lb.TextColor=Colors.White
	lb.Typeface = mycode.semibold
	
	Dim lbl2 As Label
	lbl2.Initialize("")
	lbl2.Typeface = mycode.defaultfont
	lbl2.TextSize  = mycode.textsize(8)
	lbl2.TextColor=Colors.LightGray
	lbl2.Text = "Do you want to report "&Mdata.Get("name")
	
	pbase.AddView(lb,10dip,10dip,pbase.Width-20dip,30dip)
	lb.Height = su.MeasureMultilineTextHeight(lb,lb.Text)
	
	pbase.AddView(lbl2,10dip,lb.Height+lb.Top+10dip,pbase.Width-20dip,30dip)
	lbl2.Height = su.MeasureMultilineTextHeight(lbl2,lbl2.Text)
	Dim ls As List 
	ls.Initialize
	ls.AddAll(Array As String("Behavior for potential violation","Content for potential violation"))
	Dim top As Int = lbl2.Height +lbl2.Top+10dip
	For i = 0 To ls.Size -1
		pbase.AddView(Radiotype(ls.Get(i),i),10dip,top,pbase.Width-20dip,30dip)
		top = top + 40dip
	Next
	Dim btnreport As Button
	btnreport.Initialize("summitbtn")
	btnreport.Text = "Summit Report"
	btnreport.TextSize = mycode.textsize(8)
	btnreport.Typeface = mycode.defaultfont
	btnreport.TextColor=Colors.White
	btnreport.Background = mycode.btnbgdynamic(mycode.bgColor,Colors.Gray,10dip)
	Dim l As Int = (pbase.Width-150dip)/2
	pbase.AddView(btnreport,l,top,150dip,40dip)
	pbase.Height = btnreport.Height+btnreport.Top+10dip
	cv.Height= pbase.Height
	reportpnHeight = cv.Height+20dip
	Return p
End Sub

Sub Radiotype(txt As String,i As Int) As RadioButton
	Dim rd As RadioButton
	rd.Initialize("")
	rd.Text  = txt
	rd.Color = Colors.Gray
	rd.TextColor=Colors.LightGray
	rd.TextSize = mycode.textsize(8)
	rd.Typeface =mycode.defaultfont
	If i = 0 Then
		rd.Checked=True
	End If
	Return rd
End Sub

Sub backbtn_click
	Activity.Finish
End Sub

Sub setreport
	Dim j As HttpJob
	j.Initialize(reportJob,Starter)
	j.Download(Main.site&"public_chat/report.php?id="&Mdata.Get("id")&"&report_id="&mycode.getUserId(mycode.id))
End Sub

Sub summitbtn_click
	setreport
End Sub