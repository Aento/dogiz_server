/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.main;

import com.google.gson.Gson;
import doggizz.classes.*;
import doggizz.cloud.SendingMessage;
import doggizz.cloud.SmackCcsClient;
import doggizz.sql.*;
import doggizz.utils.GeneralAction;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jivesoftware.smack.XMPPException;
import org.json.JSONException;
import org.json.JSONObject;

public class AsyncRequestProcessor implements Runnable {

	private AsyncContext asyncContext;
	private int secs;
        HttpServletRequest request;
        HttpServletResponse response;
	public AsyncRequestProcessor() {
	}

	public AsyncRequestProcessor(AsyncContext asyncCtx,HttpServletRequest request, HttpServletResponse response) {
		this.asyncContext = asyncCtx;
		this.secs = secs;
                this.request = request;
                this.response = response;
	}

	@Override
	public void run() {
        try {
            processRequest(request,response);
            asyncContext.complete();
        } catch (ServletException ex) {
            Logger.getLogger(AsyncRequestProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AsyncRequestProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AsyncRequestProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(AsyncRequestProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
        
            protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, PropertyVetoException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DoggizMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
	GeneralAction responseObject = new GeneralAction();
        try {
            Query personQuery;
            GeneralAction requestAction = getRequestObject(request);
            

            if(requestAction==null)
            {
                System.out.println("Test print");
                out.print("test");
//                final String userName = "621192712917" + "@gcm.googleapis.com";
//                //final String userName = "621192712917@developer.gserviceaccount.com";
//                final String password = "AIzaSyC6DPs9chpI8wQC-rqm887JxrYjVp6CCzg";
//
//                SmackCcsClient ccsClient = new SmackCcsClient();
//
//                try {
//                ccsClient.connect(userName, password);
//                } catch (XMPPException e) {
//                System.out.println(e);
//                e.printStackTrace();
//                }
//            //     Send a sample hello downstream message to a device.
//                                  
//                String toRegId = "APA91bGxJ1JhjBlNFUzEUBLbM3XA13TM0icADE9IkjZ3RPqKzPeQiXyZdHDJdiugBACWyri8R64UiqZvbFoDQ5AD4fz3ENDyhpJRFdlXW1kQi8zZWkbt6bewliv3U9nW--_FpuoSEaADAh8ndVCbaa7K5QJ3jExbHQ";
//                String messageId = ccsClient.getRandomMessageId();
//                Map<String, String> payload = new HashMap<String, String>();
//                payload.put("action","1");
//                payload.put("EmbeddedMessageId", messageId+"test@#stas");
//                String collapseKey = "sample";
//                Long timeToLive = 10000L;
//                Boolean delayWhileIdle = true;
//                ccsClient.send(SmackCcsClient.createJsonMessage(toRegId, messageId, payload, collapseKey,
//                    timeToLive, delayWhileIdle));
                return;
            }
            
            switch(requestAction.getOperationCode())
	    {
                case GeneralAction.SENDING_REGISTRATION_INFO:
                {
                    UserPass up = requestAction.getUserPass();
                    User u = requestAction.getUser();
                    SendingRegistrationInfo sri = new SendingRegistrationInfo();
                    if(sri.CheckIfMailExist(u) <= 0)
                    {
                        try
                        {
                            u.setId(sri.SendingRegistrationInfo(u));
                            up.setPersonID(u.getId());
                            up.setId(sri.SendingRegistrationInfo(up));
                            sri = null;
//                            if(requestAction.getId() != null){
//                                Gcm gcm = new Gcm();
//                                gcm.Registrate_Gcm(requestAction.getString(), requestAction.getId());
//                            }
                        }
                        finally
                        {
                            responseObject.setUser(u);
                            responseObject.setUserPass(up);
                            responseObject.setResponseStatus("OK");
                            out.print(createServerResponse(responseObject));

                        }
                    }
                    else
                    {
                        responseObject.setUser(u);
                        responseObject.setUserPass(up);
                        responseObject.setResponseStatus("EXIST");
                        out.print(createServerResponse(responseObject));
                    }
                    u = null;
                    up = null;
                    break;
                }
                    
                    
                case GeneralAction.UPDATE_USER_INFO:
                {
                    UserPass up = requestAction.getUserPass();
                    User u = requestAction.getUser();
                    LoadingUsers lu = new LoadingUsers();
                    
                    try
                    {
                        if(u!=null){
                            lu.UpdateUserInfo(u);
                        }
                        if(up!=null){
                            lu.UpdateUserPass(up);
                        }
                    }
                    finally
                    {
                            responseObject.setResponseStatus("OK");
                            out.print(createServerResponse(responseObject));
                    }
                    u = null;
                    up = null;
                    break;
                }
                    
                case GeneralAction.LOADING_ALL_PARKS:
                {
                    ArrayList<Park> parkList = new ArrayList<Park>();
                    try
                    {
                        
                        LoadingParks lap = new LoadingParks();
                        parkList = lap.LoadingAllParks();
                        lap = null;
                    }
                    finally
                    {
                        responseObject.setParkList(parkList);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                        parkList = null;
                    }
                    break;
                }
//                    
//                case GeneralAction.LOADING_ALL_VETERINARIANS:
//                {
//                    ArrayList<Veterinarian> vetList = new ArrayList<Veterinarian>();
//                    try
//                    {
//                        
//                        LoadingVeterinarian lv = new LoadingVeterinarian();
//                        vetList = lv.LoadingVeterinarian();
//                        lv = null;
//                    }
//                    finally
//                    {
//                        responseObject.setVetList(vetList);
//                        responseObject.setResponseStatus("OK");
//                        out.print(createServerResponse(responseObject));
//                        vetList = null;
//                    }
//                    break;
//                }
//                    
//                case GeneralAction.LOADING_ALL_SHOPS:
//                {
//                    ArrayList<Shop> shopList = new ArrayList<Shop>();
//                    try
//                    {
//                        
//                        LoadingShop ls = new LoadingShop();
//                        shopList = ls.LoadingShop();
//                        ls = null;
//                    }
//                    finally
//                    {
//                        responseObject.setShopList(shopList);
//                        responseObject.setResponseStatus("OK");
//                        out.print(createServerResponse(responseObject));
//                        shopList = null;
//                    }
//                    break;
//                }
//                 
                case GeneralAction.LOADING_USER:
                {
                    User u = new User();
                    
                    LoadingUsers lu = new LoadingUsers();
                    u = lu.LoadingUsers(requestAction.getId());
                    responseObject.setUser(u);
                    responseObject.setResponseStatus("OK");
                    out.print(createServerResponse(responseObject));
                    u = null;
                    break;
                }                 
                case GeneralAction.CHECK_LOGIN:
                {
                    User u = new User();
                    UserPass up = new UserPass();
                    String email = requestAction.getString();
                    String pass = requestAction.getString2();
                    
                    CheckLogin cl = new CheckLogin();
                    up = cl.CheckLogin(email, pass);
                    if(up.getId() != null)
                    {
                        LoadingUsers lu = new LoadingUsers();
                        u = lu.LoadingUsers(up.getPersonID());
                        responseObject.setUser(u);
                        responseObject.setUserPass(up);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                        lu = null;
                    }
                    else
                    {
                        responseObject.setResponseStatus("ERROR");
                        out.print(createServerResponse(responseObject));
                    }
                    u = null;
                    up = null;
                    cl = null;
                    email = null;
                    pass = null;
                    break;
                }
//                
                case GeneralAction.LOADING_POINT_LOCATION:
                {
                    ArrayList<PointLocation> pointList = new ArrayList<PointLocation>();
                    try
                    {
                        
                        LoadingPointLocation lpl = new LoadingPointLocation();
                        pointList = lpl.LoadingPointLocation();
                        lpl = null;
                    }
                    finally
                    {
                        responseObject.setPointList(pointList);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                        pointList = null;
                    }
                    break;
                }
//                    
//                case GeneralAction.LOADING_ALL_CHECKIN:
//                {
//                    ArrayList<ParkCheckIn> checkInList = new ArrayList<ParkCheckIn>();
//                    try
//                    {
//                        
//                        LoadingCheckIn lci = new LoadingCheckIn();
//                        checkInList = lci.LoadingAllCheckIn(requestAction.getId());
//                        lci = null;
//                    }
//                    finally
//                    {
//                        responseObject.setCheckinList(checkInList);
//                        responseObject.setResponseStatus("OK");
//                        out.print(createServerResponse(responseObject));
//                        checkInList = null;
//                    }
//                    break;
//                }
//                    
//                case GeneralAction.LOADING_CHECKIN:
//                {
//                    ParkCheckIn pch = new ParkCheckIn();
//                    try
//                    {
//                        LoadingCheckIn lci = new LoadingCheckIn();
//                        pch = lci.LoadingCheckIn(requestAction.getId());
//                        lci = null;
//                    }
//                    finally
//                    {
//                        if(pch!=null)
//                        {
//                            responseObject.setCheckin(pch);
//                            responseObject.setResponseStatus("OK");
//                        }
//                        else
//                        {
//                            responseObject.setResponseStatus("NO RECORDS");
//                        }
//                        out.print(createServerResponse(responseObject));
//                        pch = null;
//                    }
//                    break;
//                } 
//                    
                case GeneralAction.SENDING_CHECKIN:
                {
                    try
                    {
                        LoadingCheckIn lci = new LoadingCheckIn();
                        lci.SendingCheckIn(requestAction.getCheckin(),requestAction.getLatitude(), requestAction.getLongitude());
                        lci = null;
                    }
                    finally
                    {
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    break;
                }
                
                case GeneralAction.SENDING_CHECKOUT:
                {
                    try
                    {
                        LoadingCheckIn lci = new LoadingCheckIn();
                        lci.SendingCheckOut(requestAction.getId());
                        lci = null;
                    }
                    finally
                    {
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    break;
                } 
                    
                case GeneralAction.SENDING_GCM_REGISTRATION:
                {
                    try
                    {
                        //IPHONE - 1
                        //ANDROID - 2
                        Gcm gcm = new Gcm();
                        gcm.Registrate_Gcm(requestAction.getString(), requestAction.getId(),requestAction.getId2());
                        gcm = null;
                    }
                    finally
                    {
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    break;
                } 
                    
                case GeneralAction.LOADING_ALL_NEW_MESSAGES:
                {
                    ArrayList<Message> msgList = new ArrayList<Message>();
                    try
                    {
                        Messages msg = new Messages();
                        msgList = msg.Load_All_New_Messages(requestAction.getId());
                        msg = null;
                    }
                    finally
                    {
                        responseObject.setMessageList(msgList);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    break;
                } 
//                
                case GeneralAction.SENDING_NEW_MESSAGE:
                {
                    long id = 0;
                    //long sender_id = requestAction.getId();
                    Messages msg = new Messages();
                    try
                    {
                        id = msg.Sending_New_Message(requestAction.getMessage());     
                        SendingMessage sm = new SendingMessage();
                        if(id!=0)
                            sm.SendingMessage(requestAction.getMessage().getReceiver().getId());
                        sm = null;
                        msg = null;
                    }
                    finally
                    {
                        responseObject.setId(id);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    break;
                }
                
                case GeneralAction.LOADING_ALL_MESSAGES:
                {
                    ArrayList<Message> msgList = new ArrayList<Message>();
                    try
                    {
                        Messages msg = new Messages();
                        msgList = msg.Load_All_Messages(requestAction.getId());
                        msg = null;
                    }
                    finally
                    {
                        responseObject.setMessageList(msgList);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    msgList = null;
                    break;
                }
                case GeneralAction.UPLOAD_PICTURE:
                {
                    long id = 0;
                    try
                    {
                        Pictures_sql pic = new Pictures_sql();
                        if(requestAction.getPicture()==null)
                            id = pic.UploadPicture(requestAction.getString(),0);
                        else
                            id = pic.UploadPicture(requestAction.getPicture().getPicture(),requestAction.getPicture().getId());
                        pic = null;
                    }
                    finally
                    {
                        responseObject.setId(id);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    break;
                }
                    
                case GeneralAction.LOAD_PICTURE:
                {
                    Picture picture = new Picture();
                    try
                    {
                        Pictures_sql pic = new Pictures_sql();
                        picture = pic.LoadPicture(requestAction.getId());
                        pic = null;
                    }
                    finally
                    {
                        responseObject.setPicture(picture);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    picture = null;
                    break;
                }
                    
                case GeneralAction.LOADING_PICTURE_OF_USER:
                {
                    Picture picture = new Picture();
                    try
                    {
                        Pictures_sql pic = new Pictures_sql();
                        picture = pic.LoadPictureOfUser(requestAction.getId());
                        pic = null;
                    }
                    finally
                    {
                        responseObject.setPicture(picture);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    picture = null;
                    break;
                }
                    
                case GeneralAction.SENDING_POINT:
                {
                    
                }
                    
                case GeneralAction.LOADING_AREA_POINTS:
                {
                    //Picture picture = new Picture();
                    try
                    {
                        Points_sql p = new Points_sql();
                        responseObject.setParkCheckInTotalList(p.LoadingMyAreaPoints(requestAction.getLatitude(), requestAction.getLongitude()));
                        p = null;
                    }
                    finally
                    {
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    //picture = null;
                    break;
                }
                    
                case GeneralAction.LOADING_CENTER_SCREEN_AREA_POINTS:
                {
                    ArrayList<ActivePoints> pointsList = new ArrayList<ActivePoints>();
                    try
                    {
                        Points_sql p = new Points_sql();
                        pointsList = p.LoadingCeneterScreenAreaPoints(requestAction.getLatitude(), requestAction.getLongitude());
                        p = null;
                    }
                    finally
                    {
                        responseObject.setActPointsList(pointsList);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    //picture = null;
                    break;
                }
                
                case GeneralAction.LOADING_CHECKIN_GENDER_COUNT:
                {
                    ParkCheckinTotal p = null;
                    try
                    {
                        LoadingCheckIn lch = new LoadingCheckIn();
                        p = lch.CheckInGenderCount(requestAction.getId());
                        lch = null;
                    }
                    finally
                    {
                        responseObject.setParkCheckInTotal(p);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    p = null;
                    break;
                }
                    
                case GeneralAction.LOADING_CHECKIN_DOGS_PICTURES:
                {
                    ArrayList<User> u = null;
                    try
                    {
                        LoadingCheckIn lch = new LoadingCheckIn();
                        u = lch.CheckInDogsPictures(requestAction.getId());
                        lch = null;
                    }
                    finally
                    {
                        responseObject.setUserList(u);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    u = null;
                    break;
                }
                
                
                case GeneralAction.LOADING_TOP_BOARD_MESSAGES:
                {
                    ArrayList<TopBoardMessages> tb = null;
                    try
                    {
                        SqlBoardMessages bm = new SqlBoardMessages();
                        tb = bm.LoadTopBoardMessages(requestAction.getId(),requestAction.getId2());
                        bm = null;
                    }
                    finally
                    {
                        responseObject.setTopBoardMessagesList(tb);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    tb = null;
                    break;
                }
                    
                case GeneralAction.LOADING_BOARD_MESSAGES:
                {
                    ArrayList<BoardMessages> tb = null;
                    try
                    {
                        SqlBoardMessages bm = new SqlBoardMessages();
                        tb = bm.LoadBoardMessages(requestAction.getId(),requestAction.getId2());
                        bm = null;
                    }
                    finally
                    {
                        responseObject.setBoardMessagesList(tb);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    tb = null;
                    break;
                }
                    
                case GeneralAction.UPLOAD_BOARD_MESSAGES:
                {
                    long id = 0;
                    try
                    {
                        SqlBoardMessages bm = new SqlBoardMessages();
                        id = bm.UploadBoardMEssage(requestAction.getBoardMessage());
                        bm = null;
                    }
                    finally
                    {
                        responseObject.setId(id);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    break;
                }
                
                    
                case GeneralAction.UPLOAD_TOP_BOARD_MESSAGES:
                {
                    long id = 0;
                    try
                    {
                        SqlBoardMessages bm = new SqlBoardMessages();
                        id = bm.UploadTopBoardMEssage(requestAction.getTopBoardMessage());
                        bm = null;
                    }
                    finally
                    {
                        responseObject.setId(id);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    break;
                }
                
                case GeneralAction.UPLOAD_ACTIVE_POINT:
                {
                    long id = 0;
                    try
                    {
                        Points_sql ps = new Points_sql();
                        id = ps.UploadPoint(requestAction.getActPoint());
                        ps = null;
                    }
                    finally
                    {
                        responseObject.setId(id);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    break;
                } 
                    
                case GeneralAction.UPLOAD_TAGGED_USER:
                {
                    long id = 0;
                    try
                    {
                        TaggedUser_sql ts = new TaggedUser_sql();
                        id = ts.UploadTaggedUser(requestAction.getTaggedUser());
                        ts = null;
                    }
                    finally
                    {
                        responseObject.setId(id);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    break;
                } 
                    
                case GeneralAction.REMOVE_TAGGED_USER:
                {
                    long id = 0;
                    try
                    {
                        TaggedUser_sql ts = new TaggedUser_sql();
                        ts.RemoveTaggedUser(requestAction.getId());
                        ts = null;
                    }
                    finally
                    {
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    break;
                } 
                    
                case GeneralAction.LOADING_ALL_TAGGED_USERS:
                {
                    ArrayList<TaggedUser> tagList = null;
                    try
                    {
                        TaggedUser_sql ts = new TaggedUser_sql();
                        tagList = ts.LoadAllTaggedUsers(requestAction.getId());
                        ts = null;
                    }
                    finally
                    {
                        responseObject.setTaggedList(tagList);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    break;
                } 
                    
                    //
                case GeneralAction.UPLOAD_FAVORITE_PARK:
                {
                    long id = 0;
                    try
                    {
                        FavoriteParks_sql fp = new FavoriteParks_sql();
                        id = fp.UploadFavoritePark(requestAction.getFavoritePark());
                        fp = null;
                    }
                    finally
                    {
                        responseObject.setId(id);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    break;
                } 
                    
                case GeneralAction.REMOVE_FAVORITE_PARK:
                {
                    long id = 0;
                    try
                    {
                        FavoriteParks_sql fp = new FavoriteParks_sql();
                        fp.RemoveFavoritePark(requestAction.getId());
                        fp = null;
                    }
                    finally
                    {
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    break;
                } 
                    
                case GeneralAction.LOADING_ALL_FAVORITE_PARKS:
                {
                    ArrayList<FavoriteParks> favList = null;
                    try
                    {
                        FavoriteParks_sql fp = new FavoriteParks_sql();
                        favList = fp.LoadAllFavoritesParks(requestAction.getId());
                        fp = null;
                    }
                    finally
                    {
                        responseObject.setFavoriteList(favList);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    break;
                } 
                    
                case GeneralAction.LOAD_ALL_DWPOINTS:
                {
                    ArrayList<ActivePoints> ap = null;
                    try
                    {
                        DWPoints_sql dw = new DWPoints_sql();
                        ap = dw.LoadAllDWPoints();
                        dw = null;
                    }
                    finally
                    {
                        responseObject.setActPointsList(ap);
                        responseObject.setResponseStatus("OK");
                        out.print(createServerResponse(responseObject));
                    }
                    break;
                } 
            }
        }catch(IOException e)
	{
	       e.printStackTrace();
	} finally { 
            System.gc();
            out.close();
            //destroy();
        }   
    }
            
        public String toString(GeneralAction responseObject) throws JSONException
    {
        Gson gsonConvertTo = new Gson();
        String jsonString = gsonConvertTo.toJson(responseObject);
        JSONObject myJsonObj = new JSONObject(jsonString);
        return myJsonObj.toString();
    }


    public GeneralAction getRequestObject(HttpServletRequest request) throws IOException
    {
	//String personDetails = request.getParameter("newPerson");
	StringBuilder sb = new StringBuilder();
	BufferedReader br = request.getReader();
	String str;
	while( (str = br.readLine()) != null ){
	    sb.append(str);
	}

	Gson gsonConvertFrom = new Gson();

	GeneralAction receivedAction = gsonConvertFrom.fromJson(sb.toString(), GeneralAction.class);

         if(br!=null)
            br.close();
	return receivedAction;
    }

    public String createServerResponse(GeneralAction responseObject)
    {
	String response;
	try
	{
	 response = toString(responseObject);
	}catch(JSONException e)
	{
	    response = "";
	}

	return response;
    }
}