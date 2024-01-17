# 📚 Library Management System - Documentation


## Project Overview:
<h3> The project aims to create a comprehensive library management system that facilitates 
  efficient management of users, books, loans, fines, and reservations.</h3>

## Business Requirements:

1. **User Registration and Management:**
   - The system allows adding, modifying, and displaying users.
2. **Book Management:**
   - The system allows adding, modifying, and displaying books.
3. **Loan Management:**
   - The system allows adding, modifying, and displaying loans.
4. **Reservation Management:**
   - The system allows adding, modifying, and displaying reservations.
5. **Fine Management:**
   - The system allows adding, modifying, and displaying fines.
6. **Deletion:**
   - The system allows users to be deleted.
   - The system allows books to be deleted.
   - The system allows loans, reservations, and fines to be deleted.
7. **Fines Displaying:**
   - The system allows the display of a user's fines.
8. **Loans Displaying:**
   - The system allows the display of a user's loans.
9. **Loans Reservations:**
   - The system allows the display of a user's reservations.
10. **Database Storage:**
    - The system uses a database to store information about users, books, fines, loans, and reservations.

## MVP Features:

### 1. User Management:

The system should allow administrators to manage user information, including registration, retrieval, update, and deletion of user profiles.

#### Key Functionalities:

- User registration with essential details (firstName, lastName, email).
- View and edit existing user profiles.
- Delete user accounts if needed.


### 2. Book Management:
Description:
The system should enable efficient handling of the library's book inventory, covering aspects like adding new books, updating book details, and removing books from circulation.

#### Key Functionalities:

- Add new books to the library inventory with details such as title, author, and ISBN.
- Update existing book information.


### 3. Loan Management:
Description:
The system should support the process of loaning books to users and managing the return of borrowed items, including tracking due dates and fines.

#### Key Functionalities:

- Loan books to users, associating them with due dates.
- Record book returns and calculate fines for overdue items.
- Display a user's active loans.


### 4. Reservation System:
Description:
The system should allow users to reserve books that are currently unavailable, providing a queue system for popular titles.

#### Key Functionalities:

- Users can place reservations for books that are currently on loan or unavailable.
- Display a user's reservations.


### 5. Fine Management:
Description:
The system should handle fines accrued by users for overdue book returns, allowing administrators to manage and users to view their fines.

#### Key Functionalities:

- Automatically calculate fines based on due dates for returned books.
- Display a user's current fines.
- Admins can view and manage fines, including marking them as paid.


## 🛢 MySQL database - Entities
![DB schema](https://github.com/MattiaIojica/LibraryManagementSystem/blob/main/diagram.jpeg?raw=true)

## 🌐 REST ENDPOINTS
<details>
  <summary> <h3>CRUD</h3> </summary>
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
  3. Get the Reservation of the user with the specific ID
  4. Get Loans of the user with the specific ID
  5. Get Fines of the user with the specific ID
  6. Get all Books
  7. Get Book by ID 
  8. Get all Reservations
  9. Get a Reservation by ID
  10. Get all Loans
  11. Get a Loan by ID
  12. Get all Fines
  13. Get Fine by ID
  14. Get all Addresses
  15. Get the Address by ID
   
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
- Each entity of type *EntityNameDto* contains all field validations according to type
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
