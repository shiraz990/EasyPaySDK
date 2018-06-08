package application.base.librarytestapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.easypay.widget.Checkout;
import com.easypay.widget.EPCallback;
import com.easypay.widget.EPCheckout;
import com.easypay.widget.EPCheckoutView;
import com.easypay.widget.EPEnvironment;
import com.easypay.widget.EPInitializeMerchant;
import com.easypay.widget.InitializeMerchant;

/**
 * Created by khilt-149 on 6/6/2018.
 */

public class EasyPayCheckoutActivity  extends AppCompatActivity implements EPCallback {
    EPCheckout easyPayCheckoutObj;
    EPCheckoutView component;
    EPInitializeMerchant initializeMerchant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easypay);
        initialization();
        proceedCheckout();
    }
    //Initialization of easypay SDK components
    private void initialization() {
        component = (EPCheckoutView) findViewById(R.id.componentID);

        initializeMerchant = EPInitializeMerchant.getInstance(
                getString(R.string.storeID), getString(R.string.easyPaySecretKey),
                getString(R.string.postBackURL), this);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            easyPayCheckoutObj = (EPCheckout) bundle.getSerializable("EasyPayCheckout");

        }

    }


    // Method call on proceedCheckout
    private void proceedCheckout(){
        initializeMerchant.proceedToCheckout(component,easyPayCheckoutObj, EPEnvironment.PRODUCTION);
    }




    @Override
    public void checkoutViewDidStartLoad(String s) {

    }

    @Override
    public void checkoutViewDidFailLoadWithError(String s) {

    }

    @Override
    public void checkoutViewDidFinishLoad(String s) {
        Toast.makeText(this,"Response : "+ s,Toast.LENGTH_LONG).show();

    }
}
