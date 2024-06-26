package com.example.yuanann.stray_cat.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yuanann.stray_cat.DatabaseHelper;
import com.example.yuanann.stray_cat.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Adopt_insert extends AppCompatActivity {

    Button b1;
    EditText ed1;
    String e1;
    ImageView image,b2;
    String fileName=null;
    String a="null";
    //调用系统相册-选择图片
    private static final int IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt_insert);
        int result = PermissionChecker.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result != PermissionChecker.PERMISSION_GRANTED) {
            //申请 WRITE_EXTERNAL_STORAGE 权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    123);
        }
        ImageView back=(ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("id",2);//其中1表示Fragment的编号，从0开始编，secondFra的编号为1
                intent.setClass(Adopt_insert.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ed1 = (EditText) findViewById(R.id.ed1);
        image=(ImageView)findViewById(R.id.image);
        b1 = (Button) findViewById(R.id.bu1);
        b2 = (ImageView) findViewById(R.id.ziliao);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMAGE);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1= ed1.getText().toString().trim();
                a=String.valueOf(image.getBackground());
                SharedPreferences sp = Adopt_insert.this.getSharedPreferences("User", MODE_PRIVATE);
                final String name =sp.getString("name", null);
                if (TextUtils.isEmpty(e1)) {
                    Toast.makeText(Adopt_insert.this, "请输入内容", Toast.LENGTH_SHORT).show();
                }else if(fileName==null){
                    Toast.makeText(Adopt_insert.this, "发布一张图片吧...", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Adopt_insert.this, "发布成功", Toast.LENGTH_SHORT).show();
                    DatabaseHelper dbHelper = new DatabaseHelper(Adopt_insert.this);
                    ContentValues values = new ContentValues();

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                    String str = formatter.format(curDate);
                    values.put("f_content", e1);
                    values.put("f_sendname",name);
                    values.put("f_pic",fileName);
                    values.put("f_state", "n");
                    values.put("f_time", str);
                    dbHelper.insertAdopt(values);

                    Intent intent = new Intent();
                    finish();
                    intent.putExtra("id",2);//其中1表示Fragment的编号，从0开始编，secondFra的编号为1
                    intent.setClass(Adopt_insert.this,MainActivity.class);
                    startActivity(intent);

                }
            }

        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String imagePath = getFileUrl(selectedImage);

            File tempFile =new File( imagePath.trim());
            fileName = tempFile.getName();
            System.out.println("fileName = " + fileName);
            showImage(imagePath);
            b2.setVisibility(View.GONE );
//            Toast.makeText(MainActivity7.this, "收藏成功"+imagePath, Toast.LENGTH_SHORT).show();
            File file =new File("data//data//"+ getPackageName() + "//files//");
//如果文件夹不存在则创建
            if  (!file .exists()  && !file .isDirectory())
            {
                System.out.println("//不存在");
                file .mkdir();
            } else
            {
                System.out.println("//目录存在");
            }

            copyFile(imagePath,"data//data//"+ getPackageName() + "//files//"+fileName);
            //content://media/external/images/media/450646
            Log.d("测试",imagePath);
        }

    }

    private String getFileUrl(Uri selectedImage) {
        String[] filePathColumns = {MediaStore.Images.Media.DATA};
        Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
        c.moveToFirst();
        int columnIndex = c.getColumnIndex(filePathColumns[0]);
        String imagePath = c.getString(columnIndex);
        c.close();
        return imagePath;
    }

    //加载图片
    private void showImage(String imaePath){
        Bitmap bm = BitmapFactory.decodeFile(imaePath);
        ((ImageView)findViewById(R.id.image)).setImageBitmap(bm);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        handleGrantResults(requestCode,grantResults);
    }

    private void handleGrantResults(int requestCode, int[] grantResults) {
        if (requestCode == 123) {
            if (grantResults.length>0&&grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted 获得权限后执行xxx
            } else {
                // Permission Denied 拒绝后xx的操作。
            }
        }
    }
    public void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }
    }
    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
