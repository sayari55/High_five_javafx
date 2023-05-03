/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


/**
 *
 * @author khali
 */
public class SendSms {
     static final String ACCOUNT_SID ="AC6322202e674e9c2ede42f41107988ea1";
     static final String AUTH_TOKEN = "15e6276ab8399af2689300b26419d795";
 public void sendSms() {
     
        
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("+21658404108"),
                new PhoneNumber("+16076954758"),
                "Votre reservation a été confirmé")
            .create();

        System.out.println(message.getSid());
    }
    
}
