package com.heathcliff.app.ws.ui.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.heathcliff.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.heathcliff.app.ws.ui.model.request.UserDetailsRequestModel;
import com.heathcliff.app.ws.ui.model.response.UserRest;
import jakarta.validation.Valid;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("user")//http://localhost:8080/users
public class UserController {
    Map<String,UserRest>users;
//Retrieve DAta from the server
    @GetMapping
    public String getUsers(@RequestParam(value="page", defaultValue="1") int page,
                                           @RequestParam(value="limit" ,defaultValue="60") int limit,
                                           @RequestParam(value="sort",required =false ) String sort  
                                           ){
        return "get user was called with page ="+page +  " limit = "+limit + " & sort = "+sort;
    }
     @GetMapping(path="/{userId}",
                              produces={
                                MediaType.APPLICATION_XML_VALUE,
                                MediaType.APPLICATION_JSON_VALUE
                            })
    public ResponseEntity<UserRest> getUser(@PathVariable String userId){
        if(users.containsKey(userId)){
            return new ResponseEntity<UserRest>(users.get(userId),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT);
        }
    } 

    //create POST
 
    @PostMapping(consumes={
                                 MediaType.APPLICATION_XML_VALUE,
                                 MediaType.APPLICATION_JSON_VALUE
                            },
                            produces={
                                 MediaType.APPLICATION_XML_VALUE ,
                                 MediaType.APPLICATION_JSON_VALUE
                            })
    public ResponseEntity<UserRest> createUser(@Valid@RequestBody UserDetailsRequestModel userDetails ){
          UserRest returnValue=new UserRest() ;
          returnValue.setEmail(userDetails.getEmail());
          returnValue.setFirstName(userDetails.getFirstName());
          returnValue.setLastName(userDetails.getLastName());
          returnValue.setPassword(userDetails.getPassword());
          String userId=UUID.randomUUID().toString();
          returnValue.setUserId(userId);
          if(users==null)users=new HashMap<>();
          users.put(userId,returnValue);
          return new ResponseEntity<UserRest>( returnValue,HttpStatus.OK);
    }


//Update PUT

    @PutMapping(path="/{userId}",
                            consumes = {
                                MediaType.APPLICATION_JSON_VALUE,
                                MediaType.APPLICATION_XML_VALUE
                             },
                             produces={
                                MediaType.APPLICATION_XML_VALUE,
                                MediaType.APPLICATION_JSON_VALUE
                             }
                            )
    public UserRest updateUser(@PathVariable String userId,@Valid@RequestBody UpdateUserDetailsRequestModel userDetails){
        UserRest storedUserDetails=users.get(userId);
        storedUserDetails.setFirstName(userDetails.getFirstName());
        users.put(userId,storedUserDetails);
        return storedUserDetails;
    }


//DELETE
    @DeleteMapping(path="/{Id}")
    public  ResponseEntity<Void> deleteUser(@PathVariable String Id){
        users.remove(Id);
        return ResponseEntity.noContent().build();
    }
}
