package ir.maktab.bank.domain;

import ir.maktab.bank.base.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = Log.TABLE_NAME)
public class Log extends BaseEntity<Long> {

    public static final String TABLE_NAME = "log";

    private String fromCardNumber;

    private Long initialCredit;

    private Long logAmountChange;

    private Long afterLogCredit;

    private Date logDate;

    private String toCardNumber;

    public Log() {
    }

    public Log(String fromCardNumber, Long initialCredit,
               Long logAmountChange, Long afterLogCredit, Date logDate, String toCardNumber) {
        this.fromCardNumber = fromCardNumber;
        this.initialCredit = initialCredit;
        this.logAmountChange = logAmountChange;
        this.afterLogCredit = afterLogCredit;
        this.logDate = logDate;
        this.toCardNumber = toCardNumber;
    }
}
