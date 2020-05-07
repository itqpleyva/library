package mainPackage.bookRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import mainPackage.Model.Category;

public interface categoryRepository extends JpaRepository<Category,String> {
	

}
