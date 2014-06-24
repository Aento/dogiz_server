package doggizz.classes;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.0.v20110604-r9504", date="2014-02-15T11:00:04")
@StaticMetamodel(UserPass.class)
public class UserPass_ { 

    public static volatile SingularAttribute<UserPass, Long> id;
    public static volatile SingularAttribute<UserPass, String> email;
    public static volatile SingularAttribute<UserPass, String> password;
    public static volatile SingularAttribute<UserPass, Long> personID;

}