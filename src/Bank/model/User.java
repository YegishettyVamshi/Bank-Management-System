package Bank.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserId;
    private String firstName;
    private String lastName;
    private int nationalCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Account> accountList = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UpdateInfo> updateInfos = new ArrayList<>();

    public User() {
    }

    public User(String firstName, String lastName, int nationalCode, UserType userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.userType = userType;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(int nationalCode) {
        this.nationalCode = nationalCode;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<UpdateInfo> getUpdateInfos() {
        return updateInfos;
    }

    public void setUpdateInfos(List<UpdateInfo> updateInfos) {
        this.updateInfos = updateInfos;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationalCode=" + nationalCode +
                ", accountList=" + accountList +
                ", userType=" + userType +
                ", creationDate=" + creationDate +
                ", lastUpdate=" + lastUpdate +
                ", updateInfos=" + updateInfos +
                '}';
    }

    public enum UserType {
        GOODDEALLER, BADDEALER, NOHISTORY
    }
}
