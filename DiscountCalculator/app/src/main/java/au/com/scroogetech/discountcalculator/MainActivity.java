package au.com.scroogetech.discountcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    //calculation variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public double calcDiscount(int cost, int discount){
        double total=0;

        total = cost - (cost * discount);

        return total;
    }
}
