package spb.academy.android.ex_3_app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by kvarg-ru on 11.04.2018.
 */

public class GaleryAdapter extends RecyclerView.Adapter<GaleryAdapter.ImageHolder> {

    private final List<Cat> catList;
    private final OnItemClickListener onItemClickListener;

    private final static int POSITION_KEY = 0;
    private final static int DATA_KEY = 1;

    public void removeItem(Cat cat) {

        int position = catList.indexOf(cat);
        if (position >= 0) {
            catList.remove(position);
            notifyItemRemoved(position);
        }

    }

    public void removeItem(RecyclerView.ViewHolder viewHolder) {
        int position = viewHolder.getAdapterPosition();
        catList.remove(position);
        notifyItemRemoved(position);
    }

    public GaleryAdapter(List<Cat> catList, OnItemClickListener onItemClickListener) {
        this.catList = catList;
        this.onItemClickListener = onItemClickListener;
    }

    private final View.OnClickListener internalClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Cat cat = (Cat) view.getTag();
            int position = catList.indexOf(cat);
            onItemClickListener.onClick(cat, position);
            //onItemClickListener.onClick((Cat) view.getTag(R.string.DATA_KEY), (int) view.getTag(R.string.POSITION_KEY));
        }
    };

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_layout, parent, false);
        return new ImageHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {

        Cat cat = catList.get(position);
        //holder.image.setTag(R.string.POSITION_KEY, position);
        //holder.image.setTag(R.string.DATA_KEY, cat);
        holder.image.setTag(cat);
        Picasso.get().load(cat.getUrl()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return catList.size();
    }

    public interface OnItemClickListener {
        public void onClick(Cat cat, int position);
    }

    class ImageHolder extends RecyclerView.ViewHolder {

        public final ImageView image;

        public ImageHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_image);
            itemView.setOnClickListener(internalClickListener);
        }
    }
}
