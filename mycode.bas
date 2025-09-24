B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=StaticCode
Version=13.1
@EndOfDesignText@
'Code module
'Subs in this code module will be accessible from all modules.
Sub Process_Globals
	Dim checkentertime As Int
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	''''Store
	Dim futureimglist As List
	Dim weeklyimglist As List
	Dim calendarimglist As List
	
	Dim ActivityHeight As Int
	Dim appbartop As Int
	Dim appbarheight As Int
	Public naviColor As Int =Colors.RGB(114,28,48) '0xFF842F46
	Public bgColor As Int  = Colors.RGB(20,12,31)'0xFF241A32
	Dim mmfont As Typeface = Typeface.LoadFromAssets("PYIDAUNGSU-2.5.3_REGULAR.TTF")
	Dim defaultfont As Typeface =Typeface.LoadFromAssets("DMSans-Regular.ttf")
	Dim semibold As Typeface = Typeface.LoadFromAssets("DMSans-SemiBold.ttf")
	Dim livebold As Typeface = Typeface.LoadFromAssets("DMSans-ExtraBold.ttf")
	Dim lightfont As Typeface = Typeface.LoadFromAssets("DMSans-Light.ttf")
	Dim id As String  = "id"
	Dim profile_pic As String = "profile_pic"
	Dim name As String = "name"
	Dim email As String = "email"
	Dim chatlist As List
	Dim LastLive As String
	Dim paperdata As String
End Sub

Sub checkActivityHeihgt(h As Int)
	Dim phone As Phone
	Dim m As Map
	m.Initialize
'	If phone. SdkVersion >=35 Then
'		ActivityHeight = h-GetStatusBarHeight
'		appbartop  = GetStatusBarHeight
'		m.Put("activiyheight",ActivityHeight)
'		m.Put("appbartop",GetStatusBarHeight)
'		Else 
			m.Put("activityheight",h)
			m.Put("appbartop",0)
			ActivityHeight = h
			appbartop=0
'	End If
	File.WriteMap(File.DirInternal,"heighttop",m)
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

Sub GetStatusBarHeight As Int
	Dim ctxt As JavaObject
	ctxt.InitializeContext
	Dim resources As JavaObject = ctxt.RunMethod("getResources", Null)
	Dim resourceId As Int = resources.RunMethod("getIdentifier", Array As Object("status_bar_height", "dimen", "android"))
	If resourceId > 0 Then
		Return resources.RunMethod("getDimensionPixelSize", Array As Object(resourceId))
	Else
		Return 0
	End If
End Sub



Sub CreateNotificationChannel
	Dim ctxt As JavaObject
	ctxt.InitializeContext
	Dim channel As JavaObject
	channel.InitializeNewInstance("android.app.NotificationChannel", _
        Array("burma", "My Channel", 4)) ' IMPORTANCE_HIGH = 4

	' Prepare sound URI
	Dim soundUri As JavaObject
	soundUri = soundUri.InitializeStatic("android.net.Uri").RunMethod("parse", _
        Array("android.resource://" & ctxt.RunMethod("getPackageName", Null) & "/raw/bell"))

	' Build AudioAttributes object properly
	Dim attrBuilder As JavaObject
	attrBuilder.InitializeNewInstance("android.media.AudioAttributes$Builder", Null)
	attrBuilder.RunMethod("setContentType", Array(4)) ' CONTENT_TYPE_SONIFICATION
	attrBuilder.RunMethod("setUsage", Array(5)) ' USAGE_NOTIFICATION
	Dim audioAttr As JavaObject = attrBuilder.RunMethod("build", Null)

	' Set sound and create channel
	channel.RunMethod("setSound", Array(soundUri, audioAttr))

	' Register channel
	Dim manager As JavaObject = ctxt.RunMethod("getSystemService", Array("notification"))
	manager.RunMethod("createNotificationChannel", Array(channel))
End Sub

Sub btnbg(isGift As Boolean) As StateListDrawable
	Dim std As StateListDrawable
	std.Initialize
	Dim defaultdb As ColorDrawable
	Dim pressdb As ColorDrawable
	
	If isGift = True Then
		defaultdb.Initialize(0xE1241A32,180)
		Else
		defaultdb.Initialize(Colors.Transparent,0)
	End If
	pressdb.Initialize(0x9A466584,180)
	std.AddState(std.State_Pressed,pressdb)
	std.AddCatchAllState(defaultdb)
	Return std
End Sub


Sub SETnavigationcolor
	
	
	Dim phone As Phone
	Dim jo As JavaObject
	If phone.SdkVersion >= 21 Then
		jo.InitializeContext
		Dim window As JavaObject = jo.RunMethodJO("getWindow", Null)
		window.RunMethod("setStatusBarColor", Array As Object(naviColor)) ' Replace with your color (ARGB)
	End If

	' Set light status bar icons (requires Android 6.0 / API 23)
	If phone.SdkVersion >= 23 Then
		Dim decorView As JavaObject = jo.RunMethodJO("getWindow", Null).RunMethodJO("getDecorView", Null)
		Dim flags As Int = decorView.RunMethod("getSystemUiVisibility", Null)
		flags = Bit.Or(flags, 0x00000000) ' SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
		decorView.RunMethod("setSystemUiVisibility", Array(flags))
	End If
End Sub

Sub SETnavigationcolor1
	Dim phone As Phone
	Dim jo As JavaObject
	If phone.SdkVersion >= 21 Then
		jo.InitializeContext
		Dim window As JavaObject = jo.RunMethodJO("getWindow", Null)
		window.RunMethod("setStatusBarColor", Array As Object(bgColor)) ' Replace with your color (ARGB)
	End If

	' Set light status bar icons (requires Android 6.0 / API 23)
	If phone.SdkVersion >= 23 Then
		Dim decorView As JavaObject = jo.RunMethodJO("getWindow", Null).RunMethodJO("getDecorView", Null)
		Dim flags As Int = decorView.RunMethod("getSystemUiVisibility", Null)
		flags = Bit.Or(flags, 0x00000000) ' SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
		decorView.RunMethod("setSystemUiVisibility", Array(flags))
	End If
End Sub

Sub appbar(title As String,isMain As Boolean) As Panel
	Dim p As Panel
	p.Initialize("")
	p.Color= naviColor
	Dim backbtn As Button
	backbtn.Initialize("backbtn")
	
	backbtn.Background = btnbg(False)
	backbtn.TextColor=Colors.White
	backbtn.Gravity=Gravity.CENTER
	backbtn.Typeface= Typeface.FONTAWESOME
	backbtn.Text=Chr(0xF060)
	Dim tlb As Label
	tlb.Initialize("")	
	Dim cs As CSBuilder
	cs.Initialize.Color(Colors.White).Size(textsize(8)).Typeface(semibold).Append(title).PopAll
	tlb.Text=cs
	tlb.SingleLine=True
	tlb.Gravity=Gravity.CENTER_VERTICAL
	p.AddView(backbtn,10dip,0,50dip,50dip)
	Dim su As StringUtils
	
	If isMain =True Then
		backbtn.Visible=False
		p.AddView(tlb,10dip,0,100%x-(backbtn.Width+backbtn.Left),50dip)
		
		Else
		p.AddView(tlb,backbtn.Width+backbtn.Left,0,100%x-(backbtn.Width+backbtn.Left),50dip)
			
	End If
	
	If su.MeasureMultilineTextHeight(tlb,cs)> 50dip Then
		appbarheight=tlb.Height
		backbtn.Width=tlb.Height
		backbtn.Height=tlb.Height
	Else
		appbarheight=50dip
	End If

	Return p
End Sub

Sub btnbgdynamic(dc As Int ,pc As Int,rd As Int) As StateListDrawable
	Dim std As StateListDrawable
	std.Initialize
	Dim defaultdb As ColorDrawable
	Dim pressdb As ColorDrawable
	defaultdb.Initialize(dc,rd)
	pressdb.Initialize(pc,rd)
	std.AddState(std.State_Pressed,pressdb)
	std.AddCatchAllState(defaultdb)
	Return std
End Sub


Sub btnbg2 As StateListDrawable
	Dim std As StateListDrawable
	std.Initialize
	Dim defaultdb As ColorDrawable
	Dim pressdb As ColorDrawable
	defaultdb.Initialize(naviColor,10dip)
	pressdb.Initialize(0x9A466584,10dip)
	std.AddState(std.State_Pressed,pressdb)
	std.AddCatchAllState(defaultdb)
	Return std
End Sub



Sub AddBitmapToGallery (In As InputStream, TargetName As String, MimeType As String)
	Dim p As Phone
	Dim ctxt As JavaObject
	ctxt.InitializeContext
	If p.SdkVersion >= 29 Then
		Dim cr As ContentResolver
		cr.Initialize("cr")
		Dim values As ContentValues
		values.Initialize
		values.PutString("_display_name", TargetName)
		values.PutString("mime_type", MimeType)
		values.PutString("relative_path", "Pictures/Burma2D/")
		Dim MediaStoreImagesMedia As JavaObject
		MediaStoreImagesMedia.InitializeStatic("android.provider.MediaStore.Images$Media")
		Dim EXTERNAL_CONTENT_URI As Uri = MediaStoreImagesMedia.GetField("EXTERNAL_CONTENT_URI")
		cr.Delete(EXTERNAL_CONTENT_URI, "_display_name = ?", Array As String(TargetName))
		Dim imageuri As JavaObject = cr.Insert(EXTERNAL_CONTENT_URI, values)
		Dim out As OutputStream = ctxt.RunMethodJO("getContentResolver", Null).RunMethod("openOutputStream", Array(imageuri))
		File.Copy2(In, out)
		out.Close
		ToastMessageShow("save image success",False)
	Else
		ToastMessageShow("You Can't Save Take ScreenShot",False)
	End If
End Sub

Sub AddBitmapToGallery1(In As B4XBitmap, TargetName As String, MimeType As String)
	Dim p As Phone
	Dim ctxt As JavaObject
	ctxt.InitializeContext
	If p.SdkVersion >= 29 Then
		Dim cr As ContentResolver
		cr.Initialize("cr")
		Dim values As ContentValues
		values.Initialize
		values.PutString("_display_name", TargetName)
		values.PutString("mime_type", MimeType)
		values.PutString("relative_path", "Pictures/Burma2D/")
		Dim MediaStoreImagesMedia As JavaObject
		MediaStoreImagesMedia.InitializeStatic("android.provider.MediaStore.Images$Media")
		Dim EXTERNAL_CONTENT_URI As Uri = MediaStoreImagesMedia.GetField("EXTERNAL_CONTENT_URI")
		cr.Delete(EXTERNAL_CONTENT_URI, "_display_name = ?", Array As String(TargetName))
		Dim imageuri As JavaObject = cr.Insert(EXTERNAL_CONTENT_URI, values)
		Dim out As OutputStream = ctxt.RunMethodJO("getContentResolver", Null).RunMethod("openOutputStream", Array(imageuri))
		In.WriteToStream(out,100,"PNG")
		out.Close
		ToastMessageShow("save image success",False)
	Else
		ToastMessageShow("You Can't Save Take ScreenShot",False)
	End If
End Sub

Sub moderninernetcs(n As String,t As String) As CSBuilder
	Dim cs As CSBuilder
	cs.Initialize
	cs.Color(Colors.Yellow).Typeface(semibold).Size(textsize(9)).Append(misub(n)).Pop
	cs.Color(Colors.White).Typeface(semibold).Size(textsize(9)).Append(mimain(n)).Pop
	cs.Color(Colors.Yellow).Typeface(semibold).Size(textsize(9)).Append(CRLF&misub(t)).Pop
	cs.Color(Colors.White).Typeface(semibold).Size(textsize(9)).Append(mimain(t)).PopAll
	Return cs
End Sub



Sub moderninernetcs1(n As String) As CSBuilder
	Dim cs As CSBuilder
	cs.Initialize
	cs.Color(Colors.Yellow).Typeface(semibold).Size(textsize(10)).Append(misub(n)).Pop
	cs.Color(Colors.White).Typeface(semibold).Size(textsize(10)).Append(mimain(n)).PopAll
	Return cs
End Sub




Sub mimain(m As String) As String
	m = m.SubString(1)
	Return m
End Sub
Sub misub(m As String) As String
	m = m.SubString2(0,1)
	Return m
End Sub

Sub mitimecs As CSBuilder
	Dim cs As CSBuilder
	cs.Initialize
	cs.Color(Colors.White).Size(textsize(8)).Typeface(Typeface.FONTAWESOME).Append(Chr(0xF2D5)).Pop
	cs.Color(Colors.White).Typeface(defaultfont).Size(textsize(8)).Append(" 9:30 AM").Pop
	cs.Size(textsize(8)).Color(Colors.White).Typeface(Typeface.FONTAWESOME).Append(CRLF&Chr(0xF2D5)).Pop
	cs.Size(textsize(8)).Typeface(defaultfont).Color(Colors.White).Append(" 2:00 PM").PopAll
	Return cs
End Sub
Sub mitimecs1(tm As String) As CSBuilder
	Dim cs As CSBuilder
	cs.Initialize
	cs.Color(Colors.White).Size(textsize(8)).Typeface(Typeface.FONTAWESOME).Append(Chr(0xF2D5)).Pop
	cs.Color(Colors.White).Typeface(defaultfont).Size(textsize(8)).Append(" "&tm).PopAll
	Return cs
End Sub

Sub textsize(size As Int) As Float
	
	If GetDeviceLayoutValues.Scale >2.5 Then
		Return size *2.5
	Else if GetDeviceLayoutValues.Scale <2 Then
		Return size *2.3
		Else
		Return size * GetDeviceLayoutValues.Scale
	End If
	
End Sub


Sub textsize1(size As Int) As Float
	Return size * GetDeviceLayoutValues.Scale
	
'	If GetDeviceLayoutValues.Scale >2.5 Then
'		Return size *2.3
'	Else if GetDeviceLayoutValues.Scale <2 Then
'		Return size *2
'		Else
'		Return size * GetDeviceLayoutValues.Scale
'	End If
	
End Sub


Sub TextWidth(lb As Label, Texto As String) As Int
	Private bmp As Bitmap
	bmp.InitializeMutable(1dip, 1dip)
	Private cvs As Canvas
	cvs.Initialize2(bmp)
	Return cvs.MeasureStringWidth(Texto, lb.Typeface , lb.TextSize)
End Sub


Sub getUserId(typ As String) As String
	Dim rt As String
	If File.Exists(File.DirInternal,"user") Then
		Dim json As JSONParser
		json.Initialize(File.ReadString(File.DirInternal,"user"))
		Dim m As Map = json.NextObject
		Log(m.Get("id"))
		Log(m.Get("name"))
		Log(m.Get("profile_pic"))
		Select typ
			Case id
				rt  = m.Get(id)
				Case name
					rt = m.Get(name)
					Case profile_pic
						rt= m.Get(profile_pic)
						Case email
							rt = m.Get(email)
		End Select
	End If
	Return rt
End Sub


Sub chatjson As String
	Dim json As JSONGenerator
	If chatlist.IsInitialized And chatlist.Size >0 Then
		json.Initialize2(chatlist)
		Else 
			Dim ls As List
			ls.Initialize
			json.Initialize2(ls)
			
	End If
	Return json.ToString
End Sub


