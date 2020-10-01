
/**
 * Author: Christian Vincent
 * Last edited: 11/25/2018
 * Description: This program creates a clock, adds 12hr and 24hr format support,
 * and lets the user change the color of the text.
 * 
 * The media stage for the national anthem is called
 * in getPane()
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.Calendar;

import javafx.scene.control.Label;
import java.text.SimpleDateFormat;
import javafx.scene.input.KeyCode;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Slider;
import javafx.scene.layout.Region;

public class TimeButtonDemo extends Application {

    //method for creating media for the national anthem
    protected Stage nationalAnthemMedia () {
       
        Media media = new Media("https://r6---sn-25ge7nld.googlevideo.com/vide"
                + "oplayback?beids=9466587&signature=65A9BE6670DE3BD4C6C54ED"
                + "2498BF48459AC618B.25D070BEC639299DCC4E72728BE442595C30D"
                + "99D&key=yt6&ei=MDv_W7uSGIe-hgbSzrWwBw&expire=1543475088"
                + "&requiressl=yes&ipbits=0&sparams=dur%2Cei%2Cid%2Cinitcwn"
                + "dbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2"
                + "Cmv%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&fvi"
                + "p=5&ms=au%2Crdu&ratebypass=yes&mv=m&mt=1543453443&source"
                + "=youtube&itag=22&pl=24&dur=78.344&lmt=1508867185337555&i"
                + "p=144.217.15.31&mime=video%2Fmp4&initcwndbps=453750&id=o"
                + "-ABTbzU6vKsG62b5ue52zKE0MutuRySJLyyyIhke4sMXX&c=WEB&mn=s"
                + "n-25ge7nld%2Csn-25glen7y&mm=31%2C29&title=United+States+"
                + "of+America+National+Anthem+%28Instrumental%29");
        
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        
        mediaView.setFitHeight(550);
        mediaView.setFitWidth(500);
        
        Button playButton = new Button(">");
        playButton.setOnAction(e -> {
           if(playButton.getText().equals(">"))
                {
                    mediaPlayer.play();
                    playButton.setText("||");
                }
           else
                {
                    mediaPlayer.pause();
                    playButton.setText(">");
                }
           
        });
        
        Button rewindButton = new Button("<<");
        rewindButton.setOnAction(e -> {
            mediaPlayer.seek(Duration.ZERO);
        });
        
        Slider slVolume = new Slider();
        slVolume.setPrefWidth(150);
        slVolume.setMaxWidth(Region.USE_PREF_SIZE);
        slVolume.setMinWidth(30);
        slVolume.setValue(50);
        mediaPlayer.volumeProperty().bind(slVolume.valueProperty().divide(100));
        
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(playButton, rewindButton, 
                new Label("Volume", slVolume));
        
        BorderPane pane = new BorderPane();
        pane.setCenter(mediaView);
        pane.setBottom(hBox);
        
        Scene mediaScene = new Scene(pane, 550, 450);
        Stage mediaStage = new Stage();
        mediaStage.setTitle("U.S. National Anthem");
        mediaStage.setScene(mediaScene);
        
        return mediaStage;
        
    }
    
    
    protected BorderPane getPane () {
        
        BorderPane pane = new BorderPane(); // pane for containing buttons and clock
        
        HBox paneForButtons = new HBox(50); // pane for containing buttons
        
        //write code for buttons
        ImageView usaFlag = new ImageView("file:C:\\usa.jpg");
        usaFlag.setFitHeight(25);
        usaFlag.setFitWidth(35);
        ImageView euFlag = new ImageView("file:C:\\eu.jpg");
        euFlag.setFitHeight(25);
        euFlag.setFitWidth(35);
        
        Button twelveHr = new Button("12 hr", usaFlag);
        Button twentyfourHr = new Button("24 hr", euFlag);
    
        
        DigitalClock clock = new DigitalClock(); //clock to be added to pane
        
        // handle button clicks with lamdas
        twelveHr.setOnAction(e -> {
            clock.changeFormat12();
        });
        
        twentyfourHr.setOnAction(e -> {
           clock.changeFormat24();
        });
        
        // handle keyboard presses with lamdas
        twelveHr.setOnKeyPressed(e -> {
           if(e.getCode() == KeyCode.UP) 
                {
                    clock.changeFontColorRed();
                }
           else if(e.getCode() == KeyCode.DOWN) 
                {
                    clock.changeFontColorCyan();
                }
           else if(e.getCode() == KeyCode.ENTER)
                {
                    clock.changeFontColorBlack();
                }
           else if(e.getCode() == KeyCode.P)
                {
                    nationalAnthemMedia().show(); //shows the national anthem media
                }
          
          
        });
        
        twentyfourHr.setOnKeyPressed(e -> {
           if(e.getCode() == KeyCode.UP) 
                {
                    clock.changeFontColorRed();
                }
           else if(e.getCode() == KeyCode.DOWN) 
                {
                    clock.changeFontColorCyan();
                }
           else if(e.getCode() == KeyCode.ENTER)
                {
                    clock.changeFontColorBlack();
                }
           else if(e.getCode() == KeyCode.P)
                {
                    nationalAnthemMedia().show(); //shows the national anthem media
                }
          
          
        });
       
        paneForButtons.getChildren().addAll(twelveHr, twentyfourHr);
        paneForButtons.setStyle("-fx-border-color: green");
        paneForButtons.setAlignment(Pos.CENTER);
        pane.getChildren().add(clock);
        pane.setCenter(clock.getClockText());
        pane.setBottom(paneForButtons);
    
        return pane;
    }
    
    @Override
    public void start(Stage primaryStage) {
        // Create a scene and place it in the stage
        Scene scene = new Scene(getPane(), 250, 150);
        primaryStage.setTitle("ClockApplication"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
       
        getPane().requestFocus();
        
    }
    
    public static void main(String[] args) {
       launch(args);
    }
    
}

class DigitalClock extends Label {
    
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private Timeline animation;
    private Calendar time;
    private Label clockText;
    private String formattedTime;
     
    public DigitalClock() {
        
        /*initial time for the clock is set to get rid of 
          1 sec display delay*/
        time = Calendar.getInstance();
        clockText = new Label();
        formattedTime = dateFormat.format(time.getTime());
        clockText.setText(formattedTime); 
        
        // get time and set text with lambda
        EventHandler<ActionEvent> eventHandler = e -> { 
        time = Calendar.getInstance();
        formattedTime = dateFormat.format(time.getTime());
        clockText.setText(formattedTime); 
        };
        
        // change text font here
        clockText.setFont(Font.font("Arial", 30));
        
        // set animation here
        animation = new Timeline(
                new KeyFrame(Duration.millis(1000), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
       
    }
    
    public Label getClockText() {
        return this.clockText;
    }
    
    public void changeFormat24() {
        // write code here for changing to 24 hour clock
        dateFormat.applyPattern("HH:mm:ss");
    }
    
    public void changeFormat12() {
        // write code here for changing to 12 hour clock
        dateFormat.applyPattern("hh:mm:ss a");
    }
    
    public void changeFontColorRed() {
        clockText.setTextFill(Color.RED);
    }
    
    public void changeFontColorCyan() {
        clockText.setTextFill(Color.CYAN);
    }
    
    public void changeFontColorBlack() {
        clockText.setTextFill(Color.BLACK);
    }
    
}