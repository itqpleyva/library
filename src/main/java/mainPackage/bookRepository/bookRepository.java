package mainPackage.bookRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mainPackage.Model.Book;

@Repository
public interface bookRepository extends JpaRepository<Book,Integer>{

	List<Book> findAllBycategory(String category);
}
