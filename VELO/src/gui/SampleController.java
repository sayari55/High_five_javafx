package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class SampleController implements Initializable {

    private final Random random = new Random();
    private int randomNumber;
    private int guessCount = 0;

    @FXML
    private TextField guess;
    @FXML
    private Text guessCounterText;
    @FXML
    private ImageView upArrow;
    @FXML
    private ImageView downArrow;
    @FXML
    private ImageView correct;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        randomNumber = random.nextInt(100);
        System.out.println(randomNumber);
        downArrow.setVisible(false);
        upArrow.setVisible(false);
        correct.setVisible(false);
    }

    @FXML
    void checkGuess(ActionEvent event) {
        if(Integer.parseInt(guess.getText()) == randomNumber){
            downArrow.setVisible(false);
            upArrow.setVisible(false);
            correct.setVisible(true);
        } else if(Integer.parseInt(guess.getText()) > randomNumber){
            downArrow.setVisible(true);
            upArrow.setVisible(false);
            correct.setVisible(false);
        } else if(Integer.parseInt(guess.getText()) < randomNumber){
            downArrow.setVisible(false);
            upArrow.setVisible(true);
            correct.setVisible(false);
        }
        guessCount++;
        guessCounterText.setText("Guesses: " + guessCount);
    }

    @FXML
    void reset(ActionEvent event) {
        randomNumber = random.nextInt(100);
        downArrow.setVisible(false);
        upArrow.setVisible(false);
        correct.setVisible(false);
        guessCount = 0;
        guessCounterText.setText("Guesses: " + guessCount);
    }
}