# 📚 Library Management System - Documentation


## 🛢 MySQL database - Entities
![DB schema](https://github.com/MattiaIojica/LibraryManagementSystem/blob/main/diagram.jpeg?raw=true)

<details>
  <summary> <h2> 🌐 REST ENDPOINTS - CRUD </h2> </summary>
<!-- ## 🌐 REST ENDPOINTS - CRUD -->

### CREATE
  1. Create a User
  2. Create a Book
  3. Create a Reservation
  4. Create a Loan
  5. Create a Fine
  6. Create an Address

### READ
  1. Get all Users
  2. Get User by ID
  3. Get Reservation of the user with the specific ID
  4. Get Loans of the user with the specific ID
  5. Get Fines of the user with the specific ID
  6. Get all Books
  7. Get Book by ID 
  8. Get all Reservations
  9. Get Reservation by ID
  10. Get all Loans
  11. Get Loan by ID
  12. Get all Fines
  13. Get Fine by ID
  14. Get all Addresses
  15. Get Address by ID
   
### UPDATE
  1. Update User details
  2. Update Book details
  3. Update Reservation details
  4. Update Loan details
  5. Update Fine details
  6. Update Address details

### DELETE
  1. Deleting a User and related Address
  2. Cancellation of a Reservation
  3. Delete a Loan
  4. Delete a Fine
  5. Delete a Book
  6. Delete an Address

</details>

## ✅ Testing

UNIT tests for REST endpoints and services with all tests passed.


```Java
  public class BookControllerUnitTest {

      private MockMvc mockMvc;
      @Mock
      private BookService service;
      @InjectMocks
      private BookController controller;
  
      ...
  
      @Test
      public void testGetAllBooks() throws Exception {
          List<BookDto> dtoList = getDummyDtos();
          Mockito.when(service.getAllBooks()).thenReturn(dtoList);
  
          mockMvc.perform(MockMvcRequestBuilders
                          .get("/books"))
                  .andExpect(status().isOk())
                  .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(dtoList)));
      }
  ...
}
```

```Java
  public class BookServiceUnitTest {

    @Mock
    private BookRepository repository;
    @InjectMocks
    private BookService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteAddress_SuccessfulDeletion() {
        Long idToDelete = 1L;

        when(repository.existsById(idToDelete)).thenReturn(true);

        boolean result = service.deleteBook(idToDelete);

        assertTrue(result); // Deletion should be successful
        verify(repository, times(1)).deleteById(idToDelete);
    }
  ...
}
```

## Controllers 

- UserController /users
- BookController /books
- LoanController /loans
- FineController /fines
- ReservationController /reservations
- AddressController /addresses

```Java
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @Operation(summary = "Create a book",
            description = "Returns the new book")
    public ResponseEntity<BookDto> createBook(@RequestBody @Valid BookDto bookDto) {
        ...
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book",
            description = "Update a book's information based on the provided book ID")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id,
                                              @RequestBody @Valid BookDto bookDto) {
        BookDto updatedBook = bookService.updateBook(id, bookDto);
        ...
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book",
            description = "Delete a book by id from the database")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        ...
    }
  ...
}
```
  
## Services

- UserService 
- BookService 
- LoanService 
- FineService  
- ReservationService  
- AddressService  

```Java
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository,
                       BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public BookDto createBook(BookDto bookDto) {
        ...
    }

    public BookDto updateBook(Long id,
                              BookDto bookDto) {
       ...
    }

    public boolean deleteBook(Long id) {
        ...
    }
  ...
}
```

## Repositories
- Each entity has an associated interface that extends *JpaRepository<Entity, Type_ID>* where the methods that were not in the basic interface were defined (those already existing: findById, findAllById, delete, save, ...)
```Java
@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {

    List<User> findAllByAddress_Street(String street);

    @Query( nativeQuery = true, value = "select * from user where firstName = :name")
    User findUserByFirstNameWithNativeQuery(String name);

    Optional<User> findByEmail(String email);
}
```

## Model mapper 
- The classes of type *@Component* with the purpose of mapping request-type entities into entities used as a model in the database
```Java
@Component
public class UserMapper {

    private final AddressMapper addressMapper;

    public UserMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public List<UserDto> mapListToStudentDto(List<User> users){
        return users.stream().map(this::mapToUserDto).collect(Collectors.toList());
    }

    public User mapToUser(UserDto userDto) {
        ...
    }

    public UserDto mapToUserDto(User user) {
        ...
    }
}
```

## DTOs
- Each entity of type *EntityNameDto* contains all fields validations according to type
```Java
public class UserDto {

    @Schema(accessMode = READ_ONLY)
    private Long id;

    @NotNull(message = "First name is mandatory.")
    @NotBlank(message = "First Name must have a value.")
    @Schema(name = "firstName", example = "John")
    private String firstName;

    @NotNull(message = "Last name is mandatory.")
    @NotBlank(message = "Last Name must have a value.")
    @Schema(name = "lastName", example = "Smith")
    private String lastName;

    @NotNull(message = "Email is mandatory.")
    @Email(message = "Email should be valid.")
    @Schema(name = "email", example = "email@example.com")
    private String email;

  ...
}
```

## 💻 SWAGGER

```
http://localhost:8081/swagger-ui/index.html
```
![SWAGGER](https://github.com/MattiaIojica/LibraryManagementSystem/blob/main/Swagger.png?raw=true)

Contains information for each API

```Java
@GetMapping("/{id}")
    @Operation(summary = "Get a User by id", description = "Returns a user as per the id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - The User was not found")
    })
    public ResponseEntity<UserDto> getById(@PathVariable @Parameter(example = "1") Long id) {
        return ResponseEntity.ok().body(userService.getById(id));
    }
```








