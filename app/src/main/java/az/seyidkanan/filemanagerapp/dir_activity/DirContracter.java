package az.seyidkanan.filemanagerapp.dir_activity;

import android.content.Context;

import java.io.File;
import java.util.List;

import az.seyidkanan.filemanagerapp.BasePresenter;
import az.seyidkanan.filemanagerapp.pojo.DirPojo;

public interface DirContracter {

    interface View extends BasePresenter<Presenter> {

        void onUpdateDirList(List<DirPojo> fileList);

        void showErrorMessage(String message);

        Context getContext();

        void showProgressBar();

        void hideProgress();

    }

    interface Model {

        List<DirPojo> getDirList();

        List<DirPojo> getDirListFromThisPath(String path);

    }

    interface Presenter {

        void onDirClick(DirPojo file);

        void showFileList();

    }

}