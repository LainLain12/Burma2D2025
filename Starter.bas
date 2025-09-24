B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Service
Version=9.85
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: False
	#ExcludeFromLibrary: True
#End Region

Sub Process_Globals
	Dim fbi As FirebaseAnalytics

Dim downloader As HttpJob
	Dim infodata As String
End Sub

Sub Service_Create
	fbi.Initialize
	fbi.SendEvent("login", CreateMap ("additional parameter": 100))
	CallSubDelayed2(FirebaseMessaging, "SubscribeToTopics", Array("general"))

End Sub

Sub Service_Start (StartingIntent As Intent)
	If File.Exists(File.DirInternal,"user") =False Then
		Dim auth As FirebaseAuth
		auth.Initialize("auth")
		auth.SignOutFromGoogle
	End If
End Sub

Sub Service_TaskRemoved
	
End Sub


Sub Application_Error (Error As Exception, StackTrace As String) As Boolean
	Return True
End Sub

Sub Service_Destroy

End Sub

Sub JobDone (job As HttpJob)
	
Try
	If job.Success Then
		Select job.JobName	
			Case "lottohis"
				ApiCall.lottohis = job.GetString
				If LottoHistory .isCall = True Then
					CallSubDelayed(LottoHistory,"lottohisuccess")
				End If
				Case lottosociety.lottogetter
					
					ApiCall.lottodata=job.GetString
					If lottosociety.isCall =True Then
						CallSubDelayed(lottosociety,"getlottosuccess")
					End If
				Case "saveimage"
					mycode.AddBitmapToGallery(job.GetInputStream,DateTime.Now&".png","image/png")
			Case Future_Tips.papergetters
				mycode.paperdata= job.GetString
			If Future_Tips.isCall = True Then
				CallSubDelayed(Future_Tips,"getpapersuccess")
			End If
			
			Case history.historyloader
				If history.hiscallable=True Then
				
					CallSubDelayed2(history,"addview11",job.GetString)
				End If
		
			Case threed.threedloader
				
				If threed.IsCall = True Then
					CallSubDelayed2(threed,"addview",job.GetString)	
				End If
			
			Case gift_activity.giftdataloader
				If gift_activity.isCall = True Then
					CallSubDelayed2(gift_activity,"giftsuccess",job.GetString)	
				End If
			Case gift_imageview.imageloader
					Dim bmp As Bitmap = job.GetBitmap
					Dim out As OutputStream
					out = File.OpenOutput(File.DirInternal, gift_imageview.data.Get("img_id")&".png", False)
					bmp.WriteToStream(out, 100, "PNG") ' 100 = qualityhttps://www.vecteezy.com/vector-art/22008797-dark-black-geometric-grid-carbon-fiber-background-modern-dark-abstract-seamless-texture
					out.Close
					If gift_imageview.isCall = True Then
						CallSubDelayed(gift_imageview,"progresshide")
					End If
				Case report_details.reportJob
					Dim json As JSONParser
					json.Initialize(job.GetString)
					Dim m As Map = json.NextObject
					ToastMessageShow(m.Get("message"),False)
					'''''''''SEND MESSAGE'''''''''''
				Case public_chat.sendSMS
					
		End Select
		
		Else
			Select job.JobName
				Case lottosociety.lottogetter
					ApiCall.getlottodata
				Case Future_Tips.papergetters
'					Dim j As HttpJob
'					j.Initialize("papergetter",Me)
'					j.Download(Main.newsite&"/futurepaper/getpaper")
				Case threed.threedloader
					If threed.IsCall = True Then
						downloader.Initialize(threed.threedloader,Me)
						downloader.Download(Main.site&"?q= SELECT * FROM `threed` ORDER BY date DESC;")
					End If
					
				
				Case gift_activity.giftdataloader
					If gift_activity.isCall = True Then
						downloader.Initialize(gift_activity.giftdataloader,Me)
						downloader.Download(Main.site&"?q=SELECT * FROM `gift` WHERE 1;")
					
					End If
				Case Main.inforeader
					Log("Info Check Fail")
					If Main.isCall = True Then				
						downloader.Initialize(Main.inforeader,Me)
						downloader.Download(Main.site&"info.txt")
					End If
				Case history.historyloader
					If history.hiscallable = True Then
						downloader.Initialize(Main.livereader,Me)
						downloader.Download(Main.site&"?q=SELECT * FROM `dailyresults` ORDER BY date DESC;")
					End If
			End Select	
	End If
	Catch

	Select job.JobName
			Case Main.inforeader
				Log("Info Check Fail")
				If Main.isCall = True Then
					downloader.Initialize(Main.inforeader,Me)
					downloader.Download(Main.site&"info.txt")
				End If
			
	End Select
	Log(LastException)
End Try
End Sub
