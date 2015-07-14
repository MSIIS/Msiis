package com.web.soupe.roll;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "M_ROLL", catalog = "apple")
public class Roll implements Serializable {

    private static final long serialVersionUID = -7268159051330064687L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 期号
     */
    @Column(name = "iISSUE_NUM", nullable = false)
    private String issueNum;

    @Column(name = "RED", nullable = false)
    private String red;

    @Column(name = "BLUE", nullable = false)
    private String blue;

    @Column(name = "RED_SUM")
    private int redSum;

    @Column(name = "data_source_type")
    private String dataSourceType;

    public Roll() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIssueNum() {
        return issueNum;
    }

    public void setIssueNum(String issueNum) {
        this.issueNum = issueNum;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public String getBlue() {
        return blue;
    }

    public void setBlue(String blue) {
        this.blue = blue;
    }

    public int getRedSum() {
        return redSum;
    }

    public void setRedSum(int redSum) {
        this.redSum = redSum;
    }

    public String getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(String dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

}
