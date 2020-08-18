package com.websarva.wings.android.playmedias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeStandalonePlayer;

public class YoutubeActivity2 extends AppCompatActivity {

    private static final String API_KEY = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube2);

        Intent intent = getIntent();
        String inputUri = intent.getStringExtra("inputList");

        // 処理をYoutubeAPIに飛ばす
        assert inputUri != null;
        Intent intent2 = YouTubeStandalonePlayer.createPlaylistIntent(this, API_KEY, inputUri);
        startActivity(intent2);
    }
}