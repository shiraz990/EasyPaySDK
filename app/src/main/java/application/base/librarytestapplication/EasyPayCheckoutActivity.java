package application.base.librarytestapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.easypay.widget.Checkout;
import com.easypay.widget.EasyPayCallback;
import com.easypay.widget.EasyPayCheckout;
import com.easypay.widget.InitializeMerchant;

/**
 * Created by khilt-149 on 6/6/2018.
 */

public class EasyPayCheckoutActivity  extends AppCompatActivity implements EasyPayCallback {
    EasyPayCheckout easyPayCheckoutObj;
    Checkout component;
    InitializeMerchant initializeMerchant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easypay);
        initialization();
        proceedCheckout();
    }
    //Initialization of easypay SDK components
    private void initialization() {
        component = (Checkout) findViewById(R.id.componentID);

        initializeMerchant = InitializeMerchant.getInstance(
                getString(R.string.storeID), getString(R.string.easyPaySecretKey),
                getString(R.string.postBackURL), this);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            easyPayCheckoutObj = (EasyPayCheckout) bundle.getSerializable("EasyPayCheckout");

        }

    }


    // Method call on proceedCheckout
    private void proceedCheckout(){
        initializeMerchant.proceedCheckout(component,easyPayCheckoutObj);
    }

    //  Callback recieved on proceedCheckout method
    @Override
    public void checkoutCallback(boolean status,String message) {

        Toast.makeText(this,"Response : "+message,Toast.LENGTH_LONG).show();
    }
}
