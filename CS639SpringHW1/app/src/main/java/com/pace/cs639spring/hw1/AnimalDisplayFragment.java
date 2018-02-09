package com.pace.cs639spring.hw1;

import android.app.Fragment;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by kachi on 1/31/18.
 */

public class AnimalDisplayFragment extends Fragment {

    AnimalDetails dogDetails,catDetails,birdDetails,choosenBox;

    ImageButton redColor,greenColor,blueColor,orangeColor,purpleColor;
    Map<Integer,AnimalDetails> hashMap = new HashMap<Integer,AnimalDetails>();

    class AnimalDetails{

        ImageView imageBlock;
        TextView textBlock;
        String str;
        int index;

        public AnimalDetails(String type,ImageView iB,TextView tB,int indx){
            str = type;
            imageBlock = iB;
            textBlock = tB;
            index = indx;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.animal_display, container, false);

        // Creating an object of onClickListeners for ImageView (Animal/Bird image) and bottom Color Buttons;
        ImageClickListener ICL = new ImageClickListener();
        ColorSetterClickListener CSCL = new ColorSetterClickListener();


        dogDetails = new AnimalDetails("Dog",(ImageView)view.findViewById(R.id.dogImage),(TextView)view.findViewById(R.id.dogTextView),R.id.dogImage);
        dogDetails.imageBlock.setOnClickListener(ICL);
        dogDetails.imageBlock.setColorFilter(R.color.colorWhite, PorterDuff.Mode.SRC_IN);

        catDetails = new AnimalDetails("Cat",(ImageView)view.findViewById(R.id.catImage),(TextView)view.findViewById(R.id.catTextView),R.id.catImage);
        catDetails.imageBlock.setOnClickListener(ICL);
        catDetails.imageBlock.setColorFilter(R.color.colorWhite, PorterDuff.Mode.SRC_IN);

        birdDetails = new AnimalDetails("Bird",(ImageView)view.findViewById(R.id.birdImage),(TextView)view.findViewById(R.id.birdTextView),R.id.birdImage);
        birdDetails.imageBlock.setOnClickListener(ICL);
        birdDetails.imageBlock.setColorFilter(R.color.colorWhite, PorterDuff.Mode.SRC_IN);


        hashMap.put(R.id.dogImage,dogDetails);
        hashMap.put(R.id.birdImage,birdDetails);
        hashMap.put(R.id.catImage,catDetails);


        redColor = (ImageButton)view.findViewById(R.id.ColorRedImage);
        greenColor = (ImageButton)view.findViewById(R.id.ColorGreenImage);
        orangeColor = (ImageButton)view.findViewById(R.id.ColorOrangeImage);
        blueColor = (ImageButton)view.findViewById(R.id.ColorBlueImage);
        purpleColor = (ImageButton)view.findViewById(R.id.ColorPurpleImage);

        redColor.setOnClickListener(CSCL);
        greenColor.setOnClickListener(CSCL);
        orangeColor.setOnClickListener(CSCL);
        blueColor.setOnClickListener(CSCL);
        purpleColor.setOnClickListener(CSCL);

        return view;


    }
    class ImageClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(TAG,"Inside ImageView OnClick Event (Animal/Bird Click)");

            ImageView IV = (ImageView) view;
            int IVId = IV.getId();
            // Check if any Animal/Bird is selected or not. If not selected then make their color as Black and set TextView visibility to Visible
            if(choosenBox==null){
                IV.setColorFilter(R.color.colorAccent);
                hashMap.get(IVId).textBlock.setVisibility(View.VISIBLE);
                choosenBox = hashMap.get(IVId);
            }
            else{
                // If same Animal/Bird is selected, Change its color to White and Text to Invisible
                if(choosenBox.index==IVId){
                    IV.setColorFilter(R.color.colorWhite, PorterDuff.Mode.SRC_IN);
                    hashMap.get(IVId).textBlock.setVisibility(View.INVISIBLE);
                    choosenBox = null;
                }// If any other Animal/Bird is selected, change the color or previous selected component to White and text to Invisible and set the new component
                else{
                    hashMap.get(choosenBox.index).imageBlock.setColorFilter(R.color.colorWhite, PorterDuff.Mode.SRC_IN);
                    hashMap.get(choosenBox.index).textBlock.setVisibility(View.INVISIBLE);
                    IV.setColorFilter(R.color.colorAccent);
                    hashMap.get(IVId).textBlock.setVisibility(View.VISIBLE);
                    choosenBox = hashMap.get(IVId);
                }
            }
        }
    }

    class ColorSetterClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.i(TAG,"Inside ColorButton OnClick Event");

            ImageButton ib = (ImageButton) view;
            ColorDrawable dc = (ColorDrawable)ib.getBackground();
            int colorId = dc.getColor();

            //Check if Any Animal/Bird is selected, if selected change the color else display Toast message
            if(choosenBox!=null){
                choosenBox.imageBlock.setColorFilter(colorId);
            }
            else{
                Toast.makeText(view.getContext(),"No!!!!! You Must Select the image if you want to change the color", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
