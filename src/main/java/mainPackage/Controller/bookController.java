package mainPackage.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;



import mainPackage.storage.StorageService;
import mainPackage.Model.Book;
import mainPackage.Model.Category;
import mainPackage.bookRepository.bookRepository;
import mainPackage.bookRepository.categoryRepository;


@Controller
public class bookController {
	public static final long FIXED_RATE = 5000;

    @Autowired
    TaskScheduler taskScheduler;

    ScheduledFuture<?> scheduledFuture;
    
	@Autowired
    private SimpMessagingTemplate template;
	
	
	@Autowired
	bookRepository repository;
	
	@Autowired
	categoryRepository catrepository;
	
	private final StorageService storageService;

	@Autowired
	public bookController(StorageService storageService) {
		this.storageService = storageService;
	}
	@PostMapping("/unsubscribe")
	  public void unsubscribe(@RequestBody String cat) {
		
		System.out.println("desde el form leyendo: "+cat);
	  }
	@GetMapping("/")
	  public String petForm(Model model) {
	    model.addAttribute("book", new Book());
	    return "index";
	  }
	@GetMapping("/insert")
	  public String bookForm(Model model) {
	    model.addAttribute("book", new Book());
	    return "bookForm";
	  }

	 @PostMapping("/")
	  public String bookSubmit(@ModelAttribute Book b, BindingResult bindingResult, @RequestParam("file") MultipartFile file, @RequestParam("cover") MultipartFile cover) {
		 
		 b.setUrl("http://localhost:8080/files/"+file.getOriginalFilename());
		 
		 b.setCover("http://localhost:8080/files/"+cover.getOriginalFilename());
		 
		 storageService.store(file);
		 storageService.store(cover);
		 b.setId((int)System.nanoTime());
		 repository.save(b);
		 
		return "bookDetails";
	  }
	@RequestMapping("/books/{bookId}")
	public String getAllBooks(@PathVariable String bookId, Model model){
		
	
		Optional<Book> book = repository.findById(Integer.parseInt(bookId));
		model.addAttribute("book", book.get());
		
		return "bookDetails";
	}
	@GetMapping("/books")
	public String getAllBooks(Model model){
		
		List<Book> books = repository.findAll();
		model.addAttribute("books", books);
		
		return "booksList";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	@Scheduled(fixedRate = 5000)
	@Transactional
	public void romance() throws Exception {
		Optional<Category> categoryTocount = catrepository.findById("Romance");
		if (categoryTocount.get().getCantidad()>0) {
			List<Book> bookslist= new ArrayList<Book>();
			List<Category> categoryList = new ArrayList<Category>();
			bookslist = repository.findAllBycategory("Romance");
			categoryList = catrepository.findAll();	
			ArrayList<Object> lista = new ArrayList<>();
			lista.add(0, bookslist);
			lista.add(1, categoryList);
			Map<List<Book>, List<Category>> map = new HashMap<>();
			map.put(bookslist, categoryList);
			this.template.convertAndSend("/topic/Romance",lista );
		}

	}
	@Scheduled(fixedRate = 5000)
	@Transactional
	public void fiction() throws Exception {
		Optional<Category> categoryTocount = catrepository.findById("Fiction");
		if (categoryTocount.get().getCantidad()>0) {
		List<Book> bookslist= new ArrayList<Book>();
		List<Category> categoryList = new ArrayList<Category>();
		bookslist = repository.findAllBycategory("Fiction");
		categoryList = catrepository.findAll();	
		ArrayList<Object> lista = new ArrayList<>();
		lista.add(0, bookslist);
		lista.add(1, categoryList);
		Map<List<Book>, List<Category>> map = new HashMap<>();
		map.put(bookslist, categoryList);
		this.template.convertAndSend("/topic/Fiction",lista );
		}
	}
	@Scheduled(fixedRate = 5000)
	@Transactional
	public void adventure() throws Exception {
		Optional<Category> categoryTocount = catrepository.findById("Adventure");
		if (categoryTocount.get().getCantidad()>0) {
		List<Book> bookslist= new ArrayList<Book>();
		List<Category> categoryList = new ArrayList<Category>();
		bookslist = repository.findAllBycategory("Adventure");
		categoryList = catrepository.findAll();	
		ArrayList<Object> lista = new ArrayList<>();
		lista.add(0, bookslist);
		lista.add(1, categoryList);
		Map<List<Book>, List<Category>> map = new HashMap<>();
		map.put(bookslist, categoryList);
		this.template.convertAndSend("/topic/Adventure",lista );
		}
	}
	@Scheduled(fixedRate = 5000)
	@Transactional
	public void economy() throws Exception {
		Optional<Category> categoryTocount = catrepository.findById("Economy");
		if (categoryTocount.get().getCantidad()>0) {
		List<Book> bookslist= new ArrayList<Book>();
		List<Category> categoryList = new ArrayList<Category>();
		bookslist = repository.findAllBycategory("Economy");
		categoryList = catrepository.findAll();	
		ArrayList<Object> lista = new ArrayList<>();
		lista.add(0, bookslist);
		lista.add(1, categoryList);
		Map<List<Book>, List<Category>> map = new HashMap<>();
		map.put(bookslist, categoryList);
		this.template.convertAndSend("/topic/Economy",lista );
		}
	}
	@Scheduled(fixedRate = 5000)
	@Transactional
	public void nature() throws Exception {
		Optional<Category> categoryTocount = catrepository.findById("Nature");
		if (categoryTocount.get().getCantidad()>0) {
		List<Book> bookslist= new ArrayList<Book>();
		List<Category> categoryList = new ArrayList<Category>();
		bookslist = repository.findAllBycategory("Nature");
		categoryList = catrepository.findAll();	
		ArrayList<Object> lista = new ArrayList<>();
		lista.add(0, bookslist);
		lista.add(1, categoryList);
		Map<List<Book>, List<Category>> map = new HashMap<>();
		map.put(bookslist, categoryList);
		this.template.convertAndSend("/topic/Nature",lista );
		}
	}
}
