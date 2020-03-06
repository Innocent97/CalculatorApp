package com.example.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView fOutput, fUserInput;
    Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero,
            btnPlus, btnMinus, btnMultiply, btnDivide, btnEquals, btnPoint, btnClear;
    String process;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // connect to xml
        fOutput =  findViewById( R.id.text_view_output );
        fUserInput = findViewById(R.id.text_view_input);
        btnOne =  findViewById(R.id.button_one);
        btnTwo = findViewById(R.id.button_two);
        btnThree =  findViewById(R.id.button_three);
        btnFour =  findViewById(R.id.button_four);
        btnFive =  findViewById(R.id.button_five);
        btnSix =  findViewById(R.id.button_six);
        btnSeven =  findViewById(R.id.button_seven);
        btnEight = findViewById(R.id.button_eight);
        btnNine = findViewById(R.id.button_nine);
        btnZero =  findViewById(R.id.button_zero);
        btnPlus =  findViewById(R.id.button_plus);
        btnMinus =  findViewById(R.id.button_minus);
        btnMultiply = findViewById(R.id.button_multiply);
        btnDivide =  findViewById(R.id.button_divide);
        btnEquals = findViewById(R.id.button_equals);
        btnPoint = findViewById(R.id.button_point);
        btnClear = findViewById(R.id.button_clear);

        // OnClick listeners
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnZero.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnEquals.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch ( view.getId() ) {

            case R.id.button_one:
                fUserInput.setText(String.format("%s1", fUserInput.getText()));
                break;

            case R.id.button_two:
                fUserInput.setText(String.format("%s2", fUserInput.getText()));
                break;

            case R.id.button_three:
                fUserInput.setText(String.format("%s3", fUserInput.getText()));
                break;

            case R.id.button_four:
                fUserInput.setText(String.format("%s4", fUserInput.getText()));
                break;

            case R.id.button_five:
                fUserInput.setText(String.format("%s5", fUserInput.getText()));
                break;

            case R.id.button_six:
                fUserInput.setText(String.format("%s6", fUserInput.getText()));
                break;

            case R.id.button_seven:
                fUserInput.setText(String.format("%s7", fUserInput.getText()));
                break;

            case R.id.button_eight:
                fUserInput.setText(String.format("%s8", fUserInput.getText()));
                break;

            case R.id.button_nine:
                fUserInput.setText(String.format("%s9", fUserInput.getText()));
                break;

            case R.id.button_zero:
                fUserInput.setText(String.format("%s0", fUserInput.getText()));
                break;

            case R.id.button_point:
                process = fUserInput.getText().toString();
                fUserInput.setText(String.format("%s.", process));
                break;

            case R.id.button_clear:
                fUserInput.setText( "" );
                fOutput.setText( "" );
                break;

            case R.id.button_plus:
                doAddition();
                break;

            case R.id.button_minus:
                doMinus();
                break;

            case R.id.button_multiply:
                doMultiply();
                break;

            case R.id.button_divide:
                doDivide();
                break;

            case R.id.button_equals:
                performEquals();
                break;

        }
    }

    private void performEquals() {

            process = fUserInput.getText().toString();

            process = process.replaceAll(getString(R.string.multiply),"*");
            process = process.replaceAll(getString(R.string.remainder),"/100");
            process = process.replaceAll(getString(R.string.divide),"/");

            Context rhino = Context.enter();

            rhino.setOptimizationLevel(-1);

            String finalResult;

            try {
                Scriptable scriptable = rhino.initStandardObjects();
                finalResult = rhino.evaluateString(scriptable,process,"javascript",1,null).toString();
            }catch (Exception e){
                finalResult="0";
            }

            fOutput.setText(finalResult);
    }

    private void doDivide() {
        process = fUserInput.getText().toString();
        fUserInput.setText(String.format("%s÷", process));

    }

    private void doMultiply() {
        process = fUserInput.getText().toString();
        fUserInput.setText(String.format("%s×", process));
    }

    private void doMinus() {

        process = fUserInput.getText().toString();
        fUserInput.setText(String.format("%s-", process));
    }

    private void doAddition() {

        process = fUserInput.getText().toString();
        fUserInput.setText(String.format("%s+", process));

    }
}
