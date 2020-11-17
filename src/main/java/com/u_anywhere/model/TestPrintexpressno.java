package com.u_anywhere.model;

import com.u_anywhere.base.BaseVo;
import javax.persistence.*;

@Table(name = "dbo.TEST_PrintExpressNo")
public class TestPrintexpressno extends BaseVo {
    @Id
    @Column(name = "ExpressNo")
    private String expressno;

    @Column(name = "PrintCount")
    private Integer printcount;

    @Column(name = "DBCount")
    private Integer dbcount;

    /**
     * @return ExpressNo
     */
    public String getExpressno() {
        return expressno;
    }

    /**
     * @param expressno
     */
    public void setExpressno(String expressno) {
        this.expressno = expressno;
    }

    /**
     * @return PrintCount
     */
    public Integer getPrintcount() {
        return printcount;
    }

    /**
     * @param printcount
     */
    public void setPrintcount(Integer printcount) {
        this.printcount = printcount;
    }

    /**
     * @return DBCount
     */
    public Integer getDbcount() {
        return dbcount;
    }

    /**
     * @param dbcount
     */
    public void setDbcount(Integer dbcount) {
        this.dbcount = dbcount;
    }
}