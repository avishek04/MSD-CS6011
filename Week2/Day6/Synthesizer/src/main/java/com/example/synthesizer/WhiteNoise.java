package com.example.synthesizer;
import java.lang.Math;
import java.util.concurrent.ThreadLocalRandom;

public class WhiteNoise implements AudioComponent {
    boolean hasInput;
    @Override
    public AudioClip getClip() {
        AudioClip clip = new AudioClip();
        for (int i = 0; i < 88200; i++) {
            int sampleVal = ThreadLocalRandom.current().nextInt(Short.MIN_VALUE, Short.MAX_VALUE + 1);
            clip.setSample(i, sampleVal);
        }
        return clip;
    }

    @Override
    public boolean hasInput() {
        return hasInput;
    }

    @Override
    public void connectInput(AudioComponent input) {
    }
}

