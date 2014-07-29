/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.cloud;

import com.fasterxml.jackson.core.JsonGenerationException;
import doggizz.classes.Gcm_id;
import doggizz.sql.Gcm;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.sasl.SaslException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPException;
//IOS - JRE.
import javapns.*;
import javapns.devices.Device;
import javapns.notification.*;
import java.util.List;
/**
 *
 * @author Stas
 */
public class SendingMessage {
    public void SendingMessage(Long id)
    {
        //IPHONE - 1
        //ANDROID - 2
        //This message for maste to check merge!!!!
        System.out.println("Sending Message Notification Entry");
        Gcm gcm = new Gcm();
        
        Gcm_id gcm_id = new Gcm_id();
        try {
            gcm_id = gcm.Load_Gcm(id);
        } catch (SQLException ex) {
            Logger.getLogger(SendingMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        if(gcm_id.getOs_code() == 1){//IOS
            try {
            //Push.alert("Hello World!", "E:\\SQLServer\\SharedFolder\\PushNotificationCertificates\\Certificates.p12", "dogiz123", false,gcm_id.getGcm_id());
                System.out.println("Ios Part");
                List <PushedNotification> notifications = Push.badge(1, "E:\\SQLServer\\SharedFolder\\PushNotificationCertificates\\Certificates.p12", "dogiz123", false,gcm_id.getGcm_id());
            
                for (PushedNotification notification : notifications) {
                                if (notification.isSuccessful()) {
                                        /* Apple accepted the notification and should deliver it */  
                                        System.out.println("Push notification sent successfully to: " +
                                                                        notification.getDevice().getToken());
                                          
                                } else {
                                        String invalidToken = notification.getDevice().getToken();
                                        /* Add code here to remove invalidToken from your database */  
                                       // List<Device> inactiveDevices = Push.feedback("keystore.p12", "dogiz123", false);
                                        System.out.println("Invalid Token");
                                        if(invalidToken != null && !invalidToken.isEmpty()){
                                            System.out.println("Going to remove following token " +  notification.getDevice().getToken()+ "with user id =" + id);
                                            
                                        }
                                        
                                        /* Find out more about what the problem was */  
                                        Exception theProblem = notification.getException();
                                        theProblem.printStackTrace();
        
                                        /* If the problem was an error-response packet returned by Apple, get it */  
                                        ResponsePacket theErrorResponse = notification.getResponse();
                                        if (theErrorResponse != null) {
                                                System.out.println(theErrorResponse.getMessage());
                                        }
                                }
                }

           
           
           } catch (Exception ex) { 
               //Keystore or Communicatio Exceptoion may be thrown.
                   Logger.getLogger(SendingMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
            
            
        if(gcm_id.getOs_code()==2){
         /*   final String userName = "621192712917" + "@gcm.googleapis.com";
            //final String userName = "621192712917@developer.gserviceaccount.com";
            final String password = "AIzaSyC6DPs9chpI8wQC-rqm887JxrYjVp6CCzg";
            SmackCcsClient ccsClient;
            try {
                ccsClient = new SmackCcsClient();
            try {
                    //try {
                        ccsClient.connect(userName, password);
                    //} catch (SmackException ex) {
                        Logger.getLogger(SendingMessage.class.getName()).log(Level.SEVERE, null );
                    //} catch (SaslException ex) 
                        Logger.getLogger(SendingMessage.class.getName()).log(Level.SEVERE, null );
                    //} catch (IOException ex) {
                        Logger.getLogger(SendingMessage.class.getName()).log(Level.SEVERE, null);
                    //}
            } 
            catch (XMPPException e) 
            {
                System.out.println(e);
                e.printStackTrace();
            }

        // String toRegId = "APA91bGxJ1JhjBlNFUzEUBLbM3XA13TM0icADE9IkjZ3RPqKzPeQiXyZdHDJdiugBACWyri8R64UiqZvbFoDQ5AD4fz3ENDyhpJRFdlXW1kQi8zZWkbt6bewliv3U9nW--_FpuoSEaADAh8ndVCbaa7K5QJ3jExbHQ";
            String messageId = ccsClient.getRandomMessageId();
            Map<String, String> payload = new HashMap<String, String>();
            payload.put("action","1");
            payload.put("EmbeddedMessageId", messageId+"test@#stas");
            String collapseKey = "sample";
            Long timeToLive = 10000L;
            Boolean delayWhileIdle = true;
            ccsClient.send(SmackCcsClient.createJsonMessage(gcm_id.getGcm_id(), messageId, payload, collapseKey,
                timeToLive, delayWhileIdle));
            //} catch (NotConnectedException ex) {
                //Logger.getLogger(SendingMessage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(SendingMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
*/
            String apiKey = "AIzaSyC6DPs9chpI8wQC-rqm887JxrYjVp6CCzg";
            Content c = new Content();
            c.addRegId(gcm_id.getGcm_id());
            c.createData("action","1");
            try {
                POST2GCM.post(apiKey, c);
            } catch (JsonGenerationException ex) {
                Logger.getLogger(SendingMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
