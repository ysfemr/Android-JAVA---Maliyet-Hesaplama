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
import com.example.ozgrmtl_v3.listeler.Cesitlist;
import java.util.ArrayList;
import java.util.List;

public class cesit_crud_activity extends AppCompatActivity {
    EditText cesit_crud_ekle_ekletext, cesit_crud_guncel_gunceltext, cesit_crud_guncel_ip, cesit_crud_sil_ip;
    Button cesit_crud_ekle_eklebut, cesit_crud_sil_silbut, cesit_crud_guncel_guncelbut;
    Spinner cesit_crud_sil_silspin, cesit_crud_guncel_guncelspin;
    static List<Cesitlist> cesitListe = new ArrayList<>();
    DbHandler dbhandler = new DbHandler(this);
    String cesit_spin_table = "cesit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cesit_crud);
        cesid();
        load_cesitup_spin();
        cesit_crud_ekle_eklebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cescrlabel = cesit_crud_ekle_ekletext.getText().toString();
                if (cescrlabel.trim().length() > 0){
                    dbhandler.insertLabel(cescrlabel, cesit_spin_table); finish();
                    cesit_crud_ekle_ekletext.setText("");
                    Toast toast=Toast.makeText(getApplicationContext(),"Çeşit Eklendi",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();

                    // Hiding the keyboard
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(cesit_crud_ekle_ekletext.getWindowToken(), 0);
                } else {
                    Toast.makeText(getApplicationContext(), "LÜTFEN BOŞ ALAN BIRAKMAYINIZ",
                            Toast.LENGTH_SHORT).show();
                }

            }
        }); //cesit ekle buton click listener
        cesit_crud_sil_silspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Object item = adapterView.getItemAtPosition(position);

                Cesitlist cesit = cesitListe.stream()
                        .filter(x -> item.toString().equals(x.getName()))
                        .findAny()
                        .orElse(null);

                if (item != null) {
                    String hdeid = String.valueOf(cesit.getId()); // seçili verinin id'sini aldık
                    cesit_crud_sil_ip.setText(hdeid);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }); //cesit sil veri ip alındı
        cesit_crud_sil_silbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deid = String.valueOf(cesit_crud_sil_ip.getText());
                if (!deid.isEmpty()){
                    if (dbhandler.deleteLabel(deid, cesit_spin_table)) finish();
                    Toast toast=Toast.makeText(getApplicationContext(),"Çeşit Silindi",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();
                }

            }
        }); //cesit silme isteği gönderdi
        cesit_crud_guncel_guncelspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Object item = adapterView.getItemAtPosition(position);

                Cesitlist cesit = cesitListe.stream()
                        .filter(x -> item.toString().equals(x.getName()))
                        .findAny()
                        .orElse(null);

                if (item != null) {
                    String hupid = String.valueOf(cesit.getId()); // seçili verinin id'sini aldık
                    cesit_crud_guncel_ip.setText(hupid);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }); //cesit güncelle veri ip alındı
        cesit_crud_guncel_guncelbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String upid = String.valueOf(cesit_crud_guncel_ip.getText());
                String cesulabel = cesit_crud_guncel_gunceltext.getText().toString();
                if (cesulabel.trim().length() > 0) {
                    dbhandler.updateLabel(upid, cesulabel, cesit_spin_table); finish();
                    cesit_crud_guncel_gunceltext.setText("");
                    Toast toast=Toast.makeText(getApplicationContext(),"Çeşit Güncellendi",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();

                    // Hiding the keyboard
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(cesit_crud_ekle_ekletext.getWindowToken(), 0);
                } else {
                    Toast.makeText(getApplicationContext(), "LÜTFEN BOŞ ALAN BIRAKMAYINIZ",
                            Toast.LENGTH_SHORT).show();
                }


            }
        }); //hammadde güncelleme isteği gönderdi
    }
    public void cesid(){
        cesit_crud_ekle_ekletext = findViewById(R.id.cesit_crud_ekle_ekletext);
        cesit_crud_guncel_gunceltext = findViewById(R.id.cesit_crud_guncel_gunceltext);
        cesit_crud_guncel_ip = findViewById(R.id.cesit_crud_guncel_ip);
        cesit_crud_sil_ip = findViewById(R.id.cesit_crud_sil_ip);
        cesit_crud_ekle_eklebut = findViewById(R.id.cesit_crud_ekle_eklebut);
        cesit_crud_sil_silbut = findViewById(R.id.cesit_crud_sil_silbut);
        cesit_crud_guncel_guncelbut = findViewById(R.id.cesit_crud_guncel_guncelbut);
        cesit_crud_sil_silspin = findViewById(R.id.cesit_crud_sil_silspin);
        cesit_crud_guncel_guncelspin= findViewById(R.id.cesit_crud_guncel_guncelspin);
    }   //değişken-id eşleme
    public void load_cesitup_spin() {
        List<Cesitlist> cesuplabels = dbhandler.getcesitdata(cesit_spin_table);
        this.cesitListe = cesuplabels;
        List<String> cesupliste = new ArrayList<>();
        if (cesuplabels.size() == 0){
            return;
        }
        for(Cesitlist c: cesuplabels){
            cesupliste.add(c.name);
        }

        ArrayAdapter<String> cesdataadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cesupliste);
        cesdataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cesit_crud_guncel_guncelspin.setAdapter(cesdataadapter);
        cesit_crud_sil_silspin.setAdapter(cesdataadapter);

    } //cesit_updateanddelete_spin adapter


}