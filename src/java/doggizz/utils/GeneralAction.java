/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doggizz.utils;

import doggizz.classes.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Stas
 */
public class GeneralAction {
    public static final int SENDING_REGISTRATION_INFO = 1;
    public static final int LOADING_ALL_PARKS = 2;
    /*
    Loading User From Server 
    Send:GeneralAction with ID field.
    Receieve:User Class (NOT include dog,owner pictures)Wraped in GeneralAction.
    */
    public static final int LOADING_USER = 3;
    public static final int CHECK_LOGIN = 4;
    public static final int LOADING_POINT_LOCATION = 5;
    public static final int LOADING_ALL_VETERINARIANS = 6;
    public static final int LOADING_ALL_SHOPS = 7;
    public static final int LOADING_ALL_CHECKIN = 8;
    public static final int LOADING_CHECKIN = 9;
    public static final int SENDING_CHECKIN = 10;
    public static final int SENDING_CHECKOUT = 11;
    public static final int SENDING_GCM_REGISTRATION = 12;
    public static final int LOADING_ALL_NEW_MESSAGES = 13;
    public static final int SENDING_NEW_MESSAGE = 14;
    public static final int LOADING_ALL_MESSAGES = 15;
    public static final int LOADING_CHECK_MESSAGES_GROUP_ID_COUNT = 40;
    public static final int UPLOAD_PICTURE = 16;
    public static final int LOAD_PICTURE = 17;
    public static final int SENDING_POINT = 18;
    public static final int LOADING_POINTS = 19;
    public static final int LOADING_AREA_POINTS = 20;
    public static final int LOADING_CENTER_SCREEN_AREA_POINTS = 21;
    public static final int LOADING_CHECKIN_GENDER_COUNT = 22;
    public static final int LOADING_CHECKIN_DOGS_PICTURES = 23;
    public static final int LOADING_PICTURE_OF_USER = 24;
    public static final int LOADING_TOP_BOARD_MESSAGES = 25;
    public static final int LOADING_BOARD_MESSAGES = 26;
    public static final int UPLOAD_BOARD_MESSAGES = 27;
    public static final int UPLOAD_TOP_BOARD_MESSAGES = 28;
    /*
    Uploading ActivePoint To Server (e.g:INSPECTOR,LOSTDOG,REPORTSMTH):
    Send:GeneralAction with ActivePoints Class.
    Receieve:"OK" in respond Status.
    */
    public static final int UPLOAD_ACTIVE_POINT = 29;
    /*
    Uploading TaggedUser To Server:
    Send:GeneralAction with TaggedUser Class.
    Receieve:"OK" in respond Status.
    */
    public static final int UPLOAD_TAGGED_USER = 30;
    /*
    Load TaggedUserList of User specified by his ID From Server:
    Send:GeneralAction with ID.
    Receieve:TaggedList of taggetdUsers when User Field is Null.
    "OK" in respondStatus wrapped in GeneralAction.
    */
    public static final int LOADING_ALL_TAGGED_USERS = 31;
     /*
    Remove TaggedUser From Server:
    Send:GeneralAction with ID.
    Receieve:"OK" in respondStatus wrapped in GeneralAction.
    */
    public static final int REMOVE_TAGGED_USER = 32;
    public static final int UPLOAD_FAVORITE_PARK = 33;
    public static final int LOADING_ALL_FAVORITE_PARKS = 34;
    public static final int REMOVE_FAVORITE_PARK= 35;
    /*
    Load User and UserPass to Server:
    Send:GeneralAction with User UserPass Classes.
    (in case of dog,owner pictures are NULL the current one won't
    be replaced.
    Receieve:"OK" in respondStatus wrapped in GeneralAction.
    */
    public static final int UPDATE_USER_INFO = 36;
    public static final int LOAD_ALL_DWPOINTS = 37;
    public static final int REMOVE_DWPOINT = 38;
    public static final int SENDING_DWPOINTS = 39;
    public static final int UPDATE_USER_NAME_PHONE = 40;
    
    
    private long id;
    private long id2;
    private String string;
    private String string2;
    private User user;
    private UserPass userPass;
    private Picture picture;
    private ActivePoints actPoint;
    private Float latitude;
    private Float longitude;
    private ParkCheckinTotal parkCheckInTotal;
    private BoardMessages boardMessage;
    private TopBoardMessages topBoardMessage;
    private TaggedUser taggedUser;
    private ArrayList<ActivePoints> actPointsList;
    private ArrayList<TaggedUser> taggedList;
    private FavoriteParks favoritePark;
    private ArrayList<FavoriteParks> favoriteList;
    private ParkCheckIn checkin;
    private Message message;
//    private List<Long> id_list;
    private ArrayList<Message> messageList;
    private ArrayList<Park> parkList;
    private ArrayList<ParkCheckinTotal> parkCheckInTotalList;
    private ArrayList<Picture> pictureList;
//    private ArrayList<Veterinarian> vetList;
    private ArrayList<Shop> shopList;
    private ArrayList<PointLocation> pointList;
    private ArrayList<ParkCheckIn> checkinList;
    private ArrayList<User> userList;
    private ArrayList<TopBoardMessages> topBoardMessagesList;
    private ArrayList<BoardMessages> boardMessagesList;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public long getId2() {
        return id2;
    }

