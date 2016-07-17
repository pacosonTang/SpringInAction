<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<html lang="zh-CN">
<head>
    <title>Hello WebSocket</title>
    <script src="<c:url value="/resources/sockjs-1.1.1.js" />"></script>
    <script src="<c:url value="/resources/stomp.js" />"></script>
    <script type="text/javascript">
    	$(document).ready(function(){
    		connectAny();
    		checkoutUserlist();
    	});
    	
    	//ajax 访问函数
		function checkoutUserlist(){
			var userid=1;
			// alert("request for user list by ajax.");
			var url = "<c:url value='/feed' />"; //请求的地址 
			$.post(url,{
					greeting:"hello xiaotang" //[逗号 连接 ]
				},
				function(data){ // 回调函数 .
					alert(data);
				},"json"); 
		}
    	
        var stompClient = null;

        function setConnected(connected) {
            document.getElementById('connect').disabled = connected;
            document.getElementById('disconnect').disabled = !connected;
            document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
            document.getElementById('response').innerHTML = '';
        }
		
        //this line.
        function connect() {
            var socket = new SockJS("<c:url value='/hello'/>");
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                setConnected(true);
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/greetings', function(greeting){
                    showGreeting(JSON.parse(greeting.body).content);
                });
                
                stompClient.subscribe('/app/macro',function(greeting){
                	alert(JSON.parse(greeting.body).content);
                	showGreeting(JSON.parse(greeting.body).content);
                });
            });
        }
        
        function sendName() {
            var name = document.getElementById('name').value;
            stompClient.send("/app/hello", {}, JSON.stringify({ 'name': name }));
        }
        
        function connectAny() {
            var socket = new SockJS("<c:url value='/hello'/>");
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                setConnected(true);
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/feed', function(greeting){
                	alert(JSON.parse(greeting.body).content);
                    showGreeting(JSON.parse(greeting.body).content);
                });
            });
        }

        function disconnect() {
            if (stompClient != null) {
                stompClient.disconnect();
            }
            setConnected(false);
            console.log("Disconnected");
        }


        function showGreeting(message) {
            var response = document.getElementById('response');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(document.createTextNode(message));
            response.appendChild(p);
        }
    </script>
</head>
<body>
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being enabled. Please enable
    Javascript and reload this page!</h2></noscript>
<div>
    <div>
        <button id="connect" onclick="connect();">Connect</button>
        <button id="connectAny" onclick="connectAny();">ConnectAny</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
    </div>
    <div id="conversationDiv">
        <label>What is your name?</label><input type="text" id="name" />
        <button id="sendName" onclick="sendName();">Send</button>
        <p id="response"></p>
    </div>
</div>
</body>
</html>