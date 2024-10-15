package com.example.lab6.demo6.Controller;

import com.example.lab6.demo6.Model.Category;
import com.example.lab6.demo6.Service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("")
    public List<Category> getCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable ("id") int id) {
        Category category = categoryService.findById(id);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.save(category);
        return ResponseEntity.status(201).body(savedCategory);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable ("id")  int id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();  // Trả về 404 nếu không tìm thấy
        }
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
//    @RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.PUT})
//    public ResponseEntity<Category> createOrUpdateCategory(@RequestBody Category category) {
//        // mã xử lý cho cả POST và PUT
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category updatedCategory) {
        Category category = categoryService.findById(id);
        if (category != null) {
            // Cập nhật các thuộc tính từ updatedCategory
            category.setName(updatedCategory.getName());
            category.setDescription(updatedCategory.getDescription());

            // Lưu lại thay đổi
            categoryService.save(category);
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}


