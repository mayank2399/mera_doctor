package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class blooddonor extends AppCompatActivity {
    EditText et;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blooddonor);
        et = (EditText) findViewById(R.id.et);
        firestore = FirebaseFirestore.getInstance();
        firestore.collection("donate").whereEqualTo("donate", "T").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (!task.getResult().getDocuments().isEmpty()) {
                    Map m = task.getResult().getDocuments().get(0).getData();
                    String s, t, u, v, w;
                    s = (String) m.get("name");
                    t = (String) m.get("age");
                    u = (String) m.get("address");
                    v = (String) m.get("contact");
                    w = (String) m.get("blood_group");
                    et.setText("Name:-"+s+"\nAge-"+t+"\n"+"Contact:-"+v+"\n"+"Blood Group:-"+w+"\n"+"Address:-"+u);
                    Map<String, Object> may = new HashMap<>();
                    may.put("name", s);
                    may.put("age", t);
                    may.put("address", u);
                    may.put("contact", v);
                    may.put("blood_group", w);
                    may.put("donate", "F");
                    firestore.collection("lab").document(s).set(may);

                }
                else
                { et.setText("No data");

                }
            }


        });




    }
}
