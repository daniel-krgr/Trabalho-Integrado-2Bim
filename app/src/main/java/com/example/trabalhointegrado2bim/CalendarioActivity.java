package com.example.trabalhointegrado2bim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CalendarioActivity extends AppCompatActivity {

    CalendarView calendarView;
    Calendar calendar;
    TextView tvSwitchAtividades;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calendario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
            tvSwitchAtividades = findViewById(R.id.tvSwitchAtividades);

            tvSwitchAtividades.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(CalendarioActivity.this, AtividadesActivity.class);

                    startActivity(intent);
                }
            });

                    calendarView = findViewById(R.id.calendarView);
            calendar = Calendar.getInstance();

            setDate(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));

            getDate();
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView calendarView, int ano, int mes, int dia) {

                    Toast.makeText(CalendarioActivity.this, dia+ "/"+ (mes+1) +"/"+ ano, Toast.LENGTH_SHORT).show();

                }
            });

    }

    public void getDate() {

        long date = calendarView.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        calendar.setTimeInMillis(date);
        String selectedDate = simpleDateFormat.format(calendar.getTime());
        Toast.makeText(this, selectedDate, Toast.LENGTH_SHORT).show();


    }

    public void setDate(int dia, int mes, int ano) {
        calendar.set(Calendar.YEAR, ano);
        calendar.set(Calendar.MONTH, mes - 1);
        calendar.set(Calendar.DAY_OF_MONTH, dia);

        long milli = calendar.getTimeInMillis();

        calendarView.setDate(milli);
    }
}
