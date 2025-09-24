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
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
Dim downloader As HttpJob
	Dim infodata As String
End Sub

Sub Service_Create
	'This is the program entry point.
	'This is a good place to load resources that are not specific to a single activity.
		
	fbi.Initialize
	fbi.SendEvent("login", CreateMap ("additional parameter": 100))
	CallSubDelayed2(FirebaseMessaging, "SubscribeToTopics", Array("general"))

End Sub

Sub Service_Start (StartingIntent As Intent)
	Service.StartForeground(1,CreateNotification("Burma 2D","Running Live Data"))
'Starter service can start in the foreground state in some edge cases.
End Sub
Sub CreateNotification(title As String, content As String) As Notification
	Dim n As Notification
	n.Initialize
	n.Icon = "icon" ' မိမိ drawable folder မှ icon နာမည် (ဥပမာ: "icon")
	n.SetInfo(title, content, Main) ' Main Activity ကို Target လုပ်ထားတာ
	Return n
End Sub
Sub Service_TaskRemoved
	'This event will be raised when the user removes the app from the recent apps list.
End Sub

'Return true to allow the OS default exceptions handler to handle the uncaught exception.
Sub Application_Error (Error As Exception, StackTrace As String) As Boolean
	Return True
End Sub

Sub Service_Destroy

End Sub

Sub JobDone (job As HttpJob)
	
Try
	If job.Success Then
		Select job.JobName
			Case Main.inforeader
				If Main.callable=True Then
					File.WriteString(File.DirInternal,"info",job.GetString)
				End If
			
			Case history.historyloader
				Log(job.GetString)
				If history.hiscallable=True Then
					CallSubDelayed2(history,"addview",job.GetString)
				End If
				
			Case "prelive"
				Log(infodata)
					If File.Exists(File.DirInternal,"info") Then
						CallSubDelayed2(Main,"getinfo",File.ReadString(File.DirInternal,"info"))
					End If
				
					File.WriteString(File.DirInternal,"live",job.GetString)
					Dim jb As HttpJob
					jb.Initialize(Main.livereader,Me)
					jb.Download(Main.site&"live.php")
			Case Main.livereader
				File.WriteString(File.DirInternal,"live",job.GetString)
				If Main.callable== True Then
					Sleep(2000)
					If Main.isCall =True Then
							Dim jb As HttpJob
							jb.Initialize(Main.livereader,Me)
							jb.Download(Main.site&"live.php")
					End If
	
				End If
			Case threed.threedloader
				Log(job.GetString)
				If threed.IsCall = True Then
					CallSubDelayed2(threed,"addview",job.GetString)	
				End If
			
			Case gift_activity.giftdataloader
				Log(job.GetString)
				If gift_activity.isCall = True Then
					CallSubDelayed2(gift_activity,"giftsuccess",job.GetString)	
				End If
			Case gift_imageview.imageloader
					Dim bmp As Bitmap = job.GetBitmap
					Dim out As OutputStream
					out = File.OpenOutput(File.DirInternal, gift_imageview.data.Get("img_id")&".png", False)
					bmp.WriteToStream(out, 100, "PNG") ' 100 = quality
					out.Close
					If gift_imageview.isCall = True Then
						CallSubDelayed(gift_imageview,"progresshide")
					End If
		End Select
		
		Else
			Select job.JobName
				Case threed.threedloader
					downloader.Initialize(threed.threedloader,Me)
					downloader.Download(Main.site&"?q= SELECT * FROM `threed` ORDER BY date DESC;")
				
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
				
				Case Main.livereader
						If Main.isCall = True Then
						downloader.Initialize(Main.livereader,Me)
						downloader.Download(Main.site&"live.php")
						End If
				Case history.historyloader
					If history.hiscallable = True Then
						downloader.Initialize(Main.livereader,Me)
						downloader.Download(Main.site&"?q=SELECT * FROM `dailyresults` ORDER BY date DESC;")
					End If
					
				
				
			End Select	
	End If
Catch
	If Main.isCall=True Then
			Dim jb As HttpJob
			jb.Initialize(Main.livereader,Me)
			jb.Download(Main.site&"live.php")
	End If
	Log(LastException)
End Try
End Sub
