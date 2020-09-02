package com.example.myapplication;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class CompanyViewHolder extends GroupViewHolder {
    private TextView mtextView;
    private ImageView arrow;
    public CompanyViewHolder(View itemView) {
        super(itemView);

        mtextView = itemView.findViewById(R.id.textViewww);
        arrow = itemView.findViewById(R.id.arrow);
    }

    public void bind(Company company){
        mtextView.setText(company.getTitle());
    }
    @Override
    public void expand(){
        animateExpand();
    }

    @Override
    public void collapse(){
        animateCollapse();
    }

    private void animateExpand(){
        RotateAnimation rotateAnimation = new RotateAnimation(360,90, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(300);
        rotateAnimation.setFillAfter(true);
        arrow.setAnimation(rotateAnimation);
    }

    private void animateCollapse(){
        RotateAnimation rotateAnimation = new RotateAnimation(90,360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(300);
        rotateAnimation.setFillAfter(true);
        arrow.setAnimation(rotateAnimation);
    }
}
