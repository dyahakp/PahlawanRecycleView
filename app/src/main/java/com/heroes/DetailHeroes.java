package com.heroes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myrecycleview.MainActivity;
import com.example.myrecycleview.R;

public class DetailHeroes extends AppCompatActivity {
    ImageView detailImage;
    TextView detailDesc, detailTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_heroes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        detailTitle = findViewById(R.id.detail_title);
        detailImage = findViewById(R.id.detail_image);
        detailDesc = findViewById(R.id.detail_desc);
        getIncomingIntent();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getIncomingIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int getImage = bundle.getInt("image");
            Glide.with(getApplicationContext()).load(getImage).into(detailImage);
            String getTitle = bundle.getString("title");
            String getDesc = bundle.getString("desc");

            detailDesc.setText(getDesc);
            detailTitle.setText(getTitle);


        }
    }
}
