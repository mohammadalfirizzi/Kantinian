package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Api.ApiRequest;
import com.example.myapplication.Api.RetroClient;
import com.example.myapplication.Model.ResponseApiModel;
import com.example.myapplication.Model.ResponseDelete;
import com.example.myapplication.Model.ResponseImageModel;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadBukti extends AppCompatActivity {
    TextView id_topup, total;
    Button metode;
    Button batal;
    ImageButton kembali;
    EditText title;
    private static final int PICK_IMAGE = 100;
    private static final int PERMISSION_STORAGE = 2;

    private static final String TAG = UploadBukti.class.getSimpleName();
    private Bitmap bitmap;

    ImageView mImageThumb;

    @BindView(R.id.btn_upload)
    protected AppCompatButton mBtnUploads;

    Button mAddImage;
    Button mBtnUpload;
    private Unbinder mUnbinder;
    private String selectImagePath;
    private Snackbar mSnackbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_bukti);
        mAddImage = (Button)findViewById(R.id.btn_tmbh);
        mBtnUpload = (Button) findViewById(R.id.btn_upload);
        batal = (Button) findViewById(R.id.btnbatal);
        kembali = (ImageButton) findViewById(R.id.pengaturanupload);
        mImageThumb = (ImageView) findViewById(R.id.img_thumb);
        id_topup = (TextView) findViewById(R.id.texttopup);
        total = (TextView) findViewById(R.id.textuang);
        title = (EditText) findViewById(R.id.haa);
        title.setVisibility(View.INVISIBLE);
        String stotal = total.getText().toString();
        String sid_topup = id_topup.getText().toString();
        metode = (Button) findViewById(R.id.button6);
        mUnbinder = ButterKnife.bind(this);
        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle == null) {
                stotal = null;
                sid_topup = null;
                total.setText(stotal);
                title.setText(sid_topup);
                id_topup.setText("ID TOP UP "+sid_topup);
            }
            else {
                stotal = bundle.getString("angka");
                total.setText(stotal);
                sid_topup = bundle.getString("hasilid");
                title.setText(sid_topup);
                id_topup.setText("ID TOP UP "+sid_topup);
            }
        }
        else {
            stotal = (String) savedInstanceState.getSerializable("angka");
            sid_topup = (String) savedInstanceState.getSerializable("hasilid");
            title.setText(sid_topup);
            total.setText(stotal);
            id_topup.setText("ID TOP UP "+sid_topup);
        }

        final String finalSid_topup = sid_topup;
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(UploadBukti.this);
                View mView = getLayoutInflater().inflate(R.layout.custom_dialog_batal_transaksi,null);
                Button btn_cancel = (Button) mView.findViewById(R.id.cancelbtn);
                Button btn_ok = (Button) mView.findViewById(R.id.okbtn);
                builder.setView(mView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.setCanceledOnTouchOutside(false);
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ApiRequest Api = RetroClient.getRequestService();
                        Call<ResponseDelete> delete = Api.delete(finalSid_topup);
                        delete.enqueue(new Callback<ResponseDelete>() {
                            @Override
                            public void onResponse(Call<ResponseDelete> call, Response<ResponseDelete> response) {
                                Log.d("RETRO", "response : " + response.body().toString());
                                String kode = response.body().getKode();

                                if(kode.equals("1"))
                                {
                                    Toast.makeText(UploadBukti.this, "Data Topup berhasil dibatalkan", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(UploadBukti.this, Sliding.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                }
                                else {
                                    Toast.makeText(UploadBukti.this, "Data Gagal", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseDelete> call, Throwable t) {
                                Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                            }
                        });
                    }
                });
                alertDialog.show();


            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploadBukti.this, Sliding.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        mBtnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    uploadImage();
                }
                catch (Exception e) {
//                    Toast.makeText(UploadBukti.this, "Anda Belum memilih", Toast.LENGTH_SHORT).show();
                    Log.e("MYAPP", "exception", e);
                }

            }
        });
        mAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(UploadBukti.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(UploadBukti.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(UploadBukti.this,
                            new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            PERMISSION_STORAGE);

                } else {
                    openImage();
                }
            }
        });
    }
    public void openImage() {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        startActivityForResult(intent,PICK_IMAGE);
    }
    public void uploadImage() {
        String Image = iamgeToString();
        String Title = title.getText().toString();

        ApiRequest request = RetroClient.getRequestService();
        Call<ResponseImageModel> responseCall = request.uploadImage(Title, Image);
        responseCall.enqueue(new Callback<ResponseImageModel>() {
            @Override
            public void onResponse(Call<ResponseImageModel> call, Response<ResponseImageModel> response) {
                ResponseImageModel imageClass = response.body();
                Toast.makeText(UploadBukti.this, "Upload berhasil", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseImageModel> call, Throwable t) {

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri selectImageUri = data.getData();
            selectImagePath = getRealPathFromURI(selectImageUri);
            decodeImage(selectImagePath);
            uploadImage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openImage();
                }

                return;
            }
        }
    }
    private String getRealPathFromURI(Uri selectImageUri) {
        String[] imageprojection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(selectImageUri, imageprojection, null, null, null);
        if (cursor == null) {
            return selectImageUri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(imageprojection[0]);
            return cursor.getString(idx);
        }
    }

    private void decodeImage(String selectImagePath) {
        int targetW = mImageThumb.getWidth();
        int targetH = mImageThumb.getHeight();

        final BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(selectImagePath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bitmap = BitmapFactory.decodeFile(selectImagePath, bmOptions);
        if (bitmap != null) {
            mImageThumb.setImageBitmap(bitmap);
        }
    }
    private String iamgeToString(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
    public void onBackPressed(){
        Intent intent = new Intent(UploadBukti.this, Sliding.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
