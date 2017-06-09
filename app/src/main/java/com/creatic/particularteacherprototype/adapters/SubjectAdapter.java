package com.creatic.particularteacherprototype.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.creatic.particularteacherprototype.R;
import com.creatic.particularteacherprototype.databinding.TemplateSubjectItemBinding;
import com.creatic.particularteacherprototype.models.Subject;

import java.util.List;

/**
 * Created by RicardoAndrés on 07/06/2017.
 */

public class SubjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    public interface OnItemClick{
        void onSubjectClick(View v);
    }

    Context context;
    OnItemClick onItemClick;
    List<Subject> subjectList;
    View conView;

    public SubjectAdapter(Context context, OnItemClick onItemClick, List<Subject> subjectList) {
        this.context = context;
        this.onItemClick = onItemClick;
        this.subjectList = subjectList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.template_subject_item, null);
        RecyclerView.ViewHolder viewHolder = new SubjectViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Subject subject = subjectList.get(position);
        SubjectViewHolder viewHolder = (SubjectViewHolder) holder;
        viewHolder.binding.setSubject(subject);
        conView = viewHolder.binding.getRoot();
        conView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onItemClick.onSubjectClick(v);
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    static class SubjectViewHolder extends RecyclerView.ViewHolder{

        TemplateSubjectItemBinding binding;

        public SubjectViewHolder(View itemView) {
            super(itemView);
            binding = TemplateSubjectItemBinding.bind(itemView);
        }
    }
}
