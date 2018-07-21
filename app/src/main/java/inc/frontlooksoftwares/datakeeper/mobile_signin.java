package inc.frontlooksoftwares.datakeeper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class mobile_signin extends Activity implements
        View.OnClickListener {

    private static final String TAG = "PhoneAuthActivity";

    private static final String KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress";

    private static final int STATE_INITIALIZED = 1;
    private static final int STATE_CODE_SENT = 2;
    private static final int STATE_VERIFY_FAILED = 3;
    private static final int STATE_VERIFY_SUCCESS = 4;
    private static final int STATE_SIGNIN_FAILED = 5;
    private static final int STATE_SIGNIN_SUCCESS = 6;
    int back = Color.parseColor("#70e2aa23");
    int pink = Color.parseColor("#70dd2476");
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    private ViewGroup mPhoneNumberViews;
    private ViewGroup mSignedInViews;

    private boolean mVerificationInProgress = false;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private Button mStartButton;
    private Button mVerifyButton;
    private Button mResendButton;
    private Button mSignOutButton;

    private EditText mPhoneNumberField;
    private EditText mVerificationField;

    private TextView show_time, code_sent, instruction, instruction2;
//    private CountDownTimer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobile_signin);

        // Restore instance state
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }

// Assign views

        show_time = findViewById(R.id.show_time);
        code_sent = findViewById(R.id.code_sent);
        mPhoneNumberField = findViewById(R.id.mob_no);
        mVerificationField = findViewById(R.id.code);
        mStartButton = findViewById(R.id.mobno_submit);
        mVerifyButton = findViewById(R.id.submitcode);
        mResendButton = findViewById(R.id.coderesend);
        instruction = findViewById(R.id.instruction);
        instruction2 = findViewById(R.id.instruction2);

        mSignOutButton = findViewById(R.id.sign_out_button);

        // Assign click listeners
        mStartButton.setOnClickListener(this);
        mVerifyButton.setOnClickListener(this);
        mResendButton.setOnClickListener(this);
        mSignOutButton.setOnClickListener(this);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        myRef = mFirebaseDatabase.getReference();
        // Initialize phone auth callbacks
// [START phone_auth_callbacks]
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verificaiton without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:" + credential);
                // [START_EXCLUDE silent]
                mVerificationInProgress = false;
                // [END_EXCLUDE]

                // [START_EXCLUDE silent]
                // Update the UI and attempt sign in with the phone credential
                updateUI(STATE_VERIFY_SUCCESS, credential);
                // [END_EXCLUDE]
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e);
                // [START_EXCLUDE silent]
                mVerificationInProgress = false;
                // [END_EXCLUDE]

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // [START_EXCLUDE]
                    mPhoneNumberField.setError("Invalid phone number.");
                    // [END_EXCLUDE]
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // [START_EXCLUDE]
                    //  Snackbar.make(findViewById(android.R.id.content), "Quota exceeded.",
                    //    Snackbar.LENGTH_SHORT).show();
                    // [END_EXCLUDE]
                }

                // Show a message and update the UI
                // [START_EXCLUDE]
                updateUI(STATE_VERIFY_FAILED);
                // [END_EXCLUDE]
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:" + verificationId);
                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                // [START_EXCLUDE]
                // Update UI
                updateUI(STATE_CODE_SENT);
                // [END_EXCLUDE]
            }
        };
// [END phone_auth_callbacks]
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

        // [START_EXCLUDE]
        if (mVerificationInProgress && validatePhoneNumber()) {
            startPhoneNumberVerification(mPhoneNumberField.getText().toString());
        }
        // [END_EXCLUDE]
    }
    // [END on_start_check_user]

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_VERIFY_IN_PROGRESS, mVerificationInProgress);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mVerificationInProgress = savedInstanceState.getBoolean(KEY_VERIFY_IN_PROGRESS);
    }


    private void startPhoneNumberVerification(String phoneNumber) {
        // [START start_phone_auth]
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
        // [END start_phone_auth]
        CountDownTimer timer = new CountDownTimer(60000, 1000) {
            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
                show_time.setText("seconds remaining: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
//                mResendButton.setVisibility(View.GONE);
            }

            @SuppressLint("SetTextI18n")
            public void onFinish() {
                show_time.setText("Time Up!!");
//                mResendButton.setVisibility(View.VISIBLE);
            }
        }.start();

        mVerificationInProgress = true;
    }
    public String uid;

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        // [START verify_with_code]
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential);
    }

    // [START resend_verification]
    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
