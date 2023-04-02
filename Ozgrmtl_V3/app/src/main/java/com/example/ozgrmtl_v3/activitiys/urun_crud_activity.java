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
import java.util.ArrayList;
import java.util.List;
import com.example.ozgrmtl_v3.listeler.Urunlist;

public class urun_crud_activity extends AppCompatActivity {
    EditText urun_crud_ekle_ekletext, urun_crud_guncel_gunceltext, urun_crud_guncel_ip, urun_crud_sil_ip;
    Button urun_crud_ekle_eklebut, urun_crud_sil_silbut, urun_crud_guncel_guncelbut;
    Spinner urun_crud_sil_silspin, urun_crud_guncel_guncelspin;
    static List<Urunlist> urunListe = new ArrayList<>();
    DbHandler dbhandler = new DbHandler(this);
    String urun_spin_table = "urun";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_crud);
        urid();
        load_urunup_spin();
        urun_crud_ekle_eklebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urcrlabel = urun_crud_ekle_ekletext.getText().toString();
                if (urcrlabel.trim().length() > 0){
                    dbhandler.insertLabel(urcrlabel, urun_spin_table); finish();
                    urun_crud_ekle_ekletext.setText("");
                    Toast toast=Toast.makeText(getApplicationContext(),"Ürün Eklendi",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();

                    // Hiding the keyboard
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(urun_crud_ekle_ekletext.getWindowToken(), 0);
                } else {
                    Toast.makeText(getApplicationContext(), "LÜTFEN BOŞ ALAN BIRAKMAYINIZ",
                            Toast.LENGTH_SHORT).show();
                }

            }
        }); //urun ekle buton click listener
        urun_crud_sil_silspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Object item = adapterView.getItemAtPosition(position);

                Urunlist urun = urunListe.stream()
                        .filter(x -> item.toString().equals(x.getName()))
                        .findAny()
                        .orElse(null);

                if (item != null) {
                    String hdeid = String.valueOf(urun.getId()); // seçili verinin id'sini aldık
                    urun_crud_sil_ip.setText(hdeid);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }); //urun sil veri ip alındı
        urun_crud_sil_silbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deid = String.valueOf(urun_crud_sil_ip.getText());
                if (!deid.isEmpty()){
                    if (dbhandler.deleteLabel(deid, urun_spin_table)) finish();
                    Toast toast=Toast.makeText(getApplicationContext(),"Ürün Silindi",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();
                }

            }
        }); //urun silme isteği gönderdi
        urun_crud_guncel_guncelspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Object item = adapterView.getItemAtPosition(position);

                Urunlist urun = urunListe.stream()
                        .filter(x -> item.toString().equals(x.getName()))
                        .findAny()
                        .orElse(null);

                if (item != null) {
                    String hupid = String.valueOf(urun.getId()); // seçili verinin id'sini aldık
                    urun_crud_guncel_ip.setText(hupid);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }); //hammadde güncelle veri ip alındı
        urun_crud_guncel_guncelbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String upid = String.valueOf(urun_crud_guncel_ip.getText());
                String urulabel = urun_crud_guncel_gunceltext.getText().toString();
                if (urulabel.trim().length() > 0) {
                    dbhandler.updateLabel(upid, urulabel, urun_spin_table); finish();
                    urun_crud_guncel_gunceltext.setText("");
                    Toast toast=Toast.makeText(getApplicationContext(),"Ürün Güncellendi",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();

                    // Hiding the keyboard
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(urun_crud_ekle_ekletext.getWindowToken(), 0);
                } else {
                    Toast.makeText(getApplicationContext(), "LÜTFEN BOŞ ALAN BIRAKMAYINIZ",
                            Toast.LENGTH_SHORT).show();
                }


            }
        }); //urun güncelleme isteği gönderdi

    }
    public void urid(){
        urun_crud_ekle_ekletext = findViewById(R.id.urun_crud_ekle_ekletext);
        urun_crud_guncel_gunceltext = findViewById(R.id.urun_crud_guncel_gunceltext);
        urun_crud_guncel_ip = findViewById(R.id.urun_crud_guncel_ip);
        urun_crud_sil_ip = findViewById(R.id.urun_crud_sil_ip);
        urun_crud_ekle_eklebut = findViewById(R.id.urun_crud_ekle_eklebut);
        urun_crud_sil_silbut = findViewById(R.id.urun_crud_sil_silbut);
        urun_crud_guncel_guncelbut = findViewById(R.id.urun_crud_guncel_guncelbut);
        urun_crud_sil_silspin = findViewById(R.id.urun_crud_sil_silspin);
        urun_crud_guncel_guncelspin= findViewById(R.id.urun_crud_guncel_guncelspin);
    }   //değişken-id eşleme
    public void load_urunup_spin() {
        List<Urunlist> uruplabels = dbhandler.geturundata(urun_spin_table);
        this.urunListe = uruplabels;
        List<String> urupliste = new ArrayList<>();
        if (uruplabels.size() == 0){
            return;
        }
        for(Urunlist u: uruplabels){
            urupliste.add(u.name);
        }

        ArrayAdapter<String> urdataadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, urupliste);
        urdataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        urun_crud_guncel_guncelspin.setAdapter(urdataadapter);
        urun_crud_sil_silspin.setAdapter(urdataadapter);

    } //hammadde_updateanddelete_spin adapter


}