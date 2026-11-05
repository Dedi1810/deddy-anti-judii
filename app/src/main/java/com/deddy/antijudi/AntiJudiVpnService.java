package com.deddy.antijudi;

import android.net.VpnService;
import android.content.Intent;
import android.os.IBinder;

public class AntiJudiVpnService extends VpnService {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Layanan dasar saja.
        // Belum membuat koneksi VPN atau memblokir DNS.
        stopSelf(startId);
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }
}
