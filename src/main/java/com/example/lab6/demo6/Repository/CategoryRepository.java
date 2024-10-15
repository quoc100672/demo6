package com.example.lab6.demo6.Repository;

import com.example.lab6.demo6.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}