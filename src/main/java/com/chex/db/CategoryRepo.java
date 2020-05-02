package com.chex.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chex.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

}
