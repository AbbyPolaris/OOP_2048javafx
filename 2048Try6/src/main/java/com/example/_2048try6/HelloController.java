package com.example._2048try6;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.Optional;

public class HelloController {

    Blockgrid blocks = new Blockgrid();
    Button[][] Images = new Button[4][4];
    final int winScore = 128;
    boolean flagWon = false;
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    @FXML
    Label Score;

    @FXML
    Button debug;

    @FXML
    Button reset;

    @FXML
    Button B00, B01, B02, B03, B10, B11, B12, B13, B20, B21,
            B22, B23, B30, B31, B32, B33;

    @FXML
    void onHelloButtonClick(MouseEvent ev) throws CloneNotSupportedException {
        if (ev.getSource() == reset) {
            System.out.println("reset");
            //ToDO
            initialize();
            blocks.debugMessage();
        }
        if (ev.getSource() == debug) {
            System.out.println("debug : ");
            //ToDO
            blocks.debugMessage();
            //---
        }
    }

    @FXML
    void HandleKeys(KeyEvent event) throws InterruptedException, CloneNotSupportedException {
        //System.out.println(event.getText());
        if (event.getText().equals("w")) {
            blocks.up();
            if (blocks.madeMove)
                blocks.MakeNewBlock();
        }
        if (event.getText().equals("a")) {
            blocks.left();
            if (blocks.madeMove)
                blocks.MakeNewBlock();
        }
        if (event.getText().equals("s")) {
            blocks.down();
            if (blocks.madeMove)
                blocks.MakeNewBlock();
        }
        if (event.getText().equals("d")) {
            blocks.right();
            if (blocks.madeMove)
                blocks.MakeNewBlock();
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blocks.blocks[i][j].theNum != 0) {
                    Images[i][j].setText(String.valueOf(blocks.blocks[i][j].theNum));
                    Images[i][j].setFont(new Font("", 18));
                    if (blocks.blocks[i][j].theNum == 2) {
                        Images[i][j].setStyle("-fx-background-color:" + Colorpicker.color[0]);

                    } else if (blocks.blocks[i][j].theNum == 4) {
                        Images[i][j].setStyle("-fx-background-color:" + Colorpicker.color[1]);

                    } else if (blocks.blocks[i][j].theNum == 8) {
                        Images[i][j].setStyle("-fx-background-color:" + Colorpicker.color[2]);

                    } else if (blocks.blocks[i][j].theNum == 16) {
                        Images[i][j].setStyle("-fx-background-color:" + Colorpicker.color[3]);

                    } else if (blocks.blocks[i][j].theNum == 32) {
                        Images[i][j].setStyle("-fx-background-color:" + Colorpicker.color[4]);

                    } else if (blocks.blocks[i][j].theNum == 64) {
                        Images[i][j].setStyle("-fx-background-color:" + Colorpicker.color[5]);

                    } else if (blocks.blocks[i][j].theNum == 128) {
                        Images[i][j].setStyle("-fx-background-color:" + Colorpicker.color[6]);

                    } else if (blocks.blocks[i][j].theNum == 256) {
                        Images[i][j].setStyle("-fx-background-color:" + Colorpicker.color[7]);

                    } else if (blocks.blocks[i][j].theNum == 512) {
                        Images[i][j].setStyle("-fx-background-color:" + Colorpicker.color[8]);

                    } else if (blocks.blocks[i][j].theNum == 1024) {
                        Images[i][j].setStyle("-fx-background-color:" + Colorpicker.color[9]);

                    } else if (blocks.blocks[i][j].theNum == 2048) {
                        Images[i][j].setStyle("-fx-background-color:" + Colorpicker.color[10]);

                    }
                }
                if (blocks.blocks[i][j].theNum == 0) {
                    Images[i][j].setText("");
                    Images[i][j].setFont(new Font("", 18));
                    Images[i][j].setStyle("-fx-background-color:" + "white");

                }
            }
        }

        Score.setText("score : " + blocks.Score);
        if (blocks.checkEnd() && !blocks.isWon()) {
            Score.setText("  you lost");
            System.out.println("you lost");
            alert.setContentText("you lost. reset Game?");
            alert.setResizable(false);
            Optional<ButtonType> result = alert.showAndWait();
            if(!result.isPresent() || result.get() == ButtonType.OK) {
                initialize();
            }
        }
        if (blocks.isWon() && blocks.checkEnd()) {
            Score.setText("  game end");
            System.out.println("game end");
            alert.setResizable(false);
            alert.setContentText("game end and you won, reset?");
            Optional<ButtonType> result = alert.showAndWait();
            if(!result.isPresent() || result.get() == ButtonType.OK) {
               initialize();
            }
        }
        if (blocks.isWon() && !flagWon) {
            Score.setText("  you won");
            System.out.println("you won , winning score was : "+blocks.Score);
            flagWon = true;
        }
    }

    public void initialize() throws CloneNotSupportedException {
        System.out.println("initializing!");
        blocks = new Blockgrid();

        Images[0][0] = B00;
        Images[0][1] = B10;
        Images[0][2] = B20;
        Images[0][3] = B30;
        Images[1][0] = B01;
        Images[1][1] = B11;
        Images[1][2] = B21;
        Images[1][3] = B31;
        Images[2][0] = B02;
        Images[2][1] = B12;
        Images[2][2] = B22;
        Images[2][3] = B32;
        Images[3][0] = B03;
        Images[3][1] = B13;
        Images[3][2] = B23;
        Images[3][3] = B33;

        Score.setText("score : " + 0);
        blocks.Score = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                GridPoint Res = new GridPoint(j, i);
                Block B = new Block();
                B.point = Res;
                blocks.setBlock(Res, B);
            }
        }

        blocks.MakeNewBlock();
        blocks.MakeNewBlock();


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blocks.blocks[i][j].theNum != 0) {
                    Images[i][j].setText(String.valueOf(blocks.blocks[i][j].theNum));
                    Images[i][j].setFont(new Font("", 18));
                    //System.out.println(blocks.blocks[i][j].theNum);
                    if (blocks.blocks[i][j].theNum == 2) {
                        Images[i][j].setStyle("-fx-background-color:" + Colorpicker.color[0]);

                    } else if (blocks.blocks[i][j].theNum == 4) {
                        Images[i][j].setStyle("-fx-background-color:" + Colorpicker.color[1]);

                    }
                }
                if (blocks.blocks[i][j].theNum == 0) {
                    Images[i][j].setText("");
                    Images[i][j].setFont(new Font("", 18));
                    Images[i][j].setStyle("-fx-background-color:" + "white");
                }
            }
        }

    }
}