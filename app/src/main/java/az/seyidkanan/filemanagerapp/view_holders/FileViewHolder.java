package az.seyidkanan.filemanagerapp.view_holders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import az.seyidkanan.filemanagerapp.R;
import az.seyidkanan.filemanagerapp.interfaces.RVFileClickListener;
import az.seyidkanan.filemanagerapp.pojo.DirPojo;

public class FileViewHolder extends RecyclerView.ViewHolder {

    private TextView textViewFileName;
    private TextView textViewFileSize;
    private TextView textViewFileCreatedDate;
    private ImageView imageViewIcon;
    private ConstraintLayout constraintLayout;

    private Context context;

    public FileViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        textViewFileName = itemView.findViewById(R.id.textViewFileName);
        textViewFileSize = itemView.findViewById(R.id.textView_file_size);
        textViewFileCreatedDate = itemView.findViewById(R.id.textView_created_date);
        imageViewIcon = itemView.findViewById(R.id.imageViewFileIcon);
        constraintLayout = itemView.findViewById(R.id.rv_view_element);
    }

    public void onBind(final DirPojo file, final RVFileClickListener rvFileClickListener) {
        textViewFileCreatedDate.setText(file.getDate());

        if (file != null) {
            if (file.isDirectory()) {
                imageViewIcon.setImageResource(R.drawable.ic_fol);
                textViewFileSize.setText("Directory");
            } else {
                imageViewIcon.setImageResource(R.drawable.ic_doc);
                textViewFileSize.setText(Formatter.formatShortFileSize(context, file.getLength()));
            }
            textViewFileName.setText(file.getName());
            constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rvFileClickListener.onItemClick(file);
                }
            });
        }
    }

}
