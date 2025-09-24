B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=13.35
@EndOfDesignText@
'RewardedInterstitialHelper.bas'

#Region  Project Attributes 

#End Region

Sub Class_Globals
	Private EventName As String
	Private CallBack As Object
	Private AdUnitId As String
	Private rewardedAd As JavaObject
	Private isLoaded As Boolean = False
End Sub

Public Sub Initialize (CallbackModule As Object, Event As String, AdUnit As String)
	CallBack = CallbackModule
	EventName = Event
	AdUnitId = AdUnit
	LoadAd
End Sub

Private Sub GetContext As JavaObject
	Dim jo As JavaObject
	jo.InitializeContext
	Return jo
End Sub

Private Sub GetAdRequest As Object
	Dim builder As JavaObject
	builder.InitializeNewInstance("com.google.android.gms.ads.AdRequest.Builder", Null)
	Return builder.RunMethod("build", Null)
End Sub

Public Sub LoadAd
	Dim cls As JavaObject
	cls.InitializeStatic("com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd")

	Dim CallBack As Object = Me
	Dim loader As Object = cls.RunMethodJO("load", Array(GetContext, AdUnitId, GetAdRequest, _
        CreateLoadCallback(CallBack)))
End Sub

Private Sub CreateLoadCallback(Module As Object) As JavaObject
	Dim jo As JavaObject
	jo.InitializeNewInstance(Application.PackageName & ".main$MyRewardedInterstitialLoadCallback", Null)
	Return jo
End Sub

Private Sub CreateShowListener As Object
	Dim jo As JavaObject
	jo.InitializeNewInstance(Application.PackageName & ".main$MyUserEarnedRewardListener", Null)
	Return jo
End Sub

Public Sub ShowIfReady
	If rewardedAd.IsInitialized And isLoaded Then
		rewardedAd.RunMethod("show", Array(GetContext, CreateShowListener))
	Else
		Log("Ad not ready.")
	End If
End Sub

' These subs should be called by inline Java callbacks
Public Sub OnAdLoaded(ad As Object)
	rewardedAd = ad
	isLoaded = True
	CallSubDelayed2(CallBack, EventName & "_AdLoaded", Null)
End Sub

Public Sub OnAdFailedWithCode(Code As Int)
	isLoaded = False
	CallSubDelayed3(CallBack, EventName & "_AdFailedWithCode", Code, Null)
End Sub

Public Sub OnAdDismissed
	isLoaded = False
	CallSubDelayed2(CallBack, EventName & "_AdClosed", Null)
End Sub

Public Sub OnUserEarnedReward
	CallSubDelayed2(CallBack, EventName & "_AdRewarded", Null)
End Sub

#If JAVA
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.OnUserEarnedRewardListener;

public static class MyRewardedInterstitialLoadCallback extends RewardedInterstitialAdLoadCallback {
    @Override
    public void onAdLoaded(RewardedInterstitialAd ad) {
        com.burma.royal2d.main.interstitial.OnAdLoaded(ad);
        ad.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdDismissedFullScreenContent() {
                com.burma.royal2d.main.interstitial.OnAdDismissed();
            }
        });
    }

    @Override
    public void onAdFailedToLoad(LoadAdError adError) {
        int errorCode = adError.getCode();
        com.burma.royal2d.main.interstitial.OnAdFailedWithCode(errorCode);
    }
}

public static class MyUserEarnedRewardListener implements OnUserEarnedRewardListener {
    @Override
    public void onUserEarnedReward(RewardItem rewardItem) {
        com.burma.royal2d.main.interstitial.OnUserEarnedReward();
    }
}
#End If
