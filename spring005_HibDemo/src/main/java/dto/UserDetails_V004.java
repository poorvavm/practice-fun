package dto;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SelectBeforeUpdate;

@Entity(name = "USER_DETAILS")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)

@SelectBeforeUpdate(value = true)
public class UserDetails_V004 {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Column(name = "USER_NAME")
    private String userName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserDetails_V004 [userId=" + userId + ", userName=" + userName + "]";
    }

}
