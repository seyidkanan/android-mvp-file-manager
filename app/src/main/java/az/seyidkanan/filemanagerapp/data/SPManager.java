package az.seyidkanan.filemanagerapp.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import az.seyidkanan.filemanagerapp.R;
import az.seyidkanan.filemanagerapp.pojo.DirPojo;
import az.seyidkanan.filemanagerapp.util.Constant;

public class SPManager {

    private Context context;

    private SharedPreferences sp;


    public SPManager(Context context) {
        this.context = context;
        initSP();
    }

    private void initSP() {
        sp = context.getSharedPreferences(context.getString(R.string.const_sp_name),
                Context.MODE_PRIVATE);
    }

    private String objectToString(Object object) {
        String encoded = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream
                    = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream
                    = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.close();
            encoded = new String(Base64.encodeToString(
                    byteArrayOutputStream.toByteArray(), 0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encoded;
    }

    private void setStringElement(String key, String val) {
        sp.edit().putString(key, val).apply();
    }

    private String getStringElement(String key, String defaultVal) {
        return sp.getString(key, defaultVal);
    }

    public void setDirList(List<DirPojo> dirList) {
        clearDirListData();
        setStringElement(Constant.PREF_DIR_LIST, objectToString(dirList));
    }


    public void clearDirListData() {
        sp.edit().remove(Constant.PREF_DIR_LIST).apply();
    }

    public List<DirPojo> getDirList() {
        byte[] bytes = Base64.decode(getStringElement(Constant.PREF_DIR_LIST,
                ""), 0);
        List<DirPojo> object = null;
        try {
            ObjectInputStream objectInputStream
                    = new ObjectInputStream(new ByteArrayInputStream(bytes));
            object = (List<DirPojo>) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

}
