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
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim auth As FirebaseAuth
End Sub

Sub Globals
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.
	Dim AXrLottie As AXrLottie
	Dim LottieView As AXrLottieImageView
End Sub

Sub Activity_Create(FirstTime As Boolean)
	
	Activity.Color=mycode.bgColor
	mycode.SETnavigationcolor
	Dim appbar As Panel = mycode.appbar("Login",False)
	Activity.AddView(appbar,0,mycode.appbartop,100%x,mycode.appbarheight)
	

	AXrLottie.Initialize
	LottieView.Initialize("")
	
	Sleep(100)
	Dim Drawable As AXrLottieDrawableBuilder
	Drawable.InitializeFromFile(File.DirAssets,"login.json") _
				.SetAutoRepeat(Drawable.AUTO_REPEAT_INFINITE) _
				.SetAutoStart(True) _
				.SetCacheEnabled(False)
	LottieView.SetLottieDrawable(Drawable.Build)
	
	Dim base As Panel = pbase
	Activity.AddView(base,0,((mycode.ActivityHeight-mycode.appbarheight)-base.Height)/2,100%x,base.Height)
	If FirstTime Then
		auth.Initialize("auth")
		
	End If
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub loginbtn_click
	auth.SignInWithGoogle
End Sub

Sub pbase As Panel
	Dim p As Panel
	p.Initialize("")
	Dim bmp As Bitmap
	bmp.Initialize(File.DirAssets, "lg.webp")
	Dim left As Int = (100%x-(300dip))/2
	p.AddView(LottieView,left,0,(300dip),(300dip)/1.5)
	Dim width As Int = bmp.Width/1.5
	Dim height As Int = bmp.Height/1.5
	Dim loginbtn As Button
	loginbtn.Initialize("loginbtn")
	loginbtn.SetBackgroundImage(LoadBitmap(File.DirAssets,"lg.webp"))
	p.AddView(loginbtn,(100%x-width)/2,LottieView.Height+LottieView.Top+20dip,width,height)
	p.Height = loginbtn.Height+loginbtn.Top
	Return p
End Sub

Sub auth_SignError (Error As Exception)
	ToastMessageShow("error",False)
End Sub

Sub auth_SignedIn (User As FirebaseUser)
	Dim m As Map
	m.Initialize
	m.Put("id",User.Uid)
	m.Put("name",User.DisplayName)
	m.Put("email",User.Email)
	m.Put("profile_pic",User.PhotoUrl)
	Dim json As JSONGenerator
	json.Initialize(m)
	File.WriteString(File.DirInternal,"user",json.ToString)
	If File.Exists(File.DirInternal,"user") Then
		Activity.Finish
		
	End If
End Sub

Sub backbtn_click
	Activity.Finish
End Sub