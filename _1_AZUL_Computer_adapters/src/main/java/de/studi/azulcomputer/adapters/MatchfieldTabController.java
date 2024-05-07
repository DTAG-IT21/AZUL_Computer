package de.studi.azulcomputer.adapters;

import de.studi.azulcomputer.application.Player;
import de.studi.azulcomputer.domain.Mosaic;
import de.studi.azulcomputer.domain.Tile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class MatchfieldTabController {

    public Player player = new Player();

    @FXML
    private Label lbl_TotalScore;

    @FXML
    private Button btn_brd_00;
    @FXML
    private Button btn_brd_01;
    @FXML
    private Button btn_brd_02;
    @FXML
    private Button btn_brd_03;
    @FXML
    private Button btn_brd_04;
    @FXML
    private Button btn_brd_10;
    @FXML
    private Button btn_brd_11;
    @FXML
    private Button btn_brd_12;
    @FXML
    private Button btn_brd_13;
    @FXML
    private Button btn_brd_14;
    @FXML
    private Button btn_brd_20;
    @FXML
    private Button btn_brd_21;
    @FXML
    private Button btn_brd_22;
    @FXML
    private Button btn_brd_23;
    @FXML
    private Button btn_brd_24;
    @FXML
    private Button btn_brd_30;
    @FXML
    private Button btn_brd_31;
    @FXML
    private Button btn_brd_32;
    @FXML
    private Button btn_brd_33;
    @FXML
    private Button btn_brd_34;
    @FXML
    private Button btn_brd_40;
    @FXML
    private Button btn_brd_41;
    @FXML
    private Button btn_brd_42;
    @FXML
    private Button btn_brd_43;
    @FXML
    private Button btn_brd_44;


    @FXML
    private Button btn_11_bl;
    @FXML
    private Button btn_11_re;
    @FXML
    private Button btn_11_or;
    @FXML
    private Button btn_11_bk;
    @FXML
    private Button btn_11_tq;

    @FXML
    private Button btn_21_bl;
    @FXML
    private Button btn_21_re;
    @FXML
    private Button btn_21_or;
    @FXML
    private Button btn_21_bk;
    @FXML
    private Button btn_21_tq;


    @FXML
    private Button btn_22_bl;
    @FXML
    private Button btn_22_re;
    @FXML
    private Button btn_22_or;
    @FXML
    private Button btn_22_bk;
    @FXML
    private Button btn_22_tq;


    @FXML
    private Button btn_31_bl;
    @FXML
    private Button btn_31_re;
    @FXML
    private Button btn_31_or;
    @FXML
    private Button btn_31_bk;
    @FXML
    private Button btn_31_tq;


    @FXML
    private Button btn_32_bl;
    @FXML
    private Button btn_32_re;
    @FXML
    private Button btn_32_or;
    @FXML
    private Button btn_32_bk;
    @FXML
    private Button btn_32_tq;

    @FXML
    private Button btn_33_bl;
    @FXML
    private Button btn_33_re;
    @FXML
    private Button btn_33_or;
    @FXML
    private Button btn_33_bk;
    @FXML
    private Button btn_33_tq;





    @FXML
    private Button btn_41_bl;
    @FXML
    private Button btn_41_re;
    @FXML
    private Button btn_41_or;
    @FXML
    private Button btn_41_bk;
    @FXML
    private Button btn_41_tq;

    @FXML
    private Button btn_42_bl;
    @FXML
    private Button btn_42_re;
    @FXML
    private Button btn_42_or;
    @FXML
    private Button btn_42_bk;
    @FXML
    private Button btn_42_tq;

    @FXML
    private Button btn_43_bl;
    @FXML
    private Button btn_43_re;
    @FXML
    private Button btn_43_or;
    @FXML
    private Button btn_43_bk;
    @FXML
    private Button btn_43_tq;

    @FXML
    private Button btn_44_bl;
    @FXML
    private Button btn_44_re;
    @FXML
    private Button btn_44_or;
    @FXML
    private Button btn_44_bk;
    @FXML
    private Button btn_44_tq;

    @FXML
    private Button btn_51_bl;
    @FXML
    private Button btn_51_re;
    @FXML
    private Button btn_51_or;
    @FXML
    private Button btn_51_bk;
    @FXML
    private Button btn_51_tq;

    @FXML
    private Button btn_52_bl;
    @FXML
    private Button btn_52_re;
    @FXML
    private Button btn_52_or;
    @FXML
    private Button btn_52_bk;
    @FXML
    private Button btn_52_tq;

    @FXML
    private Button btn_53_bl;
    @FXML
    private Button btn_53_re;
    @FXML
    private Button btn_53_or;
    @FXML
    private Button btn_53_bk;
    @FXML
    private Button btn_53_tq;

    @FXML
    private Button btn_54_bl;
    @FXML
    private Button btn_54_re;
    @FXML
    private Button btn_54_or;
    @FXML
    private Button btn_54_bk;
    @FXML
    private Button btn_54_tq;

    @FXML
    private Button btn_55_bl;
    @FXML
    private Button btn_55_re;
    @FXML
    private Button btn_55_or;
    @FXML
    private Button btn_55_bk;
    @FXML
    private Button btn_55_tq;

    private Button[][] buttonGrid;


    @FXML
    public void initialize() {

        initializeButtonGrid();
        initializeLabels();

    }

    private void initializeButtonGrid() {
        buttonGrid = new Button[][]{
                {btn_brd_00, btn_brd_01, btn_brd_02, btn_brd_03, btn_brd_04},
                {btn_brd_10, btn_brd_11, btn_brd_12, btn_brd_13, btn_brd_14},
                {btn_brd_20, btn_brd_21, btn_brd_22, btn_brd_23, btn_brd_24},
                {btn_brd_30, btn_brd_31, btn_brd_32, btn_brd_33, btn_brd_34},
                {btn_brd_40, btn_brd_41, btn_brd_42, btn_brd_43, btn_brd_44}
        };

    }

    private void initializeLabels() {
        lbl_TotalScore.setText("0");
    }


    //Buttonhandler Methoden
    @FXML
    public void handleBoardButtonClick(ActionEvent event) {

        // Get button object
        Object source = event.getSource();
        if (source instanceof Button button) {

            // Get row and column
            int row = GridPane.getRowIndex(button);
            int column = GridPane.getColumnIndex(button);

            // Place tile on board
            player.placeTile(row, new Tile(Mosaic.colorPattern[row][column]));

            // Update score
            lbl_TotalScore.setText(Integer.toString(player.getScore()));

            // Adjust color of button
            Color buttonColor = (Color) button.getBackground().getFills().get(0).getFill();
            button.setStyle("-fx-background-color: " + toRGBCode(buttonColor.brighter().brighter()));
        }

        for (Button[] buttonList : buttonGrid) {
            for (Button button : buttonList) {
                int row = GridPane.getRowIndex(button);
                int column = GridPane.getColumnIndex(button);
                int buttonScore = player.potentialScore(row, Mosaic.colorPattern[row][column]);
                if (buttonScore != 0) {
                    button.setText(Integer.toString(buttonScore));
                } else {
                    button.setText("");
                }
            }
        }
    }


    //Backend verschieben ?
    private String toRGBCode(Color color) {
        return String.format("#%02X%02X%02X",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }

    @FXML
    public void resetButtonGrid() {
        player.reset();
        lbl_TotalScore.setText(Integer.toString(player.getScore()));
        for (Button[] buttonList : buttonGrid) {
            for (Button button : buttonList) {
                int row = GridPane.getRowIndex(button);
                int column = GridPane.getColumnIndex(button);
                button.setText("1");
            }
        }
    }
}