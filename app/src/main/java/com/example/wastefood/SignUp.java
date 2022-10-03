package com.example.wastefood;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wastefood.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class SignUp extends DrawerBaseActivity {
    ActivitySignUpBinding activitySignUpBinding;
    TextView textView;
    EditText email,password;
    Button signin;
    String getacts;
    FirebaseAuth mAuth= FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySignUpBinding= ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(activitySignUpBinding.getRoot());
        allocatedActivityTitle("Log In");

        textView=findViewById(R.id.textviewSignUp);
        signin=findViewById(R.id.login);
        //checkuserlog();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this,Registration.class));
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=findViewById(R.id.editTextTextEmailAddress);
                password=findViewById(R.id.editTextTextPassword);
                String Email=email.getText().toString().trim();
                String Password=password.getText().toString().trim();


                FirebaseAuth mAuth= FirebaseAuth.getInstance();
                FirebaseFirestore db= FirebaseFirestore.getInstance();
                DocumentReference noteRef=db.collection("UserInfo").document(Email);
                noteRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot documentSnapshot=task.getResult();
                            if(documentSnapshot!=null && documentSnapshot.exists()){
                                getacts=documentSnapshot.getString("RegisterAS"); //donar
                            }
                        }
                    }
                });
                System.out.println(getacts);
                mAuth.signInWithEmailAndPassword(Email,Password).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(SignUp.this, "Error : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        else{
                            finish();
                            if(getacts.equalsIgnoreCase("donar")){
                                Intent intent=new Intent(SignUp.this,DonarHomePage.class);
                                intent.putExtra("EmailAddress",Email);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        }
                    }
                });
            }
        });
    }
}