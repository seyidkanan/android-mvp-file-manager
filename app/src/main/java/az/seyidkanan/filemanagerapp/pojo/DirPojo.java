package az.seyidkanan.filemanagerapp.pojo;

import java.io.File;

public class DirPojo {

    private String name;
    private String date;
    private long length;
    private boolean isDirectory;
    private boolean isFile;

    private String absolutePath;

    private String parentPath;


    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public boolean isFile() {
        return isFile;
    }

    public void setFile(boolean file) {
        isFile = file;
    }

    @Override
    public String toString() {
        return "DirPojo{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", length='" + length + '\'' +
                ", isDirectory=" + isDirectory +
                ", isFile=" + isFile +
                '}';
    }
}
