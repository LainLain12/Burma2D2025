B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=13.1
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: True
#End Region

Sub Process_Globals

End Sub

Sub Globals
	Dim pf As Panel 
	Dim blistpn As Panel
	Dim pbitem As Panel
End Sub

Sub Activity_Create(FirstTime As Boolean)
	mycode.SETnavigationcolor
	Dim appbar As Panel = mycode.appbar("Profile Details",False)
	Activity.AddView(appbar,0,0,100%x,mycode.appbarheight)
	Activity.Color=mycode.bgColor
	pf = profile
	Activity.AddView(pf,0,mycode.appbarheight,100%x,pf.Height)
	addblockpn
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub profile As Panel
	Dim p As Panel
	p.Initialize("")
	Dim pwall As Panel
	pwall.Initialize("")
	p.AddView(pwall,0,0,100%x,40%x)
	pwall.SetBackgroundImage(LoadBitmap(File.DirAssets,"profile_bg.png"))
	Dim img As ImageView
	img.Initialize("")
	Dim glide As Amir_Glide
	glide.Initializer.Default
	glide.Load(mycode.getUserId(mycode.profile_pic)).Apply(glide.RO.CircleCrop).Into(img)
	Dim l As Int = (100%x-70dip)/2
	p.AddView(img,l,pwall.Height - 35dip,70dip,70dip)
	Dim lblname As Label
	lblname.Initialize("")
	lblname.text = mycode.getUserId(mycode.name)
	lblname.Typeface=mycode.defaultfont
	lblname.Gravity=Gravity.CENTER
	lblname.TextSize = mycode.textsize (9)
	lblname.TextColor=Colors.White
	p.AddView(lblname,0,img.Height+img.Top+10dip,100%x,10dip)
	Dim su As StringUtils
	lblname.Height = su.MeasureMultilineTextHeight(lblname,lblname.Text)
	Dim lblemail As Label
	lblemail.Initialize("")
	lblemail.text = mycode.getUserId(mycode.email)
	lblemail.Typeface=mycode.defaultfont
	lblemail.Gravity=Gravity.CENTER
	lblemail.TextSize = mycode.textsize (8)
	lblemail.TextColor=Colors.White
	p.AddView(lblemail,0,lblname.Height+lblname.Top+10dip,100%x,10dip)
	Dim su As StringUtils
	lblemail.Height = su.MeasureMultilineTextHeight(lblemail,lblemail.Text)
	p.Height = lblemail.Height+lblemail.Top
	
	Return p
End Sub

Sub addblockpn
	If blistpn.IsInitialized Then
		Activity.RemoveViewAt(2)
	End If
	If blocklist.Size > 0 Then
		blistpn = blockListpn
		Activity.AddView(blistpn, 0, pf.Top + pf.Height + 20dip, 100%x, blistpn.Height)
	End If
	
End Sub
Sub blockListpn As Panel
	Dim p As Panel
	p.Initialize("")
	p.RemoveAllViews
	Dim cv As CardView
	cv.Initialize("")
	
	pbitem.Initialize("")
	p.AddView(cv,10dip,10dip,100%x-20dip,50dip)
	cv.Elevation = 5dip
	cv.Color=mycode.naviColor
	cv.CornerRadius =10dip
	cv.AddView(pbitem,0,0,cv.Width,cv.Height)
	Dim titlelb As Label 
	titlelb.Initialize("")
	titlelb.Typeface = mycode.defaultfont
	titlelb.TextSize =  mycode.textsize(10)
	titlelb.Gravity=Gravity.CENTER
	titlelb.TextColor=Colors.White
	titlelb.Text= "Block Users"
	pbitem.AddView(titlelb,0,10dip,pbitem.Width-20dip,20dip)
	Dim su As StringUtils 
	Log("here")
	titlelb.Height = su.MeasureMultilineTextHeight(titlelb,titlelb.Text)
	
	Dim subtitle As Label
	subtitle.Initialize("")
	subtitle.Typeface = mycode.defaultfont
	subtitle.TextSize =  mycode.textsize(7)
	subtitle.Gravity=Gravity.CENTER
	subtitle.TextColor=Colors.White
	subtitle.Text= "Will Not See Message From This Users"
	pbitem.AddView(subtitle,0,titlelb.Height+titlelb.Top+10dip,pbitem.Width-20dip,20dip)
	subtitle.Height = su.MeasureMultilineTextHeight(subtitle,subtitle.Text)
	
	Dim top As Int = subtitle.Height+subtitle.Top+10dip
	
	If blocklist.Size > 0 Then
		For i = 0 To blocklist.Size -1
			Dim m As Map = blocklist.Get(i)
			Dim item As Panel = blockitem(m.Get("name"),m.Get("profile_pic"),i)
			pbitem.AddView(item,10dip,top,pbitem.Width-20dip,item.Height)
			top = top +item.Height+10dip
		Next
	End If
	pbitem.Height = top
	cv.Height =top
	p.Height = cv.Height+20dip
	
	
	Return p
End Sub

Sub blockitem(name,profile_pic As String,i As Int) As Panel
	Dim p As Panel
	p.Initialize("")
	Dim namelb As Label
	Dim profileimg As ImageView
	Dim unblockbtn As Button
	namelb.Initialize("")
	profileimg.Initialize("")
	unblockbtn.Initialize("unblockbtn")
	namelb.Typeface = mycode.defaultfont
	namelb.TextSize = mycode.textsize(9)
	namelb.Text= name
	namelb.Gravity=Gravity.LEFT+Gravity.CENTER_VERTICAL
	namelb.TextColor=Colors.White
	Dim glide As Amir_Glide
	glide.Initializer.Default
	glide.Load(profile_pic).Apply(glide.RO.CircleCrop).Into(profileimg)
	unblockbtn.Text= "Un Block"
	unblockbtn.Tag= i
	unblockbtn.Typeface=mycode.defaultfont
	unblockbtn.TextSize = mycode.textsize(8)
	unblockbtn.Background = mycode.btnbgdynamic(Colors.LightGray,Colors.Gray,20dip)
	unblockbtn.TextColor=Colors.Red
	p.AddView(profileimg,0,0,40dip,40dip)
	Dim pw As Int = (100%x-40dip)
	Dim l As Int =(profileimg.Left+profileimg.Width+10dip)
	Dim r As Int = 100dip
	Dim w As Int = pw -(l+r)
	p.AddView(namelb,profileimg.Left+profileimg.Height+10dip,0,w,40dip)
	p.AddView(unblockbtn,namelb.Left+namelb.Width+10dip,0,80dip,40dip)
	p.Height = 40dip
	Return p 
End Sub

Sub blocklist As List
	Dim ls As List
	ls.Initialize
	Try
		If File.Exists(File.DirInternal,"block") Then
			Dim json As JSONParser
			json.Initialize(File.ReadString(File.DirInternal,"block"))
			ls = json.NextArray
		End If
	Catch
		Log(LastException)
	End Try
	Return ls
End Sub
Sub unblockbtn_click
	Dim b As Button = Sender
	Dim i As Int = b.Tag
	unlock(i)
End Sub

Sub unlock(i As Int)
	If blocklist.Size>0 Then
		Dim ls As List  = blocklist
		Log(ls)
		ls.RemoveAt(i)
		Log(ls)
		Dim js As JSONGenerator
		js.Initialize2(ls)
		File.WriteString(File.DirInternal,"block",js.ToString)
		addblockpn
	End If
End Sub

Sub backbtn_click
	Activity.Finish
End Sub