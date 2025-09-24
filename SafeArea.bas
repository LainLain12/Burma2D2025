B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=13.35
@EndOfDesignText@

Sub Class_Globals
	Private joActivity As JavaObject
	Private mCallBack As Object
	Private mEventName As String
	Private xui As XUI
    
	' Store the calculated insets
	Public TopInset As Int
	Public BottomInset As Int
	Public LeftInset As Int
	Public RightInset As Int
    
	'Private insetsListenerJO As JavaObject ' <-- ဒီလိုင်းကို ဖယ်ရှား/comment လုပ်ပါ
	Private customInsetsListener As JavaObject ' <-- ဒီလိုင်း အသစ်ထည့်ပါ
End Sub

' Initializes the class module.
Public Sub Initialize(CallBack As Object, EventName As String)
	mCallBack = CallBack
	mEventName = EventName
    
	TopInset = 0
	BottomInset = 0
	LeftInset = 0
	RightInset = 0
    
	If Not(mCallBack Is Activity) Then
		Log("SafeAreaUtils: WARNING - CallBack should ideally be the Activity object for correct context. Current: " & GetType(mCallBack))
	End If
    
	joActivity.InitializeContext
	SetupWindowInsetsListener
End Sub

' Sets up the OnApplyWindowInsetsListener
Private Sub SetupWindowInsetsListener
	If GetDeviceCurrentApiLevel >= 20 Then ' For WindowInsets compatibility
		Try
			Dim activityView As JavaObject = joActivity.RunMethod("getWindow", Null)
			activityView.RunMethod("getDecorView", Null)
            
			' ***** ပြောင်းလဲရမယ့် နေရာ *****
			' Custom Java Class ကို Initialize လုပ်ခြင်း
			customInsetsListener.InitializeNewInstance("com.yourcompany.b4a.CustomInsetsListener", Null) ' <-- Class Path ကို မှန်ကန်စွာ ထည့်ပါ။
			customInsetsListener.RunMethod("Initialize", Array(mCallBack, mEventName)) ' <-- CallBack နဲ့ EventName ကို ပေးပါ။
            
			' Custom Java Listener ကို setOnApplyWindowInsetsListener ထဲ ထည့်သွင်းခြင်း
			activityView.RunMethod("setOnApplyWindowInsetsListener", Array(customInsetsListener))
			activityView.RunMethod("requestApplyInsets", Null)
            
			Log("SafeAreaUtils: OnApplyWindowInsetsListener setup successfully (using custom Java listener).")
		Catch
			Log(LastException)
			Log("SafeAreaUtils: Failed to set up WindowInsetsListener. Falling back to old method.")
			CalculateInsetsOldMethod
		End Try
	Else
		Log("SafeAreaUtils: Device API level < 20. Using traditional method.")
		CalculateInsetsOldMethod
	End If
End Sub

' **** အရေးကြီး: Event Sub နာမည် ပြောင်းရပါမည်။ ****
' custom Java class မှ ခေါ်မည့် Callback Sub
' Note: Event name will be [EventNamePrefix]_onapplywindowinsets
Private Sub ApplyWindowInsets_onapplywindowinsets(view As JavaObject, insets As JavaObject) As JavaObject
	TopInset = insets.RunMethod("getSystemWindowInsetTop", Null)
	BottomInset = insets.RunMethod("getSystemWindowInsetBottom", Null)
	LeftInset = insets.RunMethod("getSystemWindowInsetLeft", Null)
	RightInset = insets.RunMethod("getSystemWindowInsetRight", Null)
    
	Log($"SafeAreaUtils: Insets received - Top: ${TopInset}px, Bottom: ${BottomInset}px, Left: ${LeftInset}px, Right: ${RightInset}px"$)
    
	' Raise an event to the calling module/activity
	If mCallBack <> Null And xui.SubExists(mCallBack, mEventName & "_InsetsCalculated",1) Then
		CallSub(mCallBack, mEventName & "_InsetsCalculated")
	End If
    
	Return insets ' Important: return the insets
End Sub

' Callback from OnApplyWindowInsetsListener
' This sub is called when the insets change.
Private Sub ApplyWindowInsets(view As JavaObject, insets As JavaObject) As JavaObject
	TopInset = insets.RunMethod("getSystemWindowInsetTop", Null)
	BottomInset = insets.RunMethod("getSystemWindowInsetBottom", Null)
	LeftInset = insets.RunMethod("getSystemWindowInsetLeft", Null)
	RightInset = insets.RunMethod("getSystemWindowInsetRight", Null)
    
	Log($"SafeAreaUtils: Insets received - Top: ${TopInset}px, Bottom: ${BottomInset}px, Left: ${LeftInset}px, Right: ${RightInset}px"$)
    
	' Raise an event to the calling module/activity
	If mCallBack <> Null And xui.SubExists(mCallBack, mEventName & "_InsetsCalculated",1) Then
		CallSub(mCallBack, mEventName & "_InsetsCalculated")
	End If
    
	Return insets ' Important: return the insets
End Sub

' Fallback method for older Android versions (or if insets listener setup fails)
Private Sub CalculateInsetsOldMethod
	TopInset = GetStatusBarHeightOld
	BottomInset = GetNavigationBarHeightOld' This might not be accurate for all devices/versions
	LeftInset = 0 ' Old method doesn't give these
	RightInset = 0
    
	Log($"SafeAreaUtils: Old method Insets - Top: ${TopInset}px, Bottom: ${BottomInset}px"$)
    
	If mCallBack <> Null And xui.SubExists(mCallBack, mEventName & "_InsetsCalculated",1) Then
		CallSub(mCallBack, mEventName & "_InsetsCalculated")
	End If
End Sub

' Helper: Get Status Bar Height (old method)
Private Sub GetStatusBarHeightOld As Int
	Dim jo As JavaObject
	Dim resourcesObj As JavaObject
	jo.InitializeStatic("android.content.res.Resources")
	resourcesObj = jo.RunMethod("getSystem", Null)
	Dim ResourceID As Int = resourcesObj.RunMethod("getIdentifier", Array("status_bar_height", "dimen", "android"))
	If ResourceID > 0 Then
		Return resourcesObj.RunMethod("getDimensionPixelSize", Array(ResourceID))
	Else
		Return 0
	End If
End Sub

' Helper: Get Navigation Bar Height (old method - less reliable than WindowInsets)
Private Sub GetNavigationBarHeightOld As Int
	Dim navBarHeight As Int = 0
	Try
		Dim jo As JavaObject
		Dim resourcesObj As JavaObject
		jo.InitializeStatic("android.content.res.Resources")
		resourcesObj = jo.RunMethod("getSystem", Null)
		Dim resourceId As Int = resourcesObj.RunMethod("getIdentifier", Array("navigation_bar_height", "dimen", "android"))
		If resourceId > 0 Then
			navBarHeight = resourcesObj.RunMethod("getDimensionPixelSize", Array(resourceId))
		End If
	Catch
		Log(LastException)
		Log("SafeAreaUtils: Could not get Navigation Bar height using old method.")
	End Try
	Return navBarHeight
End Sub

' Helper: Get Device API Level
Private Sub GetDeviceCurrentApiLevel As Int
	Dim jo As JavaObject
	jo.InitializeStatic("android.os.Build$VERSION")
	Return jo.GetField("SDK_INT")
End Sub