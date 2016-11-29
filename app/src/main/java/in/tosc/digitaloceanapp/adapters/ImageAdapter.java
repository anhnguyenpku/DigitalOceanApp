package in.tosc.digitaloceanapp.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import in.tosc.digitaloceanapp.R;
import in.tosc.doandroidlib.objects.Image;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private List<Image> imageList;
    private int position;
    private Context context;

    public ImageAdapter(List<Image> items, Context context)
    {
        imageList = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_image_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.imageName.setText(imageList.get(position).getName());
//        this.position = holder.getAdapterPosition();
        holder.imageDistribution.setText(imageList.get(position).getDistribution());
        switch (imageList.get(position).getDistribution()){
            case "CoreOS":
                holder.imageImage.setBackground(ContextCompat.getDrawable(context,R.drawable.coreos));
                break;
            case "FreeBSD":
                holder.imageImage.setBackground(ContextCompat.getDrawable(context,R.drawable.freebsd));
                break;
            case "Fedora":
                holder.imageImage.setBackground(ContextCompat.getDrawable(context,R.drawable.fedora));
                break;
            case "Debian":
                holder.imageImage.setBackground(ContextCompat.getDrawable(context,R.drawable.debian));
                break;
            case "CentOS":
                holder.imageImage.setBackground(ContextCompat.getDrawable(context,R.drawable.centos));
                break;
            case "Ubuntu":
                holder.imageImage.setBackground(ContextCompat.getDrawable(context,R.drawable.ubuntu));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageImage;
        TextView imageName;
        TextView imageDistribution;

        ViewHolder(View view) {
            super(view);
            imageImage = (ImageView) view.findViewById(R.id.imageImage);
            imageName = (TextView) view.findViewById(R.id.imageName);
            imageDistribution = (TextView) view.findViewById(R.id.imageDistribution);
        }
    }
}
