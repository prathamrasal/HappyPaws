package com.example.happypaws;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Dialog;
import android.app.Instrumentation;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.DexterActivity;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;
import java.util.Random;

public class AddPet extends AppCompatActivity {
    EditText name,contact,breed,address;
    int sno=0;
    Uri filepath;
    ImageView img;
    Button browse, upload;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);
        img=(ImageView) findViewById(R.id.img);
        upload = (Button) findViewById(R.id.upload);
        browse = (Button) findViewById(R.id.browse);

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(AddPet.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                Intent intent=new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent,"Select Image File"),1);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadToFirebase();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode==1&&resultCode==RESULT_OK)
        {
            filepath = data.getData();
            try{
                InputStream inputStream=getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            }catch (Exception ex)
            {

            }
        }
        super.onActivityResult(requestCode,resultCode,data);

    }


    private void uploadToFirebase()
    {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Adding Your Pet");
        dialog.show();
        name = (EditText) findViewById(R.id.name);
        breed =(EditText) findViewById(R.id.breed);
//        sno =(EditText) findViewById(R.id.sno);
        contact =(EditText) findViewById(R.id.contact);
        address =(EditText) findViewById(R.id.address);

//        FirebaseDatabase db = FirebaseDatabase.getInstance();
//        DatabaseReference root =db.getReference();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference uploader = storage.getReference("Image1"+ new Random().nextInt(50));
        uploader.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            dialog.dismiss();
                            FirebaseDatabase db = FirebaseDatabase.getInstance();
                            DatabaseReference root =db.getReference("users");


                            dataholder obj = new dataholder(name.getText().toString(),contact.getText().toString(),address.getText().toString(),breed.getText().toString(),uri.toString());
                            Random rn = new Random();
                            int max = 1000000 ;
                            int min =0;
                            sno = rn.nextInt(max - min + 1) + min;
                            root.child (String.valueOf(sno)).setValue(obj);

                            name.setText("");
                            contact.setText("");
                            breed.setText("");
                            address.setText("");
//                            sno.setText("");
                            img.setImageResource(R.drawable.ic_launcher_background);
                            Toast.makeText(AddPet.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();

                        }
                    });
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                float percent =(100*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
                dialog.setMessage("Added: "+(int)percent+"%");
                    }
                });
    }
}