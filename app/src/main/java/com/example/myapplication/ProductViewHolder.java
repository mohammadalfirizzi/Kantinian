package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class ProductViewHolder extends ChildViewHolder {
    private TextView mtextView;
    public ProductViewHolder(View itemView) {
        super(itemView);
        mtextView = itemView.findViewById(R.id.textViewww);

    }

    public void bind(Product product){
        mtextView.setText(product.nama);
    }
}
