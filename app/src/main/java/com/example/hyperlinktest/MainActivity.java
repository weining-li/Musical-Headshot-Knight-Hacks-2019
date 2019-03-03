package com.example.hyperlinktest;

import java.io.*;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.graphics.*;
import android.widget.*;
import android.provider.*;
import com.microsoft.projectoxford.face.*;
import com.microsoft.projectoxford.face.contract.*;

import java.security.SecureRandom;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    private final int PICK_IMAGE = 1;
    private ProgressDialog detectionProgressDialog;


    private final String apiEndpoint = "https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect?&returnFaceAttributes";

    private final String subscriptionKey = "6d672a71190c4e3ba06e8625d44898d4";

    private final FaceServiceClient faceServiceClient =
            new FaceServiceRestClient(apiEndpoint, subscriptionKey);

    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static String emo = "Angry";
    SecureRandom rand = new SecureRandom();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Welcome!", Toast.LENGTH_LONG);
        toast.show();
        setContentView(R.layout.activity_main);

        final ArrayList<String> anger = new ArrayList<>(Arrays.asList("https://www.youtube.com/watch?v=Wv0KwOEYKU4", "https://www.youtube.com/watch?v=qeMFqkcPYcg", "https://www.youtube.com/watch?v=FLZS3jQPnKw", "https://www.youtube.com/watch?v=l9PxOanFjxQ", "https://www.youtube.com/watch?v=iC8oP4Z_xPw"));
        final ArrayList<String> sad = new ArrayList<>(Arrays.asList("https://www.youtube.com/watch?v=k4V3Mo61fJM", "https://www.youtube.com/watch?v=CjxugyZCfuw", "https://www.youtube.com/watch?v=j3BA0IQd_lA", "https://www.youtube.com/watch?v=Xn676-fLq7I", "https://www.youtube.com/watch?v=DzwkcbTQ7ZE"));
        final ArrayList<String> happy = new ArrayList<>(Arrays.asList("https://www.youtube.com/watch?v=ZbZSe6N_BXs", "https://www.youtube.com/watch?v=09R8_2nJtjg", "https://www.youtube.com/watch?v=cmSbXsFE3l8", "https://www.youtube.com/watch?v=ru0K8uYEZWw", "https://www.youtube.com/watch?v=7PCkvCPvDXk"));
        final ArrayList<String> disgusted = new ArrayList<>(Arrays.asList("https://www.youtube.com/watch?v=y6120QOlsfU", "https://www.youtube.com/watch?v=wZZ7oFKsKzY", "https://www.youtube.com/watch?v=pBI3lc18k8Q", "https://www.youtube.com/watch?v=jofNR_WkoCE", "https://www.youtube.com/watch?v=HMUDVMiITOU"));
        final ArrayList<String> neutral = new ArrayList<>(Arrays.asList("https://www.youtube.com/watch?v=DDgdoPSkPnM", "https://www.youtube.com/watch?v=Cl8a9b76GMg", "https://www.youtube.com/watch?v=CPF9eIBBV7c", "https://www.youtube.com/watch?v=qq-RGFyaq0U", "https://www.youtube.com/watch?v=oHg5SJYRHA0"));
        final ArrayList<String> suprise = new ArrayList<>(Arrays.asList("https://www.youtube.com/watch?v=R3WwcsjWPIQ", "https://www.youtube.com/watch?v=U9FzgsF2T-s", "https://www.youtube.com/watch?v=9OPc7MRm4Y8", "https://www.youtube.com/watch?v=yRh-dzrI4Z4", "https://www.youtube.com/watch?v=zHalXjs0cDA"));
        final ArrayList<String> contempt = new ArrayList<>(Arrays.asList("https://www.youtube.com/watch?v=6LAPFM3dgag", "https://www.youtube.com/watch?v=FCi2u265wxQ", "https://www.youtube.com/watch?v=-YcwR89cfao", "https://www.youtube.com/watch?v=HMjQygwPI1c", "https://www.youtube.com/watch?v=75x6DncZDgI"));
        final ArrayList<String> fear = new ArrayList<>(Arrays.asList("https://www.youtube.com/watch?v=w0ZHlp6atUQ", "https://www.youtube.com/watch?v=cPAbx5kgCJo\n", "https://www.youtube.com/watch?v=2aqpF-MwyUs", "https://www.youtube.com/watch?v=TZ0pXUb5jVU", "https://www.youtube.com/watch?v=PAD_E1kaYuY"));
        File angry = new File("src/main/java/com/example/hyperlinktest/angry.txt");
        System.out.println(angry.exists());
        final Button myButton = findViewById(R.id.button3);


        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url;
                if (emo.equals("Angry")) {
                    int i = rand.nextInt(5);
                    url = anger.get(i);

                } else if (emo.equals("Contempt")) {
                    int i = rand.nextInt(5);
                    url = contempt.get(i);

                } else if (emo.equals("Disgusted")) {
                    int i = rand.nextInt(5);
                    url = disgusted.get(i);

                } else if (emo.equals("Afraid")) {
                    int i = rand.nextInt(5);
                    url = fear.get(i);

                } else if (emo.equals("Happy")) {
                    int i = rand.nextInt(5);
                    url = happy.get(i);

                } else if (emo.equals("Calm")) {
                    int i = rand.nextInt(5);
                    url = neutral.get(i);

                } else if (emo.equals("Sad")) {
                    int i = rand.nextInt(5);
                    url = sad.get(i);

                } else if (emo.equals("Surprised")) {
                    int i = rand.nextInt(5);
                    url = suprise.get(i);

                } else {
                    url = "https://en.wikipedia.org/wiki/HTTP_404";
                }

                Intent i = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(url));
                startActivity(i);
            }
        });

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(
                        intent, "Select Picture"), PICK_IMAGE);
                myButton.setVisibility(View.VISIBLE);





            }
        });

        detectionProgressDialog = new ProgressDialog(this);

        this.imageView = (ImageView) this.findViewById(R.id.imageView1);
        Button photoButton = (Button) this.findViewById(R.id.button2);
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MyCameraImages");
                imagesFolder.mkdirs();
                File image = new File(imagesFolder, "image.jpg");
                Uri uriSavedImage = Uri.fromFile(image);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
                myButton.setVisibility(View.VISIBLE);


                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, "Good Choice!", Toast.LENGTH_LONG);
                toast.show();



            }
        });
    }

    private File createTemporaryFile(String part, String ext) throws Exception {
        File tempDir = Environment.getExternalStorageDirectory();
        tempDir = new File(tempDir.getAbsolutePath() + "/.temp/");
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }
        return File.createTempFile(part, ext, tempDir);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK &&
                data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                        getContentResolver(), uri);
                ImageView imageView = findViewById(R.id.imageView1);
                imageView.setImageBitmap(bitmap);

                // Comment out for tutorial
                detectAndFrame(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            detectAndFrame(photo);
        }

        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "Good Choice!", Toast.LENGTH_LONG);
        toast.show();


    }


    // Detect faces by uploading a face image.
