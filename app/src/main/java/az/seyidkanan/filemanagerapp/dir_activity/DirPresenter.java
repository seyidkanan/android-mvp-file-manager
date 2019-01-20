package az.seyidkanan.filemanagerapp.dir_activity;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import az.seyidkanan.filemanagerapp.R;
import az.seyidkanan.filemanagerapp.data.SPManager;
import az.seyidkanan.filemanagerapp.pojo.DirPojo;

public class DirPresenter implements DirContracter.Presenter {

    private DirContracter.View view;

    private DirModel dirModel;

    private SPManager spManager;

    public DirPresenter(DirContracter.View view) {
        this.view = view;
        this.view.setPresenter(this);
        dirModel = new DirModel();
        spManager = new SPManager(view.getContext());
    }

    @Override
    public void onDirClick(DirPojo file) {
        if (view != null) {
            try {
                view.showProgressBar();
                if (file.isDirectory()) {
                    List<DirPojo> files = dirModel.getDirListFromThisPath(file.getAbsolutePath());
                    if (files != null) {
                        if (files.size() > 0) {
                            Log.e("kanan", "list=" + spManager.getDirList());
                            List<DirPojo> dirPojoList = spManager.getDirList();
                            dirPojoList.add(file);
                            spManager.setDirList(dirPojoList);

                            view.onUpdateDirList(files);
                        } else {
                            view.showErrorMessage(view.getContext().getString(R.string.empty_dir));
                        }
                    } else {
                        view.showErrorMessage(view.getContext().getString(R.string.empty_dir));
                    }
                } else {
                    view.showErrorMessage(view.getContext().getString(R.string.this_is_file));
                }
            } catch (Exception e) {
                view.showErrorMessage(e.getMessage());
            }
            view.hideProgress();
        }
    }


    @Override
    public void showFileList() {
        if (view != null) {
            try {
                view.showProgressBar();
                if (dirModel != null) {
                    List<DirPojo> files = dirModel.getDirList();
                    if (files != null) {
                        if (files.size() > 0) {
                            view.onUpdateDirList(files);
                        } else {
                            view.showErrorMessage(view.getContext().getString(R.string.empty_dir));
                        }
                    } else {
                        view.showErrorMessage(view.getContext().getString(R.string.empty_dir));
                    }
                }
            } catch (Exception e) {
                view.showErrorMessage(e.getMessage());
            }
            view.hideProgress();
        }
    }

    @Override
    public void onBackPressed() {
        SPManager spManager = new SPManager(view.getContext());

        List<DirPojo> dirPojoList = spManager.getDirList();
        int size = dirPojoList.size();

        if (size == 0) {
            view.customOnBackPress();
        } else if (size == 1) {
            dirPojoList.remove(dirPojoList.get(size - 1));
            view.customOnBackPress();
        } else {
//            Log.e("kanan", "data remove==>>" + dirPojoListBackManage.get(size - 1).getAbsolutePath());
            dirPojoList.remove(dirPojoList.get(size - 1));
            size = dirPojoList.size();
//            Log.e("kanan", "data get==>>" + dirPojoListBackManage.get(size - 1).getAbsolutePath());
            onDirClick(dirPojoList.get(size - 1));
        }
        spManager.setDirList(dirPojoList);
    }

    @Override
    public void addRootDirToSP() {
        List<DirPojo> dirPojoListBackManage = new ArrayList<>();
        DirPojo dirPojo = new DirPojo();
        dirPojo.setAbsolutePath("/");
        dirPojo.setDirectory(true);
        dirPojoListBackManage.add(dirPojo);
        SPManager spManager = new SPManager(view.getContext());
        spManager.setDirList(dirPojoListBackManage);
    }

}