    public void setId2(long id2) {
        this.id2 = id2;
    }
    
    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
    
    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }
       
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public UserPass getUserPass() {
        return userPass;
    }

    public void setUserPass(UserPass userPass) {
        this.userPass = userPass;
    }
    
    public ArrayList<Park> getParkList() {
        return parkList;
    }

    public void setParkList(ArrayList<Park> parkList) {
        this.parkList = parkList;
    }    
    
    public ArrayList<PointLocation> getPointList() {
        return pointList;
    }

    public void setPointList(ArrayList<PointLocation> pointList) {
        this.pointList = pointList;
    }
    
    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture pictur) {
        this.picture = pictur;
    }
    
    public ArrayList<ParkCheckinTotal> getParkCheckInTotalList() {
        return parkCheckInTotalList;
    }

    public void setParkCheckInTotalList(ArrayList<ParkCheckinTotal> parkCheckInTotalList) {
        this.parkCheckInTotalList = parkCheckInTotalList;
    }
    
    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongtitude(Float longitude) {
        this.longitude = longitude;
    }
    
    public ArrayList<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(ArrayList<Picture> pictureList) {
        this.pictureList = pictureList;
    }
    
    public ParkCheckinTotal getParkCheckInTotal() {
        return parkCheckInTotal;
    }

    public void setParkCheckInTotal(ParkCheckinTotal parkCheckInTotal) {
        this.parkCheckInTotal = parkCheckInTotal;
    }
    
    public TaggedUser getTaggedUser() {
        return taggedUser;
    }

    public void setTaggedUser(TaggedUser taggedUser) {
        this.taggedUser = taggedUser;
    }
    
     public ArrayList<TaggedUser> getTaggedList() {
        return taggedList;
    }

    public void setTaggedList(ArrayList<TaggedUser> taggedList) {
        this.taggedList = taggedList;
    }
//    public ArrayList<Veterinarian> getVetList() {
//        return vetList;
//    }
//
//    public void setVetList(ArrayList<Veterinarian> vetList) {
//        this.vetList = vetList;
//    }
    
    public ArrayList<Shop> getShopList() {
            return shopList;
    }

    public void setShopList(ArrayList<Shop> shopList) {
            this.shopList = shopList;
    }
    
    public ParkCheckIn getCheckin() {
            return checkin;
    }

    public void setCheckin(ParkCheckIn checkin) {
            this.checkin = checkin;
    }

    public ArrayList<ParkCheckIn> getCheckinList() {
            return checkinList;
    }

    public void setCheckinList(ArrayList<ParkCheckIn> checkinList) {
            this.checkinList = checkinList;
    }
    
    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }
    
    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public ArrayList<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(ArrayList<Message> messageList) {
        this.messageList = messageList;
    }
//    
//    public List<Long> getId_list() {
//            return id_list;
//    }
//
//    public void setId_list(List<Long> id_list) {
//            this.id_list = id_list;
//    }
//    
    
    public ActivePoints getActPoint() {
        return actPoint;
    }

    public void setActPoint(ActivePoints actPoint) {
        this.actPoint = actPoint;
    }

    public ArrayList<ActivePoints> getActPointsList() {
        return actPointsList;
    }

    public void setActPointsList(ArrayList<ActivePoints> actPointsList) {
        this.actPointsList = actPointsList;
    }

    public ArrayList<TopBoardMessages> getTopBoardMessagesList() {
        return topBoardMessagesList;
    }

    public void setTopBoardMessagesList(ArrayList<TopBoardMessages> topBoardMessagesList) {
        this.topBoardMessagesList = topBoardMessagesList;
    }

    public ArrayList<BoardMessages> getBoardMessagesList() {
        return boardMessagesList;
    }

    public void setBoardMessagesList(ArrayList<BoardMessages> boardMessagesList) {
        this.boardMessagesList = boardMessagesList;
    }
    private int operationCode;

        public BoardMessages getBoardMessage() {
        return boardMessage;
    }

    public void setBoardMessage(BoardMessages boardMessage) {
        this.boardMessage = boardMessage;
    }
    
    
    public TopBoardMessages getTopBoardMessage() {
        return topBoardMessage;
    }

    public void setTopBoardMessage(TopBoardMessages topBoardMessage) {
        this.topBoardMessage = topBoardMessage;
    }
    
    public FavoriteParks getFavoritePark() {
        return favoritePark;
    }

    public void setFavoritePark(FavoriteParks favoritePark) {
        this.favoritePark = favoritePark;
    }

    public ArrayList<FavoriteParks> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(ArrayList<FavoriteParks> favoriteList) {
        this.favoriteList = favoriteList;
    }
    
    /**
     * Get the value of operationCode
     *
     * @return the value of operationCode
     */
    public int getOperationCode() {
	return operationCode;
    }

    /**
     * Set the value of operationCode
     *
     * @param operationCode new value of operationCode
     */
    public void setOperationCode(int operationCode) {
	this.operationCode = operationCode;
    }


    private byte[] imageContent;

    /**
     * Get the value of imageContent
     *
     * @return the value of imageContent
     */
    public byte[] getImageContent() {
	return imageContent;
    }

    /**
     * Set the value of imageContent
     *
     * @param imageContent new value of imageContent
     */
    public void setImageContent(byte[] imageContent) {
	this.imageContent = imageContent;
    }
    
    
    protected String responseStatus;

    /**
     * Get the value of responseStatus
     *
     * @return the value of responseStatus
     */
    public String getResponseStatus() {
	return responseStatus;
    }

    /**
     * Set the value of responseStatus
     *
     * @param responseStatus new value of responseStatus
     */
    public void setResponseStatus(String responseStatus) {
	this.responseStatus = responseStatus;
    }
}
