package com.example.assessment.views.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assessment.R;
import com.example.assessment.databinding.LayoutRowArticleBinding;
import com.example.assessment.network.models.Result;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.Holder>{

    private List<Result> results;
    private LayoutInflater layoutInflater;

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        LayoutRowArticleBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.layout_row_article, parent, false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.bind(results.get(i));
    }

    @Override
    public int getItemCount() {
        if(results == null) return 0;
        return results.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        LayoutRowArticleBinding layoutBinding;

        public Holder(@NonNull LayoutRowArticleBinding binding) {
            super(binding.getRoot());
            this.layoutBinding = binding;
        }

        public void bind(Result result){
            layoutBinding.setResult(result);
            layoutBinding.txtTitle.setText(result.title);
        }

    }

    public void setResults(List<Result> results){
        this.results = results;
    }


}
