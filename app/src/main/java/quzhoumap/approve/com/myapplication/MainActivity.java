package quzhoumap.approve.com.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.megvii.facepp.sdk.Facepp;
import com.megvii.licensemanager.sdk.LicenseManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Facepp.getSDKAuthType(getFileContent(this, R.raw.megviifacepp_0_5_2_model));
        LicenseManager licenseManager = new LicenseManager(this);
        licenseManager.setAuthTimeBufferMillis(0);
        String s = UUID.randomUUID().toString();
        Log.e("ss", s);
        licenseManager.takeLicenseFromNetwork("https://api-cn.faceplusplus.com/sdk/v3/auth", "0c26ed06-652d-4a95-9fd6-1079af94e09a", "8OUMfZ4w4Y2rg_2lZ8uUEvZ-8vYZ_XhN", "0TEfFOvPpc6w-QnUAMlYLbxZdLpfiouX", Facepp.getApiName(), "1", new LicenseManager.TakeLicenseCallback() {
            @Override
            public void onSuccess() {
                Log.e("sss", "初始化成功");
            }

            @Override
            public void onFailed(int i, byte[] bytes) {
                Log.e(i + "", new String(bytes));
            }
        });
    }

    public byte[] getFileContent(Context context, int id) {
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int count = -1;
        try {
            inputStream = context.getResources().openRawResource(id);
            while ((count = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, count);
            }
            byteArrayOutputStream.close();
        } catch (IOException e) {
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                    inputStream = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

}
