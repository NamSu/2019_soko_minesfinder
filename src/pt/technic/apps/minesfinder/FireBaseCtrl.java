package pt.technic.apps.minesfinder;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.*;
import java.util.concurrent.CountDownLatch;

public class FireBaseCtrl {
    private static final String DATABASE_URL = "https://minesfinder-rank.firebaseio.com";
    private FirebaseDatabase firebaseDatabase;

    public FireBaseCtrl() {

        try {
            FileInputStream serviceAccount = new FileInputStream(System.getProperty("user.dir") + "\\" + "minesfinder-rank-firebase.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(DATABASE_URL)
                    .build();

            FirebaseApp.initializeApp(options);
            firebaseDatabase = FirebaseDatabase.getInstance(DATABASE_URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Object value, String level, String key) {
        try {
            DatabaseReference reference = firebaseDatabase.getReference(level).child(key);
            final CountDownLatch latch = new CountDownLatch(1);
            reference.setValue(value, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        System.out.println("data not saved.");
                        latch.countDown();
                    } else {
                        System.out.println("data saved");
                        latch.countDown();
                    }
                }
            });
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        firebaseDatabase.getApp().delete();
    }
}
