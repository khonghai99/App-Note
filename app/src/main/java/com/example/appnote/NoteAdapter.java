package com.example.appnote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private ArrayList<Note> listNote;
    private ArrayList<Note> getNote;
    private CustomFilter filter;


    public NoteAdapter(Context context, ArrayList<Note> listNote) {
        this.context = context;
        this.listNote = listNote;
        this.getNote = listNote;
    }


    @Override
    public int getCount() {
        return listNote.size();
    }

    @Override
    public Object getItem(int position) {
        return listNote.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.activity_one_row_note, null);
        }
        Note note = (Note) getItem(position);

        if (note != null) {
            TextView txtNote = (TextView) view.findViewById(R.id.txtNote);
            txtNote.setText(note.getNameNote());
            TextView dateNote = (TextView) view.findViewById(R.id.dateNote);
            dateNote.setText(note.getDateNote().toString());
            /*ImageView imgDel = (ImageView) view.findViewById(R.id.imgDel);
            imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Ok Delete", Toast.LENGTH_SHORT).show();

                }
            });*/
            /*ImageView imgChange = (ImageView) view.findViewById(R.id.imgChange);
            imgChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Ok change", Toast.LENGTH_SHORT).show();
                }
            });*/
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new CustomFilter();
        }
        return filter;
    }

    public class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                constraint = constraint.toString().toUpperCase();
                ArrayList<Note> filterNote = new ArrayList<>();
                for (int i = 0; i < getNote.size(); i++) {
                    if (getNote.get(i).getNameNote().toUpperCase().contains(constraint)) {
                        Note note = new Note(getNote.get(i).getID(), getNote.get(i).getNameNote(), getNote.get(i).getDateNote());
                        filterNote.add(note);
                    }
                }
                results.count = filterNote.size();
                results.values = filterNote;
            } else {
                results.count = getNote.size();
                results.values = getNote;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listNote = (ArrayList<Note>) results.values;
            notifyDataSetChanged();
        }
    }

}
