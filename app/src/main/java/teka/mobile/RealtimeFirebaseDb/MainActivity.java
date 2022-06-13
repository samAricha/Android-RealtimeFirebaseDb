package teka.mobile.RealtimeFirebaseDb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText fName, fPrice, fQuantity;
    Button saveBtn, retrieveBtn;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference root = database.getReference().child("MyKUKU");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fName = findViewById(R.id.f_name);
        fPrice = findViewById(R.id.f_price);
        fQuantity = findViewById(R.id.f_quantity);
        saveBtn = findViewById(R.id.save_btn);
        retrieveBtn = findViewById(R.id.retrieve_btn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference feedsRef = root.child("feeds");
                String feed_name = fName.getText().toString();
                String feed_price_per_kg = fPrice.getText().toString();
                String feed_quantity_per_week = fQuantity.getText().toString();

                Calendar calendar = Calendar.getInstance();
                //calendar.setTimeInMillis(time);
                String date = DateFormat.format("dd/MM/yyyy HH:mm", calendar).toString();

                //storing the record in firebase
                HashMap<String, String> weeklyFeedRecordMap = new HashMap<>();
                weeklyFeedRecordMap.put("name", feed_name);
                weeklyFeedRecordMap.put("price", feed_price_per_kg);
                weeklyFeedRecordMap.put("quantity", feed_quantity_per_week);
                weeklyFeedRecordMap.put("date", date);

                feedsRef.push().setValue(weeklyFeedRecordMap).
                        addOnCompleteListener(task -> Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_LONG).show());
            }
        });

        retrieveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ShowActivity.class));
            }
        });
    }
}