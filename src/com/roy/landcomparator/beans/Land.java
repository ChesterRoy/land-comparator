package com.roy.landcomparator.beans;

public class Land {
    double NKMSTART; //Начало участка, км
    double NKMEND; //Конец участка, км
    double NKRPLANYEAR; //"Планируемый год проведения К(с учетом предложений ГТО - 09.2014)"
    String CPIPENAME; //Наименование в Инфотех

    public Land(double NKMSTART, double NKMEND, double NKRPLANYEAR, String CPIPENAME) {
        this.NKMSTART = NKMSTART;
        this.NKMEND = NKMEND;
        this.NKRPLANYEAR = NKRPLANYEAR;
        this.CPIPENAME = CPIPENAME;
    }

    public double getNKMSTART() {
        return NKMSTART;
    }

    public void setNKMSTART(double NKMSTART) {
        this.NKMSTART = NKMSTART;
    }

    public double getNKMEND() {
        return NKMEND;
    }

    public void setNKMEND(double NKMEND) {
        this.NKMEND = NKMEND;
    }

    public double getNKRPLANYEAR() {
        return NKRPLANYEAR;
    }

    public void setNKRPLANYEAR(double NKRPLANYEAR) {
        this.NKRPLANYEAR = NKRPLANYEAR;
    }

    public String getCPIPENAME() {
        return CPIPENAME;
    }

    public void setCPIPENAME(String CPIPENAME) {
        this.CPIPENAME = CPIPENAME;
    }

    @Override
    public String toString() {
        return "Land{"
                + ", NKMSTART=" + NKMSTART
                + ", NKMEND=" + NKMEND
                + ", NKRPLANYEAR=" + NKRPLANYEAR
                + ", CPIPENAME=" + CPIPENAME + '}';
    }

}
