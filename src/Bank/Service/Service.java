package Bank.Service;

import Bank.model.Account;
import Bank.model.Transactions;
import Bank.model.User;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import java.util.Date;
import java.util.List;

public class Service {
    static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public void createAccount(User user, int accountNumber, int cardNumber,
                              Account.AccountType accountType, int balance, int cvv,
                              Date expire) {
        Account account = new Account(accountNumber, cardNumber, accountType,
                balance, cvv, expire, user);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(account);
        transaction.commit();
        session.close();
        //  sessionFactory.close();

    }

    public void createUser(String firstName, String lastName, int nationalCode
            , User.UserType userType) {
        User user = new User(firstName, lastName, nationalCode, userType);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();

    }

    public void close() {
        sessionFactory.close();
    }

    public void findByFirstName(String firstName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from user where firstName= :firstName";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(User.class);
        query.setParameter("firstName", firstName);
        List<User> list = query.list();
        list.forEach(System.out::println);
        session.close();

    }

    public void findByLastName(String lastName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from user where lastName = :lastName";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(User.class);
        query.setParameter("lastName", lastName);
        List list = query.list();
        list.forEach(System.out::println);
        session.close();

    }

    public User findByCardNumber(int cardNumber) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from account where cardNumber = :cardNumber";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Account.class);
        query.setParameter("cardNumber", cardNumber);
        Account account = (Account) query.list().get(0);
        session.close();
        return account.getUser();

    }
    public User findByNationalCode(int nationalCode){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from user where nationalCode = :nationalCode";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(User.class);
        query.setParameter("nationalCode", nationalCode);
        User user = (User) query.list().get(0);
        transaction.commit();
        session.close();
        return user;

    }
    public Account.AccountType findAccountType(String accountType){
        switch (accountType){
            case "i":
                return Account.AccountType.SAVINGS;
               // break;
            case "s":
                return Account.AccountType.SHORTTERM;
            case "l":
                return Account.AccountType.LONGTERM;
            case"c":
                return Account.AccountType.CURRENT;

        }
        return null;
    }

    public Account findAccountByCardNumber(int cardNumber) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "select * from account where cardNumber = :cardNumber";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Account.class);
        query.setParameter("cardNumber", cardNumber);
        Account account = (Account) query.list().get(0);
        session.close();
        return account;

    }

    public void withdraw(int cardNumber, int fee) {
        Account account = findAccountByCardNumber(cardNumber);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Transactions transactions = new Transactions(fee,new Date(),
                Transactions.TransactionType.WITHDRAW,account);
        account = this.saveTransaction(cardNumber,transactions,account);
        float temp = account.getBalance() - fee;
        account.setBalance(temp);
        session.update(account);
        transaction.commit();
        session.close();


    }
    public void deposit(int cardNumber,int fee){
        Account account = findAccountByCardNumber(cardNumber);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Transactions transactions = new Transactions(fee,new Date(),
                Transactions.TransactionType.DEPOSIT,account);
        account = this.saveTransaction(cardNumber,transactions,account);
        float temp = account.getBalance() + fee;
        account.setBalance(temp);
        session.update(account);
        transaction.commit();
        session.close();
    }

    public Account saveTransaction(int cardNumber, Transactions accountTransaction, Account account){
        account.getTransactionList().add(accountTransaction);
        return account;
    }
}