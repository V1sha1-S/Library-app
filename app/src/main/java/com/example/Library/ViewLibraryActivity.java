package com.example.Library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewLibraryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_library);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(this);
        List<LibraryModelClass> libraryModelClasses = databaseHelperClass.getBookList();

        try {
            if (libraryModelClasses.size() > 0){
                LibraryAdapterClass Libraryadapterclass = new LibraryAdapterClass(libraryModelClasses, ViewLibraryActivity.this);
                recyclerView.setAdapter(Libraryadapterclass);
            }else {
                Toast.makeText(this, "There is no book in the database", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(this, "error in fetching data", Toast.LENGTH_SHORT).show();
        }




    }
}
