package com.example.zt.feeling.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.zt.feeling.Adapter.WriteImageAdapter;
import com.example.zt.feeling.Model.BlogUser;
import com.example.zt.feeling.MyApp;
import com.example.zt.feeling.R;
import com.example.zt.feeling.tools.URL;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class WriteBlogActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, RadioGroup.OnCheckedChangeListener {
    private GridView gridView;
    private WriteImageAdapter adapter;
    private List<Bitmap> bitmaps=new ArrayList<>();
    private final int IMAGE_OPEN = 1;
    private String pathImage;
    private ArrayList<HashMap<String, Object>> imageItem;
    private RadioGroup radioGroup;
    private BlogUser blogUser;
    private EditText write_edit;
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_blog);
        gridView= (GridView) findViewById(R.id.write_gridView);
        radioGroup= (RadioGroup) findViewById(R.id.write_blog_radio);
        Bitmap bitmap1=BitmapFactory.decodeResource(getResources(),R.drawable.imageadd);
        bitmaps.add(bitmaps.size(),bitmap1);
       adapter=new WriteImageAdapter(this,bitmaps);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(this);
        radioGroup.setOnCheckedChangeListener(this);
        blogUser=((MyApp)getApplication()).getBlogUser();
        write_edit= (EditText) findViewById(R.id.writeblog_edit);


    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
           if(i==bitmaps.size()-1) {
               Intent intent = new Intent(Intent.ACTION_PICK,
                       android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
               startActivityForResult(intent, IMAGE_OPEN);
           }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==IMAGE_OPEN) {
            Uri uri = data.getData();
            if (!TextUtils.isEmpty(uri.getAuthority())) {
                //查询选择图片
                Cursor cursor = getContentResolver().query(
                        uri,
                        new String[] { MediaStore.Images.Media.DATA },
                        null,
                        null,
                        null);
                //返回 没找到选择图片
                if (null == cursor) {
                    return;
                }
                //光标移动至开头 获取图片路径
                cursor.moveToFirst();
                pathImage = cursor.getString(cursor
                        .getColumnIndex(MediaStore.Images.Media.DATA));
            }
        }  //end if 打开图片
    }
    public String getPathImage()
    {
        String path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/myimages";
        File file=new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        return path;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!TextUtils.isEmpty(pathImage)){
            Bitmap addbmp=BitmapFactory.decodeFile(pathImage);
            bitmaps.add(bitmaps.get(bitmaps.size()-1));
            bitmaps.set(bitmaps.size()-2,addbmp);

            adapter.notifyDataSetChanged();
            pathImage = null;
        }
    }
    public String getNowTime()
    {
        long time= System.currentTimeMillis();
        return String.valueOf(time);
    }
    public boolean saveBitMapFile(Bitmap bitmap,String filename)
    {
        Bitmap.CompressFormat format= Bitmap.CompressFormat.JPEG;
        int quality=100;
        OutputStream out=null;
        try {
            out=new FileOutputStream(getPathImage()+"/"+filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap.compress(format,quality,out);
    }
    public void deleteFiles()
    {
        File file=new File(getPathImage());
        File[] files=file.listFiles();
        for(int n=0;n<files.length;n++)
        {

            files[n].delete();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(R.id.write_send==i)
        {
            String id=String.valueOf(blogUser.getBloguser_id());
            Log.i("userid",id);
            String content=write_edit.getText().toString();
            RequestParams params=new RequestParams(URL.URL_UPLOAD);

            for(int k=0;k<bitmaps.size()-1;k++)
            {
                saveBitMapFile(bitmaps.get(k),UUID.randomUUID()+".jpg");

            }
            File file=new File(getPathImage());
            File[] files=file.listFiles();
            for(int n=0;n<files.length;n++)
            {

                params.addBodyParameter("file",files[n]);
            }


            params.addBodyParameter("blog_content",content);
            params.addBodyParameter("blog_userId",id);
            params.addBodyParameter("blog_id",getBlogId(id));


            params.setMultipart(true);
            x.http().post(params, new Callback.CacheCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    Toast.makeText(WriteBlogActivity.this,result,Toast.LENGTH_LONG).show();
                    deleteFiles();

                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Toast.makeText(WriteBlogActivity.this,ex.toString(),Toast.LENGTH_LONG).show();

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }

                @Override
                public boolean onCache(String result) {
                    return false;
                }
            });

        }
         else if(R.id.write_back_write==i){
            finish();
            overridePendingTransition(R.anim.slide_left_out,R.anim.slide_right_in);

        }
    }
    public String getBlogId(String id)
    {
        UUID uuid=UUID.randomUUID();
        String str=uuid.toString();
        i++;
        return str;
    }
}
