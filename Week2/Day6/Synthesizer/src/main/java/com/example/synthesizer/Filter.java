package com.example.synthesizer;

public class Filter {
    double volumeScale;
    Filter (double volScale) {
        volumeScale = volScale;
    }
    public AudioClip ApplyFilter(AudioComponent clip) {
        AudioClip audioClip = clip.getClip();
        AudioClip filteredClip = new AudioClip();
        for (int i = 0; i < 88200; i++) {
            double sample = volumeScale * audioClip.getSample(i);
            filteredClip.setSample(i, sample <= Short.MAX_VALUE ? (int) sample : Short.MAX_VALUE);
        }
        return filteredClip;
    }
}
