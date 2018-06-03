package au.com.scroogetech.discountcalculator;

import android.content.Context;
import android.renderscript.ScriptGroup;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //calculation variables
    //text edited vars
    private boolean costEntered = false;
    private boolean discountEntered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set EditText Listeners
        final EditText costText = (EditText) findViewById(R.id.costText);
        final EditText discountText = (EditText) findViewById(R.id.discountText);
        final Button calcButton = (Button) findViewById(R.id.calculateButton);

        //set button disabled
        calcButton.setEnabled(false);

        //cost text change listener
        costText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (discountEntered){
                    if (s.toString().length() !=0){
                        calcButton.setEnabled(true);
                        costEntered = true;
                    }else{
                        calcButton.setEnabled(false);
                        costEntered = false;
                    }
                }else{
                    if (s.toString().length() !=0){
                        costEntered = true;
                    }else{
                        costEntered = false;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //discount text change listener
        discountText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().length() > 0){
                    double dc = Double.parseDouble(s.toString());
                    if (dc>=100 || dc <= 0){
                        calcButton.setEnabled(false);
                    }else if (costEntered){
                        if (s.toString().length() !=0){
                            calcButton.setEnabled(true);
                            discountEntered = true;
                        }else{
                            calcButton.setEnabled(false);
                            discountEntered = false;
                        }
                    }else{
                        if (s.toString().length() !=0){
                            discountEntered = true;
                        }else{
                            discountEntered = false;
                        }
                    }
                }else{
                    discountEntered = false;
                    calcButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //button listener
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //hide keyboard
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(),0);

                //get other values
                double cost = Double.parseDouble(costText.getText().toString());
                double discountVal = Double.parseDouble(discountText.getText().toString());


                double total = calcDiscount(cost,discountVal);

                TextView totalView = (TextView) findViewById(R.id.totalTextView);
                String totalDouble = Double.toString(total);

                DecimalFormat format = new DecimalFormat("##.00");
                totalView.setText("$ " + format.format(total));


            }
        });
    }

    public double calcDiscount(double cost, double discount){
        double total=0;

        total = cost - (cost * (discount /100));

        return total;
    }
}
