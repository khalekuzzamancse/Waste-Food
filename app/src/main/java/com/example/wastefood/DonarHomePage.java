package com.example.wastefood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DonarHomePage extends AppCompatActivity {
    TextView nametextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar_home_page);

        nametextView=findViewById(R.id.textView7);

        Bundle bundle=getIntent().getExtras();
        String getEmail=bundle.getString("EmailAddress");

        FirebaseFirestore db= FirebaseFirestore.getInstance();
        DocumentReference noteRef=db.collection("UserInfo").document(getEmail);
        noteRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot=task.getResult();
                    if(documentSnapshot!=null && documentSnapshot.exists()){
                        String getacts=documentSnapshot.getString("Name");
                        nametextView.setText(getacts);
                    }
                }
            }
        });
    }
}