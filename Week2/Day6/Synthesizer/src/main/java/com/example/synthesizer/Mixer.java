package com.example.synthesizer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Mixer implements AudioComponent {
    //ArrayList<AudioPair> audioList;
    ArrayList<AudioClip> audioList;

    Mixer() {
        audioList = new ArrayList<>();
    }
//    Mixer(AudioClip[] audioClips) {
//        if (audioList == null) {
//            audioList = audioClips;
//        }
//    }

//    Mixer(ArrayList<AudioComponent> list) {
//        if (audioList == null) {
//            audioList = list;
//        }
//        else {
//            audioList.addAll(list);
//        }
//    }

    @Override
    public AudioClip getClip() {
        AudioClip mix = new AudioClip();
//        byte[] arr1 = audioList.get(0).getClip().audioArray;
//        byte[] arr2 = audioList.get(1).getClip().audioArray;
        try {
            for (int i = 0; i < 88200; i++) {
                short mixWave = 0;
                for (int j = 0; j < audioList.size(); j++) {
                    mixWave += audioList.get(j).getSample(i);
                }
                mix.setSample(i, mixWave/2);
            }
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return mix;
    }

    @Override
    public boolean hasInput() {
        if (audioList != null) {
            return true;
        }
        return false;
    }

    @Override
    public void connectInput(AudioComponent input) {
        if (audioList != null) {
            audioList.add(input.getClip());
        }
    }
}
