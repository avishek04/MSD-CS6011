package com.example.synthesizer;
import java.lang.Math;

public class SquareWave implements AudioComponent {
    float frequency;
    float sampleRate = 44100;
    boolean hasInput;

    SquareWave(float freq) {
        frequency = freq;
    }
    @Override
    public AudioClip getClip() {
        AudioClip clip = new AudioClip();
        int sampleVal;
        for (int i = 0; i < 88200; i++) {
            if(( frequency * i / sampleRate) % 1 > 0.5) {
                sampleVal = Short.MAX_VALUE;
            }
            else {
                sampleVal = -Short.MAX_VALUE;
            }
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
        if (input != null) {
            hasInput = true;
        }
        frequency += ((SquareWave)input).frequency;
    }
}
