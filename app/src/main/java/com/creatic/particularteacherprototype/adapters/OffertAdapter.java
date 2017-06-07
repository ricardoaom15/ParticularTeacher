package com.creatic.particularteacherprototype.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.creatic.particularteacherprototype.R;
import com.creatic.particularteacherprototype.databinding.TemplateOfferItemBinding;
import com.creatic.particularteacherprototype.models.Offer;

import java.util.List;

/**
 * Created by RicardoAndrés on 07/06/2017.
 */

public class OffertAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    public interface OnItemClick{
        void onClick(View v);
    }

    Context context;
    OnItemClick onItemClick;
    List<Offer> offerList;
    View conView;

    public OffertAdapter(Context context, OnItemClick onItemClick, List<Offer> offerList) {
        this.context = context;
        this.onItemClick = onItemClick;
        this.offerList = offerList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.template_offer_item, null);
        RecyclerView.ViewHolder viewHolder = new OfferViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Offer offer = offerList.get(position);
        OfferViewHolder viewHolder = (OfferViewHolder) holder;
        viewHolder.binding.setOffert(offer);
        conView = viewHolder.binding.getRoot();
        conView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onItemClick.onClick(v);
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    static class OfferViewHolder extends RecyclerView.ViewHolder{

        TemplateOfferItemBinding binding;

        public OfferViewHolder(View itemView) {
            super(itemView);
            binding = TemplateOfferItemBinding.bind(itemView);
        }
    }
}
