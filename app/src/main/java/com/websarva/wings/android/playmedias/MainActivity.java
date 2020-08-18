package com.websarva.wings.android.playmedias;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity {

    // APIキー
    private static final String API_KEY = "";

    YouTubePlayerView mYoutubePlayerView;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //端末画面を縦向きに固定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mYoutubePlayerView = findViewById(R.id.Youtube_view);
        // レイアウトを非表示
        mYoutubePlayerView.setVisibility(View.INVISIBLE);
    }

    // リセットボタンタップ時の処理
    public void onResetButtonClick(View view){
        finish();
        startActivity(getIntent());
    }
    @Override
    protected void onResume(){
        super.onResume();
        this.onCreate(null);
    }

    // 再生ボタンタップ時の処理
    public void onStartButtonClick(View view){
        EditText input = findViewById(R.id.edURL);
        EditText input2 = findViewById(R.id.edList);
        // videoId取得メンバ関数に渡す
        final String inputList = replaceInputList(input2.getText().toString());
        // videoId取得メンバ関数に渡す
        final String inputUri = replaceInput(input.getText().toString());

        // URLとListId、両方に文字列が入力された場合
        if ((!inputUri.equals("")) && (!inputList.equals(""))){
            toastStr("どちらか片方のみ入力できます！");

         // 全く文字列が入力されていない場合
        }else if (inputUri.equals("") && inputList.equals("")){
            toastStr("URLがどちらも入力されていません！");
        }
        else if (!inputUri.equals("")){
            mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                        // レイアウトの非表示を解除
                        mYoutubePlayerView.setVisibility(View.VISIBLE);
                        // 指定したURLの再生を開始
                        youTubePlayer.loadVideo(inputUri);
                        // フルスクリーンを無効化
                        youTubePlayer.setShowFullscreenButton(false);
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            };
            mYoutubePlayerView.initialize(API_KEY, mOnInitializedListener);
        }else {
            mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    mYoutubePlayerView.setVisibility(View.VISIBLE);
                    youTubePlayer.loadPlaylist(inputList);
                    youTubePlayer.setShowFullscreenButton(false);
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            };
            mYoutubePlayerView.initialize(API_KEY, mOnInitializedListener);
        }
    }

    public void onAntherStartButtonClick(View view){
        // Uri文字列の取得
        EditText input = findViewById(R.id.edURL);
        EditText input2 = findViewById(R.id.edList);
        // videoId取得メンバ関数に渡す
        final String inputList = replaceInputList(input2.getText().toString());
        // videoId取得メンバ関数に渡す
        final String inputUri = replaceInput(input.getText().toString());

        // URLとListId、両方に文字列が入力された場合
        if ((!inputUri.equals("")) && (!inputList.equals(""))) {
            toastStr("どちらか片方のみ入力できます！");

        // 全く文字列が入力されて居ない場合
        }else if (inputUri.equals("") && inputList.equals("")){
            toastStr("URLがどちらも入力されていません！");
        }
        else if (!inputUri.equals("")){
            Intent intent = new Intent(MainActivity.this, YoutubeActivity.class);
            intent.putExtra("inputUri", inputUri);
            startActivity(intent);
        }else {
            Intent intent = new Intent(MainActivity.this, YoutubeActivity2.class);
            intent.putExtra("inputList", inputList);
            startActivity(intent);
        }
    }

    // Toast表示クラス
    private void toastStr(String str){
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    // 曲URLからvideoIdを取得
    private String replaceInput(String inputUri){
        String input = inputUri.replace("https://www.youtube.com/watch?v=", "");

        return input.replace("https://m.youtube.com/watch?v=", "");
    }

    // プレイリストURLからvideoIdを取得
    private String replaceInputList(String inputList){
        String input = inputList.replace("https://www.youtube.com/watch?v=", "");
        String input2 = input.replace("https://m.youtube.com/watch?v=", "");

        return input2.replaceFirst(".+&list=","");
    }
}