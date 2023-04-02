package com.example.ozgrmtl_v3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.ozgrmtl_v3.DataBase.DbHandler;
import com.example.ozgrmtl_v3.activitiys.cesit_crud_activity;
import com.example.ozgrmtl_v3.activitiys.hammadde_crud_activity;
import com.example.ozgrmtl_v3.activitiys.nihai_crud_activity;
import com.example.ozgrmtl_v3.activitiys.olcu_crud_activity;
import com.example.ozgrmtl_v3.activitiys.urun_crud_activity;
import com.example.ozgrmtl_v3.listeler.Cesitlist;
import com.example.ozgrmtl_v3.listeler.Hammaddelist;
import com.example.ozgrmtl_v3.listeler.Olculist;
import com.example.ozgrmtl_v3.listeler.Urunlist;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public Spinner hammadde_spin, urun_spin, cesit_spin, olcu_spin, nihai_tablo_gorspin;
    public RadioButton faturali_check, faturasiz_check, toptan_check, perakende_check;
    public EditText maliyet_edit, hammadde_fiyat_edit, hammadde_gramaj_edit, iscilik_edit, kazanc_edit;
    Button hammadde_crud, urun_crud, cesit_crud, olcu_crud, maliyet_kaydet_but,
            maliyet_hesapla_but, nihai_crud, nihai_gor_but;
    public String hammadde_spin_table = "hammadde";
    public String urun_spin_table = "urun";
    public String cesit_spin_table = "cesit";
    public String olcu_spin_table = "olcu";
    public String db_nihai_tablo = "nihai";


    static List<Hammaddelist> mainhammaddelists = new ArrayList<>();
    static List<Urunlist> mainurunlists = new ArrayList<>();
    static List<Cesitlist> maincesitlists = new ArrayList<>();
    static List<Olculist> mainolculists = new ArrayList<>();
    DbHandler db = new DbHandler(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id();
        load();

        hammadde_crud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hammaddecrudintent =new Intent(MainActivity.this, hammadde_crud_activity.class);
                startActivity(hammaddecrudintent);
            }
        }); //hammadde crud activitys
        urun_crud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uruncrudintent =new Intent(MainActivity.this, urun_crud_activity.class);
                startActivity(uruncrudintent);
            }
        }); //urun crud activitys
        cesit_crud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cesitcrudintent =new Intent(MainActivity.this, cesit_crud_activity.class);
                startActivity(cesitcrudintent);
            }
        }); //cesit crud activitys
        olcu_crud.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent olcucrudintent = new Intent(MainActivity.this, olcu_crud_activity.class);
               startActivity(olcucrudintent);
           }
       });  //olcu crud activitys
        nihai_crud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nihaicrudintent = new Intent(MainActivity.this, nihai_crud_activity.class);
                startActivity(nihaicrudintent);
            }
        }); //nihai crud activity
        maliyet_hesapla_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count();
                Toast toast=Toast.makeText(getApplicationContext(),"Maliyet Hesabı Yapıldı",Toast.LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();
            }
        }); //hesaplama listener
        maliyet_kaydet_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urundata = urun_spin.getSelectedItem().toString();
                String cesitdata = cesit_spin.getSelectedItem().toString();
                String olcudata = olcu_spin.getSelectedItem().toString();
                String maliyetdata = maliyet_edit.getText().toString();
                String faturadata = "";
                String toppardata = "";
                if (faturali_check.isChecked()) faturadata = "faturalı";
                else if (faturasiz_check.isChecked()) faturadata = "faturasız";
                if (toptan_check.isChecked()) toppardata = "toptan";
                else if (perakende_check.isChecked()) toppardata = "perakende";
                Log.i("çalıştı", "kayıt verileri hazır");
                db.nihaiinsert(urundata, cesitdata, olcudata, faturadata, toppardata, maliyetdata, db_nihai_tablo);
                Toast toast=Toast.makeText(getApplicationContext(),"Kayıt Yapıldı",Toast.LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();

            }

        }); //nihai_tablo kayıt listener
        nihai_gor_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("çalıştı", "görüntüleme başladı");
                String mainurun = nihai_tablo_gorspin.getSelectedItem().toString();
                Cursor res = db.nihaigetdataurun(mainurun);
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID :" +res.getInt(0)+"\n");
                    buffer.append("URUN :" +res.getString(1)+"\n");
                    buffer.append("CESİT :" +res.getString(2)+"\n");
                    buffer.append("OLCU :" +res.getString(3)+"\n");
                    buffer.append("FATURA :" +res.getString(4)+"\n");
                    buffer.append("TOPTAN/PERAKENDE :" +res.getString(5)+"\n");
                    buffer.append("MALİYET :" +res.getString(6)+"\n");
                }
                Toast toast=Toast.makeText(getApplicationContext(),"Kayıt Tablosu Görüntüleme",Toast.LENGTH_SHORT);
                toast.setMargin(50,50);
                toast.show();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("ÖZGÜR METAL \n" + mainurun + " TABLOSU");
                builder.setMessage(buffer.toString());
                builder.show();
                Log.i("çalıştı", "görüntüleme bitti");

            }
        }); //nihai_tablo gör listener
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        load();
    }   //telefon geri tuşu aktif olduğunda

    @Override
    protected void onResume() {
        super.onResume();
        load();
    }   //(lifeciycle) activitys devam ediyorsa

    private void load() {
        load_hammadde_spin();
        load_urun_spin();
        load_cesit_spin();
        load_olcu_spin();

    }   //genel load methodu

    private void id(){
        hammadde_spin = findViewById(R.id.hammadde_spin);
        urun_spin = findViewById(R.id.urun_spin);
        cesit_spin = findViewById(R.id.cesit_spin);
        olcu_spin = findViewById(R.id.olcu_spin);
        nihai_tablo_gorspin = findViewById(R.id.nihai_tablo_gorspin);
        faturali_check = findViewById(R.id.faturali_check);
        faturasiz_check = findViewById(R.id.faturasiz_check);
        toptan_check = findViewById(R.id.toptan_check);
        perakende_check = findViewById(R.id.perakende_check);
        maliyet_edit = findViewById(R.id.maliyet_edit);
        hammadde_fiyat_edit = findViewById(R.id.hammadde_fiyat_edit);
        hammadde_gramaj_edit = findViewById(R.id.hammadde_gramaj_edit);
        iscilik_edit = findViewById(R.id.iscilik_edit);
        kazanc_edit = findViewById(R.id.kazanc_edit);
        hammadde_crud = findViewById(R.id.hammadde_crud);
        urun_crud = findViewById(R.id.urun_crud);
        cesit_crud = findViewById(R.id.cesit_crud);
        olcu_crud = findViewById(R.id.olcu_crud);
        nihai_crud = findViewById(R.id.nihai_crud);
        maliyet_kaydet_but = findViewById(R.id.maliyet_kaydet_but);
        maliyet_hesapla_but = findViewById(R.id.maliyet_hesapla_but);
        nihai_gor_but = findViewById(R.id.nihai_gor_but);


    }  //değişken-id eşleme
    public void load_hammadde_spin() {
        List<Hammaddelist> hamlabels = db.gethammaddedata(hammadde_spin_table);
        this.mainhammaddelists = hamlabels;
        List<String> hamliste = new ArrayList<>();
        if (hamlabels.size() == 0){
            return;
        }
        for(Hammaddelist h: hamlabels){
            hamliste.add(h.name);
        }

        ArrayAdapter<String> hamdataadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hamliste);
        hamdataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hammadde_spin.setAdapter(hamdataadapter);
        Log.i("çalıştı", "hammadde verileri yüklendi");


    } //hammadde_spin adapter
    public void load_urun_spin() {
        List<Urunlist> urlabels = db.geturundata(urun_spin_table);
        this.mainurunlists = urlabels;
        List<String> urliste = new ArrayList<>();
        if (urlabels.size() == 0){
            return;
        }
        for(Urunlist u: urlabels){
            urliste.add(u.name);
        }

        ArrayAdapter<String> urdataadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, urliste);
        urdataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        urun_spin.setAdapter(urdataadapter);
        nihai_tablo_gorspin.setAdapter(urdataadapter);
        Log.i("çalıştı", "urun verileri yüklendi");

    } //urun_spin adapter
    public void load_cesit_spin() {
        List<Cesitlist> celabels = db.getcesitdata(cesit_spin_table);
        this.maincesitlists = celabels;
        List<String> celiste = new ArrayList<>();
        if (celabels.size() == 0){
            return;
        }
        for(Cesitlist c: celabels){
            celiste.add(c.name);
        }

        ArrayAdapter<String> urdataadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, celiste);
        urdataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cesit_spin.setAdapter(urdataadapter);
        Log.i("çalıştı", "cesit verileri yüklendi");

    } //cesit_spin adapter
    public void load_olcu_spin() {
        List<Olculist> ollabels = db.getolcudata(olcu_spin_table);
        this.mainolculists = ollabels;
        List<String> olliste = new ArrayList<>();
        if (ollabels.size() == 0){
            return;
        }
        for(Olculist o: ollabels){
            olliste.add(o.name);
        }

        ArrayAdapter<String> urdataadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, olliste);
        urdataadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        olcu_spin.setAdapter(urdataadapter);
        Log.i("çalıştı", "olcu verileri yüklendi");

    } //olcu_spin adapter
    public void count(){
        double hammadde = Double.parseDouble(hammadde_fiyat_edit.getText().toString());
        double gramaj = Double.parseDouble(hammadde_gramaj_edit.getText().toString());
        double iscilik = Double.parseDouble(iscilik_edit.getText().toString());
        double kazanc = Double.parseDouble(kazanc_edit.getText().toString());
        double aratoplam = (hammadde * gramaj) + iscilik + kazanc;  //toptan ve topfaturasız fiyat
        double topfaturali = aratoplam + (aratoplam * 18)/100;  //topfaturalı fiyat
        double perakende = aratoplam + (aratoplam * 25)/100;    //perakende ve perfaturasız fiyat
        double perfaturali = perakende + (perakende * 18)/100;  //perfaturalı fiyat
        if (faturali_check.isChecked()){  //faturalı
            if (perakende_check.isChecked()) maliyet_edit.setText(String.valueOf(perfaturali)); //perfaturalı fiyat
            else if (toptan_check.isChecked()) maliyet_edit.setText(String.valueOf(topfaturali));   //topfaturalı fiyat
        }else   //faturasız
            if (perakende_check.isChecked()) maliyet_edit.setText(String.valueOf(perakende));   //perfaturasız fiyat
            else if (toptan_check.isChecked()) maliyet_edit.setText(String.valueOf(aratoplam));    //topfaturasız fiyat

        Log.i("çalıştı", "count çalıştı");



    }   //maliyet hesaplama
}