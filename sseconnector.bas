B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Class
Version=13.35
@EndOfDesignText@
Sub Class_Globals
	Private mCallback As Object
	Private mEventName As String
	Private hc As OkHttpClient
	Private bbuffer As B4XBytesBuilder
	Public gout As OutputStream
	Public filename As String
	Dim packageName As String
	Dim gResponse As OkHttpResponse
	Dim lastReceiveTime As Long
	Dim fullbuffer As StringBuilder
	Dim urls As String
End Sub

Public Sub Initialize (Callback As Object, EventName As String,fname As String,pkg As String)
	fullbuffer.Initialize
	mCallback = Callback
	mEventName = EventName
	hc.Initialize("hc")
	filename=fname
	packageName=pkg 
	bbuffer.Initialize
End Sub

Public Sub Connect(url As String)
	Log(Main.isCall)
	Log(public_chat.isCall)
	If public_chat.isCall =True Or Main.isCall = True Then
		Log(Main.isCall)
		Log(public_chat.isCall)
		urls = url
		Dim req As OkHttpRequest
		req.InitializeGet(url)
		hc.Execute(req, 0)
	End If
End Sub

Private Sub hc_ResponseError (Response As OkHttpResponse, Reason As String, StatusCode As Int, TaskId As Int)
	Log("Live SSE Error: " & StatusCode)
	If Main.isCall=True Then
		CallSubDelayed(Main,"Connect")
	End If
	If public_chat.isCall = True Then
		CallSubDelayed(public_chat,"liveLoader")
	End If
	Response.Release
End Sub

Private Sub hc_ResponseSuccess (Response As OkHttpResponse, TaskId As Int)
	Dim out As JavaObject
	out.InitializeNewInstance(packageName&".sseconnector$MyOutputStream", Array(Me))
	gout = out
	gResponse=Response
	bbuffer.Clear
	Response.GetAsynchronously("req", out, False, 0)
	Wait For req_StreamFinish (Success As Boolean, TaskId As Int)
	If Main.isCall =True Or public_chat.isCall = True Then
		CallSubDelayed(Me,Finish)
		CallSubDelayed2(Me,"Connect",urls)
	End If
End Sub


Private Sub Data_Available (Buffer() As Byte)
	lastReceiveTime = DateTime.Now
Dim data As String =BytesToString(Buffer,0,Buffer.Length,"UTF-8")

Try

		If data.Contains("data:") Then
			data = data.Replace("data: ","")
			File.WriteString(File.DirInternal,filename,data)
			Dim json As JSONParser
			json.Initialize(data)
			Dim ls As List = json.NextArray
			If ls.Size>0 Then
				For i = 0 To ls.Size -1
					Dim m As Map = ls.Get(i)
			
					If mycode.LastLive  <> m.Get("live") Then
						If Main.isCall = True Then
							Log("live")
							CallSubDelayed(Main,"change")
						else if public_chat.isCall =True Then
							CallSubDelayed(public_chat,"Change")
						End If
					End If
					mycode.LastLive = m.Get("live")
				Next
			End If

		
		End If
	Catch
		Log("Data_Available error: " & LastException)
	End Try
End Sub



Public Sub Finish
	If gout.IsInitialized Then
		Try
			gResponse.Release
			gout.Close
		Catch
			Log(LastException)
		End Try
	End If
End Sub

Public Sub getlastime As Long
Return	lastReceiveTime
End Sub



#if Java
import java.io.*;
public static class MyOutputStream extends OutputStream {
    B4AClass cls;
    private boolean closed;
    public MyOutputStream (B4AClass cls) {
        this.cls = cls;
    }
    
    public void write(int b) throws IOException {
        if (closed)
            throw new IOException("closed");
        cls.getBA().raiseEventFromDifferentThread (null, null, 0, "data_available", true, new Object[] {new byte[] {(byte)b}});
    }
    public void write(byte b[], int off, int len) throws IOException {
        if (closed)
            throw new IOException("closed");
        byte[] bb = new byte[len];
        System.arraycopy(b, off, bb, 0, len);
        cls.getBA().raiseEventFromDifferentThread (null, null, 0, "data_available", true, new Object[] {bb});
    }
    public void close() {
        closed = true;
    }
}
#End If