//        code_sent.setText("Code Requested");
        CountDownTimer timer = new CountDownTimer(60000, 1000) {
            @SuppressLint("SetTextI18n")
            public void onTick(long millisUntilFinished) {
                show_time.setText("seconds remaining: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
//                mResendButton.setVisibility(View.GONE);
            }

            @SuppressLint("SetTextI18n")
            public void onFinish() {
                show_time.setText("Time Up!!");
//                mResendButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    // [END resend_verification]

    // [START sign_in_with_phone]
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();


                            //check if user exist or not in Database


                            final String userID = user.getUid();
                            uid= userID;

//                            myRef.child("users").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(DataSnapshot snapshot) {
//                                    if (snapshot.getValue() != null) {
//
//                                        Intent y = new Intent(mobile_signin.this, MainActivity.class);
//                                        y.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                        y.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                        startActivity(y);
//
//                                        SharedPreferences mPreferences;
//
//                                        mPreferences = getSharedPreferences("User", MODE_PRIVATE);
//                                        SharedPreferences.Editor editor = mPreferences.edit();
//                                        editor.putString("saveuserid", userID);
//                                        editor.commit();
//
//                                        //user exists, do something
//                                    } else {
//
//                                        SharedPreferences mPreferences;
//
//                                        mPreferences = getSharedPreferences("User", MODE_PRIVATE);
//                                        SharedPreferences.Editor editor = mPreferences.edit();
//                                        editor.putString("saveuserid", userID);
//                                        editor.commit();
//
//                                        String contactno = mPhoneNumberField.getText().toString();
//                                        //user does not exist, do something else
//                                        myRef.child("users").child(userID).setValue("true");
//                                        //    myRef.child("users").child(userID).child("Name").setValue("true");
//                                        myRef.child("users").child(userID).child("contact").setValue(contactno);
//
//
//                                        Intent y = new Intent(mobile_signin.this, MainActivity.class);
//                                        y.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                        y.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                        startActivity(y);
//
//                                    }
//                                }
//
//                                public void onCancelled(DatabaseError arg0) {
//                                }
//                            });
//
//
//                            //  myRef.child(userID).child("title").setValue("true");
//                            //    myRef.child(userID).child("description").setValue("true");
//                            // myRef.child(userID).child("imageurl").setValue("true");
//                            //  myRef.child(userID).child("url").setValue("true");
//                            // [START_EXCLUDE]
//                            updateUI(STATE_SIGNIN_SUCCESS, user);
//                            // [END_EXCLUDE]
//                        } else {
//                            // Sign in failed, display a message and update the UI
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
//                                // The verification code entered was invalid
//                                // [START_EXCLUDE silent]
//                                mVerificationField.setError("Invalid code.");
//                                // [END_EXCLUDE]
//                            }
//                            // [START_EXCLUDE silent]
//                            // Update UI
//                            updateUI(STATE_SIGNIN_FAILED);
//                            // [END_EXCLUDE]
                        }
                    }
                });
    }
    // [END sign_in_with_phone]

    private void signOut() {
        mAuth.signOut();
        updateUI(STATE_INITIALIZED);
    }

    private void updateUI(int uiState) {
        updateUI(uiState, mAuth.getCurrentUser(), null);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            updateUI(STATE_SIGNIN_SUCCESS, user);
        } else {
            updateUI(STATE_INITIALIZED);
        }
    }

    private void updateUI(int uiState, FirebaseUser user) {
        updateUI(uiState, user, null);
    }

    private void updateUI(int uiState, PhoneAuthCredential cred) {
        updateUI(uiState, null, cred);
    }

    private void updateUI(int uiState, FirebaseUser user, PhoneAuthCredential cred) {
        switch (uiState) {
            case STATE_INITIALIZED:
                // Initialized state, show only the phone number field and start button
                enableViews(mStartButton, mPhoneNumberField, instruction);
                disableViews(mVerifyButton, mResendButton, mVerificationField, code_sent);
//                mDetailText.setText(null);
                break;
            case STATE_CODE_SENT:
                // Code sent state, show the verification field, the
                enableViews(mVerifyButton, mResendButton, mPhoneNumberField, mVerificationField, code_sent);
                disableViews(mStartButton, instruction);
                code_sent.setText("Code Requested");
                //  mDetailText.setText(R.string.status_code_sent);
                Toast.makeText(mobile_signin.this, "code sent", Toast.LENGTH_LONG).show();
                break;
            case STATE_VERIFY_FAILED:
                // Verification has failed, show all options
                enableViews(mStartButton, instruction, mVerifyButton, mResendButton, mPhoneNumberField, mVerificationField);
                disableViews(code_sent);
                //  mDetailText.setText(R.string.status_verification_failed);
                mStartButton.setVisibility(View.VISIBLE);
                mPhoneNumberField.setVisibility(View.VISIBLE);
                instruction.setVisibility(View.VISIBLE);
                instruction2.setVisibility(View.VISIBLE);
                Toast.makeText(mobile_signin.this, "verification failed, check if phone number is correct", Toast.LENGTH_LONG).show();
                break;
            case STATE_VERIFY_SUCCESS:
                // Verification has succeeded, proceed to firebase sign in
                disableViews(mStartButton, instruction, mVerifyButton, mResendButton, mPhoneNumberField, mVerificationField);
                //  mDetailText.setText(R.string.status_verification_succeeded);
                Toast.makeText(mobile_signin.this, "verification success", Toast.LENGTH_LONG).show();
                // Set the verification text based on the credential
                if (cred != null) {
                    if (cred.getSmsCode() != null) {
                        mVerificationField.setText(cred.getSmsCode());
                    } else {
                        mVerificationField.setText(R.string.instant_validation);
                    }
                }

                break;
            case STATE_SIGNIN_FAILED:
                // No-op, handled by sign-in check
                //  mDetailText.setText(R.string.status_sign_in_failed);
                break;
            case STATE_SIGNIN_SUCCESS:
                // Np-op, handled by sign-in check
                int SPLASH_DISPLAY_LENGTH = 0;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                Logger.d("Start splash screen");
                        mAuthListener = new FirebaseAuth.AuthStateListener() {
                            @Override
                            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                if (user != null) {
                                    startActivity(new Intent(mobile_signin.this, MainActivity.class));
                                }
                            }
                        };
                        //add listener
                        mAuth.addAuthStateListener(mAuthListener);
                    }
                }, SPLASH_DISPLAY_LENGTH);
                break;
        }

        if (user == null) {
            // Signed out
//              mPhoneNumberViews.setVisibility(View.VISIBLE);
//                mSignedInViews.setVisibility(View.GONE);

            //  mStatusText.setText(R.string.signed_out);
        } else {
            // Signed in


            //  mStatusText.setText(R.string.signed_in);
            //  mDetailText.setText(getString(R.string.firebase_status_fmt, user.getUid()));
        }
    }

    private boolean validatePhoneNumber() {
        String phoneNumber = mPhoneNumberField.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)) {
            mPhoneNumberField.setError("Invalid phone number.");
            return false;
        }

        return true;
    }

    private void enableViews(View... views) {
        for (View v : views) {
            v.setEnabled(true);
        }
    }

    private void disableViews(View... views) {
        for (View v : views) {
            v.setEnabled(false);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mobno_submit:
                if (!validatePhoneNumber()) {
                    return;
                }

//                mStartButton.setBackgroundColor(pink);

                startPhoneNumberVerification(mPhoneNumberField.getText().toString());

                mResendButton.setVisibility(View.VISIBLE);
                mVerificationField.setVisibility(View.VISIBLE);
                mVerifyButton.setVisibility(View.VISIBLE);
                mStartButton.setVisibility(View.GONE);
                mPhoneNumberField.setVisibility(View.GONE);
                instruction.setVisibility(View.GONE);
                instruction2.setVisibility(View.GONE);


                break;
            case R.id.submitcode:
                String code = mVerificationField.getText().toString();
                if (TextUtils.isEmpty(code)) {
                    mVerificationField.setError("Cannot be empty.");
                    return;
                }

                verifyPhoneNumberWithCode(mVerificationId, code);
                break;
            case R.id.coderesend:
                code_sent.setText("Requesting Code");
                resendVerificationCode(mPhoneNumberField.getText().toString(), mResendToken);
                break;
            case R.id.sign_out_button:
                signOut();
                break;
        }
    }

}



