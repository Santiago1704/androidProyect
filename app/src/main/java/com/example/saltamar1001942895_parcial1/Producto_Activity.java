package com.example.saltamar1001942895_parcial1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Producto_Activity extends AppCompatActivity {
   // private Mesa mesa;
    private List<Producto> productos;
    private ArrayAdapter<Producto> adapterCategorias;
    private ArrayAdapter<String> adapterTipoProducto;
    private ArrayAdapter<Mesa> adapterMesas;
    private Spinner spinnerEmpleados;
    private ArrayAdapter<Empleado> adapterEmpleados;

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "MesasTotals";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_producto);

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Spinner spinnerMesas = findViewById(R.id.spinnerMesas);
        Spinner spinnerCategorias = findViewById(R.id.spinnerCategorias);
        Spinner spinnerTipoProducto = findViewById(R.id.spinnerTipoProducto);
        spinnerEmpleados = findViewById(R.id.spinnerEmpleados);

        List<Mesa> mesas = MesasManager.getInstance().getMesas();

        List<Empleado> empleados = new ArrayList<>();
        empleados.add(new Empleado(1, "Santiago"));
        empleados.add(new Empleado(2, "Juan"));
        empleados.add(new Empleado(3, "Pepe"));

        productos = new ArrayList<>();
        productos.add(new Producto("Coca-Cola", 2.0, "Bebidas", "Bebida"));
        productos.add(new Producto("Pizza", 8.0, "Comida", "Comida"));
        productos.add(new Producto("Ensalada", 5.0, "Comida", "Comida"));
        productos.add(new Producto("Papas Fritas", 10.0, "Comida", "Comida"));
        productos.add(new Producto("Agua Mineral", 1.5, "Bebidas", "Bebida"));
        productos.add(new Producto("Hamburguesa", 6.0, "Comida", "Comida"));
        productos.add(new Producto("Tacos", 7.0, "Comida", "Comida"));
        productos.add(new Producto("Sopa", 4.0, "Comida", "Comida"));
        productos.add(new Producto("Refresco de Naranja", 2.5, "Bebidas", "Bebida"));
        productos.add(new Producto("Gelato", 3.0, "Postres", "Postre"));
        productos.add(new Producto("Pastel de Chocolate", 4.5, "Postres", "Postre"));
        productos.add(new Producto("Nachos", 5.5, "Comida", "Comida"));
        productos.add(new Producto("Cerveza", 3.0, "Bebidas", "Bebida"));
        productos.add(new Producto("Batido de Fresa", 2.5, "Bebidas", "Bebida"));
        productos.add(new Producto("Crepas", 6.0, "Postres", "Postre"));

        List<String> tiposDeProductos = new ArrayList<>();
        for (Producto producto : productos) {
            if (!tiposDeProductos.contains(producto.getTipo())) {
                tiposDeProductos.add(producto.getTipo());
            }
        }

        adapterCategorias = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, productos);
        adapterTipoProducto = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tiposDeProductos);
        adapterMesas = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mesas);
        adapterEmpleados = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, empleados);

        spinnerCategorias.setAdapter(adapterCategorias);
        spinnerTipoProducto.setAdapter(adapterTipoProducto);
        spinnerMesas.setAdapter(adapterMesas);
        spinnerEmpleados.setAdapter(adapterEmpleados);

        Button btnAgregarProducto = findViewById(R.id.btnAgregarProducto);
        btnAgregarProducto.setOnClickListener(v -> {
            Producto productoSeleccionado = (Producto) spinnerCategorias.getSelectedItem();
            Mesa mesaSeleccionada = (Mesa) spinnerMesas.getSelectedItem();
            Empleado empleadoSeleccionado = (Empleado) spinnerEmpleados.getSelectedItem();

            mesaSeleccionada.agregarProducto(productoSeleccionado);
            guardarTotalMesa(mesaSeleccionada.getIdMesa(), mesaSeleccionada.getTotal());
            guardarEmpleadoMesa(mesaSeleccionada.getIdMesa(), empleadoSeleccionado.getIdEmpleado());

            TextView totalView = findViewById(R.id.textTotal);
            totalView.setText(String.format(getString(R.string.total_mesa), mesaSeleccionada.getTotal()));
            Toast.makeText(this, String.format(getString(R.string.producto_agregado), empleadoSeleccionado.getNombre()), Toast.LENGTH_SHORT).show();
        });
    }

    private void guardarEmpleadoMesa(int idMesa, int idEmpleado) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("empleado_mesa_" + idMesa, idEmpleado);
        editor.apply();
    }

    private void guardarTotalMesa(int idMesa, double total) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("total_mesa_" + idMesa, (float) total);
        editor.apply();
    }
}