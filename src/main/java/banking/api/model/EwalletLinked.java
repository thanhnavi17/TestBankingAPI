package banking.api.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table
public class EwalletLinked {
    @Id
    @Column
    private int trans_id;

    @Column
    private String account_number;

    @Column
    private String ewallet_id;

    @Column
    private Date linked_date;

    @Column
    private String token;

    @Column
    private String status;

    public int getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(int trans_id) {
        this.trans_id = trans_id;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getEwallet_id() {
        return ewallet_id;
    }

    public void setEwallet_id(String ewallet_id) {
        this.ewallet_id = ewallet_id;
    }

    public Date getLinked_date() {
        return linked_date;
    }

    public void setLinked_date(Date linked_date) {
        this.linked_date = linked_date;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
