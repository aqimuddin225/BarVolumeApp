package com.example.ibrohimaqimuddin.barvolume;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    EditText edtWidth, edtHeight, edtLenght;
    Button btnCalculate;
    TextView tvResult;

    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtWidth = (EditText) findViewById(R.id.edt_width);
        edtHeight = (EditText) findViewById(R.id.edt_height);
        edtLenght = (EditText) findViewById(R.id.edt_length);
        btnCalculate = (Button) findViewById(R.id.btn_calculate);
        tvResult = (TextView) findViewById(R.id.tv_result);

        btnCalculate.setOnClickListener(this);

        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_calculate) {
            String lenght = edtLenght.getText().toString().trim();
            String width = edtWidth.getText().toString().trim();
            String height = edtHeight.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(lenght)) {
                isEmptyFields = true;
                edtLenght.setError("Field ini tidak boleh kosong");
            } else if (!isDouble(lenght)) {
                isInvalidDouble = true;
                edtLenght.setError("Field ini harus berupa nomer yang valid");
            }

            if (TextUtils.isEmpty(width)) {
                isEmptyFields = true;
                edtWidth.setError("Field ini tidak boleh kosong");
            } else if (!isDouble(width)) {
                isInvalidDouble = true;
                edtWidth.setError("Field ini harus berupa nomer yang valid");
            }

            if (TextUtils.isEmpty(height)) {
                isEmptyFields = true;
                edtHeight.setError("Field ini tidak boleh kosong");
            } else if (!isDouble(height)) {
                isInvalidDouble = true;
                edtHeight.setError("Field ini harus berupa nomer yang valid");
            }

            if (!isEmptyFields && !isInvalidDouble) {
                double l = Double.parseDouble(lenght);
                double w = Double.parseDouble(width);
                double h = Double.parseDouble(height);

                double volume = l * w * h;

                tvResult.setText(String.valueOf(volume));
            }
        }
    }

    boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}