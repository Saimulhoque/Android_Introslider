package com.infobox.introslide;

        import android.os.PersistableBundle;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.ArrayAdapter;
        import android.widget.Spinner;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.util.ArrayList;
        import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    List<String> districtname;
    DatabaseReference databasestudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        spinner = findViewById(R.id.spinnerid);

        districtname = new ArrayList<>();
        databasestudent = FirebaseDatabase.getInstance().getReference("student");
        newM();


    }

    private void newM() {
        databasestudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot studentsnapsot:dataSnapshot.getChildren()){
                    String s =studentsnapsot.getKey();
                    districtname.add(s);

                }
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,districtname);

                spinner.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}