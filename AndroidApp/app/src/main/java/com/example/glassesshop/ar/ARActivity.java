package com.example.glassesshop.ar;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.glassesshop.R;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ARActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    // distance from camera to object(face) measured
    private final double KNOWN_DISTANCE = 76.2;  // centimeter
    // width of face in the real world or Object Plane
    private final double KNOWN_WIDTH = 14.3;  // centimeter
    private final double FOCAL_LENGTH = 1000;


    private JavaCameraView javaCameraView;
    private File cascFile;
    private CascadeClassifier faceDetector;
    private Mat mRgba, mGrey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_aractivity);

        javaCameraView = (JavaCameraView)findViewById(R.id.view);

        if (!OpenCVLoader.initDebug()) {
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, baseLoaderCallback);
        }
        else {
            baseLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }

        javaCameraView.setVisibility(CameraBridgeViewBase.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat();
        mGrey = new Mat();
    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();
        mGrey.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        Imgproc.cvtColor(inputFrame.rgba(), mRgba, Imgproc.COLOR_BGR2RGB);

        double faceWidth = getFaceWidth(mRgba);

        if (faceWidth != 0)
        {

            double distance = getDistance(FOCAL_LENGTH, KNOWN_WIDTH, faceWidth);

            Imgproc.putText(mRgba, "D: " + String.valueOf(Math.round(distance)),
                    new Point(50, 50), 3, 1, new Scalar(255, 0, 0, 255), 2);
        }

        return mRgba;
    }

    private BaseLoaderCallback baseLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    try{
                        InputStream is = getResources().openRawResource(R.raw.haarcascade_frontalcatface);
                        File cascadeDir = getDir("cascade", Context.MODE_PRIVATE);
                        cascFile = new File(cascadeDir, "haarcascade_frontalface.xml");
                        FileOutputStream os = new FileOutputStream(cascFile);

                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        while ((bytesRead = is.read(buffer)) != -1) {
                            os.write(buffer, 0, bytesRead);
                        }
                        is.close();
                        os.close();

                        faceDetector = new CascadeClassifier(cascFile.getAbsolutePath());

                        if (faceDetector.empty()) {
                            faceDetector = null;
                        } else {
                            cascadeDir.delete();
                        }

                        javaCameraView.enableView();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("err", "Failed to load cascade. Exception thrown: " + e);
                    }
                } break;

                default:
                    super.onManagerConnected(status);
                    break;
            }
        }
    };

    private double getFocalLength(double measuredDistance, double realWidth, double widthInRfImage) {
        double focalLengthValue = (widthInRfImage * measuredDistance) / realWidth;
        return focalLengthValue;
    }

    private double getDistance(double focalLength, double realFaceWidth, double faceWidthInFrame) {
        double distance = (realFaceWidth * focalLength) / faceWidthInFrame;
        return distance;
    }

    private double getFaceWidth(Mat imgGrey) {
        double faceWidth = 0;

        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(imgGrey, faceDetections, 1.3, 5);

        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(mRgba, new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(255, 0, 0));

            faceWidth = rect.width;
        }

        return faceWidth;
    }
}