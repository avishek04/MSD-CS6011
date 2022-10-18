package com.example.synthesizer;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class Music {
    public void Music() {
        try {
            // AudioSystem is a class from the Java standard library.
            Clip c = AudioSystem.getClip();

            // This is the format that we're following, 44.1 KHz mono audio, 16 bits per sample.
            AudioFormat format16 = new AudioFormat( 44100, 16, 1, true, false );

            AudioComponent sin = new SineWave(25000); // Your code
//            AudioComponent mix = new SineWave(900);
            //AudioComponent sqr = new SquareWave(500);
//            AudioComponent white = new WhiteNoise();
//
//            VFSineWave vfs = new VFSineWave();
//            LinearRamp lr = new LinearRamp(50, 10000);
//            vfs.connectInput(lr);
//            AudioClip clip = vfs.getClip();
//           // AudioComponent mix = new SquareWave(900);
//            AudioClip[] audio = {sin.getClip(), sqr.getClip()};
//            AudioComponent mix = new Mixer(audio);
//            mix.connectInput(sqr);
           // AudioClip clip = mix.getClip();
            Filter volume = new Filter(0.9);
            AudioClip clip = volume.ApplyFilter(sin);
            //AudioClip clip = white.getClip();


            //AudioClip clip = gen.getClip();         // Your code

            c.open( format16, clip.getData(), 0, clip.getData().length ); // Reads data from our byte array to play it.

            System.out.println( "About to play..." );
            c.start(); // Plays it.
            c.loop( 2 ); // Plays it 2 more times if desired, so 6 seconds total

// Makes sure the program doesn't quit before the sound plays.
            while( c.getFramePosition() < AudioClip.TOTAL_SAMPLES || c.isActive() || c.isRunning() ){
                // Do nothing while we wait for the note to play.
            }

            System.out.println( "Done." );
            c.close();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
}
