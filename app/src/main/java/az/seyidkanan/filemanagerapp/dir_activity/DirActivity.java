package az.seyidkanan.filemanagerapp.dir_activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import az.seyidkanan.filemanagerapp.R;
import az.seyidkanan.filemanagerapp.interfaces.RVFileClickListener;
import az.seyidkanan.filemanagerapp.pojo.DirPojo;
import az.seyidkanan.filemanagerapp.adapters.RVFileApater;

public class DirActivity extends AppCompatActivity implements DirContracter.View {

    private RecyclerView recyclerView;
    private RVFileApater rvFileApater;
    private List<DirPojo> files;

    private DirContracter.Presenter presenter;

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_circular);

        initAdapter();
        new DirPresenter(this);
        presenter.showFileList();

        presenter.addRootDirToSP();
    }

    private void initAdapter() {
        files = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleView_files);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);

        rvFileApater = new RVFileApater(this, files, new RVFileClickListener() {
            @Override
            public void onItemClick(DirPojo file) {
                presenter.onDirClick(file);
            }
        });
        recyclerView.setAdapter(rvFileApater);
    }

    @Override
    public void onUpdateDirList(List<DirPojo> fileList) {
        files.clear();
        files.addAll(fileList);
        rvFileApater.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void customOnBackPress() {
        super.onBackPressed();
    }

    @Override
    public void setPresenter(DirContracter.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }

}
