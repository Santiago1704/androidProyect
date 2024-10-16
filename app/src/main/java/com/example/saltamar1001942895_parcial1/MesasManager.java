package com.example.saltamar1001942895_parcial1;

import java.util.ArrayList;
import java.util.List;

public class MesasManager {
    private static MesasManager instance;
    private List<Mesa> mesas;

    private MesasManager() {
        mesas = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            mesas.add(new Mesa(i));
        }
    }

    public static MesasManager getInstance() {
        if (instance == null) {
            instance = new MesasManager();
        }
        return instance;
    }

    public List<Mesa> getMesas() {
        return mesas;
    }

    public Mesa getMesaById(int idMesa) {
        for (Mesa mesa : mesas) {
            if (mesa.getIdMesa() == idMesa) {
                return mesa;
            }
        }
        return null;
    }

    public double getTotalVentasTodasMesas() {
        double totalGeneral = 0;
        for (Mesa mesa : mesas) {
            totalGeneral += mesa.getTotal();
        }
        return totalGeneral;
    }
}
