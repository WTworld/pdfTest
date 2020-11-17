package com.u_anywhere.model;

import com.u_anywhere.base.BaseVo;
import javax.persistence.*;

@Table(name = "dbo.TEST_PrintWaveNo")
public class TestPrintwaveno extends BaseVo {
    @Id
    @Column(name = "WaveNo")
    private String waveno;

    @Column(name = "PrintCount")
    private Integer printcount;

    @Column(name = "DBCount")
    private Integer dbcount;

    /**
     * @return WaveNo
     */
    public String getWaveno() {
        return waveno;
    }

    /**
     * @param waveno
     */
    public void setWaveno(String waveno) {
        this.waveno = waveno;
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