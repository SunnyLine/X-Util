package com.pullein.demo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.pullein.common.util.Log;
import com.pullein.common.widget.SignatureView;

public class SignatureActivity extends AppCompatActivity {

    private SignatureView signatureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);
        signatureView = findViewById(R.id.signatureView);
        signatureView.setOnSignatureReadyListener(new SignatureView.SignatureReadyListener() {
            @Override
            public void onSignatureReady(boolean ready) {
                Log.d("签字准备:" + ready);
            }

            @Override
            public void onStartSigningSignature(boolean startSigning) {
                Log.d("开始签字：" + startSigning);
            }
        });
    }

    public void clear(View view) {
        signatureView.clear();
    }

    public void confirm(View view) {
    }
}
