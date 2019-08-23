package com.cybertech.cyberchat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

import de.hdodenhof.circleimageview.CircleImageView;


public class UserAdapter extends FirebaseRecyclerAdapter<UserModel, UserAdapter.Holder> {

    Context context;

    public UserAdapter(Context context, Query ref) {
        super(UserModel.class, R.layout.user_card, Holder.class, ref);
        this.context = context;
    }

    @Override
    protected void populateViewHolder(UserAdapter.Holder viewHolder, final UserModel model, int position) {
        viewHolder.setUserDetails(model);
        final TinyDB tinyDB = new TinyDB(context);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessageActivity.class);
                tinyDB.putObject("User",model);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card, parent, false);
        return new UserAdapter.Holder(itemView);
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView username_TV;
        CircleImageView circleImageView;
        CardView cardView;


        public Holder(View itemView) {
            super(itemView);
            username_TV = itemView.findViewById(R.id.username_card);
            circleImageView = itemView.findViewById(R.id.profile_image_card);
            cardView = itemView.findViewById(R.id.cardView_card);

        }

        public void setUserDetails(UserModel model) {
            username_TV.setText(model.getUsername());

            ColorGenerator generator = ColorGenerator.DEFAULT;
            int mycolor = generator.getRandomColor();

            TextDrawable.IBuilder builder = TextDrawable.builder().beginConfig().withBorder(4).endConfig().round();
            TextDrawable drawable = builder.build(model.getName().substring(0, 1).toUpperCase(), mycolor);

            circleImageView.setImageDrawable(drawable);
            // circleImageView
        }


    }
}
