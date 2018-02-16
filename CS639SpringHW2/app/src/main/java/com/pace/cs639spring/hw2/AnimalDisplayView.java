package com.pace.cs639spring.hw2;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Created by RICHIE on 16-02-2018.
 */

public class AnimalDisplayView extends Fragment {


    public static ListView mListView;
    public final String TAG = "AnimalDisplayFragment";

    View mAnimalDisplayView;
    EditText mAddDescEditText;
    Button mAddDescBtn;
    AnimalDisplayListViewAdapter mAdapter;
    ImageButton redColor,blueColor,greencolor,orangeColor,purpleColor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mAnimalDisplayView = inflater.inflate(R.layout.view_display_content, container, false);
        mListView = (ListView)mAnimalDisplayView.findViewById(R.id.imgTxtViewLstView);

        configureListView();
        return mAnimalDisplayView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAddDescBtn = mAnimalDisplayView.findViewById(R.id.descAddBtn);
        mAddDescEditText = mAnimalDisplayView.findViewById(R.id.descAddEditText);
        mAddDescBtn.setOnClickListener(addDescBtnOnClickListener());

        redColor = mAnimalDisplayView.findViewById(R.id.ColorRedImage);
        orangeColor = mAnimalDisplayView.findViewById(R.id.ColorOrangeImage);
        greencolor = mAnimalDisplayView.findViewById(R.id.ColorGreenImage);
        blueColor = mAnimalDisplayView.findViewById(R.id.ColorBlueImage);
        purpleColor = mAnimalDisplayView.findViewById(R.id.ColorPurpleImage);

        redColor.setOnClickListener(colorButtonClickListener());
        orangeColor.setOnClickListener(colorButtonClickListener());
        greencolor.setOnClickListener(colorButtonClickListener());
        blueColor.setOnClickListener(colorButtonClickListener());
        purpleColor.setOnClickListener(colorButtonClickListener());

    }

    public View.OnClickListener colorButtonClickListener(){
        View.OnClickListener vocl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ImageButton ib = (ImageButton) view;
                ColorDrawable dc = (ColorDrawable)ib.getBackground();
                int colorId = dc.getColor();

                //Check if Any Animal/Bird is selected, if selected change the color else display Toast message
                if(mAdapter.mSelectedAnimal!=null){
                    mAdapter.mSelectedAnimal.mImageView.setColorFilter(colorId);
                }
                else{
                    Toast.makeText(view.getContext(),R.string.image_not_selected,Toast.LENGTH_SHORT).show();
                }
            }
        };
        return vocl;
    }

    public View.OnClickListener addDescBtnOnClickListener(){
        View.OnClickListener vocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newDesc = mAddDescEditText.getText().toString();
                if(newDesc.equalsIgnoreCase("")){
                    Toast.makeText(mAnimalDisplayView.getContext() , R.string.desc_not_added, Toast.LENGTH_SHORT).show();
                    return;
                }
                if(null!=mAdapter.mSelectedAnimal){
                    mAdapter.mSelectedAnimal.mAnimalDescList.add(newDesc);
                    mAddDescEditText.setText("");
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mAddDescEditText.getWindowToken(), 0);
                }
                else{
                    Toast.makeText(mAnimalDisplayView.getContext() , R.string.image_not_selected, Toast.LENGTH_SHORT).show();
                }
            }
        };

        return vocl;
    }

    public void configureListView(){
        List<AnimalDetailsInfo> animalDescriptionList = new ArrayList<>();

        animalDescriptionList.add(new AnimalDetailsInfo(R.drawable.cat, Arrays.asList(getResources().getString(R.string.cat_description),"Cats are Fluffy","Cats were worshiped by Egyptian" ),0));
        animalDescriptionList.add(new AnimalDetailsInfo(R.drawable.dog, Arrays.asList(getResources().getString(R.string.dog_description),"Dogs are most peted animal in USA"),0));
        animalDescriptionList.add(new AnimalDetailsInfo(R.drawable.bird, Arrays.asList(getResources().getString(R.string.bird_description)),0));
        animalDescriptionList.add(new AnimalDetailsInfo(R.drawable.cat, Arrays.asList(getResources().getString(R.string.cat_description),"Cats are Fluffy","Cats were worshiped by Egyptian" ),0));
        animalDescriptionList.add(new AnimalDetailsInfo(R.drawable.dog, Arrays.asList(getResources().getString(R.string.dog_description),"Dogs are most peted animal in USA"),0));
        animalDescriptionList.add(new AnimalDetailsInfo(R.drawable.bird, Arrays.asList(getResources().getString(R.string.bird_description)),0));
        /*animalDescriptionList.add(new AnimalDescription(R.drawable.bird, Arrays.asList(getResources().getString(R.string.bird_description)),0));
        animalDescriptionList.add(new AnimalDescription(R.drawable.dog, Arrays.asList(getResources().getString(R.string.dog_description)),0));
        animalDescriptionList.add(new AnimalDescription(R.drawable.cat, Arrays.asList(getResources().getString(R.string.cat_description)),0));
        animalDescriptionList.add(new AnimalDescription(R.drawable.bird, Arrays.asList(getResources().getString(R.string.bird_description)),0));
        animalDescriptionList.add(new AnimalDescription(R.drawable.dog, Arrays.asList(getResources().getString(R.string.dog_description)),0));
        animalDescriptionList.add(new AnimalDescription(R.drawable.cat, Arrays.asList(getResources().getString(R.string.cat_description)),0));
        animalDescriptionList.add(new AnimalDescription(R.drawable.bird, Arrays.asList(getResources().getString(R.string.bird_description)),0));*/

        mAdapter = new AnimalDisplayListViewAdapter(mAnimalDisplayView.getContext(), animalDescriptionList);
        mListView.setAdapter(mAdapter);

    }
}
