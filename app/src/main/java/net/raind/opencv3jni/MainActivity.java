package net.raind.opencv3jni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.view.View;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button btnProc;
    private ImageView imageView;
    private Bitmap bmp;
    private Bitmap scr;
    private EditText MyeditText;

    public static native int[] grayProc(int[] pixels, int w, int h,int threshv);

    static {
        System.loadLibrary("gray-process");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyeditText=(EditText)findViewById(R.id.edit_text);
        btnProc = (Button) findViewById(R.id.btn_gray_process);
        imageView = (ImageView) findViewById(R.id.image_view);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.pic);
        scr = BitmapFactory.decodeResource(getResources(), R.drawable.scr);
        imageView.setImageBitmap(bmp);
        btnProc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int w = bmp.getWidth();
        int h = bmp.getHeight();
        int[] pixels = new int[w*h];
        int u = Integer.parseInt(MyeditText.getText().toString());
        MyeditText.selectAll();
        bmp.getPixels(pixels, 0, w, 0, 0, w, h);
        int[] resultInt = grayProc(pixels, w, h, u);
        Bitmap resultImg = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        resultImg.setPixels(resultInt, 0, w, 0, 0, w, h);
        imageView.setImageBitmap(resultImg);
    }

    @Override
    public void onResume(){
        super.onResume();
    }
}
