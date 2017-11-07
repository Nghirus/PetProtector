package edu.orangecoastcollege.cs273.petprotector;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PetDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String details = intent.getStringExtra("details");
        String phone = intent.getStringExtra("phone");
        String uri = intent.getStringExtra("uri");

        ImageView detailImageView = (ImageView) findViewById(R.id.detailsImageView);
        TextView detailNameTextView =(TextView) findViewById(R.id.detailNameTextView);
        TextView detailDetailsTextView =(TextView) findViewById(R.id.detailDetailsTextView);
        TextView detailPhoneTextView =(TextView) findViewById(R.id.detailPhoneTextView);

        detailNameTextView.setText(name);
        detailDetailsTextView.setText(details);
        detailPhoneTextView.setText(phone);

        detailImageView.setImageURI(Uri.parse(uri));

    }
}
