package com.example.android.stagstreet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by jaswanth on 10/06/17.
 */

public class main_activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Bitmap bitmapOriginal;

        Button but1=(Button) findViewById(R.id.button);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(main_activity.this,sidemenu.class);
                startActivity(intent);
            }
        });


        /**
         * created the round shape for the profile pic
         */
        //getting the refrence fro the xml code by the id
        ImageView profile_pic = (ImageView) findViewById(R.id.profile_pic);
        //Bitmap it is an animation software
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic);
        //it is usefull for getting the picture round
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        //it assign the picture to circular
        roundedBitmapDrawable.setCircular(true);
        profile_pic.setImageDrawable(roundedBitmapDrawable);
        BlurMaskFilter blurMaskFilter = new BlurMaskFilter(3, BlurMaskFilter.Blur.OUTER);
        ImageView profile_pic_background = (ImageView) findViewById(R.id.profile_background);

        bitmapOriginal = BitmapFactory.decodeResource(getResources(), R.drawable.profile_pic);
        profile_pic_background.setImageBitmap(createBitmap_ScriptIntrinsicBlur(bitmapOriginal, 25.0f));


    }

    private Bitmap createBitmap_ScriptIntrinsicBlur(Bitmap src, float r) {

        //Radius range (0 < r <= 25)
        if (r <= 0) {
            r = 0.1f;
        } else if (r > 25) {
            r = 25.0f;

        }
        Bitmap bitmap = Bitmap.createBitmap(
                src.getWidth(), src.getHeight(),
                Bitmap.Config.ARGB_8888);

        RenderScript renderScript = RenderScript.create(this);

        Allocation blurInput = Allocation.createFromBitmap(renderScript, src);
        Allocation blurOutput = Allocation.createFromBitmap(renderScript, bitmap);

        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(renderScript,
                Element.U8_4(renderScript));
        blur.setInput(blurInput);
        blur.setRadius(r);
        blur.forEach(blurOutput);

        blurOutput.copyTo(bitmap);
        renderScript.destroy();
        return bitmap;
    }

}
