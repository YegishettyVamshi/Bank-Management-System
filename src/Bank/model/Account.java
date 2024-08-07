package Bank.model;

import jdk.jfr.Timespan;
import jdk.jfr.Timestamp;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserId;
    private int accountNumber;
    private int cardNumber;
    @CreationTimestamp
    private Date openDate;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private float balance;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public List<Transactions> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transactions> transactionList) {
        this.transactionList = transactionList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private int cvv;
    @Timestamp
    private Date expire;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account",fetch = FetchType.EAGER)
    private List<Transactions> transactionList = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    public Account(int accountNumber, int cardNumber, AccountType accountType, float balance, int cvv, Date expire, User user) {
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.cvv = cvv;
        this.expire = expire;
        this.user = user;
    }


    @Override
    public String toString() {
        return "Account{" +
                "UserId=" + UserId +
                ", accountNumber=" + accountNumber +
                ", cardNumber=" + cardNumber +
                ", openDate=" + openDate +
                ", accountType=" + accountType +
                ", balance=" + balance +
                ", cvv=" + cvv +
                ", expire=" + expire +
                ", transactionList size=" + (transactionList != null ? transactionList.size() : 0) +
                ", user=" + (user != null ? user.getUserId() : "null") +
                '}';
    }

    public Account(){

    }

    public enum AccountType{
        SAVINGS, CURRENT, SHORTTERM, LONGTERM
    }

}
