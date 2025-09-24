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
#Extends : android.support.v4.app.FragmentActivity

Sub Process_Globals
	Dim historyloader As String = "historyloader"
	Dim hiscallable As Boolean
	Dim panelheight As Int
	Dim mpanelheight As Int
	Dim progresstimer As Timer
	Dim miheight As Int
End Sub
Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim progressbg As Panel
	Dim scv As ScrollView
	Dim mitimelb As Label
	Dim modernlb,internetlb As Label
'	Dim Datepicker As Intellvold_DatePicker
'	Dim Mycalendar As Intellvold_Calendar
End Sub

Sub Activity_Create(FirstTime As Boolean)

	mycode.SETnavigationcolor
	Activity.color=mycode.bgcolor

	Dim job As HttpJob
	job.Initialize(historyloader,Starter)
	job.Download(Main.site&"?q=SELECT * FROM `dailyresults` ORDER BY date DESC;")
	Dim appbar As Panel = mycode.appbar("2D History",False)
	Dim searchbtn As Button
	searchbtn.Initialize("searchbtn")
	searchbtn.Typeface = Typeface.FONTAWESOME
	searchbtn.TextColor=Colors.White
	searchbtn.TextSize = mycode.textsize(7)
	searchbtn.Background = mycode.btnbgdynamic(Colors.Transparent,mycode.bgColor,180)
	searchbtn.Text=Chr(0xF073)
	'appbar.AddView(searchbtn,100%x-(mycode.appbarheight+10dip),0,mycode.appbarheight,mycode.appbarheight)
	Activity.AddView(appbar,0,mycode.appbartop,100%x,mycode.appbarheight)
	
	scv.Initialize(1000dip)
	Activity.AddView(scv,0,mycode.appbarheight+2dip,100%x,mycode.ActivityHeight-mycode.appbarheight)
	
	progressShow
End Sub

Sub Activity_Resume
hiscallable=True
End Sub

Sub Activity_Pause (UserClosed As Boolean)
hiscallable=False
End Sub

Sub progresstimer_tick
	If progressbg.Width = 100%x Then
		progressbg.Width = 0
		Else
			progressbg.Width = progressbg.Width+5%x
	End If
End Sub

Sub progressShow
	progressbg.Initialize("")
	Activity.AddView(progressbg,0,mycode.appbarHeight,0,2dip)
	progressbg.Color=Colors.Yellow
	
	progresstimer.Initialize("progresstimer",50)
	progresstimer.Enabled=True
End Sub

'Sub searchbtn_click
'	Datepicker.Initialize("Date",DateTime.GetYear(DateTime.Now) ,DateTime.GetMonth(DateTime.Now) ,DateTime.GetDayOfMonth(DateTime.Now))
'	Mycalendar.Initialize("dd.mm.yyyy",Mycalendar.PRC,"2.02.1990") 'start date
''	Datepicker.SetThemeDark
'	Datepicker.SetMinDate(Mycalendar)
'	Mycalendar.Initialize("dd.mm.yyyy",Mycalendar.PRC,"2.02.2030") 'end date
'	Datepicker.SetMaxDate(Mycalendar)
'	Datepicker.CancelColor = Colors.Red
'	Datepicker.OkColor = Colors.Black
'	Datepicker.Title = "IntellVold"
'	Datepicker.CancelText = "CANCEL"
'	Datepicker.OkText = "SEARCH"
''	Datepicker.SetThemeDark
'	Datepicker.SetOnCancel("can")
''	Datepicker.show("bir")
'	Datepicker.show2("iki")
'	Datepicker.About
'	Sleep(10000)
''	Datepicker.Dismiss_Dialog
'End Sub

Sub Date_OnDateSet (year As Int ,monthOfYear As Int , dayOfMonth As Int)
	Dim date As String = year&"/"&(NumberFormat(monthOfYear,2 ,0))&"/"&NumberFormat(dayOfMonth ,2 ,0)
	progressShow
	Dim job As HttpJob
	job.Initialize(historyloader,Starter)
	job.Download(Main.site&"?q=SELECT * FROM `dailyresults` WHERE date='"&date&"'")
End Sub

Sub can_OnCancelDate ()

End Sub


