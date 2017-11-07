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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the PetActivity that will load when the app starts. User will have the option to enter
 * the pet's detail and then add it to a scrolling list at the bottom. User can click the item on
 * the list to get more details about a pet.
 */
public class PetListActivity extends AppCompatActivity {

    private ImageView mPetImageView;
    private EditText mNameEditText;
    private EditText mDetailEditText;
    private EditText mPhoneNumberEditText;
    private DBHelper db;
    private List<Pet> mPetList;
    private PetListAdaptor mPetListAdaptor;
    private ListView petListView;
    private Uri imageUri;

    //Constants for permissions:
    private static final int GRANTED = PackageManager.PERMISSION_GRANTED;
    private static final int DENIED = PackageManager.PERMISSION_DENIED;

    /**
     * This is called when the app ios loaded, and makes the view visible to the user.
     * @param savedInstanceState the current view
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        mPetImageView = (ImageView) findViewById(R.id.petImageView);
        imageUri = getUriFromResource(this,R.drawable.none);
        mPetImageView.setImageURI(imageUri);

        mNameEditText = (EditText) findViewById(R.id.nameEditText);
        mDetailEditText = (EditText) findViewById(R.id.detailEditText);
        mPhoneNumberEditText = (EditText) findViewById(R.id.phoneEditText);
        petListView = (ListView) findViewById(R.id.petListView);

        this.deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);
//        db.addPet(new Pet("Dog", "A random dog on the street", "7148222222",imageUri.toString()));
//        db.addPet(new Pet("Cat", "Keeps coming for food", "7148222222",imageUri.toString()));
//        db.addPet(new Pet("Rabbit", "Tiny Rabbit in the backyard", "7148222222",imageUri.toString()));
//        db.addPet(new Pet("Spider", "Spider near my desk", "7148222222",imageUri.toString()));
        mPetList = db.getAllPets();

        mPetListAdaptor = new PetListAdaptor(this,R.layout.pet_list_item, mPetList);
        petListView.setAdapter(mPetListAdaptor);
        petListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * This is the onclick listener to find out where the user clicked in the item
             * @param adapterView unused
             * @param view the current view
             * @param position the position of the item clicked
             * @param l unused
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Pet selectedPet = mPetList.get(position);
                String name = selectedPet.getName();
                String details = selectedPet.getDetails();
                String phone = selectedPet.getPhone();
                String uri = selectedPet.getImageURI();

                Intent detailIntent = new Intent(PetListActivity.this,PetDetailsActivity.class);
                detailIntent.putExtra("name", name);
                detailIntent.putExtra("details", details);
                detailIntent.putExtra("phone", phone);
                detailIntent.putExtra("uri", uri);

                startActivity(detailIntent);

            }
        });

    }

    /**
     * This function will get a URI from a resource file
     * @param context where the current resource is located
     * @param resID the R id for the resource file
     * @return
     */
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

    /**
     * Will ask for permissions for gallery and camera
     * Then ask user to select an image to load
     * @param v the current view
     */
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

    /**
     * If then user selects an image, and does not click the back button This function will generate
     * the new image to display, and a new URI
     * @param requestCode request code generated from selectPetImage
     * @param resultCode the result of the request
     * @param data the intent
     */
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

    /**
     * Takes the user's input from the Edit Texts and Imageview and create a new pet to add to the
     * database and list.
     * @param v the current view
     */
    public void addPet(View v)
    {
        String name = mNameEditText.getText().toString();
        String phone = mPhoneNumberEditText.getText().toString();
        String details = mDetailEditText.getText().toString();
        String uri = imageUri.toString();

        Pet newPet = new Pet(name, details, phone, uri);
        db.addPet(newPet);
        mPetList.add(newPet);
        mPetListAdaptor.notifyDataSetChanged();

        mNameEditText.setText("");
        mPhoneNumberEditText.setText("");
        mDetailEditText.setText("");
        mPetImageView.setImageURI(getUriFromResource(this,R.drawable.none));
    }


}