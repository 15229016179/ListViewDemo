package com.xiaoming.listviewdemo;

import android.view.View;

import com.xiaoming.listviewdemo.model.Ticket;

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
public interface OnTicketItemListener {

    /**
     * 商品点击
     */
    void onTicketClick(View view, Ticket ticket);

    /**
     * 订单item点击
     */
    void onChartClick(View view, int position, Ticket ticket, boolean isChecked);

    /**
     * 全选点击
     */
    void onAllClick(View view, Ticket ticket, boolean isChecked);
}
