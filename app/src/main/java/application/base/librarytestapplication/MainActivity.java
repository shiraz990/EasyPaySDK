package application.base.librarytestapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.easypay.widget.EasyPayCheckout;

public class MainActivity extends AppCompatActivity  {



    Button proceedButton;
    EditText amount ,orderRefNo , expiryDate  ,emailAdd , mobileNum , bankIdentificationNumber ;
    Spinner spinnerPaymentMethod;
    Switch autoRedirect , isEncrypted ;
    EasyPayCheckout easyPayCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
        addListeners();

    }


    public void addListeners() {
        spinnerPaymentMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    easyPayCheckout.setPaymentMethod(parent.getItemAtPosition(position).toString());
                }
                else{
                    easyPayCheckout.setPaymentMethod("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                easyPayCheckout.setPaymentMethod("");
            }
        });

        autoRedirect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    easyPayCheckout.setAutoRedirect("1");
                } else {
                    easyPayCheckout.setAutoRedirect("0");

                }
            }
        });
        isEncrypted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {

                    easyPayCheckout.setRequestEncrypted(true);
                } else {

                    easyPayCheckout.setRequestEncrypted(false);
                }
            }
        });



        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEasyPayCheckoutActivity();
            }
        });
    }

    private void openEasyPayCheckoutActivity() {
        easyPayCheckout.setAmount(amount.getText().toString());
        easyPayCheckout.setOrderRefNum(orderRefNo.getText().toString());
        easyPayCheckout.setExpiryDate(expiryDate.getText().toString());
        easyPayCheckout.setEmailAdd(emailAdd.getText().toString());
        easyPayCheckout.setMobileNo(mobileNum.getText().toString());
        easyPayCheckout.setBankIdentificationNum(bankIdentificationNumber.getText().toString());
        Intent intent = new Intent(getApplicationContext(), EasyPayCheckoutActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("EasyPayCheckout",easyPayCheckout );
        intent.putExtras(b);
        startActivity(intent);

    }


    //Initialization
    private void initialize() {
        amount = (EditText) findViewById(R.id.editTextAmount);
        orderRefNo = (EditText) findViewById(R.id.editTextOrderRef);
        expiryDate = (EditText) findViewById(R.id.editTextExpiryDate);
        emailAdd = (EditText) findViewById(R.id.editTextEmail);
        mobileNum = (EditText) findViewById(R.id.editTextMobile);
        bankIdentificationNumber = (EditText) findViewById(R.id.editTextBankIdentifier);
        autoRedirect = (Switch) findViewById(R.id.switchAutoRedirect);
        isEncrypted = (Switch) findViewById(R.id.switchEncrypted);

        proceedButton = (Button) findViewById(R.id.proceedButton);
        spinnerPaymentMethod = (Spinner) findViewById(R.id.spinnerPaymentMethod);
        easyPayCheckout = new EasyPayCheckout();
    }


}
