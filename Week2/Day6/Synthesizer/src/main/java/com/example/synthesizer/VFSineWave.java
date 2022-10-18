package com.example.synthesizer;

public class VFSineWave {
    float frequency;
    float sampleRate = 44100;
    int numSamples = 88200;
    int phase = 0;
    AudioClip inputClip;
    boolean hasInput;
    //VFSineWave(float freq) {
//        frequency = freq;
//    }
    public AudioClip getClip() {
        AudioClip clip = new AudioClip();
        if (hasInput) {
            for (int i = 0; i < numSamples; i++) {
                phase += 2 * Math.PI * inputClip.getSample( i ) / sampleRate;
                int sampleVal = (int) (Short.MAX_VALUE * Math.sin( phase ));
                clip.setSample(i, sampleVal);
            }
        }
        return clip;
    }

    public boolean hasInput() {
        return hasInput;
    }

    public void connectInput(LinearRamp input) {
        if (input != null) {
            hasInput = true;
        }
        inputClip = input.getClip();
    }
}

