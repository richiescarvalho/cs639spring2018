package com.pace.cs639spring.hw2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RICHIE on 16-02-2018.
 */

class AnimalDetailsInfo {
    int mAnimalImageId;
    ArrayList<String> mAnimalDescList;
    int mDescCurrentIndex;

    AnimalDetailsInfo(int animalImageId, List<String> animalDescList, int descCurrentIndex) {
        mAnimalImageId = animalImageId;
        mAnimalDescList = new ArrayList<>(animalDescList);
        mDescCurrentIndex = descCurrentIndex;
    }

}
