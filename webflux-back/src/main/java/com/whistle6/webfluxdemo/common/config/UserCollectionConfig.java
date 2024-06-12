// package com.whistle6.webfluxdemo.common.config;

// import java.util.Arrays;
// import java.util.ArrayList;
// import java.util.stream.IntStream;

// import org.bson.BsonDocument;
// import org.bson.BsonInt64;
// import org.bson.Document;
// import org.bson.conversions.Bson;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.mongodb.ConnectionString;
// import com.mongodb.MongoClientSettings;
// import com.mongodb.MongoException;
// import com.mongodb.ServerApi;
// import com.mongodb.ServerApiVersion;
// import com.mongodb.client.MongoClient;
// import com.mongodb.client.MongoClients;
// import com.mongodb.client.MongoCollection;
// import com.mongodb.client.MongoDatabase;
// import com.whistle6.webfluxdemo.user.domain.UserModel;
// import com.whistle6.webfluxdemo.user.repository.UserRepository;

// import lombok.extern.slf4j.Slf4j;
// import reactor.core.publisher.Mono;

// @Slf4j
// @Configuration
// public class UserCollectionConfig {
//     @Value("${spring.data.mongodb.uri}")
//     private String uri;

//     @Bean
//     CommandLineRunner initUser(UserRepository repo){
//         log.info("실행");
//         ServerApi serverApi = ServerApi.builder()
//             .version(ServerApiVersion.V1)
//             .build();
//         MongoClientSettings settings = MongoClientSettings.builder()
//                 .applyConnectionString(new ConnectionString(uri))
//                 .serverApi(serverApi)
//                 .build();
//         try (MongoClient mongoClient = MongoClients.create(settings)) {
//             System.out.println(" DB 접속 ...");
//             MongoDatabase database = mongoClient.getDatabase("fluxdb");
//             System.out.println(" users 컬렉션 접속 ...");
//             try {
//                 // Send a ping to confirm a successful connection
//                 Bson command = new BsonDocument("ping", new BsonInt64(1));
//                 Document commandResult = database.runCommand(command);
//                 System.out.println(" 핑 연 결 ...");
//                 System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
//                 System.out.println(commandResult);
                
                
//                 // MongoCollection<Document> collection = database.getCollection("users");
//                 // Document user = new Document()
//                 //         .append("firstName", "Joe")
//                 //         .append("lastName", "Smith")
//                 //         .append("email", "joe@test.com")
//                 //         .append("yearsOfService", 3)
//                 //         .append("skills", Arrays.asList("java", "spring", "mongodb"))
//                 //         .append("manager", new Document()
//                 //                 .append("firstName", "Sally")
//                 //                 .append("lastName", "Johanson"));
//                 // collection.insertOne(user);

//                 //
//                 // 4.3 Find documents
//                 //

//                 // Document query = new Document("lastName", "Smith");
//                 // var results = new ArrayList<>();
//                 // collection.find(query).into(results);

//                 // query = new Document("$or", Arrays.asList(
//                 //         new Document("lastName", "Smith"),
//                 //         new Document("firstName", "Joe")));
//                 // results = new ArrayList<>();
//                 // collection.find(query).into(results);

//                 //
//                 // 4.4 Update document
//                 //

//                 // query = new Document(
//                 //         "skills",
//                 //         new Document(
//                 //                 "$elemMatch",
//                 //                 new Document("$eq", "spring")));
//                 // Document update = new Document(
//                 //         "$push",
//                 //         new Document("skills", "security"));
//                 // collection.updateMany(query, update);

//                 //
//                 // 4.5 Delete documents
//                 //

//                 // query = new Document(
//                 //         "yearsOfService",
//                 //         new Document("$lt", 0));
//                 // collection.deleteMany(query);
//                 // System.out.println(" >>>> 8 컬렉션 생성 확인 >>>>");
//             } catch (MongoException me) {
//                 // System.out.println(" >>>> 9 에러 발생 >>>>");
//                 // System.err.println(me);
//             }
//         }

//         return args -> {
//             IntStream.range(0, 1).forEach(i -> {
//                 Mono<UserModel> userMono = Mono.just(UserModel.builder()
//                         .email(i + "@test.com")
//                         .firstName("James" + i)
//                         .lastName("Byden " + i)
//                         .password("aaa")
//                         .roles(null)
//                         .build());

//                 repo.insert(userMono);
//             });
//         };
//     }
// }
