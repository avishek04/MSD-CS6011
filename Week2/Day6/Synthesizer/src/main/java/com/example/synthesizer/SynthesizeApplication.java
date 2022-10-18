package com.example.synthesizer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javax.sound.sampled.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.util.ArrayList;

public class SynthesizeApplication extends Application {
    AnchorPane mainCanvas_ ;
    public static Circle Speaker ;
    public static ArrayList<AudioComponentWidget> widgets_ = new ArrayList<>() ;
    public static ArrayList<AudioComponentWidget> connectedWidgetsToSpeaker_ = new ArrayList<>() ;
    @Override
    public void start( Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Audio Synthesizer");
        ///////////////////////////////////////////////////
        // Right Pane of the Border Pane
        // Adding Buttons
        VBox rightPanel = new VBox();
        rightPanel.setPadding(new Insets(10));
        rightPanel.setStyle("-fx-background-color: darkgrey");

        Button SineWaveButton = new Button("Sine Wave");
        rightPanel.getChildren().add(SineWaveButton);

        Button SquareWaveButton = new Button("Square Wave");
        rightPanel.getChildren().add(SquareWaveButton);

        SineWaveButton.setOnAction(e -> createComponent("SineWave"));
        SquareWaveButton.setOnAction(e -> createComponent("SquareWave"));
        /////////////////////////////////////////////////////////
        // Center Panel
        mainCanvas_ = new AnchorPane() ;
        mainCanvas_.setStyle("-fx-background-color:grey");
        Speaker = new Circle(400,200,15) ;
        Speaker.setFill(Color.BLACK);
        mainCanvas_.getChildren().add(Speaker);
        ////////////////////////////////////////////////////////////
        // Bottom panel
        HBox bottomPanel = new HBox() ;
        Button playButton = new Button("Play") ;
        bottomPanel.getChildren().add(playButton);
        playButton.setOnAction(e -> playNetwork());
        root.setRight(rightPanel);
        root.setCenter(mainCanvas_);
        root.setBottom(bottomPanel);
        stage.setScene(scene);
        stage.show();
    }
    private void playNetwork()
    {
        if(widgets_.size() == 0)
        {
            return ;
        }
        try {
            Clip c = AudioSystem.getClip();
            AudioListener listener = new AudioListener(c) ;
            Mixer mixer = new Mixer();
            AudioComponent ac = null;
            for(AudioComponentWidget w : connectedWidgetsToSpeaker_)
            {
                ac = w.getAudioComponent();
                mixer.connectInput(ac);
            }
            AudioFormat format = new AudioFormat( 44100, 16, 1, true, false );
            //AudioComponentWidget acw = widgets_.get(0) ;
            //AudioComponent ac = acw.getAudioComponent();
            byte[] data = mixer.getClip().getData();
            c.open( format, data, 0, data.length ); // Reads data from our byte array to play it.
            c.start();
            c.addLineListener(listener);
        }
        catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }
    private void createComponent(String wave)
    {
        AudioComponent ac = null;
        if (wave.equals("SineWave")) {
            ac = new SineWave(440);
        }
        else if (wave.equals("SquareWave")) {
            ac = new SquareWave(440);
        }
        AudioComponentWidget acw = new AudioComponentWidget(ac,mainCanvas_,wave) ;
        System.out.println("Component Created ");
        widgets_.add(acw) ;
    }
    public static void main(String[] args) {
        launch();
    }
}