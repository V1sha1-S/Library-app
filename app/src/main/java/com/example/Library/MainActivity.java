package com.example.Library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText editText_title,editText_author,etPages;
    Button button_add,button_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_title = findViewById(R.id.title_input);
        editText_author = findViewById(R.id.author_input);
        etPages=findViewById(R.id.pages_input);
        button_add = findViewById(R.id.button_add);
        button_view = findViewById(R.id.button_view);


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringBook_title = editText_title.getText().toString();
                String stringAuthor = editText_author.getText().toString();
                String stringPages = etPages.getText().toString();

                try {
                    if (stringBook_title.length() <=0 || stringAuthor.length() <=0 || stringPages.length() <=0){
                        Toast.makeText(MainActivity.this, "Enter All Data", Toast.LENGTH_SHORT).show();
                    }else {
                        DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(MainActivity.this);
                        LibraryModelClass libraryModelClass = new LibraryModelClass(stringBook_title,stringAuthor,stringPages);
                        databaseHelperClass.addBook(libraryModelClass);
                        Toast.makeText(MainActivity.this, "Added Book Details Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(getIntent());
                    }
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error in Adding Book Details", Toast.LENGTH_SHORT).show();
                }
            }
        });


        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewLibraryActivity.class);
                startActivity(intent);
            }
        });


    }
}
