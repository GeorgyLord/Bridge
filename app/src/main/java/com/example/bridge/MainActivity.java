package com.example.bridge;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private List<Pair<String, HashMap<String, String>>> list = new ArrayList<>();
    private final String coolectionPath = "people";
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference newDocRef;
    String docId;
    Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        reg();
        //response();
        /*
        db.collection(coolectionPath)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG123", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d("TAG123", "Error getting documents: ", task.getException());
                        }
                    }
                });
         */
        /*
        CollectionReference collectionRef = db.collection("people");
        DocumentReference docRef = db.collection("people").document("10");
        Log.d("TAG123", "AAA ");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("TAG123", "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d("TAG123", "No such document");
                    }
                } else {
                    Log.d("TAG123", "get failed with ", task.getException());
                }
            }
        });
        */
        //String id = collectionRef.get().get;
        //System.out.println(id);

        //Map<String, Object> data = new HashMap<>();
        //data.put("name", "Bob");
        /*
        try {
            FileInputStream serviceAccount = new FileInputStream("path/to/your/serviceAccountKey.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }*/
        //db.collection("people").document().set(data);
        //data.clear();
        /*
        Iterable<CollectionReference> collections = db.listCollections();
        CollectionReference c = db.lis;
        String collectionName = "people";
        String documentName = "3XqWpw1COJgcot5cyUlx";
*/
        /*DocumentReference docRef = db.collection(collectionName).document(documentName);
        docRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                // Access all fields in the document
                Map<String, Object> data = documentSnapshot.getData();
                if (data != null) {
                    for (Map.Entry<String, Object> entry : data.entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                }
            } else {
                System.out.println("No such document");
            }
        }).addOnFailureListener(e -> {
            System.out.println("get failed with " + e);
        });*/
    }

    private void response(){
        //list = new ArrayList<>();
        db.collection(coolectionPath)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //HashMap<String, String> hm = new HashMap<>();
                                //hm.put("apple", "10");
                                //hm.put("banana", "20");
                                //list.add(new Pair<>(document.getId(), hm));
                                //Log.d("TAG123", ""+list.size());
                                Log.d("TAG123", document.getId() + " => " + document.getData());
                                //Log.d("TAG123", ""+document.getData().getClass());
                            }
                        } else {
                            Log.d("TAG123", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
    private void reg(){
        person = new Person("Bob", 18);
        newDocRef = db.collection(coolectionPath).document();
        docId = newDocRef.getId();
        newDocRef.set(person);
        System.out.println(docId);
    }
}