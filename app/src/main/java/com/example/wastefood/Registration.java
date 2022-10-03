package com.example.wastefood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wastefood.databinding.ActivityContactUsBinding;
import com.example.wastefood.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Registration extends DrawerBaseActivity {
    ActivityRegistrationBinding activityRegistrationBinding;
    EditText fullName,contactNumber,email,password,registerAS;
    Button register;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegistrationBinding= ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(activityRegistrationBinding.getRoot());
        allocatedActivityTitle("Registration");

        fullName=findViewById(R.id.editTextTextPersonName);
        contactNumber=findViewById(R.id.editTextContact);
        email=findViewById(R.id.editEmailAddress);
        password=findViewById(R.id.editTextPassword);
        registerAS=findViewById(R.id.editTextregisteras);

        register=findViewById(R.id.register);
        textView=findViewById(R.id.alreadyhave);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUserInfo();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Registration.this,SignUp.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    private void setUserInfo(){
        String fullname=fullName.getText().toString().trim();
        String contact=contactNumber.getText().toString().trim();
        String Email=email.getText().toString().trim();
        String Password=password.getText().toString().trim();
        String Registeras=registerAS.getText().toString().trim();

        if(fullname.isEmpty()){
            fullName.setError("Name can not be empty!");
            fullName.requestFocus();
        }

        if(contact.isEmpty()){
            contactNumber.setError("Contact Number can not be empty!");
            contactNumber.requestFocus();
        }

        if(contact.length()!=11){
            contactNumber.setError("Contact Number must be 11 digits");
            contactNumber.requestFocus();
        }

        if(Email.isEmpty()){
            email.setError("Email can not be empty!");
            email.requestFocus();
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            email.setError("Enter a valid email address");
            email.requestFocus();
        }

        if(Password.isEmpty())
        {
            password.setError("Password can not be empty!");
            password.requestFocus();
        }

        else if(Password.length()<6)
        {
            password.setError("Password length is less than 6!");
            password.requestFocus();
        }

        if(Registeras.isEmpty()){
            registerAS.setError("Name can not be empty!");
            registerAS.requestFocus();
        }

        HashMap<String,Object> Data=new HashMap<>();
        Data.put("Name",fullname);
        Data.put("ContactNumber",contact);
        Data.put("Email",Email);
        Data.put("Password",Password);
        Data.put("RegisterAS",Registeras);

        Register(Email,Password);
        setDataToDatabase(Data);
    }

    private void setDataToDatabase(HashMap<String, Object> Data) {
        String email= (String) Data.get("Email");
        FirebaseFirestore db= FirebaseFirestore.getInstance();
        db.collection("UserInfo").document(email).set(Data).addOnCompleteListener((Task<Void>task)->{
            if(!task.isSuccessful())
            {
                Log.i("Failed Added","Next,Inshallah");
            }
            else
            {
                Log.i("Added to Database","Alhadulliah");
            }
        });
    }

    private void Register(String Email, String Password){
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Error : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
                else{
                    finish();
                    Intent intent=new Intent(Registration.this,SignUp.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
    }

}