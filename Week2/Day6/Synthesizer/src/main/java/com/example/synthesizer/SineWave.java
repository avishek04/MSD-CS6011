package com.example.synthesizer;
import java.lang.Math;

public class SineWave implements AudioComponent {
    float frequency;
    float sampleRate = 44100;
    boolean hasInput;
    SineWave(float freq) {
        frequency = freq;
    }
    @Override
    public AudioClip getClip() {
        AudioClip clip = new AudioClip();
        for (int i = 0; i < 88200; i++) {
            int sampleVal = (int) (Short.MAX_VALUE * Math.sin( 2 * Math.PI * frequency * i / sampleRate ));
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
        frequency += ((SineWave)input).frequency;
    }
}
