package com.example.carcontrol;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void ShowGif(View view) {
        ImageView imageView = findViewById(R.id.imageView);
        Glide.with(this).load(R.drawable.giphy).into(imageView);
    }
}
