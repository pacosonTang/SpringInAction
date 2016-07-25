// XMPP服务器BOSH地址  
var BOSH_SERVICE = 'http://lenovo-pc:7070/http-bind/';  
  
// XMPP连接  
var connection = null;  
  
// 当前状态是否连接  
var connected = false;  
  
// 当前登录的JID  
var jid = "";  
  
// 连接状态改变的事件  
function onConnect(status) {  
    console.log(status)  
    if (status == Strophe.Status.CONNFAIL) {  
        alert("连接失败！");  
    } else if (status == Strophe.Status.AUTHFAIL) {  
        alert("登录失败！");  
    } else if (status == Strophe.Status.DISCONNECTED) {  
        alert("连接断开！");  
        connected = false;  
    } else if (status == Strophe.Status.CONNECTED) {  
        alert("连接成功，可以开始聊天了！");  
        connected = true;  
          
        // 当接收到<message>节，调用onMessage回调函数  
        connection.addHandler(onMessage, null, 'message', null, null, null);  
          
        // 首先要发送一个<presence>给服务器（initial presence）  
        connection.send($pres().tree());  
    }  
}  
  
// 接收到<message>  
function onMessage(msg) {  
      
    // 解析出<message>的from、type属性，以及body子元素  
    var from = msg.getAttribute('from');  
    var type = msg.getAttribute('type');  
    var elems = msg.getElementsByTagName('body');  
  
    if (type == "chat" && elems.length > 0) {  
        var body = elems[0];  
        $("#msg").append(from + ":<br>" + Strophe.getText(body) + "<br>")  
    }  
    return true;  
}  
  
$(document).ready(function() {  
  
    // 通过BOSH连接XMPP服务器  
    $('#btn-login').click(function() {  
        if(!connected) {  
            connection = new Strophe.Connection(BOSH_SERVICE);  
            connection.connect($("#input-jid").val(), $("#input-pwd").val(), onConnect);  
            jid = $("#input-jid").val();  
        }  
    });  
      
    // 发送消息  
    $("#btn-send").click(function() {  
        if(connected) {  
            if($("#input-contacts").val() == '') {  
                alert("请输入联系人！");  
                return;  
            }  
  
            // 创建一个<message>元素并发送  
            var msg = $msg({  
                to: $("#input-contacts").val(),   
                from: jid,   
                type: 'chat'  
            }).c("body", null, $("#input-msg").val());  
            connection.send(msg.tree());  
  
            $("#msg").append(jid + ":<br>" + $("#input-msg").val() + "<br>");  
            $("#input-msg").val('');  
        } else {  
            alert("请先登录！");  
        }  
    });  
});  