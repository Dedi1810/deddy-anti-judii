package com.deddy.antijudi;

import android.app.Activity;
import android.content.Intent;
import android.net.VpnService;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(32, 32, 32, 32);

        TextView title = new TextView(this);
        title.setText("DEDDY ANTI JUDI");
        title.setTextSize(26);
        title.setGravity(Gravity.CENTER);

        statusText = new TextView(this);
        statusText.setText("PERLINDUNGAN BELUM AKTIF");
        statusText.setTextSize(18);
        statusText.setGravity(Gravity.CENTER);
        statusText.setPadding(0, 40, 0, 40);

        Button startButton = new Button(this);
        startButton.setText("AKTIFKAN PERLINDUNGAN");

        startButton.setOnClickListener(v -> {
            Intent intent = VpnService.prepare(this);

            if (intent != null) {
                startActivityForResult(intent, 100);
            } else {
                onActivityResult(100, RESULT_OK, null);
            }
        });

        layout.addView(title);
        layout.addView(statusText);
        layout.addView(startButton);

        setContentView(layout);
    }

    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            Intent serviceIntent =
                new Intent(this, AntiJudiVpnService.class);

            startService(serviceIntent);

            statusText.setText("PERMINTAAN AKTIVASI DIKIRIM");
        }
    }
}
