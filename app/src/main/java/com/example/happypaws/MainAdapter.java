package com.example.happypaws;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


import de.hdodenhof.circleimageview.CircleImageView;

public class MainAdapter extends FirebaseRecyclerAdapter<MainModel,MainAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull MainModel model) {
        holder.name.setText(model.getName());
        holder.contact.setText(model.getContact());
        holder.breed.setText(model.getBreed());
        holder.address.setText(model.getAddress());
        holder.petimage.setText(model.getPetimage());

        Glide.with(holder.img.getContext())
            .load(model.getImage())
                .placeholder(com.firebase.ui.auth.R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(com.firebase.ui.auth.R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.img);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder
            {
                CircleImageView img;
                TextView name,contact,breed,address,petimage;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView) itemView.findViewById(R.id.img1);
            name =(TextView) itemView.findViewById(R.id.nametext);
            contact =(TextView) itemView.findViewById(R.id.contacttext);
            breed =(TextView) itemView.findViewById(R.id.breedtext);
            address =(TextView) itemView.findViewById(R.id.addresstext);
            petimage =(TextView) itemView.findViewById(R.id.petnametext);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "Contact The Owner for Adopting", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
