package com.example.mmt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PublicacionAdapter extends RecyclerView.Adapter<PublicacionAdapter.ViewHolder> {

    private List<Publicacion> publicacionesList;

    public PublicacionAdapter(List<Publicacion> publicacionesList) {
        this.publicacionesList = publicacionesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Publicacion publicacion = publicacionesList.get(position);

        holder.titleTextView.setText(publicacion.getTitle());
        holder.genereTextView.setText(publicacion.getGenre());
        holder.locationTextView.setText(publicacion.getLocation());
        holder.contentTextView.setText(publicacion.getContent());
        holder.creationDateTextView.setText(publicacion.getCreationDate().toString());
    }

    @Override
    public int getItemCount() {
        return publicacionesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView genereTextView;
        TextView locationTextView;
        TextView contentTextView;
        TextView creationDateTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            genereTextView = itemView.findViewById(R.id.genereTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
            creationDateTextView = itemView.findViewById(R.id.creationDateTextView);
        }
    }
}
