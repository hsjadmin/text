package cn.logicalthinking.common.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * http下载器
 */
public class HttpDownloader {
    private final String url, destFilename;

    public HttpDownloader(String url, String destFilename) {
        this.url = url;
        this.destFilename = destFilename;
    }

    public void download(Callback callback) {
        try (FileOutputStream fos = new FileOutputStream(destFilename)) {
            URLConnection connection = new URL(url).openConnection();
            long fileSize = connection.getContentLengthLong();
            InputStream inputStream = connection.getInputStream();
            byte[] buffer = new byte[10 * 1024 * 1024];
            int numberOfBytesRead;
            long totalNumberOfBytesRead = 0;
            while ((numberOfBytesRead = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, numberOfBytesRead);
                totalNumberOfBytesRead += numberOfBytesRead;
                callback.onProgress(totalNumberOfBytesRead * 100 / fileSize);
            }
            callback.onFinish();

        } catch (IOException ex) {
            callback.onError(ex);
        }
    }

    public interface Callback {

        void onProgress(long progress);

        void onFinish();

        void onError(IOException ex);
    }
}