Sub resultscs (set As String, value As String,twod As String) As CSBuilder
	Dim sett As String
	Dim setresult As String
	Dim valuel As String
	Dim valuer As String
	Dim valueresult As String
	
	If set.Length >0 Then
		setresult = set.SubString(set.Length-1)
		sett  = set.SubString2(0,set.Length-1)
	End If
	If value.Length>0 Then
		valuel = value.SubString(value.IndexOf("."))
		valuer = value.Replace(valuel,"")
		valueresult = valuer.SubString(valuer.Length-1)
		valuer = valuer.SubString2(0,valuer.Length-1)
	End If
	
	Dim  cs As CSBuilder
	cs.Initialize
	Dim cs1 As CSBuilder
	cs1.Initialize
	Dim tfthin As Typeface = mycode.lightfont
	cs.Size(30).Bold.Color(Colors.White).Append(twod&CRLF).Pop
	cs.Size(18).Typeface(tfthin).Color(Colors.Yellow).Append(sett).Pop
	cs.Size(18).Typeface(tfthin).Color(Colors.White).Append(setresult&CRLF).Pop
	cs.Size(18).Typeface(tfthin).Color(Colors.Yellow).Append(valuer).Pop
	cs.Size(18).Typeface(tfthin).Color(Colors.White).Append(valueresult).Pop
	cs.Size(18).Typeface(tfthin).Color(Colors.Yellow).Append(valuel).PopAll
	
	Return cs
End Sub

Sub timecsb (time As String,icon As String,ampm As String) As CSBuilder
	Dim  cs As CSBuilder
	cs.Initialize
	cs.Typeface(Typeface.FONTAWESOME).Size(15).Color(Colors.White).Append(icon&" ").Pop
	cs.Typeface(Typeface.DEFAULT_BOLD).Size(15).Color(Colors.White).Append(time&" ")
	cs.Typeface(Typeface.DEFAULT_BOLD).Size(15).Color(0xFFC5C5C5).Append(ampm).PopAll
	Return cs
End Sub

Sub mresultspn(ms As String,mv As String,mr As String,es As String,ev As String,er As String,date As String,nm As String,tm As String,ni As String,ti As String) As Panel
	Dim pn As Panel
	pn.Initialize("")
	Dim datelb As Label
	datelb.Initialize("")
	datelb.Text= date
	datelb.Gravity=Gravity.CENTER
	pn.AddView(datelb,0,0,100%x,40dip)
	Dim su As StringUtils
	datelb.TextSize=16
	datelb.Height=su.MeasureMultilineTextHeight(datelb,datelb.Text)
	datelb.TextColor=Colors.White
	pn.AddView(resultpn( _ 
	ms,mv,mr,es,ev,er,nm,ni,tm,ti _ 
	),0,datelb.Height+datelb.Top,100%x,panelheight)
	mpanelheight=panelheight+datelb.Height+datelb.Top+10dip
	Return pn
End Sub

Sub resultpn( mset As String,mvalue As String,mresult As String,eset As String,evalue As String,eresult As String,nm As String ,ni As String,tm As String,ti As String) As Panel
	Dim mainpn As Panel	
	mainpn.Initialize("")
	
	Dim morningresultlb,eveningresultlb As Label
	Dim morningresultpn,eveningresultpn As Panel
	Dim mdevider,edevider As Panel
	Dim mtimelb,etimelb As Label
	
	morningresultpn.Initialize("")
	morningresultpn.Elevation=10dip
	Dim cd As ColorDrawable
	cd.Initialize(mycode.naviColor,14dip)
	morningresultpn.Background=cd
	Dim w As Int = (100%x-30dip)/2
	mainpn.AddView(morningresultpn,10dip,10dip,w,50dip)
	mtimelb.Initialize("")
	mdevider.Initialize("")
	morningresultpn.AddView(mtimelb,0,5dip,morningresultpn.Width,20dip)
	mtimelb.Gravity=Gravity.CENTER
	mtimelb.Text=timecsb("12:01",Chr(0xF185),"PM")
	Dim su As StringUtils
	mtimelb.Height=su.MeasureMultilineTextHeight(mtimelb,timecsb("12:01 PM",Chr(0xF185),"PM"))
	morningresultpn.AddView(mdevider,10dip,mtimelb.Height+mtimelb.Top+5dip,morningresultpn.Width-20dip,1dip)
	mdevider.Color=Colors.Gray
	
	morningresultlb.Initialize("")
	morningresultpn.AddView(morningresultlb,10dip,mdevider.Height+mdevider.Top+2dip,morningresultpn.Width-20dip,50dip)
	morningresultlb.Gravity=Gravity.CENTER
	Log(mset)
	Log(mvalue)
	Log(mresult)
	morningresultlb.Text=resultscs(mset,mvalue,mresult)
	Dim su As StringUtils
	morningresultlb.Height=su.MeasureMultilineTextHeight(morningresultlb,resultscs("1245.12","31465.88","25"))
	morningresultpn.Height=morningresultlb.Height+morningresultlb.Top+5dip
	Log("error break point")
	'evening..................................
	eveningresultpn.Initialize("")
	eveningresultpn.Elevation=10dip
	Dim cd As ColorDrawable
	cd.Initialize(mycode.naviColor,14dip)
	eveningresultpn.Background=cd
	Dim w As Int = (100%x-30dip)/2
	mainpn.AddView(eveningresultpn,morningresultpn.Width+morningresultpn.Left+10dip,morningresultpn.Top,w,50dip)
	
	etimelb.Initialize("")
	edevider.Initialize("")
	eveningresultpn.AddView(etimelb,0,5dip,eveningresultpn.Width,20dip)
	etimelb.Gravity=Gravity.CENTER
	etimelb.Text=timecsb("4:30",Chr(0xF186),"PM")
	Dim su As StringUtils
	etimelb.Height=su.MeasureMultilineTextHeight(etimelb,timecsb("12:01 PM",Chr(0xF185),"PM"))
	eveningresultpn.AddView(edevider,10dip,etimelb.Height+etimelb.Top+5dip,eveningresultpn.Width-20dip,1dip)
	edevider.Color=Colors.Gray
	
	eveningresultlb.Initialize("")
	eveningresultpn.AddView(eveningresultlb,10dip,edevider.Height+edevider.Top+2dip,eveningresultpn.Width-20dip,50dip)
	eveningresultlb.Gravity=Gravity.CENTER
	eveningresultlb.Text=resultscs(eset,evalue,eresult)
	Dim su As StringUtils
	eveningresultlb.Height=su.MeasureMultilineTextHeight(eveningresultlb,resultscs("1245.12","31465.88","25"))
	eveningresultpn.Height=eveningresultlb.Height+eveningresultlb.Top+5dip
	
	mainpn.AddView(mornetpanel(nm,ni,tm,ti),10dip,eveningresultpn.Height+eveningresultpn.Top+10dip,(100%x-20dip),miheight)
	
	panelheight = miheight+eveningresultpn.Height+eveningresultpn.Top+10dip
	mainpn.Height=panelheight
	Return mainpn
