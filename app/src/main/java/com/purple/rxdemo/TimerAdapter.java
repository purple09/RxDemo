package com.purple.rxdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

public class TimerAdapter extends RecyclerView.Adapter {
    private CompositeSubscription compositeSubscription;
    private List<Integer> datas;
    public Observable<Long> observable;

    public TimerAdapter(List<Integer> paramList) {
        this.datas = paramList;
        this.observable = Observable.interval(1L, TimeUnit.SECONDS);
        this.compositeSubscription = new CompositeSubscription();
    }

    public int getItemCount() {
        return this.datas.size();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        TimerViewHolder mHolder = (TimerViewHolder) viewHolder;
        mHolder.tvItem.setTag(this.datas.get(position));
        mHolder.tvItem.setText("倒计时：" + this.datas.get(position));
        Subscription subscription = this.observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(value -> {
                    int count = (int) mHolder.tvItem.getTag();
                    count--;
                    if (count > 0) {
                        mHolder.tvItem.setTag(count);
                        mHolder.tvItem.setText("倒计时：" + count);
                    }
                });
        this.compositeSubscription.add(subscription);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        return new TimerViewHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(R.layout.recyclerview_item, paramViewGroup, false));
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (this.compositeSubscription != null)
            this.compositeSubscription.unsubscribe();
    }

    class TimerViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_item)
        TextView tvItem;

        public TimerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}