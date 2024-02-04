package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Text bestScore;

    @FXML
    private Text guessResult;

    @FXML
    private Text numberOfGuesses;

    @FXML
    private TextField input;

    @FXML
    private Text range;

    @FXML
    void giveUp(MouseEvent event) {
        int value = guessGame.giveUp();
        alert(String.valueOf(value));
        restart(null);
    }

    @FXML
    void guess(MouseEvent event) {
        if(!isNumeric(input.getText())){
            alert("Input a numeric value");
            return;
        }
        if(Double.parseDouble(input.getText()) > guessGame.getRange()
                || Double.parseDouble(input.getText()) < 1){
            alert("Input a number > 1 and < " + guessGame.getRange());
            return;
        }
        GuessResult result = guessGame.guess((int)Double.parseDouble(input.getText()));
        alert(result.toString());
        numberOfGuesses.setText(String.valueOf(guessGame.getNumberOfGuesses()));

    }

    @FXML
    void restart(MouseEvent event) {
        guessGame.restart();
        bestScore.setText(String.valueOf(guessGame.getBestScore()));
        numberOfGuesses.setText(String.valueOf(guessGame.getNumberOfGuesses()));
        range.setText(String.valueOf(guessGame.getRange()));
    }
    public static void alert(String value){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle(value);
        alert.setContentText(value);
        Optional<ButtonType> result = alert.showAndWait();
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static GuessGame guessGame;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        guessGame = new GuessGame(100);
        bestScore.setText(String.valueOf(guessGame.getBestScore()));
        numberOfGuesses.setText(String.valueOf(guessGame.getNumberOfGuesses()));
        range.setText(String.valueOf(guessGame.getRange()));
    }
}
