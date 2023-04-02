package com.example.ozgrmtl_v3.activitiys;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.ozgrmtl_v3.DataBase.DbHandler;
import com.example.ozgrmtl_v3.R;
import com.example.ozgrmtl_v3.listeler.Nihailist;
import java.util.ArrayList;
import java.util.List;

public class nihai_crud_activity extends AppCompatActivity {
    Spinner nihai_crud_sil_silspin_olcu, nihai_crud_guncel_guncelspin_olcu;
    EditText nihai_crud_nihai_maliyet, nihai_crud_guncel_maliyet;
    Button nihai_crud_sil_silbut, nihai_crud_guncel_guncelbut;
    static List<Nihailist> nihailiste = new ArrayList<>();
    DbHandler dbhandler = new DbHandler(this);
    String nihai_spin_table = "NIHAI";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nihai_crud);
        nihid();
        load();
        nihai_crud_sil_silbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String niid = String.valueOf(nihai_crud_sil_silspin_olcu.getSelectedItem());
                if (!niid.isEmpty()){
                    if (dbhandler.deleteLabel(niid, nihai_spin_table)) finish();
                    Toast toast=Toast.makeText(getApplicationContext(),"Kayıt Silindi",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();
                }
            }
        }); //nihai sil listener
        nihai_crud_guncel_guncelspin_olcu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                load_maliyet();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        nihai_crud_guncel_guncelbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String upid = String.valueOf(nihai_crud_guncel_guncelspin_olcu.getSelectedItem());
                String hamulabel = nihai_crud_guncel_maliyet.getText().toString();
                if (hamulabel.trim().length() > 0) {
                    dbhandler.updatemaliyetLabel(upid, hamulabel, nihai_spin_table); finish();
                    nihai_crud_guncel_maliyet.setText("");
                    Toast toast=Toast.makeText(getApplicationContext(),"Kayıt Güncellendi",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();

                    // Hiding the keyboard
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(nihai_crud_guncel_maliyet.getWindowToken(), 0);
                } else {
                    Toast.makeText(getApplicationContext(), "LÜTFEN BOŞ ALAN BIRAKMAYINIZ",
                            Toast.LENGTH_SHORT).show();
                }


            }
        }); //nihai güncelle listener


    }
    public void nihid(){
        nihai_crud_sil_silspin_olcu = findViewById(R.id.nihai_crud_sil_silspin_olcu);
        nihai_crud_guncel_guncelspin_olcu = findViewById(R.id.nihai_crud_guncel_guncelspin_olcu);
        nihai_crud_nihai_maliyet = findViewById(R.id.nihai_crud_nihai_maliyet);
        nihai_crud_guncel_maliyet = findViewById(R.id.nihai_crud_guncel_maliyet);
        nihai_crud_sil_silbut = findViewById(R.id.nihai_crud_sil_silbut);
        nihai_crud_guncel_guncelbut = findViewById(R.id.nihai_crud_guncel_guncelbut);
    }   //değiken id eşleme
    public void load(){
        load_nihaisilid_spin();
    }   //genel load methodu
    public void load_nihaisilid_spin(){
        List<Nihailist> nihuplabels = dbhandler.getnihaidata(nihai_spin_table);
        this.nihailiste = nihuplabels;
        List<String> nihupliste = new ArrayList<>();

        if (nihuplabels.size() == 0){
            return;
        }
        for(Nihailist n: nihuplabels){
            nihupliste.add(String.valueOf(n.id));
        }
        ArrayAdapter<String> nihdataadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nihupliste);
        nihdataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nihai_crud_sil_silspin_olcu.setAdapter(nihdataadapter);
        nihai_crud_guncel_guncelspin_olcu.setAdapter(nihdataadapter);

    }   //sil spine id atar
    public void load_maliyet(){
        List<Nihailist> nihuplabels = dbhandler.getnihaiiddata(nihai_spin_table);
        this.nihailiste = nihuplabels;
        List<String> nihupliste = new ArrayList<>();

        if (nihuplabels.size() == 0){
            return;
        }
        for(Nihailist n: nihuplabels){
            nihupliste.add(String.valueOf(n.mname));
        }
        String id = nihai_crud_guncel_guncelspin_olcu.getSelectedItem().toString();
        int guncelid = Integer.parseInt(id);
        String eskimaliyet = nihupliste.get(guncelid - 1);
        nihai_crud_nihai_maliyet.setText(eskimaliyet);


    }   //eski maliyet atama


}