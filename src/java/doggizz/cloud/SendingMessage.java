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

/**
 *
 * @author Stas
 */
public class SendingMessage {
    public void SendingMessage(Long oc_code,int action,String value)
    {   
        
        //  OC_CODE 
        //IPHONE - 1
        //ANDROID - 2
        
        //  ACTION
        //Message - 1
        //BoardMessage - 2
        
        
        
        
        Gcm gcm = new Gcm();
        
        Gcm_id gcm_id = new Gcm_id();
        try {
            gcm_id = gcm.Load_Gcm(oc_code);
        } catch (SQLException ex) {
            Logger.getLogger(SendingMessage.class.getName()).log(Level.SEVERE, null, ex);
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
            //comment test
            String apiKey = "AIzaSyC6DPs9chpI8wQC-rqm887JxrYjVp6CCzg";
            //String apiKey = "AIzaSyDdkuQfmIPLcmYZiVCQ_RTq-S_PitOJnvY";
            Content c = new Content();
            c.addRegId(gcm_id.getGcm_id());
            c.createData("action","" +action );
            c.createData("value", "" +value);
            try {
                POST2GCM.post(apiKey, c);
            } catch (JsonGenerationException ex) {
                Logger.getLogger(SendingMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
