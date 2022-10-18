package com.example.synthesizer;

import java.util.Arrays;

public class AudioClip {
    public int duration = 2;
    public int sampleRate = 44100;
    public static int TOTAL_SAMPLES = 5;
    public byte[] audioArray = new byte[duration * sampleRate * 2];

    public int getSample(int index) {
        int least = Byte.toUnsignedInt(audioArray[2 * index]);
        int most = audioArray[2 * index + 1];
        most = most << 8;
        int sample = most | least;
        return sample;
    }

    public void setSample(int index, int value) {
        byte least = (byte) value;
        byte most = (byte) (value >> 8);
        audioArray[2 * index] = least;
        audioArray[2 * index + 1] = most;
    }
    public byte[] getData() {
        return Arrays.copyOf(audioArray, duration * sampleRate * 2);
    }
}
