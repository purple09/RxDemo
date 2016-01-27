package com.purple.rxdemo;

import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.subjects.PublishSubject;

public class MainAdapter extends RecyclerView.Adapter {
    private List<Pair<String, Class>> datas;
    public PublishSubject<Integer> mSubject;

    public MainAdapter(List<Pair<String, Class>> datas) {
        this.datas = datas;
        mSubject = PublishSubject.create();
    }

    public int getItemCount() {
        return this.datas.size();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder mHolder, int position) {
        MainViewHolder holder = (MainViewHolder) mHolder;
        holder.tvItem.setText((CharSequence) ((Pair) this.datas.get(position)).first);
        holder.itemView.setOnClickListener(v -> mSubject.onNext(position));
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        return new MainViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup, false));
    }

    class MainViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_item)
        TextView tvItem;

        public MainViewHolder(View itemview) {
            super(itemview);
            ButterKnife.bind(this, itemview);
        }
    }
}