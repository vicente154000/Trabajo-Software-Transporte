package com.example.aplicacion;

import android.graphics.Color;  // Needed for chart text coloring
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.components.LimitLine;


public class EstadisticasActivity extends AppCompatActivity {

    private PieChart pieChart;
    private BarChart barChart;
    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);

        // Buscamos los chart.
        pieChart = findViewById(R.id.pieChart);
        barChart = findViewById(R.id.barChart);
        lineChart = findViewById(R.id.lineChart);

        /*
            Obtencion de los datos gracias a realizar las llamadas necesarias.
        */

        // Fake data:
        int[] intensidadCounts = {5, 10, 15};            // baja, media, alta
        List<Float> durations = Arrays.asList(30f, 45f, 60f, 50f, 70f);  // example durations

        setupCharts(intensidadCounts, durations);

        // Botón Volver al hacer click finalizará la actividad
        findViewById(R.id.buttonVolverEstadisticas).setOnClickListener(v -> finish());
    }

    private void setupCharts(int[] counts, List<Float> durations) {
        setupPieAndBar(counts);
        setupLine(durations);
    }

    private void setupPieAndBar(int[] counts) {
        String[] labels = {"Baja", "Media", "Alta"};

        // Pie Chart
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < counts.length; i++) {
            pieEntries.add(new PieEntry(counts[i], labels[i]));
        }
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Intensidad");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueTextColor(Color.WHITE);
        pieChart.setData(pieData);
        pieChart.setHoleColor(Color.TRANSPARENT);
        pieChart.getLegend().setTextColor(Color.WHITE);
        pieChart.getDescription().setEnabled(false);
        pieChart.invalidate();

        // Bar Chart
        List<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < counts.length; i++) {
            barEntries.add(new BarEntry(i, counts[i]));
        }
        BarDataSet barDataSet = new BarDataSet(barEntries, "Intensidad");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        BarData barData = new BarData(barDataSet);
        barData.setValueTextColor(Color.WHITE);

        barChart.setData(barData);
        barChart.getLegend().setTextColor(Color.WHITE);
        barChart.getDescription().setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(Arrays.asList(labels)));
        xAxis.setGranularity(1f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisLineColor(Color.WHITE);

        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setTextColor(Color.WHITE);
        yAxis.setDrawGridLines(false);
        barChart.getAxisRight().setEnabled(false);

        barChart.invalidate();
    }



    // Gráfico de Línea: mostramos la duración de cada entrenamiento.
    private void setupLine(List<Float> durations) {
        List<Entry> lineEntries = new ArrayList<>();
        for (int i = 0; i < durations.size(); i++) {
            lineEntries.add(new Entry(i, durations.get(i)));
        }

        LineDataSet lineDataSet = new LineDataSet(lineEntries, "Duración");
        lineDataSet.setColor(Color.BLUE);
        lineDataSet.setValueTextColor(Color.WHITE);
        LineData lineData = new LineData(lineDataSet);

        lineChart.setData(lineData);
        lineChart.getLegend().setTextColor(Color.WHITE);
        lineChart.getData().setValueTextColor(Color.WHITE);

        XAxis xAxisLine = lineChart.getXAxis();
        xAxisLine.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxisLine.setGranularity(1f);
        xAxisLine.setTextColor(Color.WHITE);
        xAxisLine.setDrawGridLines(false);
        xAxisLine.setAxisLineColor(Color.WHITE);

        YAxis yAxisLine = lineChart.getAxisLeft();
        yAxisLine.setTextColor(Color.WHITE);
        yAxisLine.setDrawGridLines(false);
        lineChart.getAxisRight().setEnabled(false);

        lineChart.getDescription().setEnabled(false);
        lineChart.invalidate();
    }


    public void onVolverClick(View view) {
        finish();
    }
}
