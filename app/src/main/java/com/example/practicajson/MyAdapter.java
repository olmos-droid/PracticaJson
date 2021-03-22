package com.example.practicajson;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public static final String TAG = "TAG";
    private Context mContext;
    private List<String> peakNames;
    private List<String> peakHeight;
    private List<String> peakUrl;
    private List<String> peakCountry;

    /**
     *
     * @param context
     * @param peakNames
     * @param peakHeight
     * @param peakUrl
     * @param peakCountry
     */

    public MyAdapter(Context context, List<String> peakNames, List<String> peakHeight, List<String> peakUrl, List<String> peakCountry) {
        this.mContext = context;
        this.peakNames = peakNames;
        this.peakHeight = peakHeight;
        this.peakUrl = peakUrl;
        this.peakCountry = peakCountry;
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.peak_layout, parent, false);
        return new MyViewHolder(view);
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Picasso.get().load(peakUrl.get(position)).into(holder.imageUrl);
        Log.d(TAG, "onBindViewHolder: Url image " + position + " " + peakUrl.get(position));


        holder.textName.setText(peakNames.get(position));
        holder.textHeight.setText(peakHeight.get(position));
        holder.textCountry.setText(peakCountry.get(position));

    }

    /**
     * Retorna el numero de items que te el array
     * @return
     */
    @Override
    public int getItemCount() {
        return peakNames.size();

    }

    /**
     * Clss MyviewÇHolder
     */

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textHeight;
        ImageView imageUrl;
        TextView textCountry;

        /**
         *
         * @param itemView
         */
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textHeight = itemView.findViewById(R.id.textHeight);
            imageUrl = itemView.findViewById(R.id.imageUrl);
            textCountry = itemView.findViewById(R.id.textCountry);
        }
    }
}
