package Bank.model;

import javax.persistence.*;
import java.util.Date;
@Entity
public class UpdateInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String name;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    private User user=new User();

    @Override
    public String toString() {
        return "UpdateInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", user=" + user +
                '}';
    }
}
