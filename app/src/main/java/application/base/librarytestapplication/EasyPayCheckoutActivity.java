package application.base.librarytestapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.easypay.widget.Checkout;
import com.easypay.widget.EasyPayCallback;
import com.easypay.widget.EasyPayCheckout;
import com.easypay.widget.EasyPayCheckoutView;
import com.easypay.widget.EasyPayEnvironment;
import com.easypay.widget.EasyPayInitializeMerchant;
import com.easypay.widget.InitializeMerchant;

/**
 * Created by khilt-149 on 6/6/2018.
 */

public class EasyPayCheckoutActivity  extends AppCompatActivity implements EasyPayCallback {
    EasyPayCheckout easyPayCheckoutObj;
    EasyPayCheckoutView component;
    EasyPayInitializeMerchant initializeMerchant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easypay);
        initialization();
        proceedCheckout();
    }
    //Initialization of easypay SDK components
    private void initialization() {
        component = (EasyPayCheckoutView) findViewById(R.id.componentID);

        initializeMerchant = EasyPayInitializeMerchant.getInstance(
                getString(R.string.storeID), getString(R.string.easyPaySecretKey),
                getString(R.string.postBackURL), this);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            easyPayCheckoutObj = (EasyPayCheckout) bundle.getSerializable("EasyPayCheckout");

        }

    }


    // Method call on proceedCheckout
    private void proceedCheckout(){
        initializeMerchant.proceedToCheckout(component,easyPayCheckoutObj, EasyPayEnvironment.PRODUCTION);
    }


    @Override
    public void checkoutSuccessCallback(String message) {
        Toast.makeText(this,"Response : "+message,Toast.LENGTH_LONG).show();

    }

    @Override
    public void checkoutFailureCallback(String message) {
        Toast.makeText(this,"Response : "+message,Toast.LENGTH_LONG).show();

    }
}
