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
import com.example.ozgrmtl_v3.listeler.Hammaddelist;
import java.util.ArrayList;
import java.util.List;

public class hammadde_crud_activity extends AppCompatActivity {
    EditText hammadde_crud_ekle_ekletext, hammadde_crud_guncel_gunceltext, hammadde_crud_guncel_ip, hammadde_crud_sil_ip;
    Button hammadde_crud_ekle_eklebut, hammadde_crud_sil_silbut, hammadde_crud_guncel_guncelbut;
    Spinner hammadde_crud_sil_silspin, hammadde_crud_guncel_guncelspin;
    static List<Hammaddelist> hammaddeListe = new ArrayList<>();
    DbHandler dbhandler = new DbHandler(this);
    String hammadde_spin_table = "hammadde";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hammadde_crud);
        hamid();
        load_hammaddeup_spin();

        hammadde_crud_ekle_eklebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hamcrlabel = hammadde_crud_ekle_ekletext.getText().toString();
                if (hamcrlabel.trim().length() > 0){
                    dbhandler.insertLabel(hamcrlabel, hammadde_spin_table); finish();
                    hammadde_crud_ekle_ekletext.setText("");
                    Toast toast=Toast.makeText(getApplicationContext(),"Hammadde Eklendi",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();

                    // Hiding the keyboard
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(hammadde_crud_ekle_ekletext.getWindowToken(), 0);
                } else {
                    Toast.makeText(getApplicationContext(), "LÜTFEN BOŞ ALAN BIRAKMAYINIZ",
                            Toast.LENGTH_SHORT).show();
                }

            }
        }); //hammadde ekle buton click listener
        hammadde_crud_sil_silspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Object item = adapterView.getItemAtPosition(position);

                Hammaddelist hammadde = hammaddeListe.stream()
                        .filter(x -> item.toString().equals(x.getName()))
                        .findAny()
                        .orElse(null);

                if (item != null) {
                    String hdeid = String.valueOf(hammadde.getId()); // seçili verinin id'sini aldık
                    hammadde_crud_sil_ip.setText(hdeid);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }); //hammadde sil veri ip alındı
        hammadde_crud_sil_silbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deid = String.valueOf(hammadde_crud_sil_ip.getText());
                if (!deid.isEmpty()){
                    if (dbhandler.deleteLabel(deid, hammadde_spin_table)) finish();
                    Toast toast=Toast.makeText(getApplicationContext(),"Hammadde Silindi",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();
                }
//                ma.load_hammadde_spin();

            }
        }); //hammadde silme isteği gönderdi
        hammadde_crud_guncel_guncelspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Object item = adapterView.getItemAtPosition(position);

                Hammaddelist hammadde = hammaddeListe.stream()
                        .filter(x -> item.toString().equals(x.getName()))
                        .findAny()
                        .orElse(null);

                if (item != null) {
                    String hupid = String.valueOf(hammadde.getId()); // seçili verinin id'sini aldık
                    hammadde_crud_guncel_ip.setText(hupid);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }); //hammadde güncelle veri ip alındı
        hammadde_crud_guncel_guncelbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String upid = String.valueOf(hammadde_crud_guncel_ip.getText());
                String hamulabel = hammadde_crud_guncel_gunceltext.getText().toString();
                if (hamulabel.trim().length() > 0) {
                    dbhandler.updateLabel(upid, hamulabel, hammadde_spin_table); finish();
                    hammadde_crud_guncel_gunceltext.setText("");
                    Toast toast=Toast.makeText(getApplicationContext(),"Hammadde Güncellendi",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();

                    // Hiding the keyboard
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(hammadde_crud_ekle_ekletext.getWindowToken(), 0);
                } else {
                    Toast.makeText(getApplicationContext(), "LÜTFEN BOŞ ALAN BIRAKMAYINIZ",
                            Toast.LENGTH_SHORT).show();
                }


            }
        }); //hammadde güncelleme isteği gönderdi

    }
    public void hamid(){
        hammadde_crud_ekle_ekletext = findViewById(R.id.hammadde_crud_ekle_ekletext);
        hammadde_crud_guncel_gunceltext = findViewById(R.id.hammadde_crud_guncel_gunceltext);
        hammadde_crud_guncel_ip = findViewById(R.id.hammadde_crud_guncel_ip);
        hammadde_crud_sil_ip = findViewById(R.id.hammadde_crud_sil_ip);
        hammadde_crud_ekle_eklebut = findViewById(R.id.hammadde_crud_ekle_eklebut);
        hammadde_crud_sil_silbut = findViewById(R.id.hammadde_crud_sil_silbut);
        hammadde_crud_guncel_guncelbut = findViewById(R.id.hammadde_crud_guncel_guncelbut);
        hammadde_crud_sil_silspin = findViewById(R.id.hammadde_crud_sil_silspin);
        hammadde_crud_guncel_guncelspin= findViewById(R.id.hammadde_crud_guncel_guncelspin);
    }   //değişken-id eşleme
    public void load_hammaddeup_spin() {
        List<Hammaddelist> hamuplabels = dbhandler.gethammaddedata(hammadde_spin_table);
        this.hammaddeListe = hamuplabels;
        List<String> hamupliste = new ArrayList<>();
        if (hamuplabels.size() == 0){
            return;
        }
        for(Hammaddelist h: hamuplabels){
            hamupliste.add(h.name);
        }

        ArrayAdapter<String> hamdataadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hamupliste);
        hamdataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hammadde_crud_guncel_guncelspin.setAdapter(hamdataadapter);
        hammadde_crud_sil_silspin.setAdapter(hamdataadapter);

    } //hammadde_updateanddelete_spin adapter




}