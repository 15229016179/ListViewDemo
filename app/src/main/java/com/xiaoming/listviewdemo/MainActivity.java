package com.xiaoming.listviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.xiaoming.listviewdemo.model.Chart;
import com.xiaoming.listviewdemo.model.Ticket;
import com.xiaoming.listviewdemo.view.PackagedListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CrashHandler.getInstance().init();

        init();
    }

    private void init() {

        LinearLayout linear_root = (LinearLayout) findViewById(R.id.linear_root);

        for (int i = 0; i < 5; i++){
            PackagedListView packagedListView = (PackagedListView) LayoutInflater.from(this).inflate(R.layout.layout_listview, null);
            List<Chart> datas = new ArrayList<Chart>();
            for (int j = 0; j < (i + 1); j++){
                Chart chart = new Chart("订单" + j, "买家" + j + i, "商品" + i);
                datas.add(chart);
            }
            packagedListView.init(new Ticket("商品" + i, "商品" + i, datas));
            packagedListView.setOnTicketItemListener(new OnTicketItemListener(){

                @Override
                public void onTicketClick(View view, Ticket ticket) {
                    Log.i("TAG", "点击" + ticket.getTicket_id());
                }

                @Override
                public void onChartClick(View view, int position, Ticket ticket, boolean isChecked) {
                    Log.i("TAG", "点击" + ticket.getOrder_list().get(position).getChart_id() + isChecked);
                }

                @Override
                public void onAllClick(View view, Ticket ticket, boolean isChecked) {
                    Log.i("TAG", "全选" + ticket.getTicket_id() + isChecked);
                }
            });
            linear_root.addView(packagedListView);
        }
    }
}
