package com.example.recyclowaste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclowaste.model.Advertisment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyAds extends AppCompatActivity {

   /* TextView et_title ;
    TextView et_description;
    TextView et_price;
    TextView et_quantity;*/

    private RecyclerView recyclerView;
    private AdapterMyAds adapter;
    private DatabaseReference dbRef;
    private List<Advertisment> ads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ads2);

        recyclerView = findViewById(R.id.MyAdsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this ));

        ads = new ArrayList<>();

        dbRef = FirebaseDatabase.getInstance().getReference().child("Advertisment").child("user1");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren() ){
                    Advertisment ad = snap.getValue(Advertisment.class);
                    ads.add(ad);
                }

                adapter = new AdapterMyAds(getApplicationContext(),ads);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void onClickEdit(View view){
        Intent intent = new Intent(MyAds.this , EditAd.class);
        intent.putExtra("title","Sample Title");
        intent.putExtra("Description","Sample Description");
        intent.putExtra("price","10");
        intent.putExtra("quantity","4");
      //  intent.putExtra(Intent.EXTRA_STREAM , imageURI);
        intent.putExtra("key","-MkGmb-IdBBKNZ27tqKI");
        startActivity(intent);

    }
}