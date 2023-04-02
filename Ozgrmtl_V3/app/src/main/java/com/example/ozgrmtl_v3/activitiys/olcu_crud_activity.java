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
import com.example.ozgrmtl_v3.listeler.Olculist;
import java.util.ArrayList;
import java.util.List;

public class olcu_crud_activity extends AppCompatActivity {
    EditText olcu_crud_ekle_ekletext, olcu_crud_guncel_gunceltext, olcu_crud_guncel_ip, olcu_crud_sil_ip;
    Button olcu_crud_ekle_eklebut, olcu_crud_sil_silbut, olcu_crud_guncel_guncelbut;
    Spinner olcu_crud_sil_silspin, olcu_crud_guncel_guncelspin;
    static List<Olculist> olcuListe = new ArrayList<>();
    DbHandler dbhandler = new DbHandler(this);
    String olcu_spin_table = "olcu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olcu_crud);
        olid();
        load_olcuup_spin();
        olcu_crud_ekle_eklebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String olcrlabel = olcu_crud_ekle_ekletext.getText().toString();
                if (olcrlabel.trim().length() > 0){
                    dbhandler.insertLabel(olcrlabel, olcu_spin_table); finish();
                    olcu_crud_ekle_ekletext.setText("");
                    Toast toast=Toast.makeText(getApplicationContext(),"Ölçü Eklendi",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();

                    // Hiding the keyboard
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(olcu_crud_ekle_ekletext.getWindowToken(), 0);
                } else {
                    Toast.makeText(getApplicationContext(), "LÜTFEN BOŞ ALAN BIRAKMAYINIZ",
                            Toast.LENGTH_SHORT).show();
                }

            }
        }); //olcu ekle buton click listener
        olcu_crud_sil_silspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Object item = adapterView.getItemAtPosition(position);

                Olculist olcu = olcuListe.stream()
                        .filter(x -> item.toString().equals(x.getName()))
                        .findAny()
                        .orElse(null);

                if (item != null) {
                    String hdeid = String.valueOf(olcu.getId()); // seçili verinin id'sini aldık
                    olcu_crud_sil_ip.setText(hdeid);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }); //olcu sil veri ip alındı
        olcu_crud_sil_silbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deid = String.valueOf(olcu_crud_sil_ip.getText());
                if (!deid.isEmpty()){
                    if (dbhandler.deleteLabel(deid, olcu_spin_table)) finish();
                    Toast toast=Toast.makeText(getApplicationContext(),"Ölçü Silindi",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();
                }

            }
        }); //olcu silme isteği gönderdi
        olcu_crud_guncel_guncelspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Object item = adapterView.getItemAtPosition(position);

                Olculist olcu = olcuListe.stream()
                        .filter(x -> item.toString().equals(x.getName()))
                        .findAny()
                        .orElse(null);

                if (item != null) {
                    String hupid = String.valueOf(olcu.getId()); // seçili verinin id'sini aldık
                    olcu_crud_guncel_ip.setText(hupid);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        }); //hammadde güncelle veri ip alındı
        olcu_crud_guncel_guncelbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String upid = String.valueOf(olcu_crud_guncel_ip.getText());
                String olulabel = olcu_crud_guncel_gunceltext.getText().toString();
                if (olulabel.trim().length() > 0) {
                    dbhandler.updateLabel(upid, olulabel, olcu_spin_table); finish();
                    olcu_crud_guncel_gunceltext.setText("");
                    Toast toast=Toast.makeText(getApplicationContext(),"Ölçü Güncellendi",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();

                    // Hiding the keyboard
                    InputMethodManager imm = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(olcu_crud_ekle_ekletext.getWindowToken(), 0);
                } else {
                    Toast.makeText(getApplicationContext(), "LÜTFEN BOŞ ALAN BIRAKMAYINIZ",
                            Toast.LENGTH_SHORT).show();
                }


            }
        }); //hammadde güncelleme isteği gönderdi

    }
    public void olid(){
        olcu_crud_ekle_ekletext = findViewById(R.id.olcu_crud_ekle_ekletext);
        olcu_crud_guncel_gunceltext = findViewById(R.id.olcu_crud_guncel_gunceltext);
        olcu_crud_guncel_ip = findViewById(R.id.olcu_crud_guncel_ip);
        olcu_crud_sil_ip = findViewById(R.id.olcu_crud_sil_ip);
        olcu_crud_ekle_eklebut = findViewById(R.id.olcu_crud_ekle_eklebut);
        olcu_crud_sil_silbut = findViewById(R.id.olcu_crud_sil_silbut);
        olcu_crud_guncel_guncelbut = findViewById(R.id.olcu_crud_guncel_guncelbut);
        olcu_crud_sil_silspin = findViewById(R.id.olcu_crud_sil_silspin);
        olcu_crud_guncel_guncelspin= findViewById(R.id.olcu_crud_guncel_guncelspin);
    }   //değişken-id eşleme
    public void load_olcuup_spin() {
        List<Olculist> oluplabels = dbhandler.getolcudata(olcu_spin_table);
        this.olcuListe = oluplabels;
        List<String> olupliste = new ArrayList<>();
        if (oluplabels.size() == 0){
            return;
        }
        for(Olculist o: oluplabels){
            olupliste.add(o.name);
        }

        ArrayAdapter<String> oldataadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, olupliste);
        oldataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        olcu_crud_guncel_guncelspin.setAdapter(oldataadapter);
        olcu_crud_sil_silspin.setAdapter(oldataadapter);

    } //olcu_updateanddelete_spin adapter


}