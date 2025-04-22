package com.example.bridge;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

    private Handler handler = new Handler();
    private Runnable runnable;
    public View v;
    //Map<String, String> mes = new HashMap<>();
    List<String> mes;
    private Integer count_mes = 0;

    public LinearLayout messagesContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        messagesContainer = findViewById(R.id.messagesContainer);
        mes = new ArrayList<>();
        // Определяем задачу, которая будет выполняться каждые 5 секунд
        runnable = new Runnable() {
            @Override
            public void run() {
                // Вызываем вашу функцию
                yourFunction();

                // Повторяем задачу через 5 секунд
                handler.postDelayed(this, 5000); // 5000 миллисекунд = 5 секунд
            }
        };

        // Запускаем задачу
        handler.post(runnable);

        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        */
        //reg();
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Удаляем задачу при уничтожении активности, чтобы избежать утечек памяти
        handler.removeCallbacks(runnable);
    }

    private void yourFunction() {
        //System.out.println(mes.size());
        mes = new ArrayList<>();
        //System.out.println(mes.size());
        response();
    }
    public void onSendButtonClick(View view) {
        v = view;
        // Получаем текст из поля ввода
        EditText messageInput = findViewById(R.id.messageInput);
        String messageText = messageInput.getText().toString();

        // Проверяем, что сообщение не пустое
        if (!messageText.isEmpty()) {
            // Добавляем сообщение в область чата
            TextView messageView = new TextView(this);
            messageView.setText(messageText);
            messageView.setPadding(16, 8, 16, 8);
            messagesContainer.addView(messageView);
            Map<String, Object> data = new HashMap<>();
            data.put("mes", messageText);
            newDocRef = db.collection(coolectionPath).document();
            docId = newDocRef.getId();
            newDocRef.set(data);
            // Очищаем поле ввода
            messageInput.setText("");
        } else {
            Toast.makeText(this, "Введите сообщение!", Toast.LENGTH_SHORT).show();
        }
    }

    private void response(){
        //list = new ArrayList<>();
        //mes.clear();
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
                                /*
                                String text = document.getData().toString();
                                messageView.setText(text);
                                messageView.setPadding(16, 8, 16, 8); // Отступы внутри TextView
                                messagesContainer.addView(messageView);
                                */
                                //mes.put(count_mes+": ", document.getData().toString());
                                String s = document.getData().toString().substring(5, document.getData().toString().length()-1);
                                mes.add(s);

                                //System.out.println(mes.size());
                                //Log.d("System.out", document.getId() + " => " + document.getData());
                                //Log.d("System.out", ""+document.getData().getClass());
                            }
                        } else {
                            Log.d("System.out", "Error getting documents: ", task.getException());
                        }
                        messagesContainer.removeAllViews();
                        draw();
                    }
                });
    }
    private void draw(){
        for (int i = 0; i < mes.size(); i++) {
            String item = mes.get(i);
            TextView messageView = new TextView(this);
            messageView.setText(item);
            messageView.setPadding(16, 8, 16, 8); // Отступы внутри TextView
            messagesContainer.addView(messageView);
        }
    }
    private void reg(){
        person = new Person("Bob", 18);
        newDocRef = db.collection(coolectionPath).document();
        docId = newDocRef.getId();
        newDocRef.set(person);
        System.out.println(docId);
    }
/*
    private void sent_message(View view){
        System.out.println("1");
    }*/
}