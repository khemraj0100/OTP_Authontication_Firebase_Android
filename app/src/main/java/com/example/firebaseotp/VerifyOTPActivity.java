package com.example.firebaseotp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyOTPActivity extends AppCompatActivity {

    private EditText input1,input2,input3,input4,input5,input6;
    private String verificationID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_o_t_p);

        Button buttonBT=findViewById(R.id.buttonBT);
        TextView resendOtpTV=findViewById(R.id.resendOtpTV);
        resendOtpTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" +getIntent().getStringExtra("mobile")
                        ,60, TimeUnit.SECONDS,VerifyOTPActivity.this,new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
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
                    public void onCodeSent(@NonNull String newverificationCode, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken)
                    {
                        super.onCodeSent(newverificationCode, forceResendingToken);
                        verificationID =newverificationCode;
                        Toast.makeText(getApplicationContext(),"opt sent",Toast.LENGTH_LONG).show();


                    }
                });

            }
        });

        verificationID =getIntent().getStringExtra("verificationCode");
        buttonBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input1.getText().toString().trim().isEmpty()
                    ||input2.getText().toString().trim().isEmpty()
                    || input3.getText().toString().trim().isEmpty()
                    || input4.getText().toString().trim().isEmpty()
                    || input5.getText().toString().trim().isEmpty()
                    || input6.getText().toString().trim().isEmpty()){
                    Toast.makeText(VerifyOTPActivity.this,"enter valid code",Toast.LENGTH_LONG).show();
                    return;
                }
                String code =
                        input1.getText().toString().trim()+ input2.getText().toString().trim()+ input3.getText().toString().trim()
                        + input4.getText().toString().trim()+ input5.getText().toString().trim()+ input6.getText().toString().trim();

                if (verificationID !=null){

                    PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(
                            verificationID,code
                    );

                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful())
                                    {
                                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(VerifyOTPActivity.this,"enter code is invalid",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                }
            }

        });

        TextView textPhone =findViewById(R.id.appCompatTextView3);
        textPhone.setText(String.format("+91-%s",getIntent().getStringExtra("mobile")));
        input1=findViewById(R.id.input1);
        input2=findViewById(R.id.input2);
        input3=findViewById(R.id.input3);
        input4=findViewById(R.id.input4);
        input5=findViewById(R.id.input5);
        input6=findViewById(R.id.input6);
        setUpOtp();
    }
    private void setUpOtp(){

        input1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    input2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    input3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    input4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    input5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        input5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    input6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}