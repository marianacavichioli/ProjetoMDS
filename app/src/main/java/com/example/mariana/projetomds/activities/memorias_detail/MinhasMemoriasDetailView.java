package com.example.mariana.projetomds.activities.memorias_detail;

import com.example.mariana.projetomds.persist.model.Memoria;

public interface MinhasMemoriasDetailView {
    void showDetails(Memoria memoria);

    void showError();
}
