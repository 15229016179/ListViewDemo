package com.xiaoming.listviewdemo.model;

/**
 * Copyright	${year}	CoderDream's AndroidStudio
 * <p/>
 * All right reserved.
 * <p/>
 * Created on ${date} ${time}
 * <p/>
 * Update on ${date} ${time}
 *
 * @author xiaoming
 * @mail wangfeng.wf@warmdoc.com
 * @tags An overview of this file: ${tags}
 */
public class Chart {

    private String chart_id;
    private String buyer_id;
    private String ticket_id;

    public Chart(String chart_id, String buyer_id, String ticket_id) {
        this.chart_id = chart_id;
        this.buyer_id = buyer_id;
        this.ticket_id = ticket_id;
    }

    public String getChart_id() {
        return chart_id;
    }

    public void setChart_id(String chart_id) {
        this.chart_id = chart_id;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }
}
