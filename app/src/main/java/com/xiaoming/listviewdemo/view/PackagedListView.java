package com.xiaoming.listviewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xiaoming.listviewdemo.OnTicketItemListener;
import com.xiaoming.listviewdemo.R;
import com.xiaoming.listviewdemo.model.Chart;
import com.xiaoming.listviewdemo.model.Ticket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright	CoderDream's AndroidStudio
 * <p/>
 * All right reserved.
 * <p/>
 * @author xiaoming
 * @mail wangfeng.wf@warmdoc.com
 * @tags An overview of this file: 封装的ListView
 */
public class PackagedListView extends ListView {

    private final static int CASE_TAG_LAYOUT_HEADER = 1;
    private final static int CASE_TAG_LAYOUT_ALL = 2;
    private final static int CASE_TAG_LAYOUT_ORDER = 3;

    private Context mContext;
    private GoodsAdapter mAdapter;
    private Ticket mTicket;

    public PackagedListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public PackagedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public PackagedListView(Context context) {
        super(context);
        mContext = context;
    }

    // 该自定义控件只是重写了ListView的onMeasure方法，使其不会出现滚动条。
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    /**
     * 初始化
     */
    public void init(Ticket ticket){
        List<Chart> datas = ticket.getOrder_list();
        if(datas.size() > 1) // 订单数大于1，给数据里面添加全选item
            datas.add(new Chart("", "", ""));
        if(mAdapter == null)
            mAdapter = new GoodsAdapter(datas);
        mTicket = ticket;

        View header = LayoutInflater.from(mContext).inflate(R.layout.layout_header, null);
        header.setTag(CASE_TAG_LAYOUT_HEADER);
        TextView tv_tivketName = (TextView) header.findViewById(R.id.item_tv);
        tv_tivketName.setText(ticket.getTicket_title());

        header.setOnClickListener(new Listener(0));

        addHeaderView(header);
        setAdapter(mAdapter);
    }

    /**
     * 设置商品操作监听
     * @param listener 实现OnTicketItemListener监听
     */
    public void setOnTicketItemListener(OnTicketItemListener listener){
        mListener = listener;
    }

    /**
     * 商品列表数据适配器
     */
    class GoodsAdapter extends BaseAdapter{

        private List<Chart> mDatas;
        private Map<Integer, Boolean> checkedMap;

        GoodsAdapter(List<Chart> datas){
            mDatas = datas;
            checkedMap = new HashMap<Integer, Boolean>();
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public Object getItem(int i) {
            return mDatas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(mDatas.size() > 1) { // 数据中有全选数据时的处理
                if (i == mDatas.size() - 1){ // 设置全选布局
                    view = LayoutInflater.from(mContext).inflate(R.layout.layout_all, null);
                    view.setTag(CASE_TAG_LAYOUT_ALL);
                    ImageView cb_all = (ImageView) view.findViewById(R.id.item_cb_all);
                    if(checkedMap.get(i) == null)
                        cb_all.setTag(false);
                    else
                        cb_all.setTag(checkedMap.get(i));
                    if(!(Boolean) cb_all.getTag())
                        cb_all.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_icon_cb));
                    else
                        cb_all.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_icon_cb_checked));
                    view.setOnClickListener(new Listener(i));
                    return view;
                }
            }

            view = LayoutInflater.from(mContext).inflate(R.layout.layout_order, null);
            view.setTag(CASE_TAG_LAYOUT_ORDER);
            TextView tv_orderTitle = (TextView) view.findViewById(R.id.item_tv_order);
            tv_orderTitle.setText(mDatas.get(i).getChart_id());

            ImageView cb_order = (ImageView) view.findViewById(R.id.item_cb_order);
            if(checkedMap.get(i) == null)
                cb_order.setTag(false);
            else
                cb_order.setTag(checkedMap.get(i));

            if(!(Boolean) cb_order.getTag())
                cb_order.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_icon_cb));
            else
                cb_order.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_icon_cb_checked));

            view.setOnClickListener(new Listener(i));
            checkedMap.put(i, (Boolean) cb_order.getTag());
            return view;
        }

        /**
         * 更新选中状态
         * @param pos
         * @param checked
         */
        public void updateChecked(int pos, boolean checked){
            checkedMap.put(pos, checked);
            if(pos == mDatas.size() - 1){
                for (int i = 0; i < mDatas.size(); i++){
                    checkedMap.put(i, checked);
                }
            }else if(!checked){
                checkedMap.put(mDatas.size() - 1, false);
            }else{
                for (int i = 0; i < mDatas.size() - 1; i++){
                    if(checkedMap.get(i) == null || !checkedMap.get(i)){
                        checkedMap.put(mDatas.size() - 1, false);
                        break;
                    }
                    checkedMap.put(mDatas.size() - 1, true);
                }
            }
            notifyDataSetChanged();
        }
    }

    class Listener implements OnClickListener {

        private int mPosition;

        Listener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View view) {
            switch ((Integer) view.getTag()){
                case CASE_TAG_LAYOUT_ALL:
                    ImageView cb_all = (ImageView) view.findViewById(R.id.item_cb_all);
                    if((Boolean) cb_all.getTag())
                        cb_all.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_icon_cb));
                    else
                        cb_all.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_icon_cb_checked));
                    mAdapter.updateChecked(mPosition, !(Boolean) cb_all.getTag());

                    if(mListener != null) {
                        mListener.onAllClick(view, mTicket, !(Boolean) cb_all.getTag());
                    }
                    break;
                case CASE_TAG_LAYOUT_ORDER:
                    ImageView cb_order = (ImageView) view.findViewById(R.id.item_cb_order);
                    if((Boolean) cb_order.getTag())
                        cb_order.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_icon_cb));
                    else
                        cb_order.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_icon_cb_checked));
                    mAdapter.updateChecked(mPosition, !(Boolean) cb_order.getTag());

                    if(mListener != null)
                        mListener.onChartClick(view, mPosition, mTicket, !(Boolean) cb_order.getTag());
                    break;
                case CASE_TAG_LAYOUT_HEADER:
                    if(mListener != null)
                        mListener.onTicketClick(view, mTicket);
                    break;
            }
        }
    }

    private OnTicketItemListener mListener;

}
