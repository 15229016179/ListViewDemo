package com.xiaoming.listviewdemo.model;

import java.util.List;

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
public class Ticket {

    private String ticket_id;
    private String ticket_title;
    private List<Chart> order_list;

    public Ticket(String ticket_id, String ticket_title, List<Chart> order_list) {
        this.ticket_id = ticket_id;
        this.ticket_title = ticket_title;
        this.order_list = order_list;
    }

    public String getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(String ticket_id) {
        this.ticket_id = ticket_id;
    }

    public List<Chart> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<Chart> order_list) {
        this.order_list = order_list;
    }

    public String getTicket_title() {
        return ticket_title;
    }

    public void setTicket_title(String ticket_title) {
        this.ticket_title = ticket_title;
    }
}
