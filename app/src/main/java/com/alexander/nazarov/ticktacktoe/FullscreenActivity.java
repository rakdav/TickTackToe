package com.alexander.nazarov.ticktacktoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class FullscreenActivity extends AppCompatActivity {

    private ArrayList<Button> buttons = new ArrayList<>();
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9, randomB;
    private TextView score, round_text, turn_text;
    private String lastCommand = "O"; //за кем был последний ход
    private int x = 0;   //счетчик побед крестиков
    private int o = 0;   //счетчик побед ноликов
    private int round = 1;   //счетчик раундов
    private int turn = 0;    //счетчик ходов
    private Toast winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//========================Нажимаем на кнопку========================

    public void pushButton(View view) {
        b1 = (Button) findViewById(R.id.first_button);
        buttons.add(b1);
        b2 = (Button) findViewById(R.id.second_button);
        buttons.add(b2);
        b3 = (Button) findViewById(R.id.third_button);
        buttons.add(b3);
        b4 = (Button) findViewById(R.id.fourth_button);
        buttons.add(b4);
        b5 = (Button) findViewById(R.id.fith_button);
        buttons.add(b5);
        b6 = (Button) findViewById(R.id.sixth_button);
        buttons.add(b6);
        b7 = (Button) findViewById(R.id.seventh_button);
        buttons.add(b7);
        b8 = (Button) findViewById(R.id.eigth_button);
        buttons.add(b8);
        b9 = (Button) findViewById(R.id.ninth_button);
        buttons.add(b9);
        score = (TextView) findViewById(R.id.score);
        round_text = (TextView) findViewById(R.id.round_text);

//==============Определяем, на какую именно кнопку нажали===============

        switch (view.getId()) {
            case R.id.first_button:
                turn(b1);
                break;
            case R.id.second_button:
                turn(b2);
                break;
            case R.id.third_button:
                turn(b3);
                break;
            case R.id.fourth_button:
                turn(b4);
                break;
            case R.id.fith_button:
                turn(b5);
                break;
            case R.id.sixth_button:
                turn(b6);
                break;
            case R.id.seventh_button:
                turn(b7);
                break;
            case R.id.eigth_button:
                turn(b8);
                break;
            case R.id.ninth_button:
                turn(b9);
                break;
        }
//=========================Победа Крестиков=========================

        if ((b1.getText().equals("X") && b2.getText().equals("X") && b3.getText().equals("X")) ||
                (b1.getText().equals("X") && b5.getText().equals("X") && b9.getText().equals("X")) ||
                (b1.getText().equals("X") && b4.getText().equals("X") && b7.getText().equals("X")) ||
                (b2.getText().equals("X") && b5.getText().equals("X") && b8.getText().equals("X")) ||
                (b3.getText().equals("X") && b5.getText().equals("X") && b7.getText().equals("X")) ||
                (b3.getText().equals("X") && b6.getText().equals("X") && b9.getText().equals("X")) ||
                (b4.getText().equals("X") && b5.getText().equals("X") && b6.getText().equals("X")) ||
                (b7.getText().equals("X") && b8.getText().equals("X") && b9.getText().equals("X"))) {
            turn = 0;
            round++;
            x++;
            score.setText(x + " : " + o);
            winner("Крестики");
            round_text.setText("РАУНД " + round);
        }
//==========================Победа Ноликов==========================

        else randomButton();

/* ===============Понадобится при игре без компьютера===============
            if (b1.getText().equals("O") && b2.getText().equals("O") && b3.getText().equals("O") ||
                    b1.getText().equals("O") && b5.getText().equals("O") && b9.getText().equals("O") ||
                    b1.getText().equals("O") && b4.getText().equals("O") && b7.getText().equals("O") ||
                    b2.getText().equals("O") && b5.getText().equals("O") && b8.getText().equals("O") ||
                    b3.getText().equals("O") && b5.getText().equals("O") && b7.getText().equals("O") ||
                    b3.getText().equals("O") && b6.getText().equals("O") && b9.getText().equals("O") ||
                    b4.getText().equals("O") && b5.getText().equals("O") && b6.getText().equals("O") ||
                    b7.getText().equals("O") && b8.getText().equals("O") && b9.getText().equals("O")) {
                turn = 0;
                o++;
                score.setText(x + " : " + o);
                winner("Нолики");
                round++;
                round_text.setText("РАУНД " + round);
            }
*/
//==============================Ничья==============================

        if (turn == 9) {
            winner("");
            turn = 0;
            round++;
            round_text.setText("РАУНД " + round);
        }
    }
//=========================Проверяем, чей ход======================

    private void turn(Button button) {
        turn_text = (TextView) findViewById(R.id.turn_text);
        if (lastCommand.equals("O")) {
            button.setText("X");
            turn_text.setText("НОЛИКИ");
            lastCommand = "X";
            turn++;
//====================Ход Ноликов при игре без компьютера===========
//        } else {
//            button.setText("O");
//            turn_text.setText("КРЕСТИКИ");
//            lastCommand = "O";
//            turn++;
//        }
            button.setEnabled(false);
        }
    }
//================Очистка всего поля для новой игры=================

    public void clearField() {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setText("");
            buttons.get(i).setEnabled(true);
        }
    }
//======================Ход компьютера===============================

    public void randomButton() {
        int[] mainInt = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        Random rand = new Random();

        for (int i = 0; i < buttons.size(); i++) {
            randomB = buttons.get(mainInt[rand.nextInt(9)]);
            if (randomB.getText().equals("") && lastCommand.equals("X") && turn <= 9) {

                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        turn_text.setText("КРЕСТИКИ");
                        randomB.setText("O");
                        randomB.setEnabled(false); //ИСПРАВИТЬ
                        lastCommand = "O";
                        turn++;
                        if (b1.getText().equals("O") && b2.getText().equals("O") && b3.getText().equals("O") ||
                                b1.getText().equals("O") && b5.getText().equals("O") && b9.getText().equals("O") ||
                                b1.getText().equals("O") && b4.getText().equals("O") && b7.getText().equals("O") ||
                                b2.getText().equals("O") && b5.getText().equals("O") && b8.getText().equals("O") ||
                                b3.getText().equals("O") && b5.getText().equals("O") && b7.getText().equals("O") ||
                                b3.getText().equals("O") && b6.getText().equals("O") && b9.getText().equals("O") ||
                                b4.getText().equals("O") && b5.getText().equals("O") && b6.getText().equals("O") ||
                                b7.getText().equals("O") && b8.getText().equals("O") && b9.getText().equals("O")) {
                            turn = 0;
                            o++;
                            score.setText(x + " : " + o);
                            winner("Нолики");
                            round++;
                            round_text.setText("РАУНД " + round);
                        }
                    }
                }, 500);

                break;
            } else {
                randomB = buttons.get(mainInt[rand.nextInt(9)]);
            }
        }
    }
//===============Вывод во всплывающем сообщении победителя===========

    private void winner(String win) {
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setEnabled(false);
        }
        if (!(turn == 9)) {
            winner = Toast.makeText(getApplicationContext(),
                    "Победили " + win + "!", Toast.LENGTH_SHORT);

        } else if (turn == 9) {
            winner = Toast.makeText(getApplicationContext(),
                    "Ничья", Toast.LENGTH_SHORT);
        }

        winner.setGravity(Gravity.CENTER, 0, 0);
        winner.show();
//================Делаем паузу перед началом следующего раунда=========

        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                clearField();
            }
        }, 2000);
    }
}