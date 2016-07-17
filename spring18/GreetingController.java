package com.spring.spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.pojo.Greeting;
import com.spring.pojo.HelloMessage;

@Controller
public class GreetingController {
	
	// @MessageMapping defines the sending addr for client.
	// 消息发送地址: /server/app/hello
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		System.out.println("receiving " + message.getName());
		System.out.println("connecting successfully.");
		return new Greeting("Hello, " + message.getName() + "!");
	}
	
	@SubscribeMapping("/macro")
	public Greeting handleSubscription() {
		System.out.println("this is the @SubscribeMapping('/marco')");
		Greeting greeting = new Greeting("i am a msg from SubscribeMapping('/macro').");
		return greeting;
	}
	
	/*@MessageMapping("/feed")
	@SendTo("/topic/feed")
	public Greeting greetingForFeed(HelloMessage message) throws Exception {
		System.out.println("receiving " + message.getName());
		System.out.println("connecting successfully.");
		return new Greeting("i am /topic/feed, hello " + message.getName() + "!");
	}*/

	//	private SimpMessagingTemplate  template;
	// SimpMessagingTemplate implements SimpMessageSendingOperations. 
	private SimpMessageSendingOperations  template;

    @Autowired
    public GreetingController(SimpMessageSendingOperations  template) {
        this.template = template;
    }

    @RequestMapping(path="/feed", method=RequestMethod.POST)
    public void greet(
    		@RequestParam String greeting) {
        String text = "you said just now " + greeting;
        this.template.convertAndSend("/topic/feed", text);
    }
}