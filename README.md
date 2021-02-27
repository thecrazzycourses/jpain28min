# JPA

#EntityManager
1. EntityManager Persist & Update

    <p>Course course = new Course("New Course");
    em.persist(course); // Insert Query
    course.setName("New Course Updated"); // Update Query</p>

<ul>
    <li> First EntityManager will save new course, then it find with in the same
    transaction we are updating it so it will update it.
    <li> Above method is in a single transaction as we use @Transactional
    <li> EntityManager will keep track all the changes
</ul>

2. EntityManager Clear, there are 4 steps in this transaction (assume detach is not there)

    
    Course course1 = new Course("New Course");
    em.persist(course1); // Hibernate will get the ID for Course 1
    em.flush() // Changes till this point will sent out to db

    course1.setName("New Course Updated"); // Update Query
    em.flush() // Changes till this point will sent out to db

    Course course2 = new Course("New Course 2");
    em.persist(course2); // Hibernate will get the ID for Course 2
    em.flush() // Changes till this point will sent out to db
   
    em.detach(course2) // This will detach course2 from em
   
    course2.setName("New Course 2 Updated"); // Update Query
    em.flush() // Changes till this point will sent out to db
   


Insert course1
Update course1
Insert course2
Detach
Update won't call for course 2
    
    
    Course course1 = new Course("New Course");
    em.persist(course1); // Hibernate will get the ID for Course 1
    Course course2 = new Course("New Course 2");
    em.persist(course2); // Hibernate will get the ID for Course 2
    em.flush() // Changes till this point will sent out to db

    em.clear() // This will detach all objects

    course1.setName("New Course Updated"); // Update Query
    em.flush() // Changes till this point will sent out to db
   
    course2.setName("New Course 2 Updated"); // Update Query
    em.flush() // Changes till this point will sent out to db

Insert course1
Insert course2
clear
Update won't call for both course 1 & 2


2. EntityManager Refresh


    Course course1 = new Course("New Course");
    em.persist(course1); // Hibernate will get the ID for Course 1
    Course course2 = new Course("New Course 2");
    em.persist(course2); // Hibernate will get the ID for Course 2
    em.flush() // Changes till this point will sent out to db

    course1.setName("New Course Updated"); // Update Query
    course2.setName("New Course 2 Updated"); // Update Query

    em.refersh(course1) // Content of course 1 will be refresh/fetch from db, and persistence context will be updated with db
    em.flush() // Changes till this point will sent out to db only for course 2 as course 1 matches db 
    
On Refresh course 1 data will be fetched from db & update the object for course 1, this means last
state of course 1 object will be override and on flush only course 2 will be save to db.

Note: ALl the entities above with a transaction save by entity manager are saved to persistence context
persistence context will keep track of different object and there changes

3. EntityManager with persist and update both


      Course course = new Course();
      course.setName("Testing");
      entityManager.persist(course);
   
      course.setName("Testing 2");

First hibernate sequence will run to find the next available id on persist()
Then course object will be created in memory with new id fetch from db
Then insert query will be fired
Then update query will be fired all in a single transaction (at the end of method)

Insertable False : Won't allow inserts, give error constraint violation
Updatable False : Won't allow updates on that column for entity, no error as update won't run

4. Named Queries are
   <ul>
   <li>compiled and validated at app start-up time
   <li>easier to maintain than string literals embedded in your code
   <li>HQL and native SQL queries can be used and replaced without code changes (no need to re-compile your code)

So, I think you should definitely prefer named queries over string literals in your code. When you need some kind of dynamic query creation at runtime you should take a look at the Hibernate Criteria API.

5. Native Queries : Performance & Database specific operation not provided by JPA & Mass Update
   While using native queries we are not using persistence context.
   
6. Relationship: Course Student Passport Review
   Course ----1-Many---- Student
   Course ----1-Many---- Review
   Student ----1-1---- Passport
   

# One To One : Student & Passport

Hibernate:

    create table passport (
       id bigint not null,
        number varchar(255),
        primary key (id)
    )
Hibernate:

    create table student (
       id bigint not null,
        name varchar(255),
        passport_id bigint,
        primary key (id)
    )

Hibernate:

    alter table student 
       add constraint FK6i2dofwfuu97njtfprqv68pib 
       foreign key (passport_id) 
       references passport

TransientPropertyValue Exception : As we are not saving passport entity ( it's a transient entity )

      Passport passport = Passport.builder().number("E12345").build();
      Student student = Student.builder().name("Kuldeep").passport(passport).build();
      em.persist(student);

Solution :
      
      Passport passport = Passport.builder().number("E12345").build();
      em.persist(passport);

      Student student = Student.builder().name("Kuldeep").passport(passport).build();
      em.persist(student);


LazyInitializationException : if Student has, @OneToOne(fetch = FetchType.LAZY)

      Student student = studentRepository.findById(2001L);
      log.info("Student -> {}", student);
      log.info("Student Passport -> {}", student.getPassport());

Solution : Put this method under @Transactional so that we still have session to query Passport
   
      Student student = studentRepository.findById(2001L);
      log.info("Student -> {}", student);
      log.info("Student Passport -> {}", student.getPassport());
      
      Hibernate: 
    select
        student0_.id as id1_3_0_,
        student0_.name as name2_3_0_,
        student0_.passport_id as passport3_3_0_ 
    from
        student student0_ 
    where
        student0_.id=?
      
      ---------------------------------------------------------------------------

      Hibernate: 
    select
        passport0_.id as id1_1_0_,
        passport0_.number as number2_1_0_ 
    from
        passport passport0_ 
    where
        passport0_.id=?
Note : 
1. On persist() call hibernate will call sequence and get next available id.
   At the end of method it will persist it to db.
2. OneToOne is always eager
3. EntityManager talks to Persistence Context
4. Method within @Transactional will either succeed or fails.
5. Method within @Transactional start Persistence Context and at method end, persistence context close.


Bidirectional Relationship OneToOne:
1. Once we have @OneToOne on Student and Passport both
   we will going to have Student Table with Passport ID and Passport Table with Student ID
2. Mapped by is always added to other than owning side of relationship
   ex Student has owning side then mapped by will come in Passport
   This will going solve issue with data duplication in point 1 
   We will going to have Student with Passport ID only.
3. @DirtiesContext : will rollback once test is done for a method
