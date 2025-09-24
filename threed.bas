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
	Dim progresstime As Timer
	Dim threeditemheight As Int
	Dim IsCall As Boolean=True
	Dim threedloader As String = "threedloader"
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim progresspn As Panel
	Dim scv As ScrollView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	'Do not forget to load the layout file created with the visual designer. For example:
	'Activity.LoadLayout("Layout1")
	activity.color=mycode.bgcolor
	mycode.SETnavigationcolor

	Activity.AddView(mycode.appbar("3D History",False),0,0,100%x,mycode.appbarheight)
	
	progresspn.Initialize("")
	Activity.AddView(progresspn,0,mycode.appbarheight,100%x,2dip)
	progresspn.Color=Colors.Yellow
	progresstime.Initialize("ptimer",50)
	progresstime.Enabled=True
	
	scv.Initialize(1000dip)
	Activity.AddView(scv,0,mycode.appbarheight,100%x,mycode.ActivityHeight-mycode.appbarheight)
	Dim job As HttpJob
	job.Initialize(threedloader,Starter)
	job.Download(Main.site&"?q=SELECT * FROM `threed` ORDER BY date DESC;")
End Sub


Sub Activity_Resume
	IsCall=True
End Sub

Sub Activity_Pause (UserClosed As Boolean)
IsCall= False

End Sub

Sub backbtn_click
Activity.Finish
End Sub

Sub ptimer_tick
	If progresspn.Width = 100%x Then
		progresspn.Width=0
		Else
			progresspn.Width = progresspn.Width + 10%x
	End If
End Sub

Sub threedlistviewitem(date As String,result As String) As Panel
	Dim p As Panel
	p.Initialize("")
	p.Elevation=10dip
	Dim cd As ColorDrawable 
	cd.Initialize(mycode.naviColor,10dip)
	p.Background=cd
	Dim datelb As Label
	datelb.Initialize("")
	Dim resultlb As Label
	resultlb.Initialize("")
	p.AddView(datelb,0,10dip,100%x/2,50dip)
	p.AddView(resultlb,(100%x/2),10dip,100%x/2,50dip)
	datelb.Text= date
	datelb.TextColor=Colors.Yellow
	datelb.TextSize = 20
	datelb.Gravity=Gravity.CENTER
	resultlb.Text= result
	resultlb.Typeface=Typeface.DEFAULT_BOLD
	resultlb.TextColor=Colors.White
	resultlb.TextSize = 20
	resultlb.Gravity=Gravity.CENTER
	Dim su As StringUtils
	datelb.Height=su.MeasureMultilineTextHeight(datelb,date)
	resultlb.Height=datelb.Height
	threeditemheight = datelb.Height+20dip
	
	Return p
End Sub

Sub addview (data As String)
	progressHide
	Dim top As Int =10dip
	Dim json As JSONParser
	json.Initialize(data)
	Dim ls As List = json.NextArray
	If ls.Size > 0 Then
		For i = 0 To ls.Size -1
			Dim m As Map = ls.Get(i)
			scv.Panel.AddView(threedlistviewitem(m.Get("date"),m.Get("result")),10dip,top,100%x-20dip,threeditemheight)
			top=top+threeditemheight+10dip
			scv.Panel.Height=top
			Sleep(100)
		Next
	End If
End Sub

Sub progressHide
	progresstime.Enabled=False
	progresspn.Visible=False
End Sub