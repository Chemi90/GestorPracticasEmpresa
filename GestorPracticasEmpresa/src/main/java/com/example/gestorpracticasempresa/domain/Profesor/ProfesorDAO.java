package com.example.gestorpracticasempresa.domain.Profesor;

import com.example.gestorpracticasempresa.persistence.DAO;

import java.util.ArrayList;

public class ProfesorDAO implements DAO<ProfesorEntity> {
    @Override
    public ArrayList<ProfesorEntity> getAll() {
        return null;
    }

    @Override
    public ProfesorEntity get(Long id) {
        return null;
    }

    @Override
    public ProfesorEntity save(ProfesorEntity data) {
        return null;
    }

    @Override
    public void update(ProfesorEntity data) {

    }

    @Override
    public void delete(ProfesorEntity data) {

    }

    @Override
    public boolean remove(ProfesorEntity profesor) {
        return false;
    }
}