End Sub



Sub addview11(data As String)
Log(data)
	Try
		progressbg.Visible=False
		progresstimer.Enabled=False
		Dim json As JSONParser
		json.Initialize(data)
		Dim ls As List = json.NextArray
		Dim topp As Int = 10dip
		scv.Panel.RemoveAllViews
		If ls.Size>0 Then
			For i = 0 To ls.Size-1
				Dim m As Map = ls.Get(i)
				Dim ms ,mv,mr,es,ev,er,date,nm,tm,ni,ti As String
				ms = m.Get("1200set")
				mv=m.Get("1200value")
				mr=m.get("1200")
				es=m.get("430set")
				ev=m.get("430value")
				er=m.Get("430")
				date= m.Get("date")
				Log(ms)
				Log(mv)
				Log(mr)
				Log(m.Get("date"))
				nm=m.get("930modern")
				tm=m.Get("200modern")
				ni =m.Get("930internet")
				ti =m.Get("200internet")
				Log("here>")
				scv.Panel.AddView(mresultspn( _
		ms,mv,mr,es,ev,er,date,nm,ni,tm,ti _ 
		),0,topp,scv.Width,mpanelheight)
				Log("here<<")
				topp=topp+mpanelheight
				scv.Panel.Height=topp+10dip
				Sleep(100)
			Next
		End If
Catch
	Log(LastException)
End Try
End Sub


Sub backbtn_click
	Activity.Finish
End Sub

Sub mornetpanel(nm As String,tm As String,ni As String,ti As String) As Panel
	Dim pn As Panel
	pn.Initialize("")
	pn.Elevation=10dip
	pn.Background=mycode.btnbg2
	modernlb.Initialize("")
	internetlb.Initialize("")
	Dim w As Int = (100%x-40dip)/2
	mitimelb.Initialize("")
	pn.AddView(mitimelb,10dip,10dip,w,20dip)
	mitimelb.Text=mycode.mitimecs
	Dim su As StringUtils
	mitimelb.Height=su.MeasureMultilineTextHeight(mitimelb,mycode.mitimecs)
	
	pn.AddView(modernlb,w,10dip,w/2,20dip)
	modernlb.Text=mycode.moderninernetcs(nm,tm)
	Dim su As StringUtils
	modernlb.Height=su.MeasureMultilineTextHeight(modernlb,mycode.moderninernetcs("---","---"))
	
	pn.AddView(internetlb,modernlb.Left+modernlb.Width+10dip,10dip,w/2,20dip)
	internetlb.Text=mycode.moderninernetcs(ni,ti)
	internetlb.Height = modernlb.Height
	modernlb.Gravity=Gravity.CENTER
	internetlb.Gravity=Gravity.CENTER
	miheight=internetlb.Height+20dip
	
	Return pn
End Sub