package com.example.saltamar1001942895_parcial1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Consulta_Activity extends AppCompatActivity {
    private Spinner spinnerMesas;
    private Spinner spinnerEmpleados;
    private ListView listViewVentas;
    private ArrayAdapter<String> ventasAdapter;
    private List<String> ventasList;
    List<Mesa> mesas;
    List<Empleado> empleados;
    private TextView textViewTotalMesas;

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "MesasTotals";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consulta);

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        spinnerMesas = findViewById(R.id.spinnerMesas);
        spinnerEmpleados = findViewById(R.id.spinnerEmpleados);
        listViewVentas = findViewById(R.id.listViewVentas);
        textViewTotalMesas = findViewById(R.id.textViewTotalMesas);

        ventasList = new ArrayList<>();
        ventasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ventasList);
        listViewVentas.setAdapter(ventasAdapter);

        mesas = MesasManager.getInstance().getMesas();
        if (mesas == null) {
            mesas = new ArrayList<>();
        }

        empleados = new ArrayList<>();
        empleados.add(new Empleado(1, "Santiago"));
        empleados.add(new Empleado(2, "Juan"));
        empleados.add(new Empleado(3, "Pepe"));

        ArrayAdapter<Mesa> mesasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mesas);
        ArrayAdapter<Empleado> adapterEmpleados = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, empleados);
        spinnerMesas.setAdapter(mesasAdapter);
        spinnerEmpleados.setAdapter(adapterEmpleados);

        cargarMesas();
        cargarEmpleados();

        Button btnBuscarPorMesa = findViewById(R.id.btnBuscarPorMesa);
        btnBuscarPorMesa.setOnClickListener(v -> buscarPorMesa());

        actualizarTotalMesas();

        Button btnBuscarPorEmpleado = findViewById(R.id.btnBuscarPorEmpleado);
        btnBuscarPorEmpleado.setOnClickListener(v -> buscarPorEmpleado());

        ventasList = new ArrayList<>();
        ventasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ventasList);
        listViewVentas.setAdapter(ventasAdapter);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void buscarPorEmpleado() {
        Empleado empleadoSeleccionado = (Empleado) spinnerEmpleados.getSelectedItem();
        int idEmpleadoSeleccionado = empleadoSeleccionado.getIdEmpleado();
        ventasList.clear();

        for (Mesa mesa : mesas) {
            int idEmpleadoMesa = sharedPreferences.getInt("empleado_mesa_" + mesa.getIdMesa(), -1);
            if (idEmpleadoMesa == idEmpleadoSeleccionado) {
                double totalMesa = obtenerTotalMesa(mesa.getIdMesa());
                String mensaje = getString(R.string.table_total_sale, mesa.getIdMesa(), totalMesa);
                ventasList.add(mensaje);
            }
        }

        if (ventasList.isEmpty()) {
            ventasList.add(getString(R.string.no_sales_for_employee, empleadoSeleccionado.getNombre()));
        }

        ventasAdapter.notifyDataSetChanged();
    }

    private void buscarPorMesa() {
        Mesa mesaSeleccionada = (Mesa) spinnerMesas.getSelectedItem();
        double total = obtenerTotalMesa(mesaSeleccionada.getIdMesa());
        String mensaje = getString(R.string.table_total_sale, mesaSeleccionada.getIdMesa(), total);
        ventasList.clear();
        ventasList.add(mensaje);
        ventasAdapter.notifyDataSetChanged();
    }

    private double obtenerTotalMesa(int idMesa) {
        return sharedPreferences.getFloat("total_mesa_" + idMesa, 0);
    }

    private void actualizarTotalMesas() {
        double totalGeneral = 0;
        for (Mesa mesa : mesas) {
            totalGeneral += obtenerTotalMesa(mesa.getIdMesa());
        }
        textViewTotalMesas.setText(getString(R.string.total_of_all_tables, totalGeneral));
    }

    private void cargarEmpleados() {
        ArrayAdapter<Empleado> empleadosAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, empleados);
        spinnerEmpleados.setAdapter(empleadosAdapter);

    }

    private void cargarMesas() {
        ArrayAdapter<Mesa> mesasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mesas);
        spinnerMesas.setAdapter(mesasAdapter);
    }
}