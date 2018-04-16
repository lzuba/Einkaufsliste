package lzuba.einkaufsliste;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.*;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference database;

    private Button saveButton;
    private ListView listview;
    private EditText todoText;

    private ArrayList<String> todoData;
    private ArrayList<String> todoKeys;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.database = FirebaseDatabase.getInstance().getReference("lists");
        this.saveButton = (Button) findViewById(R.id.saveButton);
        this.listview = (ListView) findViewById(R.id.list);
        this.todoText = (EditText) findViewById(R.id.todoText);
        this.todoData = new ArrayList<>();
        this.todoKeys = new ArrayList<>();

        this.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todo = todoText.getText().toString().trim();

                database.child("list1").push().setValue(todo);

                todoText.setText("");
            }
        });

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,this.todoData);
        listview.setAdapter(arrayAdapter);

        this.database.child("list1").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                todoData.add(value);

                String key = dataSnapshot.getKey();
                todoKeys.add(key);

                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                String key = dataSnapshot.getKey();
                int index = todoKeys.indexOf(key);
                todoData.set(index,value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                int index = todoKeys.indexOf(key);
                todoData.remove(index);
                todoKeys.remove(index);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        this.listview.setLongClickable(true);
        this.listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String key = todoKeys.get(position);
                database.child("list1").child(key).removeValue();
                return true;
            }
        });

    }
}
