package org.clipper.accessdb;

import java.util.Optional;

import org.clipper.websecurity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController // This means that this class is a Controller
@RequestMapping(path = "/api") // This means URL's start with /demo (after Application path)
public class MainController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @Autowired
    private LinksRepository linksRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private CollectionUsersRepository collectionUsersRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;

    /*
     * @ResponseStatus(HttpStatus.BAD_REQUEST)
     *
     * @ExceptionHandler(DataIntegrityViolationException.class)
     *
     * @ResponseBody ErrorInfo conflict(HttpServletRequest req, Exception ex) {
     * return new ErrorInfo(req.getRequestURI(), ex);
     * }
     */

    @PostMapping(path = "/adduser") // Map ONLY POST Requests
    public @ResponseBody String addNewUser(@RequestParam String name, @RequestParam String pass) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User(name, pass);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/isuser")
    public @ResponseBody String isUser(@RequestParam String name) {
        return Boolean.toString(userRepository.existsById(name));
    }

    @GetMapping(path = "/allusers")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @GetMapping(path = "/getowncollections")
    public @ResponseBody Iterable<LinkCollection> getPvtCollections(@RequestParam String username) {
        var u = userRepository.findById(username);
        if (!u.isPresent())
            throw new GenericNotFoundException(String.format("user '%s' doesn't exist", username));
        return collectionRepository.findByCreatorId(u.get());
    }

    @GetMapping(path = "/getothercollections")
    public @ResponseBody Iterable<LinkCollection> getAllCollections(@RequestParam String username) {
        var u = userRepository.findById(username);
        if (!u.isPresent())
            throw new GenericNotFoundException("user doesn't exist");

        return collectionUsersRepository.findByUserId(u.get());
    }

    @PostMapping(path = "/addcollection")
    public @ResponseBody String addNewCollection(@RequestParam String name, String user_id, String access) {
        Optional<User> u = userRepository.findById(user_id);
        colAccess acc;

        if (!u.isPresent())
            throw new GenericNotFoundException(String.format("user '%s' doesn't exist", user_id));
        try {
            acc = colAccess.valueOf(access);
        } catch (IllegalArgumentException e) {
            return "invalid access modifier";
        }

        LinkCollection col = new LinkCollection(u.get(), name, acc);
        collectionRepository.save(col);
        collectionUsersRepository.save(new CollectionUsers(u.get(), col));
        return "Saved";
    }

    @PostMapping(path = "/addaccess")
    public @ResponseBody String addNewUserAccess(@RequestParam Integer colId, @RequestParam String username) {
        var c = collectionRepository.findById(colId);
        var u = userRepository.findById(username);

        if (!c.isPresent() || !u.isPresent())
            throw new GenericNotFoundException("user or collection doesn't exist");

        var coluser = new CollectionUsers(u.get(), c.get());
        collectionUsersRepository.save(coluser);
        return "Success";
    }

    @PostMapping(path = "/addlink")
    public @ResponseBody String addNewLink(@RequestParam Integer colId, @RequestParam String name,
            @RequestParam String url, @RequestParam String date, @RequestParam String type,
            @RequestParam Optional<String> category) {
        var col = collectionRepository.findById(colId);
        if (!col.isPresent())
            throw new GenericNotFoundException("collection doesn't exist");

        Link link = new Link(col.get(), name, url, date, type);
        linksRepository.save(link);
        if (category.isPresent()) {
            var cat = new Category(link, category.get());
            categoryRepository.save(cat);
        }

        return "Saved";
    }

    @PostMapping(path = "/addcategory")
    public @ResponseBody String addNewCategory(@RequestParam Integer linkId, String category) {
        var link = linksRepository.findById(linkId);
        if (!link.isPresent())
            throw new GenericNotFoundException("link doesn't exist");

        categoryRepository.save(new Category(link.get(), category));
        return "Saved";
    }

    @GetMapping(path = "/getlinks")
    public @ResponseBody Iterable<Link> getLinks(@RequestParam Integer colId) {
        var col = collectionRepository.findById(colId);
        if (!col.isPresent())
            throw new GenericNotFoundException("Collection doesn't exist");
        return linksRepository.findByColId(col.get());
    }

    @PostMapping(path = "/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest)
      throws Exception {
        try {
            authenticationManager.authenticate(
                // standard token spring mvc uses for username and password
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsServiceImpl
                .loadUserByUsername(authRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}

