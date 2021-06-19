package com.smsoft.controller;

import com.smsoft.entity.Obj;
import com.smsoft.repository.ObjRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class Controller {
    @Autowired
    private ObjRepo objRepo;
    @GetMapping
    public List<Obj> getAllAndAdd(){
        Obj obj=new Obj();
        obj.setCount(new Random().nextInt(10));
        objRepo.save(obj);
        return objRepo.findAll();
    }
    @GetMapping("/{id}")
    public List<Obj> deleteObj(@PathVariable int id){

        objRepo.deleteById(id);
        return objRepo.findAll();
    }

}
