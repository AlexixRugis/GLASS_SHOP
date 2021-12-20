package com.example.glassesshop.api;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadFileFromURL extends AsyncTask<String, String, String> {

    public interface ISaveCallback {
        void onSave(Boolean success);
        void onProgress(int percents);
    }

    private final ISaveCallback callback;
    private boolean success = true;

    public DownloadFileFromURL(@NonNull ISaveCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... f_url) {
        int count;
        try {
            URL url = new URL(f_url[0]);
            URLConnection connection = url.openConnection();
            connection.connect();

            InputStream input = new BufferedInputStream(url.openStream(), 8192);
            OutputStream output = new FileOutputStream(f_url[1]);

            byte[] data = new byte[1024];
            int lenghtOfFile = connection.getContentLength();
            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                publishProgress(""+(int)((total*100)/lenghtOfFile));
                output.write(data, 0, count);
            }

            output.flush();

            output.close();
            input.close();

            success = true;
        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
            success = false;
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        callback.onProgress(Integer.parseInt(progress[0]));
    }

    @Override
    protected void onPostExecute(String file_url) {
        callback.onSave(success);
    }
}
