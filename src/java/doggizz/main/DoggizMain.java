/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.main;
import com.google.gson.Gson;
import doggizz.utils.GeneralAction;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;
import java.util.concurrent.ThreadPoolExecutor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jivesoftware.smack.XMPPException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Stas
 */
@WebServlet(name = "DoggizMain", urlPatterns = {"/DoggizMain"}, asyncSupported = true)
public class DoggizMain extends HttpServlet {
//    EntityManagerFactory emf;
//    EntityManager em;
    public DoggizMain() throws XMPPException
    {
       //emf = Persistence.createEntityManagerFactory("OurDogsServerV2PU");
       //em = emf.createEntityManager();
    }
    
    @Override
    public void destroy()
    {
//        em.close();
//        emf.close();
    }
//    
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException, SQLException, PropertyVetoException {
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DoggizMain.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        //SmackCcsClient scc = new SmackCcsClient();
//        //scc.createJsonMessage(user, user, null, connection, Long.MIN_VALUE, Boolean.TRUE)
//        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");
//        PrintWriter out = response.getWriter();
//	GeneralAction responseObject = new GeneralAction();
//        //int ids = GenerateId("PICS");
//        try {
//            Query personQuery;
//            GeneralAction requestAction = getRequestObject(request);
//            
//
//            if(requestAction==null)
//            {
//                out.print("test");
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
//                return;
//            }
//            
//            switch(requestAction.getOperationCode())
//	    {
//                case GeneralAction.SENDING_REGISTRATION_INFO:
//                {
//                    UserPass up = requestAction.getUserPass();
//                    User u = requestAction.getUser();
//                    SendingRegistrationInfo sri = new SendingRegistrationInfo();
//                    if(sri.CheckIfMailExist(u) <= 0)
//                    {
//                        try
//                        {
//                            u.setId(sri.SendingRegistrationInfo(u));
//                            up.setPersonID(u.getId());
//                            up.setId(sri.SendingRegistrationInfo(up));
//                            sri = null;
//                        }
//                        finally
//                        {
//                            responseObject.setUser(u);
//                            responseObject.setUserPass(up);
//                            responseObject.setResponseStatus("OK");
//                            out.print(createServerResponse(responseObject));
//
//                        }
//                    }
//                    else
//                    {
//                        responseObject.setUser(u);
//                        responseObject.setUserPass(up);
//                        responseObject.setResponseStatus("EXIST");
//                        out.print(createServerResponse(responseObject));
//                    }
//                    u = null;
//                    up = null;
//                    break;
//                }
//                    
//                case GeneralAction.LOADING_ALL_PARKS:
//                {
//                    ArrayList<Park> parkList = new ArrayList<Park>();
//                    try
//                    {
//                        
//                        LoadingParks lap = new LoadingParks();
//                        parkList = lap.LoadingAllParks();
//                        lap = null;
//                    }
//                    finally
//                    {
//                        responseObject.setParkList(parkList);
//                        responseObject.setResponseStatus("OK");
//                        out.print(createServerResponse(responseObject));
//                        parkList = null;
//                    }
//                    break;
//                }
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
//                case GeneralAction.LOADING_USER:
//                {
//                    User u = new User();
//                    
//                    LoadingUsers lu = new LoadingUsers();
//                    u = lu.LoadingUsers(requestAction.getId());
//                    responseObject.setUser(u);
//                    responseObject.setResponseStatus("OK");
//                    out.print(createServerResponse(responseObject));
//                    u = null;
//                    break;
//                }
//                    
//                case GeneralAction.CHECK_LOGIN:
//                {
//                    User u = new User();
//                    UserPass up = new UserPass();
//                    String email = requestAction.getString();
//                    String pass = requestAction.getString2();
//                    
//                    CheckLogin cl = new CheckLogin();
//                    up = cl.CheckLogin(email, pass);
//                    if(up.getId() != null)
//                    {
//                        LoadingUsers lu = new LoadingUsers();
//                        u = lu.LoadingUsers(up.getPersonID());
//                        responseObject.setUser(u);
//                        responseObject.setUserPass(up);
//                        responseObject.setResponseStatus("OK");
//                        out.print(createServerResponse(responseObject));
//                        lu = null;
//                    }
//                    else
//                    {
//                        responseObject.setResponseStatus("ERROR");
//                        out.print(createServerResponse(responseObject));
//                    }
//                    u = null;
//                    up = null;
//                    cl = null;
//                    email = null;
//                    pass = null;
//                    break;
//                }
//                
//                case GeneralAction.LOADING_POINT_LOCATION:
//                {
//                    ArrayList<PointLocation> pointList = new ArrayList<PointLocation>();
//                    try
//                    {
//                        
//                        LoadingPointLocation lpl = new LoadingPointLocation();
//                        pointList = lpl.LoadingPointLocation();
//                        lpl = null;
//                    }
//                    finally
//                    {
//                        responseObject.setPointList(pointList);
//                        responseObject.setResponseStatus("OK");
//                        out.print(createServerResponse(responseObject));
//                        pointList = null;
//                    }
//                    break;
//                }
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
//                case GeneralAction.SENDING_CHECKIN:
//                {
//                    try
//                    {
//                        LoadingCheckIn lci = new LoadingCheckIn();
//                        lci.SendingCheckIn(requestAction.getCheckin());
//                        lci = null;
//                    }
//                    finally
//                    {
//                        responseObject.setResponseStatus("OK");
//                        out.print(createServerResponse(responseObject));
//                    }
//                    break;
//                }
//                
//                case GeneralAction.SENDING_CHECKOUT:
//                {
//                    try
//                    {
//                        LoadingCheckIn lci = new LoadingCheckIn();
//                        lci.SendingCheckOut(requestAction.getCheckin().getPersonID());
//                        lci = null;
//                    }
//                    finally
//                    {
//                        responseObject.setResponseStatus("OK");
//                        out.print(createServerResponse(responseObject));
//                    }
//                    break;
//                } 
//                    
//                case GeneralAction.SENDING_GCM_REGISTRATION:
//                {
//                    try
//                    {
//                        Gcm gcm = new Gcm();
//                        gcm.Registrate_Gcm(requestAction.getString(), requestAction.getId());
//                        gcm = null;
//                    }
//                    finally
//                    {
//                        responseObject.setResponseStatus("OK");
//                        out.print(createServerResponse(responseObject));
//                    }
//                    break;
//                } 
//                    
//                case GeneralAction.LOADING_ALL_NEW_MESSAGES:
//                {
//                    ArrayList<Message> msgList = new ArrayList<Message>();
//                    try
//                    {
//                        Messages msg = new Messages();
//                        msgList = msg.Load_All_New_Messages(requestAction.getId());
//                        msg = null;
//                    }
//                    finally
//                    {
//                        responseObject.setMessageList(msgList);
//                        responseObject.setResponseStatus("OK");
//                        out.print(createServerResponse(responseObject));
//                    }
//                    break;
//                } 
//                
//                case GeneralAction.SENDING_NEW_MESSAGE:
//                {
//                    long id = 0;
//                    try
//                    {
//                        Message m = requestAction.getMessage();
//                        Messages msg = new Messages();
//                        id = msg.Sending_New_Message(m);
//                        SendingMessage sm = new SendingMessage();
//                        sm.SendingMessage(m.getResiver_id());
//                        sm = null;
//                        msg = null;
//                        m = null;
//                    }
//                    finally
//                    {
//                        responseObject.setId(id);
//                        responseObject.setResponseStatus("OK");
//                        out.print(createServerResponse(responseObject));
//                    }
//                    break;
//                }
//                
//                  
//            }
//        }catch(IOException e)
//	{
//	       e.printStackTrace();
//	} finally { 
//            System.gc();
//            out.close();
//            //destroy();
//        }
//        
//        
//        
//        
//    }
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
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
        AsyncContext asyncCtx = request.startAsync();
        asyncCtx.addListener(new AppAsyncListener());
        asyncCtx.setTimeout(10000);

        ThreadPoolExecutor executor = (ThreadPoolExecutor) request
                        .getServletContext().getAttribute("executor");
        //executor.execute(new AsyncRequestProcessor(asyncCtx,request,response));
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
                AsyncContext asyncCtx = request.startAsync(request,response);
                asyncCtx.addListener(new AppAsyncListener());
                asyncCtx.setTimeout(10000);
                ThreadPoolExecutor executor = (ThreadPoolExecutor) request
                                .getServletContext().getAttribute("executor");

                executor.execute(new AsyncRequestProcessor(asyncCtx,request,response));
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
