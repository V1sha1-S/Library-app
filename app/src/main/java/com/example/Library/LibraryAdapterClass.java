package com.example.Library;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class LibraryAdapterClass extends RecyclerView.Adapter<LibraryAdapterClass.ViewHolder> {

    List<LibraryModelClass> library;
    Context context;
    DatabaseHelperClass databaseHelperClass;

    public LibraryAdapterClass(List<LibraryModelClass> library, Context context) {
        this.library = library;
        this.context = context;
        databaseHelperClass = new DatabaseHelperClass(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.library_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final LibraryModelClass libraryModelClass = library.get(position);

        holder.textViewID.setText(Integer.toString(libraryModelClass.getId()));
        holder.editText_title.setText(libraryModelClass.getBook_title());
        holder.editText_author.setText(libraryModelClass.getBook_author());
        holder.etPages.setText(libraryModelClass.getPages());

        holder.button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringBook_title = holder.editText_title.getText().toString();
                String stringBook_author = holder.editText_author.getText().toString();
                String StringPages = holder.etPages.getText().toString();

                databaseHelperClass.updateEmployee(new LibraryModelClass(libraryModelClass.getId(),stringBook_title,stringBook_author,StringPages));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperClass.deleteEmployee(libraryModelClass.getId());
                library.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return library.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText editText_title;
        EditText editText_author;
        EditText etPages;
        Button button_Edit;
        Button button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            editText_title = itemView.findViewById(R.id.title_input);
            editText_author = itemView.findViewById(R.id.author_input);
            etPages = itemView.findViewById(R.id.pages_input);
            button_delete = itemView.findViewById(R.id.button_delete);
            button_Edit = itemView.findViewById(R.id.button_edit);

        }
    }
}
