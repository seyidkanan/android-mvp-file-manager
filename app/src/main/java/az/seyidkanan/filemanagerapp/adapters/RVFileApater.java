package az.seyidkanan.filemanagerapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import az.seyidkanan.filemanagerapp.R;
import az.seyidkanan.filemanagerapp.interfaces.RVFileClickListener;
import az.seyidkanan.filemanagerapp.pojo.DirPojo;
import az.seyidkanan.filemanagerapp.view_holders.FileViewHolder;

public class RVFileApater extends RecyclerView.Adapter<FileViewHolder> {

    private List<DirPojo> files;

    private RVFileClickListener rvFileClickListener;

    private Context context;

    public RVFileApater(Context context, List<DirPojo> files, RVFileClickListener rvFileClickListener) {
        this.context = context;
        this.files = files;
        this.rvFileClickListener = rvFileClickListener;
    }

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rv_adapter_list_element,
                        viewGroup,
                        false);
        return new FileViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder fileViewHolder, int i) {
        fileViewHolder.onBind(files.get(i), rvFileClickListener);
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

}
