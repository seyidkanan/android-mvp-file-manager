package az.seyidkanan.filemanagerapp.dir_activity;

import android.text.format.Formatter;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import az.seyidkanan.filemanagerapp.data.FileManager;
import az.seyidkanan.filemanagerapp.pojo.DirPojo;

public class DirModel implements DirContracter.Model {


    @Override
    public List<DirPojo> getDirList() {

        List<DirPojo> dirPojoList = new ArrayList<>();

        List<File> files = FileManager.getFileList("/");

        for (File file : files) {
            DirPojo dirPojo = new DirPojo();
            dirPojo.setAbsolutePath(file.getAbsolutePath());
            dirPojo.setFile(!file.isDirectory());
            dirPojo.setDirectory(file.isDirectory());
            dirPojo.setParentPath(file.getParent());
            try {
                Date lastModDate = new Date(file.lastModified());
                SimpleDateFormat format;
                Date newDate;
                //Fri May 29 21:46:36 GMT+04:00 2015
                format = new SimpleDateFormat("EEE MMM dd hh:mm:ss Z yyyy");
                newDate = format.parse(lastModDate.toString());

                format = new SimpleDateFormat("dd MMM yyyy hh:mm a");
                String date = format.format(newDate);
                dirPojo.setDate(date);
            } catch (Exception e) {
                e.printStackTrace();
            }


            dirPojo.setName(file.getName());

            dirPojo.setLength(file.length());

            dirPojoList.add(dirPojo);
        }

        return dirPojoList;
    }

    @Override
    public List<DirPojo> getDirListFromThisPath(String path) {
        List<DirPojo> dirPojoList = new ArrayList<>();

        List<File> files = FileManager.getFileList(path);

        for (File file : files) {
            DirPojo dirPojo = new DirPojo();
            dirPojo.setAbsolutePath(file.getAbsolutePath());
            dirPojo.setFile(!file.isDirectory());
            dirPojo.setDirectory(file.isDirectory());

            dirPojo.setParentPath(file.getParent());

            try {
                Date lastModDate = new Date(file.lastModified());
                SimpleDateFormat format;
                Date newDate;
                //Fri May 29 21:46:36 GMT+04:00 2015
                format = new SimpleDateFormat("EEE MMM dd hh:mm:ss Z yyyy");
                newDate = format.parse(lastModDate.toString());

                format = new SimpleDateFormat("dd MMM yyyy hh:mm a");
                String date = format.format(newDate);
                dirPojo.setDate(date);
            } catch (Exception e) {
                e.printStackTrace();
            }


            dirPojo.setName(file.getName());

            dirPojo.setLength(file.length());

            dirPojoList.add(dirPojo);
        }

        return dirPojoList;
    }


}
