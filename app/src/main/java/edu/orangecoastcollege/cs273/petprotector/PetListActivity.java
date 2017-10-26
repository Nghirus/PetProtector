package edu.orangecoastcollege.cs273.petprotector;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class PetListActivity extends AppCompatActivity {

    private ImageView mPetImageView;
    private Uri imageUri;

    //Constants for permissions:
    private static final int GRANTED = PackageManager.PERMISSION_GRANTED;
    private static final int DENIED = PackageManager.PERMISSION_DENIED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        mPetImageView = (ImageView) findViewById(R.id.petImageView);
        mPetImageView.setImageURI(getUriFromResource(this,R.drawable.none));
    }

    public static Uri getUriFromResource(Context context, int resID)
    {
        Resources res = context.getResources();
        //Build a string in the form:
        //android.resource://edu.orangecoastcollege.cs273.petprotector/drawable/none
        String uri = ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + res.getResourcePackageName(resID) + "/"
                + res.getResourceTypeName(resID) + "/"
                + res.getResourceEntryName(resID);

        //Parse string to construct  Uri
        return Uri.parse(uri);
    }

    public void selectPetImage(View v)
    {
        List<String> permList = new ArrayList<>();
        //Check each permission individually
        int hasCameraPerm = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (hasCameraPerm == DENIED)
            permList.add(Manifest.permission.CAMERA);

        int hasReadPerm = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if(hasReadPerm == DENIED)
            permList.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        int hasWritePerm = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(hasWritePerm == DENIED)
            permList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permList.size() > 0) {
            //Convert perms list into an array;
            String[] permsArray = new String[permList.size()];
            permList.toArray(permsArray);

            ActivityCompat.requestPermissions(this, permsArray, 1337);
        }
            // Make sure we have all permission, then start gallery.
            if(hasCameraPerm == GRANTED && hasReadPerm == GRANTED && hasWritePerm == GRANTED)
            {
                // Open the image gallery
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                //start activity for a result (picture)
                startActivityForResult(galleryIntent,1);
            }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null)
        {
            //data = data from galleryItent (the URI of some image)
            imageUri = data.getData();
            mPetImageView.setImageURI(imageUri);
        }

    }
}
