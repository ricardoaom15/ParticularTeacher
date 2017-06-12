package com.creatic.particularteacherprototype.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.creatic.particularteacherprototype.databinding.TemplateSubjectSpinnerBinding;
import com.creatic.particularteacherprototype.models.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RicardoAndrés on 12/06/2017.
 */

public class SubjectSpinnerAdapter extends BaseAdapter {

    Context context;
    List<Subject> subjectList;

    public SubjectSpinnerAdapter(Context context, List<Subject> subjectList) {
        this.context = context;
        this.subjectList = new ArrayList<>();
        this.subjectList.addAll(subjectList);
    }

    @Override
    public int getCount() {
        return subjectList.size();
    }

    @Override
    public Object getItem(int i) {
        return subjectList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TemplateSubjectSpinnerBinding binding;
        if(view == null){
            binding = TemplateSubjectSpinnerBinding.inflate(LayoutInflater.from(context), viewGroup, false);
            view = binding.getRoot();
        }else{
            binding = (TemplateSubjectSpinnerBinding) view.getTag();
        }

        binding.setSubject((Subject) getItem(i));
        view.setTag(binding);

        return view;
    }
}
