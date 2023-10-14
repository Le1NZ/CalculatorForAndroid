package com.example.calc;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.calc.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Locale.setDefault(new Locale("en", "US"));

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        viewModel.string.observe(this, string -> {
            binding.text.setText(string);
        });


        binding.button0.setOnClickListener(v -> {
            viewModel.pressedNum("0");
        });
        binding.button1.setOnClickListener(v -> {
            viewModel.pressedNum("1");
        });
        binding.button2.setOnClickListener(v -> {
            viewModel.pressedNum("2");
        });
        binding.button3.setOnClickListener(v -> {
            viewModel.pressedNum("3");
        });
        binding.button4.setOnClickListener(v -> {
            viewModel.pressedNum("4");
        });
        binding.button5.setOnClickListener(v -> {
            viewModel.pressedNum("5");
        });
        binding.button6.setOnClickListener(v -> {
            viewModel.pressedNum("6");
        });
        binding.button7.setOnClickListener(v -> {
            viewModel.pressedNum("7");
        });
        binding.button8.setOnClickListener(v -> {
            viewModel.pressedNum("8");
        });
        binding.button9.setOnClickListener(v -> {
            viewModel.pressedNum("9");
        });
        binding.buttonDot.setOnClickListener(b -> {
            viewModel.pressedNum(".");
        });

        binding.buttonAC.setOnClickListener(v -> {
            viewModel.clearString();
            setAllButtonsNonChecked();
        });
        binding.buttonC.setOnClickListener(v -> {
            viewModel.deleteLast();
        });

        binding.buttonDiv.setOnClickListener(v -> {
            setAllButtonsNonChecked();
            binding.buttonDiv.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FEEAE6")));
            binding.buttonDiv.setTextColor(Color.parseColor("#442C2E"));
            viewModel.pressedOperator(Operators.DIV);
        });
        binding.buttonPlus.setOnClickListener(v -> {
            setAllButtonsNonChecked();
            binding.buttonPlus.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FEEAE6")));
            binding.buttonPlus.setTextColor(Color.parseColor("#442C2E"));
            viewModel.pressedOperator(Operators.PLUS);
        });
        binding.buttonMinus.setOnClickListener(v -> {
            setAllButtonsNonChecked();
            binding.buttonMinus.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FEEAE6")));
            binding.buttonMinus.setTextColor(Color.parseColor("#442C2E"));
            viewModel.pressedOperator(Operators.MINUS);
        });
        binding.buttonMultiply.setOnClickListener(v -> {
            setAllButtonsNonChecked();
            binding.buttonMultiply.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FEEAE6")));
            binding.buttonMultiply.setTextColor(Color.parseColor("#442C2E"));
            viewModel.pressedOperator(Operators.MULTIPLY);
        });

        binding.buttonEqual.setOnClickListener(v -> {
            viewModel.doOperation(this);
            setAllButtonsNonChecked();
        });

        binding.buttonPercent.setOnClickListener(v -> {
            viewModel.pressedOperator(Operators.DIV);
            viewModel.pressedNum("100");
            viewModel.doOperation(this);
        });

    }

    private void setAllButtonsNonChecked() {
        binding.buttonDiv.setTextColor(Color.WHITE);
        binding.buttonDiv.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#442C2E")));
        binding.buttonMultiply.setTextColor(Color.WHITE);
        binding.buttonMultiply.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#442C2E")));
        binding.buttonMinus.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#442C2E")));
        binding.buttonMinus.setTextColor(Color.WHITE);
        binding.buttonPlus.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#442C2E")));
        binding.buttonPlus.setTextColor(Color.WHITE);
    }

}