// Frame faces after detection.
    private void detectAndFrame(final Bitmap imageBitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(outputStream.toByteArray());

        AsyncTask<InputStream, String, Face[]> detectTask =
                new AsyncTask<InputStream, String, Face[]>() {
                    String exceptionMessage = "";

                    @Override
                    protected Face[] doInBackground(InputStream... params) {
                        try {
                            publishProgress("Detecting...");
                            Face[] result = faceServiceClient.detect(
                                    params[0],
                                    true,         // returnFaceId
                                    false,        // returnFaceLandmarks
                                    // returnFaceAttributes:
                                    new FaceServiceClient.FaceAttributeType[]{
                                            FaceServiceClient.FaceAttributeType.Age,
                                            FaceServiceClient.FaceAttributeType.Gender,
                                            FaceServiceClient.FaceAttributeType.Emotion}
                            );
                            if (result == null) {
                                publishProgress(
                                        "Detection Finished. Nothing detected");
                                return null;
                            }
                            publishProgress(String.format(
                                    "Detection Finished. %d face(s) detected",
                                    result.length));


                            return result;
                        } catch (Exception e) {
                            exceptionMessage = String.format(
                                    "Detection failed: %s", e.getMessage());
                            return null;
                        }
                    }

                    @Override
                    protected void onPreExecute() {
                        //TODO: show progress dialog
                        detectionProgressDialog.show();
                    }

                    @Override
                    protected void onProgressUpdate(String... progress) {
                        //TODO: update progress
                        detectionProgressDialog.setMessage(progress[0]);
                    }

                    @Override
                    protected void onPostExecute(Face[] result) {
                        //TODO: update face frames
                        detectionProgressDialog.dismiss();

                        if (!exceptionMessage.equals("")) {
                            showError(exceptionMessage);
                        }
                        if (result == null) return;

                        ImageView imageView = findViewById(R.id.imageView1);
                        imageView.setImageBitmap(
                                drawFaceRectanglesOnBitmap(imageBitmap, result));
                        imageBitmap.recycle();
                    }
                };

        detectTask.execute(inputStream);
    }

    private void showError(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                })
                .create().show();
    }

    private static Bitmap drawFaceRectanglesOnBitmap(
            Bitmap originalBitmap, Face[] faces) {
        Bitmap bitmap = originalBitmap.copy(Bitmap.Config.ARGB_8888, true);
        int height = originalBitmap.getHeight();
        int width = originalBitmap.getWidth();
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(height / 800);
        if (faces != null) {
            for (Face face : faces) {
                Emotion emotionScores = face.faceAttributes.emotion;

                FaceRectangle faceRectangle = face.faceRectangle;
                emo = "Angry";
                double high = emotionScores.anger;
                if (emotionScores.contempt > high) {
                    high = emotionScores.contempt;
                    emo = "Contempt";
                }
                if (emotionScores.disgust > high) {
                    emo = "Disgusted";
                    high = emotionScores.disgust;
                }
                if (emotionScores.fear > high) {
                    emo = "Afraid";
                    high = emotionScores.fear;
                }

                if (emotionScores.happiness > high) {
                    emo = "Happy";
                    high = emotionScores.happiness;
                }
                if (emotionScores.neutral > high) {
                    emo = "Calm";
                    high = emotionScores.neutral;
                }
                if (emotionScores.sadness > high) {
                    emo = "Sad";
                    high = emotionScores.sadness;
                }
                if (emotionScores.surprise > high) {
                    emo = "Surprised";
                    high = emotionScores.surprise;
                }

                String fa = (int) face.faceAttributes.age + "";
                String fg = face.faceAttributes.gender;
                String fe = emo;
                canvas.drawRect(
                        faceRectangle.left,
                        faceRectangle.top,
                        faceRectangle.left + faceRectangle.width,
                        faceRectangle.top + faceRectangle.height,
                        paint);
                paint.setStrokeWidth(2);
                int fontSize = (height / 12 + width / 12) / 2;
                paint.setTextSize(fontSize);
                canvas.drawText(fa, (float) (float) faceRectangle.left + faceRectangle.width / 8, faceRectangle.top + faceRectangle.height / 4, paint);
                canvas.drawText(fg, (float) (float) faceRectangle.left + faceRectangle.width / 8, faceRectangle.top + faceRectangle.height / 4 + fontSize, paint);
                canvas.drawText(fe, (float) (float) faceRectangle.left + faceRectangle.width / 8, faceRectangle.top + faceRectangle.height / 4 + 2 * fontSize, paint);



            }
        }

        return bitmap;
    }
}

