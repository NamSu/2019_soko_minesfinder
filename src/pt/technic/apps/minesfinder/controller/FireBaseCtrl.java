package pt.technic.apps.minesfinder.controller;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.*;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * create NamYounSu, LeeJeongHun, JeongWu
 *
 */
public class FireBaseCtrl {
    private static final String DATABASE_URL = "https://minesfinder-rank.firebaseio.com"; // don't change url
    private FirebaseDatabase firebaseDatabase;

    public FireBaseCtrl() {
        try {
            FileInputStream serviceAccount = new FileInputStream(System.getProperty("user.dir") + "/src/pt/technic/apps/minesfinder/resources/" + "minesfinder-rank-firebase.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(DATABASE_URL)
                    .build();

            FirebaseApp.initializeApp(options);
            firebaseDatabase = FirebaseDatabase.getInstance(DATABASE_URL);
        } catch (IOException e) {
            Logger.getLogger(FireBaseCtrl.class.getName()).log(Level.SEVERE, "I/O is not intelligent!");
        }
    }

    public void update(Object value, String level, String name) {
        long unixTime = System.currentTimeMillis() / 1000;
        try {
            DatabaseReference scoreReference = firebaseDatabase.getReference(level).child(String.valueOf(unixTime)).child("score"); // ex. level(param1) -> score + "value"
            DatabaseReference nameReference = firebaseDatabase.getReference(level).child(String.valueOf(unixTime)).child("name"); // ex. level(param2) -> name + "name"
            final CountDownLatch latch = new CountDownLatch(1);

            nameReference.setValue(name, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        Logger.getLogger(FireBaseCtrl.class.getName()).log(Level.SEVERE, "name data not saved.");
                        latch.countDown();
                    } else {
                        Logger.getLogger(FireBaseCtrl.class.getName()).log(Level.INFO, "name data saved.");
                        latch.countDown();
                    }
                }
            });

            scoreReference.setValue(value, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        Logger.getLogger(FireBaseCtrl.class.getName()).log(Level.SEVERE, "score data not saved.");
                        latch.countDown();
                    } else {
                        Logger.getLogger(FireBaseCtrl.class.getName()).log(Level.INFO, "score data saved.");
                        latch.countDown();
                    }
                }
            });

            latch.await();
        } catch (InterruptedException e) {
            Logger.getLogger(FireBaseCtrl.class.getName()).log(Level.SEVERE, "interrupted!");
            Thread.currentThread().interrupt();
        }
    }

    public void close() {
        Logger.getLogger(FireBaseCtrl.class.getName()).log(Level.INFO, "firebase controller is safely closed.");
        firebaseDatabase.getApp().delete();
    }
}