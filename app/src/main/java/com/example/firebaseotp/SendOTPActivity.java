package com.example.firebaseotp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SendOTPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_o_t_p);

        final EditText input=findViewById(R.id.getTextET);
        Button getotpBT= findViewById(R.id.buttonBT);

        getotpBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (input.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter phone number",Toast.LENGTH_LONG).show();
                    return;
                }
                PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" +input.getText().toString(),60,TimeUnit.SECONDS,SendOTPActivity.this,new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
                {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential)
                    {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e)
                    {

                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        Log.d("Error","****e.getMessage()****"+e.getMessage());

                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationCode, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken)
                    {
                        super.onCodeSent(verificationCode, forceResendingToken);


                        Intent intent = new Intent(getApplicationContext(), VerifyOTPActivity.class);
                        intent.putExtra("mobile", input.getText().toString().trim());
                        intent.putExtra("verificationCode", verificationCode);
                        startActivity(intent);


                    }
                });



            }
        });

    }
}