package az.seyidkanan.filemanagerapp.data;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {

//    private static final String TAG = "kanan";

    public static List<File> getFileList(String folder) {
        try {
            String path;
            if (TextUtils.isEmpty(folder)) {
                path = Environment.getExternalStorageDirectory().toString();
            } else {
                path = folder;
            }
            File directory = new File(path);
            File[] files = directory.listFiles();
            return new ArrayList<>(Arrays.asList(files));
        } catch (Exception e) {
            return null;
        }
    }

}
