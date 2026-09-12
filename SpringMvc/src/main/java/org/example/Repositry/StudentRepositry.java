package org.example.Repositry;

import org.example.Entity.student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Repository
public class StudentRepositry {
    HashMap<String, student> hm;
    public StudentRepositry() {
        hm=new HashMap<>();
    }
    public student find(String id){
        return hm.get(id);
    }
    public List<student> findAll() {
        return new ArrayList<>(hm.values());
    }
    public student save(student s1) {
        hm.put(s1.getId(),s1);
        return s1;
    }
}
