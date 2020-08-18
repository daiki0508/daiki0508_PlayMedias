package com.websarva.wings.android.playmedias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeStandalonePlayer;

public class YoutubeActivity extends AppCompatActivity {

    private static final String API_KEY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        Intent intent = getIntent();
         String inputUri = intent.getStringExtra("inputUri");

         // 処理をYoutubeAPIに飛ばす
        assert inputUri != null;
        Intent intent2 = YouTubeStandalonePlayer.createVideoIntent(this, API_KEY, inputUri);
        startActivity(intent2);
    }
}