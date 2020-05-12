package sg.edu.rp.c346.id19036849.thebillcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText etAmount, etNumOfPax, etDiscount;
    ToggleButton tbSVS, tbGST;
    TextView tvTotalBill, tvEachPays;
    Button btSplit, btReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount = findViewById(R.id.etAmount);
        etNumOfPax = findViewById(R.id.etNumOfPax);
        tbSVS = findViewById(R.id.tbSVS);
        tbGST = findViewById(R.id.tbGST);
        etDiscount = findViewById(R.id.etDiscount);
        tvTotalBill = findViewById(R.id.tvTotalBill);
        tvEachPays = findViewById(R.id.tvEachPays);
        btSplit = findViewById(R.id.btSplit);
        btReset = findViewById(R.id.btReset);

        btSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double amount = 0;
                int numOfPax = 0;
                double discount = 0;
                double addOnCharge = 0;

                double totalBill = 0;
                double eachPays = 0;

                if ((etAmount.getText().toString().trim().length() > 0) && (etNumOfPax.getText().toString().trim().length() > 0)) {
                    amount = Double.parseDouble(etAmount.getText().toString());
                    numOfPax = Integer.parseInt(etNumOfPax.getText().toString());

                    if (etDiscount.getText().toString().trim().length() > 0) {
                        discount = Double.parseDouble(etDiscount.getText().toString());
                    }

                    if (tbGST.isChecked()) {
                        addOnCharge += 0.07;
                    }
                    if (tbSVS.isChecked()) {
                        addOnCharge += 0.1;
                    }

                    totalBill = (amount * (1 + addOnCharge)) * (1 - (discount / 100));
                    eachPays = totalBill / numOfPax;

                    tvTotalBill.setText(String.format("Total Bill :$%.2f", totalBill));
                    tvEachPays.setText(String.format("Each Pays :$%.2f", eachPays));
                } else {
                    tvTotalBill.setText("");
                    tvEachPays.setText("Please enter the necessary details (\"Amount\" and \"Num of Pax\").");
                }
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAmount.getText().clear();
                etNumOfPax.getText().clear();
                etDiscount.getText().clear();

                tvTotalBill.setText("");
                tvEachPays.setText("");

                tbSVS.setChecked(true);
                tbGST.setChecked(true);
            }
        });
    }
}
