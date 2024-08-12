package lesson2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CustomReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("zxl-->CustomReceiver-->"+intent.getAction());
    }
}
