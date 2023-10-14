package com.example.calc;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Locale;

public class MainActivityViewModel extends ViewModel {

    private Double prevNum = 0.0;
    private Operators selectedOperator = Operators.NONE;
    private boolean isNeedToClear = false;

    MutableLiveData<String> string = new MutableLiveData<>("0");

    public void pressedNum(String num) {
        if (isNeedToClear) {
            string.setValue("0");
        }

        String currString = string.getValue();
        if (num.equals(".")) {
            if (!checkForDot()) {
                string.setValue(currString + num);
            }
        } else {
            if (currString.equals("0")) {
                string.setValue(num);
            } else {
                string.setValue(currString + num);
            }
        }
        isNeedToClear = false;
    }

    public void pressedOperator(Operators operator) {
        prevNum = Double.parseDouble(string.getValue());
        selectedOperator = operator;
        isNeedToClear = true;
    }

    public void clearString() {
        string.setValue("0");
        selectedOperator = Operators.NONE;
    }

    public void deleteLast() {
        String currString = string.getValue();
        if (currString.length() <= 1) {
            string.setValue("0");
        } else {
            string.setValue(string.getValue().substring(0, string.getValue().length() - 1));
        }
    }

    public void doOperation(Context context) {
        Double nowNum = Double.parseDouble(string.getValue());
        switch (selectedOperator) {
            case MULTIPLY: {
                outNum(prevNum * nowNum);
                break;
            }
            case DIV: {
                if (nowNum != 0) {
                    outNum(prevNum / nowNum);
                } else {
                    Toast.makeText(context, "Не дели на ноль, позязя...", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case PLUS: {
                outNum(prevNum + nowNum);
                break;
            }
            case MINUS: {
                outNum(prevNum - nowNum);
                break;
            }
            case NONE: {
                Toast.makeText(context, "Выберите операцию", Toast.LENGTH_SHORT).show();
            }
        }
        selectedOperator = Operators.NONE;
    }

    private boolean checkForDot() {
        return string.getValue().contains(".");
    }

    private void outNum(Double num) {
        DecimalFormat df = new DecimalFormat("#.#######");
        df.setRoundingMode(RoundingMode.CEILING);
        if (num % 1 == 0) {
            string.setValue(String.valueOf(df.format(num.intValue())));
        } else {
            string.setValue(String.valueOf(df.format(num)));
        }
    }

}
