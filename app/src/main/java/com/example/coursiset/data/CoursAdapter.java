package com.example.coursiset.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.coursiset.R;
import com.example.coursiset.model.Cours;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CoursAdapter extends RecyclerView.Adapter<CoursAdapter.ViewHolder>{
    @NonNull
   // private ArrayList<Cours> CoursarrayList;//list of cours
    private coursdata coursdata=new coursdata();

private View.OnClickListener onClickListener;
public OnItemClickListener itemClickListener;
    @Override
    public CoursAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cours_row= LayoutInflater.from(parent.getContext()).inflate(R.layout.cours_ow,parent,false );
        //inflate the layout cours_ow
        //return ViewHolder that i creat class viewholder
        return new ViewHolder(cours_row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//bind data n view
    Context context=holder.courstitle.getContext();
        Cours cours=coursdata.coursArrayList().get(position);
holder.courstitle.setText(cours.getcoursname());


//holder.courseImage.setImageBitmap(BitmapFactory.decodeByteArray(holder.authimage));
 Glide.with(holder.courstitle.getContext()).load(cours.getImageResourcidaut(context)).into(holder.authimage);
       Glide.with(holder.courstitle.getContext()).load(cours.getImageResourcid(holder.courstitle.getContext())).into(holder.courseImage);
//Picasso.with(holder.courstitle.getContext()).load(cours.getImageResourcid(context)).into(holder.courseImage);
//the photo of withch row that we are fetshing
//Bitmap photo= BitmapFactory.decodeResource(context.getResources(),cours.getImageResourcid(context));

    }

    @Override
    public int getItemCount() {
        return coursdata.coursArrayList().size();//CoursarrayList.size();
    }
    public void setOnClickListener(OnItemClickListener itemClickListener){
this.itemClickListener=itemClickListener;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //inisaliation layout cour_ow
        public TextView courstitle;
        public ImageView courseImage;
        public CircleImageView authimage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //register our viewto recive events;
            itemView.setOnClickListener(this);
            courstitle=itemView.findViewById(R.id.courstxtid);
            courseImage=itemView.findViewById(R.id.coursimaageVid);
            authimage=itemView.findViewById(R.id.authimageid);
        }


        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v,getAdapterPosition());

        }
    }
    public interface OnItemClickListener{
        void onItemClick(View v,int position);
    }
}
