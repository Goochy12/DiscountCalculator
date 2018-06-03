package au.com.scroogetech.discountcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //calculation variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get button
        final Button calcButton = (Button) findViewById(R.id.calculateButton);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get other values
                EditText costText = (EditText) findViewById(R.id.costText);
                EditText discountText = (EditText) findViewById(R.id.discountText);

                double cost = Double.parseDouble(costText.toString());
                double discountVal = Double.parseDouble(discountText.toString());


                double total = calcDiscount(cost,discountVal);

                TextView totalView = (TextView) findViewById(R.id.totalTextView);
                String totalDouble = Double.toString(total);
                totalView.setText(totalDouble);


            }
        });
    }

    public double calcDiscount(double cost, double discount){
        double total=0;

        total = cost - (cost * discount);

        return total;
    }
}
