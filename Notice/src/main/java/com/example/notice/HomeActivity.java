package com.example.notice;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.cardview.widget.CardView;

public class HomeActivity extends BaseActivity {

    CardView about, std, location, contact, wapp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate your screen's layout inside the drawer's content_frame
        setContentLayout(R.layout.activity_home);
        about = findViewById(R.id.aboutusCard);
        std = findViewById(R.id.stdCard);
        location = findViewById(R.id.locationCard);
        contact = findViewById(R.id.contactCard);
        wapp = findViewById(R.id.whatsAppCard);

        std.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getActivity(),Std.class);
                //startActivity(intent);
            }
        });

        // Add OnClickListeners for other CardViews if needed:

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://hvdesaicollege.org/HVDesai/Home "));
                startActivity(intent);
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.google.com/maps?q=18.518145306659388, 73.85556361534508")); // Use coordinates or address
                intent.setPackage("com.google.android.apps.maps"); // Ensures it opens in Google Maps
                startActivity(intent);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getActivity(), ContactList.class); // Replace ContactActivity.class
                //startActivity(intent);
            }
        });

        wapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://wa.me/9767108208 "));
                startActivity(intent);

            }
        });


    }
}
