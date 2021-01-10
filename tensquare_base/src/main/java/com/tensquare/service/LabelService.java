package com.tensquare.service;

import com.tensquare.dao.LabelDao;
import com.tensquare.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    public void save(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    public void update(Label label){
        labelDao.save(label);
    }

    public void delete(String id){
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            /***
             *
             * @param root
             * @param criteriaQuery
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                ArrayList<Predicate> predicates = new ArrayList<>();

                if(label.getLabelname()!=null&&label.getLabelname()!=""){
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    predicates.add(predicate);
                }
                if(label.getState()!=null&&label.getState()!=""){
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
                    predicates.add(predicate);
                }
                Predicate[] parr = new Predicate[predicates.size()];
                parr = predicates.toArray(parr);
                return cb.and(parr);
            }
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size) {

        Pageable pageable = PageRequest.of(page-1,size);
        return labelDao.findAll(new Specification<Label>() {
            /***
             *
             * @param root
             * @param criteriaQuery
             * @return
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                ArrayList<Predicate> predicates = new ArrayList<>();

                if(label.getLabelname()!=null&&label.getLabelname()!=""){
                    Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    predicates.add(predicate);
                }
                if(label.getState()!=null&&label.getState()!=""){
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
                    predicates.add(predicate);
                }
                Predicate[] parr = new Predicate[predicates.size()];
                parr = predicates.toArray(parr);
                return cb.and(parr);
            }
        }, pageable);
    }
}
