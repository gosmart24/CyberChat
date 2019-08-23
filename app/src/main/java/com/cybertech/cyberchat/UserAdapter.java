package com.cybertech.cyberchat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;


public class UserAdapter extends FirebaseRecyclerAdapter<UserModel, UserAdapter.Holder > {


    public UserAdapter(Context context, Query ref) {
        super(UserModel.class, R.layout.user_card, Holder.class, ref);
    }

    @Override
    protected void populateViewHolder(UserAdapter.Holder viewHolder, UserModel model, int position) {

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    public class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }


    }
}
