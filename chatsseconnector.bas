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

	Private fullbuffer As StringBuilder
End Sub

Public Sub Initialize (Callback As Object, EventName As String,fname As String,pkg As String)
	fullbuffer.Initialize
	mCallback = Callback
	mEventName = EventName
	hc.Initialize("hchat")
	filename=fname
	packageName=pkg
	bbuffer.Initialize
End Sub

Public Sub Connect(url As String)
	If public_chat.isCall = True Then
		Dim req As OkHttpRequest
		req.InitializeGet(url)
		hc.Execute(req, 0)
	End If
End Sub

Private Sub hchat_ResponseError (Response As OkHttpResponse, Reason As String, StatusCode As Int, TaskId As Int)
	Log("Chat SSE Error: " & StatusCode)
	Response.Release
End Sub

Private Sub hchat_ResponseSuccess (Response As OkHttpResponse, TaskId As Int)
	Dim out As JavaObject
	out.InitializeNewInstance(packageName&".chatsseconnector$MyOutputStream", Array(Me))
	gout = out
	gResponse=Response
	bbuffer.Clear
	Response.GetAsynchronously("chatreq", out, False, 0)
	Wait For chatreq_StreamFinish (Success As Boolean, TaskId As Int)
	Log("Stream finish")
End Sub
Private Sub Data_Available (Buffer() As Byte)
	lastReceiveTime = DateTime.Now

	Try
		Dim chunk As String = BytesToString(Buffer, 0, Buffer.Length, "UTF-8")
		If chunk.Contains("data: ") Then
			chunk = chunk.Replace("data: ","")
		End If
		If(chunk.Contains("data: ") ) Then
			chunk =chunk.Replace("data: ","")
		End If
		fullbuffer.Append(chunk)

		' Messages separated by newline (\n or \r\n)
		Do While True
			Dim newlineIndex As Int = fullbuffer.ToString.IndexOf(CRLF)
			If newlineIndex = -1 Then Exit

			Dim oneMessage As String = fullbuffer.ToString.SubString2(0, newlineIndex).Trim
			fullbuffer.Remove(0, newlineIndex + 2)

			If oneMessage.Length > 0 Then
				CallSubDelayed2(Me, "LiveChat", oneMessage)
			End If
		Loop

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


Private Sub LiveChat (data As String)
	If public_chat.isCall = True Then
		If data.Contains(": ping") Then
		Else
			If data.StartsWith("[") Then
				
					Dim json As JSONParser
					json.Initialize(data)
					mycode.chatlist =json.NextArray
			
			Else if data.StartsWith("{") Then
				Dim json As JSONParser
				json.Initialize(data)
				Dim m As Map = json.NextObject
				If mycode.chatlist.IsInitialized Then
					mycode.chatlist.Add(m)
					If mycode.chatlist.Size >= 50 Then
						mycode.chatlist.RemoveAt(0) ' Remove first item
					End If
					Else	
				End If
			End If
			CallSubDelayed2(public_chat,"loadMessageSuccess",data)
		End If
	End If
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
