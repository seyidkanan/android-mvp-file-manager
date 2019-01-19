package az.seyidkanan.filemanagerapp.dir_activity;

import java.io.File;
import java.util.List;

import az.seyidkanan.filemanagerapp.R;
import az.seyidkanan.filemanagerapp.pojo.DirPojo;

public class DirPresenter implements DirContracter.Presenter {

    private DirContracter.View view;

    private DirModel dirModel;

    public DirPresenter(DirContracter.View view) {
        this.view = view;
        this.view.setPresenter(this);
        dirModel = new DirModel();
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

}
