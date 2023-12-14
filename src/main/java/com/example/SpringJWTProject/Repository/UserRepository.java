package com.example.SpringJWTProject.Repository;

import com.example.SpringJWTProject.Model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    //SELECT* FROM USER WHERE email=:mail
    @Query("{email:\"?0\"}")
    List<User>getUserByEmail(String mail);

    //SELECT *  FROM USER WHERE id=:id
    @Query("{id:?0}")
    Optional<User> getUserById(ObjectId id);

    //SELECT * FROM USER WHERE age<:age
    @Query("{age:{$lt:?0}}")
    List<User> getUserofLessThanParticularAge(int age);

    //SELECT * FROM USER WHERE name=:name Or email=:email
    @Query("{$or:[{name:?0},{email:?1}]}")
    List<User>  getUserByNameOrEmail(String name, String email);

    //SELECT * FROM USER WHERE age=:age ORDERBY name ASC
    @Query(value="{age:?0}",sort="{name:1}")//1 for asc and -1 for desc
    List<User> getUserBYAgeSortByName(int age);
}
