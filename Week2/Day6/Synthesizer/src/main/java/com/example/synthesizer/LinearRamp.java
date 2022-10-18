package com.example.synthesizer;

public class LinearRamp implements AudioComponent {
    public float start;
    public float numSamples = 88200;
    public float stop;

    LinearRamp(int sr, int sp) {
        start = sr;
        stop = sp;
    }

    public AudioClip getClip() {
        AudioClip clip = new AudioClip();
        for (int i = 0; i < numSamples; i++) {
            int sampleVal = (int)(( start * ( numSamples - i ) + stop * i ) / numSamples);
            clip.setSample(i, sampleVal);
        }
        return clip;
    }

    @Override
    public boolean hasInput() {
        return false;
    }

    @Override
    public void connectInput(AudioComponent input) {

    }
}
