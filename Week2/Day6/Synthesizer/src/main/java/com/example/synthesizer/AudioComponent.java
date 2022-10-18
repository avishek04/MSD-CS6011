package com.example.synthesizer;

public interface AudioComponent {
    public AudioClip getClip();
    boolean hasInput();
    void connectInput( AudioComponent input);
}